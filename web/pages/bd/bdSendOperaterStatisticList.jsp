<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js">
	
</script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js">
	
</script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp">
	
</script>
<title><jecs:locale key="bdSendOperaterSearchList.title" />
</title>
<content tag="heading">
<jecs:locale key="bdSendOperaterSearchList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />
<form action="bdSendOperaterStatistic.html" method="get"
	name="bdSendOperaterStatisticForm1" id="bdSendOperaterStatisticForm1">
	<input type="hidden" name="strAction" value="${param.strAction }" />
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.sysUser.userName" />
			<input name="operaterCode" type="text" value="${param.operaterCode }"
				size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdBounsDeduct.wweek" />
			<input name="wweek" type="text" value="${param.wweek }" size="6" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.registerStatus" />
			<jecs:list name="registerStatus" listCode="bd.send.status"
				value="${param.registerStatus }" defaultValue="" showBlankLine="true" />
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
			<jecs:list name="sendLateCause" listCode="bdsendrecord.sendlateremark"
				value="" defaultValue="0" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.operaterTime" />
			<input name="startOperaterTime" id="startOperaterTime" type="text" 
					value="${param.startOperaterTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endOperaterTime" id="endOperaterTime" type="text" 
					value="${param.endOperaterTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" type="submit" class="btn btn_sele" onclick="loading('<jecs:locale key="button.loading"/>');">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>


		<%--<table width="100%" border="0">
	<tr>
		<th>
		</th>
		<th>
		</th>
		
		<th>
		</th>
		
		<th>
		</th>
		<th>
		</th>
		
		<th>
		</th>
		
		<th>
		</th>	
				<th>
<jecs:locale key="operation.button.search"/>
		</th>
		<th>&nbsp;</th>
	</tr>
	<tr>
			
		<td>	
		</td>
		<td>
		</td>
		<td>
		</td>
		<td>			
		</td>
		<td>	
		</td>
		<td>	
		</td>
		<td>		
		</td>
		<td>	
		</td>
		<td width="100%">&nbsp;</td>
	</tr>
</table>--%>
	</div>




</form>
<c:set var="footTotalVar">
	<tr>
		<td align="right" class="footer" colspan="4">
			<jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal" />
		</td>
		<td class="footer" align="right">
			<b> <fmt:formatNumber value="${totalRemittanceMoney }"
					pattern="###,###,##0.00"></fmt:formatNumber> </b>
		</td>
		<td align="right" class="footer" colspan="10"></td>
	</tr>
</c:set>

<div id="loading">
	<form action="bdSendOperaterStatistic.html" method="get"
		name="bdSendOperaterStatisticForm" id="bdSendOperaterStatisticForm">
		<ec:table tableId="bdSendOperaterStatisticListTable"
			items="bdSendOperaterStatisticList" var="bdSendOperaterStatistic"
			autoIncludeParameters="true" retrieveRowsCallback="limit"
			form="bdSendOperaterStatisticForm"
			action="${pageContext.request.contextPath}/bdSendOperaterStatistic.html"
			width="100%" rowsDisplayed="20" sortable="true"
			imagePath="./images/extremetable/*.gif" footer="${footTotalVar }">

			<ec:row>
				<ec:exportXls fileName="bdSendStatistic.xls" />
				<ec:column property="wweek" title="bdBounsDeduct.wweek">
					<jecs:weekFormat week="${bdSendOperaterStatistic.wweek }"
						weekType="w" />
				</ec:column>


				<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
				<ec:column property="name" title="bdCalculatingSubDetail.name" />

				<ec:column property="remittanceBNum"
					title="bdSendRecord.remittanceBNum" />
				<ec:column property="remittanceMoney"
					title="bdSendRecord.remittanceMoney" styleClass="numberColumn">
					<fmt:formatNumber
						value="${bdSendOperaterStatistic.remittanceMoney}" type="number"
						pattern="###,###,##0.00" />
				</ec:column>

				<ec:column property="bankbook" title="bdSendRecord.bnum" />
				<ec:column property="sendDate" title="bdSendRecord.sendDate">
					<fmt:formatDate value="${bdSendOperaterStatistic.sendDate }"
						pattern="yyyy-MM-dd" />
				</ec:column>
				<ec:column property="supplyTime" title="bdSendRecord.supplyTime" />


				<ec:column property="sendLateCause"
					title="bdSendRecord.sendLateCause">
					<jecs:code listCode="bdsendrecord.sendlateremark"
						value="${bdSendOperaterStatistic.sendLateCause }" />
				</ec:column>

				<ec:column property="operaterSysUser.userCode"
					title="bdSendRecord.sysUser.userName" />
				<ec:column property="operaterTime" title="bdSendRecord.operaterTime" />
				<ec:column property="sendRemark" title="bdSendRecord.sendRemark" />
				<ec:column property="sendLateRemark"
					title="bdSendRecord.sendLateRemark" />
			</ec:row>

		</ec:table>
	</form>
</div>


