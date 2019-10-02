
package com.joymain.jecs.al.service;

import java.util.List;

import com.joymain.jecs.service.Manager;
import com.joymain.jecs.al.model.JalLibraryFile;
import com.joymain.jecs.al.dao.JalLibraryFileDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
public interface JalLibraryFileManager extends Manager {
    /**
     * Retrieves all of the jalLibraryFiles
     */
    public List getJalLibraryFiles(JalLibraryFile jalLibraryFile);

    /**
     * Gets jalLibraryFile's information based on fileId.
     * @param fileId the jalLibraryFile's fileId
     * @return jalLibraryFile populated jalLibraryFile object
     */
    public JalLibraryFile getJalLibraryFile(final String fileId);

    /**
     * Saves a jalLibraryFile's information
     * @param jalLibraryFile the object to be saved
     */
    public void saveJalLibraryFile(JalLibraryFile jalLibraryFile);

    /**
     * Removes a jalLibraryFile from the database by fileId
     * @param fileId the jalLibraryFile's fileId
     */
    public void removeJalLibraryFile(final String fileId);
    //added for getJalLibraryFilesByCrm
    public List getJalLibraryFilesByCrm(CommonRecord crm, Pager pager);
    
    public List getJalLibraryFileByCulumnId(final String columnId);
}

