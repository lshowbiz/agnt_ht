<%@ include file="/common/taglibs.jsp"%>
<script type='text/javascript' src='<c:url value="/dwr/interface/jpmProductSaleTeamTypeManager.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="/dwr/util.js"/>'></script>
<title><jecs:locale key="jpmProductSaleTeamTypeDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jpmProductSaleTeamTypeDetail.heading" />
</content>

<c:choose>
	<c:when test="${uniNoStr!=null && uniNoStr!='' && uniNoStr!='null'}">
		<script type="text/javascript">
			location.href='jpmProductSaleTeamTypes.html?strAction=confirmJpmProductSaleTeamType&uniNoStr=${uniNoStr }&status2=${status2 }';
		</script>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${type=='p2' || type=='p3'}">
				<form:form commandName="jpmProductSaleTeamType" method="post"
					action="editJpmProductSaleTeamType.html"
					onsubmit="return validateJpmProductSaleTeamType(this)"
					id="jpmProductSaleTeamTypeForm">
				
					<input type="hidden" name="strAction" value="${param.strAction }" />
					<input type="hidden" name="type" value="${type }" />
					<input type="hidden" name="uniNoP" value="${uniNoP }" />
					<input type="hidden" name="companyCodeP" value="${companyCodeP }" />
					<c:set var="buttons">
						
					<c:choose>
							<c:when test="${type == 'p2'}">
								<jecs:power powerCode="${param.strAction}">
									<input type="button" class="btn btn_sele" name="save"
										onclick="nextAddJpmProductSaleTeamType();"
										value="<jecs:locale key="button.next"/>" />
								</jecs:power>
							</c:when>
							<c:when test="${type == 'p3'}">
								<jecs:power powerCode="${param.strAction}">
									<input type="submit" class="btn btn_sele" name="save"
										onclick="bCancel=false"
										value="<jecs:locale key="operation.button.save"/>" />
								</jecs:power>
							</c:when>
							<c:otherwise>
								Not,Found!
							</c:otherwise>
						</c:choose>
						 
						<c:if test="${param.strAction=='editJpmProductSaleTeamType'}">
							<jecs:power powerCode="deleteJpmProductSaleTeamType">
								<!-- <input type="submit" class="btn btn_sele" name="delete"
									onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleTeamType')"
									value="<jecs:locale key="operation.button.delete"/>" /> -->
							</jecs:power>
						</c:if>
						<input type="button" class="btn btn_sele" name="back"
							onclick="javascript:history.back();"
							value="<jecs:locale  key="operation.button.return"/>" />
					</c:set>
					
					<spring:bind path="jpmProductSaleTeamType.*">
						<c:if test="${not empty status.errorMessages}">
							<div class="error">
								<c:forEach var="error" items="${status.errorMessages}">
									<img src="<c:url value="/images/iconWarning.gif"/>"
										alt="<jecs:locale key="icon.warning"/>" class="icon" />
									<c:out value="${error}" escapeXml="false" />
									<br />
								</c:forEach>
							</div>
						</c:if>
					</spring:bind>
					
					<table class='detail' width="70%">
						<form:hidden path="pttId" />
						<tbody class="window">		
							<c:if test="${sessionScope.sessionLogin.companyCode == 'AA'}">		
								<tr class="edit_tr">
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="sys.company.code" />
										</span>
									</th>
									<td colspan="3" >
									 	<jecs:company name="companyCode"
										selected="${jpmProductSaleTeamType.companyCode}" prompt="" withAA="true"
										label="${disabled}" />
									</td>			
								</tr>
							</c:if>
							<c:if test="${sessionScope.sessionLogin.companyCode != 'AA'}">
								<form:hidden id="companyCode" path="companyCode" />
							</c:if>
							<c:if test="${type=='p2'}">
								<tr class="edit_tr">
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="jpmProductSaleTeamType.teamCode" />
										</span>
									</th>
									<td>
										<span class="textbox">
										 	<select name="teamCode" id="teamCode" class="textbox-text">
												<option value=""></option>
												<c:forEach items="${jmiMemberTeams}" var="jmiMemberTeam">
													<option value="${jmiMemberTeam.code}" <c:if test="${jmiMemberTeam.code eq teamCode}">selected</c:if>>
															${jmiMemberTeam.name}------${jmiMemberTeam.fullName }
													</option>
												</c:forEach>
											</select>
										</span>
									</td>			
									<th>
										<c:choose>
											<c:when test="${type=='p2'}">
												<span class="text-font-style-tc">
													<jecs:label key="jpmProductSaleTeamType.orderType" />
												</span>
											</c:when>
											<c:otherwise>
												<jecs:label key="jpmProductSaleTeamType.orderType" />
											</c:otherwise>
										</c:choose>
									</th>
									<td>
										<span class="textbox">
										 	<select name="orderType" id="orderType" class="textbox-text">
												<option value=""></option>
												<c:forEach items="${orderTypeList}" var="orderTypeT">
													<option value="${orderTypeT.VALUE_CODE}" ${orderTypeT.VALUE_CODE==orderType ? 'selected' : ''}>
														<jecs:locale key="${orderTypeT.VALUE_TITLE }"/>
													</option>
												</c:forEach>
											</select>
										</span>
									</td>			
								</tr>
								<tr class="edit_tr">
									<th>
										<c:choose>
											<c:when test="${type=='p2'}">
												<span class="text-font-style-tc">
													<jecs:label key="jpmProductSaleTeamType.state" />
												</span>
											</c:when>
											<c:otherwise>
												<jecs:label key="jpmProductSaleTeamType.state" />
											</c:otherwise>
										</c:choose>										
									</th>
									<td>
										<span class="textbox">
										 	<jecs:list listCode="jmimemberteam.status" name="state" id="state" styleClass="textbox-text"
											showBlankLine="false" value="${jpmProductSaleTeamType.state}"
											defaultValue="0" />
										</span>
									</td>
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="busi.product.productno" />
										</span>
									</th>
									<td>
										<span class="textbox">
											<input name="productNo" id="productNo"
											value="<c:out value='${param.productNo}'/>" class="textbox-text" />
										</span>
									</td>
								</tr>
								<tr class="edit_tr">
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="pmProduct.productName" />
										</span>
									</th>
									<td>
										<span class="textbox">
											<input name="productName" id="productName" value="<c:out value='${param.productName}'/>" class="textbox-text" />
										</span>
									</td>
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="piProduct.statusNo" />
										</span>
									</th>
									<td>
										<span class="textbox">
											<jecs:list listCode="pmproduct.statusno" name="status" id="status"
											showBlankLine="false" value="${param.status}" styleClass="textbox-text"
											defaultValue="" />
										</span>
									</td>
								</tr>
								<tr class="edit_tr">
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="piProductCategory.categoryNo"/>
										</span>
									</th>
									<td>
										<span class="textbox">
											<jecs:list listCode="pmproduct.productcategory" styleClass="textbox-text" name="productCategory" showBlankLine="true" id="productCategory" value="${requestParaMap.productCategory}" defaultValue=""/>
										</span>
									</td>
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="pm.smNo" styleClass="textbox-text"/>
										</span>
									</th>
									<td>
										<span class="textbox">
											<jecs:list listCode="pmproduct.smno" name="smNo" styleClass="textbox-text" id="smNo" showBlankLine="true" value="${requestParaMap.smNo}" defaultValue=""/>
										</span>
									</td>
								</tr>
								<tr class="edit_tr">
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="jpmProductSaleTeamType.state" />
										</span>
									</th>
									<td>
										<span class="textbox">
											<jecs:list listCode="jmimemberteam.status" name="state" styleClass="textbox-text" id="state" showBlankLine="false" value="${requestParaMap.state}"	defaultValue="" />
										</span>
									</td>
								</tr>
							</c:if>
							<c:if test="${type=='p3'}">
								<tr class="edit_tr">
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="jpmProductSaleTeamType.teamCode" />
										</span>
									</th>
									<td>
										<span class="textbox">
										 	<select name="teamCode" id="teamCode" class="textbox-text">
												<option value=""></option>
												<c:forEach items="${jmiMemberTeams}" var="jmiMemberTeam">
													<option value="${jmiMemberTeam.code}" <c:if test="${jmiMemberTeam.code eq teamCode}">selected</c:if>  disabled="true">
															${jmiMemberTeam.name}------${jmiMemberTeam.fullName }
													</option>
												</c:forEach>
											</select>
											<input type="hidden" name="teamCode" value="${teamCode }"/>
										</span>
									</td>			
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="jpmProductSaleTeamType.orderType" />
										</span>
									</th>
									<td>
										<span class="textbox">
										 	<select name="orderType" id="orderType" class="textbox-text">
												<option value=""></option>
												<c:forEach items="${orderTypeList}" var="orderTypeT">
													<option value="${orderTypeT.VALUE_CODE}" ${orderTypeT.VALUE_CODE==orderType ? 'selected' : ''} disabled="true">
														<jecs:locale key="${orderTypeT.VALUE_TITLE }"/>
													</option>
												</c:forEach>
											</select>
											<input type="hidden" name="orderType" value="${orderType }"/>
										</span>
									</td>			
								</tr>
								<tr class="edit_tr">
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="jpmProductSaleTeamType.teamCode" />
										</span>
									</th>
									<td>
										<span class="textbox">
										 	<jecs:list listCode="jmimemberteam.status" name="state" id="state" 
												showBlankLine="false" value="${jpmProductSaleTeamType.state}" styleClass="textbox-text"
												defaultValue="0" />
											<%-- <input type="hidden" name="state" value="${state }"/> --%>
										</span>
									</td>			
								</tr>								
								<tr class="edit_tr">
									<th>
										<jecs:label key="pdWarehouseStock.productNo" />
									</th>
									<td colspan="3" >
									 	<table>
											<tr>
								        		<td colspan="5">
								        			<input type="button" class="btn btn_sele" onclick="selectAll();"
											        value="<jecs:locale  key='button.selectAll'/>"/>
											        <input type="button" class="btn btn_sele" onclick="reSelectAll();"
											        value="<jecs:locale  key='common.reSelectAll'/>"/>
								        		</td>
								        	</tr>
											<tr>
												<td><input type="checkbox" onclick="selectAll();"/></td>
												<td><jecs:label key="jpmProductSaleNew.productNo" /></td>
												<td><jecs:label key="jpmProductSaleNew.productName" /></td>
												<td><jecs:label key="jpmProductSaleTeamType.price" /></td>
												<td><jecs:label key="jpmProductSaleTeamType.pv" /></td>
											</tr>
											<c:forEach items="${jpmProductSaleNewlist}" var="jpstt">
												<tr>
													<td><input type="checkbox" name="orderTypeW" id="orderType${jpstt.UNI_NO }" value="${jpstt.UNI_NO }" onclick="clickOrderType('${jpstt.UNI_NO}');"/></td>
													<td>${jpstt.PRODUCT_NO }</td>
													<td>${jpstt.PRODUCT_NAME }</td>
													<td><input type="text" name="priceW" id="price${jpstt.UNI_NO }" disabled="disabled" value="${jpstt.PRICE}" onchange="checkOrderType(this.value,'${jpstt.UNI_NO}','1');"/></td>
													<td><input type="text" name="pvW" id="pv${jpstt.UNI_NO }" disabled="disabled" value="${jpstt.PV}" onchange="checkOrderType(this.value,'${jpstt.UNI_NO}','2');"/></td>
												</tr>
											</c:forEach>
										</table>
									</td>			
								</tr>
							</c:if>
							<c:if test="${param.strAction=='editJpmProductSaleTeamType'}">
								<tr class="edit_tr">
									<th>
										<span class="text-font-style-tc">
											<jecs:label key="jpmProductSaleTeamType.ishidden" />
										</span>
									</th>
									<td colspan="3" >
									 	<jecs:list listCode="pmproduct.ishidden" name="isHidden" id="isHidden"
										showBlankLine="false" value="${jpmProductSaleTeamType.isHidden}"
										defaultValue="0" />
									</td>			
								</tr>
							</c:if>
							<tr>
								<td class="command" colspan="4" align="center">
									<c:out value="${buttons}" escapeXml="false" />
								</td>
							</tr>
						</tbody>
					</table>
				</form:form>
				
				<script type="text/javascript">
				    Form.focusFirstElement($('jpmProductSaleTeamTypeForm'));
				    
				    function selectProduct() {
				    	document.getElementById('teamCode').value='';
						open(
								"<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=2'/>",
								"",
								"height=800, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
					}
					
					/**onmousemove**/
					function checkTeamCodeMove(){
						var companyCode = document.getElementById("companyCode");
						var uniNo = document.getElementById("uniNo");
						if(uniNo!=null && uniNo.value!=null && uniNo.value!=''){
							//var returnStr = jpmProductSaleTeamTypeManager.getTeamCodeStr(companyCode.value,uniNo.value,validateTeamCode);
						}
					}
					
					function validateTeamCode(ret){
						alert(ret);
					}
					
					
					/**onchange teamCode**/
					function checkTeamCode(teamCode){
						var uniNo = document.getElementById("uniNo");
						var companyCode = document.getElementById("companyCode");
						if(uniNo.value!=null){
							if(uniNo.value==''){
								alert('<jecs:locale key="piProductNew.uncheck.unino"/>');
								document.getElementById("teamCode").value='';
								product.focus();
								return false;
							}else{
								var returnStr = jpmProductSaleTeamTypeManager.getOrderTypeStr(companyCode.value,uniNo.value,teamCode,validateOrderType);
							}
						}
					}
					
					function validateOrderType(ret){
						if(ret!=null && ret!='' && ret!='null'){
							var strs = ret.split("#");
							var ss;
							for(var i=0;i<strs.length-1;i++){
								ss = strs[i].split("-"); 
								if(document.getElementById("price"+ss[2])!=null){
									document.getElementById("price"+ss[2]).disabled = 'disabled';
									document.getElementById("price"+ss[2]).value=ss[0];
								}
								if(document.getElementById("pv"+ss[2])!=null){
									document.getElementById("pv"+ss[2]).disabled = 'disabled';
									document.getElementById("pv"+ss[2]).value=ss[1];
								}
							}
							var orderType = document.getElementsByName("orderTypeW");
							for(var i=0;i<orderType.length;i++){
								if(ret.indexOf('-'+orderType[i].value+'#')>=0){
									orderType[i].checked = true;
									orderType[i].disabled = 'disabled';
								}else{
									orderType[i].checked = false;
									orderType[i].disabled = '';
								}
							}
						}else{
							var orderType = document.getElementsByName("orderTypeW");
							var price = document.getElementsByName("priceW");
							var pv = document.getElementsByName("pvW");
							for(var i=0;i<orderType.length;i++){
								orderType[i].checked = false; 
								orderType[i].disabled = '';
								price[i].disabled = '';
								price[i].value = '';
								pv[i].disabled = '';
								pv[i].value = '';
							}
						}
					}
					
					/**dianji**/
					function clickOrderType(num){
						if(document.getElementById("orderType"+num).checked==true){
							if(document.getElementById("price"+num).value==''){
								document.getElementById("price"+num).value='0';
							}
							if(document.getElementById("pv"+num).value==''){
								document.getElementById("pv"+num).value='0';
							}
							document.getElementById("price"+num).disabled='';
							document.getElementById("pv"+num).disabled='';
						}else{
							if(document.getElementById("price"+num).value==''){
								document.getElementById("price"+num).value='0';
							}
							if(document.getElementById("pv"+num).value==''){
								document.getElementById("pv"+num).value='0';
							}
							document.getElementById("price"+num).disabled='disabled';
							document.getElementById("pv"+num).disabled='disabled';
						}
					}
					
					/**she zhi orderType Auto**/
					function checkOrderType(text,num,type){
						var orderType = document.getElementsByName("orderTypeW"); 
						if(text!=null && text!=''){
							document.getElementById("orderType"+num).checked = true;
						}else{
							this.value='0';
						}
						
						var price = document.getElementById("price"+num);  
						var pv = document.getElementById("pv"+num);
						if(price.value==null || price.value==''){
							price.value=0;
						}
						if(pv.value==null || pv.value==''){
							pv.value=0;
						}
						
						if(type=='1'){
							if(isNaN(price.value) || price.value<0){
								alert('<jecs:locale key="error.isNotRightNum"/>!');
								price.value='0';
								price.focus();
							}
						}else if(type=='2'){
							if(isNaN(pv.value) || pv.value<0){
								alert('<jecs:locale key="error.isNotRightNum"/>!');
								pv.value='0';
								price.focus();
							}
						}
					}
					
					
					function selectAllP(){
						var teamCodeP = document.getElementsByName("teamCodeP");
						for(var i=0;i<teamCodeP.length;i++){ 
							teamCodeP[i].checked=true;
						} 		 
					}
					
					function reSelectAllP(){
						var teamCodeP = document.getElementsByName("teamCodeP");
						for(var i=0;i<teamCodeP.length;i++){ 
							if(teamCodeP[i].checked==false){
								teamCodeP[i].checked=true;
							}else if(teamCodeP[i].checked==true){
								teamCodeP[i].checked=false;
							}
						} 		
					}
					/**************************************/
					function selectAll(){
						var orderType = document.getElementsByName("orderTypeW");
						var price = document.getElementsByName("priceW");
						var pv = document.getElementsByName("pvW");
						for(var i=0;i<orderType.length;i++){ 
							orderType[i].checked=true;
							price[i].disabled='';
							pv[i].disabled='';
						}
					}
					
					function reSelectAll(){
						var orderType = document.getElementsByName("orderTypeW");
						var price = document.getElementsByName("priceW");
						var pv = document.getElementsByName("pvW");
						for(var i=0;i<orderType.length;i++){ 
							if(orderType[i].checked==false){
								orderType[i].checked=true;
								price[i].disabled='';
								pv[i].disabled='';
							}else if(orderType[i].checked==true && orderType[i].disabled==''){
								orderType[i].checked=false;
								price[i].disabled='disabled';
								pv[i].disabled='disabled';
							}
						} 		
					}
					
					function setPriceP(){
						var priceP = document.getElementById("priceP");
						var setValue = 0;
						if(priceP.value==null || priceP.value=='' || isNaN(priceP.value) || priceP.value<0){
							setValue = '0';
						}else{
							setValue = priceP.value;
						}
						
						var orderType = document.getElementsByName("orderTypeW");
						var price = document.getElementsByName("priceW");
						for(var i=0;i<orderType.length;i++){ 
							if(orderType[i].checked==true && orderType[i].disabled==''){ 
								price[i].value=setValue; 
							}
						} 
					}
					
					function setPvP(){
						var pvP = document.getElementById("pvP");
						var setValue = 0;
						if(pvP.value==null || pvP.value=='' || isNaN(pvP.value) || pvP.value<0){
							setValue = '0';
						}else{
							setValue = pvP.value;
						}
						
						var orderType = document.getElementsByName("orderTypeW");
						var pv = document.getElementsByName("pvW");
						for(var i=0;i<orderType.length;i++){ 
							if(orderType[i].checked==true && orderType[i].disabled==''){ 
								pv[i].value=setValue; 
							}
						} 
					}
					
					function waiting(){
						disableButton();
						var o = document.createElement('iframe');
						o.id = 'fram_bk';
						document.body.appendChild(o);
						with ($('fram_bk').style){
							display='block';
							top = "60px";
							left = "30%";
						}
						var el=document.getElementById("sending_sub");
						if (el.hasChildNodes()){
						  el.removeChild(el.lastChild);
						}
						
						el.appendChild( document.createTextNode('<jecs:locale key="button.loading"/>'));
						document.getElementById("sending").style.visibility="visible";
						document.getElementById("sending").style.display="block";
					}
				</script>
				
				<v:javascript formName="jpmProductSaleTeamType" cdata="false"
					dynamicJavascript="true" staticJavascript="false" />
				<script type="text/javascript"
					src="<c:url value="/scripts/validator.jsp"/>"></script>
				<script type="text/javascript"><!--
					function nextAddJpmProductSaleTeamType(){
						var orderType = document.getElementById("orderType"); 
						var teamCode = document.getElementById("teamCode"); 
						var state = document.getElementById("state"); 
						if(teamCode.value==''){
							alert("<jecs:locale key='amMessage.discuss.select' /><jecs:locale key='jpmProductSaleTeamType.teamCode' />");
							return false;
						}
						if(orderType.value==''){ 
							alert("<jecs:locale key='amMessage.discuss.select' /><jecs:locale key='jpmProductSaleTeamType.orderType' />");
							return false;
						}
						if(state.value==''){
							alert("<jecs:locale key='amMessage.discuss.select' /><jecs:locale key='jpmProductSaleTeamType.state' />");
							return false;
						}
						
						var productNo = document.getElementById("productNo"); 
						var productName = document.getElementById("productName"); 
						var status = document.getElementById("status"); 
						var productCategory = document.getElementById("productCategory"); 
						var smNo = document.getElementById("smNo"); 
						
						waiting();
						location.href='editJpmProductSaleTeamType.html?strAction=addJpmProductSaleTeamType&type=p3&orderType='+orderType.value+'&teamCode='+teamCode.value+'&state='+state.value
									  +'&productNo='+productNo.value+'&productName='+productName.value+'&status='+status.value+'&productCategory='+productCategory.value+'&smNo='+smNo.value;
					}
					
				 	function validateJpmProductSaleTeamType(){
				 		waiting();
				 		/*var strAction = '${param.strAction }';
				 		var uniNoId = document.getElementById("uniNoId");
				 		var teamCode = document.getElementById("teamCode"):
				 		alert(strAction);
				 		alert(uniNoId.value);
				 		alert(teamCode.value);*/
				 		return true;
				 	}
				</script>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${returnStr=='1'}">
						<script type="text/javascript">
							alert('<jecs:locale key="teamtye.productno.check"/>');
							window.history.go(-1);
						</script>
					</c:when>
					<c:when test="${returnStr=='2'}">
						<script type="text/javascript">
							alert('<jecs:locale key="teamtye.productno.check"/>');
							window.history.go(-1);
						</script>
					</c:when>
					<c:otherwise>
								<c:set var="buttons">
						
							<jecs:power powerCode="${param.strAction}">
								<input type="submit" class="btn btn_sele" name="save"
									onclick="bCancel=false"
									value="<jecs:locale key="operation.button.save"/>" />
							</jecs:power>
							 
							<c:if test="${param.strAction=='editJpmProductSaleTeamType'}">
								<jecs:power powerCode="deleteJpmProductSaleTeamType">
									<!-- <input type="submit" class="btn btn_ins" name="delete"
										onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleTeamType')"
										value="<jecs:locale key="operation.button.delete"/>" />-->
								</jecs:power>
							</c:if>
							<input type="button" class="btn btn_sele" name="back"
								onclick="javascript:history.back();"
								value="<jecs:locale  key="operation.button.return"/>" />
						</c:set>
						
						<spring:bind path="jpmProductSaleTeamType.*">
							<c:if test="${not empty status.errorMessages}">
								<div class="error">
									<c:forEach var="error" items="${status.errorMessages}">
										<img src="<c:url value="/images/iconWarning.gif"/>"
											alt="<jecs:locale key="icon.warning"/>" class="icon" />
										<c:out value="${error}" escapeXml="false" />
										<br />
									</c:forEach>
								</div>
							</c:if>
						</spring:bind>
						
						<form:form commandName="jpmProductSaleTeamType" method="post"
							action="editJpmProductSaleTeamType.html"
							onsubmit="return validateJpmProductSaleTeamType(this)"
							id="jpmProductSaleTeamTypeForm">
						
							<input type="hidden" name="strAction" value="${param.strAction }" />
							<input type="hidden" name="type" value="${type }" />
							<input type="hidden" name="uniNoP" value="${uniNoP }" />
							<input type="hidden" name="companyCodeP" value="${companyCodeP }" />
							<!--fieldset style="padding: 2">
						<legend>
										<input type="submit" class="btn btn_ins" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
						        <input type="submit" class="btn btn_ins" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleTeamType')" value="<jecs:locale key="button.delete"/>" />
						        <input type="submit" class="btn btn_ins" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
						
						</legend-->
							
							<table class='detail' width="70%">
								<form:hidden path="pttId" />
								<tbody class="window">
									<c:if test="${sessionScope.sessionLogin.companyCode == 'AA'}">		
										<tr class="edit_tr">
											<th>
												<jecs:label key="sys.company.code" />
											</th>
											<td colspan="3" >
											 	<jecs:company name="companyCode"
												selected="${jpmProductSaleTeamType.companyCode}" prompt="" withAA="true"
												label="${disabled}" />
											</td>			
										</tr>
									</c:if>
									<c:if test="${sessionScope.sessionLogin.companyCode != 'AA'}">
										<form:hidden id="companyCode" path="companyCode" />
									</c:if>
									<tr class="edit_tr">
										<th>
											<span class="text-font-style-tc">
												<jecs:label key="jpmProductSaleTeamType.uniNo" />
											</span>
										</th>
										<td>
										 	<c:choose>
												<c:when test="${type=='p'}">
													<span class="textbox">
														<input type="text" name="uniNo" id="uniNoId" class="textbox-text" disabled="disabled" value="${jpmProductSaleNew.jpmProduct.productNo}-${jpmProductSaleNew.productName}"/>
													</span>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo!=null}">
															${jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo }
															<input type="hidden" name="uniNo" id="uniNoId" value="${jpmProductSaleTeamType.uniNo}"/>
														</c:when>
														<c:otherwise>
															<span class="textbox">
																<form:input path="jpmProductSaleNew.jpmProduct.productNo"  id="jpmProductSaleNew.jpmProduct.productNo" cssClass="textbox-text" onclick="selectProduct();"/>
															</span>
															<input type="button" class="btn btn_ins" name="select"
																onclick="selectProduct();"
																value="<jecs:locale key="button.select"/>"/>
															<!--<form:hidden id="jpmProductSaleNew.uniNo" path="jpmProductSaleNew.uniNo" />-->
															<form:hidden path="uniNo" id="uniNo"/>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</td>
										<th>
											<span class="text-font-style-tc">
												<jecs:label key="jpmProductSaleTeamType.state" />
											</span>	
										</th>
										<td>
											<span class="textbox">
											 	<jecs:list listCode="jmimemberteam.status" name="state" id="state"
												showBlankLine="false" value="${jpmProductSaleTeamType.state}" styleClass="textbox-text"
												defaultValue="0" />
											</span>
										</td>			
									</tr>
									<tr class="edit_tr">
										<th>
											<c:choose>
												<c:when test="${type!='p'}">
													<span class="text-font-style-tc">
														<jecs:label key="jpmProductSaleTeamType.teamCode" />
													</span>
												</c:when>
												<c:otherwise>
													<jecs:label key="jpmProductSaleTeamType.teamCode" />
												</c:otherwise>
											</c:choose>	
										</th>
										<td colspan="3" >
										 	<c:choose>
												<c:when test="${type=='p'}">
													<input type="button" class="btn btn_ins" onclick="selectAllP();"
											        value="<jecs:locale  key='button.selectAll'/>"/>
											        <input type="button" class="btn btn_ins" onclick="reSelectAllP();"
											        value="<jecs:locale  key='common.reSelectAll'/>"/>
											        <br/>
											        <table>
														<c:forEach items="${jmiMemberTeams}" var="jmiMemberTeam" varStatus="status">
															<c:if test="${status.count%4==1}"><tr></c:if>
															<td><input type="checkbox" name="teamCodeP" id="teamCodeP${jmiMemberTeam.code }" value="${jmiMemberTeam.code }"/>${jmiMemberTeam.name }-${jmiMemberTeam.fullName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${status.count%4==0}"><br/></c:if></td>					
															<c:if test="${status.count%4==0}"></tr></c:if>
														</c:forEach>
													</table>
												</c:when>
												<c:otherwise>
													<span class="textbox">
														<select name="teamCode" class="textbox-text" onchange="checkTeamCode(this.value);" id="teamCode" ${param.strAction=="editJpmProductSaleTeamType" ? "disabled='disabled'" : ""} >
															<option value=""></option>
															<c:forEach items="${jmiMemberTeams}" var="jmiMemberTeam">
																<option value="${jmiMemberTeam.code}" <c:if test="${jmiMemberTeam.code eq jpmProductSaleTeamType.teamCode}">selected</c:if>>
																		${jmiMemberTeam.name}------${jmiMemberTeam.fullName }
																</option>
															</c:forEach>
														</select>
													</span>
												</c:otherwise>
											</c:choose>
										</td>			
									</tr>
									<tr class="edit_tr">
										<th>
											<jecs:label key="jpmProductSaleTeamType.orderType" />
										</th>
										<td colspan="3" >
										 	<c:choose>
												<c:when test="${param.strAction=='addJpmProductSaleTeamType'}">
											        <table>
											        	<tr>
											        		<td>
											        			<input type="button" class="btn btn_ins" onclick="selectAll();"
														        value="<jecs:locale  key='button.selectAll'/>"/>
														        <input type="button" class="btn btn_ins" onclick="reSelectAll();"
														        value="<jecs:locale  key='common.reSelectAll'/>"/>
											        		</td>
											        		<td>
											        			<input type="text" class="textbox-text" name="priceP" id="priceP"/><input type="button" class="btn btn_ins" onclick="setPriceP();" value="<jecs:locale key='batch.setvalue'/>"/>
											        		</td>
											        		<td>
											        			<input type="text" class="textbox-text" name="pvP" id="pvP"/><input type="button" class="btn btn_ins" onclick="setPvP();" value="<jecs:locale key='batch.setvalue'/>"/>
											        		</td>
											        	</tr>
														<c:forEach items="${orderTypeList }" var="orderType" varStatus="status">
															<tr>
																<td><input type="checkbox" name="orderTypeW" id="orderType${orderType.VALUE_CODE }" value="${orderType.VALUE_CODE }" onclick="clickOrderType('${orderType.VALUE_CODE}');"/><jecs:locale key="${orderType.VALUE_TITLE }"/></td>
																<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:label key="jpmProductSaleTeamType.price" /><input type="text" class="textbox-text" name="priceW" id="price${orderType.VALUE_CODE }" onchange="checkOrderType(this.value,'${orderType.VALUE_CODE}','1');"/></td>
																<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:label key="jpmProductSaleTeamType.pv" /><input type="text" class="textbox-text" name="pvW" id="pv${orderType.VALUE_CODE }" onchange="checkOrderType(this.value,'${orderType.VALUE_CODE}','2');"/></td>
															</tr>
														</c:forEach>
													</table>
												</c:when>
												<c:when test="${param.strAction=='editJpmProductSaleTeamType'}">
													<jecs:list listCode="po.all.order_type" name="orderType" id="orderType" styleClass="textbox-text"
													showBlankLine="false" value="${jpmProductSaleTeamType.orderType}"
													defaultValue=""  disabled="disabled"/>
												</c:when>
											</c:choose>
										</td>			
									</tr>
									<c:if test="${param.strAction=='editJpmProductSaleTeamType'}">
										<tr class="edit_tr">
											<th>
												<span class="text-font-style-tc">
													<jecs:label key="jpmProductSaleTeamType.price" />
												</span>
											</th>
											<td colspan="3" >
												<span class="textbox">
											 		<form:input path="price" id="price" cssClass="textbox-text" disabled="disabled"/>
											 	</span>
											</td>			
										</tr>
										<tr class="edit_tr">
											<th>
												<span class="text-font-style-tc">
													<jecs:label key="jpmProductSaleTeamType.pv" />
												</span>
											</th>
											<td colspan="3" >
												<span class="textbox">
											 		<form:input path="pv" id="pv" cssClass="textbox-text" />
											 	</span>
											</td>			
										</tr>
									</c:if>
									<c:if test="${param.strAction=='editJpmProductSaleTeamType'}">
										<tr class="edit_tr">
											<th>
												<span class="text-font-style-tc">
													<jecs:label key="jpmProductSaleTeamType.ishidden" />
												</span>
											</th>
											<td colspan="3" >
											 	<jecs:list listCode="pmproduct.ishidden" name="isHidden" id="isHidden"
												showBlankLine="false" value="${jpmProductSaleTeamType.isHidden}" styleClass="textbox-text"
												defaultValue="0" />
											</td>			
										</tr>
									</c:if>
									<tr>
										<td class="command" colspan="4" align="center">
											<c:out value="${buttons}" escapeXml="false" />
										</td>
									</tr>
								</tbody>
							</table>
							<!--/fieldset-->
						
							<!--table class='detail' width="100%">
						    <tr>
						    <td>
						        <input type="submit" class="btn btn_ins" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
						        <input type="submit" class="btn btn_ins" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleTeamType')" value="<jecs:locale key="button.delete"/>" />
						        <input type="submit" class="btn btn_ins" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
						    </td></tr>
						</table-->
						</form:form>
						
						<script type="text/javascript">
						    Form.focusFirstElement($('jpmProductSaleTeamTypeForm'));
						    
						    function selectProduct() {
						    	document.getElementById('teamCode').value='';
								open(
										"<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=2'/>",
										"",
										"height=800, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
							}
							
							/**onmousemove**/
							function checkTeamCodeMove(){
								var companyCode = document.getElementById("companyCode");
								var uniNo = document.getElementById("uniNo");
								if(uniNo!=null && uniNo.value!=null && uniNo.value!=''){
									//var returnStr = jpmProductSaleTeamTypeManager.getTeamCodeStr(companyCode.value,uniNo.value,validateTeamCode);
								}
							}
							
							function validateTeamCode(ret){
								alert(ret);
							}
							
							
							/**onchange teamCode**/
							function checkTeamCode(teamCode){
								var uniNo = document.getElementById("uniNo");
								var companyCode = document.getElementById("companyCode");
								if(uniNo.value!=null){
									if(uniNo.value==''){
										alert('<jecs:locale key="piProductNew.uncheck.unino"/>');
										document.getElementById("teamCode").value='';
										product.focus();
										return false;
									}else{
										var returnStr = jpmProductSaleTeamTypeManager.getOrderTypeStr(companyCode.value,uniNo.value,teamCode,validateOrderType);
									}
								}
							}
							
							function validateOrderType(ret){
								if(ret!=null && ret!='' && ret!='null'){
									var strs = ret.split("#");
									var ss;
									for(var i=0;i<strs.length-1;i++){
										ss = strs[i].split("-"); 
										if(document.getElementById("price"+ss[2])!=null){
											document.getElementById("price"+ss[2]).disabled = 'disabled';
											document.getElementById("price"+ss[2]).value=ss[0];
										}
										if(document.getElementById("pv"+ss[2])!=null){
											document.getElementById("pv"+ss[2]).disabled = 'disabled';
											document.getElementById("pv"+ss[2]).value=ss[1];
										}
									}
									var orderType = document.getElementsByName("orderTypeW");
									for(var i=0;i<orderType.length;i++){
										if(ret.indexOf('-'+orderType[i].value+'#')>=0){
											orderType[i].checked = true;
											orderType[i].disabled = 'disabled';
										}else{
											orderType[i].checked = false;
											orderType[i].disabled = '';
										}
									}
								}else{
									var orderType = document.getElementsByName("orderTypeW");
									var price = document.getElementsByName("priceW");
									var pv = document.getElementsByName("pvW");
									for(var i=0;i<orderType.length;i++){
										orderType[i].checked = false; 
										orderType[i].disabled = '';
										price[i].disabled = '';
										price[i].value = '';
										pv[i].disabled = '';
										pv[i].value = '';
									}
								}
							}
							
							/**dianji**/
							function clickOrderType(num){
								if(document.getElementById("orderType"+num).checked==true){
									document.getElementById("price"+num).value='0';
									document.getElementById("pv"+num).value='0';
								}else{
									document.getElementById("price"+num).value='';
									document.getElementById("pv"+num).value='';
								}
							}
							
							/**she zhi orderType Auto**/
							function checkOrderType(text,num,type){
								var orderType = document.getElementsByName("orderTypeW"); 
								if(text!=null && text!=''){
									document.getElementById("orderType"+num).checked = true;
								}else{
									this.value='0';
								}
								
								var price = document.getElementById("price"+num);  
								var pv = document.getElementById("pv"+num);
								if(price.value==null || price.value==''){
									price.value=0;
								}
								if(pv.value==null || pv.value==''){
									pv.value=0;
								}
								
								if(type=='1'){
									if(isNaN(price.value) || price.value<0){
										alert('<jecs:locale key="error.isNotRightNum"/>!');
										price.value='0';
										price.focus();
									}
								}else if(type=='2'){
									if(isNaN(pv.value) || pv.value<0){
										alert('<jecs:locale key="error.isNotRightNum"/>!');
										pv.value='0';
										price.focus();
									}
								}
							}
							
							
							function selectAllP(){
								var teamCodeP = document.getElementsByName("teamCodeP");
								for(var i=0;i<teamCodeP.length;i++){ 
									teamCodeP[i].checked=true;
								} 		 
							}
							
							function reSelectAllP(){
								var teamCodeP = document.getElementsByName("teamCodeP");
								for(var i=0;i<teamCodeP.length;i++){ 
									if(teamCodeP[i].checked==false){
										teamCodeP[i].checked=true;
									}else if(teamCodeP[i].checked==true){
										teamCodeP[i].checked=false;
									}
								} 		
							}
							/**************************************/
							function selectAll(){
								var orderType = document.getElementsByName("orderTypeW");
								for(var i=0;i<orderType.length;i++){ 
									orderType[i].checked=true;
								} 		 
								
								var price = document.getElementsByName("priceW");
								var pv = document.getElementsByName("pvW");
								for(var i=0;i<price.length;i++){
									if(price[i].disabled==false){
										price[i].disabled = '';
										price[i].value = '0';
									}
									if(pv[i].disabled==false){
										pv[i].disabled = '';
										pv[i].value = '0';
									}
								}
							}
							
							function reSelectAll(){
								var orderType = document.getElementsByName("orderTypeW");
								var price = document.getElementsByName("priceW");
								var pv = document.getElementsByName("pvW");
								for(var i=0;i<orderType.length;i++){ 
									if(orderType[i].checked==false && orderType[i].disabled==''){
										orderType[i].checked=true;
										price[i].value='0';
										pv[i].value='0';
									}else if(orderType[i].checked==true && orderType[i].disabled==''){
										orderType[i].checked=false;
										price[i].value='';
										pv[i].value='';
									}
								} 		
							}
							
							function setPriceP(){
								var priceP = document.getElementById("priceP");
								var setValue = 0;
								if(priceP.value==null || priceP.value=='' || isNaN(priceP.value) || priceP.value<0){
									setValue = '0';
								}else{
									setValue = priceP.value;
								}
								
								var orderType = document.getElementsByName("orderTypeW");
								var price = document.getElementsByName("priceW");
								for(var i=0;i<orderType.length;i++){ 
									if(orderType[i].checked==true && orderType[i].disabled==''){ 
										price[i].value=setValue; 
									}
								} 
							}
							
							function setPvP(){
								var pvP = document.getElementById("pvP");
								var setValue = 0;
								if(pvP.value==null || pvP.value=='' || isNaN(pvP.value) || pvP.value<0){
									setValue = '0';
								}else{
									setValue = pvP.value;
								}
								
								var orderType = document.getElementsByName("orderTypeW");
								var pv = document.getElementsByName("pvW");
								for(var i=0;i<orderType.length;i++){ 
									if(orderType[i].checked==true && orderType[i].disabled==''){ 
										pv[i].value=setValue; 
									}
								} 
							}
							
							function waiting(){
								disableButton();
								var o = document.createElement('iframe');
								o.id = 'fram_bk';
								document.body.appendChild(o);
								with ($('fram_bk').style){
									display='block';
									top = "60px";
									left = "30%";
								}
								var el=document.getElementById("sending_sub");
								if (el.hasChildNodes()){
								  el.removeChild(el.lastChild);
								}
								
								el.appendChild( document.createTextNode('<jecs:locale key="button.loading"/>'));
								document.getElementById("sending").style.visibility="visible";
								document.getElementById("sending").style.display="block";
							}
						</script>
						
						<v:javascript formName="jpmProductSaleTeamType" cdata="false"
							dynamicJavascript="true" staticJavascript="false" />
						<script type="text/javascript"
							src="<c:url value="/scripts/validator.jsp"/>"></script>
						<script type="text/javascript">
						 	function validateJpmProductSaleTeamType(){
								var type = '${type}';
								var pttId = '${jpmProductSaleTeamType.pttId }';
						 		if(type=='p'){
						 			var checkTeamCode = 0;
									var teamCodeP = document.getElementsByName("teamCodeP");
							 		for(var i=0;i<teamCodeP.length;i++){
										if(teamCodeP[i].checked){
											checkTeamCode++;
										}
								 	}
							 		if(checkTeamCode==0){
										alert('<jecs:locale key="teamtye.productno.check"/>');
										return false;
									}
						 		}
						 		
								if(pttId=='' || pttId == null){
									var orderTypeW = document.getElementsByName("orderTypeW");
									var checkOrderTypeW = 0;
									for(var i=0;i<orderTypeW.length;i++){
										if(orderTypeW[i].checked){
											checkOrderTypeW++;
										}
									}
									if(checkOrderTypeW==0){
										alert('<jecs:locale key="teamtye.productno.check"/>');
										return false;
									}
								}
						 		/*var strAction = '${param.strAction }';
						 		var uniNoId = document.getElementById("uniNoId");
						 		var teamCode = document.getElementById("teamCode"):
						 		alert(strAction);
						 		alert(uniNoId.value);
						 		alert(teamCode.value);*/
						 		waiting();
						 		return true;
						 	}
						</script>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
 </c:otherwise>
</c:choose>