<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>




<form action="" method="get"  name="bdSendRecordAllotForm1" id="bdSendRecordAllotForm1" >

<div class="searchBar">

<div class="new_searchBar">	
<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode }" size="12"/>
	</div>
	
	<div class="new_searchBar">	
				<button name="search" type="submit" class="btn btn_sele"  >
					<jecs:locale key="operation.button.search"/>
				</button>
		</div>


</div>
</form>

<ec:table 
	tableId="jbdManualConListTable"
	items="jbdManualConList"
	var="jbdManualCon"
	action="${pageContext.request.contextPath}/jbdManualCons.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="会员编号" />
    			<ec:column property="startWeek" title="开始期" >
    				<jecs:weekFormat week="${jbdManualCon.startWeek }" weekType="w"></jecs:weekFormat>
    			</ec:column>
    			<ec:column property="endWeek" title="结束期" >
    				<jecs:weekFormat week="${jbdManualCon.endWeek }" weekType="w"></jecs:weekFormat>
    			</ec:column>
    			<ec:column property="salesStatus" title="销售奖资格" >
    				<jecs:code listCode="yesno" value="${jbdManualCon.salesStatus }"></jecs:code>
    			</ec:column>
    			<ec:column property="consumerStatus" title="感恩奖资格" >
    				
    				<jecs:code listCode="yesno" value="${jbdManualCon.consumerStatus }"></jecs:code>
    			</ec:column>
    			<ec:column property="createNo" title="创建人" />
    			<ec:column property="createTime" title="创建时间" />
    			
    			
    			<ec:column property="1" title="title.operation" sortable="false" width="150px">
					
							
	<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJbdManualCon.html?id=${jbdManualCon.id}';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
				
					
				</ec:column>
    			
    			
    			
				</ec:row>

</ec:table>	
