package com.example.informationfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ValidationUnsucc {

    @FXML
    private MFXButton ok;

    @FXML
    void close(ActionEvent event) {
        HelloController.validationStage.close();

    }

}
