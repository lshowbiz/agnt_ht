<%@ include file="/common/taglibs.jsp"%>

<script type="text/JavaScript">
<!--
function MM_jumpMenu(selObj,restore){ //v3.0
	var aa="";
	if(selObj.options[selObj.selectedIndex].value=='0'){
		var aa="&startAllotMoney=500";
	}
  eval("window.location='?allot="+selObj.options[selObj.selectedIndex].value+"&strAction=bdSendRecordAllots"+aa+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>

<script type="text/javascript">
		function unAllotSelected(theForm){
			theForm.strAdviceCodes.value='';
			if(!confirm("<jecs:locale key="bdSendRecordAllot.confirm.unAllot"/>")){
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
		
			theForm.strAction.value="unAllotSelected";
			if(isFormPosted()){
				theForm.submit();
			}
		}
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
			if(theForm.remittanceBNumAllot.value==''){
				alert("<jecs:locale key="operation.notice.js.select.code"/>");
				return false;
			}	
			theForm.strAction.value="allotSelected";
			if(isFormPosted()){
				theForm.submit();
			}
		}
				
</script>
<title><jecs:locale key="jbdSendRecordList.title" />
</title>
<content tag="heading">
<jecs:locale key="jbdSendRecordList.heading" />
</content>
<meta name="menu" content="JbdSendRecordMenu" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<form action="jbdSendRecordAllot.html" method="post"
	name="bdSendRecordForm1" id="bdSendRecordForm1">

	<input type="hidden" id="strAction" name="strAction"
		value="bdSendRecordAllots" />
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value="" />
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.status" />
			<jecs:list name="allot" listCode="bdsendrecordallot.status"
				value="${param.allot }" defaultValue="0"
				onchange="MM_jumpMenu(this,0)" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="customerRecord.type" />
			<jecs:list name="flag" listCode="bd.send.app" value="${param.flag }"
				defaultValue="0" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="bdSendRecord.openBank" />
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
		<c:if test="${param.allot=='0' || empty param.allot}">
			<div class="new_searchBar">		
				<jecs:label key="bdSendRecord.remittanceMoneyScope" />
				<input name="startAllotMoney" type="text" style="width:75px;"
					value="${empty param.startAllotMoney ? startAllotMoney : param.startAllotMoney }"
					size="12" /> - 
				<input name="endAllotMoney" type="text"
					value="${param.endAllotMoney }" size="12" style="width:75px;"/>
			</div>
			<div class="new_searchBar">		
				<jecs:label key="bd.app.time" />
				<input name="startDate" id="startDate" type="text" 
					value="${param.startDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
				<input name="endDate" id="endDate" type="text"
					value="${param.endDate }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					
			</div>
		</c:if>
		<c:if test="${param.allot=='1' }">
			<div class="new_searchBar">
				<jecs:locale key="bdSendRecord.operaterCode" />:
			 	<input name="operaterCode" type="text"
					value="${param.operaterCode }" />
			</div>
		</c:if>
		
		<c:if test="${param.allot=='0' || empty param.allot}">
			<jecs:power powerCode="allotSelected">
				<div class="new_searchBar">
					&nbsp;&nbsp;&nbsp;<jecs:locale key="bdSendRecord.remittanceBNum" />:
			 		<select name="remittanceBNumAllot" id="remittanceBNumAllot">
						<option value="">
							<jecs:locale key="list.please.select" />
						</option>
						<c:forEach items="${bdOutwardBanks }" var="var">
	
							<option value="${var.bankId }"
								<c:if test="${param.remittanceBNumAllot==var.bankId }">selected="selected"</c:if>>
								${var.bankCode }-${var.bankName }
							</option>
						</c:forEach>
					</select>
				</div>
				<div class="new_searchBar">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<jecs:locale key="bdSendRecord.operaterCode" />:<input type="text"
						name="operaterCodeInput" value="${param.operaterCodeInput }"
						size="10" />
				</div>
			</jecs:power>
		</c:if>		
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" type="submit" class="btn btn_sele" onclick="loading('<jecs:locale key="button.loading"/>');">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button name="search" class="btn btn_ins" onclick="allotSelected(document.bdSendRecordForm1)">
				<jecs:locale key="function.menu.allotSelected"/>
			</button>
			<c:if test="${param.allot=='1' }">
				<jecs:power powerCode="unAllotSelected">
					<button name="search" class="btn btn_sele" onclick="unAllotSelected(document.bdSendRecordForm1)">
						<jecs:locale key="function.menu.unAllotSelected"/>
					</button>
				</jecs:power>
			</c:if>			
		</div>
	</div>



</form>

<c:set var="footTotalVar">
	<tr id="aaa">
		<td id="frontTd" align="right" class="footer" colspan="9">
			<jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal" />
		</td>
		<td class="footer" align="right">
			<b> <fmt:formatNumber value="${sumMoney }"
					pattern="###,###,##0.00"></fmt:formatNumber> </b>
		</td>
		<td align="right" class="footer" colspan="3"></td>
	</tr>
</c:set>




<div id="loading">
	<ec:table tableId="jbdSendRecordListTable" items="jbdSendRecordList"
		var="jbdSendRecord"
		action="${pageContext.request.contextPath}/jbdSendRecordAllot.html"
		width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
		showPagination="false" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:exportXls fileName="jbdSendRecordList.xls" />
		<ec:row highlightRow="false">


			<ec:column property="1" title="alCharacterKey.select1"
				sortable="false" styleClass="centerColumn">
				<input type="checkbox" name="selectedId" id="selectedId"
					value="${jbdSendRecord.id}" class="checkbox" />
			</ec:column>

			<ec:column property="w_week" title="bdBounsDeduct.wweek"
				cell="com.joymain.jecs.util.ectable.EcTableCell">
				<jecs:weekFormat week="${jbdSendRecord.w_week }" weekType="w" />
			</ec:column>
			<ec:column property="user_code" title="miMember.memberNo" />
			<ec:column property="name" title="bdCalculatingSubDetail.name" />
			<ec:column property="card_type" title="miMember.cardType">
				<jecs:code listCode="bd.cardtype"
					value="${jbdSendRecord.card_type }" />
			</ec:column>
			<ec:column property="bank" title="bdSendRecord.openBank" />
			<ec:column property="bankaddress" title="miMember.bcity" />
			<ec:column property="bankbook" title="miMember.bname" />
			<ec:column property="bankcard" title="miMember.bnum" />
			<ec:column property="remittance_money"
				title="bdSendRecord.remittanceMoney" />

			<ec:column property="remittance_b_num"
				title="bdSendRecord.remittanceBNum" />
			<ec:column property="operater_code" title="bdSendRecord.operaterCode" />
		</ec:row>

	</ec:table>
</div>

