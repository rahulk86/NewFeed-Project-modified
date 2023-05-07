
package com.mycompany.newfeedproject;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class shownewsfeed {
    private NewFeedUser cvoNewFeedUser;
    private DbManager cvoDbManager;
    public shownewsfeed(NewFeedUser pvoNewFeedUser, DbManager pvoDbManager){
        cvoNewFeedUser = pvoNewFeedUser;
        cvoDbManager = pvoDbManager;
    }
    public List<NewFeedPost> getNewFeedPostLst() throws SQLException, FileNotFoundException{
        List<NewFeedPost> svoNewFeedPostLst = new ArrayList<>();
        cvoDbManager.addContextParm("<tciNewfeedUserId>", cvoNewFeedUser.getNewFeedUserId());
        cvoDbManager.addContextParm("<rvsPostIdx>", NewFeedPost.cvsPostIdx);
        ResultSet lvoRs = cvoDbManager.select("sarShowNewFeed");
        while (lvoRs.next()) {
            NewFeedUser svoNewFeedUser = NewFeedUser.load("", cvoDbManager);
            svoNewFeedUser.setNewFeedActv(lvoRs.getInt("sviNewfeedUserActv"));
            svoNewFeedUser.setNewFeedUserId(lvoRs.getInt("sviNewfeedUserId"));
            svoNewFeedUser.setNewFeedUserName(lvoRs.getString("svsNewfeedUserName"));
            svoNewFeedUser.setNewFeedUserMail(lvoRs.getString("svsNewfeedUserEmail"));
            svoNewFeedUser.setNewFeedUserPaswrd(lvoRs.getString("svsNewfeedUserPaswrd"));

            NewFeedPost svoNewFeedPost = new NewFeedPost(svoNewFeedUser, cvoDbManager);
            svoNewFeedPost.setUpVoteCount(lvoRs.getInt("sviUpvoteCount"));
            svoNewFeedPost.setDwnVoteCount(lvoRs.getInt("sviDwnvoteCount"));
            svoNewFeedPost.setCmntCount(lvoRs.getInt("sviCmntCount"));
            
            svoNewFeedPost.setNewFeedPostActv(lvoRs.getInt("svbNewFeedPostActv"));
            svoNewFeedPost.setNewFeedPostId(lvoRs.getInt("sviNewFeedPostId"));
            svoNewFeedPost.setNewFeedPostTxt(lvoRs.getString("svsNewFeedPostTxt"));
            svoNewFeedPost.setNewFeedPostDate(lvoRs.getTimestamp("svsNewFeedPostDate").toLocalDateTime());
            svoNewFeedPostLst.add(svoNewFeedPost);

        }
        return svoNewFeedPostLst;
    }
}
