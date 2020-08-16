package cn.gq.android.web.controller.banji;

import cn.gq.android.web.entity.BanJi;
import cn.gq.android.web.service.banji.IBanjiService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DELL
 * @create 2020/4/22 15:05
 * @update 2020/4/22
 * Description:
 * @since 1.0.0
 */
@RestController
@RequestMapping("/banji")
public class BanJIController {
   @Autowired
   private IBanjiService BanjiService ;
   @PostMapping("/addbanji")
   public JSONObject addBanji (@RequestBody BanJi banJi){
     JSONObject jsonObject = new JSONObject();
       boolean b = BanjiService.addBanJi(banJi);
      int code = 205 ;
      String msg = "添加失败" ;
       if(b) {
           code = 200;
           msg = "添加成功" ;
       }
       jsonObject.put("code",code);
       jsonObject.put("msg",msg);
     return jsonObject ;
 }
}
