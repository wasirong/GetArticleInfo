package com.dhl.Util;

import com.dhl.MainApp.ArticleInfoInstance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadTXTFile {
    public void GetArticleID() {
        try {
            File file = new File(System.getProperty("user.dir") + "\\IDFile.txt");

            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;

            //使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                ArticleInfoInstance.getGf().ArticleInfoList.add(s);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
