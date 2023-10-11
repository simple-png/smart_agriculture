package com.agriculture.service.impl;

import com.agriculture.mapper.LeaderBoardMapper;
import com.agriculture.entity.Echarts;
import com.agriculture.service.LeaderBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LeaderBoardServiceImpl implements LeaderBoardService {
    @Autowired
    private LeaderBoardMapper leaderBoardMapper;

    @Override
    public List<Echarts> getRankByProvinceId(Integer provinceId) {
        List<Echarts> list = leaderBoardMapper.getRankByProvinceId(provinceId);
        return list;
    }
}
