<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="JbdYdRebateList.title"/></title>
<content tag="heading"><jecs:locale key="JbdYdRebateList.heading"/></content>
<meta name="menu" content="JbdYdRebateListMenu"/>

<c:set var="buttons">
		 <div class="new_searchBar"> 
				<button  onclick="query()" class="btn btn_sele"  style="width:auto" >
								        <jecs:locale  key='operation.button.search'/>
				</button>
		</div>
		
</c:set>
<form name="jbdYdRebateList" action="<c:url value='/jbdYdRebateLists.html'/>" >
<div class="searchBar">
   <div class="new_searchBar">
        <jecs:title  key="miMember.memberNo"/>
		<input name="userCode" id="userCode" value="${param.userCode}" cssClass="text medium"/>	
   </div>
   
	<div class="new_searchBar">	
		<jecs:title key="customerFollow.createTime"/>
		<input name="startCalcTime" type="text" value="${param.startCalcTime }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  size="12"/>
	</div>
			
			
	<div class="new_searchBar">	
		<jecs:title key="customerFollow.endTime"/>
		<input name="endCalcTime" type="text" value="${param.endCalcTime }" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"  size="12"/>
	</div>
   <div class="new_searchBar">
		<label><jecs:locale key="JbdYdRebateList.memberType" />:</label>
		<jecs:list listCode="membertype" name="memberType" id="memberType"
			showBlankLine="false" value="${param.memberType}"
			defaultValue="" style="width: 165px;"/>
	</div>
   	<div class="new_searchBar">
		<label><jecs:locale key="miMember.freezestatus" />:</label>
		<jecs:list listCode="mimember.freezestatus" name="freezeStatus" id="freezeStatus"
			showBlankLine="false" value="${param.freezeStatus}"
			defaultValue="" style="width: 165px;"/>
	</div>
   
<c:out value="${buttons}" escapeXml="false"/>

</div>

<ec:table 
	tableId="jbdYdRebateListTable"
	items="jbdYdRebateLists"
	var="jbdYdRebateList"
	action="${pageContext.request.contextPath}/jbdYdRebateLists.html"
	width="100%"
	showStatusBar="true"
	form="jbdYdRebateList"
	retrieveRowsCallback="limit"
	
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row highlightRow="false">
    			<ec:exportXls fileName="jbdYdRebateLists.xls"></ec:exportXls>
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			<ec:column property="miUserName" title="miMember.petName" />
    			<ec:column property="memberLevel" title="bdCalculatingSubDetail.cardType" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			      <jecs:code listCode="member.level" value="${jbdYdRebateList.memberLevel}"/>
    			</ec:column>
    			<ec:column property="calcTime" title="JbdYdRebateList.calcTime" />
    			
    			<ec:column property="rebateUserCode" title="JbdYdRebateList.rebateUserCode" />
    			
    			<ec:column property="rebateOrderNo" title="JbdYdRebateList.rebateOrderNo" />
    			
    			<ec:column property="rebateRatio" title="JbdYdRebateList.rebateRatio" />
    			
    			<ec:column property="rebateAmount" title="JbdYdRebateList.rebateAmount" styleClass="numberColumn"
				cell="number" format="###,###,##0.00" />
    			
    			<ec:column property="sendAmount" title="bdSendRecord.bonusMoney"  styleClass="numberColumn"
				cell="number" format="###,###,##0.00"   />
    			
    			<ec:column property="memberType" title="JbdYdRebateList.memberType" cell="com.joymain.jecs.util.ectable.EcTableCell" > 
    				<jecs:code listCode="membertype" value="${jbdYdRebateList.memberType}"/>
    			</ec:column>
    			
    			<ec:column property="freezeStatus" title="miMember.freezestatus" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			      <jecs:code listCode="mimember.freezestatus" value="${jbdYdRebateList.freezeStatus}"/>
    			</ec:column>
    			<ec:column property="sendStatus" title="JbdYdRebateList.sendStatus" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="jbdydrebatelist.sendstatus" value="${jbdYdRebateList.sendStatus }"/>
    			</ec:column>
    			<ec:column property="sendDate" title="bdSendRecord.sendDate"  />
    			 
				</ec:row>

</ec:table>	
</form>
<script type="text/javascript">
function query(){
	
   document.forms.jbdYdRebateList.elements['ec_eti'].value='';
   document.forms.jbdYdRebateList.submit()
}

</script>
