<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdYkJiandianListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdYkJiandianListList.heading"/></content>
<meta name="menu" content="JbdYkJiandianListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdYkJiandianList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdYkJiandianList.html"/>?strAction=addJbdYkJiandianList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdYkJiandianListListTable"
	items="jbdYkJiandianListList"
	var="jbdYkJiandianList"
	action="${pageContext.request.contextPath}/jbdYkJiandianLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdYkJiandianList('${jbdYkJiandianList.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wweek" title="jbdYkJiandianList.wweek" />
    			<ec:column property="userCode" title="jbdYkJiandianList.userCode" />
    			<ec:column property="petName" title="jbdYkJiandianList.petName" />
    			<ec:column property="userType" title="jbdYkJiandianList.userType" />
    			<ec:column property="memberLevel" title="jbdYkJiandianList.memberLevel" />
    			<ec:column property="ykRefMoney" title="jbdYkJiandianList.ykRefMoney" />
    			<ec:column property="comTime" title="jbdYkJiandianList.comTime" />
    			<ec:column property="reuserCode" title="jbdYkJiandianList.reuserCode" />
    			<ec:column property="nlevel" title="jbdYkJiandianList.nlevel" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdYkJiandianList(id){
   		<jecs:power powerCode="editJbdYkJiandianList">
					window.location="editJbdYkJiandianList.html?strAction=editJbdYkJiandianList&id="+id;
			</jecs:power>
		}

</script>
