<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript">

		function allotSelected(theForm,strAction){
			theForm.strAdviceCodes.value='';
		
			if(!confirm("<jecs:locale key="message.YesOrNo"/>")){
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
			theForm.strAction.value=strAction;
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


<form action="jbdSendRecordFreeze.html" method="post"
	name="bdSendRecordForm1" id="bdSendRecordForm1">

	<input type="hidden" id="strAction" name="strAction"
		value="jbdSendRecordFreeze" />
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value="" />
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="fiBankbookTemp.status" />
			<jecs:list name="freezeStatus" listCode="mimember.freezestatus"
				value="${param.freezeStatus }" defaultValue="0" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="bdBounsDeduct.wweek" />
			<input name="wweek" type="text" value="${param.wweek }" size="8" />
		</div>
		<div class="new_searchBar">
			<jecs:label key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode }"
				size="12" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" type="submit" class="btn btn_sele" onclick="loading('<jecs:locale key="button.loading"/>');">
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="freezeSubmit0">
				<button name="search" type="button" class="btn btn_ins" onclick="allotSelected(document.bdSendRecordForm1,'freezeSubmit0')">
					<jecs:locale key="jbdSendRecord.freeze.0"/>
				</button>
			</jecs:power>
	
			<jecs:power powerCode="freezeSubmit1">
				<button name="search" type="button" class="btn btn_ins" onclick="allotSelected(document.bdSendRecordForm1,'freezeSubmit1')">
					<jecs:locale key="jbdSendRecord.freeze.1"/>
				</button>
			</jecs:power>
		</div>

	</div>



</form>



<div id="loading">
	<ec:table tableId="jbdSendRecordListTable" items="jbdSendRecordList"
		var="jbdSendRecord"
		action="${pageContext.request.contextPath}/jbdSendRecordFreeze.html"
		width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
		showPagination="false" sortable="true"
		imagePath="./images/extremetable/*.gif" footer="${footTotalVar}">
		<ec:row highlightRow="false">


			<ec:column property="1" title="alCharacterKey.select1"
				sortable="false" styleClass="centerColumn">
				<input type="checkbox" name="selectedId" id="selectedId"
					value="${jbdSendRecord.id}" class="checkbox" />
			</ec:column>

			<ec:column property="w_week" title="bdBounsDeduct.wweek">
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

			<ec:column property="freeze_status" title="miMember.freezestatus">
				<jecs:code listCode="mimember.freezestatus"
					value="${jbdSendRecord.freeze_status }" />
			</ec:column>

			<ec:column property="remittance_b_num"
				title="bdSendRecord.remittanceBNum" />
			<ec:column property="operater_code" title="bdSendRecord.operaterCode" />
		</ec:row>

	</ec:table>
</div>

