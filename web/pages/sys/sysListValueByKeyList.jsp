<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysListValueList.title"/></title>
<content tag="heading"><jecs:locale key="sysListValueList.heading"/></content>



<table class='grid_table'>
	<tr class='grid_span'>
	<c:if test="${sysListKey.isReadOnly!=1}">
		<td colspan='10'>
			<span onclick="location.href='<c:url value="/editSysListValueByKey.html"/>?strAction=addSysListValueByKey&keyId=${keyId}'" style="cursor:pointer">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font> <c:remove var="keyId"/>
			</span>
			<jecs:power powerCode="addSysListValueByKey">	
 </jecs:power>
 </c:if>
		</td>
</tr>
</table>
<ec:table 
	items="sysListValueList"
	var="sysListValue"
	action="${pageContext.request.contextPath}/sysListValuesByKey.html"
	width="100%"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif">
	<ec:row >
			<ec:column property="keyId" title="title.operation" styleClass="centerColumn" style="width:40px;">
 			<c:if test="${sysListKey.isReadOnly!=1}">
 			<jecs:power powerCode="editSysListValueByKey">
			<a href="editSysListValueByKey.html?strAction=editSysListValueByKey&valueId=${sysListValue.valueId}"><img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16" alt="<jecs:locale key="button.edit"/>"/></a>
			</jecs:power>
			</c:if>
			&nbsp;
		</ec:column>
		<ec:column property="valueCode" title="sysListValue.valueCode" />
		<ec:column property="valueTitle" title="sysListValue.valueTitle">
		<jecs:locale key="${sysListValue.valueTitle}"/>
		</ec:column>
		<ec:column property="exCompanyCode" title="sysListValue.exCompanyCode" />
		<ec:column property="orderNo" title="sysListValue.orderNo" />
	</ec:row>

</ec:table>	

<script type="text/javascript">

function editSysListValue(valueId){
	<c:if test="${sysListKey.isReadOnly!=1}">
	<jecs:power powerCode="editSysListValue">
	window.location="editSysListValue.html?strAction=editSysListValueByKey&valueId="+valueId;
	</jecs:power>
	</c:if>
}

</script>
