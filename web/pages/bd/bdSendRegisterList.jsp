<%@ include file="/common/taglibs.jsp"%>
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
	var registerDate=document.getElementById("registerDate").value;
	if(registerDate==''){
		alert("<jecs:locale key="please.input.date"/>");
		return false;
	}
	if(!confirm("<jecs:locale key="bdSendRegister.confirm.register"/>"))
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
			theForm.strRegisterSuccessCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.bdSendRegister"/>");
		return false;
	}
	$('strAction').value="registerSuccess";
			if(isFormPosted()){
				theForm.submit();
			}
}

function reissueSubmit(theForm){

	if(!confirm("<jecs:locale key="bdSendRegister.confirm.reissueSelect"/>"))
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
			theForm.strRegisterSuccessCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.bdSendRegister"/>");
		return false;
	}
	if(theForm.sendLateCauseForSelect.value==''){
		alert("<jecs:locale key="busi.bdsend.select.reason"/>");
		return false;
	}
	theForm.strAction.value="reissueSubmit";
				if(isFormPosted()){
				theForm.submit();
			}
}
function registerFail(theForm){

	if(!confirm("<jecs:locale key="bdSendRegister.confirm.reissueSelect"/>"))
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
			theForm.strRegisterSuccessCodes.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<jecs:locale key="please.select.bdSendRegister"/>");
		return false;
	}
	theForm.strAction.value="registerFail";
				if(isFormPosted()){
				theForm.submit();
			}
}
function check_amount(){
    var elements = document.getElementsByName('selectedId');
	var money=0;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			var inputBox = bdsendmoney[elements[i].value];
			money+=Number(inputBox);
		}
	}
	var nAmount = document.getElementById('nAmount');
	 money=Math.round(money*100)/100;
	nAmount.innerHTML = money;
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
<title><jecs:locale key="bdSendRecordAllotList.title" />
</title>
<content tag="heading">
<jecs:locale key="bdSendRecordAllotList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="bdSendRegister.html" method="post"
	name="bdSendRecordAllotForm1" id="bdSendRecordAllotForm1">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.registerStatus" />
			<jecs:list name="registerStatus"
				listCode="bdsendregister.registerstatus1"
				value="${param.registerStatus }" defaultValue="1" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdBounsDeduct.wweek" />
			<input name="wweek" type="text" value="${param.wweek }" size="8" />
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
			<jecs:title key="bdSendRecord.openBank" />
			<select name="bank">
				<option value="">
					<jecs:locale key="list.please.select" />
				</option>
				<c:forEach items="${sysBanks }" var="bank">
	
					<c:if test="${param.bank==bank.bankKey }">
						<option value="${bank.bankKey }" selected="selected">
							<jecs:locale key="${bank.bankValue }" />
						</option>
					</c:if>
					<c:if test="${param.bank!=bank.bankKey}">
						<option value="${bank.bankKey }">
							<jecs:locale key="${bank.bankValue }" />
						</option>
					</c:if>
				</c:forEach>
			</select>
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdOutwardBank.accountName" />
			<input name="bankbook" type="text" value="${param.bankbook }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.bnum" />
			<input name="bankcard" type="text" value="${param.bankcard }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.remittanceBNum" />
			<input name="remittanceBNum" type="text"
				value="${param.remittanceBNum }" size="12" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" type="submit" class="btn btn_sele">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
		<c:if test="${param.registerStatus==1 }">
			<div class="new_searchBar">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="registerDate" id="registerDate" type="text" 
					value="${param.registerDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				<button name="search" class="btn btn_ins" onclick="registerSuccess(bdSendRecordAllotForm1)">
					<jecs:locale key="bdSendRegister.success"/>
				</button>
			</div>
			<div class="new_searchBar">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:locale key="bdSendRecord.sendLateCause" />:<jecs:list
				name="sendLateCauseForSelect" listCode="bdsendrecord.sendlateremark"
				value="" defaultValue="0" showBlankLine="true" />
			</div>
			<div class="new_searchBar">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_ins" onclick="reissueSubmit(bdSendRecordAllotForm1)">
					<jecs:locale key="function.menu.reissueSubmit"/>
				</button>
	
				<button name="search" class="btn btn_ins" onclick="registerFail(bdSendRecordAllotForm1)">
					<jecs:locale key="function.menu.registerFail"/>
				</button>
			</div>
			<div class="new_searchBar">
				
				<jecs:title key="bdSendRecord.sendRemark" /><textarea
					name="sendRemark" rows="1" cols="20"
					onclick="javascript:this.rows=1;"></textarea>
			</div>
			<div class="new_searchBar">
				<jecs:title key="bdSendRecord.sendLateRemark" /><textarea
					name="sendLateRemark" rows="1" cols="20"
					onclick="javascript:this.rows=1;"></textarea>
			</div>
		</c:if>
		
		
		
		

		
		
		
		


		




		<%--<table width="100%" border="0">
	<tr>
		<th>	
		</th>
		
		<th>
		</th>
		
		<th>
		</th>

		<th>
		</th>
				<th>
		</th>
		
		<th>
		</th>
		
		<th>
		</th>

		<th>
<jecs:locale key="operation.button.search"/>
		</th>	
		<th>&nbsp;</th>
	</tr>
	<tr>

		<td>	
		</td>	
		<td>		
		</td>
		<td>		
		</td>
		<td>		
		</td>
		<td>	
		</td>
		<td>	
		</td>
		<td>		
		</td>
		<td>	
		</td>
		<td width="100%">&nbsp;</td>
	</tr>
</table>--%>
	</div>


	<input type="hidden" id="strAction" name="strAction"
		value="bdSendRegister" />
	<input type="hidden" name="strRegisterSuccessCodes" value="" />



	<%--<table class='grid_table'>
	<tr class='grid_span'>
		<td colspan='10'>	


		</td>
</tr>
</table>--%>

</form>


<c:set var="footTotalVar">
	<tr id="aaa">
		<td id="frontTd" align="right" class="footer" colspan="8">
			<jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal" />
		</td>
		<td class="footer" align="right">
			<b> <fmt:formatNumber value="${totalRemittanceMoney }"
					pattern="###,###,##0.00"></fmt:formatNumber> </b>
		</td>
	</tr>
</c:set>
<c:if test="${param.registerStatus=='2'}">
	<c:set var="footTotalVar">
		<tr id="aaa">
			<td id="frontTd" align="right" class="footer" colspan="8">
				<jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal" />
			</td>
			<td class="footer" align="right">
				<b> <fmt:formatNumber value="${totalRemittanceMoney }"
						pattern="###,###,##0.00"></fmt:formatNumber> </b>
			</td>
			<td align="right" class="footer"></td>
		</tr>
	</c:set>
</c:if>


<form action="bdSendRegister.html" method="get"
	name="bdSendRegisterForm" id="bdSendRegisterForm">
	<ec:table tableId="bdSendRegisterListTable" items="bdSendRegisterList"
		var="bdSendRegister" autoIncludeParameters="true"
		retrieveRowsCallback="limit"
		action="${pageContext.request.contextPath}/bdSendRegister.html"
		width="100%" form="bdSendRegisterForm" showPagination="false"
		sortable="true" imagePath="./images/extremetable/*.gif"
		footer="${footTotalVar }">
		<ec:exportXls fileName="bdSendStatistic.xls" />

		<ec:row highlightRow="false">
			<c:if test="${param.registerStatus==1}">
				<ec:column property="1"
					title="<input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchAll();check_amount();>"
					sortable="false" styleClass="centerColumn">
					<input type="checkbox" name="selectedId" id="selectedId"
						value="${bdSendRegister.id}" class="checkbox"
						onclick="check_amount();" />
					<script>
						bdsendmoney['${bdSendRegister.id }'] =${bdSendRegister.remittanceMoney };
					</script>
				</ec:column>
			</c:if>

			<ec:column property="wweek" title="bdBounsDeduct.wweek">
				<jecs:weekFormat week="${bdSendRegister.wweek }" weekType="w" />
			</ec:column>
			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
			<ec:column property="name" title="bdCalculatingSubDetail.name" />
			<ec:column property="remittanceBNum"
				title="bdSendRecord.remittanceBNum" />

			<ec:column property="jmiMember.bank" title="bdSendRecord.openBank" />

			<ec:column property="jmiMember.bankaddress"
				title="bdSendRemittanceReport.openCityCH" />
			<ec:column property="jmiMember.bankbook"
				title="bdOutwardBank.accountName" />

			<ec:column property="jmiMember.bankcard" title="bdSendRecord.bnum" />

			<ec:column property="remittanceMoney"
				title="bdSendRecord.remittanceMoney" styleClass="numberColumn">
				<fmt:formatNumber value="${bdSendRegister.remittanceMoney}"
					type="number" pattern="###,###,##0.00" />

			</ec:column>

			<c:if test="${param.registerStatus==2}">
				<ec:column property="sendDate" title="bdSendRecord.sendDate" />
			</c:if>

		</ec:row>

	</ec:table>

</form>
<c:if test="${param.registerStatus=='1' }">

	<div id='moenyBar'
		style='position: absolute; z-index: 100; width: 100%;'>
		<table width=100% height=34 border=0 cellpadding=0 cellspacing=0>
			<tr>
				<td align=center>
					<table width=90% height=34 border=0 cellpadding=0 cellspacing=0
						style='border: 1px solid #0000FF; background: #FFFFCC;'>
						<tr>
							<td valign=middle align=center>

								<font color=#0000FF style='font-size: 16pt;'><jecs:title
										key="busi.order.amount" /><b><span><font
											color=#0000FF style='font-size: 16pt;' id=nAmount>0</font>
									</span>
								</b>
								</font>




							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<script> 
function moveToolBar(){
	moenyBar.style.left = document.documentElement.scrollLeft + 0;
	moenyBar.style.top = document.documentElement.clientHeight + document.documentElement.scrollTop - 34;   
}
moveToolBar();
setInterval ('moveToolBar()',30);
</script>

</c:if>







<c:if test="${empty param.registerStatus}">
	<script type="text/javascript">
	document.getElementById("frontTd").setAttribute("colSpan",8);
	</script>
</c:if>
<c:if test="${param.registerStatus=='1'}">
	<script type="text/javascript">
	document.getElementById("frontTd").setAttribute("colSpan",9);
	</script>
</c:if>


<script type="text/javascript">

   function editBdSendRecord(recordId){
   		<jecs:power powerCode="editBdSendRecord">
					window.location="editBdSendRecord.html?strAction=editBdSendRecord&recordId="+recordId;
			</jecs:power>
		}

</script>
