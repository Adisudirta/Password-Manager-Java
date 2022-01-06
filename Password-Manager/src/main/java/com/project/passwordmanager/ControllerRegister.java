package com.project.passwordmanager;

import com.project.passwordmanager.helper.FileHandling;
import com.project.passwordmanager.templateObject.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class ControllerRegister {
    @FXML
    public Label registerMessege;
    @FXML
    private Hyperlink linkToLogin;
    @FXML
    private TextField txtUsernameRegister;
    @FXML
    private TextField txtPasswordRegister;

    private ArrayList<User> dataUser = new ArrayList<User>();

    /*
    * Method ini berfungsi mengecek dan membandingkan tiap data username,
    * jika cocok maka method ini akan return true, jika tidak maka sebaliknya
    * */
    public Boolean isAlreadyUsed(String dataUsername, ArrayList<User> dataUser){
        for(User data : dataUser){
            if(dataUsername.compareTo(data.username) == 0){
                return true;
            }
        }

        return false;
    }

    public void handleRegister(ActionEvent actionEvent){
        //Mengambil data dari storage
        this.dataUser = FileHandling.getDataUser();

        if((txtUsernameRegister.getText() != "") && (txtPasswordRegister.getText() != "") && (this.isAlreadyUsed(txtUsernameRegister.getText(), this.dataUser) == false)){
            //Membentuk ID dengan class UUID
            UUID uuid = UUID.randomUUID();
            String userId = uuid.toString();

            //Membuat object User dan memasukannya ke Arraylist
            this.dataUser.add(new User(txtUsernameRegister.getText(), txtPasswordRegister.getText(), txtUsernameRegister.getText() + userId));

            //Set data ke USER_STORAGE.txt
            FileHandling.setDataUser(this.dataUser);

            //Membuat storage khusus untuk sebuah akun yang sudah diregistrasi
            FileHandling.createStorageFile(this.dataUser.get(this.dataUser.size() - 1).userId);

            try{
                FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("list-password-view.fxml"));
                Parent secondPane = secondPageLoader.load();
                Scene secondScene = new Scene(secondPane, 600, 400);

                Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                primaryStage.setScene(secondScene);
            }catch(IOException e){
                e.printStackTrace();
           }


        }else{
            if((txtUsernameRegister.getText() == "") && (txtPasswordRegister.getText() == "")){
                registerMessege.setText("Please enter username and password!");
            }else if(txtUsernameRegister.getText() == ""){
                registerMessege.setText("Please enter username!");
            }else if(txtPasswordRegister.getText() == ""){
                registerMessege.setText("Please enter password!");
            }else if(this.isAlreadyUsed(txtUsernameRegister.getText(), this.dataUser)){
                registerMessege.setText("Username is already used!");
            }
        }
    }

    public void toLogin(ActionEvent actionEvent) {
        try{
            FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent secondPane = secondPageLoader.load();
            Scene secondScene = new Scene(secondPane, 600, 400);

            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            primaryStage.setScene(secondScene);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
