<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiAvailableInvoiceList.title" />
</title>
<content tag="heading">
<jecs:locale key="fiAvailableInvoiceList.heading" />
</content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./js/jquery-1.4.2.min.js"> </script>
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<meta name="menu" content="FiAvailableInvoiceMenu" />


<form action="fiAvailableInvoices.html" method="get"
	name="fiAvailableInvoiceQuery" id="fiAvailableInvoiceQuery">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="pd.agentormember"></jecs:title>
			<input name="userCode" id="userCode" value="${param.userCode }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="sys.user.username"></jecs:title>
			<input name="userName" id="userName" value="${param.userName }" />
		</div>
		<div class="new_searchBar">
			<!-- 
	         <jecs:title key="fiAvailableInvoice.jyear"></jecs:title>
	         <input name="jyear" id="jyear" value="${param.jyear }" onkeyup="this.value=this.value.replace(/\D/g,'')"/> -->
			<jecs:title key="bdBounsDeduct.wweek"></jecs:title>
			<input name="jmonth" id="jmonth" value="${param.jmonth }"
				onkeyup="this.value=this.value.replace(/\D/g,'')" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiAvailableInvoice.ownedCompany"></jecs:title>
			<input name="ownedCompany" id="ownedCompany"
				value="${param.ownedCompany }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="pd.createTime" />
			<input name="createTimeStart" id="createTimeStart" type="text" 
					value="${param.createTimeStart }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="createTimeEnd" id="createTimeEnd" type="text" 
					value="${param.createTimeEnd }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="pd.checkstatus"></jecs:title>
			<jecs:list name="status" listCode="fiavailableinvoice.status"
				defaultValue="" value="${param.status }" showBlankLine=""></jecs:list>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" onclick="fiAvailableInvoiceQ(document.fiAvailableInvoiceQuery)">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
	<ec:table tableId="fiAvailableInvoiceListTable"
		items="fiAvailableInvoiceList" var="fiAvailableInvoice"
		action="${pageContext.request.contextPath}/fiAvailableInvoices.html"
		width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
		rowsDisplayed="20" sortable="false"
		imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column property="userCode" title="pd.agentormember" />
			<ec:column property="userName" title="sys.user.username" />
			<ec:column property="invoiceValue"
				title="fiAvailableInvoice.invoiceValue" />
			<ec:column property="bond" title="fiAvailableInvoice.bond" />
			<ec:column property="jmonth" title="bdBounsDeduct.wweek">
				<jecs:weekFormat week="${fiAvailableInvoice.jmonth }" weekType="w" />
			</ec:column>
			<ec:column property="ownedCompany"
				title="fiAvailableInvoice.ownedCompany" />
			<ec:column property="createTime" title="pd.createTime" />
			<ec:column property="status" title="pd.checkstatus">
				<jecs:code listCode="jmistore.status"
					value="${fiAvailableInvoice.status}"></jecs:code>
			</ec:column>
			<ec:column property="_1" title="sysOperationLog.moduleName">
				<img src="images/newIcons/pencil_16.gif"
					onclick="javascript:editFiAvailableInvoice('${fiAvailableInvoice.id}')"
					alt="<jecs:locale key="operation.button.edit"/>"
					style="cursor: pointer" />
			</ec:column>
		</ec:row>

	</ec:table>
</form>
<script type="text/javascript">

   function editFiAvailableInvoice(id){
		    window.location="editFiAvailableInvoice.html?strAction=editFiAvailableInvoice&id="+id;
   }

   function fiAvailableInvoiceQ(theForm){
       theForm.submit();
  }

</script>
