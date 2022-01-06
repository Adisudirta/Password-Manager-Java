package com.project.passwordmanager.helper;

import com.project.passwordmanager.templateObject.Account;
import com.project.passwordmanager.templateObject.User;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/*
* Class ini menampung method static yang kedepannya digunakan
* dalam memodifikasi isi dari file.txt
* */

public class FileHandling {

    /*
    * interface ini digunakan untuk
    * menampung logic dari lambda dalam
    * memisahkan tiap data dengan karakter ","
    * */
    interface Separate{
        String[] opration(String d);
    }

    /*
    * method ini berguna dalam membuat / menginisialisasi sebuah file
    * */
    public static void createStorageFile(String fileName){
        try{
            File myFile = new File("C:\\Users\\yanad\\IdeaProjects\\Password-Manager\\src\\storage\\" + fileName + ".txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File is already exist");
            }
        }catch (Exception e){
            System.out.println("An error occurred");
        }
    }

    /*
    * Method yang berguna untuk mengeset / menulis sesuatu pada USER_STORAGE.txt
    * */
    public static void setDataUser(ArrayList<User> datas){
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\yanad\\IdeaProjects\\Password-Manager\\src\\storage\\USER_STORAGE.txt");

            for(User data : datas){
                myWriter.write(data.username + "," + data.password + "," + data.userId + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*
    * Method untuk mengambil data yang telah ditulis pada USER_STORAGGE.txt
    * */
    public static ArrayList<User> getDataUser(){
        ArrayList<User> dataUser = new ArrayList<User>();
        Separate wordSeparate = (String d)-> d.split(",");

        try {
            File myObj = new File("C:\\Users\\yanad\\IdeaProjects\\Password-Manager\\src\\storage\\USER_STORAGE.txt");
            Scanner myReader = new Scanner(myObj);

            //check the file is empty or not
            if(myObj.length() != 1){
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] dataList = wordSeparate.opration(data);
                    dataUser.add(new User(dataList[0], dataList[1], dataList[2]));
                }

                myReader.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return dataUser;
    }

    /*
    * Method yang berguna untuk mengeset / menulis sesuatu pada <Username><UserID>.txt
    * */
    public static void setDataAccount(ArrayList<Account> datas, String fileName){
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\yanad\\IdeaProjects\\Password-Manager\\src\\storage\\" + fileName + ".txt");

            for(Account data : datas){
                myWriter.write(data.accountName + "," + data.username + "," + data.password + "," + data.accountId + "\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /*
    * Method yang berguna untuk mengambil data yang telah ditulis pada <Username><UserID>.txt
    * */
    public static ArrayList<Account> getDataAccount(String fileName){
        ArrayList<Account> dataAccount = new ArrayList<Account>();
        Separate wordSeparate = (String d)-> d.split(",");

        try {
            File myObj = new File("C:\\Users\\yanad\\IdeaProjects\\Password-Manager\\src\\storage\\"+ fileName +".txt");
            Scanner myReader = new Scanner(myObj);

            //check the file is empty or not
            if(myObj.length() != 1){
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] dataList = wordSeparate.opration(data);
                    dataAccount.add(new Account(dataList[0], dataList[1], dataList[2],dataList[3]));
                }

                myReader.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return dataAccount;
    }
}
