package com.ifood.backend.advancedtest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@EnableCaching
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    @Value("${cacheManager.defaultCacheExpireTime}")
    int defaultCacheExpireTime;

    @Value("${cacheManager.strategy}")
    String cacheManagerStrategy;

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(cf);
        return redisTemplate;
    }

    @Primary
    @Bean(name = "default")
    public CacheManager defaultCacheManager(RedisTemplate redisTemplate) {
        if ("redis".equalsIgnoreCase(cacheManagerStrategy)) {
            RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
            cacheManager.setDefaultExpiration(defaultCacheExpireTime); // Number of seconds before expiration. Defaults to unlimited (0)
            cacheManager.setUsePrefix(true);
            return cacheManager;
        } else {
            return new ConcurrentMapCacheManager();
        }
    }
}
