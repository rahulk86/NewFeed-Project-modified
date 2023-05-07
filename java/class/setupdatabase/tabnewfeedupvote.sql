CREATE TABLE tabnewfeedupvote (
  tcbNewfeedUpvoteActv varchar(1) DEFAULT NULL,
  tciNewfeedUpvoteId int NOT NULL AUTO_INCREMENT,
  tcsNewfeedUpvoteIdx varchar(45) DEFAULT NULL,
  tcsNewfeedUpvoteIdxId int DEFAULT NULL,
  tciNewfeedUserId int DEFAULT NULL,
  PRIMARY KEY (tciNewfeedUpvoteId)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
