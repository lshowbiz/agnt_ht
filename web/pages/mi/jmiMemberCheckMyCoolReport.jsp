<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />



<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="./scripts/validate.js"> </script> 
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/print.css'/>" />
<style media="print">
	.noPrint { 
		display: none;
	}
</style>

<form method="post" action="" id="bdPeriodForm">

	<div class="searchBar">
				<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="button"/>
				<input type="hidden" name="strAction" value="jmiMemberCheckMyCoolReport"/>
	</div>
	<table class="detail" width="100%">
		<tr>
			<th><jecs:label key="customerRecord.type"/></th>
			<td>
        <jecs:list name="type" id="type" listCode="relation.type" value="${param.type }" defaultValue=""/>
			</td>
		</tr>
		<tr>
			<th><jecs:label key="miMember.memberNo"/></th>
			<td>
			<input type="text" name="userCode" id="userCode" value="${param.userCode }" size="10"/>
			</td>
		</tr>
		<c:forEach var="mi" items="${memberCardType}">
		<tr>
			<th><jecs:code listCode="bd.cardtype" value="${mi.card_type}"/></th>
			<td>
			${mi.counts }
			</td>
		</tr>
		</c:forEach>
	</table>
	
</form>
