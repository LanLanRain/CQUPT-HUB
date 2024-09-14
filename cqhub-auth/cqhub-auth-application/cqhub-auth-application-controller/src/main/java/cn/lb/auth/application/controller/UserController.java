package cn.lb.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.lb.auth.application.convert.AuthUserDTOConverter;
import cn.lb.auth.domain.entity.AuthUserBO;
import cn.lb.auth.domain.service.AuthUserDomainService;
import cn.lb.auth.entity.AuthUserDTO;
import cn.lb.auth.entity.Result;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RainSoul
 * @create 2024-09-11
 */
@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    /**
     * 用户注册接口
     *
     * @param authUserDTO 包含用户认证信息的请求体数据传输对象
     * @return 返回一个Result对象，其中包含布尔值表示注册是否成功
     */
    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        // 尝试执行用户注册流程
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.dto:{}", JSON.toJSONString(authUserDTO));
            }

            // 校验用户信息的合法性
            checkUserInfo(authUserDTO);

            // 将用户认证DTO对象转换为业务对象BO
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOTOBO(authUserDTO);

            // 调用领域服务进行用户注册，并返回注册结果
            return Result.success(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            // 记录用户注册过程中的异常信息
            log.error("UserController.register.error", e);

            // 返回注册失败的结果
            return Result.fail();
        }
    }

    private void checkUserInfo(AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(StringUtils.isNotBlank(authUserDTO.getUserName()), "用户名不能为空");
    }

    /**
     * 处理用户信息更新请求
     *
     * @param authUserDTO 包含用户信息的数据传输对象，通过请求体传递
     * @return 返回一个Result对象，其中包含一个布尔值，指示更新是否成功
     */
    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}", JSON.toJSONString(authUserDTO));
            }
            // 检查用户信息的合法性
            checkUserInfo(authUserDTO);
            // 将DTO对象转换为BO对象，以便于领域服务使用
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOTOBO(authUserDTO);
            // 调用领域服务执行更新操作，并将结果包装在Result对象中返回
            return Result.success(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update.error", e);
            return Result.fail("更新信息失败");
        }
    }

    /**
     * 通过用户信息获取用户详情
     *
     * @param authUserDTO 用户信息对象，包含用户名等信息
     * @return 包含用户详情的结果对象，成功时包含用户详情，失败时包含错误信息
     */
    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.getUserInfo.dto:{}", JSON.toJSONString(authUserDTO));
            }
            // 检查用户名是否为空，如果为空则抛出异常
            Preconditions.checkArgument(StringUtils.isNotBlank(authUserDTO.getUserName()), "用户名不能为空");

            // 将传入的用户信息对象转换为业务对象
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOTOBO(authUserDTO);
            // 调用领域服务获取用户详情
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            // 将查询到的用户详情转换为传输对象并封装在结果对象中返回
            return Result.success(AuthUserDTOConverter.INSTANCE.convertBOTODTO(userInfo));
        } catch (Exception e) {
            // 记录获取用户信息时发生的异常
            log.error("UserController.getUserInfo.error", e);
            // 返回失败的结果，包含错误信息
            return Result.fail("获取用户信息失败");
        }
    }

    /**
     * 处理用户删除请求
     *
     * @param authUserDTO 包含用户身份信息的数据传输对象，用于标识哪个用户
     * @return 删除操作的结果，成功或失败
     */
    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}", JSON.toJSONString(authUserDTO));
            }
            // 将DTO对象转换为BO对象，以便于领域服务使用
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOTOBO(authUserDTO);
            // 调用领域服务进行用户信息更新，返回操作结果
            return Result.success(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            // 记录删除操作中出现的异常错误日志
            log.error("UserController.delete.error", e);
            // 操作失败，返回失败结果
            return Result.fail("删除用户失败");
        }
    }

    /**
     * 用户登出功能
     *
     * @param username 用户名，用于标识需要登出的用户
     * @return 返回操作结果，成功时返回"退出成功"，失败时返回"退出失败"
     */
    @RequestMapping("logOut")
    public Result<String> logOut(@RequestBody String username) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.logOut.username:{}", username);
            }
            // 检查用户名是否为空或仅含空白字符，如果是，抛出异常
            Preconditions.checkArgument(StringUtils.isNotBlank(username), "用户名不能为空");
            // 执行用户登出操作
            StpUtil.logout(username);
            // 登出成功，返回成功结果
            return Result.success("退出成功");
        } catch (Exception e) {
            log.error("UserController.logOut.error", e);
            return Result.fail("退出失败");
        }
    }

    /**
     * 用户登录接口
     *
     * @param validCode 验证码，用于验证用户身份
     * @return 返回登录结果，包含用户token信息或错误提示
     */
    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.doLogin.validCode:{}", validCode);
            }
            // 检查验证码是否为空，如果为空则抛出异常
            Preconditions.checkArgument(StringUtils.isNotBlank(validCode), "验证码不能为空");
            // 如果验证码非空，则调用业务服务进行登录操作，并返回成功结果
            return Result.success(authUserDomainService.doLogin(validCode));
        } catch (Exception e) {
            // 如果登录过程中发生异常，则记录错误日志并返回失败结果
            log.error("UserController.doLogin.error", e);
            return Result.fail("用户登录失败");
        }
    }

    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    /**
     * 修改用户状态
     *
     * @param authUserDTO 包含用户状态的信息DTO
     * @return 返回一个Result对象，对象中包含一个布尔值，表示用户状态修改是否成功
     */
    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeStatus.dto:{}", JSON.toJSONString(authUserDTO));
            }
            // 校验用户状态不能为空
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            // 将DTO对象转换为BO对象
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTOTOBO(authUserDTO);
            // 调用领域服务进行用户状态的更新，并返回更新结果
            return Result.success(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            // 记录异常日志信息
            log.error("UserController.changeStatus.error:{}", e.getMessage(), e);
            // 返回操作失败的结果
            return Result.fail("启用/禁用用户信息失败");
        }
    }

}
