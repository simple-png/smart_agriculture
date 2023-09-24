package com.agriculture.task;

import com.agriculture.common.constant.MessageConstant;
import com.agriculture.common.exception.BaseException;
import com.agriculture.common.utils.PythonOutputUtil;
import com.agriculture.mapper.NewsMapper;
import com.agriculture.pojo.entity.News;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class getNewsTask {
    @Autowired
    private NewsMapper newsMapper;

    @Scheduled(cron = "0 0 9 ? * *") //每天上午九点执行
    @CacheEvict(cacheNames = "news", key = "'agriculture'")
    public void getAgricultureNews() {
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
