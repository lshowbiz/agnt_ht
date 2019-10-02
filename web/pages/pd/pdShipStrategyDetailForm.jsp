<%@ page pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipStrategyDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdShipStrategyDetailDetail.heading"/></content>
<script>
	function selectWarehouse(str){
		<c:if test="${sessionScope.sessionLogin.isManager}">
			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectAllWarehouse&elementId="+str,"","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
		</c:if>
		<c:if test="${!sessionScope.sessionLogin.isManager}">
			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=600, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
		</c:if>
	}
	
	function selectAllP(){
		var teamCodeP = document.getElementsByName("shipAreaA");
		for(var i=0;i<teamCodeP.length;i++){ 
			teamCodeP[i].checked=true;
		} 		 
	}
	
	function reSelectAllP(){
		var teamCodeP = document.getElementsByName("shipAreaA");
		for(var i=0;i<teamCodeP.length;i++){ 
			if(teamCodeP[i].checked==false){
				teamCodeP[i].checked=true;
			}else if(teamCodeP[i].checked==true){
				teamCodeP[i].checked=false;
			}
		} 		
	}
</script>

<c:choose>
	<c:when test="${uniNoStr!=null && uniNoStr!='' && uniNoStr!='null'}">
		<script type="text/javascript">
			location.href='pdShipStrategyDetails.html?strAction=ssDetailList&uniNoStr=${uniNoStr }&status2=${status2 }&ssId=${ssId }';
		</script>
	</c:when>
	<c:otherwise>
		<c:set var="buttons">
		
				<jecs:power powerCode="${param.strAction}">
				<c:if test="${param.strAction == 'addPdShipStrategyDetail' || param.strAction == 'editPdShipStrategyDetail' && pdShipStrategyDetail.status=='1'}">
					
<%-- 					<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
 --%>			
                 <button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" >
				           <jecs:locale key="operation.button.save"/>
				</button>	
				</c:if>
				</jecs:power>
				<jecs:power powerCode="deletePdShipStrategyDetail">
				<c:if test="${param.strAction == 'editPdShipStrategyDetail' && pdShipStrategyDetail.status=='1'}">
						
<%-- 						<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdShipStrategyDetail')" value="<jecs:locale key="operation.button.delete"/>" />
 --%>				
                            <button type="submit" class="btn btn_sele" name="delete"  onclick="bCancel=true;return confirmDelete(this.form,'PdShipStrategyDetail')" >
							           <jecs:locale key="operation.button.delete"/>
							</button>
				</c:if>
				</jecs:power>
				
<%-- 				<input type="button" class="button" onclick="history.back();" value="<jecs:locale key="operation.button.return"/>" />
 --%>		
                    <button type="button" class="btn btn_sele"  onclick="history.back();" >
					           <jecs:locale key="operation.button.return"/>
					</button>	
		</c:set>
		
		<spring:bind path="pdShipStrategyDetail.*">
		    <c:if test="${not empty status.errorMessages}">
		    <div class="error">    
		        <c:forEach var="error" items="${status.errorMessages}">
		            <img src="<c:url value="/images/iconWarning.gif"/>"
		                alt="<jecs:locale key="icon.warning"/>" class="icon" />
		            <c:out value="${error}" escapeXml="false"/><br />
		        </c:forEach>
		    </div>
		    </c:if>
		</spring:bind>
		
		<form:form commandName="pdShipStrategyDetail" method="post" action="editPdShipStrategyDetail.html" onsubmit="return validatePdShipStrategyDetail(this)" id="pdShipStrategyDetailForm">
		
		<input type="hidden" name="strAction" value="${param.strAction }"/>
		
		<!--fieldset style="padding: 2">
		<legend>
						<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipStrategyDetail')" value="<jecs:locale key="button.delete"/>" />
		        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
		
		</legend-->
		<table class='detail' width="70%">
		<tbody class="window" >
		<form:hidden path="ssdId"/>
			<input type="hidden" name="ssId" id="ssId" value="${param.ssId}" />
		
		<c:if test="${param.strAction == 'editPdShipStrategyDetail'}">
		    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="pdShipStrategyDetail.ssId"/></span>
		    </th>
		    <td>
		       <span class="textbox"><jecs:code listCode="ship.strategy" value="${pdShipStrategyDetail.ssId}" /></span>
		    </td>
		    
			<th>
	            <span class="text-font-style-tc"><jecs:label  key="alStateProvince.stateProvinceName"/></span>
		    </th>
		    <td>
		    	<c:forEach items="${alStateProvinces}" var="area">  
				<c:if test="${pdShipStrategyDetail.shipArea eq area.stateProvinceId}">
		    		<span class="textbox"><label for="${area.stateProvinceCode}">
				       ${area.stateProvinceName}
		    		</label></span> 
	    		</c:if>
	    	</c:forEach>
		    </td>
		    </tr>
		 </c:if>
		 	<c:choose>
				<c:when test="${param.strAction == 'addPdShipStrategyDetail'}">
				    <tr>
				    <%-- <input type="button" class="button" onclick="selectAllP();"
				        value="<jecs:locale  key='button.selectAll'/>"/> --%>
				        &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
				        <button type="button" class="btn btn_sele"  onclick="selectAllP();" >
						           <jecs:locale  key='button.selectAll'/>
						</button>
				        
				       <%--  <input type="button" class="button" onclick="reSelectAllP();"
				        value="<jecs:locale  key='common.reSelectAll'/>"/> --%>
				        
				        <button type="button" class="btn btn_sele"  onclick="reSelectAllP();" >
						           <jecs:locale  key='common.reSelectAll'/>
						</button>
						</tr>
				    <tr class="edit_tr">
				    <th>
				        <span class="text-font-style-tc"><jecs:label  key="alStateProvince.stateProvinceName"/></span>
				    </th>
				    <td colspan="3">
				    	<table>
					    	<c:forEach items="${areaList}" var="area" varStatus="status">
					    		<c:if test="${status.count%6==1}"><tr></c:if>
							        <td style="white-space:nowrap;">
								        <input type="checkbox" value="${area.state_province_id}" id="${area.state_province_id}" name="shipAreaA" />
							    		<label for="${area.state_province_id}" style="white-space:nowrap;">
									        ${area.state_province_name}
							    		</label>
							    	</td>
					    		<c:if test="${status.count%6==0}"></tr></c:if>
					    	</c:forEach>
					    </table>
				    </td></tr>
				         
				        
				</c:when>
				<%-- <c:otherwise>
						<tr  class="edit_tr">
						<th>
				            <span class="text-font-style-tc"><jecs:locale  key="alStateProvince.stateProvinceName"/></span>
					    </th>
					    <td align="left">
					    	<c:forEach items="${alStateProvinces}" var="area">  
							<c:if test="${pdShipStrategyDetail.shipArea eq area.stateProvinceId}">
					    		<span class="textbox"><label for="${area.stateProvinceCode}">
							       ${area.stateProvinceName}
					    		</label></span> 
				    		</c:if>
				    	</c:forEach>
					    </td></tr>
				</c:otherwise> --%>
			</c:choose>
		    <tr  class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="busi.warehouse.warehouseno"/></span>
		    </th>
		    <td>
		        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
		        <c:choose>
		        	<c:when test="${pdShipStrategyDetail.status=='0'}">
		        		<span class="textbox"><form:input path="warehouseNo" id="warehouseNo" cssClass="textbox-text" readonly="true" disabled="true"/></span>
		        	</c:when>
		        	<c:otherwise>
		        		<span class="textbox"><form:input path="warehouseNo" id="warehouseNo" cssClass="textbox-text" readonly="true"/></span>
		        		
<!-- 		        		<input class="button" type="button" value="选择" onclick="selectWarehouse('warehouseNo');">
 -->		        	
                         <button type="button" class="btn btn_sele" onclick="selectWarehouse('warehouseNo');" >
						           选择
						</button>
		        	</c:otherwise>
		        </c:choose>
		    </td>
		
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="pd.shno"/></span>
		    </th>
		    <td>
		        <!--form:errors path="shNo" cssClass="fieldError"/
		        <form:input path="shNo" id="shNo" cssClass="text medium"/>
		        -->
		        <c:choose>
		        	<c:when test="${pdShipStrategyDetail.status=='0'}">
		        		<span class="textbox"><jecs:list listCode="pd.shno" name="shNo" showBlankLine="true" id="shNo" value="${pdShipStrategyDetail.shNo}" defaultValue="" disabled="true" styleClass="textbox-text"/></span>
		        	</c:when>
		        	<c:otherwise>
		        		<span class="textbox"><jecs:list listCode="pd.shno" name="shNo" showBlankLine="true" id="shNo" value="${pdShipStrategyDetail.shNo}" defaultValue=""  styleClass="textbox-text"/></span>
		        	</c:otherwise>
		        </c:choose>
		    </td></tr>
		    <!-- 
		    <tr>
				<th>
					<jecs:label key="jpmProductSaleTeamType.state" />
				</th>
				<td align="left">
					<jecs:list listCode="jmimemberteam.status" name="status" id="status"
						showBlankLine="false" value="${pdShipStrategyDetail.status}"
						defaultValue="1" disabled="disabled"/>
				</td>
			</tr>
			 -->
			 
			 <tr class="edit_tr">
				<td class="command"  colspan="4" align="center">
					<c:out value="${buttons}" escapeXml="false"/>
				</td>
			</tr>
			<%--  <div id="titleBar">
				<c:out value="${buttons}" escapeXml="false"/>
			</div> --%>
			</tbody>
		</table>
	
		<!--/fieldset-->
		
		<!--table class='detail' width="100%">
		    <tr>
		    <td>
		        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipStrategyDetail')" value="<jecs:locale key="button.delete"/>" />
		        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
		    </td></tr>
		</table-->
		</form:form>
</c:otherwise>
</c:choose>
<script type="text/javascript">
    Form.focusFirstElement($('pdShipStrategyDetailForm'));
</script>

<v:javascript formName="pdShipStrategyDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
