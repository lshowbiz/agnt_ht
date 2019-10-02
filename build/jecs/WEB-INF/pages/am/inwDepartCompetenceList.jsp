<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwDepartCompetenceList.title"/></title>
<content tag="heading"><jecs:locale key="inwDepartCompetenceList.heading"/></content>
<meta name="menu" content="InwDepartCompetenceMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="inwDepartCompetences.html"  method="get" name="inwDepartCompetenceQueryForm" id="inwDepartCompetenceQueryForm">
       <div class="searchBar">&nbsp;&nbsp;
	            <jecs:title key="inwDepartment.name"/>
	            <input name="departmentName" id="departmentName" type="text" value="${param.departmentName }"/>
	            <jecs:title key="pd.createTime"/>
	                  <input id="createTimeBegin" name="createTimeBegin" type="text"
								size="8" maxlength="10" value="${param.createTimeBegin }" />
							<img src="./images/calendar/calendar7.gif"
								id="img_createTimeBegin" style="cursor: pointer;"
								title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
							<script type="text/javascript"> 
								Calendar.setup({
								inputField     :    "createTimeBegin", 
								ifFormat       :    "%Y-%m-%d",  
								button         :    "img_createTimeBegin", 
								singleClick    :    true
								}); 
							</script>
							-
							<input id="createTimeEnd" name="createTimeEnd" type="text"
								size="8" maxlength="10" value="${param.createTimeEnd }" />
							<img src="./images/calendar/calendar7.gif" id="img_createTimeEnd"
								style="cursor: pointer;"
								title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
							<script type="text/javascript"> 
								Calendar.setup({
								inputField     :    "createTimeEnd", 
								ifFormat       :    "%Y-%m-%d",  
								button         :    "img_createTimeEnd", 
								singleClick    :    true
								}); 
							</script>
	           
	            <jecs:title key="sysPower.powerName"/>
	            <input name="demandName" id="demandName" type="text" value="${param.demandName }"/>
                <input name="query"  type="button" class="button" size="1" onclick="queryInwDepartCompetence(document.inwDepartCompetenceQueryForm)" value="<jecs:locale key="operation.button.search"/>" />
                <input name="add"  type="button" class="button" size="1" onclick="addInwDepartCompetence()" value="<jecs:locale key="operation.button.add"/>" />
      </div>
</form>

<ec:table 
	tableId="inwDepartCompetenceListTable"
	items="inwDepartCompetenceList"
	var="inwDepartCompetence"
	action="${pageContext.request.contextPath}/inwDepartCompetences.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="departmentname" title="inwDepartment.name" />
				<ec:column property="demandname" title="sysPower.powerName" />
    			<ec:column property="create_time" title="inwDepartment.createTime" />
    			<ec:column property="create_user_code" title="customerRecord.createNo" />
    			<ec:column property="_1" title="title.operation">
    			     <img src="images/newIcons/search_16.gif" onclick="window.location.href='editInwDepartCompetence.html?id=${inwDepartCompetence.id }&strAction=queryDetailInwDepartCompetence';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
    			     &nbsp;&nbsp;&nbsp;&nbsp;
    			     <img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editInwDepartCompetence.html?id=${inwDepartCompetence.id }&strAction=editorInwDepartCompetence';" alt="<jecs:locale key="operation.button.edit"/>" style="cursor:pointer"/>
    			</ec:column>
				</ec:row>
</ec:table>	

<script type="text/javascript">
    function queryInwDepartCompetence(theForm){
          theForm.submit();
    }
    
    function addInwDepartCompetence(){
         var editorInwDepartCompetence = "editorInwDepartCompetence";
         window.location.href = "editInwDepartCompetence.html?strAction="+editorInwDepartCompetence;
    }

</script>
