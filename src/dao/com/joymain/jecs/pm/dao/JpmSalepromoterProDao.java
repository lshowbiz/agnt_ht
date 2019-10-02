
package com.joymain.jecs.pm.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.pm.model.JpmSalepromoterPro;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JpmSalepromoterProDao extends Dao {

    /**
     * Retrieves all of the jpmSalepromoterPros
     */
    public List getJpmSalepromoterPros(JpmSalepromoterPro jpmSalepromoterPro);

    /**
     * Gets jpmSalepromoterPro's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the jpmSalepromoterPro's id
     * @return jpmSalepromoterPro populated jpmSalepromoterPro object
     */
    public JpmSalepromoterPro getJpmSalepromoterPro(final Long id);

    /**
     * Saves a jpmSalepromoterPro's information
     * @param jpmSalepromoterPro the object to be saved
     */    
    public void saveJpmSalepromoterPro(JpmSalepromoterPro jpmSalepromoterPro);

    /**
     * Removes a jpmSalepromoterPro from the database by id
     * @param id the jpmSalepromoterPro's id
     */
    public void removeJpmSalepromoterPro(final Long id);
    //added for getJpmSalepromoterProsByCrm
    public List getJpmSalepromoterProsByCrm(CommonRecord crm, Pager pager);
    /**
     * get JpmSalepromoterPro by spno and productid
     * @param spno
     * @param proNo
     * @return JpmSalepromoterPro
     */
    public JpmSalepromoterPro getJpmSaleProByproductId(Long spno, String proNo);
    /**
     * get JpmSalepromoterPro by spno
     * @param spno
     * @return list
     */
    public List<JpmSalepromoterPro> getJpmSaleProBySpno(String spno);
    public void delJpmSalepromoterProBySpno(String spno);
    
}

