CREATE TABLE tabnewfeedfollow (
  tcbNewFeedFollowAtcv varchar(1) DEFAULT NULL,
  tciNewFeedFollowId int NOT NULL AUTO_INCREMENT,
  tciNewFeedFollowUserId int DEFAULT NULL,
  tciNewfeedUserId int DEFAULT NULL,
  PRIMARY KEY (tciNewFeedFollowId)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
