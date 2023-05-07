
package com.mycompany.newfeedproject;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NewFeedItemControler {
   private NewFeedUser cvoNewFeedUser;
   private DbManager   cvoDbManager;
   int MAX_LENGTH = 80;
   public NewFeedItemControler(NewFeedUser pvoNewFeedUser,DbManager pvoDbManager){
        cvoNewFeedUser = pvoNewFeedUser;
        cvoDbManager = pvoDbManager;
    }
   public void NewFeedItemDshbrd(NewFeedPost pvoNewFeedPost) throws SQLException, FileNotFoundException{
        
         boolean NewFeedItemDshbrdExit = true;
         pvoNewFeedPost.laod();
         while (NewFeedItemDshbrdExit) {
            String svsNewFeedUserName = pvoNewFeedPost.getNewFeedUser().getNewFeedUserName();
            int sviUpVoteCount = pvoNewFeedPost.getUpVoteCount();
            int sviDwnVoteCount = pvoNewFeedPost.getDwnVoteCount();
            int sviCmntCount = pvoNewFeedPost.getCmntCount();
            String postDateTime = "";
            LocalDateTime svoNewFeedPostDate = pvoNewFeedPost.getNewFeedPostDate();
            LocalDateTime svoNowDateTime = LocalDateTime.now();
            if ((svoNowDateTime.getYear() - svoNewFeedPostDate.getYear()) > 0) {
                postDateTime = (svoNowDateTime.getYear() - svoNewFeedPostDate.getYear()) + "yr ago";
            } else if ((svoNowDateTime.getMonthValue() - svoNewFeedPostDate.getMonthValue()) > 0) {
                postDateTime = (svoNowDateTime.getMonthValue() - svoNewFeedPostDate.getMonthValue()) + "mnth ago";
            } else if ((svoNowDateTime.getDayOfMonth() - svoNewFeedPostDate.getDayOfMonth()) > 0) {
                postDateTime = (svoNowDateTime.getDayOfMonth() - svoNewFeedPostDate.getDayOfMonth()) + "dy ago";
            } else if ((svoNowDateTime.getHour() - svoNewFeedPostDate.getHour()) > 0) {
                postDateTime = (svoNowDateTime.getHour() - svoNewFeedPostDate.getHour()) + "hr ago";
            } else {
                postDateTime = (svoNowDateTime.getMinute() - svoNewFeedPostDate.getMinute()) + "m ago";
            }
            String svoExit   = "1.[Exit]";
            String svoFollow = "";
            if(cvoNewFeedUser.isFollow(pvoNewFeedPost.getNewFeedUser())){
              svoFollow = "[Following]";  
            }
            else if(pvoNewFeedPost.getNewFeedUser().getNewFeedUserId()!=cvoNewFeedUser.getNewFeedUserId()){
                svoFollow = "0.[Follow]";
            }
            System.out.println();
            System.out.println();
            System.out.println("-".repeat(MAX_LENGTH));
            System.out.println("| "  + "[ " + svsNewFeedUserName + " ]" + " ".repeat(MAX_LENGTH - 12  - svsNewFeedUserName.length() - postDateTime.length()-svoFollow.length()-svoExit.length())+ postDateTime +" " +svoFollow+"   "+svoExit+ " |");
            System.out.println("-".repeat(MAX_LENGTH));
            int range = MAX_LENGTH - 2;
            String svsNewFeedPostTxt = pvoNewFeedPost.getNewFeedPostTxt();
            int StrN = svsNewFeedPostTxt.length();
            for (int i = 0; i < StrN; i += range) {
                int lastIndx = Math.min(i + range, StrN);
                System.out.print("|" + svsNewFeedPostTxt.substring(i, lastIndx));
                if ((lastIndx - i) < range) {
                    System.out.print(" ".repeat(range + i - lastIndx));
                }
                System.out.println("|");

            }
            System.out.println("|" + " ".repeat(range) + "|");
            System.out.println("-".repeat(MAX_LENGTH));
            int num = MAX_LENGTH - 52 - ("" + sviUpVoteCount + "" + sviDwnVoteCount + "" + sviCmntCount).length();
            System.out.println("|" + " ".repeat(num / 5) + "2.[upvote " + sviUpVoteCount + "]" + " ".repeat(num / 5) + "3.[downvote " + sviDwnVoteCount + "]  " + " ".repeat(num / 5) +"4.[comment]" + " ".repeat(num / 5)+"5.[comments " + sviCmntCount + "]" + " ".repeat(num - (num / 5) * 4) + "|");
            System.out.println("-".repeat(MAX_LENGTH));
            
            
             boolean innerNewFeedItemDshbrdExit = true;
            while (innerNewFeedItemDshbrdExit) {
             Scanner s = new Scanner(System.in);
             System.out.println("Enter selected number");
             num = s.nextInt();
            switch(num){
                case 0 -> {
                    try {
                        if (!cvoNewFeedUser.isFollow(pvoNewFeedPost.getNewFeedUser())&&pvoNewFeedPost.getNewFeedUser().getNewFeedUserId() != cvoNewFeedUser.getNewFeedUserId()) {
                            cvoNewFeedUser.saveFollowinUesr(pvoNewFeedPost.getNewFeedUser());
                            System.out.println("You are following "+svsNewFeedUserName);
                            innerNewFeedItemDshbrdExit = false;
                        } else {
                          throw new RuntimeException("Invalid number");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 1 -> {NewFeedItemDshbrdExit=false;innerNewFeedItemDshbrdExit=false;}
                case 2 -> {
                    try {
                        pvoNewFeedPost.upVote(cvoNewFeedUser);
                        innerNewFeedItemDshbrdExit = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        pvoNewFeedPost.dwnVote(cvoNewFeedUser);
                        innerNewFeedItemDshbrdExit = false;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    s = new Scanner(System.in);
                    System.out.println("Leave your thoughts here......."); 
                    String svsNewFeedCmntTxt = s.nextLine();
                    try{
                      pvoNewFeedPost.comment(cvoNewFeedUser, svsNewFeedCmntTxt);
                      innerNewFeedItemDshbrdExit = false;
                    }
                    catch(Exception e){
                      System.out.println(e.getMessage());   
                    }
                }
                case 5 -> {
                    comments(pvoNewFeedPost);
                     innerNewFeedItemDshbrdExit = false;
                }
                
               default -> System.out.println("Invalid number");
            }
          }
            
        }
    }
   
   
   
   
   
   private void comments(NewFeedPost pvoNewFeedPost) throws SQLException, FileNotFoundException{
       boolean NewFeedItemcommentsExit = true;
       String svsNewFeedUserName = pvoNewFeedPost.getNewFeedUser().getNewFeedUserName();
       int sviUpVoteCount = pvoNewFeedPost.getUpVoteCount();
       int sviDwnVoteCount = pvoNewFeedPost.getDwnVoteCount();
       int sviCmntCount = pvoNewFeedPost.getCmntCount();
       String postDateTime = "";
       LocalDateTime svoNewFeedPostDate = pvoNewFeedPost.getNewFeedPostDate();
       LocalDateTime svoNowDateTime = LocalDateTime.now();
       if ((svoNowDateTime.getYear() - svoNewFeedPostDate.getYear()) > 0) {
           postDateTime = (svoNowDateTime.getYear() - svoNewFeedPostDate.getYear()) + "yr ago";
       } else if ((svoNowDateTime.getMonthValue() - svoNewFeedPostDate.getMonthValue()) > 0) {
           postDateTime = (svoNowDateTime.getMonthValue() - svoNewFeedPostDate.getMonthValue()) + "mnth ago";
       } else if ((svoNowDateTime.getDayOfMonth() - svoNewFeedPostDate.getDayOfMonth()) > 0) {
           postDateTime = (svoNowDateTime.getDayOfMonth() - svoNewFeedPostDate.getDayOfMonth()) + "dy ago";
       } else if ((svoNowDateTime.getHour() - svoNewFeedPostDate.getHour()) > 0) {
           postDateTime = (svoNowDateTime.getHour() - svoNewFeedPostDate.getHour()) + "hr ago";
       } else {
           postDateTime = (svoNowDateTime.getMinute() - svoNewFeedPostDate.getMinute()) + "m ago";
       }
       String svoFollow = "";
       if (cvoNewFeedUser.isFollow(pvoNewFeedPost.getNewFeedUser())) {
           svoFollow = "[Following]";
       }
       System.out.println();
       System.out.println();
       System.out.println("-".repeat(MAX_LENGTH));
       System.out.println("| " + "[ " + svsNewFeedUserName + " ]" + " ".repeat(MAX_LENGTH - 9 - svsNewFeedUserName.length() - postDateTime.length() - svoFollow.length()) + postDateTime + " " + svoFollow + " |");
       System.out.println("-".repeat(MAX_LENGTH));
       int range = MAX_LENGTH - 2;
       String svsNewFeedPostTxt = pvoNewFeedPost.getNewFeedPostTxt();
       int StrN = svsNewFeedPostTxt.length();
       for (int i = 0; i < StrN; i += range) {
           int lastIndx = Math.min(i + range, StrN);
           System.out.print("|" + svsNewFeedPostTxt.substring(i, lastIndx));
           if ((lastIndx - i) < range) {
               System.out.print(" ".repeat(range + i - lastIndx));
           }
           System.out.println("|");

       }
       System.out.println("|" + " ".repeat(range) + "|");
       System.out.println("-".repeat(MAX_LENGTH));
       int num = MAX_LENGTH - 43 - ("" + sviUpVoteCount + "" + sviDwnVoteCount + "" + sviCmntCount).length();
       System.out.println("|" + " ".repeat(num / 5) + "[upvote " + sviUpVoteCount + "]" + " ".repeat(num / 5) + "[downvote " + sviDwnVoteCount + " ]" + " ".repeat(num / 5) + "[comment]" + " ".repeat(num / 5) + "[comments " + sviCmntCount + "]" + " ".repeat(num - (num / 5) * 4) + "|");
       System.out.println("-".repeat(MAX_LENGTH));

       pvoNewFeedPost.laodComment();
           int MAX_LENGTH = 60;
       while (NewFeedItemcommentsExit) {
              List<NewFeedCmnt> svoNewFeedCmntLst = pvoNewFeedPost.getAllComments();
             int NewFeedCmntLstLength = svoNewFeedCmntLst.size();
          NewFeedItemcommentsExit = false;
         for (int indx = 1; indx<=NewFeedCmntLstLength;indx++) {
             NewFeedCmnt svoNewFeedCmnt = svoNewFeedCmntLst.get(indx-1);
             svsNewFeedUserName         = svoNewFeedCmnt.getNewFeedUser().getNewFeedUserName();
             sviUpVoteCount             = svoNewFeedCmnt.getUpVoteCount();
             sviDwnVoteCount            = svoNewFeedCmnt.getDwnVoteCount();
             sviCmntCount               = svoNewFeedCmnt.getRplyCount();
             postDateTime = "";
             svoNewFeedPostDate = svoNewFeedCmnt.getNewFeedCmntDate();
             svoNowDateTime = LocalDateTime.now();
             if((svoNowDateTime.getYear()-svoNewFeedPostDate.getYear())>0){
                 postDateTime = (svoNowDateTime.getYear()-svoNewFeedPostDate.getYear())+"yr ago";
             }
             else if((svoNowDateTime.getMonthValue()-svoNewFeedPostDate.getMonthValue())>0){
                 postDateTime = (svoNowDateTime.getMonthValue()-svoNewFeedPostDate.getMonthValue())+"mnth ago";
             }
             else if((svoNowDateTime.getDayOfMonth()-svoNewFeedPostDate.getDayOfMonth())>0){
                 postDateTime = (svoNowDateTime.getDayOfMonth()-svoNewFeedPostDate.getDayOfMonth())+"dy ago";
             }
             else if((svoNowDateTime.getHour()-svoNewFeedPostDate.getHour())>0){
                 postDateTime = (svoNowDateTime.getHour()-svoNewFeedPostDate.getHour())+"hr ago";
             }
             else{
                 postDateTime = (svoNowDateTime.getMinute()-svoNewFeedPostDate.getMinute())+"m ago"; 
             }
             System.out.println();
             System.out.println("-".repeat(MAX_LENGTH));
             System.out.println("| "+indx+"." + "[ " + svsNewFeedUserName + " ]" + " ".repeat(MAX_LENGTH - 9 - (indx+"").length()-svsNewFeedUserName.length()-postDateTime.length()) +postDateTime+ " |");
             System.out.println("-".repeat(MAX_LENGTH));
             range = MAX_LENGTH - 2;
             svsNewFeedPostTxt = svoNewFeedCmnt.getNewFeedCmntTxt();
             StrN = svsNewFeedPostTxt.length();
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
             num = MAX_LENGTH - 32 - ("" + sviUpVoteCount + "" + sviDwnVoteCount + "" + sviCmntCount).length();
             System.out.println("|" + " ".repeat(num / 4) + "[upvote " + sviUpVoteCount + "]" + " ".repeat(num / 4) + "[downvote " + sviDwnVoteCount + "]" + " ".repeat(num / 4) + "["+sviCmntCount+" replies" + "]" + " ".repeat(num - (num / 4) * 3) + "|");
             System.out.println("-".repeat(MAX_LENGTH));
         }
      
           boolean innerNewFeedItemcommentsExit= true;
           while (innerNewFeedItemcommentsExit) {
               Scanner s = new Scanner(System.in);
               System.out.println("0.[Exit]");
               System.out.println("Select a news item or enter zero for exit");
               num = s.nextInt();
               switch (num) {
                   case 0 -> {
                       NewFeedItemcommentsExit = false;
                       innerNewFeedItemcommentsExit = false;
                   }
                   default -> {
                       try {
                           if (num <= NewFeedCmntLstLength) {
                               NewFeedItmCommentsControler svoNewFeedItmCommentsControler = new NewFeedItmCommentsControler(cvoNewFeedUser, cvoDbManager);
                               NewFeedCmnt svoNewFeedCmnt = svoNewFeedCmntLst.get(num - 1);
                               svoNewFeedItmCommentsControler.commentsDsbrd(svoNewFeedCmnt);
                               innerNewFeedItemcommentsExit = false;
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
