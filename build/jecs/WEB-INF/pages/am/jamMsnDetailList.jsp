<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jamMsnDetailList.title"/></title>
<content tag="heading"><jecs:locale key="jamMsnDetailList.heading"/></content>
<meta name="menu" content="JamMsnDetailMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJamMsnDetail">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJamMsnDetail.html"/>?strAction=addJamMsnDetail'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<%--<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>--%>
<ec:table 
	tableId="jamMsnDetailListTable"
	items="jamMsnDetailList"
	var="jamMsnDetail"
	action="${pageContext.request.contextPath}/jamMsnDetails.html"
	width="100%"
	showPagination="false"
	showStatusBar="false"
	sortable="false"
	rowsDisplayed="20" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="msn_name" title="jamMsnType.msnName" />
    			<ec:column property="status" title="customerRecord.state" >
    				<jecs:code listCode="msnstatus" value="${jamMsnDetail.status }"/>
    			</ec:column>	
				<ec:column property="edit" title="title.operation" sortable="false">
					<jecs:power powerCode="memberMsnStatus">
				
						
						<c:if test="${jamMsnDetail.status=='0'}">
						<a href="jamMsnDetails.html?strAction=memberMsnStatus&mdId=${jamMsnDetail.md_id }&mtId=${jamMsnDetail.mt_id }&status=1" onclick="return window.confirm('<jecs:locale key="member.msnstatus.change"/>');">
						<img border="0" src="images/icons/w.gif" alt="<jecs:locale key="member.msnstatus0"/>" />
						</a>
						</c:if>	
							
						<c:if test="${jamMsnDetail.status=='1'}">
						<a href="jamMsnDetails.html?strAction=memberMsnStatus&mdId=${jamMsnDetail.md_id }&mtId=${jamMsnDetail.mt_id }&status=0" onclick="return window.confirm('<jecs:locale key="member.msnstatus.change"/>');">
						<img border="0" src="images/icons/r.gif" alt="<jecs:locale key="member.msnstatus1"/>" />
						</a>
						</c:if>
					</jecs:power>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJamMsnDetail(mdId){
   		<jecs:power powerCode="editJamMsnDetail">
					window.location="editJamMsnDetail.html?strAction=editJamMsnDetail&mdId="+mdId;
			</jecs:power>
		}

</script>
