<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdCheckstockOrderDetailList.title"/></title>
<content tag="heading"><jecs:locale key="pdCheckstockOrderDetailList.heading"/></content>
<meta name="menu" content="PdCheckstockOrderDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addPdCheckstockOrderDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editPdCheckstockOrderDetail.html"/>?strAction=addPdCheckstockOrderDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="pdCheckstockOrderDetailListTable"
	items="pdCheckstockOrderDetailList"
	var="pdCheckstockOrderDetail"
	action="${pageContext.request.contextPath}/pdCheckstockOrderDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editPdCheckstockOrderDetail('${pdCheckstockOrderDetail.uniNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productNo" title="pdCheckstockOrderDetail.productNo" />
    			<ec:column property="price" title="pdCheckstockOrderDetail.price" />
    			<ec:column property="sysQty" title="pdCheckstockOrderDetail.sysQty" />
    			<ec:column property="realQty" title="pdCheckstockOrderDetail.realQty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editPdCheckstockOrderDetail(uniNo){
   		<jecs:power powerCode="editPdCheckstockOrderDetail">
					window.location="editPdCheckstockOrderDetail.html?strAction=editPdCheckstockOrderDetail&uniNo="+uniNo;
			</jecs:power>
		}

</script>
