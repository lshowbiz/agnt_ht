<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiInvoiceDepositList.title"/></title>
<content tag="heading"><jecs:locale key="jfiInvoiceDepositList.heading"/></content>
<meta name="menu" content="JfiInvoiceDepositMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiInvoiceDeposit">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiInvoiceDeposit.html"/>?strAction=addJfiInvoiceDeposit'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiInvoiceDepositListTable"
	items="jfiInvoiceDepositList"
	var="jfiInvoiceDeposit"
	action="${pageContext.request.contextPath}/jfiInvoiceDeposits.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiInvoiceDeposit('${jfiInvoiceDeposit.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="invoiceId" title="jfiInvoiceDeposit.invoiceId" />
    			<ec:column property="depositId" title="jfiInvoiceDeposit.depositId" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiInvoiceDeposit(id){
   		<jecs:power powerCode="editJfiInvoiceDeposit">
					window.location="editJfiInvoiceDeposit.html?strAction=editJfiInvoiceDeposit&id="+id;
			</jecs:power>
		}

</script>
