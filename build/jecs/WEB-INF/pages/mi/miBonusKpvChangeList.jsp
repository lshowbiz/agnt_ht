<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miBonusKpvChangeList.title"/></title>
<content tag="heading"><jecs:locale key="miBonusKpvChangeList.heading"/></content>
<meta name="menu" content="BdPvSubDetailMenu"/>


<form action="" method="get" name="bdPvSubDetailFrom" id="bdPvSubDetailFrom">
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo"/>	
		<input name="userCode" type="text" value="${param.userCode }" size="10"/>
	</div>	
	<div class="new_searchBar">
		<jecs:label key="bdBounsDeduct.wweek"/>	
		<input name="wweek" type="text" value="${param.wweek }" size="10"/>
	</div>
	<div class="new_searchBar">
		<button name="search" type="submit" class="btn btn_sele">
			<jecs:locale key="operation.button.search"/>
		</button>
	</div>	
</div>

</form>


<ec:table 
	tableId="miBonusKpvChangeListTable"
	items="miBonusKpvChangeList"
	var="miBonusKpvChange"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	action="${pageContext.request.contextPath}/miBonusKpvChanges.html"
	width="100%"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	
	
		<jecs:power powerCode="miBonusKpvChangesImport">
			<ec:exportXls fileName="miBonusKpvChanges.xls"/>
			</jecs:power>
	
				<ec:row>
				<ec:column property="wweek" title="bdBounsDeduct.wweek" cell="com.joymain.jecs.util.ectable.EcTableCell">
					<jecs:weekFormat week="${ miBonusKpvChange.wweek}" weekType="w" />
				</ec:column>
    			<ec:column property="userCode" title="miMember.memberNo" />
    			
    			<ec:column property="name" title="bdCalculatingSubDetail.name" viewsDenied="html" />
    			
    			<ec:column property="cardType" title="bdSendRecord.cardType" styleClass="centerColumn" sortable="false" viewsDenied="xls">
    				<jecs:code listCode="bd.cardtype" value="${miBonusKpvChange.cardType}"/> 
    			</ec:column>
    			
    			
    			<ec:column property="keepPv" title="bdBonusStatReport.company.keey.pv" styleClass="numberColumn">
 
    			</ec:column>
    			<ec:column property="keepUserCode" title="bdBonus.kpvMember" />
				<ec:column property="2" title="title.operation" sortable="false" styleClass="centerColumn" viewsDenied="xls">
					<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editMiBonusKpvChange.html?id=${miBonusKpvChange.id }'" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
				</ec:column>
				</ec:row>

</ec:table>	


<form name="form1" action="<c:url value='editBdPvSubDetail.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='id' id='id'/>
</form>

<script type="text/javascript">

   function editBdPvSubDetail(id){
   		<jecs:power powerCode="editBdPvSubDetail">
					window.location="editBdPvSubDetail.html?strAction=editBdPvSubDetail&id="+id;
			</jecs:power>
		}

</script>
