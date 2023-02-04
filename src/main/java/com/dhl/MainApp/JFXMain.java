package com.dhl.MainApp;

import com.dhl.Controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class JFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String xmlName = "";
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int ScreenWidth = gd.getDisplayMode().getWidth();
        int ScreenHeight = gd.getDisplayMode().getHeight();
        int width = 0;
        int height = 0;
        if (ScreenWidth >= 1920){
            System.out.println("big");
            xmlName = "MainScreen.fxml";
            width = 1522;
            height = 818;
        }else {
            System.out.println("small");
            xmlName = "MainScreen_Small.fxml";
            width = 1186;
            height = 730;
        }
        Parent root = FXMLLoader.load(getClass().getResource(xmlName));
        primaryStage.setTitle("DHL商品信息获取工具 Version.2.0.0.2");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();

//        primaryStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
