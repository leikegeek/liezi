package org.liezi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Lake
 * @description: 从库Redis数据源配置
 * @date 2019/6/3
 */
@Configuration
@EnableCaching
public class SlaveRedisConfig extends RedisConfig {

    @Value("${spring.redis2.database}")
    private int dbIndex;

    @Value("${spring.redis2.host}")
    private String host;

    @Value("${spring.redis2.port}")
    private int port;

    @Value("${spring.redis2.password}")
    private String password;

    @Value("${spring.redis2.timeout}")
    private int timeout;

    /**
     * 配置redis连接工厂
     *
     * @return
     */
    @Primary
    @Bean
    public RedisConnectionFactory cacheRedisConnectionFactory() {
        return createJedisConnectionFactory(dbIndex, host, port, password, timeout);
    }

    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     *
     * @return
     */
    @Bean(name = "slaveRedisTemplate")
    public RedisTemplate cacheRedisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(cacheRedisConnectionFactory());
        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

}
