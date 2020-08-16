package cn.gq.android.web.service.login;

import cn.gq.android.web.entity.Login;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/3/1 14:23
 * @update 2020/3/1
 * Description:
 * @since 1.0.0
 */
public interface LoginService   {
   boolean registerUser (Login login)  ;
   List<Map> getRolePerm (int role);

   /***
    *
    * @param userId
    * @return
    * 返回用户的密码和角色
    */
   Map<String,Object> getUserByUserId(String userId);

   /**
    *
    * @param userId
    * @param role
    * @return 返回用户的基本信息
    */
   Map<String, Object> getUserByUserIdAndRole(String userId, int role);
}
