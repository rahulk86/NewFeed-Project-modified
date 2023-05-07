CREATE TABLE tabnewfeeddwnvote (
  tcbNewfeedDwnVoteActv varchar(1) DEFAULT NULL,
  tciNewfeedDwnVoteId int NOT NULL AUTO_INCREMENT,
  tcsNewfeedDwnVoteIdx varchar(45) DEFAULT NULL,
  tcsNewfeedDwnVoteIdxId int DEFAULT NULL,
  tciNewfeedUserId int DEFAULT NULL,
  PRIMARY KEY (tciNewfeedDwnVoteId)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

