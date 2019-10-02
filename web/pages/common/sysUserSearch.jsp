<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCompanyList.title"/></title>
<content tag="heading"><jecs:locale key="alCompanyList.heading"/></content>
<meta name="menu" content="AlCompanyMenu"/>
<meta http-equiv="Cache-Control" CONTENT="no-cache">

<form name="frm" action="<c:url value='/sysUserSelect.html'/>" target="_self">
<div id="titleBar">
<table width="100%" border="0">
	<tr>
		<td>
			<input type="hidden" id="selectControl" name="selectControl" value="${requestMap.selectControl}"/>
			<input type="hidden" id="userCodeElementId" name="userCodeElementId" value="${requestMap.userCodeElementId}"/>
			<input type="hidden" id="userNameElementId" name="userNameElementId" value="${requestMap.userNameElementId}"/>
			<input type="hidden" id="searchFlag" name="searchFlag" value="show"/>
			<div class="new_searchBar">
				<jecs:locale  key="sys.user.usercode"/>:
				<input name="userCode" id="userCode" value="<c:out value='${requestMap.userCode}'/>" cssClass="text medium"/>
			</div>
			<div class="new_searchBar">
				<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
			</div>
		</td>
	</tr>
</table>
</div>



<ec:table tableId="sysUserListTable"
	items="sysUserList"
	var="sysUser"
	form="frm"
	method="post"
	retrieveRowsCallback="limit"
	width="100%"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row onclick="javascript:selectSysUser('${sysUser.userCode}','${sysUser.userName}')">
				
    			<ec:column property="userCode" title="sys.user.usercode" />
    			<ec:column property="userName" title="sys.user.username" />
    			
				</ec:row>

</ec:table>	
</form>

<script type="text/javascript">
    function selectSysUser(userCode,userName){
    //	var userCodeElementId = $('userCodeElementId').value;
    //	var userNameElementId = $('userNameElementId').value;
    //
    //	window.opener.document.getElementById('companyNo').value=companyCode;
    //	window.opener.document.getElementById('companyName').value=companyName;
    	var ret = new Object();
    	ret['userCode']=userCode;
    	ret['userName']=userName;
    	parent.window.returnValue=ret;
    	parent.window.close();
    	
    	
					//window.location="editPiSalesModel.html?smId="+smId;
		}
		
		
</script>
