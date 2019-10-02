<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiDepositSendList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiDepositSendList.heading" />
</content>
<meta name="menu" content="JfiDepositSendMenu" />


<script type="text/javascript">

		function allotSelected(theForm){
			theForm.strAdviceCodes.value='';
		
			if(!confirm("<jecs:locale key="bdSendRecordAllot.confirm.allot"/>")){
				return false;
			}
			var elements=document.getElementsByName("selectedId");
		
			if(elements==undefined){
				alert("<jecs:locale key="please.select.bdSendRegister"/>");
				return false;
			}
			var selectedOne=false;
			for(var i=0;i<elements.length;i++){
				if(elements[i].checked){
					selectedOne=true;
					theForm.strAdviceCodes.value+=","+elements[i].value;
				}
			}
			
			if(!selectedOne){
				alert("<jecs:locale key="please.select.bdSendRegister"/>");
				return false;
			}
			theForm.strAction.value="depositMoneySend";
			if(isFormPosted()){
				theForm.submit();
			}
		}
		function deleteSelected(theForm){
			theForm.strAdviceCodes.value='';
		
			if(!confirm("<jecs:locale key="bdSendRecordAllot.confirm.allot"/>")){
				return false;
			}
			var elements=document.getElementsByName("selectedId");
		
			if(elements==undefined){
				alert("<jecs:locale key="please.select.bdSendRegister"/>");
				return false;
			}
			var selectedOne=false;
			for(var i=0;i<elements.length;i++){
				if(elements[i].checked){
					selectedOne=true;
					theForm.strAdviceCodes.value+=","+elements[i].value;
				}
			}
			
			if(!selectedOne){
				alert("<jecs:locale key="please.select.bdSendRegister"/>");
				return false;
			}
			theForm.strAction.value="deleteDepositMoney";
			if(isFormPosted()){
				theForm.submit();
			}
		}	
		function allotSelectedAll(theForm){
		
			if(!confirm("确认执行操作？")){
				return false;
			}
		
			theForm.strAction.value="depositMoneySendAll";
			if(isFormPosted()){
				theForm.submit();
			}
		}	
</script>



<c:if test="${not empty errorList }">

	<c:forEach var="msg" items="${errorList}">
				${msg }<br />
	</c:forEach>

</c:if>



<form action="jfiDepositSends.html" method="post" name="miMemberForm"
	id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction"
		value="jfiDepositSends" />
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value="" />
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode}" size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdBounsDeduct.wweek"></jecs:title>
			<input name="wweek" type="text" value="${param.wweek }" size="10" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="customerRecord.state" />
			<jecs:list name="status" listCode="status.send" defaultValue="1"
				value="${param.status }"></jecs:list>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="cancel" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="cancel" class="btn btn_ins" type="button" onclick="deleteSelected(document.miMemberForm)">
				删除
			</button>
			<c:if test="${param.status=='1' }">
				<button name="search" class="btn btn_long" type="button" onclick="allotSelected(document.miMemberForm)">
					<jecs:locale key="function.menu.allotSelectedNote"/>
				</button>
				<button name="search" class="btn btn_long" type="button" onclick="allotSelectedAll(document.miMemberForm)">
					<jecs:locale key="function.menu.allotSelectedNote.all"/>
				</button>
			</c:if>
		</div>
	</div>



</form>





<ec:table tableId="jfiDepositSendListTable" items="jfiDepositSendList"
	var="jfiDepositSend"
	action="${pageContext.request.contextPath}/jfiDepositSends.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:row>


		<ec:column property="1" title="alCharacterKey.select1"
			sortable="false" styleClass="centerColumn">

			<input type="checkbox" name="selectedId" id="selectedId"
				value="${jfiDepositSend.id}" class="checkbox" />
		</ec:column>


		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="userName" title="bdCalculatingSubDetail.name" />
		<ec:column property="wyear" title="财年" />

		<ec:column property="wweek" title="bdBounsDeduct.wweek">
			<jecs:weekFormat week="${jfiDepositSend.wweek }" weekType="w" />
		</ec:column>
		<ec:column property="depositMoney" title="实退保证金" />
		<ec:column property="remark" title="备注" />
		<ec:column property="createTime" title="创建时间"
			format="yyyy-MM-dd HH:mm:ss" cell="date" />
		<ec:column property="createNo" title="创建人" />
		<ec:column property="status" title="状态">
			<jecs:code listCode="status.send" value="${jfiDepositSend.status }"></jecs:code>
		</ec:column>
		<ec:column property="checkNo" title="审核人" />
		<ec:column property="checkTime" title="审核时间" />
	</ec:row>

</ec:table>

