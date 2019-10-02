<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunOrderListList.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunOrderListList.heading"/></content>
<meta name="menu" content="JfiSunOrderListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiSunOrderList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiSunOrderList.html"/>?strAction=addJfiSunOrderList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiSunOrderListListTable"
	items="jfiSunOrderListList"
	var="jfiSunOrderList"
	action="${pageContext.request.contextPath}/jfiSunOrderLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiSunOrderList('${jfiSunOrderList.molId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productId" title="jfiSunOrderList.productId" />
    			<ec:column property="price" title="jfiSunOrderList.price" />
    			<ec:column property="pv" title="jfiSunOrderList.pv" />
    			<ec:column property="qty" title="jfiSunOrderList.qty" />
    			<ec:column property="weight" title="jfiSunOrderList.weight" />
    			<ec:column property="volume" title="jfiSunOrderList.volume" />
    			<ec:column property="sprice" title="jfiSunOrderList.sprice" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiSunOrderList(molId){
   		<jecs:power powerCode="editJfiSunOrderList">
					window.location="editJfiSunOrderList.html?strAction=editJfiSunOrderList&molId="+molId;
			</jecs:power>
		}

</script>
