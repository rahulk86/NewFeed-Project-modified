package com.mycompany.newfeedproject;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;


public class NewFeedUserControler {
    
    private DbManager cvoDbManager;
    
    public NewFeedUserControler(DbManager pvoDbManager){
        cvoDbManager = pvoDbManager;
    }
    
    
    public void signUp() throws SQLException, FileNotFoundException{
        boolean Signupexit = true;
        while(Signupexit){
        Scanner s = new Scanner(System.in);    
        System.out.println();
        System.out.println("Enter Full Name");
        String pvsNewFeedUserName = s.nextLine();
		System.out.println();
        System.out.println("Enter Email Id");
        String  pvsNewFeedUserMail = s.nextLine();
		System.out.println();
        System.out.println("Enter Password");
        String  pvsNewFeedUserPaswrd = s.nextLine();
		System.out.println();
        NewFeedUser svoNewFeedUser = NewFeedUser.load(pvsNewFeedUserMail, cvoDbManager);
        System.out.println();
        System.out.println();
        System.out.println("1.[Save]");
        System.out.println();
        System.out.println("2.[Exit]");
        System.out.println();
        System.out.println("Enter selected number");
        int num = s.nextInt(); 
        switch(num){
            case 1 -> {
                try{
                    svoNewFeedUser.setNewFeedUserName(pvsNewFeedUserName);
                    svoNewFeedUser.setNewFeedUserMail(pvsNewFeedUserMail);
                    svoNewFeedUser.setNewFeedUserPaswrd(pvsNewFeedUserPaswrd);
                    svoNewFeedUser.save();
                    System.out.println("Record save sucessfully");
                    Signupexit = false;
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    
                }
                }
            case 2 -> Signupexit = false;
            default -> System.out.println("Invalid number");
        }
        }
        
    }
    
    public void login() throws SQLException, FileNotFoundException{
        boolean Loginexit = true;
        while(Loginexit){
         Scanner s = new Scanner(System.in);      
        System.out.println();
        System.out.println("Enter Email Id");
        String  pvsNewFeedUserMail = s.nextLine();
		System.out.println();
        System.out.println("Enter Password");
        String  pvsNewFeedUserPaswrd = s.nextLine();
		System.out.println();
        NewFeedUser svoNewFeedUser = NewFeedUser.load(pvsNewFeedUserMail, cvoDbManager);
        System.out.println();
        System.out.println();
        System.out.println("1.[Login]");
        System.out.println();
        System.out.println("2.[Exit]");
        System.out.println();
        System.out.println("Enter selected number");
        int num = s.nextInt(); 
        switch(num){
            case 1 -> {
                try{
                    if(svoNewFeedUser==null){
                      throw new RuntimeException("You are not a user of newfeed please check email id");   
                    }
                    svoNewFeedUser.validateUser(pvsNewFeedUserPaswrd);
                    NewFeedLoginUserControler svoNewFeedLoginUserControler = new NewFeedLoginUserControler(svoNewFeedUser, cvoDbManager);
                    svoNewFeedLoginUserControler.aftrLoginDshbrd();
                    Loginexit = false;
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                } }
            case 2 -> Loginexit = false;
            default -> System.out.println("Invalid number");
        }
        }
        
    }
}
