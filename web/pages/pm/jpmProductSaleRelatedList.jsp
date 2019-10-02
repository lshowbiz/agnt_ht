<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleRelatedList.title" />
</title>
<content tag="heading">
<jecs:locale key="jpmProductSaleRelatedList.heading" />
</content>
<meta name="menu" content="JpmProductSaleRelatedMenu" />

<%-- 
<c:set var="buttons">
	<jecs:power powerCode="addJpmProductSaleRelated">
		<input type="button" class="button" style="margin-ight: 5px"
			onclick="location.href='<c:url value="/editJpmProductSaleRelated.html"/>?strAction=addJpmProductSaleRelated&uniNo=${uniNo }&productNo=${productNo}'"
			value="<jecs:locale key="button.add"/>" />
	</jecs:power>
</c:set>
--%>
<c:set var="buttons">
	<jecs:power powerCode="addJpmProductSaleRelated">
		<button type="button" class="btn btn_ins" style="width:auto"
			onclick="location.href='<c:url value="/editJpmProductSaleRelated.html"/>?strAction=addJpmProductSaleRelated&uniNo=${uniNo }&productNo=${productNo}'"><jecs:locale key="button.add"/></button>
	</jecs:power>
</c:set>

<form name="frm" action="<c:url value='/jpmProductSaleRelateds.html'/>">
	<table width="100%" border="0">
		<tr>
			<td>
				
				<input type="hidden" id="strAction" name="strAction"
					value="${param.strAction}" /> 
				<input type="hidden" id="uniNo" name="uniNo"
					value="${param.uniNo}" /> 
				<input type="hidden" id="productNo" name="productNo"
					value="${param.productNo}" /> 
				<div class="searchBar">
					<div class="new_searchBar">
						<label><jecs:locale key="relationJpmProductSaleRelated.relationUniNo" />:</label>
						<input name="relatedproductNo" id="relatedproductNo"
							value="<c:out value='${param.relatedproductNo}'/>" cssClass="text medium" />
					</div>
					<div class="new_searchBar">
						<label><jecs:locale key="pmProduct.productName" />:</label>
						<input name="productName" id="productName"
							value="<c:out value='${param.productName}'/>"
							cssClass="text medium" />
					</div>
					<div class="new_searchBar">	
						<label><jecs:locale key="jpmProductSaleTeamType.state" />:</label>
						<jecs:list listCode="jmimemberteam.status" name="status" id="status"
							showBlankLine="false" value="${param.status}"
							defaultValue="" />
					</div>
					<div class="new_searchBar">	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
						<c:out value="${buttons}" escapeXml="false" />
					</div>
				</div>
			</td>	
		</tr>
	</table>
	<ec:table tableId="jpmProductSaleRelatedListTable"
		items="jpmProductSaleRelatedList" var="jpmProductSaleRelated"
		action="${pageContext.request.contextPath}/jpmProductSaleRelateds.html"
		width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif" form="frm">
		<ec:row>
			
			<!-- modify by lihao -->
			<%-- 
			<ec:column property="jpmProductSaleNew.companyCode" title="jpmProductSaleNew.companyCode" />
			<ec:column property="jpmProductSaleNew.jpmProduct.productNo"
				title="jpmProductSaleNew.productNo" />
			<ec:column property="relationJpmProductSaleNew.jpmProduct.productNo"
				title="relationJpmProductSaleRelated.relationUniNo" />
			<ec:column property="relationJpmProductSaleNew.productName"
				title="relationJpmProductSaleRelated.productName" />
			<ec:column property="status" title="jpmProductSaleRelated.status">
				<jecs:code listCode="jmimemberteam.status"
					value="${jpmProductSaleRelated.status}" />
			</ec:column>
			--%>
			<!-- 分公司 -->
			<ec:column property="companycode" title="jpmProductSaleNew.companyCode" />
			<!-- 商品编码 -->
			<ec:column property="productno"
				title="jpmProductSaleNew.productNo" />
			<!-- 相关商品编码 -->
			<ec:column property="relationproductno"
				title="relationJpmProductSaleRelated.relationUniNo" />
			<!-- 相关商品名称 -->
			<ec:column property="relationproductname"
				title="relationJpmProductSaleRelated.productName" />
			<!-- 相关商品状态 -->
			<ec:column property="status" title="jpmProductSaleRelated.status">
				<jecs:code listCode="jmimemberteam.status"
					value="${jpmProductSaleRelated.status}" />
			</ec:column>
			<ec:column property="edit" title="title.operation" sortable="false"
				viewsAllowed="html">
				<img src="<c:url value='/images/icons/editIcon.gif'/>"
					onclick="javascript:editJpmProductSaleRelated('${jpmProductSaleRelated.id}')"
					style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
			</ec:column>
		</ec:row>
	
	</ec:table>
</form>

<script type="text/javascript">

   function editJpmProductSaleRelated(id){
   		<jecs:power powerCode="editJpmProductSaleRelated">
					window.location="editJpmProductSaleRelated.html?strAction=editJpmProductSaleRelated&id="+id;
			</jecs:power>
		}

</script>
