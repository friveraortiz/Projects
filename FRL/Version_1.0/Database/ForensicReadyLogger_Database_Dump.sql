CREATE DATABASE  IF NOT EXISTS `ForensicReadyLogger` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `ForensicReadyLogger`;

/*USERS*/
/*Create the Admin User*/
CREATE USER 'admin_frl'@'localhost' IDENTIFIED BY 'eL9V4-tx$B';
GRANT ALL PRIVILEGES ON ForensicReadyLogger.* TO 'admin_frl'@'localhost' WITH GRANT OPTION;
FLUSH PRIVILEGES;

-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: ForensicReadyLogger
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class_inventory`
--

DROP TABLE IF EXISTS `class_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `class_inventory` (
  `pi_id` int(11) NOT NULL COMMENT 'Package Inventory Identifier',
  `ci_id` int(11) NOT NULL COMMENT 'Class Inventory Identifier',
  `ci_name` varchar(500) NOT NULL COMMENT 'Class Inventory Name',
  PRIMARY KEY (`pi_id`,`ci_id`),
  CONSTRAINT `fk_pi_id_ci` FOREIGN KEY (`pi_id`) REFERENCES `package_inventory` (`pi_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_inventory`
--

LOCK TABLES `class_inventory` WRITE;
/*!40000 ALTER TABLE `class_inventory` DISABLE KEYS */;
INSERT INTO `class_inventory` VALUES (1,1,'Configuration.java'),(1,2,'JavaClasses.java'),(2,1,'ClassMethodController.java'),(2,2,'DatabaseOperationsController.java'),(2,3,'ProjectExceptionController.java'),(3,1,'Database.java'),(4,1,'ForensicReadyLogger.java'),(5,1,'ClassMethod.java'),(5,2,'ClassMethodDatabase.java'),(5,3,'DatabaseClass.java'),(5,4,'DatabaseMethod.java'),(5,5,'DatabaseOperationsDatabase.java'),(5,6,'Dbms.java'),(5,7,'ProgrammingLanguage.java'),(5,8,'ProjectException.java'),(5,9,'ProjectExceptionDatabase.java');
/*!40000 ALTER TABLE `class_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_method`
--

DROP TABLE IF EXISTS `class_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `class_method` (
  `cm_id` int(11) NOT NULL COMMENT 'Class Method Identifier',
  `cm_package_name` varchar(1000) NOT NULL COMMENT 'Class Method Package Name',
  `cm_class_name` varchar(1000) NOT NULL COMMENT 'Class Method Class Name',
  `cm_full_method_name` varchar(1000) NOT NULL COMMENT 'Class Method Full Method Name',
  `cm_short_method_name` varchar(1000) NOT NULL COMMENT 'Class Method Short Method Name',
  `cm_return_type_1` varchar(1000) DEFAULT NULL COMMENT 'Class Method Return Type 1',
  `cm_return_type_2` varchar(1000) DEFAULT NULL COMMENT 'Class Method Return Type 2',
  `cm_filename` varchar(5000) NOT NULL COMMENT 'Class Method FileName',
  `cm_start_line_number` int(11) NOT NULL COMMENT 'Class Method Starting Line Number',
  `cm_end_line_number` int(11) DEFAULT NULL COMMENT 'Class Method Ending Line Number',
  `cm_text_filename` varchar(5000) DEFAULT NULL COMMENT 'Class Method Text FileName',
  PRIMARY KEY (`cm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_method`
--

LOCK TABLES `class_method` WRITE;
/*!40000 ALTER TABLE `class_method` DISABLE KEYS */;
INSERT INTO `class_method` VALUES (1,'test','TestTravelRequestDb','public static void main(','main','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/test/TestTravelRequestDb.java',12,131,'main.txt'),(2,'test','TestLoginDb','public static void main(','main','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/test/TestLoginDb.java',18,90,'main.txt'),(3,'test','TestUserDb','public static void main(','main','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/test/TestUserDb.java',16,142,'main.txt'),(4,'test','TestModuleDb','public static void main(','main','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/test/TestModuleDb.java',18,118,'main.txt'),(5,'test','TestEmployeeDb','public static void main(','main','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/test/TestEmployeeDb.java',25,176,'main.txt'),(6,'controller','TravelRequestController','public void load(','load','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',371,385,'load.txt'),(7,'controller','TravelRequestController','public void delete(','delete','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',257,370,'delete.txt'),(8,'controller','TravelRequestController','public void save(','save','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',142,256,'save.txt'),(9,'controller','TravelRequestController','public void deleteTravelRequest(','deleteTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',137,141,'deleteTravelRequest.txt'),(10,'controller','TravelRequestController','public void loadTravelRequest(','loadTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',386,409,'loadTravelRequest.txt'),(11,'controller','TravelRequestController','public boolean validateTravelRequestFields(','validateTravelRequestFields','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',410,476,'validateTravelRequestFields.txt'),(12,'controller','TravelRequestController','public boolean validateTravelReturnDates(','validateTravelReturnDates','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',477,500,'validateTravelReturnDates.txt'),(13,'controller','TravelRequestController','public boolean validateTotalFunding(','validateTotalFunding','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',501,517,'validateTotalFunding.txt'),(14,'controller','TravelRequestController','public int maxNumTravelRequest(','maxNumTravelRequest','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',518,538,'maxNumTravelRequest.txt'),(15,'controller','TravelRequestController','public void addTravelRequest(','addTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/TravelRequestController.java',33,136,'addTravelRequest.txt'),(16,'controller','EmployeeController','public void load(','load','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',284,299,'load.txt'),(17,'controller','EmployeeController','public void delete(','delete','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',198,283,'delete.txt'),(18,'controller','EmployeeController','public void save(','save','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',114,197,'save.txt'),(19,'controller','EmployeeController','public void addEmployee(','addEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',35,108,'addEmployee.txt'),(20,'controller','EmployeeController','public void deleteEmployee(','deleteEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',109,113,'deleteEmployee.txt'),(21,'controller','EmployeeController','public void loadEmployee(','loadEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',300,321,'loadEmployee.txt'),(22,'controller','EmployeeController','public boolean validateEmployeeFields(','validateEmployeeFields','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',322,389,'validateEmployeeFields.txt'),(23,'controller','EmployeeController','public boolean validateSupervisor(','validateSupervisor','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',390,422,'validateSupervisor.txt'),(24,'controller','EmployeeController','public boolean validateDobJdDates(','validateDobJdDates','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',423,447,'validateDobJdDates.txt'),(25,'controller','EmployeeController','public boolean validateJdTdDates(','validateJdTdDates','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',448,472,'validateJdTdDates.txt'),(26,'controller','EmployeeController','public boolean validateDeleteEmployee1(','validateDeleteEmployee1','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',473,560,'validateDeleteEmployee1.txt'),(27,'controller','EmployeeController','public boolean validateFullName(','validateFullName','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',561,651,'validateFullName.txt'),(28,'controller','EmployeeController','public int maxNumEmployee(','maxNumEmployee','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',652,673,'maxNumEmployee.txt'),(29,'controller','EmployeeController','public boolean validateDeleteEmployee2(','validateDeleteEmployee2','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/EmployeeController.java',674,760,'validateDeleteEmployee2.txt'),(30,'controller','ModuleController','public void saveModules(','saveModules','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/ModuleController.java',18,33,'saveModules.txt'),(31,'controller','ModuleController',' public String[] getModules(','getModules','Class','String','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/ModuleController.java',34,78,'getModules.txt'),(32,'controller','ModuleController',' public String[][] getSubModules(','getSubModules','Class','String','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/ModuleController.java',79,124,'getSubModules.txt'),(33,'controller','UserController','public void load(','load','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',180,195,'load.txt'),(34,'controller','UserController','public void delete(','delete','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',128,179,'delete.txt'),(35,'controller','UserController','public void save(','save','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',74,127,'save.txt'),(36,'controller','UserController','public void addUser(','addUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',28,68,'addUser.txt'),(37,'controller','UserController','public void deleteUser(','deleteUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',69,73,'deleteUser.txt'),(38,'controller','UserController','public void loadUser(','loadUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',196,229,'loadUser.txt'),(39,'controller','UserController','public boolean validateUserFields(','validateUserFields','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',230,266,'validateUserFields.txt'),(40,'controller','UserController','public boolean validateEmployee(','validateEmployee','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',267,322,'validateEmployee.txt'),(41,'controller','UserController','public boolean validatePassword(','validatePassword','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',323,340,'validatePassword.txt'),(42,'controller','UserController','public boolean validateUserName(','validateUserName','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',341,397,'validateUserName.txt'),(43,'controller','UserController','public boolean validateEmail(','validateEmail','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',398,454,'validateEmail.txt'),(44,'controller','UserController','public boolean validateDeleteUser(','validateDeleteUser','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',455,511,'validateDeleteUser.txt'),(45,'controller','UserController','public boolean validateUser(','validateUser','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/controller/UserController.java',512,570,'validateUser.txt'),(46,'instrumentation','LoggingClient','private LoggingClient(','LoggingClient','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/instrumentation/LoggingClient.java',13,28,'LoggingClient.txt'),(47,'instrumentation','LoggingClient','private int getThreadID(','getThreadID','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/instrumentation/LoggingClient.java',69,96,'getThreadID.txt'),(48,'instrumentation','LoggingClient','public void setNodeID(','setNodeID','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/instrumentation/LoggingClient.java',29,32,'setNodeID.txt'),(49,'instrumentation','LoggingClient','public int getNodeID(','getNodeID','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/instrumentation/LoggingClient.java',33,38,'getNodeID.txt'),(50,'instrumentation','LoggingClient','public void instrument(','instrument','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/instrumentation/LoggingClient.java',39,68,'instrument.txt'),(51,'gui','UserToolBar','public void setUserToolBarListener(','setUserToolBarListener','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserToolBar.java',65,71,'setUserToolBarListener.txt'),(52,'gui','UserToolBar','public void actionPerformed(','actionPerformed','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserToolBar.java',72,87,'actionPerformed.txt'),(53,'gui','UserFormEvent','public UserFormEvent(','UserFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',16,21,'UserFormEvent.txt'),(54,'gui','UserFormEvent','public UserFormEvent(','UserFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',22,34,'UserFormEvent1.txt'),(55,'gui','UserFormEvent','public UserFormEvent(','UserFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',35,46,'UserFormEvent2.txt'),(56,'gui','UserFormEvent','public UserFormEvent(','UserFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',47,53,'UserFormEvent3.txt'),(57,'gui','UserFormEvent','public int getId(','getId','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',54,59,'getId.txt'),(58,'gui','UserFormEvent','public void setId(','setId','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',60,70,'setId.txt'),(59,'gui','UserFormEvent','public void setEmployee(','setEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',101,110,'setEmployee.txt'),(60,'gui','UserFormEvent','public void setUserLevel(','setUserLevel','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',121,124,'setUserLevel.txt'),(61,'gui','UserFormEvent','public void setUserName(','setUserName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',71,80,'setUserName.txt'),(62,'gui','UserFormEvent','public void setPassword(','setPassword','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',81,90,'setPassword.txt'),(63,'gui','UserFormEvent','public void setconfirmPassword(','setconfirmPassword','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',91,100,'setconfirmPassword.txt'),(64,'gui','UserFormEvent','public void setEmail(','setEmail','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormEvent.java',111,120,'setEmail.txt'),(65,'gui','UserTableModel','public UserTableModel(','UserTableModel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTableModel.java',18,30,'UserTableModel.txt'),(66,'gui','UserTableModel','public int getColumnCount(','getColumnCount','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTableModel.java',44,72,'getColumnCount.txt'),(67,'gui','UserTableModel','public void setDataUser(','setDataUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTableModel.java',31,37,'setDataUser.txt'),(68,'gui','UserTableModel','public int getRowCount(','getRowCount','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTableModel.java',38,43,'getRowCount.txt'),(69,'gui','UserFrame','public boolean validateEmployee(','validateEmployee','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFrame.java',557,573,'validateEmployee.txt'),(70,'gui','UserFrame','public boolean validateUserName(','validateUserName','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFrame.java',574,592,'validateUserName.txt'),(71,'gui','UserFrame','public boolean validateEmail(','validateEmail','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFrame.java',593,608,'validateEmail.txt'),(72,'gui','UserFrame','public boolean validateDeleteUser(','validateDeleteUser','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFrame.java',609,627,'validateDeleteUser.txt'),(73,'gui','UserFrame','public void loadingUsers(','loadingUsers','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFrame.java',490,506,'loadingUsers.txt'),(74,'gui','UserFrame','public void refreshUser(','refreshUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFrame.java',450,489,'refreshUser.txt'),(75,'gui','UserFrame','public void disableFieldsUsers(','disableFieldsUsers','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFrame.java',507,531,'disableFieldsUsers.txt'),(76,'gui','UserFrame','public void enableFieldsUsers(','enableFieldsUsers','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFrame.java',532,556,'enableFieldsUsers.txt'),(77,'gui','LoginFormPanel','public LoginFormPanel(','LoginFormPanel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/LoginFormPanel.java',27,86,'LoginFormPanel.txt'),(78,'gui','LoginFormPanel','public void setLoginFormPanelListener(','setLoginFormPanelListener','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/LoginFormPanel.java',144,148,'setLoginFormPanelListener.txt'),(79,'gui','LoginFormPanel','public void layoutComponents(','layoutComponents','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/LoginFormPanel.java',87,143,'layoutComponents.txt'),(80,'gui','EmployeeTableModel','public EmployeeTableModel(','EmployeeTableModel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTableModel.java',20,31,'EmployeeTableModel.txt'),(81,'gui','EmployeeTableModel','public int getColumnCount(','getColumnCount','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTableModel.java',45,89,'getColumnCount.txt'),(82,'gui','EmployeeTableModel','public int getRowCount(','getRowCount','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTableModel.java',39,44,'getRowCount.txt'),(83,'gui','EmployeeTableModel','public void setDataEmployee(','setDataEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTableModel.java',32,38,'setDataEmployee.txt'),(84,'gui','LoginFrame','public boolean validateUser(','validateUser','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/LoginFrame.java',100,139,'validateUser.txt'),(85,'gui','ModuleFormEvent','public ModuleFormEvent(','ModuleFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/ModuleFormEvent.java',11,15,'ModuleFormEvent.txt'),(86,'gui','ModuleFormEvent','public ModuleFormEvent(','ModuleFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/ModuleFormEvent.java',16,30,'ModuleFormEvent1.txt'),(87,'gui','ModuleFormEvent','public void setModuleName(','setModuleName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/ModuleFormEvent.java',31,40,'setModuleName.txt'),(88,'gui','ModuleFormEvent','public void setSubModuleName(','setSubModuleName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/ModuleFormEvent.java',41,50,'setSubModuleName.txt'),(89,'gui','ModuleFormEvent','public void setUserLevel(','setUserLevel','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/ModuleFormEvent.java',51,55,'setUserLevel.txt'),(90,'gui','UserFormPanel','public UserFormPanel(','UserFormPanel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormPanel.java',67,295,'UserFormPanel.txt'),(91,'gui','UserFormPanel','public void setUserFormListener(','setUserFormListener','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormPanel.java',460,464,'setUserFormListener.txt'),(92,'gui','UserFormPanel','public void loadEmployees(','loadEmployees','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormPanel.java',475,504,'loadEmployees.txt'),(93,'gui','UserFormPanel','public void layoutComponents(','layoutComponents','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormPanel.java',296,459,'layoutComponents.txt'),(94,'gui','UserFormPanel','public void cleanFields(','cleanFields','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserFormPanel.java',465,474,'cleanFields.txt'),(95,'gui','TravelRequestTableModel','public TravelRequestTableModel(','TravelRequestTableModel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTableModel.java',20,31,'TravelRequestTableModel.txt'),(96,'gui','TravelRequestTableModel','public int getColumnCount(','getColumnCount','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTableModel.java',45,89,'getColumnCount.txt'),(97,'gui','TravelRequestTableModel','public int getRowCount(','getRowCount','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTableModel.java',39,44,'getRowCount.txt'),(98,'gui','TravelRequestTableModel','public void setDataTravelRequest(','setDataTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTableModel.java',32,38,'setDataTravelRequest.txt'),(99,'gui','TravelRequestFormPanel','public TravelRequestFormPanel(','TravelRequestFormPanel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormPanel.java',84,415,'TravelRequestFormPanel.txt'),(100,'gui','TravelRequestFormPanel','public void assignDefaultValues(','assignDefaultValues','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormPanel.java',749,776,'assignDefaultValues.txt'),(101,'gui','TravelRequestFormPanel','public void setTravelRequestFormListener(','setTravelRequestFormListener','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormPanel.java',695,699,'setTravelRequestFormListener.txt'),(102,'gui','TravelRequestFormPanel','public void assignTravelRequestNumber(','assignTravelRequestNumber','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormPanel.java',777,820,'assignTravelRequestNumber.txt'),(103,'gui','TravelRequestFormPanel','public void loadEmployees(','loadEmployees','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormPanel.java',718,748,'loadEmployees.txt'),(104,'gui','TravelRequestFormPanel','public void layoutComponents(','layoutComponents','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormPanel.java',416,694,'layoutComponents.txt'),(105,'gui','TravelRequestFormPanel','public void cleanFields(','cleanFields','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormPanel.java',700,717,'cleanFields.txt'),(106,'gui','HRMApp','public static void main(','main','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/HRMApp.java',8,30,'main.txt'),(107,'gui','EmployeeTablePanel','public EmployeeTablePanel(','EmployeeTablePanel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTablePanel.java',37,144,'EmployeeTablePanel.txt'),(108,'gui','EmployeeTablePanel','public void setEmployeeTableListener(','setEmployeeTableListener','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTablePanel.java',157,161,'setEmployeeTableListener.txt'),(109,'gui','EmployeeTablePanel','public int getEmployeeIdSelected(','getEmployeeIdSelected','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTablePanel.java',162,286,'getEmployeeIdSelected.txt'),(110,'gui','EmployeeTablePanel','public void setDataEmployee(','setDataEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTablePanel.java',145,151,'setDataEmployee.txt'),(111,'gui','EmployeeTablePanel','public void refresh(','refresh','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeTablePanel.java',152,156,'refresh.txt'),(112,'gui','UserTablePanel','public UserTablePanel(','UserTablePanel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTablePanel.java',32,143,'UserTablePanel.txt'),(113,'gui','UserTablePanel','public void setDataUser(','setDataUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTablePanel.java',144,150,'setDataUser.txt'),(114,'gui','UserTablePanel','public void setUserTableListener(','setUserTableListener','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTablePanel.java',156,160,'setUserTableListener.txt'),(115,'gui','UserTablePanel','public void refresh(','refresh','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTablePanel.java',151,155,'refresh.txt'),(116,'gui','UserTablePanel','public int getUserIdSelected(','getUserIdSelected','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/UserTablePanel.java',161,225,'getUserIdSelected.txt'),(117,'gui','TravelRequestFormEvent','public TravelRequestFormEvent(','TravelRequestFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',24,29,'TravelRequestFormEvent.txt'),(118,'gui','TravelRequestFormEvent','public TravelRequestFormEvent(','TravelRequestFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',30,51,'TravelRequestFormEvent1.txt'),(119,'gui','TravelRequestFormEvent','public TravelRequestFormEvent(','TravelRequestFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',52,72,'TravelRequestFormEvent2.txt'),(120,'gui','TravelRequestFormEvent','public int getId(','getId','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',73,78,'getId.txt'),(121,'gui','TravelRequestFormEvent','public void setCurrency(','setCurrency','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',199,204,'setCurrency.txt'),(122,'gui','TravelRequestFormEvent','public void setId(','setId','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',79,90,'setId.txt'),(123,'gui','TravelRequestFormEvent','public float getTotalFunding(','getTotalFunding','float','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',205,210,'getTotalFunding.txt'),(124,'gui','TravelRequestFormEvent','public void setNumber(','setNumber','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',91,102,'setNumber.txt'),(125,'gui','TravelRequestFormEvent','public void setEmployee(','setEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',103,114,'setEmployee.txt'),(126,'gui','TravelRequestFormEvent','public void setTransportation(','setTransportation','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',115,126,'setTransportation.txt'),(127,'gui','TravelRequestFormEvent','public void setPurpose(','setPurpose','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',127,138,'setPurpose.txt'),(128,'gui','TravelRequestFormEvent','public void setTravelFrom(','setTravelFrom','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',139,150,'setTravelFrom.txt'),(129,'gui','TravelRequestFormEvent','public void setTravelTo(','setTravelTo','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',151,162,'setTravelTo.txt'),(130,'gui','TravelRequestFormEvent','public void setTravelDate(','setTravelDate','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',163,174,'setTravelDate.txt'),(131,'gui','TravelRequestFormEvent','public void setReturnDate(','setReturnDate','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',175,186,'setReturnDate.txt'),(132,'gui','TravelRequestFormEvent','public void setNotes(','setNotes','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',187,198,'setNotes.txt'),(133,'gui','TravelRequestFormEvent','public void setTotalFunding(','setTotalFunding','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',211,222,'setTotalFunding.txt'),(134,'gui','TravelRequestFormEvent','public void setStatus(','setStatus','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',223,234,'setStatus.txt'),(135,'gui','TravelRequestFormEvent','public void setStatusNotes(','setStatusNotes','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFormEvent.java',235,239,'setStatusNotes.txt'),(136,'gui','TravelRequestTablePanel','public TravelRequestTablePanel(','TravelRequestTablePanel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTablePanel.java',39,206,'TravelRequestTablePanel.txt'),(137,'gui','TravelRequestTablePanel','public void setTravelRequestTableListener(','setTravelRequestTableListener','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTablePanel.java',219,223,'setTravelRequestTableListener.txt'),(138,'gui','TravelRequestTablePanel','public int getTravelRequestIdSelected(','getTravelRequestIdSelected','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTablePanel.java',224,348,'getTravelRequestIdSelected.txt'),(139,'gui','TravelRequestTablePanel','public void refresh(','refresh','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTablePanel.java',214,218,'refresh.txt'),(140,'gui','TravelRequestTablePanel','public void setDataTravelRequest(','setDataTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestTablePanel.java',207,213,'setDataTravelRequest.txt'),(141,'gui','EmployeeFormEvent','public EmployeeFormEvent(','EmployeeFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',23,28,'EmployeeFormEvent.txt'),(142,'gui','EmployeeFormEvent','public EmployeeFormEvent(','EmployeeFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',29,51,'EmployeeFormEvent1.txt'),(143,'gui','EmployeeFormEvent','public EmployeeFormEvent(','EmployeeFormEvent','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',52,73,'EmployeeFormEvent2.txt'),(144,'gui','EmployeeFormEvent','public int getId(','getId','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',74,79,'getId.txt'),(145,'gui','EmployeeFormEvent','public void setId(','setId','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',80,90,'setId.txt'),(146,'gui','EmployeeFormEvent','public void setNumber(','setNumber','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',91,100,'setNumber.txt'),(147,'gui','EmployeeFormEvent','public void setFirstName(','setFirstName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',101,110,'setFirstName.txt'),(148,'gui','EmployeeFormEvent','public void setMiddleName(','setMiddleName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',111,120,'setMiddleName.txt'),(149,'gui','EmployeeFormEvent','public void setLastName(','setLastName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',121,130,'setLastName.txt'),(150,'gui','EmployeeFormEvent','public void setDob(','setDob','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',131,140,'setDob.txt'),(151,'gui','EmployeeFormEvent','public void setGender(','setGender','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',141,150,'setGender.txt'),(152,'gui','EmployeeFormEvent','public void setMaritalStatus(','setMaritalStatus','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',151,160,'setMaritalStatus.txt'),(153,'gui','EmployeeFormEvent','public void setMobilePhone(','setMobilePhone','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',161,170,'setMobilePhone.txt'),(154,'gui','EmployeeFormEvent','public void setJoinedDate(','setJoinedDate','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',171,180,'setJoinedDate.txt'),(155,'gui','EmployeeFormEvent','public void setTerminatedDate(','setTerminatedDate','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',181,190,'setTerminatedDate.txt'),(156,'gui','EmployeeFormEvent','public void setJobTitle(','setJobTitle','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',191,200,'setJobTitle.txt'),(157,'gui','EmployeeFormEvent','public void setDepartment(','setDepartment','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',201,210,'setDepartment.txt'),(158,'gui','EmployeeFormEvent','public void setSupervisor(','setSupervisor','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormEvent.java',211,214,'setSupervisor.txt'),(159,'gui','PasswordCellRenderer','public PasswordCellRenderer(','PasswordCellRenderer','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/PasswordCellRenderer.java',11,31,'PasswordCellRenderer.txt'),(160,'gui','TravelRequestFrame','public void loadingTravelRequests(','loadingTravelRequests','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFrame.java',611,626,'loadingTravelRequests.txt'),(161,'gui','TravelRequestFrame','public void refreshTravelRequest(','refreshTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFrame.java',499,600,'refreshTravelRequest.txt'),(162,'gui','TravelRequestFrame','public void originalStatus(','originalStatus','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFrame.java',792,801,'originalStatus.txt'),(163,'gui','TravelRequestFrame',' public String getStatusTravelRequest(','getStatusTravelRequest','Class','String','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFrame.java',601,610,'getStatusTravelRequest.txt'),(164,'gui','TravelRequestFrame','public void disableFieldsTravelRequest(','disableFieldsTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFrame.java',627,686,'disableFieldsTravelRequest.txt'),(165,'gui','TravelRequestFrame','public void enableFieldsTravelRequest(','enableFieldsTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFrame.java',687,736,'enableFieldsTravelRequest.txt'),(166,'gui','TravelRequestFrame','public void enableFieldsChangeStatus(','enableFieldsChangeStatus','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFrame.java',737,791,'enableFieldsChangeStatus.txt'),(167,'gui','TravelRequestFrame','public void changeStatus(','changeStatus','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/TravelRequestFrame.java',802,810,'changeStatus.txt'),(168,'gui','EmployeeFormPanel','public EmployeeFormPanel(','EmployeeFormPanel','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormPanel.java',89,426,'EmployeeFormPanel.txt'),(169,'gui','EmployeeFormPanel','public void setEmployeeFormListener(','setEmployeeFormListener','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormPanel.java',717,721,'setEmployeeFormListener.txt'),(170,'gui','EmployeeFormPanel','public void assignDefaultDates(','assignDefaultDates','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormPanel.java',773,803,'assignDefaultDates.txt'),(171,'gui','EmployeeFormPanel','public void assignEmployeeNumber(','assignEmployeeNumber','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormPanel.java',804,847,'assignEmployeeNumber.txt'),(172,'gui','EmployeeFormPanel','public void assignDefaultValues(','assignDefaultValues','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormPanel.java',848,860,'assignDefaultValues.txt'),(173,'gui','EmployeeFormPanel','public void loadSupervisors(','loadSupervisors','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormPanel.java',742,772,'loadSupervisors.txt'),(174,'gui','EmployeeFormPanel','public void layoutComponents(','layoutComponents','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormPanel.java',427,716,'layoutComponents.txt'),(175,'gui','EmployeeFormPanel','public void cleanFields(','cleanFields','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFormPanel.java',722,741,'cleanFields.txt'),(176,'gui','EmployeeFrame','public void loadingEmployees(','loadingEmployees','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFrame.java',545,560,'loadingEmployees.txt'),(177,'gui','EmployeeFrame','public void refreshEmployee(','refreshEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFrame.java',469,544,'refreshEmployee.txt'),(178,'gui','EmployeeFrame','public void disableFieldsEmployee(','disableFieldsEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFrame.java',561,603,'disableFieldsEmployee.txt'),(179,'gui','EmployeeFrame','public void enableFieldsEmployee(','enableFieldsEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFrame.java',604,646,'enableFieldsEmployee.txt'),(180,'gui','EmployeeFrame','public boolean validateDeleteEmployee1(','validateDeleteEmployee1','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFrame.java',647,663,'validateDeleteEmployee1.txt'),(181,'gui','EmployeeFrame','public boolean validateFullName(','validateFullName','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFrame.java',681,697,'validateFullName.txt'),(182,'gui','EmployeeFrame','public boolean validateDeleteEmployee2(','validateDeleteEmployee2','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/gui/EmployeeFrame.java',664,680,'validateDeleteEmployee2.txt'),(183,'utilites','PrintClassHierarchy','public PrintClassHierarchy(','PrintClassHierarchy','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/utilites/PrintClassHierarchy.java',20,24,'PrintClassHierarchy.txt'),(184,'utilites','PrintClassHierarchy','private void traverseClasses(','traverseClasses','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/utilites/PrintClassHierarchy.java',64,69,'traverseClasses.txt'),(185,'utilites','PrintClassHierarchy','private void traverseClasses(','traverseClasses','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/utilites/PrintClassHierarchy.java',70,100,'traverseClasses1.txt'),(186,'utilites','PrintClassHierarchy','private void printHierarchy(','printHierarchy','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/utilites/PrintClassHierarchy.java',31,63,'printHierarchy1.txt'),(187,'utilites','PrintClassHierarchy','public void printHierarchy(','printHierarchy','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/utilites/PrintClassHierarchy.java',25,30,'printHierarchy.txt'),(188,'model','Module','public Module(','Module','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Module.java',21,33,'Module.txt'),(189,'model','Module','public Module(','Module','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Module.java',34,43,'Module1.txt'),(190,'model','Module','public int getId(','getId','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Module.java',44,48,'getId.txt'),(191,'model','Module','public void setId(','setId','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Module.java',49,58,'setId.txt'),(192,'model','Module','public UserLevel getUserLevel(','getUserLevel','Class','model.UserLevel','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Module.java',74,78,'getUserLevel.txt'),(193,'model','Module','public void setModuleName(','setModuleName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Module.java',59,68,'setModuleName.txt'),(194,'model','Module','public void setSubModuleName(','setSubModuleName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Module.java',69,73,'setSubModuleName.txt'),(195,'model','Module','public void setUserLevel(','setUserLevel','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Module.java',79,89,'setUserLevel.txt'),(196,'model','UserDatabase','public UserDatabase(','UserDatabase','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',30,36,'UserDatabase.txt'),(197,'model','UserDatabase','public void load(','load','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',252,284,'load.txt'),(198,'model','UserDatabase','public void load(','load','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',639,643,'load1.txt'),(199,'model','UserDatabase','public void delete(','delete','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',220,251,'delete.txt'),(200,'model','UserDatabase','public void connect(','connect','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',82,106,'connect.txt'),(201,'model','UserDatabase','public void save(','save','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',126,219,'save.txt'),(202,'model','UserDatabase',' public ArrayList<Employee> loadFullNameEmployees(','loadFullNameEmployees','ArrayList','<model.Employee>','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',335,365,'loadFullNameEmployees.txt'),(203,'model','UserDatabase','public void disconnect(','disconnect','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',107,125,'disconnect.txt'),(204,'model','UserDatabase','public void configureDBParameters(','configureDBParameters','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',37,81,'configureDBParameters.txt'),(205,'model','UserDatabase','public UserLevel getUserLevel(','getUserLevel','Class','model.UserLevel','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',606,638,'getUserLevel.txt'),(206,'model','UserDatabase',' public List<User> getUsers(','getUsers','List','<model.User>','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',323,329,'getUsers.txt'),(207,'model','UserDatabase','public void addUser(','addUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',317,322,'addUser.txt'),(208,'model','UserDatabase','public void deleteUser(','deleteUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',330,334,'deleteUser.txt'),(209,'model','UserDatabase','public void loadUser(','loadUser','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',285,316,'loadUser.txt'),(210,'model','UserDatabase','public boolean validateEmployee(','validateEmployee','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',366,427,'validateEmployee.txt'),(211,'model','UserDatabase','public boolean validateUserName(','validateUserName','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',428,478,'validateUserName.txt'),(212,'model','UserDatabase','public boolean validateEmail(','validateEmail','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',479,529,'validateEmail.txt'),(213,'model','UserDatabase','public boolean validateDeleteUser(','validateDeleteUser','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',530,571,'validateDeleteUser.txt'),(214,'model','UserDatabase','public boolean validateUser(','validateUser','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/UserDatabase.java',572,605,'validateUser.txt'),(215,'model','User','public User(','User','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',24,41,'User.txt'),(216,'model','User','public User(','User','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',42,52,'User1.txt'),(217,'model','User','public User(','User','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',53,59,'User2.txt'),(218,'model','User','public int getId(','getId','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',60,63,'getId.txt'),(219,'model','User','public void setId(','setId','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',64,73,'setId.txt'),(220,'model','User','public void setEmployee(','setEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',99,106,'setEmployee.txt'),(221,'model','User','public void setUserLevel(','setUserLevel','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',115,124,'setUserLevel.txt'),(222,'model','User','public void setUserName(','setUserName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',74,81,'setUserName.txt'),(223,'model','User','public void setPassword(','setPassword','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',82,90,'setPassword.txt'),(224,'model','User','public void setconfirmPassword(','setconfirmPassword','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',91,98,'setconfirmPassword.txt'),(225,'model','User','public void setEmail(','setEmail','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/User.java',107,114,'setEmail.txt'),(226,'model','TravelRequest','public TravelRequest(','TravelRequest','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',33,58,'TravelRequest.txt'),(227,'model','TravelRequest','public TravelRequest(','TravelRequest','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',59,70,'TravelRequest1.txt'),(228,'model','TravelRequest','public int getId(','getId','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',71,75,'getId.txt'),(229,'model','TravelRequest','public Currency getCurrency(','getCurrency','Class','model.Currency','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',172,176,'getCurrency.txt'),(230,'model','TravelRequest','public void setCurrency(','setCurrency','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',177,181,'setCurrency.txt'),(231,'model','TravelRequest','public void setId(','setId','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',76,85,'setId.txt'),(232,'model','TravelRequest','public Status getStatus(','getStatus','Class','model.Status','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',193,197,'getStatus.txt'),(233,'model','TravelRequest','public Transportation getTransportation(','getTransportation','Class','model.Transportation','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',101,105,'getTransportation.txt'),(234,'model','TravelRequest','public float getTotalFunding(','getTotalFunding','float','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',182,186,'getTotalFunding.txt'),(235,'model','TravelRequest','public void setNumber(','setNumber','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',86,95,'setNumber.txt'),(236,'model','TravelRequest','public void setEmployee(','setEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',96,100,'setEmployee.txt'),(237,'model','TravelRequest','public void setTransportation(','setTransportation','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',106,115,'setTransportation.txt'),(238,'model','TravelRequest','public void setPurpose(','setPurpose','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',116,125,'setPurpose.txt'),(239,'model','TravelRequest','public void setTravelFrom(','setTravelFrom','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',126,135,'setTravelFrom.txt'),(240,'model','TravelRequest','public void setTravelTo(','setTravelTo','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',136,145,'setTravelTo.txt'),(241,'model','TravelRequest','public void setTravelDate(','setTravelDate','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',146,155,'setTravelDate.txt'),(242,'model','TravelRequest','public void setReturnDate(','setReturnDate','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',156,165,'setReturnDate.txt'),(243,'model','TravelRequest','public void setNotes(','setNotes','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',166,171,'setNotes.txt'),(244,'model','TravelRequest','public void setTotalFunding(','setTotalFunding','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',187,192,'setTotalFunding.txt'),(245,'model','TravelRequest','public void setStatus(','setStatus','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',198,207,'setStatus.txt'),(246,'model','TravelRequest','public void setStatusNotes(','setStatusNotes','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequest.java',208,218,'setStatusNotes.txt'),(247,'model','TravelRequestDatabase','public TravelRequestDatabase(','TravelRequestDatabase','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',35,41,'TravelRequestDatabase.txt'),(248,'model','TravelRequestDatabase','public void load(','load','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',367,475,'load.txt'),(249,'model','TravelRequestDatabase','public void delete(','delete','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',333,366,'delete.txt'),(250,'model','TravelRequestDatabase','public void connect(','connect','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',87,111,'connect.txt'),(251,'model','TravelRequestDatabase','public void save(','save','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',131,332,'save.txt'),(252,'model','TravelRequestDatabase','public void deleteTravelRequest(','deleteTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',532,536,'deleteTravelRequest.txt'),(253,'model','TravelRequestDatabase','public void loadTravelRequest(','loadTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',476,519,'loadTravelRequest.txt'),(254,'model','TravelRequestDatabase',' public ArrayList<Employee> loadFullNameEmployees(','loadFullNameEmployees','ArrayList','<model.Employee>','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',537,598,'loadFullNameEmployees.txt'),(255,'model','TravelRequestDatabase','public void disconnect(','disconnect','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',112,130,'disconnect.txt'),(256,'model','TravelRequestDatabase','public void configureDBParameters(','configureDBParameters','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',42,86,'configureDBParameters.txt'),(257,'model','TravelRequestDatabase','public int maxNumTravelRequest(','maxNumTravelRequest','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',599,617,'maxNumTravelRequest.txt'),(258,'model','TravelRequestDatabase',' public List<TravelRequest> getTravelRequests(','getTravelRequests','List','<model.TravelRequest>','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',526,531,'getTravelRequests.txt'),(259,'model','TravelRequestDatabase','public void addTravelRequest(','addTravelRequest','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/TravelRequestDatabase.java',520,525,'addTravelRequest.txt'),(260,'model','EmployeeDatabase','public EmployeeDatabase(','EmployeeDatabase','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',34,40,'EmployeeDatabase.txt'),(261,'model','EmployeeDatabase','public void load(','load','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',426,484,'load.txt'),(262,'model','EmployeeDatabase','public void delete(','delete','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',394,425,'delete.txt'),(263,'model','EmployeeDatabase','public void connect(','connect','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',86,110,'connect.txt'),(264,'model','EmployeeDatabase','public void save(','save','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',130,393,'save.txt'),(265,'model','EmployeeDatabase','public void disconnect(','disconnect','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',111,129,'disconnect.txt'),(266,'model','EmployeeDatabase','public void configureDBParameters(','configureDBParameters','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',41,85,'configureDBParameters.txt'),(267,'model','EmployeeDatabase',' public List<Employee> getEmployees(','getEmployees','List','<model.Employee>','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',540,545,'getEmployees.txt'),(268,'model','EmployeeDatabase','public void addEmployee(','addEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',534,539,'addEmployee.txt'),(269,'model','EmployeeDatabase','public void deleteEmployee(','deleteEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',546,550,'deleteEmployee.txt'),(270,'model','EmployeeDatabase','public void loadEmployee(','loadEmployee','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',485,533,'loadEmployee.txt'),(271,'model','EmployeeDatabase',' public ArrayList<Employee> loadSupervisors(','loadSupervisors','ArrayList','<model.Employee>','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',551,585,'loadSupervisors.txt'),(272,'model','EmployeeDatabase','public boolean validateDeleteEmployee1(','validateDeleteEmployee1','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',586,623,'validateDeleteEmployee1.txt'),(273,'model','EmployeeDatabase','public boolean validateFullName(','validateFullName','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',624,684,'validateFullName.txt'),(274,'model','EmployeeDatabase','public int maxNumEmployee(','maxNumEmployee','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',685,703,'maxNumEmployee.txt'),(275,'model','EmployeeDatabase','public boolean validateDeleteEmployee2(','validateDeleteEmployee2','boolean','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/EmployeeDatabase.java',704,733,'validateDeleteEmployee2.txt'),(276,'model','ModuleDatabase','public ModuleDatabase(','ModuleDatabase','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/ModuleDatabase.java',27,33,'ModuleDatabase.txt'),(277,'model','ModuleDatabase','public void connect(','connect','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/ModuleDatabase.java',79,103,'connect.txt'),(278,'model','ModuleDatabase','public void saveModules(','saveModules','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/ModuleDatabase.java',124,325,'saveModules.txt'),(279,'model','ModuleDatabase','public void disconnect(','disconnect','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/ModuleDatabase.java',104,123,'disconnect.txt'),(280,'model','ModuleDatabase','public void configureDBParameters(','configureDBParameters','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/ModuleDatabase.java',34,78,'configureDBParameters.txt'),(281,'model','Employee','public Employee(','Employee','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',34,59,'Employee.txt'),(282,'model','Employee','public Employee(','Employee','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',60,71,'Employee1.txt'),(283,'model','Employee','public Employee(','Employee','Constructor','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',72,78,'Employee2.txt'),(284,'model','Employee','public int getId(','getId','int','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',79,82,'getId.txt'),(285,'model','Employee','public void setId(','setId','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',83,90,'setId.txt'),(286,'model','Employee','public void setNumber(','setNumber','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',91,98,'setNumber.txt'),(287,'model','Employee','public void setFirstName(','setFirstName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',99,106,'setFirstName.txt'),(288,'model','Employee','public void setMiddleName(','setMiddleName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',107,113,'setMiddleName.txt'),(289,'model','Employee','public void setLastName(','setLastName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',114,121,'setLastName.txt'),(290,'model','Employee','public void setDob(','setDob','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',122,125,'setDob.txt'),(291,'model','Employee','public Gender getGender(','getGender','Class','model.Gender','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',126,129,'getGender.txt'),(292,'model','Employee','public void setGender(','setGender','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',130,133,'setGender.txt'),(293,'model','Employee','public MaritalStatus getMaritalStatus(','getMaritalStatus','Class','model.MaritalStatus','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',134,137,'getMaritalStatus.txt'),(294,'model','Employee','public void setMaritalStatus(','setMaritalStatus','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',138,145,'setMaritalStatus.txt'),(295,'model','Employee','public void setMobilePhone(','setMobilePhone','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',146,153,'setMobilePhone.txt'),(296,'model','Employee','public void setJoinedDate(','setJoinedDate','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',154,161,'setJoinedDate.txt'),(297,'model','Employee','public void setTerminatedDate(','setTerminatedDate','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',162,169,'setTerminatedDate.txt'),(298,'model','Employee','public void setJobTitle(','setJobTitle','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',170,177,'setJobTitle.txt'),(299,'model','Employee','public void setDepartment(','setDepartment','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',178,185,'setDepartment.txt'),(300,'model','Employee','public void setSupervisor(','setSupervisor','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',186,195,'setSupervisor.txt'),(301,'model','Employee','public void setFullName(','setFullName','void','','/Users/fannyriveraortiz/Desktop/Input/HRMApplication/src/model/Employee.java',196,206,'setFullName.txt');
/*!40000 ALTER TABLE `class_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `database_class`
--

DROP TABLE IF EXISTS `database_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `database_class` (
  `dc_id` int(11) NOT NULL COMMENT 'Database Class Identifier',
  `dc_name` varchar(500) NOT NULL COMMENT 'Database Class Name',
  PRIMARY KEY (`dc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `database_class`
--

LOCK TABLES `database_class` WRITE;
/*!40000 ALTER TABLE `database_class` DISABLE KEYS */;
INSERT INTO `database_class` VALUES (1,'Statement'),(2,'PreparedStatement'),(3,'CallableStatement');
/*!40000 ALTER TABLE `database_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `database_method`
--

DROP TABLE IF EXISTS `database_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `database_method` (
  `dm_id` int(11) NOT NULL COMMENT 'Database Method Identifier',
  `dm_name` varchar(500) NOT NULL COMMENT 'Database Method Name',
  `dm_dml_operations` varchar(1000) NOT NULL COMMENT 'Database Method Data Manipulation Language (DML) operations',
  PRIMARY KEY (`dm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `database_method`
--

LOCK TABLES `database_method` WRITE;
/*!40000 ALTER TABLE `database_method` DISABLE KEYS */;
INSERT INTO `database_method` VALUES (1,'execute','Select, Insert, Delete, Update'),(2,'executeQuery','Select'),(3,'executeUpdate','Insert, Delete, Update'),(4,'prepareCall','Select, Insert, Delete, Update, Stored Procedures');
/*!40000 ALTER TABLE `database_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `database_operation`
--

DROP TABLE IF EXISTS `database_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `database_operation` (
  `do_id` int(11) NOT NULL COMMENT 'Database Operations Identifier',
  `pl_id` int(11) NOT NULL COMMENT 'Programming Language Identifier',
  `dbms_id` int(11) NOT NULL COMMENT 'Database Management System Identifier',
  `dc_id` int(11) NOT NULL COMMENT 'Database Class Identifier',
  `dm_id` int(11) NOT NULL COMMENT 'Database Method Identifier',
  PRIMARY KEY (`do_id`),
  KEY `fk_pl_id_do_idx` (`pl_id`),
  KEY `fk_dbms_id_do_idx` (`dbms_id`),
  KEY `fk_dc_id_do_idx` (`dc_id`),
  KEY `fk_dm_id_do_idx` (`dm_id`),
  CONSTRAINT `fk_dbms_id_do` FOREIGN KEY (`dbms_id`) REFERENCES `dbms` (`dbms_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_dc_id_do` FOREIGN KEY (`dc_id`) REFERENCES `database_class` (`dc_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_dm_id_do` FOREIGN KEY (`dm_id`) REFERENCES `database_method` (`dm_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_pl_id_do` FOREIGN KEY (`pl_id`) REFERENCES `programming_language` (`pl_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `database_operation`
--

LOCK TABLES `database_operation` WRITE;
/*!40000 ALTER TABLE `database_operation` DISABLE KEYS */;
INSERT INTO `database_operation` VALUES (1,1,1,1,1),(2,1,1,1,2),(3,1,1,1,3),(4,1,1,2,1),(5,1,1,2,2),(6,1,1,2,3),(7,1,1,3,1),(8,1,1,3,2),(9,1,1,3,3),(10,1,1,3,4);
/*!40000 ALTER TABLE `database_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dbms`
--

DROP TABLE IF EXISTS `dbms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dbms` (
  `dbms_id` int(11) NOT NULL COMMENT 'Database Management identificator',
  `dbms_name` varchar(500) NOT NULL COMMENT 'Database Management name',
  PRIMARY KEY (`dbms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dbms`
--

LOCK TABLES `dbms` WRITE;
/*!40000 ALTER TABLE `dbms` DISABLE KEYS */;
INSERT INTO `dbms` VALUES (1,'MySQL');
/*!40000 ALTER TABLE `dbms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `error_inventory`
--

DROP TABLE IF EXISTS `error_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `error_inventory` (
  `pi_id` int(11) NOT NULL COMMENT 'Package Identifier',
  `ci_id` int(11) NOT NULL COMMENT 'Class Identifier',
  `mi_id` int(11) NOT NULL COMMENT 'Method Identifier',
  `ei_id` int(11) NOT NULL COMMENT 'Error Inventory Identifier',
  `ei_description` varchar(500) NOT NULL COMMENT 'Error Inventory Description',
  `ei_type` varchar(10) NOT NULL COMMENT 'Error Inventory Type',
  PRIMARY KEY (`pi_id`,`ci_id`,`mi_id`,`ei_id`),
  KEY `fk_ci_id_ei_idx` (`ci_id`),
  KEY `fk_mi_id_ei_idx` (`mi_id`),
  CONSTRAINT `fk_ci_id_ei` FOREIGN KEY (`ci_id`) REFERENCES `class_inventory` (`pi_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_mi_id_ei` FOREIGN KEY (`mi_id`) REFERENCES `method_inventory` (`pi_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_pi_id_ei` FOREIGN KEY (`pi_id`) REFERENCES `package_inventory` (`pi_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `error_inventory`
--

LOCK TABLES `error_inventory` WRITE;
/*!40000 ALTER TABLE `error_inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `error_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `graphical_user_interface`
--

DROP TABLE IF EXISTS `graphical_user_interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `graphical_user_interface` (
  `gui_id` int(11) NOT NULL COMMENT 'Graphical User Interface Class Identifier',
  `gui_name` varchar(500) NOT NULL COMMENT 'Graphical User Interface Class Name',
  `pl_id` int(11) NOT NULL COMMENT 'Programming Language Identifier',
  PRIMARY KEY (`gui_id`),
  KEY `fk_pl_id_gui_idx` (`pl_id`),
  CONSTRAINT `fk_pl_id_gui` FOREIGN KEY (`pl_id`) REFERENCES `programming_language` (`pl_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `graphical_user_interface`
--

LOCK TABLES `graphical_user_interface` WRITE;
/*!40000 ALTER TABLE `graphical_user_interface` DISABLE KEYS */;
INSERT INTO `graphical_user_interface` VALUES (1,'java.awt',1),(2,'javax.swing',1),(3,'org.eclipse.swt',1),(4,'javafx',1),(5,'org.apache.pivot.wtk',1),(6,'com.trolltech.qt.gui',1);
/*!40000 ALTER TABLE `graphical_user_interface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `input_method`
--

DROP TABLE IF EXISTS `input_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `input_method` (
  `im_id` int(11) NOT NULL COMMENT 'Input Method Identifier',
  `im_name` varchar(500) NOT NULL COMMENT 'Input Method Name',
  PRIMARY KEY (`im_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `input_method`
--

LOCK TABLES `input_method` WRITE;
/*!40000 ALTER TABLE `input_method` DISABLE KEYS */;
INSERT INTO `input_method` VALUES (1,'Database');
/*!40000 ALTER TABLE `input_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `method_inventory`
--

DROP TABLE IF EXISTS `method_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `method_inventory` (
  `pi_id` int(11) NOT NULL COMMENT 'Package Inventory Identifier',
  `ci_id` int(11) NOT NULL COMMENT 'Class Inventory Identifier',
  `mi_id` varchar(500) NOT NULL COMMENT 'Method Inventory Identifier',
  `mi_name` varchar(500) DEFAULT NULL COMMENT 'Method Inventory Name',
  `mi_type` varchar(100) DEFAULT NULL COMMENT 'Method Inventory Type',
  KEY `fk_pi_id_mi_idx` (`pi_id`),
  KEY `fk_ci_id_mi_idx` (`ci_id`),
  CONSTRAINT `fk_ci_id_mi` FOREIGN KEY (`ci_id`) REFERENCES `class_inventory` (`pi_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_pi_id_mi` FOREIGN KEY (`pi_id`) REFERENCES `package_inventory` (`pi_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `method_inventory`
--

LOCK TABLES `method_inventory` WRITE;
/*!40000 ALTER TABLE `method_inventory` DISABLE KEYS */;
INSERT INTO `method_inventory` VALUES (1,1,'1','Configuration','Constructor'),(1,1,'2','configureParameters','Method'),(1,2,'1','JavaClasses','Constructor'),(1,2,'10','getClassMethodsEndLInes','Method'),(1,2,'11','getMethodStartLineNumbers','Method'),(1,2,'12','verifyProjectException','Method'),(1,2,'13','getFinalMethodName','Method'),(1,2,'14','getFinalConstructorShortName','Method'),(1,2,'15','getClassConstructorsFullMethodsNames','Method'),(1,2,'16','getClassMethodsStartLines','Method'),(1,2,'17','getFilesMethodsBody','Method'),(1,2,'2','readJavaFiles','Method'),(1,2,'3','getMethodFile','Method'),(1,2,'4','createDirectory','Method'),(1,2,'5','deleteDirectory','Method'),(1,2,'6','getFileMethodBody','Method'),(1,2,'7','getClassMethodsBody','Method'),(1,2,'8','getMethodLastLineNumber','Method'),(1,2,'9','getClassMethodsZeroEndLines','Method'),(3,1,'1','Database','Constructor'),(3,1,'10','getShortMethodName','Method'),(3,1,'11','getDBClassesMethods','Method'),(3,1,'2','loadDbMethods','Method'),(3,1,'3','getVariable','Method'),(3,1,'4','getDBMethodLines','Method'),(3,1,'5','getDBClass','Method'),(3,1,'6','getVariableLines','Method'),(3,1,'7','validateDbOperations','Method'),(3,1,'8','validateDatabaseMethod','Method'),(3,1,'9','readMethodBodyFiles','Method'),(4,1,'1','ForensicReadyLogger','Constructor'),(4,1,'2','exitApplicationError','Method'),(4,1,'3','main','Method'),(5,2,'1','ClassMethodDatabase','Constructor'),(5,2,'10','updateTextFileName','Method'),(5,2,'11','loadShortMethodName','Method'),(5,2,'12','addClassMethodDetails','Method'),(5,2,'2','configureDBParameters','Method'),(5,2,'3','connect','Method'),(5,2,'4','disconnect','Method'),(5,2,'5','deleteClassMethod','Method'),(5,2,'6','saveClassMethod','Method'),(5,2,'7','load','Method'),(5,2,'8','updateEndLineNumbers','Method'),(5,2,'9','loadZeroEndLineNumbers','Method'),(5,5,'1','DatabaseOperationsDatabase','Constructor'),(5,5,'2','configureDBParameters','Method'),(5,5,'3','connect','Method'),(5,5,'4','disconnect','Method'),(5,5,'5','loadDatabaseMethods','Method'),(5,5,'6','loadDatabaseOperations','Method');
/*!40000 ALTER TABLE `method_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_inventory`
--

DROP TABLE IF EXISTS `package_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `package_inventory` (
  `pi_id` int(11) NOT NULL COMMENT 'Package Inventory Identifier',
  `pi_name` varchar(500) NOT NULL COMMENT 'Package Inventory Name',
  PRIMARY KEY (`pi_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_inventory`
--

LOCK TABLES `package_inventory` WRITE;
/*!40000 ALTER TABLE `package_inventory` DISABLE KEYS */;
INSERT INTO `package_inventory` VALUES (1,'frl.configuration'),(2,'frl.controller'),(3,'frl.inputmethods'),(4,'frl.main'),(5,'frl.model');
/*!40000 ALTER TABLE `package_inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `programming_language`
--

DROP TABLE IF EXISTS `programming_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `programming_language` (
  `pl_id` int(11) NOT NULL COMMENT 'Programming Language Identification',
  `pl_name` varchar(500) NOT NULL COMMENT 'Programming Language Name',
  PRIMARY KEY (`pl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programming_language`
--

LOCK TABLES `programming_language` WRITE;
/*!40000 ALTER TABLE `programming_language` DISABLE KEYS */;
INSERT INTO `programming_language` VALUES (1,'Java');
/*!40000 ALTER TABLE `programming_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return_type`
--

DROP TABLE IF EXISTS `return_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `return_type` (
  `rt_id` int(11) NOT NULL COMMENT 'Return Type Identifier',
  `rt_name` varchar(500) NOT NULL COMMENT 'Return Type Name',
  `rt_type` enum('Primitive','NonPrimitive') NOT NULL COMMENT 'Return Type Classification (Primitive, NonPrimitive)',
  `pl_id` int(11) NOT NULL COMMENT 'Programming Language Identifier',
  `rt_library` varchar(1000) DEFAULT 'None' COMMENT 'Return Type Library',
  PRIMARY KEY (`rt_id`),
  KEY `fk_pl_id_rt_idx` (`pl_id`),
  CONSTRAINT `fk_pl_id_rt` FOREIGN KEY (`pl_id`) REFERENCES `programming_language` (`pl_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return_type`
--

LOCK TABLES `return_type` WRITE;
/*!40000 ALTER TABLE `return_type` DISABLE KEYS */;
INSERT INTO `return_type` VALUES (1,'boolean','Primitive',1,'None'),(2,'char','Primitive',1,'None'),(3,'byte','Primitive',1,'None'),(4,'short','Primitive',1,'None'),(5,'int','Primitive',1,'None'),(6,'long','Primitive',1,'None'),(7,'float','Primitive',1,'None'),(8,'double','Primitive',1,'None'),(9,'String','NonPrimitive',1,'import java.lang.String;'),(10,'Class','NonPrimitive',1,'None'),(11,'ArrayList','NonPrimitive',1,'import java.util.ArrayList;'),(12,'List','NonPrimitive',1,'import java.util.List;'),(13,'Array','NonPrimitive',1,'import java.util.Arrays;'),(14,'Interface','NonPrimitive',1,'None'),(15,'void','NonPrimitive',1,'None'),(16,'Constructor','NonPrimitive',1,'None');
/*!40000 ALTER TABLE `return_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_file_details`
--

DROP TABLE IF EXISTS `text_file_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `text_file_details` (
  `tfh_id` int(11) NOT NULL COMMENT 'Text File Header Identifier',
  `tfd_line_number` int(11) NOT NULL COMMENT 'Text File Details Line Number',
  `tfd_text` varchar(5000) NOT NULL COMMENT 'Text File Details Line Text',
  `tfd_type` enum('Personalized','Fixed') NOT NULL COMMENT 'Text File Details Type',
  `tfp_id` int(11) DEFAULT NULL COMMENT 'Text File Properties Identifier',
  PRIMARY KEY (`tfd_line_number`,`tfh_id`),
  KEY `fk_tfh_id_tfd_idx` (`tfh_id`),
  CONSTRAINT `fk_tfh_id_tfd` FOREIGN KEY (`tfh_id`) REFERENCES `text_file_header` (`tfh_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_file_details`
--

LOCK TABLES `text_file_details` WRITE;
/*!40000 ALTER TABLE `text_file_details` DISABLE KEYS */;
INSERT INTO `text_file_details` VALUES (1,1,'package configuration_property_1;','Personalized',1),(2,1,'package configuration_property_1;','Personalized',1),(1,2,'','Fixed',NULL),(2,2,'','Fixed',NULL),(1,3,'import java.io.BufferedWriter;','Fixed',NULL),(2,3,'import java.io.BufferedWriter;','Fixed',NULL),(1,4,'import java.io.File;','Fixed',NULL),(2,4,'import java.io.File;','Fixed',NULL),(1,5,'import java.io.FileWriter;','Fixed',NULL),(2,5,'import java.io.FileWriter;','Fixed',NULL),(1,6,'import java.util.Arrays;','Fixed',NULL),(2,6,'import java.io.IOException;','Fixed',NULL),(1,7,'import org.aspectj.lang.Signature;','Fixed',NULL),(2,7,'import java.util.List;','Fixed',NULL),(1,8,'import configuration_property_1;','Personalized',2),(2,8,'','Fixed',NULL),(1,9,'','Fixed',NULL),(2,9,'import net.sourceforge.plantuml.GeneratedImage;','Fixed',NULL),(1,10,'public aspect configuration_property_1','Personalized',3),(2,10,'import net.sourceforge.plantuml.SourceFileReader;','Fixed',NULL),(1,11,'{','Fixed',NULL),(2,11,'','Fixed',NULL),(1,12,'   String configuration_property_1;','Personalized',4),(2,12,'public aspect configuration_property_1','Personalized',2),(1,13,'   String configuration_property_1;','Personalized',5),(2,13,'{','Fixed',NULL),(1,14,'   String configuration_property_1;','Personalized',6),(2,14,'   String configuration_property_1;','Personalized',3),(1,15,'   String configuration_property_1;','Personalized',7),(2,15,'   String configuration_property_1;','Personalized',4),(1,16,'   String configuration_property_1;','Personalized',8),(2,16,'   String configuration_property_1;','Personalized',5),(1,17,'   String configuration_property_1;','Personalized',9),(2,17,'   ','Fixed',NULL),(1,18,'   String configuration_property_1;','Personalized',10),(2,18,'   String configuration_property_1;','Personalized',6),(1,19,'   String configuration_property_1;','Personalized',11),(2,19,'   String configuration_property_1;','Personalized',7),(1,20,'   String configuration_property_1;','Personalized',12),(2,20,'   String configuration_property_1;','Personalized',8),(1,21,'   String configuration_property_1;','Personalized',13),(2,21,'','Fixed',NULL),(1,22,'   ','Fixed',NULL),(2,22,'   String umlSeqDiagTextFile=projectOutputDir + umlSeqDiagTextFileName1;','Fixed',NULL),(1,23,'   String configuration_property_1;','Personalized',14),(2,23,'   String umlSeqDiagPngFile=projectOutputDir  + umlSeqDiagPngFileName1;','Fixed',NULL),(1,24,'   String configuration_property_1;','Personalized',15),(2,24,'   String method, errorMessage, content=\"\";','Fixed',NULL),(1,25,'   String configuration_property_1;','Personalized',16),(2,25,'   ','Fixed',NULL),(1,26,'   String configuration_property_1;','Personalized',17),(2,26,'   pointcut configuration_property_1(): ','Personalized',9),(1,27,'   String configuration_property_1;','Personalized',18),(2,27,'   	                      execution(configuration_property_1);','Personalized',10),(1,28,'   String configuration_property_1;','Personalized',19),(2,28,'','Fixed',NULL),(1,29,'   String configuration_property_1;','Personalized',20),(2,29,'   after(): configuration_property_1()','Personalized',9),(1,30,'   String configuration_property_1;','Personalized',21),(2,30,'   {','Fixed',NULL),(1,31,'','Fixed',NULL),(2,31,'	  System.out.println(\"Message FRL: Welcome to the Forensic-Ready Logger Tool ...\");','Fixed',NULL),(1,32,'   String configuration_property_1;','Personalized',22),(2,32,'	   ','Fixed',NULL),(1,33,'   String configuration_property_1;','Personalized',23),(2,33,'	  // Delete UML Sequence Diagram previous Files','Fixed',NULL),(1,34,'   String configuration_property_1;','Personalized',24),(2,34,'	  deleteFiles();','Fixed',NULL),(1,35,'   String configuration_property_1;','Personalized',25),(2,35,'','Fixed',NULL),(1,36,'   String configuration_property_1;','Personalized',26),(2,36,'	  // Create a new UML Sequence Diagram Text File','Fixed',NULL),(1,37,'   String configuration_property_1;','Personalized',27),(2,37,'	  createSeqDiagramTextFile(umlSeqDiagTextFile);','Fixed',NULL),(1,38,'   String configuration_property_1;','Personalized',28),(2,38,'   }','Fixed',NULL),(1,39,'   String configuration_property_1;','Personalized',29),(2,39,'   ','Fixed',NULL),(1,40,'   ','Fixed',NULL),(2,40,'   pointcut configuration_property_1(): ','Personalized',11),(1,41,'   String umlSeqDiagTextFile=projectOutputDir + umlSeqDiagTextFileName1;','Fixed',NULL),(2,41,'   	                      call(configuration_property_1);','Personalized',12),(1,42,'   String callerClass=\"\", calleeClass=\"\", callerClassLine, calleeClassLine;','Fixed',NULL),(2,42,'','Fixed',NULL),(1,43,'   String packageMethod=\"\", classMethod=\"\";','Fixed',NULL),(2,43,'   before(): configuration_property_1()','Personalized',11),(1,44,'   String nameMethod=\"\", fullMethod=\"\", callerSuperClass=\"\", calleeSuperClass=\"\";','Fixed',NULL),(2,44,'   {','Fixed',NULL),(1,45,'   String callerType=\"\", calleeType=\"\", errorMessage=\"\", note=\"\";','Fixed',NULL),(2,45,'      // Create and update the UML Sequence Diagram Files ','Fixed',NULL),(1,46,'   String line=\"\", content=\"\", currentUser=\"\";','Fixed',NULL),(2,46,'      updateFiles();','Fixed',NULL),(1,47,'   int actorOrder=1, partOrder=1, methodsCounter=0, userCounter=0;','Fixed',NULL),(2,47,'   }','Fixed',NULL),(1,48,'   boolean callerGuiFlag= false, calleeGuiFlag=false;','Fixed',NULL),(2,48,'   ','Fixed',NULL),(1,49,'   Class<?> callerClassObj1, callerClassObj2, calleeClassObj1, calleeClassObj2; ','Fixed',NULL),(2,49,'   public void deleteSeqDiagramFile(String inputFile)','Fixed',NULL),(1,50,'   Signature method;','Fixed',NULL),(2,50,'   {','Fixed',NULL),(1,51,'   Object[] methodArgs;','Fixed',NULL),(2,51,'      File file = new File(inputFile);','Fixed',NULL),(1,52,'','Fixed',NULL),(2,52,'	   ','Fixed',NULL),(1,53,'   pointcut configuration_property_1(): ','Personalized',30),(2,53,'      // Delete an existing UML Sequence Diagram File','Fixed',NULL),(1,54,'   	                      call(configuration_property_1);','Personalized',31),(2,54,'	  if(file.exists() && !file.isDirectory()) ','Fixed',NULL),(1,55,'','Fixed',NULL),(2,55,'	     file.delete();','Fixed',NULL),(1,56,'   before(): configuration_property_1()','Personalized',30),(2,56,'	  ','Fixed',NULL),(1,57,'   {','Fixed',NULL),(2,57,'   }','Fixed',NULL),(1,58,'','Fixed',NULL),(2,58,'   ','Fixed',NULL),(1,59,'      // Get the Caller Class Details','Fixed',NULL),(2,59,'   public void deleteFiles()','Fixed',NULL),(1,60,'      callerClassObj1 = thisEnclosingJoinPointStaticPart.getSignature().getDeclaringType();','Fixed',NULL),(2,60,'   {','Fixed',NULL),(1,61,'      getCallerClassDetails(callerClassObj1);','Fixed',NULL),(2,61,'      // Delete UML Sequence Diagram Text File','Fixed',NULL),(1,62,'','Fixed',NULL),(2,62,'	  deleteSeqDiagramFile(umlSeqDiagTextFile);','Fixed',NULL),(1,63,'      // Get the Callee Class Details','Fixed',NULL),(2,63,'		  ','Fixed',NULL),(1,64,'      calleeClassObj1 = thisJoinPoint.getTarget().getClass();','Fixed',NULL),(2,64,'	  // Delete UML Sequence Diagram Png File','Fixed',NULL),(1,65,'      getCalleeClassDetails(calleeClassObj1);','Fixed',NULL),(2,65,'      deleteSeqDiagramFile(umlSeqDiagPngFile); ','Fixed',NULL),(1,66,'','Fixed',NULL),(2,66,'      ','Fixed',NULL),(1,67,'      // Get the details of the Method being executed','Fixed',NULL),(2,67,'   }','Fixed',NULL),(1,68,'      method        = thisJoinPoint.getSignature();','Fixed',NULL),(2,68,'   ','Fixed',NULL),(1,69,'      packageMethod = method.getDeclaringType().getPackageName();','Fixed',NULL),(2,69,'   public void writeTextFile(String textFile, String content)','Fixed',NULL),(1,70,'      classMethod   = method.getDeclaringType().getSimpleName();','Fixed',NULL),(2,70,'   {','Fixed',NULL),(1,71,'      nameMethod    = method.getName();','Fixed',NULL),(2,71,'      FileWriter fw = null;','Fixed',NULL),(1,72,'      methodArgs    = thisJoinPoint.getArgs();','Fixed',NULL),(2,72,'      BufferedWriter bw;','Fixed',NULL),(1,73,'      fullMethod	= nameMethod + startParameters;','Fixed',NULL),(2,73,'      File file;','Fixed',NULL),(1,74,'      ','Fixed',NULL),(2,74,'','Fixed',NULL),(1,75,'      // Validate if the CallerClass is a Graphical User Interface Class','Fixed',NULL),(2,75,'      try','Fixed',NULL),(1,76,'      // Build the Caller Class Line','Fixed',NULL),(2,76,'      {','Fixed',NULL),(1,77,'      if(callerGuiFlag == true)','Fixed',NULL),(2,77,'         // Create a new textFile','Fixed',NULL),(1,78,'      {	  ','Fixed',NULL),(2,78,'         file = new File(textFile);','Fixed',NULL),(1,79,'    	 // Build the method line ','Fixed',NULL),(2,79,'','Fixed',NULL),(1,80,'         fullMethod = fullMethod + endParameters;','Fixed',NULL),(2,80,'         // Validate if the textFile exists','Fixed',NULL),(1,81,'         ','Fixed',NULL),(2,81,'         if (!file.exists()) ','Fixed',NULL),(1,82,'         // Build the note line','Fixed',NULL),(2,82,'         {','Fixed',NULL),(1,83,'         note = startNote   + whiteSpaceWordsDelimiter1 + currentUser + whiteSpaceWordsDelimiter1 + colorDelimiter + colorNote + ','Fixed',NULL),(2,83,'            file.createNewFile();','Fixed',NULL),(1,84,'                whiteSpaceWordsDelimiter1 + newLine1 + callerClass + ','Fixed',NULL),(2,84,'            fw = new FileWriter(file.getAbsoluteFile());','Fixed',NULL),(1,85,'                newLine1 + endNote + newLine1;','Fixed',NULL),(2,85,'         }','Fixed',NULL),(1,86,'         ','Fixed',NULL),(2,86,'         else','Fixed',NULL),(1,87,'         // Assign the value to the caller Class','Fixed',NULL),(2,87,'            fw = new FileWriter(file, true);','Fixed',NULL),(1,88,'         callerClass = currentUser;','Fixed',NULL),(2,88,'         ','Fixed',NULL),(1,89,'         ','Fixed',NULL),(2,89,'         bw = new BufferedWriter(fw);','Fixed',NULL),(1,90,'         // Build the caller Class Line','Fixed',NULL),(2,90,'','Fixed',NULL),(1,91,'         callerClassLine = member1 + whiteSpaceWordsDelimiter1  + callerClass + ','Fixed',NULL),(2,91,'         // Write in the textFile','Fixed',NULL),(1,92,'        		           whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + actorOrder;','Fixed',NULL),(2,92,'         fw.append(content);','Fixed',NULL),(1,93,'      }   ','Fixed',NULL),(2,93,'','Fixed',NULL),(1,94,'      else','Fixed',NULL),(2,94,'         // Close the textFile','Fixed',NULL),(1,95,'      {	  ','Fixed',NULL),(2,95,'         bw.close();','Fixed',NULL),(1,96,'    	 // Build the method line  ','Fixed',NULL),(2,96,'         ','Fixed',NULL),(1,97,'         fullMethod = fullMethod + Arrays.toString(methodArgs) + endParameters;','Fixed',NULL),(2,97,'      }','Fixed',NULL),(1,98,'         ','Fixed',NULL),(2,98,'      catch(Exception e)','Fixed',NULL),(1,99,'         // The note line will be empty','Fixed',NULL),(2,99,'      {','Fixed',NULL),(1,100,'         note = \"\";','Fixed',NULL),(2,100,'         errorMessage = e.getMessage();','Fixed',NULL),(1,101,'         ','Fixed',NULL),(2,101,'         System.out.println(\"Error FRL: Occurred while opening the UML Sequence Diagram Text File: \"+umlSeqDiagTextFile + \"Error Message: \" + errorMessage);','Fixed',NULL),(1,102,'         // Increase the participants Order Counter','Fixed',NULL),(2,102,'      }','Fixed',NULL),(1,103,'         partOrder++;','Fixed',NULL),(2,103,'            ','Fixed',NULL),(1,104,'         ','Fixed',NULL),(2,104,'   }  ','Fixed',NULL),(1,105,'         // Build the caller Class Line','Fixed',NULL),(2,105,'   ','Fixed',NULL),(1,106,'         callerClassLine = member2 + whiteSpaceWordsDelimiter1 + callerClass + ','Fixed',NULL),(2,106,'   public void createSeqDiagramTextFile(String textFile)','Fixed',NULL),(1,107,'        		           whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;','Fixed',NULL),(2,107,'   {','Fixed',NULL),(1,108,'      }   ','Fixed',NULL),(2,108,' ','Fixed',NULL),(1,109,'      ','Fixed',NULL),(2,109,'      content = \"\";','Fixed',NULL),(1,110,'      partOrder++;','Fixed',NULL),(2,110,'','Fixed',NULL),(1,111,'      ','Fixed',NULL),(2,111,'      // Plant UML Instructions','Fixed',NULL),(1,112,'      // Build the callee Class Line','Fixed',NULL),(2,112,'      content = startUMLSeqDiagram + newLine1;','Fixed',NULL),(1,113,'      calleeClassLine = member2 + whiteSpaceWordsDelimiter1 + calleeClass + ','Fixed',NULL),(2,113,'      content = content + \"skinparam backgroundColor #EEEBDC\"   + newLine1;','Fixed',NULL),(1,114,'    		            whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;','Fixed',NULL),(2,114,'      content = content + \"skinparam sequence {\"                + newLine1;','Fixed',NULL),(1,115,'    		  ','Fixed',NULL),(2,115,'      content = content + \"ArrowColor DarkBlue\"                 + newLine1;','Fixed',NULL),(1,116,'      // Generate the Plant UML line to generate the UML Sequence Diagram Text File','Fixed',NULL),(2,116,'      content = content + \"ActorBorderColor DarkBlue\"           + newLine1;','Fixed',NULL),(1,117,'      updateSeqDiagTextFile(callerClass, calleeClass, fullMethod, note, callerClassLine, calleeClassLine);','Fixed',NULL),(2,117,'      content = content + \"LifeLineBorderColor blue\"            + newLine1;','Fixed',NULL),(1,118,'','Fixed',NULL),(2,118,'      content = content + \"LifeLineBackgroundColor DarkBlue\"    + newLine1;','Fixed',NULL),(1,119,'   } ','Fixed',NULL),(2,119,'      content = content + \"ParticipantBorderColor DarkBlue\"     + newLine1;','Fixed',NULL),(1,120,'   ','Fixed',NULL),(2,120,'      content = content + \"ParticipantBackgroundColor DarkBlue\" + newLine1;','Fixed',NULL),(1,121,'   public void updateSeqDiagTextFile(String inputCallerClass, String inputCalleeClass, ','Fixed',NULL),(2,121,'      content = content + \"ParticipantFontName Impact\"          + newLine1;','Fixed',NULL),(1,122,'		                             String inputMethod, String inputNote, String inputCallerClassLine,','Fixed',NULL),(2,122,'      content = content + \"ParticipantFontSize 17\"              + newLine1;','Fixed',NULL),(1,123,'		                             String inputCalleeClassLine)','Fixed',NULL),(2,123,'      content = content + \"ParticipantFontColor #A9DCDF\"        + newLine1;','Fixed',NULL),(1,124,'   {','Fixed',NULL),(2,124,'      content = content + \"ActorBackgroundColor DarkBlue\"       + newLine1;','Fixed',NULL),(1,125,'	   ','Fixed',NULL),(2,125,'      content = content + \"ActorFontColor DarkBlue\"             + newLine1;','Fixed',NULL),(1,126,'      line    = \"\";','Fixed',NULL),(2,126,'      content = content + \"ActorFontSize 17\"                    + newLine1;','Fixed',NULL),(1,127,'      content = \"\";','Fixed',NULL),(2,127,'      content = content + \"ActorFontName Aapex\"                 + newLine1;','Fixed',NULL),(1,128,'      ','Fixed',NULL),(2,128,'      content = content + \"}\"                                   + newLine1;','Fixed',NULL),(1,129,'      // Generating the content of the Text File which contains Plant UML instructions','Fixed',NULL),(2,129,'         ','Fixed',NULL),(1,130,'      // to generate the UML Sequence Diagram','Fixed',NULL),(2,130,'      content = String.format(content);','Fixed',NULL),(1,131,'      ','Fixed',NULL),(2,131,'      ','Fixed',NULL),(1,132,'      line    = inputCallerClassLine + newLine1;','Fixed',NULL),(2,132,'      // Write the Plant Uml Lines into the UML Sequence Diagram Text File','Fixed',NULL),(1,133,'      content = content + line;','Fixed',NULL),(2,133,'	  writeTextFile(textFile, content);','Fixed',NULL),(1,134,'      ','Fixed',NULL),(2,134,' ','Fixed',NULL),(1,135,'      line    = inputCalleeClassLine + newLine1;','Fixed',NULL),(2,135,'   }','Fixed',NULL),(1,136,'      content = content + line;','Fixed',NULL),(2,136,'','Fixed',NULL),(1,137,'      ','Fixed',NULL),(2,137,'   public void createSeqDiagramPngFile(String textFile)','Fixed',NULL),(1,138,'      line    = inputNote;','Fixed',NULL),(2,138,'   {','Fixed',NULL),(1,139,'      content = content + line;','Fixed',NULL),(2,139,'      File file1 = new File(textFile); ','Fixed',NULL),(1,140,'      ','Fixed',NULL),(2,140,'	  SourceFileReader reader = null;','Fixed',NULL),(1,141,'      // Increase the methods Counter','Fixed',NULL),(2,141,'	  List<GeneratedImage> list = null;','Fixed',NULL),(1,142,'      methodsCounter ++;','Fixed',NULL),(2,142,'	  ','Fixed',NULL),(1,143,'      ','Fixed',NULL),(2,143,'	  // Create the Reader of the UML Sequence Diagram Text File','Fixed',NULL),(1,144,'      line = inputCallerClass + whiteSpaceWordsDelimiter1 + startSendMessage + ','Fixed',NULL),(2,144,'	  try ','Fixed',NULL),(1,145,'    		 inputCalleeClass + endSendMessage            + whiteSpaceWordsDelimiter1 + ','Fixed',NULL),(2,145,'	  {','Fixed',NULL),(1,146,'    		 methodsCounter   + objectOrientedDelimiter1  + inputMethod + newLine1;','Fixed',NULL),(2,146,'	     reader = new SourceFileReader(file1);','Fixed',NULL),(1,147,'      content = content + line;     ','Fixed',NULL),(2,147,'	  } ','Fixed',NULL),(1,148,'      ','Fixed',NULL),(2,148,'	  catch (IOException e) ','Fixed',NULL),(1,149,'      line    = space + newLine1;','Fixed',NULL),(2,149,'	  {','Fixed',NULL),(1,150,'      content = content + line;','Fixed',NULL),(2,150,'	     errorMessage = e.getMessage();','Fixed',NULL),(1,151,'      ','Fixed',NULL),(2,151,'	     System.out.println(\"Error FRL: Occurred while reading the UML Sequence Diagram Text File: \"+textFile + \"Error Message: \" + errorMessage);','Fixed',NULL),(1,152,'      content = String.format(content);','Fixed',NULL),(2,152,'	  }','Fixed',NULL),(1,153,'','Fixed',NULL),(2,153,'	   ','Fixed',NULL),(1,154,'      // Write the Plant Uml Line into the UML Sequence Diagram Text File','Fixed',NULL),(2,154,'	  // Generate the list of images using the Sequence Diagram Text File','Fixed',NULL),(1,155,'      writeTextFile(umlSeqDiagTextFile, content);   ','Fixed',NULL),(2,155,'	  try ','Fixed',NULL),(1,156,'','Fixed',NULL),(2,156,'	  {','Fixed',NULL),(1,157,'   } ','Fixed',NULL),(2,157,'	     list = reader.getGeneratedImages();','Fixed',NULL),(1,158,'   ','Fixed',NULL),(2,158,'	  } ','Fixed',NULL),(1,159,'   pointcut configuration_property_1(): ','Personalized',32),(2,159,'	  catch (IOException e) ','Fixed',NULL),(1,160,'   	                      execution(configuration_property_1);','Personalized',33),(2,160,'	  {','Fixed',NULL),(1,161,'','Fixed',NULL),(2,161,'	     errorMessage = e.getMessage();','Fixed',NULL),(1,162,'   before(): configuration_property_1()','Personalized',32),(2,162,'		 System.out.println(\"Error FRL: Occurred while generating the UML Sequence Diagram Png File\" + \"Error Message: \" + errorMessage);','Fixed',NULL),(1,163,'   {','Fixed',NULL),(2,163,'	  }','Fixed',NULL),(1,164,'   	   ','Fixed',NULL),(2,164,'	   ','Fixed',NULL),(1,165,'	  line    = \"\";','Fixed',NULL),(2,165,'	  // Generated the UML Sequence Diagram Png File','Fixed',NULL),(1,166,'	  content = \"\";','Fixed',NULL),(2,166,'	  file1 = list.get(0).getPngFile();','Fixed',NULL),(1,167,'	      ','Fixed',NULL),(2,167,' 	   ','Fixed',NULL),(1,168,'      // Add a division in the UML Sequence Diagram Text File  ','Fixed',NULL),(2,168,'   }','Fixed',NULL),(1,169,'	  line = startDivision + whiteSpaceWordsDelimiter1 + ','Fixed',NULL),(2,169,'   ','Fixed',NULL),(1,170,'	         projectName   + whiteSpaceWordsDelimiter1 + ','Fixed',NULL),(2,170,'   public void updateFiles()','Fixed',NULL),(1,171,'	    	 endDivision   + newLine1;','Fixed',NULL),(2,171,'   {','Fixed',NULL),(1,172,'	  ','Fixed',NULL),(2,172,'	   ','Fixed',NULL),(1,173,'      content = content + line;','Fixed',NULL),(2,173,'	  // Update the UML Sequence Diagram Text File with the @endUml Instruction  ','Fixed',NULL),(1,174,'      ','Fixed',NULL),(2,174,'      content = endUMLSeqDiagram + newLine1;','Fixed',NULL),(1,175,'      content = String.format(content);','Fixed',NULL),(2,175,'      content = String.format(content);','Fixed',NULL),(1,176,'      ','Fixed',NULL),(2,176,'      ','Fixed',NULL),(1,177,'      // Write this division into the UML Sequence Diagram Text File','Fixed',NULL),(2,177,'      // Write the contents into the UML Sequence Diagram Text File','Fixed',NULL),(1,178,'	  writeTextFile(umlSeqDiagTextFile, content);','Fixed',NULL),(2,178,'      writeTextFile(umlSeqDiagTextFile, content);','Fixed',NULL),(1,179,'	  ','Fixed',NULL),(2,179,'      ','Fixed',NULL),(1,180,'	  // Initialize the methods counter','Fixed',NULL),(2,180,'      // Create the UML Sequence Diagram Png File','Fixed',NULL),(1,181,'	  methodsCounter = 0;','Fixed',NULL),(2,181,'   	  createSeqDiagramPngFile(umlSeqDiagTextFile);','Fixed',NULL),(1,182,'	  ','Fixed',NULL),(2,182,'	      ','Fixed',NULL),(1,183,'	  // Increase the users counters','Fixed',NULL),(2,183,'  	  // Print on the screen Informative Messages','Fixed',NULL),(1,184,'	  userCounter ++;','Fixed',NULL),(2,184,'	  System.out.println(\"Message FRL: UML Sequence Diagram Text File created: \"+umlSeqDiagTextFile);','Fixed',NULL),(1,185,'	  ','Fixed',NULL),(2,185,'	  System.out.println(\"Message FRL: UML Sequence Diagram PNG File created : \"+umlSeqDiagPngFile);','Fixed',NULL),(1,186,'	  currentUser = userName + validSpecialCharacter + userCounter;','Fixed',NULL),(2,186,'      System.out.println(\"Message FRL: Good Bye to the Forensic-Ready Logger Tool.\");','Fixed',NULL),(1,187,'	  ','Fixed',NULL),(2,187,'	  ','Fixed',NULL),(1,188,'   }  ','Fixed',NULL),(2,188,'   }  ','Fixed',NULL),(1,189,'     ','Fixed',NULL),(2,189,'   ','Fixed',NULL),(1,190,'   public void writeTextFile(String textfile, String content)','Fixed',NULL),(2,190,'}','Fixed',NULL),(1,191,'   {','Fixed',NULL),(1,192,'      FileWriter fw = null;','Fixed',NULL),(1,193,'      BufferedWriter bw;','Fixed',NULL),(1,194,'      File file;','Fixed',NULL),(1,195,'','Fixed',NULL),(1,196,'      try','Fixed',NULL),(1,197,'      {','Fixed',NULL),(1,198,'         // Create a new textFile','Fixed',NULL),(1,199,'         file = new File(umlSeqDiagTextFile);','Fixed',NULL),(1,200,'','Fixed',NULL),(1,201,'         if(file.exists())','Fixed',NULL),(1,202,'         {','Fixed',NULL),(1,203,'            fw = new FileWriter(file,true);','Fixed',NULL),(1,204,'            bw = new BufferedWriter(fw);','Fixed',NULL),(1,205,'','Fixed',NULL),(1,206,'            // Write in the textFile','Fixed',NULL),(1,207,'            fw.append(content);','Fixed',NULL),(1,208,'','Fixed',NULL),(1,209,'            // Close the textFile','Fixed',NULL),(1,210,'            bw.close();','Fixed',NULL),(1,211,'         }','Fixed',NULL),(1,212,'         ','Fixed',NULL),(1,213,'      }','Fixed',NULL),(1,214,'      catch(Exception e)','Fixed',NULL),(1,215,'      {','Fixed',NULL),(1,216,'         errorMessage = e.getMessage();','Fixed',NULL),(1,217,'         System.out.println(\"Error FRL: Occurred while opening the UML Sequence Diagram Text File: \"+umlSeqDiagTextFile + \"Error Message: \" + errorMessage);','Fixed',NULL),(1,218,'      }','Fixed',NULL),(1,219,'            ','Fixed',NULL),(1,220,'   }  ','Fixed',NULL),(1,221,'   ','Fixed',NULL),(1,222,'   public boolean validateSuperClass(String inputSuperClass)','Fixed',NULL),(1,223,'   {','Fixed',NULL),(1,224,'      String inputGuiLibStr, lib;','Fixed',NULL),(1,225,'      String [] guiLibsArray; ','Fixed',NULL),(1,226,'      int c=0;','Fixed',NULL),(1,227,'      boolean guiFlag = false;	','Fixed',NULL),(1,228,'','Fixed',NULL),(1,229,'      inputGuiLibStr = guiLibs;','Fixed',NULL),(1,230,'   ','Fixed',NULL),(1,231,'      // Get the Array from the Input GUI Library String','Fixed',NULL),(1,232,'      guiLibsArray = inputGuiLibStr.split(guiLibDelimiter); ','Fixed',NULL),(1,233,'   ','Fixed',NULL),(1,234,'      while(c < guiLibsArray.length && guiFlag == false)','Fixed',NULL),(1,235,'      {','Fixed',NULL),(1,236,'         lib = guiLibsArray[c];','Fixed',NULL),(1,237,'         ','Fixed',NULL),(1,238,'         // Validate if the inputSuperClass contains the current Library prefix','Fixed',NULL),(1,239,'	     if(inputSuperClass.contains(lib))','Fixed',NULL),(1,240,'	        guiFlag = true;	','Fixed',NULL),(1,241,'	  ','Fixed',NULL),(1,242,'	     c++;','Fixed',NULL),(1,243,'      } ','Fixed',NULL),(1,244,'   ','Fixed',NULL),(1,245,'      return guiFlag;   ','Fixed',NULL),(1,246,'   }  ','Fixed',NULL),(1,247,'','Fixed',NULL),(1,248,'   public String getSuperClass(Class<?>  inputClass)','Fixed',NULL),(1,249,'   {','Fixed',NULL),(1,250,'	   ','Fixed',NULL),(1,251,'      // Obtain the Class and SuperClass of the current Method Body File','Fixed',NULL),(1,252,'      String superClass = \"\";','Fixed',NULL),(1,253,'		  		  		  ','Fixed',NULL),(1,254,'      // Get the superclass from the class','Fixed',NULL),(1,255,'      Class<?> superclz = inputClass.getSuperclass();','Fixed',NULL),(1,256,'      superClass = superclz.toString();','Fixed',NULL),(1,257,'   ','Fixed',NULL),(1,258,'      return superClass;  ','Fixed',NULL),(1,259,'   }','Fixed',NULL),(1,260,'','Fixed',NULL),(1,261,'   public Class<?> getUpdatedClass(String inputClass)','Fixed',NULL),(1,262,'   {','Fixed',NULL),(1,263,'      Class<?> classObj = null;','Fixed',NULL),(1,264,'	','Fixed',NULL),(1,265,'      try ','Fixed',NULL),(1,266,'      {','Fixed',NULL),(1,267,'         classObj = Class.forName(inputClass);','Fixed',NULL),(1,268,'      } ','Fixed',NULL),(1,269,'      catch (ClassNotFoundException e) ','Fixed',NULL),(1,270,'      {','Fixed',NULL),(1,271,'         errorMessage = e.getMessage();','Fixed',NULL),(1,272,'	     System.out.println(\"Error FRL: Occurred while creating a New Class. Error Message: \" + errorMessage);','Fixed',NULL),(1,273,'      }','Fixed',NULL),(1,274,'	','Fixed',NULL),(1,275,'      return classObj;	','Fixed',NULL),(1,276,'   }','Fixed',NULL),(1,277,'   ','Fixed',NULL),(1,278,'   public String validateClass(String inputClass)','Fixed',NULL),(1,279,'   {','Fixed',NULL),(1,280,'      String outputClass = inputClass, element1, searchWhiteSpace=\"\\\\s+\";	','Fixed',NULL),(1,281,'      String[] elements;','Fixed',NULL),(1,282,'   ','Fixed',NULL),(1,283,'      // Replace the bluePrintObject: \"class\" for \"\"','Fixed',NULL),(1,284,'      if(outputClass.contains(bluePrintObject1))','Fixed',NULL),(1,285,'         outputClass = outputClass.replace(bluePrintObject1,\"\");','Fixed',NULL),(1,286,'	','Fixed',NULL),(1,287,'      // Remove the WhiteSpaces from the beginning of the string','Fixed',NULL),(1,288,'      outputClass = outputClass.replaceFirst(searchWhiteSpace, \"\");','Fixed',NULL),(1,289,'   ','Fixed',NULL),(1,290,'      // Replace the specialCharacter1:\"$\" for \"_\"','Fixed',NULL),(1,291,'      if(outputClass.contains(invalidSpecialCharacter))','Fixed',NULL),(1,292,'      {	   ','Fixed',NULL),(1,293,'	     // Replace the dollarDelimiter:\"$\" for \"_\"','Fixed',NULL),(1,294,'         outputClass = outputClass.replace(invalidSpecialCharacter, validSpecialCharacter);','Fixed',NULL),(1,295,'','Fixed',NULL),(1,296,'	     // Divide the string in two parts using the \"_\" delimiter','Fixed',NULL),(1,297,'         elements = outputClass.split(validSpecialCharacter); ','Fixed',NULL),(1,298,'      ','Fixed',NULL),(1,299,'         // Get the first part','Fixed',NULL),(1,300,'  	     element1 = elements[0];','Fixed',NULL),(1,301,'         outputClass = element1;','Fixed',NULL),(1,302,'      ','Fixed',NULL),(1,303,'      }   ','Fixed',NULL),(1,304,'   ','Fixed',NULL),(1,305,'      return outputClass;','Fixed',NULL),(1,306,'   }','Fixed',NULL),(1,307,'   ','Fixed',NULL),(1,308,'   public void getCalleeClassDetails(Class<?> calleeClassObj0)','Fixed',NULL),(1,309,'   {','Fixed',NULL),(1,310,'	   ','Fixed',NULL),(1,311,'	  // Get the calleeClass ','Fixed',NULL),(1,312,'      calleeClass = calleeClassObj0.toString();','Fixed',NULL),(1,313,'','Fixed',NULL),(1,314,'	  // Validate the calleeClass','Fixed',NULL),(1,315,'	  calleeClass = validateClass(calleeClass);','Fixed',NULL),(1,316,'','Fixed',NULL),(1,317,'	  //Get the Updated Object Class ','Fixed',NULL),(1,318,'	  calleeClassObj2 = getUpdatedClass(calleeClass);','Fixed',NULL),(1,319,'','Fixed',NULL),(1,320,'	  //Get the superClass of the caller Class','Fixed',NULL),(1,321,'	  calleeSuperClass = getSuperClass(calleeClassObj2);','Fixed',NULL),(1,322,'	','Fixed',NULL),(1,323,'	  // Get the flag of the callerSuperClass GUI','Fixed',NULL),(1,324,'	  calleeGuiFlag = validateSuperClass(calleeSuperClass);','Fixed',NULL),(1,325,'','Fixed',NULL),(1,326,'	  // Validate if callerSuperClass corresponds to GUI','Fixed',NULL),(1,327,'	  if(calleeGuiFlag == true)','Fixed',NULL),(1,328,'	  {','Fixed',NULL),(1,329,'	     //calleeClass = calleeClass;','Fixed',NULL),(1,330,'	     calleeType = member1;','Fixed',NULL),(1,331,'	  }','Fixed',NULL),(1,332,'	  else','Fixed',NULL),(1,333,'	     calleeType = member2;','Fixed',NULL),(1,334,'   }','Fixed',NULL),(1,335,'   ','Fixed',NULL),(1,336,'   public void getCallerClassDetails(Class<?> callerClassObj0)','Fixed',NULL),(1,337,'   {','Fixed',NULL),(1,338,'	   ','Fixed',NULL),(1,339,'      // Get the caller Class','Fixed',NULL),(1,340,'      callerClass = callerClassObj0.toString();','Fixed',NULL),(1,341,'','Fixed',NULL),(1,342,'	  // Validate the caller Class','Fixed',NULL),(1,343,'	  callerClass = validateClass(callerClass);','Fixed',NULL),(1,344,'','Fixed',NULL),(1,345,'	  // Get the Updated Object Class ','Fixed',NULL),(1,346,'	  callerClassObj2 = getUpdatedClass(callerClass);','Fixed',NULL),(1,347,'','Fixed',NULL),(1,348,'	  // Get the superClass of the caller Class','Fixed',NULL),(1,349,'	  callerSuperClass = getSuperClass(callerClassObj2);','Fixed',NULL),(1,350,'','Fixed',NULL),(1,351,'	  // Get the flag of the callerSuperClass GUI','Fixed',NULL),(1,352,'	  callerGuiFlag = validateSuperClass(callerSuperClass);','Fixed',NULL),(1,353,'','Fixed',NULL),(1,354,'	  // Validate if callerSuperClass corresponds to GUI','Fixed',NULL),(1,355,'	  if(callerGuiFlag == true)','Fixed',NULL),(1,356,'	  {	','Fixed',NULL),(1,357,'	     //callerClass = callerClass;','Fixed',NULL),(1,358,'	     callerType = member1;','Fixed',NULL),(1,359,'	  }','Fixed',NULL),(1,360,'	  else','Fixed',NULL),(1,361,'	     callerType = member2;','Fixed',NULL),(1,362,'   }','Fixed',NULL),(1,363,'  ','Fixed',NULL),(1,364,'}','Fixed',NULL);
/*!40000 ALTER TABLE `text_file_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_file_header`
--

DROP TABLE IF EXISTS `text_file_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `text_file_header` (
  `tfh_id` int(11) NOT NULL COMMENT 'Text File Header Identifier',
  `tfh_filename` varchar(500) NOT NULL COMMENT 'Text File Header Filename',
  `tfh_path` varchar(5000) NOT NULL COMMENT 'Text File Header Path',
  `tfh_name` varchar(500) NOT NULL COMMENT 'Text File Header Name',
  `tfh_type` enum('Aspect','Class') NOT NULL COMMENT 'Text File Header Type',
  `tfh_package_name` varchar(500) NOT NULL COMMENT 'Text File Header Package Name',
  `pl_id` int(11) NOT NULL COMMENT 'Programming Language Identifier',
  PRIMARY KEY (`tfh_id`),
  KEY `fk_pl_id_idx` (`pl_id`),
  CONSTRAINT `fk_pl_id_tfh` FOREIGN KEY (`pl_id`) REFERENCES `programming_language` (`pl_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_file_header`
--

LOCK TABLES `text_file_header` WRITE;
/*!40000 ALTER TABLE `text_file_header` DISABLE KEYS */;
INSERT INTO `text_file_header` VALUES (1,'FrlDatabaseMethods.aj','/Users/fannyriveraortiz/Desktop/Input/frl/','FrlDatabaseMethods','Aspect','frl',1),(2,'FrlStart.aj','/Users/fannyriveraortiz/Desktop/Input/frl/','FrlStart','Aspect','frl',1);
/*!40000 ALTER TABLE `text_file_header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_file_plain`
--

DROP TABLE IF EXISTS `text_file_plain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `text_file_plain` (
  `tfh_id` int(11) DEFAULT NULL COMMENT 'Text File Header Id',
  `tfp_line_number` int(11) NOT NULL COMMENT 'Text File Plain Line Number',
  `tfp_text` varchar(5000) NOT NULL COMMENT 'Text File Plain Text',
  KEY `fk_tfh_id_tfp_idx` (`tfh_id`),
  CONSTRAINT `fk_tfh_id_tfp` FOREIGN KEY (`tfh_id`) REFERENCES `text_file_header` (`tfh_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_file_plain`
--

LOCK TABLES `text_file_plain` WRITE;
/*!40000 ALTER TABLE `text_file_plain` DISABLE KEYS */;
INSERT INTO `text_file_plain` VALUES (1,1,'package frl;'),(1,2,''),(1,3,'import java.io.BufferedWriter;'),(1,4,'import java.io.File;'),(1,5,'import java.io.FileWriter;'),(1,6,'import java.util.Arrays;'),(1,7,'import org.aspectj.lang.Signature;'),(1,8,'import java.util.ArrayList;'),(1,9,''),(1,10,'public aspect FrlDatabaseMethods'),(1,11,'{'),(1,12,'   String projectName=\"HRMApplication\";		'),(1,13,'   String projectOutputDir=\"/Users/fannyriveraortiz/Desktop/Output/\";'),(1,14,'   String umlSeqDiagTextFileName1=\"IncidentSequenceDiagram.txt\";'),(1,15,'   String guiLibs=\"java.awt;javax.swing;org.eclipse.swt;javafx;org.apache.pivot.wtk;com.trolltech.qt.gui;\";'),(1,16,'   String bluePrintObject1=\"class\";'),(1,17,'   String objectOrientedDelimiter1=\".\";'),(1,18,'   String startParameters=\"(\";'),(1,19,'   String endParameters=\")\";'),(1,20,'   String newLine1=\"%n\";'),(1,21,'   String whiteSpaceWordsDelimiter1=\" \";'),(1,22,'   '),(1,23,'   String guiLibDelimiter=\";\";'),(1,24,'   String invalidSpecialCharacter=\"$\";'),(1,25,'   String validSpecialCharacter=\"_\";'),(1,26,'   String member1=\"actor\";'),(1,27,'   String member2=\"participant\";'),(1,28,'   String startSendMessage=\"->\";'),(1,29,'   String endSendMessage=\":\";'),(1,30,'   String userName=\"User\";'),(1,31,''),(1,32,'   String startNote=\"note left of\";'),(1,33,'   String endNote=\"end note\";'),(1,34,'   String colorDelimiter=\"#\";'),(1,35,'   String colorNote=\"aqua\";'),(1,36,'   String position=\"order\";'),(1,37,'   String space=\"||20||\";'),(1,38,'   String startDivision=\"== Connects to the\";'),(1,39,'   String endDivision=\"==\";'),(1,40,'   '),(1,41,'   String umlSeqDiagTextFile=projectOutputDir + umlSeqDiagTextFileName1;'),(1,42,'   String callerClass=\"\", calleeClass=\"\", callerClassLine, calleeClassLine;'),(1,43,'   String packageMethod=\"\", classMethod=\"\";'),(1,44,'   String nameMethod=\"\", fullMethod=\"\", callerSuperClass=\"\", calleeSuperClass=\"\";'),(1,45,'   String callerType=\"\", calleeType=\"\", errorMessage=\"\", note=\"\";'),(1,46,'   String line=\"\", content=\"\", currentUser=\"\";'),(1,47,'   int actorOrder=1, partOrder=1, methodsCounter=0, userCounter=0;'),(1,48,'   boolean callerGuiFlag= false, calleeGuiFlag=false;'),(1,49,'   Class<?> callerClassObj1, callerClassObj2, calleeClassObj1, calleeClassObj2; '),(1,50,'   Signature method;'),(1,51,'   Object[] methodArgs;'),(1,52,''),(1,53,'   pointcut databaseMethods(): '),(1,54,'                            call(void model.ModuleDatabase.saveModules(..)) || '),(1,55,'                            call(void controller.ModuleController.saveModules(..)) || '),(1,56,'                            call(void test.TestModuleDb.main(..)) || '),(1,57,'                            call(void model.EmployeeDatabase.loadEmployee(..)) || '),(1,58,'                            call(void test.TestEmployeeDb.main(..)) || '),(1,59,'                            call(void controller.EmployeeController.loadEmployee(..)) || '),(1,60,'                            call(int model.EmployeeDatabase.maxNumEmployee(..)) || '),(1,61,'                            call(int controller.EmployeeController.maxNumEmployee(..)) || '),(1,62,'                            call(void model.EmployeeDatabase.load(..)) || '),(1,63,'                            call(void controller.EmployeeController.load(..)) || '),(1,64,'                            call(boolean model.EmployeeDatabase.validateFullName(..)) || '),(1,65,'                            call(boolean controller.EmployeeController.validateFullName(..)) || '),(1,66,'                            call(boolean model.EmployeeDatabase.validateDeleteEmployee2(..)) || '),(1,67,'                            call(boolean controller.EmployeeController.validateDeleteEmployee2(..)) || '),(1,68,'                            call(ArrayList<model.Employee> model.EmployeeDatabase.loadSupervisors(..)) || '),(1,69,'                            call(boolean model.EmployeeDatabase.validateDeleteEmployee1(..)) || '),(1,70,'                            call(boolean controller.EmployeeController.validateDeleteEmployee1(..)) || '),(1,71,'                            call(void model.EmployeeDatabase.save(..)) || '),(1,72,'                            call(void controller.EmployeeController.save(..)) || '),(1,73,'                            call(void model.EmployeeDatabase.delete(..)) || '),(1,74,'                            call(void controller.EmployeeController.delete(..)) || '),(1,75,'                            call(boolean model.UserDatabase.validateDeleteUser(..)) || '),(1,76,'                            call(void test.TestUserDb.main(..)) || '),(1,77,'                            call(boolean controller.UserController.validateDeleteUser(..)) || '),(1,78,'                            call(boolean model.UserDatabase.validateEmail(..)) || '),(1,79,'                            call(boolean controller.UserController.validateEmail(..)) || '),(1,80,'                            call(void model.UserDatabase.load(..)) || '),(1,81,'                            call(void controller.UserController.load(..)) || '),(1,82,'                            call(boolean model.UserDatabase.validateEmployee(..)) || '),(1,83,'                            call(boolean controller.UserController.validateEmployee(..)) || '),(1,84,'                            call(boolean model.UserDatabase.validateUserName(..)) || '),(1,85,'                            call(boolean controller.UserController.validateUserName(..)) || '),(1,86,'                            call(model.UserLevel model.UserDatabase.getUserLevel(..)) || '),(1,87,'                            call(boolean controller.UserController.validateUser(..)) || '),(1,88,'                            call(void test.TestLoginDb.main(..)) || '),(1,89,'                            call(void model.UserDatabase.save(..)) || '),(1,90,'                            call(void controller.UserController.save(..)) || '),(1,91,'                            call(boolean model.UserDatabase.validateUser(..)) || '),(1,92,'                            call(void model.UserDatabase.loadUser(..)) || '),(1,93,'                            call(void controller.UserController.loadUser(..)) || '),(1,94,'                            call(ArrayList<model.Employee> model.UserDatabase.loadFullNameEmployees(..)) || '),(1,95,'                            call(void model.UserDatabase.delete(..)) || '),(1,96,'                            call(void controller.UserController.delete(..)) || '),(1,97,'                            call(void model.TravelRequestDatabase.loadTravelRequest(..)) || '),(1,98,'                            call(void test.TestTravelRequestDb.main(..)) || '),(1,99,'                            call(void controller.TravelRequestController.loadTravelRequest(..)) || '),(1,100,'                            call(void model.TravelRequestDatabase.load(..)) || '),(1,101,'                            call(void controller.TravelRequestController.load(..)) || '),(1,102,'                            call(int model.TravelRequestDatabase.maxNumTravelRequest(..)) || '),(1,103,'                            call(int controller.TravelRequestController.maxNumTravelRequest(..)) || '),(1,104,'                            call(void model.TravelRequestDatabase.save(..)) || '),(1,105,'                            call(void controller.TravelRequestController.save(..)) || '),(1,106,'                            call(ArrayList<model.Employee> model.TravelRequestDatabase.loadFullNameEmployees(..)) || '),(1,107,'                            call(void model.TravelRequestDatabase.delete(..)) || '),(1,108,'                            call(void controller.TravelRequestController.delete(..));'),(1,109,''),(1,110,'   before(): databaseMethods()'),(1,111,'   {'),(1,112,''),(1,113,'      // Get the Caller Class Details'),(1,114,'      callerClassObj1 = thisEnclosingJoinPointStaticPart.getSignature().getDeclaringType();'),(1,115,'      getCallerClassDetails(callerClassObj1);'),(1,116,''),(1,117,'      // Get the Callee Class Details'),(1,118,'      calleeClassObj1 = thisJoinPoint.getTarget().getClass();'),(1,119,'      getCalleeClassDetails(calleeClassObj1);'),(1,120,''),(1,121,'      // Get the details of the Method being executed'),(1,122,'      method        = thisJoinPoint.getSignature();'),(1,123,'      packageMethod = method.getDeclaringType().getPackageName();'),(1,124,'      classMethod   = method.getDeclaringType().getSimpleName();'),(1,125,'      nameMethod    = method.getName();'),(1,126,'      methodArgs    = thisJoinPoint.getArgs();'),(1,127,'      fullMethod	= nameMethod + startParameters;'),(1,128,'      '),(1,129,'      // Validate if the CallerClass is a Graphical User Interface Class'),(1,130,'      // Build the Caller Class Line'),(1,131,'      if(callerGuiFlag == true)'),(1,132,'      {	  '),(1,133,'    	 // Build the method line '),(1,134,'         fullMethod = fullMethod + endParameters;'),(1,135,'         '),(1,136,'         // Build the note line'),(1,137,'         note = startNote   + whiteSpaceWordsDelimiter1 + currentUser + whiteSpaceWordsDelimiter1 + colorDelimiter + colorNote + '),(1,138,'                whiteSpaceWordsDelimiter1 + newLine1 + callerClass + '),(1,139,'                newLine1 + endNote + newLine1;'),(1,140,'         '),(1,141,'         // Assign the value to the caller Class'),(1,142,'         callerClass = currentUser;'),(1,143,'         '),(1,144,'         // Build the caller Class Line'),(1,145,'         callerClassLine = member1 + whiteSpaceWordsDelimiter1  + callerClass + '),(1,146,'        		           whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + actorOrder;'),(1,147,'      }   '),(1,148,'      else'),(1,149,'      {	  '),(1,150,'    	 // Build the method line  '),(1,151,'         fullMethod = fullMethod + Arrays.toString(methodArgs) + endParameters;'),(1,152,'         '),(1,153,'         // The note line will be empty'),(1,154,'         note = \"\";'),(1,155,'         '),(1,156,'         // Increase the participants Order Counter'),(1,157,'         partOrder++;'),(1,158,'         '),(1,159,'         // Build the caller Class Line'),(1,160,'         callerClassLine = member2 + whiteSpaceWordsDelimiter1 + callerClass + '),(1,161,'        		           whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;'),(1,162,'      }   '),(1,163,'      '),(1,164,'      partOrder++;'),(1,165,'      '),(1,166,'      // Build the callee Class Line'),(1,167,'      calleeClassLine = member2 + whiteSpaceWordsDelimiter1 + calleeClass + '),(1,168,'    		            whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;'),(1,169,'    		  '),(1,170,'      // Generate the Plant UML line to generate the UML Sequence Diagram Text File'),(1,171,'      updateSeqDiagTextFile(callerClass, calleeClass, fullMethod, note, callerClassLine, calleeClassLine);'),(1,172,''),(1,173,'   } '),(1,174,'   '),(1,175,'   public void updateSeqDiagTextFile(String inputCallerClass, String inputCalleeClass, '),(1,176,'		                             String inputMethod, String inputNote, String inputCallerClassLine,'),(1,177,'		                             String inputCalleeClassLine)'),(1,178,'   {'),(1,179,'	   '),(1,180,'      line    = \"\";'),(1,181,'      content = \"\";'),(1,182,'      '),(1,183,'      // Generating the content of the Text File which contains Plant UML instructions'),(1,184,'      // to generate the UML Sequence Diagram'),(1,185,'      '),(1,186,'      line    = inputCallerClassLine + newLine1;'),(1,187,'      content = content + line;'),(1,188,'      '),(1,189,'      line    = inputCalleeClassLine + newLine1;'),(1,190,'      content = content + line;'),(1,191,'      '),(1,192,'      line    = inputNote;'),(1,193,'      content = content + line;'),(1,194,'      '),(1,195,'      // Increase the methods Counter'),(1,196,'      methodsCounter ++;'),(1,197,'      '),(1,198,'      line = inputCallerClass + whiteSpaceWordsDelimiter1 + startSendMessage + '),(1,199,'    		 inputCalleeClass + endSendMessage            + whiteSpaceWordsDelimiter1 + '),(1,200,'    		 methodsCounter   + objectOrientedDelimiter1  + inputMethod + newLine1;'),(1,201,'      content = content + line;     '),(1,202,'      '),(1,203,'      line    = space + newLine1;'),(1,204,'      content = content + line;'),(1,205,'      '),(1,206,'      content = String.format(content);'),(1,207,''),(1,208,'      // Write the Plant Uml Line into the UML Sequence Diagram Text File'),(1,209,'      writeTextFile(umlSeqDiagTextFile, content);   '),(1,210,''),(1,211,'   } '),(1,212,'   '),(1,213,'   pointcut connect():'),(1,214,'	      execution(gui.LoginFrame.new(..));'),(1,215,''),(1,216,'   before(): connect() '),(1,217,'   {'),(1,218,'   	   '),(1,219,'	  line    = \"\";'),(1,220,'	  content = \"\";'),(1,221,'	      '),(1,222,'      // Add a division in the UML Sequence Diagram Text File  '),(1,223,'	  line = startDivision + whiteSpaceWordsDelimiter1 + '),(1,224,'	         projectName   + whiteSpaceWordsDelimiter1 + '),(1,225,'	    	 endDivision   + newLine1;'),(1,226,'	  '),(1,227,'      content = content + line;'),(1,228,'      '),(1,229,'      content = String.format(content);'),(1,230,'      '),(1,231,'      // Write this division into the UML Sequence Diagram Text File'),(1,232,'	  writeTextFile(umlSeqDiagTextFile, content);'),(1,233,'	  '),(1,234,'	  // Initialize the methods counter'),(1,235,'	  methodsCounter = 0;'),(1,236,'	  '),(1,237,'	  // Increase the users counters'),(1,238,'	  userCounter ++;'),(1,239,'	  '),(1,240,'	  currentUser = userName + validSpecialCharacter + userCounter;'),(1,241,'	  '),(1,242,'   }  '),(1,243,'     '),(1,244,'   public void writeTextFile(String textfile, String content)'),(1,245,'   {'),(1,246,'      FileWriter fw = null;'),(1,247,'      BufferedWriter bw;'),(1,248,'      File file;'),(1,249,''),(1,250,'      try'),(1,251,'      {'),(1,252,'         // Create a new textFile'),(1,253,'         file = new File(umlSeqDiagTextFile);'),(1,254,''),(1,255,'         if(file.exists())'),(1,256,'         {'),(1,257,'            fw = new FileWriter(file,true);'),(1,258,'            bw = new BufferedWriter(fw);'),(1,259,''),(1,260,'            // Write in the textFile'),(1,261,'            fw.append(content);'),(1,262,''),(1,263,'            // Close the textFile'),(1,264,'            bw.close();'),(1,265,'         }'),(1,266,'         '),(1,267,'      }'),(1,268,'      catch(Exception e)'),(1,269,'      {'),(1,270,'         errorMessage = e.getMessage();'),(1,271,'         System.out.println(\"Error FRL: Occurred while opening the UML Sequence Diagram Text File: \"+umlSeqDiagTextFile + \"Error Message: \" + errorMessage);'),(1,272,'      }'),(1,273,'            '),(1,274,'   }  '),(1,275,'   '),(1,276,'   public boolean validateSuperClass(String inputSuperClass)'),(1,277,'   {'),(1,278,'      String inputGuiLibStr, lib;'),(1,279,'      String [] guiLibsArray; '),(1,280,'      int c=0;'),(1,281,'      boolean guiFlag = false;	'),(1,282,''),(1,283,'      inputGuiLibStr = guiLibs;'),(1,284,'   '),(1,285,'      // Get the Array from the Input GUI Library String'),(1,286,'      guiLibsArray = inputGuiLibStr.split(guiLibDelimiter); '),(1,287,'   '),(1,288,'      while(c < guiLibsArray.length && guiFlag == false)'),(1,289,'      {'),(1,290,'         lib = guiLibsArray[c];'),(1,291,'         '),(1,292,'         // Validate if the inputSuperClass contains the current Library prefix'),(1,293,'	     if(inputSuperClass.contains(lib))'),(1,294,'	        guiFlag = true;	'),(1,295,'	  '),(1,296,'	     c++;'),(1,297,'      } '),(1,298,'   '),(1,299,'      return guiFlag;   '),(1,300,'   }  '),(1,301,''),(1,302,'   public String getSuperClass(Class<?>  inputClass)'),(1,303,'   {'),(1,304,'	   '),(1,305,'      // Obtain the Class and SuperClass of the current Method Body File'),(1,306,'      String superClass = \"\";'),(1,307,'		  		  		  '),(1,308,'      // Get the superclass from the class'),(1,309,'      Class<?> superclz = inputClass.getSuperclass();'),(1,310,'      superClass = superclz.toString();'),(1,311,'   '),(1,312,'      return superClass;  '),(1,313,'   }'),(1,314,''),(1,315,'   public Class<?> getUpdatedClass(String inputClass)'),(1,316,'   {'),(1,317,'      Class<?> classObj = null;'),(1,318,'	'),(1,319,'      try '),(1,320,'      {'),(1,321,'         classObj = Class.forName(inputClass);'),(1,322,'      } '),(1,323,'      catch (ClassNotFoundException e) '),(1,324,'      {'),(1,325,'         errorMessage = e.getMessage();'),(1,326,'	     System.out.println(\"Error FRL: Occurred while creating a New Class. Error Message: \" + errorMessage);'),(1,327,'      }'),(1,328,'	'),(1,329,'      return classObj;	'),(1,330,'   }'),(1,331,'   '),(1,332,'   public String validateClass(String inputClass)'),(1,333,'   {'),(1,334,'      String outputClass = inputClass, element1, searchWhiteSpace=\"\\\\s+\";	'),(1,335,'      String[] elements;'),(1,336,'   '),(1,337,'      // Replace the bluePrintObject: \"class\" for \"\"'),(1,338,'      if(outputClass.contains(bluePrintObject1))'),(1,339,'         outputClass = outputClass.replace(bluePrintObject1,\"\");'),(1,340,'	'),(1,341,'      // Remove the WhiteSpaces from the beginning of the string'),(1,342,'      outputClass = outputClass.replaceFirst(searchWhiteSpace, \"\");'),(1,343,'   '),(1,344,'      // Replace the specialCharacter1:\"$\" for \"_\"'),(1,345,'      if(outputClass.contains(invalidSpecialCharacter))'),(1,346,'      {	   '),(1,347,'	     // Replace the dollarDelimiter:\"$\" for \"_\"'),(1,348,'         outputClass = outputClass.replace(invalidSpecialCharacter, validSpecialCharacter);'),(1,349,''),(1,350,'	     // Divide the string in two parts using the \"_\" delimiter'),(1,351,'         elements = outputClass.split(validSpecialCharacter); '),(1,352,'      '),(1,353,'         // Get the first part'),(1,354,'  	     element1 = elements[0];'),(1,355,'         outputClass = element1;'),(1,356,'      '),(1,357,'      }   '),(1,358,'   '),(1,359,'      return outputClass;'),(1,360,'   }'),(1,361,'   '),(1,362,'   public void getCalleeClassDetails(Class<?> calleeClassObj0)'),(1,363,'   {'),(1,364,'	   '),(1,365,'	  // Get the calleeClass '),(1,366,'      calleeClass = calleeClassObj0.toString();'),(1,367,''),(1,368,'	  // Validate the calleeClass'),(1,369,'	  calleeClass = validateClass(calleeClass);'),(1,370,''),(1,371,'	  //Get the Updated Object Class '),(1,372,'	  calleeClassObj2 = getUpdatedClass(calleeClass);'),(1,373,''),(1,374,'	  //Get the superClass of the caller Class'),(1,375,'	  calleeSuperClass = getSuperClass(calleeClassObj2);'),(1,376,'	'),(1,377,'	  // Get the flag of the callerSuperClass GUI'),(1,378,'	  calleeGuiFlag = validateSuperClass(calleeSuperClass);'),(1,379,''),(1,380,'	  // Validate if callerSuperClass corresponds to GUI'),(1,381,'	  if(calleeGuiFlag == true)'),(1,382,'	  {'),(1,383,'	     //calleeClass = calleeClass;'),(1,384,'	     calleeType = member1;'),(1,385,'	  }'),(1,386,'	  else'),(1,387,'	     calleeType = member2;'),(1,388,'   }'),(1,389,'   '),(1,390,'   public void getCallerClassDetails(Class<?> callerClassObj0)'),(1,391,'   {'),(1,392,'	   '),(1,393,'      // Get the caller Class'),(1,394,'      callerClass = callerClassObj0.toString();'),(1,395,''),(1,396,'	  // Validate the caller Class'),(1,397,'	  callerClass = validateClass(callerClass);'),(1,398,''),(1,399,'	  // Get the Updated Object Class '),(1,400,'	  callerClassObj2 = getUpdatedClass(callerClass);'),(1,401,''),(1,402,'	  // Get the superClass of the caller Class'),(1,403,'	  callerSuperClass = getSuperClass(callerClassObj2);'),(1,404,''),(1,405,'	  // Get the flag of the callerSuperClass GUI'),(1,406,'	  callerGuiFlag = validateSuperClass(callerSuperClass);'),(1,407,''),(1,408,'	  // Validate if callerSuperClass corresponds to GUI'),(1,409,'	  if(callerGuiFlag == true)'),(1,410,'	  {	'),(1,411,'	     //callerClass = callerClass;'),(1,412,'	     callerType = member1;'),(1,413,'	  }'),(1,414,'	  else'),(1,415,'	     callerType = member2;'),(1,416,'   }'),(1,417,'  '),(1,418,'}'),(2,1,'package frl;'),(2,2,''),(2,3,'import java.io.BufferedWriter;'),(2,4,'import java.io.File;'),(2,5,'import java.io.FileWriter;'),(2,6,'import java.io.IOException;'),(2,7,'import java.util.List;'),(2,8,''),(2,9,'import net.sourceforge.plantuml.GeneratedImage;'),(2,10,'import net.sourceforge.plantuml.SourceFileReader;'),(2,11,''),(2,12,'public aspect FrlStart'),(2,13,'{'),(2,14,'   String projectOutputDir=\"/Users/fannyriveraortiz/Desktop/Output/\";'),(2,15,'   String umlSeqDiagTextFileName1=\"IncidentSequenceDiagram.txt\";'),(2,16,'   String umlSeqDiagPngFileName1=\"IncidentSequenceDiagram.png\";'),(2,17,'   '),(2,18,'   String newLine1=\"%n\";'),(2,19,'   String startUMLSeqDiagram=\"@startuml\";'),(2,20,'   String endUMLSeqDiagram=\"@enduml\";'),(2,21,''),(2,22,'   String umlSeqDiagTextFile=projectOutputDir + umlSeqDiagTextFileName1;'),(2,23,'   String umlSeqDiagPngFile=projectOutputDir  + umlSeqDiagPngFileName1;'),(2,24,'   String method, errorMessage, content=\"\";'),(2,25,'   '),(2,26,'   pointcut start(): '),(2,27,'	            execution(void gui.HRMApp.main(..));'),(2,28,''),(2,29,'   after(): start()'),(2,30,'   {'),(2,31,'	  System.out.println(\"Message FRL: Welcome to the Forensic-Ready Logger Tool ...\");'),(2,32,'	   '),(2,33,'	  // Delete UML Sequence Diagram previous Files'),(2,34,'	  deleteFiles();'),(2,35,''),(2,36,'	  // Create a new UML Sequence Diagram Text File'),(2,37,'	  createSeqDiagramTextFile(umlSeqDiagTextFile);'),(2,38,'   }'),(2,39,'   '),(2,40,'   pointcut end(): '),(2,41,'       call(void exit(..));'),(2,42,''),(2,43,'   before(): end()'),(2,44,'   {'),(2,45,'      // Create and update the UML Sequence Diagram Files '),(2,46,'      updateFiles();'),(2,47,'   }'),(2,48,'   '),(2,49,'   public void deleteSeqDiagramFile(String inputFile)'),(2,50,'   {'),(2,51,'      File file = new File(inputFile);'),(2,52,'	   '),(2,53,'      // Delete an existing UML Sequence Diagram File'),(2,54,'	  if(file.exists() && !file.isDirectory()) '),(2,55,'	     file.delete();'),(2,56,'	  '),(2,57,'   }'),(2,58,'   '),(2,59,'   public void deleteFiles()'),(2,60,'   {'),(2,61,'      // Delete UML Sequence Diagram Text File'),(2,62,'	  deleteSeqDiagramFile(umlSeqDiagTextFile);'),(2,63,'		  '),(2,64,'	  // Delete UML Sequence Diagram Png File'),(2,65,'      deleteSeqDiagramFile(umlSeqDiagPngFile); '),(2,66,'      '),(2,67,'   }'),(2,68,'   '),(2,69,'   public void writeTextFile(String textFile, String content)'),(2,70,'   {'),(2,71,'      FileWriter fw = null;'),(2,72,'      BufferedWriter bw;'),(2,73,'      File file;'),(2,74,''),(2,75,'      try'),(2,76,'      {'),(2,77,'         // Create a new textFile'),(2,78,'         file = new File(textFile);'),(2,79,''),(2,80,'         // Validate if the textFile exists'),(2,81,'         if (!file.exists()) '),(2,82,'         {'),(2,83,'            file.createNewFile();'),(2,84,'            fw = new FileWriter(file.getAbsoluteFile());'),(2,85,'         }'),(2,86,'         else'),(2,87,'            fw = new FileWriter(file, true);'),(2,88,'         '),(2,89,'         bw = new BufferedWriter(fw);'),(2,90,''),(2,91,'         // Write in the textFile'),(2,92,'         fw.append(content);'),(2,93,''),(2,94,'         // Close the textFile'),(2,95,'         bw.close();'),(2,96,'         '),(2,97,'      }'),(2,98,'      catch(Exception e)'),(2,99,'      {'),(2,100,'         errorMessage = e.getMessage();'),(2,101,'         System.out.println(\"Error FRL: Occurred while opening the UML Sequence Diagram Text File: \"+umlSeqDiagTextFile + \"Error Message: \" + errorMessage);'),(2,102,'      }'),(2,103,'            '),(2,104,'   }  '),(2,105,'   '),(2,106,'   public void createSeqDiagramTextFile(String textFile)'),(2,107,'   {'),(2,108,' '),(2,109,'      content = \"\";'),(2,110,''),(2,111,'      // Plant UML Instructions'),(2,112,'      content = startUMLSeqDiagram + newLine1;'),(2,113,'      content = content + \"skinparam backgroundColor #EEEBDC\"   + newLine1;'),(2,114,'      content = content + \"skinparam sequence {\"                + newLine1;'),(2,115,'      content = content + \"ArrowColor DarkBlue\"                 + newLine1;'),(2,116,'      content = content + \"ActorBorderColor DarkBlue\"           + newLine1;'),(2,117,'      content = content + \"LifeLineBorderColor blue\"            + newLine1;'),(2,118,'      content = content + \"LifeLineBackgroundColor DarkBlue\"    + newLine1;'),(2,119,'      content = content + \"ParticipantBorderColor DarkBlue\"     + newLine1;'),(2,120,'      content = content + \"ParticipantBackgroundColor DarkBlue\" + newLine1;'),(2,121,'      content = content + \"ParticipantFontName Impact\"          + newLine1;'),(2,122,'      content = content + \"ParticipantFontSize 17\"              + newLine1;'),(2,123,'      content = content + \"ParticipantFontColor #A9DCDF\"        + newLine1;'),(2,124,'      content = content + \"ActorBackgroundColor DarkBlue\"       + newLine1;'),(2,125,'      content = content + \"ActorFontColor DarkBlue\"             + newLine1;'),(2,126,'      content = content + \"ActorFontSize 17\"                    + newLine1;'),(2,127,'      content = content + \"ActorFontName Aapex\"                 + newLine1;'),(2,128,'      content = content + \"}\"                                   + newLine1;'),(2,129,'         '),(2,130,'      content = String.format(content);'),(2,131,'      '),(2,132,'      // Write the Plant Uml Lines into the UML Sequence Diagram Text File'),(2,133,'	  writeTextFile(textFile, content);'),(2,134,' '),(2,135,'   }'),(2,136,''),(2,137,'   public void createSeqDiagramPngFile(String textFile)'),(2,138,'   {'),(2,139,'      File file1 = new File(textFile); '),(2,140,'	  SourceFileReader reader = null;'),(2,141,'	  List<GeneratedImage> list = null;'),(2,142,'	  '),(2,143,'	  // Create the Reader of the UML Sequence Diagram Text File'),(2,144,'	  try '),(2,145,'	  {'),(2,146,'	     reader = new SourceFileReader(file1);'),(2,147,'	  } '),(2,148,'	  catch (IOException e) '),(2,149,'	  {'),(2,150,'	     errorMessage = e.getMessage();'),(2,151,'	     System.out.println(\"Error FRL: Occurred while reading the UML Sequence Diagram Text File: \"+textFile + \"Error Message: \" + errorMessage);'),(2,152,'	  }'),(2,153,'	   '),(2,154,'	  // Generate the list of images using the Sequence Diagram Text File'),(2,155,'	  try '),(2,156,'	  {'),(2,157,'	     list = reader.getGeneratedImages();'),(2,158,'	  } '),(2,159,'	  catch (IOException e) '),(2,160,'	  {'),(2,161,'	     errorMessage = e.getMessage();'),(2,162,'		 System.out.println(\"Error FRL: Occurred while generating the UML Sequence Diagram Png File\" + \"Error Message: \" + errorMessage);'),(2,163,'	  }'),(2,164,'	   '),(2,165,'	  // Generated the UML Sequence Diagram Png File'),(2,166,'	  file1 = list.get(0).getPngFile();'),(2,167,' 	   '),(2,168,'   }'),(2,169,'   '),(2,170,'   public void updateFiles()'),(2,171,'   {'),(2,172,'	   '),(2,173,'	  // Update the UML Sequence Diagram Text File with the @endUml Instruction  '),(2,174,'      content = endUMLSeqDiagram + newLine1;'),(2,175,'      content = String.format(content);'),(2,176,'      '),(2,177,'      // Write the contents into the UML Sequence Diagram Text File'),(2,178,'      writeTextFile(umlSeqDiagTextFile, content);'),(2,179,'      '),(2,180,'      // Create the UML Sequence Diagram Png File'),(2,181,'   	  createSeqDiagramPngFile(umlSeqDiagTextFile);'),(2,182,'	      '),(2,183,'  	  // Print on the screen Informative Messages'),(2,184,'	  System.out.println(\"Message FRL: UML Sequence Diagram Text File created: \"+umlSeqDiagTextFile);'),(2,185,'	  System.out.println(\"Message FRL: UML Sequence Diagram PNG File created : \"+umlSeqDiagPngFile);'),(2,186,'      System.out.println(\"Message FRL: Good Bye to the Forensic-Ready Logger Tool.\");'),(2,187,'	  '),(2,188,'   }  '),(2,189,'   '),(2,190,'}');
/*!40000 ALTER TABLE `text_file_plain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text_file_properties`
--

DROP TABLE IF EXISTS `text_file_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `text_file_properties` (
  `tfh_id` int(11) NOT NULL COMMENT 'Text File Header Identifier',
  `tfp_id` int(11) NOT NULL COMMENT 'Text File Properties Identifier',
  `tfp_property_name` varchar(500) NOT NULL COMMENT 'Text File Properties Property Name',
  `tfp_property_value` varchar(500) DEFAULT NULL COMMENT 'Text File Properties Property Value',
  `tfp_data_type` varchar(500) NOT NULL COMMENT 'Text File Properties Data Type',
  `tfp_type` enum('Component','Attribute','Method') NOT NULL COMMENT 'Text File Properties Type',
  PRIMARY KEY (`tfh_id`,`tfp_id`),
  CONSTRAINT `fk_tfh_id_tfp_1` FOREIGN KEY (`tfh_id`) REFERENCES `text_file_header` (`tfh_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text_file_properties`
--

LOCK TABLES `text_file_properties` WRITE;
/*!40000 ALTER TABLE `text_file_properties` DISABLE KEYS */;
INSERT INTO `text_file_properties` VALUES (1,1,'textFilePackageName','frl','String','Component'),(1,2,'methodName1','nonPrimitiveDataType','String','Method'),(1,3,'textFileName1','FrlDatabaseMethods','String','Component'),(1,4,'projectName','HRMApplication','String','Attribute'),(1,5,'projectOutputDir','/Users/fannyriveraortiz/Desktop/Output/','String','Attribute'),(1,6,'umlSeqDiagTextFileName1','IncidentSequenceDiagram.txt','String','Attribute'),(1,7,'methodName3','guiLibs','String','Method'),(1,8,'bluePrintObject1','class','String','Attribute'),(1,9,'objectOrientedDelimiter1','.','String','Attribute'),(1,10,'startParameters','(','String','Attribute'),(1,11,'endParameters',')','String','Attribute'),(1,12,'newLine1','%n','String','Attribute'),(1,13,'whiteSpaceWordsDelimiter1','\" \"','String','Attribute'),(1,14,'guiLibDelimiter',';','String','Attribute'),(1,15,'invalidSpecialCharacter','$','String','Attribute'),(1,16,'validSpecialCharacter','_','String','Attribute'),(1,17,'member1','actor','String','Attribute'),(1,18,'member2','participant','String','Attribute'),(1,19,'startSendMessage','->','String','Attribute'),(1,20,'endSendMessage',':','String','Attribute'),(1,21,'userName','User','String','Attribute'),(1,22,'startNote','note left of','String','Attribute'),(1,23,'endNote','end note','String','Attribute'),(1,24,'colorDelimiter','#','String','Attribute'),(1,25,'colorNote','aqua','String','Attribute'),(1,26,'position','order','String','Attribute'),(1,27,'space','||20||','String','Attribute'),(1,28,'startDivision','== Connects to the','String','Attribute'),(1,29,'endDivision','==','String','Attribute'),(1,30,'pointCutName1a','databaseMethods','String','Component'),(1,31,'methodName2','databaseMethods','String','Method'),(1,32,'pointCutName1b','connect','String','Component'),(1,33,'methodName6','connectProjectMethod','String','Method'),(2,1,'textFilePackageName','frl','String','Component'),(2,2,'textFileName2','FrlStart','String','Component'),(2,3,'projectOutputDir','/Users/fannyriveraortiz/Desktop/Output/','String','Attribute'),(2,4,'umlSeqDiagTextFileName1','IncidentSequenceDiagram.txt','String','Attribute'),(2,5,'umlSeqDiagPngFileName1','IncidentSequenceDiagram.png','String','Attribute'),(2,6,'newLine1','%n','String','Attribute'),(2,7,'startUMLSeqDiagram','@startuml','String','Attribute'),(2,8,'endUMLSeqDiagram','@enduml','String','Attribute'),(2,9,'pointCutName2a','start','String','Component'),(2,10,'methodName4','startApplication','String','Method'),(2,11,'pointCutName2b','end','String','Component'),(2,12,'methodName5','endApplication','String','Method');
/*!40000 ALTER TABLE `text_file_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tree_structure`
--

DROP TABLE IF EXISTS `tree_structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tree_structure` (
  `ts_id` int(11) NOT NULL COMMENT 'Tree Structure Identifier',
  `ts_name` varchar(200) NOT NULL COMMENT 'Tree Structure Name',
  `ts_node_type` enum('Root','Node','Internal','Leaf') NOT NULL COMMENT 'Tree Structure Node Type (Root, Node, Internal, Leaf)',
  `ts_parent_id` int(11) DEFAULT NULL COMMENT 'Tree Structure Parent Identifier',
  `ts_level` int(11) NOT NULL COMMENT 'Tree Structure Level',
  `cm_id` int(11) DEFAULT NULL COMMENT 'Class Method Id',
  PRIMARY KEY (`ts_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tree_structure`
--

LOCK TABLES `tree_structure` WRITE;
/*!40000 ALTER TABLE `tree_structure` DISABLE KEYS */;
INSERT INTO `tree_structure` VALUES (0,'/Users/fannyriveraortiz/Desktop/Input/HRMApplication','Root',-1,0,NULL),(1,'model/ModuleDatabase/saveModules.txt','Node',0,1,280),(2,'controller/ModuleController/saveModules.txt','Internal',1,2,32),(3,'test/TestModuleDb/main.txt','Internal',2,3,4),(4,'model/EmployeeDatabase/loadEmployee.txt','Node',0,2,268),(5,'test/TestEmployeeDb/main.txt','Internal',4,2,5),(6,'controller/EmployeeController/loadEmployee.txt','Internal',4,2,21),(7,'model/EmployeeDatabase/maxNumEmployee.txt','Node',0,2,272),(8,'test/TestEmployeeDb/main.txt','Internal',7,2,5),(9,'controller/EmployeeController/maxNumEmployee.txt','Internal',7,2,28),(10,'model/EmployeeDatabase/load.txt','Node',0,2,261),(11,'test/TestEmployeeDb/main.txt','Internal',10,2,5),(12,'controller/EmployeeController/load.txt','Internal',10,2,16),(13,'model/EmployeeDatabase/validateFullName.txt','Node',0,2,271),(14,'test/TestEmployeeDb/main.txt','Internal',13,2,5),(15,'controller/EmployeeController/validateFullName.txt','Internal',13,2,27),(16,'model/EmployeeDatabase/validateDeleteEmployee2.txt','Node',0,2,273),(17,'test/TestEmployeeDb/main.txt','Internal',16,2,5),(18,'controller/EmployeeController/validateDeleteEmployee2.txt','Internal',16,2,29),(19,'model/EmployeeDatabase/loadSupervisors.txt','Node',0,2,269),(20,'test/TestEmployeeDb/main.txt','Internal',19,2,5),(21,'controller/EmployeeController/loadEmployee.txt','Internal',19,2,21),(22,'model/EmployeeDatabase/validateDeleteEmployee1.txt','Node',0,2,270),(23,'test/TestEmployeeDb/main.txt','Internal',22,2,5),(24,'controller/EmployeeController/validateDeleteEmployee1.txt','Internal',22,2,26),(25,'model/EmployeeDatabase/save.txt','Node',0,2,264),(26,'test/TestEmployeeDb/main.txt','Internal',25,2,5),(27,'controller/EmployeeController/save.txt','Internal',25,2,18),(28,'model/EmployeeDatabase/delete.txt','Node',0,2,262),(29,'controller/EmployeeController/delete.txt','Internal',28,2,17),(30,'model/UserDatabase/validateDeleteUser.txt','Node',0,2,208),(31,'test/TestUserDb/main.txt','Internal',30,2,3),(32,'controller/UserController/validateDeleteUser.txt','Internal',30,2,43),(33,'model/UserDatabase/validateEmail.txt','Node',0,2,207),(34,'test/TestUserDb/main.txt','Internal',33,2,3),(35,'controller/UserController/validateEmail.txt','Internal',33,2,42),(36,'model/UserDatabase/load.txt','Node',0,2,197),(37,'test/TestUserDb/main.txt','Internal',36,2,3),(38,'controller/UserController/load.txt','Internal',36,2,33),(39,'model/UserDatabase/validateEmployee.txt','Node',0,2,205),(40,'test/TestUserDb/main.txt','Internal',39,2,3),(41,'controller/UserController/validateEmployee.txt','Internal',39,2,39),(42,'model/UserDatabase/validateUserName.txt','Node',0,2,206),(43,'test/TestUserDb/main.txt','Internal',42,2,3),(44,'controller/UserController/validateUserName.txt','Internal',42,2,41),(45,'model/UserDatabase/getUserLevel.txt','Node',0,2,202),(46,'model/UserDatabase/save.txt','Node',0,2,201),(47,'test/TestUserDb/main.txt','Internal',46,2,3),(48,'controller/UserController/save.txt','Internal',46,2,35),(49,'model/UserDatabase/validateUser.txt','Node',0,2,209),(50,'controller/UserController/validateUser.txt','Internal',49,2,44),(51,'test/TestLoginDb/main.txt','Internal',50,3,2),(52,'model/UserDatabase/loadUser.txt','Node',0,2,204),(53,'test/TestUserDb/main.txt','Internal',52,2,3),(54,'controller/UserController/loadUser.txt','Internal',52,2,37),(55,'model/UserDatabase/loadFullNameEmployees.txt','Node',0,2,211),(56,'test/TestUserDb/main.txt','Internal',55,2,3),(57,'controller/UserController/loadUser.txt','Internal',55,2,37),(58,'model/UserDatabase/delete.txt','Node',0,2,199),(59,'controller/UserController/delete.txt','Internal',58,2,34),(60,'model/TravelRequestDatabase/loadTravelRequest.txt','Node',0,2,257),(61,'test/TestTravelRequestDb/main.txt','Internal',60,2,1),(62,'controller/TravelRequestController/loadTravelRequest.txt','Internal',60,2,12),(63,'model/TravelRequestDatabase/load.txt','Node',0,2,248),(64,'test/TestTravelRequestDb/main.txt','Internal',63,2,1),(65,'controller/TravelRequestController/load.txt','Internal',63,2,6),(66,'model/TravelRequestDatabase/maxNumTravelRequest.txt','Node',0,2,252),(67,'test/TestTravelRequestDb/main.txt','Internal',66,2,1),(68,'controller/TravelRequestController/maxNumTravelRequest.txt','Internal',66,2,9),(69,'model/TravelRequestDatabase/save.txt','Node',0,2,251),(70,'test/TestTravelRequestDb/main.txt','Internal',69,2,1),(71,'controller/TravelRequestController/save.txt','Internal',69,2,8),(72,'model/TravelRequestDatabase/loadFullNameEmployees.txt','Node',0,2,258),(73,'test/TestTravelRequestDb/main.txt','Internal',72,2,1),(74,'controller/TravelRequestController/loadTravelRequest.txt','Internal',72,2,12),(75,'model/TravelRequestDatabase/delete.txt','Node',0,2,249),(76,'test/TestTravelRequestDb/main.txt','Internal',75,2,1),(77,'controller/TravelRequestController/delete.txt','Internal',75,2,7);
/*!40000 ALTER TABLE `tree_structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ForensicReadyLogger'
--

--
-- Dumping routines for database 'ForensicReadyLogger'
--
/*!50003 DROP PROCEDURE IF EXISTS `create_structure_text_file_header_1` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_structure_text_file_header_1`()
BEGIN

   /*Update the Text in every record*/
   
   /*Package Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = 'package configuration_property_1;',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number = 1;

   /*Framework Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = 'import configuration_property_1;',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number = 8;
      
   /*Aspect Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = 'public aspect configuration_property_1',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number = 10;
      
   /*Attribute Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = '   String configuration_property_1;',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number between 12 and 21;

   /*Attribute Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = '   String configuration_property_1;',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number between 23 and 30;
      
   /*Attribute Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = '   String configuration_property_1;',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number between 32 and 39;      

   /* PointCut #1 */
   
   /* PointCut Name*/
   update text_file_details tfd
   set tfd.tfd_text  = '   pointcut configuration_property_1(): ', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number  = 53;
   
   /* Method Name*/
   update text_file_details tfd
   set tfd.tfd_text  = '   	                      call(configuration_property_1);', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number  = 54;
   
   update text_file_details tfd
   set tfd.tfd_text  = '   before(): configuration_property_1()', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number  = 110;   
   
    /* PointCut #2 */
    
   /* PointCut Name*/    
   update text_file_details tfd
   set tfd.tfd_text  = '   pointcut configuration_property_1(): ', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number  = 213;  
   
   /* Method Name*/
   update text_file_details tfd
   set tfd.tfd_text  = '   	                      execution(configuration_property_1);', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number  = 214;    
   
   /* PointCut Name*/
   update text_file_details tfd
   set tfd.tfd_text  = '   before(): configuration_property_1()', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 1 and
      tfd.tfd_line_number  = 216;    
   
   /*Update the Property Identifier in every record*/
   update text_file_details tfd set tfd.tfp_id  = 1 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 1;
   update text_file_details tfd set tfd.tfp_id  = 2 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 8;
   update text_file_details tfd set tfd.tfp_id  = 3 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 10;
   
   update text_file_details tfd set tfd.tfp_id  = 4 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 12;
   update text_file_details tfd set tfd.tfp_id  = 5 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 13;
   update text_file_details tfd set tfd.tfp_id  = 6 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 14;
   update text_file_details tfd set tfd.tfp_id  = 7 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 15;
   update text_file_details tfd set tfd.tfp_id  = 8 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 16;
   update text_file_details tfd set tfd.tfp_id  = 9 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 17;
   update text_file_details tfd set tfd.tfp_id  = 10 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 18;
   update text_file_details tfd set tfd.tfp_id  = 11 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 19;
   update text_file_details tfd set tfd.tfp_id  = 12 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 20;
   update text_file_details tfd set tfd.tfp_id  = 13 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 21;
   
   update text_file_details tfd set tfd.tfp_id  = 14 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 23;
   update text_file_details tfd set tfd.tfp_id  = 15 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 24;
   update text_file_details tfd set tfd.tfp_id  = 16 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 25;
   update text_file_details tfd set tfd.tfp_id  = 17 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 26;
   update text_file_details tfd set tfd.tfp_id  = 18 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 27;
   update text_file_details tfd set tfd.tfp_id  = 19 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 28;
   update text_file_details tfd set tfd.tfp_id  = 20 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 29;
   update text_file_details tfd set tfd.tfp_id  = 21 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 30;
   
   update text_file_details tfd set tfd.tfp_id  = 22 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 32;
   update text_file_details tfd set tfd.tfp_id  = 23 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 33;
   update text_file_details tfd set tfd.tfp_id  = 24 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 34;
   update text_file_details tfd set tfd.tfp_id  = 25 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 35;
   update text_file_details tfd set tfd.tfp_id  = 26 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 36;
   update text_file_details tfd set tfd.tfp_id  = 27 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 37;
   update text_file_details tfd set tfd.tfp_id  = 28 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 38;
   update text_file_details tfd set tfd.tfp_id  = 29 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 39;
   
   update text_file_details tfd set tfd.tfp_id  = 30 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 53;
   update text_file_details tfd set tfd.tfp_id  = 31 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 54;
   update text_file_details tfd set tfd.tfp_id  = 30 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 110;
   
   update text_file_details tfd set tfd.tfp_id  = 32 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 213;
   update text_file_details tfd set tfd.tfp_id  = 33 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 214;
   update text_file_details tfd set tfd.tfp_id  = 32 where tfd.tfh_id  = 1 and tfd.tfd_line_number = 216;

   /*Delete the records that are not required*/
   delete from text_file_details
   where 
      tfh_id  = 1 and
      tfd_line_number between 55 and 108;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_structure_text_file_header_2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_structure_text_file_header_2`()
BEGIN

   /*Update the Text in every record*/
   
   /*Package Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = 'package configuration_property_1;',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number = 1;

   /*Aspect Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = 'public aspect configuration_property_1',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number = 12;
      
   /*Attribute Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = '   String configuration_property_1;',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number between 14 and 16;

   /*Attribute Name*/
   update text_file_details tfd
   set 
      tfd.tfd_text  = '   String configuration_property_1;',
      tfd.tfd_type  = 'Personalized'
   where 
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number between 18 and 20;

   /* PointCut #1 */
   
   /* PointCut Name*/
   update text_file_details tfd
   set tfd.tfd_text  = '   pointcut configuration_property_1(): ', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 2 and 
	  tfd.tfd_line_number  = 26;
   
   /* Method Name*/
   update text_file_details tfd
   set tfd.tfd_text  = '   	                      execution(configuration_property_1);', 
         tfd.tfd_type  = 'Personalized' 
   where  
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number  = 27;
   
   update text_file_details tfd
   set tfd.tfd_text  = '   after(): configuration_property_1()', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number  = 29;
   
    /* PointCut #2 */
    
   /* PointCut Name*/    
   update text_file_details tfd
   set tfd.tfd_text  = '   pointcut configuration_property_1(): ', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number  = 40;
   
   /* Method Name*/
   update text_file_details tfd
   set tfd.tfd_text  = '   	                      call(configuration_property_1);', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number  = 41;   
   
   /* PointCut Name*/
   update text_file_details tfd
   set tfd.tfd_text  = '   before(): configuration_property_1()', 
         tfd.tfd_type  = 'Personalized' 
   where 
      tfd.tfh_id  = 2 and
      tfd.tfd_line_number  = 43; 
   
   /*Update the Property Identifier in every record*/
   update text_file_details tfd set tfd.tfp_id  = 1 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 1;
   
   update text_file_details tfd set tfd.tfp_id  = 2 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 12;
   
   update text_file_details tfd set tfd.tfp_id  = 3 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 14;
   update text_file_details tfd set tfd.tfp_id  = 4 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 15;
   update text_file_details tfd set tfd.tfp_id  = 5 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 16;
   
   update text_file_details tfd set tfd.tfp_id  = 6 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 18;
   update text_file_details tfd set tfd.tfp_id  = 7 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 19;
   update text_file_details tfd set tfd.tfp_id  = 8 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 20;
   
   update text_file_details tfd set tfd.tfp_id  = 9 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 26;
   update text_file_details tfd set tfd.tfp_id  = 10 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 27;
   update text_file_details tfd set tfd.tfp_id  = 9 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 29;
   
   update text_file_details tfd set tfd.tfp_id  = 11 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 40;
   update text_file_details tfd set tfd.tfp_id  = 12 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 41;
   update text_file_details tfd set tfd.tfp_id  = 11 where tfd.tfh_id  = 2 and tfd.tfd_line_number = 43;
   
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-31  9:36:37
