<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwDemandList.title"/></title>
<content tag="heading"><jecs:locale key="inwDemandList.heading"/></content>
<meta name="menu" content="InwDemandMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/inwDemandManager.js'/>" ></script>

<%-- <c:set var="buttons">
		<jecs:power powerCode="addInwDemand">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editInwDemand.html"/>?strAction=addInwDemand'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
 --%>

<form name="inwDemandListTableForm" id="inwDemandListTableForm" action="<c:url value='/inwDemands.html'/>" >

<input type="hidden" name="strAction" value="${strAction}"/>

<div class="searchBar">&nbsp;&nbsp;
	 
		<jecs:title  key="pd.checkstatus"/>
		<jecs:list listCode="inwdemand.verify" name="verify" id="verify" showBlankLine="true" value="${param.verify}" defaultValue=""/>	
		
	  	<jecs:locale  key="amAnnounce.subject"/>:<input type="text" name="name" value="${param.name}" size="10"/>
	  	<jecs:locale  key="amAnnounce.content"/>:<input type="text" name="summary" value="${param.summary}" size="12"/>	
		
			<jecs:title  key="busi.message.issuetime"/>
			<input id="postTime" name="postTime" readonly value="${param.postTime }" size="10"/>
		    <img src="./images/calendar/calendar7.gif" id="img_startSearchTime"  style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		        	<script type="text/javascript">        	 
					Calendar.setup({
					inputField     :    "postTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startSearchTime", 
					singleClick    :    true
					});
				</script>
				
			<input id="postTimeEnd" name="postTimeEnd" readonly value="${param.postTimeEnd }" size="10"/>
		    <img src="./images/calendar/calendar7.gif" id="img_endSearchTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		        	<script type="text/javascript">        	 
					Calendar.setup({
						
					inputField     :    "postTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endSearchTime", 
					singleClick    :    true
					}); 
				</script> 		
			<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>" /> &nbsp;&nbsp;&nbsp;&nbsp;
		

		<jecs:power powerCode="addInwDemand">
			    <input type="button" class="button" style="margin-right: 5px"  onclick="location.href='<c:url value="/editInwDemand.html"/>?strAction=addInwDemand'"
			        value="<jecs:locale key="member.new.num"/>"/>
			    &nbsp;&nbsp;
			    <input type="button" class="button" style="margin-right: 5px"  onclick="location.href='<c:url value="/editInwDemand.html"/>?strAction=addInwDemandSystem'"
			        value="<jecs:locale key="inwDemand.addSystemDemand"/>"/>  
		</jecs:power>
		

<c:if test="${strAction =='checkDemands'}">
	<input class="button"  type='button' name='cmd' value='<jecs:locale  key="operation.button.delete"/>' onclick="delInwDemand()">
	<input class="button"  type='button' name='cmd' value='<jecs:locale  key="demand.checkDemands"/>' onclick="checkInwDemand();">
	
</c:if>	

</div>



<ec:table 
	tableId="inwDemandListTable"
	items="inwDemandList"
	var="inwDemand"
	action="${pageContext.request.contextPath}/inwDemands.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				
				<c:if test="${strAction =='checkDemands'}">
				<ec:column alias="chkSel" headerCell="selectAll" style="width:5px;">  
					      <input type="checkbox" class="checkbox" name="chkSel" value="${inwDemand.id}" >
					</ec:column>
				</c:if>
				
					<ec:column property="post_user_code" title="inwDemand.postUserCode" />
					<ec:column property="post_time" title="inwDemand.postTime" />
    			<ec:column property="name" title="inwDemand.name" />
    			<ec:column property="summary" title="inwDemand.summary" />
    			<%-- <ec:column property="detailExplanation" title="inwDemand.detailExplanation" /> --%>
    			<%-- <ec:column property="demandImage" title="inwDemand.demandImage" /> --%>
    			<ec:column property="reviewed_user_code" title="inwDemand.reviewedUserCode" />
    			<ec:column property="reviewed_time" title="pd.checkTime" />
    			<ec:column property="verify" title="inwDemand.verify" >
		    				<c:if test="${inwDemand.verify=='N'}"><font color="red"></c:if>
		    				<jecs:code listCode="inwdemand.verify" value="${inwDemand.verify}"/>
		    				<c:if test="${amAnnounce.status=='Y'}"></font></c:if>
    			</ec:column>
    			<%-- <ec:column property="other" title="inwDemand.other" /> --%>
    			
    		 <ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
								
						<img title="<jecs:locale  key='function.menu.view'/>" alt="<jecs:locale  key='function.menu.view'/>" src="images/newIcons/search_16.gif" border="0" align="absmiddle"
									 style="cursor:pointer" onclick="javascript:browserInwDemand('${inwDemand.id}')"/>  
							
							<c:if test="${inwDemand.verify=='N'}">
								<img title="<jecs:locale  key='amMessage.editItem'/>" alt="<jecs:locale  key='amMessage.editItem'/>" src="images/newIcons/pencil_16.gif" border="0" align="absmiddle"
									 style="cursor:pointer" onclick="javascript:editInwDemand('${inwDemand.id}')"/>
							</c:if>
								
					</ec:column>
					
				</ec:row>

</ec:table>	 <!-- +"&editFlag="+editFlag -->
</form>

<script type="text/javascript">

   function editInwDemand(id){
   		<jecs:power powerCode="editInwDemand">
					window.location="editInwDemand.html?strAction=editInwDemand&id="+id;
			</jecs:power>
		}

   function browserInwDemand(id){
  		<jecs:power powerCode="browserInwDemand">
					window.location="editInwDemand.html?strAction=browserInwDemand&id="+id;
			</jecs:power>
		}
   
	function getSelectedInfoRows(formId,checkName)
	{
	 var temp = "";
	 for ( var i=0; i<Form.getInputs(formId,'checkbox',checkName).length; i++ )
	 {
	  var e = Form.getInputs(formId,'checkbox',checkName)[i];
	  if((!e.disabled)&&(e.checked)){
	  	temp += temp==""? e.value: "," + e.value ;
	  	}
	
	 }
	 return temp;
	}
	
	function delInwDemand(){
		var inwDemand = getSelectedInfoRows('inwDemandListTableForm','chkSel');
		if(inwDemand.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				if(confirm("<jecs:locale key="bdOutWardBank.confirm.delete"/>"))
					inwDemandManager.removeInwDemands(inwDemand,function(){window.location.reload();});
			}
	}
	
	function checkInwDemand(){
		var inwDemand = getSelectedInfoRows('inwDemandListTableForm','chkSel');
		if(inwDemand.length==0){
				alert("<jecs:locale key="sys.role.noselect"/>");
				return;
			}else{
				inwDemandManager.checkInwDemand(inwDemand,'${userName}',function(){window.location.reload();});
			}
	}
	

	
</script>
