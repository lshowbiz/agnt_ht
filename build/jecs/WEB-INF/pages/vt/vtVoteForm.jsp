<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="vtVoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="vtVoteDetail.heading"/></content>


<style>
	center
	{
		font-size:12px;
		color:red;
		font-weight:bold;
	}
	select
	{
		font-size:12px;
		color:green;
	}
</style>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<spring:bind path="vtVote.*">
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

<form:form commandName="vtVote" method="post" action="editVtVote.html" onsubmit="selectOption()" id="vtVoteForm">

<%--   
<div id="titleBar" class="searchBar">
		<c:if test="${param.strAction!='viewVtVote' }">
			<jecs:power powerCode="${param.strAction}">
					<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
			</jecs:power>
			<c:if test="${not empty vtVote.vtId}">
				<jecs:power powerCode="deleteVtVote">
						<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'VtVote')" value="<jecs:locale key="operation.button.delete"/>" />
				</jecs:power>
			</c:if>
		</c:if>
		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>


<table class='detail' width="70%">
<tbody class="window">
 
<form:hidden path="vtId"/>


	<%-- 
		<tr class="edit_tr">
			<th align="left" ><span class="edit_tr_span"><jecs:label  key="sys.company.code"/><span></th>
			<td align="left"  colspan="3">
			
				<c:forEach items="${companyList}" var="company" >
					<div class="edit_tr_div"><input type="checkbox" name="cks" value="${company.COMPANY_CODE}">${company.COMPANY_NAME}</div>
		        </c:forEach>
			</td>
	</tr>
	
	 --%>

    <tr class="edit_tr"><th>
    	<c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote' }">
        	<span class="text-font-style-tc"><jecs:label  key="customerRecord.subject"/></span>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	<jecs:label  key="customerRecord.subject"/>
        </c:if>
    </th>
    <td colspan="3">
        <!--form:errors path="subject" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote' }">
        	<form:input path="subject" id="subject" cssClass="textbox-text" cssStyle="width:600px;height:22px;"/>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	${vtVote.subject }
        </c:if>
    </td></tr>

	<%--投票方式 --%>
	<tr class="edit_tr">
		<%--投票时间区间 --%>
	       <th>
	       		<c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
	        		<span class="text-font-style-tc"><jecs:label  key="vtVote.voteTime.scope"/></span>
	       	 </c:if>
	       	 <c:if test="${param.strAction=='viewVtVote' }">
	       	 	<jecs:label  key="vtVote.voteTime.scope"/>
	       	 </c:if>
	    </th>
	    <td>
	    	
	        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
	         
		         	<form:input path="startTime" id="startTime" cssClass="textbox-text" cssStyle="width:90px;height:22px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		   			-
			   		<form:input path="endTime" id="endTime" cssClass="textbox-text" cssStyle="width:90px;height:22px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	        </c:if>
	        <c:if test="${param.strAction=='viewVtVote' }">
	        <fmt:formatDate value="${vtVote.startTime }" pattern="yyyy-MM-dd"/>   -   <fmt:formatDate value="${vtVote.endTime }" pattern="yyyy-MM-dd"/>
	        
	        </c:if>
	        
	        
	    </td>
	<th>
		<c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
        	<span class="text-font-style-tc"><jecs:label  key="vtVote.voteWay"/></span>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	<jecs:label  key="vtVote.voteWay"/>
         </c:if>
        
    </th>
    <td>
        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
        	<!-- style="width:180px;heigh:22px;" -->
         <span class="textbox"><jecs:list name="optionNum" styleClass="textbox-text" listCode="vote.way" value="${vtVote.optionNum}" defaultValue="1" /></span>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	<jecs:code listCode="vote.way" value="${vtVote.optionNum}"/>
        </c:if>
    </td>    
    </tr>

	<%--描述 --%>
    <tr class="edit_tr">
	    <th>
	    	<c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
	        	<span class="text-font-style-tc-tare"><jecs:label  key="vtVote.description"/></span>
	        </c:if>
	        <c:if test="${param.strAction=='viewVtVote' }">
	        	<jecs:label  key="vtVote.description"/>
	        </c:if>
	    </th>
	    <td colspan="3">
	        <!--form:errors path="description" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
	        	<span class="text-font-style-tc-right"><form:textarea cssClass="textarea_border" path="description" id="description"/></span>
	        </c:if>
	        <c:if test="${param.strAction=='viewVtVote' }">
	        	${vtVote.description }
	        </c:if>
	    </td>
	 </tr>

    <tr class="edit_tr"><th>
       <jecs:label  key="option.select"/></span>
    </th>
    <td colspan="2">
        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
        	 <c:forEach begin= "1"   step= "1"   end= "20" varStatus="miStatus">
  
		    <c:if test="${miStatus.count==11 && vtVoteDetailSize<=10 }">
		    	<div id="movediv" style="display:none">
		    </c:if>
		    	<c:choose>
		    		<c:when test="${miStatus.count-1<vtVoteDetailSize }">
		    			<span style="width:100px;"><jecs:label  key="option.select${miStatus.count }"/></span>
		    			<span style="margin-left:2px;"><input name="optionSelect" class="textbox-text" type="text" value="${vtVoteDetails[miStatus.count-1].content }" size="30" style="width:400px;height:22px;"/></span><br/>
		    		</c:when>
		    		<c:otherwise>
		    			<span style="width:100px;"><jecs:label  key="option.select${miStatus.count }"/></span>
		  				<span style="margin-left:2px;"><input class="textbox-text" name="optionSelect" type="text" value="" size="30" style="width:400px;height:22px;"/></span><br/>
		    		</c:otherwise>
		    	</c:choose>
		  
		
		  
		    <c:if test="${miStatus.count==20 && vtVoteDetailSize<=10}">
		    	</div>
		    </c:if>
		  </c:forEach>
		  		<br/>
				<div id="more">
		    		<a href="#" onclick="more()"><jecs:locale key="option.select.add" /></a>
		    	</div>
    	
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
       
        	 <c:forEach items="${vtVote.vtVoteDetails }" var="vtVoteDetailVar" varStatus="miStatus">
        	 	<jecs:label  key="option.select${miStatus.count }"/>
        	 	${vtVoteDetailVar.content}<br/>
        	 </c:forEach>
        </c:if>
 
       
    </td></tr>
    
    

    <tr><th>
        <jecs:label  key="link.edit.sysPower"/>
    </th>
    <td>
    
    
        <c:if test="${param.strAction=='editVtVote'|| param.strAction=='addVtVote' }">
          <table border="0" width="400">
			 <tr>
			  <td><CENTER><jecs:locale  key="vote.can.select"/></CENTER></td>
			  <td>&nbsp; </td>
			  <td><CENTER><jecs:locale  key="vote.can.selected"/></CENTER></td>
			 </tr>
			    <tr>
			      <td width="40%">
			  <select  multiple="multiple" name="left" id="left" size="12" style='width:200px;height: 220px;'
			  ondblclick="moveOption(document.getElementById('left'), document.getElementById('right'))">
			  		<c:forEach items="${alCompanys }" var="alCompanyVar">
			  			<c:if test="${sessionScope.sessionLogin.companyCode=='AA' || sessionScope.sessionLogin.companyCode==alCompanyVar.companyCode}">
				  			<c:forEach items="${ userTypes }" var="userTypeVar" >
				  						<c:set var="remove" value="true"></c:set>
				  						<c:forEach items="${vtVote.vtVotePows }" var="vtVotePowVar">
				  							<c:if test="${vtVotePowVar.companyCode==alCompanyVar.companyCode && vtVotePowVar.userType==userTypeVar.key}">
				  								<c:set var="remove" value="false"></c:set>
				  							</c:if>
				  						</c:forEach>
				  						<c:if test="${remove==true }">
				  							<option value="${alCompanyVar.companyCode }-${userTypeVar.key }">${alCompanyVar.companyName }-<jecs:locale  key="${userTypeVar.value }"/></option>
				  						</c:if>
					   				
				  			</c:forEach>
			  			</c:if>
			  		</c:forEach>
			  </select>
			   </td>
			      <td width="20%" align="center">
			  <input type="button" value=" >> " onclick="moveOption(document.getElementById('left'),document.getElementById('right'))"><br><br>
			  <input type="button" value=" << " onclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
			   </td>
			      <td width="40%">
			  <select  multiple="multiple" name="right" id="right" size="12" style='width:200px;height: 220px;' ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
			  	<c:forEach items="${vtVote.vtVotePows }" var="vtVotePowVar">
			  		<option value="${vtVotePowVar.companyCode }-${vtVotePowVar.userType }" >
			  		
				  		<c:forEach items="${alCompanys }" var="alCompanyVar">
				  			<c:if test="${alCompanyVar.companyCode==vtVotePowVar.companyCode }">
				  				${alCompanyVar.companyName }
				  			</c:if>
				  		</c:forEach>
				  		<c:forEach items="${ userTypes }" var="userTypeVar" >
					  		<c:if test="${userTypeVar.key==vtVotePowVar.userType }">
					  			-<jecs:locale  key="${userTypeVar.value }"/>
					  		</c:if>
				  		</c:forEach>
			  		
			  		
			  		</option>
			  	</c:forEach>
			  </select>
			   </td>
			    </tr>
		  </table>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	<select  multiple="multiple" disabled="disabled" name="right" id="right" size="12" style='width:200px;height: 110px; ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
			  	<c:forEach items="${vtVote.vtVotePows }" var="vtVotePowVar">
			  		<option value="${vtVotePowVar.companyCode }-${vtVotePowVar.userType }" >
			  		
				  		<c:forEach items="${alCompanys }" var="alCompanyVar">
				  			<c:if test="${alCompanyVar.companyCode==vtVotePowVar.companyCode }">
				  				${alCompanyVar.companyName }
				  			</c:if>
				  		</c:forEach>
				  		<c:forEach items="${ userTypes }" var="userTypeVar" >
					  		<c:if test="${userTypeVar.key==vtVotePowVar.userType }">
					  			-<jecs:locale  key="${userTypeVar.value }"/>
					  		</c:if>
				  		</c:forEach>
			  		</option>
			  	</c:forEach>
			  </select>
        </c:if>
		      
    </td></tr>
   
    <tr>		
	    <td class="command" colspan="4" align="center">
	    	<c:if test="${param.strAction!='viewVtVote' }">
			<jecs:power powerCode="${param.strAction}">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<c:if test="${not empty vtVote.vtId}">
				<jecs:power powerCode="deleteVtVote">
						<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'VtVote')"><jecs:locale key="operation.button.delete"/></button>
				</jecs:power>
			</c:if>
		</c:if>
		
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()"><jecs:locale key="operation.button.return"/></button>
	    </td>
    </tr>
    
  </tbody>
</table>




<%--
<table class='detail' width="100%">

<form:hidden path="vtId"/>

    <tr><th>
        <jecs:label  key="customerRecord.subject"/>
    </th>
    <td align="left">
        <!--form:errors path="subject" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote' }">
        	<form:input path="subject" id="subject" cssClass="text medium"/>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	${vtVote.subject }
        </c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="vtVote.description"/>
    </th>
    <td align="left">
        <!--form:errors path="description" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
        	<form:textarea path="description" id="description" cols="40" rows="4"/>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	${vtVote.description }
        </c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="option.select"/>
    </th>
    <td align="left">
    	
    
    
        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
        	 <c:forEach begin= "1"   step= "1"   end= "20" varStatus="miStatus">
  
		    <c:if test="${miStatus.count==11 && vtVoteDetailSize<=10 }">
		    	<div id="movediv" style="display:none">
		    </c:if>
		    	<c:choose>
		    		<c:when test="${miStatus.count-1<vtVoteDetailSize }">
		    			<jecs:label  key="option.select${miStatus.count }"/><input name="optionSelect" type="text" value="${vtVoteDetails[miStatus.count-1].content }" size="30"/><br/>
		    		</c:when>
		    		<c:otherwise>
		  				<jecs:label  key="option.select${miStatus.count }"/><input name="optionSelect" type="text" value="" size="30"/><br/>
		    		</c:otherwise>
		    	</c:choose>
		  
		
		  
		    <c:if test="${miStatus.count==20 && vtVoteDetailSize<=10}">
		    	</div>
		    </c:if>
		  </c:forEach>
		  		<br/>
				<div id="more">
		    		<a href="#" onclick="more()"><jecs:locale key="option.select.add" /></a>
		    	</div>
    	
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
       
        	 <c:forEach items="${vtVote.vtVoteDetails }" var="vtVoteDetailVar" varStatus="miStatus">
        	 	<jecs:label  key="option.select${miStatus.count }"/>
        	 	${vtVoteDetailVar.content}<br/>
        	 </c:forEach>
        </c:if>
 
       
    </td></tr>
    
    
    <tr><th>
        <jecs:label  key="vtVote.voteWay"/>
    </th>
    <td align="left">
        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
         <jecs:list name="optionNum" listCode="vote.way" value="${vtVote.optionNum}" defaultValue="1" />	
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	<jecs:code listCode="vote.way" value="${vtVote.optionNum}"/>
        </c:if>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="vtVote.voteTime.scope"/>
    </th>
    <td align="left">
    	
        <c:if test="${param.strAction=='editVtVote' || param.strAction=='addVtVote'}">
	         <form:input path="startTime" id="startTime" cssClass="text medium"/>
	        <img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
					<script type="text/javascript"> 
						Calendar.setup({
						inputField     :    "startTime", 
						ifFormat       :    "%Y-%m-%d",  
						button         :    "img_startTime", 
						singleClick    :    true
						}); 
					</script>-
	        <form:input path="endTime" id="endTime" cssClass="text medium"/>
	        <img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
					<script type="text/javascript"> 
						Calendar.setup({
						inputField     :    "endTime", 
						ifFormat       :    "%Y-%m-%d",  
						button         :    "img_endTime", 
						singleClick    :    true
						}); 
					</script>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        <fmt:formatDate value="${vtVote.startTime }" pattern="yyyy-MM-dd"/>   -   <fmt:formatDate value="${vtVote.endTime }" pattern="yyyy-MM-dd"/>
        
        </c:if>
        
        
    </td></tr>

    <tr><th>
        <jecs:label  key="link.edit.sysPower"/>
    </th>
    <td align="left">
    
    
        <c:if test="${param.strAction=='editVtVote'|| param.strAction=='addVtVote' }">
          <table border="0" width="400">
			 <tr>
			  <td><CENTER><jecs:locale  key="vote.can.select"/></CENTER></td>
			  <td>&nbsp; </td>
			  <td><CENTER><jecs:locale  key="vote.can.selected"/></CENTER></td>
			 </tr>
			    <tr>
			      <td width="40%">
			  <select  multiple="multiple" name="left" id="left" size="12" style='width:200px;'
			  ondblclick="moveOption(document.getElementById('left'), document.getElementById('right'))">
			  		<c:forEach items="${alCompanys }" var="alCompanyVar">
			  			<c:if test="${sessionScope.sessionLogin.companyCode=='AA' || sessionScope.sessionLogin.companyCode==alCompanyVar.companyCode}">
				  			<c:forEach items="${ userTypes }" var="userTypeVar" >
				  						<c:set var="remove" value="true"></c:set>
				  						<c:forEach items="${vtVote.vtVotePows }" var="vtVotePowVar">
				  							<c:if test="${vtVotePowVar.companyCode==alCompanyVar.companyCode && vtVotePowVar.userType==userTypeVar.key}">
				  								<c:set var="remove" value="false"></c:set>
				  							</c:if>
				  						</c:forEach>
				  						<c:if test="${remove==true }">
				  							<option value="${alCompanyVar.companyCode }-${userTypeVar.key }">${alCompanyVar.companyName }-<jecs:locale  key="${userTypeVar.value }"/></option>
				  						</c:if>
					   				
				  			</c:forEach>
			  			</c:if>
			  		</c:forEach>
			  </select>
			   </td>
			      <td width="20%" align="center">
			  <input type="button" value=" >> " onclick="moveOption(document.getElementById('left'),document.getElementById('right'))"><br><br>
			  <input type="button" value=" << " onclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
			   </td>
			      <td width="40%">
			  <select  multiple="multiple" name="right" id="right" size="12" style='width:200px;' ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
			  	<c:forEach items="${vtVote.vtVotePows }" var="vtVotePowVar">
			  		<option value="${vtVotePowVar.companyCode }-${vtVotePowVar.userType }" >
			  		
				  		<c:forEach items="${alCompanys }" var="alCompanyVar">
				  			<c:if test="${alCompanyVar.companyCode==vtVotePowVar.companyCode }">
				  				${alCompanyVar.companyName }
				  			</c:if>
				  		</c:forEach>
				  		<c:forEach items="${ userTypes }" var="userTypeVar" >
					  		<c:if test="${userTypeVar.key==vtVotePowVar.userType }">
					  			-<jecs:locale  key="${userTypeVar.value }"/>
					  		</c:if>
				  		</c:forEach>
			  		
			  		
			  		</option>
			  	</c:forEach>
			  </select>
			   </td>
			    </tr>
		  </table>
        </c:if>
        <c:if test="${param.strAction=='viewVtVote' }">
        	<select  multiple="multiple" disabled="disabled" name="right" id="right" size="12" style='width:200px;' ondblclick="moveOption(document.getElementById('right'), document.getElementById('left'))">
			  	<c:forEach items="${vtVote.vtVotePows }" var="vtVotePowVar">
			  		<option value="${vtVotePowVar.companyCode }-${vtVotePowVar.userType }" >
			  		
				  		<c:forEach items="${alCompanys }" var="alCompanyVar">
				  			<c:if test="${alCompanyVar.companyCode==vtVotePowVar.companyCode }">
				  				${alCompanyVar.companyName }
				  			</c:if>
				  		</c:forEach>
				  		<c:forEach items="${ userTypes }" var="userTypeVar" >
					  		<c:if test="${userTypeVar.key==vtVotePowVar.userType }">
					  			-<jecs:locale  key="${userTypeVar.value }"/>
					  		</c:if>
				  		</c:forEach>
			  		</option>
			  	</c:forEach>
			  </select>
        </c:if>
		      
    </td></tr>
    
</table>
--%>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('VtVote')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('vtVoteForm'));
    function more()
	{
		
		$('movediv').style.display = "block";
		$('more').style.display = "none";
	}
		function moveOption(obj1, obj2)
		{
			 for(var i = obj1.options.length - 1 ; i >= 0 ; i--)
			 {
				 if(obj1.options[i].selected)
				 {
					var opt = new Option(obj1.options[i].text,obj1.options[i].value);
					opt.selected = true;
					obj2.options.add(opt);
					obj1.remove(i);
				}
			 }
		}
		function selectOption()
		{
			var rights=$('right');
			 for(var i =0 ; i <rights.length ; i++)
			 {
					rights.options[i].selected = true;

			 }
		}
</script>

