package com.project.passwordmanager;


import com.project.passwordmanager.helper.FileHandling;
import com.project.passwordmanager.templateObject.Account;
import com.project.passwordmanager.templateObject.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class ControllerList {
    @FXML
    private Text succesAddMassage;
    @FXML
    private Text succuesEditDeleteMassage;
    @FXML
    private Text txtAccount;
    @FXML
    private TextField search;
    @FXML
    private Text errorSearchMassage;
    @FXML
    private Text errorEditDeleteMessege;
    @FXML
    private TextField editDeleteUsername;
    @FXML
    private TextField editDeletePassword;
    @FXML
    private TextField addUserPM;
    @FXML
    private Label errorMassage;
    @FXML
    private TextField addUsername;
    @FXML
    private TextField addPassword;
    @FXML
    private TextField addAccount;

    // Arraylist penampung data account
    private ArrayList<Account> dataAccount = new ArrayList<Account>();
    // Arraylist  penampung data User
    private ArrayList<User> dataUser = new ArrayList<User>();
    // Property yang menampung data user yang menggunakan program sekarang
    private String userNow;

    // method untuk melakukan oprasi CREATE
    public void handleAddData(ActionEvent actionEvent){
        //Ambil data dari storage user
        this.dataUser = FileHandling.getDataUser();

        if(addUserPM.getText() != ""){
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString();

            //cari tau user yang memakai program sekarang
            for(User data : this.dataUser){
                if(data.username.compareTo(addUserPM.getText()) == 0){
                    this.userNow = data.userId;
                }
            }

            // jika tak ada yang cocok maka tampilkan pesan dan matikan proses method
            if(this.userNow == null){
                errorMassage.setText("Username PM is not exist!");
                return;
            }

            // ambil data account berdasarkan userID sekarang
            this.dataAccount = FileHandling.getDataAccount(this.userNow);

            // tambahkan data yang telah diinputkan user
            dataAccount.add(new Account(addAccount.getText(), addUsername.getText(), addPassword.getText(), addAccount.getText() + id));

            // set data pada storage
            FileHandling.setDataAccount(this.dataAccount, this.userNow);
            succesAddMassage.setText("Data succesfully added!");
        }else{
           if(addUserPM.getText() == ""){
               errorMassage.setText("Please enter user PM!");
           }
        }
    }

    // method untuk melakukan oprasi UPDATE
    public void handleEditData(ActionEvent actionEvent) {
        if(addUserPM.getText() != ""){
            // ambil data dari storage
            this.dataUser = FileHandling.getDataUser();

            // ketahui user yang menggunakan program sekarang
            for(User data : this.dataUser){
                if(data.username.compareTo(addUserPM.getText()) == 0){
                    this.userNow = data.userId;
                }
            }

            // Ambil data pada storage berdasarkan userID
            this.dataAccount = FileHandling.getDataAccount(this.userNow);

            // cari baris yang ingin diedit berdasarkan account (contoh: Facebook)
            for(Account data : this.dataAccount){
                if(data.accountName.compareTo(txtAccount.getText()) == 0){
                    data.username = editDeleteUsername.getText();
                    data.password = editDeletePassword.getText();
                    succuesEditDeleteMassage.setText("Data succesfully edited!");
                }
            }

            // setelah selesai mengedit maka  set data pada storage
            FileHandling.setDataAccount(this.dataAccount, this.userNow);
        }else{
            errorEditDeleteMessege.setText("Please enter User PM feild!");
        }
    }

    // method untuk melakukan oprasi DELETE
    public void handleDeleteData(ActionEvent actionEvent) {
        if(addUserPM.getText() != ""){
            // ambil data user dari storage
            this.dataUser = FileHandling.getDataUser();

            // cari tau user sekarang
            for(User data : this.dataUser){
                if(data.username.compareTo(addUserPM.getText()) == 0){
                    this.userNow = data.userId;
                }
            }

            // ambil data Account pada storage
            this.dataAccount = FileHandling.getDataAccount(this.userNow);

            // jika nama account sama maka remove
           for(int i = 0; i < this.dataAccount.size(); i++){
               if(this.dataAccount.get(i).accountName.compareTo(txtAccount.getText()) == 0){
                   this.dataAccount.remove(i);
                   succuesEditDeleteMassage.setText("Data succesfully deleted!");
               }
           }

            // set kembali data pada storage
            FileHandling.setDataAccount(this.dataAccount, this.userNow);
        }else{
            errorEditDeleteMessege.setText("Please enter User PM feild!");
        }
    }

    // method untuk melakukan oprasi READ
    public void handleSearch(ActionEvent actionEvent) {
        if((addUserPM.getText() != "") && (search.getText() != "")){
            // ambil data user pada storage user
            this.dataUser = FileHandling.getDataUser();

            // cari tau user yang menggunakan program sekarang
            for(User data : this.dataUser){
                if(data.username.compareTo(addUserPM.getText()) == 0){
                    this.userNow = data.userId;
                }
            }


            if(this.userNow == null){
                errorSearchMassage.setText("Username PM is not exist!");
                return;
            }

            // ambil data acount pada storage
            this.dataAccount = FileHandling.getDataAccount(this.userNow);

            // cocokan tiap data, jika cocok maka tampilkan
            for(Account data : this.dataAccount){
                if(data.accountName.compareTo(search.getText()) == 0){
                    txtAccount.setText(data.accountName);
                    editDeleteUsername.setText(data.username);
                    editDeletePassword.setText(data.password);
                }
            }
        }else{
            if((addUserPM.getText() == "") && (search.getText() == "")){
                errorSearchMassage.setText("Please enter User PM and Search feild!");
            }else if((addUserPM.getText() == "") && (search.getText() != "")){
                errorSearchMassage.setText("Please enter User PM feild");
            }else if((addUserPM.getText() != "") && (search.getText() == "")){
                errorSearchMassage.setText("Please enter Search feild");
            }
        }
    }
}
