<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoCounterOrderDetailList.title"/></title>
<content tag="heading"><jecs:locale key="jpoCounterOrderDetailList.heading"/></content>
<meta name="menu" content="JpoCounterOrderDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpoCounterOrderDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoCounterOrderDetail.html"/>?strAction=addJpoCounterOrderDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoCounterOrderDetailListTable"
	items="jpoCounterOrderDetailList"
	var="jpoCounterOrderDetail"
	action="${pageContext.request.contextPath}/jpoCounterOrderDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoCounterOrderDetail('${jpoCounterOrderDetail.codNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="coNo" title="jpoCounterOrderDetail.coNo" />
    			<ec:column property="productId" title="jpoCounterOrderDetail.productId" />
    			<ec:column property="price" title="jpoCounterOrderDetail.price" />
    			<ec:column property="qty" title="jpoCounterOrderDetail.qty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoCounterOrderDetail(codNo){
   		<jecs:power powerCode="editJpoCounterOrderDetail">
					window.location="editJpoCounterOrderDetail.html?strAction=editJpoCounterOrderDetail&codNo="+codNo;
			</jecs:power>
		}

</script>
