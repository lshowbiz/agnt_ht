<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCalcDayKbListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdCalcDayKbListList.heading"/></content>
<meta name="menu" content="JbdCalcDayKbListMenu"/>

<%-- <c:set var="buttons">
		<jecs:power powerCode="addJbdCalcDayKbList">
			    <input type="button" class="button" style="margin-right: 5px"
			       
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set> --%>
<div class="searchBar">

<div class="new_searchBar">	
		<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode }" size="12"/>
			</div>

		<div class="new_searchBar">	
		<jecs:title key="customerFollow.createTime"/>
			<input name="startWeek" type="text" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  value="${param.startWeek }" size="12"/>
			</div>
			
			
		<div class="new_searchBar">	
		<jecs:title key="customerFollow.endTime"/>
			<input name="endWeek" type="text" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"   value="${param.endWeek }" size="12"/>
			</div>
			<div class="new_searchBar">	
			<button name="search" type="submit" class="btn btn_sele" >
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addJbdCalcDayKbList">
			<button name="search" class="btn btn_ins" type="button"  onclick="location.href='<c:url value="/editJbdCalcDayKbList.html"/>?strAction=addJbdCalcDayKbList'" >
				<jecs:locale key="member.new.num"/>
			</button>
			</jecs:power>

		</div>

</div>



<ec:table 
	tableId="jbdCalcDayKbListListTable"
	items="jbdCalcDayKbListList"
	var="jbdCalcDayKbList"
	action="${pageContext.request.contextPath}/jbdCalcDayKbLists.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" />
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="kbMoney" title="jbdMemberLinkCalcHist.deductMoney" />
    			<ec:column property="kbReason" title="bdBounsDeduct.summary" />
    			<ec:column property="status" title="fiBankbookTemp.status" >
    				<jecs:code listCode="bdperiod.bonussend" value="${jbdCalcDayKbList.status }"/>
    			</ec:column>
    			<ec:column property="operationNo" title="pdProductsMain.editUreNo" />
    			<ec:column property="operationDate" title="sysOperationLog.operateTime" />
    			
    			
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdCalcDayKbList('${jbdCalcDayKbList.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
					
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdCalcDayKbList(id){
   		<jecs:power powerCode="editJbdCalcDayKbList">
					window.location="editJbdCalcDayKbList.html?strAction=editJbdCalcDayKbList&id="+id;
			</jecs:power>
		}

</script>
