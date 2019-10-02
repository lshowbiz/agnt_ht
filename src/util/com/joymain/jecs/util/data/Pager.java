package com.joymain.jecs.util.data;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.context.HttpServletRequestContext;
import org.extremecomponents.table.limit.Limit;
import org.extremecomponents.table.limit.LimitFactory;
import org.extremecomponents.table.limit.TableLimit;
import org.extremecomponents.table.limit.TableLimitFactory;


/**
 * 
 * @author Administrator
 *
 */
public class Pager {
    private int totalPages = 0;
    private int totalObjects = 0;
    private int pageNumber = 1;
    private int pageSize = 20;
    private boolean pageAble = true;
    private Limit limit;

    private int firstResult;

    public Pager(){
    	
    }
    
    /**
     * 初始化分页参数(extremeTable专用)
     * @param request
     * @param pageSize
     */
    public Pager(HttpServletRequest request,int pageSize){
    	this.initPager(null, request, pageSize);
    }
    
    /**
     * 初始化分页参数(extremeTable专用)
     * @param request
     * @param pageSize
     */
    public Pager(String tableId, HttpServletRequest request,int pageSize){
    	this.initPager(tableId, request, pageSize);
    }
    
    private void initPager(String tableId, HttpServletRequest request,int pageSize){
    	Context context = new HttpServletRequestContext(request);
    	LimitFactory limitFactory=null;
    	if(!StringUtils.isEmpty(tableId)){
    		limitFactory = new TableLimitFactory(context,tableId);
    	}else{
    		limitFactory = new TableLimitFactory(context);
    	}
		this.limit = new TableLimit(limitFactory);
		this.setPageSize(pageSize);
		this.setPageNumber(limit.getPage());
		//System.out.println("currentRowsDisplayed: " + limit.getCurrentRowsDisplayed());
		//System.out.println("page: " + limit.getPage());
    }

    public void calc(){
        totalPages =  totalObjects % pageSize == 0 ? totalObjects
                / pageSize : totalObjects / pageSize + 1;
        if(totalPages>0){
        	if(pageNumber>totalPages) pageNumber=totalPages;
        }else{
        	pageNumber=1;
        }
        firstResult = (pageNumber - 1) * pageSize;
        if(limit!=null){
        	limit.setRowAttributes(totalObjects, pageSize);
        }
    }

    public boolean isPageAble() {
        return pageAble;
    }

    public void setPageAble(boolean pageAble) {
        this.pageAble = pageAble;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int param) {
        this.totalObjects = param;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int param) {
        this.totalPages = param;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String toString(){
        return("\ntotalPages:" + totalPages +
                "\ntotalObjects:" + totalObjects +
                "\npageNumber:" + pageNumber +
                "\npageSize:" + pageSize +
                "\npageAble:" + pageAble +
                "\nfirstResult:" + firstResult);
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

	public Limit getLimit() {
    	return limit;
    }

	public void setLimit(Limit limit) {
    	this.limit = limit;
    }
}
