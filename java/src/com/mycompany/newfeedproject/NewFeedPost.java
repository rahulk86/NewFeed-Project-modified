
package com.mycompany.newfeedproject;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class NewFeedPost {
    static int NewFeedPostLength = 200;
    public static String             cvsPostIdx = "post";
    private int                      cvbNewFeedPostActv;
    private int                      cviNewFeedPostId;
    private NewFeedUser              cvoNewFeedUser;

    private String                   cvsNewFeedPostTxt;
    private LocalDateTime            cvsNewFeedPostDate;
    private Map<Integer,NewFeedUser> cvoUpVoteUser;
    private Map<Integer,NewFeedUser> cvoDwnVoteUser;
    private List<NewFeedCmnt> cvoNewFeedCmntLst;
    private int cviUpVoteCount;
    private int cviDwnVoteCount;
    private int cviCmntCount;
    private DbManager                cvoDbManager;

    public NewFeedPost(NewFeedUser pvoNewFeedUser, DbManager pvoDbManager) {
        cvoNewFeedUser = pvoNewFeedUser;
        cvoDbManager = pvoDbManager;
    }
    
    public NewFeedUser getNewFeedUser() {
        return cvoNewFeedUser;
    }
    public List<NewFeedCmnt> getAllComments() {
        return cvoNewFeedCmntLst;
    }

    public int getNewFeedPostActv() {
        return cvbNewFeedPostActv;
    }

    public void setNewFeedPostActv(int cvbNewFeedPostActv) {
        this.cvbNewFeedPostActv = cvbNewFeedPostActv;
    }

    public int getNewFeedPostId() {
        return cviNewFeedPostId;
    }

    public void setNewFeedPostId(int cviNewFeedPostId) {
        this.cviNewFeedPostId = cviNewFeedPostId;
    }

    public String getNewFeedPostTxt() {
        return cvsNewFeedPostTxt;
    }

    public void setNewFeedPostDate(LocalDateTime cvsNewFeedPostDate) {
        this.cvsNewFeedPostDate = cvsNewFeedPostDate;
    }

    public int getUpVoteCount() {
        return cviUpVoteCount;
    }

    public void setUpVoteCount(int pviUpVoteCount) {
        this.cviUpVoteCount = pviUpVoteCount;
    }

    public int getDwnVoteCount() {
        return cviDwnVoteCount;
    }

    public void setDwnVoteCount(int pviDwnVoteCount) {
        this.cviDwnVoteCount = pviDwnVoteCount;
    }

    public int getCmntCount() {
        return cviCmntCount;
    }

    public void setCmntCount(int pviCmntCount) {
        this.cviCmntCount = pviCmntCount;
    }
    public void  laod() throws SQLException, FileNotFoundException{
        cvoUpVoteUser  = new HashMap<>();
        cvoDwnVoteUser = new HashMap<>();
        cvoDbManager.addContextParm("<rviNewFeedPostId>", cviNewFeedPostId);
        cvoDbManager.addContextParm("<rvsPostIdx>", cvsPostIdx);
        ResultSet lvoRs = cvoDbManager.select("sarNewFeedUpVote");
        while (lvoRs.next()) {
            if (lvoRs.getInt("sviNewfeedUserUpVoteId") != -1 && cvoUpVoteUser.get(lvoRs.getInt("sviNewfeedUserUpVoteId")) == null) {
                NewFeedUser svoNewFeedUser = NewFeedUser.load("", cvoDbManager);
                svoNewFeedUser.setNewFeedActv(lvoRs.getInt("sviNewfeedUserUpVoteActv"));
                svoNewFeedUser.setNewFeedUserId(lvoRs.getInt("sviNewfeedUserUpVoteId"));
                svoNewFeedUser.setNewFeedUserName(lvoRs.getString("svsNewfeedUserUpVoteName"));
                svoNewFeedUser.setNewFeedUserMail(lvoRs.getString("svsNewfeedUserUpVoteEmail"));
                svoNewFeedUser.setNewFeedUserPaswrd(lvoRs.getString("svsNewfeedUserUpVotePaswrd"));
                cvoUpVoteUser.put(lvoRs.getInt("sviNewfeedUserUpVoteId"), svoNewFeedUser);
            }
        }
        lvoRs = cvoDbManager.select("sarNewFeeddwnVote");
        while (lvoRs.next()) {
            if (lvoRs.getInt("sviNewfeedUserDwnVoteId") != -1 && cvoDwnVoteUser.get(lvoRs.getInt("sviNewfeedUserDwnVoteId")) == null) {
                NewFeedUser svoNewFeedUser = NewFeedUser.load("", cvoDbManager);
                svoNewFeedUser.setNewFeedActv(lvoRs.getInt("sviNewfeedUserDwnVoteActv"));
                svoNewFeedUser.setNewFeedUserId(lvoRs.getInt("sviNewfeedUserDwnVoteId"));
                svoNewFeedUser.setNewFeedUserName(lvoRs.getString("svsNewfeedUserDwnVoteName"));
                svoNewFeedUser.setNewFeedUserMail(lvoRs.getString("svsNewfeedUserDwnVoteEmail"));
                svoNewFeedUser.setNewFeedUserPaswrd(lvoRs.getString("svsNewfeedUserDwnVotePaswrd"));
                cvoDwnVoteUser.put(lvoRs.getInt("sviNewfeedUserDwnVoteId"), svoNewFeedUser);
            }
        }
    
    }
    public void laodComment() throws SQLException, FileNotFoundException {
        cvoNewFeedCmntLst = new ArrayList<>();
        cvoDbManager.addContextParm("<rvsCmntIdx>", NewFeedCmnt.cvsCmntIdx);
        cvoDbManager.addContextParm("<rviNewFeedPostId>", cviNewFeedPostId);
        ResultSet lvoRs = cvoDbManager.select("sarNewFeedPostCmnts");
        while (lvoRs.next()) {
            NewFeedUser svoNewFeedUser = NewFeedUser.load("", cvoDbManager);
            svoNewFeedUser.setNewFeedActv(lvoRs.getInt("sviNewfeedCmntUserActv"));
            svoNewFeedUser.setNewFeedUserId(lvoRs.getInt("sviNewfeedCmntUserId"));
            svoNewFeedUser.setNewFeedUserName(lvoRs.getString("svsNewfeedCmntUserName"));
            svoNewFeedUser.setNewFeedUserMail(lvoRs.getString("svsNewfeedCmntUserEmail"));
            svoNewFeedUser.setNewFeedUserPaswrd(lvoRs.getString("svsNewfeedCmntUserPaswrd"));

            NewFeedCmnt svoNewFeedCmnt = new NewFeedCmnt(this, svoNewFeedUser, cvoDbManager);
            svoNewFeedCmnt.setNewFeedUser(svoNewFeedUser);
            svoNewFeedCmnt.setUpVoteCount(lvoRs.getInt("sviUpvoteCount"));
            svoNewFeedCmnt.setDwnVoteCount(lvoRs.getInt("sviDwnvoteCount"));
            svoNewFeedCmnt.setRplyCount(lvoRs.getInt("sviCmntCount"));
            svoNewFeedCmnt.setNewFeedCmntActv(lvoRs.getInt("svbNewFeedCmntActv"));
            svoNewFeedCmnt.setNewFeedCmntId(lvoRs.getInt("sviNewFeedCmntId"));
            svoNewFeedCmnt.setNewFeedCmntTxt(lvoRs.getString("svsNewFeedCmntTxt"));
            svoNewFeedCmnt.setNewFeedCmntDate(lvoRs.getTimestamp("svdNewFeedCmntDate").toLocalDateTime());
            cvoNewFeedCmntLst.add(svoNewFeedCmnt);
        }
    }
    public void setNewFeedPostTxt(String cvsNewFeedPostTxt) {
        if(0<cvsNewFeedPostTxt.length()&&cvsNewFeedPostTxt.length()<=NewFeedPostLength){
         this.cvsNewFeedPostTxt = cvsNewFeedPostTxt;
        }
        else{
            throw new RuntimeException("the length of newfeed text should be between 1 to "+NewFeedPostLength);
        }
    }

    public LocalDateTime getNewFeedPostDate() {
        return cvsNewFeedPostDate;
    }

    public void save() throws FileNotFoundException, SQLException{
        cvsNewFeedPostDate = LocalDateTime.now();
        cvoDbManager.addContextParm("<rviNewFeedId>",      cvoNewFeedUser.getNewFeedUserId());
        cvoDbManager.addContextParm("<rvsNewFeedPostTxt>", cvsNewFeedPostTxt);
        cvoDbManager.addContextParm("<rvsNewFeedPostDate>",cvsNewFeedPostDate);
        cvoDbManager.update("i1rNewfeedpost");
    }
    public void upVote(NewFeedUser pvoNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewfeedUpvoteActv>", "1");
        cvoDbManager.addContextParm("<rvsNewfeedUpvoteIdx>", cvsPostIdx);
        cvoDbManager.addContextParm("<rvsNewfeedUpvoteIdxId>", cviNewFeedPostId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>", pvoNewFeedUser.getNewFeedUserId());
        if(cvoUpVoteUser.get(pvoNewFeedUser.getNewFeedUserId())==null){
          dactvDwnVote(pvoNewFeedUser);
          if( cvoDbManager.update("DactvNewfeedupvote")==0){
           cvoDbManager.update("i1rNewfeedupvote");
          }
          cvoUpVoteUser.put(pvoNewFeedUser.getNewFeedUserId(), pvoNewFeedUser);
        }
        cviDwnVoteCount = cvoDwnVoteUser.size();
        cviUpVoteCount  = cvoUpVoteUser.size();
    }
    private void dactvUpVote(NewFeedUser pvoNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewfeedUpvoteActv>", "0");
        cvoDbManager.addContextParm("<rvsNewfeedUpvoteIdx>", cvsPostIdx);
        cvoDbManager.addContextParm("<rvsNewfeedUpvoteIdxId>", cviNewFeedPostId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>", pvoNewFeedUser.getNewFeedUserId());
        if(cvoUpVoteUser.get(pvoNewFeedUser.getNewFeedUserId())!=null){
          cvoDbManager.update("DactvNewfeedupvote");
          cvoUpVoteUser.remove(pvoNewFeedUser.getNewFeedUserId());
        }
    }
    public void dwnVote(NewFeedUser pvoNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewfeedDwnVoteActv>", "1");
        cvoDbManager.addContextParm("<rvsNewfeedDwnVoteIdx>", cvsPostIdx);
        cvoDbManager.addContextParm("<rvsNewfeedDwnVoteIdxId>", cviNewFeedPostId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>", pvoNewFeedUser.getNewFeedUserId());
        if(cvoDwnVoteUser.get(pvoNewFeedUser.getNewFeedUserId())==null){
          dactvUpVote(pvoNewFeedUser);
          if(cvoDbManager.update("DactvNewfeeddwnvote")==0){
          cvoDbManager.update("i1rNewfeeddwnvote");
          }
          cvoDwnVoteUser.put(pvoNewFeedUser.getNewFeedUserId(), pvoNewFeedUser);
        }
        cviDwnVoteCount = cvoDwnVoteUser.size();
        cviUpVoteCount = cvoUpVoteUser.size();
    }
    private void dactvDwnVote(NewFeedUser pvoNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewfeedDwnVoteActv>", "0");
        cvoDbManager.addContextParm("<rvsNewfeedDwnVoteIdx>", cvsPostIdx);
        cvoDbManager.addContextParm("<rvsNewfeedDwnVoteIdxId>", cviNewFeedPostId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>", pvoNewFeedUser.getNewFeedUserId());
        if(cvoDwnVoteUser.get(pvoNewFeedUser.getNewFeedUserId())!=null){
          cvoDbManager.update("DactvNewfeeddwnvote");
          cvoDwnVoteUser.remove(pvoNewFeedUser.getNewFeedUserId());
        }
    }
    public void comment(NewFeedUser pvoNewFeedUser, String pvsNewFeedCmntTxt) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewFeedCmntActv>","1");
        cvoDbManager.addContextParm("<rviNewFeedPostId>",  cviNewFeedPostId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>",  pvoNewFeedUser.getNewFeedUserId());
        cvoDbManager.addContextParm("<rvsNewFeedCmntTxt>", pvsNewFeedCmntTxt);
        cvoDbManager.addContextParm("<rvdNewFeedCmntDate>",LocalDateTime.now());
        cvoDbManager.update("i1rNewfeedCmnt");
        cviCmntCount ++;
    }
    
}
