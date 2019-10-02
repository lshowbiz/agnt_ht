<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSendNoteList.title" />
</title>
<content tag="heading">
<jecs:locale key="jbdSendNoteList.heading" />
</content>
<meta name="menu" content="JbdSendNoteMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<c:set var="buttons">
	<jecs:power powerCode="addJbdSendNote">
		<input type="button" class="button" style="margin-right: 5px"
			onclick="location.href='<c:url value="/editJbdSendNote.html"/>?strAction=addJbdSendNote'"
			value="<jecs:locale key="button.add"/>" />
	</jecs:power>
</c:set>

<form action="jbdSendNotes.html" method="post"
	name="bdSendRecordAllotForm1" id="bdSendRecordAllotForm1">

	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<div class="new_searchBar">
				<jecs:title key="miMember.memberNo" />
				<input name="userCode" type="text" value="${param.userCode }"
					/>
			</div>
			<div class="new_searchBar">
				<jecs:title key="bdSendRecord.sysUser.userName" />
				<input name="operaterCode" type="text" value="${param.operaterCode }"
					/>
			</div>
			<div class="new_searchBar">
				<jecs:title key="bdSendRecord.registerStatus" />
				<jecs:list name="registerStatus" listCode="bd.send.status"
					value="${param.registerStatus }" defaultValue=""
					showBlankLine="true" />
			</div>
			<div class="new_searchBar">
				<jecs:title key="bdSendRecord.remittanceBNum" />
				<select name="remittanceBNum">
					<option value="">
						<jecs:locale key="list.please.select" />
					</option>
					<c:forEach items="${bdOutwardBanks }" var="var">
						<c:if test="${param.remittanceBNum==var.bankCode }">
							<option value="${var.bankCode }" selected="selected">
								${var.bankCode }
							</option>
						</c:if>
						<c:if test="${param.remittanceBNum!=var.bankCode }">
							<option value="${var.bankCode }">
								${var.bankCode }
							</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<div class="new_searchBar">
				<jecs:title key="bdSendRecord.sendLateCause" />
				<jecs:list name="sendLateCause"
					listCode="bdsendrecord.sendlateremark" value="" defaultValue="0"
					showBlankLine="true" />
			</div>
			<div class="new_searchBar">
				<jecs:title key="bdSendRecord.operaterTime.st" />
				<input name="startOperaterTime" id="startOperaterTime" type="text" 
					value="${param.startOperaterTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
			</div>
			
			
			<div class="new_searchBar">
				<jecs:title key="bdSendRecord.operaterTime.end" />
				
				<input name="endOperaterTime" id="endOperaterTime" type="text" 
					value="${param.endOperaterTime }" style="cursor: pointer;;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
			</div>
			
			<div class="new_searchBar">	
				<jecs:title key="bd.app.time.st" />
				<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
			</div>
			
			
			<div class="new_searchBar">	
				<jecs:title key="bd.app.time.end" />
			
				<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
			</div>
			<div class="new_searchBar">		
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
					<jecs:locale key="operation.button.search"/>
				</button>
			</div>
		</c:if>

		<c:if test="${sessionScope.sessionLogin.userType=='M'}">
			<jecs:power powerCode="jfiMoney">
				<div class="new_searchBar">	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" onclick="location.href='<c:url value="jfiMoney.html"/>'">
						<jecs:locale key="member.new.num"/>
					</button>
				</div>
			</jecs:power>
		</c:if>
	</div>
</form>

<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	<c:set var="footTotalVar">
		<tr>
			<td align="right" class="footer" colspan="4">
				<jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal" />
			</td>
			<td class="footer" align="right">
				<b> <fmt:formatNumber value="${totalRemittanceMoney }"
						pattern="###,###,##0.00"></fmt:formatNumber> </b>
			</td>
			<td align="right" class="footer" colspan="8"></td>
		</tr>
	</c:set>
</c:if>

<ec:table tableId="jbdSendNoteListTable" items="jbdSendNoteList"
	var="jbdSendNote"
	action="${pageContext.request.contextPath}/jbdSendNotes.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif" footer="${footTotalVar }">
	<ec:row>

		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<jecs:power powerCode="jbdSendNoteExlExport">
				<ec:exportXls fileName="jbdSendNotes.xls"></ec:exportXls>
			</jecs:power>
		</c:if>

		<ec:column property="createTime" title="bd.app.time" />
		<ec:column property="jmiMember.userCode" title="miMember.memberNo"
			escapeAutoFormat="true" />

		<ec:column property="registerStatus" title="fiBankbookTemp.status"
			styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<c:if
				test="${jbdSendNote.registerStatus=='1'||jbdSendNote.registerStatus=='3' }">

				<jecs:locale key="bdSendRecord.unSend" />
			</c:if>
			<c:if test="${jbdSendNote.registerStatus=='2' }">
				<jecs:locale key="bdSendRecord.sended" />
			</c:if>
			<c:if test="${jbdSendNote.registerStatus=='4' }">
				<jecs:locale key="busi.bd.notSend" />
			</c:if>
		</ec:column>

		<ec:column property="sendDate" title="bdSendRecord.sendDate">
			<fmt:formatDate pattern="yyyy-MM-dd" value="${ jbdSendNote.sendDate}" />
		</ec:column>

		<ec:column property="remittanceMoney" title="bdSendRecord.bonusMoney"
			styleClass="numberColumn">
			<fmt:formatNumber value="${jbdSendNote.remittanceMoney}"
				type="number" pattern="###,###,##0.00" />
		</ec:column>

		<ec:column property="fee" title="bdSendRecord.costType05" />

		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<ec:column property="remittanceBNum"
				title="bdSendRecord.remittanceBNum" />
			<ec:column property="supplyTime" title="bdSendRecord.supplyTime" />
			<ec:column property="operaterSysUser.userCode"
				title="bdSendRecord.sysUser.userName" />
			<ec:column property="operaterTime" title="bdSendRecord.operaterTime" />
		</c:if>


		<ec:column property="sendLateCause" title="bdSendRecord.sendLateCause"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="bdsendrecord.sendlateremark"
				value="${jbdSendNote.sendLateCause}" />
		</ec:column>
		<ec:column property="sendLateRemark"
			title="bdSendRecord.sendLateRemark" />
		<ec:column property="sendRemark" title="bdSendRecord.sendRemark" />
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editJbdSendNote(id){
   		<jecs:power powerCode="editJbdSendNote">
					window.location="editJbdSendNote.html?strAction=editJbdSendNote&id="+id;
			</jecs:power>
		}

</script>
