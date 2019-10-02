<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCompanyList.title"/></title>
<content tag="heading"><jecs:locale key="alCompanyList.heading"/></content>
<meta name="menu" content="AlCompanyMenu"/>


<form name="frm" action="<c:url value='/sysUserSelect.html'/>" >
<div class="searchBar">
			<input type="hidden" id="strAction" name="strAction" value="${requestMap.strAction}"/>
			<input type="hidden" id="selectControl" name="selectControl" value="${requestMap.selectControl}"/>
			<input type="hidden" id="userCodeElementId" name="userCodeElementId" value="${requestMap.userCodeElementId}"/>
			<input type="hidden" id="userNameElementId" name="userNameElementId" value="${requestMap.userNameElementId}"/>
			<input type="hidden" id="totalRows" name="totalRows" value="${sysUserListTable_totalRows}"/>
			<input type="hidden" id="searchFlag" name="searchFlag" value="${requestMap.searchFlag}"/>
            <div class="new_searchBar">
						<c:choose>
							<c:when test="${requestMap.selectControl eq 'company'}">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:locale  key="ammessage.sendroute.0"/>:
							</c:when>
							<c:otherwise>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:locale  key="label.member.or.agent.code"/>:
							</c:otherwise>
						</c:choose>
						<input name="userCode" id="userCode" value="<c:out value='${requestMap.userCode}'/>" cssClass="text medium"/>
			</div>
			<div class="new_searchBar">	
						<jecs:locale  key="sysOperationLog.operaterName"/>:
					    <input name="userName" id="userName" value="<c:out value='${requestMap.userName}'/>" cssClass="text medium"/>
						
			</div>
			<div class="new_searchBar">
			           <button type="submit" class="btn btn_sele"  style="width:auto">
							   <jecs:locale  key='operation.button.search'/>
					    </button>
			</div>	
</div>


<!-- 
<div id="titleBar">
<table width="100%" border="0">
	<tr>
		<td>
			<input type="hidden" id="strAction" name="strAction" value="${requestMap.strAction}"/>
			<input type="hidden" id="selectControl" name="selectControl" value="${requestMap.selectControl}"/>
			<input type="hidden" id="userCodeElementId" name="userCodeElementId" value="${requestMap.userCodeElementId}"/>
			<input type="hidden" id="userNameElementId" name="userNameElementId" value="${requestMap.userNameElementId}"/>
			<input type="hidden" id="totalRows" name="totalRows" value="${sysUserListTable_totalRows}"/>
			<input type="hidden" id="searchFlag" name="searchFlag" value="${requestMap.searchFlag}"/>

			<c:choose>
				<c:when test="${requestMap.selectControl eq 'company'}">
					<jecs:locale  key="ammessage.sendroute.0"/>:
				</c:when>
				<c:otherwise>
					<jecs:locale  key="label.member.or.agent.code"/>:
				</c:otherwise>
			</c:choose>
			<input name="userCode" id="userCode" value="<c:out value='${requestMap.userCode}'/>" cssClass="text medium"/>
				<jecs:locale  key="sysOperationLog.operaterName"/>:
			<input name="userName" id="userName" value="<c:out value='${requestMap.userName}'/>" cssClass="text medium"/>
			<input type="submit" class="button"  value="<jecs:locale  key='operation.button.search'/>"/>
				
		</td>
	</tr>
</table>
</div>
 -->


<ec:table tableId="sysUserListTable"
	items="sysUserList"
	var="sysUser"
	form="frm"
	action="${pageContext.request.contextPath}/sysUserSelect.html"
	retrieveRowsCallback="limit"
	width="100%"
	rowsDisplayed="150" sortable="true" imagePath="../images/extremetable/*.gif">
				<ec:row onclick="javascript:selectSysUser('${sysUser.userCode}','${sysUser.userName}','${sysUser.userType}')">
				
    			<ec:column property="userCode" title="label.member.or.agent.code" />
    			<ec:column property="userName" title="sys.user.username" />
    			
				</ec:row>

</ec:table>	
</form>

<script type="text/javascript">
    function selectSysUser(userCode,userName,userType){
    //	var userCodeElementId = $('userCodeElementId').value;
    //	var userNameElementId = $('userNameElementId').value;
    //
    //	window.opener.document.getElementById('companyNo').value=companyCode;
    //	window.opener.document.getElementById('companyName').value=companyName;
    	var ret = new Object();
    	ret['userCode']=userCode;
    	ret['userName']=userName;
    	ret['userType']=userType;
    	top.window.returnValue=ret;
    	top.window.close();
    	
    	
					//window.location="editPiSalesModel.html?smId="+smId;
		}
		
		
		function loadMe(){
				var totalRows = $('totalRows').value;
				alert(totalRows);
					if( 1 == totalRows){
							selectSysUser('${sysUserList[0].userCode}','${sysUserList[0].userName}','${sysUserList[0].userType}');
						}
				
			}
			
		
			//loadMe();
				
</script>
