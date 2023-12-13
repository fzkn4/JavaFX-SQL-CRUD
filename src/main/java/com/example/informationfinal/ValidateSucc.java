package com.example.informationfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ValidateSucc implements Initializable {
    public static Stage validationStage;

    private void goMain() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        validationStage = stage;
        HelloApplication.mainStage.getScene().getWindow().hide();
    }
    private void closeOnDuration(){
            PauseTransition delay = new PauseTransition(Duration.seconds(0.05));
            delay.setOnFinished( event -> {
                try {
                    goMain();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            delay.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeOnDuration();
    }
}
