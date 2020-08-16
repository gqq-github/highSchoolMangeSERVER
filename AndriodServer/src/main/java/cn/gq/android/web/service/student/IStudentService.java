package cn.gq.android.web.service.student;

import cn.gq.android.web.entity.Student;
import cn.gq.android.web.entity.StudentCourseEntity;
import cn.gq.android.web.entity.StudentHomework;
import cn.gq.android.web.entity.StudentSignInEntity;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/5/1 20:44
 * @update 2020/5/1
 * Description:
 * @since 1.0.0
 */
public interface IStudentService {
    Boolean updateStudentInfo(Student student);

    List<Map<String, Object>> showStudentCourse(String header);

    List<Map<String, Object>> showSelectCourse(String userId);

    boolean saveCourse(StudentCourseEntity studentCourseEntity, String header);

    Boolean deleteStudentCourse(Integer id);

    List<Map<String, Object>> showCourseTable(String userId);

    Map <String,Object> studentSignIn (StudentCourseEntity entity,String userId);

    Boolean createSignIn(StudentSignInEntity signInEntity);

    List<Map<String,Object>> showHomework(String header);

    Boolean submitHomeWork(StudentHomework studentHomework);

    List<Map<String, Object>> sawHomework(String header);

    List<Map<String, Object>> showHomeWorkPipe(String header);

    Boolean submitHomeWork_1(StudentHomework studentHomework);

    List<Map<String, Object>> sawHomework_1(String header);
}
