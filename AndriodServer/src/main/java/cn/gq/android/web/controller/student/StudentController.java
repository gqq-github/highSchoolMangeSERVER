package cn.gq.android.web.controller.student;

import cn.gq.android.core.content.AuthConstant;
import cn.gq.android.web.entity.Student;
import cn.gq.android.web.entity.StudentCourseEntity;
import cn.gq.android.web.entity.StudentHomework;
import cn.gq.android.web.entity.StudentSignInEntity;
import cn.gq.android.web.service.student.IStudentService;
import cn.gq.android.web.utils.FileUtils;
import cn.gq.android.web.utils.MyResult;
import cn.gq.android.web.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/5/1 20:39
 * @update 2020/5/1
 * Description:
 * @since 1.0.0
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    IStudentService studentService ;
    // 本机IP
    @Value("${system.ip}")
    private  String ip ;
     // 安卓的映射路径
    @Value("${android.file.database.path}")
    private String dataBasePath ;

    @Value("${android.file.save.file}")
    private String rootPath;

    @Value("${android.file.save.file.linux}")
    private  String linuxRootPath ;

    // 设置学生的班级信息 学院信息
    @PostMapping("/upinfo")
    public MyResult UpStudentInfo (@RequestBody Student student, HttpServletRequest request) {
               student.setStudentId(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
               Boolean b = studentService.updateStudentInfo (student) ;
               if(b) {
                   return  MyResult.success("完成") ;
               }else {
                   return  MyResult.faile("设置失败") ;
               }
    }

    @GetMapping ("/showstudentcourse")
    public MyResult showStudentCourse (HttpServletRequest request) {
       List<Map<String , Object>> data = studentService.showStudentCourse(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        return  MyResult.success("完成",data);
    }

    @GetMapping("/showselectcourse")
    //显示学生的可选课程数据
   public MyResult showSelectCourse(HttpServletRequest request) {
        String userId = request.getHeader(AuthConstant.CLIENT_PARAM_USERID) ;
       List<Map<String , Object>> data =  studentService.showSelectCourse(userId) ;
     return  MyResult.success("完成",data);
    }
    @PostMapping("/saveselectcourse")
    public MyResult saveSelectCourse (@RequestBody StudentCourseEntity studentCourseEntity,HttpServletRequest request) {
        boolean b = studentService.saveCourse(studentCourseEntity,request.getHeader(AuthConstant.CLIENT_PARAM_USERID)) ;
        if(!b) {
         return    MyResult.faile("失败") ;
        }
        return MyResult.faile("成功") ;
    }
    @GetMapping ("/deletestudentcourse/{id}")
    public MyResult deleteStudentCourse (@PathVariable("id") Integer id)  {
        Boolean b = studentService.deleteStudentCourse (id)  ;
        return MyResult.success("完成");
    }

    @GetMapping("/coursetableshow")
    public MyResult showStudentCourseTable (HttpServletRequest request) {
        String userId = request.getHeader(AuthConstant.CLIENT_PARAM_USERID) ;
        List<Map<String , Object>> data = studentService.showCourseTable (userId) ;
        String msg = "没有课程" ;
        if(data!=null&&data.size()>0) {
            msg = "完成";
        }
        return  MyResult.success(msg,data);
    }
    @PostMapping ("/signin")
    public MyResult studentSignIn (@RequestBody StudentCourseEntity courseEntity , HttpServletRequest request) {
        Map<String, Object> objectMap = studentService.studentSignIn(courseEntity, request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        return  MyResult.success("完成",objectMap) ;
    }

    @PostMapping ("/creatsignin")
    public MyResult studentSignIn(@RequestBody StudentSignInEntity signInEntity,HttpServletRequest request) {
        signInEntity.setStudentId(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
       Boolean b =  studentService.createSignIn (signInEntity);
       if(b) {
           return  MyResult.success("签到成功");
       }
       return  MyResult.faile("签到失败") ;
    }

    @GetMapping("/showHomework")
    public MyResult showStudentHomework (HttpServletRequest request) {
        List<Map<String, Object>> maps = studentService.showHomework(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        return MyResult.success("完成",maps) ;
    }

    // 学生提交作业
    @PostMapping("/submitHomework")
    public MyResult submitHomework (@RequestBody StudentHomework studentHomework , HttpServletRequest request) {
        studentHomework.setStudentId(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        studentHomework.setImage( Base64.getMimeDecoder().decode(studentHomework.getHomeworkPicture()));
        studentHomework.setHomeworkPicture(null);
        Boolean b = studentService.submitHomeWork (studentHomework) ;
        return  MyResult.success("完成") ;
     }

      // 将图片保存到本地 在数据库中存储路径
    @PostMapping("/submitHomework_1")
    public MyResult submitHomework_1 ( StudentHomework studentHomework , HttpServletRequest request) {
        String dataPath = ip + dataBasePath ;
        studentHomework.setStudentId(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        if(SystemUtils.isWinEnv()) {
            System.out.println("当前为window环境");
            studentHomework.setFileUrl(dataPath+FileUtils.uploadFile(studentHomework.getStudentFile(),rootPath));
        }else {
            studentHomework.setFileUrl(dataPath+FileUtils.uploadFile(studentHomework.getStudentFile(),linuxRootPath));
            System.out.println("当前为linux环境");
        }
        studentService.submitHomeWork_1(studentHomework);
        return  MyResult.success("完成") ;
    }

     // 获取教师已经更改过得作业
     @GetMapping("/findHomework")
    public MyResult findSawHomework (HttpServletRequest request) {
        List<Map<String,Object>> data =  studentService.sawHomework(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        return MyResult.success("完成",data);
     }

    // 获取教师已经更改过得作业
    @GetMapping("/findHomework_1")
    public MyResult findSawHomework_1 (HttpServletRequest request) {
        List<Map<String,Object>> data =  studentService.sawHomework_1(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        return MyResult.success("完成",data);
    }


   @GetMapping("/showPipe")
    public MyResult showStudentHomeWorkPipe (HttpServletRequest request) {

      List<Map<String,Object>> data =   studentService.showHomeWorkPipe ( request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
      return  MyResult.success("完成",data) ;
   }

}
