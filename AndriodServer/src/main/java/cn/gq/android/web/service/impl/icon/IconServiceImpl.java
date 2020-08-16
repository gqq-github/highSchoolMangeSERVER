package cn.gq.android.web.service.impl.icon;

import cn.gq.android.core.content.CacheConstant;
import cn.gq.android.web.dao.icon.IconMapper;
import cn.gq.android.web.entity.Icon;
import cn.gq.android.web.service.icon.IIconService;
import cn.gq.android.web.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @create 2020/4/10 15:30
 * @update 2020/4/10
 * Description:
 * @since 1.0.0
 */

@Service
public class IconServiceImpl implements IIconService {
    @Autowired
    IconMapper iconMapper;

    @Cacheable(value = CacheConstant.CacheName_RESOURCECACHE,key = "#roleId",condition ="#roleId>-1&&#roleId<3")
    @Override
    public List<Map<String, Object>> getIconByRole(int roleId) {
        return iconMapper.findIconByRoleId(roleId);
    }

    @Override
    @CacheEvict ( value = CacheConstant.CacheName_RESOURCECACHE, allEntries=true)
    public void saveIcon(Icon icon) {
        iconMapper.saveIcon(icon);
    }

    @Override
    public List<Map<String, Object>> regetIconByRole(int roleId) {
        return iconMapper.findIconByRoleId(roleId);
    }
}
