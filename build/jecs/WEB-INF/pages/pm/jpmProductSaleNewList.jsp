<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleNewList.title"/></title>
<content tag="heading"><jecs:locale key="jpmProductSaleNewList.heading"/></content>
<meta name="menu" content="JpmProductSaleNewMenu"/>

<c:set var="buttons">
	
		<jecs:power powerCode="addJpmProductSaleNew">
			    <button type="button" class="btn btn_ins" style="width:auto"
			        onclick="location.href='<c:url value="/editJpmProductSaleNew.html"/>?strAction=addJpmProductSaleNew'"
			        ><jecs:locale key="button.add"/></button>
		</jecs:power>
	
</c:set>
<div id="titleBar"> 
</div>
<form name="frm" id="frm"
	action="<c:url value='/jpmProductSaleNews.html'/>">
	<input type="hidden" id="batchSynProduct" name="batchSynProduct"
		value="" />
	<input type="hidden" id="strAction" name="strAction"
		value="${requestParaMap.strAction}" />
	<div class="searchBar">
			<input type="hidden" id="strAction" name="strAction"
						value="${param.strAction}" />
					<input type="hidden" id="selectControl" name="selectControl"
						value="${param.selectControl}" />
				<div class="new_searchBar">	
					<jecs:title key="busi.product.productno" />
					<input name="productNo" id="productNo"
						value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
				</div>
				<div class="new_searchBar">
					<jecs:title key="pmProduct.productName" />
					<input name="productName" id="productName"
						value="<c:out value='${param.productName}'/>"
						cssClass="text medium" />
				</div>
				<div class="new_searchBar">		
					<jecs:title key="piProduct.statusNo" />
					<jecs:list listCode="pmproduct.statusno" name="status" id="status"
						showBlankLine="false" value="${param.status}"
						defaultValue="" />
				</div>
					<!--
					<jecs:title key="piProduct.confirmNo" />
					<jecs:list listCode="pmproduct.confirmno" name="confirm" id="confirm"
						showBlankLine="false" value="${param.confirm}"
						defaultValue="" />-->
				<div class="new_searchBar">
					<jecs:title key="piProductCategory.categoryNo"/>
					<jecs:list listCode="pmproduct.productcategory" name="productCategory" showBlankLine="true" id="productCategory" value="${param.productCategory}" defaultValue=""/>
				</div>	
					<c:if test="${sessionScope.sessionLogin.isManager}">
						<div class="new_searchBar">
							<jecs:title key="sys.company.code" />
							<jecs:company name="companyCode"
								selected="${requestParaMap.companyCode}" prompt="" withAA="true" />
						</div>
					</c:if>
			
			<div class="new_searchBar">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
					<c:out value="${buttons}" escapeXml="false" />
					
				<button type="button" class="btn btn_ins" style="width:auto"
			       		 onclick="exportXls('${requestParaMap.companyCode}',
								'${param.productNo}',
								'${param.productName}',
								'${param.status}',
								'${param.productCategory}');"><jecs:locale key="toolbar.text.xls"/></button>
			</div>
					
					<!-- Add By WuCF 2013-05-09 -->
					
		<%-- 			
					<c:if test="${requestParaMap.strAction=='confirmJpmProductSaleNew'}">
						<div class="new_searchBar"></div>
						<div class="new_searchBar">
							<jecs:title key="piProduct.statusNo2" />
							<jecs:list listCode="pmproduct.statusno" name="status2" id="status2"
							value="${param.status2}" defaultValue="1" />
						</div>
						<div class="new_searchBar">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<jecs:power powerCode="updateTrackingNo">	
					  			<button type="button" class="btn btn_long" style="width:auto" onclick="batchPrint();"><jecs:locale  key='memu.pd.batchAudit'/></button>
							</jecs:power>
						</div>
					</c:if>
					
		--%>
		<c:if test="${requestParaMap.strAction=='confirmJpmProductSaleNew'}">
				<div class="new_searchBar">
							<jecs:title key="piProduct.statusNo2" />
							<jecs:list listCode="pmproduct.statusno" name="status2" id="status2" style="width:auto;" 
							value="${param.status2}" defaultValue="1" />
							&nbsp;&nbsp;
							<jecs:power powerCode="updateTrackingNoPL">	
					  			<button type="button" class="btn btn_long" style="width:auto" onclick="batchPrint();"><jecs:locale  key='memu.pd.batchAudit'/></button>
							</jecs:power>
						</div>
					</c:if>
		
	<%-- 
		<table width="100%" border="0">
			<tr>
				<td>
					<input type="hidden" id="strAction" name="strAction"
						value="${param.strAction}" />
					<input type="hidden" id="selectControl" name="selectControl"
						value="${param.selectControl}" />
				<div class="new_searchBar">	
					<jecs:title key="busi.product.productno" />
					<input name="productNo" id="productNo"
						value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
				</div>
				<div class="new_searchBar">
					<jecs:title key="pmProduct.productName" />
					<input name="productName" id="productName"
						value="<c:out value='${param.productName}'/>"
						cssClass="text medium" />
				</div>
				<div class="new_searchBar">		
					<jecs:title key="piProduct.statusNo" />
					<jecs:list listCode="pmproduct.statusno" name="status" id="status"
						showBlankLine="false" value="${param.status}"
						defaultValue="" />
				</div>
					<!--
					<jecs:title key="piProduct.confirmNo" />
					<jecs:list listCode="pmproduct.confirmno" name="confirm" id="confirm"
						showBlankLine="false" value="${param.confirm}"
						defaultValue="" />-->
				<div class="new_searchBar">
					<jecs:title key="piProductCategory.categoryNo"/>
					<jecs:list listCode="pmproduct.productcategory" name="productCategory" showBlankLine="true" id="productCategory" value="${param.productCategory}" defaultValue=""/>
				</div>	
					<c:if test="${sessionScope.sessionLogin.isManager}">
						<div class="new_searchBar">
							<jecs:title key="sys.company.code" />
							<jecs:company name="companyCode"
								selected="${requestParaMap.companyCode}" prompt="" withAA="true" />
						</div>
					</c:if>
			
			<div class="new_searchBar">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
					<c:out value="${buttons}" escapeXml="false" />
			</div>
					
					<!-- Add By WuCF 2013-05-09 -->
					<c:if test="${requestParaMap.strAction=='confirmJpmProductSaleNew'}">
						<div class="new_searchBar"></div>
						<div class="new_searchBar">
							<jecs:title key="piProduct.statusNo2" />
							<jecs:list listCode="pmproduct.statusno" name="status2" id="status2"
							value="${param.status2}" defaultValue="1" />
						</div>
						<div class="new_searchBar">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<jecs:power powerCode="updateTrackingNo">	
					  			<button type="button" class="btn btn_long" style="width:auto" onclick="batchPrint();"><jecs:locale  key='memu.pd.batchAudit'/></button>
							</jecs:power>
						</div>
					</c:if>
				</td>	
			</tr>
		</table>
		--%>
	</div>
	<ec:table 
		tableId="jpmProductSaleNewListTable"
		items="jpmProductSaleNewList"
		var="jpmProductSaleNew"
		action="${pageContext.request.contextPath}/jpmProductSaleNews.html"
		width="100%"
		retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif" form="frm">
					<ec:row >		
					<c:if test="${requestParaMap.strAction=='confirmJpmProductSaleNew'}">		
						<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
							<input type="checkbox" name="selectedId" id="selectedId" value="${jpmProductSaleNew.uniNo}" class="checkbox"/>
						</ec:column>
					</c:if>		
	    			<ec:column property="jpmProduct.productNo" title="jpmProductSaleNew.productNo" />
	    			<ec:column property="companyCode" title="jpmProductSaleNew.companyCode" />
	    			<ec:column property="productName" title="jpmProductSaleNew.productName" />
	    			<ec:column property="status" title="piProduct.statusNo">
						<jecs:code listCode="pmproduct.statusno"
							value="${jpmProductSaleNew.status}" />
					</ec:column> 
					<ec:column property="jpmProduct.productCategory" title="piProductCategory.categoryNo">
						<jecs:code listCode="pmproduct.productcategory"
							value="${jpmProductSaleNew.jpmProduct.productCategory}" />
					</ec:column> 
					<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmProductSaleNew('${jpmProductSaleNew.uniNo}','${requestParaMap.strAction}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
					</ec:row>
	
	</ec:table>	
</form>

<script type="text/javascript">

   function editJpmProductSaleNew(uniNo,strAction){
   		<jecs:power powerCode="${requestParaMap.strAction}">
					window.location="editJpmProductSaleNew.html?strAction=${param.strAction}&uniNo="+uniNo;
	    </jecs:power>
   }
   
   function batchPrint(){
   		var status2 = document.getElementById("status2");
   	    var selectedId = document.getElementsByName("selectedId");
		var selectStr = '';
		for(var i=0;i<selectedId.length;i++){ 
			if(selectedId[i].checked){
				selectStr += selectedId[i].value+",";
			}
		}  
		if(selectStr==''){
			alert("<jecs:locale key='amMessage.discuss.select'/>");//?
			return;
		} 
		//window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printSendInfo&siNo="+selectStr);	
		window.location="editJpmProductSaleNew.html?strAction=confirmJpmProductSaleNew&uniNoStr="+selectStr+"&status2="+status2.value;
   }
   
   function exportXls(companyCode,productNo,productName,status,productCategory){
		
		window.location="<c:url value="/jpmProductSaleNewReport.html"/>?productNo="+productNo+"&productName="+productName+"&status="+status+"&productCategory="+productCategory;
		
	}

</script>
