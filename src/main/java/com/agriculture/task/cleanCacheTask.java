package com.agriculture.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class cleanCacheTask {
    @Autowired
    private RedisTemplate redisTemplate;
    private final String prefix1 = "cropPageQuery";

    @Scheduled(cron = "0 0 1 ? * 2")//每周一凌晨一点
    public void cleanCache() {
        log.info("开始清理分页查询缓存");
        // 清理分页查询不以cropPageQuery*10结尾的所有key
        cleanPageCache(prefix1, "10");

    }

    private void cleanPageCache(String pattern, String endPattern) {
        Set<String> keys = redisTemplate.keys(pattern + "*");
        for (String key : keys) {
            if (!key.endsWith(endPattern)) {
                redisTemplate.delete(key);
            }
        }
    }
}
