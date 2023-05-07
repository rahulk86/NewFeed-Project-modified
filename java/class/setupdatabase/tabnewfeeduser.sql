CREATE TABLE tabnewfeeduser (
  tciNewfeedUserActv varchar(1) DEFAULT '1',
  tciNewfeedUserId int NOT NULL AUTO_INCREMENT,
  tcsNewfeedUserName varchar(35) DEFAULT NULL,
  tcsNewfeedUserEmail varchar(35) DEFAULT NULL,
  tcsNewfeedUserPaswrd varchar(35) DEFAULT NULL,
  PRIMARY KEY (tciNewfeedUserId)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
