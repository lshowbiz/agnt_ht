<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>

<script type="text/javascript">
var bdsendmoney= Array();
function selectAll(status){
	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}
function registerSuccess(theForm){
	theForm.strRegisterSuccessCodes.value=''
	if(!confirm("确定发送积分到电子商场？"))
	{
		return false;
	}
	var elements=document.getElementsByName("selectedId");

	if(elements==undefined){
		alert("请选择条目");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strRegisterSuccessCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.bdSendRegister"/>");
		return false;
	}
	$('strAction').value="miCoinLogSend";
			if(isFormPosted()){
				theForm.submit();
			}
}

</script>



<c:if test="${not empty mesList }">
	<c:forEach items="${mesList}" var="varMap">
		${varMap.info }<br />
	</c:forEach>

</c:if>



<form action="miCoinLogs.html" method="post"
	name="bdSendRecordAllotForm1" id="bdSendRecordAllotForm1">

	<div class="searchBar">
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" id="strAction" name="strAction"
				value="miCoinLogs" />
			<input type="hidden" name="strRegisterSuccessCodes" value="" />
			发送状态：
			<jecs:list name="status" listCode="jsm.status"
				value="${param.status }" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_ins" onclick="registerSuccess(bdSendRecordAllotForm1)">
				发送
			</button>
	
			<b>总积分:<fmt:formatNumber pattern="###,###,###,###,##0.00"
					value="${sum_balance }"></fmt:formatNumber>
		</div>
		</b>
	</div>
</form>
<c:set var="footTotalVar">
	<tr id="aaa">
		<td id="frontTd" align="right" class="footer" colspan="4">
			总计:
		</td>
		<td class="footer" align="right">
			<b> <fmt:formatNumber value="${sumCoin }"
					pattern="###,###,##0.00"></fmt:formatNumber> </b>
		</td>
		<td colspan="4" class="footer"></td>
	</tr>
</c:set>


<ec:table tableId="miCoinLogListTable" items="miCoinLogList"
	var="miCoinLog"
	action="${pageContext.request.contextPath}/miCoinLogs.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true"
	imagePath="images/extremetable/*.gif">
	<ec:row>
		<ec:column property="1"
			title="<input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchAll();>"
			sortable="false" styleClass="centerColumn">
			<c:if test="${miCoinLog.status=='1' }">
				<input type="checkbox" name="selectedId" id="selectedId"
					value="${miCoinLog.id}" class="checkbox" />
			</c:if>


		</ec:column>

		<ec:column property="userCode" title="编号" />
		<ec:column property="coinType" title="积分类型">
			<jecs:code listCode="cointype" value="${miCoinLog.coinType }"></jecs:code>
		</ec:column>
		<ec:column property="dealType" title="接收方">
			<jecs:code listCode="dealtype" value="${miCoinLog.dealType }"></jecs:code>
		</ec:column>
		<ec:column property="coin" title="积分" />
		<ec:column property="status" title="发送状态">
			<jecs:code listCode="jsm.status" value="${miCoinLog.status }"></jecs:code>
		</ec:column>
		<ec:column property="sendDate" title="发送时间" />
		<ec:column property="createTime" title="创建时间" />
		<ec:column property="createNo" title="创建人" />
	</ec:row>

</ec:table>


