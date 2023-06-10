package frl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

import org.aspectj.lang.Signature;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;

import java.util.Arrays;
import java.util.List;
import java.util.List;

public aspect FrlDatabaseMethods_2
{
   String projectName="OpenHospital";
   String projectOutputDir="C:\\Users\\friverao\\eclipse-workspace\\Directories\\Output\\";
   String umlSeqDiagTextFileName1="IncidentSequenceDiagram.txt";
   String guiLibs="java.awt;javax.swing;com.github.lgooddatepicket;javax.image.io;org.dcm4che2;org.imgscalr;java.beans;com.jgoodies.forms;com.jgoodies.binding;com.github.sarxos.webcam;org.jivesoftware.smack;org.isf.utils.jobjects.ModalJFrame;";
   String bluePrintObject1="class";
   String objectOrientedDelimiter1=".";
   String startParameters="(";
   String endParameters=")";
   String newLine1="%n";
   String whiteSpaceWordsDelimiter1=" ";
   
   String guiLibDelimiter=";";
   String invalidSpecialCharacter="$";
   String validSpecialCharacter="_";
   String member1="actor";
   String member2="participant";
   String startSendMessage="->";
   String endSendMessage=":";
   String userName="User";

   String startNote="note left of";
   String endNote="end note";
   String colorDelimiter="#";
   String colorNote="lightskyblue";
   String position="order";
   String space="||20||";
   String startDivision="== Connects to the";
   String endDivision="==";
   String connectProjectMethod="org.isf.menu.gui.Login.acceptPwd";
   Object connectProjectMethodReturnValue=true;
   String endUMLSeqDiagram="@enduml";
   String umlSeqDiagPngFileName1="IncidentSequenceDiagram.png";
   String umlSeqDiagTextFileName2="IncidentSequenceDiagramTEMPORAL.txt";
   
   String umlSeqDiagTextFile=projectOutputDir + umlSeqDiagTextFileName1;
   String callerClass="", calleeClass="", callerClassLine, calleeClassLine;
   String packageMethod="", classMethod="";
   String nameMethod="", fullMethod="", callerSuperClass="", calleeSuperClass="";
   String callerType="", calleeType="", note="";
   String line="", content="", currentUser="", fullMethodName="";
   int actorOrder=1, partOrder=0, methodsCounter=0, userCounter=1;
   boolean callerGuiFlag= false, calleeGuiFlag=false;
   Class<?> callerClassObj1, callerClassObj2, calleeClassObj1, calleeClassObj2; 
   Signature method;
   Object[] methodArgs;
   String umlSeqDiagPngFile=projectOutputDir  + umlSeqDiagPngFileName1;
   int numberLines=0;
   String umlSeqDiagTextFile2=projectOutputDir + umlSeqDiagTextFileName2;
   File inputFile  = new File(umlSeqDiagTextFile);
   File outputFile = new File(umlSeqDiagTextFile2);
   String errorMessage1="", errorMessage2="";
   
   /* Evaluation Start (DECLARE VARIABLES) */
   long time1, time2, duration;
   /* Evaluation End */
   
   pointcut databaseMethods(): 
      call(void org.isf.distype.test.Tests.testMgrValidateDiseaseTypeDescriptionEmpty(..)) || 
      call(void org.isf.distype.test.Tests.testDiseaseTypeEqualHashToString(..)) || 
      call(java.lang.String org.isf.distype.test.Tests.setupTestDiseaseType(..)) || 
      call(void org.isf.distype.test.Tests.checkDiseaseTypeIntoDb(..)) || 
      call(void org.isf.hospital.test.Tests.testIoGetHospital(..)) || 
      call(void org.isf.hospital.test.Tests.setUp(..)) || 
      call(void org.isf.hospital.test.Tests.testHospitalGets(..)) || 
      call(void org.isf.hospital.test.Tests.testHospitalSets(..)) || 
      call(void org.isf.hospital.test.Tests.testIoHospitalSanitize(..)) || 
      call(void org.isf.hospital.test.Tests.testMgrGetHospital(..)) || 
      call(void org.isf.hospital.test.Tests.testMgrUpdateHospital(..)) || 
      call(void org.isf.hospital.test.Tests.testHospitalGetterSetter(..)) || 
      call(void org.isf.hospital.test.Tests.testHospitalHashToString(..)) || 
      call(void org.isf.hospital.test.Tests.checkHospitalIntoDb(..)) || 
      call(void org.isf.hospital.test.Tests.testIoUpdateHospital(..)) || 
      call(void org.isf.patvac.test.Tests.setUpClass(..)) || 
      call(void org.isf.patvac.test.Tests.setUp(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrGetProgYear(..)) || 
      call(void org.isf.patvac.test.Tests.testPatientVaccineGets(..)) || 
      call(void org.isf.patvac.test.Tests.testPatientVaccineSets(..)) || 
      call(void org.isf.patvac.test.Tests.testIoGetPatientVaccineToday(..)) || 
      call(void org.isf.patvac.test.Tests.testIoGetPatientVaccineLastWeek(..)) || 
      call(void org.isf.patvac.test.Tests.testIoGetPatientVaccine(..)) || 
      call(void org.isf.patvac.test.Tests.testIoUpdatePatientVaccine(..)) || 
      call(void org.isf.patvac.test.Tests.testIoDeletePatientVaccine(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrGetPatientVaccineToday(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrGetPatientVaccineLastWeek(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrGetPatientVaccine(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrNewPatientVaccine(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrDeletePatientVaccine(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrValidationProgrLessThanZero(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrValidationPatientNameIsEmpty(..)) || 
      call(void org.isf.patvac.test.Tests.testMgrValidationPatientSexIsEmpty(..)) || 
      call(int org.isf.patvac.test.Tests.setupTestPatientVaccine(..)) || 
      call(void org.isf.patvac.test.Tests.checkPatientVaccineIntoDb(..)) || 
      call(void org.isf.patvac.test.Tests.testListenerShouldUpdatePatientToMergedWhenPatientMergedEventArrive(..)) || 
      call(void org.isf.supplier.test.Tests.setUpClass(..)) || 
      call(void org.isf.supplier.test.Tests.setUp(..)) || 
      call(void org.isf.supplier.test.Tests.testSupplierGets(..)) || 
      call(void org.isf.supplier.test.Tests.testSupplierSets(..)) || 
      call(void org.isf.supplier.test.Tests.testIoSupplierSaveOrUpdate(..)) || 
      call(void org.isf.supplier.test.Tests.testIoSupplierGetByID(..)) || 
      call(void org.isf.supplier.test.Tests.testIoSupplierGetAll(..)) || 
      call(void org.isf.supplier.test.Tests.testIoSupplierGetList(..)) || 
      call(void org.isf.supplier.test.Tests.testMgrSupplierSaveOrUpdate(..)) || 
      call(void org.isf.supplier.test.Tests.testMgrSupplierGetByID(..)) || 
      call(void org.isf.supplier.test.Tests.testMgrSupplierGetAll(..)) || 
      call(void org.isf.supplier.test.Tests.testMgrSupplierGetList(..)) || 
      call(void org.isf.supplier.test.Tests.testMgrGetHashMap(..)) || 
      call(void org.isf.supplier.test.Tests.testSupplierToString(..)) || 
      call(void org.isf.supplier.test.Tests.testSupplierEquals(..)) || 
      call(void org.isf.supplier.test.Tests.testSupplierHashCode(..)) || 
      call(int org.isf.supplier.test.Tests.setupTestSupplier(..)) || 
      call(void org.isf.supplier.test.Tests.checkSupplierIntoDb(..)) || 
      call(void org.isf.patient.test.Tests.setUpClass(..)) || 
      call(void org.isf.patient.test.Tests.setUp(..)) || 
      call(void org.isf.patient.test.Tests.testPatientGets(..)) || 
      call(void org.isf.patient.test.Tests.testPatientSets(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatients(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientsPageable(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientsByOneOfFieldsLikeMiddleOfFirstName(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientsByOneOfFieldsLikeSecondName(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientsByOneOfFieldsLikeNote(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientsByOneOfFieldsLikeNotExistingStringShouldNotFindAnything(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientFromName(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientFromNameDoesNotExist(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientFromCodeDoesNotExist(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientAll(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetPatientsByParams(..)) || 
      call(void org.isf.patient.test.Tests.testIoSaveNewPatient(..)) || 
      call(void org.isf.patient.test.Tests.testIoUpdatePatient(..)) || 
      call(void org.isf.patient.test.Tests.testIoDeletePatient(..)) || 
      call(void org.isf.patient.test.Tests.testIoGetNextPatientCode(..)) || 
      call(void org.isf.patient.test.Tests.testMergePatientHistory(..)) || 
      call(void org.isf.patient.test.Tests.testMgrGetPatientsByOneOfFieldsLikeFirstName(..)) || 
      call(void org.isf.patient.test.Tests.testMgrGetPatientsByOneOfFieldsLikeNote(..)) || 
      call(void org.isf.patient.test.Tests.testMgrGetPatientByName(..)) || 
      call(void org.isf.patient.test.Tests.testMgrGetPatientByNameDoesNotExist(..)) || 
      call(void org.isf.patient.test.Tests.testMgrGetPatientById(..)) || 
      call(void org.isf.patient.test.Tests.testMgrGetPatientAll(..)) || 
      call(void org.isf.patient.test.Tests.testMgrDeletePatient(..)) || 
      call(void org.isf.patient.test.Tests.testMgrIsNamePresent(..)) || 
      call(void org.isf.patient.test.Tests.testMgrGetProfessionTranslated(..)) || 
      call(void org.isf.patient.test.Tests.testPatientHashCode(..)) || 
      call(void org.isf.patient.test.Tests.testPatientProfilePhoto(..)) || 
      call(void org.isf.patient.test.Tests.assertThatObsoletePatientWasDeletedAndMergedIsTheActiveOne(..)) || 
      call(void org.isf.patient.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.patient.TestMergePatient.testWholeMergeOperationShouldBeRolledBackWhenOneOfUpdateOperationsFails(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergePatient(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergePatientPatient1MissingInformation(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergePatientMergeNotes(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergePatientMergeBirthDateMissingAgeTypePatient2HasBirthDate(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergePatientMergeBirthDatePatient2HasAgeType(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrWholeMergeOperationShouldBeRolledBackWhenOneOfUpdateOperationsFails(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergeValidationSexNotTheSame(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergeValidationPatient1PendingBills(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergeValidationPatient2PendingBills(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergeValidationPatient1Admitted(..)) || 
      call(void org.isf.patient.TestMergePatient.testMgrMergeValidationPatient2Admitted(..)) || 
      call(void org.isf.patient.TestMergePatient.assertThatObsoletePatientWasNotDeletedAndIsTheActiveOne(..)) || 
      call(void org.isf.patient.TestMergePatient.assertThatVisitWasMovedFromObsoleteToMergedPatient(..)) || 
      call(void org.isf.patient.TestMergePatient.assertThatVisitIsStillAssignedToObsoletePatient(..)) || 
      call(void org.isf.patient.TestMergePatient.assertThatExaminationWasMovedFromObsoleteToMergedPatient(..)) || 
      call(void org.isf.patient.TestMergePatient.assertThatExaminationIsStillAssignedToObsoletePatient(..)) || 
      call(org.isf.visits.model.Visit org.isf.patient.TestMergePatient.setupVisitAndAssignPatient(..)) || 
      call(org.isf.examination.model.PatientExamination org.isf.patient.TestMergePatient.setupPatientExaminationAndAssignPatient(..)) || 
      call(void org.isf.patient.TestMergePatient.testMergePatientHistory(..)) || 
      call(void org.isf.patient.TestMergePatient.assertThatObsoletePatientWasDeletedAndMergedIsTheActiveOne(..)) || 
      call(void org.isf.menu.test.Tests.checkUserGroupIntoDb(..)) || 
      call(void org.isf.menu.test.Tests.setUpClass(..)) || 
      call(void org.isf.menu.test.Tests.setUp(..)) || 
      call(void org.isf.menu.test.Tests.testIoIsUserNamePresent(..)) || 
      call(void org.isf.menu.test.Tests.testIoIsGroupNamePresent(..)) || 
      call(void org.isf.menu.test.Tests.testUserGroupGets(..)) || 
      call(void org.isf.menu.test.Tests.testUserGroupSets(..)) || 
      call(void org.isf.menu.test.Tests.testUserGets(..)) || 
      call(void org.isf.menu.test.Tests.testUserSets(..)) || 
      call(void org.isf.menu.test.Tests.testUserMenuGets(..)) || 
      call(void org.isf.menu.test.Tests.testGroupMenuSets(..)) || 
      call(void org.isf.menu.test.Tests.testIoGetUser(..)) || 
      call(void org.isf.menu.test.Tests.testIoGetUserByName(..)) || 
      call(void org.isf.menu.test.Tests.testIoGetUserInfo(..)) || 
      call(void org.isf.menu.test.Tests.testIoGetUserGroup(..)) || 
      call(void org.isf.menu.test.Tests.testIoNewUser(..)) || 
      call(void org.isf.menu.test.Tests.testIoUpdateUser(..)) || 
      call(void org.isf.menu.test.Tests.testIoUpdatePassword(..)) || 
      call(void org.isf.menu.test.Tests.testIoDeleteUser(..)) || 
      call(void org.isf.menu.test.Tests.testIoGetGroupMenu(..)) || 
      call(void org.isf.menu.test.Tests.testIoSetGroupMenu(..)) || 
      call(void org.isf.menu.test.Tests.testIoDeleteGroup(..)) || 
      call(void org.isf.menu.test.Tests.testIoNewUserGroup(..)) || 
      call(void org.isf.menu.test.Tests.testIoUpdateUserGroup(..)) || 
      call(void org.isf.menu.test.Tests.testMgrGetUserByName(..)) || 
      call(void org.isf.menu.test.Tests.testMgrGetUserInfo(..)) || 
      call(void org.isf.menu.test.Tests.testMgrDeleteGroup(..)) || 
      call(void org.isf.menu.test.Tests.testMgrDeleteGroupAdminGroup(..)) || 
      call(void org.isf.menu.test.Tests.testMgrGetUserGroup(..)) || 
      call(void org.isf.menu.test.Tests.testMgrNewUser(..)) || 
      call(void org.isf.menu.test.Tests.testMgrUpdatePassword(..)) || 
      call(void org.isf.menu.test.Tests.testMgrNewUserGroupAlreadyExists(..)) || 
      call(void org.isf.menu.test.Tests.testUserGroupToString(..)) || 
      call(void org.isf.menu.test.Tests.testUserGroupEquals(..)) || 
      call(java.lang.String org.isf.menu.test.Tests.setupTestUser(..)) || 
      call(void org.isf.menu.test.Tests.checkUserMenuIntoDb(..)) || 
      call(java.lang.Integer org.isf.menu.test.Tests.setupTestGroupMenu(..)) || 
      call(void org.isf.menu.test.Tests.checkGroupMenuIntoDb(..)) || 
      call(void org.isf.ward.test.Tests.setUp(..)) || 
      call(void org.isf.ward.test.Tests.testMgrValidationDescriptionEmpty(..)) || 
      call(void org.isf.ward.test.Tests.testIoGetCurrentOccupation(..)) || 
      call(void org.isf.ward.test.Tests.testIoGetWardsNoMaternity(..)) || 
      call(void org.isf.ward.test.Tests.testWardGets(..)) || 
      call(void org.isf.ward.test.Tests.testWardSets(..)) || 
      call(void org.isf.ward.test.Tests.testIoGetWards(..)) || 
      call(void org.isf.ward.test.Tests.testIoGetWardsGetAll(..)) || 
      call(void org.isf.ward.test.Tests.testIoNewWard(..)) || 
      call(void org.isf.ward.test.Tests.testIoUpdateWard(..)) || 
      call(void org.isf.ward.test.Tests.testIoUpdateWardNoCodePresent(..)) || 
      call(void org.isf.ward.test.Tests.testIoDeleteWard(..)) || 
      call(void org.isf.ward.test.Tests.testIoIsCodePresentFalse(..)) || 
      call(void org.isf.ward.test.Tests.testIoFindWardNull(..)) || 
      call(void org.isf.ward.test.Tests.testMgrGetCurrentOccupationNoAdmissions(..)) || 
      call(void org.isf.ward.test.Tests.testMgrGetCurrentOccupation(..)) || 
      call(void org.isf.ward.test.Tests.testMgrGetWardsGetAll(..)) || 
      call(void org.isf.ward.test.Tests.testMgrDeleteWardNoPatients(..)) || 
      call(void org.isf.ward.test.Tests.testMgrDeleteWardMaternity(..)) || 
      call(void org.isf.ward.test.Tests.testMgrDeleteWardWithPatients(..)) || 
      call(void org.isf.ward.test.Tests.testMgrMaternityControlCreate(..)) || 
      call(void org.isf.ward.test.Tests.testMgrFindWard(..)) || 
      call(void org.isf.ward.test.Tests.checkWardIntoDb(..)) || 
      call(void org.isf.ward.test.Tests.testIoGetCurrentOccupationNoAdmissions(..)) || 
      call(void org.isf.ward.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.ward.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.pricesothers.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.pricesothers.test.Tests.testPricesOthersGets(..)) || 
      call(void org.isf.pricesothers.test.Tests.testPricesOthersSets(..)) || 
      call(void org.isf.pricesothers.test.Tests.testIoGetPricesOthers(..)) || 
      call(void org.isf.pricesothers.test.Tests.testIoUpdatePricesOthers(..)) || 
      call(void org.isf.pricesothers.test.Tests.testIoNewPricesOthers(..)) || 
      call(void org.isf.pricesothers.test.Tests.testMgrGetPricesOthers(..)) || 
      call(void org.isf.pricesothers.test.Tests.testMgrUpdatePricesOthers(..)) || 
      call(void org.isf.pricesothers.test.Tests.testMgrDeletePricesOther(..)) || 
      call(void org.isf.pricesothers.test.Tests.testMgrValidationCodeEmpty(..)) || 
      call(void org.isf.pricesothers.test.Tests.testPricesOthersToString(..)) || 
      call(void org.isf.pricesothers.test.Tests.testPricesOthersEquals(..)) || 
      call(void org.isf.pricesothers.test.Tests.testPricesOthersHashCode(..)) || 
      call(int org.isf.pricesothers.test.Tests.setupTestPricesOthers(..)) || 
      call(void org.isf.examination.test.Tests.setUpClass(..)) || 
      call(void org.isf.examination.test.Tests.setUp(..)) || 
      call(org.isf.patient.model.Patient org.isf.examination.test.Tests.setupTestPatient(..)) || 
      call(void org.isf.examination.test.Tests.testIoListenerShouldUpdatePatientToMergedWhenPatientMergedEventArrive(..)) || 
      call(void org.isf.examination.test.Tests.testPatientExaminationGets(..)) || 
      call(void org.isf.examination.test.Tests.testIoGetFromLastPatientExamination(..)) || 
      call(void org.isf.examination.test.Tests.testIoSaveOrUpdate(..)) || 
      call(void org.isf.examination.test.Tests.testIoGetByID(..)) || 
      call(void org.isf.examination.test.Tests.testIoGetLastByPatID(..)) || 
      call(void org.isf.examination.test.Tests.testIoGetLastNByPatID(..)) || 
      call(void org.isf.examination.test.Tests.testIoGetByPatID(..)) || 
      call(void org.isf.examination.test.Tests.testIoRemove(..)) || 
      call(void org.isf.examination.test.Tests.testMgrSaveOrUpdate(..)) || 
      call(void org.isf.examination.test.Tests.testMgrGetLastByPatID(..)) || 
      call(void org.isf.examination.test.Tests.testMgrGetLastNByPatID(..)) || 
      call(void org.isf.examination.test.Tests.testMgrGetByPatID(..)) || 
      call(void org.isf.examination.test.Tests.testMgrRemove(..)) || 
      call(void org.isf.examination.test.Tests.testMgrGetAuscultationKey(..)) || 
      call(void org.isf.examination.test.Tests.testGetBMIdescription(..)) || 
      call(void org.isf.examination.test.Tests.testGetDefaultPatientExamination(..)) || 
      call(void org.isf.examination.test.Tests.testMgrExaminationValidation(..)) || 
      call(void org.isf.examination.test.Tests.testPatientExaminationGettersSetters(..)) || 
      call(void org.isf.vactype.test.Tests.setUpClass(..)) || 
      call(void org.isf.vactype.test.Tests.setUp(..)) || 
      call(void org.isf.vactype.test.Tests.testVaccineTypeGets(..)) || 
      call(void org.isf.vactype.test.Tests.testVaccineTypeSets(..)) || 
      call(void org.isf.vactype.test.Tests.testIoGetVaccineType(..)) || 
      call(void org.isf.vactype.test.Tests.testIoUpdateVaccineType(..)) || 
      call(void org.isf.vactype.test.Tests.testIoDeleteVaccineType(..)) || 
      call(void org.isf.vactype.test.Tests.testIoFindVaccineType(..)) || 
      call(void org.isf.vactype.test.Tests.testMgrUpdateVaccineType(..)) || 
      call(void org.isf.vactype.test.Tests.testMgrFindVaccineType(..)) || 
      call(void org.isf.vactype.test.Tests.testMgrFindVaccineTypeNull(..)) || 
      call(void org.isf.vactype.test.Tests.testMgrValidationCodeExists(..)) || 
      call(void org.isf.vactype.test.Tests.testVaccineToString(..)) || 
      call(void org.isf.vactype.test.Tests.testVaccinePrint(..)) || 
      call(void org.isf.vactype.test.Tests.testVaccineEquals(..)) || 
      call(void org.isf.vactype.test.Tests.testVaccineTypeHashCode(..)) || 
      call(void org.isf.vactype.test.Tests.checkVaccineTypeIntoDb(..)) || 
      call(void org.isf.vactype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.vactype.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.vactype.test.Tests.testMgrValidationCodeEmpty(..)) || 
      call(void org.isf.vaccine.test.Tests.testIoGetVaccineShouldFindByTypeCode(..)) || 
      call(void org.isf.vaccine.test.Tests.setUpClass(..)) || 
      call(void org.isf.vaccine.test.Tests.setUp(..)) || 
      call(void org.isf.vaccine.test.Tests.testMgrValidationCodeExists(..)) || 
      call(void org.isf.vaccine.test.Tests.testVaccineToString(..)) || 
      call(void org.isf.vaccine.test.Tests.testIoGetVaccineShouldFindAllVaccinesWhenNoCodeProvided(..)) || 
      call(void org.isf.vaccine.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.vaccine.test.Tests.testIoDeleteVaccine(..)) || 
      call(void org.isf.vaccine.test.Tests.testIoFindVaccine(..)) || 
      call(void org.isf.vaccine.test.Tests.testIoFindVaccineNull(..)) || 
      call(void org.isf.vaccine.test.Tests.testMgrGetVaccineShouldFindByTypeCode(..)) || 
      call(void org.isf.vaccine.test.Tests.testMgrGetVaccineShouldFindAllVaccinesWhenNoCodeProvided(..)) || 
      call(void org.isf.vaccine.test.Tests.testMgrNewVaccine(..)) || 
      call(void org.isf.vaccine.test.Tests.testMgrDeleteVaccine(..)) || 
      call(void org.isf.vaccine.test.Tests.checkVaccineIntoDb(..)) || 
      call(void org.isf.vaccine.test.Tests.testMgrValidationCodeEmpty(..)) || 
      call(void org.isf.therapy.test.Tests.setUp(..)) || 
      call(void org.isf.therapy.test.Tests.testTherapyRowGets(..)) || 
      call(void org.isf.therapy.test.Tests.testTherapyRowSets(..)) || 
      call(void org.isf.therapy.test.Tests.testIoGetTherapyRow(..)) || 
      call(void org.isf.therapy.test.Tests.testIoGetTherapyRowWithZeroAsIdentifierProvided(..)) || 
      call(void org.isf.therapy.test.Tests.testIoNewTherapyRow(..)) || 
      call(void org.isf.therapy.test.Tests.testIoDeleteTherapyRow(..)) || 
      call(void org.isf.therapy.test.Tests.testMgrGetTherapies(..)) || 
      call(void org.isf.therapy.test.Tests.testMgrGetTherapyRowWithZeroAsIdentifierProvided(..)) || 
      call(void org.isf.therapy.test.Tests.testMgrNewTherapyRow(..)) || 
      call(void org.isf.therapy.test.Tests.testMgrGetMedicalsOutOfStockDayActualGreaterThanNeed(..)) || 
      call(void org.isf.therapy.test.Tests.testTherapyRowHashCode(..)) || 
      call(void org.isf.therapy.test.Tests.testTherapyToString(..)) || 
      call(void org.isf.dicomtype.test.Tests.testDicomTypeGets(..)) || 
      call(void org.isf.dicomtype.test.Tests.testDicomTypeSets(..)) || 
      call(void org.isf.dicomtype.test.Tests.testIoGetDicomType(..)) || 
      call(void org.isf.dicomtype.test.Tests.testIoUpdateDicomType(..)) || 
      call(void org.isf.dicomtype.test.Tests.testIoNewDicomType(..)) || 
      call(void org.isf.dicomtype.test.Tests.testMgrGetDicomType(..)) || 
      call(void org.isf.dicomtype.test.Tests.testMgrUpdateDicomType(..)) || 
      call(void org.isf.dicomtype.test.Tests.testMgrValidationTypeIdIsTooLong(..)) || 
      call(java.lang.String org.isf.dicomtype.test.Tests.setupTestDicomType(..)) || 
      call(void org.isf.dicomtype.test.Tests.checkDicomTypeIntoDb(..)) || 
      call(void org.isf.dicomtype.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.disctype.test.Tests.testMgrDeleteDischargeType(..)) || 
      call(void org.isf.disctype.test.Tests.setUpClass(..)) || 
      call(void org.isf.disctype.test.Tests.setUp(..)) || 
      call(void org.isf.disctype.test.Tests.testMgrValidateDeleteDischargeType(..)) || 
      call(void org.isf.disctype.test.Tests.testIoDeleteDischargeType(..)) || 
      call(void org.isf.disctype.test.Tests.testIoUpdateDischargeType(..)) || 
      call(void org.isf.disctype.test.Tests.testMgrNewDischargeType(..)) || 
      call(void org.isf.dicom.test.TestSqlDicomManager.testLoadPatientFiles(..)) || 
      call(void org.isf.dicom.test.TestSqlDicomManager.testExist(..)) || 
      call(void org.isf.dicom.test.TestSqlDicomManager.testDeleteSerie(..)) || 
      call(long org.isf.dicom.test.TestSqlDicomManager.setupTestFileDicom(..)) || 
      call(void org.isf.pregtreattype.test.Tests.setUpClass(..)) || 
      call(void org.isf.pregtreattype.test.Tests.setUp(..)) || 
      call(void org.isf.pregtreattype.test.Tests.testMgrValidationDescriptionIsEmpty(..)) || 
      call(void org.isf.pregtreattype.test.Tests.testMgrGetPregnantTreatmentType(..)) || 
      call(void org.isf.pregtreattype.test.Tests.testMgrDeletePregnantTreatmentType(..)) || 
      call(void org.isf.pregtreattype.test.Tests.testPregnantTreatmentTypeEquals(..)) || 
      call(void org.isf.pregtreattype.test.Tests.checkPregnantTreatmentTypeIntoDb(..)) || 
      call(void org.isf.pregtreattype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.pregtreattype.test.Tests.testMgrIsCodePresent(..));

  before(): databaseMethods()
   {
	   
      // Obtain the Caller Class Object
	  try
	  {
         callerClassObj1 = thisEnclosingJoinPointStaticPart.getSignature().getDeclaringType();
	  }
	  catch(Exception e1)
      {
         errorMessage1 = e1.getMessage();
         errorMessage2 = "Error FRL: Occurred while getting the Caller Class Object 1." + System.lineSeparator();
         errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
      }
	    
      // Get the Caller Class Details
      try
      {
         getCallerClassDetails(callerClassObj1);
      }
      catch(Exception e2)
      {
         errorMessage1 = e2.getMessage();
         errorMessage2 = "Error FRL: Occurred while getting the Caller Class Details." + System.lineSeparator();
         errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
      }

      // / Obtain the Callee Class Object
      try
      {
         calleeClassObj1 = thisJoinPoint.getTarget().getClass();
      }
	  catch(Exception e3)
      {
         errorMessage1 = e3.getMessage();
         errorMessage2 = "Error FRL: Occurred while getting the Callee Class Object 1." + System.lineSeparator();
         errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
      }
	    
      // Validate the callee Class Object 1 that is not null
      if(calleeClassObj1 != null)
      {	 
    	 // Get the Callee Class Details 
    	 try
    	 {
            getCalleeClassDetails(calleeClassObj1);
    	 }
    	 catch(Exception e4)
         {
            errorMessage1 = e4.getMessage();
            errorMessage2 = "Error FRL: Occurred while getting the Callee Class Details." + System.lineSeparator();
            errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
         }	 
      }
      /*else
      {	  
         calleeClass = "Unknown";
      } */  
        

      // Get the details of the Method being executed
      method        = thisJoinPoint.getSignature();
      packageMethod = method.getDeclaringType().getPackage().toString();
      packageMethod = packageMethod.replace("package ","");  
      classMethod   = method.getDeclaringType().getSimpleName();
      nameMethod    = method.getName();
      methodArgs    = thisJoinPoint.getArgs();
      fullMethod	= nameMethod + startParameters;
      
      // Build the full Method Name
      fullMethodName = packageMethod + "." + classMethod + "." + nameMethod;
 
      // Add in the UML Sequence Diagram the information of a new User Connected
      if(fullMethodName.equals(connectProjectMethod) == true)
      {
         userConnection();
      }
      
      //partOrder++;
      
      // Validate if the CallerClass is a Graphical User Interface Class
      // Build the Caller Class Line
      if(callerGuiFlag == true)
      {	  
    	 // Build the method line 
         fullMethod = fullMethod + endParameters;
         
         // Build the note line
         note = startNote   + whiteSpaceWordsDelimiter1 + currentUser + whiteSpaceWordsDelimiter1 + colorDelimiter + colorNote + 
                whiteSpaceWordsDelimiter1 + newLine1 + callerClass + 
                newLine1 + endNote + newLine1;
         
         // Assign the value to the Caller Class
         if(currentUser.isEmpty() == false)
         {	 
            callerClass = currentUser;
            
            // Build the caller Class Line
            callerClassLine = member1 + whiteSpaceWordsDelimiter1  + callerClass + 
        		              whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + actorOrder;
         }   
         else
        	 callerClass = "Unknown";
         
      }   
      else
      {	  
    	 // Build the method line  
         fullMethod = fullMethod + Arrays.toString(methodArgs) + endParameters;
         
         // The note line will be empty
         note = "";
         
         // Validate that the Caller Class is EMPTY
         if(callerClass.isEmpty()== true)
            callerClass = currentUser;
         /*    callerClass = "Unknown"; 
         else
            // Increase the participants Order Counter
            partOrder++;
         */
         partOrder++;
         
         // Build the caller Class Line
         callerClassLine = member2 + whiteSpaceWordsDelimiter1 + callerClass + 
        		           whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;
      }   
      
      // Validate that the Callee Class is NOT EMPTY
      /*if(calleeClass.equals("Unknown") == false)
         partOrder++;
      */
      partOrder++;
      
      if(calleeClass.isEmpty() == true) 
      {	  
         //calleeClass = "Unknown";
    	  calleeClass = currentUser;
      }    
      
      // Build the callee Class Line
      calleeClassLine = member2 + whiteSpaceWordsDelimiter1 + calleeClass + 
    		            whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;
      
      // Validate if the Callee Class and Caller Class are Known,
      // Then update the UML Sequence Diagram Text and Image Files
      /*if(calleeClass.equals("Unknown") == false && 
    	 callerClass.equals("Unknown") == false)
      { */  
      
      // Generate the Plant UML line to generate the UML Sequence Diagram Text File
      //if (callerClass.equals("Unknown") == false)
      
      /* Evaluation Start (TIME 1) */
	  time1 = System.currentTimeMillis();
	  /* Evaluation End */
	  
 	  updateSeqDiagTextFile(callerClass, calleeClass, fullMethod, note, callerClassLine, calleeClassLine);
 	  
 	  /* Evaluation Start (TIME 2) */
	  time2 = System.currentTimeMillis();
	  duration = time2 - time1;
	  System.out.println("Message FRL: The UML Sequence Diagram for the METHOD EXCHANGE: " + fullMethod +  " was Generated in : " + duration + " milliseconds.");
	  /* Evaluation End */
	  
      //}
      /*else
         partOrder = 1;
      */   
	        
   } 

   pointcut connect(): 
   	                      call(void org.isf.menu.gui.Login.acceptPwd(..));

   Object around() : connect()
   {
	   
      Object methodReturnValue1 = proceed();
      
	  if(methodReturnValue1 != null &&
	     methodReturnValue1.equals(connectProjectMethodReturnValue))
	  {   
	     userCounter++; 
	  }  
      else 
      {
    	 //userCounter = 1;
    	 //partOrder   = 1;
    	  
    	 /* Evaluation Start (TIME 1) */
     	 time1 = System.currentTimeMillis();
     	 /* Evaluation End */  
        		 
    	 // Delete UML Sequence Diagram Image File
         deleteSeqDiagramFile(umlSeqDiagPngFile); 
         
         // Create and update the UML Sequence Diagram Files 
         updateFiles();
         
         /* Evaluation Start (TIME 2) */
    	 time2 = System.currentTimeMillis();
    	 duration = time2 - time1;
    	 System.out.println("Message FRL: The UML Sequence Diagram Files were FINALLY Created in : " + duration + " milliseconds");      
    	 /* Evaluation End */
          
         System.out.println("Message FRL: Good Bye to the Forensic-Ready Logger.");
      }
	  
      return methodReturnValue1;
      
   }
   
   public void updateFiles()
   {
	   
	  // Update the UML Sequence Diagram Text File with the @endUml Instruction  
      content = endUMLSeqDiagram + newLine1;
      content = String.format(content);
      
      // Write the contents into the UML Sequence Diagram Text File
      writeTextFile(umlSeqDiagTextFile, content);
      
	  // Conting the number of lines of the UML Sequence Diagram Text File
	  countLinesTextFile();
	  
	  // Deleting the repeated @enduml instructions
	  deleteLineTextFile();
      
      // Create the UML Sequence Diagram Image File
   	  createSeqDiagramPngFile(umlSeqDiagTextFile);
	      
  	  // Print on the screen Informative Messages
	  //System.out.println("Message FRL: UML Sequence Diagram Text File created  : " + umlSeqDiagTextFile);
	  //System.out.println("Message FRL: UML Sequence Diagram Image File created : " + umlSeqDiagPngFile);
	  
   } 
   
   public void countLinesTextFile()
   {
		try 
		{
			LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(umlSeqDiagTextFile));
			lineNumberReader.skip(Long.MAX_VALUE);
			numberLines = lineNumberReader.getLineNumber();
			lineNumberReader.close();
		} 
		catch (Exception e1) 
		{
		   errorMessage1 = e1.getMessage();
		   errorMessage2 = "Error FRL: Occurred while counting the lines of the UML Sequence Diagram Text File." + System.lineSeparator(); 
		   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		   System.out.println(errorMessage2);
		}		

   }
   
   public  void deleteLineTextFile()
   {
      String line1="";
      int c=0;
      
	  try 
	  {
	     try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		    		
		    BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) 
		 {
		
		    while ((line1 = reader.readLine()) != null) 
		    {
		       c++;
		       
		       if(c != numberLines)
		       {	
		          if (!line1.equals(endUMLSeqDiagram)) 
		          {
		             writer.write(line1);
		             writer.newLine();
		          }
		       }
		       else
		       {		
		          writer.write(line1);
	              writer.newLine();
		       }   
		    }
		 }
		 catch (Exception e1) 
		 {
		    errorMessage1 = e1.getMessage();
			errorMessage2 = "Error FRL: Occurred while writing a new UML Sequence Diagram Text File without double @enduml instructions." + System.lineSeparator();
			errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			System.out.println(errorMessage2);
		 }

		 if (inputFile.delete()) 
		 {
		    // Rename the output file to the input file
		    if (!outputFile.renameTo(inputFile)) 
		    {
		       errorMessage1 = "Error FRL: Ocurred while renaming the File: " + umlSeqDiagTextFile2 + " to " + umlSeqDiagTextFile;	
		       System.out.println(errorMessage1);
		       throw new Exception(errorMessage1);
		    }
		 } 
		 else
		 {
	        errorMessage1 = "Error FRL: Ocurred while deleting the File: " + umlSeqDiagTextFile;
		    System.out.println(errorMessage1);
		    throw new Exception(errorMessage1);
		 }
	  } 
	  catch (Exception e2) 
	  {
	     errorMessage1 = e2.getMessage();
	     errorMessage2 = "Error FRL: Occurred while deleting or renaming the new UML Sequence Diagram Text File without double @enduml instructions." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 System.out.println(errorMessage2);
      }
   }  
   
   public void createSeqDiagramPngFile(String textFile)
   {
      File file1 = new File(textFile); 
	  SourceFileReader reader = null;
	  List<GeneratedImage> list = null;
	  
	  // Create the Reader of the UML Sequence Diagram Text File
	  try 
	  {
	     reader = new SourceFileReader(file1);
	  } 
	  catch (IOException e1) 
	  {
	     errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error FRL: Occurred while reading the UML Sequence Diagram Text File: " + textFile + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
	     System.out.println(errorMessage2);
	  }
	   
	  // Generate the list of images using the Sequence Diagram Text File
	  try 
	  {
	     list = reader.getGeneratedImages();
	  } 
	  catch (IOException e2) 
	  {
	     errorMessage1 = e2.getMessage();
		 errorMessage2 = "Error FRL: Occurred while generating the UML Sequence Diagram Image File." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 System.out.println(errorMessage2);
	  }
	   
	  // Generated the UML Sequence Diagram Image File
	  file1 = list.get(0).getPngFile();
 	   
   }
   
   public void deleteSeqDiagramFile(String inputFile)
   {
      File file = new File(inputFile);
	   
      // Delete an existing UML Sequence Diagram File
	  if(file.exists() && !file.isDirectory()) 
	     file.delete();
	  
   }

   public void userConnection()
   {
		  
	  currentUser = userName + validSpecialCharacter + userCounter;
	  	  
   	  methodsCounter = 0;
   	  
      line    = "";
      content = "";
	  

      // Add a division in the UML Sequence Diagram Text File  
      line = startDivision + whiteSpaceWordsDelimiter1 + 
      projectName   + whiteSpaceWordsDelimiter1 + 
      endDivision   + newLine1;

      content = content + line;

      content = String.format(content);

      // Write this division into the UML Sequence Diagram Text File
      writeTextFile(umlSeqDiagTextFile, content);
            
   }
   
   public void updateSeqDiagTextFile(String inputCallerClass, String inputCalleeClass, 
		                             String inputMethod, String inputNote, String inputCallerClassLine,
		                             String inputCalleeClassLine)
   {
	   
      line    = "";
      content = "";
      
      // Generating the content of the Text File which contains Plant UML instructions
      // to generate the UML Sequence Diagram
      
      line    = inputCallerClassLine + newLine1;
      content = content + line;
      
      line    = inputCalleeClassLine + newLine1;
      content = content + line;
      
      line    = inputNote;
      content = content + line;

      // Increase the methods Counter
      methodsCounter ++;
      
      line = inputCallerClass + whiteSpaceWordsDelimiter1 + startSendMessage + 
    		 inputCalleeClass + endSendMessage            + whiteSpaceWordsDelimiter1 + 
    		 methodsCounter   + objectOrientedDelimiter1  + inputMethod + newLine1;
      content = content + line;     
      
      line    = space + newLine1;
      content = content + line;
      
      content = String.format(content);

      // Write the Plant Uml Line into the UML Sequence Diagram Text File
      writeTextFile(umlSeqDiagTextFile, content);   

   } 
   
   public void writeTextFile(String textfile, String content)
   {
      FileWriter fw = null;
      BufferedWriter bw;
      File file;

      try
      {
         // Create a new textFile
         file = new File(textfile);

         if(file.exists())
         {
            fw = new FileWriter(file,true);
            bw = new BufferedWriter(fw);

            // Write in the textFile
            fw.append(content);

            // Close the textFile
            bw.close();
         }
         
      }
      catch(Exception e1)
      {
         errorMessage1 = e1.getMessage();
         errorMessage2 = "Error FRL: Occurred while opening the UML Sequence Diagram Text File: " + umlSeqDiagTextFile + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 System.out.println(errorMessage2);
      }
            
   }  
   
   public boolean validateSuperClass(String inputSuperClass)
   {
      String inputGuiLibStr, lib;
      String [] guiLibsArray; 
      int c=0;
      boolean guiFlag = false;	

      inputGuiLibStr = guiLibs;
   
      // Get the Array from the Input GUI Library String
      guiLibsArray = inputGuiLibStr.split(guiLibDelimiter); 
   
      while(c < guiLibsArray.length && guiFlag == false)
      {
         lib = guiLibsArray[c];
         
         // Validate if the inputSuperClass contains the current Library prefix
	     if(inputSuperClass.contains(lib))
	        guiFlag = true;	
	  
	     c++;
      } 
   
      return guiFlag;   
   }  

   public String getSuperClass(Class<?>  inputClass)
   {
	   
      // Obtain the Class and SuperClass of the current Method Body File
      String superClass = "";
		  		  		  
      // Get the superclass from the class
      Class<?> superclz = inputClass.getSuperclass();
      superClass = superclz.toString();
   
      return superClass;  
   }

   public Class<?> getUpdatedClass(String inputClass)
   {
      Class<?> classObj = null;
	
      try 
      {
         classObj = Class.forName(inputClass);
      } 
      catch (ClassNotFoundException e1) 
      {
         errorMessage1 = e1.getMessage();
	     errorMessage2 = "Error FRL: Occurred while creating a New Class." + System.lineSeparator();
		 errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
		 System.out.println(errorMessage2);
      }
	
      return classObj;	
   }
   
   public String validateClass(String inputClass)
   {
      String outputClass = inputClass, element1, searchWhiteSpace="\\s+";	
      String[] elements;
   
      // Replace the bluePrintObject: "class" for ""
      if(outputClass.contains(bluePrintObject1))
         outputClass = outputClass.replace(bluePrintObject1,"");
	
      // Remove the WhiteSpaces from the beginning of the string
      outputClass = outputClass.replaceFirst(searchWhiteSpace, "");
   
      // Replace the specialCharacter1:"$" for "_"
      if(outputClass.contains(invalidSpecialCharacter))
      {	   
	     // Replace the dollarDelimiter:"$" for "_"
         outputClass = outputClass.replace(invalidSpecialCharacter, validSpecialCharacter);

	     // Divide the string in two parts using the "_" delimiter
         elements = outputClass.split(validSpecialCharacter); 
      
         // Get the first part
  	     element1 = elements[0];
         outputClass = element1;
      
      }   
   
      return outputClass;
   }
   
   public void getCalleeClassDetails(Class<?> calleeClassObj0)
   {
	   
	  // Get the calleeClass 
      calleeClass = calleeClassObj0.toString();

	  // Validate the calleeClass
	  calleeClass = validateClass(calleeClass);

	  //Get the Updated Object Class 
	  calleeClassObj2 = getUpdatedClass(calleeClass);

	  //Get the superClass of the caller Class
	  calleeSuperClass = getSuperClass(calleeClassObj2);
	
	  // Get the flag of the callerSuperClass GUI
	  calleeGuiFlag = validateSuperClass(calleeSuperClass);

	  // Validate if callerSuperClass corresponds to GUI
	  if(calleeGuiFlag == true)
	  {
	     //calleeClass = calleeClass;
	     calleeType = member1;
	  }
	  else
	     calleeType = member2;
   }
   
   public void getCallerClassDetails(Class<?> callerClassObj0)
   {
	   
      // Get the caller Class
      callerClass = callerClassObj0.toString();

	  // Validate the caller Class
	  callerClass = validateClass(callerClass);

	  // Get the Updated Object Class 
	  callerClassObj2 = getUpdatedClass(callerClass);

	  // Get the superClass of the caller Class
	  callerSuperClass = getSuperClass(callerClassObj2);

	  // Get the flag of the callerSuperClass GUI
	  callerGuiFlag = validateSuperClass(callerSuperClass);

	  // Validate if callerSuperClass corresponds to GUI
	  if(callerGuiFlag == true)
	  {	
	     //callerClass = callerClass;
	     callerType = member1;
	  }
	  else
	     callerType = member2;
   }
  
}
