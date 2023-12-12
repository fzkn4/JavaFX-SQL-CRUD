package com.example.informationfinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Student {
    private int studentID;
    private String studentFname;
    private String studentLname;
    private String studentCourse;


    public Student(int studentID, String studentFname, String studentLname, String studentCourse) {
        this.studentID = studentID;
        this.studentFname = studentFname;
        this.studentLname = studentLname;
            this.studentCourse = studentCourse;
    }

    public static int size = 0;

    public int getStudentID() {
        return studentID;
    }

    public String getStudentFname() {
        return studentFname;
    }

    public String getStudentLname() {
        return studentLname;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    //getting size of db
    public static int nextID(){
        int len = 0;
        DBConnect connect = new DBConnect();
        Connection connection = connect.getConnection();
            String query = "SELECT MAX(studentID) AS maxID FROM students";
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet maxIdResult = statement.executeQuery();
                if (maxIdResult.next()) {
                    int maxId = maxIdResult.getInt("maxID");
                    len = maxId + 1;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return len;
        }

    //function fetching from db
    public static ObservableList<Student> getInfo() {
        ObservableList<Student> infoList = FXCollections.observableArrayList();

        DBConnect connect = new DBConnect();
        Connection connection = connect.getConnection();

        if (connection != null) {
            String query = "SELECT studentID, fname , lname, Course FROM students";

            try {
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("studentID");
                    String fname = resultSet.getString("fname");
                    String lname = resultSet.getString("lname");
                    String course = resultSet.getString("Course");
                    infoList.add(new Student(id, fname, lname, course));
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Failed to establish a database connection.");
        }
        return infoList;
    }

}
