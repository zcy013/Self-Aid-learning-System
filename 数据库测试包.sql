-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: learning_system
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chapter2_answers`
--

DROP TABLE IF EXISTS `chapter2_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapter2_answers` (
  `StudentID` int(11) NOT NULL,
  `Answer1` varchar(1000) DEFAULT NULL,
  `Answer2` varchar(1000) DEFAULT NULL,
  `Answer3` varchar(1000) DEFAULT NULL,
  `Answer4` varchar(1000) DEFAULT NULL,
  `Answer5` varchar(1000) DEFAULT NULL,
  `Answer6` varchar(1000) DEFAULT NULL,
  `Answer7` varchar(1000) DEFAULT NULL,
  `Answer8` varchar(1000) DEFAULT NULL,
  `Answer9` varchar(1000) DEFAULT NULL,
  `Answer10` varchar(1000) DEFAULT NULL,
  `Answer11` varchar(1000) DEFAULT NULL,
  `Answer12` varchar(1000) DEFAULT NULL,
  `Answer13` varchar(1000) DEFAULT NULL,
  `Answer14` varchar(1000) DEFAULT NULL,
  `Answer15` varchar(1000) DEFAULT NULL,
  `Answer16` varchar(1000) DEFAULT NULL,
  `Answer17` varchar(1000) DEFAULT NULL,
  `Answer18` varchar(1000) DEFAULT NULL,
  `Answer19` varchar(1000) DEFAULT NULL,
  `Answer20` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter2_answers`
--

LOCK TABLES `chapter2_answers` WRITE;
/*!40000 ALTER TABLE `chapter2_answers` DISABLE KEYS */;
INSERT INTO `chapter2_answers` VALUES (11510352,'D',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11510374,'C','就爱',NULL,NULL,NULL,'B','B','的',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `chapter2_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter2_questions`
--

DROP TABLE IF EXISTS `chapter2_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapter2_questions` (
  `QuestionID` int(11) NOT NULL AUTO_INCREMENT,
  `Property` varchar(6) NOT NULL,
  `Description` varchar(3000) NOT NULL,
  `Reference` varchar(1000) NOT NULL,
  PRIMARY KEY (`QuestionID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter2_questions`
--

LOCK TABLES `chapter2_questions` WRITE;
/*!40000 ALTER TABLE `chapter2_questions` DISABLE KEYS */;
INSERT INTO `chapter2_questions` VALUES (1,'choice',' ______是行末注释的开始\nA. (  B. /*  C. //  D. %\n','C'),(2,'blank','______、______、______、______称为空白。','空行，空格符，换行符，水平制表符'),(3,'choice','Java应用程序从______方法开始执行\nA. Start  B. run   C. try  D. main\n','D'),(4,'choice','每个语句都以___________结束\nA.逗号  B.分号  C.句号  D.省略号\n','B'),(5,'choice','所有变量在声明时必须指定______\nA. 类型  B.初始值  C.访问权限  D.以上都必须指定\n','A'),(6,'choice','以下运算符优先级最高的是______\nA.*  B.-  C.>  D.==\n','A'),(7,'choice','以下运算符优先级最低的是______\nA.%  B.+  C.<=  D.!=','D'),(8,'blank','_____是为Java使用而保留的','关键字'),(9,'blank','写出实现以下任务的语句：如果变量number不等于7，显示“The variable number is not equal to 7”','if ( number != 7 )\nSystem.out.println( \"The variable number is not equal to 7\" );'),(10,'blank','编写一个完整的程序，计算并打印3 个整数的乘积','import java.util.Scanner;\npublic class Product {\n	public static void main ( String args[] ){\n		Scanner input = new Scanner(System.in);\n		\n		int x, y, z, result;\n		\n		System.out.print( \"Enter first integer:\" );\n		x = input.nextInt();\n		\n		System.out.print( \"Enter second integer:\" );\n		y = input.nextInt();\n		\n		System.out.print( \"Enter third integer:\" );\n		z = input.nextInt();\n		\n		result = x * y * z;\n		System.out.printf( \"Product is %d\n\", result );\n	}\n}\n');
/*!40000 ALTER TABLE `chapter2_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter2_scores`
--

DROP TABLE IF EXISTS `chapter2_scores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapter2_scores` (
  `StudentID` int(11) NOT NULL,
  `Answer1` int(11) DEFAULT NULL,
  `Answer2` int(11) DEFAULT NULL,
  `Answer3` int(11) DEFAULT NULL,
  `Answer4` int(11) DEFAULT NULL,
  `Answer5` int(11) DEFAULT NULL,
  `Answer6` int(11) DEFAULT NULL,
  `Answer7` int(11) DEFAULT NULL,
  `Answer8` int(11) DEFAULT NULL,
  `Answer9` int(11) DEFAULT NULL,
  `Answer10` int(11) DEFAULT NULL,
  `Answer11` int(11) DEFAULT NULL,
  `Answer12` int(11) DEFAULT NULL,
  `Answer13` int(11) DEFAULT NULL,
  `Answer14` int(11) DEFAULT NULL,
  `Answer15` int(11) DEFAULT NULL,
  `Answer16` int(11) DEFAULT NULL,
  `Answer17` int(11) DEFAULT NULL,
  `Answer18` int(11) DEFAULT NULL,
  `Answer19` int(11) DEFAULT NULL,
  `Answer20` int(11) DEFAULT NULL,
  `Score` int(11) DEFAULT NULL,
  `Done` enum('done','undone') DEFAULT NULL,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter2_scores`
--

LOCK TABLES `chapter2_scores` WRITE;
/*!40000 ALTER TABLE `chapter2_scores` DISABLE KEYS */;
INSERT INTO `chapter2_scores` VALUES (11510352,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11510374,1,NULL,0,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'undone');
/*!40000 ALTER TABLE `chapter2_scores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter3_answers`
--

DROP TABLE IF EXISTS `chapter3_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapter3_answers` (
  `StudentID` int(11) NOT NULL,
  `Answer1` varchar(1000) DEFAULT NULL,
  `Answer2` varchar(1000) DEFAULT NULL,
  `Answer3` varchar(1000) DEFAULT NULL,
  `Answer4` varchar(1000) DEFAULT NULL,
  `Answer5` varchar(1000) DEFAULT NULL,
  `Answer6` varchar(1000) DEFAULT NULL,
  `Answer7` varchar(1000) DEFAULT NULL,
  `Answer8` varchar(1000) DEFAULT NULL,
  `Answer9` varchar(1000) DEFAULT NULL,
  `Answer10` varchar(1000) DEFAULT NULL,
  `Answer11` varchar(1000) DEFAULT NULL,
  `Answer12` varchar(1000) DEFAULT NULL,
  `Answer13` varchar(1000) DEFAULT NULL,
  `Answer14` varchar(1000) DEFAULT NULL,
  `Answer15` varchar(1000) DEFAULT NULL,
  `Answer16` varchar(1000) DEFAULT NULL,
  `Answer17` varchar(1000) DEFAULT NULL,
  `Answer18` varchar(1000) DEFAULT NULL,
  `Answer19` varchar(1000) DEFAULT NULL,
  `Answer20` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter3_answers`
--

LOCK TABLES `chapter3_answers` WRITE;
/*!40000 ALTER TABLE `chapter3_answers` DISABLE KEYS */;
INSERT INTO `chapter3_answers` VALUES (11510352,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11510374,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `chapter3_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter3_questions`
--

DROP TABLE IF EXISTS `chapter3_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapter3_questions` (
  `QuestionID` int(11) NOT NULL AUTO_INCREMENT,
  `Property` varchar(6) NOT NULL,
  `Description` varchar(3000) NOT NULL,
  `Reference` varchar(1000) NOT NULL,
  PRIMARY KEY (`QuestionID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter3_questions`
--

LOCK TABLES `chapter3_questions` WRITE;
/*!40000 ALTER TABLE `chapter3_questions` DISABLE KEYS */;
INSERT INTO `chapter3_questions` VALUES (1,'choice','以关键字______开始声明的每个类必须存储在与类名完全相同的文件夹里，并且文件扩展名为.java\nA. public  B. protected  C. private  D. final','A'),(2,'choice','Scanner方法______返回一个双精度浮点数\n  A. next  B. nextInt  C. nextFloat  D. nextDouble','D'),(3,'choice','类String在包______中\nA. java.io  B. java.util  C. java.lang  D. java.awt','C'),(4,'choice','格式说明符______用于输出float或double类型的值\nA.%c  B. %f  C. %d  D. %s','B'),(5,'choice','每个类声明都包含关键字______，且后面紧跟类的名字\nA. project  B. package  C. class  D. file','B'),(6,'choice','关键字public是一个______\nA. 访问修饰符  B. 类修饰符  C. 成员修饰符  D. 声明修饰符','A'),(7,'blank','每个形式参数都必须指定一个______和一个______','类型、名字'),(8,'blank','如果总是用完全限定名来指定一个类，则不需要用______','导入声明'),(9,'blank','Java中类型分为两类：______类型和______类型','基本、引用'),(10,'blank','设计一个类Simple，有三个成员变量，分别为int型、double型和String型，这三个成员变量分别含有各自的get方法和set方法，可以用toString方法显示这三个成员变量。','public class Simple {\n	private int intData; \n	private double doubleData; \n	private String stringData; \n	\n	public int getIntData() {\n		return intData;\n	}\n	public void setIntData(int intData) {\n		this.intData = intData;\n	}\n	public double getDoubleData() {\n		return doubleData;\n	}\n	public void setDoubleData(double doubleData) {\n		this.doubleData = doubleData;\n	}\n	public String getStringData() {\n		return stringData;\n	}\n	public void setStringData(String stringData) {\n		this.stringData = stringData;\n	}\n	\n	@Override\n	public String toString() {\n		return \"Simple [intDate=\" + intData + \", doubleData=\" + doubleData\n				+ \", stringData=\" + stringData +\"]\";\n	}\n	\n}');
/*!40000 ALTER TABLE `chapter3_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter3_scores`
--

DROP TABLE IF EXISTS `chapter3_scores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chapter3_scores` (
  `StudentID` int(11) NOT NULL,
  `Answer1` int(11) DEFAULT NULL,
  `Answer2` int(11) DEFAULT NULL,
  `Answer3` int(11) DEFAULT NULL,
  `Answer4` int(11) DEFAULT NULL,
  `Answer5` int(11) DEFAULT NULL,
  `Answer6` int(11) DEFAULT NULL,
  `Answer7` int(11) DEFAULT NULL,
  `Answer8` int(11) DEFAULT NULL,
  `Answer9` int(11) DEFAULT NULL,
  `Answer10` int(11) DEFAULT NULL,
  `Answer11` int(11) DEFAULT NULL,
  `Answer12` int(11) DEFAULT NULL,
  `Answer13` int(11) DEFAULT NULL,
  `Answer14` int(11) DEFAULT NULL,
  `Answer15` int(11) DEFAULT NULL,
  `Answer16` int(11) DEFAULT NULL,
  `Answer17` int(11) DEFAULT NULL,
  `Answer18` int(11) DEFAULT NULL,
  `Answer19` int(11) DEFAULT NULL,
  `Answer20` int(11) DEFAULT NULL,
  `Score` int(11) DEFAULT NULL,
  `Done` enum('done','undone') DEFAULT NULL,
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter3_scores`
--

LOCK TABLES `chapter3_scores` WRITE;
/*!40000 ALTER TABLE `chapter3_scores` DISABLE KEYS */;
INSERT INTO `chapter3_scores` VALUES (11510352,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11510374,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `chapter3_scores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ppts`
--

DROP TABLE IF EXISTS `ppts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ppts` (
  `PPT_page` int(11) NOT NULL AUTO_INCREMENT,
  `Chapter2` varchar(1000) DEFAULT NULL,
  `Chapter3` varchar(1000) DEFAULT NULL,
  `Chapter4` varchar(1000) DEFAULT NULL,
  `Chapter5` varchar(1000) DEFAULT NULL,
  `Chapter6` varchar(1000) DEFAULT NULL,
  `Chapter7` varchar(1000) DEFAULT NULL,
  `Chapter8` varchar(1000) DEFAULT NULL,
  `Chapter9` varchar(1000) DEFAULT NULL,
  `Chapter10` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`PPT_page`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ppts`
--

LOCK TABLES `ppts` WRITE;
/*!40000 ALTER TABLE `ppts` DISABLE KEYS */;
INSERT INTO `ppts` VALUES (1,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-1.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-1.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-2.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-2.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-3.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-3.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-4.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-4.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-5.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-5.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-6.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-6.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-7.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-7.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-8.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-8.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-9.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-9.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-10.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-10.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-11.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-11.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-12.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-12.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-13.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-13.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-14.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-14.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-15.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-15.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-16.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-16.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-17.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-17.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-18.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-18.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-19.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-19.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-20.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-20.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-21.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-21.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-22.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-22.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-23.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-23.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-24.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-24.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-25.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-25.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-26.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-26.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-27.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-27.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-28.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-28.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-29.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-29.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-30.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-30.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-31.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-31.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-32.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-32.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-33.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-33.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-34.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-34.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-35.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-35.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-36.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-36.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-37.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-37.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-38.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-38.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-39.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-39.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-40.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-40.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-41.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-41.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-42.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-42.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-43.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-43.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-44.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-44.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-45.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-45.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-46.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-46.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-47.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-47.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-48.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-48.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-49.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-49.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-50.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-50.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-51.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-51.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-52.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-52.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-53.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-53.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-54.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-54.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-55.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-55.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-56.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-56.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-57.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-57.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-58.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-58.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(59,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-59.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-59.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-60.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-60.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-61.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-61.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(62,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-62.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-62.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(63,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-63.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-63.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-64.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-64.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-65.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-65.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(66,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-66.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-66.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(67,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-67.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-67.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-68.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-68.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-69.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-69.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-70.jpg','E:\\StudySlides\\ch03\\ch03_cheng_V3-70.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-71.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-72.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-73.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-74.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-75.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(76,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-76.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-77.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(78,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-78.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,'E:\\StudySlides\\ch02\\ch02_cheng_ver3-79.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(86,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(87,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(88,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(89,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(91,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(92,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(93,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(96,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(97,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(98,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(99,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(100,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `ppts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reading`
--

DROP TABLE IF EXISTS `reading`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reading` (
  `chapterID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `Reading` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`chapterID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reading`
--

LOCK TABLES `reading` WRITE;
/*!40000 ALTER TABLE `reading` DISABLE KEYS */;
INSERT INTO `reading` VALUES (2,'Java之关键字和保留字','1 名字不能用保留字和关键字，但如果保留字或关键字中有字母大写就不算保留字或关键字了。\n2 开头字符能用字母，$或者_\n\n3 名字中不能用+ ，-  空格等字符\n\n4 部分易混的保留字关键字和非保留字关键字\n\n null, native, goto，const，instanceof，default是关键字保留字\n then,sizeof,main，Boolean，unsigned,Java, Integer不是关键字保留字\n \n注意：由于unsigned不是java中的关键字。所以java中没有这种写法：unsigned int a=1; 会显示编译出错。 \n\n\njava中所有的关键字和保留字如下：\n\nabstract    |   continue    |   for    |    new     |   switch \nassert***  |   default     |   goto*     |   package     |   synchronized \nboolean    |   do     |   if     |   private     |   this \nbreak         |   double     |   implements    |   protected    |   throw \nbyte            |   else     |   import     |   public  throws \ncase          |   enum****    |   instanceof    |   return     |   transient  \ncatch         |   extends     |   int     |   short     |   try \nchar           |   final     |   interface    |   static     |   void \nclass         |   finally     |   long     |   strictfp**    |   volatile \nconst*       |   float     |   native     |   super     |   while\n\n\n*   not used \n**   added in 1.2 \n***   added in 1.4  \n****   added in 5.0      \n\nture和false是保留字么？好像不是，但应该也不能用。 \n\nnative：只能用于方法，包括静态方法。java的类通过使用声明为native的方法来完成某些功能和访问某些硬件。例如在多线程编程时Thread的start()就调用private native void start0()方法。Java的缺点一是速度比不上C++，二是不能直接操作系统底层。native方法相当于Java程序与C程序的借口，扩展了Java的功能。具体参见： \n\nJava入门--认识理解Java中native方法：http://www.enet.com.cn/article/2007/1029/A20071029886398.shtml。\n\n拓展阅读JNI：http://baike.baidu.com/view/1272329.htm\n\ntransient:只能应用于类的成员变量，不能应用于函数的局部变量。声明为transient的变量将不被对象序列化，可用于保护安全信息。\n\nsynchronized:在多线程中使用。\n\nvolatile: 能用于变量，也能用于常量。一个声明为volatile的变量可以被线程修改，正在运行的多个线程都可以访问该变量。\n\nfinal：\n对基本类型变量用final修饰表示该变量只能被初始化一次，之后不能再修改值。\n对一个对象变量用final修饰的意义，不是不能修改这个对象内容了，而是这个对象的引用只能指向这个对象在堆中的地址。\nfinal Person p1 = new Person();不是不能修改p1这个对象了，而是p1不能指向其它对象了(p1 = p2错误)。\n对一个方法用final修饰表示该方法不能被覆盖（即不能被子类方法覆盖，子类天性继承父类非private方法）。\nfinal修饰一个类表示该类不能被继承。\nfinal、finally、finalize的简单区别：http://wenku.baidu.com/view/2d4593d0ce2f0066f533224a.html\nJava关键字final、static使用总结：http://java.chinaitlab.com/base/724785.html'),(3,'Java类与对象 ','面向对象基本概念：封装、继承、多态\n封装：就是把数据和行为结合在一起形成统一的整体，并对对象使用者隐藏数据的实现过程。\n继承：Java继承是使用已存在的类的定义作为基础建立新类的技术，继承避免了对一般类和特殊类之间共同特征进行的重复描述。\n多态：多态指同一个实体同时具有多种形式。\njava类与对象类与对象关系：类是描述具有相同特征的一类事物的基本原型，定义了这类事物所拥有的数据特征以及可以执行的操作；对象是类的实例，是类的具体化\njava类与对象java类是面向对象封装概念的基本体现，java类封装了抽象概念的数据（属性）与行为（方法）。\njava类的基本构成：属性、方法、构造方法\n属性是是类对抽象概念数据特征的描述，方法是类对概念行为的描述，构造方法在类的对象实例化时对类对象进行初始化。\n重载与覆写（重写）\n重载：方法的重载是多态性的体现，重载方法具有相同的方法名称，但方法参数列表不同（参数类型或数目不同），重载为相似功能提供了不同的实现\n重写：重写是指在子类中覆盖父类方法的实现，对父类方法进行重新定义，当父类引用指向子类对象并调用重写方法时，将调用子类方法的实现。子类函数的访问修饰权限不能低于父类的。\nsuper与this\nsuper代表当前类的父类（超类），子类的构造函数如果要引用super的话，必须把super放在函数的首位；当子类变量与父类变量重名时，使用super调用父类变量\nthis代表当前对象，this使用：this.属性，this.方法，this（）；使用this来区别重名的局部变量与成员变量；使用this在一个构造函数中调用其他的重载构造函数\njava类与对象java修饰符：访问权限修饰符、final、static、abstract\njava类与对象访问权限修饰符：public、protected、private、default\npublic 成员对所有类可见\nprivate 成员仅类内部可见\nprotected相同包中的类可以访问（包访问权限）；基类通过protected把基类中的成员的访问权限赋予派生类不是所有类（派生类访问权限）。\ndefault如果一个类的成员没有任何权限修饰，那么它门就是缺省包访问权限\n类仅能用public或默认权限修饰\njava类与对象final修饰符：\n    final修饰成员变量：该变量为常量；修饰方法：该方法不能够在子类中被重写；修饰类：该类不能被继承\njava类与对象static修饰符：\n    修饰成员变量：该变量为静态变量（类变量），属于类本身，所有该类对象公用该变量；\n    修饰方法：该方法为静态方法，在静态方法中不能使用非静态成员变量或方法，因为在静态方法调用时可能还没有对象被创建，没有对象也就无法获取其成员。静态成员函数中也不能使用this或者super，因为它们是和类的对象相关联的\n    静态内部类：静态内部类可以对照静态成员变量来理解\njava类与对象abstract修饰符：\n    定义抽象类、方法\n    抽象方法 没有方法体｛｝，仅有方法声明\n    抽象类中可以没有抽象方法，有抽象方法的类一定是抽象类');
/*!40000 ALTER TABLE `reading` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `StudentID` int(11) NOT NULL,
  `StudentName` varchar(10) NOT NULL,
  `StudentPassword` varchar(16) NOT NULL,
  `Chapter2` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Chapter3` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Chapter4` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Chapter5` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Chapter6` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Chapter7` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Chapter8` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Chapter9` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Chapter10` enum('unlearned','submitted','unpassed','passed') DEFAULT 'unlearned',
  `Vcode` int(11) DEFAULT NULL,
  `Activation` enum('yes','no') NOT NULL DEFAULT 'no',
  PRIMARY KEY (`StudentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (11111111,'admin','123456','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned',NULL,'yes'),(11510123,'张三','123123','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned',123456,'yes'),(11510352,'李子强','267289','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned',123456,'yes'),(11510374,'周','123456','submitted','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned',247938,'yes'),(11510456,'李四','456456','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned',123456,'yes'),(11510789,'王五','789789','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned','unlearned',123456,'yes');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-09 10:40:49
