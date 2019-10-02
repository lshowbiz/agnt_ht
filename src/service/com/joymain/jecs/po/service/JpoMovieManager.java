package com.joymain.jecs.po.service;

import java.util.List;
import com.joymain.jecs.po.model.JpoMovie;
import com.joymain.jecs.service.Manager;

public interface JpoMovieManager extends Manager{

	/**
	 * find movie by mName
	 * @param mName
	 * @return JpoMovie list
	 */
	public List<JpoMovie> findMovieByName(String mName);
}
