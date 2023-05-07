SELECT 
  IFNULL(tabnewfeeduserUpVote.tciNewfeedUserActv,'0')    AS sviNewfeedUserUpVoteActv,
  IFNULL(tabnewfeeduserUpVote.tciNewfeedUserId,-1)       AS sviNewfeedUserUpVoteId,
  IFNULL(tabnewfeeduserUpVote.tcsNewfeedUserName,'')     AS svsNewfeedUserUpVoteName,
  IFNULL(tabnewfeeduserUpVote.tcsNewfeedUserEmail,'')    AS svsNewfeedUserUpVoteEmail,
  IFNULL(tabnewfeeduserUpVote.tcsNewfeedUserPaswrd,'')   AS svsNewfeedUserUpVotePaswrd
FROM 
    tabnewfeedpost                        
        LEFT JOIN tabnewfeedupvote ON
        tabnewfeedpost.tciNewFeedPostId = tabnewfeedupvote.tcsNewfeedUpvoteIdxId           AND
        tabnewfeedupvote.tcsNewfeedUpvoteIdx = '<rvsPostIdx>'                              AND
        tabnewfeedupvote.tcbNewfeedUpvoteActv = '1'
            LEFT JOIN tabnewfeeduser AS tabnewfeeduserUpVote ON
            tabnewfeeduserUpVote.tciNewfeedUserId = tabnewfeedupvote.tciNewfeedUserId 
WHERE
  tabnewfeedpost.tciNewFeedPostId = <rviNewFeedPostId> AND
  tabnewfeedpost.tcbNewFeedPostActv = '1'
       
      