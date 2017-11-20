package com.asms.common.helper;

public class Constants {

	public static final String role_admin = "Administrator";
	public static final String role_student = "Student";
	public static final String role_management = "Management";
	public static final String role_teaching_staff = "Teacher";
	public static final String role_non_teaching_staff = "Admin_finance";
	public static final String role_parent = "Parent";
	public static final String role_super_admin = "Super_Admin";
	
	public static final String role_admin_subrole_admin = "Admin";//Admin
	public static final String role_nonTeaching_subrole_manager = "Manager";
	public static final String role_nonTeaching_subrole_advisor = "Advisor";
	public static final String role_nonTeaching_subrole_sAccountant = "Senior_Accountant";
	public static final String role_nonTeaching_subrole_jAccountant = "Junior_Accountant";
	public static final String role_nonTeaching_subrole_frontClerk = "Front_Clerk";
	public static final String role_teaching_subrole_principal = "Principal";
	public static final String role_teaching_subrole_viceprincipal = "Vice_Principal";
	public static final String role_teaching_subrole_headMistress = "Head_Mistress";

	public static final String role_teaching_subrole_teacher = "Teacher";
	public static final String role_teaching_subrole_classTeacher = "Class_Teacher";

	public static final String role_student_subrole_student = "Student";
	public static final String role_student_subrole_leader = "Class_Leader";

	public static final String role_management_subrole_Treasurer = "Treasurer";
	public static final String role_management_subrole_President = "President";
	public static final String role_management_subrole_CEO = "CEO";
	public static final String role_management_subrole_Trustee = "Trustee";

	public static final String detail_address = "addressDetails";
	public static final String documents = "documents";
	public static final String previous_info = "previous_info";
	public static final String statutory_details = "statutory_details";
	public static final String detail_parent = "parentInfo";

	public static final String detail_documents = "documents";

	public static final String detail_siblings = "siblings";

	public static final String detail_previous_school = "previousInfo";

	// privileges

	public static final String admin_category = "admin";
	public static final String admin_category_setup = "School Setup";
	public static final String admin_category_roles = "Roles and Subroles";
	public static final String admin_category_userManagement = "User management";
	public static final String admin_category_assignPermissions = "Assign Permissions";

	public static final String academics_category = "Academics";
	public static final String academics_category_calendar = "School Calendar";
	public static final String academics_category_timeTable = "Class Time Table";
	public static final String academics_category_attendance = "Attendance";
	public static final String academics_category_studentAttendance = "Student Attendance";
	public static final String academics_category_staffAttendance = "Staff Attendance";
	public static final String academics_category_teacherTimeTable = "Teacher Time Table";
	public static final String academics_category_mrksSheetAndReportCard = "Marks sheet and Report Card";
	public static final String academics_category_workPlan = "Work Plan";
	public static final String academics_category_uploadArticles = "Upload Articles";
	public static final String academics_category_broadcastMessages = "Broadcast Messages";

	public static final String fee_category = "Fee";
	public static final String fee_category_feeStructure = "Fee Structure";
	public static final String fee_category_feePayments = "Fee Payments";
	public static final String fee_category_feeTransactions = "Fee Transactions";
	
	public static final String no_of_installments="no_of_installments";
	public static final String no_of_months="no_of_months";

	public static final String settings_category = "Settings";
	public static final String settings_category_dashboard = "Dashboard";
	public static final String settings_category_general = "General";
	public static final String settings_category_account= "Account";
	public static final String settings_category_display= "Display";

	public enum privileges {
		create_check, update_check, retrieve_check, delete_check
	}

	// message constants

	public enum receiver_type {
		all, all_parents, all_students, all_staff, all_teaching_staff, all_non_teaching_staff, all_management, parents_class_section, student_class_section, selected_students, selected_parents, selected_staff,
		selected_users
	}

	public enum message_type {
		Inbox, Sent
	}
	
	public enum message_view_types {
		dashboard, mailbox
	}

	public enum user_status {Complete, InComplete};

}
