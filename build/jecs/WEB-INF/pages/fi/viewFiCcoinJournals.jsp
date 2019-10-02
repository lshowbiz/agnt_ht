<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiCcoinJournal.title"/></title>
<content tag="heading"><jecs:locale key="fiCcoinJournal.heading"/></content>


<table class='detail' width="100%">

    <tr><th width="200">
        <jecs:label  key="label.member.or.agent.code"/>
    </th>
    <td align="left">
        ${fiCcoinJournal.sysUser.userCode }
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.serial"/>
    </th>
    <td align="left">
        ${fiCcoinJournal.serial }
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.dealType"/>
    </th>
    <td align="left">
        <jecs:code listCode="fibankbooktemp.dealtype" value="${fiCcoinJournal.dealType}"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="comm.busi.deal.transaction.date"/>
    </th>
    <td align="left">
        ${fiCcoinJournal.dealDate }
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.money"/>
    </th>
    <td align="left">
        <fmt:formatNumber pattern="###,##0.00">${fiCcoinJournal.coin }</fmt:formatNumber>
    </td></tr>

    <tr><th>
        <jecs:label  key="bdBounsDeduct.summary"/>
    </th>
    <td align="left">
        ${fiCcoinJournal.notes }
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookJournal.balance"/>
    </th>
    <td align="left">
        <fmt:formatNumber pattern="###,##0.00">${fiCcoinJournal.balance }</fmt:formatNumber>
    </td></tr>

    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        ${fiCcoinJournal.remark }
    </td></tr>
    
    <tr><th class="command">
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td class="command">
        <input type="button" class="button" name="cancel" onclick="history.back();" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table>