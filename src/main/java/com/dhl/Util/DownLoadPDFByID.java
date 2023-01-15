package com.dhl.Util;

import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownLoadPDFByID {
    public void DownLoad(String fileSavePath, String id, String type) throws InterruptedException {
        Logger logger = Logger.getLogger("TestLogger");
        FileOutputStream fileOutputStream = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        String msg = "";
        try {
            logger.log(Level.INFO, LogPrint.WriteInfoLog("*****************Down Start********************"));
            logger.log(Level.INFO, LogPrint.WriteInfoLog("OpenConnect"));
            String tmpUrl = "https://swapp.singlewindow.cn/decserver/sw/dec/printCluster/entries/" + type + "/0/0/" + id + ".pdf";
            logger.log(Level.INFO, LogPrint.WriteInfoLog("DownLoadURL: " + tmpUrl));
            URL url = new URL(tmpUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Cookie", "route1plat=" + new GetConfig().GetConfigByKey("COOKIE", "route1plat") + "; JSESSIONID=" + new GetConfig().GetConfigByKey("COOKIE", "JSESSIONID"));
            conn.setRequestProperty("Content-Type", "application/pdf;charset=UTF-8");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36");
            conn.setConnectTimeout(20 * 1000);

            logger.log(Level.INFO, LogPrint.WriteInfoLog("开始获取文件流"));
            inputStream = conn.getInputStream();
            logger.log(Level.INFO, LogPrint.WriteInfoLog("成功获取文件流"));

            logger.log(Level.INFO, LogPrint.WriteInfoLog("文件夹路径确认开始"));
            File file = new File(fileSavePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            logger.log(Level.INFO, LogPrint.WriteInfoLog("文件夹路径确认完了"));

            logger.log(Level.INFO, LogPrint.WriteInfoLog("开始写入文件"));
            fileOutputStream = new FileOutputStream(fileSavePath + "\\" + id + ".pdf");
            byte[] bytes = new byte[1024];
            int readCount = 0;
            while ((readCount = inputStream.read(bytes)) > 0) {
//                logger.log(Level.INFO, LogPrint.WriteInfoLog("写入中.....本次写入字节长度" + readCount));
                fileOutputStream.write(bytes, 0, readCount);
            }
            logger.log(Level.INFO, LogPrint.WriteInfoLog("写入成功"));

            if (fileOutputStream != null) {
                fileOutputStream.flush();
            }

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            msg = e.getMessage();
            if (msg != null) {
                Thread.sleep(10000);
                logger.log(Level.INFO, LogPrint.WriteInfoLog("重新下载"));
                DownLoad(fileSavePath, id, type);
            }
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}














