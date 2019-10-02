
package com.joymain.jecs.pm.service;

import java.util.List;

import com.joymain.jecs.pm.model.JpmSalepromoterPro;
import com.joymain.jecs.service.Manager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JpmSalepromoterProManager extends Manager {
    /**
     * Retrieves all of the jpmSalepromoterPros
     */
    public List getJpmSalepromoterPros(JpmSalepromoterPro jpmSalepromoterPro);

    /**
     * Gets jpmSalepromoterPro's information based on id.
     * @param id the jpmSalepromoterPro's id
     * @return jpmSalepromoterPro populated jpmSalepromoterPro object
     */
    public JpmSalepromoterPro getJpmSalepromoterPro(final String id);

    /**
     * Saves a jpmSalepromoterPro's information
     * @param jpmSalepromoterPro the object to be saved
     */
    public void saveJpmSalepromoterPro(JpmSalepromoterPro jpmSalepromoterPro);

    /**
     * Removes a jpmSalepromoterPro from the database by id
     * @param id the jpmSalepromoterPro's id
     */
    public void removeJpmSalepromoterPro(final String id);
    /**
     * Removes a jpmSalepromoterPro
     */
    public void removeJpmSalepromoterPro(JpmSalepromoterPro jsp);
    //added for getJpmSalepromoterProsByCrm
    public List getJpmSalepromoterProsByCrm(CommonRecord crm, Pager pager);
    /**
     * get JpmSalepromoterPro by productId and spno
     * @param spno
     * @param proNo
     * @return JpmSalepromoterPro
     */
    public JpmSalepromoterPro getJpmSaleProByproductId(String spno,String proNo);
    /**
     * get JpmSalepromoterPro by spno
     * @param spno
     * @return list
     */
    public List<JpmSalepromoterPro> getJpmSaleProBySpno(String spno);
    /**
     * delete JpmSalepromoterPro by spno
     * @param spno
     */
    public void delJpmSalepromoterProBySpno(String spno);
    
}

