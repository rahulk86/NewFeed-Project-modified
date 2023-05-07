
package com.mycompany.newfeedproject;

import java.time.LocalDateTime;

public class NewFeedRply {
    private NewFeedCmnt      cvoNewFeedCmnt;
    private NewFeedUser      cvoNewFeedUser;
    private int              cvbNewFeedRplyActv;
    private int              cviNewFeedRplyId;
    private String           cvsNewFeedRplyTxt;
    private LocalDateTime    cvdNewFeedRplyDate;
    private DbManager cvoDbManager;
    public NewFeedRply(NewFeedCmnt pvoNewFeedCmnt, NewFeedUser pvoNewFeedUser, DbManager pvoDbManager) {
        cvoNewFeedCmnt = pvoNewFeedCmnt;
        cvoNewFeedUser = pvoNewFeedUser;
        cvoDbManager = pvoDbManager;
    }
    public int getNewFeedRplyActv() {
        return cvbNewFeedRplyActv;
    }

    public void setNewFeedRplyActv(int cvbNewFeedRplyActv) {
        this.cvbNewFeedRplyActv = cvbNewFeedRplyActv;
    }

    public int getNewFeedRplyId() {
        return cviNewFeedRplyId;
    }

    public void setNewFeedRplyId(int cviNewFeedRplyId) {
        this.cviNewFeedRplyId = cviNewFeedRplyId;
    }

   public NewFeedUser getNewFeedUser() {
        return cvoNewFeedUser;
    }

    public String getNewFeedRplyTxt(){
        return cvsNewFeedRplyTxt;
    }

    public void setNewFeedRplyTxt(String cvsNewFeedRplyTxt) {
        this.cvsNewFeedRplyTxt = cvsNewFeedRplyTxt;
    }

    public LocalDateTime getNewFeedRplyDate() {
        return cvdNewFeedRplyDate;
    }

    public void setNewFeedRplyDate(LocalDateTime cvdNewFeedRplyDate) {
        this.cvdNewFeedRplyDate = cvdNewFeedRplyDate;
    }
}
