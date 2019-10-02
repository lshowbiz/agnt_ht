<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleTeamTypeList.title" /></title>
<content tag="heading">
<jecs:locale key="jpmProductSaleTeamTypeList.heading" />
</content>
<meta name="menu" content="JpmProductSaleTeamTypeMenu" />
 
<form name="frm" action="<c:url value='/jpmProductSaleTeamTypes.html'/>">

	<div class="searchBar">
		 
						
						<input type="hidden" id="strAction" name="strAction"
							value="${param.strAction}" />
						<input type="hidden" id="selectControl" name="selectControl"
							value="${param.selectControl}" />
						<input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
					<c:if test="${sessionScope.sessionLogin.isManager}">
							<div class="new_searchBar">
								<jecs:title key="sys.company.code" />
								<jecs:company name="companyCode"
									selected="${requestParaMap.companyCode}" prompt="" withAA="true" />
							</div>
						</c:if>
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
						<label><jecs:locale key="jpmProductSaleTeamType.state" />:</label>
						<jecs:list listCode="jmimemberteam.status" name="state" id="state"
							showBlankLine="false" value="${param.state}"
							defaultValue="" style="width: 165px;"/>
					</div>
					<div class="new_searchBar">
						<label><jecs:locale key="jpmProductSaleTeamType.teamCode" />:</label>
						<select name="teamCode">
							<option value=""></option>
							<c:forEach items="${jmiMemberTeams}" var="jmiMemberTeam">
								<option value="${jmiMemberTeam.code}" <c:if test="${jmiMemberTeam.code eq param.teamCode}">selected</c:if>>
										${jmiMemberTeam.name}------${jmiMemberTeam.fullName }
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="new_searchBar">	
						<label><jecs:locale key="jpmProductSaleTeamType.orderType" />:</label>
						<jecs:list listCode="po.all.order_type" name="orderType" id="orderType"
							showBlankLine="false" value="${param.orderType}"
							defaultValue="" />
					</div>
					<div class="new_searchBar" style="white-space:nowrap;">	 
						<jecs:title key="jpmProductSaleTeamType.ishidden" />
						<jecs:list listCode="pmproduct.ishidden" name="isHidden"
							showBlankLine="true" id="isHidden"
							value="${param.isHidden}" defaultValue=""  style="width: 165px;"/>
						<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
					</div>	
				 
	
					<div class="new_searchBar">	
						<jecs:title key="busi.product.productno" />
						<input name="productNoP" id="productNoP"
							value="<c:out value='${param.productNoP}'/>"
							cssClass="text medium" />
					</div>
					<div class="new_searchBar">		
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<jecs:power powerCode="addJpmProductSaleTeamType">
							<button type="button" class="btn btn_ins" style="width:auto"
								onclick="checkNewAdd();"><jecs:locale key="button.newadd"/></button>
						</jecs:power>
						<jecs:power powerCode="addJpmProductSaleTeamType">
							<button type="button" class="btn btn_ins" style="width:auto"
								onclick="location.href='<c:url value="/editJpmProductSaleTeamType.html"/>?strAction=addJpmProductSaleTeamType&type=p2'"
								><jecs:locale key="button.newadd2"/></button>
						</jecs:power>
						<jecs:power powerCode="addJpmProductSaleTeamType">
							<button type="button" class="btn btn_ins" style="width:auto"
								onclick="location.href='<c:url value="/editJpmProductSaleTeamType.html"/>?strAction=addJpmProductSaleTeamType'"
								><jecs:locale key="button.add"/></button>
						</jecs:power>
					</div> 
					<div class="new_searchBar" style="white-space:nowrap;">	
						<label><jecs:locale key="jpmProductSaleImage.status" />:</label>
						<jecs:list listCode="jmimemberteam.status" name="status2" id="status2"
							value="${param.status2}" defaultValue="0" style="width: 165px;"/>
						<button type="button" class="btn btn_long" style="width:auto" onclick="batchAudit();"><jecs:locale  key='button.audit'/></button>
					</div>
				 
		
	</div>
<!--  
	<div id="titleBar">
		<table width="100%" border="0">
			<tr>
				<td>
					<input type="hidden" id="strAction" name="strAction"
						value="${param.strAction}" />
					<input type="hidden" id="selectControl" name="selectControl"
						value="${param.selectControl}" />
					<input type="hidden" id="smNo" name="smNo" value="${param.smNo}" />
					<jecs:title key="busi.product.productno" />
					<input name="productNo" id="productNo"
						value="<c:out value='${param.productNo}'/>" cssClass="text medium" />
	
					<jecs:title key="pmProduct.productName" />
					<input name="productName" id="productName"
						value="<c:out value='${param.productName}'/>"
						cssClass="text medium" />
						
					<jecs:locale key="jpmProductSaleTeamType.state" />
					<jecs:list listCode="jmimemberteam.status" name="state" id="state"
						showBlankLine="false" value="${param.state}"
						defaultValue="" />
	
					<jecs:locale key="jpmProductSaleTeamType.teamCode" />
					<select name="teamCode">
						<option value=""></option>
						<c:forEach items="${jmiMemberTeams}" var="jmiMemberTeam">
							<option value="${jmiMemberTeam.code}" <c:if test="${jmiMemberTeam.code eq param.teamCode}">selected</c:if>>
									${jmiMemberTeam.name}------${jmiMemberTeam.fullName }
							</option>
						</c:forEach>
					</select>
					
					<jecs:locale key="jpmProductSaleTeamType.orderType" />
					<jecs:list listCode="po.all.order_type" name="orderType" id="orderType"
						showBlankLine="false" value="${param.orderType}"
						defaultValue="" />
					
					<jecs:title key="jpmProductSaleTeamType.ishidden" />
					<jecs:list listCode="pmproduct.ishidden" name="isHidden"
						showBlankLine="true" id="isHidden"
						value="${param.isHidden}" defaultValue="" />
					
					<br/>
					<c:if test="${sessionScope.sessionLogin.isManager}">
						<jecs:title key="sys.company.code" />
						<jecs:company name="companyCode"
							selected="${requestParaMap.companyCode}" prompt="" withAA="true" />
					</c:if>
					<input type="submit" class="button"
						value="<jecs:locale  key='operation.button.search'/>" />
					
					<jecs:power powerCode="addJpmProductSaleTeamType">
						<input type="button" class="button" style="margin-right: 5px"
							onclick="location.href='<c:url value="/editJpmProductSaleTeamType.html"/>?strAction=addJpmProductSaleTeamType'"
							value="<jecs:locale key="button.add"/>" />
					</jecs:power>
					
					&nbsp;&nbsp;&nbsp;
					<jecs:title key="busi.product.productno" />
					<input name="productNoP" id="productNoP"
						value="<c:out value='${param.productNoP}'/>"
						cssClass="text medium" />
					<jecs:power powerCode="addJpmProductSaleTeamType">
						<input type="button" class="button" style="margin-right: 5px"
							onclick="checkNewAdd();"
							value="<jecs:locale key="button.newadd"/>"/>
					</jecs:power>
					
					&nbsp;&nbsp;&nbsp;
					<jecs:power powerCode="addJpmProductSaleTeamType">
						<input type="button" class="button" style="margin-right: 5px"
							onclick="location.href='<c:url value="/editJpmProductSaleTeamType.html"/>?strAction=addJpmProductSaleTeamType&type=p2'"
							value="<jecs:locale key="button.newadd2"/>" />
					</jecs:power>
					<br/>
					<jecs:locale key="jpmProductSaleImage.status" />
					<jecs:list listCode="jmimemberteam.status" name="status2" id="status2"
						value="${param.status2}" defaultValue="0" />
			  		<input type="button" class="button" onclick="batchAudit();" value="<jecs:locale  key='memu.pd.batchAudit.select'/>"/>
					
				</td>	
			</tr>
		</table>
		
	</div>
-->
	<ec:table tableId="jpmProductSaleTeamTypeListTable"
		items="jpmProductSaleTeamTypeList" var="jpmProductSaleTeamType"
		action="${pageContext.request.contextPath}/jpmProductSaleTeamTypes.html"
		width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif" form="frm">
		<ec:row>
			<jecs:power powerCode="jpmProductSaleTeamTypeExpotExcel">
				 <ec:exportXls fileName="jpmProductSaleTeamTypes.xls"/>
			</jecs:power>
			<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn" viewsAllowed="html">
				<input type="checkbox" name="selectedId" id="selectedId" value="${jpmProductSaleTeamType.pttId}" class="checkbox"/>
			</ec:column>
			<ec:column property="companyCode"
				title="jpmProductSaleTeamType.companyCode" />
			<ec:column property="jpmProductSaleNew.jpmProduct.productNo"
				title="jpmProductSaleTeamType.uniNo" />
			<ec:column property="jpmProductSaleNew.productName"
				title="jpmProductSaleTeamType.productName" />
			<ec:column property="jmiMemberTeam.name"
				title="jpmProductSaleTeamType.teamCode" />
			<ec:column property="jmiMemberTeam.fullName"
				title="jmiMemberTeam.fullname" />
			<ec:column property="orderType"
				title="jpmProductSaleTeamType.orderType"  cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="po.all.order_type"
					value="${jpmProductSaleTeamType.orderType}" />
			</ec:column>
			<ec:column property="price" title="jpmProductSaleTeamType.price" />
			<ec:column property="pv" title="jpmProductSaleTeamType.pv" />
			<ec:column property="state" title="jpmProductSaleTeamType.state"  cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="jmimemberteam.status"
					value="${jpmProductSaleTeamType.state}" />
			</ec:column>
			<ec:column property="isHidden" title="jpmProductSaleTeamType.ishidden"  cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="pmproduct.ishidden"
					value="${jpmProductSaleTeamType.isHidden}" />
			</ec:column>
			<ec:column property="edit" title="title.operation" sortable="false"
				viewsAllowed="html">
				<img src="<c:url value='/images/icons/editIcon.gif'/>"
					onclick="javascript:editJpmProductSaleTeamType('${jpmProductSaleTeamType.pttId}')"
					style="cursor: pointer;" title="<jecs:locale key="button.edit"/>" />
			</ec:column>
		</ec:row>
	
	</ec:table>
</form>

<script type="text/javascript">

   function editJpmProductSaleTeamType(pttId){
   		<jecs:power powerCode="editJpmProductSaleTeamType">
					window.location="editJpmProductSaleTeamType.html?strAction=editJpmProductSaleTeamType&pttId="+pttId;
			</jecs:power>
		}
	function checkNewAdd(){
		var productNoP = document.getElementById("productNoP");
		if(productNoP.value==null || productNoP.value==''){
			alert('<jecs:locale key="button.newadd.err"/>');
			return;
		}else{
			location.href='<c:url value="/editJpmProductSaleTeamType.html"/>?strAction=addJpmProductSaleTeamType&type=p&productNoP='+productNoP.value;		
		}
	}

	function batchAudit(){
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
   		window.location="editJpmProductSaleTeamType.html?strAction=confirmJpmProductSaleTeamType&uniNoStr="+selectStr+"&status2="+status2.value;
   }
</script>
