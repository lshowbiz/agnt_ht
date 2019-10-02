<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiRecommendRefList.title"/></title>
<content tag="heading"><jecs:locale key="jmiRecommendRefList.heading"/></content>
<meta name="menu" content="JmiRecommendRefMenu"/>

<c:set var="buttons">
		<wecs:power powerCode="addJmiRecommendRef">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJmiRecommendRef.html"/>?strAction=addJmiRecommendRef'"
			     value="<jecs:locale key="button.add"/>"/>
		</wecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jmiRecommendRefListTable"
	items="jmiRecommendRefList"
	var="jmiRecommendRef"
	action="${pageContext.request.contextPath}/jmiRecommendRefs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/wecs/images/extremetable/*.gif">
				<ec:row onclick="javascript:editJmiRecommendRef('${jmiRecommendRef.memberNo}')">
    			<ec:column property="recommendNo" title="jmiRecommendRef.recommendNo" />
    			<ec:column property="layerId" title="jmiRecommendRef.layerId" />
    			<ec:column property="treeNo" title="jmiRecommendRef.treeNo" />
    			<ec:column property="treeIndex" title="jmiRecommendRef.treeIndex" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJmiRecommendRef(memberNo){
   		<wecs:power powerCode="editJmiRecommendRef">
					window.location="editJmiRecommendRef.html?strAction=editJmiRecommendRef&memberNo="+memberNo;
			</wecs:power>
		}

</script>
