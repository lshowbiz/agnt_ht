<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdReturnPurchaseDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdReturnPurchaseDetailList.heading"/></content>
<meta name="menu" content="PdReturnPurchaseDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdReturnPurchaseDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdReturnPurchaseDetail.html"/>?strAction=addPdReturnPurchaseDetail&rpNo=${rpNo}'"
			        value="<jecs:locale key="operation.button.add"/>"/>
		</jecs:power>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<ec:table 
	tableId="pdReturnPurchaseDetailListTable"
	items="pdReturnPurchaseDetailList"
	var="pdReturnPurchaseDetail"
	action="${pageContext.request.contextPath}/pdReturnPurchaseDetails.html"
	width="100%"
	rowsDisplayed="20" sorta./images/extremetablecs/images/extremetable/*.gif">
				<ec:row onclick="javascript:editPdReturnPurchaseDetail('${pdReturnPurchaseDetail.uniNo}')">
    			<ec:column property="companyNo" title="pdReturnPurchaseDetail.companyNo" />
    			<ec:column property="productNo" title="pdReturnPurchaseDetail.productNo" />
    			<ec:column property="price" title="pdReturnPurchaseDetail.price" />
    			<ec:column property="qty" title="pdReturnPurchaseDetail.qty" />
    			<ec:column property="rpNo" title="pdReturnPurchaseDetail.rpNo" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editPdReturnPurchaseDetail.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='uniNo' id='uniNo'/>
</form>

<script type="text/javascript">

   function editPdReturnPurchaseDetail(uniNo){
   		<jecs:power powerCode="editPdReturnPurchaseDetail">
					window.location="editPdReturnPurchaseDetail.html?strAction=editPdReturnPurchaseDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
