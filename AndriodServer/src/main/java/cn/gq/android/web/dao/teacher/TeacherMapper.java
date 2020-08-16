package cn.gq.android.web.dao.teacher;

import cn.gq.android.web.entity.CheckHomework;
import cn.gq.android.web.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/23 14:40
 * @update 2020/4/23
 * Description:
 * @since 1.0.0
 */
@Mapper
public interface TeacherMapper {
    int addTeacher(Teacher teacher);

    List<Map<String, Object>> selectedTeacherCourseById(@Param("userId") String userId);

    int updateTeacherSelectCourse(Integer id);
    // id  表示的teacher—_major_course中的id
    int updateTeacherId(@Param("id") Integer id,@Param("userId") String userId);

    List<Map<String, Object>> seeSubmitHomeworkByTeacherId(String teacherId);

    int checkStudentHomework(CheckHomework checkHomework);

    List<Map<String, Object>> checkStudentSignIn(@Param("courseId") String courseId,@Param("courseType") Integer courseType, @Param("tsId") Integer teacherSignInId);

    List<Map<String, Object>> seeSubmitHomeworkByTeacherId_1(String header);

    int checkStudentHomework_1(CheckHomework checkHomework);
}
