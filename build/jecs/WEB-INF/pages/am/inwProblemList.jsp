<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwProblemList.title"/></title>
<content tag="heading"><jecs:locale key="inwProblemList.heading"/></content>
<meta name="menu" content="InwProblemMenu"/>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/inwProblemManager.js'/>" ></script>
<script type="text/javascript">
       function checkApplication(theForm){
			theForm.strAction.value="queryInwProblem";
			if(isFormPosted()){
				theForm.submit();
			}
	   }
</script> 
<table width="100%">
<tr class="searchBar">
<td width="25%">
<form  name="inwProblemForm1" id="inwProblemForm1" action="inwProblems.html">
    <input type="hidden" id="strAction" name="strAction" value="queryInwProblem"/>
              <font size="2"><jecs:title key="inwProblem.species"/></font>
              <jecs:list name="species" listCode="inwproblem.type" value="${param.species}" defaultValue="" showBlankLine=""/>	
              <input name="search" type="button" class="button" onclick="checkApplication(document.inwProblemForm1)" value="<jecs:locale key="operation.button.search"/>"/>
              <jecs:power powerCode="addInitInwproblem">
                  <input name="inwProblemAdd" type="button" class="button" onclick="location.href='<c:url value="/editInwProblem.html"/>?strAction=addInwProblem'" value="<jecs:locale key="member.new.num"/>"/>
              </jecs:power>
</form>
</td>
<td width="75%" align="left">
<input name="inwProblemAudit" class="button"  type="button"  onclick="inwProblemAudit()" value="<jecs:locale  key='button.audit'/>" >
</td>
</tr>
</table>
<div id="loading">
<ec:table 
	tableId="inwProblemListTable"
	items="inwProblemList"
	var="inwProblem"
	action="${pageContext.request.contextPath}/inwProblems.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row>
				<ec:column property="1111111" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
						<input type="checkbox" name="selectedId" id="selectedId" value="${inwProblem.id}" class="checkbox" />
				</ec:column>
				
    			<ec:column property="species" title="inwProblem.species">
    			     <c:if test = "${inwProblem.species=='1'}">
    			          <jecs:code listCode="inwproblem.type" value="1"/>
    			     </c:if>
    			      <c:if test = "${inwProblem.species=='2'}">
    			          <jecs:code listCode="inwproblem.type" value="2"/>
    			     </c:if>
    			      <c:if test = "${inwProblem.species=='3'}">
    			          <jecs:code listCode="inwproblem.type" value="3"/>
    			     </c:if>
    			      <c:if test = "${inwProblem.species=='4'}">
    			          <jecs:code listCode="inwproblem.type" value="4"/>
    			     </c:if>
    			</ec:column>
    			<ec:column property="ask" title="inwProblem.problem" />
    			<ec:column property="other" title="inwDemand.verify">
    			            <c:if test="${empty inwProblem.other}"><font color="red">
		    				      <jecs:locale key="aiAgent.status1"/>
		    				</c:if>
		    				<c:if test="${inwProblem.other=='Y'}"></font>
		    				      <jecs:locale key="amannounce.status.1"/>
		    				</c:if>
		    	</ec:column>
    			<ec:column property="1" title="sysOperationLog.moduleName" >
						<img src="images/newIcons/search_16.gif" onclick="window.location.href='inwProblems.html?id=${inwProblem.id }&strAction=queryDetailInwProblem';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
						&nbsp;&nbsp;
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editInwProblem.html?id=${inwProblem.id }&strAction=updateInwProblem';" alt="<jecs:locale key="operation.button.edit"/>" style="cursor:pointer"/>
						&nbsp;&nbsp;
				        <img  src="images/newIcons/delete_16.gif"  onclick="inwProblemDelete(${inwProblem.id})"  alt="<jecs:locale  key="operation.button.delete"/>" style="cursor:pointer" />
				</ec:column>
				</ec:row>

</ec:table>	
</div>
<script type="text/javascript">
                      function inwProblemDelete(id){
                          if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
                             inwProblemManager.removeInwProblem(id,pRefresh);
                          }
                      }
                      
                      function pRefresh(){
                           window.location.href = "inwProblems.html?strAction=queryInwProblem";
                      }
                      
                      function inwProblemAudit(){
							var idList = getSelectedInfoRows();
							if(idList.length==0){
								alert("<jecs:locale key="sys.role.noselect"/>");
								return;
							}else{
								inwProblemManager.inwProblemAudit(idList,function(){window.location.reload();});
							}
	                  }
	                  
	                  function getSelectedInfoRows(){
							   var elements = document.getElementsByName("selectedId");
							      var temp = "";
							   for(var i=0;i<elements.length;i++){
							        if(elements[i].checked){
							           temp += (temp=="")? elements[i].value : "," + elements[i].value ;    
							        }
							   }
							   return temp;
	                  }
</script>