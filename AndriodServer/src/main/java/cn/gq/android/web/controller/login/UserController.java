package cn.gq.android.web.controller.login;

import cn.gq.android.core.commexception.NotFoundException;
import cn.gq.android.core.content.AuthConstant;
import cn.gq.android.web.entity.Login;
import cn.gq.android.web.service.login.LoginService;
import cn.gq.android.web.utils.JwtTokenUtil;
import cn.gq.android.web.utils.ResponseUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/3/1 13:26
 * @update 2020/3/1
 * Description: 用户登录注册控制层
 * @since 1.0.0
 */
@Controller
@Api(value = "UserController",description = "用户登录注册接口")
@RequestMapping("/user")

public class UserController {
    @Autowired
    LoginService loginService ;
    @ApiOperation(value = "进行用户注册",notes = "用户注册")
    @PostMapping(value = "/register" , consumes = "application/json; charset=utf-8")
    @ResponseBody
    public JSONObject registerUser (@RequestBody @ApiParam Login login ) {
        System.out.println("userId="+login.getUserId()+"  " + "password="+login.getUserId());
        if(login!=null) {
        login.setCreatDate(new Date());
        login.setUpDate(new Date());
        }
        login.setPassWord(DigestUtils.md5DigestAsHex(login.getPassWord().getBytes()));
        boolean b = loginService.registerUser(login);
        JSONObject res = new JSONObject();
        res.put("msg","注册成功");
        res.put("code",200);
        return  res;
    }

    @ApiOperation(value = "用户登录",notes = "用户登录")
    @PostMapping(value = "/login")
    /*
    * 进行用户的登录功能
    */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity loginUser ( @RequestBody Login user , HttpServletRequest request, HttpServletResponse response) {
        //1 首先 验证 输入的用户名和密码的有效性
        String userId = null;
        String password = null;
        if (user != null) {
            userId = user.getUserId();
            password = user.getPassWord();
        }

        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(password)) {
            return ResponseUtil.failure("用户名或者密码不能为空");
        }
        // 2 验证输入的信息的有效性； 用户是否已经注册
        Map<String, Object> res = loginService.getUserByUserId(userId); ;

            if(res==null) {
                throw new NotFoundException("用户不存在或者被删除") ;
            }
        String n_passWord = (String) res.get("password");
        int n_roleId = (int) res.get("roleid");

        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        if (n_passWord.equals(md5Pass)) {
            // 生成token
            String accessToken = JwtTokenUtil.createToken(userId, AuthConstant.TTLMILLS, AuthConstant.SECRETKET);
            response.setHeader(AuthConstant.DEFAULT_TOKEN_NAME, accessToken);
            response.setHeader(AuthConstant.CLIENT_PARAM_USERNAME, userId);
            ///Map<String, String> authMap = new HashMap<String, String>();
            res.put(AuthConstant.DEFAULT_TOKEN_NAME, accessToken);
            // authMap.put(AuthConstant.CLIENT_PARAM_USERNAME, userId);
            res.put(AuthConstant.CLIENT_PARAM_USERID, userId);
            res.put("role",n_roleId);
            res.remove("roleid");
            //  authMap.put("role", "1");
            return ResponseUtil.success().ok().body(res);
        }
        return ResponseUtil.failure("用户名或者密码有误");
    }
}
