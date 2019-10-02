<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiLinkRefList.title"/></title>
<content tag="heading"><jecs:locale key="jmiLinkRefList.heading"/></content>
<meta name="menu" content="JmiLinkRefMenu"/>

<c:set var="buttons">
		<wecs:power powerCode="addJmiLinkRef">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiLinkRef.html"/>?strAction=addJmiLinkRef'"
			     value="<jecs:locale key="button.add"/>"/>
		</wecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jmiLinkRefListTable"
	items="jmiLinkRefList"
	var="jmiLinkRef"
	action="${pageContext.request.contextPath}/jmiLinkRefs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/wecs/images/extremetable/*.gif">
				<ec:row onclick="javascript:editJmiLinkRef('${jmiLinkRef.memberNo}')">
    			<ec:column property="linkNo" title="jmiLinkRef.linkNo" />
    			<ec:column property="layerId" title="jmiLinkRef.layerId" />
    			<ec:column property="treeNo" title="jmiLinkRef.treeNo" />
    			<ec:column property="treeIndex" title="jmiLinkRef.treeIndex" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiLinkRef(memberNo){
   		<wecs:power powerCode="editJmiLinkRef">
					window.location="editJmiLinkRef.html?strAction=editJmiLinkRef&memberNo="+memberNo;
			</wecs:power>
		}

</script>
