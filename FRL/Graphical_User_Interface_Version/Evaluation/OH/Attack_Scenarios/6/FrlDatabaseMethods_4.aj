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

public aspect FrlDatabaseMethods_4
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
   
   //String connectProjectMethod="String org.isf.menu.model.User.getUserName";
   //String connectProjectMethod="org.isf.menu.gui.MainMenu.getUser";
   //String connectProjectMethod="List<org.isf.menu.model.User> org.isf.menu.manager.UserBrowsingManager.getUser";

   String connectProjectMethod="org.isf.menu.gui.Login.acceptPwd";
   Object connectProjectMethodReturnValue=null;
   String connectProjectType="ReturnValue";   
   
   String connectProjectMethod1="org.isf.menu.manager.UserBrowsingManager.getMenu";
   Object connectProjectMethodReturnValue1="admin, guest, doctor, sysadmin";
   String connectProjectType1="Parameter";
   
   String endUMLSeqDiagram="@enduml";
   String umlSeqDiagPngFileName1="IncidentSequenceDiagram.png";
   String umlSeqDiagTextFileName2="IncidentSequenceDiagramTEMPORAL.txt";
   
   String umlSeqDiagTextFile=projectOutputDir + umlSeqDiagTextFileName1;
   String callerClass="", calleeClass="", callerClassLine, calleeClassLine;
   String packageMethod="", classMethod="";
   String nameMethod="", fullMethod="", callerSuperClass="", calleeSuperClass="";
   String callerType="", calleeType="", note="";
   String line="", content="", currentUser="", fullMethodName="";
   int actorOrder=1, partOrder=0, methodsCounter=0, userCounter=0;
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
   int methodExists=0;
   
   /* Evaluation Start (DECLARE VARIABLES) */
   long time1, time2, duration;
   /* Evaluation End */
   
   pointcut databaseMethods(): 
      call(List<org.isf.menu.model.UserMenuItem> org.isf.menu.manager.UserBrowsingManager.getMenu(..)) || 
      call(List<org.isf.patvac.model.PatientVaccine> org.isf.patvac.service.PatVacIoOperations.findForPatient(..)) || 
      call(void org.isf.patvac.service.VaccinePatientMergedEventListener.handle(..)) || 
      call(List<org.isf.supplier.model.Supplier> org.isf.supplier.manager.SupplierBrowserManager.getAll(..)) || 
      call(List<org.isf.supplier.model.Supplier> org.isf.supplier.manager.SupplierBrowserManager.getList(..)) || 
      call(boolean org.isf.supplier.manager.SupplierBrowserManager.saveOrUpdate(..)) || 
      call(org.isf.supplier.model.Supplier org.isf.supplier.manager.SupplierBrowserManager.getByID(..)) || 
      call(List<org.isf.supplier.model.Supplier> org.isf.supplier.service.SupplierIoOperationRepository.findAllWhereNotDeleted(..)) || 
      call(List<org.isf.supplier.model.Supplier> org.isf.supplier.service.SupplierOperations.getAll(..)) || 
      call(List<org.isf.supplier.model.Supplier> org.isf.supplier.service.SupplierOperations.getList(..)) || 
      call(boolean org.isf.supplier.service.SupplierOperations.saveOrUpdate(..)) || 
      call(org.isf.supplier.model.Supplier org.isf.supplier.service.SupplierOperations.getByID(..)) || 
      call(boolean org.isf.patient.manager.PatientBrowserManager.isNamePresent(..)) || 
      call(boolean org.isf.patient.manager.PatientBrowserManager.mergePatient(..)) || 
      call(List<org.isf.patient.model.Patient> org.isf.patient.manager.PatientBrowserManager.getPatients(..)) || 
      call(List<org.isf.patient.model.Patient> org.isf.patient.manager.PatientBrowserManager.getPatientsByOneOfFieldsLike(..)) || 
      call(org.isf.patient.model.Patient org.isf.patient.manager.PatientBrowserManager.getPatientAll(..)) || 
      call(org.isf.patient.model.Patient org.isf.patient.manager.PatientBrowserManager.savePatient(..)) || 
      call(boolean org.isf.patient.manager.PatientBrowserManager.deletePatient(..)) || 
      call(int org.isf.patient.manager.PatientBrowserManager.getNextPatientCode(..)) || 
      call(org.isf.patient.model.Patient org.isf.patient.manager.PatientBrowserManager.getPatientByName(..)) || 
      call(org.isf.patient.model.Patient org.isf.patient.manager.PatientBrowserManager.getPatientById(..)) || 
      call(List<org.isf.patient.model.Patient> org.isf.patient.manager.PatientBrowserManager.getPatient(..)) || 
      call(java.lang.Integer org.isf.patient.service.PatientIoOperationRepository.findMaxCode(..)) || 
      call(List<org.isf.patient.model.Patient> org.isf.patient.service.PatientIoOperationRepository.findByNameAndDeletedOrderByName(..)) || 
      call(List<org.isf.patient.model.Patient> org.isf.patient.service.PatientIoOperationRepository.findAllWhereIdAndDeleted(..)) || 
      call(int org.isf.patient.service.PatientIoOperationRepository.updateDeleted(..)) || 
      call(boolean org.isf.patient.service.PatientIoOperations.isCodePresent(..)) || 
      call(List<org.isf.patient.model.Patient> org.isf.patient.service.PatientIoOperations.getPatients(..)) || 
      call(List<org.isf.patient.model.Patient> org.isf.patient.service.PatientIoOperations.getPatientsByOneOfFieldsLike(..)) || 
      call(org.isf.patient.model.Patient org.isf.patient.service.PatientIoOperations.getPatientAll(..)) || 
      call(org.isf.patient.model.Patient org.isf.patient.service.PatientIoOperations.savePatient(..)) || 
      call(boolean org.isf.patient.service.PatientIoOperations.updatePatient(..)) || 
      call(boolean org.isf.patient.service.PatientIoOperations.deletePatient(..)) || 
      call(boolean org.isf.patient.service.PatientIoOperations.isPatientPresentByName(..)) || 
      call(int org.isf.patient.service.PatientIoOperations.getNextPatientCode(..)) || 
      call(boolean org.isf.patient.service.PatientIoOperations.mergePatientHistory(..)) || 
      call(org.isf.patient.model.Patient org.isf.patient.service.PatientIoOperations.getPatient(..)) || 
      call(boolean org.isf.menu.manager.UserBrowsingManager.newUserGroup(..)) || 
      call(List<org.isf.menu.model.UserMenuItem> org.isf.menu.manager.UserBrowsingManager.getMenu(..)) || 
      call(List<org.isf.menu.model.User> org.isf.menu.manager.UserBrowsingManager.getUser(..)) || 
      call(List<org.isf.menu.model.UserGroup> org.isf.menu.manager.UserBrowsingManager.getUserGroup(..)) || 
      call(boolean org.isf.menu.manager.UserBrowsingManager.updateUser(..)) || 
      call(boolean org.isf.menu.manager.UserBrowsingManager.updateUserGroup(..)) || 
      call(org.isf.menu.model.User org.isf.menu.manager.UserBrowsingManager.getUserByName(..)) || 
      call(java.lang.String org.isf.menu.manager.UserBrowsingManager.getUsrInfo(..)) || 
      call(boolean org.isf.menu.manager.UserBrowsingManager.newUser(..)) || 
      call(boolean org.isf.menu.manager.UserBrowsingManager.updatePassword(..)) || 
      call(List<org.isf.menu.model.UserMenuItem> org.isf.menu.manager.UserBrowsingManager.getGroupMenu(..)) || 
      call(boolean org.isf.menu.manager.UserBrowsingManager.setGroupMenu(..)) || 
      call(boolean org.isf.menu.manager.UserBrowsingManager.deleteGroup(..)) || 
      call(java.lang.String org.isf.menu.manager.UserBrowsingManager.getCurrentUser(..)) || 
      call(boolean org.isf.menu.manager.UserBrowsingManager.deleteUser(..)) || 
      call(void org.isf.menu.service.GroupMenuIoOperationRepository.deleteWhereUserGroup(..)) || 
      call(List<java.lang.Object[]> org.isf.menu.service.UserMenuItemIoOperationRepository.findAllWhereUserId(..)) || 
      call(List<org.isf.menu.model.User> org.isf.menu.service.UserIoOperationRepository.findAllWhereUserGroupNameByOrderUserNameAsc(..)) || 
      call(int org.isf.menu.service.UserIoOperationRepository.updatePassword(..)) || 
      call(int org.isf.menu.service.UserIoOperationRepository.updateDescription(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.newUserGroup(..)) || 
      call(List<org.isf.menu.model.UserMenuItem> org.isf.menu.service.MenuIoOperations.getMenu(..)) || 
      call(List<org.isf.menu.model.User> org.isf.menu.service.MenuIoOperations.getUser(..)) || 
      call(List<org.isf.menu.model.UserGroup> org.isf.menu.service.MenuIoOperations.getUserGroup(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.updateUser(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.updateUserGroup(..)) || 
      call(org.isf.menu.model.User org.isf.menu.service.MenuIoOperations.getUserByName(..)) || 
      call(java.lang.String org.isf.menu.service.MenuIoOperations.getUsrInfo(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.isUserNamePresent(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.isGroupNamePresent(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.newUser(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.updatePassword(..)) || 
      call(List<org.isf.menu.model.UserMenuItem> org.isf.menu.service.MenuIoOperations.getGroupMenu(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.deleteGroup(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.deleteUser(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.deleteGroupMenu(..)) || 
      call(boolean org.isf.menu.service.MenuIoOperations.insertGroupMenu(..)) || 
      call(List<org.isf.menu.model.UserGroup> org.isf.menu.service.UserGroupIoOperationRepository.findAllByOrderByCodeAsc(..)) || 
      call(int org.isf.menu.service.UserGroupIoOperationRepository.updateDescription(..)) || 
      call(boolean org.isf.ward.manager.WardBrowserManager.isCodePresent(..)) || 
      call(org.isf.ward.model.Ward org.isf.ward.manager.WardBrowserManager.newWard(..)) || 
      call(org.isf.ward.model.Ward org.isf.ward.manager.WardBrowserManager.updateWard(..)) || 
      call(int org.isf.ward.manager.WardBrowserManager.getCurrentOccupation(..)) || 
      call(List<org.isf.ward.model.Ward> org.isf.ward.manager.WardBrowserManager.getWardsNoMaternity(..)) || 
      call(List<org.isf.ward.model.Ward> org.isf.ward.manager.WardBrowserManager.getWards(..)) || 
      call(boolean org.isf.ward.manager.WardBrowserManager.deleteWard(..)) || 
      call(org.isf.ward.model.Ward org.isf.ward.manager.WardBrowserManager.findWard(..)) || 
      call(boolean org.isf.ward.manager.WardBrowserManager.maternityControl(..)) || 
      call(boolean org.isf.ward.service.WardIoOperations.isCodePresent(..)) || 
      call(org.isf.ward.model.Ward org.isf.ward.service.WardIoOperations.newWard(..)) || 
      call(org.isf.ward.model.Ward org.isf.ward.service.WardIoOperations.updateWard(..)) || 
      call(int org.isf.ward.service.WardIoOperations.getCurrentOccupation(..)) || 
      call(List<org.isf.ward.model.Ward> org.isf.ward.service.WardIoOperations.getWardsNoMaternity(..)) || 
      call(List<org.isf.ward.model.Ward> org.isf.ward.service.WardIoOperations.getWards(..)) || 
      call(boolean org.isf.ward.service.WardIoOperations.deleteWard(..)) || 
      call(org.isf.ward.model.Ward org.isf.ward.service.WardIoOperations.findWard(..)) || 
      call(List<org.isf.pricesothers.model.PricesOthers> org.isf.pricesothers.manager.PricesOthersManager.getOthers(..)) || 
      call(boolean org.isf.pricesothers.manager.PricesOthersManager.updateOther(..)) || 
      call(boolean org.isf.pricesothers.manager.PricesOthersManager.newOther(..)) || 
      call(boolean org.isf.pricesothers.manager.PricesOthersManager.deleteOther(..)) || 
      call(boolean org.isf.pricesothers.service.PriceOthersIoOperations.isCodePresent(..)) || 
      call(List<org.isf.pricesothers.model.PricesOthers> org.isf.pricesothers.service.PriceOthersIoOperations.getOthers(..)) || 
      call(boolean org.isf.pricesothers.service.PriceOthersIoOperations.updateOther(..)) || 
      call(boolean org.isf.pricesothers.service.PriceOthersIoOperations.newOthers(..)) || 
      call(boolean org.isf.pricesothers.service.PriceOthersIoOperations.deleteOthers(..)) || 
      call(void org.isf.examination.manager.ExaminationBrowserManager.remove(..)) || 
      call(void org.isf.examination.manager.ExaminationBrowserManager.saveOrUpdate(..)) || 
      call(org.isf.examination.model.PatientExamination org.isf.examination.manager.ExaminationBrowserManager.getByID(..)) || 
      call(List<org.isf.examination.model.PatientExamination> org.isf.examination.manager.ExaminationBrowserManager.getLastNByPatID(..)) || 
      call(List<org.isf.examination.model.PatientExamination> org.isf.examination.manager.ExaminationBrowserManager.getByPatID(..)) || 
      call(void org.isf.examination.service.ExaminationPatientMergedEventListener.handle(..)) || 
      call(List<org.isf.examination.model.PatientExamination> org.isf.examination.service.ExaminationIoOperationRepository.findByPatient_CodeOrderByPexDateDesc(..)) || 
      call(void org.isf.examination.service.ExaminationOperations.remove(..)) || 
      call(void org.isf.examination.service.ExaminationOperations.saveOrUpdate(..)) || 
      call(org.isf.examination.model.PatientExamination org.isf.examination.service.ExaminationOperations.getByID(..)) || 
      call(List<org.isf.examination.model.PatientExamination> org.isf.examination.service.ExaminationOperations.getLastNByPatID(..)) || 
      call(List<org.isf.examination.model.PatientExamination> org.isf.examination.service.ExaminationOperations.getByPatID(..)) || 
      call(boolean org.isf.vactype.manager.VaccineTypeBrowserManager.isCodePresent(..)) || 
      call(List<org.isf.vactype.model.VaccineType> org.isf.vactype.manager.VaccineTypeBrowserManager.getVaccineType(..)) || 
      call(boolean org.isf.vactype.manager.VaccineTypeBrowserManager.updateVaccineType(..)) || 
      call(org.isf.vactype.model.VaccineType org.isf.vactype.manager.VaccineTypeBrowserManager.findVaccineType(..)) || 
      call(boolean org.isf.vactype.manager.VaccineTypeBrowserManager.newVaccineType(..)) || 
      call(boolean org.isf.vactype.manager.VaccineTypeBrowserManager.deleteVaccineType(..)) || 
      call(boolean org.isf.vactype.service.VacTypeIoOperation.isCodePresent(..)) || 
      call(List<org.isf.vactype.model.VaccineType> org.isf.vactype.service.VacTypeIoOperation.getVaccineType(..)) || 
      call(boolean org.isf.vactype.service.VacTypeIoOperation.updateVaccineType(..)) || 
      call(org.isf.vactype.model.VaccineType org.isf.vactype.service.VacTypeIoOperation.findVaccineType(..)) || 
      call(boolean org.isf.vactype.service.VacTypeIoOperation.newVaccineType(..)) || 
      call(boolean org.isf.vactype.service.VacTypeIoOperation.deleteVaccineType(..)) || 
      call(boolean org.isf.vaccine.manager.VaccineBrowserManager.isCodePresent(..)) || 
      call(List<org.isf.vaccine.model.Vaccine> org.isf.vaccine.manager.VaccineBrowserManager.getVaccine(..)) || 
      call(org.isf.vaccine.model.Vaccine org.isf.vaccine.manager.VaccineBrowserManager.updateVaccine(..)) || 
      call(org.isf.vaccine.model.Vaccine org.isf.vaccine.manager.VaccineBrowserManager.findVaccine(..)) || 
      call(org.isf.vaccine.model.Vaccine org.isf.vaccine.manager.VaccineBrowserManager.newVaccine(..)) || 
      call(boolean org.isf.vaccine.manager.VaccineBrowserManager.deleteVaccine(..)) || 
      call(boolean org.isf.vaccine.service.VaccineIoOperations.isCodePresent(..)) || 
      call(List<org.isf.vaccine.model.Vaccine> org.isf.vaccine.service.VaccineIoOperations.getVaccine(..)) || 
      call(org.isf.vaccine.model.Vaccine org.isf.vaccine.service.VaccineIoOperations.updateVaccine(..)) || 
      call(org.isf.vaccine.model.Vaccine org.isf.vaccine.service.VaccineIoOperations.findVaccine(..)) || 
      call(org.isf.vaccine.model.Vaccine org.isf.vaccine.service.VaccineIoOperations.newVaccine(..)) || 
      call(boolean org.isf.vaccine.service.VaccineIoOperations.deleteVaccine(..)) || 
      call(List<org.isf.therapy.model.TherapyRow> org.isf.therapy.manager.TherapyManager.getTherapyRows(..)) || 
      call(org.isf.therapy.model.TherapyRow org.isf.therapy.manager.TherapyManager.newTherapy(..)) || 
      call(boolean org.isf.therapy.manager.TherapyManager.deleteAllTherapies(..)) || 
      call(boolean org.isf.therapy.manager.TherapyManager.newTherapies(..)) || 
      call(void org.isf.therapy.service.TherapyPatientMergedEventListener.handle(..)) || 
      call(boolean org.isf.therapy.service.TherapyIoOperations.isCodePresent(..)) || 
      call(List<org.isf.therapy.model.TherapyRow> org.isf.therapy.service.TherapyIoOperations.getTherapyRows(..)) || 
      call(org.isf.therapy.model.TherapyRow org.isf.therapy.service.TherapyIoOperations.newTherapy(..)) || 
      call(boolean org.isf.therapy.service.TherapyIoOperations.deleteAllTherapies(..)) || 
      call(boolean org.isf.dicomtype.manager.DicomTypeBrowserManager.isCodePresent(..)) || 
      call(List<org.isf.dicomtype.model.DicomType> org.isf.dicomtype.manager.DicomTypeBrowserManager.getDicomType(..)) || 
      call(boolean org.isf.dicomtype.manager.DicomTypeBrowserManager.updateDicomType(..)) || 
      call(boolean org.isf.dicomtype.manager.DicomTypeBrowserManager.newDicomType(..)) || 
      call(boolean org.isf.dicomtype.manager.DicomTypeBrowserManager.deleteDicomType(..)) || 
      call(boolean org.isf.dicomtype.service.DicomTypeIoOperation.isCodePresent(..)) || 
      call(List<org.isf.dicomtype.model.DicomType> org.isf.dicomtype.service.DicomTypeIoOperation.getDicomType(..)) || 
      call(boolean org.isf.dicomtype.service.DicomTypeIoOperation.updateDicomType(..)) || 
      call(boolean org.isf.dicomtype.service.DicomTypeIoOperation.newDicomType(..)) || 
      call(boolean org.isf.dicomtype.service.DicomTypeIoOperation.deleteDicomType(..)) || 
      call(boolean org.isf.disctype.manager.DischargeTypeBrowserManager.isCodePresent(..)) || 
      call(boolean org.isf.disctype.manager.DischargeTypeBrowserManager.updateDischargeType(..)) || 
      call(boolean org.isf.disctype.manager.DischargeTypeBrowserManager.newDischargeType(..)) || 
      call(boolean org.isf.disctype.manager.DischargeTypeBrowserManager.deleteDischargeType(..)) || 
      call(List<org.isf.disctype.model.DischargeType> org.isf.disctype.manager.DischargeTypeBrowserManager.getDischargeType(..)) || 
      call(boolean org.isf.disctype.service.DischargeTypeIoOperation.isCodePresent(..)) || 
      call(boolean org.isf.disctype.service.DischargeTypeIoOperation.updateDischargeType(..)) || 
      call(boolean org.isf.disctype.service.DischargeTypeIoOperation.newDischargeType(..)) || 
      call(boolean org.isf.disctype.service.DischargeTypeIoOperation.deleteDischargeType(..)) || 
      call(List<org.isf.disctype.model.DischargeType> org.isf.disctype.service.DischargeTypeIoOperation.getDischargeType(..)) || 
      call(boolean org.isf.dicom.manager.SqlDicomManager.exist(..)) || 
      call(void org.isf.dicom.manager.SqlDicomManager.saveFile(..)) || 
      call(org.isf.dicom.model.FileDicom org.isf.dicom.manager.SqlDicomManager.loadDetails(..)) || 
      call(java.lang.Long[] org.isf.dicom.manager.SqlDicomManager.getSerieDetail(..)) || 
      call(boolean org.isf.dicom.manager.SqlDicomManager.deleteSerie(..)) || 
      call(List<org.isf.dicom.model.FileDicom> org.isf.dicom.service.DicomIoOperationRepository.findAllWhereIdAndNumberByOrderNameAsc(..)) || 
      call(List<org.isf.dicom.model.FileDicom> org.isf.dicom.service.DicomIoOperationRepository.findAllWhereIdGroupBySeriesInstanceUIDOrderSerDateDesc(..)) || 
      call(List<org.isf.dicom.model.FileDicom> org.isf.dicom.service.DicomIoOperationRepository.findAllWhereIdAndFileAndUid(..)) || 
      call(void org.isf.dicom.service.DicomIoOperationRepository.deleteByIdAndNumber(..)) || 
      call(int org.isf.dicom.service.DicomIoOperationRepository.seriesExists(..)) || 
      call(int org.isf.dicom.service.DicomIoOperationRepository.countFramesInSeries(..)) || 
      call(boolean org.isf.dicom.service.DicomIoOperations.exist(..)) || 
      call(boolean org.isf.dicom.service.DicomIoOperations.isCodePresent(..)) || 
      call(void org.isf.dicom.service.DicomIoOperations.saveFile(..)) || 
      call(org.isf.dicom.model.FileDicom org.isf.dicom.service.DicomIoOperations.loadDetails(..)) || 
      call(java.lang.Long[] org.isf.dicom.service.DicomIoOperations.getSerieDetail(..)) || 
      call(boolean org.isf.dicom.service.DicomIoOperations.deleteSerie(..)) || 
      call(boolean org.isf.dicom.service.DicomIoOperations.isSeriesPresent(..)) || 
      call(List<org.isf.pregtreattype.model.PregnantTreatmentType> org.isf.pregtreattype.manager.PregnantTreatmentTypeBrowserManager.getPregnantTreatmentType(..)) || 
      call(boolean org.isf.pregtreattype.manager.PregnantTreatmentTypeBrowserManager.isCodePresent(..)) || 
      call(boolean org.isf.pregtreattype.manager.PregnantTreatmentTypeBrowserManager.newPregnantTreatmentType(..)) || 
      call(boolean org.isf.pregtreattype.manager.PregnantTreatmentTypeBrowserManager.updatePregnantTreatmentType(..)) || 
      call(boolean org.isf.pregtreattype.manager.PregnantTreatmentTypeBrowserManager.deletePregnantTreatmentType(..)) || 
      call(List<org.isf.pregtreattype.model.PregnantTreatmentType> org.isf.pregtreattype.service.PregnantTreatmentTypeIoOperation.getPregnantTreatmentType(..)) || 
      call(boolean org.isf.pregtreattype.service.PregnantTreatmentTypeIoOperation.isCodePresent(..)) || 
      call(boolean org.isf.pregtreattype.service.PregnantTreatmentTypeIoOperation.newPregnantTreatmentType(..)) || 
      call(boolean org.isf.pregtreattype.service.PregnantTreatmentTypeIoOperation.updatePregnantTreatmentType(..)) || 
      call(boolean org.isf.pregtreattype.service.PregnantTreatmentTypeIoOperation.deletePregnantTreatmentType(..));

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
      
      /*
      System.out.println("AOP 4: ");
      System.out.println("Full Method          : " + fullMethod);
      */
       
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
      if (currentUser.equals("Unknown") == false)
      {	
    	 /* Evaluation Start (TIME 1) */
    	 time1 = System.currentTimeMillis();
    	 /* Evaluation End */
    	  
    	 /* 
         System.out.println("*** INFORMATION ***");     	 
    	 System.out.println("Aspect Oriented File : " + "4");
    	 System.out.println("Current User         : " + currentUser);
    	 System.out.println("Caller Class         : " + callerClass);
    	 System.out.println("Callee Class         : " + calleeClass);
    	 System.out.println("Full Method          : " + fullMethod);
    	 System.out.println("Note                 : " + note);
    	 System.out.println("Caller Class Line    : " + callerClassLine);
    	 System.out.println("Callee Class Line    : " + calleeClassLine);
    	 */
    	        	 
    	 // Validate if the method that brings the connected User is already in the UML Text File    	     	 
	     //methodExists = findMethodFile(umlSeqDiagTextFile, currentUser, connectProjectMethod1);
	     
	     /*
	     System.out.println("*** VALIDATING USER CONNECTED METHOD AOP 4 ***");
	     System.out.println("Current User  : " + currentUser);
	     System.out.println("Method        : " + connectProjectMethod1);
	     System.out.println("Method Exists : " + methodExists);
	     */
	            	 
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
   	            //call(void org.isf.menu.gui.Login.acceptPwd(..)) ||
   	            call(List<org.isf.menu.model.UserMenuItem> org.isf.menu.manager.UserBrowsingManager.getMenu(..));
   	            /*call(void org.isf.menu.gui.Login.fireLoginInserted(..)) ||
   	            call(org.isf.menu.model.User org.isf.menu.gui.MainMenu.getUser(..)) ||
   	            call(String org.isf.menu.model.User.getUserName(..))||
   	            call(List<org.isf.menu.model.User> org.isf.menu.manager.UserBrowsingManager.getUser(..))||
   	            call(String org.isf.menu.manager.UserBrowsingManager.getUsrInfo(..)) ||
   	            call(String org.isf.menu.manager.UserBrowsingManager.getCurrentUser(..))||
   	            call(String org.isf.menu.model.User.getUserName(..)) ||
   	            call(org.isf.menu.model.User org.isf.menu.manager.UserBrowsingManager.getUserByName(..));*/
   	            
   Object around() : connect()
   //after(): connect()
   //after () returning(Object methodReturnValue1): connect() && args()
   {
	   
      Object methodReturnValue1 = proceed();
      //Object[] args1;
      Object[] parameterValues;
      String value="";
      int i=0;
      
      method = thisJoinPoint.getSignature();
      
      // Get the Information about the Parameters: Names and Types	  
	  parameterValues = thisJoinPoint.getArgs();
      
      /*
      System.out.println("*** AFTER CONNECTING AOP 4 ***");
      System.out.println("Method                   : " + method);
      System.out.println("Connect Project Method 1 : " + connectProjectMethod1);
      System.out.println("Connect Project Type 1   : " + connectProjectType1);

      System.out.println("Connect Project Return Value 1  : " + connectProjectMethodReturnValue1.toString());
      
	  System.out.println("Method Return Value 1   : " + methodReturnValue1);
	  System.out.println("Parameter Values        : " + Arrays.toString(parameterValues));
      */
       
      if( method.toString().contains(connectProjectMethod1))
      {
         //System.out.println("Method: " + method.toString() + " equals " + connectProjectMethod1);
         
         if(connectProjectType1.equals("Parameter"))
         {
            for(i = 0; i < parameterValues.length; i++)
               value = parameterValues[i].toString();
               
            /*   
            System.out.println("Parameter");   
            System.out.println("Value: " + value);   
            */
         
            if(connectProjectMethodReturnValue1.toString().contains(value))
            {
               //userName = value;
               userCounter++; 
               assignUser();
               //currentUser = userName + validSpecialCharacter + userCounter;
               
               /*
               System.out.println("*** USER REALLY CONNECTED (PARAMETER ) AOP4 ***");
               System.out.println("User Name     : " + value);
               System.out.println("User Counter  : " + userCounter);
               System.out.println("Current User  : " + currentUser);
               */
               
            }
         }    
         else 
            if(connectProjectType1.equals("ReturnType"))
            {
               value = methodReturnValue1.toString();
               
               /*
               System.out.println("Return Type");   
               System.out.println("Value: " + value);   
               */
               
               if(connectProjectMethodReturnValue1.toString().contains(value))
               {
                  //userName = value;
                  userCounter++; 
                  assignUser();
         
                  //currentUser = userName + validSpecialCharacter + userCounter;
                  
                  /*
                  System.out.println("*** USER REALLY CONNECTED (RETURN TYPE ) AOP4 ***");
                  System.out.println("User Name     : " + value);
                  System.out.println("User Counter  : " + userCounter);
                  System.out.println("Current User  : " + currentUser);
                  */
                  
               }
             }  
      }
      
	  /*if(methodReturnValue1 != null &&
	     methodReturnValue1.equals(connectProjectMethodReturnValue))
	  {   
	     userCounter++; 
	     //userName = methodReturnValue1.toString();
	     
	     
	     System.out.println("*** USER REALLY CONNECTED (RETURN VALUE ) AOP4 ***");
	     System.out.println("User Name    : " + userName);
	     System.out.println("User Counter : " + userCounter);
	     
	  }  
      else 
      {*/
      
      if( method.toString().equals(connectProjectMethod))
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
      
      //userConnection();
	  
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

   public void assignUser()
   {
      currentUser = userName + validSpecialCharacter + userCounter;
	  
	  /*
	  System.out.println("*** USER TRYING TO CONNECT AOP4 ***");
	  System.out.println("Current User: " + currentUser);
	  */
   }
   
   public void userConnection()
   {
		  
	  currentUser = defaultUser;
	  	  
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

   public int findMethodFile(String textFile, String user, String method)
   {
       String lineText="", errorMessage1="", errorMessage2="";
       int c=0;
       BufferedReader br;

       try 
       {
          br = new BufferedReader(new FileReader(textFile));
          
          while ((lineText = br.readLine()) != null) 
          {
        	 if(lineText.contains(user) && lineText.contains(method))
             {	  
                System.out.println("Line Text: " + lineText); 	 
                c++;
             }   

          }
           
          br.close();

       } 
       catch (Exception e1) 
       {
          errorMessage1 = e1.getMessage();
          errorMessage2 = "Error FRL: Occurred while loading the lines of the UML Sequence Diagram Text File: " + textFile + System.lineSeparator();
          errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
          System.out.println(errorMessage2);
       }
              
       return c;
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
