<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiBankbookJournalDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiBankbookJournalDetail.heading"/></content>


<table class='detail' width="70%" >
	<tbody class="window" >

    <tr class="edit_tr">
		<th><span class="text-font-style-tc">
			<jecs:label  key="label.member.or.agent.code"/>
		</th>
		<td>
			<span class="textbox">
			${jfiBankbookJournal.sysUser.userCode }
			</span>
		</td>
		<th><span class="text-font-style-tc">
			<jecs:label  key="fiBankbookJournal.serial"/>
		</th>
		<td>
			<span class="textbox">
			${jfiBankbookJournal.serial }
			</span>
		</td>
	</tr>

    <tr class="edit_tr">
		<th><span class="text-font-style-tc">
			<jecs:label  key="fiBankbookJournal.dealType"/>
			</span>
		</th>
		<td>
			<span class="textbox">
			<jecs:code listCode="fibankbooktemp.dealtype" value="${jfiBankbookJournal.dealType}"/>
			</span>
		</td>
		<th><span class="text-font-style-tc">
			<jecs:label  key="comm.busi.deal.transaction.date"/>
			</span>
		</th>
		<td>
			<span class="textbox">
			${jfiBankbookJournal.dealDate }
			</span>
		</td>
	</tr>

   <tr class="edit_tr">
		<th><span class="text-font-style-tc">
        <jecs:label  key="fiBankbookJournal.money"/>
		</span>
		</th>
		<td>
			<span class="textbox">
			<fmt:formatNumber pattern="###,##0.00">${jfiBankbookJournal.money }</fmt:formatNumber>
			</span>
		</td>
		<th><span class="text-font-style-tc">
			<jecs:label  key="bdBounsDeduct.summary"/>
			</span>
		</th>
		<td>
			<span class="textbox">
			${jfiBankbookJournal.notes }
			</span>
		</td>
	</tr>

   

    <tr class="edit_tr">
		<th><span class="text-font-style-tc">
			<jecs:label  key="fiBankbookJournal.balance"/>
			</span>
		</th>
		<td>
			<span class="textbox">
			<fmt:formatNumber pattern="###,##0.00">${jfiBankbookJournal.balance }</fmt:formatNumber>
			</span>
		</td>
		<th><span class="text-font-style-tc">
			<jecs:label  key="busi.common.remark"/>
			</span>
		</th>
		<td>
			<span class="textbox">
			${jfiBankbookJournal.remark }
			</span>
		</td>
	</tr>
    <tr>
		<td class="command" colspan="4" align="center">
			<button type="button" class="btn btn_sele" name="cancel" onclick="history.back();" value="<jecs:locale key="operation.button.cancel"/>" >
			<jecs:locale key="operation.button.cancel"/>
			</button>
		</td>
	</tr>
</table>
