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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerLogin {
    @FXML
    private Label loginMessege;
    @FXML
    private TextField txtUsernameLogin;
    @FXML
    private TextField txtPasswordLogin;
    @FXML
    private Button btnSendLogin;

    // arraylist yang bertanggung jawab menampung data yang diambil dari file.txt
    private ArrayList<User> dataUser = new ArrayList<User>();


    public void handleLogin(ActionEvent actionEvent) {
        // Mengambil data dari storage
        this.dataUser = FileHandling.getDataUser();

        if((txtUsernameLogin.getText() != "") && (txtPasswordLogin.getText() != "")){
            String username = txtUsernameLogin.getText();
            String password = txtPasswordLogin.getText();

            // temukan dan cocokan data yang diinputkan
            for(User data : this.dataUser){
                // jika sama maka pindah sceen
                if((username.compareTo(data.username) == 0) && (password.compareTo(data.password) == 0)){
                    try{
                        FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("list-password-view.fxml"));
                        Parent secondPane = secondPageLoader.load();
                        Scene secondScene = new Scene(secondPane, 600, 400);

                        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                        primaryStage.setScene(secondScene);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }

            //jika tak ada yang cocok maka tampilkan pesan
            loginMessege.setText("Username and Password is unregistered!");

        }else {
            if((txtUsernameLogin.getText() == "") && (txtPasswordLogin.getText() == "")){
                loginMessege.setText("Please enter username and password!");
            }else if(txtUsernameLogin.getText() == ""){
                loginMessege.setText("Please enter username!");
            }else if(txtPasswordLogin.getText() == ""){
                loginMessege.setText("Please enter password!");
            }
        }
    }
}
