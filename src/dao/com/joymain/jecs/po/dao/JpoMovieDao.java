package com.joymain.jecs.po.dao;


import java.util.List;

import com.joymain.jecs.dao.Dao;


public interface JpoMovieDao extends  Dao{
	/**
	 * find movie by status
	 * @param satus
	 * @return
	 */
	public List<com.joymain.jecs.po.model.JpoMovie> findMovieByName(char status);
	/**
	 * get movie info by orderNo
	 * @param orderNo
	 * @return jpoMovie
	 */
	public com.joymain.jecs.po.model.JpoMovie getMovieByOrderNo(String orderNo);
}
