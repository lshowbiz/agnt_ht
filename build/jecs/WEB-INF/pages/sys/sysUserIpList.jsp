<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUserIpList.title"/></title>
<content tag="heading"><jecs:locale key="sysUserIpList.heading"/></content>
<meta name="menu" content="SysUserIpMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysUserIp">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSysUserIp.html"/>?strAction=addSysUserIp'"
			     value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="sysUserIpListTable"
	items="sysUserIpList"
	var="sysUserIp"
	action="${pageContext.request.contextPath}/sysUserIps.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editSysUserIp('${sysUserIp.ipId}')">
    			<ec:column property="userCode" title="sysUserIp.userCode" />
    			<ec:column property="ipAddress" title="sysUserIp.ipAddress" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editSysUserIp.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='ipId' id='ipId'/>
</form>

<script type="text/javascript">

   function editSysUserIp(ipId){
   		<jecs:power powerCode="editSysUserIp">
					window.location="editSysUserIp.html?strAction=editSysUserIp&ipId="+ipId;
			</jecs:power>
		}

</script>
