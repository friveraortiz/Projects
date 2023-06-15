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

public aspect FrlDatabaseMethods_3
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
   //String connectProjectMethod="List<org.isf.menu.model.User> org.isf.menu.manager.UserBrowsingManager.getUser";
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
   String errorMessage1="", errorMessage2="", defaultUser="User_1";
   
   /* Evaluation Start (DECLARE VARIABLES) */
   long time1, time2, duration;
   /* Evaluation End */
   
   pointcut databaseMethods(): 
      call(org.isf.malnutrition.model.Malnutrition org.isf.malnutrition.manager.MalnutritionManager.updateMalnutrition(..)) || 
      call(org.isf.malnutrition.model.Malnutrition org.isf.malnutrition.manager.MalnutritionManager.getLastMalnutrition(..)) || 
      call(boolean org.isf.malnutrition.manager.MalnutritionManager.newMalnutrition(..)) || 
      call(boolean org.isf.malnutrition.manager.MalnutritionManager.deleteMalnutrition(..)) || 
      call(List<org.isf.malnutrition.model.Malnutrition> org.isf.malnutrition.manager.MalnutritionManager.getMalnutrition(..)) || 
      call(org.isf.malnutrition.model.Malnutrition org.isf.malnutrition.service.MalnutritionIoOperation.updateMalnutrition(..)) || 
      call(List<org.isf.malnutrition.model.Malnutrition> org.isf.malnutrition.service.MalnutritionIoOperation.getMalnutritions(..)) || 
      call(org.isf.malnutrition.model.Malnutrition org.isf.malnutrition.service.MalnutritionIoOperation.getLastMalnutrition(..)) || 
      call(boolean org.isf.malnutrition.service.MalnutritionIoOperation.newMalnutrition(..)) || 
      call(boolean org.isf.malnutrition.service.MalnutritionIoOperation.deleteMalnutrition(..)) || 
      call(boolean org.isf.malnutrition.service.MalnutritionIoOperation.isCodePresent(..)) || 
      call(List<org.isf.malnutrition.model.Malnutrition> org.isf.malnutrition.service.MalnutritionIoOperationRepository.findAllWhereAdmissionByOrderDate(..)) || 
      call(List<org.isf.malnutrition.model.Malnutrition> org.isf.malnutrition.service.MalnutritionIoOperationRepository.findAllWhereAdmissionByOrderDateDesc(..)) || 
      call(org.isf.accounting.model.Bill org.isf.accounting.manager.BillBrowserManager.getBill(..)) || 
      call(List<java.lang.String> org.isf.accounting.manager.BillBrowserManager.getUsers(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.manager.BillBrowserManager.getPendingBills(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.manager.BillBrowserManager.getBills(..)) || 
      call(List<org.isf.accounting.model.BillItems> org.isf.accounting.manager.BillBrowserManager.getItems(..)) || 
      call(List<org.isf.accounting.model.BillPayments> org.isf.accounting.manager.BillBrowserManager.getPayments(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.manager.BillBrowserManager.getPendingBillsAffiliate(..)) || 
      call(int org.isf.accounting.manager.BillBrowserManager.newBill(..)) || 
      call(boolean org.isf.accounting.manager.BillBrowserManager.newBillItems(..)) || 
      call(boolean org.isf.accounting.manager.BillBrowserManager.newBillPayments(..)) || 
      call(boolean org.isf.accounting.manager.BillBrowserManager.updateBill(..)) || 
      call(boolean org.isf.accounting.manager.BillBrowserManager.deleteBill(..)) || 
      call(List<org.isf.accounting.model.BillItems> org.isf.accounting.manager.BillBrowserManager.getDistinctItems(..)) || 
      call(List<java.lang.String> org.isf.accounting.service.AccountingBillIoOperationRepository.findUserDistinctByOrderByUserAsc(..)) || 
      call(void org.isf.accounting.service.AccountingBillIoOperationRepository.updateDeleteWhereId(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingBillIoOperationRepository.findByDateBetween(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingBillIoOperationRepository.findByDateAndPatient(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingBillIoOperationRepository.findAllPendindBillsByBillPatient(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingBillIoOperationRepository.findAllWhereDatesAndBillItem(..)) || 
      call(void org.isf.accounting.service.AccountingPatientMergedEventListener.handle(..)) || 
      call(void org.isf.accounting.service.AccountingBillItemsIoOperationRepository.deleteWhereId(..)) || 
      call(List<org.isf.accounting.model.BillItems> org.isf.accounting.service.AccountingBillItemsIoOperationRepository.findAllGroupByDescription(..)) || 
      call(org.isf.accounting.model.Bill org.isf.accounting.service.AccountingIoOperations.getBill(..)) || 
      call(List<java.lang.String> org.isf.accounting.service.AccountingIoOperations.getUsers(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingIoOperations.getPendingBills(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingIoOperations.getBills(..)) || 
      call(List<org.isf.accounting.model.BillItems> org.isf.accounting.service.AccountingIoOperations.getItems(..)) || 
      call(List<org.isf.accounting.model.BillPayments> org.isf.accounting.service.AccountingIoOperations.getPayments(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingIoOperations.getPendingBillsAffiliate(..)) || 
      call(int org.isf.accounting.service.AccountingIoOperations.newBill(..)) || 
      call(boolean org.isf.accounting.service.AccountingIoOperations.newBillItems(..)) || 
      call(boolean org.isf.accounting.service.AccountingIoOperations.newBillPayments(..)) || 
      call(boolean org.isf.accounting.service.AccountingIoOperations.updateBill(..)) || 
      call(boolean org.isf.accounting.service.AccountingIoOperations.deleteBill(..)) || 
      call(List<org.isf.accounting.model.BillItems> org.isf.accounting.service.AccountingIoOperations.getDistictsBillItems(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingIoOperations.getBillsBetweenDatesWherePatient(..)) || 
      call(List<org.isf.accounting.model.BillPayments> org.isf.accounting.service.AccountingIoOperations.getPaymentsBetweenDatesWherePatient(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingIoOperations.getBillsBetweenDates(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingIoOperations.getBillsBetweenDatesWhereBillItem(..)) || 
      call(List<org.isf.accounting.model.Bill> org.isf.accounting.service.AccountingIoOperations.getAllPatientsBills(..)) || 
      call(List<org.isf.accounting.model.BillPayments> org.isf.accounting.service.AccountingBillPaymentIoOperationRepository.findByDateBetweenOrderByIdAscDateAsc(..)) || 
      call(List<org.isf.accounting.model.BillPayments> org.isf.accounting.service.AccountingBillPaymentIoOperationRepository.findAllWherBillIdByOrderByBillAndDate(..)) || 
      call(List<org.isf.accounting.model.BillPayments> org.isf.accounting.service.AccountingBillPaymentIoOperationRepository.findAllByOrderByBillAndDate(..)) || 
      call(List<java.lang.String> org.isf.accounting.service.AccountingBillPaymentIoOperationRepository.findUserDistinctByOrderByUserAsc(..)) || 
      call(void org.isf.accounting.service.AccountingBillPaymentIoOperationRepository.deleteWhereId(..)) || 
      call(List<org.isf.accounting.model.BillPayments> org.isf.accounting.service.AccountingBillPaymentIoOperationRepository.findByDateAndPatient(..)) || 
      call(List<org.isf.operation.model.OperationRow> org.isf.operation.manager.OperationRowBrowserManager.getOperationRowByAdmission(..)) || 
      call(List<org.isf.operation.model.OperationRow> org.isf.operation.manager.OperationRowBrowserManager.getOperationRowByOpd(..)) || 
      call(boolean org.isf.operation.manager.OperationRowBrowserManager.deleteOperationRow(..)) || 
      call(boolean org.isf.operation.manager.OperationRowBrowserManager.updateOperationRow(..)) || 
      call(boolean org.isf.operation.manager.OperationRowBrowserManager.newOperationRow(..)) || 
      call(boolean org.isf.operation.manager.OperationBrowserManager.isCodePresent(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.manager.OperationBrowserManager.getOperation(..)) || 
      call(boolean org.isf.operation.manager.OperationBrowserManager.updateOperation(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.manager.OperationBrowserManager.getOperationByTypeDescription(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.manager.OperationBrowserManager.getOperationOpd(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.manager.OperationBrowserManager.getOperationAdm(..)) || 
      call(boolean org.isf.operation.manager.OperationBrowserManager.newOperation(..)) || 
      call(boolean org.isf.operation.manager.OperationBrowserManager.deleteOperation(..)) || 
      call(org.isf.operation.model.Operation org.isf.operation.manager.OperationBrowserManager.getOperationByCode(..)) || 
      call(boolean org.isf.operation.manager.OperationBrowserManager.descriptionControl(..)) || 
      call(List<org.isf.operation.model.OperationRow> org.isf.operation.service.OperationRowIoOperations.getOperationRow(..)) || 
      call(List<org.isf.operation.model.OperationRow> org.isf.operation.service.OperationRowIoOperations.getOperationRowByAdmission(..)) || 
      call(List<org.isf.operation.model.OperationRow> org.isf.operation.service.OperationRowIoOperations.getOperationRowByOpd(..)) || 
      call(boolean org.isf.operation.service.OperationRowIoOperations.deleteOperationRow(..)) || 
      call(void org.isf.operation.service.OperationRowIoOperations.updateOperationRow(..)) || 
      call(void org.isf.operation.service.OperationRowIoOperations.newOperationRow(..)) || 
      call(boolean org.isf.operation.service.OperationIoOperations.isCodePresent(..)) || 
      call(boolean org.isf.operation.service.OperationIoOperations.updateOperation(..)) || 
      call(org.isf.operation.model.Operation org.isf.operation.service.OperationIoOperations.findByCode(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.service.OperationIoOperations.getOperationByTypeDescription(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.service.OperationIoOperations.getOperationOpd(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.service.OperationIoOperations.getOperationAdm(..)) || 
      call(boolean org.isf.operation.service.OperationIoOperations.newOperation(..)) || 
      call(boolean org.isf.operation.service.OperationIoOperations.deleteOperation(..)) || 
      call(boolean org.isf.operation.service.OperationIoOperations.isDescriptionPresent(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.service.OperationIoOperationRepository.findByOrderByDescriptionAsc(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.service.OperationIoOperationRepository.findAllByType_DescriptionContainsOrderByDescriptionAsc(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.service.OperationIoOperationRepository.findAllWithoutDescriptionOpd(..)) || 
      call(List<org.isf.operation.model.Operation> org.isf.operation.service.OperationIoOperationRepository.findAllWithoutDescriptionAdm(..)) || 
      call(boolean org.isf.admtype.manager.AdmissionTypeBrowserManager.isCodePresent(..)) || 
      call(boolean org.isf.admtype.manager.AdmissionTypeBrowserManager.updateAdmissionType(..)) || 
      call(List<org.isf.admtype.model.AdmissionType> org.isf.admtype.manager.AdmissionTypeBrowserManager.getAdmissionType(..)) || 
      call(boolean org.isf.admtype.manager.AdmissionTypeBrowserManager.newAdmissionType(..)) || 
      call(boolean org.isf.admtype.manager.AdmissionTypeBrowserManager.deleteAdmissionType(..)) || 
      call(boolean org.isf.admtype.service.AdmissionTypeIoOperation.isCodePresent(..)) || 
      call(boolean org.isf.admtype.service.AdmissionTypeIoOperation.updateAdmissionType(..)) || 
      call(List<org.isf.admtype.model.AdmissionType> org.isf.admtype.service.AdmissionTypeIoOperation.getAdmissionType(..)) || 
      call(boolean org.isf.admtype.service.AdmissionTypeIoOperation.newAdmissionType(..)) || 
      call(boolean org.isf.admtype.service.AdmissionTypeIoOperation.deleteAdmissionType(..)) || 
      call(boolean org.isf.medtype.manager.MedicalTypeBrowserManager.isCodePresent(..)) || 
      call(boolean org.isf.medtype.manager.MedicalTypeBrowserManager.updateMedicalType(..)) || 
      call(boolean org.isf.medtype.manager.MedicalTypeBrowserManager.newMedicalType(..)) || 
      call(boolean org.isf.medtype.manager.MedicalTypeBrowserManager.deleteMedicalType(..)) || 
      call(List<org.isf.medtype.model.MedicalType> org.isf.medtype.manager.MedicalTypeBrowserManager.getMedicalType(..)) || 
      call(boolean org.isf.medtype.service.MedicalTypeIoOperation.isCodePresent(..)) || 
      call(boolean org.isf.medtype.service.MedicalTypeIoOperation.updateMedicalType(..)) || 
      call(List<org.isf.medtype.model.MedicalType> org.isf.medtype.service.MedicalTypeIoOperation.getMedicalTypes(..)) || 
      call(boolean org.isf.medtype.service.MedicalTypeIoOperation.newMedicalType(..)) || 
      call(boolean org.isf.medtype.service.MedicalTypeIoOperation.deleteMedicalType(..)) || 
      call(boolean org.isf.opetype.manager.OperationTypeBrowserManager.isCodePresent(..)) || 
      call(boolean org.isf.opetype.manager.OperationTypeBrowserManager.updateOperationType(..)) || 
      call(List<org.isf.opetype.model.OperationType> org.isf.opetype.manager.OperationTypeBrowserManager.getOperationType(..)) || 
      call(boolean org.isf.opetype.manager.OperationTypeBrowserManager.newOperationType(..)) || 
      call(boolean org.isf.opetype.manager.OperationTypeBrowserManager.deleteOperationType(..)) || 
      call(boolean org.isf.opetype.service.OperationTypeIoOperation.isCodePresent(..)) || 
      call(boolean org.isf.opetype.service.OperationTypeIoOperation.updateOperationType(..)) || 
      call(List<org.isf.opetype.model.OperationType> org.isf.opetype.service.OperationTypeIoOperation.getOperationType(..)) || 
      call(boolean org.isf.opetype.service.OperationTypeIoOperation.newOperationType(..)) || 
      call(boolean org.isf.opetype.service.OperationTypeIoOperation.deleteOperationType(..)) || 
      call(boolean org.isf.disease.manager.DiseaseBrowserManager.isCodePresent(..)) || 
      call(org.isf.disease.model.Disease org.isf.disease.manager.DiseaseBrowserManager.updateDisease(..)) || 
      call(org.isf.disease.model.Disease org.isf.disease.manager.DiseaseBrowserManager.getDiseaseByCode(..)) || 
      call(org.isf.disease.model.Disease org.isf.disease.manager.DiseaseBrowserManager.newDisease(..)) || 
      call(boolean org.isf.disease.manager.DiseaseBrowserManager.deleteDisease(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.manager.DiseaseBrowserManager.getDisease(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.manager.DiseaseBrowserManager.getDiseaseOpd(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.manager.DiseaseBrowserManager.getDiseaseIpdIn(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.manager.DiseaseBrowserManager.getDiseaseIpdOut(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.manager.DiseaseBrowserManager.getDiseaseAll(..)) || 
      call(boolean org.isf.disease.manager.DiseaseBrowserManager.descriptionControl(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAll(..)) || 
      call(org.isf.disease.model.Disease org.isf.disease.service.DiseaseIoOperationRepository.findOneByDescriptionAndTypeCode(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByDiseaseTypeCode(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByDiseaseTypeCodeAndOpd(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByDiseaseTypeCodeAndIpdIn(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByDiseaseTypeCodeAndIpdOut(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByDiseaseTypeCodeAndOpdAndIpdIn(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByDiseaseTypeCodeAndOpdAndIpdOut(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByDiseaseTypeCodeAndIpdInAndIpdOut(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByDiseaseTypeCodeAndOpdAndIpdInAndIpdOut(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByOpd(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByIpdIn(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByIpdOut(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByOpdAndIpdIn(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByOpdAndIpdOut(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByIpdInAndIpdOut(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperationRepository.findAllByOpdAndIpdInAndIpdOut(..)) || 
      call(boolean org.isf.disease.service.DiseaseIoOperations.isCodePresent(..)) || 
      call(org.isf.disease.model.Disease org.isf.disease.service.DiseaseIoOperations.updateDisease(..)) || 
      call(org.isf.disease.model.Disease org.isf.disease.service.DiseaseIoOperations.getDiseaseByCode(..)) || 
      call(List<org.isf.disease.model.Disease> org.isf.disease.service.DiseaseIoOperations.getDiseases(..)) || 
      call(org.isf.disease.model.Disease org.isf.disease.service.DiseaseIoOperations.newDisease(..)) || 
      call(boolean org.isf.disease.service.DiseaseIoOperations.deleteDisease(..)) || 
      call(boolean org.isf.disease.service.DiseaseIoOperations.isDescriptionPresent(..)) || 
      call(List<org.isf.opd.model.Opd> org.isf.opd.manager.OpdBrowserManager.getOpd(..)) || 
      call(int org.isf.opd.manager.OpdBrowserManager.getProgYear(..)) || 
      call(org.isf.opd.model.Opd org.isf.opd.manager.OpdBrowserManager.updateOpd(..)) || 
      call(List<org.isf.opd.model.Opd> org.isf.opd.manager.OpdBrowserManager.getOpdList(..)) || 
      call(boolean org.isf.opd.manager.OpdBrowserManager.newOpd(..)) || 
      call(boolean org.isf.opd.manager.OpdBrowserManager.deleteOpd(..)) || 
      call(java.lang.Boolean org.isf.opd.manager.OpdBrowserManager.isExistOpdNum(..)) || 
      call(org.isf.opd.model.Opd org.isf.opd.manager.OpdBrowserManager.getLastOpd(..)) || 
      call(void org.isf.opd.manager.OpdBrowserManager.setPatientConsistency(..)) || 
      call(List<org.isf.opd.model.Opd> org.isf.opd.service.OpdIoOperationRepository.findAllOrderByProgYearDesc(..)) || 
      call(List<org.isf.opd.model.Opd> org.isf.opd.service.OpdIoOperationRepository.findAllByPatient_CodeOrderByProgYearDesc(..)) || 
      call(java.lang.Integer org.isf.opd.service.OpdIoOperationRepository.findMaxProgYear(..)) || 
      call(java.lang.Integer org.isf.opd.service.OpdIoOperationRepository.findMaxProgYearWhereDateBetween(..)) || 
      call(List<org.isf.opd.model.Opd> org.isf.opd.service.OpdIoOperationRepository.findByProgYear(..)) || 
      call(List<org.isf.opd.model.Opd> org.isf.opd.service.OpdIoOperationRepository.findByProgYearAndDateBetween(..)) || 
      call(boolean org.isf.opd.service.OpdIoOperations.isCodePresent(..)) || 
      call(int org.isf.opd.service.OpdIoOperations.getProgYear(..)) || 
      call(org.isf.opd.model.Opd org.isf.opd.service.OpdIoOperations.updateOpd(..)) || 
      call(List<org.isf.opd.model.Opd> org.isf.opd.service.OpdIoOperations.getOpdList(..)) || 
      call(boolean org.isf.opd.service.OpdIoOperations.newOpd(..)) || 
      call(boolean org.isf.opd.service.OpdIoOperations.deleteOpd(..)) || 
      call(java.lang.Boolean org.isf.opd.service.OpdIoOperations.isExistOpdNum(..)) || 
      call(org.isf.opd.model.Opd org.isf.opd.service.OpdIoOperations.getLastOpd(..)) || 
      call(void org.isf.opd.service.OpdPatientMergedEventListener.handle(..)) || 
      call(java.lang.String org.isf.agetype.manager.AgeTypeBrowserManager.getTypeByAge(..)) || 
      call(boolean org.isf.agetype.manager.AgeTypeBrowserManager.updateAgeType(..)) || 
      call(List<org.isf.agetype.model.AgeType> org.isf.agetype.manager.AgeTypeBrowserManager.getAgeType(..)) || 
      call(org.isf.agetype.model.AgeType org.isf.agetype.manager.AgeTypeBrowserManager.getTypeByCode(..)) || 
      call(boolean org.isf.agetype.service.AgeTypeIoOperations.updateAgeType(..)) || 
      call(List<org.isf.agetype.model.AgeType> org.isf.agetype.service.AgeTypeIoOperations.getAgeType(..)) || 
      call(org.isf.agetype.model.AgeType org.isf.agetype.service.AgeTypeIoOperations.getAgeTypeByCode(..)) || 
      call(boolean org.isf.dlvrtype.manager.DeliveryTypeBrowserManager.isCodePresent(..)) || 
      call(boolean org.isf.dlvrtype.manager.DeliveryTypeBrowserManager.updateDeliveryType(..)) || 
      call(boolean org.isf.dlvrtype.manager.DeliveryTypeBrowserManager.newDeliveryType(..)) || 
      call(boolean org.isf.dlvrtype.manager.DeliveryTypeBrowserManager.deleteDeliveryType(..)) || 
      call(List<org.isf.dlvrtype.model.DeliveryType> org.isf.dlvrtype.manager.DeliveryTypeBrowserManager.getDeliveryType(..)) || 
      call(boolean org.isf.dlvrtype.service.DeliveryTypeIoOperation.isCodePresent(..)) || 
      call(boolean org.isf.dlvrtype.service.DeliveryTypeIoOperation.updateDeliveryType(..)) || 
      call(boolean org.isf.dlvrtype.service.DeliveryTypeIoOperation.newDeliveryType(..)) || 
      call(boolean org.isf.dlvrtype.service.DeliveryTypeIoOperation.deleteDeliveryType(..)) || 
      call(List<org.isf.dlvrtype.model.DeliveryType> org.isf.dlvrtype.service.DeliveryTypeIoOperation.getDeliveryType(..)) || 
      call(boolean org.isf.medicalstockward.manager.MovWardBrowserManager.updateMovementWard(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.manager.MovWardBrowserManager.getWardMovementsToWard(..)) || 
      call(void org.isf.medicalstockward.manager.MovWardBrowserManager.newMovementWard(..)) || 
      call(int org.isf.medicalstockward.manager.MovWardBrowserManager.getCurrentQuantityInWard(..)) || 
      call(List<org.isf.medicalstockward.model.MedicalWard> org.isf.medicalstockward.manager.MovWardBrowserManager.getMedicalsWard(..)) || 
      call(List<org.isf.medicalstockward.model.MedicalWard> org.isf.medicalstockward.manager.MovWardBrowserManager.getMedicalsWardTotalQuantity(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.manager.MovWardBrowserManager.getMovementWard(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.manager.MovWardBrowserManager.getMovementToPatient(..)) || 
      call(org.isf.medicalstockward.model.MedicalWard org.isf.medicalstockward.service.MedicalStockWardIoOperationRepository.findOneWhereCodeAndMedicalAndLot(..)) || 
      call(java.lang.Double org.isf.medicalstockward.service.MedicalStockWardIoOperationRepository.findQuantityInWardWhereMedical(..)) || 
      call(java.lang.Double org.isf.medicalstockward.service.MedicalStockWardIoOperationRepository.findQuantityInWardWhereMedicalAndWard(..)) || 
      call(void org.isf.medicalstockward.service.MedicalStockWardIoOperationRepository.updateInQuantity(..)) || 
      call(void org.isf.medicalstockward.service.MedicalStockWardIoOperationRepository.updateOutQuantity(..)) || 
      call(void org.isf.medicalstockward.service.MedicalStockWardIoOperationRepository.insertMedicalWard(..)) || 
      call(List<org.isf.medicalstockward.model.MedicalWard> org.isf.medicalstockward.service.MedicalStockWardIoOperationRepository.findAllWhereWard(..)) || 
      call(org.isf.medicalstockward.model.MedicalWard org.isf.medicalstockward.service.MedicalStockWardIoOperationRepository.findOneWhereCodeAndMedical(..)) || 
      call(void org.isf.medicalstockward.service.MovementWardPatientMergedEventListener.handle(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.service.MovementWardIoOperationRepository.findWardMovements(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.service.MovementWardIoOperationRepository.findWardMovementPat(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.service.MedicalStockWardIoOperations.findAllForPatient(..)) || 
      call(boolean org.isf.medicalstockward.service.MedicalStockWardIoOperations.updateStockWardQuantity(..)) || 
      call(boolean org.isf.medicalstockward.service.MedicalStockWardIoOperations.updateMovementWard(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.service.MedicalStockWardIoOperations.getWardMovementsToWard(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.service.MedicalStockWardIoOperations.getWardMovements(..)) || 
      call(void org.isf.medicalstockward.service.MedicalStockWardIoOperations.newMovementWard(..)) || 
      call(int org.isf.medicalstockward.service.MedicalStockWardIoOperations.getCurrentQuantityInWard(..)) || 
      call(boolean org.isf.medicalstockward.service.MedicalStockWardIoOperations.deleteMovementWard(..)) || 
      call(List<org.isf.medicalstockward.model.MedicalWard> org.isf.medicalstockward.service.MedicalStockWardIoOperations.getMedicalsWard(..)) || 
      call(List<org.isf.medicalstockward.model.MovementWard> org.isf.medicalstockward.service.MedicalStockWardIoOperations.getWardMovementsToPatient(..)) || 
      call(List<org.isf.medicalstockward.model.MedicalWard> org.isf.medicalstockward.service.MedicalStockWardIoOperations.getMedicalsWardTotalQuantity(..)) || 
      call(void org.isf.sms.manager.SmsManager.delete(..)) || 
      call(List<org.isf.sms.model.Sms> org.isf.sms.manager.SmsManager.getAll(..)) || 
      call(void org.isf.sms.manager.SmsManager.saveOrUpdate(..)) || 
      call(void org.isf.sms.service.SmsOperations.delete(..)) || 
      call(List<org.isf.sms.model.Sms> org.isf.sms.service.SmsOperations.getAll(..)) || 
      call(List<org.isf.sms.model.Sms> org.isf.sms.service.SmsOperations.getList(..)) || 
      call(boolean org.isf.sms.service.SmsOperations.isCodePresent(..)) || 
      call(boolean org.isf.sms.service.SmsOperations.saveOrUpdate(..)) || 
      call(org.isf.sms.model.Sms org.isf.sms.service.SmsOperations.getByID(..)) || 
      call(void org.isf.sms.service.SmsOperations.deleteByModuleModuleID(..)) || 
      call(void org.isf.sms.service.SmsSender.run(..)) || 
      call(boolean org.isf.sms.service.SmsSenderOperations.initialize(..)) || 
      call(boolean org.isf.sms.service.SmsSenderOperations.sendSMS(..)) || 
      call(boolean org.isf.sms.service.SmsSenderOperations.terminate(..)) || 
      call(org.isf.medicals.model.Medical org.isf.medicals.manager.MedicalBrowsingManager.getMedical(..)) || 
      call(boolean org.isf.medicals.manager.MedicalBrowsingManager.updateMedical(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.manager.MedicalBrowsingManager.getMedicals(..)) || 
      call(boolean org.isf.medicals.manager.MedicalBrowsingManager.newMedical(..)) || 
      call(boolean org.isf.medicals.manager.MedicalBrowsingManager.deleteMedical(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.manager.MedicalBrowsingManager.getMedicalsSortedByName(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.manager.MedicalBrowsingManager.getMedicalsSortedByCode(..)) || 
      call(void org.isf.medicals.manager.MedicalBrowsingManager.checkMedical(..)) || 
      call(org.isf.medicals.model.Medical org.isf.medicals.service.MedicalsIoOperations.getMedical(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperations.getMedicalsByType(..)) || 
      call(boolean org.isf.medicals.service.MedicalsIoOperations.updateMedical(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperations.getMedicals(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperations.medicalCheck(..)) || 
      call(boolean org.isf.medicals.service.MedicalsIoOperations.productCodeExists(..)) || 
      call(boolean org.isf.medicals.service.MedicalsIoOperations.medicalExists(..)) || 
      call(boolean org.isf.medicals.service.MedicalsIoOperations.newMedical(..)) || 
      call(boolean org.isf.medicals.service.MedicalsIoOperations.deleteMedical(..)) || 
      call(boolean org.isf.medicals.service.MedicalsIoOperations.isMedicalReferencedInStockMovement(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllByOrderByTypeAndDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereDescriptionSoundsLike(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereDescriptionOrderByDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllByOrderByDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereDescriptionAndTypeAndCriticalOrderByTypeAndDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereDescriptionAndTypeOrderByTypeAndDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereDescriptionAndCriticalOrderByTypeAndDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereDescriptionOrderByTypeAndDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereTypeAndCriticalOrderByTypeAndDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereTypeOrderByTypeAndDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereCriticalOrderByTypeAndDescription(..)) || 
      call(org.isf.medicals.model.Medical org.isf.medicals.service.MedicalsIoOperationRepository.findOneWhereProductCode(..)) || 
      call(org.isf.medicals.model.Medical org.isf.medicals.service.MedicalsIoOperationRepository.findOneWhereDescriptionAndType(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllOrderBySmartCodeAndDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereTypeOrderByDescription(..)) || 
      call(List<org.isf.medicals.model.Medical> org.isf.medicals.service.MedicalsIoOperationRepository.findAllWhereTypeOrderBySmartCodeAndDescription(..)) || 
      call(List<org.isf.visits.model.Visit> org.isf.visits.manager.VisitManager.getVisits(..)) || 
      call(List<org.isf.visits.model.Visit> org.isf.visits.manager.VisitManager.getVisitsWard(..)) || 
      call(org.isf.visits.model.Visit org.isf.visits.manager.VisitManager.newVisit(..)) || 
      call(org.isf.visits.model.Visit org.isf.visits.manager.VisitManager.findVisit(..)) || 
      call(boolean org.isf.visits.manager.VisitManager.deleteAllVisits(..)) || 
      call(boolean org.isf.visits.manager.VisitManager.newVisits(..)) || 
      call(List<org.isf.visits.model.Visit> org.isf.visits.manager.VisitManager.getVisitsOPD(..)) || 
      call(void org.isf.visits.manager.VisitManager.deleteVisit(..)) || 
      call(void org.isf.visits.service.VisitsPatientMergedEventListener.handle(..)) || 
      call(List<org.isf.visits.model.Visit> org.isf.visits.service.VisitsIoOperationRepository.findAllWhereWardByOrderDateAsc(..)) || 
      call(List<org.isf.visits.model.Visit> org.isf.visits.service.VisitsIoOperationRepository.findAllWherePatientByOrderPatientAndDateAsc(..)) || 
      call(boolean org.isf.visits.service.VisitsIoOperations.isCodePresent(..)) || 
      call(List<org.isf.visits.model.Visit> org.isf.visits.service.VisitsIoOperations.getVisits(..)) || 
      call(List<org.isf.visits.model.Visit> org.isf.visits.service.VisitsIoOperations.getVisitsWard(..)) || 
      call(org.isf.visits.model.Visit org.isf.visits.service.VisitsIoOperations.newVisit(..)) || 
      call(org.isf.visits.model.Visit org.isf.visits.service.VisitsIoOperations.findVisit(..)) || 
      call(boolean org.isf.visits.service.VisitsIoOperations.deleteAllVisits(..)) || 
      call(List<org.isf.visits.model.Visit> org.isf.visits.service.VisitsIoOperations.getVisitsOPD(..)) || 
      call(void org.isf.visits.service.VisitsIoOperations.deleteVisit(..)) || 
      call(boolean org.isf.priceslist.manager.PriceListManager.newList(..)) || 
      call(List<org.isf.priceslist.model.Price> org.isf.priceslist.manager.PriceListManager.getPrices(..)) || 
      call(boolean org.isf.priceslist.manager.PriceListManager.updatePrices(..)) || 
      call(boolean org.isf.priceslist.manager.PriceListManager.updateList(..)) || 
      call(boolean org.isf.priceslist.manager.PriceListManager.deleteList(..)) || 
      call(boolean org.isf.priceslist.manager.PriceListManager.copyList(..)) || 
      call(boolean org.isf.priceslist.service.PricesListIoOperations.newList(..)) || 
      call(org.isf.priceslist.model.PriceList org.isf.priceslist.service.PricesListIoOperations.insertNewPriceList(..)) || 
      call(List<org.isf.priceslist.model.Price> org.isf.priceslist.service.PricesListIoOperations.getPrices(..)) || 
      call(boolean org.isf.priceslist.service.PricesListIoOperations.updatePrices(..)) || 
      call(boolean org.isf.priceslist.service.PricesListIoOperations.updateList(..)) || 
      call(boolean org.isf.priceslist.service.PricesListIoOperations.deleteList(..)) || 
      call(boolean org.isf.priceslist.service.PricesListIoOperations.copyList(..)) || 
      call(void org.isf.priceslist.service.PricesListIoOperationRepository.deleteById(..)) || 
      call(void org.isf.priceslist.service.PriceIoOperationRepository.deleteByListId(..)) || 
      call(boolean org.isf.dlvrrestype.manager.DeliveryResultTypeBrowserManager.newDeliveryResultType(..)) || 
      call(boolean org.isf.dlvrrestype.manager.DeliveryResultTypeBrowserManager.isCodePresent(..)) || 
      call(boolean org.isf.dlvrrestype.manager.DeliveryResultTypeBrowserManager.updateDeliveryResultType(..)) || 
      call(boolean org.isf.dlvrrestype.manager.DeliveryResultTypeBrowserManager.deleteDeliveryResultType(..)) || 
      call(List<org.isf.dlvrrestype.model.DeliveryResultType> org.isf.dlvrrestype.manager.DeliveryResultTypeBrowserManager.getDeliveryResultType(..)) || 
      call(boolean org.isf.dlvrrestype.service.DeliveryResultTypeIoOperation.newDeliveryResultType(..)) || 
      call(boolean org.isf.dlvrrestype.service.DeliveryResultTypeIoOperation.isCodePresent(..)) || 
      call(boolean org.isf.dlvrrestype.service.DeliveryResultTypeIoOperation.updateDeliveryResultType(..)) || 
      call(boolean org.isf.dlvrrestype.service.DeliveryResultTypeIoOperation.deleteDeliveryResultType(..)) || 
      call(List<org.isf.dlvrrestype.model.DeliveryResultType> org.isf.dlvrrestype.service.DeliveryResultTypeIoOperation.getDeliveryResultType(..)) || 
      call(boolean org.isf.exa.manager.ExamBrowsingManager.newExam(..)) || 
      call(boolean org.isf.exa.manager.ExamBrowsingManager.updateExam(..)) || 
      call(List<org.isf.exa.model.Exam> org.isf.exa.manager.ExamBrowsingManager.getExams(..)) || 
      call(List<org.isf.exatype.model.ExamType> org.isf.exa.manager.ExamBrowsingManager.getExamType(..)) || 
      call(boolean org.isf.exa.manager.ExamBrowsingManager.deleteExam(..)) || 
      call(boolean org.isf.exa.manager.ExamBrowsingManager.isKeyPresent(..)) || 
      call(List<org.isf.exa.model.Exam> org.isf.exa.manager.ExamBrowsingManager.getExamsByTypeDescription(..)) || 
      call(boolean org.isf.exa.manager.ExamRowBrowsingManager.newExamRow(..)) || 
      call(List<org.isf.exa.model.ExamRow> org.isf.exa.manager.ExamRowBrowsingManager.getExamRow(..)) || 
      call(boolean org.isf.exa.manager.ExamRowBrowsingManager.deleteExamRow(..)) || 
      call(List<org.isf.exa.model.ExamRow> org.isf.exa.manager.ExamRowBrowsingManager.getExamRowByExamCode(..)) || 
      call(boolean org.isf.exa.service.ExamIoOperations.isCodePresent(..)) || 
      call(boolean org.isf.exa.service.ExamIoOperations.newExamRow(..)) || 
      call(boolean org.isf.exa.service.ExamIoOperations.newExam(..)) || 
      call(boolean org.isf.exa.service.ExamIoOperations.updateExam(..)) || 
      call(List<org.isf.exatype.model.ExamType> org.isf.exa.service.ExamIoOperations.getExamType(..)) || 
      call(boolean org.isf.exa.service.ExamIoOperations.deleteExam(..)) || 
      call(boolean org.isf.exa.service.ExamIoOperations.deleteExamRow(..)) || 
      call(boolean org.isf.exa.service.ExamIoOperations.isRowPresent(..)) || 
      call(boolean org.isf.exa.service.ExamIoOperations.isKeyPresent(..)) || 
      call(List<org.isf.exa.model.Exam> org.isf.exa.service.ExamIoOperations.getExamsByDesc(..)) || 
      call(List<org.isf.exa.model.Exam> org.isf.exa.service.ExamIoOperations.getExamsByExamTypeDesc(..)) || 
      call(boolean org.isf.exa.service.ExamRowIoOperations.isCodePresent(..)) || 
      call(boolean org.isf.exa.service.ExamRowIoOperations.newExamRow(..)) || 
      call(boolean org.isf.exa.service.ExamRowIoOperations.updateExamRow(..)) || 
      call(List<org.isf.exa.model.ExamRow> org.isf.exa.service.ExamRowIoOperations.getExamRow(..)) || 
      call(List<org.isf.exa.model.ExamRow> org.isf.exa.service.ExamRowIoOperations.getExamsRowByDesc(..)) || 
      call(List<org.isf.exatype.model.ExamType> org.isf.exa.service.ExamRowIoOperations.getExamType(..)) || 
      call(boolean org.isf.exa.service.ExamRowIoOperations.deleteExamRow(..)) || 
      call(boolean org.isf.exa.service.ExamRowIoOperations.isRowPresent(..)) || 
      call(boolean org.isf.exa.service.ExamRowIoOperations.isKeyPresent(..)) || 
      call(List<org.isf.exa.model.ExamRow> org.isf.exa.service.ExamRowIoOperations.getExamRowByExamCode(..)) || 
      call(List<org.isf.lab.model.LaboratoryRow> org.isf.lab.manager.LabRowManager.getLabRowByLabId(..)) || 
      call(boolean org.isf.lab.manager.LabManager.updateLaboratory(..)) || 
      call(boolean org.isf.lab.manager.LabManager.newLabFirstProcedure(..)) || 
      call(boolean org.isf.lab.manager.LabManager.newLabSecondProcedure(..)) || 
      call(boolean org.isf.lab.manager.LabManager.editLabFirstProcedure(..)) || 
      call(boolean org.isf.lab.manager.LabManager.editLabSecondProcedure(..)) || 
      call(List<org.isf.lab.model.Laboratory> org.isf.lab.manager.LabManager.getLaboratory(..)) || 
      call(List<org.isf.lab.model.LaboratoryForPrint> org.isf.lab.manager.LabManager.getLaboratoryForPrint(..)) || 
      call(boolean org.isf.lab.manager.LabManager.deleteLaboratory(..)) || 
      call(boolean org.isf.lab.manager.LabManager.newLaboratory2(..)) || 
      call(boolean org.isf.lab.manager.LabManager.newLaboratory(..)) || 
      call(void org.isf.lab.manager.LabManager.setLabMultipleResults(..)) || 
      call(void org.isf.lab.service.LabPatientMergedEventListener.handle(..)) || 
      call(boolean org.isf.lab.service.LabIoOperations.isCodePresent(..)) || 
      call(boolean org.isf.lab.service.LabIoOperations.updateLaboratory(..)) || 
      call(boolean org.isf.lab.service.LabIoOperations.newLabSecondProcedure(..)) || 
      call(List<org.isf.lab.model.LaboratoryRow> org.isf.lab.service.LabIoOperations.getLabRow(..)) || 
      call(List<org.isf.lab.model.Laboratory> org.isf.lab.service.LabIoOperations.getLaboratory(..)) || 
      call(List<org.isf.lab.model.LaboratoryForPrint> org.isf.lab.service.LabIoOperations.getLaboratoryForPrint(..)) || 
      call(boolean org.isf.lab.service.LabIoOperations.newLabSecondProcedure2(..)) || 
      call(boolean org.isf.lab.service.LabIoOperations.updateLabFirstProcedure(..)) || 
      call(boolean org.isf.lab.service.LabIoOperations.updateLabSecondProcedure(..)) || 
      call(boolean org.isf.lab.service.LabIoOperations.deleteLaboratory(..)) || 
      call(java.lang.Integer org.isf.lab.service.LabIoOperations.newLaboratory(..)) || 
      call(boolean org.isf.medicalstock.manager.MovStockInsertingManager.prepareChargingMovement(..)) || 
      call(java.time.LocalDateTime org.isf.medicalstock.manager.MovStockInsertingManager.getLastMovementDate(..)) || 
      call(boolean org.isf.medicalstock.manager.MovStockInsertingManager.refNoExists(..)) || 
      call(List<org.isf.medicalstock.model.Lot> org.isf.medicalstock.manager.MovStockInsertingManager.getLotByMedical(..)) || 
      call(boolean org.isf.medicalstock.manager.MovStockInsertingManager.alertCriticalQuantity(..)) || 
      call(boolean org.isf.medicalstock.manager.MovStockInsertingManager.storeLot(..)) || 
      call(void org.isf.medicalstock.manager.MovStockInsertingManager.validateMovement(..)) || 
      call(boolean org.isf.medicalstock.manager.MovStockInsertingManager.prepareDishargingMovement(..)) || 
      call(List<org.isf.medicalstock.model.Movement> org.isf.medicalstock.manager.MovBrowserManager.getMovements(..)) || 
      call(List<org.isf.medicalstock.model.Movement> org.isf.medicalstock.manager.MovBrowserManager.getMovementsByReference(..)) || 
      call(org.isf.medicalstock.model.Movement org.isf.medicalstock.service.MovementIoOperationRepository.findAllByMedicalCode(..)) || 
      call(org.isf.medicalstock.model.Movement org.isf.medicalstock.service.MovementIoOperationRepository.findAllByMedicalCodeOrderByLot_(..)) || 
      call(List<java.lang.Integer> org.isf.medicalstock.service.MovementIoOperationRepository.findAllByLot(..)) || 
      call(List<org.isf.medicalstock.model.Movement> org.isf.medicalstock.service.MovementIoOperationRepository.findAllByRefNo(..)) || 
      call(java.time.LocalDateTime org.isf.medicalstock.service.MovementIoOperationRepository.findMaxDate(..)) || 
      call(List<java.lang.String> org.isf.medicalstock.service.MovementIoOperationRepository.findAllWhereRefNo(..)) || 
      call(List<java.lang.Integer> org.isf.medicalstock.service.MedicalStockIoOperations.getMedicalsFromLot(..)) || 
      call(List<org.isf.medicalstock.model.Lot> org.isf.medicalstock.service.MedicalStockIoOperations.getLotsByMedical(..)) || 
      call(boolean org.isf.medicalstock.service.MedicalStockIoOperations.lotExists(..)) || 
      call(List<org.isf.medicalstock.model.Movement> org.isf.medicalstock.service.MedicalStockIoOperations.getMovements(..)) || 
      call(List<org.isf.medicalstock.model.Movement> org.isf.medicalstock.service.MedicalStockIoOperations.getMovementForPrint(..)) || 
      call(java.time.LocalDateTime org.isf.medicalstock.service.MedicalStockIoOperations.getLastMovementDate(..)) || 
      call(boolean org.isf.medicalstock.service.MedicalStockIoOperations.refNoExists(..)) || 
      call(List<org.isf.medicalstock.model.Movement> org.isf.medicalstock.service.MedicalStockIoOperations.getMovementsByReference(..)) || 
      call(boolean org.isf.medicalstock.service.MedicalStockIoOperations.storeLot(..)) || 
      call(boolean org.isf.medicalstock.service.MedicalStockIoOperations.storeMovement(..)) || 
      call(java.lang.String org.isf.medicalstock.service.MedicalStockIoOperations.generateLotCode(..)) || 
      call(boolean org.isf.medicalstock.service.MedicalStockIoOperations.updateMedicalIncomingQuantity(..)) || 
      call(boolean org.isf.medicalstock.service.MedicalStockIoOperations.updateMedicalOutcomingQuantity(..)) || 
      call(boolean org.isf.medicalstock.service.MedicalStockIoOperations.updateMedicalWardQuantity(..)) || 
      call(java.lang.Integer org.isf.medicalstock.service.LotIoOperationRepository.getMainStoreQuantity(..)) || 
      call(java.lang.Double org.isf.medicalstock.service.LotIoOperationRepository.getWardsTotalQuantity(..)) || 
      call(java.lang.Double org.isf.medicalstock.service.LotIoOperationRepository.getQuantityByWard(..)) || 
      call(List<org.isf.medicalstock.model.Lot> org.isf.medicalstock.service.LotIoOperationRepository.findByMedicalOrderByDueDate(..)) || 
      call(List<java.lang.Object[]> org.isf.medicalstock.service.LotIoOperationRepository.findAllWhereLot(..)) || 
      call(org.isf.admission.model.Admission org.isf.admission.manager.AdmissionBrowserManager.getAdmission(..)) || 
      call(List<org.isf.admtype.model.AdmissionType> org.isf.admission.manager.AdmissionBrowserManager.getAdmissionType(..)) || 
      call(boolean org.isf.admission.manager.AdmissionBrowserManager.updateAdmission(..)) || 
      call(List<org.isf.admission.model.AdmittedPatient> org.isf.admission.manager.AdmissionBrowserManager.getAdmittedPatients(..)) || 
      call(org.isf.admission.model.Admission org.isf.admission.manager.AdmissionBrowserManager.getCurrentAdmission(..)) || 
      call(List<org.isf.admission.model.Admission> org.isf.admission.manager.AdmissionBrowserManager.getAdmissions(..)) || 
      call(boolean org.isf.admission.manager.AdmissionBrowserManager.newAdmission(..)) || 
      call(int org.isf.admission.manager.AdmissionBrowserManager.newAdmissionReturnKey(..)) || 
      call(List<org.isf.disctype.model.DischargeType> org.isf.admission.manager.AdmissionBrowserManager.getDischargeType(..)) || 
      call(int org.isf.admission.manager.AdmissionBrowserManager.getNextYProg(..)) || 
      call(int org.isf.admission.manager.AdmissionBrowserManager.getUsedWardBed(..)) || 
      call(boolean org.isf.admission.manager.AdmissionBrowserManager.deletePatientPhoto(..)) || 
      call(org.isf.admission.model.AdmittedPatient org.isf.admission.manager.AdmissionBrowserManager.loadAdmittedPatients(..)) || 
      call(boolean org.isf.admission.manager.AdmissionBrowserManager.setDeleted(..)) || 
      call(void org.isf.admission.service.AdmissionPatientMergedEventListener.handle(..)) || 
      call(List<org.isf.admission.model.Admission> org.isf.admission.service.AdmissionIoOperationRepository.findAllWhereWard(..)) || 
      call(org.isf.admission.model.Admission org.isf.admission.service.AdmissionIoOperationRepository.findOneWherePatientIn(..)) || 
      call(List<org.isf.admission.model.Admission> org.isf.admission.service.AdmissionIoOperationRepository.findAllWherePatientByOrderByDate(..)) || 
      call(List<org.isf.admission.model.Admission> org.isf.admission.service.AdmissionIoOperationRepository.findAllWhereWardAndDates(..)) || 
      call(List<org.isf.admission.model.Admission> org.isf.admission.service.AdmissionIoOperationRepository.findAllWhereWardIn(..)) || 
      call(org.isf.admission.model.Admission org.isf.admission.service.AdmissionIoOperations.getAdmission(..)) || 
      call(List<org.isf.admtype.model.AdmissionType> org.isf.admission.service.AdmissionIoOperations.getAdmissionType(..)) || 
      call(boolean org.isf.admission.service.AdmissionIoOperations.updateAdmission(..)) || 
      call(List<org.isf.admission.model.AdmittedPatient> org.isf.admission.service.AdmissionIoOperations.getAdmittedPatients(..)) || 
      call(org.isf.admission.model.Admission org.isf.admission.service.AdmissionIoOperations.getCurrentAdmission(..)) || 
      call(List<org.isf.admission.model.Admission> org.isf.admission.service.AdmissionIoOperations.getAdmissions(..)) || 
      call(boolean org.isf.admission.service.AdmissionIoOperations.newAdmission(..)) || 
      call(List<org.isf.disctype.model.DischargeType> org.isf.admission.service.AdmissionIoOperations.getDischargeType(..)) || 
      call(int org.isf.admission.service.AdmissionIoOperations.getNextYProg(..)) || 
      call(int org.isf.admission.service.AdmissionIoOperations.getUsedWardBed(..)) || 
      call(boolean org.isf.admission.service.AdmissionIoOperations.deletePatientPhoto(..)) || 
      call(org.isf.admission.model.AdmittedPatient org.isf.admission.service.AdmissionIoOperations.loadAdmittedPatient(..)) || 
      call(java.time.LocalDateTime org.isf.admission.service.AdmissionIoOperations.getNow(..)) || 
      call(boolean org.isf.admission.service.AdmissionIoOperations.setDeleted(..)) || 
      call(boolean org.isf.utils.db.DbQueryLogger.setData(..)) || 
      call(boolean org.isf.utils.db.DbQueryLogger.setDataWithParams(..)) || 
      call(boolean org.isf.exatype.manager.ExamTypeBrowserManager.isCodePresent(..)) || 
      call(List<org.isf.exatype.model.ExamType> org.isf.exatype.manager.ExamTypeBrowserManager.getExamType(..)) || 
      call(boolean org.isf.exatype.manager.ExamTypeBrowserManager.updateExamType(..)) || 
      call(boolean org.isf.exatype.manager.ExamTypeBrowserManager.newExamType(..)) || 
      call(boolean org.isf.exatype.manager.ExamTypeBrowserManager.deleteExamType(..)) || 
      call(boolean org.isf.exatype.service.ExamTypeIoOperation.isCodePresent(..)) || 
      call(List<org.isf.exatype.model.ExamType> org.isf.exatype.service.ExamTypeIoOperation.getExamType(..)) || 
      call(boolean org.isf.exatype.service.ExamTypeIoOperation.updateExamType(..)) || 
      call(boolean org.isf.exatype.service.ExamTypeIoOperation.newExamType(..)) || 
      call(boolean org.isf.exatype.service.ExamTypeIoOperation.deleteExamType(..)) || 
      call(boolean org.isf.medstockmovtype.manager.MedicaldsrstockmovTypeBrowserManager.isCodePresent(..)) || 
      call(List<org.isf.medstockmovtype.model.MovementType> org.isf.medstockmovtype.manager.MedicaldsrstockmovTypeBrowserManager.getMedicaldsrstockmovType(..)) || 
      call(boolean org.isf.medstockmovtype.manager.MedicaldsrstockmovTypeBrowserManager.updateMedicaldsrstockmovType(..)) || 
      call(boolean org.isf.medstockmovtype.manager.MedicaldsrstockmovTypeBrowserManager.newMedicaldsrstockmovType(..)) || 
      call(boolean org.isf.medstockmovtype.manager.MedicaldsrstockmovTypeBrowserManager.deleteMedicaldsrstockmovType(..)) || 
      call(org.isf.medstockmovtype.model.MovementType org.isf.medstockmovtype.manager.MedicaldsrstockmovTypeBrowserManager.getMovementType(..)) || 
      call(boolean org.isf.medstockmovtype.service.MedicalStockMovementTypeIoOperation.isCodePresent(..)) || 
      call(List<org.isf.medstockmovtype.model.MovementType> org.isf.medstockmovtype.service.MedicalStockMovementTypeIoOperation.getMedicaldsrstockmovType(..)) || 
      call(boolean org.isf.medstockmovtype.service.MedicalStockMovementTypeIoOperation.updateMedicaldsrstockmovType(..)) || 
      call(boolean org.isf.medstockmovtype.service.MedicalStockMovementTypeIoOperation.newMedicaldsrstockmovType(..)) || 
      call(boolean org.isf.medstockmovtype.service.MedicalStockMovementTypeIoOperation.deleteMedicaldsrstockmovType(..)) || 
      call(org.isf.medstockmovtype.model.MovementType org.isf.medstockmovtype.service.MedicalStockMovementTypeIoOperation.findOneByCode(..)) || 
      call(boolean org.isf.distype.manager.DiseaseTypeBrowserManager.isCodePresent(..)) || 
      call(boolean org.isf.distype.manager.DiseaseTypeBrowserManager.updateDiseaseType(..)) || 
      call(boolean org.isf.distype.manager.DiseaseTypeBrowserManager.newDiseaseType(..)) || 
      call(boolean org.isf.distype.manager.DiseaseTypeBrowserManager.deleteDiseaseType(..)) || 
      call(List<org.isf.distype.model.DiseaseType> org.isf.distype.manager.DiseaseTypeBrowserManager.getDiseaseType(..)) || 
      call(boolean org.isf.distype.service.DiseaseTypeIoOperation.isCodePresent(..)) || 
      call(boolean org.isf.distype.service.DiseaseTypeIoOperation.updateDiseaseType(..)) || 
      call(List<org.isf.distype.model.DiseaseType> org.isf.distype.service.DiseaseTypeIoOperation.getDiseaseTypes(..)) || 
      call(boolean org.isf.distype.service.DiseaseTypeIoOperation.newDiseaseType(..)) || 
      call(boolean org.isf.distype.service.DiseaseTypeIoOperation.deleteDiseaseType(..)) || 
      call(org.isf.hospital.model.Hospital org.isf.hospital.manager.HospitalBrowsingManager.updateHospital(..)) || 
      call(java.lang.String org.isf.hospital.manager.HospitalBrowsingManager.getHospitalCurrencyCod(..)) || 
      call(org.isf.hospital.model.Hospital org.isf.hospital.manager.HospitalBrowsingManager.getHospital(..)) || 
      call(org.isf.hospital.model.Hospital org.isf.hospital.service.HospitalIoOperations.updateHospital(..)) || 
      call(java.lang.String org.isf.hospital.service.HospitalIoOperations.getHospitalCurrencyCod(..)) || 
      call(org.isf.hospital.model.Hospital org.isf.hospital.service.HospitalIoOperations.getHospital(..)) || 
      call(List<java.lang.String> org.isf.hospital.service.HospitalIoOperationRepository.findAllHospitalCurrencyCode(..)) || 
      call(int org.isf.patvac.manager.PatVacManager.getProgYear(..)) || 
      call(boolean org.isf.patvac.manager.PatVacManager.updatePatientVaccine(..)) || 
      call(List<org.isf.patvac.model.PatientVaccine> org.isf.patvac.manager.PatVacManager.getPatientVaccine(..)) || 
      call(boolean org.isf.patvac.manager.PatVacManager.newPatientVaccine(..)) || 
      call(boolean org.isf.patvac.manager.PatVacManager.deletePatientVaccine(..)) || 
      call(java.lang.Integer org.isf.patvac.service.PatVacIoOperationRepository.findMaxCode(..)) || 
      call(java.lang.Integer org.isf.patvac.service.PatVacIoOperationRepository.findMaxCodeWhereVaccineDate(..)) || 
      call(boolean org.isf.patvac.service.PatVacIoOperations.isCodePresent(..)) || 
      call(int org.isf.patvac.service.PatVacIoOperations.getProgYear(..)) || 
      call(boolean org.isf.patvac.service.PatVacIoOperations.updatePatientVaccine(..)) || 
      call(List<org.isf.patvac.model.PatientVaccine> org.isf.patvac.service.PatVacIoOperations.getPatientVaccine(..)) || 
      call(boolean org.isf.patvac.service.PatVacIoOperations.newPatientVaccine(..)) || 
      call(boolean org.isf.patvac.service.PatVacIoOperations.deletePatientVaccine(..));
      

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
      else
      {	  
         calleeClass = "Unknown";
      }  
        

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
      
      partOrder++;
      
      // Validate if the CallerClass is a Graphical User Interface Class
      // Build the Caller Class Line
      if(callerGuiFlag == true)
      {	  
    	 // Build the method line 
         fullMethod = fullMethod + endParameters;
         
         if(currentUser.isEmpty() == true || currentUser == null)
            currentUser = "Unknown";
            
            
         //if(currentUser.isEmpty() == false || currentUser != null)   
            // Build the note line
            note = startNote   + whiteSpaceWordsDelimiter1 + currentUser + whiteSpaceWordsDelimiter1 + colorDelimiter + colorNote + 
                   whiteSpaceWordsDelimiter1 + newLine1 + callerClass + 
                   newLine1 + endNote + newLine1;
         
         // Assign the value to the Caller Class
         if(currentUser.isEmpty() == false)
         {	 
            callerClass = currentUser;
            
            partOrder++;
            
            // Build the caller Class Line
            callerClassLine = member1 + whiteSpaceWordsDelimiter1  + callerClass + 
        		              whiteSpaceWordsDelimiter1 + position + whiteSpaceWordsDelimiter1 + partOrder;
        		              //+ actorOrder;
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
         
         //partOrder++;
         
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
      if (callerClass.equals("Unknown") == false)
      {	 
         /* Evaluation Start (TIME 1) */
    	 time1 = System.currentTimeMillis();
    	 /* Evaluation End */
    	  
 	     updateSeqDiagTextFile(callerClass, calleeClass, fullMethod, note, callerClassLine, calleeClassLine);
 	     
 	     /* Evaluation Start (TIME 2) */
 		 time2 = System.currentTimeMillis();
 		 duration = time2 - time1;
 		 System.out.println("Message FRL: The UML Sequence Diagram for the METHOD EXCHANGE: " + fullMethod +  " was Generated in : " + duration + " milliseconds.");
 		 /* Evaluation End */
 		 
      }   
      //}
      /*else
         partOrder = 1;
      */   
	        
   } 

   pointcut connect(): 
   	            call(void org.isf.menu.gui.Login.acceptPwd(..));
   	           //call(List<org.isf.menu.model.User> org.isf.menu.manager.UserBrowsingManager.getUser(..));

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
