<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="amMessagePermitList.title"/></title>
<content tag="heading"><jecs:locale key="amMessagePermitList.heading"/></content>
<meta name="menu" content="AmMessagePermitMenu"/>

<%-- 
<c:set var="buttons">
		<jecs:power powerCode="addAmMessagePermit">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editAmMessagePermit.html"/>?strAction=addAmMessagePermit&msgClassNo=${requestParaMap.msgClassNo}'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
--%>
<c:set var="buttons">
		<jecs:power powerCode="addAmMessagePermit">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editAmMessagePermit.html"/>?strAction=addAmMessagePermit&msgClassNo=${requestParaMap.msgClassNo}'"><jecs:locale key="button.add"/></button>
		</jecs:power>
</c:set>

<%-- 
<div id="titleBar" class="searchBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>

<div id="titleBar" class="searchBar">
	<div class="new_searchBar">
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
</div>
<ec:table 
	tableId="amMessagePermitListTable"
	items="amMessagePermitList"
	var="amMessagePermit"
	action="${pageContext.request.contextPath}/amMessagePermits.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="msgClassNo" title="amMessage.msgClassNo" >
        <jecs:code listCode="msgclassno" value="${amMessagePermit.msgClassNo}"/>
    			</ec:column>
    			<ec:column property="userCode" title="sysOperationLog.operaterName" />
				<%--<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editAmMessagePermit('${amMessagePermit.ampNo}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>--%>
					<ec:column property="edit" title="title.operation" sortable="false" >
						<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" onclick="javascript:editAmMessagePermit('${amMessagePermit.ampNo}')" style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editAmMessagePermit(ampNo){
   		<jecs:power powerCode="editAmMessagePermit">
					window.location="editAmMessagePermit.html?strAction=editAmMessagePermit&ampNo="+ampNo;
			</jecs:power>
		}

</script>
