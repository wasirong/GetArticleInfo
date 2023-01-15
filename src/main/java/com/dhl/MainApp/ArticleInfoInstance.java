package com.dhl.MainApp;

import com.dhl.Data.InputCSVData;

import java.util.ArrayList;
import java.util.List;

public class ArticleInfoInstance {
    // 将构造器私有化
    private ArticleInfoInstance() {
    }

    // 创建私有属性
    private static ArticleInfoInstance m_articleInfo;

    // 提供公开方法
    public static ArticleInfoInstance getGf() {
        // 判断是否存在实例对象
        if (m_articleInfo == null) {
            // 不存在实例对象，就创建
            m_articleInfo = new ArticleInfoInstance();
        }
        return m_articleInfo;
    }

    public List<String> ArticleInfoList = new ArrayList<String>();
    public List<InputCSVData> allInputCSVData = new ArrayList<InputCSVData>();
}
