SELECT 
  COUNT(DISTINCT tabnewfeedupvote.tciNewfeedUserId)           AS sviUpvoteCount,
  COUNT(DISTINCT tabnewfeeddwnvote.tciNewfeedUserId)          AS sviDwnvoteCount,
  COUNT(DISTINCT tabnewfeedrply.tciNewFeedRplyId)             AS sviCmntCount,

  MAX(tabnewfeedcmnt.tcbNewFeedCmntActv)                      AS svbNewFeedCmntActv,
  MAX(tabnewfeedcmnt.tciNewFeedCmntId)                        AS sviNewFeedCmntId,
  MAX(tabnewfeedcmnt.tcsNewFeedCmntTxt)                       AS svsNewFeedCmntTxt,
  MAX(tabnewfeedcmnt.tcdNewFeedCmntDate)                      AS svdNewFeedCmntDate,
           
  MAX(tabnewfeedCmntuser.tciNewfeedUserActv)                  AS sviNewfeedCmntUserActv,
  MAX(tabnewfeedCmntuser.tciNewfeedUserId)                    AS sviNewfeedCmntUserId,
  MAX(tabnewfeedCmntuser.tcsNewfeedUserName)                  AS svsNewfeedCmntUserName,
  MAX(tabnewfeedCmntuser.tcsNewfeedUserEmail)                 AS svsNewfeedCmntUserEmail,
  MAX(tabnewfeedCmntuser.tcsNewfeedUserPaswrd)                AS svsNewfeedCmntUserPaswrd
  
  
FROM 
    tabnewfeedcmnt  
        INNER JOIN tabnewfeeduser AS tabnewfeedCmntuser ON
        tabnewfeedCmntuser.tciNewfeedUserId = tabnewfeedcmnt.tciNewfeedUserId 
            LEFT JOIN tabnewfeedupvote ON
            tabnewfeedcmnt.tciNewFeedCmntId = tabnewfeedupvote.tcsNewfeedUpvoteIdxId       AND
            tabnewfeedupvote.tcsNewfeedUpvoteIdx = '<rvsCmntIdx>'                                  AND
            tabnewfeedupvote.tcbNewfeedUpvoteActv = '1'
                LEFT JOIN tabnewfeeddwnvote ON
                tabnewfeedcmnt.tciNewFeedCmntId = tabnewfeeddwnvote.tcsNewfeedDwnVoteIdxId AND
                tabnewfeeddwnvote.tcsNewfeedDwnVoteIdx = '<rvsCmntIdx>'                            AND
                tabnewfeeddwnvote.tcbNewfeedDwnVoteActv = '1'
                    LEFT JOIN tabnewfeedrply  ON
                    tabnewfeedrply.tciNewFeedCmntId = tabnewfeedcmnt.tciNewFeedCmntId      AND
                    tabnewfeedrply.tcbNewFeedRplyActv = '1'
WHERE
  tabnewfeedcmnt.tciNewFeedPostId = <rviNewFeedPostId> AND
  tabnewfeedcmnt.tcbNewFeedCmntActv = '1' 
GROUP BY 
   tabnewfeedcmnt.tciNewFeedCmntId  
ORDER BY
 svdNewFeedCmntDate DESC;
      