<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="bdSendRecordList.title"/></title>
<content tag="heading"><jecs:locale key="bdSendRecordList.heading"/></content>
<meta name="menu" content="BdSendRecordMenu"/>



<script type="text/javascript">
	function checkApplication(theForm){
			if(!confirm("<jecs:locale key="bd.send.Application.confirm"/>")){
				return false;
			}
			theForm.strAction.value="jbdApplication";
			if(isFormPosted()){
				theForm.submit();
			}
			
	}
	

</script>
<form action="" method="get" name="bdSendRecordForm1" id="bdSendRecordForm1">

		

<input type="hidden" id="strAction" name="strAction" value="bdSendRecords"/>


<div class="searchBar">


<jecs:power powerCode="jbdApplication">

	<c:if test="${not empty JbdBonusBalance }">
	<div class="new_searchBar">	
		<button name="search1" type="button" class="btn btn_sele" onclick="checkApplication(document.bdSendRecordForm1)" >
			<jecs:locale key="bd.send.Application"/>
		</button>
	</div>
	</c:if>
</jecs:power>

<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<div class="new_searchBar">	
		<jecs:title key="bdReconsumMoneyReport.company"/>
			<jecs:company name="companyCode" selected="${param.companyCode }"  prompt="" withAA="false"  />	
			</div>
		</c:if>
		
		<div class="new_searchBar">	
		<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode }" size="12"/>
			</div>
			
		<%--<jecs:title key="bdCalculatingSubDetail.name"/>
			<input name="name" type="text" value="${param.name}" size="12"/>--%>
		<div class="new_searchBar">	
		<jecs:locale key="bdBounsDeduct.wweek"/><jecs:title key="busi.common.ex"/>
			<input name="wweek" type="text" value="${param.wweek }" size="8"/>
			</div>
		
		<div class="new_searchBar">	
			<button name="search" type="submit" class="btn btn_sele" onclick="loading('<jecs:locale key="button.loading"/>');" >
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="exportExcel" type="submit" class="btn btn_sele" value="exportExcel" >
				<jecs:locale key="button.report"/>
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
		<jecs:locale key="operation.button.search"/>
		</th>
		<th>&nbsp;</th>
	</tr>
	<tr>

		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<td>
		</td>
		</c:if>
		
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


</c:if><%--
&nbsp;&nbsp;
	<c:if test="${not empty JbdBonusBalanceDate }">
		<jecs:title key="bd.app.time"/><fmt:formatDate value="${ JbdBonusBalanceDate}" pattern="yyyy-MM-dd hh:MM:dd"/>
	</c:if>
	&nbsp;&nbsp;
		<c:if test="${sessionScope.sessionLogin.companyCode=='CN' && sessionScope.sessionLogin.userType=='M' }">
			<jecs:title key="bd.e.point"/>${ bpoint}
		</c:if>--%>
</div>


</form>


<c:set var="footTotalVar">
<tr >
	<td id="frontTd" align="right" class="footer" colspan="5"><jecs:locale key="report.allTotal"/></td>
	<td class="footer" align="right">
	<b>
<fmt:formatNumber value="${bonusObj[0] }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td><td class="footer" align="right">
	<b>
<fmt:formatNumber value="${bonusObj[1] }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td><td class="footer" align="right">
	<b>
<fmt:formatNumber value="${bonusObj[2] }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td>
	<td align="right" class="footer" colspan="7"></td>
</tr>
</c:set>




<div id="loading">

<ec:table 

	
	tableId="bdSendRecordListTable"
	items="bdSendRecordList"
	var="bdSendRecord"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/bdSendRecords.html"
	width="100%"
	rowsDisplayed="20" sortable="${sortAble}" imagePath="./images/extremetable/*.gif" footer="${footTotalVar }">
				<ec:row>
				
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			
	
    			<ec:column property="petName" title="miMember.petName" />
	
    			
    			<ec:column property="cardType" title="bdSendRecord.cardType.old" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				
					<jecs:code listCode="bd.cardtype" value="${bdSendRecord.cardType }"/>
    			</ec:column>
    			
    			<ec:column property="memberLevel" title="bdSendRecord.cardType" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="member.level" value="${bdSendRecord.memberLevel }"/>
    			</ec:column>
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:weekFormat week="${ bdSendRecord.wweek}" weekType="w" />
    			</ec:column>
    			<ec:column property="remittanceMoney" title="bonus.pv.total" styleClass="numberColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			    <fmt:formatNumber value="${bdSendRecord.remittanceMoney+bdSendRecord.currentDev}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			<ec:column property="remittanceMoney" title="bdSendRecord.bonusMoney" styleClass="numberColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			    <fmt:formatNumber value="${bdSendRecord.remittanceMoney}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			<ec:column property="currentDev" title="bd.currentDev.bd" styleClass="numberColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			    <fmt:formatNumber value="${bdSendRecord.currentDev}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			
				<c:if test="${sessionScope.sessionLogin.companyCode=='CN' || sessionScope.sessionLogin.companyCode=='PH'}">
	    			<ec:column property="freezeStatus" title="miMember.freezestatus" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
	    				<jecs:code listCode="mimember.freezestatus" value="${bdSendRecord.freezeStatus}"/>
	    			</ec:column>
    			</c:if>
    			
    			
    			<ec:column property="registerStatus" title="fiBankbookTemp.status" styleClass="centerColumn" viewsAllowed="html"  cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<c:if test="${bdSendRecord.registerStatus=='1'||bdSendRecord.registerStatus=='3' }">
    					<font color='#CC0000'>
						<jecs:locale key="bdSendRecord.unSend"/>
						</font>
    				</c:if>
    				<c:if test="${bdSendRecord.registerStatus=='2' }">
    				<font color='#009900'>
						<jecs:locale key="bdSendRecord.sended"/>
					</font>
    				</c:if>
    				<c:if test="${bdSendRecord.registerStatus=='4' }">
						<jecs:locale key="busi.bd.notSend"/>
    				</c:if>
    			</ec:column>
    			
    			<ec:column property="registerStatus1" title="fiBankbookTemp.status" styleClass="centerColumn" viewsAllowed="xls" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<c:if test="${bdSendRecord.registerStatus=='1'||bdSendRecord.registerStatus=='3' }">
    				
						<jecs:locale key="bdSendRecord.unSend"/>
		
    				</c:if>
    				<c:if test="${bdSendRecord.registerStatus=='2' }">
    				
						<jecs:locale key="bdSendRecord.sended"/>
				
    				</c:if>
    				<c:if test="${bdSendRecord.registerStatus=='4' }">
						<jecs:locale key="busi.bd.notSend"/>
    				</c:if>
    			</ec:column>
    			
    			
    			<ec:column property="sendDate" title="bdSendRecord.sendDate" >

				<fmt:formatDate value="${bdSendRecord.sendDate}" pattern="yyyy-MM-dd"/>
	
    			</ec:column>
    			<ec:column property="sendLateCause" title="bdSendRecord.sendLateCause" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="bdsendrecord.sendlateremark" value="${bdSendRecord.sendLateCause}"/>
    			</ec:column>
    			<ec:column property="sendLateRemark" title="bdSendRecord.sendLateRemark" />
				<ec:column property="sendRemark" title="bdSendRecord.sendRemark" />
    			<ec:column property="1" title="title.view" viewsDenied="xls">
	    			<jecs:power powerCode="editJbdMemberLinkCalcHist">
						<img src="images/newIcons/search_16.gif" onclick="window.location.href='editJbdMemberLinkCalcHist.html?userCode=${bdSendRecord.jmiMember.userCode }&wweek=${bdSendRecord.wweek }';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
				</ec:column>
				</ec:row>
</ec:table>	

</div>
