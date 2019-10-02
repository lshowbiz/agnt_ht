<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleImageList.title" />
</title>
<content tag="heading">
<jecs:locale key="jpmProductSaleImageList.heading" />
</content>
<meta name="menu" content="JpmProductSaleImageMenu" />

<c:set var="buttons">
	<c:if test="${param.strAction=='editJpmProductSaleImage'}">
		<jecs:power powerCode="addJpmProductSaleImage">
			<button type="button" class="btn btn_ins" style="width:auto"
				onclick="location.href='<c:url value="/editJpmProductSaleImage.html"/>?strAction=addJpmProductSaleImage'"><jecs:locale key="button.add"/></button>
		</jecs:power>
	</c:if>
</c:set>
<form name="frm" action="<c:url value='/jpmProductSaleImages.html'/>">
	<div id="titleBar">
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
						<label><jecs:locale key="jpmProductSaleImage.imageType" />:</label>
						<jecs:list listCode="productimage.imagetype" name="imagetype" id="imagetype"
							showBlankLine="false" value="${param.imagetype}"
							defaultValue="" />
		 			</div>
		 			<div class="new_searchBar">	
		 				<label><jecs:locale key="jpmProductSaleImage.status" />:</label>
						<jecs:list listCode="jmimemberteam.status" name="status" id="status"
							showBlankLine="false" value="${param.status}"
							defaultValue="" />
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
						<c:if test="${requestParaMap.strAction=='confirmJpmProductSaleImage'}">
							<div class="new_searchBar">
								<label><jecs:locale key="jpmProductSaleImage.status" />:</label>
								<jecs:list listCode="jmimemberteam.status" name="status2" id="status2"
								value="${param.status2}" defaultValue="0" style="width:auto;"/>
								&nbsp;&nbsp;
						  		<button type="button" class="btn btn_long" style="width:auto" onclick="batchPrint();"><jecs:locale  key='memu.pd.batchAudit'/></button>
						  	</div>
						</c:if>
				</td>	
			</tr>
		</table>
	</div>
	<ec:table tableId="jpmProductSaleImageListTable"
		items="jpmProductSaleImageList" 
		var="jpmProductSaleImage"
		action="${pageContext.request.contextPath}/jpmProductSaleImages.html"
		width="100%" 
		retrieveRowsCallback="limit" 
		rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif"  form="frm">
		<ec:row>
			<c:if test="${requestParaMap.strAction=='confirmJpmProductSaleImage'}">		
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
					<input type="checkbox" name="selectedId" id="selectedId" value="${jpmProductSaleImage.imageId}" class="checkbox"/>
				</ec:column>
			</c:if>	
			<ec:column property="jpmProductSaleNew.companyCode" title="sys.company.code" />
			<ec:column property="jpmProductSaleNew.jpmProduct.productNo" title="jpmProductSaleNew.productNo" />
			<ec:column property="jpmProductSaleNew.productName" title="jpmProductSaleNew.productName" />
			<ec:column property="imageLink" title="jpmProductSaleImage.imageLink" >
				<img src="${FILE_URL }${jpmProductSaleImage.imageLink }" onclick="suonvtuShow('${FILE_URL }${jpmProductSaleImage.imageLink }');" width="120" height="90" border="1" style="border-color: green;border-width: 1px;"/>
				<!--<c:choose>
					<c:when test="${jpmProductSaleImage.imageType==1}">
						<img src="${FILE_URL }${jpmProductSaleImage.imageLink }" width="120" height="90" border="1" style="border-color: green;border-width: 1px;"/>
					</c:when>
					<c:when test="${jpmProductSaleImage.imageType==2}">
						<img src="${FILE_URL }${jpmProductSaleImage.imageLink }" width="120" height="90" border="1" style="border-color: green;border-width: 1px;"/>
					</c:when>
					<c:when test="${jpmProductSaleImage.imageType==3}">
						<img src="${FILE_URL }${jpmProductSaleImage.imageLink }" width="120" height="90" border="1" style="border-color: green;border-width: 1px;"/>
					</c:when>
				</c:choose>-->
			</ec:column>
			<ec:column property="imageType" title="jpmProductSaleImage.imageType">
				<jecs:code listCode="productimage.imagetype"
					value="${jpmProductSaleImage.imageType}" />
			</ec:column>
			<ec:column property="status" title="jpmProductSaleImage.status">
				<jecs:code listCode="jmimemberteam.status"
					value="${jpmProductSaleImage.status}" />
			</ec:column>
			<ec:column property="edit" title="title.operation" sortable="false"
				viewsAllowed="html">
				<img src="<c:url value='/images/icons/editIcon.gif'/>"
					onclick="javascript:editJpmProductSaleImage('${jpmProductSaleImage.imageId}')"
					style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
			</ec:column>
		</ec:row>
	
	</ec:table>
</form>

<script type="text/javascript">
		
   		function editJpmProductSaleImage(imageId){
   			<jecs:power powerCode="editJpmProductSaleImage">
					window.location="editJpmProductSaleImage.html?strAction=${param.strAction}&imageId="+imageId;
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
			//window.location="editJpmProductSaleNew.html?strAction=confirmJpmProductSaleNew&uniNoStr="+selectStr+"&status2="+status2.value;
	   		window.location="editJpmProductSaleImage.html?strAction=confirmJpmProductSaleImage&uniNoStr="+selectStr+"&status2="+status2.value;
	   }
		
</script>