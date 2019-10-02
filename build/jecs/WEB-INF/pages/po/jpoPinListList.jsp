<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoPinListList.title"/></title>
<content tag="heading"><jecs:locale key="jpoPinListList.heading"/></content>
<meta name="menu" content="JpoPinListMenu"/>

<%--<c:set var="buttons">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoPinList.html"/>?strAction=addJpoPinList'"
			        value="<jecs:locale key="button.add"/>"/>
</c:set>

--%>
<form name="frm" action="<c:url value='/jpoPinLists.html'/>">
	<input type="hidden" id="strAction" name="strAction" value="${param.strAction}" />
	<input type="hidden" id="selectId2" name="selectId2" value="${param.selectId2}" />
	<div class="searchBar">
		<div class="new_searchBar">   
			<jecs:label key="jpoPinList.pinCode"/>
			<input name="pinCode" id="pinCode" type="text" value="${param.pinCode}" size="12"/>
		</div>
		
		<div class="new_searchBar">
			<jecs:title key="schedule.state" />
			<jecs:list name="status" listCode="jpopinlist.status" value="${param.status}" defaultValue="" showBlankLine="true"/>	
		</div>		
		
		<div class="new_searchBar">  
			<jecs:label key="fiTransferAccount.createTime"/>
			<input name="createTimeB" id="createTimeB" type="text" 
				value="${param.createTimeB }" style="cursor: pointer;width:77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					-
				<input name="createTimeE" id="createTimeE" type="text"
				value="${param.createTimeE }" style="cursor: pointer;width:77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		
		<div class="new_searchBar"  style="width: 400px;">
			<jecs:label key="jpoPinList.day.cancle"/>
			<input name="createTime2B" id="createTime2B" type="text" 
				value="${param.createTime2B }" style="cursor: pointer;width:77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			<button name="Auditing" class="btn btn_sele" type="button" onclick="auditingPinCode();" >		
				<jecs:locale key="jpoPinList.cancle"/>
			</button>
		</div>
			
		<div class="new_searchBar">		
			<button name="search" class="btn btn_sele" type="submit" onclick="seachPinCode()">
				<jecs:locale key="operation.button.search"/>
			</button>
			<button type="button" class="btn btn_ins" onclick="location.href='<c:url value="/editJpoPinList.html"/>'">
				<jecs:locale  key='button.editAll.xls'/>
			</button>
		</div>
		
	</div>
</form>
<ec:table 
	tableId="jpoPinListListTable"
	items="jpoPinListList"
	var="jpoPinList"
	action="${pageContext.request.contextPath}/jpoPinLists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn" width="10px">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jpoPinList.id}" class="checkbox"/>
				</ec:column>
				<ec:column property="userCode" title="pd.agentormember" width="120px"/> 				
    			<ec:column property="pinCode" title="jpoPinList.pinCode" width="150px"/>
				<ec:column property="status" title="schedule.state" styleClass="centerColumn" width="100px">
					<jecs:code listCode="jpopinlist.status" value="${jpoPinList.status}" />
				</ec:column>    			    			
    			<ec:column property="createTime" title="pd.createTime" width="200px">
    				<fmt:formatDate type="time" value="${jpoPinList.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
    			</ec:column>    			
    			<ec:column property="createUserCode" title="fiPayData.createrName" width="100px"/>
    			<ec:column property="remark" title="busi.common.remark" width="250px"/>    			
    			<%-- 
    			<ec:column property="editTime" title="poCounterOrder.editTime" width="150px">
    				<fmt:formatDate type="time" value="${jpoPinList.editTime}" pattern="yyyy-MM-dd HH:mm:ss" />
    			</ec:column> 
    			<ec:column property="editUserCode" title="jpmCouponInfo.updateUserCode" width="60px"/>    			
    			<ec:column property="userCode" title="miMember.memberNo" />    			
    			<ec:column property="status" title="schedule.state" />    			   			
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpoPinList('${jpoPinList.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
				</ec:column>    			 
    			<ec:column property="useTime" title="jpoPinList.useTime" />
    			<ec:column property="version" title="jpoPinList.version" />    			
    			<ec:column property="pinType" title="jpoPinList.pinType" />    			  			
    			<ec:column property="useTime" title="jpoPinList.useTime" />
    			<ec:column property="version" title="jpoPinList.version" />    			  			
    			<ec:column property="memberOrderNo" title="jpoPinList.memberOrderNo" />
				--%>
				</ec:row>

</ec:table>	

<script type="text/javascript">

	function auditingPinCode(){
		var sure=confirm('<jecs:locale key="jpopinlist.message.sure"/>')
		var beginTime=document.getElementById("createTime2B").value;		
  	    var selectedId = document.getElementsByName("selectedId");
		var selectStr = '';
		for(var i=0;i<selectedId.length;i++){ 
			if(selectedId[i].checked){
				selectStr += selectedId[i].value+",";
			}
		}
		if(sure){
			if((beginTime!=null&&beginTime!="")&&(selectStr!=''&&selectStr!=null)){
				alert('<jecs:locale key="please.makeOne"/>');
				return false;
			}else{
				if((beginTime!=null&&beginTime!="")||(selectStr!=''&&selectStr!=null)){
					document.getElementById("strAction").value="auditingPinCode";				
					document.getElementById("selectId2").value=selectStr;
					frm.submit();
					return true;
				}else{
					alert('<jecs:locale key="jpopinlist.nocancle"/>');
					return false;
				}
			}
		}

	}

	function seachPinCode(){
		document.getElementById("strAction").value="";
	}

   function editJpoPinList(id){
   		<jecs:power powerCode="editJpoPinList">
					window.location="editJpoPinList.html?strAction=editJpoPinList&id="+id;
			</jecs:power>
	}

</script>
