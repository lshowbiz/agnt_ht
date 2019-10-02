<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayAdviceDetail.title" /></title>
<content tag="heading">
<jecs:locale key="jfiPayAdviceDetail.heading" />
</content>

	<div id="titleBar"><input type="button" class="button" name="cancel" onclick="window.location='jfiPayAdvices.html?strAction=listFiPayAdvices';" value="<jecs:locale key="operation.button.cancel"/>" /></div>

	<table class='detail' width="100%">
		<tr>
			<th><jecs:label key="fiPayAdvice.adviceCode" /></th>
			<td align="left">${jfiPayAdvice.adviceCode }</td>
		</tr>

		<tr>
			<th><jecs:label key="fiPayAdvice.accountCode" /></th>
			<td align="left">${jfiPayAdvice.accountCode }</td>
		</tr>

		<tr>
			<th><jecs:label key="comm.busi.deal.transaction.date" /></th>
			<td align="left">${jfiPayAdvice.payDate}</td>
		</tr>

		<tr>
			<th><label for="payMoney" class="required"><span class="req">*</span> <jecs:locale key="fiBankbookJournal.money" />:</label></th>
			<td align="left">${jfiPayAdvice.payMoney }</td>
		</tr>

		<tr>
			<th><jecs:label key="fiPayAdvice.notice" /></th>
			<td align="left">${jfiPayAdvice.notice }</td>
		</tr>

		<tr>
			<th><jecs:label key="fiPayAdvice.dealType" /></th>
			<td align="left">${jfiPayAdvice.dealType }</td>
		</tr>

		<tr>
			<th><jecs:label key="fiPayAdvice.telephone" /></th>
			<td align="left">${jfiPayAdvice.telephone }</td>
		</tr>

		<tr>
			<th><jecs:label key="busi.common.remark" /></th>
			<td align="left">${jfiPayAdvice.remark}</td>
		</tr>
		<tr>
			<th><jecs:label key="fiBankbookTemp.status" /></th>
			<td align="left"><jecs:code listCode="fiagentpayadvice.status" value="${jfiPayAdvice.status}"/></td>
		</tr>

		<tr>
			<th><label><jecs:locale key="fiPayAdvice.input" />:</label></th>
			<td align="left"><jecs:locale key="fiPayAdvice.input.notice" /></td>
		</tr>

	</table>