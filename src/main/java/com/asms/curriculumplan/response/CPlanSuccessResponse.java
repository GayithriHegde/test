package com.asms.curriculumplan.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.curriculumplan.entity.CurriculumPlan;
import com.asms.curriculumplan.entity.Unit;

public class CPlanSuccessResponse extends SuccessResponse{
	
	
	
	private List<Unit> units;
	
	private List<CurriculumPlan> cPlans;




	public List<CurriculumPlan> getcPlans() {
		return cPlans;
	}

	public void setcPlans(List<CurriculumPlan> cPlans) {
		this.cPlans = cPlans;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
	


	
	

}
