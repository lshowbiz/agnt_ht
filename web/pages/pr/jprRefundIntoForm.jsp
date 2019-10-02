<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="prRefundDetail.title"/></title>
<content tag="heading"><jecs:locale key="prRefundDetail.heading"/></content>

<c:if test="${complete!=null}">
	<script type="text/javascript">
		history.back();
	</script>
</c:if>

<c:set var="buttons">
	<jecs:power powerCode="intoPrRefund">
		<button type="submit" class="btn btn_sele" name="save" >
			<jecs:locale key="operation.button.save"/>
		</button>
	</jecs:power>
	<button type="button"  class="btn btn_sele" name="back" onclick="history.back()">
		<jecs:locale key="operation.button.return"/>
	</button>
</c:set>

<spring:bind path="jprRefund">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jprRefund" method="post" action="editJprRefundInto.html" onsubmit="if(isFormPosted()){return true;}{return false;}" id="prRefundForm">

	<input type="hidden" name="roNo" value="${jprRefund.roNo }"/>
	<input type="hidden" name="strAction" value="${param.strAction }"/>
	
<table class='detail' width="70%">
	<tbody class="window" >
  
    <tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="prReFund.reFundOrderNo"/></span></th>
			<td><span class="textbox">${jprRefund.roNo}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="prRefund.createTime"/></span></th>
			<td><span class="textbox"><fmt:formatDate value="${ jprRefund.createTime}" pattern="yyyy-MM-dd"/> - ${jprRefund.createUNo}</span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="poMemberOrder.memberOrderNo"/></span></th>
			<td><span class="textbox">${jprRefund.jpoMemberOrder.memberOrderNo}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span></th>
			<td><span class="textbox">${jprRefund.sysUser.jmiMember.userCode}-${jprRefund.sysUser.jmiMember.petName}-<jecs:code listCode="member.level" value="${jprRefund.sysUser.jmiMember.memberLevel}"/></span></td>
		</tr>
		 <tr class="edit_tr">
			<th><span class="text-font-style-tc"><span class="req">*</span><jecs:label  key="busi.orderReturn.warehouseNo"/></span></th>
			<td><span class="textbox">${jprRefund.resendSpNo}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="poMemberOrder.pvAmt"/></span></th>
			<td><span class="textbox">${jprRefund.pvAmt}</span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="busi.order.amount"/></span></th>
			<td><span class="textbox">${jprRefund.amount}</span></td>
		
			    <th><span class="text-font-style-tc"><jecs:label  key="jprrefund.type"/></span></th>
			    <td><span class="textbox">
			    <jecs:list styleClass="textbox-text"  name="refundTye" listCode="jprrefund.refundtype" defaultValue="0" value="${jprRefund.refundTye}" id="refundTye"></jecs:list>
			    </span></td>
		   </tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="busi.order.createTime"/></span></th>
			<td><span class="textbox">
        		<fmt:formatDate value="${jprRefund.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.createUNo }"/>
        	</span></td>
	
			<th><span class="text-font-style-tc"><jecs:label  key="busi.order.editTime"/></span></th>
			<td><span class="textbox">
	    		<fmt:formatDate value="${jprRefund.editTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.editUNo }"/>
	        </span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="prRefund.remark"/></span></th>
			<td><span class="textbox">${jprRefund.remark}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="busi.order.intoTime"/></span></th>
			<td><span class="textbox">
        		<fmt:formatDate value="${jprRefund.intoTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.intoUNo }"/>
        	</span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.intoRemark"/></span></th>
			<td><span class="textbox">${jprRefund.intoRemark}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.intoStatus"/></span></th>
			<td><span class="textbox"><jecs:list styleClass="textbox-text" name="intoStatus" listCode="pr.into" value="${jprRefund.intoStatus}" defaultValue="1"/></span></td>
		</tr>
		<tr class="edit_tr">
			<td><span class="text-font-style-tc-tare"><jecs:label  key="busi.orderReturn.intoRemark"/></span></td>
			<td><span class="text-font-style-tc-right">
			<textarea cssClass="textarea_border" name="intoRemark" rows="3" cols="18"/>${jprRefund.intoRemark}</textarea>
			</span></td>
		
		<!-- modify gw 2014-11-03  begin-->
			
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeOne"/></span></th>
			    <td><span class="textbox">
			    ${jprRefund.repairFee}
			   <!--   <input name="repairFee" value="${jprRefund.repairFee}" id="repairFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/> --> 
			    </span></td>                
		   </tr>	   
		  <tr class="edit_tr">
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeTwo"/></span></th>
			    <td><span class="textbox">
			              ${jprRefund.renovationFee}   
			           <!--     <input name="renovationFee" value="${jprRefund.renovationFee}" id="renovationFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>-->
			    </span></td>
	
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeThree"/></span></th>
			    <td><span class="textbox">
			               ${jprRefund.logisticsFee}
			            <!--    <input name="logisticsFee" value="${jprRefund.logisticsFee}" id="logisticsFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>-->
			    </span></td>
		   </tr>
		   <tr class="edit_tr">
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeFour"/></span></th>
			    <td><span class="textbox">
			               ${jprRefund.settledBonus}
			    	      <!--  <input name="settledBonus" value="${jprRefund.settledBonus}" id="settledBonus" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>-->
			    </span></td>
		   
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeFive"/></span></th>
			    <td><span class="textbox">
			                ${jprRefund.fillFreight}
			    			<!-- <input name="fillFreight" value="${jprRefund.fillFreight}" id="fillFreight" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>-->
			    </span></td>
		   </tr>
		   <!-- 
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeSix"/></span></th>
			    <td><span class="textbox">
			               <input name="logisticsFeeSix" value="${jprRefund.logisticsFeeSix}" id="logisticsFeeSix" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   </tr>
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeSeven"/></span></th>
			    <td><span class="textbox">
			              <input name="logisticsFeeSeven" value="${jprRefund.logisticsFeeSeven}" id="logisticsFeeSeven" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   </tr>
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeEight"/></span></th>
			    <td><span class="textbox">
			              <input name="logisticsFeeEight" value="${jprRefund.logisticsFeeEight}" id="logisticsFeeEight" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   </tr>
		    -->
			<!-- modify gw 2014-11-03  end-->
		   
		   
		
    <tr>
		<%-- <th class="command"><jecs:label key="sysOperationLog.moduleName" /></th> --%>
		<td class="command" colspan="4" align="center">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>
	</tbody>
</table>
<ec:table
	tableId="sellStock"
	items="jprRefund.jprReFundList"
	var="pi"
	width="100%" form = "prRefundForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row>
		
    			<ec:column property="jpmProductSaleTeamType.jpmProductSaleNew.jpmProduct.productNo" title="busi.product.productno"/>
    			<ec:column property="jpmProductSaleTeamType.jpmProductSaleNew.productName" title="pmProduct.productName"/>
    			<ec:column property="qty" title="pd.qty" styleClass="numberColumn"/>
    			<ec:column property="price" title="pd.price" styleClass="centerColumn" />
    			<ec:column property="pv" title="busi.direct.pv" styleClass="centerColumn" />
				</ec:row>
</ec:table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('prRefundForm'));

    function selectWarehouse(elementId){
		var winName="<jecs:locale key="menu.pd.listPdWarehouses"/>";
		var theURL="pdWarehouses.html?strAction=selectWarehouse&elementId="+elementId;
		window.open(theURL,winName,'scrollbars=yes,width=650,height=400');
     }
</script>