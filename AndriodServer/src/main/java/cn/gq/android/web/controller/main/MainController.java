package cn.gq.android.web.controller.main;

import cn.gq.android.core.content.AuthConstant;
import cn.gq.android.web.entity.Icon;
import cn.gq.android.web.service.icon.IIconService;
import cn.gq.android.web.service.login.LoginService;
import cn.gq.android.web.utils.Base64Utils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/10 14:30
 * @update 2020/4/10
 * Description:
 * @since 1.0.0
 */
@RestController
@Api(value = "MainController" , description = "主页相关的Controller")
@RequestMapping("/main")
public class MainController {
    @Autowired
    IIconService iIconService ;
    @Autowired
    LoginService loginService ;
     @ApiOperation(value = "获取主页的相关图标",notes = "主页图标")
     @GetMapping("/contentIcon")
     public JSONObject getMainIcon (HttpServletRequest request) {
        int role = request.getIntHeader("role");
        List<Map<String, Object>> iconByRole = iIconService.getIconByRole(role);
         if(iconByRole==null||iconByRole.size()==0) {
          iconByRole = iIconService.regetIconByRole(role);
         }
         JSONObject jsonObject = new JSONObject() ;
         if(role==2) {
         Map<String, Object> userByUserIdAndRole = loginService.getUserByUserIdAndRole(request.getHeader(AuthConstant.CLIENT_PARAM_USERID), role);
         if (userByUserIdAndRole.get("majorid")==null||userByUserIdAndRole.get("classid")==null||
         userByUserIdAndRole.get("collegeid") ==null
         ) {
             jsonObject.put("info",0);
         } else {
             jsonObject.put("info",1) ;
         }
         }
         jsonObject.put("code",200) ;
         jsonObject.put("data",iconByRole) ;
         System.out.println(iconByRole);
         return jsonObject ;
    }
    @PostMapping("/uploadIcon")
    public JSONObject upLoadIcon (MultipartFile file ,HttpServletRequest request,String iconName) {
        JSONObject jsonObject = new JSONObject();
        try {
            Icon icon = new Icon(iconName, file.getBytes(), request.getIntHeader("role"));
            iIconService.saveIcon(icon);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        jsonObject.put("code",200);
        jsonObject.put("msg","保存成功");
        return  jsonObject ;
     }
}
