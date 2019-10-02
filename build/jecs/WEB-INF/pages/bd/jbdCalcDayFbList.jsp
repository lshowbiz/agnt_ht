<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCalcDayFbList.title"/></title>
<content tag="heading"><jecs:locale key="jbdCalcDayFbList.heading"/></content>
<meta name="menu" content="JbdCalcDayFbMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdCalcDayFb">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdCalcDayFb.html"/>?strAction=addJbdCalcDayFb'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>




<form action="" method="get" name="bdSendRecordForm1" id="bdSendRecordForm1">

<div class="searchBar">
	
		<div class="new_searchBar">	
		<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode }" size="12"/>
			</div>

		<div class="new_searchBar">	
		<jecs:title key="customerFollow.createTime"/>
			<input name="startWeek" type="text" value="${param.startWeek }"  onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  size="12"/>
			</div>
			
			
		<div class="new_searchBar">	
		<jecs:title key="customerFollow.endTime"/>
			<input name="endWeek" type="text" value="${param.endWeek }" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  size="12"/>
			</div>
			
		<div class="new_searchBar">  
		<jecs:title key="jpmProductSaleTeamType.teamCode"/>
			<jecs:list name="team" listCode="membertype" value="${param.team}" defaultValue="" showBlankLine="true"/>
			</div>
			
			<div class="new_searchBar">	
			<button name="search" type="submit" class="btn btn_sele" >
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
			
</div>

</form>




<ec:table 
	tableId="jbdCalcDayFbListTable"
	items="jbdCalcDayFbList"
	var="jbdCalcDayFb"
	action="${pageContext.request.contextPath}/jbdCalcDayFbs.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="images/extremetable/*.gif">
				<ec:row >
				
				<ec:exportXls fileName="jbdCalcDayFbList.xls"></ec:exportXls>
				
    			<ec:column property="jmiMember.userCode" title="miMember.memberNo" />
    			<ec:column property="jmiMember.petName" title="miMember.petName" />
    			<ec:column property="memberLevel" title="jmiMember.newMemberLevel" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="member.level" value="${jbdCalcDayFb.memberLevel }"/>
    			</ec:column>
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" />
    			
    			<ec:column property="fbMoney" title="jbdCalcDayFb.fbMoney" />
    			<ec:column property="sendMoney" title="bdSendRecord.bonusMoney" />
    			<ec:column property="deductVolume" title="jbdCalcDayFb.deductVolume" />
    			<ec:column property="rax" title="jbdCalcDayFb.rax" />
    			 <ec:column property="deductMoney" title="jbdMemberLinkCalcHist.deductMoney" /> 
    			<ec:column property="freezeStatus" title="miMember.freezestatus" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="mimember.freezestatus" value="${jbdCalcDayFb.freezeStatus }"/>
    			</ec:column>
    			<ec:column property="status" title="customerRecord.state" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="status.send" value="${jbdCalcDayFb.status }"/>
    			</ec:column>
    			<ec:column property="sendDate" title="bdSendRecord.sendDate" />
    			
    			<ec:column property="1" title="title.view" viewsDenied="xls">
						<img src="images/newIcons/search_16.gif" onclick="editJbdCalcDayFb('${jbdCalcDayFb.jmiMember.userCode }','${jbdCalcDayFb.wweek }');" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdCalcDayFb(userCode,wweek){
   		<jecs:power powerCode="editJbdCalcDayFb">
					window.location="editJbdCalcDayFb.html?strAction=editJbdCalcDayFb&userCode="+userCode+"&wweek="+wweek;
			</jecs:power>
		}

</script>
