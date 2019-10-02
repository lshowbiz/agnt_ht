<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberTeamList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberTeamList.heading"/></content>
<meta name="menu" content="JmiMemberTeamMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJmiMemberTeam">
			    <button type="button" class="btn btn_ins" style="width:auto"
			        onclick="location.href='<c:url value="/editJmiMemberTeam.html"/>?strAction=addJmiMemberTeam'"><jecs:locale key="button.add"/></button>
		</jecs:power>
</c:set>

<form name="frm" id="frm"
	action="<c:url value='/jmiMemberTeams.html'/>">
	<input type="hidden" id="batchSynProduct" name="batchSynProduct"
		value="" />
	<input type="hidden" id="strAction" name="strAction"
		value="${requestParaMap.strAction}" />
		<div class="searchBar">
		<%--去掉搜索框的边框 --%>
		<%-- 
		<table width="100%" border="0">
			<tr>
				<td>
				--%>
					<input type="hidden" id="strAction" name="strAction"
						value="${param.strAction}" />
					<input type="hidden" id="selectControl" name="selectControl"
						value="${param.selectControl}" />
					<div class="new_searchBar">		
						<label><jecs:locale key="jmiMemberTeam.code" />:</label>
						<input name="code" id="code"
							value="<c:out value='${param.code}'/>" cssClass="text medium" />
					</div>
					<div class="new_searchBar">
						<label><jecs:locale key="jmiMemberTeam.name" />:</label>
						<input name="name" id="name"
							value="<c:out value='${param.name}'/>" cssClass="text medium" />
					</div>
					<div class="new_searchBar">	
						<label><jecs:locale key="jmiMemberTeam.fullname" />:</label>
						<input name="fullName" id="fullName"
							value="<c:out value='${param.fullName}'/>" cssClass="text medium"/>
					</div>
					<div class="new_searchBar">		
						<label><jecs:locale key="jmiMemberTeam.status" />:</label>
						<jecs:list listCode="jmimemberteam.status" name="status" id="status"
							showBlankLine="false" value="${param.status}"
							defaultValue="" />
					</div>
					<div class="new_searchBar">		
						<jecs:title key="jmiMemberTeam.isbuy" />
						<jecs:list listCode="yesno" name="isbuy" id="isbuy"
							showBlankLine="false" value="${param.isbuy}"
							defaultValue="" />
					</div>
					<div class="new_searchBar">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
						<c:out value="${buttons}" escapeXml="false" />
					</div>	
<%-- 					
				</td>	
			</tr>
		</table>
		--%>
	</div>
		
<!--  
	<div class="searchBar">
		<table width="100%" border="0">
			<tr>
				<td>
					<input type="hidden" id="strAction" name="strAction"
						value="${param.strAction}" />
					<input type="hidden" id="selectControl" name="selectControl"
						value="${param.selectControl}" />
						
					<jecs:locale key="jmiMemberTeam.code" />
					<input name="code" id="code"
						value="<c:out value='${param.code}'/>" cssClass="text medium" />
					
					<jecs:locale key="jmiMemberTeam.name" />
					<input name="name" id="name"
						value="<c:out value='${param.name}'/>" cssClass="text medium" />
						
					<jecs:locale key="jmiMemberTeam.fullname" />
					<input name="fullName" id="fullName"
						value="<c:out value='${param.fullName}'/>" cssClass="text medium" />
						
					<jecs:locale key="jmiMemberTeam.status" />
					<jecs:list listCode="jmimemberteam.status" name="status" id="status"
						showBlankLine="false" value="${param.status}"
						defaultValue="" />
						
					<jecs:locale key="jmiMemberTeam.isbuy" />
					<jecs:list listCode="yesno" name="isbuy" id="isbuy"
						showBlankLine="false" value="${param.isbuy}"
						defaultValue="" />
	
					<input type="submit" class="button"
						value="<jecs:locale  key='operation.button.search'/>" />
					<c:out value="${buttons}" escapeXml="false" />
					
				</td>	
			</tr>
		</table>
	</div>
-->
</form>
<ec:table 
	tableId="jmiMemberTeamListTable"
	items="jmiMemberTeamList"
	var="jmiMemberTeam"
	action="${pageContext.request.contextPath}/jmiMemberTeams.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="code" title="jmiMemberTeam.code" />
    			<ec:column property="name" title="jmiMemberTeam.name" />
    			<ec:column property="fullName" title="jmiMemberTeam.fullname" />
    			<ec:column property="status" title="jmiMemberTeam.status" >
    				<jecs:code listCode="jmimemberteam.status" value="${jmiMemberTeam.status}"/>
    			</ec:column>	
    			<ec:column property="isBuy" title="jmiMemberTeam.isbuy" >
    				<jecs:code listCode="yesno" value="${jmiMemberTeam.isBuy}"/>
    			</ec:column>
    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJmiMemberTeam('${jmiMemberTeam.code}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
				</ec:column>
				</ec:row>

</ec:table>	
<script type="text/javascript">

   function editJmiMemberTeam(code){
   		<jecs:power powerCode="editJmiMemberTeam">
					window.location="editJmiMemberTeam.html?strAction=editJmiMemberTeam&code="+code;
			</jecs:power>
		}

</script>
