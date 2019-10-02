<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfoList.title" />
</title>
<content tag="heading">
<jecs:locale key="pdSendInfoList.heading" />
</content>
<meta name="menu" content="PdSendInfoMenu" />






<div id="titleBar" class="noPrint">



	<input type="button" name="btnPrint"
		value="<jecs:locale key="button.print"/>" class="button"
		onclick=
	go_print();
/>

</div>
<div align="center">

	<table width="720px" cellpadding="3" cellspacing="1" class="detail">
		<div align="center">
			<B style="font-size: 23px"><jecs:locale
					key="report.title.pdFlitWarehouse" />
			</B>
		</div>
		<br />
		<tr>
			<td>
				<strong style="font-size: 13px"><jecs:locale key="pdFlitWarehouseDetail.fwNo" />
				</strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px">${pdFlitWarehouse.fwNo}</strong>
			</td>
			<td>
				<strong style="font-size: 13px"><jecs:locale key="prRefund.createTime" />
				</strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px"><fmt:formatDate
						value="${pdFlitWarehouse.createTime}" pattern="yyyy-MM-dd" />
				</strong>
			</td>
			<td>
				<strong style="font-size: 13px"><jecs:locale key="bdBounsDeduct.createName" />
				</strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px">${pdFlitWarehouse.createUsrCode}</strong>
			</td>
		</tr>

		<tr>

			<td>
				<strong style="font-size: 13px"><jecs:locale key="busi.pd.enterWarehouseNo" />
				</strong>
			</td>
			<td colspan="2" align="left" width="120px">
				<strong style="font-size: 13px">${pdFlitWarehouse.inWarehouse.warehouseNo}</strong>
			</td>
			<td>
				<strong style="font-size: 13px"><jecs:locale key="pdOutWarehouse.warehouseNo" />
				</strong>
			</td>
			<td colspan="2" align="left" width="120px">
				<strong style="font-size: 13px">${pdFlitWarehouse.outWarehouse.warehouseNo}</strong>
			</td>
		</tr>
	</table>



	<table width="720px" cellpadding="3" cellspacing="1" class="detail">
		<tr>
			<td colspan="3">
				<strong style="font-size: 15px"><jecs:locale
						key="pmProduct.productName" />
				</strong>
			</td>
			<td>
				<strong style="font-size: 15px"><jecs:locale
						key="busi.pd.orderqty" />
				</strong>
			</td>
			<td>
				<strong style="font-size: 15px"><jecs:locale
						key="busi.pd.sendqty" />
				</strong>
			</td>
			<!-- 
			<td>
				<strong style="font-size: 15px"><jecs:locale
						key="busi.pd.shipqty" />
				</strong>
			</td>
			-->
			<td>
				<strong style="font-size: 15px"><jecs:locale
						key="busi.pd.acceptqty" />
				</strong>
			</td>
		</tr>
		<c:forEach items="${pdFlitWarehouse.pdFlitWarehouseDetails}"
			var="pdFlitWarehouseDetail" varStatus="status">
			<tr>
				<td colspan="3" align="left">
					<strong style="font-size: 15px">${pdFlitWarehouseDetail.productNo}-${compamyProductMap[pdFlitWarehouseDetail.productNo]}</strong>
				</td>
				<td>
					<strong style="font-size: 15px">${pdFlitWarehouseDetail.qty}</strong>
				</td>
				<td style="font-size: 15px">
					<strong>&nbsp;</strong>
				</td>
				<!-- 
				<td style="font-size: 15px">
					<strong>&nbsp;</strong>
				</td>
				 -->
				<td style="font-size: 15px">
					<strong>&nbsp;</strong>
				</td>
			</tr>
		</c:forEach>
	</table>
	<table width="720px" cellpadding="3" cellspacing="1" class="detail">
		<tr>
			<td>
				<jecs:locale key="pdSendInfo.recipientName" />
				:
			</td>
			<td align="left">
				${pdFlitWarehouse.recipientName}
			</td>
			<td>
				<jecs:locale key="aiAgent.bossPhone1" />
				:
			</td>
			<td align="left">
				${pdFlitWarehouse.recipientPhone}
			</td>
			<td>
				<jecs:locale key="aiAgent.bossPhone2" />
				:
			</td>
			<td align="left">
				${pdFlitWarehouse.recipientMobile}
			</td>
		</tr>
		<tr>
			<td>
				<jecs:locale key="busi.address" />
				:
			</td>
			<td colspan="5" align="left">
				${pdFlitWarehouse.recipientAddr}
			</td>
		</tr>
		<tr>
			<td>
				<jecs:locale key="busi.common.remark" />
			</td>
			<td colspan="5">
				${pdFlitWarehouse.remark}
			</td>
		</tr>
		<tr>
			<td>
				<jecs:locale key="busi.sign.sender" />
			</td>
			<td align="left" width="120px">
				<strong>&nbsp</strong>
			</td>
			<td>
				<jecs:locale key="busi.sign.logistics" />
			</td>
			<td align="left" width="120px">
				<strong>&nbsp;</strong>
			</td>
			<td>
				<jecs:locale key="busi.sign.consignee" />
			</td>
			<td align="left" width="120px">
				<strong>&nbsp;</strong>
			</td>
		</tr>
	</table>

	<table width="720px" cellpadding="3" cellspacing="0">
		<tr>
			<td align="right">
				${sessionLogin.userName}--
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:MM:SS" />

			</td>
		</tr>
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