<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmCouponInfoList.title"/></title>
<content tag="heading"><jecs:locale key="jpmCouponInfoList.heading"/></content>
<meta name="menu" content="JpmCouponInfoMenu"/>

<form name="frm" action="<c:url value='/jpmCouponInfos.html'/>">
	<div class="searchBar"> 						
		<input type="hidden" id="strAction" name="strAction"
			value="${param.strAction}" />
		<input type="hidden" id="selectControl" name="selectControl"
			value="${param.selectControl}" />
		<div class="new_searchBar">		
			<label><jecs:locale key="jpmCouponInfo.cpName" />:</label>
					<input name="cpName" id="cpName"
						value="<c:out value='${param.cpName}'/>"
						cssClass="text medium" />
		</div><%--
		<div class="new_searchBar">		
			<label><jecs:locale key="jpmCouponInfo.cpValue" />:</label>
					<input name="cpValue" id="cpValue"
						value="<c:out value='${param.cpValue}'/>"
						cssClass="text medium" />
		</div>
		<div class="new_searchBar" style="margin-left: 40px">
			<label><jecs:locale key="jpmCouponInfo.status" />:</label>
			<jecs:list listCode="couponinfo.status" name="status" id="status"
				showBlankLine="false" value="${param.status}"
				defaultValue="" style="width: 165px;"/>
		</div>
		
		--%><div class="new_searchBar">  
			<jecs:label key="jpmCouponInfo.createTime"/>
			<input name="startCreateTime" id="startCreateTime" type="text" 
				value="${param.startCreateTime }" style="cursor: pointer;width:77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					-
				<input name="endCreateTime" id="endCreateTime" type="text"
				value="${param.endCreateTime }" style="cursor: pointer;width:77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div><%--
		<div class="new_searchBar">  
			<jecs:label key="jpmCouponInfo.updateTime"/>
			<input name="startUpdateTime" id="startUpdateTime" type="text" 
				value="${param.startUpdateTime }" style="cursor: pointer;width:77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					-
				<input name="endUpdateTime" id="endUpdateTime" type="text"
				value="${param.endUpdateTime }" style="cursor: pointer;width:77px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		--%><div class="new_searchBar" style="margin-left: 100px">
			<button name="search" class="btn btn_sele" type="submit" >
				<jecs:locale key="operation.button.search"/>
			</button>
			<jecs:power powerCode="addJpmCouponInfo">
				<button type="button" class="btn btn_ins" style="width:auto"
					onclick="location.href='<c:url value="/editJpmCouponInfo.html"/>?strAction=addJpmCouponInfo'">
					<jecs:locale key="button.add"/>
				</button>
			</jecs:power>
		</div>
		<%--
		
		<div class="new_searchBar">		
			<label><jecs:locale key="jpmCouponInfo.status2" />:</label>
			<jecs:list listCode="couponinfo.status" name="status2" id="status2"
				showBlankLine="false" value="${param.status2}"
				defaultValue="" style="width: 165px;"/>
			<button type="button" class="btn btn_long" style="width:auto" onclick="batchAudit();">
				<jecs:locale  key='button.audit'/>
			</button>
		</div>
		
		--%>
	</div>
</form>
<ec:table 
	tableId="jpmCouponInfoListTable"
	items="jpmCouponInfoList"
	var="jpmCouponInfo"
	action="${pageContext.request.contextPath}/jpmCouponInfos.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row ><%--
				
				<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn">
						<input type="checkbox" name="selectedId" id="selectedId" value="${jpmCouponInfo.cpId}" class="checkbox"/>
				</ec:column>
    			--%><ec:column property="cpName" title="jpmCouponInfo.cpName" width="100px"/>
    			<ec:column property="cpValue" title="jpmCouponInfo.cpValue" width="60px"/><%--
    			<ec:column property="status" title="jpmCouponInfo.status">
    				<jecs:code listCode="couponinfo.status" value="${jpmCouponInfo.status }"/>
    			</ec:column>
    			--%>
    			<ec:column property="startTime" title="jpmCouponInfo.startTime" width="100px">
    				<fmt:formatDate type="time" value="${jpmCouponInfo.startTime}" pattern="yyyy-MM-dd" />
    			</ec:column>
    			<ec:column property="endTime" title="jpmCouponInfo.endTime" width="100px">
    				<fmt:formatDate type="time" value="${jpmCouponInfo.endTime}" pattern="yyyy-MM-dd" />
    			</ec:column>
    			<ec:column property="createTime" title="jpmCouponInfo.createTime" width="150px">
    				<fmt:formatDate type="time" value="${jpmCouponInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</ec:column>    			
    			<ec:column property="createUserCode" title="jpmCouponInfo.createUserCode" width="60px"/><%--
    			<ec:column property="updateTime" title="jpmCouponInfo.updateTime" />
    			<ec:column property="updateUserCode" title="jpmCouponInfo.updateUserCode" />
    			--%><ec:column property="remark" title="jpmCouponInfo.remark" width="200px"/><%--
    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
					<img src="<c:url value='/images/icons/editIcon.gif'/>" 
						onclick="javascript:editJpmCouponInfo('${jpmCouponInfo.cpId}')"
						style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
				</ec:column>
				--%></ec:row>
</ec:table>	


<script type="text/javascript">

   function editJpmCouponInfo(cpId){
   		<jecs:power powerCode="editJpmCouponInfo">
					window.location="editJpmCouponInfo.html?strAction=editJpmCouponInfo&cpId="+cpId;
		</jecs:power>
		}

	function batchAudit(){
   		var status2 = document.getElementById("status2");
   	    var selectedId = document.getElementsByName("selectedId");
		var selectStr = '';
		for(var i=0;i<selectedId.length;i++){ 
			if(selectedId[i].checked){
				selectStr += selectedId[i].value+",";
			}
		}  
		if(selectStr==''){
			alert("<jecs:locale key='amMessage.discuss.select'/>");//?
			return;
		} 
		//window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printSendInfo&siNo="+selectStr);	
		//window.location="editJpmProductSaleNew.html?strAction=confirmJpmProductSaleNew&uniNoStr="+selectStr+"&status2="+status2.value;
   		window.location="jpmCouponInfos.html?strAction=confirmJpmCouponInfo&cpId="+selectStr+"&status2="+status2.value;
   	}
</script>
