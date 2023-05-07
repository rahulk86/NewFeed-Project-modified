SELECT 
  MAX(IFNULL(tabnewfeedfollow.tcbNewFeedFollowAtcv,'0'))                               AS svbNewFeedFollowAtcv,
  COUNT(DISTINCT tabnewfeedupvote.tciNewfeedUserId) - 
                               COUNT(DISTINCT tabnewfeeddwnvote.tciNewfeedUserId)      AS sviHgrUpdwn,
  COUNT(DISTINCT tabnewfeedupvote.tciNewfeedUserId)                                    AS sviUpvoteCount,
  COUNT(DISTINCT tabnewfeeddwnvote.tciNewfeedUserId)                                   AS sviDwnvoteCount,
  COUNT(DISTINCT tabnewfeedcmnt.tciNewFeedCmntId)                                      AS sviCmntCount,
  MAX(tabnewfeedpost.tcbNewFeedPostActv)                                               AS svbNewFeedPostActv,
  MAX(tabnewfeedpost.tciNewFeedPostId)                                                 AS sviNewFeedPostId,
  MAX(tabnewfeedpost.tcsNewFeedPostTxt)                                                AS svsNewFeedPostTxt,
  MAX(tabnewfeedpost.tcsNewFeedPostDate)                                               AS svsNewFeedPostDate,
  
  MAX(tabnewfeeduser.tciNewfeedUserActv)                                               AS sviNewfeedUserActv,
  MAX(tabnewfeeduser.tciNewfeedUserId)                                                 AS sviNewfeedUserId,
  MAX(tabnewfeeduser.tcsNewfeedUserName)                                               AS svsNewfeedUserName,
  MAX(tabnewfeeduser.tcsNewfeedUserEmail)                                              AS svsNewfeedUserEmail,
  MAX(tabnewfeeduser.tcsNewfeedUserPaswrd)                                             AS svsNewfeedUserPaswrd
 
FROM 
 tabnewfeedpost
   INNER JOIN tabnewfeeduser ON
   tabnewfeeduser.tciNewfeedUserId = tabnewfeedpost.tciNewfeedUserId              AND
   tabnewfeeduser.tciNewfeedUserActv = '1'                                        AND
   tabnewfeedpost.tcbNewFeedPostActv = '1' 
    LEFT JOIN tabnewfeedfollow ON
    tabnewfeedfollow.tcbNewFeedFollowAtcv = '1'                                   AND
    tabnewfeedfollow.tciNewFeedFollowUserId = tabnewfeedpost.tciNewfeedUserId     AND
    tabnewfeedfollow.tciNewfeedUserId = <tciNewfeedUserId>
     LEFT JOIN tabnewfeedupvote ON
     tabnewfeedpost.tciNewFeedPostId = tabnewfeedupvote.tcsNewfeedUpvoteIdxId     AND
     tabnewfeedupvote.tcsNewfeedUpvoteIdx = '<rvsPostIdx>'                        AND
     tabnewfeedupvote.tcbNewfeedUpvoteActv = '1'
       LEFT JOIN tabnewfeeddwnvote ON
       tabnewfeedpost.tciNewFeedPostId = tabnewfeeddwnvote.tcsNewfeedDwnVoteIdxId AND
       tabnewfeeddwnvote.tcsNewfeedDwnVoteIdx = '<rvsPostIdx>'                    AND
       tabnewfeeddwnvote.tcbNewfeedDwnVoteActv = '1'
            LEFT JOIN tabnewfeedcmnt ON
            tabnewfeedcmnt.tciNewFeedPostId = tabnewfeedpost.tciNewFeedPostId 
WHERE
  tabnewfeedpost.tcbNewFeedPostActv = '1'
GROUP BY 
     tabnewfeedpost.tciNewFeedPostId  
ORDER BY 
 svbNewFeedFollowAtcv DESC,
 sviHgrUpdwn DESC,
 svsNewFeedPostDate DESC;
      