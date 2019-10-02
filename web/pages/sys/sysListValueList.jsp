<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysListValueList.title"/></title>
<content tag="heading"><jecs:locale key="sysListValueList.heading"/></content>

<c:if test="${not empty param.keyId && sysListKey.isReadOnly!=1}">
<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addSysListValue">
			<a href="#" onclick="location.href='<c:url value="/editSysListValue.html"/>?strAction=addSysListValue&keyId=${param.keyId}'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>
</c:if>

<ec:table 
	items="sysListValueList"
	var="sysListValue"
	action="${pageContext.request.contextPath}/sysListValues.html"
	width="100%"
	
	showPagination="false" sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="keyId" title="title.operation" styleClass="centerColumn" style="width:5px;">
			<nobr>
 			<c:if test="${sysListKey.isReadOnly!=1}">
 			<jecs:power powerCode="editSysListValue">
			<a href="editSysListValue.html?strAction=editSysListValue&valueId=${sysListValue.valueId}"><img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16" alt="<jecs:locale key="button.edit"/>"/></a>
			</jecs:power>
			</c:if>
			&nbsp;
			</nobr>
		</ec:column>
		<ec:column property="valueCode" title="sysListValue.valueCode" />
		<ec:column property="valueTitle" title="sysListValue.valueTitle">
		<jecs:locale key="${sysListValue.valueTitle}"/>
		</ec:column>
		<ec:column property="exCompanyCode" title="sysListValue.exCompanyCode" />
		<ec:column property="orderNo" title="sysListValue.orderNo" />
	</ec:row>

</ec:table>	