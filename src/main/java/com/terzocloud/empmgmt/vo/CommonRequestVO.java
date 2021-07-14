package com.terzocloud.empmgmt.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonRequestVO{
	private long empId;
	private long deptId;
	private long managerEmpId;
}