<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdBounsDeductDetail.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductDetail.heading"/></content>





<table class='detail' width="100%">
<tbody class="window">
   
	<tr class="edit_tr">
	<th><span class="text-font-style-tc">
        <jecs:label  key="miMember.memberNo"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        ${bdBounsDeduct['user_code']}
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdCalculatingSubDetail.name"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        ${bdBounsDeduct['user_name'] }
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="miMember.cardType"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="memberNo" cssClass="fieldError"/-->
        <jecs:code listCode="bd.cardtype" value="${bdBounsDeduct['card_type']}"/>
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdReconsumMoneyReport.typeCH"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="type" cssClass="fieldError"/-->
        <jecs:code listCode="bdbounsdeduct.item" value="${bdBounsDeduct['type']}"/>
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.summary"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="summary" cssClass="fieldError"/-->
        ${bdBounsDeduct['summary'] }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.money"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="money" cssClass="fieldError"/-->
        ${bdBounsDeduct['money'] }
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.remainMoney"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="remainMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct['remain_money']}
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.deductMoney"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
         ${bdBounsDeduct['deduct_money'] }
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.curWwekkRemainMoney"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct['remain_money']-bdBounsDeduct['deduct_money'] }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductMoney" cssClass="fieldError"/-->
        ${bdBounsDeduct['w_week'] }
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.deductTime"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct['deduct_time'] }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.deducterCode"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct['deducter_code'] }
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.deducterName"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct['deducter_name'] }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.status"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
       <jecs:code listCode="bdbounsdeduct.status" value="${bdBounsDeduct['status']}"/>
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="pd.okTime"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
         ${bdBounsDeduct['check_time'] }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.checkerCode"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct['checker_code'] }
    </span></td></tr>
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.checkerName"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="deductTime" cssClass="fieldError"/-->
        ${bdBounsDeduct['checker_name'] }
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="prRefund.createTime"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct['create_time'] }
    </span></td></tr>
    
    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.createCode"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct['create_code']}
    </span></td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.createName"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
         ${bdBounsDeduct['create_name']}
    </span></td></tr>
<tr>
	<td class="command" colspan="4" align="center">
		<button type="button" class="btn btn_sele" name="back"  onclick="javascript:history.back();" >
			<jecs:locale key="operation.button.return"/>
		</button>
		
	</td>
</tr>
</tbody>
</table>

