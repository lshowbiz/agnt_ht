<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunMemberOrderListList.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunMemberOrderListList.heading"/></content>
<meta name="menu" content="JfiSunMemberOrderListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiSunMemberOrderList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiSunMemberOrderList.html"/>?strAction=addJfiSunMemberOrderList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiSunMemberOrderListListTable"
	items="jfiSunMemberOrderListList"
	var="jfiSunMemberOrderList"
	action="${pageContext.request.contextPath}/jfiSunMemberOrderLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiSunMemberOrderList('${jfiSunMemberOrderList.molId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="moId" title="jfiSunMemberOrderList.moId" />
    			<ec:column property="productId" title="jfiSunMemberOrderList.productId" />
    			<ec:column property="price" title="jfiSunMemberOrderList.price" />
    			<ec:column property="pv" title="jfiSunMemberOrderList.pv" />
    			<ec:column property="qty" title="jfiSunMemberOrderList.qty" />
    			<ec:column property="weight" title="jfiSunMemberOrderList.weight" />
    			<ec:column property="volume" title="jfiSunMemberOrderList.volume" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiSunMemberOrderList(molId){
   		<jecs:power powerCode="editJfiSunMemberOrderList">
					window.location="editJfiSunMemberOrderList.html?strAction=editJfiSunMemberOrderList&molId="+molId;
			</jecs:power>
		}

</script>
