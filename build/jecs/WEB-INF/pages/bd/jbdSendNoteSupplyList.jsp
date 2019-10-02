<%@ include file="/common/taglibs.jsp"%>
<script type="text/JavaScript">
<!--
function MM_jumpMenu(selObj,restore){ //v3.0
  eval("window.location='?reissueStatus="+selObj.options[selObj.selectedIndex].value+"&strAction=jbdSendNoteSupply'");
  if (restore) selObj.selectedIndex=0;
}
//-->


function supplySubmit(theForm){

	var registerDate=document.getElementById("registerDate").value;
	if(registerDate==''){
		alert("<jecs:locale key="please.input.date"/>");
		return false;
	}
	
	var remittanceBNumSelect=document.getElementById("remittanceBNumSelect").value;
	if(remittanceBNumSelect==''){
		alert("<jecs:locale key="operation.notice.js.select.code"/>");
		return false;
	}
	
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
	theForm.strAction.value="supplySubmitNote";
				if(isFormPosted()){
				theForm.submit();
			}
}

</script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<title><jecs:locale key="bdSendSupplyList.title" />
</title>
<content tag="heading">
<jecs:locale key="bdSendSupplyList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />


<form action="jbdSendNoteSupply.html" method="post" name="bdSendSupplyForm1" id="bdSendSupplyForm1">
	<div class="searchBar"	>
		<div class="new_searchBar">
			<jecs:title key="bdSendRecord.supplyStatus" />
			<jecs:list name="reissueStatus" listCode="bdsendrecord.reissuestatus1"
				value="${param.reissueStatus }" defaultValue="2"
				onchange="MM_jumpMenu(this,0)" />
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
			<input name="bank" type="text" value="${param.bank }" size="12" />
		</div>
			<c:if test="${param.reissueStatus==3  }">
				<div class="new_searchBar">
					<jecs:title key="bdSendRecord.remittanceBNum" />
					<input name="remittanceBNum" type="text"
						value="${param.remittanceBNum }" size="12" />
				</div>
				<div class="new_searchBar">
					<jecs:title key="bdSendRecord.operaterTime" />
					<input name="startOperaterTime" id="startOperaterTime" type="text" 
					value="${param.startOperaterTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					-
					<input name="endOperaterTime" id="endOperaterTime" type="text" 
					value="${param.endOperaterTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</div>
			</c:if>
			<c:if test="${param.reissueStatus==2  }">
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
			</c:if> 
	
			<c:if test="${param.reissueStatus==2  }">
				<div class="new_searchBar">
					<select name="remittanceBNumSelect" id="remittanceBNumSelect" style="width:150px;">
						<option value="">
							<jecs:locale key="list.please.select" />
						</option>
						<c:forEach items="${bdOutwardBanks }" var="var">
							<option value="${var.bankId }">
								${var.bankCode }-${var.bankName }
							</option>
						</c:forEach>
					</select>
					<input name="registerDate" id="registerDate" type="text" 
					value="${param.registerDate }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					<button name="search" class="btn btn_sele" onclick="supplySubmit(document.bdSendSupplyForm1)">
						<jecs:locale key="bdSendSupply.supplySuccess"/>
					</button>
				</div>
				
			</c:if>
			<c:if test="${param.reissueStatus==2  }">
			<div class="new_searchBar" style="width:610px;height:45px;">
					
					<jecs:title key="bdSendRecord.sendRemark" /><textarea
						name="sendRemark" rows="1" cols="20" style="width: 496px; height: 20px;"
						onclick="javascript:this.rows=1;"></textarea>
				</div>
			</c:if>
			<div class="new_searchBar" style="margin-left:45px;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" class="btn btn_sele" type="submit" >
					<jecs:locale key="operation.button.search"/>
				</button>
			</div>

	</div>
	<input type="hidden" id="strAction" name="strAction"
		value="jbdSendNoteSupply" />
	<input type="hidden" name="strRegisterSuccessCodes" value="" />

</form>

<c:set var="footTotalVar">
	<tr>
		<td id="frontTd" align="right" class="footer" colspan="8">
			<jecs:locale key="bdSendRecord.totalRemittanceMoneyTotal" />
		</td>
		<td class="footer" align="right">
			<b> <fmt:formatNumber value="${totalRemittanceMoney }"
					pattern="###,###,##0.00"></fmt:formatNumber> </b>
		</td>
		<td align="right" class="footer" colspan="5"></td>
	</tr>
</c:set>
<form action="jbdSendNoteSupply.html" method="get"
	name="bdSendSupplyForm" id="bdSendSupplyForm">
	<ec:table tableId="jbdSendNoteListTable" items="jbdSendNoteList"
		var="jbdSendNote" autoIncludeParameters="true"
		retrieveRowsCallback="limit"
		action="${pageContext.request.contextPath}/jbdSendNoteSupply.html"
		width="100%" form="bdSendSupplyForm" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif"
		footer="${footTotalVar }">
		<ec:exportXls fileName="jbdSendNoteSupply.xls" />


		<ec:row>

	<%-- 
			<ec:column property="1"
				title=""
				sortable="false" styleClass="centerColumn" >
				<input type="checkbox" name="selectedId" id="selectedId"
					value="${jbdSendNote.id}" class="checkbox" />
			</ec:column>
	--%>		
			<ec:column property="1"
				title="<input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchAll();>"
				sortable="false" styleClass="centerColumn" viewsAllowed="html">
				<input type="checkbox" name="selectedId" id="selectedId"
					value="${jbdSendNote.id}" class="checkbox" />
			</ec:column>
			
			<ec:column property="createTime" title="bd.app.time" />
			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
			<ec:column property="jmiMember.miUserName"
				title="bdCalculatingSubDetail.name" />

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




			<ec:column property="sendLateCause"
				title="bdSendRecord.sendLateCause">
				<jecs:code listCode="bdsendrecord.sendlateremark"
					value="${jbdSendNote.sendLateCause }" />
			</ec:column>
			<ec:column property="sendLateRemark"
				title="bdSendRecord.sendLateRemark" />
			<c:if test="${param.reissueStatus==3  }">
				<ec:column property="supplyTime" title="bdSendRecord.supplyTime" />
			</c:if>
			<%--<c:if test="${param.reissueStatus==2  }">
				<ec:column property="status" title="title.operation" sortable="false" styleClass="centerColumn">
<img src="<jecs:locale key="edit_ok.icon"/>" onclick="window.location.href='editBdSendSupply.html?id=${bdSendSupply.id }';" alt="<jecs:locale key="bdSendSupply.supplySuccess"/>" style="cursor:pointer"/>
				</ec:column>
				</c:if>--%>


		</ec:row>

	</ec:table>
</form>
<c:if test="${param.reissueStatus=='3' }">
	<script type="text/javascript">
			document.getElementById("frontTd").setAttribute("colSpan",8);
	</script>
</c:if>
<form name="form1" action="<c:url value='editBdSendRecord.html'/>">
	<input type="hidden" name="strAction" id="strAction" />
	<input type="hidden" name='recordId' id='recordId' />
</form>
<script type="text/javascript">

   function editBdSendRecord(recordId){
   		<jecs:power powerCode="editBdSendRecord">
					window.location="editBdSendRecord.html?strAction=editBdSendRecord&recordId="+recordId;
			</jecs:power>
		}

</script>
