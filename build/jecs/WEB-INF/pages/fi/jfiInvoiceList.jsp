<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiInvoiceList.title" /></title>
<content tag="heading">
<jecs:locale key="jfiInvoiceList.heading" />
</content>
<meta name="menu" content="JfiInvoiceMenu" />

<c:set var="buttons">
	<jecs:power powerCode="addJfiInvoice">
		<input type="button" class="button" style="margin-right: 5px"
			onclick="location.href='<c:url value="/editJfiInvoice.html"/>?strAction=addJfiInvoice'"
			value="<jecs:locale key="button.add"/>" />
	</jecs:power>
</c:set>
<div id="titleBar">
	<%-- <c:out value="${buttons}" escapeXml="false"/> --%>
</div>

<form action="jfiInvoices.html" method="get" name="jfiInvoiceSearchForm"
	id="jfiInvoiceSearchForm">
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		<jecs:power powerCode="addInvoice">
			<div class="searchBar">
				<div class="new_searchBar">
					<jecs:title key="label.member.or.agent.code" />
					<input name="userCode" type="text" value="${param['userCode'] }"
						size="14" />
				</div>
				<div class="new_searchBar">
					<jecs:title key="jfiInvoice.wyear" />
					<input name="wyear" type="text" value="${param['wyear'] }" size="10" />
				</div>
				<div class="new_searchBar">
					<jecs:title key="system.month" />
					<input name="wweek" type="text" value="${param['wweek'] }" size="10" />
				</div>
				<div class="new_searchBar">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
						<jecs:locale key="operation.button.search"/>
					</button>
					
					<button name="search" class="btn btn_ins" type="button" onclick="location.href='<c:url value="/importJfiInvoice.html"/>'">
						<jecs:locale key="button.add"/>
					</button>
				</div>
			</div>
		</jecs:power>
	</c:if>
</form>
<br>
<%-- <form:form commandName="jfiInvoice" method="post" action="" onsubmit="return validateOthers(this)" id="jfiInvoiceForm" enctype="multipart/form-data">

<spring:bind path="jfiInvoice.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>


<input name="importExcelFile" type="file" id="importExcelFile" size="50" />
		
			 <input type="submit" class="submit" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiInvoice.html"/>?strAction=importJfiInvoice'"
			        value="<jecs:locale key="button.import"/>"/>
			        

</form:form> --%>

<ec:table tableId="jfiInvoiceListTable" items="jfiInvoiceList"
	var="jfiInvoice"
	action="${pageContext.request.contextPath}/jfiInvoices.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="images/extremetable/*.gif">
	<ec:row>

		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="userName" title="sys.user.username" />
		<ec:column property="wyear" title="jfiInvoice.wyear" />
		<ec:column property="wweek" title="system.month">
			<jecs:weekFormat week="${jfiInvoice.wweek }" weekType="w" />
		</ec:column>
		<ec:column property="invoiceMoney" title="jfiInvoice.invoiceMoney" />
		<ec:column property="company" title="jfiInvoice.company" />
		<ec:column property="createTime" title="pd.createTime" />
		<ec:column property="createNo" title="fiPayData.createrName" />
		<ec:column property="remark" title="jfiBankbookJournal.remark" />

		<%-- <ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiInvoice('${jfiInvoice.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
				</ec:column> --%>

	</ec:row>

</ec:table>

<script type="text/javascript">

   function editJfiInvoice(id){
   		<jecs:power powerCode="editJfiInvoice">
					window.location="editJfiInvoice.html?strAction=editJfiInvoice&id="+id;
			</jecs:power>
		}

   function validateOthers(theForm){

		if(theForm.importExcelFile.value==""){
			alert("<jecs:locale key="bdBounsDeduct.importExcelFile.required"/>");
			theForm.importExcelFile.focus();
			return false;
		}
		return true;
	}
   
</script>
