<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysServiceView.title"/></title>
<content tag="heading"><jecs:locale key="sysServiceView.heading"/></content>

<form action="" method="get" name="sysConfigKeyForm" id="sysConfigKeyForm">

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<a href="#" onclick="window.location.reload();">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/icons/refresh.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='button.refresh'/></font>
			</a>
		</td>
	</tr>
</table>
    
<table width="100%" class="list">
	<tr>
		<th><jecs:locale key="service.title.serviceName"/></th>
		<th><jecs:locale key="service.title.groupName"/></th>
		<th><jecs:locale key="fiBankbookTemp.status"/></th>
	</tr>
	<c:forEach items="${sysServices}" var="sysService">
		<tr>
			<td><jecs:locale key="serviceName.${sysService.serviceName }"/>(${sysService.serviceName })</td>
			<td><jecs:locale key="service.group.${sysService.groupName }"/></td>
			<td>
			<c:if test="${sysService.activeCount==0}"><jecs:locale key="service.status.0"/></c:if>
			<c:if test="${sysService.activeCount>0}"><jecs:locale key="service.status.1"/></c:if>
			</td>
		</tr>
	</c:forEach>
</table>
<p class="notice" align="center"><jecs:locale key="${errMsgKey}"/></p>
</form>