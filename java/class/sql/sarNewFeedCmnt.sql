SELECT 
  IFNULL(tabnewfeedcmnt.tcbNewFeedCmntActv,0)            AS svbNewFeedCmntActv,
  IFNULL(tabnewfeedcmnt.tciNewFeedCmntId,-1)             AS sviNewFeedCmntId,
  IFNULL(tabnewfeedcmnt.tciNewfeedUserId,-1)             AS sviNewfeedUserId,
  IFNULL(tabnewfeedcmnt.tcsNewFeedCmntTxt,'')            AS svsNewFeedCmntTxt,
  tabnewfeedcmnt.tcdNewFeedCmntDate                      AS svdNewFeedCmntDate,

  IFNULL(tabnewfeedCmntuser.tciNewfeedUserActv,'0')      AS sviNewfeedCmntUserActv,
  IFNULL(tabnewfeedCmntuser.tciNewfeedUserId,-1)         AS sviNewfeedCmntUserId,
  IFNULL(tabnewfeedCmntuser.tcsNewfeedUserName,'')       AS svsNewfeedCmntUserName,
  IFNULL(tabnewfeedCmntuser.tcsNewfeedUserEmail,'')      AS svsNewfeedCmntUserEmail,
  IFNULL(tabnewfeedCmntuser.tcsNewfeedUserPaswrd,'')     AS svsNewfeedCmntUserPaswrd
  
  
FROM 
    tabnewfeedpost  
        LEFT JOIN tabnewfeedcmnt ON
        tabnewfeedcmnt.tciNewFeedPostId = tabnewfeedpost.tciNewFeedPostId   AND
        tabnewfeedcmnt.tcbNewFeedCmntActv = '1'
            LEFT JOIN tabnewfeeduser AS tabnewfeedCmntuser ON
            tabnewfeedCmntuser.tciNewfeedUserId = tabnewfeedcmnt.tciNewfeedUserId 
WHERE
  tabnewfeedpost.tciNewFeedPostId = <rviNewFeedPostId> AND
  tabnewfeedpost.tcbNewFeedPostActv = '1'
       
      