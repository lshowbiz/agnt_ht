<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointLogList.title"/></title>
<content tag="heading"><jecs:locale key="jbdTravelPointLogList.heading"/></content>
<meta name="menu" content="JbdTravelPointLogMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<form action="" method="get" name="bdSendRecordForm1" id="bdSendRecordForm1">
<div class="searchBar">
<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
	
<jecs:title key="fiBankbookTemp.dealType"/>	
         <jecs:list name="dealType" listCode="fibankbooktemp.dealtype" value="${param.dealType}" defaultValue="" showBlankLine="true"/>	
        
<jecs:title key="pd.createTime"/>
	 <input id="startTime" name="startTime" type="text" size="8" maxlength="10" value="${param.startTime }"/>
				<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "startTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script> - 
				<input id="endTime" name="endTime" type="text" size="8" maxlength="10" value="${param.endTime }"/>
				<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "endTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endTime", 
					singleClick    :    true
					}); 
				</script>

		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />


		<jecs:power powerCode="addJbdTravelPointLog2015">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdTravelPointLog2015.html"/>?strAction=addJbdTravelPointLog2015'"
			        value="<jecs:locale key="member.new.num"/>"/>
		</jecs:power>
</div>

</form>
<ec:table 
	tableId="jbdTravelPointLog2015ListTable"
	items="jbdTravelPointLog2015List"
	var="jbdTravelPointLog2015"
	action="${pageContext.request.contextPath}/jbdTravelPointLog2015s.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				
				<jecs:power powerCode="jbdTravelPointLog2015sExport">
				
	<ec:exportXls fileName="jbdTravelPointLog2015s.xls"></ec:exportXls>
				</jecs:power>
				
				
    			<ec:column property="userCode" title="miMember.memberNo" />
    			
		<ec:column property="usePoint" title="fibankbooktemp.dealtype.A" cell="com.joymain.jecs.util.ectable.EcTableCell">
			<c:if test="${jbdTravelPointLog2015.dealType=='A'}">${jbdTravelPointLog2015.usePoint}</c:if>
			<c:if test="${jbdTravelPointLog2015.dealType=='D'}">0</c:if>
		</ec:column>
		<ec:column property="usePoint" title="fibankbooktemp.dealtype.D" cell="com.joymain.jecs.util.ectable.EcTableCell">
			<c:if test="${jbdTravelPointLog2015.dealType=='D'}">${jbdTravelPointLog2015.usePoint}</c:if>
			<c:if test="${jbdTravelPointLog2015.dealType=='A'}">0</c:if>
		</ec:column>
		
		
    			<ec:column property="remark" title="customerRecord.remark" />
    			<ec:column property="createUser" title="customerRecord.createNo" />
    			<ec:column property="createTime" title="pd.createTime" />
				</ec:row>

</ec:table>	









