package cn.gq.android.core.config.shiro;

import cn.gq.android.core.commexception.ForbiddenException;
import cn.gq.android.core.content.AuthConstant;
import cn.gq.android.core.content.CacheConstant;
import cn.gq.android.web.service.login.LoginService;
import cn.gq.android.web.utils.CacheUtil;
import cn.gq.android.web.utils.JwtTokenUtil;
import cn.gq.android.web.utils.Utils;
import io.jsonwebtoken.Claims;
import lombok.extern.java.Log;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@SuppressWarnings("all")
@Log
public class StatelessRealm extends AuthorizingRealm {

    //    @Autowired
//    @Lazy
//    private UserService userService;
//
    @Autowired
    @Lazy
    private LoginService loginService;

    @Override
    public boolean supports(AuthenticationToken token) {
        /**
         * 仅支持StatelessToken 类型的Token，
         * 那么如果在StatelessAuthcFilter类中返回的是UsernamePasswordToken，那么将会报如下错误信息：
         * Please ensure that the appropriate Realm implementation is configured correctly or
         * that the realm accepts AuthenticationTokens of this type.StatelessAuthcFilter.isAccessAllowed()
         */
        return token instanceof StatelessToken;
    }

    /**
     * 认证方法
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    // 先认证后授权
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) token;
        // 获取前端传递过来的
        String userId = statelessToken.getUserId();
        int role = statelessToken.getRole();
        // 能够获取本token中的UserID
        // 秘钥
        Claims claims;
        claims = JwtTokenUtil.parseToken(((StatelessToken) token).getAuthToken(), AuthConstant.SECRETKET);

        if (userId.equals(claims.getSubject())) {
            //验证用户是否在缓存中，如果不存在则添加到缓存
            // todo : 可以加用户信息的缓存
            //  根据UserID来查询用户的基本信息
            // 以用户的id作为key,以所查询的结果为value
            // 验证数据的合法性
            Object o = CacheUtil.get(CacheConstant.CacheName_USERCACHE, userId);
            if (o == null) {
                Map<String, Object> userByUserId = loginService.getUserByUserIdAndRole(userId, role);
                if (userByUserId != null) {
                    CacheUtil.put(CacheConstant.CacheName_USERCACHE, userId, userByUserId);
                } else {
                    throw new ForbiddenException("用户不存在或已被删除！");
                }
            }
            //Object obj = CacheUtil.get(SystemCacheUtil.CACHE_USER, username);
            //  if(obj == null){
//                SysUser sysUser = this.userService.selectUserByUsername(username);
//                CacheUtil.put(SystemCacheUtil.CACHE_USER, username,sysUser);
//                if(sysUser == null){
//                    throw new ForbiddenException("用户不存在或已被删除！");
//                }
            // }
            //直接通过认证(
            return new SimpleAuthenticationInfo(
                    userId + ":" + role,
                    ((StatelessToken) token).getAuthToken(),
                    getName());
        } else {
            log.info("用户认证失败");
            return null;//throw new AuthenticationException("授权认证失败！");
        }

    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //根据用户名查找角色，请根据需求实现
        String[] split = ((String) principals.getPrimaryPrincipal()).split(":");
        String userId = split[0];
        int roleid = Integer.parseInt(split[1]);
        //todo  可以设计缓存来实现用户信息的保存
        // 缓存中获取 todo 修改用户权限的时候
        List<Map> rolesAndPerm = (List<Map>) CacheUtil.get(CacheConstant.CacheName_RoleCACHE, Utils.getRealRole(roleid));
        //  SysUser sysUser = (SysUser) CacheUtil.get(SystemCacheUtil.CACHE_USER, SystemCacheUtil.SYS_USER_USERNAME + username);
        if (rolesAndPerm == null) {
            rolesAndPerm = loginService.getRolePerm(roleid);
            CacheUtil.put(CacheConstant.CacheName_RoleCACHE,Utils.getRealRole(roleid),rolesAndPerm);
        }
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //管理员获取所有权限
        if (rolesAndPerm == null) {
            // 抛出异常
            return null;
        }
        if ("ADMIN".equals(rolesAndPerm.get(0).get("role"))) {
            info.addRole("ADMIN");
            info.addStringPermission("ADMIN");
            return info;
        }
        info.addRole((String) rolesAndPerm.get(0).get("role"));
        for (int i = 0; i < rolesAndPerm.size(); i++) {
            if( (rolesAndPerm.get(i).get("extuser")) !=null && (rolesAndPerm.get(i).get("extuser")).toString().equals(userId)) {
                info.addStringPermission((String) rolesAndPerm.get(i).get("perm"));
                continue;
            }
                info.addStringPermission((String) rolesAndPerm.get(i).get("perm"));
        }

//        List<SysResource> resourcesList = this.resourceService.listResourceByUserId(sysUser.getId());
//
//        if(resourcesList != null){
//            for(SysResource resource: resourcesList){
//                if (StringUtil.isNotBlank(resource.getPermission())){
//                    //添加基于Permission的权限信息
//                    for (String permission : StringUtil.split(resource.getPermission(),",")){
//                        info.addStringPermission(permission);
//                    }
//                }
//            }
//        }

        return info;
    }
}
