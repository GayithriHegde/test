package com.asms.feemgmt.request;



/* User Request class represents the request object
 * for fee management operations 
 * 
 */

public class UserRequest {
	
	// request type can be create/update/delete
		private String requestType;

		// user role can be student/management/ teaching staff/ non teaching staff
		private String userRole;

		// subrole
		private String subRole;
		
		private FeeCategoryDetails categoryDetails;
		
		private PaymentTypeDetails paymentTypeDetails;
		
		private FeeMasterDetails feeMasterDetails;

		public String getRequestType() {
			return requestType;
		}

		public void setRequestType(String requestType) {
			this.requestType = requestType;
		}

		public String getUserRole() {
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
		}

		public FeeCategoryDetails getCategoryDetails() {
			return categoryDetails;
		}

		public void setCategoryDetails(FeeCategoryDetails categoryDetails) {
			this.categoryDetails = categoryDetails;
		}

		public PaymentTypeDetails getPaymentTypeDetails() {
			return paymentTypeDetails;
		}

		public void setPaymentTypeDetails(PaymentTypeDetails paymentTypeDetails) {
			this.paymentTypeDetails = paymentTypeDetails;
		}

		public FeeMasterDetails getFeeMasterDetails() {
			return feeMasterDetails;
		}

		public void setFeeMasterDetails(FeeMasterDetails feeMasterDetails) {
			this.feeMasterDetails = feeMasterDetails;
		}
		
		


}
