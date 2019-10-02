<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBcoinJournal.title"/></title>
<content tag="heading"><jecs:locale key="fiBcoinJournal.heading"/></content>


<table class='detail' width="70%" >
	<tbody class="window" >

     <tr class="edit_tr">
		<th><span class="text-font-style-tc">
        <jecs:label  key="label.member.or.agent.code"/>
		</span>
    </th>
    <td>
		<span class="textbox">
        ${fiBcoinJournal.sysUser.userCode }</span>
    </td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="fiBankbookJournal.serial"/>
		</span>
    </th>
    <td>
		<span class="textbox">
        ${fiBcoinJournal.serial }</span>
    </td>
	</tr>


     <tr class="edit_tr">
		<th><span class="text-font-style-tc">
        <jecs:label  key="fiBankbookJournal.dealType"/></span>
    </th>
    <td>
		<span class="textbox">
        <jecs:code listCode="fibankbooktemp.dealtype" value="${fiBcoinJournal.dealType}"/>
		</span>
    </td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="comm.busi.deal.transaction.date"/>
		</span>
    </th>
    <td >
		<span class="textbox">
        ${fiBcoinJournal.dealDate }
		</span>
    </td>
	</tr>

   
    <tr class="edit_tr">
		<th><span class="text-font-style-tc">
        <jecs:label  key="fiBankbookJournal.money"/>
		</span>
    </th>
    <td >
		<span class="textbox">
        <fmt:formatNumber pattern="###,##0.00">${fiBcoinJournal.coin }</fmt:formatNumber>
		</span>
    </td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.summary"/>
		</span>
		
    </th>
    <td >
		<span class="textbox">
        ${fiBcoinJournal.notes }
		</span>
    </td>
	</tr>


    <tr><th><span class="text-font-style-tc">
        <jecs:label  key="fiBankbookJournal.balance"/>
		</span>
    </th>
    <td >
		<span class="textbox">
        <fmt:formatNumber pattern="###,##0.00">${fiBcoinJournal.balance }</fmt:formatNumber>
		</span>
    </td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="busi.common.remark"/>
		</span>
    </th>
    <td >
		<span class="textbox">
        ${fiBcoinJournal.remark }
		</span>
    </td>
	</tr>

   
    
   <tr>
		<td class="command" colspan="4" align="center">
        <button type="button" class="btn btn_sele" name="cancel" onclick="history.back();" value="<jecs:locale key="operation.button.cancel"/>" ><jecs:locale key="operation.button.cancel"/></button>
    </td></tr>
</table>