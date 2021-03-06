package cn.gq.android.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching  // 标注启动了缓存
public class EHCacheConfig {
    private static Logger logger = LoggerFactory.getLogger(EHCacheConfig.class);
    /**
     *  ehcache 主要的管理器
     * @param bean
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        logger.debug("加载EHCacheConfig.ehCacheCacheManager...");
        return new EhCacheCacheManager(bean.getObject());
    }

    /**
     *
     * 据shared与否的设置,
     * Spring分别通过CacheManager.create()
     * 或new CacheManager()方式来创建一个ehcache基地.
     *
     * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
     *
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        logger.debug("加载EHCacheConfig.ehCacheManagerFactoryBean....");
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("config/ehcache.xml"));
        // 是否和其他的缓存共享
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
}
