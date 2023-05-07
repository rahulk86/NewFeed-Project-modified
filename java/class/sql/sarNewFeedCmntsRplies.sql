SELECT 
  tabnewfeedrply.tcbNewFeedRplyActv                      AS svbNewFeedRplyActv,
  tabnewfeedrply.tcbNewFeedRplyActv                      AS sviNewFeedRplyId,
  tabnewfeedrply.tcsNewFeedRplyTxt                       AS svsNewFeedRplyTxt,
  tabnewfeedrply.tcdNewFeedRplyDate                      AS svdNewFeedRplyDate,
           
  tabnewfeedRplyuser.tciNewfeedUserActv                  AS sviNewfeedRplyUserActv,
  tabnewfeedRplyuser.tciNewfeedUserId                    AS sviNewfeedRplyUserId,
  tabnewfeedRplyuser.tcsNewfeedUserName                  AS svsNewfeedRplyUserName,
  tabnewfeedRplyuser.tcsNewfeedUserEmail                 AS svsNewfeedRplyUserEmail,
tabnewfeedRplyuser.tcsNewfeedUserPaswrd                  AS svsNewfeedRplyUserPaswrd
  
  
FROM 
    tabnewfeedrply  
        INNER JOIN tabnewfeeduser AS tabnewfeedRplyuser ON
        tabnewfeedRplyuser.tciNewfeedUserId = tabnewfeedrply.tciNewfeedUserId 
WHERE
  tabnewfeedrply.tciNewFeedCmntId = <rviNewFeedCmntId>  AND
  tabnewfeedrply.tcbNewFeedRplyActv = '1' 
ORDER BY
  svdNewFeedRplyDate DESC;
      