
package com.mycompany.newfeedproject;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class NewFeedUser {
    private int    cviNewFeedActv = 0;

    private int    cvsNewFeedUserId = -1;
    private String cvsNewFeedUserName = "";
    private String cvsNewFeedUserMail = "";
    private String cvsNewFeedUserPaswrd = "";
    private Map<Integer,NewFeedUser> cvoFollowing;
    private DbManager cvoDbManager;
    public static int MAX_LENGTH = 35 ;
    private NewFeedUser(DbManager pvoDbManager){
        cvoDbManager = pvoDbManager;
        cvoFollowing = new HashMap<>();
    }
 
    public int getNewFeedActv() {
        return cviNewFeedActv;
    }

    public void setNewFeedActv(int pviNewFeedActv) {
        this.cviNewFeedActv = pviNewFeedActv;
    }

    public String getNewFeedUserName() {
        return cvsNewFeedUserName;
    }

    public void setNewFeedUserName(String pvsNewFeedUserName) {
        if(pvsNewFeedUserName.length()>0&&pvsNewFeedUserName.length()<MAX_LENGTH){
         this.cvsNewFeedUserName = pvsNewFeedUserName;
        }
        else{
           throw new RuntimeException("the length of User name should be between 1 to "+MAX_LENGTH);
        }
    }

    public String getNewFeedUserMail() {
        return cvsNewFeedUserMail;
    }
    public int getNewFeedUserId() {
        return cvsNewFeedUserId;
    }
    public void setNewFeedUserId(int pvsNewFeedUserId) {
        this.cvsNewFeedUserId = pvsNewFeedUserId;
    }

    public void setNewFeedUserMail(String pvsNewFeedUserMail) {
        if (pvsNewFeedUserMail.length() > 0 && pvsNewFeedUserMail.length() < MAX_LENGTH) {
            this.cvsNewFeedUserMail = pvsNewFeedUserMail;
        } else {
            throw new RuntimeException("the length of email id should be between 1 to "+MAX_LENGTH);
        }
    }

    public String getNewFeedUserPaswrd() {
        return cvsNewFeedUserPaswrd;
    }

    public void setNewFeedUserPaswrd(String pvsNewFeedUserPaswrd) {
        if (pvsNewFeedUserPaswrd.length() > 0 && pvsNewFeedUserPaswrd.length() < MAX_LENGTH) {
            this.cvsNewFeedUserPaswrd = pvsNewFeedUserPaswrd;
        } else {
            throw new RuntimeException("the length of password should be between 1 to "+MAX_LENGTH);
        }
    }
    public void validateUser(String pvsNewFeedUserPaswrd){
        if(cvsNewFeedUserId==-1){
            throw new RuntimeException("You are not a user of newfeed please check email id");
        }
        if(!pvsNewFeedUserPaswrd.equals(cvsNewFeedUserPaswrd)){
            throw new RuntimeException("Incorrect password");
        }
    }
    public static NewFeedUser load(String pvsNewFeedEmail,DbManager pvoDbManager) throws SQLException, FileNotFoundException{
        NewFeedUser svoNewFeedUser =null;
        if(pvsNewFeedEmail.isBlank()){
            return new NewFeedUser(pvoDbManager);
        }
        pvoDbManager.addContextParm("<rvsNewfeedUserEmail>",  pvsNewFeedEmail);
        ResultSet lvoRs = pvoDbManager.select("sarNewFeedUser");
        while (lvoRs.next()) {
            if (svoNewFeedUser == null) {
                svoNewFeedUser                      = new NewFeedUser(pvoDbManager);
                svoNewFeedUser.cviNewFeedActv       = lvoRs.getInt("sviNewfeedUserActv");
                svoNewFeedUser.cvsNewFeedUserId     = lvoRs.getInt("sviNewfeedUserId");
                svoNewFeedUser.cvsNewFeedUserName   = lvoRs.getString("svsNewfeedUserName");
                svoNewFeedUser.cvsNewFeedUserMail   = pvsNewFeedEmail;
                svoNewFeedUser.cvsNewFeedUserPaswrd = lvoRs.getString("svsNewfeedUserPaswrd");
            }
            NewFeedUser svoFollowNewFeedUser = new NewFeedUser(pvoDbManager);
            if (lvoRs.getInt("sviFollowNewfeedUserId")!=-1&&svoNewFeedUser.cvoFollowing.get(lvoRs.getInt("sviFollowNewfeedUserId")) == null) {
                svoFollowNewFeedUser.cviNewFeedActv       = lvoRs.getInt("sviFollowNewfeedUserActv");
                svoFollowNewFeedUser.cvsNewFeedUserId     = lvoRs.getInt("sviFollowNewfeedUserId");
                svoFollowNewFeedUser.cvsNewFeedUserName   = lvoRs.getString("svsFollowNewfeedUserName");
                svoFollowNewFeedUser.cvsNewFeedUserMail   = lvoRs.getString("svsFollowNewfeedUserEmail");
                svoFollowNewFeedUser.cvsNewFeedUserPaswrd = lvoRs.getString("svsFollowNewfeedUserPaswrd");
                svoNewFeedUser.cvoFollowing.put(svoFollowNewFeedUser.cvsNewFeedUserId, svoFollowNewFeedUser);
            }
        }
        return svoNewFeedUser;
    }
    public void save() throws FileNotFoundException, SQLException{
        cvoDbManager.addContextParm("<rvsNewfeedUserName>",   cvsNewFeedUserName);
        cvoDbManager.addContextParm("<rvsNewfeedUserEmail>",  cvsNewFeedUserMail);
        cvoDbManager.addContextParm("<rvsNewfeedUserPaswrd>", cvsNewFeedUserPaswrd);
        if(cvsNewFeedUserId!=-1){
             throw new RuntimeException("You are already signed up");
        }
        cvoDbManager.update("insert");
    }
   public void saveFollowinUesr(NewFeedUser pvsFollowingNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rviNewFeedFollowUserId>",   pvsFollowingNewFeedUser.getNewFeedUserId());
        cvoDbManager.addContextParm("<rviNewfeedUserId>",  cvsNewFeedUserId);
        if(cvoFollowing.get(pvsFollowingNewFeedUser.getNewFeedUserId())!=null){
             throw new RuntimeException("You are already follow this user");
        }
        cvoDbManager.update("i1rnewfeedfollow");
        cvoFollowing.put(pvsFollowingNewFeedUser.getNewFeedUserId(), pvsFollowingNewFeedUser);
   }
   public boolean isFollow(NewFeedUser pvsFollowingNewFeedUser){
       return cvoFollowing.get(pvsFollowingNewFeedUser.cvsNewFeedUserId)!=null;
   }
}
