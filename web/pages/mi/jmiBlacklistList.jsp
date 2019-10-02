<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiBlacklistList.title"/></title>
<content tag="heading"><jecs:locale key="jmiBlacklistList.heading"/></content>
<meta name="menu" content="JmiBlacklistMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction" value="jmiBlacklists"/>
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdCalculatingSubDetail.name"/>
			<input name="userName" type="text" value="${param.userName}" size="10"/>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="miMember.phone"/>
			<input name="phone" type="text" value="${param.phone}" size="10"/>	
		</div>	
		<div class="new_searchBar">
			<jecs:title key="miMember.papernumber"/>
			<input name="papernumber" type="text" value="${param.papernumber}" size="10"/>	
		</div>
		<%-- 	
		<jecs:title key="busi.common.remark"/>
		<input name="remark" type="text" value="${param.remark}" size="10"/>	 
		--%>
		<div class="new_searchBar">
			<jecs:title key="customerRecord.type"/>
         	<jecs:list name="blackType" listCode="blacktype" value="${param.blackType}" defaultValue="" showBlankLine="true"/>	
         </div>
         <div class="new_searchBar">
          	<jecs:title  key="customerRecord.state"/>
	 		<jecs:list listCode="status" name="status" id="blacklist.status" 
	 		value="${param.status}"  defaultValue="" showBlankLine="true" />
         </div>
         <div class="new_searchBar">
         	<jecs:title key="pd.createTime"/>
         	<input id="createBTime" name="createBTime" type="text" value="${param.createBTime }" 
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			 - 
			<input id="createETime" name="createETime" type="text" value="${param.createETime }" 
			style="cursor: pointer;width:75px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			
		 </div>
		 <div class="new_searchBar" style="margin-left: 30px">
		 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn_sele" name="cancel">
		 		<jecs:locale key="operation.button.search"/>
		 	</button>
			<%-- <input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />--%>
			<jecs:power powerCode="addJmiBlacklist">
				<button type="button" class="btn btn_ins" style="margin-right: 5px"
				onclick="location.href='<c:url value="/editJmiBlacklist.html"/>?strAction=addJmiBlacklist'">
					<jecs:locale key="member.new.num"/>
				</button>
				<%-- 
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiBlacklist.html"/>?strAction=addJmiBlacklist'"
			        value="<jecs:locale key="member.new.num"/>"/>
			    --%>
			</jecs:power>
		 </div>
	</div>
</form>








<ec:table 
	tableId="jmiBlacklistListTable"
	items="jmiBlacklistList"
	var="jmiBlacklist"
	action="${pageContext.request.contextPath}/jmiBlacklists.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
			<ec:exportXls fileName="jmiBlacklist.xls"/>
				<ec:row >
    			<ec:column property="papertype" title="miMember.papertype" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				
    <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
    				<jecs:code listCode="papertype.tw" value="${jmiBlacklist.papertype}"/>
    </c:if>
    <c:if test="${sessionScope.sessionLogin.companyCode!='TW'}">
    				<jecs:code listCode="papertype" value="${jmiBlacklist.papertype}"/>
    </c:if>
    			</ec:column>
    			<ec:column property="papernumber" title="miMember.papernumber" escapeAutoFormat="true"/>
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="userName" title="bdCalculatingSubDetail.name" />
    			<ec:column property="phone" title="miMember.phone" />
    			<ec:column property="blackType" title="customerRecord.type" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="blacktype" value="${jmiBlacklist.blackType}"/>
    			</ec:column>
    			<ec:column property="status" title="customerRecord.state" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="blacklist.status" value="${jmiBlacklist.status}"/>
    			</ec:column>
    			<ec:column property="remark" title="busi.common.remark" />
    			<ec:column property="createNo" title="customerRecord.createNo" />
    			<ec:column property="createTime" title="pd.createTime" format="yyyy-MM-dd HH:mm:ss" cell="date"/>
    			
    			<ec:column property="1" title="title.operation" sortable="false" width="100px" viewsDenied="xls">
    				
					<jecs:power powerCode="deleteJmiBlacklist">
						<a href="jmiBlacklists.html?blId=${jmiBlacklist.blId}&strAction=deleteJmiBlacklist" onclick="return window.confirm('<jecs:locale key="amMessage.confirmdelete"/>');"><img border="0" src="images/icons/w.gif" alt="<jecs:locale key="operation.button.delete"/>" /></a>
					</jecs:power>
					
					
					<jecs:power powerCode="editJmiBlacklist">
					<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiBlacklist.html?blId=${jmiBlacklist.blId}&strAction=editJmiBlacklist';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
					</jecs:power>
					
    			</ec:column>
    			
				</ec:row>
</ec:table>	
