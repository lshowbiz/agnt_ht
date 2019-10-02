<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderListList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderListList.heading"/></content>
<meta name="menu" content="JpoMemberOrderListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpoMemberOrderList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoMemberOrderList.html"/>?strAction=addJpoMemberOrderList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoMemberOrderListListTable"
	items="jpoMemberOrderListList"
	var="jpoMemberOrderList"
	action="${pageContext.request.contextPath}/jpoMemberOrderLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJpoMemberOrderList('${jpoMemberOrderList.molId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="moId" title="jpoMemberOrderList.moId" />
    			<ec:column property="productId" title="jpoMemberOrderList.productId" />
    			<ec:column property="price" title="jpoMemberOrderList.price" />
    			<ec:column property="pv" title="jpoMemberOrderList.pv" />
    			<ec:column property="qty" title="jpoMemberOrderList.qty" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoMemberOrderList(molId){
   		<jecs:power powerCode="editJpoMemberOrderList">
					window.location="editJpoMemberOrderList.html?strAction=editJpoMemberOrderList&molId="+molId;
			</jecs:power>
		}

</script>
