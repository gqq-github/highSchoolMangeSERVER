package cn.gq.android.web.dao.teacher;

import cn.gq.android.web.entity.TeacherSignInEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeacherSignInMapper {
    Integer findByEntity(TeacherSignInEntity signInEntity);
    Integer updateById(@Param("endTime") Long endTime,@Param("verifed") String verifed, @Param("keepTime") Long keepTime , @Param("id") Integer id);
    // 新建表数据
    Integer createTeacherSignIn(TeacherSignInEntity signInEntity) ;

}
