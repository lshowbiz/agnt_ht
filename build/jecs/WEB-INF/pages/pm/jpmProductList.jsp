<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductList.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductList.heading"/></content>
<meta name="menu" content="JpmProductMenu"/>

<c:set var="buttons">
	
		<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
		<jecs:power powerCode="addJpmProduct">
			    <button type="button" class="btn btn_ins" style="width:auto"
			        onclick="location.href='<c:url value="/editJpmProduct.html"/>?strAction=addJpmProduct'"
			        ><jecs:locale key="button.add"/></button>
		</jecs:power>
</c:set>



<form name="frm" action="<c:url value='/jpmProducts.html'/>" >
		<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="busi.product.productno"/>
			<input name="productNo"  value="<c:out value='${requestParaMap.productNo}'/>" cssClass="text medium"/>	
		</div>
<%-- 		
		<div class="new_searchBar">	
			<jecs:title key="jpmProduct.erpProductNo"/>
			<input name="erpProductNo"  value="<c:out value='${requestParaMap.erpProductNo}'/>" cssClass="text medium"/>	
		</div>
--%>
		<div class="new_searchBar">	
			<jecs:title key="pmProduct.productName"/>
			<input name="productName" id="productName" value="<c:out value='${requestParaMap.productName}'/>" cssClass="text medium"/>	
		</div>
		<div class="new_searchBar">		
			<jecs:title key="pm.smNo"/>
				<jecs:list listCode="pmproduct.smno" name="smNo" id="smNo" showBlankLine="true" value="${requestParaMap.smNo}" defaultValue=""/>
		</div>
		<div class="new_searchBar">		
			<jecs:title key="piProductCategory.categoryNo"/>
			<jecs:list listCode="pmproduct.productcategory" name="productCategory" showBlankLine="true" id="productCategory" value="${requestParaMap.productCategory}" defaultValue=""/>
		</div>	
		<div class="new_searchBar">	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:out value="${buttons}" escapeXml="false"/>
		</div>
	</div>	
<!--  
	<div class="searchBar">
		<jecs:title key="busi.product.productno"/>
		<input name="productNo"  value="<c:out value='${requestParaMap.productNo}'/>" cssClass="text medium"/>	
		
		<jecs:title key="jpmProduct.erpProductNo"/>
		<input name="erpProductNo"  value="<c:out value='${requestParaMap.erpProductNo}'/>" cssClass="text medium"/>	
		
		<jecs:title key="pmProduct.productName"/>
		<input name="productName" id="productName" value="<c:out value='${requestParaMap.productName}'/>" cssClass="text medium"/>	
			
		<jecs:title key="pm.smNo"/>
			<jecs:list listCode="pmproduct.smno" name="smNo" id="smNo" showBlankLine="true" value="${requestParaMap.smNo}" defaultValue=""/>
			
		<jecs:title key="piProductCategory.categoryNo"/>
		<jecs:list listCode="pmproduct.productcategory" name="productCategory" showBlankLine="true" id="productCategory" value="${requestParaMap.productCategory}" defaultValue=""/>
		
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
	
-->
		
<ec:table 
	tableId="jpmProductListTable"
	items="jpmProductList"
	var="jpmProduct"
	form="frm"
	action="${pageContext.request.contextPath}/jpmProducts.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
					
					<ec:column property="productNo" title="busi.product.productno" />
<%-- 					<ec:column property="erpProductNo" title="jpmProduct.erpProductNo" />	--%>
    			<ec:column property="productCategory" title="piProductCategory.categoryNo" >
    				<jecs:code listCode="pmproduct.productcategory" value="${jpmProduct.productCategory}"/>
    			</ec:column>
    			<ec:column property="productName" title="pmProduct.productName" />
    			<ec:column property="unitNo" title="piProduct.unitNo" >
    				<jecs:code listCode="pmproduct.unitno" value="${jpmProduct.unitNo}"/>
    			</ec:column>
    			<ec:column property="smNo" title="pm.smNo" >
    				<jecs:code listCode="pmproduct.smno" value="${jpmProduct.smNo}"/>
    			</ec:column>	
    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJpmProduct('${jpmProduct.productNo}')"
								style="cursor: pointer;" title="<jecs:locale key='button.edit'/>"/> 
					</ec:column>
				</ec:row>

</ec:table>	
</form>
<script type="text/javascript">

   function editJpmProduct(productNo){
   		<jecs:power powerCode="editJpmProduct">
					window.location="editJpmProduct.html?strAction=editJpmProduct&productNo="+productNo;
			</jecs:power>
		}

</script>
