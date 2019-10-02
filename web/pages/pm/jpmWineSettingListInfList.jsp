<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmWineSettingListInfList.title"/></title>
<content tag="heading"><jecs:locale key="jpmWineSettingListInfList.heading"/></content>
<meta name="menu" content="JpmWineSettingListInfMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpmWineSettingListInf">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpmWineSettingListInf.html"/>?strAction=addJpmWineSettingListInf'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpmWineSettingListInfListTable"
	items="jpmWineSettingListInfList"
	var="jpmWineSettingListInf"
	action="${pageContext.request.contextPath}/jpmWineSettingListInfs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmWineSettingListInf('${jpmWineSettingListInf.idf}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="materialNo" title="jpmWineSettingListInf.materialNo" />
    			<ec:column property="qty" title="jpmWineSettingListInf.qty" />
    			<ec:column property="sdate" title="jpmWineSettingListInf.sdate" />
    			<ec:column property="edate" title="jpmWineSettingListInf.edate" />
    			<ec:column property="memo" title="jpmWineSettingListInf.memo" />
    			<ec:column property="settingId" title="jpmWineSettingListInf.settingId" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmWineSettingListInf(idf){
   		<jecs:power powerCode="editJpmWineSettingListInf">
					window.location="editJpmWineSettingListInf.html?strAction=editJpmWineSettingListInf&idf="+idf;
			</jecs:power>
		}

</script>
