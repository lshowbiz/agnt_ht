<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberList.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberList.heading"/></content>
<meta name="menu" content="JmiMemberMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="" method="get" name="miMemberForm" id="miMemberForm">
	<input type="hidden" id="strAction" name="strAction" value="memberSearch"/>
	<div class="searchBar">
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		</div>
		<div class="new_searchBar">
			<jecs:title key="bdCalculatingSubDetail.name"/>
			<input name="userName" type="text" value="${param.userName}" size="10"/>	
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.petName"/>
			<input name="petName" type="text" value="${param.petName}" size="10"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="miMember.papernumber"/>
			<input name="papernumber" type="text" value="${param.papernumber}" size="10"/>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="miMember.mobiletele"/>
			<input name="mobiletele" type="text" value="${param.mobiletele}" size="10"/>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="miMember.spouseIdno"/>
			<input name="spouseIdno" type="text" value="${param.spouseIdno}" size="10"/>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="jmiMember.newMemberLevel"/>
			<jecs:list name="memberLevel" listCode="member.level" value="${param.memberLevel}" defaultValue="" showBlankLine="true"/>	
        </div>
        <div class="new_searchBar">
	        <jecs:title key="miMember.gradeType"/>
			<jecs:list name="gradeType" listCode="grade.type" value="${param.gradeType}" defaultValue="" showBlankLine="true"/>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="miMember.store.type"/>
			<jecs:list name="isstore" listCode="store.type" value="${param.isstore}" defaultValue="" showBlankLine="true"/>	
        </div>
        <c:if test="${sessionScope.sessionLogin.companyCode=='TW'}">
			<div class="new_searchBar">
				<jecs:title key="miMember.shop.type"/>
	      	   	<jecs:list name="shopType" listCode="shop.type" value="${param.shopType}" defaultValue="" showBlankLine="true"/>
      	   	</div>	
		</c:if>
        <div class="new_searchBar"> 
			<jecs:title key="bdReconsumMoney.orderDateScope"/>
			<input id="checkBDate" name="checkBDate" type="text" 
				value="${param.checkBDate }" style="cursor: pointer;width:75px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				- 
			<input id="checkEDate" name="checkEDate" type="text" 
				value="${param.checkEDate }" style="cursor: pointer;width:75px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">	
			<jecs:title key="miMember.joinTimeRange"/>
	 		<input id="createBTime" name="createBTime" type="text" 
	 			value="${param.createBTime }" style="cursor: pointer;width:75px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				- 
				<input id="createETime" name="createETime" type="text" 
				value="${param.createETime }" style="cursor: pointer;width:75px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				
		</div>
	</c:if>
	
	<div class="new_searchBar" style="margin-left: 40px"> 
		<button type="submit" class="btn btn_sele" name="cancel">
			<jecs:locale key="operation.button.search"/>
		</button>
		<c:if test="${sessionScope.sessionLogin.companyCode!='AA'}">
			<jecs:power powerCode="memberCreate">
				<button type="button" class="btn btn_ins"
					onclick="location.href='<c:url value="memberCreate.html"/>'" >
					<jecs:locale key="member.new.num"/>
				</button>
			</jecs:power>
		</c:if>
		<jecs:power powerCode="batchUpdateStarRemark">
			<button name="search" class="btn btn_long" type="button"
				onclick="location.href='<c:url value="batchUpdateStarRemark.html"/>'">
				批量修改奖衔备注
			</button>
		</jecs:power>
	</div>
</div>



</form>

<ec:table 
	tableId="jmiMemberListTable"
	items="jmiMemberList"
	var="jmiMember"
	action="${pageContext.request.contextPath}/jmiMembers.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
	<%-- 	<jecs:power powerCode="membersImport">
			<ec:exportXls fileName="members.xls"/>
			</jecs:power> --%>
				<ec:row>
				
				
				
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="petName" title="miMember.petName" />
    			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
    				<ec:column property="miUserName" title="bdCalculatingSubDetail.name" />
    			</c:if>
    			<%--
    			<ec:column property="gradeType" title="miMember.gradeType" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="grade.type" value="${jmiMember.gradeType }"/>
    			</ec:column>
    			 
    			<ec:column property="cardType" title="bdSendRecord.cardType.old" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="bd.cardtype" value="${jmiMember.cardType }"/>
    			</ec:column>
    			--%>
    			<ec:column property="memberLevel" title="jmiMember.newMemberLevel" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="member.level" value="${jmiMember.memberLevel }"/>
    			</ec:column>
    			
				<c:if test="${sessionScope.sessionLogin.companyCode=='CN'}">
	    			<ec:column property="3" title="member.store" cell="com.joymain.jecs.util.ectable.EcTableCell">
	    					<jecs:code listCode="store.type" value="${jmiMember.isstore }"/>
	    			</ec:column>
				</c:if>
				<%-- 
    			<ec:column property="recommendNo" title="miMember.recommendNo" />
    			--%>
				<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    				<ec:column property="jmiRecommendRef.recommendJmiMember.mobiletele" title="miMember.recommend.mobiletele" />
    				<ec:column property="linkNo" title="miMember.linkNo" />
    				<ec:column property="jmiLinkRef.linkJmiMember.mobiletele" title="miMember.link.mobiletele" />
				</c:if>
    			<ec:column property="papernumber" title="miMember.papernumber" />
				<c:if test="${sessionScope.sessionLogin.companyCode=='PH'}">
    				<ec:column property="mobiletele" title="miMember.mobiletele" />
    				<ec:column property="email" title="miMember.email" />
				</c:if>
    			<ec:column property="createTime" title="miMember.createTime" format="yyyy-MM-dd" cell="date"/>
    			<ec:column property="1" title="title.operation" sortable="false" width="200px">
					
					<jecs:power powerCode="resetPassword2">
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=resetPassword2" onclick="return window.confirm('<jecs:locale key="member.resetSysPassword.2"/>');"><img border="0" height="18" width="18" src="images/l_2.gif" title="<jecs:locale key="button.resetPassword2"/>" /></a>
					</jecs:power>
					<jecs:power powerCode="resetPassword">
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=resetPassword" onclick="return window.confirm('<jecs:locale key="member.resetSysPassword"/>');"><img border="0" src="images/newIcons/key_16.gif" title="<jecs:locale key="button.resetPassword1"/>" /></a>
					</jecs:power>
					
					<jecs:power powerCode="miMemberDetailView">
					<img src="images/newIcons/search_16.gif" onclick="window.location.href='miMemberDetailView.html?memberNo=${jmiMember.userCode}';" title="<jecs:locale key="function.menu.view"/>" style="cursor:pointer"/>
					</jecs:power>
					<jecs:power powerCode="editMember">
					<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJmiMember.html?userCode=${jmiMember.userCode}&strAction=editMember';" title="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
					</jecs:power>
					<jecs:power powerCode="deleteMember">
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=deleteMember" onclick="return window.confirm('<jecs:locale key="amMessage.confirmdelete"/>');"><img border="0" src="images/icons/w.gif" title="<jecs:locale key="operation.button.delete"/>" /></a>
					</jecs:power>
					<jecs:power powerCode="activeMember">
					
						
						<c:if test="${jmiMember.active=='0'}">
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=activeMember" onclick="return window.confirm('<jecs:locale key="member.active.0"/>');">
						<img border="0" src="images/newIcons/user_add.gif" title="<jecs:locale key="member.acitve0"/>" />
						</c:if>	
							
						<c:if test="${jmiMember.active=='1'}">
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=activeMember" onclick="return window.confirm('<jecs:locale key="member.active.1"/>');">
						<img border="0" src="images/newIcons/user_delete.gif" title="<jecs:locale key="member.acitve1"/>" />
						</a></c:if>
					
					</jecs:power>
					<jecs:power powerCode="changeStatus">
						<c:if test='${empty jmiMember.exitDate }'>
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=changeStatus" onclick="return window.confirm('<jecs:locale key="member.changeStatus"/>');"><img border="0" src="images/newIcons/user_16.gif" title="<jecs:locale key="member.exit0"/>" /></a>
						</c:if>
						<c:if test='${not empty jmiMember.exitDate}'>
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=changeStatus" onclick="return window.confirm('<jecs:locale key="member.changeStatus"/>');"><img border="0" src="images/icons/status_off.gif" title="<jecs:locale key="member.exit1"/>" /></a>
						</c:if>
					</jecs:power>
					<jecs:power powerCode="changeFlag">
						<c:if test='${jmiMember.flag=="1" }'>
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=changeFlag" onclick="return window.confirm('<jecs:locale key="member.idno.cancel.tips"/>');"><img border="0" src="images/newIcons/warning_16.gif" title="<jecs:locale key="member.idno.cancel.tips"/>" /></a>
						</c:if>
					</jecs:power>
					<jecs:power powerCode="loginFlag">
					
						<c:if test="${jmiMember.sysUser.state=='1'}">
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=loginFlag" onclick="return window.confirm('<jecs:locale key="member.login.1"/>');">
						<img border="0" src="images/icons/status_on.gif" title="<jecs:locale key="member.status0"/>" />
						</c:if>	
							
						<c:if test="${jmiMember.sysUser.state=='0'}">
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=loginFlag" onclick="return window.confirm('<jecs:locale key="member.login.0"/>');">
						<img border="0" src="images/icons/status_off.gif" title="<jecs:locale key="sys.message.userIsRestrict"/>" />
						</a></c:if>
						
					</jecs:power>
					<jecs:power powerCode="errorPwdFlag">
						<a href="jmiMembers.html?userCode=${jmiMember.userCode}&strAction=errorPwdFlag" onclick="return window.confirm('<jecs:locale key="message.YesOrNo"/>');"><img border="0" src="images/icons/changeAdvancedPwd.gif" title="<jecs:locale key="sys.pwd.unlock"/>" /></a>
					</jecs:power>
				</ec:column>
				</ec:row>

</ec:table>	

