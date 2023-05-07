SELECT 
  IFNULL(tabnewfeeduserUpVote.tciNewfeedUserActv,'0')    AS sviNewfeedUserUpVoteActv,
  IFNULL(tabnewfeeduserUpVote.tciNewfeedUserId,-1)       AS sviNewfeedUserUpVoteId,
  IFNULL(tabnewfeeduserUpVote.tcsNewfeedUserName,'')     AS svsNewfeedUserUpVoteName,
  IFNULL(tabnewfeeduserUpVote.tcsNewfeedUserEmail,'')    AS svsNewfeedUserUpVoteEmail,
  IFNULL(tabnewfeeduserUpVote.tcsNewfeedUserPaswrd,'')   AS svsNewfeedUserUpVotePaswrd
FROM 
    tabnewfeedcmnt                        
        LEFT JOIN tabnewfeedupvote ON
        tabnewfeedcmnt.tciNewFeedCmntId = tabnewfeedupvote.tcsNewfeedUpvoteIdxId           AND
        tabnewfeedupvote.tcsNewfeedUpvoteIdx = '<rvsCmntIdx>'                              AND
        tabnewfeedupvote.tcbNewfeedUpvoteActv = '1'
            LEFT JOIN tabnewfeeduser AS tabnewfeeduserUpVote ON
            tabnewfeeduserUpVote.tciNewfeedUserId = tabnewfeedupvote.tciNewfeedUserId 
WHERE
  tabnewfeedcmnt.tciNewFeedCmntId = <rviNewFeedCmntId> AND
  tabnewfeedcmnt.tcbNewFeedCmntActv = '1'
       
      