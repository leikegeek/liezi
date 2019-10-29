package org.liezi.modules.system.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.liezi.common.utils.RedisUtils;
import org.liezi.modules.system.dao.UserMapper;
import org.liezi.modules.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author: lake.lei
 * @date: 2019-03-21
 * @description:shiro认证
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;
    @Autowired
	private RedisUtils redisUtils;
    
    /**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User)principals.getPrimaryPrincipal();
		String userId = user.getUserId();
		Set<String> permsSet = new HashSet<>();
		Set<String>  permissions =  (Set<String>) redisUtils.get("permission_data_"+userId);
		String permissionFlag = (String)redisUtils.get("permission_flag_"+userId);
		if((null == permissions || permissions.isEmpty()) && (StringUtils.isNotBlank(permissionFlag) && permissionFlag.equals("Y"))){
			List<String> permsList = userMapper.queryAllPerms(userId);
			//用户权限列表
			for(String perms : permsList){
				if(StringUtils.isBlank(perms)){
					continue;
				}
				permsSet.addAll(Arrays.asList(perms.trim().split(",")));
			}
			redisUtils.set("permission_data_"+userId,permsSet);
			redisUtils.set("permission_flag_"+userId,"Y");
		}else{
			permsSet = permissions;
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
		AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		//查询用户信息
		User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", token.getUsername()));
		//账号不存在
		if(user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}
		//账号锁定
		if(user.getStatus() == 0){
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		return info;
	}

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
		shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
		shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
		super.setCredentialsMatcher(shaCredentialsMatcher);
	}
}
