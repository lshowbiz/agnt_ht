<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdPeriodMenu" />

<form:form commandName="bdPeriod" method="post" action="" onsubmit="return validateOthers(this)" id="bdPeriodForm">
<div class="searchBar">
	<table width="100%">
	<form:hidden path="wid"/>
		<tr>
			<th><jecs:locale key="bdBounsDeduct.wweek" /></th>
			<th><jecs:locale key="title.deal" /></th>
			<th width="100%">&nbsp;</th>
		</tr>
		<tr>
			<td>
				<c:set var="tmpWeek">${bdPeriod.wweek}</c:set>
				<c:if test="${fn:length(tmpWeek)==2}">${bdPeriod.wyear}${bdPeriod.wweek}</c:if>
				<c:if test="${fn:length(tmpWeek)==1}">${bdPeriod.wyear}0${bdPeriod.wweek}</c:if>
				<form:hidden path="wyear"/><form:hidden path="wweek"/>
			</td>
			<td>
				<jecs:power powerCode="bdAbroadNetworkCostCalc">	
				<input type="submit" name="submit" value="<jecs:locale key="button.caculate"/>" class="button" <c:if test="${empty bdPeriod.wid}"> disabled</c:if>/>
				</jecs:power>
				<input type="hidden" name="strAction" value="bdAbroadNetworkCostCalc"/>
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</div>
</form:form>

<script type="text/javascript">
function validateOthers(theForm){
	return true;
}
</script>