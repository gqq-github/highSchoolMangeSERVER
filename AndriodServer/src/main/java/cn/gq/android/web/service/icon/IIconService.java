package cn.gq.android.web.service.icon;

import cn.gq.android.web.entity.Icon;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/10 15:27
 * @update 2020/4/10
 * Description:
 * @since 1.0.0
 */
public interface IIconService {
    /**
     *  根据角色Id来获取数据库当中的Icon图片
     * @param roleId
     * @return
     */
    List<Map<String ,Object>>  getIconByRole (int roleId) ;

    void saveIcon(Icon icon);

    List<Map<String ,Object>> regetIconByRole(int role);
}
