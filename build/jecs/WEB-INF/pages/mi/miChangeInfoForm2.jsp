<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jmiBlacklistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiBlacklistDetail.heading"/></content>

<script type="text/javascript" src="./scripts/jquery/jquery-142min.js"> </script> 
<link rel="stylesheet" href="styles/tree_grid.css" type="text/css" />



<script type="text/javascript">
$(document).ready(function(){
  $('#memberlevelStButton').click(function(){
  
  
    var newMemberLevels=$("select._newMemberLevel");
    var memberlevelSt=$('#memberlevelSt');
   
   	if(memberlevelSt.val()!=''){
	    $.each(newMemberLevels, function() {
	    	$(this).val(memberlevelSt.val());
	　　});
   	}
  });
  
  
  $('#wweekStButton').click(function(){
  
  	
 	var wweeks=$("input._wweeks");
    var wweekSt=$('#wweekSt');
   	if(wweekSt.val()!='' && wweekSt.val().length==6){
	    $.each(wweeks, function() {
	    	$(this).val(wweekSt.val());
	　　});
   	}
  });
  

  $('#activeStButton').click(function(){
  
  
    var newActives=$("select._newActives");
    var activeSt=$('#activeSt');
   
   	if(activeSt.val()!=''){
	    $.each(newActives, function() {
	    	$(this).val(activeSt.val());
	　　});
   	}
  });
  
  
    $('#freezeStButton').click(function(){
  
  
    var newFreezeStatuss=$("select._newFreezeStatuss");
    var freezeSt=$('#freezeSt');
   
   	if(freezeSt.val()!=''){
	    $.each(newFreezeStatuss, function() {
	    	$(this).val(freezeSt.val());
	　　});
   	}
  });
  
  $('#stateStButton').click(function(){
  
  
    var newStates=$("select._newStates");
    var stateSt=$('#stateSt');
   
   	if(stateSt.val()!=''){
	    $.each(newStates, function() {
	    	$(this).val(stateSt.val());
	　　});
   	}
  });
  
  $('#isstoreStButton').click(function(){
  
  
    var newIsstore=$("select._newIsstore");
    var isstoreSt=$('#isstoreSt');
   
   	if(isstoreSt.val()!=''){
	    $.each(newIsstore, function() {
	    	$(this).val(isstoreSt.val());
	　　});
   	}
  });
  
  $('#remarkStButton').click(function(){
  
  	
 	var remarks=$("input._remark");
    var remarkSt=$('#remarkSt');
   	if(remarkSt.val()!=''){
	    $.each(remarks, function() {
	    	$(this).val(remarkSt.val());
	　　});
   	}
  });
  
});

</script>







<c:set var="buttons">
				<input type="button" onclick="window.location.href='miChangeInfo.html'" value="返回" class="button">
				
				
				 <input type="submit" value="提交" name="_finish" class="button"/>
				
				
</c:set>

<spring:bind path="jmiMemberList.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>


${filemsg }
<c:forEach items="${ messages }" var="curVar">
	${curVar } <br/>
</c:forEach>

		
<form:form commandName="jmiMemberList" method="post" action="miChangeInfo.html" id="miChangeInfoForm" onsubmit="if(isFormPosted()){return true;}{return false;}">

<div class="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<input type="hidden" name="modifyType" value="${empty modifyType ? param.modifyType : modifyType }"/>

<table class='detail' width="100%">


    <tr><th>
       
    </th>
    <td align="left">
    	
    	<c:if test="${param.modifyType=='modifyMemberLevel' }">
    	<jecs:power powerCode="modifyMemberLevel">
	   		 级别:
	    	<jecs:list id="memberlevelSt" name="memberlevelSt" listCode="member.level.new" value="${param.memberlevelSt }" defaultValue="" showBlankLine="true"/>	
	    	<button type="button" id="memberlevelStButton" class="button">设置级别</button>
	    	
			<jecs:locale key="bdBounsDeduct.wweek"/><jecs:label key="busi.common.ex"/>
				<input id="wweekSt" name="wweekSt" type="text" value="${param.wweekSt }" size="8"/>
	    	<button type="button" id="wweekStButton" class="button">设置期别</button>
    	</jecs:power>
    	</c:if>
    	
    	<c:if test="${param.modifyType=='modifyActive' }">
    	<jecs:power powerCode="modifyActive">
    		是否死点
    		<jecs:list id="activeSt" name="activeSt" listCode="yesno" value="${param.activeSt }" defaultValue="" showBlankLine="true"/>	
	    	<button type="button" id="activeStButton" class="button">设置死点状态</button>
	    </jecs:power>
    	</c:if>
    
    	<c:if test="${param.modifyType=='modifyFreeze' }">
    	<jecs:power powerCode="modifyFreeze">
    		是否冻结
    		<jecs:list id="freezeSt" name="freezeSt" listCode="yesno" value="${param.freezeSt }" defaultValue="" showBlankLine="true"/>	
	    	<button type="button" id="freezeStButton" class="button">设置冻结状态</button>
	    </jecs:power>
    	</c:if>
    	
    	
    	
    	<c:if test="${param.modifyType=='modifyState' }">
    	<jecs:power powerCode="modifyState">
    		是否可登陆
    		<jecs:list id="stateSt" name="stateSt" listCode="state.status" value="${param.stateSt }" defaultValue="" showBlankLine="true"/>	
	    	<button type="button" id="stateStButton" class="button">设置登陆状态</button>
	    </jecs:power>
    	</c:if>
    	
    	<c:if test="${param.modifyType=='modifyIsstore' }">
    	<jecs:power powerCode="modifyIsstore">
    		加盟店类型
    		<jecs:list id="isstoreSt" name="isstoreSt" listCode="isstore" value="${param.isstoreSt }" defaultValue="" showBlankLine="true"/>	
	    	<button type="button" id="isstoreStButton" class="button">设置加盟店类型</button>
	    </jecs:power>
    	</c:if>
    	
		
		备注:
			<input id="remarkSt" name="remarkSt" type="text" value="${param.remarkSt }" size="40"/>
    	<button type="button" id="remarkStButton" class="button">设置备注</button>
    	
    	
    	
    </td>
    	
    <tr>
    
    
    <th>
        会员列表
    </th>
    <td align="left">
      
      
      
      
      
      
      
      
      
      <div class="eXtremeTable" >
<table id="jmiMemberListTable_table"  border="0"  cellspacing="0"  cellpadding="4"  class="tableRegion"  width="100%" >
	<thead>
	<tr>
		<td class="tableHeader"  >会员编号</td>
		<td class="tableHeader"  >会员姓名</td>
		
		
    	<c:if test="${param.modifyType=='modifyMemberLevel' }">
    	<jecs:power powerCode="modifyMemberLevel">
			<td class="tableHeader"  >原级别</td>
			<td class="tableHeader"  >新级别</td>
			<td class="tableHeader"  >发生期别</td>
		</jecs:power>
		</c:if>	
    	<c:if test="${param.modifyType=='modifyActive' }">
    	<jecs:power powerCode="modifyActive">
			<td class="tableHeader"  >当前死点状态</td>
			<td class="tableHeader"  >更改死点状态</td>
		</jecs:power>
		</c:if>	
		
    	<c:if test="${param.modifyType=='modifyFreeze' }">
    	<jecs:power powerCode="modifyFreeze">
			<td class="tableHeader"  >当前冻结状态</td>
			<td class="tableHeader"  >更改冻结状态</td>
		</jecs:power>
		</c:if>	
		
		
    	<c:if test="${param.modifyType=='modifyState' }">
    	<jecs:power powerCode="modifyState">
			<td class="tableHeader"  >当前登陆状态</td>
			<td class="tableHeader"  >更改登陆状态</td>
		</jecs:power>
		</c:if>	
		
		
		<c:if test="${param.modifyType=='modifyIsstore' }">
    	<jecs:power powerCode="modifyIsstore">
			<td class="tableHeader"  >当前加盟店类型</td>
			<td class="tableHeader"  >更改加盟店类型</td>
		</jecs:power>
		</c:if>	
		
		
		<td class="tableHeader"  >备注</td>
		
	</tr>
	</thead>
	<tbody class="tableBody" >
    			
    			
    			
    			
    	<c:forEach items="${jmiMemberList}" var="jmiMember" varStatus="varStatus">
    	
    	
    	<c:if test="${varStatus.count % 2 ==1 }">
    		<tr class="odd"   >
    	</c:if>
    	<c:if test="${varStatus.count % 2 ==0 }">
    		<tr class="even" >
    	</c:if>
				<td>
					${jmiMember.userCode }
				</td>
				<td>
					${jmiMember.miUserName }
				</td>
				
				
		    	<c:if test="${param.modifyType=='modifyMemberLevel' }">
	    		<jecs:power powerCode="modifyMemberLevel">
					<td align="center">
						<jecs:code listCode="member.level.new" value="${jmiMember.memberLevel }"/>
					</td>
					<td align="center">
						<jecs:list styleClass="_newMemberLevel" name="${jmiMember.userCode }_newMemberLevel" listCode="member.level.new" value="${param[elf:append(jmiMember.userCode,'_newMemberLevel' )]}" defaultValue="" showBlankLine="true"/>	
					</td>
					<td align="center">
						<input class="_wweeks" name="${jmiMember.userCode }_wweek" type="text" value="${param[elf:append(jmiMember.userCode,'_wweek' )]}" size="10"/>
					</td>
				</jecs:power>
		    	</c:if>
		    	
		    	<c:if test="${param.modifyType=='modifyActive' }">
	    		<jecs:power powerCode="modifyActive">
					<td align="center">
						<jecs:code listCode="yesno" value="${jmiMember.active }"/>
					</td>
					<td align="center">
						<jecs:list styleClass="_newActives" name="${jmiMember.userCode }_newActive" listCode="yesno" value="${param[elf:append(jmiMember.userCode,'_newActive' )]}" defaultValue="" showBlankLine="true"/>	
					</td>
	    		</jecs:power>
		    	</c:if>
				
		    	<c:if test="${param.modifyType=='modifyFreeze' }">
	    		<jecs:power powerCode="modifyFreeze">
					<td align="center">
						<jecs:code listCode="yesno" value="${jmiMember.freezeStatus }"/>
					</td>
					<td align="center">
						<jecs:list styleClass="_newFreezeStatuss" name="${jmiMember.userCode }_newFreezeStatus" listCode="yesno" value="${param[elf:append(jmiMember.userCode,'_newFreezeStatus' )]}" defaultValue="" showBlankLine="true"/>	
					</td>
	    		</jecs:power>
		    	</c:if>
				
				
		    	<c:if test="${param.modifyType=='modifyState' }">
	    		<jecs:power powerCode="modifyState">
					<td align="center">
						<jecs:code listCode="state.status" value="${jmiMember.sysUser.state }"/>
					</td>
					<td align="center">
						<jecs:list styleClass="_newStates" name="${jmiMember.userCode }_newState" listCode="state.status" value="${param[elf:append(jmiMember.userCode,'_newStateStatus' )]}" defaultValue="" showBlankLine="true"/>	
					</td>
	    		</jecs:power>
		    	</c:if>
		    	
		    	<c:if test="${param.modifyType=='modifyIsstore' }">
	    		<jecs:power powerCode="modifyIsstore">
					<td align="center">
						<jecs:code listCode="isstore" value="${jmiMember.isstore }"/>
					</td>
					<td align="center">
						<jecs:list styleClass="_newIsstore" name="${jmiMember.userCode }_newIsstore" listCode="isstore" value="${param[elf:append(jmiMember.userCode,'_newIsstore' )]}" defaultValue="" showBlankLine="true"/>	
					</td>
	    		</jecs:power>
		    	</c:if>
		    	
				<td align="center">
					<input class="_remark" name="${jmiMember.userCode }_remark" type="text" value="${param[elf:append(jmiMember.userCode,'_remark' )]}" size="40"/>
				</td>
			</tr>
		</c:forEach>
    			
    			
    			
    			
    
				

	
	<tr style="padding: 0px;" >
		<td colspan="9" >
		<table border="0"  cellpadding="0"  cellspacing="0"  width="100%" >
			<tr>
				<td class="statusBar" >找到 ${fn:length(jmiMemberList)} 条记录</td>
				<td class="compactToolbar"  align="right" >&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>	

	</tbody>
</table>
</div>
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    </td></tr>


</table>

</form:form>

