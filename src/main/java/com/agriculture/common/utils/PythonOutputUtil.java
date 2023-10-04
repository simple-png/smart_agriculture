package com.agriculture.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PythonOutputUtil {
    public static String output(String path) {
        try {
            // 构建Python命令及参数
            String pythonScript = path;
            String[] cmd = {"python3", pythonScript};

            // 创建ProcessBuilder对象，并设置命令及工作目录
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.directory(null); // 设置工作目录，如果有需要的话

            // 启动进程
            Process process = pb.start();

            // 读取Python脚本的输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // 等待进程执行完毕
            int exitCode = process.waitFor();

            // 打印输出结果
            System.out.println("Python脚本执行结果：" + result);
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
