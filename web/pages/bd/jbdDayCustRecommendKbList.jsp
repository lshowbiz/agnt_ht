<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script src="./scripts/validate.js"> </script> 
<script src="scripts/jquerymobile/jquery-1.7.1.min.js"></script>
<script src="scripts/jQTreeTable/jQTreeTable.js"></script>
<title><jecs:locale key="顾问推荐奖励扣补"/></title>
<content tag="heading"><jecs:locale key="bdBounsRecommendList.heading"/></content>
<meta name="menu" content="jbdDayCustRecommendKbList"/>
<%@ page pageEncoding="UTF-8"%>



<form action="" method="get" name="jbdDayCustRecommendForm1"
	id="jbdDayCustRecommendtForm1">
	<input id="strAction" name="strAction" value="${param.strAction }"
		type="hidden">
	<div class="new_searchBar">
		<jecs:title key="miMember.memberNo" />
		<input name="userCode" type="text" value="${param.userCode }" 
			size="10" />
	</div>
	<div class="new_searchBar"> 
		<jecs:title key="common.startTime"/>
			<input name="startwweek" id="startwweek" value="${param.startwweek }"  onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  size="12"/>
	</div>
	<div class="new_searchBar">
		 <jecs:title key="schedule.endTime"/>
			<input name="endwweek" id="endwweek" value="${param.endwweek}"  onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  size="12"/>	
	</div>
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	<div class="new_searchBar">
		<button name="search" type="submit" class="btn btn_sele">
			<jecs:locale key="operation.button.search" />
		</button>
		<jecs:power powerCode="addJbdDayCustRecommendKb">
			<button name="search" class="btn btn_ins" type="button"  onclick="location.href='<c:url value="/editJbdDayCustRecommendKb.html"/>?strAction=addJbdDayCustRecommendKb'" >
				<jecs:locale key="member.new.num"/>
			</button>
			</jecs:power>
	</div>
</form>


<ec:table 
	tableId="jbdDayCustRecommendKbListTable"
	items="jbdDayCustRecommendKbList"
	var="jbdDayCustRecommendKb"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jbdDayCustRecommendKbList.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
			<ec:exportXls fileName="jbdDayCustRecommend.xls"/>
				<ec:row style="text-align:center">
 			
    			<ec:column property="w_week" title="日期" />
    			<ec:column property="user_code" title="会员编号"/>
    			<ec:column property="kb_money" title="扣补款(正补,负扣)"/>
    			<ec:column property="kb_reason" title="摘要" />
    			
    			<ec:column property="status" title="状态"   cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="bdperiod.bonussend" value="${jbdDayCustRecommendKb.status }"/>
    			</ec:column>
    			<ec:column property="operation_no" title="操作人" />
    			<ec:column property="operation_date" title="操作时间" />
    			
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="location.href='<c:url value="/editJbdDayCustRecommendKb.html"/>?strAction=editJbdDayCustRecommendKb&id=${jbdDayCustRecommendKb.id}'"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
				</ec:column>
				</ec:row>

</ec:table>	

