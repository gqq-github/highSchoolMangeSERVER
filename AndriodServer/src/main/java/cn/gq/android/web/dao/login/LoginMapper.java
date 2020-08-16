package cn.gq.android.web.dao.login;

import cn.gq.android.web.entity.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoginMapper {
    // login 表
    int addLoginUser(Login login);
    // 多表查询，
    List<Map> getRolePermByRole(@Param(value = "role") int role);
  // 查询login表
    Map<String,Object> getUserByUserId( @Param("userId") String userId);
   //获取用户的基本信息
    Map<String, Object> getUserInfo(@Param("userId") String userId, @Param("role") int role);
}
