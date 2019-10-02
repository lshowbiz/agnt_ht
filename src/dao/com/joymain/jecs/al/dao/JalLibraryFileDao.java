
package com.joymain.jecs.al.dao;

import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.al.model.JalLibraryFile;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JalLibraryFileDao extends Dao {

    /**
     * Retrieves all of the jalLibraryFiles
     */
    public List getJalLibraryFiles(JalLibraryFile jalLibraryFile);

    /**
     * Gets jalLibraryFile's information based on primary key. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param fileId the jalLibraryFile's fileId
     * @return jalLibraryFile populated jalLibraryFile object
     */
    public JalLibraryFile getJalLibraryFile(final Long fileId);

    /**
     * Saves a jalLibraryFile's information
     * @param jalLibraryFile the object to be saved
     */    
    public void saveJalLibraryFile(JalLibraryFile jalLibraryFile);

    /**
     * Removes a jalLibraryFile from the database by fileId
     * @param fileId the jalLibraryFile's fileId
     */
    public void removeJalLibraryFile(final Long fileId);
    //added for getJalLibraryFilesByCrm
    public List getJalLibraryFilesByCrm(CommonRecord crm, Pager pager);
    
    public List getJalLibraryFileByCulumnId(final String columnId);
}

