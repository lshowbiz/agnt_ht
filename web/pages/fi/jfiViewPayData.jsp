<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayDataDetail.title"/></title>
<content tag="heading"><jecs:locale key="jfiPayDataDetail.heading"/></content>

<div class="searchBar">
<input type="button" class="button" name="cancel" onclick="window.location='jfiPayDatas.html?strAction=listFiPayDatas&needReload=1';" value="<jecs:locale key="operation.button.cancel"/>" />
</div>

<table class='detail' width="100%">

    <tr><th>
        <jecs:label  key="fiPayData.accountCode"/>
    </th>
    <td align="left">${jfiPayData.accountCode }</td></tr>

    <tr><th>
        <jecs:label  key="comm.busi.deal.transaction.date"/>
    </th>
    <td align="left">${jfiPayData.dealDate }
    </td></tr>

    <tr><th>
        <label for="incomeMoney" class="required"><span class="req">*</span> <jecs:locale key="fiPayData.incomeMoney"/>:</label>
    </th>
    <td align="left">${jfiPayData.incomeMoney }
    </td></tr>

    <tr><th>
        <jecs:label  key="fiPayData.excerpt"/>
    </th>
    <td align="left">
        ${jfiPayData.excerpt }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiPayData.adviceCode"/>
    </th>
    <td align="left">${jfiPayData.adviceCode }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="label.member.or.agent.code"/>
    </th>
    <td align="left">${jfiPayData.sysUser.userCode }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiBankbookTemp.status"/>
    </th>
    <td align="left">
        <jecs:code listCode="fiagentpayadvice.status" value="${jfiPayData.status}"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">${jfiPayData.remark }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiPayData.createrName"/>
    </th>
    <td align="left">${jfiPayData.createrName }
    </td></tr>

    <tr><th>
        <jecs:label  key="miMember.createTimeRange"/>
    </th>
    <td align="left">${jfiPayData.createTime }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="miMember.createTimeRange"/>
    </th>
    <td align="left">${jfiPayData.createTime }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiPayData.createNo"/>
    </th>
    <td align="left">${jfiPayData.createNo }
    </td></tr>
    
    <tr><th>
        <jecs:label  key="fiPayAdvice.checkerName"/>
    </th>
    <td align="left">${jfiPayData.checkerName }
    </td></tr>

    <tr><th>
        <jecs:label  key="fiBankbookTemp.checkeTime"/>
    </th>
    <td align="left">${jfiPayData.checkTime }
    </td></tr>
</table>
