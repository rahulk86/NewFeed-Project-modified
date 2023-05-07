SELECT
 tabnewfeeduser.tciNewfeedUserActv                    AS sviNewfeedUserActv,
 tabnewfeeduser.tciNewfeedUserId                      AS sviNewfeedUserId,
 tabnewfeeduser.tcsNewfeedUserName                    AS svsNewfeedUserName,
 tabnewfeeduser.tcsNewfeedUserPaswrd                  AS svsNewfeedUserPaswrd,
 
 IFNULL(tabnewfeeduserFollow.tciNewfeedUserActv,'0')  AS sviFollowNewfeedUserActv,
 IFNULL(tabnewfeeduserFollow.tciNewfeedUserId,-1)     AS sviFollowNewfeedUserId,
 IFNULL(tabnewfeeduserFollow.tcsNewfeedUserName,'')   AS svsFollowNewfeedUserName,
 IFNULL(tabnewfeeduserFollow.tcsNewfeedUserEmail,'')  AS svsFollowNewfeedUserEmail,
 IFNULL(tabnewfeeduserFollow.tcsNewfeedUserPaswrd,'') AS svsFollowNewfeedUserPaswrd
FROM
    tabnewfeeduser
        LEFT JOIN tabnewfeedfollow ON
        tabnewfeedfollow.tciNewfeedUserId = tabnewfeeduser.tciNewfeedUserId                 AND
        tabnewfeedfollow.tcbNewFeedFollowAtcv = '1' 
            LEFT JOIN tabnewfeeduser AS tabnewfeeduserFollow ON
            tabnewfeeduserFollow.tciNewfeedUserId = tabnewfeedfollow.tciNewFeedFollowUserId AND
            tabnewfeeduserFollow.tciNewfeedUserActv = '1'
 WHERE
  tabnewfeeduser.tcsNewfeedUserEmail = '<rvsNewfeedUserEmail>';