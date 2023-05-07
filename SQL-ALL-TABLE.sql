-- Cmnt Table---
/*USE world;*/
CREATE TABLE `tabnewfeedcmnt` (
  `tcbNewFeedCmntActv` varchar(1) DEFAULT NULL,
  `tciNewFeedCmntId` int NOT NULL AUTO_INCREMENT,
  `tciNewFeedPostId` int DEFAULT NULL,
  `tciNewfeedUserId` int DEFAULT NULL,
  `tcsNewFeedCmntTxt` varchar(100) DEFAULT NULL,
  `tcdNewFeedCmntDate` datetime DEFAULT NULL,
  PRIMARY KEY (`tciNewFeedCmntId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--- USer Table-----
CREATE TABLE `tabnewfeeduser` (
  `tciNewfeedUserActv` varchar(1) DEFAULT '1',
  `tciNewfeedUserId` int NOT NULL AUTO_INCREMENT,
  `tcsNewfeedUserName` varchar(35) DEFAULT NULL,
  `tcsNewfeedUserEmail` varchar(35) DEFAULT NULL,
  `tcsNewfeedUserPaswrd` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`tciNewfeedUserId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*-Upvote--------*/
CREATE TABLE `tabnewfeedupvote` (
  `tcbNewfeedUpvoteActv` varchar(1) DEFAULT NULL,
  `tciNewfeedUpvoteId` int NOT NULL AUTO_INCREMENT,
  `tcsNewfeedUpvoteIdx` varchar(45) DEFAULT NULL,
  `tcsNewfeedUpvoteIdxId` int DEFAULT NULL,
  `tciNewfeedUserId` int DEFAULT NULL,
  PRIMARY KEY (`tciNewfeedUpvoteId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*--Rply----*/
CREATE TABLE `tabnewfeedrply` (
  `tcbNewFeedRplyActv` varchar(1) DEFAULT NULL,
  `tciNewFeedRplyId` int NOT NULL AUTO_INCREMENT,
  `tciNewFeedCmntId` int DEFAULT NULL,
  `tciNewfeedUserId` int DEFAULT NULL,
  `tcsNewFeedRplyTxt` varchar(100) DEFAULT NULL,
  `tcdNewFeedRplyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`tciNewFeedRplyId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*---post table----*/
CREATE TABLE `tabnewfeedpost` (
  `tcbNewFeedPostActv` varchar(1) DEFAULT NULL,
  `tciNewFeedPostId` int NOT NULL AUTO_INCREMENT,
  `tciNewfeedUserId` int DEFAULT NULL,
  `tcsNewFeedPostTxt` varchar(200) DEFAULT NULL,
  `tcsNewFeedPostDate` datetime DEFAULT NULL,
  PRIMARY KEY (`tciNewFeedPostId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*---follow--*/
CREATE TABLE `tabnewfeedfollow` (
  `tcbNewFeedFollowAtcv` varchar(1) DEFAULT NULL,
  `tciNewFeedFollowId` int NOT NULL AUTO_INCREMENT,
  `tciNewFeedFollowUserId` int DEFAULT NULL,
  `tciNewfeedUserId` int DEFAULT NULL,
  PRIMARY KEY (`tciNewFeedFollowId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*---DwnVote---*/
CREATE TABLE `tabnewfeeddwnvote` (
  `tcbNewfeedDwnVoteActv` varchar(1) DEFAULT NULL,
  `tciNewfeedDwnVoteId` int NOT NULL AUTO_INCREMENT,
  `tcsNewfeedDwnVoteIdx` varchar(45) DEFAULT NULL,
  `tcsNewfeedDwnVoteIdxId` int DEFAULT NULL,
  `tciNewfeedUserId` int DEFAULT NULL,
  PRIMARY KEY (`tciNewfeedDwnVoteId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


