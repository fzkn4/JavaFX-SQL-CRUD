package com.example.informationfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class HelloController{
    public static Stage validationStage;

    @FXML
    private MFXButton close;
    public static String user = "admin";
    public static String pass = "admin123";

    @FXML
    private MFXButton confirm;

    @FXML
    private MFXPasswordField password;

    @FXML
     private MFXTextField username;
    public static String userN;

    @FXML
    void validate(ActionEvent event) throws IOException {
        Validate();
        username.clear();
        password.clear();

    }

    private void Validate() throws IOException {


        if (valideLogin()){

            Stage stage = new Stage();
            stage.initOwner(HelloApplication.mainStage);
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("validateSucc.fxml"));
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
        }else{

            Stage stage = new Stage();
            stage.initOwner(HelloApplication.mainStage);
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("validationUnsucc.fxml"));
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            validationStage = stage;
            stage.showAndWait();


        }
    }
    @FXML
    void keyPressed(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER){
            Validate();
            username.clear();
            password.clear();
        }
    }

    @FXML
    void windowClose(ActionEvent event) {
        HelloApplication.mainStage.close();
    }

    private boolean valideLogin(){
            DBConnect connectNow = new DBConnect();
            Connection connectDB = connectNow.getConnection();

            String verifylogin = "SELECT * FROM users WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";

            try{
                Statement statement = connectDB.createStatement();
                ResultSet query_result = statement.executeQuery(verifylogin);

                if(query_result.next()) {
                        userN = query_result.getString("name");
                        return true;
                    }else{
                        return false;
                    }

            }catch(Exception e){
                e.printStackTrace();
            }
            return false;
        }
}
