package cn.gq.android.web.controller.course;

import cn.gq.android.web.entity.Course;
import cn.gq.android.web.service.cource.ICourseService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * @author DELL
 * @create 2020/4/5 15:17
 * @update 2020/4/5
 * Description: 用户课程相关的操作
 * @since 1.0.0
 */
@RestController
@Api(value = "CourseController",description = "课程操作接口")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService ;
    @GetMapping("/course_item")
    public JSONObject showItem (HttpServletRequest request) {
        request.getHeader("userId") ;
        request.getHeader("role");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200) ;
        jsonObject.put("data","testSuccess") ;
        return jsonObject ;
    }
    @PostMapping("/course_add")
    @ApiOperation(value = "课程添加",notes = "课程添加")
    @RequiresRoles("ADMIN")
    public JSONObject addCourse (@RequestBody Course course) {
        JSONObject jsonObject = new JSONObject();
        boolean b = courseService.addCourse(course);

        int code = 0 ;
        String msg = "添加失败数据格式异常" ;
         if(b){
             code = 200 ;
             msg =  "添加成功" ;
         }
        jsonObject.put("code",code);
         jsonObject.put("msg",msg) ;
        return  jsonObject;
    }
}
