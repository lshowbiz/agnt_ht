
package com.joymain.jecs.po.service;

import com.joymain.jecs.po.model.JpoCheckedFailed;
import com.joymain.jecs.service.Manager;


public interface JpoCheckedFailedManager extends Manager {
	
	public void saveCheckedFailed(JpoCheckedFailed failedInfo);
	
}

