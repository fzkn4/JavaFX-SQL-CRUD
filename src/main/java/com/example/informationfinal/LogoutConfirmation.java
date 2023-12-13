package com.example.informationfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LogoutConfirmation {

    @FXML
    private MFXButton cancel;

    @FXML
    private MFXButton ok;

    @FXML
    void close(ActionEvent event) {
        Mainpage.logout.close();
    }

    @FXML
    void logout(ActionEvent event) {
        ValidateSucc.validationStage.close();
        Mainpage.logout.close();
        HelloApplication.mainStage.show();
    }
    @FXML
    void onKeyPressed(KeyEvent event) {

    }

}
