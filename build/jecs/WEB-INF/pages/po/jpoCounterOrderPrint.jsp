<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfoList.title"/></title>
<content tag="heading"><jecs:locale key="pdSendInfoList.heading"/></content>
<meta name="menu" content="PdSendInfoMenu"/>






<div id="titleBar" class="noPrint">
	


<input type="button" name="btnPrint" value="<jecs:locale key="button.print"/>" class="button" onclick="go_print()"/>
		
</div>
<div align="center">
  
  <table width="720px"  cellpadding="3" cellspacing="1" border="0" class="detail">
    	<div align="center"><B><jecs:locale key="report.title.poCounterOrder"/></B></div><br/>
      <tr>
        <td><jecs:locale key="busi.order.orderno"/></td>
        <td align="left" width="120px"><strong>${poCounterOrder.coNo}</strong></td>
        <td><jecs:locale key="prRefund.createTime"/></td>
        <td align="left" width="120px"><fmt:formatDate value="${poCounterOrder.createTime}" pattern="yyyy-MM-dd"/></td>
        <td><jecs:locale key="pdSendInfo.sendWarehouseNo"/></td>
        <td align="left" width="120px"><strong>${poCounterOrder.warehouseNo}</strong></td>
      </tr>
      
      
      
      <tr>
        <td><jecs:locale key="pdSendInfo.recipientName"/></td>
        <td align="left" colspan="3" ><strong>${poCounterOrder.sysUser.userCode}-${poCounterOrder.sysUser.userName}</strong></td>
        <td><jecs:locale key="busi.order.amount"/></td>
        <td align="left" width="120px"><strong>${poCounterOrder.amount}</strong></td>
        
      </tr>
      
      
      
      <tr>
      	<td colspan="6"><jecs:locale key="busi.pdSendInfo.detail"/></td>
      </tr>
      <tr>
      	<td colspan="3"><strong><jecs:locale key="pmProduct.productName"/></strong></td>
      	
      	<td ><strong><jecs:locale key="busi.pd.orderqty"/></strong></td>
      		<td ><strong><jecs:locale  key="pd.price"/></strong></td>
      	<td ><strong><jecs:locale key="busi.order.amount"/></strong></td>
      
      </tr>
      <c:forEach items="${poCounterOrder.jpoCounterOrderDetails}" var="poCounterOrderDetail" varStatus="status">
      	<c:if test="${poCounterOrderDetail.qty >0 }">
      	<tr>
      	<td colspan="3"><strong>${poCounterOrderDetail.jpmProductSaleNew.jpmProduct.productNo}-${poCounterOrderDetail.jpmProductSaleNew.productName}</strong></td>
      	
      	<td ><strong>${poCounterOrderDetail.qty}</strong></td>
      	<td ><strong>${poCounterOrderDetail.price}</strong></td>
      	<td ><strong><fmt:formatNumber type="currency" value="${poCounterOrderDetail.qty * poCounterOrderDetail.price}"/></strong></td>
      </tr>
   		 </c:if>
    	</c:forEach>
    	
    	 <tr>
        <td><jecs:locale key="busi.sign.sender"/></td>
        <td align="left" width="120px"><strong>&nbsp;</strong></td>
        <td><jecs:locale key="busi.sign.logistics"/></td>
        <td align="left" width="120px"><strong>&nbsp;</strong></td>
        <td><jecs:locale key="busi.sign.consignee"/></td>
        <td align="left" width="120px"><strong>&nbsp;</strong></td>
      </tr>
  </table>
  <br/>
  <table width="720px"  cellpadding="3" cellspacing="0" >
  		<tr><td align="right">${sessionLogin.userName}--<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:MM:SS"/>
  				
  			</td></tr>
  </table>
  
</div>
<div id='details'>
	
</div>

<script type="text/javascript">
function go_print () {
    $('titleBar').style.visibility="hidden";
    window.print();
    window.close();
  }
  
 
</script>