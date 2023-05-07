SELECT 
  IFNULL(tabnewfeeduserDwnVote.tciNewfeedUserActv,'0')   AS sviNewfeedUserDwnVoteActv,
  IFNULL(tabnewfeeduserDwnVote.tciNewfeedUserId,-1)      AS sviNewfeedUserDwnVoteId,
  IFNULL(tabnewfeeduserDwnVote.tcsNewfeedUserName,'')    AS svsNewfeedUserDwnVoteName,
  IFNULL(tabnewfeeduserDwnVote.tcsNewfeedUserEmail,'')   AS svsNewfeedUserDwnVoteEmail,
  IFNULL(tabnewfeeduserDwnVote.tcsNewfeedUserPaswrd,'')  AS svsNewfeedUserDwnVotePaswrd
FROM 
    tabnewfeedpost  
        LEFT JOIN tabnewfeeddwnvote ON
        tabnewfeedpost.tciNewFeedPostId = tabnewfeeddwnvote.tcsNewfeedDwnVoteIdxId AND
        tabnewfeeddwnvote.tcsNewfeedDwnVoteIdx = '<rvsPostIdx>'                    AND
        tabnewfeeddwnvote.tcbNewfeedDwnVoteActv = '1'
            LEFT JOIN tabnewfeeduser AS tabnewfeeduserDwnVote ON
            tabnewfeeduserDwnVote.tciNewfeedUserId = tabnewfeeddwnvote.tciNewfeedUserId 
WHERE
  tabnewfeedpost.tciNewFeedPostId = <rviNewFeedPostId> AND
  tabnewfeedpost.tcbNewFeedPostActv = '1'
       
      