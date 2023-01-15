package com.dhl.Controllers;

import com.dhl.Data.InputCSVData;
import com.dhl.MainApp.ArticleInfoInstance;
import com.dhl.PDFConvertToCSV.TurnitInPDFReportProcessor;
import com.dhl.Util.DownLoadPDFByID;
import com.dhl.Util.GetConfig;
import com.dhl.Util.LogPrint;
import com.dhl.Util.ReadTXTFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    @FXML
    public Button DataDownLoad;
    @FXML
    public Button DataDownLoad_new;

    @FXML
    public Button CSVExport;
    @FXML
    public Button Search;
    @FXML
    private TextField ArticleName;
    @FXML
    private TextField SpecificationsInfo;
    @FXML
    private TextField ArticleNo;
    @FXML
    private TextField CustomsNo;
    @FXML
    private TextField DeliveryNumbers;

    @FXML
    private Label ActionTarget;
    @FXML
    private TableView<InputCSVData> dataTable = new TableView<InputCSVData>();

    @FXML
    private TableColumn mColumnHWB = new TableColumn();

    @FXML
    private TableColumn mColumnNum = new TableColumn();

    @FXML
    private TableColumn mColumnArticleNo = new TableColumn();
    @FXML
    private TableColumn mColumnCustomsNo = new TableColumn();
    @FXML
    private TableColumn mColumnDeliveryNumbers = new TableColumn();
    @FXML
    private TableColumn mColumnArticleName = new TableColumn();
    @FXML
    private TableColumn mColumnSpecifications = new TableColumn();

    //    private ObservableList<InputCSVData> data;
    Logger logger = Logger.getLogger("TestLogger");

    public static String inputDirPath;
    public static String hwbDirPath;
    public static String outputDirPath;

    @FXML
    public void DataDownLoadAction() {
        try {
            ActionTarget.setText("Processing...");

            String fileSavePath = System.getProperty("user.dir") + "\\商品附加页PDF";

            ReadTXTFile readTXTFile = new ReadTXTFile();

            logger.log(Level.INFO, LogPrint.WriteInfoLog("List存入单例."));

            // List 存入单例
            readTXTFile.GetArticleID();

            // 将pdf下载到指定文件夹
            DownLoadPDFByID downLoadPDFByID = new DownLoadPDFByID();

            for (int i = 0; i < ArticleInfoInstance.getGf().ArticleInfoList.size(); i++) {
                logger.log(Level.INFO, LogPrint.WriteInfoLog("第[" + (i + 1) + "]次下载开始."));
                downLoadPDFByID.DownLoad(fileSavePath, ArticleInfoInstance.getGf().ArticleInfoList.get(i), "ftlGoodsList/3");
            }
            logger.log(Level.INFO, LogPrint.WriteInfoLog("pdf下载完成."));
            ArticleNo.setDisable(true);
            ArticleName.setDisable(true);
            SpecificationsInfo.setDisable(true);
            CustomsNo.setDisable(true);
            DeliveryNumbers.setDisable(true);
            Search.setDisable(true);
            ArticleInfoInstance.getGf().allInputCSVData = new ArrayList<>();
            initializeDataRecordTable();
            ActionTarget.setText("商品附加页PDF下载完成!!");
        } catch (Exception e) {
            logger.log(Level.INFO, LogPrint.WriteInfoLog("例外发生: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    @FXML
    public void DataDownLoadAction_new() {
        try {
            ActionTarget.setText("Processing...");

            String fileSavePath = System.getProperty("user.dir") + "\\报关单PDF";

            ReadTXTFile readTXTFile = new ReadTXTFile();

            logger.log(Level.INFO, LogPrint.WriteInfoLog("List存入单例."));

            // List 存入单例
            readTXTFile.GetArticleID();

            // 将pdf下载到指定文件夹
            DownLoadPDFByID downLoadPDFByID = new DownLoadPDFByID();

            for (int i = 0; i < ArticleInfoInstance.getGf().ArticleInfoList.size(); i++) {
                logger.log(Level.INFO, LogPrint.WriteInfoLog("第[" + (i + 1) + "]次下载开始."));
                downLoadPDFByID.DownLoad(fileSavePath, ArticleInfoInstance.getGf().ArticleInfoList.get(i), "ftl/1");
            }
            logger.log(Level.INFO, LogPrint.WriteInfoLog("pdf下载完成."));
            ArticleNo.setDisable(true);
            ArticleName.setDisable(true);
            SpecificationsInfo.setDisable(true);
            CustomsNo.setDisable(true);
            DeliveryNumbers.setDisable(true);
            Search.setDisable(true);
            ArticleInfoInstance.getGf().allInputCSVData = new ArrayList<>();
            initializeDataRecordTable();
            ActionTarget.setText("报关单PDF下载完成!!");
        } catch (Exception e) {
            logger.log(Level.INFO, LogPrint.WriteInfoLog("例外发生: " + e.getMessage()));
            e.printStackTrace();
        }
    }

    @FXML
    public void CSVExportAction() {
        TurnitInPDFReportProcessor pdfProcessor = null;
        ArticleInfoInstance.getGf().allInputCSVData = new ArrayList<InputCSVData>();
        try {
            ArticleNo.clear();
            ArticleName.clear();
            SpecificationsInfo.clear();
            CustomsNo.clear();
            DeliveryNumbers.clear();
            ActionTarget.setText("Processing...");
            logger.log(Level.INFO, LogPrint.WriteInfoLog("CSV导出开始"));
            String pdfPath = System.getProperty("user.dir") + "\\商品附加页PDF";
            String csvPath = System.getProperty("user.dir") + "\\csv";
            File file = new File(pdfPath);

            if (!file.exists()) {
                file.mkdirs();
                ActionTarget.setText("请点击pdf下载按钮");
            } else {
                file = new File(csvPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                pdfProcessor = new TurnitInPDFReportProcessor(pdfPath, csvPath);
                pdfProcessor.startProcess();
                ArticleNo.setDisable(false);
                ArticleName.setDisable(false);
                SpecificationsInfo.setDisable(false);
                CustomsNo.setDisable(false);
                DeliveryNumbers.setDisable(false);
                Search.setDisable(false);
                initializeDataRecordTable();
                logger.log(Level.INFO, LogPrint.WriteInfoLog("CSV导出完了"));
                ActionTarget.setText("CSV导出完了!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionTarget.setText("异常发生联系管理员：" + e.getMessage());
        }
    }

    @FXML
    public void SearchAction() {
        logger.log(Level.INFO, LogPrint.WriteInfoLog("商品编号: [" + ArticleNo.getText() + "]"));
        logger.log(Level.INFO, LogPrint.WriteInfoLog("货物检索: [" + ArticleName.getText() + "]"));
        logger.log(Level.INFO, LogPrint.WriteInfoLog("规格型号: [" + SpecificationsInfo.getText() + "]"));
        initializeDataRecordTable();
    }

    public void initializeDataRecordTable() {
        dataTable.setEditable(true);
        mColumnHWB.setCellValueFactory(new PropertyValueFactory<InputCSVData, String>("HWB"));
        mColumnHWB.setCellFactory(TextFieldTableCell.forTableColumn());
        mColumnNum.setCellValueFactory(new PropertyValueFactory<InputCSVData, String>("Num"));
        mColumnNum.setCellFactory(TextFieldTableCell.forTableColumn());
        mColumnArticleNo.setCellValueFactory(new PropertyValueFactory<InputCSVData, String>("ArticleNo"));
        mColumnArticleNo.setCellFactory(TextFieldTableCell.forTableColumn());
        mColumnArticleName.setCellValueFactory(new PropertyValueFactory<InputCSVData, String>("ArticleName"));
        mColumnArticleName.setCellFactory(TextFieldTableCell.forTableColumn());
        mColumnSpecifications.setCellValueFactory(new PropertyValueFactory<InputCSVData, String>("Specifications"));
        mColumnSpecifications.setCellFactory(TextFieldTableCell.forTableColumn());
        mColumnCustomsNo.setCellValueFactory(new PropertyValueFactory<InputCSVData, String>("CustomsNo"));
        mColumnCustomsNo.setCellFactory(TextFieldTableCell.forTableColumn());
        mColumnDeliveryNumbers.setCellValueFactory(new PropertyValueFactory<InputCSVData, String>("DeliveryNumbers"));
        mColumnDeliveryNumbers.setCellFactory(TextFieldTableCell.forTableColumn());

        dataTable.getItems().clear();
        dataTable.getItems().addAll(getDataRecordInfo());
    }

    public List<InputCSVData> getDataRecordInfo() {
        String m_articleNo = ArticleNo.getText();
        String m_articleName = ArticleName.getText();
        String m_specificationsInfo = SpecificationsInfo.getText();
        String m_customsNo = CustomsNo.getText();
        String m_DeliveryNumbers = DeliveryNumbers.getText();


        List<InputCSVData> articleNoData = new ArrayList<InputCSVData>();
        for (int i = 0; i < ArticleInfoInstance.getGf().allInputCSVData.size(); i++) {
            if (ArticleInfoInstance.getGf().allInputCSVData.get(i).getArticleNo().contains(m_articleNo)) {
                articleNoData.add(ArticleInfoInstance.getGf().allInputCSVData.get(i));
            }
        }

        List<InputCSVData> articleNameData = new ArrayList<InputCSVData>();
        for (InputCSVData articleNoDatum : articleNoData) {
            if (articleNoDatum.getArticleName().contains(m_articleName)) {
                articleNameData.add(articleNoDatum);
            }
        }

        List<InputCSVData> specificationsInfoData = new ArrayList<InputCSVData>();
        for (InputCSVData articleNameDate : articleNameData) {
            if (articleNameDate.getSpecifications().contains(m_specificationsInfo)) {
                specificationsInfoData.add(articleNameDate);
            }
        }

        List<InputCSVData> customsNoData = new ArrayList<InputCSVData>();
        for (InputCSVData specificationsInfoDatum : specificationsInfoData) {
            if (specificationsInfoDatum.getCustomsNo().contains(m_customsNo)){
                customsNoData.add(specificationsInfoDatum);
            }
        }

        List<InputCSVData> deliveryNumbersData = new ArrayList<InputCSVData>();
        String[] DeliveryNumbersArr = m_DeliveryNumbers.split("/");
        for (int i = 0; i < DeliveryNumbersArr.length; i++) {
            for (InputCSVData deliveryNumber : customsNoData) {
                if (deliveryNumber.getDeliveryNumbers().contains(DeliveryNumbersArr[i])){
                    deliveryNumbersData.add(deliveryNumber);
                }
            }
        }

        // 去重
        Set<InputCSVData> resultList =
                new TreeSet<InputCSVData>(Comparator.comparing(o -> (o.getArticleName() + o.getArticleNo() + o.getSpecifications() + o.getCustomsNo() + o.getDeliveryNumbers())));

        resultList.addAll(deliveryNumbersData);

        return new ArrayList<>(resultList);
    }
}
