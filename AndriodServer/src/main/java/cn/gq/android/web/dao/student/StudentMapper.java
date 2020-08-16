package cn.gq.android.web.dao.student;

import cn.gq.android.web.entity.Student;
import cn.gq.android.web.entity.StudentCourseEntity;
import cn.gq.android.web.entity.StudentHomework;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/5/1 21:03
 * @update 2020/5/1
 * Description:
 * @since 1.0.0
 */
@Mapper
public interface StudentMapper {
    int updateStudentInfo(Student student);

    List<Map<String, Object>> selectStudentCourse(@Param("header") String header);

    List<Map<String, Object>> selectStudentCloudeSelectCourse(@Param("userId") String userId);

    int saveSelectCourse(@Param("studentCourseEntity") StudentCourseEntity studentCourseEntity,@Param("userId") String userId);

    int deleteCourse(@Param("id") Integer id);
    List<Map<String ,Object>> showStudentCourseTable (@Param("userId") String userId) ;

    int addHomework(StudentHomework studentHomework);

    List<Map<String, Object>> selectSawHomework(String studentId);

    List<Map<String, Object>> showStudentHomeWorkPipe(String studentId);

  // 实现学生作业的保存，使用URL来保存图片
    int addHomework_1(StudentHomework studentHomework);

    List<Map<String, Object>> selectSawHomework_1(String header);
}
