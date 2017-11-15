package com.asms.rolemgmt.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.usermgmt.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*Entity class for sub_roles table.
 * 
 * 
 */

 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "sub_roles")
public class SubRole {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_role_id")
	private int subRoleId;
	

	@Column(name = "sub_role_name")
	private String subRoleName;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role roleObject;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subRoleObject")
	private List<User> userObject = new ArrayList<User>();
	
	
	
	

	@JsonIgnore
	public List<User> getUserObject() {
		return userObject;
	}






	public void setUserObject(List<User> userObject) {
		this.userObject = userObject;
	}






	public int getSubRoleId() {
		return subRoleId;
	}






	public void setSubRoleId(int subRoleId) {
		this.subRoleId = subRoleId;
	}






	public String getSubRoleName() {
		return subRoleName;
	}






	public void setSubRoleName(String subRoleName) {
		this.subRoleName = subRoleName;
	}





    @JsonIgnore
	public Role getRoleObject() {
		return roleObject;
	}






	public void setRoleObject(Role roleObject) {
		this.roleObject = roleObject;
	}






	@Override
	public String toString() {
		return "";
	}
}
