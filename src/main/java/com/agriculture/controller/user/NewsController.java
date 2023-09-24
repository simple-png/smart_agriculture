package com.agriculture.controller.user;

import com.agriculture.common.result.Result;
import com.agriculture.mapper.NewsMapper;
import com.agriculture.pojo.entity.News;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/news")
@Slf4j
@Api(tags = "新闻api")
public class NewsController {
    @Autowired
    private NewsMapper newsMapper;

    @GetMapping
    @Cacheable(cacheNames = "news", key = "#dateTime")
    @ApiOperation("获取特定日期的新闻(格式为yyyy-MM-dd)")
    public Result<List<News>> getRecentNews(String dateTime) {
        List<News> news = newsMapper.getByDate(dateTime);
        return Result.success(news);
    }
}
