package cn.lb.gateway.auth;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SaToken配置
 *
 * @author RainSoul
 * @create 2024-09-08
 */
// 配置类，用于配置SaToken相关的设置
@Configuration
public class SaTokenConfigure {

    /**
     * 配置SaReactorFilter用于处理WebFlux请求中的权限验证
     *
     * @return SaReactorFilter实例，用于拦截和鉴权请求
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        // 创建一个新的SaReactorFilter实例
        return new SaReactorFilter()
                // 拦截所有请求路径
                .addInclude("/**")
                // 设置鉴权方法，决定哪些请求需要进行权限验证
                .setAuth(obj -> {
                    // 打印前端访问的路径，便于调试和日志记录
                    System.out.println("-------- 前端访问path：" + SaHolder.getRequest().getRequestPath());
                    // 使用SaRouter进行路由匹配，定义不同路径的权限要求
                    // 注释掉的代码为示例，展示如何匹配和验证权限
//                    SaRouter.match("/auth/**", "/auth/user/doLogin", r -> StpUtil.checkRole("admin"));
                    // 要求对"/oss/**"路径的访问需要登录验证
                    SaRouter.match("/oss/**", r -> StpUtil.checkLogin());
                    // 对"/subject/subject/add"路径，要求用户具有"subject:add"权限
                    SaRouter.match("/subject/subject/add", r -> StpUtil.checkPermission("subject:add"));
                    // 对"/subject/**"路径下的其他访问，要求登录验证
                    SaRouter.match("/subject/**", r -> StpUtil.checkLogin());
                })
                ;
    }

}
