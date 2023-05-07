
package com.mycompany.newfeedproject;
 
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class NewFeedItmCommentsControler {
   private NewFeedUser cvoNewFeedUser;
   private DbManager   cvoDbManager;
   int MAX_LENGTH = 65;
   public NewFeedItmCommentsControler(NewFeedUser pvoNewFeedUser,DbManager pvoDbManager){
        cvoNewFeedUser = pvoNewFeedUser;
        cvoDbManager = pvoDbManager;
    }
    public void commentsDsbrd(NewFeedCmnt pvoNewFeedCmnt) throws SQLException, FileNotFoundException {
        boolean NewFeedItemDshbrdExit = true;
        pvoNewFeedCmnt.laod();
        while (NewFeedItemDshbrdExit) {
            String svsNewFeedUserName = pvoNewFeedCmnt.getNewFeedUser().getNewFeedUserName();
            int sviUpVoteCount = pvoNewFeedCmnt.getUpVoteCount();
            int sviDwnVoteCount = pvoNewFeedCmnt.getDwnVoteCount();
            int sviCmntCount = pvoNewFeedCmnt.getRplyCount();
            String postDateTime = "";
            DbManager.DBdateTime svoDBdateTime = DbManager.diffLocalDateTime(pvoNewFeedCmnt.getNewFeedCmntDate(), LocalDateTime.now());
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
            String svoExit = "1.[Exit]";
            System.out.println();
            System.out.println();
            System.out.println("-".repeat(MAX_LENGTH));
            System.out.println("| " + "[ " + svsNewFeedUserName + " ]" + " ".repeat(MAX_LENGTH - 11 - svsNewFeedUserName.length() - postDateTime.length() - svoExit.length()) + postDateTime + "   " + svoExit + " |");
            System.out.println("-".repeat(MAX_LENGTH));
            int range = MAX_LENGTH - 2;
            String svsNewFeedPostTxt = pvoNewFeedCmnt.getNewFeedCmntTxt();
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
            int num = MAX_LENGTH - 49 - ("" + sviUpVoteCount + "" + sviDwnVoteCount + "" + sviCmntCount).length();
            System.out.println("|" + " ".repeat(num / 5) + "2.[upvote " + sviUpVoteCount + "]" + " ".repeat(num / 5) + "3.[downvote " + sviDwnVoteCount + "]  " + " ".repeat(num / 5) + "4.[reply]" + " ".repeat(num / 5) + "5.["+sviCmntCount+" replies" + "]" + " ".repeat(num - (num / 5) * 4) + "|");
            System.out.println("-".repeat(MAX_LENGTH));

            boolean innerNewFeedItemDshbrdExit = true;
            while (innerNewFeedItemDshbrdExit) {
                Scanner s = new Scanner(System.in);
                System.out.println("Enter selected number");
                num = s.nextInt();
                switch (num) {
                    case 1 -> {
                        NewFeedItemDshbrdExit = false;
                        innerNewFeedItemDshbrdExit = false;
                    }
                    case 2 -> {
                        try {
                            pvoNewFeedCmnt.upVote(cvoNewFeedUser);
                            innerNewFeedItemDshbrdExit = false;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 3 -> {
                        try {
                            pvoNewFeedCmnt.dwnVote(cvoNewFeedUser);
                            innerNewFeedItemDshbrdExit = false;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> {
                        s = new Scanner(System.in);
                        System.out.println("Add a reply");
                        String svsNewFeedCmntTxt = s.nextLine();
                        try {
                            pvoNewFeedCmnt.reply(cvoNewFeedUser, svsNewFeedCmntTxt);
                            innerNewFeedItemDshbrdExit = false;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 5 -> {
                    replies(pvoNewFeedCmnt);
                    innerNewFeedItemDshbrdExit = false;
                    }
                    default ->
                        System.out.println("Invalid number");
                }
            }

        }
    }
    
    
    
    private void replies(NewFeedCmnt pvoNewFeedCmnt) throws SQLException, FileNotFoundException{
            boolean NewFeedItemrRepliesExit = true;
            String svsNewFeedUserName = pvoNewFeedCmnt.getNewFeedUser().getNewFeedUserName();
            int sviUpVoteCount = pvoNewFeedCmnt.getUpVoteCount();
            int sviDwnVoteCount = pvoNewFeedCmnt.getDwnVoteCount();
            int sviCmntCount = pvoNewFeedCmnt.getRplyCount();
            String postDateTime = "";
            DbManager.DBdateTime svoDBdateTime = DbManager.diffLocalDateTime(pvoNewFeedCmnt.getNewFeedCmntDate(), LocalDateTime.now());
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
            String svoExit = "1.[Exit]";
            System.out.println();
            System.out.println();
            System.out.println("-".repeat(MAX_LENGTH));
            System.out.println("| " + "[ " + svsNewFeedUserName + " ]" + " ".repeat(MAX_LENGTH - 11 - svsNewFeedUserName.length() - postDateTime.length() - svoExit.length()) + postDateTime + "   " + svoExit + " |");
            System.out.println("-".repeat(MAX_LENGTH));
            int range = MAX_LENGTH - 2;
            String svsNewFeedPostTxt = pvoNewFeedCmnt.getNewFeedCmntTxt();
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
            int num = MAX_LENGTH - 49 - ("" + sviUpVoteCount + "" + sviDwnVoteCount + "" + sviCmntCount).length();
            System.out.println("|" + " ".repeat(num / 5) + "2.[upvote " + sviUpVoteCount + "]" + " ".repeat(num / 5) + "3.[downvote " + sviDwnVoteCount + "]  " + " ".repeat(num / 5) + "4.[reply]" + " ".repeat(num / 5) + "5.["+sviCmntCount+" replies" + "]" + " ".repeat(num - (num / 5) * 4) + "|");
            System.out.println("-".repeat(MAX_LENGTH));

       
       
       pvoNewFeedCmnt.loadRplies();
           int MAX_LENGTH = 55;
       while (NewFeedItemrRepliesExit) {
              List<NewFeedRply> svoNewFeedRplyLst = pvoNewFeedCmnt.getAllReplies();
             int NewFeedCmntLstLength = svoNewFeedRplyLst.size();
          NewFeedItemrRepliesExit = false;
         for (int indx = 1; indx<=NewFeedCmntLstLength;indx++) {
             NewFeedRply svoNewFeedRply = svoNewFeedRplyLst.get(indx-1);
             svsNewFeedUserName         = svoNewFeedRply.getNewFeedUser().getNewFeedUserName();
             postDateTime = "";
            svoDBdateTime =   DbManager.diffLocalDateTime(pvoNewFeedCmnt.getNewFeedCmntDate(), LocalDateTime.now());
            if (svoDBdateTime.getDBYear()> 0) {
                postDateTime = svoDBdateTime.getDBYear() + "yr ago";
            } else if (svoDBdateTime.getDBMonth() > 0) {
                postDateTime = svoDBdateTime.getDBMonth()+ "mnth ago";
            } else if (svoDBdateTime.getDBDay()> 0) {
                postDateTime = svoDBdateTime.getDBDay() + "dy ago";
            } else if (svoDBdateTime.getDBHour()> 0) {
                postDateTime = svoDBdateTime.getDBHour()+ "hr ago";
            } else {
                postDateTime = svoDBdateTime.getDBMinutes()+ "m ago";
            }
             System.out.println();
             System.out.println(" ".repeat(10)+"-".repeat(MAX_LENGTH));
             System.out.println(" ".repeat(10)+"| "+indx+"." + "[ " + svsNewFeedUserName + " ]" + " ".repeat(MAX_LENGTH - 9 - (indx+"").length()-svsNewFeedUserName.length()-postDateTime.length()) +postDateTime+ " |");
             System.out.println(" ".repeat(10)+"-".repeat(MAX_LENGTH));
             range = MAX_LENGTH - 2;
             svsNewFeedPostTxt = svoNewFeedRply.getNewFeedRplyTxt();
             StrN = svsNewFeedPostTxt.length();
             for (int i = 0; i < StrN; i += range) {
                 int lastIndx = Math.min(i+range,StrN);
                 System.out.print(" ".repeat(10)+"|" + svsNewFeedPostTxt.substring(i, lastIndx));
                 if((lastIndx-i)<range){
                   System.out.print(" ".repeat(range+i-lastIndx));  
                 }
                  System.out.println("|");    
                 
             }
             System.out.println(" ".repeat(10)+"|" + " ".repeat(range) + "|");
             System.out.println(" ".repeat(10)+"-".repeat(MAX_LENGTH));
             System.out.println(" ".repeat(10)+"-".repeat(MAX_LENGTH));
         }
      
           boolean innerNewFeedItemcommentsExit= true;
           while (innerNewFeedItemcommentsExit) {
               Scanner s = new Scanner(System.in);
               System.out.println("0.[Exit]");
               num = s.nextInt();
               switch (num) {
                   case 0 -> {
                       NewFeedItemrRepliesExit = false;
                       innerNewFeedItemcommentsExit = false;
                   }
                   default -> {
                      System.out.print("Invalid number");
                   }
               }
           }
       }
   }
}
