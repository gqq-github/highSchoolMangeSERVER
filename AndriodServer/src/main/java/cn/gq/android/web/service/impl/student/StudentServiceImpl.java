package cn.gq.android.web.service.impl.student;

import cn.gq.android.web.dao.homework.HomeWorkMapper;
import cn.gq.android.web.dao.student.StudentMapper;
import cn.gq.android.web.dao.student.StudentSignInMapper;
import cn.gq.android.web.entity.Student;
import cn.gq.android.web.entity.StudentCourseEntity;
import cn.gq.android.web.entity.StudentHomework;
import cn.gq.android.web.entity.StudentSignInEntity;
import cn.gq.android.web.service.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/5/1 21:01
 * @update 2020/5/1
 * Description:
 * @since 1.0.0
 */
@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
   private StudentMapper studentMapper ;
    @Autowired
    StudentSignInMapper studentSignInMapper ;
    @Autowired
    HomeWorkMapper homeWorkMapper ;
    @Override
    @Transactional
    public Boolean updateStudentInfo(Student student) {
        int i = studentMapper.updateStudentInfo(student) ;
        return i>0;
    }

    @Override
    public List<Map<String, Object>> showStudentCourse(String header) {
       return studentMapper.selectStudentCourse(header);
    }

    @Override
    public List<Map<String, Object>> showSelectCourse(String userId) {

        return studentMapper.selectStudentCloudeSelectCourse(userId);
    }

    @Override
    public boolean saveCourse(StudentCourseEntity studentCourseEntity, String userId) {
        int i = studentMapper.saveSelectCourse (studentCourseEntity,userId) ;
        return i>0;
    }

    @Override
    public Boolean deleteStudentCourse(Integer id) {
        int i = studentMapper.deleteCourse (id);
        return i>0;
    }

    @Override
    public List<Map<String, Object>> showCourseTable(String userId) {
        return  studentMapper.showStudentCourseTable(userId);
    }

    public Map<String,Object> studentSignIn (StudentCourseEntity entity,String userId) {
        Long nowTime = System.currentTimeMillis() ;
        // 根据课程以及用户的班级信息来获取签到信息
        Map<String, Object> objectMap = studentSignInMapper.findTeacherSignInByCourse(entity,userId);
        Map<String , Object> res = new HashMap<>() ;
        Integer type = 0 ;
        if(objectMap==null || objectMap.size()==0) {
            //该课程就没有签到
            type = 0 ; // 没有签到信息
        }else {
            // 表示当前课程创建过签到
            // 获取课程发起签到的时间
           Long endTime = (Long) objectMap.get("endTime");
           Long keepTime = (Long) objectMap.get("keepTime");
           // 获取用户的签到信息
            Integer teacher_signInId  = (Integer) objectMap.get("id");
            //用户的签到信息
            Map<String, Object> studentSignInMap = studentSignInMapper.findStudentSignInBySignInId(teacher_signInId, userId ,endTime-keepTime );
           // 判断系统时间可签到时间的差值
            if(nowTime<endTime) {
                // 表示当前需进行签到 // 需要记录系统时间并返回
             if(studentSignInMap==null||studentSignInMap.size()==0) {
                 // 进行签到
                 type = 1 ;
                 // 设置签到时间和验证形式
                 HashMap<String,Object> validate = new HashMap<>() ;
                 validate.put("verifed",objectMap.get("verifed"));
                 validate.put("endTime",endTime);
                 validate.put("signinid",teacher_signInId);
                     res.put("validate",validate) ;
             }else  {
                 //表示已经签到过了
                  type = 2 ;

             }

            }else {
                // 表示已经签到过 或者是没有签到
                if(studentSignInMap==null||studentSignInMap.size()==0) {
                    // 没有签到 签到超时；
                     type = 3 ;

                }else  {
                    // 签到过了
                    type = 4 ;
                }

            }
        }
        res.put("type", type);
        return  res ;
    }

    @Override
    public Boolean createSignIn(StudentSignInEntity signInEntity) {
        Map<String, Object> studentSignInBySignInId = studentSignInMapper.findStudentSignInBySignInId(signInEntity.getSignInId(), signInEntity.getStudentId(), null);
        int i = 0 ;
        if(studentSignInBySignInId==null ||studentSignInBySignInId.size()==0) {
           i = studentSignInMapper.createStudentSignIn(signInEntity) ;
         }else  {
            signInEntity.setId((Integer) studentSignInBySignInId.get("id"));
          i = studentSignInMapper.updateStudentSignIn (signInEntity) ;
        }

        return i>0;
    }

    @Override
    public List<Map<String, Object>> showHomework(String header) {

        return homeWorkMapper.showHomeWorkToStudent(header);
    }

    @Override
    public Boolean submitHomeWork(StudentHomework studentHomework) {
         int i = studentMapper.addHomework(studentHomework);
        return i>0 ;
    }

    @Override
    public List<Map<String, Object>> sawHomework(String header) {
        return studentMapper.selectSawHomework(header);
    }

    @Override
    public List<Map<String, Object>> showHomeWorkPipe(String header) {
        List<Map<String, Object>> maps = studentMapper.showStudentHomeWorkPipe(header);
        int sum = 0 ;
        for (int i = 0; i < maps.size() ; i++) {
            sum += ((Number)(maps.get(i).get("count"))).intValue();
        }
        DecimalFormat df = new DecimalFormat("0.0");
        for (int i = 0 ; i<maps.size() ;i++) {
            Map<String, Object> map = maps.get(i);
            Integer count = ((Number)(maps.get(i).get("count"))).intValue();
            map.put("count",(count*1.0)/sum*100);
        }
        return  maps ;
    }

    @Override
    public Boolean submitHomeWork_1(StudentHomework studentHomework) {
        int i = studentMapper.addHomework_1(studentHomework);
        return i>0;
    }

    @Override
    public List<Map<String, Object>> sawHomework_1(String header) {
        return studentMapper.selectSawHomework_1(header);
    }


}
