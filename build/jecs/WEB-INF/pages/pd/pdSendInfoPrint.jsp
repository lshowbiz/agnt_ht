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
	go_print();;
/>

</div>
<!--  
<div align="center" style="visibility: hidden;">

	<table width="720px" cellpadding="3" cellspacing="1" class="detail">
		<div align="center">
			<B><jecs:locale key="report.title.pdSendInfo" /> </B>
		</div>
		<br />
		<tr>
			<td nowrap="nowrap">
				<jecs:locale key="pdSendInfo.siNo" />
			</td>
			<td align="left" width="120px">
				<strong>${pdSendInfo.siNo}</strong>
			</td>
			<td nowrap="nowrap">
				<jecs:locale key="busi.order.orderno" />
			</td>
			<td align="left" width="120px">
				<strong>${pdSendInfo.orderNo}</strong>
			</td>
			<td nowrap="nowrap">
				<jecs:locale key="pdSendInfo.sendWarehouseNo" />
			</td>
			<td align="left" width="120px">
				<strong>${pdSendInfo.warehouseNo}</strong>
			</td>
		</tr>
		<tr>


			<td nowrap="nowrap">
				<jecs:locale key="pd.shno" />
			</td>
			<td colspan="5" align="left">
				<strong><jecs:code listCode="pd.shno"
						value="${pdSendInfo.shNo}" /> </strong>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap">
				<jecs:locale key="pdSendInfo.recipientName" />
			</td>
			<td colspan="2" align="left">
				<strong>${pdSendInfo.recipientName}</strong>
			</td>
			<td nowrap="nowrap">
				<jecs:locale key="customerRecord.customerName" />
			</td>
			<td colspan="2" align="left">
				<strong>${pdSendInfo.customer.userCode}/${pdSendInfo.customer.userName}</strong>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap">
				<jecs:locale key="pdSendInfo.recipientPhone" />
			</td>
			<td align="left" width="120px">
				<strong>${pdSendInfo.recipientPhone}/${pdSendInfo.recipientMobile}</strong>
			</td>

			<td nowrap="nowrap">
				<jecs:locale key="pdSendInfo.recipientAddr" />
			</td>
			<td colspan="3" align="left">
				<strong>${alStateProvinceMap[pdSendInfo.recipientCaNo]}${alCityMap[pdSendInfo.city]}${pdSendInfo.recipientAddr}</strong>
			</td>
		</tr>


		<tr>
			<td nowrap="nowrap">
				<jecs:label key="busi.common.remark" />
			</td>
			<td colspan="5">
				${pdSendInfo.remark}
			</td>
		</tr>

		<tr>
			<td colspan="6" nowrap="nowrap">
				<jecs:locale key="busi.pdSendInfo.detail" />
			</td>
		</tr>
		<tr>
			<td>
				<strong>SN</strong>
			</td>
			<td>
				<strong><jecs:locale key="pmProduct.productName" /> </strong>
			</td>
			
			<td nowrap="nowrap">
				<strong><strong><jecs:locale
							key="busi.pd.orderqty" /> </strong>
			</td>
			<td nowrap="nowrap">
				<strong><strong><jecs:locale key="busi.pd.sendqty" />
				</strong>
			</td>
			<td nowrap="nowrap">
				<strong><strong><jecs:locale key="busi.pd.shipqty" />
				</strong>
			</td>
			<td nowrap="nowrap">
				<strong><strong><jecs:locale
							key="busi.pd.acceptqty" /> </strong>
			</td>
		</tr>

		<c:forEach items="${pdSendInfo.pdSendInfoDetails}"
			var="pdSendInfoDetail" varStatus="status">
			<c:if test="${pdSendInfoDetail.acceptQty >0}">


				<tr>
					<td>
						<strong>${status.count}</strong>
					</td>
					<td>
						<strong>${pdSendInfoDetail.productNo}-${compamyProductMap[pdSendInfoDetail.productNo]}</strong>
					</td>
					 
					<td>
						<strong>${pdSendInfoDetail.acceptQty}</strong>
					</td>
					<td>
						<strong>&nbsp;</strong>
					</td>
					<td>
						<strong>&nbsp;</strong>
					</td>
					<td>
						<strong>&nbsp;</strong>
					</td>
				</tr>
			</c:if>
		</c:forEach>



		<tr>
			<td nowrap="nowrap">
				<jecs:locale key="busi.sign.sender" />
			</td>
			<td align="left" width="120px">
				<strong>&nbsp</strong>
			</td>
			<td nowrap="nowrap">
				<jecs:locale key="busi.sign.logistics" />
			</td>
			<td align="left" width="120px">
				<strong>&nbsp;</strong>
			</td>
			<td nowrap="nowrap">
				<jecs:locale key="busi.sign.consignee" />
			</td>
			<td align="left" width="120px">
				<strong>&nbsp;</strong>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<jecs:locale key="pd.order.explain" />
			</td>
		</tr>
	</table>
	<br />
	<table width="720px" cellpadding="3" cellspacing="0">
		<tr>
			<td align="right">
				${sessionLogin.userName}--
				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:MM:ss" />

			</td>
		</tr>
	</table>

</div>
-->
<c:forEach items="${pdSendInfoList }" var="pdSendInfo" varStatus="pstatus" begin="0" step="1">
	<div align="center"> 
		<table width="720px" cellpadding="3" cellspacing="1" class="detail">
			<div align="center">
				<B style="font-size: 23px"><jecs:locale key="report.title.pdSendInfo" /> </B>
			</div>
			<br />
			<tr>
				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="pdSendInfo.siNo" /></b>
				</td>
				<td align="left" width="120px">
					<strong style="font-size: 13px">${pdSendInfo.siNo}</strong>
				</td>
				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="busi.order.orderno" /></b>
				</td>
				<td align="left" width="120px">
					<strong style="font-size: 13px">${pdSendInfo.orderNo}</strong>
				</td>
				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="pdSendInfo.sendWarehouseNo" /></b>
				</td>
				<td align="left" width="120px">
					<strong style="font-size: 13px">${pdSendInfo.warehouseNo}</strong>
				</td>
			</tr>
			<tr>


				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="pd.shno" /></b>
				</td>
				<td colspan="5" align="left">
					<strong style="font-size: 13px"><jecs:code listCode="pd.shno"
							value="${pdSendInfo.shNo}" /> </strong>
				</td>
			</tr>
			<tr>
				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="pdSendInfo.recipientName" /></b>
				</td>
				<td colspan="2" align="left">
					<strong style="font-size: 13px">${pdSendInfo.recipientName}</strong>
				</td>
				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="customerRecord.customerName" /></b>
				</td>
				<td colspan="2" align="left">
					<strong style="font-size: 13px">${pdSendInfo.customer.userCode}/${pdSendInfo.customer.userName}</strong>
				</td>
			</tr>
			<tr>
				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="pdSendInfo.recipientPhone" /></b>
				</td>
				<td align="left" width="120px">
					<strong style="font-size: 13px">${pdSendInfo.recipientPhone}/${pdSendInfo.recipientMobile}</strong>
				</td>

				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="pdSendInfo.recipientAddr" /></b>
				</td>
				<td colspan="3" align="left">
					<strong style="font-size: 13px">${alStateProvinceMap[pdSendInfo.recipientCaNo]}${alCityMap[pdSendInfo.city]}${pdSendInfo.recipientAddr}</strong>
				</td>
			</tr>


			<tr>
				<td nowrap="nowrap">
					<b style="font-size: 13px"><jecs:label key="busi.common.remark" /></b>
				</td>
				<td colspan="5">
					<b style="font-size: 13px">${pdSendInfo.remark}</b>
				</td>
			</tr>

			<tr>
				<td colspan="6" nowrap="nowrap">
					<b style="font-size: 13px"><jecs:locale key="busi.pdSendInfo.detail" /></b>
				</td>
			</tr>
			</table>
			<table width="720px" cellpadding="3" cellspacing="1" class="detail">
				<tr>
					<td>
						<strong style="font-size: 15px">SN</strong>
					</td>
					<td colspan="2">
						<strong style="font-size: 15px"><jecs:locale key="pmProduct.productName" /> </strong>
					</td>
					<!--td nowrap="nowrap"><strong><strong><jecs:locale key="pd.price"/></strong></td-->
					<td nowrap="nowrap">
						<strong style="font-size: 15px"><jecs:locale
									key="busi.pd.orderqty" /> </strong>
					</td>
					<td nowrap="nowrap">
						<strong style="font-size: 15px"><jecs:locale
									key="busi.pd.sendqty" /> </strong>
					</td>
					<!-- 
					<td nowrap="nowrap">
						<strong style="font-size: 15px"><jecs:locale
									key="busi.pd.shipqty" /> </strong>
					</td>
					 -->
					<td nowrap="nowrap">
						<strong style="font-size: 15px"><jecs:locale
									key="busi.pd.acceptqty" /> </strong>
					</td>
				</tr>
	
				<c:forEach items="${pdSendInfo.pdSendInfoDetails}"
					var="pdSendInfoDetail" varStatus="status">
					<c:if test="${pdSendInfoDetail.acceptQty >0}">
	
	
						<tr>
							<td>
								<strong style="font-size: 15px">${status.count}</strong>
							</td>
							<td colspan="2" align="left">
								<strong style="font-size: 15px">${pdSendInfoDetail.productNo}-${compamyProductMap[pdSendInfoDetail.productNo]}</strong>
							</td>
							<!--td ><strong>${pdSendInfoDetail.price}</strong></td-->
							<td>
								<strong style="font-size: 15px">${pdSendInfoDetail.qty}</strong>
							</td>
							<td>
								<strong style="font-size: 15px">${pdSendInfoDetail.acceptQty}</strong>
							</td>
							<!-- 
							<td>
								<strong>&nbsp;</strong>
							</td>
							 -->
							<td>
								<strong>&nbsp;</strong>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</table>

			<table width="720px" cellpadding="3" cellspacing="1" class="detail">
			<tr>
				<td nowrap="nowrap">
					<jecs:locale key="busi.sign.sender" />
				</td>
				<td align="left" width="120px">
					<strong>&nbsp</strong>
				</td>
				<td nowrap="nowrap">
					<jecs:locale key="busi.sign.logistics" />
				</td>
				<td align="left" width="120px">
					<strong>&nbsp;</strong>
				</td>
				<td nowrap="nowrap">
					<jecs:locale key="busi.sign.consignee" />
				</td>
				<td align="left" width="120px">
					<strong>&nbsp;</strong>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<jecs:locale key="pd.order.explain" />
				</td>
			</tr>
		</table>
		<br />
		<table width="720px" cellpadding="3" cellspacing="0">
			<tr>
				<td align="right">
					${sessionLogin.userName}--
					<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:MM:ss" />

				</td>
			</tr>
		</table>
	<c:if test="${fn:length(pdSendInfoList)>pstatus.count }">
		<div STYLE="page-break-after: always;" ></div>
	</c:if>
	</div>
</c:forEach>
<div id='details'>

</div> 


<script type="text/javascript">
function go_print () {
    $('titleBar').style.visibility="hidden";
    window.print();
    window.close();
  }
  
 
</script>