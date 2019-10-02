<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysDataLogDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysDataLogDetail.heading"/></content>

<script language="javascript" src="scripts/validate.js"></script>
<script type="text/javascript" src="<c:url value="/dwr/interface/alCompanyManager.js"/>"></script>
<script type="text/javascript" src="<c:url value="/dwr/engine.js"/>"></script>
<script type="text/javascript" src="<c:url value="/dwr/util.js"/>"></script>

<form action="clone_sys_power.html" method="post" name="formCloneByUser" id="formCloneByUser" onsubmit="return validateCloneByUser(this)">

<table width="100%" border="0" class="detail">
	<tr>
		<td colspan="2" class="title"><jecs:locale key="title.copy.to.user"/></td>
		</tr>
	<tr>
		<th><jecs:label key="notice.select.user"/></th>
		<td><table border="0" cellspacing="0" class="inSideTable">
			<tr>
				<td><jecs:locale key="notice.select.from.user"/>:</td>
				<td><jecs:locale key="notice.copy.from.company"/>:</td>
				<td>&nbsp;</td>
				<td><jecs:locale key="info.select.copy.to.user"/>:</td>
			</tr>
			<tr>
				<td><input type="text" name="fromUserKeyword" id="fromUserKeyword" style="width:196px;" onkeyup="filterUser(this,this.form.fromUser,event)"/></td>
				<td><jecs:locale key="info.select.copy.company"/></td>
				<td>&nbsp;</td>
				<td><input type="text" name="toUserKeyword" id="toUserKeyword" style="width:196px;" onkeyup="filterUser(this,this.form.toUser,event)"/></td>
			</tr>
			<tr>
				<td valign="top"><select name="fromUser" size="10" id="fromUser" style="width:200px;height:140px;" onchange="changeFromUser(this.value, this.form.fromUserCompany)">
				</select></td>
				<td valign="top"><select name="fromUserCompany" size="10" multiple="multiple" id="fromUserCompany1" style="width:200px;height:140px;"></select>				</td>
				<td>=&gt;</td>
				<td valign="top"><select name="toUser" size="10" multiple="multiple" id="toUser" style="width:200px;height:140px;">
				</select></td>
			</tr>

		</table></td>
	</tr>
	<tr>
		<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
		<td class="command"><input type="submit" class="button" name="submit" value="<jecs:locale key="button.copy"/>"/>
			<input name="doType" type="hidden" id="doType" value="byUser" /></td>
	</tr>
</table>
</form>
<br />
<form action="clone_sys_power.html" method="post" name="formCloneByCompany" id="formCloneByCompany" onsubmit="return validateCloneByCompany(this)">
	<table width="100%" border="0" class="detail">
		<tr>
			<td colspan="2" class="title"><jecs:locale key="title.copy.to.company"/></td>
		</tr>
		<tr>
			<th><jecs:label key="notice.select.user"/></th>
			<td><table border="0" cellspacing="0" class="inSideTable">
				<tr>
					<td><jecs:locale key="notice.select.from.user"/>:</td>
					<td><jecs:locale key="notice.copy.from.company"/>:</td>
					<td>&nbsp;</td>
					<td><jecs:locale key="please.select.copy.to.company"/>:</td>
				</tr>
				<tr>
					<td><input type="text" name="fromUserKeyword" id="fromUserKeyword" style="width:196px;" onkeyup="filterUser(this,this.form.fromUser,event)"/></td>
					<td><jecs:locale key="info.select.copy.company"/></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td valign="top"><select name="fromUser" size="10" id="fromUser" style="width:200px;height:140px;" onchange="changeFromUser(this.value, this.form.fromUserCompany)">
					</select></td>
					<td valign="top"><select name="fromUserCompany" size="10" id="fromUserCompany2" style="width:200px;height:140px;"></select>
					</td>
					<td>=&gt;</td>
					<td valign="top"><select name="toUserCompany" size="10" multiple="multiple" id="toUserCompany" style="width:200px;height:140px;">
						<c:forEach items="${alCompanys}" var="toCompany">
							<option value="${toCompany.companyCode}">[${toCompany.companyCode}]${toCompany.companyName}</option>
						</c:forEach>
					</select></td>
				</tr>
			</table></td>
		</tr>
		<tr>
			<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
			<td class="command"><input type="submit" class="button" name="submit" value="<jecs:locale key="button.copy"/>"/>
				<input name="doType" type="hidden" id="doType" value="byCompany" /></td>
		</tr>
	</table>
</form>

<script>
var myUsers=[<c:forEach items="${sysManagers}" var="fromSysManager" varStatus="sysManagerStatus"><c:if test="${sysManagerStatus.index>0}">,</c:if>{key:"${fromSysManager.user_code}",label:"${fromSysManager.user_name}(${fromSysManager.user_code})"}</c:forEach>];
window.onload=function(){
	reloadUserSelect(document.formCloneByUser.fromUser,"");
	reloadUserSelect(document.formCloneByUser.toUser,"");
	reloadUserSelect(document.formCloneByCompany.fromUser,"");
}

function reloadUserSelect(obj, searchText){
	obj.options.length=0;
	for(var i=0;i<myUsers.length;i++){
		if(searchText=="" || myUsers[i].label.indexOf(searchText)!=-1){
			var varItem = new Option(myUsers[i].label,myUsers[i].key);      
        	obj.options.add(varItem);
		}
	}
}

function filterUser(obj, listObj, e){
	var _key;
	if (e == null) { // ie
		_key = event.keyCode;
	} else { // firefox
		_key = e.which;
	}

	if(_key != 13){
		reloadUserSelect(listObj,obj.value);
	}
}

function changeFromUser(userCode, targetObj){
	dwr.util.removeAllOptions(targetObj.id);
 	alCompanyManager.getMyAlCompanysToMap(userCode, function(data){
 	dwr.util.addOptions(targetObj.id, data);
	});
}

function validateCloneByUser(theForm){
	if(theForm.fromUser.value==""){
		alert("<jecs:locale key="notice.select.from.user"/>");
		return false;
	}
	
	if(theForm.fromUserCompany.value==""){
		alert("<jecs:locale key="info.select.copy.company"/>");
		return false;
	}
	
	if(theForm.toUser.value==""){
		alert("<jecs:locale key="info.select.copy.to.user"/>");
		return false;
	}
	
	if(!confirm("<jecs:locale key="confirm.clone.power"/>")){
		return false;
	}
	
	return isFormPosted();
}

function validateCloneByCompany(theForm){
	if(theForm.fromUser.value==""){
		alert("<jecs:locale key="notice.select.from.user"/>");
		return false;
	}
	
	if(theForm.fromUserCompany.value==""){
		alert("<jecs:locale key="info.select.copy.company"/>");
		return false;
	}
	
	if(theForm.toUserCompany.value==""){
		alert("<jecs:locale key="please.select.copy.to.company"/>");
		return false;
	}
	
	if(!confirm("<jecs:locale key="confirm.clone.power"/>")){
		return false;
	}
	
	return isFormPosted();
}
</script>