<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberNycQualifyList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberNycQualifyList.heading"/></content>
<meta name="menu" content="JpoMemberNycQualifyMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpoMemberNycQualify">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoMemberNycQualify.html"/>?strAction=addJpoMemberNycQualify'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoMemberNycQualifyListTable"
	items="jpoMemberNycQualifyList"
	var="jpoMemberNycQualify"
	action="${pageContext.request.contextPath}/jpoMemberNycQualifys.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNycQualify('${jpoMemberNycQualify.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="id" title="jpoMemberNycQualify.id" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

   function editJpoMemberNycQualify(id){
   		<jecs:power powerCode="editJpoMemberNycQualify">
					window.location="editJpoMemberNycQualify.html?strAction=editJpoMemberNycQualify&id="+id;
			</jecs:power>
		}

</script>
