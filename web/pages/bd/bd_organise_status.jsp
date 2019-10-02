<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<style>
<!--
#TT_1{
	height: 220px;
	width: 120px;
	background-image: url(./images/organise/tb_1.jpg);
	font-size: 14px;
	text-align: center;
	padding-top: 3px;
	padding-bottom: 3px;
	padding-right: 0px;
	padding-left: 0px;
}
*html #TT_1{
	height: 215px;
}

#TT_2{
	height: 220px;
	width: 120px;
	background-image: url(./images/organise/tb_2.jpg);
	font-size: 14px;
	text-align: center;
	padding-top: 3px;
	padding-bottom: 3px;
	padding-right: 0px;
	padding-left: 0px;
}

#TT_3{
	height: 220px;
	width: 120px;
	background-image: url(./images/organise/tb_3.jpg);
	font-size: 14px;
	text-align: center;
	padding-top: 3px;
	padding-bottom: 3px;
	padding-right: 0px;
	padding-left: 0px;
}

.ta_b{
	color: #0000FF;
	font-weight: bold;
}

.ta_g{
	color: #009900;
	font-weight: bold;
}

.ta_r{
	color: #FF0000;
	font-weight: bold;
}
-->
</style>
<script type="text/javascript" src="./scripts/validate.js"> </script> 

<form method="get" name="bdOrganiseForm" action="bd_organise_status.html" onsubmit="return validateOthers(this)" id="bdPeriodForm">

<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo" />
		<input type="text" name="memberNo" 
			value="<c:if test="${not empty param.memberNo}">${param.memberNo }</c:if>
				   <c:if test="${empty param.memberNo and sessionScope.sessionLogin.isMember==true}">
					${sessionScope.sessionLogin.userCode }</c:if>" size="10"/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="bdBounsDeduct.wweek" />
		<select name="wweek">
			<c:forEach items="${latesBdPeriods}" var="latesBdPeriodVar">
				<option value="${latesBdPeriodVar.long_f_week }"<c:if test="${latesBdPeriodVar.long_f_week==param.wweek }">selected</c:if>>
					[${latesBdPeriodVar.long_f_week }] ${latesBdPeriodVar.start_time } ~ ${latesBdPeriodVar.end_time }
				</option>
			</c:forEach>
		</select>
	</div>
	<div class="new_searchBar">
		<jecs:power powerCode="bdOrganiseStatusQuery">	
			<button type="submit" name="search" class="btn btn_sele">
				<jecs:locale key="operation.button.search"/>
			</button>
			<%-- <input type="submit" name="search" value="<jecs:locale key="operation.button.search"/>" class="button"/>--%>
		</jecs:power>
		<input type="hidden" name="strAction" value="bdOrganiseStatusQuery"/>
	</div>
</div>
<br>

<c:if test="${not empty bdDayBounsCalcMap}">
	<jecs:locale key="bdBonus.tree.organize"/>: <a href="bd_organise_status.html?strAction=bdOrganiseStatusQuery&firstMemberNo=${firstMiMember.userCode }&memberNo=${firstMiMember.userCode }&wweek=${param.wweek }">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		${firstMiMember.petName }
	</c:if>
	<c:if test="${sessionScope.sessionLogin.userType!='C'}">
		${firstMiMember.petName }
	</c:if>
	</a>
	<c:if test="${not empty upMiLinkRefs}">
		<c:forEach items="${upMiLinkRefs}" var="upMiLinkRefVar">
			/ <a href="bd_organise_status.html?strAction=bdOrganiseStatusQuery&firstMemberNo=${firstMiMember.userCode }&memberNo=${upMiLinkRefVar.jmiMember.userCode }&wweek=${param.wweek }">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			${upMiLinkRefVar.jmiMember.petName }
	</c:if>
	<c:if test="${sessionScope.sessionLogin.userType!='C'}">
			${upMiLinkRefVar.jmiMember.petName }
	</c:if>
			
			</a>
		</c:forEach>
		
	</c:if>
	<br>
</c:if>

<c:if test="${not empty bdDayBounsCalcMap}">
	<table border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td colspan="9" align="center"><table width="120" border="0" cellspacing="0">
          <tr>
            <td id="TT_1"><span class="ta_b">${bdDayBounsCalcMap.user_code }</span>
					<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				          <br />
				          <B>${bdDayBounsCalcMap.user_name }</B>
				          <br />
				          <B>${bdDayBounsCalcMap.pet_name }</B>
					</c:if>
					<c:if test="${sessionScope.sessionLogin.userType!='C'}">
				          <br />
				          <B>${bdDayBounsCalcMap.pet_name }</B>
					</c:if>
          
          
          <br/><font style="font-size:9pt;"><jecs:code listCode="bd.cardtype" value="${bdDayBounsCalcMap.card_type }"/></font>
          <br /><span class="ta_g"><jecs:locale key="pv.person"/>:
          	<fmt:formatNumber pattern="###,##0.00">${bdDayBounsCalcMap.month_consumer_pv }</fmt:formatNumber>pv
          	</span>
          <br /><span class="ta_b"><jecs:locale key="bdBonus.tree.organize"/>:
          	<fmt:formatNumber pattern="###,##0.00">${bdDayBounsCalcMap.week_group_pv }</fmt:formatNumber> pv
          	</span>
          <HR width="80%">
          <jecs:power powerCode="viewSomeData">
          <span class="ta_g"><jecs:locale key="miLinkRef.total"/>:
          	${bdDayBounsCalcMap.link_num }<jecs:locale key="unit.person"/>
          	</span>
          <br /><span class="ta_r"><jecs:locale key="miLinkRef.new"/>:
         	${bdDayBounsCalcMap.pending_link_num }<jecs:locale key="unit.person"/>
         	</span>
         	</jecs:power>
          <%--<br />
          <span class="ta_r"><jecs:locale key="bdBonus.tree.organize"/>:
          	<c:if test="${not empty bdDayBounsCalcMap.total}">${bdDayBounsCalcMap.total }</c:if>
          	<c:if test="${empty bdDayBounsCalcMap.total}">0</c:if>
          	人</span>--%>
        </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="9" align="center"><img src="images/organise/tb.jpg" width="640" height="50" /></td>
      </tr>
      <tr><td><table width="0" border="0" cellspacing="0">
  <tr>
      	<c:set var="loopCount" value="0"></c:set>
      	<c:forEach items="${childBdDayBonusCalcs}" var="childBdDayBonusCalcVar" varStatus="childBdDayBonusCalcStatus">
      		<c:set var="loopCount" value="${loopCount+1}"></c:set>
      		<c:if test="${childBdDayBonusCalcStatus.index>0}">
      			<td width="7">&nbsp;</td>
      		</c:if>
      		<c:set var="cssStyle" value="TT_2"/>
      		<c:if test="${childBdDayBonusCalcVar.card_type==0}">
      			<c:set var="cssStyle" value="TT_3"/>
      		</c:if>
			<td id="${cssStyle }">
				<c:if test="${childBdDayBonusCalcVar.user_code==bdSendRecord.keep_user_code}">
					<span class="ta_r"><jecs:locale key="pv.keep"/>:
						<c:if test="${not empty bdSendRecord.keep_pv}"><fmt:formatNumber pattern="###,##0.00">${bdSendRecord.keep_pv }</fmt:formatNumber></c:if>
			          	<c:if test="${empty bdSendRecord.keep_pv}">0.00</c:if>
					pv</span><br>
				</c:if>
				<span class="ta_b">${childBdDayBonusCalcVar.user_code }</span>
				
					<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				          <br />
							<B>${childBdDayBonusCalcVar.user_name }</B>
				          <br />
							<B>${childBdDayBonusCalcVar.pet_name }</B>
					</c:if>
					<c:if test="${sessionScope.sessionLogin.userType!='C'}">
				          <br />
							<B>${childBdDayBonusCalcVar.pet_name }</B>
					</c:if>
				<br/><font style="font-size:9pt;"><jecs:code listCode="bd.cardtype" value="${childBdDayBonusCalcVar.card_type }"/></font>
					<br /><span class="ta_g"><jecs:locale key="pv.person"/>:
						<fmt:formatNumber pattern="###,##0.00">${childBdDayBonusCalcVar.month_consumer_pv }</fmt:formatNumber>
					pv</span>
					<br /><span class="ta_b"><jecs:locale key="bdBonus.tree.organize"/>:
						<fmt:formatNumber pattern="###,##0.00">${childBdDayBonusCalcVar.week_group_pv }</fmt:formatNumber>
						pv</span>
					<HR width="80%">
					
          <jecs:power powerCode="viewSomeData">
					<span class="ta_g"><jecs:locale key="miLinkRef.total"/>:
						${childBdDayBonusCalcVar.link_num }
						<jecs:locale key="unit.person"/></span>
					<br /><span class="ta_r"><jecs:locale key="miLinkRef.new"/>:
						${childBdDayBonusCalcVar.pending_link_num }
						<jecs:locale key="unit.person"/></span>
					<br />
					<%--<span class="ta_r"><jecs:locale key="bdBonus.tree.organize"/>:
			          	<c:if test="${not empty childBdDayBonusCalcVar.total}">${childBdDayBonusCalcVar.total }</c:if>
			          	<c:if test="${empty childBdDayBonusCalcVar.total}">0</c:if>
			          	<jecs:locale key="unit.person"/></span>
			        <br/>--%>
			        <br/>
					<%--<c:if test="${sessionScope.sessionLogin.companyCode=='US'}">
						<a href="bd_organise_status.html?strAction=bdOrganiseStatusQuery&doType=showLastMember&firstMemberNo=${firstMiMember.memberNo }&memberNo=${childBdDayBonusCalcVar.member_no }&wweek=${param.wweek }" title="search the last member">
						<font style="font-size: 10px;">search the last member</font>
						</a>
						<br/>
						<a href="bd_organise_status.html?strAction=bdOrganiseStatusQuery&firstMemberNo=${firstMiMember.memberNo }&memberNo=${childBdDayBonusCalcVar.member_no }&wweek=${param.wweek }" title="search the next member">
						<font style="font-size: 10px;">search the next member</font>
						</a>
					</c:if>--%>
					
					<%--<c:if test="${sessionScope.sessionLogin.companyCode!='US'}">
					</c:if>--%>
						<a href="bd_organise_status.html?strAction=bdOrganiseStatusQuery&firstMemberNo=${firstMiMember.userCode }&memberNo=${childBdDayBonusCalcVar.user_code }&wweek=${param.wweek }">
						<img src="images/organise/tt_l.gif" width="28" height="28" border="0"/>
						</a>
						</jecs:power>
			</td>
        </c:forEach>
        <c:if test="${loopCount<6}">
        	<c:set var="loopVar" value="0,1,2,3,4,5"></c:set>
        	<c:forTokens items="${loopVar}" delims="," begin="${loopCount}">
        		<td width="7">&nbsp;</td>
        		<td width="120" id="TT_3">&nbsp;</td>
        	</c:forTokens>
        </c:if>
      </tr>
</table>
</td></tr>
</table>

<br>

		<c:if test="${not empty bdDayBounsCalcMap}">
		<span style="font-size:13px">
		<br>
			<jecs:locale key="bdOrganiseStatus.busi.stat.week"/>[ ${param.wweek } ]
		<br>
			<jecs:locale key="bdOrganiseStatus.keep.busi.week"/>[ <jecs:weekFormat week="${bdSendRecord.w_week }" weekType="w" /> ]
		<br>
			<jecs:locale key="bdOrganiseStatus.sample.tips"/>
		</span>
		</c:if>
		<br>
<table width="100%" border="0" bgcolor="#FFFFCC">
	<tr>
		<td>
		<jecs:locale key="bdBounsDeduct.summary"/>:<br>
		<jecs:locale key="bd.organise.notice.pv.person"/><br>
		<jecs:locale key="bd.organise.notice.pv.group"/><br>
		<HR>
		 <jecs:locale key="bd.organise.notice.member.recommend"/><br>
		<jecs:locale key="bd.organise.notice.member.new"/><br>
		<%--<jecs:locale key="bdBonus.tree.organize"/>: <jecs:locale key="bd.organise.notice.group.totalnum"/>--%>
		</td>
	</tr>
</table>
</c:if>
	
</form>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.memberNo.value==""){
		alert("<jecs:locale key="bdBonus.tree.memberNo.required"/>");
		theForm.memberNo.focus();
		return false;
	}
	
	return true; 
}

<c:if test="${empty param.memberNo and sessionScope.sessionLogin.isMember==true}">
if (document.all){
	window.attachEvent('onload',autoSubmitForm)
}else{
	window.addEventListener('load',autoSubmitForm,false);
}
function autoSubmitForm(){
	document.bdOrganiseForm.submit();
}
</c:if>
</script>