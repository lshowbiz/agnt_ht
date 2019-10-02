package com.joymain.jecs.sys.model;

/**
 * 后台服务状态,与数据表不关联
 * @author Aidy.Q
 * 
 */
public class SysService {
	private String serviceName;
	private String groupName;
	private int activeCount;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}
}
