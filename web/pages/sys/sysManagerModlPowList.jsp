<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysManagerModlPowList.title"/></title>
<content tag="heading"><jecs:locale key="sysManagerModlPowList.heading"/></content>
<meta name="menu" content="SysManagerModlPowMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysManagerModlPow">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSysManagerModlPow.html"/>?strAction=addSysManagerModlPow'"
			     value="<jecs:locale key="member.new.num"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="sysManagerModlPowListTable"
	items="sysManagerModlPowList"
	var="sysManagerModlPow"
	action="${pageContext.request.contextPath}/sysManagerModlPows.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editSysManagerModlPow('${sysManagerModlPow.rollId}')">
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editSysManagerModlPow.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='rollId' id='rollId'/>
</form>

<script type="text/javascript">

   function editSysManagerModlPow(rollId){
   		<jecs:power powerCode="editSysManagerModlPow">
					window.location="editSysManagerModlPow.html?strAction=editSysManagerModlPow&rollId="+rollId;
			</jecs:power>
		}

</script>
