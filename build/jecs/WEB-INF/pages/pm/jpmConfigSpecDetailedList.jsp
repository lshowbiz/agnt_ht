<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmConfigSpecDetailedList.title"/></title>
<content tag="heading"><jecs:locale key="jpmConfigSpecDetailedList.heading"/></content>
<meta name="menu" content="JpmConfigSpecDetailedMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpmConfigSpecDetailed">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmConfigSpecDetailed.html"/>?strAction=addJpmConfigSpecDetailed'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpmConfigSpecDetailedListTable"
	items="jpmConfigSpecDetailedList"
	var="jpmConfigSpecDetailed"
	action="${pageContext.request.contextPath}/jpmConfigSpecDetaileds.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigSpecDetailed('${jpmConfigSpecDetailed.specNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configNo" title="jpmConfigSpecDetailed.configNo" />
    			<ec:column property="productTemplateNo" title="jpmConfigSpecDetailed.productTemplateNo" />
    			<ec:column property="productTemplateName" title="jpmConfigSpecDetailed.productTemplateName" />
    			<ec:column property="complateAmount" title="jpmConfigSpecDetailed.complateAmount" />
    			<ec:column property="complateWeight" title="jpmConfigSpecDetailed.complateWeight" />
    			<ec:column property="createTime" title="jpmConfigSpecDetailed.createTime" />
    			<ec:column property="price" title="jpmConfigSpecDetailed.price" />
    			<ec:column property="productNum" title="jpmConfigSpecDetailed.productNum" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmConfigSpecDetailed(specNo){
   		<jecs:power powerCode="editJpmConfigSpecDetailed">
					window.location="editJpmConfigSpecDetailed.html?strAction=editJpmConfigSpecDetailed&specNo="+specNo;
			</jecs:power>
		}

</script>
