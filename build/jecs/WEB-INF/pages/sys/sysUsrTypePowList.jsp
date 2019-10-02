<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysUsrTypePowList.title"/></title>
<content tag="heading"><jecs:locale key="sysUsrTypePowList.heading"/></content>
<meta name="menu" content="SysUsrTypePowMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addSysUsrTypePow">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editSysUsrTypePow.html"/>?strAction=addSysUsrTypePow'"
			     value="<jecs:locale key="member.new.num"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="sysUsrTypePowListTable"
	items="sysUsrTypePowList"
	var="sysUsrTypePow"
	action="${pageContext.request.contextPath}/sysUsrTypePows.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:editSysUsrTypePow('${sysUsrTypePow.id}')">
    			<ec:column property="powerId" title="sysUsrTypePow.powerId" />
    			<ec:column property="userType" title="sysUsrTypePow.userType" />
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editSysUsrTypePow.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='id' id='id'/>
</form>

<script type="text/javascript">

   function editSysUsrTypePow(id){
   		<jecs:power powerCode="editSysUsrTypePow">
					window.location="editSysUsrTypePow.html?strAction=editSysUsrTypePow&id="+id;
			</jecs:power>
		}

</script>
