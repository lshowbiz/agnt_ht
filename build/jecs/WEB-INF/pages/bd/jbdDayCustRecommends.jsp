<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<script src="./scripts/validate.js"> </script> 
<script src="scripts/jquerymobile/jquery-1.7.1.min.js"></script>
<script src="scripts/jQTreeTable/jQTreeTable.js"></script>
<title><jecs:locale key="顾问推荐奖励查询"/></title>
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
			size="10" />
	</div>
	<div class="new_searchBar"> 
		<jecs:title key="common.startTime"/>
			<input name="startCreateTime" id="startCreateTime" value="${param.startCreateTime }"  onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  size="12"/>
	</div>
	<div class="new_searchBar">
		 <jecs:title key="schedule.endTime"/>
			<input name="endCreateTime" id="endCreateTime" value="${param.endCreateTime}"  onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})"  size="12"/>	
	</div>
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	<div class="new_searchBar">
		<button name="search" type="submit" class="btn btn_sele">
			<jecs:locale key="operation.button.search" />
		</button>
	</div>
	</div>
</form>


<ec:table 
	tableId="jbdDayCustRecommendListTable"
	items="jbdDayCustRecommendList"
	var="jbdDayCustRecommend"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/jbdDayCustRecommends.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
			<ec:exportXls fileName="jbdDayCustRecommend.xls"/>
				<ec:row style="text-align:center">
 			
    			<ec:column property="jmiMember.userCode" title="会员编号" style="text-align:center"/>
    			<ec:column property="userName" title="会员名称"  style="text-align:center" />
    			<ec:column property="memberLevel" title="会员级别" cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
    				<jecs:code listCode="member.level" value="${jbdDayCustRecommend.memberLevel}"/>
    			</ec:column>
    			<ec:column property="wweek" title="日期" >
    				<%-- <jecs:weekFormat week="${jbdDayCustRecommend['w_week']}" weekType="w" /> --%>
    			</ec:column>
    			<ec:column property="memberStar" title="顾问奖衔"  cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
    				<jecs:code listCode="pass.star" value="${jbdDayCustRecommend.memberStar}"/>
    			</ec:column>
				
				<ec:column property="recommendPv" title="顾问推荐奖励" styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount"  style="text-align:center"/>
				<ec:column property="sendMoney" title="日应发奖金" styleClass="numberColumn" cell="number" format="###,###,##0.00"
				calc="total" calcTitle="poOrder.amtCount" style="text-align:center"/>

				<ec:column property="comDate" title="结算时间"  style="text-align:center"/>
	
    			<ec:column property="freezeStatus" title="冻结状态" cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
    				<jecs:code listCode="mimember.freezestatus" value="${jbdDayCustRecommend.freezeStatus}" />
    			</ec:column>
    			
    			<ec:column property="status" title="发放状态"  cell="com.joymain.jecs.util.ectable.EcTableCell" style="text-align:center">
    				<jecs:code listCode="jbdjdsendrecord.status.send" value="${jbdDayCustRecommend['status']}" />
    			</ec:column>
				<ec:column property="sendDate" title="发放日期"  style="text-align:center"/>
				
			<%-- 	<ec:column property="operation" title="title.operation" sortable="false" styleClass="centerColumn">
						<img style="cursor:pointer" src="images/newIcons/search_16.gif" onclick="window.location.href='jbdDayCustRecommends.html?userCode=${jbdDayCustRecommend.user_code }&wweek=${jbdDayCustRecommend.w_week }&strAction=jbdDayCustRecommendDetail';" alt="<jecs:locale key="function.menu.view"/>"/>
				</ec:column> --%>
				<ec:column property="edit" title="title.view" sortable="false" styleClass="centerColumn" style="width:100px;">
					<a href="jbdDayCustRecommends.html?userCode=${jbdDayCustRecommend.jmiMember.userCode }&wweek=${jbdDayCustRecommend.wweek }&strAction=jbdDayCustRecommendDetail"><img border="0" src="images/newIcons/search_16.gif" alt="<jecs:locale key="function.menu.view"/>" /></a>
				</ec:column>
				</ec:row>

</ec:table>	

