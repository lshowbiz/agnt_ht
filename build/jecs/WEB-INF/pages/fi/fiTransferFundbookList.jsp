<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiTransferFundbookList.title"/></title>
<content tag="heading"><jecs:locale key="fiTransferFundbookList.heading"/></content>
<meta name="menu" content="FiTransferFundbookMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addFiTransferFundbook">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiTransferFundbook.html"/>?strAction=addFiTransferFundbook'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiTransferFundbookListTable"
	items="fiTransferFundbookList"
	var="fiTransferFundbook"
	action="${pageContext.request.contextPath}/fiTransferFundbooks.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiTransferFundbook('${fiTransferFundbook.taId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="transferUserCode" title="fiTransferFundbook.transferUserCode" />
    			<ec:column property="destinationUserCode" title="fiTransferFundbook.destinationUserCode" />
    			<ec:column property="money" title="fiTransferFundbook.money" />
    			<ec:column property="notes" title="fiTransferFundbook.notes" />
    			<ec:column property="status" title="fiTransferFundbook.status" />
    			<ec:column property="createrCode" title="fiTransferFundbook.createrCode" />
    			<ec:column property="createrName" title="fiTransferFundbook.createrName" />
    			<ec:column property="createTime" title="fiTransferFundbook.createTime" />
    			<ec:column property="checkerCode" title="fiTransferFundbook.checkerCode" />
    			<ec:column property="checkerName" title="fiTransferFundbook.checkerName" />
    			<ec:column property="checkeTime" title="fiTransferFundbook.checkeTime" />
    			<ec:column property="dealDate" title="fiTransferFundbook.dealDate" />
    			<ec:column property="bankbookType" title="fiTransferFundbook.bankbookType" />
    			<ec:column property="transferType" title="fiTransferFundbook.transferType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiTransferFundbook(taId){
   		<jecs:power powerCode="editFiTransferFundbook">
					window.location="editFiTransferFundbook.html?strAction=editFiTransferFundbook&taId="+taId;
			</jecs:power>
		}

</script>
