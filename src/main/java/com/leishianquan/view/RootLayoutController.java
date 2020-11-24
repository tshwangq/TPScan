package com.leishianquan.view;


import com.jfoenix.controls.JFXAlert;
import com.leishianquan.MainApp;
import com.leishianquan.util.Dialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class RootLayoutController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleAbout() {
        Dialog.alert_Dialog("关于",null,"唐小风内部共享版!");
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
