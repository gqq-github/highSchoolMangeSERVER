package cn.gq.android.web.service.impl.teacher;

import cn.gq.android.web.dao.homework.HomeWorkMapper;
import cn.gq.android.web.dao.teacher.TeacherMapper;
import cn.gq.android.web.dao.teacher.TeacherSignInMapper;
import cn.gq.android.web.entity.CheckHomework;
import cn.gq.android.web.entity.SendHomeWork;
import cn.gq.android.web.entity.Teacher;
import cn.gq.android.web.entity.TeacherSignInEntity;
import cn.gq.android.web.service.teacher.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/23 14:39
 * @update 2020/4/23
 * Description:
 * @since 1.0.0
 */
@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeacherMapper teacherMapper ;
    @Autowired
    private TeacherSignInMapper teacherSignInMapper ;
    @Autowired
    private HomeWorkMapper homeWorkMapper ;
    @Override
    public boolean addTeacher(Teacher teacher) {
      int i = teacherMapper.addTeacher(teacher);
        return i>0;
    }

    @Override
    public List<Map<String, Object>> showTeacherSelectCourse(String userId) {
     return   teacherMapper.selectedTeacherCourseById (userId);
    }

    @Override
    public boolean deleteTeacherSelectCourse(Integer id) {
        int i = teacherMapper.updateTeacherSelectCourse(id);

        return i>0;
    }

    @Override
    public boolean selectCourseToTeacher(Integer id, String userId) {
        int i = teacherMapper.updateTeacherId(id , userId);
        return i>0;
    }

    @Override
    public Integer createCourseSignIn(TeacherSignInEntity signInEntity) {
       Integer id = teacherSignInMapper.findByEntity (signInEntity);
        if(id==null) {
          id = teacherSignInMapper.createTeacherSignIn(signInEntity);
        } else {
            teacherSignInMapper.updateById(signInEntity.getEndTime(),signInEntity.getVerifed(),signInEntity.getKeepTime() ,id);
        }
        return id;
    }

    @Transactional
    @Override
    public Boolean sendHomeWork(SendHomeWork sendHomeWork) {
        Integer integer = homeWorkMapper.saveHomeWork(sendHomeWork);
        return integer>0;
    }

    @Override
    public List<Map<String, Object>> getStudentHomework(String header) {

        return   teacherMapper.seeSubmitHomeworkByTeacherId(header);
    }

    @Override
    public Boolean checkStudentsHomework(CheckHomework checkHomework) {
       int i =  teacherMapper.checkStudentHomework (checkHomework);
        return i > 0;
    }

    @Override
    public List<Map<String, Object>> checkStudentSignIn(String courseId, Integer courseType, Integer teacherSignInId) {
        return teacherMapper.checkStudentSignIn (courseId,courseType,teacherSignInId);
    }

    @Override
    public List<Map<String, Object>> getStudentHomework_1(String header) {

        return teacherMapper.seeSubmitHomeworkByTeacherId_1(header);
    }

    @Override
    public Boolean checkStudentsHomework_1(CheckHomework checkHomework) {
        int i =  teacherMapper.checkStudentHomework_1 (checkHomework);
        return i > 0;
    }
}
