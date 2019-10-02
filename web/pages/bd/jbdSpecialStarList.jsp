<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSpecialStarList.title"/></title>
<content tag="heading"><jecs:locale key="jbdSpecialStarList.heading"/></content>
<meta name="menu" content="JbdSpecialStarMenu"/>

	<c:if test="${empty param.type}">
<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction" value="jbdSpecialStars"/>
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:title key="bdPeriod.fyear"/>
		<input name="fyear" type="text" value="${param.fyear}" size="10"/>
	</div>
	<div class="new_searchBar">
		<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
	</div>
	<div class="new_searchBar">
		<label >达成特使次数:</label>
		<input name="crownEnvoyNum" type="text" value="${param.crownEnvoyNum}" size="10"
			title="达成皇冠特使次数"/>
	</div>
	<div class="new_searchBar">	
		<label >拥有部门:</label>
		<input name="departmentNum" type="text" value="${param.departmentNum}" size="10"/>
	</div>
	<div class="new_searchBar">	
		<label >部门达成人数:</label>
		<input name="minDepartmentNum" type="text" value="${param.minDepartmentNum}" size="10"
			title="最小部门达成皇冠人数（第三部门）"/>	
	</div>
	<div class="new_searchBar" style="margin-left: 20px">
		<button type="submit" class="btn btn_sele" name="cancel">
			<jecs:locale key="operation.button.search"/>
		</button>
		<%-- 
		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
		--%>
	</div>
		
</div>

</form>

	<ec:table 
	tableId="jbdSpecialStarListTable"
	items="jbdSpecialStarList"
	var="jbdSpecialStar"
	action="${pageContext.request.contextPath}/jbdSpecialStars.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
			<ec:exportXls fileName="jbdSpecialStars.xls"/>
    			<ec:column property="f_year" title="财年" />
    			<ec:column property="user_code" title="会员编号" sortable="false"/>
    			<ec:column property="mi_user_name" title="会员名称" />
    			<ec:column property="crown_envoy_num" title="达成皇冠特使次数" />
    			<ec:column property="department_num" title="拥有部门" />
    			<ec:column property="min_department_num" title="最小部门达成皇冠人数（第三部门）" />
    			<ec:column property="pass_Star" title="合格奖衔" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="pass.star.zero" value="${jbdSpecialStar.pass_star}"/>
    			</ec:column>
    			
    			
    			<ec:column property="2" title="拥有部门" >
    			<img src="images/newIcons/search_16.gif" onclick="window.location.href='jbdSpecialStars.html?team_code=${jbdSpecialStar.user_code }&s_fyear=${jbdSpecialStar.f_year }&type=detail';" alt="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					
    			</ec:column>
    			
    			
    			
				</ec:row>

</ec:table>	
	
	</c:if>
	<c:if test="${not empty param.type}">
	
	
	<div class="searchBar">
	<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
		<jecs:locale key="operation.button.return"/>
	</button>
	</div>
	
	
	
	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdCubMemberList"
		var="jbdSpecialStar"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
    			<ec:column property="department" title="部门" />
    			<ec:column property="user_code" title="会员编号" />
    			<ec:column property="mi_user_name" title="会员名称" />
    			<ec:column property="w_month" title="财月" />
    			<ec:column property="pass_star" title="合格奖衔" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="pass.star.zero" value="${jbdSpecialStar.pass_star}"/>
    			</ec:column>
    			<ec:column property="hg_count" title="达到皇冠的次数" />
    			<ec:column property="team_code" title="星级特使编号" />
				</ec:row>

</ec:table>	
	</c:if>
