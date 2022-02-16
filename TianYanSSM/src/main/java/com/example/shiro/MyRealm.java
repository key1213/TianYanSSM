package com.example.shiro;

import com.example.mapper.EmployeesMapper;
import com.example.mapper.PermissionMapper;
import com.example.mapper.RoleMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {

    RoleMapper roleMapper;

    PermissionMapper permissionMapper;

    EmployeesMapper employeesMapper;

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }
    @Autowired
    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }
    @Autowired
    public void setEmployeesMapper(EmployeesMapper employeesMapper) {
        this.employeesMapper = employeesMapper;
    }

    public String getPasswordByUserName(String name){
        return employeesMapper.selectPasswordByName(name);
    }



    @Override//认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken= (UsernamePasswordToken)token;
        String username = upToken.getUsername();
        System.out.println("传过来的用户名"+username);
        String password = getPasswordByUserName(username);
        if(password == null){
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, "myRealm");
        //加盐
        info.setCredentialsSalt(ByteSource.Util.bytes("admin"));
        return info;
    }


    public Set<String> getRolesByUserName(String userName){
        List<String> strings = roleMapper.selectRoleNameByName(userName);
        for (String string : strings) {
            System.out.println("职位:"+string);
        }
        return new HashSet<>(strings);
    }

    public Set<String> getPermissionByUserName(String userName){
        List<String> strings = permissionMapper.selectPermissionByName(userName);
        for (String string : strings) {
            System.out.println("权限:"+string);
        }
        return new HashSet<>(strings);
    }

    @Override//授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) getAvailablePrincipal(principals);
        System.out.println("要检查的用户名"+username);
        Set<String> roles = getRolesByUserName(username);
        Set<String> permission = getPermissionByUserName(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permission);
        return info;
    }
}
