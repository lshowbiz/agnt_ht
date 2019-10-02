<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="prRefundDetail.title"/></title>
<content tag="heading"><jecs:locale key="prRefundDetail.heading"/></content>

<c:if test="${complete!=null}">
	<script type="text/javascript">
		history.back();
	</script>
</c:if>

<c:set var="buttons">
	<button type="button"  class="btn btn_sele" name="back"  onclick="history.back()">
		<jecs:locale key="operation.button.return"/>
	</button>
</c:set>

	<input type="hidden" name="roNo" value="${jprRefund.roNo }"/>

<table class='detail' width="70%" >
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
			<th><span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.warehouseNo"/></span></th>
			<td><span class="textbox">${jprRefund.resendSpNo}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="poMemberOrder.pvAmt"/></span></th>
			<td><span class="textbox">${jprRefund.pvAmt}</span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="busi.order.amount"/></span></th>
			<td><span class="textbox">${jprRefund.amount}</span></td>
		
			    <th><span class="text-font-style-tc"><jecs:label  key="jprrefund.type"/></span></th>
			    <td><span class="textbox">
			    <jecs:code listCode="jprrefund.refundtype" value="${jprRefund.refundTye}"></jecs:code>
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
		
			<th><span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.intoStatus"/></span></th>
			<td><span class="textbox"><jecs:code listCode="pr.into" value="${jprRefund.intoStatus }"/> - <fmt:formatDate value="${ jprRefund.intoTime}" pattern="yyyy-MM-dd"/> - ${jprRefund.intoUNo}</span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.intoRemark"/></span></th>
			<td><span class="textbox">${jprRefund.intoRemark}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.refundStatus"/></span></th>
			<td><span class="textbox"><jecs:code listCode="pr.refund" value="${jprRefund.refundStatus }"/> - <fmt:formatDate value="${ jprRefund.refundTime}" pattern="yyyy-MM-dd"/> - ${jprRefund.refundUNo}</span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="busi.orderReturn.refundRemark"/></span></th>
			<td><span class="textbox">${jprRefund.refundRemark}</span></td>
			
		
		<!-- modify gw 2014-11-03  begin-->
			
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeOne"/></span></th>
			    <td><span class="textbox">${jprRefund.repairFee}</span></td>
		  </tr>	   
		 <tr class="edit_tr">
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeTwo"/></span></th>
			    <td><span class="textbox">${jprRefund.renovationFee}</span></td>
		   
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeThree"/></span></th>
			    <td><span class="textbox">${jprRefund.logisticsFee}</span></td>
		   </tr>
		  <tr class="edit_tr">
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeFour"/></span></th>
			    <td><span class="textbox">${jprRefund.settledBonus}</span></td>
		   
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeFive"/></span></th>
			    <td><span class="textbox">${jprRefund.fillFreight}</span></td>
		   </tr>
		   <!--  
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeSix"/></span></th>
			    <td><span class="textbox">${jprRefund.logisticsFeeSix}</span></td>
		   </tr>
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeSeven"/></span></th>
			    <td><span class="textbox">${jprRefund.logisticsFeeSeven}</span></td>
		   </tr>
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeEight"/></span></th>
			    <td><span class="textbox">${jprRefund.logisticsFeeEight}</span></td>
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
    			<ec:column property="jpmProductSaleTeamType.jpmProductSaleNew.productName" title="pmProduct.productName"/>
    			<ec:column property="qty" title="pd.qty" styleClass="numberColumn"/>
    			<ec:column property="price" title="pd.price" styleClass="centerColumn" />
    			<ec:column property="pv" title="busi.direct.pv" styleClass="centerColumn" />
				</ec:row>
</ec:table>