package cn.gq.android.web.service.impl.banji;

import cn.gq.android.web.dao.banji.BanJiMapper;
import cn.gq.android.web.entity.BanJi;
import cn.gq.android.web.service.banji.IBanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DELL
 * @create 2020/4/22 21:05
 * @update 2020/4/22
 * Description:
 * @since 1.0.0
 */
@Service
public class BanJIService implements IBanjiService {
    @Autowired
    private BanJiMapper banJiMapper ;
    @Override
    @Transactional
    public boolean addBanJi(BanJi banJi) {
         int i = banJiMapper.addBanJi(banJi);
        return i>0;
    }
}
