<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysConfigValueList.title"/></title>
<content tag="heading"><jecs:locale key="sysConfigValueList.heading"/></content>
<meta name="menu" content="SysConfigValueMenu"/>

<c:set var="buttons">
    <input type="button" class="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editSysConfigValue.html"/>'"
        value="<jecs:locale key="member.new.num"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<jecs:locale key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<ec:table 
	items="sysConfigValueList"
	var="sysConfigValue"
	action="${pageContext.request.contextPath}/sysConfigValues.html"
	width="100%"
	rowsDisplayed="20" sorta./images/extremetablecs/images/extremetable/*.gif">
				<ec:row onclick="javascript:editSysConfigValue(${sysConfigValue.valueId})">
    			<ec:column property="companyCode" title="sysConfigValue.companyCode" />
    			<ec:column property="configValue" title="sysConfigValue.configValue" />
				</ec:row>

</ec:table>	

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">

   function editSysConfigValue(valueId){
					window.location="editSysConfigValue.html?valueId="+valueId;
		}

</script>
