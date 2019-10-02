
<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jbdTravelPointDetailList.title"/></title>
<content tag="heading"><jecs:locale key="jbdTravelPointDetailList.heading"/></content>
<meta name="menu" content="JbdTravelPointDetailMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="" method="get" name="bdSendRecordForm1" id="bdSendRecordForm1">
<div class="searchBar">&nbsp;&nbsp;
<c:if test="${sessionScope.sessionLogin.userType=='C'}">
<input type="hidden" id="strAction" name="strAction" value="jbdTravelPointDetail2014s"/>
<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
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
		</c:if>
		
		<jecs:power powerCode="addJbdTravelPointDetail2014">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdTravelPointDetail2014.html"/>?strAction=addJbdTravelPointDetail2014'"
			        value="<jecs:locale key="member.new.num"/>"/>
		</jecs:power>
		
</div>



</form>




	<c:if test="${sessionScope.sessionLogin.userType=='M'}">
<table class='detail' width="100%">


    <tr><th>
        奖励积分：
    </th>
    <td align="left">
        ${jbdTravelPoint2014.total }
    </td></tr>
    

    <%--<tr><th>
        活动考核期间内最高奖衔：
    </th>
    <td align="left">
       <jecs:code listCode="pass.star.zero" value="${jbdTravelPoint2014.passStar }"/>
    </td></tr>--%>

</table>

	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdMemberLinkCalcHist2014s"
		var="jbdMemberLinkCalcHist2014"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			
					<ec:column property="w_week" title="bdBounsDeduct.wweek" >
						<jecs:monthFormat monthType="w" month="${jbdMemberLinkCalcHist2014.w_week }"></jecs:monthFormat>
					</ec:column>
					<ec:column property="pass_star" title="jbdMemberLinkCalcHist.passStar" >
    					<jecs:code listCode="pass.star.zero" value="${jbdMemberLinkCalcHist2014.pass_star}"/>
					</ec:column>
					
		</ec:row>
	</ec:table>	




	</c:if>



<ec:table 
	tableId="jbdTravelPointDetail2014ListTable"
	items="jbdTravelPointDetail2014List"
	var="jbdTravelPointDetail2014"
	action="${pageContext.request.contextPath}/jbdTravelPointDetail2014s.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<jecs:power powerCode="jbdTravelPointDetail2014sExport">
	<ec:exportXls fileName="jbdTravelPointDetail2014s.xls"></ec:exportXls>
				</jecs:power>
				
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="travelType" title="bdBonusStatReport.item" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="bd.traveltype.2014" value="${jbdTravelPointDetail2014.travelType }"/>
    			</ec:column>
    			<ec:column property="usePoint" title="fiCoinLog.coin" />
    			<ec:column property="createUser" title="customerRecord.createNo" />
    			<ec:column property="createTime" title="pd.createTime" />
    			
    			
					<jecs:power powerCode="deleteJbdTravelPointDetail2014">
    			<ec:column property="1" title="title.operation" sortable="false" width="150px" viewsDenied="xls">
					
						<a href="jbdTravelPointDetail2014s.html?id=${jbdTravelPointDetail2014.id}&strAction=deleteJbdTravelPointDetail2014" onclick="return window.confirm('<jecs:locale key="amMessage.confirmdelete"/>');"><img border="0" src="images/icons/w.gif" alt="<jecs:locale key="operation.button.delete"/>" /></a>
					
				</ec:column>
    				</jecs:power>
    			
    			
				</ec:row>

</ec:table>	





























