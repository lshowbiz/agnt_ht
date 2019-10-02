<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductWineTemplateList.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductWineTemplateList.heading"/></content>
<meta name="menu" content="JpmProductWineTemplateMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpmProductWineTemplate">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmProductWineTemplate.html"/>?strAction=addJpmProductWineTemplate'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpmProductWineTemplateListTable"
	items="jpmProductWineTemplateList"
	var="jpmProductWineTemplate"
	action="${pageContext.request.contextPath}/jpmProductWineTemplates.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmProductWineTemplate('${jpmProductWineTemplate.productTemplateNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="productTemplateName" title="jpmProductWineTemplate.productTemplateName" />
    			<ec:column property="productNo" title="jpmProductWineTemplate.productNo" />
    			<ec:column property="productName" title="jpmProductWineTemplate.productName" />
    			<ec:column property="isDefault" title="jpmProductWineTemplate.isDefault" >
                    <jecs:code listCode="jpmproductwinetemplate.isdefault" value="${jpmProductWineTemplate.isDefault}"/>
                </ec:column>
    			<ec:column property="isInvalid" title="jpmProductWineTemplate.isInvalid" >
                    <jecs:code listCode="jpmproductwinetemplate.isinvalid" value="${jpmProductWineTemplate.isInvalid}"/>
                </ec:column>
    			<ec:column property="memo" title="jpmProductWineTemplate.memo" />
    			<%-- <ec:column property="createTime" title="jpmProductWineTemplate.createTime" /> --%>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmProductWineTemplate(productTemplateNo){
   		<jecs:power powerCode="editJpmProductWineTemplate">
					window.location="editJpmProductWineTemplate.html?strAction=editJpmProductWineTemplate&productTemplateNo="+productTemplateNo;
			</jecs:power>
		}

</script>
