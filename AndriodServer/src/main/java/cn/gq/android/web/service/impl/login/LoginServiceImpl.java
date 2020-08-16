package cn.gq.android.web.service.impl.login;

import cn.gq.android.core.commexception.NotFoundException;
import cn.gq.android.web.dao.login.LoginMapper;
import cn.gq.android.web.entity.Login;
import cn.gq.android.web.service.login.LoginService;
import cn.gq.android.web.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/3/1 14:27
 * @update 2020/3/1
 * Description:
 * @since 1.0.0
 */
@Service

public class LoginServiceImpl implements LoginService {


    @Autowired
    LoginMapper loginMapper;

    @Transactional
    public boolean registerUser(Login login) {
        Map<String, Object> userInfo = loginMapper.getUserInfo(login.getUserId(), login.getRole());
        if(userInfo==null) {
            throw new NotFoundException("用户名需要管理员授权，联系管理员，注册失败");
        }
        int res = loginMapper.addLoginUser(login);
        System.out.println(res);

        return res == 1;
    }

    @Override

    public List<Map> getRolePerm(int role) {
        List<Map> res = loginMapper.getRolePermByRole(role);
        if (res == null) {
            throw new NotFoundException("用户异常，联系管理员");
        }
        return res;
    }

    @Override
    public Map<String,Object> getUserByUserId(String userId) {
        Map<String,Object> map = loginMapper.getUserByUserId(userId);
        return map  ;
    }

    @Override
    public Map<String, Object> getUserByUserIdAndRole(String userId, int role) {
        // 根据角色来定位到不同的表信息 （student 和 teacher ， manger）
      Map<String , Object> res = loginMapper.getUserInfo (userId,role) ;
       return res ;
    }

}
