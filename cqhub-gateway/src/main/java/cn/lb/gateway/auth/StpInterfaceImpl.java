package cn.lb.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限相关的方法实现类
 * 该类实现了StpInterface接口，主要用于提供权限和角色的相关信息
 *
 * @author RainSoul
 * @create 2024-09-08
 */
public class StpInterfaceImpl implements StpInterface {

    /**
     * 获取指定对象的权限列表
     * 该方法目前只返回一个固定的权限标识，具体对象参数未使用
     *
     * @param o 未使用的对象参数，预留
     * @param s 未使用的字符串参数，预留
     * @return 返回权限列表，包含一个权限标识
     */
    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> permissionList = new ArrayList<>();
        permissionList.add("subject:add");
        return permissionList;
    }

    /**
     * 获取指定对象的角色列表
     * 该方法目前只返回一个固定的角色名称，具体对象参数未使用
     *
     * @param o 未使用的对象参数，预留
     * @param s 未使用的字符串参数，预留
     * @return 返回角色列表，包含一个角色名称
     */
    @Override
    public List<String> getRoleList(Object o, String s) {
        List<String> roleList = new ArrayList<>();
        roleList.add("user");
        return roleList;
    }
}

