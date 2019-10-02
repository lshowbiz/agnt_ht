<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdJdSendRecordList.title"/></title>
<content tag="heading"><jecs:locale key="jbdJdSendRecordList.heading"/></content>
<meta name="menu" content="JbdJdSendRecordMenu"/>

<c:set var="buttons">
		<%-- <jecs:power powerCode="addJbdJdSendRecord">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdJdSendRecord.html"/>?strAction=addJbdJdSendRecord'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power> --%>
		 <div class="new_searchBar"> 
				<button type="submit" class="btn btn_sele"  style="width:auto" >
								        <jecs:locale  key='operation.button.search'/>
				</button>
		</div>
		
</c:set>
<form name="jbdJdSendRecordlist" action="<c:url value='/jbdJdSendRecords.html'/>" >
<div class="searchBar">
   <div class="new_searchBar">
        <jecs:title  key="miMember.memberNo"/>
		<input name="userCode" id="userCode" value="${param.userCode}" cssClass="text medium"/>	
   </div>
   
   <div class="new_searchBar">
        <jecs:title key="common.startTime"/>
		<input name="startWeek" id="startWeek" value="${param.startWeek}" cssClass="text medium"/>	
   </div>
   
    <div class="new_searchBar">
        <jecs:title key="schedule.endTime"/>
		<input name="endWeek" id="endWeek" value="${param.endWeek}" cssClass="text medium"/>	
   </div>
   
   	<div class="new_searchBar">
		<label><jecs:locale key="miMember.freezestatus" />:</label>
		<jecs:list listCode="mimember.freezestatus" name="freezestatus" id="freezestatus"
			showBlankLine="false" value="${param.freezestatus}"
			defaultValue="" style="width: 165px;"/>
	</div>
   
<c:out value="${buttons}" escapeXml="false"/>

</div>

<ec:table 
	tableId="jbdJdSendRecordListTable"
	items="jbdJdSendRecordList"
	var="jbdJdSendRecord"
	action="${pageContext.request.contextPath}/jbdJdSendRecords.html"
	width="100%"
	method="post"
	form="jbdJdSendRecordlist"
	retrieveRowsCallback="limit"
	
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:exportXls fileName="jbdJdSendRecords.xls"></ec:exportXls>
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			<ec:column property="petName" title="miMember.petName" />
    			<ec:column property="userType" title="miMember.gradeType" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			       <jecs:code listCode="yun.usertype" value="${jbdJdSendRecord.userType}"/>
    			</ec:column>
    			<ec:column property="memberLevel" title="bdCalculatingSubDetail.cardType" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			      <jecs:code listCode="member.level" value="${jbdJdSendRecord.memberLevel}"/>
    			</ec:column>
    			<ec:column property="wweek" title="title.date" />
    			
    			<ec:column property="ykRefMoney" title="jbdJdSendRecord.ykRefMoney" />
    			<ec:column property="sendMoney" title="bdSendRecord.bonusMoney" />
    			<ec:column property="freezeStatus" title="miMember.freezestatus" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			      <jecs:code listCode="mimember.freezestatus" value="${jbdJdSendRecord.freezeStatus}"/>
    			</ec:column>
    			<ec:column property="status" title="schedule.state" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			       <jecs:code listCode="jbdjdsendrecord.status.send" value="${jbdJdSendRecord.status}"/>
    			</ec:column>
    			<ec:column property="sendDate" title="bdSendRecord.sendDate" />
    			<ec:column property="edit" title="title.view" sortable="false" styleClass="centerColumn" style="width:100px;">
					<a href="jbdJdSendRecords.html?strAction=jbdJdSendRecordList&userCodeDetail=${jbdJdSendRecord.jmiMember.userCode}&wweekDetail=${jbdJdSendRecord.wweek}"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
				</ec:column>
				</ec:row>

</ec:table>	
</form>
<script type="text/javascript">

   function editJbdJdSendRecord(id){
   		<jecs:power powerCode="editJbdJdSendRecord">
					window.location="editJbdJdSendRecord.html?strAction=editJbdJdSendRecord&id="+id;
			</jecs:power>
		}

</script>
