<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfoList.title"/></title>
<content tag="heading"><jecs:locale key="pdSendInfoList.heading"/></content>
<meta name="menu" content="PdSendInfoMenu"/>






<div id="titleBar" class="noPrint">
	


<input type="button" name="btnPrint" value="<jecs:locale key="button.print"/>" class="button" onclick="go_print()"/>
		
</div>
<div align="center">
  
  <table width="720px"  cellpadding="3" cellspacing="1" class="detail">
    	<div align="center"><B><jecs:locale key="report.title.pdAdjustStock"/></B></div><br/>
      <tr>
    <td><strong><jecs:locale key="pdAdjustStock.asNo"/></strong></td>
    <td align="left" width="120px"><strong>${pdAdjustStock.asNo}</strong></td>
    <td><strong><jecs:locale key="prRefund.createTime"/></strong></td>
    <td align="left" width="120px"><strong><fmt:formatDate value="${pdAdjustStock.createTime}" pattern="yyyy-MM-dd"/></strong></td>
    <td><strong><jecs:locale key="busi.warehouse.warehouseno"/></strong></td>
    <td align="left" width="120px"><strong>${pdAdjustStock.warehouseNo}</strong></td>
  </tr>
  <tr>
        <td><jecs:locale key="bdBounsDeduct.createName"/></td>
        <td align="left" width="120px"><strong>${pdAdjustStock.createUsrCode}</strong></td>
        <td nowrap="nowrap"><jecs:locale key="busi.pd.weight"/></td>
        <td align="left" width="120px"><strong>
        	<fmt:formatNumber type="number" value='${weight}' pattern="##0.00"/>
        	  </strong>kg</td>
        <td nowrap="nowrap"><jecs:locale key="busi.pd.volume"/></td>
        <td align="left" width="120px"><strong>
        	<fmt:formatNumber type="number" value='${volume}' pattern="##0.000"/>
        	</strong>m*m*m</td>
  </tr>
      
  <tr>
      	<td colspan="2"><strong><jecs:locale key="pmProduct.productName"/></strong></td>
      	<td ><strong><strong><jecs:locale key="busi.pd.orderqty"/></strong></td>
      	<td ><strong><strong><jecs:locale key="busi.pd.sendqty"/></strong></td>
      	<td ><strong><strong><jecs:locale key="busi.pd.shipqty"/></strong></td>
      	<td ><strong><strong><jecs:locale key="busi.pd.acceptqty"/></strong></td>
      </tr>
  <c:forEach items="${pdAdjustStock.pdAdjustStockDetails}" var="pdAdjustStockDetail" varStatus="status">
  <tr>
    <td  colspan="2">${pdAdjustStockDetail.productNo}-${compamyProductMap[pdAdjustStockDetail.productNo]}</td>
    <td >${pdAdjustStockDetail.qty}</td>
    <td ><strong>&nbsp;</strong></td>
    <td ><strong>&nbsp;</strong></td>
    <td ><strong>&nbsp;</strong></td>
  </tr>
	</c:forEach>
  <tr>
    <td><jecs:locale key="busi.common.remark"/></td>
    <td colspan="5">${pdAdjustStock.remark}</td>
  </tr>
   <tr>
        <td><jecs:locale key="busi.sign.sender"/></td>
        <td align="left" width="120px"><strong>&nbsp</strong></td>
        <td><jecs:locale key="busi.sign.logistics"/></td>
        <td align="left" width="120px"><strong>&nbsp;</strong></td>
        <td><jecs:locale key="busi.sign.consignee"/></td>
        <td align="left" width="120px"><strong>&nbsp;</strong></td>
      </tr>
  </table>
  
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