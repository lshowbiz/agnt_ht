<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx }/scripts/jquery/jquery-142min.js"> </script>

<script type="text/javascript">
	String.prototype.startWith=function(str){     
	  var reg=new RegExp("^"+str);     
	  return reg.test(this);        
	};
	 
	String.prototype.endWith=function(str){     
	  var reg=new RegExp(str+"$");     
	  return reg.test(this);        
	};
	
	function countDate(theForm){
		if(theForm.userCode.value=='') {
			alert("<jecs:locale key="miMember.memberNo.notEmpty"/>");
			return;
		}
		theForm.operateStr.value='count';
		theForm.submit();
	}
	function editState(theForm,operateStr){
		var $chk = $(":checkbox:checked");
		var $nochk = $(":checkbox").not(":checked");
		var excludeVal=[];//ææéæ©æé¤çidéå
		$chk.each(function(){
			var v = $(this).val();
			var u = $(this).attr("id");
			var tip=[];
			$nochk.each(function(){
				var n = $(this).val();
				var i = $(this).attr("id");
				if(n.startWith(v)) {
					tip.push(i);
				}
			});
			if(tip.length!=0) {
				var t = '<jecs:locale key="jmi.ref.state.log.tip" args="'+u+'" argTransFlag="true"/>';
				alert(t+"["+tip.join(",")+"]");
			}
			//excludeVal.push(v);
			excludeVal.push(u);
		});
		theForm.excludeVal.value=excludeVal.join(",");
		theForm.operateStr.value=operateStr;
		theForm.submit();
	}
</script>

<form name="updateForm" method="get" action="" id="updateForm">

	<table class="detail" width="70%">
	<tbody class="window">
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:title key="miMember.netType" /></span></th>
			<td>
					<span class="textbox">
		
					<c:if test="${count<=0 || empty count }">
			
         <jecs:list styleClass="textbox-text" name="netType" id="netType" listCode="net.type" value="${param.netType}" defaultValue="" />		
					
					</c:if>	
				
					<c:if test="${count>0 }">
    				<jecs:code listCode="net.type" value="${param.netType }"></jecs:code>
					<input type="hidden"  id="netType" name="netType" value="${param.netType}" >
					
					</c:if>	
         		</span>
			</td>
		
			<th><span class="text-font-style-tc"><jecs:title key="miMember.memberNo" /></span></th>
			<td>
				<span class="textbox">
				<input id="userCode" name="userCode" value="${param.userCode }" class="textbox-text"
				<c:if test="${count>0 }">readonly="readonly" </c:if>>
				</span>
				
			</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:title key="jmiLink.count" /></span></th>
			<td>
				<span id="count" ><span class="textbox">${ count}</span></span>
			</td>
		</tr>
		
		<c:if test="${count>0 }">
			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><jecs:title key="busi.common.remark" /></span></th>
				<td>
					<span class="textbox">
					<input id="remark" name="remark" value="${param.remark }">
					</span>
				</td>
			</tr>
		</c:if>
		
	
	<td class="command" colspan="4" align="center">
	
	<c:if test="${count<=0 ||empty count}">
		<button type="button" name="submit1" onclick="javascript:countDate(this.form);" class="btn btn_sele">
			<jecs:locale key="button.next"/>
		</button>
	</c:if>
	<c:if test="${count>0 }">
		<button type="button" name="submit1" onclick="javascript:editState(this.form,'changeState1');" class="btn btn_sele">
			<jecs:locale key="member.status.limit1"/>
		</button>
		<button type="button" name="submit1" onclick="javascript:editState(this.form,'changeState0');" class="btn btn_sele">
			<jecs:locale key="member.status.limit0"/>
		</button>
		<button type="button" name="submit1" onclick="javascript:window.location.href='jmiRefState.html'" class="btn btn_sele">
			<jecs:locale key="operation.button.return"/>
		</button>
		<%-- 
		<input type="button" name="submit1" onclick="javascript:editState(this.form,'changeState1');" value="<jecs:locale key="member.status.limit1"/>" class="button"/>
		<input type="button" name="submit1" onclick="javascript:editState(this.form,'changeState0');" value="<jecs:locale key="member.status.limit0"/>" class="button"/>
		
		<input type="button" name="submit1" onclick="javascript:window.location.href='jmiRefState.html'" value="<jecs:locale key="operation.button.return"/>" class="button"/>
		--%>
	</c:if>
		<input type="hidden" name="operateStr" id="operateStr" value=""/>
		<input type="hidden" name="excludeVal" id="excludeVal" value=""/>
		
	</td>
	</table>
</form>

<c:if test="${not empty count && count>0}">
<div class="searchBar">
<table width="100%" border="0">
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:locale key="jmi.ref.state.log"/></span></th>
	</tr>
</table>
</div>
<ec:table 
	tableId="jmiRefStateLogTable"
	items="jmiRefStateLogs"
	var="jmiRefStateLog"
	action="${pageContext.request.contextPath}/jmiRefState.html?userCode=${param.userCode }&netType=${param.netType }"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="100" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="user_code" title="miMember.memberNo" style="text-align:center"/>
    			<ec:column property="net_type" title="miMember.netType" style="text-align:center">
    				<jecs:code listCode="net.type" value="${jmiRefStateLog.net_type }"></jecs:code>
    			</ec:column>
    			<ec:column property="operate_type" title="operate.type" style="text-align:center">
    				<c:choose>
    					<c:when test="${jmiRefStateLog.operate_type=='1' }"><jecs:locale key="member.status.limit1"/></c:when>
    					<c:when test="${jmiRefStateLog.operate_type=='0' }"><jecs:locale key="member.status.limit0"/></c:when>
    				</c:choose>
    			</ec:column>
    			<ec:column property="state" title="current.status" style="text-align:center">
    				<c:choose>
    					<c:when test="${jmiRefStateLog.state=='1' }"><jecs:locale key="member.status.limit1"/></c:when>
    					<c:when test="${jmiRefStateLog.state=='0' }"><jecs:locale key="member.status.limit0"/></c:when>
    				</c:choose>
    			</ec:column>
    			<ec:column property="create_time" title="sysOperationLog.operateTime" style="text-align:center" cell="date" format="yyyy-MM-dd HH:mm:ss"/>
    			
    			<ec:column property="1" title="jmiRefStateLog.exclude" style="text-align:center">
    				<input type="checkbox" id="${jmiRefStateLog.user_code }" value="${jmiRefStateLog.tree_index }"/>
    			</ec:column>
				</ec:row>

</ec:table>	






<ec:table 
	tableId="jmiStateLogTable"
	items="jmiStateLogs"
	var="jmiStateLog"
	action="${pageContext.request.contextPath}/jmiRefState.html?userCode=${param.userCode }&netType=${param.netType }"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="100" sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="user_code" title="miMember.memberNo" style="text-align:center"/>
    			
    			<ec:column property="operate_type" title="operate.type" style="text-align:center">
    				<c:choose>
    					<c:when test="${jmiStateLog.log_type=='1' }">会员资料维护处修改</c:when>
    					<c:when test="${jmiStateLog.log_type=='2' }">批量修改</c:when>
    				</c:choose>
    			</ec:column>
    			<ec:column property="state" title="更改后状态" style="text-align:center">
    				<c:choose>
    					<c:when test="${jmiStateLog.state=='1' }"><jecs:locale key="member.status.limit1"/></c:when>
    					<c:when test="${jmiStateLog.state=='0' }"><jecs:locale key="member.status.limit0"/></c:when>
    				</c:choose>
    			</ec:column>
    			<ec:column property="create_time" title="sysOperationLog.operateTime" style="text-align:center" cell="date" format="yyyy-MM-dd HH:mm:ss"/>
    			
				</ec:row>

</ec:table>	


</c:if>