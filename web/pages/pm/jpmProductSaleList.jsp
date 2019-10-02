<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleList.title" />
</title>
<content tag="heading">
<jecs:locale key="jpmProductSaleList.heading" />
</content>
<meta name="menu" content="JpmProductSaleMenu" />

<c:set var="buttons">
	<c:if test="${requestParaMap.strAction =='editJpmProductSale'}">
		<jecs:power powerCode="addJpmProductSale">
			<input type="button" class="button" style="margin-right: 5px"
				onclick="location.href='<c:url value="/editJpmProductSale.html"/>?strAction=addJpmProductSale'"
				value="<jecs:locale key="button.add"/>" />
		</jecs:power>
	</c:if>
</c:set>




<form name="frm" id="frm"
	action="<c:url value='/jpmProductSales.html'/>">
	<input type="hidden" id="batchSynProduct" name="batchSynProduct"
		value="" />
	<input type="hidden" id="strAction" name="strAction"
		value="${requestParaMap.strAction}" />
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.isManager}">
			<jecs:title key="sys.company.code" />
			<jecs:company name="companyCode"
				selected="${requestParaMap.companyCode}" prompt="" withAA="true" />
		</c:if>
		<jecs:title key="busi.product.productno" />
		<input name="productNo" id="productNo"
			value="<c:out value='${requestParaMap.productNo}'/>"
			cssClass="text medium" />

		<jecs:title key="pm.smNo" />
		<jecs:list listCode="pmproduct.smno" name="smNo" id="smNo"
			showBlankLine="true" value="${requestParaMap.smNo}" defaultValue="" />


		<jecs:title key="piProductCategory.categoryNo" />
		<jecs:list listCode="pmproduct.productcategory" name="productCategory"
			showBlankLine="true" id="productCategory"
			value="${requestParaMap.productCategory}" defaultValue="" />
			
		<jecs:title key="pm.ishidden" />
		<jecs:list listCode="pmproduct.ishidden" name="isHidden"
			showBlankLine="true" id="isHidden"
			value="${requestParaMap.isHidden}" defaultValue="" />
		
		<jecs:title key="piProduct.statusNo" />
		<jecs:list listCode="pmproduct.statusno" name="status"
			showBlankLine="true" id="status"
			value="${requestParaMap.status}" defaultValue="" />
			
		<br>
		<jecs:title key="pd.jpmproductsale.keyword" />
		<input name="keyword" id="keyword"
			value="<c:out value='${requestParaMap.keyword}'/>"
			cssClass="text medium" />
		 
		<input type="submit" class="button"
			value="<jecs:locale  key='operation.button.search'/>" />
<!-- 
		<c:if
			test="${requestParaMap.strAction == 'editJpmProductSale' && sessionScope.sessionLogin.companyCode =='CN'}">
			<jecs:power powerCode="synJpmProductSale">
				<input type="button" class="button" onclick="synproduct();"
					value="<jecs:locale  key='operation.button.syn'/>" />
			</jecs:power>
		</c:if>
		 -->
		<c:if test="${requestParaMap.strAction == 'editStorageCordon' && sessionScope.sessionLogin.companyCode =='CN'}">
			<jecs:power powerCode="updateTrackingNo">
				<input type="button" class="button" onclick="importFile(3);" value="<jecs:locale  key='operation.button.uploadstoragecordon'/>"/>
			</jecs:power>
		</c:if>
		
		<c:out value="${buttons}" escapeXml="false" />
	</div>
	<ec:table tableId="jpmProductSaleListTable" items="jpmProductSaleList"
		var="jpmProductSale" form="frm"
		action="${pageContext.request.contextPath}/jpmProductSales.html"
		width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:exportCsv fileName="products.csv" />
		<ec:exportXls fileName="products.xls" />
		<ec:row>



			<ec:column property="companyCode" title="sys.company.code" />
			<ec:column property="productName" title="pmProduct.productName" />
			<ec:column property="jpmProduct.productNo"
				title="busi.product.productno" />
			<ec:column property="fpPrice"
				title="busi.direct.price.first.purchase" styleClass="numberColumn" />
			<ec:column property="fpPv" title="busi.product.pv"
				styleClass="numberColumn" />
			<ec:column property="mpPrice" title="piProduct.rePrice"
				styleClass="numberColumn" />
			<ec:column property="mpPv" title="piProduct.rePv"
				styleClass="numberColumn" />

			<ec:column property="storeFpPrice" title="pmProductSale.storeFpPrice"
				styleClass="numberColumn" />
			<ec:column property="storeFpPv" title="pmProductSale.storeFpPv"
				styleClass="numberColumn" />
			<ec:column property="storeMpPrice" title="pmProductSale.storeMpPrice"
				styleClass="numberColumn" />
			<ec:column property="storeMpPv" title="pmProductSale.storeMpPv"
				styleClass="numberColumn" />

			<ec:column property="substoreFpPrice"
				title="pmProductSale.substoreFpPrice" styleClass="numberColumn"
				viewsDenied="html" />
			<ec:column property="substoreFpPv" title="pmProductSale.substoreFpPv"
				styleClass="numberColumn" viewsDenied="html" />
			<ec:column property="substoreMpPrice"
				title="pmProductSale.substoreMpPrice" styleClass="numberColumn"
				viewsDenied="html" />
			<ec:column property="substoreMpPv" title="pmProductSale.substoreMpPv"
				styleClass="numberColumn" viewsDenied="html" />

			<ec:column property="status" title="piProduct.statusNo">
				<jecs:code listCode="pmproduct.statusno"
					value="${jpmProductSale.status}" />
			</ec:column>
			<ec:column property="confirm" title="piProduct.confirmNo">
				<jecs:code listCode="pmproduct.confirmno"
					value="${jpmProductSale.confirm}" />
			</ec:column>
			<ec:column property="controlFirst" title="busi.direct.first.purchase">
				<c:choose>
					<c:when test="${jpmProductSale.controlFirst=='1'}">
						<img src="<c:url value='/images/icons/r.gif'/>" />
					</c:when>
					<c:otherwise>
						<img src="<c:url value='/images/icons/w.gif'/>" />
					</c:otherwise>
				</c:choose>
			</ec:column>
			<ec:column property="controlUpdate"
				title="busi.bdPinTitleRecord.upgrade">
				<c:choose>
					<c:when test="${jpmProductSale.controlUpdate=='1'}">
						<img src="<c:url value='/images/icons/r.gif'/>" />
					</c:when>
					<c:otherwise>
						<img src="<c:url value='/images/icons/w.gif'/>" />
					</c:otherwise>
				</c:choose>


			</ec:column>
			<ec:column property="controlRepurchase"
				title="pmProduct.controlRepurchase">
				<c:choose>
					<c:when test="${jpmProductSale.controlRepurchase=='1'}">
						<img src="<c:url value='/images/icons/r.gif'/>" />
					</c:when>
					<c:otherwise>
						<img src="<c:url value='/images/icons/w.gif'/>" />
					</c:otherwise>
				</c:choose>
			</ec:column>

			<ec:column property="edit" title="title.operation" sortable="false"
				viewsAllowed="html">
				<img src="<c:url value='/images/newIcons/pencil_16.gif'/>"
					onclick="javascript:editJpmProductSale('${jpmProductSale.uniNo}','${requestParaMap.strAction}')"
					style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
			</ec:column>
		</ec:row>

	</ec:table>
</form>
<script type="text/javascript">

   function editJpmProductSale(uniNo,strAction){
   		<jecs:power powerCode="${requestParaMap.strAction}">
					window.location="editJpmProductSale.html?strAction="+strAction+"&uniNo="+uniNo;
			</jecs:power>
		}
		
		function synproduct(){
				waiting();
				$('batchSynProduct').value='batchSynProduct';
				$('frm').submit();
			
		}
		function importFile(flagnum){
			window.open("<c:url value='/pdFileUpload2.html?strAction=updateTrackingNo&flagnum="+flagnum+"'/>");
		}

</script>
