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
			<B style="font-size: 23px"><jecs:locale key="report.title.pdEnterWarehouse" />
			</B>
		</div>
		<br />
		<tr>
			<td nowrap>
				<strong style="font-size: 13px"><jecs:locale key="pdEnterWarehouse.ewNo" />
				</strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px">${pdEnterWarehouse.ewNo}</strong>
			</td>
			<td nowrap>
				<strong style="font-size: 13px"><jecs:locale key="prRefund.createTime" />
				</strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px"><fmt:formatDate
						value="${pdEnterWarehouse.createTime}" pattern="yyyy-MM-dd" />
				</strong>
			</td>
			<td nowrap>
				<strong style="font-size: 13px"><jecs:locale key="busi.pd.enterWarehouseNo" />
				</strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px">${pdEnterWarehouse.warehouseNo}</strong>
			</td>
		</tr>
	</table>
	<table width="720px" cellpadding="3" cellspacing="1" class="detail">
		<tr>
			<td>
				<strong style="font-size: 13px"><jecs:locale key="bdBounsDeduct.createName" /></strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px">${pdEnterWarehouse.createUsrCode}</strong>
			</td>
			<td nowrap="nowrap">
				<strong style="font-size: 13px"><jecs:locale key="busi.pd.weight" /></strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px"><fmt:formatNumber type="number" value='${weight}'
						pattern="##0.00" /> </strong>kg
			</td>
			<td nowrap="nowrap">
				<strong style="font-size: 13px"><jecs:locale key="busi.pd.volume" /></strong>
			</td>
			<td align="left" width="120px">
				<strong style="font-size: 13px"><fmt:formatNumber type="number" value='${volume}'
						pattern="##0.000" /> </strong>m*m*m
			</td>
		</tr>

		<tr>
			<td colspan="3">
				<strong style="font-size: 15px"><jecs:locale key="pmProduct.productName" />
				</strong>
			</td>
			<td nowrap>
				<strong style="font-size: 15px"><jecs:locale
							key="busi.pd.orderqty" />
				</strong>
			</td>
			<td nowrap>
				<strong style="font-size: 15px"><jecs:locale key="busi.pd.sendqty" />
				</strong>
			</td>
			<!-- 
			<td nowrap>
				<strong style="font-size: 15px"><jecs:locale key="busi.pd.shipqty" />
				</strong>
			</td>
			 -->
			<td nowrap>
				<strong style="font-size: 15px"><jecs:locale
							key="busi.pd.acceptqty" />
				</strong>
			</td>
		</tr>
		<c:forEach items="${pdEnterWarehouse.pdEnterWarehouseDetails}"
			var="pdEnterWarehouseDetail" varStatus="status">
			<tr>
				<td colspan="3" align="left">
					<strong style="font-size: 15px">${pdEnterWarehouseDetail.productNo}-${compamyProductMap[pdEnterWarehouseDetail.productNo]}</strong>
				</td>
				<td>
					<strong style="font-size: 15px">${pdEnterWarehouseDetail.qty}</strong>
				</td>
				<td>
					<strong style="font-size: 15px">&nbsp;</strong>
				</td>
				<!-- 
				<td>
					<strong style="font-size: 15px">&nbsp;</strong>
				</td>
				 -->
				<td>
					<strong style="font-size: 15px">&nbsp;</strong>
				</td>
			</tr>
		</c:forEach>
	</table>
	<table width="720px" cellpadding="3" cellspacing="1" class="detail">
		<tr>
			<td>
				<jecs:locale key="busi.common.remark" />
			</td>
			<td colspan="5">
				${pdEnterWarehouse.remark}
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