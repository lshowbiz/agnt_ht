<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdMemberLinkCalcHistList.title"/></title>
<content tag="heading"><jecs:locale key="jbdMemberLinkCalcHistList.heading"/></content>
<meta name="menu" content="JbdMemberLinkCalcHistMenu"/>

<script type="text/javascript">
<!--

function selectedMe(theForm){
	theForm.strAdviceCodes.value='';

	if(!confirm("<jecs:locale key="bdMessage.select.confirm"/>")){
		return false;
	}
	var elements=document.getElementsByName("selectedId");

	if(elements==undefined){
		alert("<jecs:locale key="amMessage.discuss.select"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			theForm.strAdviceCodes.value+=","+elements[i].value;
		}
	}
	if(!selectedOne){ 
		alert("<jecs:locale key="amMessage.discuss.select"/>");
		return false;
	}
	if(theForm.honorStar.value==''){
		alert("<jecs:locale key="bdMessage.select.honorStar"/>");
		return false;
	}	
	theForm.strAction.value="editJbdMemberLinkCalcHistGrade2"; 
	if(isFormPosted()){
		theForm.submit();
	}
}

//-->
</script>
<form action="jbdMemberLinkCalcHistGrade.html" method="post" name="miMemberForm" id="miMemberForm">
	<input type="hidden" name="strAction" id="strAction" value="jbdMemberLinkCalcHistGrade"/>
	<input type="hidden" name="strAdviceCodes" id="strAdviceCodes" value=""/>
<div class="searchBar">
	<div class="new_searchBar">
		<jecs:title key="jbdMemberLinkCalcHist.honorStar"/>
		<jecs:list name="honorStar" listCode="honor.star.zero" value="${jbdMemberLinkCalcHist.honorStar }" defaultValue="0" />	
	</div>
   	<div class="new_searchBar">
    	<jecs:title key="jbdMemberLinkCalcHist.qualifyStar"/>
		<jecs:list name="qualifyStar" listCode="qualify.star.zero" value="${jbdMemberLinkCalcHist.qualifyStar }" defaultValue="0" />
    </div>
	<div class="new_searchBar">
		<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>
	</div>
	<div class="new_searchBar" style="margin-left: 30px">
		<input type="checkbox" value="changeTravelPoint2012" style="width:30px"
			id="changeTravelPoint2012" name="changeTravelPoint2012" /> 
		<jecs:title  key="jbdMemberLinkCalcHist.changeTravelPoint2012"/>
    </div>
	<div class="new_searchBar" style="margin-left: 20px">
		<button type="submit" class="btn btn_sele" name="cancel">
			<jecs:locale key="operation.button.search"/>
		</button>
		<%-- 
		<button type="button" class="btn btn_long" onclick="selectAll();">
			<jecs:locale  key='button.selectAll'/>
		</button>
		<button type="button" class="btn btn_long" onclick="reSelectAll();">
			<jecs:locale  key='common.reSelectAll'/>
		</button>
		--%>
		<button type="button" class="btn btn_long" onclick="selectedMe(document.miMemberForm);">
    		<jecs:locale  key='button.editAll'/>
    	</button>
		<%-- 
		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
		<input type="button" class="button" onclick="selectAll();"
        	value="<jecs:locale  key='button.selectAll'/>"/>
        <input type="button" class="button" onclick="reSelectAll();"
        	value="<jecs:locale  key='common.reSelectAll'/>"/>
        --%>
	</div>	
	<input type="hidden" id="oldHonorStar" name="oldHonorStar" value="" />
	<input type="hidden" id="oldPassStar" name="oldPassStar" value="" />
</div>

</form>


<ec:table 
	tableId="jbdMemberLinkCalcHistListTable"
	items="jbdMemberLinkCalcHistGradeList"
	var="jbdMemberLinkCalcHist"
	action="${pageContext.request.contextPath}/jbdMemberLinkCalcHistGrade.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row highlightRow="false">
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jbdMemberLinkCalcHist.id }" class="checkbox" />
				</ec:column>
				
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" >
    				 <jecs:weekFormat week="${jbdMemberLinkCalcHist.wweek }" weekType="w" />
    			</ec:column>
   			
    			<ec:column property="userCode" title="bdNetWorkCostReport.memberCH" />
    			<ec:column property="name" title="bdCalculatingSubDetail.name" />
    			<ec:column property="petName" title="miMember.petName" />
    			<ec:column property="cardType" title="bdSendRecord.cardType" >
    				 <jecs:code listCode="bd.cardtype" value="${jbdMemberLinkCalcHist.cardType}"/>
    			</ec:column> 
    			<ec:column property="honorStar" title="jbdMemberLinkCalcHist.honorStar" >
    				<jecs:code listCode="honor.star.zero" value="${jbdMemberLinkCalcHist.honorStar}"/>
    			</ec:column>
    			<ec:column property="passStar" title="jbdMemberLinkCalcHist.passStar" >
    				<jecs:code listCode="pass.star.zero" value="${jbdMemberLinkCalcHist.passStar}"/>
    			</ec:column>
    			
    			<ec:column property="qualifyStar" title="jbdMemberLinkCalcHist.qualifyStar" >
    				<jecs:code listCode="qualify.star.zero" value="${jbdMemberLinkCalcHist.qualifyStar}"/>
    			</ec:column>
    			<ec:column property="-1" title="jbdMemberLinkCalcHist.qualifyRemark">
    				<span title="${jbdMemberLinkCalcHist.qualifyRemark}">${jbdMemberLinkCalcHist.qualifyRemark}</span>
    			</ec:column>
    			
    			<ec:column property="2" title="title.operation" sortable="false" >
    			
    			<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJbdMemberLinkCalcHistGrade.html?id=${jbdMemberLinkCalcHist.id}&strAction=editJbdMemberLinkCalcHistGrade';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">
<!--
function selectJbdMemberLinkById(id){
	
}

function reSelectAll(){
	var ids = document.getElementsByName("selectedId");
	for(var i=0;i<ids.length;i++){ 
		if(ids[i].checked==false){
			ids[i].checked=true;
		}else if(ids[i].checked==true){
			ids[i].checked=false;
		}
	} 		 
}

function selectAll(){
	var ids = document.getElementsByName("selectedId");
	for(var i=0;i<ids.length;i++){ 
		ids[i].checked=true;
	} 		 
}



//-->
</script>
<!-- -->