<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberList.heading"/></content>
<meta name="menu" content="JmiMemberMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:forEach items="${ messages }" var="curVar">
	${curVar } <br/>
</c:forEach>

<form action="" method="get" name="miMemberForm" id="miMemberForm">

	<input type="hidden" id="strAction" name="strAction" value="jmiMemberTwPromotions"/>
	<div class="searchBar">
		<input name="search" class="button" type="button" onclick="location.href='jmiMemberTwPromotions.html?strAction=jmiMemberTwPromotionUpGrade'" value="<jecs:locale key="busi.bdPinTitleRecord.upgrade"/>" />
	</div>



</form>


	<ec:table 
		tableId="jmiMemberTwPromotions"
		items="jmiMemberTwPromotions"
		var="jmiMemberTwPromotion"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column property="user_code" title="miMember.memberNo" />
			<ec:column property="card_type" title="jmiMember.oriCard" >
				<jecs:code listCode="bd.cardtype" value="${jmiMemberTwPromotion.card_type }"/>
			</ec:column>
			<ec:column property="nCardType" title="jmiMemberTwPromotions.nCadType" >
				<jecs:code listCode="bd.cardtype" value="${jmiMemberTwPromotion.nCardType }"/>
			</ec:column>
			<ec:column property="pv_amt" title="poMemberOrder.pvAmt" />
			<ec:column property="repvamt" title="member.recommend.pv" />
			
					
					
		</ec:row>
	</ec:table>	

