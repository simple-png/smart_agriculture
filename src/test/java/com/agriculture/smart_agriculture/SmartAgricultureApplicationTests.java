package com.agriculture.smart_agriculture;

import com.agriculture.common.utils.PythonOutputUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;

class SmartAgricultureApplicationTests {


    @Test
    void contextLoads() {
        String pythonFilePath = "src/main/resources/static/getNews.py";
        String output = PythonOutputUtil.output(pythonFilePath);
        JSONArray jsonObject = JSON.parseArray(output);
        System.out.println(jsonObject);
    }

}
