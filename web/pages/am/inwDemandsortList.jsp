<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="inwDemandsortList.title"/></title>
<content tag="heading"><jecs:locale key="inwDemandsortList.heading"/></content>
<meta name="menu" content="InwDemandsortMenu"/>
<content tag="heading"><jecs:locale key="inwDemandsortDetail.heading"/></content>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/inwDemandsortManager.js'/>" ></script>

<form  action="inwDemandsorts.html" method="get" name="inwDemandsortQuery" id="inwDemandsortQuery">
         <input type="hidden" id="strAction" name="strAction" value="${param.strAction}"/>
         <font size="2"><jecs:title key="inwDemandsort.name"></jecs:title></font>
         <input name="name" id="name" value="${param.name }"/>
          <input name="search" type="button" class="button" onclick="inwDemandsortQqq(document.inwDemandsortQuery)" value="<jecs:locale key="operation.button.search"/>"/>
         <input type="button" name="add"  class="button" onclick="inwDemandsortAdd()" value="<jecs:locale key="member.new.num"/>" />
</form>

<ec:table 
	tableId="inwDemandsortListTable"
	items="inwDemandsortList"
	var="inwDemandsort"
	action="${pageContext.request.contextPath}/inwDemandsorts.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="name" title="inwDemandsort.name" />
    			<ec:column property="1" title="sysOperationLog.moduleName" >
						<img src="images/newIcons/search_16.gif" onclick="window.location.href='inwDemandsorts.html?id=${inwDemandsort.id }&strAction=queryDetailInwDemandsort';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
						&nbsp;&nbsp;
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editInwDemandsort.html?id=${inwDemandsort.id }&strAction=updateInwDemandsort';" alt="<jecs:locale key="operation.button.edit"/>" style="cursor:pointer"/>
						&nbsp;&nbsp;
				        <img  src="images/newIcons/delete_16.gif"  onclick="inwProblemDelete(${inwDemandsort.id})"  alt="<jecs:locale  key="operation.button.delete"/>" style="cursor:pointer" />
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">
      function  inwDemandsortQqq(theForm){
            theForm.strAction.value="queryInwDemandsort";
			if(isFormPosted()){
				theForm.submit();
			}
      }
      
      function   inwDemandsortAdd(){
            window.location.href = "editInwDemandsort.html?strAction=addInwDemandsort";      
      }
 
      function inwProblemDelete(id){
             if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
                  inwDemandsortManager.removeInwDemandsort(id,pRefresh);
             }
      }
      
      function pRefresh(){
            window.location.href = "inwDemandsorts.html?strAction=queryInwDemandsort";
     }
</script>
