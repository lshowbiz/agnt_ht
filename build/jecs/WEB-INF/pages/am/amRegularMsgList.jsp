<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="amRegularMsgList.title"/></title>
<content tag="heading"><jecs:locale key="amRegularMsgList.heading"/></content>
<meta name="menu" content="AmRegularMsgMenu"/>


<form name="frm" action="<c:url value='/amRegularMsgs.html?strAction=regularMsgSelect'/>" >
<div id="titleBar">
<table width="100%" border="0">
	<tr>
		<td>
			<input type="hidden" id="strAction" name="strAction" value="${requestMap.strAction}"/>			
			
			<input type="hidden" id="totalRows" name="totalRows" value="${amRegularMsgListTable_totalRows}"/>
			
			
			<jecs:locale  key="ammessage.regularmsg"/>:
			<input name="content" id="content" value="<c:out value='${param.content}'/>" cssClass="text medium"/>
			
			
			<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
			
			<jecs:power powerCode="addAmRegularMsg">	
			<button type="button" class="btn btn_ins" style="width:auto;margin-right: 5px;"
			        onclick="location.href='<c:url value="/editAmRegularMsg.html"/>?strAction=addAmRegularMsg&returnView=amRegularMsgList'"><jecs:locale key="member.new.num"/></button>
			</jecs:power>      

		</td>
	</tr>
</table>
</div>


<ec:table 
	tableId="amRegularMsgListTable"
	items="amRegularMsgList"
	var="amRegularMsg"
	form="frm"
	action="${pageContext.request.contextPath}/amRegularMsgs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row>
	    			<ec:column property="content" title="amregularmsg.content" sortable="false"/>
	    			<ec:column property="addTime" title="amregularmsg.operation" sortable="false">
	    				
	    				<input type="button" class="button" onclick="selectAmRegularMsg('${amRegularMsg.content}');" value="<jecs:locale  key='button.select'/>"/>
	    				<jecs:power powerCode="editAmRegularMsg">
	    				<input type="button" class="button" onclick="editAmRegularMsg('${amRegularMsg.uniNo}');" value="<jecs:locale  key='button.edit'/>"/>
	    				</jecs:power>
	    				<jecs:power powerCode="deleteAmRegularMsg">
	    				<input type="button" class="button" onclick="deleteAmRegularMsg('${amRegularMsg.uniNo}');" value="<jecs:locale  key='operation.button.delete'/>"/>
	    				</jecs:power>
	    			</ec:column>
				</ec:row>

</ec:table>	
</form>
<form name="form1" action="<c:url value='editAmRegularMsg.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='uniNo' id='uniNo'/>
			<input type="hidden" name='uniNo' id='uniNo'/>
			<input type="hidden" name='uniNo' id='uniNo'/>
			<input type="hidden" name='uniNo' id='uniNo'/>
			<input type="hidden" name='uniNo' id='uniNo'/>
</form>

<script type="text/javascript">

   function editAmRegularMsg(uniNo){
		window.location="<c:url value='/editAmRegularMsg.html?strAction=editAmRegularMsg&uniNo='/>"+uniNo;
	}
	
	function selectAmRegularMsg(msg){
		//var ret = new Object();
    	//ret['msg']=msg;
    	//parent.window.returnValue=ret;
    	//parent.window.close();
    	//alert(msg);
    	//alert(opener.FCKeditorAPI);
    	
   		var oEditor = window.opener.CKEDITOR.instances.replyContent; 
        oEditor.insertText( msg );
    	window.close();
	}
	
	function deleteAmRegularMsg(uniNo){
		window.location="<c:url value='/editAmRegularMsg.html?strAction=deleteAmRegularMsg&uniNo='/>"+uniNo;
	}

</script>
