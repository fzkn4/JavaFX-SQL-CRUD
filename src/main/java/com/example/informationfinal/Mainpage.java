package com.example.informationfinal;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Mainpage implements Initializable {

    @FXML
    private TableColumn<Student, String> course;

    @FXML
    private TableColumn<Student, String> f_name;

    @FXML
    private TableColumn<Student, Integer> id_no;

    @FXML
    private TableColumn<Student, String> l_name;

    @FXML
    private TableView<Student> table;

    public static Stage logout;

    @FXML
    private MFXButton save;


    @FXML
    private MFXTextField Fname_input;

    @FXML
    private MFXTextField Lname_input;

    @FXML
    private MFXTextField course_input;
    @FXML
    private MFXTextField idNumber_input;

    @FXML
    private Text user_name;
    private ObservableList<Student> data;
    private final DBConnect connects = new DBConnect();
    private final Connection con_pro = connects.getConnection();
    @FXML
    private MFXTextField searchInput;


    @FXML
    void exit(ActionEvent event) {
        ValidateSucc.validationStage.close();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.initOwner(ValidateSucc.validationStage);
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("logoutConfirmation.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        logout = stage;
        stage.showAndWait();
    }

    public void updateTable(){
        id_no.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        f_name.setCellValueFactory(new PropertyValueFactory<>("studentFname"));
        l_name.setCellValueFactory(new PropertyValueFactory<>("studentLname"));
        course.setCellValueFactory(new PropertyValueFactory<>("studentCourse"));

        data = Student.getInfo();
        table.setItems(data);

    }

    public void addStudent() {
        String insertSql = "INSERT INTO students(studentID, fname, lname, Course) VALUES(?, ?, ?, ?)";
        if (Fname_input.getText().length() == 0 || Lname_input.getText().length() == 0  || course_input.getText().length() == 0 ) {
            System.out.println("please complete all text field.");
        } else {
            try {
                PreparedStatement ps = con_pro.prepareStatement(insertSql);
                ps.setString(1, idNumber_input.getText());
                ps.setString(2, capitalize(Fname_input.getText()));
                ps.setString(3, capitalize(Lname_input.getText()));
                ps.setString(4, course_input.getText().toUpperCase());

                ps.execute();
                    System.out.println("Successfully added.");
                    clearTextFields();

            } catch (SQLException e) {
                e.printStackTrace();
                clearTextFields();
            }
        }
    }
    private String capitalize(String str)
    {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }

    private void getSelected(MouseEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            idNumber_input.setText(id_no.getCellData(index).toString());
            Fname_input.setText(f_name.getCellData(index));
            Lname_input.setText(l_name.getCellData(index));
            course_input.setText(course.getCellData(index));
        }
    }

    private void Edit() {
        DBConnect connectNow = new DBConnect();
        Connection connectDB = connectNow.getConnection();

        try {
            String value1 = capitalize(Fname_input.getText());
            String value2 = capitalize(Lname_input.getText());
            String value3 = course_input.getText().toUpperCase();
            String value4 = idNumber_input.getText();

            String sql = "UPDATE students SET fname=?, lname=?, Course=? WHERE studentID=?";
            PreparedStatement pst = connectDB.prepareStatement(sql);
                pst.setString(1, value1);
                pst.setString(2, value2);
                pst.setString(3, value3);
                pst.setString(4, value4);

                pst.execute();
                System.out.println("Successfully Updated.");
                updateTable();
                pst.close();
                connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update Failed.");
        }
    }
    @FXML
    void deleteData(ActionEvent event) {
        Delete();
        idNumber_input.setText(String.valueOf(Student.nextID()));
    }

    @FXML
    void clearText(ActionEvent event) {
        clearTextFields();
        idNumber_input.setText(String.valueOf(Student.nextID()));
    }

    @FXML
    void saveData(ActionEvent event) {
        addStudent();
        updateTable();
        idNumber_input.setText(String.valueOf(Student.nextID()));
    }

    private void clearTextFields(){
        idNumber_input.clear();
        Fname_input.clear();
        Lname_input.clear();
        course_input.clear();
    }

    @FXML
    void updateData(ActionEvent event) {
        Edit();
        clearTextFields();
        idNumber_input.setText(String.valueOf(Student.nextID()));
    }

    private void Delete() {
        DBConnect connectNow = new DBConnect();
        Connection connectDB = connectNow.getConnection();

        String sql = "DELETE FROM students WHERE studentID = ?";
        try {
            PreparedStatement pst = connectDB.prepareStatement(sql);
            pst.setString(1, idNumber_input.getText());
            pst.execute();

            System.out.println("Deleted Successfully.");
            updateTable();
            clearTextFields();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Deletion Failed.");
        }
    }


    @FXML
    void search(InputMethodEvent event) {
        DBConnect connectNow = new DBConnect();
        Connection connectDB = connectNow.getConnection();

        try {
            String sql = "SELECT * FROM students WHERE fname=? or lname=? or Course=?";
            PreparedStatement pst = connectDB.prepareStatement(sql);
            pst.setString(1, searchInput.getText());

            pst.execute();
            updateTable();
            pst.close();
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Search Failed.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idNumber_input.setText(String.valueOf(Student.nextID()));
        Student.nextID();
        updateTable();
        user_name.setText(HelloController.userN);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idNumber_input.setText(Integer.toString(newSelection.getStudentID()));
                Fname_input.setText(newSelection.getStudentFname());
                Lname_input.setText(newSelection.getStudentLname());
                course_input.setText(newSelection.getStudentCourse());
            }
        });

    }
}
