package cn.gq.android.web.service.teacher;

import cn.gq.android.web.entity.CheckHomework;
import cn.gq.android.web.entity.SendHomeWork;
import cn.gq.android.web.entity.Teacher;
import cn.gq.android.web.entity.TeacherSignInEntity;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/23 14:38
 * @update 2020/4/23
 * Description:
 * @since 1.0.0
 */
public interface ITeacherService {
 boolean addTeacher(Teacher teacher) ;

    List<Map<String, Object>> showTeacherSelectCourse(String userId);

    boolean deleteTeacherSelectCourse(Integer id);

    boolean selectCourseToTeacher(Integer id, String userId);

    Integer createCourseSignIn(TeacherSignInEntity signInEntity);

    Boolean sendHomeWork(SendHomeWork sendHomeWork);

    List<Map<String, Object>> getStudentHomework(String header);

   Boolean checkStudentsHomework(CheckHomework checkHomework);

    List<Map<String, Object>> checkStudentSignIn(String courseId, Integer courseType, Integer teacherSignInId);

     // 实现获取图片的URI
    List<Map<String, Object>> getStudentHomework_1(String header);

    Boolean checkStudentsHomework_1(CheckHomework checkHomework);
}
