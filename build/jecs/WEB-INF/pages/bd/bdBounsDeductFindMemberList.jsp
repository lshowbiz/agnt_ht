<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdBounsDeductFindMemberList.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductFindMemberList.heading"/></content>
<meta name="menu" content="BdSendRecordMenu"/>


<form action="" method="get" name="bdBounsDeductFindMemberList1" id="bdBounsDeductFindMemberList1">
<div class="searchBar">
<table width="100%" border="0">
	<tr>
		<th>
	<jecs:locale key="miMember.memberNo"/>
		</th>
		
		<th>
<jecs:locale key="bdCalculatingSubDetail.name"/>
		</th>
		
		<th>
<jecs:locale key="operation.button.search"/>
		</th>

	
		<th>&nbsp;</th>
	</tr>
	<tr>

		<td>
	<input name="userCode" type="text" value="${param.userCode }" size="12"/>		
		</td>
		<td>
	<input name="userName" type="text" value="${param.userName }"/>		
		</td>
		<td>
		<input name="search" type="submit" class="button" value="<jecs:locale key="operation.button.search"/>"/>	
		</td>


		<td width="100%">&nbsp;</td>
	</tr>
</table>
</div>



</form>


<form action="" method="get" name="bdBounsDeductFindMemberList" id="bdBounsDeductFindMemberList">
<ec:table 
	tableId="bdBounsDeductFindMemberListTable"
	items="members"
	var="member"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/bdBounsDeductFindMembers.html"
	width="100%"
	form="bdBounsDeductFindMemberList"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">

				<ec:row>
				
				<ec:column property="1" title="title.operation" sortable="false" styleClass="centerColumn" >
<img src="images/newIcons/tick_16.gif" onclick="javascript:selectMember('${member.userCode }','${member.miUserName }')" alt="<jecs:locale key="button.select"/>" style="cursor:pointer"/>
				</ec:column>
				<ec:column property="userCode" title="miMember.memberNo" />
				<ec:column property="sysUser.userName" title="bdCalculatingSubDetail.name" />
				
				<ec:column property="cardType" title="bdSendRecord.cardType">
    				<jecs:code listCode="bd.cardtype" value="${member.cardType}"/>
    			</ec:column>

				</ec:row>

</ec:table>
</form>
<script type="text/javascript">

function selectMember(memberNo,userName){
			 window.opener.document.getElementById('userCode').value=memberNo;
    		window.opener.document.getElementById('userName').value=userName;
    		this.close();
			}

</script>


