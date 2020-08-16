package cn.gq.android.web.controller.coursetable;

import cn.gq.android.web.entity.CourseTable;
import cn.gq.android.web.entity.CourseTableWarp;
import cn.gq.android.web.service.coursetable.ICourseTableService;
import cn.gq.android.web.utils.MyResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/29 16:08
 * @update 2020/4/29
 * Description:
 * @since 1.0.0
 */
@RestController
@RequestMapping("coursetable")
public class CourseTableController {
      @Autowired
      ICourseTableService courseTableService ;
    @PostMapping("/addcoursetable")
    public JSONObject addCourseTable(@RequestBody CourseTable courseTable) {
        boolean res = courseTableService.saveCourseTable(courseTable);
        Integer code = 205 ;
        String msg = "添加失败" ;

        if(res){
            code = 200 ;
            msg = "添加成功" ;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg) ;
        return jsonObject ;
    }
    @PostMapping("/deletecoursetable")
    public JSONObject deleteCourseTable(@RequestBody() CourseTable courseTable) {
      boolean res =  courseTableService.deleteCourseTable(courseTable);
        Integer code = 205 ;
        String msg = "删除失败" ;

        if(res){
            code = 200 ;
            msg = "删除成功" ;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);
        jsonObject.put("msg",msg) ;
        return jsonObject ;
    }
    @GetMapping("coursetableshow/{query}")
    public MyResult showCourseTable (@PathVariable("query") String query) {
     List<Map<String,Object>> data = courseTableService.showCourseTable (query) ;
        MyResult res = MyResult.success("加载成功", data);
     return  res  ;
    }
}
