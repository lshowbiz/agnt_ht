<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdExchangeOrderDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdExchangeOrderDetailList.heading"/></content>
<meta name="menu" content="PdExchangeOrderDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdExchangeOrderDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdExchangeOrderDetail.html"/>?strAction=addPdExchangeOrderDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdExchangeOrderDetailListTable"
	items="pdExchangeOrderDetailList"
	var="pdExchangeOrderDetail"
	action="${pageContext.request.contextPath}/pdExchangeOrderDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdExchangeOrderDetail('${pdExchangeOrderDetail.uniNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="pdExchangeOrderDetail.productNo" />
    			<ec:column property="price" title="pdExchangeOrderDetail.price" />
    			<ec:column property="qty" title="pdExchangeOrderDetail.qty" />
    			<ec:column property="eoNo" title="pdExchangeOrderDetail.eoNo" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdExchangeOrderDetail(uniNo){
   		<jecs:power powerCode="editPdExchangeOrderDetail">
					window.location="editPdExchangeOrderDetail.html?strAction=editPdExchangeOrderDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
