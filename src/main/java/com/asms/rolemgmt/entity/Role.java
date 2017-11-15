package com.asms.rolemgmt.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.usermgmt.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*Entity class for Roles table.
 * 
 * 
 */

 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "roles")
public class Role {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int roleId;
	

	@Column(name = "role_name")
	private String roleName;
	
	//mapping for sub roles under role
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "roleObject")
	private List<SubRole> subRoleObjectList = new ArrayList<SubRole>();
	
	//mapping for users under a role
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "roleObject")
	private List<User> userObject = new ArrayList<User>();
	
	
	@JsonIgnore
	public List<User> getUserObject() {
		return userObject;
	}






	public void setUserObject(List<User> userObject) {
		this.userObject = userObject;
	}






	@JsonIgnore
	public List<SubRole> getSubRoleObjectList() {
		return subRoleObjectList;
	}






	public void setSubRoleObjectList(List<SubRole> subRoleObjectList) {
		this.subRoleObjectList = subRoleObjectList;
	}






	public int getRoleId() {
		return roleId;
	}






	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}






	public String getRoleName() {
		return roleName;
	}






	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}






	@Override
	public String toString() {
		return "";
	}
}
