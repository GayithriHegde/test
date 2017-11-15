/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.request;

public class AttendRequest {
	
	
		// request type can be create/update/delete
		private String requestType;

/*		// user role can be student/management/ teaching staff/ non teaching staff
		private String userRole;

		// subrole
		private String subRole;*/
		
		private AttendanceDetails attendanceDetails;

		public String getRequestType() {
			return requestType;
		}

		public void setRequestType(String requestType) {
			this.requestType = requestType;
		}

		/*public String getUserRole() {
			return userRole;
		}

		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}

		public String getSubRole() {
			return subRole;
		}

		public void setSubRole(String subRole) {
			this.subRole = subRole;
		}*/

		public AttendanceDetails getAttendanceDetails() {
			return attendanceDetails;
		}

		public void setAttendanceDetails(AttendanceDetails attendanceDetails) {
			this.attendanceDetails = attendanceDetails;
		}

}
