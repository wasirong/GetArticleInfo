package com.dhl.Data;

import javafx.beans.property.SimpleStringProperty;

public class InputCSVData {
    private SimpleStringProperty HWB = new SimpleStringProperty("");

    private SimpleStringProperty Num = new SimpleStringProperty("");

    private SimpleStringProperty ArticleNo = new SimpleStringProperty("");

    private SimpleStringProperty ArticleName = new SimpleStringProperty("");

    private SimpleStringProperty Specifications = new SimpleStringProperty("");

    private SimpleStringProperty CustomsNo = new SimpleStringProperty("");

    private SimpleStringProperty DeliveryNumbers = new SimpleStringProperty("");

    public InputCSVData() {
    }

    public InputCSVData(String num, String articleNo, String articleName, String specifications, String hwb) {
        Num = new SimpleStringProperty(num);
        ArticleNo = new SimpleStringProperty(articleNo);
        ArticleName = new SimpleStringProperty(articleName);
        Specifications = new SimpleStringProperty(specifications);
        HWB = new SimpleStringProperty(hwb);
    }

    public String getCustomsNo() {
        String temp = CustomsNo.get();
        if (!temp.equals("")){
            return "'" + temp.trim();
        }

        return temp;
    }

    public void setCustomsNo(String customsNo) {
        this.CustomsNo.set(customsNo);
    }

    public String getDeliveryNumbers() {
        return DeliveryNumbers.get();
    }

    public void setDeliveryNumbers(String deliveryNumbers) {
        this.DeliveryNumbers.set(deliveryNumbers);
    }

    public String getNum() {
        return Num.get();
    }

    public SimpleStringProperty numProperty() {
        return Num;
    }

    public void setNum(String num) {
        this.Num.set(num);
    }

    public String getArticleNo() {
        return ArticleNo.get();
    }

    public void setArticleNo(String articleNo) {
        ArticleNo.set(articleNo);
    }

    public String getArticleName() {
        return ArticleName.get();
    }

    public void setArticleName(String articleName) {
        ArticleName.set(articleName);
    }

    public String getSpecifications() {
        return Specifications.get();
    }

    public void setSpecifications(String specifications) {
        Specifications.set(specifications);
    }

    public String getHWB() {
        return HWB.get();
    }

    public void setHWB(String HWB) {
        this.HWB.set(HWB);
    }

    @Override
    public String toString() {
        return "InputCSVData{" +
                "HWB='" + HWB + '\'' +
                ", Num='" + Num + '\'' +
                ", ArticleNo='" + ArticleNo + '\'' +
                ", ArticleName='" + ArticleName + '\'' +
                ", Specifications='" + Specifications + '\'' +
                '}';
    }
}
