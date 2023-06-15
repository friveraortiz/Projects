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

public aspect FrlDatabaseMethods_1
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
      call(void org.isf.malnutrition.test.Tests.setUpClass(..)) || 
      call(void org.isf.malnutrition.test.Tests.setUp(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMalnutritionGets(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMalnutritionSets(..)) || 
      call(void org.isf.malnutrition.test.Tests.testIoGetMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testIoGetLastMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testIoUpdateMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testIoNewMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testIoDeleteMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMgrGetMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMgrGetLastMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMgrUpdateMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMgrNewMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMgrNewMalnutritionValidateDatesOutOfOrder(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMgrNewMalnutritionValidateDatesNoWeight(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMgrNewMalnutritionValidateDatesNoHeight(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMalnutritionConstructor(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMalnutritionGetterSetter(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMalnutritionEquals(..)) || 
      call(void org.isf.malnutrition.test.Tests.testMalnutritionHasCode(..)) || 
      call(int org.isf.malnutrition.test.Tests.setupTestMalnutrition(..)) || 
      call(void org.isf.malnutrition.test.Tests.checkMalnutritionIntoDb(..)) || 
      call(void org.isf.accounting.test.Tests.setUpClass(..)) || 
      call(void org.isf.accounting.test.Tests.setUp(..)) || 
      call(void org.isf.accounting.test.Tests.mgrGetBillsPayment(..)) || 
      call(void org.isf.accounting.test.Tests.testBillGets(..)) || 
      call(void org.isf.accounting.test.Tests.testBillSets(..)) || 
      call(void org.isf.accounting.test.Tests.testBillItemsGets(..)) || 
      call(void org.isf.accounting.test.Tests.testBillItemsSets(..)) || 
      call(void org.isf.accounting.test.Tests.testBillPaymentsGets(..)) || 
      call(void org.isf.accounting.test.Tests.testBillPaymentsSets(..)) || 
      call(void org.isf.accounting.test.Tests.testListenerShouldUpdatePatientToMergedWhenPatientMergedEventArrive(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetPendingBills(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetPendingBillsPatId(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetBills(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetBill(..)) || 
      call(void org.isf.accounting.test.Tests.ioBillChecks(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetUsers(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetItems(..)) || 
      call(void org.isf.accounting.test.Tests.ioGetAllItems(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetPayments(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetPaymentsBillId(..)) || 
      call(void org.isf.accounting.test.Tests.ioGetBillsByDateForPatient(..)) || 
      call(void org.isf.accounting.test.Tests.ioGetPendingBills(..)) || 
      call(void org.isf.accounting.test.Tests.testIoNewBillPayments(..)) || 
      call(void org.isf.accounting.test.Tests.testIoUpdateBill(..)) || 
      call(void org.isf.accounting.test.Tests.testIoDeleteBill(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetBillsTimeRange(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetBillsPayment(..)) || 
      call(void org.isf.accounting.test.Tests.testIoGetPaymentsBill(..)) || 
      call(void org.isf.accounting.test.Tests.ioBillPaymentsChecks(..)) || 
      call(void org.isf.accounting.test.Tests.ioBillItemChecks(..)) || 
      call(void org.isf.accounting.test.Tests.mgrGetPaymentsByDateForPatient(..)) || 
      call(void org.isf.accounting.test.Tests.mgrGetAllPayments(..)) || 
      call(void org.isf.accounting.test.Tests.mgrGetAllPaymentsWithId(..)) || 
      call(void org.isf.accounting.test.Tests.mgrGetDistictsBillItems(..)) || 
      call(void org.isf.accounting.test.Tests.mgrGetBillsBetweenDatesWherePatient(..)) || 
      call(void org.isf.accounting.test.Tests.mgrGetPendingBillsForPatientId(..)) || 
      call(void org.isf.accounting.test.Tests.mgrNewBillNoItemsNoPayments(..)) || 
      call(void org.isf.accounting.test.Tests.mgrNewBillItemsAndPayments(..)) || 
      call(void org.isf.accounting.test.Tests.msgDeleteBill(..)) || 
      call(void org.isf.accounting.test.Tests.checkBillIntoDb(..)) || 
      call(void org.isf.accounting.test.Tests.checkBillItemsIntoDb(..)) || 
      call(void org.isf.accounting.test.Tests.checkBillPaymentsIntoDb(..)) || 
      call(void org.isf.operation.test.Tests.setUpClass(..)) || 
      call(void org.isf.operation.test.Tests.setUp(..)) || 
      call(void org.isf.operation.test.Tests.testOperationGets(..)) || 
      call(void org.isf.operation.test.Tests.testOperationSets(..)) || 
      call(void org.isf.operation.test.Tests.testOperationRowGets(..)) || 
      call(void org.isf.operation.test.Tests.testIoGetOperationByTypeDescription(..)) || 
      call(void org.isf.operation.test.Tests.testIoGetOperationByTypeDescriptionNull(..)) || 
      call(void org.isf.operation.test.Tests.testIoGetOperationOpdOpdAdmission(..)) || 
      call(void org.isf.operation.test.Tests.testIoGetOperationOpdOpd(..)) || 
      call(void org.isf.operation.test.Tests.testIoGetOperationAdmOpd(..)) || 
      call(void org.isf.operation.test.Tests.testIoGetOperationAdmAdmission(..)) || 
      call(void org.isf.operation.test.Tests.testIoGetOperationAdmNotOpd(..)) || 
      call(void org.isf.operation.test.Tests.testIoUpdateOperation(..)) || 
      call(void org.isf.operation.test.Tests.testIoDeleteOperation(..)) || 
      call(void org.isf.operation.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.operation.test.Tests.testIoIsDescriptionPresent(..)) || 
      call(void org.isf.operation.test.Tests.testMgrGetOperationByTypeDescription(..)) || 
      call(void org.isf.operation.test.Tests.testMgrGetOperation(..)) || 
      call(void org.isf.operation.test.Tests.testMgrGetOperationOpdOpdAdmission(..)) || 
      call(void org.isf.operation.test.Tests.testMgrGetOperationOpdOpd(..)) || 
      call(void org.isf.operation.test.Tests.testMgrGetOperationAdmOpd(..)) || 
      call(void org.isf.operation.test.Tests.testMgrGetOperationAdmAdmission(..)) || 
      call(void org.isf.operation.test.Tests.testMgrGetOperationAdmNotOpd(..)) || 
      call(void org.isf.operation.test.Tests.testMgrUpdateOperation(..)) || 
      call(void org.isf.operation.test.Tests.testMgrDeleteOperation(..)) || 
      call(void org.isf.operation.test.Tests.testMgrDescriptionControl(..)) || 
      call(void org.isf.operation.test.Tests.testMgrDescriptionControlNotFound(..)) || 
      call(void org.isf.operation.test.Tests.testRowIoGetRowOperation(..)) || 
      call(void org.isf.operation.test.Tests.testRowIoGetOperationRowByOpd(..)) || 
      call(void org.isf.operation.test.Tests.testRowIoDeleteOperationRowNotFound(..)) || 
      call(void org.isf.operation.test.Tests.testRowIoNewOperationRow(..)) || 
      call(void org.isf.operation.test.Tests.testMgrRowDeleteOperationRow(..)) || 
      call(void org.isf.operation.test.Tests.testMgrRowNewOperationRow(..)) || 
      call(java.lang.String org.isf.operation.test.Tests.setupTestOperation(..)) || 
      call(void org.isf.operation.test.Tests.checkOperationIntoDb(..)) || 
      call(int org.isf.operation.test.Tests.setupTestOperationRow(..)) || 
      call(void org.isf.operation.test.Tests.checkOperationRowIntoDb(..)) || 
      call(void org.isf.admtype.test.Tests.setUpClass(..)) || 
      call(void org.isf.admtype.test.Tests.setUp(..)) || 
      call(void org.isf.admtype.test.Tests.testAdmissionTypeGets(..)) || 
      call(void org.isf.admtype.test.Tests.testAdmissionTypeSets(..)) || 
      call(void org.isf.admtype.test.Tests.testIoGetAdmissionType(..)) || 
      call(void org.isf.admtype.test.Tests.testIoUpdateAdmissionType(..)) || 
      call(void org.isf.admtype.test.Tests.testIoNewAdmissionType(..)) || 
      call(void org.isf.admtype.test.Tests.testIoDeleteAdmissionType(..)) || 
      call(void org.isf.admtype.test.Tests.testMgrGetAdmissionType(..)) || 
      call(void org.isf.admtype.test.Tests.testMgrUpdateAdmissionType(..)) || 
      call(void org.isf.admtype.test.Tests.testAdmissionTypeEqualHashToString(..)) || 
      call(void org.isf.admtype.test.Tests.testMgrAdmissionValidation(..)) || 
      call(void org.isf.admtype.test.Tests.testMgrNewAdmissionType(..)) || 
      call(void org.isf.admtype.test.Tests.testMgrDeleteAdmissionType(..)) || 
      call(java.lang.String org.isf.admtype.test.Tests.setupTestAdmissionType(..)) || 
      call(void org.isf.admtype.test.Tests.checkAdmissionTypeIntoDb(..)) || 
      call(void org.isf.admtype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.admtype.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.medtype.test.Tests.setUpClass(..)) || 
      call(void org.isf.medtype.test.Tests.setUp(..)) || 
      call(void org.isf.medtype.test.Tests.testMedicalTypeGets(..)) || 
      call(void org.isf.medtype.test.Tests.testMedicalTypeSets(..)) || 
      call(void org.isf.medtype.test.Tests.testIoGetMedicalType(..)) || 
      call(void org.isf.medtype.test.Tests.testIoUpdateMedicalType(..)) || 
      call(void org.isf.medtype.test.Tests.testIoNewMedicalType(..)) || 
      call(void org.isf.medtype.test.Tests.testIoDeleteMedicalType(..)) || 
      call(void org.isf.medtype.test.Tests.testMgrGetMedicalType(..)) || 
      call(void org.isf.medtype.test.Tests.testMgrUpdateMedicalType(..)) || 
      call(void org.isf.medtype.test.Tests.testMgrNewMedicalType(..)) || 
      call(void org.isf.medtype.test.Tests.testMgrValidateMedicalTypeKeyTooLong(..)) || 
      call(void org.isf.medtype.test.Tests.testMgrValidateMedicalTypeNoDescription(..)) || 
      call(void org.isf.medtype.test.Tests.testMgrValidateMedicalTypeCodeAlreadyExists(..)) || 
      call(void org.isf.medtype.test.Tests.testMedicalTypeToString(..)) || 
      call(void org.isf.medtype.test.Tests.testMedicalTypeEquals(..)) || 
      call(void org.isf.medtype.test.Tests.testMedicalTypeHashCode(..)) || 
      call(java.lang.String org.isf.medtype.test.Tests.setupTestMedicalType(..)) || 
      call(void org.isf.medtype.test.Tests.checkMedicalTypeIntoDb(..)) || 
      call(void org.isf.medtype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.medtype.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.opetype.test.Tests.testOperationTypeGets(..)) || 
      call(void org.isf.opetype.test.Tests.testOperationTypeSets(..)) || 
      call(void org.isf.opetype.test.Tests.testIoGetOperationType(..)) || 
      call(void org.isf.opetype.test.Tests.testIoUpdateOperationType(..)) || 
      call(void org.isf.opetype.test.Tests.testIoDeleteOperationType(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrGetOperationType(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrUpdateOperationType(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrNewOperationType(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrDeleteOperationType(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrValidationKeyNull(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrValidationKeyEmpty(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrValidationKeyTooLong(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrValidationDescriptionNull(..)) || 
      call(void org.isf.opetype.test.Tests.testMgrValidationCodeAlreadyInUse(..)) || 
      call(void org.isf.opetype.test.Tests.testOperationTypeToString(..)) || 
      call(java.lang.String org.isf.opetype.test.Tests.setupTestOperationType(..)) || 
      call(void org.isf.opetype.test.Tests.checkOperationTypeIntoDb(..)) || 
      call(void org.isf.disease.test.Tests.setUpClass(..)) || 
      call(void org.isf.disease.test.Tests.setUp(..)) || 
      call(void org.isf.disease.test.Tests.testDiseaseGets(..)) || 
      call(void org.isf.disease.test.Tests.testDiseaseSets(..)) || 
      call(void org.isf.disease.test.Tests.testIoGetDiseaseByCode(..)) || 
      call(void org.isf.disease.test.Tests.testIoGetDiseases(..)) || 
      call(void org.isf.disease.test.Tests.testIoNewDisease(..)) || 
      call(void org.isf.disease.test.Tests.testIoUpdateDisease(..)) || 
      call(void org.isf.disease.test.Tests.testIoHasDiseaseModified(..)) || 
      call(void org.isf.disease.test.Tests.testIoDeleteDisease(..)) || 
      call(void org.isf.disease.test.Tests.testMgrGetDiseaseByCode(..)) || 
      call(void org.isf.disease.test.Tests.testMgrGetDiseases(..)) || 
      call(void org.isf.disease.test.Tests.testMgrNewDisease(..)) || 
      call(void org.isf.disease.test.Tests.testMgrUpdateDisease(..)) || 
      call(void org.isf.disease.test.Tests.testMgrHasDiseaseModified(..)) || 
      call(void org.isf.disease.test.Tests.testMgrDeleteDisease(..)) || 
      call(void org.isf.disease.test.Tests.testDiseaseGetterSetter(..)) || 
      call(void org.isf.disease.test.Tests.testMgrValidationUpdateCodeEmpty(..)) || 
      call(void org.isf.disease.test.Tests.testMgrValidationUpdateCodeTooLong(..)) || 
      call(void org.isf.disease.test.Tests.testMgrValidationInsert(..)) || 
      call(java.lang.String org.isf.disease.test.Tests.setupTestDisease(..)) || 
      call(void org.isf.disease.test.Tests.checkDiseaseIntoDb(..)) || 
      call(void org.isf.disease.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.disease.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.opd.test.Tests.setUpClass(..)) || 
      call(void org.isf.opd.test.Tests.testOpdGets(..)) || 
      call(void org.isf.opd.test.Tests.testIoGetOpdList(..)) || 
      call(void org.isf.opd.test.Tests.testIoGetOpdListPatientId(..)) || 
      call(void org.isf.opd.test.Tests.testIoNewOpd(..)) || 
      call(void org.isf.opd.test.Tests.testIoUpdateOpd(..)) || 
      call(void org.isf.opd.test.Tests.testIoGetProgYear(..)) || 
      call(void org.isf.opd.test.Tests.testIoIsExistsOpdNumShouldReturnTrueWhenOpdWithGivenOPDProgressiveYearAndVisitYearExists(..)) || 
      call(void org.isf.opd.test.Tests.testIoIsExistsOpdNumShouldReturnFalseWhenOpdNumExistsAndVisitYearIsIncorrect(..)) || 
      call(void org.isf.opd.test.Tests.testMgrGetOpd(..)) || 
      call(void org.isf.opd.test.Tests.testMgrGetOpdListPatientId(..)) || 
      call(void org.isf.opd.test.Tests.testMgrGetOpdLastWeek(..)) || 
      call(void org.isf.opd.test.Tests.testMgrNewOpd(..)) || 
      call(void org.isf.opd.test.Tests.testMgrDeleteOpd(..)) || 
      call(void org.isf.opd.test.Tests.testMgrGetProgYearZero(..)) || 
      call(void org.isf.opd.test.Tests.testMgrIsExistsOpdNumShouldReturnFalseWhenOpdNumExistsAndVisitYearIsIncorrect(..)) || 
      call(void org.isf.opd.test.Tests.testMgrValidationDiseaseIsEmpty(..)) || 
      call(void org.isf.opd.test.Tests.testMgrValidationDiseaseIsEqualToDisease2(..)) || 
      call(void org.isf.opd.test.Tests.testMgrValidationDisease2IsEqualToDisease3(..)) || 
      call(void org.isf.opd.test.Tests.testListenerShouldUpdatePatientToMergedWhenPatientMergedEventArrive(..)) || 
      call(void org.isf.agetype.test.Tests.setUpClass(..)) || 
      call(void org.isf.agetype.test.Tests.setUp(..)) || 
      call(void org.isf.agetype.test.Tests.testAgeTypeGets(..)) || 
      call(void org.isf.agetype.test.Tests.testAgeTypeSets(..)) || 
      call(void org.isf.agetype.test.Tests.testIoGetAgeType(..)) || 
      call(void org.isf.agetype.test.Tests.testIoUpdateAgeType(..)) || 
      call(void org.isf.agetype.test.Tests.testIoGetAgeTypeByCode(..)) || 
      call(void org.isf.agetype.test.Tests.testMgrGetAgeType(..)) || 
      call(void org.isf.agetype.test.Tests.testMgrUpdateAgeType(..)) || 
      call(void org.isf.agetype.test.Tests.testMgrGetTypeByAge(..)) || 
      call(void org.isf.agetype.test.Tests.testMgrGetAgeTypeByCode(..)) || 
      call(void org.isf.agetype.test.Tests.testAgeTypeEqualHashToString(..)) || 
      call(void org.isf.agetype.test.Tests.testMgrAgeTypeValidation(..)) || 
      call(java.lang.String org.isf.agetype.test.Tests.setupTestAgeType(..)) || 
      call(void org.isf.agetype.test.Tests.checkAgeTypeIntoDb(..)) || 
      call(void org.isf.dlvrtype.test.Tests.setUpClass(..)) || 
      call(void org.isf.dlvrtype.test.Tests.setUp(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testDeliveryTypeGets(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testDeliveryTypeSets(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testIoGetDeliveryType(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testIoUpdateDeliveryType(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testIoNewDeliveryType(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testIoDeleteDeliveryType(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testMgrGetDeliveryType(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testMgrUpdateDeliveryType(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testMgrDeleteDeliveryType(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testDeliveryTypeEqualHashToString(..)) || 
      call(java.lang.String org.isf.dlvrtype.test.Tests.setupTestDeliveryType(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.dlvrtype.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.medicalstockward.test.Tests.setUpClass(..)) || 
      call(void org.isf.medicalstockward.test.Tests.setUp(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMedicalWardGets(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMedicalWardSets(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMovementWardGets(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testTotalQuantityShouldFindMovementWardByWardCodeAndDates(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMovementWardSets(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testIoGetWardMovements(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testIoGetCurrentQuantityInWard(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testIoGetCurrentQuantityInWardNoWard(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testIoNewMovementWardWithMedicalWardDefined(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testIoNewMovementWardWithMedicalWardDefinedNoWardTo(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testIoNewMovementWardArrayList(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testIoGetMedicalsWardStripEmptyLots(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMgrGetMovementWard(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMgrNewMovementWard(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMgrGetMovementToPatient(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMgrGetMedicalsWardTotalQuantity(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMgrConvertMovementWardForPrintByDate(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMgrConvertMovementWardForPrintByMedical(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMgrValidationDescriptionEmptyIsPatient(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMedicalWardIdEquals(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMovementWardConstructor(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMovementWardConstructorWithLot(..)) || 
      call(void org.isf.medicalstockward.test.Tests.testMedicalWardHashCode(..)) || 
      call(org.isf.medicalstockward.model.MedicalWardId org.isf.medicalstockward.test.Tests.setupTestMedicalWard(..)) || 
      call(void org.isf.medicalstockward.test.Tests.checkMedicalWardIntoDb(..)) || 
      call(void org.isf.medicalstockward.test.Tests.checkMovementWardIntoDb(..)) || 
      call(void org.isf.sms.test.Tests.testSmsSets(..)) || 
      call(void org.isf.sms.test.Tests.testSmsSaveOrUpdate(..)) || 
      call(void org.isf.sms.test.Tests.testSmsGetAll(..)) || 
      call(void org.isf.sms.test.Tests.testSmsGetList(..)) || 
      call(void org.isf.sms.test.Tests.testIoDeleteSms(..)) || 
      call(int org.isf.sms.test.Tests.setupTestSms(..)) || 
      call(void org.isf.medicals.test.Tests.testIoMedicalCheckTrue(..)) || 
      call(void org.isf.medicals.test.Tests.testIoMedicalCheckFalse(..)) || 
      call(void org.isf.medicals.test.Tests.setUpClass(..)) || 
      call(void org.isf.medicals.test.Tests.setUp(..)) || 
      call(void org.isf.medicals.test.Tests.testIoProductCodeExistsUpdateTrue(..)) || 
      call(void org.isf.medicals.test.Tests.testIoProductCodeExistsUpdateFalse(..)) || 
      call(void org.isf.medicals.test.Tests.testMedicalSets(..)) || 
      call(void org.isf.medicals.test.Tests.testIoGetMedicalWithCode(..)) || 
      call(void org.isf.medicals.test.Tests.testIoGetMedicals(..)) || 
      call(void org.isf.medicals.test.Tests.testIoGetMedicalsWithDescription(..)) || 
      call(void org.isf.medicals.test.Tests.testIoGetMedicalsSortedNoDescription(..)) || 
      call(void org.isf.medicals.test.Tests.testIoGetMedicalsSortedWithTypeDescription(..)) || 
      call(void org.isf.medicals.test.Tests.testIoGetMedicalsNoDescriptionTypeCritical(..)) || 
      call(void org.isf.medicals.test.Tests.testIoGetMedicalsNoDescriptionNoTypeCritical(..)) || 
      call(void org.isf.medicals.test.Tests.testIoGetMedicalsDescriptionTypeCritical(..)) || 
      call(void org.isf.medicals.test.Tests.testIoNewMedical(..)) || 
      call(void org.isf.medicals.test.Tests.testIoUpdateMedical(..)) || 
      call(void org.isf.medicals.test.Tests.testIoDeleteMedical(..)) || 
      call(void org.isf.medicals.test.Tests.testIsMedicalReferencedInStockMovement(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrGetMedicalWithCode(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrGetMedicals(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrGetMedicalsSortedByCode(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrGetMedicalsWithTypeDescriptionNotSorted(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrGetMedicalsWithCriteria(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrNewMedical(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrUpdateMedical(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrUpdateMedicalIgnoreSimilarTrue(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrUpdateMedicalIgnoreSimilarFalse(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrDeleteMedical(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrDeleteMedicalInStockMovement(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrCheckMedicalCommonBadPcsperpck(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrCheckMedicalCommonMissingDescription(..)) || 
      call(void org.isf.medicals.test.Tests.testMgrCheckMedicalProductCodeExists(..)) || 
      call(void org.isf.medicals.test.Tests.testMedicalSimpleConstructor(..)) || 
      call(void org.isf.medicals.test.Tests.testMedicalSettersGetters(..)) || 
      call(void org.isf.medicals.test.Tests.testMedicalCompareTo(..)) || 
      call(int org.isf.medicals.test.Tests.setupTestMedical(..)) || 
      call(void org.isf.medicals.test.Tests.checkMedicalIntoDb(..)) || 
      call(void org.isf.visits.test.Tests.setUpClass(..)) || 
      call(void org.isf.visits.test.Tests.setUp(..)) || 
      call(void org.isf.visits.test.Tests.testVisitToString(..)) || 
      call(void org.isf.visits.test.Tests.testVisitFormatDateTimeSMS(..)) || 
      call(void org.isf.visits.test.Tests.testVisitGets(..)) || 
      call(void org.isf.visits.test.Tests.testVisitSets(..)) || 
      call(void org.isf.visits.test.Tests.testIoGetVisitShouldReturnVisitWhenPatientCodeProvided(..)) || 
      call(void org.isf.visits.test.Tests.testIoGetVisitShouldReturnAllVisitsWhenZeroPatientCodeProvided(..)) || 
      call(void org.isf.visits.test.Tests.testIoGetVisitsWardNullWardId(..)) || 
      call(void org.isf.visits.test.Tests.testIoGetVisitsWardWardId(..)) || 
      call(void org.isf.visits.test.Tests.testIoDeleteVisit(..)) || 
      call(void org.isf.visits.test.Tests.testIoFindVisit(..)) || 
      call(void org.isf.visits.test.Tests.testMgrGetVisitNoPatientCode(..)) || 
      call(void org.isf.visits.test.Tests.testMgrGetVisitsWardWardId(..)) || 
      call(void org.isf.visits.test.Tests.testMgrGetVisitsEmpty(..)) || 
      call(void org.isf.visits.test.Tests.testMgrNewVisitsEmptyList(..)) || 
      call(void org.isf.visits.test.Tests.testMgrNewVisitsSMSTrueDateFuture(..)) || 
      call(void org.isf.visits.test.Tests.testVisitHashCode(..)) || 
      call(int org.isf.visits.test.Tests.setupTestVisit(..)) || 
      call(void org.isf.visits.test.Tests.checkVisitIntoDb(..)) || 
      call(void org.isf.visits.test.Tests.testVisitToStringSMS(..)) || 
      call(void org.isf.priceslist.test.Tests.testPriceListGets(..)) || 
      call(void org.isf.priceslist.test.Tests.testPriceGets(..)) || 
      call(void org.isf.priceslist.test.Tests.testPriceSets(..)) || 
      call(void org.isf.priceslist.test.Tests.testIoGetPrices(..)) || 
      call(void org.isf.priceslist.test.Tests.testIoUpdatePrices(..)) || 
      call(void org.isf.priceslist.test.Tests.testIoNewList(..)) || 
      call(void org.isf.priceslist.test.Tests.testIoUpdateList(..)) || 
      call(void org.isf.priceslist.test.Tests.testIoDeleteList(..)) || 
      call(void org.isf.priceslist.test.Tests.testIoCopyList(..)) || 
      call(void org.isf.priceslist.test.Tests.testIoCopyListSteps(..)) || 
      call(void org.isf.priceslist.test.Tests.testMgrGetLists(..)) || 
      call(void org.isf.priceslist.test.Tests.testMgrGetPrices(..)) || 
      call(void org.isf.priceslist.test.Tests.testMgrUpdateList(..)) || 
      call(void org.isf.priceslist.test.Tests.testMgrDeleteList(..)) || 
      call(void org.isf.priceslist.test.Tests.testMgrCopyListSteps3(..)) || 
      call(void org.isf.priceslist.test.Tests.testMgrCopyList(..)) || 
      call(void org.isf.priceslist.test.Tests.testConvertPrice(..)) || 
      call(void org.isf.priceslist.test.Tests.testPriceListValidationCurrenyIsEmpty(..)) || 
      call(void org.isf.priceslist.test.Tests.testPriceToString(..)) || 
      call(void org.isf.priceslist.test.Tests.testPriceListEquals(..)) || 
      call(int org.isf.priceslist.test.Tests.setupTestPriceList(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.setUpClass(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.setUp(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testDeliveryResultTypeGets(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testDeliveryResultTypeSets(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testIoGetDeliveryResultType(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testIoUpdateDeliveryResultType(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testIoDeleteDeliveryResultType(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testMgrUpdateDeliveryResultType(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testMgrNewDeliveryResultType(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testMgrDeleteDeliveryResultType(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testMgrDeliveryResultTypeValidate(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testDeliveryResultTypeHashToString(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.checkDeliveryResultTypeIntoDb(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.dlvrrestype.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.exa.test.Tests.setUp(..)) || 
      call(void org.isf.exa.test.Tests.testExamGets(..)) || 
      call(void org.isf.exa.test.Tests.testIoGetExamRowNoDescription(..)) || 
      call(void org.isf.exa.test.Tests.testIoGetExamRowWithDescription(..)) || 
      call(void org.isf.exa.test.Tests.testIoGetExamTypeExam(..)) || 
      call(void org.isf.exa.test.Tests.testIoNewExamRow(..)) || 
      call(void org.isf.exa.test.Tests.testIoNewExam(..)) || 
      call(void org.isf.exa.test.Tests.testIoUpdateExam(..)) || 
      call(void org.isf.exa.test.Tests.testIoUpdateExamRow(..)) || 
      call(void org.isf.exa.test.Tests.testIoDeleteExam(..)) || 
      call(void org.isf.exa.test.Tests.testIoGetExamRowByExamCode(..)) || 
      call(void org.isf.exa.test.Tests.testMgrGetExamRowByExamCode(..)) || 
      call(void org.isf.exa.test.Tests.testMgrGetExamType(..)) || 
      call(void org.isf.exa.test.Tests.testMgrNewExam(..)) || 
      call(void org.isf.exa.test.Tests.testMgrUpdateExam(..)) || 
      call(void org.isf.exa.test.Tests.testMgrDeleteExam(..)) || 
      call(void org.isf.exa.test.Tests.testMgrDeleteExamRow(..)) || 
      call(void org.isf.exa.test.Tests.testMgrGetExamRowNoDescription(..)) || 
      call(void org.isf.exa.test.Tests.testMgrGetExamRowWithDescription(..)) || 
      call(void org.isf.exa.test.Tests.testMgrExamValidationUpdate(..)) || 
      call(void org.isf.exa.test.Tests.testMgrExamRowValidationUpdate(..)) || 
      call(void org.isf.exa.test.Tests.testExamEqualHashToString(..)) || 
      call(void org.isf.exa.test.Tests.testExamGetterSetter(..)) || 
      call(void org.isf.exa.test.Tests.testIoExamSanitize(..)) || 
      call(void org.isf.exa.test.Tests.testIoExamRowSanitize(..)) || 
      call(java.lang.String org.isf.exa.test.Tests.setupTestExam(..)) || 
      call(void org.isf.exa.test.Tests.checkExamIntoDb(..)) || 
      call(void org.isf.exa.test.Tests.checkExamRowIntoDb(..)) || 
      call(void org.isf.exa.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.lab.test.Tests.setUpClass(..)) || 
      call(void org.isf.lab.test.Tests.testLaboratorySets(..)) || 
      call(void org.isf.lab.test.Tests.testLaboratoryRowGets(..)) || 
      call(void org.isf.lab.test.Tests.testLaboratoryRowSets(..)) || 
      call(void org.isf.lab.test.Tests.testIoGetLaboratory(..)) || 
      call(void org.isf.lab.test.Tests.testIoGetLaboratoryForPrint(..)) || 
      call(void org.isf.lab.test.Tests.testIoGetLaboratoryForPrintWithDates(..)) || 
      call(void org.isf.lab.test.Tests.testIoGetLaboratoryForPrintWithExamDescriptionLikePersistedOne(..)) || 
      call(void org.isf.lab.test.Tests.testIoGetLaboratoryForPrintWithNullExamDescription(..)) || 
      call(void org.isf.lab.test.Tests.testIoNewLabFirstProcedure(..)) || 
      call(void org.isf.lab.test.Tests.testIoNewLabSecondProcedure(..)) || 
      call(void org.isf.lab.test.Tests.testIoNewLabSecondProcedureTransaction(..)) || 
      call(void org.isf.lab.test.Tests.testIoUpdateLaboratory(..)) || 
      call(void org.isf.lab.test.Tests.testIoEditLabSecondProcedure(..)) || 
      call(void org.isf.lab.test.Tests.testMgrGetLaboratoryRelatedToPatient(..)) || 
      call(void org.isf.lab.test.Tests.testMgrGetLaboratoryForPrintWithNullExamDescription(..)) || 
      call(void org.isf.lab.test.Tests.testMgrnewLaboratoryProcedureEquals1(..)) || 
      call(void org.isf.lab.test.Tests.testMgrNewLaboratoryProcedureEquals2NullLabRows(..)) || 
      call(void org.isf.lab.test.Tests.testMgrNewLaboratoryExceptionsBadProcedureNumber(..)) || 
      call(void org.isf.lab.test.Tests.testMgrNewLaboratoryProcedureEquals2RollbackTransaction(..)) || 
      call(void org.isf.lab.test.Tests.testMgrNewLaboratory2ProcedureEquals3(..)) || 
      call(void org.isf.lab.test.Tests.testMgrValidationMissingExamDate(..)) || 
      call(void org.isf.lab.test.Tests.testMgrUpdateLaboratoryProcedure1(..)) || 
      call(void org.isf.lab.test.Tests.testMgrUpdateLaboratoryProcedure3(..)) || 
      call(void org.isf.lab.test.Tests.testMgrUpdateLaboratoryExceptionNullLablRows(..)) || 
      call(void org.isf.lab.test.Tests.testMgrNewLaboratory2TransactionLabListNotEqualLabRowList(..)) || 
      call(void org.isf.lab.test.Tests.testMgrEditLabFirstProcedure(..)) || 
      call(void org.isf.lab.test.Tests.testMgrEditLabSecondProcedure(..)) || 
      call(void org.isf.lab.test.Tests.testMgrDeleteLaboratory(..)) || 
      call(void org.isf.lab.test.Tests.testMgrDeleteLaboratoryProcedure2(..)) || 
      call(void org.isf.lab.test.Tests.testLaboratoryEqualsHashToString(..)) || 
      call(void org.isf.lab.test.Tests.testLaboratoryForPrintGetterSetter(..)) || 
      call(void org.isf.lab.test.Tests.checkLaboratoryIntoDb(..)) || 
      call(java.lang.Integer org.isf.lab.test.Tests.setupTestLaboratoryRow(..)) || 
      call(void org.isf.medicalstock.test.Tests.setUpClass(..)) || 
      call(void org.isf.medicalstock.test.Tests.setUp(..)) || 
      call(void org.isf.medicalstock.test.Tests.testLotGets(..)) || 
      call(void org.isf.medicalstock.test.Tests.testLotSets(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMovementGets(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMovementSets(..)) || 
      call(void org.isf.medicalstock.test.Tests.testIoPrepareChargingMovement(..)) || 
      call(void org.isf.medicalstock.test.Tests.testIoGetMovementForPrintDateOrder(..)) || 
      call(void org.isf.medicalstock.test.Tests.testIoGetMovementForPrintWardOrderateWard(..)) || 
      call(void org.isf.medicalstock.test.Tests.testIoRefNoExists(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrGetMovementsByReference(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrGetMovementsCheckLotPrepParameters(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrAlertCriticalQuantityOverLimit(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrGetLastMovementDate(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrRefNoExists(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrPrepareDischargingMovement(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrPrepareDischargingMovementIsAutomaticLotOut(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrValidateMovementMoveDateAfterToday(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrValidateNullWard(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMgrValidateLotPrepationDateNull(..)) || 
      call(void org.isf.medicalstock.test.Tests.testLotToString(..)) || 
      call(void org.isf.medicalstock.test.Tests.testLotIsValidLot(..)) || 
      call(void org.isf.medicalstock.test.Tests.testLotEquals(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMovementToString(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMovementHashCode(..)) || 
      call(void org.isf.medicalstock.test.Tests.testMovementGetOrigin(..)) || 
      call(java.lang.String org.isf.medicalstock.test.Tests.setupTestLot(..)) || 
      call(void org.isf.medicalstock.test.Tests.checkMovementIntoDb(..)) || 
      call(void org.isf.admission.test.Tests.checkAdmissionIntoDb(..)) || 
      call(void org.isf.admission.test.Tests.testAdmissionSets(..)) || 
      call(void org.isf.admission.test.Tests.testIoGetAdmittedPatients(..)) || 
      call(void org.isf.admission.test.Tests.testIoGetCurrentAdmission(..)) || 
      call(void org.isf.admission.test.Tests.testIoGetAdmission(..)) || 
      call(void org.isf.admission.test.Tests.testIoNewAdmission(..)) || 
      call(void org.isf.admission.test.Tests.testIoGetNextYProg(..)) || 
      call(void org.isf.admission.test.Tests.testIoGetUsedWardBed(..)) || 
      call(void org.isf.admission.test.Tests.testIoLoadAdmittedPatientNotThere(..)) || 
      call(void org.isf.admission.test.Tests.testAdmissionGettersSetters(..)) || 
      call(void org.isf.admission.test.Tests.testAdmittedPatientGettersSetters(..)) || 
      call(void org.isf.admission.test.Tests.testMgrGetAdmittedPatientWithDateRanges(..)) || 
      call(void org.isf.admission.test.Tests.testMgrGetAdmittedPatientsShouldFindByOneOfFieldsLikeLastName(..)) || 
      call(void org.isf.exatype.test.Tests.setUpClass(..)) || 
      call(void org.isf.exatype.test.Tests.setUp(..)) || 
      call(void org.isf.exatype.test.Tests.testIoNewExamType(..)) || 
      call(void org.isf.exatype.test.Tests.testMgrUpdateExamType(..)) || 
      call(void org.isf.exatype.test.Tests.testMgrNewExamType(..)) || 
      call(void org.isf.exatype.test.Tests.testMgrDeleteExamType(..)) || 
      call(void org.isf.exatype.test.Tests.testMgrExamTypeValidation(..)) || 
      call(void org.isf.exatype.test.Tests.checkExamTypeIntoDb(..)) || 
      call(void org.isf.exatype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.setUpClass(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.setUp(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testIoDeleteMovementType(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testMgrNewMovementType(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testMgrDeleteMovementType(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testMgrMovementTypeValidationNoKey(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testMgrMovementTypeValidationKeyTooLong(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testMgrMovementTypeValidationTypeTooLong(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testMgrMovementTypeValidationNoDescription(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testMgrMovementTypeValidationCodeAlreadyExists(..)) || 
      call(java.lang.String org.isf.medstockmovtype.test.Tests.setupTestMovementType(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.medstockmovtype.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.distype.test.Tests.setUp(..)) || 
      call(void org.isf.distype.test.Tests.testMgrValidationInsert(..)) || 
      call(void org.isf.distype.test.Tests.testDiseaseTypeGets(..)) || 
      call(void org.isf.distype.test.Tests.testDiseaseTypeSets(..)) || 
      call(void org.isf.distype.test.Tests.testIoGetDiseaseType(..)) || 
      call(void org.isf.distype.test.Tests.testIoUpdateDiseaseType(..)) || 
      call(void org.isf.distype.test.Tests.testIoNewDiseaseType(..)) || 
      call(void org.isf.distype.test.Tests.testIoDeleteDiseaseType(..)) || 
      call(void org.isf.distype.test.Tests.testMgrNewDiseaseType(..)) || 
      call(void org.isf.distype.test.Tests.testMgrDeleteDiseaseType(..)) || 
      call(void org.isf.distype.test.Tests.testIoIsCodePresent(..)) || 
      call(void org.isf.distype.test.Tests.testMgrIsCodePresent(..)) || 
      call(void org.isf.distype.test.Tests.testMgrValidateDiseaseTypeCodeEmpty(..)) || 
      call(void org.isf.distype.test.Tests.testMgrValidateDiseaseTypeCodeTooLong(..));

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
