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
		
			theForm.strAction.value="unAllotSelectedNote";
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
			theForm.strAction.value="allotSelectedNote";
			if(isFormPosted()){
				theForm.submit();
			}
		}
				
</script>
<title><jecs:locale key="jbdSendRecordList.title" /></title>
<content tag="heading">
<jecs:locale key="jbdSendRecordList.heading" />
</content>
<meta name="menu" content="JbdSendRecordMenu" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<form action="jbdSendNoteAllot.html" method="post"
	name="bdSendRecordForm1" id="bdSendRecordForm1">

	<input type="hidden" id="strAction" name="strAction"
		value="jbdSendNoteAllot" />
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value="" />
	<div class="searchBar">
		&nbsp;&nbsp;
		<div class="new_searchBar">
			<jecs:title key="fiBankbookTemp.status" />
			<jecs:list name="allot" listCode="bdsendrecordallot.status"
				value="${param.allot }" defaultValue="0" />
		</div>
		
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
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
			<jecs:title key="bd.app.time" />
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
		</div>
		
		<div class="new_searchBar">
		
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
		</div>
		
		<div class="new_searchBar">	
			<jecs:title key="bdSendRecord.operaterCode" />
			<input name="operaterCode" type="text" value="${param.operaterCode }"
				size="12" />
		</div>
		
		<div class="new_searchBar">	
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit" onclick="loading('<jecs:locale key="button.loading"/>');">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
		
			<c:if test="${param.allot=='0' || empty param.allot}">
				<jecs:power powerCode="allotSelectedNote">
					<div class="new_searchBar">
						<jecs:title key="bdSendRecord.remittanceBNum" />
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
						<jecs:title key="bdSendRecord.operaterCode" /><input type="text"
							name="operaterCodeInput" value="${param.operaterCodeInput }"
							size="10" />
					</div>
					<div class="new_searchBar">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_ins" onclick="allotSelected(document.bdSendRecordForm1)">
							<jecs:locale key="function.menu.allotSelected"/>
						</button>
					</div>
				</jecs:power>
	
			</c:if>
	
	
	
			<c:if test="${param.allot=='1' }">
				<jecs:power powerCode="unAllotSelectedNote">
					<div class="new_searchBar">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" type="button" class="btn btn_ins" onclick="unAllotSelected(document.bdSendRecordForm1)">
							<jecs:locale key="function.menu.unAllotSelected"/>
						</button>
					</div>
				</jecs:power>
			</c:if>


	</div>



</form>

<c:set var="footTotalVar">
	<tr id="aaa">
		<td id="frontTd" align="right" class="footer" colspan="7">
			<jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal" />
		</td>
		<td class="footer" align="right">
			<b> <fmt:formatNumber value="${sumMoney }"
					pattern="###,###,##0.00"></fmt:formatNumber> </b>
		</td>
		<td align="right" class="footer" colspan="2"></td>
	</tr>
</c:set>




<div id="loading">
	<ec:table tableId="jbdSendNoteListTable" items="jbdSendNoteList"
		var="jbdSendNote"
		action="${pageContext.request.contextPath}/jbdSendNoteAllot.html"
		width="100%" autoIncludeParameters="true" showPagination="false"
		sortable="true" imagePath="./images/extremetable/*.gif"
		footer="${footTotalVar}">
		<ec:exportXls fileName="jbdSendRecordList.xls" />
		<ec:row highlightRow="false">


			<ec:column property="1" title="alCharacterKey.select1"
				sortable="false" styleClass="centerColumn">
				<input type="checkbox" name="selectedId" id="selectedId"
					value="${jbdSendNote.id}" class="checkbox" />
			</ec:column>

			<ec:column property="createTime" title="bd.app.time" />
			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />


			<ec:column property="jmiMember.bank" title="bdSendRecord.openBank" />
			<ec:column property="jmiMember.bankaddress"
				title="bdSendRemittanceReport.openCityCH" />
			<ec:column property="jmiMember.bankbook"
				title="bdOutwardBank.accountName" />
			<ec:column property="jmiMember.bankcard" title="bdSendRecord.bnum" />


			<ec:column property="remittanceMoney"
				title="bdSendRecord.remittanceMoney" styleClass="numberColumn">
				<fmt:formatNumber value="${jbdSendNote.remittanceMoney}"
					type="number" pattern="###,###,##0.00" />
			</ec:column>

			<ec:column property="remittanceBNum"
				title="bdSendRecord.remittanceBNum" />
			<ec:column property="operaterCode" title="bdSendRecord.operaterCode" />
		</ec:row>

	</ec:table>
</div>

