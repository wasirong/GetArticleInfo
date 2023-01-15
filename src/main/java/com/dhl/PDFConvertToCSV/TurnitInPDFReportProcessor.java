package com.dhl.PDFConvertToCSV;


import com.dhl.Data.InputCSVData;
import com.dhl.MainApp.ArticleInfoInstance;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TurnitInPDFReportProcessor {
    private CSVBuilder csvBuilder;
    private List<String[]> allSumbissionData;

    private String pdfDir;
    private String outputDir;

    //    Logger LOG = LoggerFactory.getLogger(TurnitInPDFReportProcessor.class);
    public TurnitInPDFReportProcessor(String pdfDir, String outputDir) {
        csvBuilder = new CSVBuilder(outputDir + "/GetArticleInfo.csv");
        allSumbissionData = new ArrayList<String[]>();
        this.pdfDir = pdfDir;
        this.outputDir = outputDir;
//        this.hwbDirPath = m_hwbDirPath;
    }

    public void startProcess() {
        File dir = new File(pdfDir);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                int extentionIndex = child.getName().lastIndexOf(".");
                if (extentionIndex == -1 || !child.getName().substring(extentionIndex).toLowerCase().equals(".pdf")) {
//                    LOG.info("当前文件不是pdf");
                    continue;
                }
                processFile(dir.getPath() + "/" + child.getName(), child.getName());
            }
            csvBuilder.writeDataToCSV();
        } else {
            if (!dir.isDirectory()) {
//                LOG.info("Not a directory");
            }
        }
    }

    public void processFile(String path, String name) {
//        LOG.info("path:" + path);
        File file = new File(path);
        FileObject fo = new FileObject(file);
//        LOG.info("PDF文件读取:" + path);
        fo.init();
        List<String> lines = fo.getLines();
//        LOG.info("PDF读取成功");
        String temN = name.replaceAll(".pdf", "");
        String tempDel = "";
        String tempCus = "";
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("海关编号")) {
                String m_customNo = lines.get(i);
                if (m_customNo.split(temN).length > 1) {
                    tempCus = m_customNo.split(temN)[1];
                }
            }
            if (lines.get(i).contains("提运单号")) {
                String m_dei = lines.get(i);
                if (m_dei.split("提运单号：").length > 1) {
                    tempDel = m_dei.split("提运单号：")[1];
                    if (tempDel.split(" ").length > 1){
                        tempDel = tempDel.split(" ")[0];
                    }
                }
            }
        }
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("照章征税") || lines.get(i).contains("全免")) {
                InputCSVData inputCSVData = new InputCSVData();
                inputCSVData.setHWB(name.replaceAll(".pdf", ""));
                inputCSVData.setDeliveryNumbers(tempDel);
                inputCSVData.setCustomsNo(tempCus);
                if (lines.get(i - 11).split(" ").length > 2 && StringUtils.isNumeric(lines.get(i - 11).split(" ")[1]) && lines.get(i - 11).split(" ")[1].length() == 10) {
                    inputCSVData.setNum(lines.get(i - 11).split(" ")[0]);
                    inputCSVData.setArticleNo(" " + lines.get(i - 11).split(" ")[1]);
                    String temName = lines.get(i - 11);
                    String resultName = temName;
                    resultName = resultName.replace(temName.split(" ")[1], "");
                    resultName = resultName.replace(temName.split(" ")[0], "");
                    resultName = resultName.trim();
                    inputCSVData.setArticleName(resultName);
                    inputCSVData.setSpecifications(lines.get(i - 10) + lines.get(i - 9) + lines.get(i - 8) + lines.get(i - 7) + lines.get(i - 6) + lines.get(i - 5));
                    ArticleInfoInstance.getGf().allInputCSVData.add(inputCSVData);
                }

                if (lines.get(i - 10).split(" ").length > 2 && StringUtils.isNumeric(lines.get(i - 10).split(" ")[1]) && lines.get(i - 10).split(" ")[1].length() == 10) {
                    inputCSVData.setNum(lines.get(i - 10).split(" ")[0]);
                    inputCSVData.setArticleNo(" " + lines.get(i - 10).split(" ")[1]);
                    String temName = lines.get(i - 10);
                    String resultName = temName;
                    resultName = resultName.replace(temName.split(" ")[1], "");
                    resultName = resultName.replace(temName.split(" ")[0], "");
                    resultName = resultName.trim();
                    inputCSVData.setArticleName(resultName);
                    inputCSVData.setSpecifications(lines.get(i - 9) + lines.get(i - 8) + lines.get(i - 7) + lines.get(i - 6) + lines.get(i - 5));
                    ArticleInfoInstance.getGf().allInputCSVData.add(inputCSVData);
                }

                if (lines.get(i - 9).split(" ").length > 2 && StringUtils.isNumeric(lines.get(i - 9).split(" ")[1]) && lines.get(i - 9).split(" ")[1].length() == 10) {
                    inputCSVData.setNum(lines.get(i - 9).split(" ")[0]);
                    inputCSVData.setArticleNo(" " + lines.get(i - 9).split(" ")[1]);
                    String temName = lines.get(i - 9);
                    String resultName = temName;
                    resultName = resultName.replace(temName.split(" ")[1], "");
                    resultName = resultName.replace(temName.split(" ")[0], "");
                    resultName = resultName.trim();
                    inputCSVData.setArticleName(resultName);
                    inputCSVData.setSpecifications(lines.get(i - 8) + lines.get(i - 7) + lines.get(i - 6) + lines.get(i - 5));
                    ArticleInfoInstance.getGf().allInputCSVData.add(inputCSVData);
                }
                if (lines.get(i - 8).split(" ").length > 2 && StringUtils.isNumeric(lines.get(i - 8).split(" ")[1]) && lines.get(i - 8).split(" ")[1].length() == 10) {
                    inputCSVData.setNum(lines.get(i - 8).split(" ")[0]);
                    inputCSVData.setArticleNo(" " + lines.get(i - 8).split(" ")[1]);
                    String temName = lines.get(i - 8);
                    String resultName = temName;
                    resultName = resultName.replace(temName.split(" ")[1], "");
                    resultName = resultName.replace(temName.split(" ")[0], "");
                    resultName = resultName.trim();
                    inputCSVData.setArticleName(resultName);
                    inputCSVData.setSpecifications(lines.get(i - 7) + lines.get(i - 6) + lines.get(i - 5));
                    ArticleInfoInstance.getGf().allInputCSVData.add(inputCSVData);
                }
                if (lines.get(i - 7).split(" ").length > 2 && StringUtils.isNumeric(lines.get(i - 7).split(" ")[1]) && lines.get(i - 7).split(" ")[1].length() == 10) {
                    inputCSVData.setNum(lines.get(i - 7).split(" ")[0]);
                    inputCSVData.setArticleNo(" " + lines.get(i - 7).split(" ")[1]);
                    String temName = lines.get(i - 7);
                    String resultName = temName;
                    resultName = resultName.replace(temName.split(" ")[1], "");
                    resultName = resultName.replace(temName.split(" ")[0], "");
                    resultName = resultName.trim();
                    inputCSVData.setArticleName(resultName);
                    inputCSVData.setSpecifications(lines.get(i - 6) + lines.get(i - 5));
                    ArticleInfoInstance.getGf().allInputCSVData.add(inputCSVData);
                }
                if (lines.get(i - 6).split(" ").length > 2 && StringUtils.isNumeric(lines.get(i - 6).split(" ")[1]) && lines.get(i - 6).split(" ")[1].length() == 10) {
                    inputCSVData.setNum(lines.get(i - 6).split(" ")[0]);
                    inputCSVData.setArticleNo(" " + lines.get(i - 6).split(" ")[1]);
                    String temName = lines.get(i - 6);
                    String resultName = temName;
                    resultName = resultName.replace(temName.split(" ")[1], "");
                    resultName = resultName.replace(temName.split(" ")[0], "");
                    resultName = resultName.trim();
                    inputCSVData.setArticleName(resultName);
                    inputCSVData.setSpecifications(lines.get(i - 5));
                    ArticleInfoInstance.getGf().allInputCSVData.add(inputCSVData);
                }
            }
        }
    }
}