package cn.gq.android.web.dao.icon;

import cn.gq.android.web.entity.Icon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/10 15:32
 * @update 2020/4/10
 * Description:
 * @since 1.0.0
 */
@Mapper
public interface IconMapper {
    List<Map<String,Object>> findIconByRoleId(@Param(value = "roleId") int roleId);

    void saveIcon(Icon icon);
}
