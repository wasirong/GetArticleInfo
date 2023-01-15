package com.dhl.MainApp;

import com.dhl.Controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class JFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        primaryStage.setTitle("DHL商品信息获取工具 Version.2.0.0.1");
        primaryStage.setScene(new Scene(root, 1522, 818));
        primaryStage.show();

//        primaryStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
