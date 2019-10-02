<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx }/scripts/jquery/jquery-142min.js"> </script>

<form action="jbdMemberStarRewards.html" method="post" name="jbdMemberStarRewards" id="jbdMemberStarRewards" onsubmit="return validateMyForm(this);">
<input type="hidden" name="strAction" id="strAction"/>
<input type="hidden" name="ids" id="ids"/>
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="bdPeriod.fyear"/>	
		<input name="fyear" type="text" id="query_fyear" value="${param.fyear }" size="8"/>	
	</div>
	<div class="new_searchBar">
		<jecs:label key="rewards.repolicy"/>
		<c:if test="${empty param.fyear }">
			<select>
				<option>请输入财政年查询</option>
			</select>
		</c:if>
		<c:if test="${not empty param.fyear }">
			${param.fyear }
			<jecs:list name="rewards" id="query_rewards" listCode="star.rewards.repolicy.${param.fyear }" 
			defaultValue="" value="${param.rewards }" showBlankLine="true"/>
		</c:if>
	</div>
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo"/>	
		<input name="userCode" type="text" id="query_userCode" value="${param.userCode }" size="10"/>	
	</div>
	<div class="new_searchBar">
		<jecs:label key="rewards.isReach"/>
		<jecs:list name="isReach" id="query_isReach" listCode="yesno" 
		defaultValue="" value="${param.isReach }" showBlankLine="true"></jecs:list>
	</div>
	<div class="new_searchBar" style="margin-left: 40px">
		<button name="search" type="submit" class="btn btn_sele">
			<jecs:locale key="operation.button.search"/>
		</button>
		<button name="search" type="button" class="btn btn_ins"
			onclick="batchDelete(document.jbdMemberStarRewards);">删除
		</button>
		<%-- 
		<input name="search" type="submit" class="button" value="<jecs:locale key="operation.button.search"/>"/>	
		<input name="search" type="button" class="button" value="删除" onclick="batchDelete(document.jbdMemberStarRewards);"/>
		--%>
	</div>
</div>

</form>


<ec:table 
	tableId="JbdMemberStarRewardsListTable"
	items="jbdMemberStarRewards"
	var="jbdMemberStarReward"
	action="${pageContext.request.contextPath}/jbdMemberStarRewards.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:exportXls fileName="jbdMemberStarRewards.xls"/>
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn" viewsAllowed="html">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jbdMemberStarReward.id }" class="checkbox" />
				</ec:column>
    			<ec:column property="user_code" title="miMember.memberNo" style="text-align:center"/>
    			<ec:column property="mi_user_name" title="bdCalculatingSubDetail.name" style="text-align:center"/>
    			<ec:column property="f_year" title="bdPeriod.fyear" style="text-align:center"/>
    			<ec:column property="rewards" title="rewards.repolicy" style="text-align:center" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			<jecs:code listCode="star.rewards.repolicy.${param.fyear }" value="${jbdMemberStarReward.rewards}"/>
    			</ec:column>
    			
    			<ec:column property="is_reach" title="rewards.isReach" style="text-align:center" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			<jecs:code listCode="yesno" value="${jbdMemberStarReward.is_reach}"/>
    			</ec:column>
    			
    			<ec:column property="remark" title="schedule.remark" style="text-align:center">
    				<span title="${jbdMemberStarReward.remark}">${jbdMemberStarReward.remark}</span>
    			</ec:column>
    			
    			<ec:column property="member_remark" title="会员备注" style="text-align:center">
    				<span title="${jbdMemberStarReward.member_remark}">${jbdMemberStarReward.member_remark}</span>
    			</ec:column>
    			
    			<ec:column property="2" title="title.operation" sortable="false" style="text-align:center">
    			
    			<jecs:power powerCode="editJbdMemberStarRewards">
    			<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJbdMemberStarRewards.html?id=${jbdMemberStarReward.id}&strAction=editJbdMemberStarRewards';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
    			</jecs:power>
    			<jecs:power powerCode="deleteJbdMemberStarRewards">
					<a onclick="deleteConfirm(${jbdMemberStarReward.id});"><img border="0" src="images/icons/w.gif" alt="<jecs:locale key="operation.button.delete"/>" /></a>
				</jecs:power>
    			</ec:column>
    			
				</ec:row>

</ec:table>	

<script type="text/javascript">
var tips = "<jecs:locale key='amMessage.confirmdelete'/>";
function deleteConfirm(id) {
	if(window.confirm(tips)) {
		var userCode=$("#query_userCode").val();
		if(!userCode)userCode="";
		var fyear=$("#query_fyear").val();
		if(!fyear)fyear="";
		var rewards=$("#query_rewards").val();
		if(!rewards)rewards="";
		var isReach=$("#query_isReach").val();
		if(!isReach)isReach="";
		
		window.location.href="jbdMemberStarRewards.html?id="+id+"&userCode="+userCode+"&fyear="+fyear+"&rewards="+rewards+"&isReach="+isReach+"&strAction=deleteJbdMemberStarRewards";
	}
}

function batchDelete(theForm){
	theForm.ids.value='';

	if(!confirm("确认删除吗?")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");

	if(elements==undefined){
		alert("请选择要删除的记录！");
		return false;
	}
	var selectedOne=false;
	var ids="";
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			ids+=elements[i].value+",";
		}
	}
	if(!selectedOne){ 
		alert("请选择要删除的记录！");
		return false;
	}
	ids = ids.substring(0,ids.length-1);
	theForm.ids.value=ids;
	theForm.strAction.value="batchDeleteJbdMemberStarRewards"; 
	if(isFormPosted()){
		theForm.submit();
	}
}

function validateMyForm(theForm){
	var query_fyear = $("#query_fyear").val();
	if(query_fyear==null||query_fyear==""){
		alert("请输入财政年!");
		return false;
	}
	return true;
}
</script>