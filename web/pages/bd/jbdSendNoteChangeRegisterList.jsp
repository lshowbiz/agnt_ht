<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function selectAll(status){
	var elements=document.getElementsByName("selectedId");
	if(elements!=undefined){
		for(var i=0;i<elements.length;i++){
			elements[i].checked=status;
		}
	}
}
function cancalRegister(theForm){
	if(!confirm("<jecs:locale key="bdSendRegister.confirm.cancalRegister"/>"))
	{
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
			theForm.strAdvicesCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.bdSendRegister"/>");
		return false;
	}
	theForm.strAction.value="cancalRegisterNote";
	
				if(isFormPosted()){
				theForm.submit();
			}
}


Date.prototype.format = function(format) {   
 var o = {
  "M+" : this.getMonth()+1,  //month
  "d+" : this.getDate(),   //day
  "h+" : this.getHours(),   //hour
  "m+" : this.getMinutes(),  //minute
  "s+" : this.getSeconds(),  //second
  "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
  "S" : this.getMilliseconds() //millisecond
 }
 if(/(y+)/.test(format)) format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
 for(var k in o)
  if(new RegExp("("+ k +")").test(format))
 format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
 return format;
}
function checkForm(theForm){
    if(theForm.wweek.value==""){
    window.alert("<jecs:locale key="operation.notice.js.bdWeek.required"/>");
      return false;
    }else{
    return true;
    }
    }
</script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<title><jecs:locale key="bdSendChangeRegisterList.title"/></title>
<content tag="heading"><jecs:locale key="bdSendChangeRegisterList.heading"/></content>
<meta name="menu" content="BdSendRecordMenu"/>


<form action="jbdSendNoteChangeRegister.html" method="get"
	name="bdSendChangeRegisterForm1" id="bdSendChangeRegisterForm1">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.status" />
			<jecs:list name="registerStatus" listCode="bdsend.sendstatus"
				value="${param.registerStatus }" defaultValue="2" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdCalculatingSubDetail.name" />
			<input name="name" type="text" value="${param.name }" size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.remittanceBNum" />
			<select name="remittanceBNum">
				<option value="">
					<jecs:locale key="list.please.select" />
				</option>
				<c:forEach items="${bdOutwardBanks }" var="var">
					<c:if test="${param.remittanceBNum==var.bankCode }">
						<option value="${var.bankCode }" selected="selected">
							${var.bankCode }
						</option>
					</c:if>
					<c:if test="${param.remittanceBNum!=var.bankCode }">
						<option value="${var.bankCode }">
							${var.bankCode }
						</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.sysUser.userName" />
			<input name="userName" type="text" value="${param.userName }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.operaterTime" />
			<input name="startOperaterTime" id="startOperaterTime" type="text" 
					value="${param.startOperaterTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					-
			<input name="endOperaterTime" id="endOperaterTime" type="text" 
					value="${param.endOperaterTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_ins" type="button" onclick=cancalRegister(bdSendChangeRegisterForm1);>
				<jecs:locale key="function.menu.cancalRegister"/>
			</button>
		</div>
	</div>



	<input type="hidden" name="strAction" value="jbdSendNoteChangeRegister" />
	<input type="hidden" name="strAdvicesCodes" value="" />



</form>


<form action="jbdSendNoteChangeRegister.html" method="get"
	name="bdSendChangeRegisterForm" id="bdSendChangeRegisterForm">
	<ec:table tableId="jbdSendNoteListTable" items="jbdSendNoteList"
		var="jbdSendNote" autoIncludeParameters="true"
		retrieveRowsCallback="limit" form="bdSendChangeRegisterForm"
		action="${pageContext.request.contextPath}/jbdSendNoteChangeRegister.html"
		width="100%" rowsDisplayed="20" sortable="true"
		imagePath="./images/extremetable/*.gif">

		<ec:row>


			<ec:exportXls fileName="jbdSendNoteChangeRegister.xls"></ec:exportXls>

			<ec:column property="1" title="alCharacterKey.select1"
				sortable="false" styleClass="centerColumn" viewsDenied="xls">
				<input type="checkbox" name="selectedId" id="selectedId"
					value="${jbdSendNote.id}" class="checkbox" />
			</ec:column>
			<ec:column property="createTime" title="bd.app.time" />
			<ec:column property="jmiMember.userCode" title="miMember.memberNo"
				escapeAutoFormat="true" />
			<ec:column property="jmiMember.miUserName"
				title="bdCalculatingSubDetail.name" />
			<ec:column property="remittanceBNum"
				title="bdSendRecord.remittanceBNum" escapeAutoFormat="true" />

			<ec:column property="jmiMember.bank" title="bdSendRecord.openBank"
				escapeAutoFormat="true" />
			<ec:column property="jmiMember.bankaddress"
				title="bdSendRemittanceReport.openCityCH" />
			<ec:column property="jmiMember.bankbook"
				title="bdOutwardBank.accountName" />
			<ec:column property="jmiMember.bankcard" title="bdSendRecord.bnum"
				escapeAutoFormat="true" />

			<ec:column property="remittanceMoney"
				title="bdSendRecord.remittanceMoney" styleClass="numberColumn">
				<fmt:formatNumber value="${jbdSendNote.remittanceMoney}"
					type="number" pattern="###,###,##0.00" />
			</ec:column>
			<ec:column property="sendDate" title="bdSendRecord.sendDate">
				<fmt:formatDate value="${jbdSendNote.sendDate}" pattern="yyyy-MM-dd" />
			</ec:column>
			<ec:column property="operaterCode"
				title="bdSendRecord.sysUser.userName" />
			<ec:column property="registerstatus"
				title="bdSendRecord.registerStatus" styleClass="centerColumn"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="bdsendregister.registerstatus"
					value="${jbdSendNote.registerStatus}" />
			</ec:column>
			<ec:column property="reissueStatus" title="bdSendRecord.supplyStatus"
				styleClass="centerColumn"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="bdsendrecord.reissuestatus"
					value="${jbdSendNote.reissueStatus}" />
			</ec:column>

			<ec:column property="sendLateCause"
				title="bdSendRecord.sendLateCause"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:code listCode="bdsendrecord.sendlateremark"
					value="${jbdSendNote.sendLateCause }" />
			</ec:column>
			<ec:column property="sendLateRemark"
				title="bdSendRecord.sendLateRemark" />

			<ec:column property="operation" title="title.operation"
				sortable="false" styleClass="centerColumn" viewsDenied="xls">
				<img src="images/newIcons/pencil_16.gif"
					onclick="window.location.href='editJbdSendNoteChangeRegister.html?id=${jbdSendNote.id }';"
					alt="<jecs:locale key="button.edit"/>" style="cursor: pointer" />
			</ec:column>

		</ec:row>

	</ec:table>
</form>


