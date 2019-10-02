<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<form name="jbdMemberFrozenListTableForm" id="jbdMemberFrozenListTableForm" action="<c:url value='/jbdMemberFrozens.html'/>" >

<input type="hidden" id="strAction" name="strAction" value="viewJbdMemberFrozen"/>

<div class="searchBar">
	<div class="new_searchBar">
		会员编号：<input type="text" id="userCode" name="userCode" value="${param.userCode}" />		
	</div>
	<div class="new_searchBar">
		<button name="search" class="btn btn_sele" type="button" onclick="queryJbdMemberFrozen();">
			<jecs:locale  key='operation.button.search'/>
		</button>
		<button name="search" class="btn btn_ins" type="button" onclick="addJbdMemberFrozen();">
			<jecs:locale  key='operation.button.add'/>
		</button>
		<button name="button" class="btn btn_ins" type="button" onclick="delJbdMemberFrozen();">
			<jecs:locale key="operation.button.delete"/>
		</button>
		<button name="button" class="btn btn_ins" type="button" onclick="importJbdMemberFrozen();">
			导入
		</button>
		
	</div>
</div>


<ec:table 
	tableId="jbdMemberFrozenListTable"
	items="jbdMemberFrozenList"
	var="jbdMemberFrozen"
	action="${pageContext.request.contextPath}/jbdMemberFrozens.html"
	width="100%"
	form="jbdMemberFrozenListTableForm"
	retrieveRowsCallback="limit"  style="word-break:break-all"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:column alias="selectedId" headerCell="selectAll" style="width:5%;text-align:center;">
            	<input name="selectedId" type="checkbox" value="${jbdMemberFrozen.user_code}">
        	</ec:column>
			<ec:column property="user_code" title="会员编号" sortable="false"/>
		</ec:row>
</ec:table>

</form>


<script type="text/javascript">
	function queryJbdMemberFrozen(){
		document.getElementById("strAction").value = "viewJbdMemberFrozen";
		document.getElementById("jbdMemberFrozenListTableForm").submit();
		document.getElementById("strAction").value = "";
	}
	
	function addJbdMemberFrozen(){
		window.location = "${pageContext.request.contextPath}/editJbdMemberFrozen.html?strAction=addJbdMemberFrozen";
	}
	
	function importJbdMemberFrozen(){
		window.location = "${pageContext.request.contextPath}/editJbdMemberFrozen.html?strAction=importJbdMemberFrozen";
	}
	
	function delJbdMemberFrozen(){
		var checkFlag = false;
		var elements=document.getElementsByName("selectedId");
		if(elements!=undefined){
			for(var i=0;i<elements.length;i++){
				if(elements[i].checked){
					checkFlag = true;
				}
			}
		}
		if(!checkFlag){
			alert("请选择要删除的记录！");
			return;
		}
		if(confirm("确定要删除吗？")){
			document.getElementById("strAction").value = "delJbdMemberFrozen";
			document.getElementById("jbdMemberFrozenListTableForm").submit();
			document.getElementById("strAction").value = "";
		}
  	}
	
	function selectAll(theForm,status){
		var elements=document.getElementsByName("selectedId");
		if(elements!=undefined){
			for(var i=0;i<elements.length;i++){
				elements[i].checked=status;
			}
		}
	}
</script>
