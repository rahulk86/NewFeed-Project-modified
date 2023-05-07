CREATE TABLE tabnewfeedrply (
  tcbNewFeedRplyActv varchar(1) DEFAULT NULL,
  tciNewFeedRplyId int NOT NULL AUTO_INCREMENT,
  tciNewFeedCmntId int DEFAULT NULL,
  tciNewfeedUserId int DEFAULT NULL,
  tcsNewFeedRplyTxt varchar(100) DEFAULT NULL,
  tcdNewFeedRplyDate datetime DEFAULT NULL,
  PRIMARY KEY (`tciNewFeedRplyId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
