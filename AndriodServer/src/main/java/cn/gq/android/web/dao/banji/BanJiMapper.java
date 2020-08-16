package cn.gq.android.web.dao.banji;

import cn.gq.android.web.entity.BanJi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author DELL
 * @create 2020/4/22 21:06
 * @update 2020/4/22
 * Description:
 * @since 1.0.0
 */
@Mapper
public interface BanJiMapper {
    int addBanJi(@Param("banJi") BanJi banJi);
}
