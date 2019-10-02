<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSendRecordNoteList.title"/></title>
<content tag="heading"><jecs:locale key="jbdSendRecordNoteList.heading"/></content>
<meta name="menu" content="JbdSendRecordNoteMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdSendRecordNote">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdSendRecordNote.html"/>?strAction=addJbdSendRecordNote'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdSendRecordNoteListTable"
	items="jbdSendRecordNoteList"
	var="jbdSendRecordNote"
	action="${pageContext.request.contextPath}/jbdSendRecordNotes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdSendRecordNote('${jbdSendRecordNote.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wyear" title="jbdSendRecordNote.wyear" />
    			<ec:column property="wmonth" title="jbdSendRecordNote.wmonth" />
    			<ec:column property="wweek" title="jbdSendRecordNote.wweek" />
    			<ec:column property="companyCode" title="jbdSendRecordNote.companyCode" />
    			<ec:column property="userCode" title="jbdSendRecordNote.userCode" />
    			<ec:column property="recommendNo" title="jbdSendRecordNote.recommendNo" />
    			<ec:column property="linkNo" title="jbdSendRecordNote.linkNo" />
    			<ec:column property="name" title="jbdSendRecordNote.name" />
    			<ec:column property="petName" title="jbdSendRecordNote.petName" />
    			<ec:column property="cardType" title="jbdSendRecordNote.cardType" />
    			<ec:column property="bank" title="jbdSendRecordNote.bank" />
    			<ec:column property="bankaddress" title="jbdSendRecordNote.bankaddress" />
    			<ec:column property="bankbook" title="jbdSendRecordNote.bankbook" />
    			<ec:column property="bankcard" title="jbdSendRecordNote.bankcard" />
    			<ec:column property="exitDate" title="jbdSendRecordNote.exitDate" />
    			<ec:column property="sendLateCause" title="jbdSendRecordNote.sendLateCause" />
    			<ec:column property="sendLateRemark" title="jbdSendRecordNote.sendLateRemark" />
    			<ec:column property="remittanceMoney" title="jbdSendRecordNote.remittanceMoney" />
    			<ec:column property="remittanceBNum" title="jbdSendRecordNote.remittanceBNum" />
    			<ec:column property="operaterCode" title="jbdSendRecordNote.operaterCode" />
    			<ec:column property="operaterTime" title="jbdSendRecordNote.operaterTime" />
    			<ec:column property="sendDate" title="jbdSendRecordNote.sendDate" />
    			<ec:column property="registerStatus" title="jbdSendRecordNote.registerStatus" />
    			<ec:column property="reissueStatus" title="jbdSendRecordNote.reissueStatus" />
    			<ec:column property="supplyTime" title="jbdSendRecordNote.supplyTime" />
    			<ec:column property="sendTrace" title="jbdSendRecordNote.sendTrace" />
    			<ec:column property="sendRemark" title="jbdSendRecordNote.sendRemark" />
    			<ec:column property="sendMoney" title="jbdSendRecordNote.sendMoney" />
    			<ec:column property="active" title="jbdSendRecordNote.active" />
    			<ec:column property="status" title="jbdSendRecordNote.status" />
    			<ec:column property="memberType" title="jbdSendRecordNote.memberType" />
    			<ec:column property="startWeek" title="jbdSendRecordNote.startWeek" />
    			<ec:column property="validWeek" title="jbdSendRecordNote.validWeek" />
    			<ec:column property="freezeStatus" title="jbdSendRecordNote.freezeStatus" />
    			<ec:column property="beforeFreezeStatus" title="jbdSendRecordNote.beforeFreezeStatus" />
    			<ec:column property="totalDev" title="jbdSendRecordNote.totalDev" />
    			<ec:column property="deductedDev" title="jbdSendRecordNote.deductedDev" />
    			<ec:column property="currentDev" title="jbdSendRecordNote.currentDev" />
    			<ec:column property="leaderDev" title="jbdSendRecordNote.leaderDev" />
    			<ec:column property="leaderDevPv" title="jbdSendRecordNote.leaderDevPv" />
    			<ec:column property="sendStatusDev" title="jbdSendRecordNote.sendStatusDev" />
    			<ec:column property="sendDateDev" title="jbdSendRecordNote.sendDateDev" />
    			<ec:column property="sendUserDev" title="jbdSendRecordNote.sendUserDev" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdSendRecordNote(id){
   		<jecs:power powerCode="editJbdSendRecordNote">
					window.location="editJbdSendRecordNote.html?strAction=editJbdSendRecordNote&id="+id;
			</jecs:power>
		}

</script>
