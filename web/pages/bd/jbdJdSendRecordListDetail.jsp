<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdYkJiandianListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdYkJiandianListList.heading"/></content>
<meta name="menu" content="JbdYkJiandianListMenu"/>
<div class="searchBar">
 <div class="new_searchBar"> 
				<button type="button" class="btn btn_sele"  style="width:auto" onclick="history.back()">
								        <jecs:locale  key='operation.button.return'/>
				</button>
		</div>
</div>
<ec:table 
	tableId="jbdJdSendRecordListDetailTable"
	items="jbdJdSendRecordListDetail"
	var="jbdYkJiandianList"
	action="${pageContext.request.contextPath}/jbdJdSendRecords.html?strAction=jbdJdSendRecordList&userCodeDetail=${jbdYkJiandianList.userCode}&wweekDetail=${jbdYkJiandianList.wweek}"
	width="100%"
	method="post"
	retrieveRowsCallback="limit"
	rowsDisplayed="50" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
				
    			<ec:column property="wweek" title="title.date" />
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="petName" title="miMember.petName" />
    			<ec:column property="userType" title="miMember.gradeType">
    			      <jecs:code listCode="yun.usertype" value="${jbdYkJiandianList.userType}"/>
    			</ec:column>
    			<ec:column property="memberLevel" title="bdCalculatingSubDetail.cardType">
    			      <jecs:code listCode="member.level" value="${jbdYkJiandianList.memberLevel}"/>
    			</ec:column>
    			<ec:column property="ykRefMoney" title="jbdYkJiandianList.ykRefMoney" />
    			<ec:column property="reuserCode" title="jbdYkJiandianList.reuserCode" />
    			<ec:column property="nlevel" title="jbdYkJiandianList.nlevel" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

 
</script>
