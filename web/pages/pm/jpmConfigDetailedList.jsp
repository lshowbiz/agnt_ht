<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmConfigDetailedList.title"/></title>
<content tag="heading"><jecs:locale key="jpmConfigDetailedList.heading"/></content>
<meta name="menu" content="JpmConfigDetailedMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpmConfigDetailed">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmConfigDetailed.html"/>?strAction=addJpmConfigDetailed'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpmConfigDetailedListTable"
	items="jpmConfigDetailedList"
	var="jpmConfigDetailed"
	action="${pageContext.request.contextPath}/jpmConfigDetaileds.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmConfigDetailed('${jpmConfigDetailed.configdetailedNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="configdetailedNo" title="jpmConfigDetailed.configdetailedNo" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

   function editJpmConfigDetailed(configdetailedNo){
   		<jecs:power powerCode="editJpmConfigDetailed">
					window.location="editJpmConfigDetailed.html?strAction=editJpmConfigDetailed&configdetailedNo="+configdetailedNo;
			</jecs:power>
		}

</script>
