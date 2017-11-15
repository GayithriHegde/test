package com.asms.usermgmt.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;


import com.asms.common.helper.Constants;
import com.asms.usermgmt.entity.Admin;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.akacartUser.AkacartUser;
import com.asms.usermgmt.entity.management.Management;
import com.asms.usermgmt.entity.nonTeachingStaff.Address;
import com.asms.usermgmt.entity.nonTeachingStaff.NonTeachingStaff;
import com.asms.usermgmt.entity.nonTeachingStaff.StaffDocuments;
import com.asms.usermgmt.entity.nonTeachingStaff.StaffPreviousInformation;
import com.asms.usermgmt.entity.nonTeachingStaff.StaffStatutory;
import com.asms.usermgmt.entity.student.Parent;
import com.asms.usermgmt.entity.student.Sibling;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.entity.student.StudentAddress;
import com.asms.usermgmt.entity.student.StudentDocuments;
import com.asms.usermgmt.entity.student.StudentPreviousInfo;
import com.asms.usermgmt.entity.teachingStaff.Address1;
import com.asms.usermgmt.entity.teachingStaff.StaffDocuments1;
import com.asms.usermgmt.entity.teachingStaff.StaffPreviousInformation1;
import com.asms.usermgmt.entity.teachingStaff.StaffStatutory1;
import com.asms.usermgmt.entity.teachingStaff.TeachingStaff;
import com.asms.usermgmt.request.AdminDetails;
import com.asms.usermgmt.request.UserBasicDetails;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.AkacartUserDetails.AkacartUserDetails;
import com.asms.usermgmt.request.management.ManagementDetails;
import com.asms.usermgmt.request.nonTeachingStaff.AddressDetails;
import com.asms.usermgmt.request.nonTeachingStaff.NonTeachingStaffDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffDocumentsDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffPreviousInformationDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffStatutoryDetails;
import com.asms.usermgmt.request.student.ParentDetails;
import com.asms.usermgmt.request.student.SiblingDetails;
import com.asms.usermgmt.request.student.StudentAddressDetails;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.request.student.StudentDocumentDetails;
import com.asms.usermgmt.request.student.StudentPreviousDetails;
import com.asms.usermgmt.request.teachingStaff.AddressDetails1;
import com.asms.usermgmt.request.teachingStaff.StaffDocumentsDetails1;
import com.asms.usermgmt.request.teachingStaff.StaffPreviousInformationDetails1;
import com.asms.usermgmt.request.teachingStaff.StaffStatutoryDetails1;
import com.asms.usermgmt.request.teachingStaff.TeachingStaffDetails;

/*
 * EntityCreator.java does creation of entity objects
 * from ui object
 * 
 */

@Component
public class EntityCreator {

	/*
	 * Method: createStudent -> maps ui values to entity input : StudentDetails
	 * return : Student
	 * 
	 */

	public Student createStudent(StudentDetails details, User user) throws ParseException {
		Student student = new Student();

		String sDate1 = details.getAdmissionDate();

		DateFormat edtFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD = edtFormat.parse(sDate1);
		student.setAdmissionDate(aLD);

		student.setAdmissionNo(details.getAdmissionNo());

		student.setStudentAgeInYears(details.getStudentAgeInYears());
		student.setStudentBirthplace(details.getStudentBirthplace());
		student.setStudentCasteCategory(details.getStudentCasteCategory());
		student.setStudentClass(details.getStudentClass());
		student.setStudentCreatedByWadmin(user.getUserId());

		String sDate = details.getStudentDob();
		DateFormat edtFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD1 = edtFormat1.parse(sDate);
		student.setStudentDob(aLD1);

		student.setStudentFirstName(details.getStudentFirstName());
		student.setStudentGender(details.getStudentGender());
		student.setStudentIdentificationMarks(details.getStudentIdentificationMarks());
		student.setStudentLastName(details.getStudentLastName());
		student.setStudentMiddleName(details.getStudentMiddleName());
		student.setStudentMotherTongue(details.getStudentMotherTongue());
		student.setStudentNationality(details.getStudentNationality());
		student.setStudentPhoto(details.getStudentPhoto());
		student.setStudentReligion(details.getStudentReligion());
		student.setStudentSection(details.getStudentSection());
		student.setEmergencyContactNo(details.getEmergencyContactNo());
		student.setStudentSubCaste(details.getStudentSubCaste());
		student.setStudentType(details.getStudentType());
		student.setBloodGroup(details.getBloodGroup());
		return student;
	}

	/*
	 * Method: createStudentDetail ->Create StudentDetailObject from The
	 * StudentObject input : User Returns : UserDetails
	 * 
	 */

	public UserDetails createStudentDetail(User user) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		UserDetails userDetails;
		userDetails = new UserDetails();
		userDetails.setEmail(user.getEmail());
		userDetails.setRole(user.getRoleObject().getRoleName());
		userDetails.setSubRole(user.getSubRoleObject().getSubRoleName());
		userDetails.setUserId(user.getUserId());

		Student student = (Student) user;
		StudentDetails sDetails = new StudentDetails();

		sDetails.setAdmissionDate(dateFormat.format(student.getAdmissionDate()));

		sDetails.setAdmissionNo(student.getAdmissionNo());
		sDetails.setStudentType(student.getStudentType());
		sDetails.setStudentClass(student.getStudentClass());
		sDetails.setStudentSection(student.getStudentSection());
		sDetails.setStudentFirstName(student.getStudentFirstName());
		sDetails.setStudentMiddleName(student.getStudentMiddleName());
		sDetails.setStudentLastName(student.getStudentLastName());
		sDetails.setStudentGender(student.getStudentGender());
		sDetails.setStudentIdentificationMarks(student.getStudentIdentificationMarks());
		sDetails.setStudentAgeInYears(student.getStudentAgeInYears());
		sDetails.setStudentBirthplace(student.getStudentBirthplace());
		sDetails.setStudentNationality(student.getStudentNationality());
		sDetails.setStudentReligion(student.getStudentReligion());
		sDetails.setStudentCasteCategory(student.getStudentCasteCategory());
		sDetails.setStudentSubCaste(student.getStudentSubCaste());
		sDetails.setStudentMotherTongue(student.getStudentMotherTongue());
		sDetails.setStudentCreatedByWadmin(student.getStudentCreatedByWadmin());
		sDetails.setStudentPhoto(student.getStudentPhoto());
		sDetails.setBloodGroup(student.getBloodGroup());
		sDetails.setStudentDob(dateFormat.format(student.getStudentDob()));

		userDetails.setStudentDetails(sDetails);

		if (null != student.getStudentAddress()) {

			StudentAddressDetails saDetails = new StudentAddressDetails();
			saDetails.setcAddressLine1(student.getStudentAddress().getcAddressLine1());
			saDetails.setcAddressLine2(student.getStudentAddress().getcAddressLine2());
			saDetails.setcCountry(student.getStudentAddress().getcCountry());
			saDetails.setcDistrict(student.getStudentAddress().getcDistrict());
			saDetails.setcLocation(student.getStudentAddress().getcLocation());
			saDetails.setcPincode(student.getStudentAddress().getcPincode());
			saDetails.setcState(student.getStudentAddress().getcState());
			saDetails.setcSubDivision(student.getStudentAddress().getcSubDivision());
			saDetails.setcTehsil(student.getStudentAddress().getcTehsil());
			saDetails.setcVillage(student.getStudentAddress().getcVillage());
			saDetails.setpAddressLine1(student.getStudentAddress().getpAddressLine1());
			saDetails.setpAddressLine2(student.getStudentAddress().getpAddressLine2());
			saDetails.setpCountry(student.getStudentAddress().getpCountry());
			saDetails.setpDistrict(student.getStudentAddress().getpDistrict());
			saDetails.setcLocation(student.getStudentAddress().getpLocation());
			saDetails.setpPincode(student.getStudentAddress().getpPincode());
			saDetails.setpState(student.getStudentAddress().getpState());
			saDetails.setpSubDivision(student.getStudentAddress().getpSubDivision());
			saDetails.setcTehsil(student.getStudentAddress().getpTehsil());
			saDetails.setpVillage(student.getStudentAddress().getpVillage());

			sDetails.setAddressDetails(saDetails);
		}
		if (null != student.getParentObject()) {
			ParentDetails pDetails = new ParentDetails();
			pDetails.setfContactNumber(student.getParentObject().getfContactNumber());
			pDetails.setfEmail(student.getParentObject().getfEmail());
			pDetails.setfFirstName(student.getParentObject().getfFirstName());
			pDetails.setfMiddleName(student.getParentObject().getfMiddleName());
			pDetails.setfLastName(student.getParentObject().getfLastName());
			pDetails.setfIncome(student.getParentObject().getfIncome());
			pDetails.setfOccupation(student.getParentObject().getfOccupation());
			pDetails.setfQualification(student.getParentObject().getfQualification());
			pDetails.setmContactNumber(student.getParentObject().getmContactNumber());
			pDetails.setmEmail(student.getParentObject().getmEmail());
			pDetails.setmFirstName(student.getParentObject().getmFirstName());
			pDetails.setmMiddleName(student.getParentObject().getmMiddleName());
			pDetails.setmLastName(student.getParentObject().getmLastName());
			pDetails.setmOccupation(student.getParentObject().getmOccupation());
			pDetails.setmQualification(student.getParentObject().getmQualification());
			pDetails.setmIncome(student.getParentObject().getmIncome());

			sDetails.setParentDetails(pDetails);
		}
		if (null != student.getSibling()) {
			SiblingDetails sibDetails = new SiblingDetails();
			sibDetails.setStudent_id(student.getSerialNo());
			
		}

		if (null != student.getStudentDocuments()) {
			StudentDocumentDetails sdDetails = new StudentDocumentDetails();
			sdDetails.setAadhaarNo(student.getStudentDocuments().getAadhaarNo());
			sdDetails.setAadhaarCard(student.getStudentDocuments().getAadhaarCard());
			sdDetails.setBirthCirtificate(student.getStudentDocuments().getBirthCertificate());
			sdDetails.setOtherCertificate(student.getStudentDocuments().getOtherCertificate());
			sdDetails.setRemarks(student.getStudentDocuments().getRemarks());

			sDetails.setDocumentDetails(sdDetails);
		}

		if (null != student.getStudentPreviousInfo()) {
			StudentPreviousDetails spDetails = new StudentPreviousDetails();
			spDetails.setSchool(student.getStudentPreviousInfo().getSchoolName());
			spDetails.setNoOfYears(student.getStudentPreviousInfo().getNoOfYears());
			spDetails.setPreviousClass(student.getStudentPreviousInfo().getPreviousClass());
			spDetails.setTotalMarks(student.getStudentPreviousInfo().getTotalMarks());
			spDetails.setObtainedMarks(student.getStudentPreviousInfo().getObtainedMarks());

			sDetails.setPreviousDetails(spDetails);
		}

		return userDetails;

	}

	/*
	 * Method: createStudentDetails ->Create StudentDetailObjects from The
	 * StudentObject input : List Of User(List<User>) Returns : List Of
	 * UserDetails(List<UserDetails>)
	 * 
	 */
	public List<UserDetails> createStudentDetails(List<User> users) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		UserDetails userDetails;
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		for (User user : users) {

			userDetails = new UserDetails();
			userDetails.setEmail(user.getEmail());
			userDetails.setRole(user.getRoleObject().getRoleName());
			userDetails.setSubRole(user.getSubRoleObject().getSubRoleName());
			userDetails.setUserId(user.getUserId());

			Student student = (Student) user;
			StudentDetails sDetails = new StudentDetails();

			sDetails.setAdmissionDate(dateFormat.format(student.getAdmissionDate()));

			sDetails.setAdmissionNo(student.getAdmissionNo());
			sDetails.setStudentType(student.getStudentType());
			sDetails.setStudentClass(student.getStudentClass());
			sDetails.setStudentSection(student.getStudentSection());
			sDetails.setStudentFirstName(student.getStudentFirstName());
			sDetails.setStudentMiddleName(student.getStudentMiddleName());
			sDetails.setStudentLastName(student.getStudentLastName());
			sDetails.setStudentGender(student.getStudentGender());
			sDetails.setStudentIdentificationMarks(student.getStudentIdentificationMarks());
			sDetails.setStudentAgeInYears(student.getStudentAgeInYears());
			sDetails.setStudentBirthplace(student.getStudentBirthplace());
			sDetails.setStudentNationality(student.getStudentNationality());
			sDetails.setStudentReligion(student.getStudentReligion());
			sDetails.setStudentCasteCategory(student.getStudentCasteCategory());
			sDetails.setStudentSubCaste(student.getStudentSubCaste());
			sDetails.setStudentMotherTongue(student.getStudentMotherTongue());
			sDetails.setStudentCreatedByWadmin(student.getStudentCreatedByWadmin());
			sDetails.setStudentPhoto(student.getStudentPhoto());
			sDetails.setBloodGroup(student.getBloodGroup());
			
			sDetails.setStudentDob(dateFormat.format(student.getStudentDob()));

			userDetails.setStudentDetails(sDetails);

			if (null != student.getStudentAddress()) {
				StudentAddressDetails saDetails = new StudentAddressDetails();
				saDetails.setcAddressLine1(student.getStudentAddress().getcAddressLine1());
				saDetails.setcAddressLine2(student.getStudentAddress().getcAddressLine2());
				saDetails.setcCountry(student.getStudentAddress().getcCountry());
				saDetails.setcDistrict(student.getStudentAddress().getcDistrict());
				saDetails.setcLocation(student.getStudentAddress().getcLocation());
				saDetails.setcPincode(student.getStudentAddress().getcPincode());
				saDetails.setcState(student.getStudentAddress().getcState());
				saDetails.setcSubDivision(student.getStudentAddress().getcSubDivision());
				saDetails.setcTehsil(student.getStudentAddress().getcTehsil());
				saDetails.setcVillage(student.getStudentAddress().getcVillage());
				saDetails.setpAddressLine1(student.getStudentAddress().getpAddressLine1());
				saDetails.setpAddressLine2(student.getStudentAddress().getpAddressLine2());
				saDetails.setpCountry(student.getStudentAddress().getpCountry());
				saDetails.setpDistrict(student.getStudentAddress().getpDistrict());
				saDetails.setcLocation(student.getStudentAddress().getpLocation());
				saDetails.setpPincode(student.getStudentAddress().getpPincode());
				saDetails.setpState(student.getStudentAddress().getpState());
				saDetails.setpSubDivision(student.getStudentAddress().getpSubDivision());
				saDetails.setcTehsil(student.getStudentAddress().getpTehsil());
				saDetails.setpVillage(student.getStudentAddress().getpVillage());

				sDetails.setAddressDetails(saDetails);
			}

			if (null != student.getParentObject()) {
				ParentDetails pDetails = new ParentDetails();
				pDetails.setfContactNumber(student.getParentObject().getfContactNumber());
				pDetails.setfEmail(student.getParentObject().getfEmail());
				pDetails.setfFirstName(student.getParentObject().getfFirstName());
				pDetails.setfMiddleName(student.getParentObject().getfMiddleName());
				pDetails.setfLastName(student.getParentObject().getfLastName());
				pDetails.setfIncome(student.getParentObject().getfIncome());
				pDetails.setfOccupation(student.getParentObject().getfOccupation());
				pDetails.setfQualification(student.getParentObject().getfQualification());
				pDetails.setmContactNumber(student.getParentObject().getmContactNumber());
				pDetails.setmEmail(student.getParentObject().getmEmail());
				pDetails.setmFirstName(student.getParentObject().getmFirstName());
				pDetails.setmMiddleName(student.getParentObject().getmMiddleName());
				pDetails.setmLastName(student.getParentObject().getmLastName());
				pDetails.setmOccupation(student.getParentObject().getmOccupation());
				pDetails.setmQualification(student.getParentObject().getmQualification());
				pDetails.setmIncome(student.getParentObject().getmIncome());

				sDetails.setParentDetails(pDetails);
			}

			if (null != student.getStudentDocuments()) {
				StudentDocumentDetails sdDetails = new StudentDocumentDetails();
				sdDetails.setAadhaarNo(student.getStudentDocuments().getAadhaarNo());
				sdDetails.setAadhaarCard(student.getStudentDocuments().getAadhaarCard());
				sdDetails.setBirthCirtificate(student.getStudentDocuments().getBirthCertificate());
				sdDetails.setOtherCertificate(student.getStudentDocuments().getOtherCertificate());
				sdDetails.setRemarks(student.getStudentDocuments().getRemarks());

				sDetails.setDocumentDetails(sdDetails);
			}

			if (null != student.getStudentPreviousInfo()) {
				StudentPreviousDetails spDetails = new StudentPreviousDetails();
				spDetails.setSchool(student.getStudentPreviousInfo().getSchoolName());

				spDetails.setStudiedFrom(dateFormat.format(student.getStudentPreviousInfo().getStudiedFrom()));

				spDetails.setStudiedTo(dateFormat.format(student.getStudentPreviousInfo().getStudiedTo()));

				spDetails.setNoOfYears(student.getStudentPreviousInfo().getNoOfYears());

				spDetails.setPreviousClass(student.getStudentPreviousInfo().getPreviousClass());
				spDetails.setTotalMarks(student.getStudentPreviousInfo().getTotalMarks());
				spDetails.setObtainedMarks(student.getStudentPreviousInfo().getObtainedMarks());

				sDetails.setPreviousDetails(spDetails);
			}
			
			
			userDetailsList.add(userDetails);
		
		}
		return userDetailsList;
			
	}

	/*
	 * Method: createUserDetails ->Create UserDetailObjects from The UserObject
	 * input : List Of User(List<User>) Returns : List Of
	 * UserDetails(List<UserDetails>)
	 * 
	 */
	public List<UserDetails> createUserDetails(List<User> users) throws ParseException {
		UserDetails userDetails;
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		for (User user : users) {

			userDetails = new UserDetails();
			userDetails.setEmail(user.getEmail());
			userDetails.setRole(user.getRoleObject().getRoleName());
			userDetails.setSubRole(user.getSubRoleObject().getSubRoleName());
			userDetails.setUserId(user.getUserId());
			userDetails.setPrivileges(user.getPrivileges());
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_admin)) {
				Admin admin = (Admin) user;
				AdminDetails adminDetails = new AdminDetails();
				adminDetails.setName(admin.getName());
				userDetails.setAdminDetails(adminDetails);
			}
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_student)) {
				Student student = (Student) user;
				StudentDetails sDetails = new StudentDetails();
				sDetails.setStudentFirstName(student.getStudentFirstName());
				sDetails.setStudentMiddleName(student.getStudentMiddleName());
				sDetails.setStudentLastName(student.getStudentLastName());

				userDetails.setStudentDetails(sDetails);
			}
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_management)) {
				Management management = (Management) user;
				ManagementDetails mDetails = new ManagementDetails();
				mDetails.setMngmtFirstName(management.getMngmtFirstName());
				mDetails.setMngmtLastName(management.getMngmtLastName());
				userDetails.setManagementDetails(mDetails);
			}
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_non_teaching_staff)) {
				NonTeachingStaff nts = (NonTeachingStaff) user;
				NonTeachingStaffDetails ntsDetails = new NonTeachingStaffDetails();
				ntsDetails.setFirstName(nts.getFirstName());
				ntsDetails.setLastName(nts.getLastName());
				userDetails.setNonTeachingStaffDetails(ntsDetails);
			}
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_teaching_staff)) {
				TeachingStaff ts = (TeachingStaff) user;
				TeachingStaffDetails tsDetails = new TeachingStaffDetails();
				tsDetails.setFirstName(ts.getFirstName());
				tsDetails.setLastName(ts.getLastName());
				userDetails.setTeachingStaffDetails(tsDetails);
			}

			userDetailsList.add(userDetails);
		}

		return userDetailsList;
	}
	
	
	
	/*
	 * Method: createUserBasicDetails ->Create UserBasicDetails from The UserObject
	 * input : List Of User(List<User>) Returns : List Of
	 * UserBasicDetails(List<UseBasicDetails>)
	 * 
	 */
	public List<UserBasicDetails> createUserBasicDetails(List<User> users) {
		UserBasicDetails userBasicDetails;
		List<UserBasicDetails> userBasicDetailsList = new ArrayList<UserBasicDetails>();
		for (User user : users) {

			userBasicDetails = new UserBasicDetails();
			userBasicDetails.setEmail(user.getEmail());
			userBasicDetails.setRole(user.getRoleObject().getRoleName());
			userBasicDetails.setSubRole(user.getSubRoleObject().getSubRoleName());
			userBasicDetails.setUserId(user.getUserId());
			
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_admin)) {
				Admin admin = (Admin) user;
				userBasicDetails.setFirstName(admin.getName());
			}
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_student)) {
				Student student = (Student) user;
				userBasicDetails.setFirstName(student.getStudentFirstName());
				userBasicDetails.setMiddleName(student.getStudentMiddleName());
				userBasicDetails.setLastName(student.getStudentLastName());
			}
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_management)) {
				Management management = (Management) user;
				userBasicDetails.setFirstName(management.getMngmtFirstName());
				userBasicDetails.setMiddleName(management.getMngmtMiddleName());
				userBasicDetails.setLastName(management.getMngmtLastName());
			}
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_non_teaching_staff)) {
				NonTeachingStaff nts = (NonTeachingStaff) user;
				userBasicDetails.setFirstName(nts.getFirstName());
				userBasicDetails.setMiddleName(nts.getMiddleName());
				userBasicDetails.setLastName(nts.getLastName());
			}
			if (user.getRoleObject().getRoleName().equalsIgnoreCase(Constants.role_teaching_staff)) {
				TeachingStaff ts = (TeachingStaff) user;
				userBasicDetails.setFirstName(ts.getFirstName());
				userBasicDetails.setLastName(ts.getLastName());
				userBasicDetails.setMiddleName(ts.getMiddleName());
			}

			userBasicDetailsList.add(userBasicDetails);
		}

		return userBasicDetailsList;
	}


	/*
	 * Method: createParent -> maps ui values to entity input : ParentDetails
	 * return : Parent
	 * 
	 */

	public Parent createParent(ParentDetails details, User user) {
		Parent parent = new Parent();
		parent.setCreatedBy(user.getUserId());
		parent.setCreatedOn(new Date());
		parent.setfContactNumber(details.getfContactNumber());
		parent.setfEmail(details.getfEmail());
		parent.setfFirstName(details.getfFirstName());
		parent.setfIncome(details.getfIncome());
		parent.setfLastName(details.getfLastName());
		parent.setfMiddleName(details.getfMiddleName());
		parent.setfOccupation(details.getfOccupation());
		parent.setfQualification(details.getfQualification());
		parent.setmContactNumber(details.getmContactNumber());
		parent.setmEmail(details.getmEmail());
		parent.setmFirstName(details.getmFirstName());
		parent.setmIncome(details.getmIncome());
		parent.setmLastName(details.getmLastName());
		parent.setmMiddleName(details.getmMiddleName());
		parent.setmOccupation(details.getmOccupation());
		parent.setmQualification(details.getmQualification());
		parent.setFpassword(details.getFpassword());
		parent.setMpassword(details.getMpassword());
		parent.setIsNew(details.getIsNew());

		return parent;
	}
	/*
	 * Method: createManagement -> maps ui values to entity input :
	 * ManagementDetails return : Management
	 * 
	 */
	/*
	 * Method: createAddress -> maps ui values to entity input : AddressDetails
	 * return : Parent
	 * 
	 */

	public StudentAddress createStudentAddress(StudentAddressDetails details, User user) {
		StudentAddress address = new StudentAddress();

		if (details.isParmanentAddressSameAsCurrent()) {
			address.setcAddressLine1(details.getpAddressLine1());
			address.setcAddressLine2(details.getpAddressLine2());
			address.setcCountry(details.getpCountry());
			address.setcDistrict(details.getpDistrict());
			address.setcLocation(details.getpLocation());
			address.setcPincode(details.getpPincode());
			address.setcState(details.getpState());
			address.setcSubDivision(details.getpSubDivision());
			address.setcTehsil(details.getpTehsil());
			address.setcVillage(details.getpVillage());
		} else {
			address.setcAddressLine1(details.getcAddressLine1());
			address.setcAddressLine2(details.getcAddressLine2());
			address.setcCountry(details.getcCountry());
			address.setcDistrict(details.getcDistrict());
			address.setcLocation(details.getcLocation());
			address.setcPincode(details.getcPincode());
			address.setcState(details.getcState());
			address.setcSubDivision(details.getcSubDivision());
			address.setcTehsil(details.getcTehsil());
			address.setcVillage(details.getcVillage());
		}
		address.setpAddressLine1(details.getpAddressLine1());
		address.setpAddressLine2(details.getpAddressLine2());
		address.setpCountry(details.getpCountry());
		address.setpDistrict(details.getpDistrict());
		address.setpLocation(details.getpLocation());
		address.setpPincode(details.getpPincode());
		address.setpState(details.getpState());
		address.setpSubDivision(details.getpSubDivision());
		address.setpTehsil(details.getpTehsil());
		address.setpVillage(details.getpVillage());

		return address;
	}

	/*
	 * Method: createStudentDocuments -> maps ui values to entity input :
	 * StudentDocumentDetails return : StudentDocuments
	 * 
	 */

	public StudentDocuments createStudentDocuments(StudentDocumentDetails details, User user) {
		StudentDocuments docs = new StudentDocuments();
		docs.setAadhaarCard(details.getAadhaarCard());
		docs.setAadhaarNo(details.getAadhaarNo());
		docs.setBirthCertificate(details.getBirthCirtificate());
		docs.setOtherCertificate(details.getOtherCertificate());
		docs.setRemarks(details.getRemarks());

		return docs;
	}

	/*
	 * Method: createSibling -> maps ui values to entity input : SiblingDetails
	 * return : sibling
	 * 
	 */

	public List<Sibling> createSibling(List<SiblingDetails> SiblingDetails, User user) {
		
		List<Sibling> siblings = new  ArrayList<Sibling>();
		for(int i=0;i<siblings.size();i++) {
		
		//sibling.getSectionName(details.getSectionName());
		}
		return siblings;
	}

	/*
	 * Method: createStudentPreviousInfo-> maps ui values to entity input :
	 * SiblingDetails return : sibling
	 * 
	 */

	public StudentPreviousInfo createPreviousDetails(StudentPreviousDetails details, User user) throws ParseException {

		StudentPreviousInfo info = new StudentPreviousInfo();
		info.setNoOfYears(details.getNoOfYears());
		info.setObtainedMarks(details.getObtainedMarks());
		info.setPreviousClass(details.getPreviousClass());
		info.setSchoolName(details.getSchool());

		String sDate1 = details.getStudiedFrom();
		DateFormat edtFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD = edtFormat.parse(sDate1);
		info.setStudiedFrom((aLD));

		String sDate = details.getStudiedTo();
		DateFormat edtFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD1 = edtFormat1.parse(sDate);
		info.setStudiedTo(aLD1);

		info.setTotalMarks(details.getTotalMarks());
		return info;
	}

	public Management createManagement(ManagementDetails managementDetails, User user) {

		Management management = new Management();

		management.setTrustId(managementDetails.getTrustId());
		management.setMngmtFirstName(managementDetails.getMngmtFirstName());
		management.setMngmtMiddleName(managementDetails.getMngmtMiddleName());
		management.setMngmtLastName(managementDetails.getMngmtLastName());
		management.setMngmtDesignation(managementDetails.getMngmtDesignation());
		management.setMngmtContactNo(managementDetails.getMngmtContactNo());

		management.setMngmtCreationTime(new Date());
		management.setMngmtCreatedByWadmin(user.getUserId());
		management.setAcStatus("Complete");
		
		return management;
	}

	/*
	 * Method: createTeachingStaff -> maps ui values to entity input :
	 * TeachingStaffDetails return : TeachingStaff
	 * 
	 */
	public TeachingStaff createTeachingStaff(TeachingStaffDetails teachingStaffDetails, User user)
			throws ParseException {

		TeachingStaff teachingStaff = new TeachingStaff();

		teachingStaff.setDesignation(teachingStaffDetails.getDesignation());
		teachingStaff.setFirstName(teachingStaffDetails.getFirstName());
		teachingStaff.setMiddleName(teachingStaffDetails.getMiddleName());
		teachingStaff.setLastName(teachingStaffDetails.getMiddleName());
		teachingStaff.setBlood_group(teachingStaffDetails.getBloodGroup());

		// teachingStaff.setDob(teachingStaffDetails.getDateOfBirth());
		String sDate1 = teachingStaffDetails.getDateOfBirth();
		DateFormat edtFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD = edtFormat.parse(sDate1);
		teachingStaff.setDob(aLD);

		teachingStaff.setGender(teachingStaffDetails.getGender());
		teachingStaff.setAgeInYears(teachingStaffDetails.getAgeInYears());
		teachingStaff.setContactNo(teachingStaffDetails.getContactNo());
		teachingStaff.setQualification(teachingStaffDetails.getQualification());
		teachingStaff.setReligion(teachingStaffDetails.getReligion());
		teachingStaff.setCasteCategory(teachingStaffDetails.getCasteCategory());
		teachingStaff.setPhoto(teachingStaffDetails.getPhoto());
		teachingStaff.setMaritalStatus(teachingStaffDetails.getMaritalStatus());
		teachingStaff.setSpouseName(teachingStaffDetails.getSpouseName());
		teachingStaff.setSpouseContactNo(teachingStaffDetails.getSpouseContactNo());
		teachingStaff.setBloodGroup(teachingStaffDetails.getBloodGroup());
		
		// hard coded values
		teachingStaff.setCreatedByWadmin(user.getUserId());
		// teachingStaff.setCreationTime(new Date());

		teachingStaff.setCreationTime(new Date());

		teachingStaff.setAcStatus("Incomplete");

		/*
		 * Before setting this status to incomplete first validate whether all
		 * teachingStaff details completed or not
		 */

		teachingStaff.setAcStatus("Incomplete");

		return teachingStaff;
	}

	/*
	 * Method: createTeachingStaffDetails ->Creates TeachingDetailObjects from
	 * The TeachingObject input : List Of User(List<User>) Returns : List Of
	 * UserDetails(List<UserDetails>)
	 * 
	 */
	public List<UserDetails> createTeachingStaffDetails(List<User> users) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		UserDetails userDetails;

		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		for (User user : users) {

			userDetails = new UserDetails();
			userDetails.setEmail(user.getEmail());
			userDetails.setRole(user.getRoleObject().getRoleName());
			userDetails.setSubRole(user.getSubRoleObject().getSubRoleName());
			userDetails.setUserId(user.getUserId());

			TeachingStaff teachingStaff = (TeachingStaff) user;
			TeachingStaffDetails tDetails = new TeachingStaffDetails();

			tDetails.setDesignation(teachingStaff.getDesignation());
			tDetails.setFirstName(teachingStaff.getFirstName());
			tDetails.setMiddleName(teachingStaff.getMiddleName());
			tDetails.setLastName(teachingStaff.getLastName());

			tDetails.setDateOfBirth(dateFormat.format(teachingStaff.getDob()));
			tDetails.setGender(teachingStaff.getGender());
			tDetails.setAgeInYears(teachingStaff.getAgeInYears());
			tDetails.setContactNo(teachingStaff.getContactNo());
			tDetails.setQualification(teachingStaff.getQualification());
			tDetails.setReligion(teachingStaff.getReligion());
			tDetails.setCasteCategory(teachingStaff.getCasteCategory());
			tDetails.setPhoto(teachingStaff.getPhoto());
			tDetails.setMaritalStatus(teachingStaff.getMaritalStatus());
			tDetails.setSpouseName(teachingStaff.getSpouseName());
			tDetails.setBloodGroup(teachingStaff.getBloodGroup());
			tDetails.setSpouseContactNo(teachingStaff.getSpouseContactNo());
			tDetails.setCreatedByWadmin(teachingStaff.getCreatedByWadmin());
			tDetails.setCreationTime(dateFormat.format(teachingStaff.getCreationTime()));

			userDetails.setTeachingStaffDetails(tDetails);

			if (null != teachingStaff.getAddress()) {
				AddressDetails1 taDetails = new AddressDetails1();
				taDetails.setAddressLine1(teachingStaff.getAddress().getAddressLine1());
				taDetails.setAddressLine2(teachingStaff.getAddress().getAddressLine2());
				taDetails.setCountry(teachingStaff.getAddress().getCountry());
				taDetails.setState(teachingStaff.getAddress().getState());
				taDetails.setDistrict(teachingStaff.getAddress().getDistrict());
				taDetails.setSubDivision(teachingStaff.getAddress().getSubDivision());
				taDetails.setTehsil(teachingStaff.getAddress().getTehsil());
				taDetails.setVillage(teachingStaff.getAddress().getVillage());
				taDetails.setPincode(teachingStaff.getAddress().getPincode());

				tDetails.setAddressDetails1(taDetails);
			}

			if (null != teachingStaff.getStaffDocuments()) {
				StaffDocumentsDetails1 details1 = new StaffDocumentsDetails1();
				details1.setNt_staff_10th_certificate(teachingStaff.getStaffDocuments().getTen10thCertificate());
				details1.setNt_staff_12th_certificate(teachingStaff.getStaffDocuments().getTwelve12thCertificate());
				details1.setNt_staff_degree_certificate(teachingStaff.getStaffDocuments().getDegreeCertificate());
				details1.setNt_staff_pg_degree_certificate(teachingStaff.getStaffDocuments().getPgDegreeCertificate());
				details1.setNt_staff_medical_fitness_certificate(
						teachingStaff.getStaffDocuments().getMedicalFitnessCertificate());
				tDetails.setStaffDocumentsDetails1(details1);
			}

			if (null != teachingStaff.getStaffPreviousInformation()) {
				StaffPreviousInformationDetails1 spDetails = new StaffPreviousInformationDetails1();
				spDetails.setExperienceFlag(teachingStaff.getStaffPreviousInformation().isExperienceFlag());
				spDetails.setLastWorkedOrganisation(
						teachingStaff.getStaffPreviousInformation().getLastWorkedOrganisation());

				// spDetails.setDateOfJoining(dateFormat.format(teachingStaff.getStaffPreviousInformation().getDateOfJoining()));

				// spDetails.setRelievingDate(dateFormat.format(teachingStaff.getStaffPreviousInformation().getRelievingDate()));

				spDetails.setExperienceCertificate(
						teachingStaff.getStaffPreviousInformation().getExperienceCertificate());
				spDetails.setLastDrawnPayslip(teachingStaff.getStaffPreviousInformation().getLastDrawnPayslip());
				spDetails.setResume(teachingStaff.getStaffPreviousInformation().getResume());
				tDetails.setStaffPreviousInformationDetails1(spDetails);
				;
			}

			if (null != teachingStaff.getStaffStatutory()) {
				StaffStatutoryDetails1 ssDetails = new StaffStatutoryDetails1();
				ssDetails.setBankName(teachingStaff.getStaffStatutory().getBankName());
				ssDetails.setBankAccountNo(teachingStaff.getStaffStatutory().getBankAccountNo());
				ssDetails.setBankIfscCode(teachingStaff.getStaffStatutory().getBankIfscCode());
				ssDetails.setPanNo(teachingStaff.getStaffStatutory().getPanNo());
				ssDetails.setPanCard(teachingStaff.getStaffStatutory().getPanCard());

				ssDetails.setAadhaarNo(teachingStaff.getStaffStatutory().getAadhaarNo());
				ssDetails.setAadhaarCard(teachingStaff.getStaffStatutory().getAadhaarCard());
				ssDetails.setPfNo(teachingStaff.getStaffStatutory().getPfNo());
				tDetails.setStatutoryDetails1(ssDetails);
			}

			userDetailsList.add(userDetails);
		}

		return userDetailsList;

	}

	/*
	 * Method: createNonTeachingStaff -> maps ui values to entity input :
	 * NonTeachingStaffDetails return : NonTeachingStaff
	 * 
	 */
	public NonTeachingStaff createNonTeachingStaff(NonTeachingStaffDetails nonTeachingStaffDetails, User user)
			throws ParseException {
		NonTeachingStaff nonTeachingStaff = new NonTeachingStaff();

		nonTeachingStaff.setDesignation(nonTeachingStaffDetails.getDesignation());
		nonTeachingStaff.setFirstName(nonTeachingStaffDetails.getFirstName());
		nonTeachingStaff.setMiddleName(nonTeachingStaffDetails.getMiddleName());
		nonTeachingStaff.setLastName(nonTeachingStaffDetails.getLastName());
		// nonTeachingStaff.setDob(nonTeachingStaffDetails.getDob());
		String sDate1 = nonTeachingStaffDetails.getDob();

		DateFormat edtFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD = edtFormat.parse(sDate1);
		nonTeachingStaff.setDob(aLD);

		nonTeachingStaff.setGender(nonTeachingStaffDetails.getGender());
		nonTeachingStaff.setAgeInYears(nonTeachingStaffDetails.getAgeInYears());
		nonTeachingStaff.setContactNo(nonTeachingStaffDetails.getContactNo());
		nonTeachingStaff.setQualification(nonTeachingStaffDetails.getQualification());
		nonTeachingStaff.setReligion(nonTeachingStaffDetails.getReligion());
		nonTeachingStaff.setCasteCategory(nonTeachingStaffDetails.getCasteCategory());
		nonTeachingStaff.setPhoto(nonTeachingStaffDetails.getPhoto());
		nonTeachingStaff.setMaritalStatus(nonTeachingStaffDetails.getMaritalStatus());
		nonTeachingStaff.setSpouseName(nonTeachingStaffDetails.getSpouseName());
		nonTeachingStaff.setSpouseContactNo(nonTeachingStaffDetails.getSpouseContactNo());
		nonTeachingStaff.setCreatedByWadmin(user.getUserId());
		nonTeachingStaff.setBloodGroup(nonTeachingStaffDetails.getBloodGroup());
		// nonTeachingStaff.setCreationTime(new Date());

		nonTeachingStaff.setCreationTime(new Date());
		// hard coded values
		/*
		 * Before setting this status to incomplete first validate whether all
		 * teachingStaff details completed or not
		 */
		nonTeachingStaff.setStatus("Incomplete");
		
		
		return nonTeachingStaff;

	}

	/*
	 * Method: createNonTeachingStaffDetails ->Creates NonTeachingDetailObjects
	 * from The NonTeachingObject input : List Of User(List<User>) Returns :
	 * List Of UserDetails(List<UserDetails>)
	 * 
	 */
	public List<UserDetails> createNonTeachingStaffDetails(List<User> users) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		UserDetails userDetails;

		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();

		for (User user : users) {

			userDetails = new UserDetails();
			userDetails.setEmail(user.getEmail());
			userDetails.setRole(user.getRoleObject().getRoleName());
			userDetails.setSubRole(user.getSubRoleObject().getSubRoleName());
			userDetails.setUserId(user.getUserId());

			NonTeachingStaff nonTeachingStaff = (NonTeachingStaff) user;
			NonTeachingStaffDetails ntDetails = new NonTeachingStaffDetails();

			ntDetails.setDesignation(nonTeachingStaff.getDesignation());
			ntDetails.setFirstName(nonTeachingStaff.getFirstName());
			ntDetails.setMiddleName(nonTeachingStaff.getMiddleName());
			ntDetails.setLastName(nonTeachingStaff.getLastName());

			ntDetails.setDob(dateFormat.format(nonTeachingStaff.getDob()));
			ntDetails.setGender(nonTeachingStaff.getGender());
			ntDetails.setAgeInYears(nonTeachingStaff.getAgeInYears());
			ntDetails.setContactNo(nonTeachingStaff.getContactNo());
			ntDetails.setQualification(nonTeachingStaff.getQualification());
			ntDetails.setReligion(nonTeachingStaff.getReligion());
			ntDetails.setCasteCategory(nonTeachingStaff.getCasteCategory());
			ntDetails.setPhoto(nonTeachingStaff.getPhoto());
			ntDetails.setMaritalStatus(nonTeachingStaff.getMaritalStatus());
			ntDetails.setSpouseName(nonTeachingStaff.getSpouseName());
			ntDetails.setBloodGroup(nonTeachingStaff.getBloodGroup());
			ntDetails.setSpouseContactNo(nonTeachingStaff.getSpouseContactNo());
			ntDetails.setCreatedByWadmin(nonTeachingStaff.getCreatedByWadmin());
			ntDetails.setCreationTime(dateFormat.format(nonTeachingStaff.getCreationTime()));
			//ntDetails.setBloodGroup(nonTeachingStaff.getBloodGroup());
			userDetails.setNonTeachingStaffDetails(ntDetails);

			if (null != nonTeachingStaff.getAddress()) {
				AddressDetails ntaDetails = new AddressDetails();
				ntaDetails.setAddressLine1(nonTeachingStaff.getAddress().getAddressLine1());
				ntaDetails.setAddressLine2(nonTeachingStaff.getAddress().getAddressLine2());
				ntaDetails.setCountry(nonTeachingStaff.getAddress().getCountry());
				ntaDetails.setState(nonTeachingStaff.getAddress().getState());
				ntaDetails.setDistrict(nonTeachingStaff.getAddress().getDistrict());
				ntaDetails.setSubDivision(nonTeachingStaff.getAddress().getSubDivision());
				ntaDetails.setTehsil(nonTeachingStaff.getAddress().getTehsil());
				ntaDetails.setVillage(nonTeachingStaff.getAddress().getVillage());
				ntaDetails.setPincode(nonTeachingStaff.getAddress().getPincode());

				ntDetails.setAddressDetails(ntaDetails);
			}

			if (null != nonTeachingStaff.getStaffDocuments()) {
				StaffDocumentsDetails nsdetails1 = new StaffDocumentsDetails();
				nsdetails1.setNt_staff_10th_certificate(nonTeachingStaff.getStaffDocuments().getTen10thCertificate());
				nsdetails1
						.setNt_staff_12th_certificate(nonTeachingStaff.getStaffDocuments().getTwelve12thCertificate());
				nsdetails1.setNt_staff_degree_certificate(nonTeachingStaff.getStaffDocuments().getDegreeCertificate());
				nsdetails1.setNt_staff_pg_degree_certificate(
						nonTeachingStaff.getStaffDocuments().getPgDegreeCertificate());
				nsdetails1.setNt_staff_medical_fitness_certificate(
						nonTeachingStaff.getStaffDocuments().getMedicalFitnessCertificate());
				ntDetails.setStaffDocumentsDetails(nsdetails1);
			}

			if (null != nonTeachingStaff.getStaffPreviousInformation()) {
				StaffPreviousInformationDetails spDetails = new StaffPreviousInformationDetails();
				spDetails.setExperienceFlag(nonTeachingStaff.getStaffPreviousInformation().isExperienceFlag());
				spDetails.setLastWorkedOrganisation(
						nonTeachingStaff.getStaffPreviousInformation().getLastWorkedOrganisation());

				// spDetails.setDateOfJoining(dateFormat.format(teachingStaff.getStaffPreviousInformation().getDateOfJoining()));

				// spDetails.setRelievingDate(dateFormat.format(teachingStaff.getStaffPreviousInformation().getRelievingDate()));

				spDetails.setExperienceCertificate(
						nonTeachingStaff.getStaffPreviousInformation().getExperienceCertificate());
				spDetails.setLastDrawnPayslip(nonTeachingStaff.getStaffPreviousInformation().getLastDrawnPayslip());
				spDetails.setResume(nonTeachingStaff.getStaffPreviousInformation().getResume());
				ntDetails.setStaffPreviousInformationDetails(spDetails);
			}

			if (null != nonTeachingStaff.getStaffStatutory()) {
				StaffStatutoryDetails ssDetails = new StaffStatutoryDetails();
				ssDetails.setBankName(nonTeachingStaff.getStaffStatutory().getBankName());
				ssDetails.setBankAccountNo(nonTeachingStaff.getStaffStatutory().getBankAccountNo());
				ssDetails.setBankIfscCode(nonTeachingStaff.getStaffStatutory().getBankIfscCode());
				ssDetails.setPanNo(nonTeachingStaff.getStaffStatutory().getPanNo());
				ssDetails.setPanCard(nonTeachingStaff.getStaffStatutory().getPanCard());

				ssDetails.setAadhaarNo(nonTeachingStaff.getStaffStatutory().getAadhaarNo());
				ssDetails.setAadhaarCard(nonTeachingStaff.getStaffStatutory().getAadhaarCard());
				ssDetails.setPfNo(nonTeachingStaff.getStaffStatutory().getPfNo());
				ntDetails.setStaffStatutoryDetails(ssDetails);
			}

			userDetailsList.add(userDetails);
		}

		return userDetailsList;

	}

	/*
	 * Method: createAddressDetails -> maps ui values to entity input :
	 * AddressDetails return : Address
	 * 
	 */
	public Address createAddressDetails(AddressDetails addressDetails, User user) {

		Address address = new Address();
		address.setAddressLine1(addressDetails.getAddressLine1());
		address.setAddressLine2(addressDetails.getAddressLine2());
		address.setCountry(addressDetails.getCountry());
		address.setDistrict(addressDetails.getDistrict());
		address.setState(addressDetails.getState());
		address.setSubDivision(addressDetails.getSubDivision());
		address.setTehsil(addressDetails.getTehsil());
		address.setVillage(addressDetails.getVillage());
		address.setPincode(addressDetails.getPincode());
		return address;

	}

	public StaffDocuments createDocumentDetails(StaffDocumentsDetails staffDocumentsDetails, User user) {

		StaffDocuments staffDocuments = new StaffDocuments();

		staffDocuments.setTen10thCertificate(staffDocumentsDetails.getNt_staff_10th_certificate());
		staffDocuments.setTwelve12thCertificate(staffDocumentsDetails.getNt_staff_12th_certificate());
		staffDocuments.setDegreeCertificate(staffDocumentsDetails.getNt_staff_degree_certificate());
		staffDocuments.setPgDegreeCertificate(staffDocumentsDetails.getNt_staff_pg_degree_certificate());
		staffDocuments.setMedicalFitnessCertificate(staffDocumentsDetails.getNt_staff_medical_fitness_certificate());

		return staffDocuments;

	}

	/*
	 * Method: createStaffPreviousInformationDetails -> maps ui values to entity
	 * input : StaffPreviousInformationDetails return : StaffPreviousInformation
	 * 
	 */
	public StaffPreviousInformation createStaffPreviousInformationDetails(
			StaffPreviousInformationDetails staffPreviousInformationDetails, User user) throws ParseException {
		StaffPreviousInformation staffPreviousInformation = new StaffPreviousInformation();

		staffPreviousInformation.setExperienceFlag(staffPreviousInformationDetails.isExperienceFlag());
		staffPreviousInformation.setLastWorkedOrganisation(staffPreviousInformationDetails.getLastWorkedOrganisation());
		// staffPreviousInformation.setDateOfJoining(staffPreviousInformationDetails.getRelievingDate());
		String sDate1 = staffPreviousInformationDetails.getDateOfJoining();
		DateFormat edtFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD = edtFormat.parse(sDate1);
		staffPreviousInformation.setDateOfJoining(aLD);

		/*String sDate = staffPreviousInformationDetails.getRelievingDate();
		DateFormat edtFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD1 = edtFormat1.parse(sDate);
		staffPreviousInformation.setRelievingDate(aLD1);*/
		staffPreviousInformation.setExperienceCertificate(staffPreviousInformationDetails.getExperienceCertificate());
		staffPreviousInformation.setLastDrawnPayslip(staffPreviousInformationDetails.getLastDrawnPayslip());
		staffPreviousInformation.setResume(staffPreviousInformationDetails.getResume());

		return staffPreviousInformation;

	}

	/*
	 * Method: createStaffStatutoryDetails -> maps ui values to entity input :
	 * StaffStatutoryDetails return : StaffStatutory
	 * 
	 */
	public StaffStatutory createStaffStatutoryDetails(StaffStatutoryDetails staffStatutoryDetails, User user) {
		StaffStatutory staffStatutory = new StaffStatutory();
		staffStatutory.setBankAccountNo(staffStatutoryDetails.getBankAccountNo());
		staffStatutory.setBankName(staffStatutoryDetails.getBankName());
		staffStatutory.setBankIfscCode(staffStatutoryDetails.getBankIfscCode());
		staffStatutory.setAadhaarNo(staffStatutoryDetails.getAadhaarNo());
		staffStatutory.setAadhaarCard(staffStatutoryDetails.getAadhaarCard());
		staffStatutory.setPanNo(staffStatutoryDetails.getPanNo());
		staffStatutory.setPanCard(staffStatutoryDetails.getPanCard());
		staffStatutory.setPfNo(staffStatutoryDetails.getPfNo());
		return staffStatutory;
	}

	// Teaching Staff Entity creator starts from here

	// --------------------------------------------------

	public Address1 createAddressDetails1(AddressDetails addressDetails, User user) {

		Address1 address = new Address1();
		address.setAddressLine1(addressDetails.getAddressLine1());
		address.setAddressLine2(addressDetails.getAddressLine2());
		address.setCountry(addressDetails.getCountry());
		address.setDistrict(addressDetails.getDistrict());
		address.setState(addressDetails.getState());
		address.setSubDivision(addressDetails.getSubDivision());
		address.setTehsil(addressDetails.getTehsil());
		address.setVillage(addressDetails.getVillage());
		address.setPincode(addressDetails.getPincode());
		return address;

	}

	public StaffDocuments1 createDocumentDetails1(StaffDocumentsDetails staffDocumentsDetails, User user) {

		StaffDocuments1 staffDocuments = new StaffDocuments1();

		staffDocuments.setTen10thCertificate(staffDocumentsDetails.getNt_staff_10th_certificate());
		staffDocuments.setTwelve12thCertificate(staffDocumentsDetails.getNt_staff_12th_certificate());
		staffDocuments.setDegreeCertificate(staffDocumentsDetails.getNt_staff_degree_certificate());
		staffDocuments.setPgDegreeCertificate(staffDocumentsDetails.getNt_staff_pg_degree_certificate());
		staffDocuments.setMedicalFitnessCertificate(staffDocumentsDetails.getNt_staff_medical_fitness_certificate());

		return staffDocuments;

	}

	/*
	 * Method: createStaffPreviousInformationDetails -> maps ui values to entity
	 * input : StaffPreviousInformationDetails return : StaffPreviousInformation
	 * 
	 */
	public StaffPreviousInformation1 createStaffPreviousInformationDetails1(
			StaffPreviousInformationDetails staffPreviousInformationDetails, User user) throws ParseException {
		StaffPreviousInformation1 staffPreviousInformation = new StaffPreviousInformation1();

		staffPreviousInformation.setExperienceFlag(staffPreviousInformationDetails.isExperienceFlag());
		staffPreviousInformation.setLastWorkedOrganisation(staffPreviousInformationDetails.getLastWorkedOrganisation());
		// staffPreviousInformation.setDateOfJoining(staffPreviousInformationDetails.getRelievingDate());
		String sDate1 = staffPreviousInformationDetails.getDateOfJoining();
		DateFormat edtFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date aLD = edtFormat.parse(sDate1);
		staffPreviousInformation.setDateOfJoining(aLD);
/*
		//String sDate = staffPreviousInformationDetails.getRelievingDate();
		//DateFormat edtFormat1 = new SimpleDateFormat("yyyy-mm-dd");
		//Date aLD1 = edtFormat1.parse(sDate);*/
		staffPreviousInformation.setExperienceCertificate(staffPreviousInformationDetails.getExperienceCertificate());
		staffPreviousInformation.setLastDrawnPayslip(staffPreviousInformationDetails.getLastDrawnPayslip());
		staffPreviousInformation.setResume(staffPreviousInformationDetails.getResume());

		return staffPreviousInformation;

	}

	/*
	 * Method: createStaffStatutoryDetails -> maps ui values to entity input :
	 * StaffStatutoryDetails return : StaffStatutory
	 * 
	 */
	public StaffStatutory1 createStaffStatutoryDetails1(StaffStatutoryDetails staffStatutoryDetails, User user) {
		StaffStatutory1 staffStatutory = new StaffStatutory1();
		staffStatutory.setBankAccountNo(staffStatutoryDetails.getBankAccountNo());
		staffStatutory.setBankName(staffStatutoryDetails.getBankName());
		staffStatutory.setBankIfscCode(staffStatutoryDetails.getBankIfscCode());
		staffStatutory.setAadhaarNo(staffStatutoryDetails.getAadhaarNo());
		staffStatutory.setAadhaarCard(staffStatutoryDetails.getAadhaarCard());
		staffStatutory.setPanNo(staffStatutoryDetails.getPanNo());
		staffStatutory.setPanCard(staffStatutoryDetails.getPanCard());
		staffStatutory.setPfNo(staffStatutoryDetails.getPfNo());
		return staffStatutory;
	}
	
	
	
	
	/*
	 * Method: createSubRoleDetails -> maps ui values to entity input :
	 * SubRoleDetails return : SubRole
	 * 
	 */
public AkacartUser createAkacartUserDetails(AkacartUserDetails akacartUserDetails,User user) {
	AkacartUser akacartuser = new AkacartUser();
	akacartuser.setUserId(akacartUserDetails.getUserId());
	akacartuser.setAkakartAccess(akacartUserDetails.isAkakartAccess());
	akacartuser.setCreatedOn(akacartUserDetails.getCreatedOn());
	return akacartuser;
}

}