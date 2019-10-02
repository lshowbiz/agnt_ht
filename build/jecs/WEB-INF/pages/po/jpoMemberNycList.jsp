<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberNycList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberNycList.heading"/></content>
<meta name="menu" content="JpoMemberNycMenu"/>
<%-- 
<c:set var="buttons">
		<jecs:power powerCode="addJpoMemberNyc">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoMemberNyc.html"/>?strAction=addJpoMemberNyc'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>

    <jecs:power powerCode="importJpoMemberNyc">
        <input type="button" class="button" style="margin-right: 5px"
               onclick="location.href='<c:url value="/importJpoMemberNyc.html"/>'"
               value="<jecs:locale key="button.import"/>"/>
    </jecs:power>

</c:set> 
--%>

<form action="jpoMemberNycs.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:title key="poMemberNyc.yearOfMonth"/>
		<input name="yearofmonth" type="text" value="${param.yearofmonth }"/> 
	</div>
	<div class="new_searchBar">
		<jecs:title key="poMemberNyc.memberNo"/>
		<input id="memberNo" name="memberNo" type="text" size="10" maxlength="10" value="${param.memberNo }"/>
	</div>
	<div class="new_searchBar">
		<button name="search" class="btn btn_sele" type="submit">
			<jecs:locale key="operation.button.search"/>
		</button>
		<jecs:power powerCode="addJpoMemberNyc">
			<button type="button" class="btn btn_ins" 
				onclick="location.href='<c:url value="/editJpoMemberNyc.html"/>?strAction=addJpoMemberNyc'">
				<jecs:locale key="button.add"/>
			</button>
			<%-- 
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoMemberNyc.html"/>?strAction=addJpoMemberNyc'"
			        value="<jecs:locale key="button.add"/>"/>
			--%>
		</jecs:power>
		<jecs:power powerCode="importJpoMemberNyc">
			<button type="button" class="btn btn_long" 
				onclick="location.href='<c:url value="/importJpoMemberNyc.html"/>'">
				<jecs:locale key="button.import"/>
			</button>
			<%-- 
	        <input type="button" class="button" style="margin-right: 5px"
	               onclick="location.href='<c:url value="/importJpoMemberNyc.html"/>'"
	               value="<jecs:locale key="button.import"/>"/>
	        --%>
	    </jecs:power>
		<%--<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />--%>
	</div>
	
		<%-- <c:out value="${buttons}" escapeXml="false"/> --%>
</div>
</form>
<ec:table 
	tableId="jpoMemberNycListTable"
	items="jpoMemberNycList"
	var="jpoMemberNyc"
	action="${pageContext.request.contextPath}/jpoMemberNycs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoMemberNyc('${jpoMemberNyc.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="yearOfMonth" title="poMemberNyc.yearOfMonth" />
    			<ec:column property="memberNo" title="poMemberNyc.memberNo" />
    			<ec:column property="4" title="poMemberNyc.pushAt" >
					<fmt:formatDate value="${jpoMemberNyc.pushAt}" pattern="yyyy-MM-dd"/>
				</ec:column>
    			<ec:column property="remarks" title="poMemberNyc.remarks" />
    			<ec:column property="status" title="poMemberNyc.status" >
					<c:if test="${jpoMemberNyc.status=='0' }">
						<img src="<c:url value='/images/icons/unLock.gif'/>"
                             onclick="javascript:editJpoMemberNyc('${jpoMemberNyc.id}')"
							 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/>
					</c:if>
					<c:if test="${jpoMemberNyc.status=='1' }">
						<img src="<c:url value='/images/icons/lock.gif'/>"
                             onclick="javascript:editJpoMemberNyc('${jpoMemberNyc.id}')"
							 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/>
					</c:if>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoMemberNyc(id){
   		<jecs:power powerCode="editJpoMemberNyc">
					window.location="editJpoMemberNyc.html?strAction=editJpoMemberNyc&id="+id;
			</jecs:power>
		}

</script>
