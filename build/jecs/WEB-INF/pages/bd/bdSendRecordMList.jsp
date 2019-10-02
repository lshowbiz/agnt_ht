<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="bdSendRecordList.title"/></title>
<content tag="heading"><jecs:locale key="bdSendRecordList.heading"/></content>
<meta name="menu" content="BdSendRecordMenu"/>


<form action="" method="get" name="bdSendRecordForm1" id="bdSendRecordForm1">

<c:if test="${sessionScope.sessionLogin.userType=='C'}">
<div class="searchBar">&nbsp;&nbsp;
		<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode }" size="12"/>
	
		<input name="search" type="submit" class="button" onclick="loading('<jecs:locale key="button.loading"/>');" value="<jecs:locale key="operation.button.search"/>"/>
		
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
</div>

</c:if>
</form>

<div id="loading">

<ec:table 

	
	tableId="bdSendRecordListTable"
	items="bdSendRecordList"
	var="bdSendRecord"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/bdSendRecordMs.html"
	width="100%"
	rowsDisplayed="20" sortable="${sortAble}" imagePath="./images/extremetable/*.gif"
	>
				<ec:row>
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			
	
    			<ec:column property="petName" title="miMember.petName" />
	
    			
    			<ec:column property="cardType" title="bdSendRecord.cardType">
    				
					<c:if test="${bdSendRecord.cardType=='0' }">
    					<font color='#999999'><jecs:code listCode="bd.cardtype" value="0"/></font>
    				</c:if>
					<c:if test="${bdSendRecord.cardType=='1' }">
    					<font color='#009900'><jecs:code listCode="bd.cardtype" value="1"/></font>
    				</c:if>
    				<c:if test="${bdSendRecord.cardType=='2' }">
    					<font color='#006666'><jecs:code listCode="bd.cardtype" value="2"/></font>
    				</c:if>
    				<c:if test="${bdSendRecord.cardType=='3' }">
    					<font color='#0033CC'><jecs:code listCode="bd.cardtype" value="3"/></font>
    				</c:if>
    				<c:if test="${bdSendRecord.cardType=='4' }">
    					<font color='#CC0000'><jecs:code listCode="bd.cardtype" value="4"/></font>
    				</c:if>
    			</ec:column>
    			
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" />
    			<ec:column property="remittanceMoney" title="bdSendRecord.bonusMoney" styleClass="numberColumn">
    			    <fmt:formatNumber value="${bdSendRecord.remittanceMoney}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			
    			<ec:column property="registerStatus" title="fiBankbookTemp.status" styleClass="centerColumn">
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
    			
    			<ec:column property="sendDate" title="bdSendRecord.sendDate" >

				<fmt:formatDate value="${bdSendRecord.sendDate}" pattern="yyyy-MM-dd"/>
	
    			</ec:column>
    			<ec:column property="sendLateCause" title="bdSendRecord.sendLateCause" >
    				<jecs:code listCode="bdsendrecord.sendlateremark" value="${bdSendRecord.sendLateCause}"/>
    			</ec:column>
    			<ec:column property="sendLateRemark" title="bdSendRecord.sendLateRemark" />
				<ec:column property="sendRemark" title="bdSendRecord.sendRemark" />
    			<%--<ec:column property="1" title="title.view" >
	    			<jecs:power powerCode="editJbdMemberLinkCalcHist">
						<img src="images/newIcons/search_16.gif" onclick="window.location.href='editJbdMemberLinkCalcHist.html?userCode=${bdSendRecord.jmiMember.userCode }&wweek=${bdSendRecord.wweek }';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
				</ec:column>--%>
				</ec:row>
</ec:table>	

</div>
