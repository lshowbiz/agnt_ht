<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdMailReceiptList.title"/></title>
<content tag="heading"><jecs:locale key="pdMailReceiptList.heading"/></content>
<meta name="menu" content="PdMailReceiptMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdMailReceipt">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdMailReceipt.html"/>?strAction=addPdMailReceipt'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdMailReceiptListTable"
	items="pdMailReceiptList"
	var="pdMailReceipt"
	action="${pageContext.request.contextPath}/pdMailReceipts.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdMailReceipt('${pdMailReceipt.mailReceiptId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="statusId" title="pdMailReceipt.statusId" />
    			<ec:column property="other" title="pdMailReceipt.other" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdMailReceipt(mailReceiptId){
   		<jecs:power powerCode="editPdMailReceipt">
					window.location="editPdMailReceipt.html?strAction=editPdMailReceipt&mailReceiptId="+mailReceiptId;
			</jecs:power>
		}

</script>
