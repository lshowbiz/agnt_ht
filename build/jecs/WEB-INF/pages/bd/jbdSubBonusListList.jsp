<%@ include file="/common/taglibs.jsp"%>

<%@ page contentType="text/html; charset=utf-8" language="java" %>




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
			theForm.strAction.value="allotSelectedSubBonusAll";
			if(isFormPosted()){
				theForm.submit();
			}
		}
			
		function allotSelectedAll(theForm){
			
			
			if(!confirm("确认执行操作？")){
				return false;
			}
		
			theForm.strAction.value="allotSelectedSubBonusAll";
			if(isFormPosted()){
				theForm.submit();
			}
		}	
</script>





<c:if test="${not empty errorList  }">
	<div class="error" id="errorMessages">
		<c:forEach var="error" items="${errorList}">
			<img src="<c:url value="images/newIcons/warning_16.gif"/>"
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
</c:if>



<form action="jbdSubBonusLists.html" method="post" name="bdSendRecordForm1" id="bdSendRecordForm1">

<input type="hidden" id="strAction" name="strAction" value="jbdSubBonusLists"/>
<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value=""/>
<div class="searchBar">

<div class="new_searchBar">	
		<jecs:title key="bdBounsDeduct.treatedNo"/>
			<input name="treatedNo" type="text" value="${param.treatedNo }" size="12"/>
			</div>
			
<div class="new_searchBar">					
<button name="search" type="submit" class="btn btn_sele" >
	<jecs:locale key="operation.button.search"/>
</button>

<%--<input name="search" class="button" type="button" onclick="allotSelected(document.bdSendRecordForm1)" value="<jecs:locale key="function.menu.allotSelectedNote"/>" />--%>
<button name="search" class="btn btn_long" type="button" onclick="allotSelectedAll(document.bdSendRecordForm1)" >
	分销奖金进电子存折
</button>
</div>



</div>



</form>




	<ec:table 
	tableId="jbdSubBonusListListTable"
	items="jbdSubBonusListList"
	var="jbdSubBonusList"
	action="${pageContext.request.contextPath}/jbdSubBonusLists.html"
	width="100%"
		showPagination="false"
		showStatusBar="true"
	 sortable="true" imagePath="./images/extremetable/*.gif" > 
	 
	 
	 
				<ec:row highlightRow="false">
					
    			
    			<ec:column property="treated_no" title="批号" />
				
    			<ec:column property="user_code" title="miMember.memberNo" />
    			<ec:column property="remark" title="摘要" />
    			
    			<ec:column property="amount" title="金额" styleClass="numberColumn" cell="number" format="###,###,##0.00" calc="total" calcTitle="poOrder.amtCount">
    			
    				
    				<fmt:formatNumber value="${jbdSubBonusList.amount }" pattern="###,###,##0.00"></fmt:formatNumber>
    			</ec:column>
    			
    				
				</ec:row>

</ec:table>	
