<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBankbookTempList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBankbookTempList.heading" />
</content>
<meta name="menu" content="JfiBankbookTempMenu" />

<form action="posNumCheckList.html" method="get"
	name="jfiBankbookTempSearchForm" id="jfiBankbookTempSearchForm">
	<input type="hidden" name="ischeckall" value="${ischeckall }" />
	<div class="searchBar">
		<div class="new_searchBar">	
			<jecs:title key="fiBankbookTemp.billpospayNum" />
			<input name="billpospayNum" type="text"
				value="${param.billpospayNum }" size="8" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="checkall" class="btn btn_ins" type="button" onclick="tocheckall()">
				<jecs:locale key="button.checkall"/>
			</button>
		</div>
	</div>
</form>



<table class='detail' width="100%">
	<tr
		style="background-color: #E6E6E6; font-size: 9pt; vertical-align: top;">

		<td>
			<jecs:locale key="jfiPosImport.posNo" />
		</td>
		<td>
			<jecs:locale key="label.member.or.agent.code" />
		</td>
		<td>
			<jecs:locale key="fiBankbookJournal.money" />
		</td>
		<td>
			<jecs:locale key="fiBankbookTemp.status" />
		</td>
		<td>
			<jecs:locale key="pd.confirmUserNo" />
		</td>
		<td>
			<jecs:locale key="logType.BC" />
		</td>
	</tr>

	<c:if test="${not empty jfiBankbookTempList1}">
		<c:forEach items="${jfiBankbookTempList1}" var="jfiBankbookTemp"
			varStatus='status'>

			<tr>

				<td>
					${jfiBankbookTemp.billpospayNum }
				</td>
				<td>
					${jfiBankbookTemp.sysUser.userCode }
				</td>
				<td>
					${jfiBankbookTemp.money }
				</td>
				<td>
					<jecs:code listCode="fiagentpayadvice.status"
						value="${jfiBankbookTemp.status}" />
				</td>
				<td>
					${jfiBankbookTemp.checkerName }
				</td>
				<td>
					${jfiBankbookTemp.checkeTime }
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${not empty jfiBankbookTempList}">
		<c:forEach items="${jfiBankbookTempList}" var="jfiBankbookTemp"
			varStatus='status'>

			<tr>

				<td>
					${jfiBankbookTemp.pos_no }
				</td>
				<td>
					${jfiBankbookTemp.user_code }
				</td>
				<td>
					${jfiBankbookTemp.amount }
				</td>
				<td>
					<jecs:code listCode="fiagentpayadvice.status"
						value="${jfiBankbookTemp.status}" />
				</td>
				<td>
					${jfiBankbookTemp.check_user }
				</td>
				<td>
					${jfiBankbookTemp.check_time }
				</td>
			</tr>
		</c:forEach>
	</c:if>

</table>

<!-- 
<ec:table 
	tableId="jfiBankbookTempListTable"
	items="jfiBankbookTempList"
	var="jfiBankbookTemp"

	action="${pageContext.request.contextPath}/posNumCheckList.html"
	width="100%"
	rowsDisplayed="20" imagePath="./images/extremetable/*.gif" >
	<ec:row highlightRow="false">
		
		<ec:column property="pos_no" title="jfiPosImport.posNo" >
		</ec:column>
		<ec:column property="user_code" title="label.member.or.agent.code"></ec:column>
		<ec:column property="amount" title="fiBankbookJournal.money" >
    				<fmt:formatNumber value="${jfiBankbookTemp.amount}" type="number" pattern="###,###,##0.00"/>
    	</ec:column>
    	
    	<ec:column property="status" title="fiBankbookTemp.status" width="50">
			<jecs:code listCode="fiagentpayadvice.status" value="${jfiBankbookTemp.status}"/>
		</ec:column>
		<ec:column property="check_user" title="pd.confirmUserNo" width="100" >
			
		</ec:column>
		<ec:column property="check_time" title="logType.BC" width="100" >

		</ec:column>
	</ec:row>

</ec:table>	
 -->

<script type="text/javascript">
	function tocheckall(){
		
		if(window.confirm('<jecs:locale key="checkall.waiting"/>')){
             
            var theForm = document.getElementById("jfiBankbookTempSearchForm")
			theForm.ischeckall.value="Y";
			//document.getElementById("checkall").disabled=true;
			theForm.submit();
        }
	}
</script>
