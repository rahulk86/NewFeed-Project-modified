
package com.mycompany.newfeedproject;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class NewFeedLoginUserControler {
    private NewFeedUser cvoNewFeedUser;
    private DbManager   cvoDbManager;
    public NewFeedLoginUserControler(NewFeedUser pvoNewFeedUser,DbManager pvoDbManager){
        cvoNewFeedUser = pvoNewFeedUser;
        cvoDbManager = pvoDbManager;
    }
    public void aftrLoginDshbrd() throws SQLException, FileNotFoundException {
        boolean aftrLoginDshbrdExit = true;

        while (aftrLoginDshbrdExit) {
            String svoNewFeedUserName = cvoNewFeedUser.getNewFeedUserName();
            String svoNewFeedUserMail = cvoNewFeedUser.getNewFeedUserMail();
            System.out.println("-------------"+"-".repeat(NewFeedUser.MAX_LENGTH)+"-");
            System.out.println("| 1.[Exit]   "+" ".repeat(NewFeedUser.MAX_LENGTH-9)+"2.[Post] |");
            System.out.println("-------------"+"-".repeat(NewFeedUser.MAX_LENGTH)+"-");
            System.out.println("| Name   :   "+svoNewFeedUserName+" ".repeat(NewFeedUser.MAX_LENGTH-svoNewFeedUserName.length())+"|");
            System.out.println("| Email  :   "+svoNewFeedUserMail+" ".repeat(NewFeedUser.MAX_LENGTH-svoNewFeedUserMail.length())+"|");
            System.out.println("-------------"+"-".repeat(NewFeedUser.MAX_LENGTH)+"-");
            int arngMid = NewFeedUser.MAX_LENGTH-4;
            System.out.println("|"+" ".repeat(arngMid - arngMid/2)+"3.[shownewsfeed]"+" ".repeat(arngMid/2)+"|");
            System.out.println("-------------"+"-".repeat(NewFeedUser.MAX_LENGTH)+"-");
            boolean inneraftrLoginDshbrdExit = true;
            while (inneraftrLoginDshbrdExit) {
             Scanner s = new Scanner(System.in);
            System.out.println("Enter selected number");
            int num = s.nextInt();
            switch(num){
                case 1 -> {aftrLoginDshbrdExit=false;inneraftrLoginDshbrdExit=false;}
                case 2 -> {
                  s = new Scanner(System.in);
                  System.out.println("What do you want to talk about?");  
                  String svsNewFeedPost = s.nextLine();
                  NewFeedPost svoNewFeedPost = new NewFeedPost(cvoNewFeedUser, cvoDbManager);
                  try{
                  svoNewFeedPost.setNewFeedPostTxt(svsNewFeedPost);
                  svoNewFeedPost.save();
                  System.out.println("Post submitted sucessfully");  
                  }
                  catch(Exception e){
                     System.out.println(e.getMessage());  
                  }
                  
                }
                case 3 -> {
                    shownewsfeedControler svoshownewsfeedControler = new shownewsfeedControler(cvoNewFeedUser, cvoDbManager);
                    svoshownewsfeedControler.newsfeedDshbrd();
                    inneraftrLoginDshbrdExit = false;
                }
                default -> System.out.println("Invalid number");
            }
          }
        }
    }
}
