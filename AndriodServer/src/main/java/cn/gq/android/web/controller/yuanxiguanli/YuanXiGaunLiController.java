package cn.gq.android.web.controller.yuanxiguanli;

import cn.gq.android.web.entity.CollegesAndMajors;
import cn.gq.android.web.service.collegeandmajors.ICollegeAndMajorService;
import cn.gq.android.web.utils.MyResult;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/18 15:52
 * @update 2020/4/18
 * Description:
 * @since 1.0.0
 */
@RestController
@Api(value = "YuanXiGaunLiController",description = "院系管理接口")
@RequestMapping("/colleges")
public class YuanXiGaunLiController {
    @Autowired
     private ICollegeAndMajorService collegeAndMajorService ;
    @RequiresRoles("ADMIN")
    @PostMapping ("/addcoandmajor")
    public JSONObject addCollegesAndMajors (@RequestBody CollegesAndMajors collegesAndMajors ) throws Exception {
         if(collegesAndMajors!=null) {
         System.out.println("院系名称：" + collegesAndMajors.getCname());
         System.out.println("专业名称"+ collegesAndMajors.getMajors());
         }
        boolean b = collegeAndMajorService.addCollegeAndMajors(collegesAndMajors);
           String msg ="添加失败" ;
           if(b) {
               msg = "添加成功" ;
           }
        JSONObject jsonObject = new JSONObject();
         jsonObject.put("code",200);
         jsonObject.put("msg",msg) ;
        return  jsonObject ;
    }


    @RequiresRoles("ADMIN")
    @GetMapping("/getcollegeandmajor")
    public JSONObject getCollegesAndMajors () {
        List<HashMap<String, Object>> collegeAndMajors = collegeAndMajorService.getCollegeAndMajors();
        JSONObject jsonObject = new JSONObject() ;
        String msg = "请添加学院专业" ;
        int code = 205 ;
        if(collegeAndMajors!=null) {
            msg = "完成";
            code =200 ;
        }
        jsonObject.put("code",code) ;
        jsonObject.put("msg",msg) ;
        jsonObject.put("data",collegeAndMajors);
      return  jsonObject ;
    }
    @GetMapping("/getCollege")
    public JSONObject showColleges() {
        JSONObject jsonObject = new JSONObject();
       List<Map<String,Object>> data = collegeAndMajorService.getColleges();
       String msg = "无学院数据请添加学院数据";
       int code = 205 ;
       if(data!=null){
            code = 200 ;
            msg = "加载成功" ;
        }
       jsonObject.put("code",code);
       jsonObject.put("msg",msg);
       jsonObject.put("data",data);
       return jsonObject ;
    }

    @GetMapping("/getcollegeandmjorandclass")
    public MyResult getAllAboutCollegeAndMajorAndClass () {
     List<HashMap<String,Object>> data =  collegeAndMajorService.getCollegeAndMajorAndClass();

        return  MyResult.success("完成",data) ;
    }
}
