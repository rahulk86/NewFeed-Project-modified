
package com.mycompany.newfeedproject;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class shownewsfeedControler {
    private NewFeedUser cvoNewFeedUser;
    private DbManager   cvoDbManager;
    int MAX_LENGTH = 80;
    public shownewsfeedControler(NewFeedUser pvoNewFeedUser,DbManager pvoDbManager){
        cvoNewFeedUser = pvoNewFeedUser;
        cvoDbManager = pvoDbManager;
    }
     public void newsfeedDshbrd() throws SQLException, FileNotFoundException {
        boolean newsfeedDshbrdExit = true;

        while (newsfeedDshbrdExit) {
          shownewsfeed svoShownewsfeed = new shownewsfeed(cvoNewFeedUser, cvoDbManager);
          List<NewFeedPost> svoNewFeedPostLst = svoShownewsfeed.getNewFeedPostLst();
          int NewFeedPostLstLength = svoNewFeedPostLst.size();
          
         for (int indx = 1; indx<=NewFeedPostLstLength;indx++) {
             NewFeedPost svoNewFeedPost = svoNewFeedPostLst.get(indx - 1);
             String svsNewFeedUserName = svoNewFeedPost.getNewFeedUser().getNewFeedUserName();
             int sviUpVoteCount = svoNewFeedPost.getUpVoteCount();
             int sviDwnVoteCount = svoNewFeedPost.getDwnVoteCount();
             int sviCmntCount = svoNewFeedPost.getCmntCount();
             String postDateTime = "";
             DbManager.DBdateTime svoDBdateTime = DbManager.diffLocalDateTime(svoNewFeedPost.getNewFeedPostDate(), LocalDateTime.now());
             if (svoDBdateTime.getDBYear() > 0) {
                 postDateTime = svoDBdateTime.getDBYear() + "yr ago";
             } else if (svoDBdateTime.getDBMonth() > 0) {
                 postDateTime = svoDBdateTime.getDBMonth() + "mnth ago";
             } else if (svoDBdateTime.getDBDay() > 0) {
                 postDateTime = svoDBdateTime.getDBDay() + "dy ago";
             } else if (svoDBdateTime.getDBHour() > 0) {
                 postDateTime = svoDBdateTime.getDBHour() + "hr ago";
             } else {
                 postDateTime = svoDBdateTime.getDBMinutes() + "m ago";
             }
             System.out.println();
             System.out.println();
             System.out.println("-".repeat(MAX_LENGTH));
             System.out.println("| "+indx+"." + "[ " + svsNewFeedUserName + " ]" + " ".repeat(MAX_LENGTH - 9 - (indx+"").length()-svsNewFeedUserName.length()-postDateTime.length()) +postDateTime+ " |");
             System.out.println("-".repeat(MAX_LENGTH));
             int range = MAX_LENGTH - 2;
             String svsNewFeedPostTxt = svoNewFeedPost.getNewFeedPostTxt();
             int StrN = svsNewFeedPostTxt.length();
             for (int i = 0; i < StrN; i += range) {
                 int lastIndx = Math.min(i+range,StrN);
                 System.out.print("|" + svsNewFeedPostTxt.substring(i, lastIndx));
                 if((lastIndx-i)<range){
                   System.out.print(" ".repeat(range+i-lastIndx));  
                 }
                  System.out.println("|");    
                 
             }
             System.out.println("|" + " ".repeat(range) + "|");
             System.out.println("-".repeat(MAX_LENGTH));
             int num = MAX_LENGTH - 33 - ("" + sviUpVoteCount + "" + sviDwnVoteCount + "" + sviCmntCount).length();
             System.out.println("|" + " ".repeat(num / 4) + "[upvote " + sviUpVoteCount + "]" + " ".repeat(num / 4) + "[downvote " + sviDwnVoteCount + "]" + " ".repeat(num / 4) + "[comments " + sviCmntCount + "]" + " ".repeat(num - (num / 4) * 3) + "|");
             System.out.println("-".repeat(MAX_LENGTH));
         }
          boolean innernewsfeedDshbrdExit = true;
            while (innernewsfeedDshbrdExit) {
             Scanner s = new Scanner(System.in);
             System.out.println("0.[Exit]");
             System.out.println("Select a news item or enter zero for exit");
            int num = s.nextInt();
            switch(num){
                case 0 -> {newsfeedDshbrdExit=false;innernewsfeedDshbrdExit=false;}
                default -> {
                    try {
                        if (num <= NewFeedPostLstLength) {
                            NewFeedItemControler svoNewFeedItemControler = new NewFeedItemControler(cvoNewFeedUser, cvoDbManager);
                            NewFeedPost svoNewFeedPost = svoNewFeedPostLst.get(num-1);
                            svoNewFeedItemControler.NewFeedItemDshbrd(svoNewFeedPost);
                            innernewsfeedDshbrdExit=false;
                        } else {
                           throw new RuntimeException("Invalid input");
                        }
                    } catch (Exception e) {
                      System.out.println(e.getMessage());
                    }
                }
            }
          }
       }
     }
}
