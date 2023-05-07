CREATE TABLE tabnewfeedcmnt (
  tcbNewFeedCmntActv varchar(1) DEFAULT NULL,
  tciNewFeedCmntId int NOT NULL AUTO_INCREMENT,
  tciNewFeedPostId int DEFAULT NULL,
  tciNewfeedUserId int DEFAULT NULL,
  tcsNewFeedCmntTxt varchar(100) DEFAULT NULL,
  tcdNewFeedCmntDate datetime DEFAULT NULL,
  PRIMARY KEY (tciNewFeedCmntId)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
