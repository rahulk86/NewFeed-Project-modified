package com.mycompany.newfeedproject;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewFeedCmnt {

    public static String cvsCmntIdx = "cmnt";
    private NewFeedPost cvoNewFeedPost;
    private int cvbNewFeedCmntActv;
    private NewFeedUser cvoNewFeedUser;
    private int cviNewFeedCmntId;
    private String cvsNewFeedCmntTxt;
    private LocalDateTime cvdNewFeedCmntDate;
    private int cviUpVoteCount;
    private int cviDwnVoteCount;
    private int cviRplyCount;

    private Map<Integer, NewFeedUser> cvoUpVoteUser;
    private Map<Integer, NewFeedUser> cvoDwnVoteUser;
    private List<NewFeedRply> cvoNewFeedRplyLst;

    public int getNewFeedCmntActv() {
        return cvbNewFeedCmntActv;
    }

    public void setNewFeedCmntActv(int cvbNewFeedCmntActv) {
        this.cvbNewFeedCmntActv = cvbNewFeedCmntActv;
    }

    public int getUpVoteCount() {
        return cviUpVoteCount;
    }
    public List<NewFeedRply> getAllReplies() {
        return cvoNewFeedRplyLst;
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

    public int getRplyCount() {
        return cviRplyCount;
    }

    public void setRplyCount(int pviRplyCount) {
        this.cviRplyCount = pviRplyCount;
    }

    public int getNewFeedCmntId() {
        return cviNewFeedCmntId;
    }

    public void setNewFeedCmntId(int cviNewFeedCmntId) {
        this.cviNewFeedCmntId = cviNewFeedCmntId;
    }

    public String getNewFeedCmntTxt() {
        return cvsNewFeedCmntTxt;
    }

    public void setNewFeedCmntTxt(String cvsNewFeedCmntTxt) {
        this.cvsNewFeedCmntTxt = cvsNewFeedCmntTxt;
    }

    public LocalDateTime getNewFeedCmntDate() {
        return cvdNewFeedCmntDate;
    }

    public void setNewFeedCmntDate(LocalDateTime cvdNewFeedCmntDate) {
        this.cvdNewFeedCmntDate = cvdNewFeedCmntDate;
    }

    public NewFeedUser getNewFeedUser() {
        return cvoNewFeedUser;
    }

    public void setNewFeedUser(NewFeedUser cvoNewFeedUser) {
        this.cvoNewFeedUser = cvoNewFeedUser;
    }
    private DbManager cvoDbManager;

    public NewFeedCmnt(NewFeedPost pvoNewFeedPost, NewFeedUser pvoNewFeedUser, DbManager pvoDbManager) {
        cvoNewFeedPost = pvoNewFeedPost;
        cvoNewFeedUser = pvoNewFeedUser;
        cvoDbManager = pvoDbManager;
    }

    public void laod() throws SQLException, FileNotFoundException {
        cvoUpVoteUser = new HashMap<>();
        cvoDwnVoteUser = new HashMap<>();
        cvoDbManager.addContextParm("<rviNewFeedCmntId>", cviNewFeedCmntId);
        cvoDbManager.addContextParm("<rvsCmntIdx>", cvsCmntIdx);
        ResultSet lvoRs = cvoDbManager.select("sarNewFeedCmntUpVote");
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
        lvoRs = cvoDbManager.select("sarNewFeedCmntDwnVote");
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

        public void loadRplies() throws SQLException, FileNotFoundException {
        cvoNewFeedRplyLst = new ArrayList<>();
        cvoDbManager.addContextParm("<rvsCmntIdx>", cvsCmntIdx);
        cvoDbManager.addContextParm("<rviNewFeedCmntId>", cviNewFeedCmntId);
        ResultSet lvoRs = cvoDbManager.select("sarNewFeedCmntsRplies");
        while (lvoRs.next()) {
            NewFeedUser svoNewFeedUser = NewFeedUser.load("", cvoDbManager);
            svoNewFeedUser.setNewFeedActv(lvoRs.getInt("sviNewfeedRplyUserActv"));
            svoNewFeedUser.setNewFeedUserId(lvoRs.getInt("sviNewfeedRplyUserId"));
            if(svoNewFeedUser.getNewFeedActv()==0||lvoRs.getString("svsNewfeedRplyUserName")==null){
                continue;
            }
            svoNewFeedUser.setNewFeedUserName(lvoRs.getString("svsNewfeedRplyUserName"));
            svoNewFeedUser.setNewFeedUserMail(lvoRs.getString("svsNewfeedRplyUserEmail"));
            svoNewFeedUser.setNewFeedUserPaswrd(lvoRs.getString("svsNewfeedRplyUserPaswrd"));

            NewFeedRply svoNewFeedRply = new NewFeedRply(this, svoNewFeedUser, cvoDbManager);
            svoNewFeedRply.setNewFeedRplyActv(lvoRs.getInt("svbNewFeedRplyActv"));
            svoNewFeedRply.setNewFeedRplyId(lvoRs.getInt("sviNewFeedRplyId"));
            svoNewFeedRply.setNewFeedRplyTxt(lvoRs.getString("svsNewFeedRplyTxt"));
            svoNewFeedRply.setNewFeedRplyDate(lvoRs.getTimestamp("svdNewFeedRplyDate").toLocalDateTime());
            cvoNewFeedRplyLst.add(svoNewFeedRply);
        }
    }
    
    public void upVote(NewFeedUser pvoNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewfeedUpvoteActv>", "1");
        cvoDbManager.addContextParm("<rvsNewfeedUpvoteIdx>", cvsCmntIdx);
        cvoDbManager.addContextParm("<rvsNewfeedUpvoteIdxId>", cviNewFeedCmntId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>", pvoNewFeedUser.getNewFeedUserId());
        if (cvoUpVoteUser.get(pvoNewFeedUser.getNewFeedUserId()) == null) {
            dactvDwnVote(pvoNewFeedUser);
            if (cvoDbManager.update("DactvNewfeedupvote") == 0) {
                cvoDbManager.update("i1rNewfeedupvote");
            }
            cvoUpVoteUser.put(pvoNewFeedUser.getNewFeedUserId(), pvoNewFeedUser);
        }
        cviDwnVoteCount = cvoDwnVoteUser.size();
        cviUpVoteCount = cvoUpVoteUser.size();
    }

    private void dactvUpVote(NewFeedUser pvoNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewfeedUpvoteActv>", "0");
        cvoDbManager.addContextParm("<rvsNewfeedUpvoteIdx>", cvsCmntIdx);
        cvoDbManager.addContextParm("<rvsNewfeedUpvoteIdxId>", cviNewFeedCmntId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>", pvoNewFeedUser.getNewFeedUserId());
        if (cvoUpVoteUser.get(pvoNewFeedUser.getNewFeedUserId()) != null) {
            cvoDbManager.update("DactvNewfeedupvote");
            cvoUpVoteUser.remove(pvoNewFeedUser.getNewFeedUserId());
        }
    }

    public void dwnVote(NewFeedUser pvoNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewfeedDwnVoteActv>", "1");
        cvoDbManager.addContextParm("<rvsNewfeedDwnVoteIdx>", cvsCmntIdx);
        cvoDbManager.addContextParm("<rvsNewfeedDwnVoteIdxId>", cviNewFeedCmntId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>", pvoNewFeedUser.getNewFeedUserId());
        if (cvoDwnVoteUser.get(pvoNewFeedUser.getNewFeedUserId()) == null) {
            dactvUpVote(pvoNewFeedUser);
            if (cvoDbManager.update("DactvNewfeeddwnvote") == 0) {
                cvoDbManager.update("i1rNewfeeddwnvote");
            }
            cvoDwnVoteUser.put(pvoNewFeedUser.getNewFeedUserId(), pvoNewFeedUser);
        }
        cviDwnVoteCount = cvoDwnVoteUser.size();
        cviUpVoteCount = cvoUpVoteUser.size();
    }

    private void dactvDwnVote(NewFeedUser pvoNewFeedUser) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewfeedDwnVoteActv>", "0");
        cvoDbManager.addContextParm("<rvsNewfeedDwnVoteIdx>", cvsCmntIdx);
        cvoDbManager.addContextParm("<rvsNewfeedDwnVoteIdxId>", cviNewFeedCmntId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>", pvoNewFeedUser.getNewFeedUserId());
        if (cvoDwnVoteUser.get(pvoNewFeedUser.getNewFeedUserId()) != null) {
            cvoDbManager.update("DactvNewfeeddwnvote");
            cvoDwnVoteUser.remove(pvoNewFeedUser.getNewFeedUserId());
        }
    }
    
    public void reply(NewFeedUser pvoNewFeedUser, String pvsNewFeedCmntTxt) throws FileNotFoundException, SQLException {
        cvoDbManager.addContextParm("<rvbNewFeedRplyActv>","1");
        cvoDbManager.addContextParm("<rviNewFeedCmntId>",  cviNewFeedCmntId);
        cvoDbManager.addContextParm("<rviNewfeedUserId>",  pvoNewFeedUser.getNewFeedUserId());
        cvoDbManager.addContextParm("<rvsNewFeedRplyTxt>", pvsNewFeedCmntTxt);
        cvoDbManager.addContextParm("<rvdNewFeedRplyDate>",LocalDateTime.now());
        cvoDbManager.update("i1rNewfeedRply");
        cviRplyCount ++;
    }

}
