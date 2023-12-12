package com.example.informationfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    void logout(ActionEvent event) throws IOException {
        ValidateSucc.validationStage.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

}
