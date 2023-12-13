package com.example.informationfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ValidationUnsucc implements Initializable {
    private void closeOnDuration(){
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished( event -> {
                HelloController.validationStage.close();
        });
        delay.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeOnDuration();
    }
}
