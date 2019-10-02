<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductWineTemplateSubList.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductWineTemplateSubList.heading"/></content>
<meta name="menu" content="JpmProductWineTemplateSubMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpmProductWineTemplateSub">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmProductWineTemplateSub.html"/>?strAction=addJpmProductWineTemplateSub'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpmProductWineTemplateSubListTable"
	items="jpmProductWineTemplateSubList"
	var="jpmProductWineTemplateSub"
	action="${pageContext.request.contextPath}/jpmProductWineTemplateSubs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmProductWineTemplateSub('${jpmProductWineTemplateSub.subNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productTemplateNo" title="jpmProductWineTemplateSub.productTemplateNo" />
    			<ec:column property="subName" title="jpmProductWineTemplateSub.subName" />
    			<ec:column property="price" title="jpmProductWineTemplateSub.price" />
    			<ec:column property="specification" title="jpmProductWineTemplateSub.specification" />
    			<ec:column property="num" title="jpmProductWineTemplateSub.num" />
    			<ec:column property="unit" title="jpmProductWineTemplateSub.unit" />
    			<ec:column property="lossRatio" title="jpmProductWineTemplateSub.lossRatio" />
    			<ec:column property="isMainMaterial" title="jpmProductWineTemplateSub.isMainMaterial" />
    			<ec:column property="isSendMaterial" title="jpmProductWineTemplateSub.isSendMaterial" />
    			<ec:column property="isDelegateOut" title="jpmProductWineTemplateSub.isDelegateOut" />
    			<ec:column property="isFeatureItem" title="jpmProductWineTemplateSub.isFeatureItem" />
    			<ec:column property="isMustSelected" title="jpmProductWineTemplateSub.isMustSelected" />
    			<ec:column property="isDefaultSelected" title="jpmProductWineTemplateSub.isDefaultSelected" />
    			<ec:column property="isNumChange" title="jpmProductWineTemplateSub.isNumChange" />
    			<ec:column property="numMax" title="jpmProductWineTemplateSub.numMax" />
    			<ec:column property="numMin" title="jpmProductWineTemplateSub.numMin" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmProductWineTemplateSub(subNo){
   		<jecs:power powerCode="editJpmProductWineTemplateSub">
					window.location="editJpmProductWineTemplateSub.html?strAction=editJpmProductWineTemplateSub&subNo="+subNo;
			</jecs:power>
		}

</script>
