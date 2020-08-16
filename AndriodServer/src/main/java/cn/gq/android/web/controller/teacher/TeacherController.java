package cn.gq.android.web.controller.teacher;

import cn.gq.android.core.content.AuthConstant;
import cn.gq.android.web.entity.CheckHomework;
import cn.gq.android.web.entity.SendHomeWork;
import cn.gq.android.web.entity.Teacher;
import cn.gq.android.web.entity.TeacherSignInEntity;
import cn.gq.android.web.service.teacher.ITeacherService;
import cn.gq.android.web.utils.FileUtils;
import cn.gq.android.web.utils.MyResult;
import cn.gq.android.web.utils.SystemUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author DELL
 * @create 2020/4/23 14:32
 * @update 2020/4/23
 * Description:
 * @since 1.0.0
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    ITeacherService teacherService ;


    @Value("${system.ip}")
    private  String ip ;
    // 安卓的映射路径
    @Value("${android.file.database.path}")
    private String dataBasePath ;

    @Value("${android.file.save.file}")
    private String rootPath;

    @Value("${android.file.save.file.linux}")
    private String linuxRootPath;


    @RequiresRoles("ADMIN")
    @PostMapping("/addteacher")
    public JSONObject addTeacher(@RequestBody Teacher teacher) {
        boolean b = teacherService.addTeacher(teacher);
        String msg  = "添加失败";
        int code = 205 ;
        if(b) {
            code =200 ;
            msg = "添加成功" ;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code) ;
        jsonObject.put("msg",msg);
        return  jsonObject ;
    }

    @GetMapping("/showteachercourse")
    public MyResult showTeacherSelectCourse (HttpServletRequest request) {
      String userId = request.getHeader(AuthConstant.CLIENT_PARAM_USERID);
        List<Map<String ,Object>> res =  teacherService.showTeacherSelectCourse(userId);
        String temp ="完成" ;
        if(res==null || res.size()==0) {
            temp ="您没有选择课程" ;
        }
        return  MyResult.success(temp,res);
    }
    // 删除该教师选择的课程
    @GetMapping ("/deleteteachercourse/{id}")
    public MyResult deleteTeacherSelectCourse (@PathVariable ("id") Integer id) {
        boolean b =  teacherService.deleteTeacherSelectCourse (id);
        return  MyResult.success("删除成功") ;
    }
     // 教师选择的课程
     @GetMapping("/selectteachercourse")
    public MyResult selectTeacherCourse () {
        List<Map<String, Object>> maps = teacherService.showTeacherSelectCourse(-1+"");
        String msg = "已经没有课程了" ;
        if(maps ==null || maps.size()==0) {
            msg = "完成" ;
        }
        return  MyResult.success(msg,maps) ;
    }

    /**
     *
     * @param id 管理员开设课程的Id
     * @param request 设置用户的id
      * @return
     */
    // 教师进行选课
    @GetMapping("/selectcourse/{id}")
    public MyResult selectCourse(@PathVariable("id") Integer id , HttpServletRequest request) {
        String userId = request.getHeader(AuthConstant.CLIENT_PARAM_USERID);
         boolean b = teacherService.selectCourseToTeacher (id,userId) ;
         String msg = "选择成功";
         if(!b){
             msg = "添加失败" ;
         }

         return  MyResult.success(msg) ;
    }

    /**
     *  教师发布签到接口
     * @param signInEntity
     * @param request
     * @return
     */
    @PostMapping("/createsignin")
    public JSONObject CreateCourseSignIn (@RequestBody TeacherSignInEntity signInEntity , HttpServletRequest request) {
        signInEntity.setTeacherId(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        Integer courseSignIn = teacherService.createCourseSignIn(signInEntity);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200) ;
        jsonObject.put("msg","成功");
        jsonObject.put("id",courseSignIn);
        return  jsonObject;
    }

    @PostMapping ("/sendHomeWork")
    public MyResult sendHomeWork (@RequestBody SendHomeWork sendHomeWork , HttpServletRequest request) {
         sendHomeWork.setTeacherId(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
         sendHomeWork.setCourseSendTime(System.currentTimeMillis()+sendHomeWork.getCourseTime()*3600000);
         Boolean b =  teacherService.sendHomeWork(sendHomeWork);

         if(!b) {
             return  MyResult.faile("发布失败联系管理员");
         }
        return  MyResult.success("发布完成");
    }

    @GetMapping("/getStudentsHomework")
    public MyResult settStudentsHomeWork (HttpServletRequest request) {
        List<Map<String,Object>> data = teacherService.getStudentHomework(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
        return  MyResult.success("完成",data) ;
    }

    @GetMapping("/getStudentsHomework_1")
    public MyResult getStudentsHomeWork_1 (HttpServletRequest request) {
        List<Map<String,Object>> data = teacherService.getStudentHomework_1(request.getHeader(AuthConstant.CLIENT_PARAM_USERID));
         if(data==null ||data.size()==0)  {
              return MyResult.success("暂时没有作业提交");
         }
        return  MyResult.success("完成",data) ;
    }


    @PostMapping("/check")
    public MyResult checkHomework (@RequestBody CheckHomework checkHomework) {
        checkHomework.setImage(Base64.getMimeDecoder().decode(checkHomework.getTeacherPic()));
        checkHomework.setTeacherPic(null);
        Boolean b = teacherService.checkStudentsHomework (checkHomework);
        if(b){
            return MyResult.success("完成");
        }
        return  MyResult.faile("批改失败联系管理员...");
    }

    @PostMapping("/check_1")
    public MyResult checkHomework_1 (CheckHomework checkHomework) {
        // 需要判断当前的系统环境
        if(SystemUtils.isWinEnv()) {
            // 判断的window的环境
            checkHomework.setCheck_homework_path(ip+dataBasePath+  FileUtils.uploadFile(checkHomework.getCheck_homework_file(),rootPath));
        }else  {
            checkHomework.setCheck_homework_path(ip+dataBasePath+  FileUtils.uploadFile(checkHomework.getCheck_homework_file(),linuxRootPath));
        }

        Boolean b = teacherService.checkStudentsHomework_1 (checkHomework);
        if(b){
            return MyResult.success("完成");
        }
        return  MyResult.faile("批改失败联系管理员...");
    }



    @GetMapping ("/{courseId}/{courseType}/{teacherSignInId}")
    public MyResult checkStudentSignIn (@PathVariable("courseId") String courseId,@PathVariable("courseType") Integer courseType,
                                        @PathVariable("teacherSignInId") Integer teacherSignInId) {
        List<Map<String,Object>> data = teacherService.checkStudentSignIn(courseId,courseType,teacherSignInId);
        return MyResult.success("完成",data);
    }

}
