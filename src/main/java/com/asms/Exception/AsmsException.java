package com.asms.Exception;

/*class AsmsException*/
public class AsmsException extends Exception
{
	private String code;
	private String Description; 
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
}
