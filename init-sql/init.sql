/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : flipped_class

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 31/12/2018 15:18:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `klass_seminar_id` bigint(20) UNSIGNED NOT NULL COMMENT '讨论课（某班级）id',
  `team_id` bigint(20) UNSIGNED NOT NULL COMMENT '队伍id',
  `team_order` tinyint(4) UNSIGNED NOT NULL COMMENT '该队伍顺序',
  `is_present` tinyint(4) UNSIGNED NOT NULL COMMENT '是否正在进行，0不是，1是',
  `report_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交的报告文件名',
  `report_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交的报告文件位置',
  `ppt_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交的PPT文件名',
  `ppt_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提交的PPT文件位置',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_team_id`(`team_id`) USING BTREE,
  INDEX `idx_klass_seminar_id`(`klass_seminar_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 149 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conflict_course_strategy
-- ----------------------------
DROP TABLE IF EXISTS `conflict_course_strategy`;
CREATE TABLE `conflict_course_strategy`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) UNSIGNED NOT NULL COMMENT '冲突课程',
  PRIMARY KEY (`id`, `course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `teacher_id` bigint(20) UNSIGNED NOT NULL COMMENT '教师id',
  `course_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `introduction` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程介绍',
  `presentation_percentage` tinyint(4) UNSIGNED NOT NULL COMMENT '展示分数占比',
  `question_percentage` tinyint(4) UNSIGNED NOT NULL COMMENT '提问分数占比',
  `report_percentage` tinyint(4) UNSIGNED NOT NULL COMMENT '报告分数占比',
  `team_start_time` datetime(0) NOT NULL COMMENT '开始组队时间',
  `team_end_time` datetime(0) NOT NULL COMMENT '截止组队时间',
  `team_main_course_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '共享分组，主课程id',
  `seminar_main_course_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '共享讨论课，主课程id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_member_limit_strategy
-- ----------------------------
DROP TABLE IF EXISTS `course_member_limit_strategy`;
CREATE TABLE `course_member_limit_strategy`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) UNSIGNED NOT NULL COMMENT '课程id',
  `min_member` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '队伍中选该课程最少人数',
  `max_member` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '队伍中选该课程最多人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for klass
-- ----------------------------
DROP TABLE IF EXISTS `klass`;
CREATE TABLE `klass`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) UNSIGNED NOT NULL COMMENT '课程id',
  `grade` int(10) UNSIGNED NOT NULL COMMENT '年级',
  `klass_serial` tinyint(4) UNSIGNED NOT NULL COMMENT '班级序号',
  `klass_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上课时间',
  `klass_location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上课地点',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for klass_round
-- ----------------------------
DROP TABLE IF EXISTS `klass_round`;
CREATE TABLE `klass_round`  (
  `klass_id` bigint(20) UNSIGNED NOT NULL COMMENT '课程id',
  `round_id` bigint(20) UNSIGNED NOT NULL COMMENT '轮次id',
  `enroll_number` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '某班级，某轮次队伍报名次数限制',
  PRIMARY KEY (`klass_id`, `round_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for klass_seminar
-- ----------------------------
DROP TABLE IF EXISTS `klass_seminar`;
CREATE TABLE `klass_seminar`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `klass_id` bigint(20) UNSIGNED NOT NULL COMMENT '班级id',
  `seminar_id` bigint(20) UNSIGNED NOT NULL COMMENT '讨论课id\n',
  `report_ddl` datetime(0) NULL DEFAULT NULL COMMENT '报告截止时间',
  `status` tinyint(4) NOT NULL COMMENT '讨论课所处状态，未开始0，正在进行1，已结束2，暂停3',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_klass_id`(`klass_id`) USING BTREE,
  INDEX `idx_seminar_id`(`seminar_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for klass_student
-- ----------------------------
DROP TABLE IF EXISTS `klass_student`;
CREATE TABLE `klass_student`  (
  `klass_id` bigint(20) UNSIGNED NOT NULL COMMENT '班级id',
  `student_id` bigint(20) UNSIGNED NOT NULL COMMENT '学生id',
  `course_id` bigint(20) UNSIGNED NOT NULL COMMENT '课程id',
  `team_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '队伍id',
  PRIMARY KEY (`klass_id`, `student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for klass_team
-- ----------------------------
DROP TABLE IF EXISTS `klass_team`;
CREATE TABLE `klass_team`  (
  `klass_id` bigint(20) UNSIGNED NOT NULL COMMENT '班级id',
  `team_id` bigint(20) UNSIGNED NOT NULL COMMENT '队伍id',
  PRIMARY KEY (`klass_id`, `team_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for member_limit_strategy
-- ----------------------------
DROP TABLE IF EXISTS `member_limit_strategy`;
CREATE TABLE `member_limit_strategy`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `min_member` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '最少人数',
  `max_member` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '最多人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `klass_seminar_id` bigint(20) UNSIGNED NOT NULL,
  `attendance_id` bigint(20) UNSIGNED NOT NULL COMMENT '问题所针对的发言id',
  `team_id` bigint(20) UNSIGNED NOT NULL COMMENT '提问小组的id',
  `student_id` bigint(20) UNSIGNED NOT NULL COMMENT '提问学生的id',
  `is_selected` tinyint(4) UNSIGNED NOT NULL COMMENT '是否被选中',
  `score` decimal(4, 1) NULL DEFAULT NULL COMMENT '提问分数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_team_id`(`team_id`) USING BTREE,
  INDEX `idx_klass_seminar_id`(`klass_seminar_id`) USING BTREE,
  INDEX `idx_attendance_id`(`attendance_id`) USING BTREE,
  INDEX `idx_student_id`(`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for round
-- ----------------------------
DROP TABLE IF EXISTS `round`;
CREATE TABLE `round`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) UNSIGNED NOT NULL COMMENT '课程id',
  `round_serial` tinyint(4) UNSIGNED NOT NULL COMMENT '轮次序号',
  `presentation_score_method` tinyint(4) UNSIGNED NOT NULL COMMENT '本轮展示分数计算方法',
  `report_score_method` tinyint(4) UNSIGNED NOT NULL COMMENT '本轮报告分数计算方法',
  `question_score_method` tinyint(4) UNSIGNED NOT NULL COMMENT '本轮提问分数计算方法',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for round_score
-- ----------------------------
DROP TABLE IF EXISTS `round_score`;
CREATE TABLE `round_score`  (
  `round_id` bigint(20) UNSIGNED NOT NULL COMMENT '轮次id',
  `team_id` bigint(20) UNSIGNED NOT NULL COMMENT '小组id',
  `total_score` decimal(4, 1) NULL DEFAULT NULL COMMENT '总成绩',
  `presentation_score` decimal(4, 1) NULL DEFAULT NULL COMMENT '展示成绩',
  `question_score` decimal(4, 1) NULL DEFAULT NULL COMMENT '提问成绩',
  `report_score` decimal(4, 1) NULL DEFAULT NULL COMMENT '报告成绩',
  PRIMARY KEY (`round_id`, `team_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for seminar
-- ----------------------------
DROP TABLE IF EXISTS `seminar`;
CREATE TABLE `seminar`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` bigint(20) UNSIGNED NOT NULL COMMENT '课程id',
  `round_id` bigint(20) UNSIGNED NOT NULL COMMENT '轮次id',
  `seminar_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '讨论课名称',
  `introduction` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '讨论课介绍',
  `max_team` tinyint(4) UNSIGNED NOT NULL COMMENT '报名讨论课最多组数',
  `is_visible` tinyint(4) UNSIGNED NOT NULL COMMENT '是否可见',
  `seminar_serial` tinyint(4) UNSIGNED NOT NULL COMMENT '讨论课序号',
  `enroll_start_time` datetime(0) NULL DEFAULT NULL COMMENT '讨论课报名开始时间',
  `enroll_end_time` datetime(0) NULL DEFAULT NULL COMMENT '讨论课报名截止时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_round_id`(`round_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for seminar_score
-- ----------------------------
DROP TABLE IF EXISTS `seminar_score`;
CREATE TABLE `seminar_score`  (
  `klass_seminar_id` bigint(20) UNSIGNED NOT NULL COMMENT '班级讨论课id',
  `team_id` bigint(20) UNSIGNED NOT NULL COMMENT '小组id',
  `total_score` decimal(4, 1) NULL DEFAULT NULL COMMENT '总成绩',
  `presentation_score` decimal(4, 1) NULL DEFAULT NULL COMMENT '展示成绩',
  `question_score` decimal(4, 1) NULL DEFAULT NULL COMMENT '提问成绩',
  `report_score` decimal(4, 1) NULL DEFAULT NULL COMMENT '报告成绩',
  PRIMARY KEY (`klass_seminar_id`, `team_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for share_seminar_application
-- ----------------------------
DROP TABLE IF EXISTS `share_seminar_application`;
CREATE TABLE `share_seminar_application`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `main_course_id` bigint(20) UNSIGNED NOT NULL COMMENT '主课程id',
  `sub_course_id` bigint(20) UNSIGNED NOT NULL COMMENT '从课程id',
  `sub_course_teacher_id` bigint(20) UNSIGNED NOT NULL COMMENT '从课程的教师id',
  `status` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '请求状态，同意1、不同意0、未处理null',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_main_course_id`(`main_course_id`) USING BTREE,
  INDEX `idx_sub_course_id`(`sub_course_id`) USING BTREE,
  INDEX `idx_sub_course_teacher_id`(`sub_course_teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for share_team_application
-- ----------------------------
DROP TABLE IF EXISTS `share_team_application`;
CREATE TABLE `share_team_application`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `main_course_id` bigint(20) UNSIGNED NOT NULL COMMENT '主课程id',
  `sub_course_id` bigint(20) UNSIGNED NOT NULL COMMENT '从课程id',
  `sub_course_teacher_id` bigint(20) UNSIGNED NOT NULL COMMENT '从课程老师id',
  `status` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '请求状态，同意1、不同意0、未处理null',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_main_course_id`(`main_course_id`) USING BTREE,
  INDEX `idx_sub_course_id`(`sub_course_id`) USING BTREE,
  INDEX `idx_sub_course_teacher_id`(`sub_course_teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生账户',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户密码',
  `is_active` tinyint(4) UNSIGNED NOT NULL COMMENT '账号是否激活',
  `student_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生姓名',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_account`(`account`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email`) USING BTREE,
  INDEX `idx_password`(`password`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 232 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师账户',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户密码',
  `teacher_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师姓名',
  `is_active` tinyint(4) UNSIGNED NOT NULL COMMENT '账号是否激活',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱地址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_account`(`account`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email`) USING BTREE,
  INDEX `idx_password`(`password`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `klass_id` bigint(20) UNSIGNED NOT NULL COMMENT '班级id',
  `course_id` bigint(20) UNSIGNED NOT NULL COMMENT '课程id',
  `leader_id` bigint(20) UNSIGNED NOT NULL COMMENT '队长的学生id',
  `team_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '队伍名称',
  `team_serial` tinyint(4) UNSIGNED NOT NULL COMMENT '队伍序号',
  `klass_serial` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '班级序号',
  `status` tinyint(4) UNSIGNED NOT NULL COMMENT '队伍状态，不合法0、合法1、审核中2',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE,
  INDEX `idx_leader_id`(`leader_id`) USING BTREE,
  INDEX `idx_klass_id`(`klass_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_and_strategy
-- ----------------------------
DROP TABLE IF EXISTS `team_and_strategy`;
CREATE TABLE `team_and_strategy`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `strategy_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '策略所属类标识',
  `strategy_id` bigint(20) UNSIGNED NOT NULL COMMENT '组队策略id',
  PRIMARY KEY (`id`, `strategy_name`, `strategy_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_or_strategy
-- ----------------------------
DROP TABLE IF EXISTS `team_or_strategy`;
CREATE TABLE `team_or_strategy`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `strategy_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '策略所属类的类名',
  `strategy_id` bigint(20) UNSIGNED NOT NULL COMMENT '组队策略id',
  PRIMARY KEY (`id`, `strategy_name`, `strategy_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_strategy
-- ----------------------------
DROP TABLE IF EXISTS `team_strategy`;
CREATE TABLE `team_strategy`  (
  `course_id` bigint(20) UNSIGNED NOT NULL COMMENT '课程id',
  `strategy_serial` tinyint(4) UNSIGNED NOT NULL COMMENT '课程组队策略序号',
  `strategy_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '策略所属类的类名',
  `strategy_id` bigint(20) UNSIGNED NOT NULL COMMENT '策略id',
  PRIMARY KEY (`course_id`, `strategy_serial`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_student
-- ----------------------------
DROP TABLE IF EXISTS `team_student`;
CREATE TABLE `team_student`  (
  `team_id` bigint(20) UNSIGNED NOT NULL COMMENT '队伍id',
  `student_id` bigint(20) UNSIGNED NOT NULL COMMENT '学生id',
  PRIMARY KEY (`team_id`, `student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for team_valid_application
-- ----------------------------
DROP TABLE IF EXISTS `team_valid_application`;
CREATE TABLE `team_valid_application`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `team_id` bigint(20) UNSIGNED NOT NULL COMMENT '小组id',
  `teacher_id` bigint(20) UNSIGNED NOT NULL COMMENT '教师id',
  `reason` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请理由',
  `status` tinyint(4) UNSIGNED NULL DEFAULT NULL COMMENT '请求状态，同意1、不同意0、未处理null',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_team_id`(`team_id`) USING BTREE,
  INDEX `idx_teacher_id`(`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
