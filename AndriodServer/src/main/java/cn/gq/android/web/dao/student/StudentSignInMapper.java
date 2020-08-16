package cn.gq.android.web.dao.student;

import cn.gq.android.web.entity.StudentCourseEntity;
import cn.gq.android.web.entity.StudentSignInEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentSignInMapper {
Map<String,Object> findTeacherSignInByCourse (@Param("entity") StudentCourseEntity entity,@Param("userId") String userId) ;

Map<String,Object> findStudentSignInBySignInId(@Param("signInId") Integer signInId, @Param("userId") String userId, @Param("startTime") Long startTime );

    Integer createStudentSignIn(StudentSignInEntity signInEntity);

    Integer updateStudentSignIn(StudentSignInEntity signInEntity);
}
