<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jamMsnTypeList.title"/></title>
<content tag="heading"><jecs:locale key="jamMsnTypeList.heading"/></content>
<meta name="menu" content="JamMsnTypeMenu"/>

<div class="new_searchBar">		
		&nbsp;
		<button name="search" class="btn btn_sele" onclick="location.href='<c:url value="/editJamMsnType.html"/>?strAction=addJamMsnType'" >
			<jecs:locale key="operation.button.add"/>
		</button>
</div>
<ec:table 
	tableId="jamMsnTypeListTable"
	items="jamMsnTypeList"
	var="jamMsnType"
	action="${pageContext.request.contextPath}/jamMsnTypes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="msnKey" title="bdOutwardBank.bankCode" />
    			<ec:column property="msnName" title="jamMsnType.msnName" />
    			<ec:column property="msnStatus" title="customerRecord.state">
    				<jecs:code listCode="msnstatus" value="${jamMsnType.msnStatus }"/>
    			</ec:column>
    			
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>"  onclick="javascript:editJamMsnType('${jamMsnType.mtId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
<img src="images/newIcons/search_16.gif" onclick="window.location.href='editJamMsnType.html?strAction=viewJamMsnType&mtId=${jamMsnType.mtId}';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJamMsnType(mtId){
   		<jecs:power powerCode="editJamMsnType">
					window.location="editJamMsnType.html?strAction=editJamMsnType&mtId="+mtId;
			</jecs:power>
		}

</script>
