<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script src="./scripts/validate.js"> </script> 
<script src="scripts/jquerymobile/jquery-1.7.1.min.js"></script>
<script src="scripts/jQTreeTable/jQTreeTable.js"></script>
<title><jecs:locale key="399店主推荐奖励查询"/></title>
<content tag="heading"><jecs:locale key="bdBounsRecommendList.heading"/></content>
<meta name="menu" content="jbdDayCustRecommendList"/>
<%@ page pageEncoding="UTF-8"%>



<form action="" method="get" name="jbdDayCustRecommendForm1"
	id="jbdDayCustRecommendtForm1">
	<input id="strAction" name="strAction" value="${param.strAction }"
		type="hidden">
	<div class="new_searchBar">
		<jecs:title key="miMember.memberNo" />
		<input name="userCode" type="text" value="${param.userCode }" 
			/>
	</div>
	<div class="new_searchBar"> 
		<jecs:title key="common.startTime"/>
			<input name="startCreateTime" id="startCreateTime" value="${param.startCreateTime }"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
	</div>
	<div class="new_searchBar">
		 <jecs:title key="schedule.endTime"/>
			<input name="endCreateTime" id="endCreateTime" value="${param.endCreateTime}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>	
	</div>
	
	<div class="new_searchBar">
		<label><jecs:locale key="miMember.memberType" />:</label>
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
	
	<div class="new_searchBar">
		<label><jecs:locale key="miMember.sendStatus" />:</label>
		<jecs:list listCode="mimember.sendstatus" name="sendStatus" id="sendStatus"
			showBlankLine="false" value="${param.sendStatus}"
			defaultValue="" style="width: 165px;"/>
	</div>
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	<div class="new_searchBar">
		<button name="search" type="submit" class="btn btn_sele">
			<jecs:locale key="operation.button.search" />
		</button>
	</div>
	</div>
</form>


<ec:table 
	tableId="jbdYd399RecommendListTable"
	items="jbdYd399RecommendList"
	var="jbdYd399Recommend"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jbdYd399RecommendLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
			<ec:exportXls fileName="jbdYd399RecommendList.xls"/>
				<ec:row style="text-align:center">
 			
    			<ec:column property="jmiMember.userCode" title="会员编号" style="text-align:center"/>
    			<ec:column property="miUserName" title="昵称"  style="text-align:center" />
    			<ec:column property="memberLevel" title="会员级别" cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
    				<jecs:code listCode="member.level" value="${jbdYd399Recommend.memberLevel}"/>
    			</ec:column>
    			<ec:column property="calcTime" title="结算时间"  style="text-align:center"/>
    			
    			<ec:column property="recommendNo" title="推荐399店主编号" style="text-align:center"/>
    			
				<ec:column property="recommendAmount" title="399店主推荐奖励" styleClass="numberColumn" cell="number" format="###,###,##0.00"
					calc="total" calcTitle="poOrder.amtCount"  style="text-align:center"/>
					
				<ec:column property="sendAmount" title="应发奖金" styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount" style="text-align:center"/>

				<ec:column property="memberType" title="团队标识" cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
    				<jecs:code listCode="membertype" value="${jbdYd399Recommend.memberType}"/>
    			</ec:column>
    			
    			<ec:column property="freezeStatus" title="冻结状态" cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
    				<jecs:code listCode="mimember.freezestatus" value="${jbdYd399Recommend.freezeStatus}" />
    			</ec:column>
    			
    			<ec:column property="sendStatus" title="发放状态"  cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
    				<jecs:code listCode="mimember.sendstatus" value="${jbdYd399Recommend['sendStatus']}" />
    			</ec:column>
				<ec:column property="sendDate" title="发放日期"  style="text-align:center"/>
				
<%-- 				<ec:column property="edit" title="title.view" sortable="false" styleClass="centerColumn" style="width:100px;">
					<a href="jbdDayCustRecommends.html?userCode=${jbdDayCustRecommend.jmiMember.userCode }&wweek=${jbdDayCustRecommend.wweek }&strAction=jbdDayCustRecommendDetail"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
				</ec:column> --%>
				
				</ec:row>

</ec:table>	

