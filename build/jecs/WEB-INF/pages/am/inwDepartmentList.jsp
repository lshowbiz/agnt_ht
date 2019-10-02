<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwDepartmentList.title"/></title>
<content tag="heading"><jecs:locale key="inwDepartmentList.heading"/></content>
<meta name="menu" content="InwDepartmentMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="inwDepartments.html"  method="get" name="inwDepartmentQueryForm" id="inwDepartmentQueryForm">  
       <div class="searchBar">&nbsp;&nbsp;
        
                <input name="departCategory" id="departCategory" value="${param.departCategory }" style="display:none"/>
                <c:if test="${strAction== 'deleteInwQueryDepartment'}">
                      <input name="strAction" id="strAction" value="deleteInwQueryDepartment" style="display:none"/>
                </c:if>
                <c:if test="${strAction== 'selectInwDepartByInwDC'}">
                      <input name="strAction" id="strAction" value="selectInwDepartByInwDC" style="display:none"/>
                </c:if>
                
	            <jecs:title key="sysDepartment.departmentName"/>
	            <input name="name" id="name" type="text" value="${param.name }"/>
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
	           
	        <c:if test="${strAction!='queryHigerDepart'}">
	             <jecs:title key="sysDepartment.parentDepartmentName"/>
	             <input name="higerDepartName" id="higerDepartName" type="text" value="${param.higerDepartName }"/>
            </c:if>
                <input name="query"  type="button" class="button" size="1" onclick="queryInwDepartment(document.inwDepartmentQueryForm)" value="<jecs:locale key="operation.button.search"/>" />
                <c:if test="${strAction !='queryHigerDepart' && strAction!='deleteInwQueryDepartment' && strAction != 'selectInwDepartByInwDC'}">
                      <input name="add"  type="button" class="button" size="1" onclick="addInwDepartment()" value="<jecs:locale key="operation.button.add"/>" />
                </c:if>
      </div>
</form>

<div id="loading">
<ec:table 
	tableId="inwDepartmentListTable"
	items="inwDepartmentList"
	var="inwDepartment"
	action="${pageContext.request.contextPath}/inwDepartments.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
				  <c:if test="${strAction=='queryHigerDepart'}">
				       <ec:column property="_1" title="button.select" >
				          <img src="images/newIcons/tick_16.gif"  onclick="selectInwDepartment('${inwDepartment.id}','${inwDepartment.name}')"  alt="<jecs:locale key="button.select"/>"style="cursor:pointer"/>
				       </ec:column>
				  </c:if>
				  <c:if test="${strAction=='selectInwDepartByInwDC'}">
				       <ec:column property="_2" title="button.select" >
				          <img src="images/newIcons/tick_16.gif"  onclick="selectInwDepartmentTwo('${inwDepartment.id}','${inwDepartment.name}')"  alt="<jecs:locale key="button.select"/>"style="cursor:pointer"/>
				       </ec:column>
				  </c:if>
	    			<ec:column property="name" title="inwDepartment.name" />
	    			<ec:column property="category" title="inwDepartment.category">
	    			     <c:if test="${inwDepartment.category=='1'}">
	    			         <jecs:code listCode="inwdepartment.departcategory" value="${inwDepartment.category}"/>
	    			     </c:if>
	    			     <c:if test="${inwDepartment.category=='2'}">
	    			         <jecs:code listCode="inwdepartment.departcategory" value="${inwDepartment.category}"/>
	    			     </c:if>
	    			     <c:if test="${inwDepartment.category=='3'}">
	    			         <jecs:code listCode="inwdepartment.departcategory" value="${inwDepartment.category}"/>
	    			     </c:if>
	    			</ec:column>
	    			<ec:column property="higerDepartName" title="inwDepartment.higerDepartName" />
	    			<ec:column property="createTime" title="inwDepartment.createTime" />
	    			<c:if test="${strAction!='selectInwDepartByInwDC'}">
		    			<c:if test="${strAction!='queryHigerDepart'}">
			    			<ec:column property="_3" title="title.operation" >
									<img src="images/newIcons/search_16.gif" onclick="window.location.href='editInwDepartment.html?id=${inwDepartment.id }&strAction=queryDetailInwDepartment';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
			    			         <c:if test="${strAction != 'deleteInwQueryDepartment'}">
				    			        &nbsp;&nbsp;&nbsp;&nbsp;
										<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editInwDepartment.html?id=${inwDepartment.id }&strAction=editorInwDepartment';" alt="<jecs:locale key="operation.button.edit"/>" style="cursor:pointer"/>
				    			     </c:if>  
			    			        <c:if test="${strAction == 'deleteInwQueryDepartment'}">
			    			            &nbsp;&nbsp;&nbsp;&nbsp;
									    <img src="images/newIcons/delete_16.gif" onclick="window.location.href='editInwDepartment.html?id=${inwDepartment.id }&strAction=deleteInwDepartment';" alt="<jecs:locale key="operation.button.edit"/>" style="cursor:pointer"/>
			    			        </c:if> 
			    			</ec:column>
		    			</c:if>
	    			</c:if>
				</ec:row>
</ec:table>	
</div>

<script type="text/javascript">
         function queryInwDepartment(theForm){
               theForm.submit();
         }
         
         function addInwDepartment(){
              var editorInwDepartment = "editorInwDepartment";
              window.location.href="editInwDepartment.html?strAction="+editorInwDepartment;
         }
         
         function selectInwDepartment(higerId,higerDepartName){
              window.opener.document.getElementById("higerId").value = higerId;
              window.opener.document.getElementById("higerDepartName").value = higerDepartName;
              this.close();
         }
         
         function selectInwDepartmentTwo(departmentId,departmentName){
               window.opener.document.getElementById("departmentId").value = departmentId;
               window.opener.document.getElementById("departmentName").value = departmentName;
               this.close();
         }
         
</script>
