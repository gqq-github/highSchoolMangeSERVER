package cn.gq.android.web.controller.opencourse;

import cn.gq.android.web.entity.OpenCourse;
import cn.gq.android.web.service.opencourse.IOpenCourseService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/27 11:59
 * @update 2020/4/27
 * Description:
 * @since 1.0.0
 */
@RestController

@RequestMapping("/opencourse")
public class OpenCourseController {
    @Autowired
    IOpenCourseService openCourseService;
    @PostMapping("/addopencourse")
    public JSONObject addOpenCourse(@RequestBody OpenCourse openCourse) {
        boolean b = openCourseService.addOpenCourse(openCourse);
        String msg = "添加失败，检查输入的课程编号" ;
        Integer code = 205 ;
        if(b) {
          msg  = "添加成功"  ;
          code = 200 ;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg",msg) ;
        jsonObject.put("code",code) ;
        return  jsonObject ;
    }
    @GetMapping("/getcourseandmajor")
      public JSONObject getCourseAndMajor () {
        JSONObject jsonObject = new JSONObject();
      List<Object> data =  openCourseService.findCourseAndMajor();
       String msg = "获取失败" ;
       int code = 205 ;
       if(data!=null){
           code = 200 ;
           msg = "完成" ;
       }
       jsonObject.put("code",code) ;
       jsonObject.put("msg",msg) ;
       jsonObject.put("data",data) ;
      return  jsonObject  ;
    }
}
