SELECT 
  IFNULL(tabnewfeeduserDwnVote.tciNewfeedUserActv,'0')   AS sviNewfeedUserDwnVoteActv,
  IFNULL(tabnewfeeduserDwnVote.tciNewfeedUserId,-1)      AS sviNewfeedUserDwnVoteId,
  IFNULL(tabnewfeeduserDwnVote.tcsNewfeedUserName,'')    AS svsNewfeedUserDwnVoteName,
  IFNULL(tabnewfeeduserDwnVote.tcsNewfeedUserEmail,'')   AS svsNewfeedUserDwnVoteEmail,
  IFNULL(tabnewfeeduserDwnVote.tcsNewfeedUserPaswrd,'')  AS svsNewfeedUserDwnVotePaswrd
FROM 
    tabnewfeedcmnt  
        LEFT JOIN tabnewfeeddwnvote ON
        tabnewfeedcmnt.tciNewFeedCmntId = tabnewfeeddwnvote.tcsNewfeedDwnVoteIdxId AND
        tabnewfeeddwnvote.tcsNewfeedDwnVoteIdx = '<rvsCmntIdx>'                    AND
        tabnewfeeddwnvote.tcbNewfeedDwnVoteActv = '1'
            LEFT JOIN tabnewfeeduser AS tabnewfeeduserDwnVote ON
            tabnewfeeduserDwnVote.tciNewfeedUserId = tabnewfeeddwnvote.tciNewfeedUserId 
WHERE
  tabnewfeedcmnt.tciNewFeedCmntId = <rviNewFeedCmntId> AND
  tabnewfeedcmnt.tcbNewFeedCmntActv = '1'
       
      