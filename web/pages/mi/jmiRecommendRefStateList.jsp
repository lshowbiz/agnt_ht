<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
	function countDate(theForm){
		theForm.operateStr.value='count';
		theForm.submit();
	}
	function editState(theForm,operateStr){
		theForm.operateStr.value=operateStr;
		theForm.submit();
	}
</script>

<title><jecs:locale key="jmiRecommendRefList.title"/></title>
<content tag="heading"><jecs:locale key="jmiRecommendRefList.heading"/></content>
<meta name="menu" content="JmiRecommendRefMenu"/>


<form name="updateForm" method="get" action="" id="updateForm">

	<table class="detail" width="100%">
	
		<tr>
			<th><jecs:title key="miMember.memberNo" /></th>
			<td>
				<input id="userCode" name="userCode" value="${param.userCode }" 
				<c:if test="${count>0 }">readonly="readonly" </c:if>
				>
				
			</td>
		</tr>
		<tr>
			<th><jecs:title key="jmiRecommend.count" /></th>
			<td>
				<span id="count" >${ count}</span>
			</td>
		</tr>
		
		<c:if test="${count>0 }">
			<tr>
				<th><jecs:title key="busi.common.remark" /></th>
				<td>
					<input id="remark" name="remark" value="${param.remark }">
				</td>
			</tr>
		</c:if>
		
	<th class="command"><jecs:title key="sysOperationLog.moduleName" /></th>
	<td class="command">
	
	<c:if test="${count<=0 ||empty count}">
		<input type="button" name="submit1" onclick="javascript:countDate(this.form);" value="<jecs:locale key="button.next"/>" class="button"/>
	</c:if>
	<c:if test="${count>0 }">
		<input type="button" name="submit1" onclick="javascript:editState(this.form,'changeState1');" value="<jecs:locale key="member.status.limit1"/>" class="button"/>
		<input type="button" name="submit1" onclick="javascript:editState(this.form,'changeState0');" value="<jecs:locale key="member.status.limit0"/>" class="button"/>
		
		<input type="button" name="submit1" onclick="javascript:window.location.href='jmiRecommendRefState.html'" value="<jecs:locale key="operation.button.return"/>" class="button"/>
	</c:if>
		<input type="hidden" name="operateStr" id="operateStr" value=""/>
	
	</td>
	</table>
</form>
