package com.agriculture.task;

import com.agriculture.constant.MessageConstant;
import com.agriculture.exception.BaseException;
import com.agriculture.utils.PythonOutputUtil;
import com.agriculture.mapper.NewsMapper;
import com.agriculture.entity.News;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@Slf4j
public class getNewsTask {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0 9 ? * *") //每天上午九点执行
    public void getAgricultureNews() {
        //清楚所有日期缓存
        Set<String> keys = redisTemplate.keys("news*");
        for (String key : keys) {
            redisTemplate.delete(key);
        }
        String pythonFilePath = "src/main/resources/static/getNews.py";
        String output = PythonOutputUtil.output(pythonFilePath);
        if (output == null)
            throw new BaseException(MessageConstant.FAILED_GET_NEWS);
        JSONArray jsonObject = JSON.parseArray(output);
        for (int i = 0; i < jsonObject.size(); i++) {
            JSONObject obj = jsonObject.getJSONObject(i);
            for (String key : obj.keySet()) {
                String value = obj.getString(key);
                News news = News.builder()
                        .url(value)
                        .title(key)
                        .createTime(LocalDateTime.now())
                        .build();
                newsMapper.insert(news);
            }
        }
    }
}
