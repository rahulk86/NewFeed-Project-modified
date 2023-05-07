CREATE TABLE tabnewfeedpost (
  tcbNewFeedPostActv varchar(1) DEFAULT NULL,
  tciNewFeedPostId int NOT NULL AUTO_INCREMENT,
  tciNewfeedUserId int DEFAULT NULL,
  tcsNewFeedPostTxt varchar(200) DEFAULT NULL,
  tcsNewFeedPostDate datetime DEFAULT NULL,
  PRIMARY KEY (tciNewFeedPostId)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
