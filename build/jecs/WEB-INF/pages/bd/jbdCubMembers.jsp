<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCubMemberList.title"/></title>

<content tag="heading"><jecs:locale key="jbdCubMemberList.heading"/></content>
<meta name="menu" content="JbdCubMemberMenu"/>

<style>
<!--
table.detail th {
	text-align: left !important;
}
-->
</style>

<c:set var="buttons">
		<jecs:power powerCode="addJbdCubMember">
			<button type="button" class="btn btn_sele"
			onclick="location.href='<c:url value="/editJbdCubMember.html"/>?strAction=addJbdCubMember'">
				<jecs:locale key="button.add"/>
			</button>
		</jecs:power>
</c:set>

<form action="jbdCubMembers.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
	<div class="new_searchBar">
			<%-- <jecs:label key="bdPeriod.fmonth"/>
			<input name="fyear" type="text" id="fyear" size="6" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="6"/>
			(<jecs:label key="label.example"/>201501)	 --%>
		<jecs:label key="bdPeriod.fyear"/>
		<input name="fyear" type="text" size="10" size="12"/>
	</div>
	<div class="new_searchBar">	
        <jecs:label key="miMember.memberNo"/>
		<input name="userCode" type="text" size="10" size="12"/>
	</div>	
	<div class="new_searchBar">
		<jecs:label key="jbdCub.status" />
		<jecs:list name="status" id="status" listCode="yesno" value="" defaultValue="" showBlankLine="true"/>
	</div>	
	<div class="new_searchBar" style="margin-left: 55px">
		<button name="search" class="btn btn_sele" type="submit">
			<jecs:locale key="operation.button.search"/>
		</button>
		<%-- <input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />--%>
	</div>
</div>
</form>

<table class="detail table-ct" width="100%">
	<tr>
		<th colspan="5"></th>
		<th colspan="3" class="tdsep-1">基础部门</th>
		<th colspan="3" class="tdsep-2">第二大部门</th>
		<th colspan="3" class="tdsep-3">其他部门</th>
		<th colspan="2"></th>
	</tr>
	<tr>
		<th>会员编号</th>
		<th>会员名称</th>
		<th>财年</th>
		<th>星特级别</th>
		<th>拥有部门</th>

		<th class="tdsep-4">皇冠特使</th> 
		<th>皇冠大使</th> 
		<th class="tdsep-5">4次皇冠达成者</th> 

		<th>皇冠特使</th> 
		<th>皇冠大使</th> 
		<th class="tdsep-6">4次皇冠达成者</th> 

		<th>皇冠特使</th> 
		<th>皇冠大使</th> 
		<th class="tdsep-7">4次皇冠达成者</th> 

		<th>创办人</th>
		<th>操作</th>

	</tr>

	<c:forEach items="${jbdCubMembers }" var="jbdCubMember">
	<tr >
		<td>${jbdCubMember.userCode }</td>
		<td>${jbdCubMember.userName }</td>
		<td>${jbdCubMember.fyear }</td>
		<td><jecs:code listCode="pass.star.lev" value="${jbdCubMember.passStar }"></jecs:code> </td>
		<td>${jbdCubMember.departmentNum }</td>

		<td>${jbdCubMember.fstHgtsNum }</td>
		<td>${jbdCubMember.fstHgdsNum }</td>
		<td>${jbdCubMember.fstHg4Num }</td>

		<td>${jbdCubMember.secHgtsNum }</td>
		<td>${jbdCubMember.secHgdsNum }</td>
		<td>${jbdCubMember.secHg4Num }</td>

		<td>${jbdCubMember.elsHgtsNum }</td>
		<td>${jbdCubMember.elsHgdsNum }</td>
		<td>${jbdCubMember.elsHg4Num }</td>

		<td><c:if test="${jbdCubMember.status == 1}">是</c:if>
			<c:if test="${jbdCubMember.status == 0}">否</c:if></td>
		<td>
			<a href="javascript:viewJbdCubMemberList('${jbdCubMember.userCode }','${jbdCubMember.fyear }')">
				<img src="<c:url value='images/newIcons/search_16.gif'/>" />
			</a>
			
<!--			
			查询<img src="<c:url value='images/newIcons/search_16.gif'/>" 
			onclick="javascript:viewJbdCubMemberList('${jbdCubMember.userCode }')" 
			style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> -->
		</td>
	</tr>

	</c:forEach>

</table>

<%-- <ec:table 
	tableId="jbdCubMemberListTable"
	items="jbdCubMemberList"
	var="jbdCubMember"
	action="${pageContext.request.contextPath}/jbdCubMembers.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJbdCubMember('${jbdCubMember.comp_id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="status" title="jbdCubMember.status" />
    			<ec:column property="departmentNum" title="jbdCubMember.departmentNum" />
    			<ec:column property="fstHgtsNum" title="jbdCubMember.fstHgtsNum" />
    			<ec:column property="fstHgdsNum" title="jbdCubMember.fstHgdsNum" />
    			<ec:column property="fstHg4Num" title="jbdCubMember.fstHg4Num" />
    			<ec:column property="secHgtsNum" title="jbdCubMember.secHgtsNum" />
    			<ec:column property="secHgdsNum" title="jbdCubMember.secHgdsNum" />
    			<ec:column property="secHg4Num" title="jbdCubMember.secHg4Num" />
    			<ec:column property="elsHgtsNum" title="jbdCubMember.elsHgtsNum" />
    			<ec:column property="elsHgdsNum" title="jbdCubMember.elsHgdsNum" />
    			<ec:column property="elsHg4Num" title="jbdCubMember.elsHg4Num" />
				</ec:row>

</ec:table>	 --%>

<script>

   function editJbdCubMember(comp_id){
   		<jecs:power powerCode="editJbdCubMember">
					window.location="editJbdCubMember.html?strAction=editJbdCubMember&comp_id="+comp_id;
			</jecs:power>
		}

   function viewJbdCubMemberList(userCode,fyear){
	   <jecs:power powerCode="viewJbdCubMember">
			window.location="jbdCubMembers.html?strAction=viewJbdCubMember&usd="+userCode+"&fyear="+fyear;
		</jecs:power>
   }
</script>
