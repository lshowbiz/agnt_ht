<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunConfigKeyList.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunConfigKeyList.heading"/></content>
<meta name="menu" content="JfiSunConfigKeyMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiSunConfigKey">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiSunConfigKey.html"/>?strAction=addJfiSunConfigKey'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiSunConfigKeyListTable"
	items="jfiSunConfigKeyList"
	var="jfiSunConfigKey"
	action="${pageContext.request.contextPath}/jfiSunConfigKeys.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiSunConfigKey('${jfiSunConfigKey.configCode}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="keyDesc" title="jfiSunConfigKey.keyDesc" />
    			<ec:column property="defaultValue" title="jfiSunConfigKey.defaultValue" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiSunConfigKey(configCode){
   		<jecs:power powerCode="editJfiSunConfigKey">
					window.location="editJfiSunConfigKey.html?strAction=editJfiSunConfigKey&configCode="+configCode;
			</jecs:power>
		}

</script>
