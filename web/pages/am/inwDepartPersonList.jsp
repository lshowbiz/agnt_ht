<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwDepartPersonList.title"/></title>
<content tag="heading"><jecs:locale key="inwDepartPersonList.heading"/></content>
<meta name="menu" content="InwDepartPersonMenu"/>

<form action="inwDepartPersons.html"  method="get" name="inwDepartPersonQueryForm" id="inwDepartPersonQueryForm"> 
       <div class="searchBar">&nbsp;&nbsp;
	           <jecs:title key="miMember.memberNo"/>
	           <input name="userCode" id="userCode" type="text" value="${param.userCode }"/>
	           <jecs:title key="inwDepartment.name"/>	            
	           <input name="name" id="name" type="text" value="${param.name }"/>
               <input name="query"  type="button" class="button" size="1" onclick="queryInwDepartPerson(document.inwDepartPersonQueryForm)" value="<jecs:locale key="operation.button.search"/>" />           
               <input name="add"  type="button" class="button" size="1" onclick="addInwDepartPerson()" value="<jecs:locale key="operation.button.add"/>" />
      </div>
</form>


<ec:table 
	tableId="inwDepartPersonListTable"
	items="inwDepartPersonList"
	var="inwDepartPerson"
	action="${pageContext.request.contextPath}/inwDepartPersons.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row >
				
    			<ec:column property="user_code" title="miMember.memberNo" />
    			<ec:column property="name" title="inwDepartment.name" />
    			<ec:column property="create_time" title="inwDepartment.createTime" />   			
    			<ec:column property="_1" title="title.operation" >
						<img src="images/newIcons/pencil_16.gif" onclick="updateInwDepartPerson('${inwDepartPerson.id }')" alt="<jecs:locale key="operation.button.edit"/>" style="cursor:pointer"/>
    			</ec:column>
		</ec:row>
</ec:table>	

<script type="text/javascript">

       function queryInwDepartPerson(theForm){
           theForm.submit();
       }
       
       function addInwDepartPerson(){
           window.location.href = "editInwDepartPerson.html?strAction=addInwDepartPerson";
       }
       
       function updateInwDepartPerson(id){
           window.location.href = "editInwDepartPerson.html?strAction=editorInwDepartPerson&id="+id;
       }
   
</script>
