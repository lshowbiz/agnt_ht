<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdManuallyAdjustStarList.title"/></title>
<content tag="heading"><jecs:locale key="jbdManuallyAdjustStarList.heading"/></content>
<meta name="menu" content="JbdManuallyAdjustStarMenu"/>

<form action="" method="get" name="miMemberManageForm1" id="miMemberManageForm1">
<div id="titleBar">

<div class="new_searchBar">	
	<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode }" size="8"/>	
		</div>
		
		<div class="new_searchBar">	
		<button name="search" type="submit" class="btn btn_sele" >
			<jecs:locale key="operation.button.search"/>
		</button>
		<jecs:power powerCode="addJbdManuallyAdjustStar">
			    <button type="button" class="btn btn_ins" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdManuallyAdjustStar.html"/>?strAction=addJbdManuallyAdjustStar'">
			        <jecs:locale key="button.add"/>
			        </button>
		</jecs:power>
		
		<jecs:power powerCode="batchUpdateJbdManuallyAdjustStar">
			    <button type="button" class="btn btn_long" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/batchUpdateJbdManuallyAdjustStar.html"/>'"
			        >批量手工定级</button>
		</jecs:power>
		
		</div>
</div>
</form>
<ec:table 
	tableId="jbdManuallyAdjustStarListTable"
	items="jbdManuallyAdjustStarList"
	var="jbdManuallyAdjustStar"
	action="${pageContext.request.contextPath}/jbdManuallyAdjustStars.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" >
    				<jecs:weekFormat week="${jbdManuallyAdjustStar.wweek }" weekType="w" />
    			</ec:column>
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="honorStar" title="jbdMemberLinkCalcHist.honorStar" >
        <jecs:code listCode="honor.star.zero" value="${jbdManuallyAdjustStar.honorStar}"/>
    			
    			</ec:column>
    			<ec:column property="passStar" title="jbdMemberLinkCalcHist.passStar" >
        <jecs:code listCode="pass.star.zero" value="${jbdManuallyAdjustStar.passStar}"/>	
    			</ec:column>
    			<ec:column property="honorGrade" title="jbdMemberLinkCalcHist.honorGrade" >
        <jecs:code listCode="honor.grade.zero" value="${jbdManuallyAdjustStar.honorGrade}"/>	
    			</ec:column>
    			<ec:column property="passGrade" title="jbdMemberLinkCalcHist.passGrade" >
        <jecs:code listCode="pass.grade.zero" value="${jbdManuallyAdjustStar.passGrade}"/>	
    			</ec:column>
				<ec:column property="edit" title="title.operation" sortable="false" >
					<jecs:power powerCode="editJbdManuallyAdjustStar">
					<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJbdManuallyAdjustStar.html?strAction=editJbdManuallyAdjustStar&id=${jbdManuallyAdjustStar.id}'" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
					</jecs:power>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdManuallyAdjustStar(id){
   		<jecs:power powerCode="editJbdManuallyAdjustStar">
					window.location="editJbdManuallyAdjustStar.html?strAction=editJbdManuallyAdjustStar&id="+id;
			</jecs:power>
		}

</script>
