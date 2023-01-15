package com.dhl.PDFConvertToCSV;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.dhl.Data.InputCSVData;
import com.dhl.MainApp.ArticleInfoInstance;
import com.opencsv.CSVWriter;

public class CSVBuilder {

    private File file;
    private FileWriter outputfile;

    public CSVBuilder(String filePath) {
        try {
            File file = new File(filePath);

            outputfile = new FileWriter(file, Charset.forName("GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    public void writeDataToCSV(List<String[]> data) {
    public void writeDataToCSV() {

        try {
            // create FileWriter object with file as parameter

            CSVWriter writer = new CSVWriter(outputfile);
            // create a List which contains String array
            List<String[]> temData = new ArrayList<String[]>();
            temData.add(new String[]{"统一编号", "项号",
                    "商品编号", "商品名称", "规格型号", "海关编号", "提运单号",
            });
            for (InputCSVData inputCSVData : ArticleInfoInstance.getGf().allInputCSVData) {

                String customsNo = inputCSVData.getCustomsNo();
                if (!customsNo.equals("")){
                    customsNo = "'" + customsNo.trim();
                }
                temData.add(new String[]{inputCSVData.getHWB(), inputCSVData.getNum(),
                        inputCSVData.getArticleNo(), inputCSVData.getArticleName(), inputCSVData.getSpecifications(), customsNo, inputCSVData.getDeliveryNumbers()
                });
            }

            writer.writeAll(temData);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
