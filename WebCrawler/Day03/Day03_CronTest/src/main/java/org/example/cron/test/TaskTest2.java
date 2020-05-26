package org.example.cron.test;

import com.lou.simhasher.SimHasher;
import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author HackerStar
 * @create 2020-05-26 10:21
 */
//@Component
public class TaskTest2 {
    @Scheduled(cron = "0/5 * * * * *")
    public void test() {
        String str1 = readAllFile("/Users/XinxingWang/Desktop/教程目录及说明 (1).txt");
        SimHasher hash1 = new SimHasher(str1);
        //打印simhash签名
        System.out.println(hash1.getSignature());
        System.out.println("============================");

        String str2 = readAllFile("/Users/XinxingWang/Desktop/教程目录及说明 (1) 2.txt");
        //打印simhash签名
        SimHasher hash2 = new SimHasher(str2);
        System.out.println(hash2.getSignature());
        System.out.println("============================");

        //打印海明距离
        System.out.println(hash1.getHammingDistance(hash2.getSignature()));
    }

    /**
     * 测试用
     * @param filename 名字
     * @return
     */
    public static String readAllFile (String filename){
        String everything = "";
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            everything = IOUtils.toString(inputStream);
            inputStream.close();
        } catch (IOException e) {
        }

        return everything;
    }
}
