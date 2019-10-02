<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="prRefundDetail.title"/></title>
<content tag="heading"><jecs:locale key="prRefundDetail.heading"/></content>

<c:if test="${complete!=null}">
	<script type="text/javascript">
		history.back();
	</script>
</c:if>

<c:set var="buttons">
	<jecs:power powerCode="refundPrRefund">
		<button type="button" class="btn btn_sele" name="handButton" onclick="pdRefund(document.prRefundForm)"  >
			<jecs:locale key="button.submit"/>
		</button>
	</jecs:power>
	<button type="button"  class="btn btn_sele" name="back" onclick="history.back()">
		<jecs:locale key="operation.button.return"/>
	</button>
</c:set>

<form:form commandName="jprRefund" method="post" action="editJprRefundRefund.html" onsubmit="return validatePrRefund(this)" id="prRefundForm" name="prRefundForm">

<input type="hidden" name="strAction" value="refundPrRefund"/>
<input type="hidden" name="roNo" value="${jprRefund.roNo }"/>

<table class='detail' width="70%" >
  <tbody class="window" >
    <tr class="edit_tr">
    
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="prReFund.reFundOrderNo"/></span></th>
			<td><span class="textbox">${jprRefund.roNo}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="poMemberOrder.memberOrderNo"/></span></th>
			<td><span class="textbox">${jprRefund.jpoMemberOrder.memberOrderNo}</span></td>
		</tr>
		<tr class="edit_tr">
			<th> <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span></th>
			<td><span class="textbox">${jprRefund.sysUser.jmiMember.userCode}-${jprRefund.sysUser.jmiMember.petName}-<jecs:code listCode="member.level" value="${jprRefund.sysUser.jmiMember.memberLevel}"/></span></td>
		
			<th> <span class="text-font-style-tc"><span class="req">*</span><jecs:label  key="busi.orderReturn.warehouseNo"/></span></th>
			<td><span class="textbox">${jprRefund.resendSpNo}</span></td>
		</tr>
		<tr class="edit_tr">
			<th> <span class="text-font-style-tc"><jecs:label  key="poMemberOrder.pvAmt"/></span></th>
			<td><span class="textbox">${jprRefund.pvAmt}</span></td>
		
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.order.amount"/></span></th>
			<td><span class="textbox">${jprRefund.amount}</span></td>
		</tr>
		<tr class="edit_tr">
			    <th> <span class="text-font-style-tc"><jecs:label  key="jprrefund.type"/></span></th>
			    <td><span class="textbox">
			    <jecs:list styleClass="textbox-text" name="refundTye" listCode="jprrefund.refundtype" defaultValue="0" value="${jprRefund.refundTye}"  id="refundTye"></jecs:list></span>
			    </td>
		  
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.order.createTime"/></span></th>
			<td><span class="textbox">
        		<fmt:formatDate value="${jprRefund.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.createUNo }"/></span>
        	</td>
		</tr>
		<tr class="edit_tr">
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.order.editTime"/></span></th>
			<td><span class="textbox">
	    		<fmt:formatDate value="${jprRefund.editTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.editUNo }"/></span>
	        </td>
		
			<th> <span class="text-font-style-tc"><jecs:label  key="prRefund.remark"/></span></th>
			<td><span class="textbox">${jprRefund.remark}</span></td>
		</tr>
		<tr class="edit_tr">
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.intoStatus"/></span></th>
			<td><span class="textbox"><jecs:code listCode="pr.into" value="${jprRefund.intoStatus }"/> - <fmt:formatDate value="${ jprRefund.intoTime}" pattern="yyyy-MM-dd"/> - ${jprRefund.intoUNo}</span></td>
		
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.intoRemark"/></span></th>
			<td><span class="textbox">${jprRefund.intoRemark}</span></td>
		</tr>
		<tr class="edit_tr">
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.order.intoTime"/></span></th>
			<td><span class="textbox">
        		<fmt:formatDate value="${jprRefund.intoTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.intoUNo }"/></span>
        	</td>
			
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.intoRemark"/></span></th>
			<td><span class="textbox">${jprRefund.intoRemark}</span></td>
		</tr>
		<c:if test="${jprRefund.locked=='N' }">
		<tr class="edit_tr">
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.refundStatus"/></span></th>
			<td><span class="textbox"><jecs:list styleClass="textbox-text" name="refundStatus" listCode="pr.refund" value="${jprRefund.refundStatus}" defaultValue="1"/></span></td>
		
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.order.refundTime"/></span></th>
			<td><span class="textbox">
        		<fmt:formatDate value="${jprRefund.refundTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.refundUNo }"/></span>
        	</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc-tare"><jecs:label  key="busi.orderReturn.refundRemark"/></span></th>
			<td><span class="text-font-style-tc-right">
			<textarea cssClass="textarea_border" name="refundRemark" rows="3" cols="18"/>${jprRefund.refundRemark}</textarea>
			</span></td>
		
		</c:if>
		<c:if test="${jprRefund.locked=='Y' }">
		<tr>
			<th><span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.refundStatus"/></span></th>
			<td><span class="textbox"><jecs:code listCode="pr.refund" value="${jprRefund.refundStatus }"/> - <fmt:formatDate value="${ jprRefund.refundTime}" pattern="yyyy-MM-dd"/> - ${jprRefund.refundUNo}</span></td>
		</tr>
		<tr>
			<th> <span class="text-font-style-tc"><jecs:label  key="busi.order.refundTime"/></span></th>
			<td><span class="textbox">
        		<fmt:formatDate value="${jprRefund.refundTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.refundUNo }"/></span>
        	</td>
		</tr>
		<tr class="edit_tr">
			<th> <span class="text-font-style-tc"><jecs:label  key="prRefund.refundRemark"/></span></th>
			<td><span class="textbox">${jprRefund.refundRemark}</span></td>
		</tr>
		</c:if>
		
		<!-- modify gw 2014-11-03  begin-->
			<tr class="edit_tr">
			    <th> <span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeOne"/></span></th>
			    <td><span class="textbox">
			    ${jprRefund.repairFee}</span>
			   <!--   <input name="repairFee" value="${jprRefund.repairFee}" id="repairFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/> --> 
			    </td>                
		
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeTwo"/></span></th>
			    <td><span class="textbox">
			              ${jprRefund.renovationFee}  </span>
			           <!--     <input name="renovationFee" value="${jprRefund.renovationFee}" id="renovationFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>-->
			    </td>
		   </tr>
		  <tr class="edit_tr">
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeThree"/></span></th>
			    <td><span class="textbox">
			               ${jprRefund.logisticsFee}</span>
			            <!--    <input name="logisticsFee" value="${jprRefund.logisticsFee}" id="logisticsFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>-->
			    </td>
		  
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeFour"/></span></th>
			    <td><span class="textbox">
			               ${jprRefund.settledBonus}</span>
			    	      <!--  <input name="settledBonus" value="${jprRefund.settledBonus}" id="settledBonus" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>-->
			    </td>
		   </tr>
		  <tr class="edit_tr">
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeFive"/></span></th>
			    <td><span class="textbox">
			                ${jprRefund.fillFreight}</span>
			    			<!-- <input name="fillFreight" value="${jprRefund.fillFreight}" id="fillFreight" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>-->
			    </td>
		   </tr>
		   <!-- 
		   <tr>
			    <th><jecs:label  key="jprRefund.logisticsFeeSix"/></th>
			    <td align="left">
			               <input name="logisticsFeeSix" value="${jprRefund.logisticsFeeSix}" id="logisticsFeeSix" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </td>
		   </tr>
		   <tr>
			    <th><jecs:label  key="jprRefund.logisticsFeeSeven"/></th>
			    <td align="left">
			              <input name="logisticsFeeSeven" value="${jprRefund.logisticsFeeSeven}" id="logisticsFeeSeven" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </td>
		   </tr>
		   <tr>
			    <th><jecs:label  key="jprRefund.logisticsFeeEight"/></th>
			    <td align="left">
			              <input name="logisticsFeeEight" value="${jprRefund.logisticsFeeEight}" id="logisticsFeeEight" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </td>
		   </tr>
		    -->
			<!-- modify gw 2014-11-03  end-->
		   
		   
		
    <tr>
		<%-- <th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
		<td class="command">
			<c:out value="${buttons}" escapeXml="false"/>
		</td> --%>
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

    function pdRefund(theForm){
            waiting();
			theForm.submit();
    }
</script>