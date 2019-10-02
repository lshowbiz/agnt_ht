<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<title><jecs:locale key="amAnnounceDetail.title"/></title>
<content tag="heading"><jecs:locale key="amAnnounceDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<style type="text/css">
#contentArea b span,#contentArea span { font-size:12pt !important;}
</style>
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script   src='<c:url value="/ckeditor/ckeditor.js" />' ></script>
<c:set var="buttons">
		
		<jecs:power powerCode="${param.strAction}">
		  <c:choose>
		     <c:when test ="${strAction == 'editAmAnnounce'}">
		       <c:if test ="${amAnnounce.status==0 && param.editFlag!='2'}">
		     		<button type="button" class="btn btn_sele" name="save"  onclick="saveAmAnnounce()"><jecs:locale  key="${buttonKey}"/></button>
		    	</c:if>
		     </c:when>
		     <c:otherwise>
		        <c:if test ="${strAction !='browserAmAnnounce' && param.editFlag!='2' }">
			  
				<button type="button" class="btn btn_sele" name="save"  onclick="saveAmAnnounce()"><jecs:locale  key="${buttonKey}"/></button>
				</c:if>
		     </c:otherwise>
		  </c:choose>
			
		</jecs:power>
		
		<jecs:power powerCode="deleteAmAnnounce">
			<c:if test="${(strAction == 'editAmAnnounce')&&(amAnnounce.status==0) && param.editFlag!='2'}">
				<button type="submit" class="btn btn_sele" name="delete" onclick="$('strAction').value='deleteAmAnnounce';return confirmDelete(this.form,'AmAnnounce')"><jecs:locale key="operation.button.delete"/></button>
			</c:if>
			<c:if test="${strAction == 'checkAmAnnounce' && param.editFlag!='2'}">
				<button type="submit" class="btn btn_sele" name="delete" onclick="$('strAction').value='deleteAmAnnounce';return confirmDelete(this.form,'AmAnnounce')" ><jecs:locale key="operation.button.delete"/></button>
			</c:if>	
		</jecs:power>
		
			<button type="button" class="btn btn_sele" name="back"  onclick="toback();"><jecs:locale  key="operation.button.return"/></button>
		
		
		
</c:set>

<spring:bind path="amAnnounce.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="amAnnounce" method="post" action="editAmAnnounce.html" onsubmit="return validateAmAnnounce(this)" id="amAnnounceForm">
<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AmAnnounce')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />

</legend-->

<table class='detail' id="contentArea" width="70%">
	<tbody class="window" >

	<form:hidden path="aaNo"/>
	<form:hidden path="status"/>
			<c:if test="${companyReadabled}">
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label  key="sys.company.code"/></span>
    					</th>
    				<td>
        <!--form:errors path="companyCode" cssClass="fieldError"/-->

			<span class="textbox"><jecs:company name="companyCode" styleClass="textbox-text" selected="${amAnnounce.companyCode }"  prompt="" withAA="false"  /></span>	
    	</td></tr>
		</c:if>
		<c:if test="${!companyReadabled}">
			<form:hidden path="companyCode"/>
		</c:if>
		
	<c:if test="${readOnly}">
	    <tr class="edit_tr">
	    	
	    	<th>
	    			<span class="text-font-style-tc"><jecs:label  key="busi.message.issuer"/></span>
	    	</th>
	    	<td>
		   			<span class="textbox"><input type="text" class="textbox-text" value="${amAnnounce.issueUserNo}" readonly="readonly"></span>
	   		</td>
	    	<th>
	        		<span class="text-font-style-tc"><jecs:label  key="amAnnounce.creatTime"/></span>
	    	</th>
		    <td>
					<span class="textbox"><input type="text" class="textbox-text" value="<fmt:formatDate value='${amAnnounce.createTime}' pattern='yyyy-MM-dd'/>" readonly="readonly"></span>	
			</td>
	    </tr>
	</c:if>
	
	<c:if test="${strAction =='checkAmAnnounce'}">
		<c:if test="${amAnnounce.status =='1'}">
   			<c:if test="${readOnly}">
			    <tr class="edit_tr">
				    <th>
				       <span class="text-font-style-tc"><jecs:label  key="busi.user.check"/></span>
				    </th>
				    <td>
				    	<span class="textbox"><input type="text" readonly="readonly" class="textbox-text" value="${amAnnounce.checkUserNo}/${amAnnounce.checkerName}"/></span>
				    </td>
				   <th>
				        <span class="text-font-style-tc"><jecs:label  key="logType.C"/></span>
				    </th>
				    <td>
					    <%-- ${amAnnounce.checkTime}	--%>
					    <span class="textbox"><input type="text" readonly="readonly" class="textbox-text" value="<fmt:formatDate value='${amAnnounce.checkTime}' pattern='yyyy-MM-dd'/>"/></span>     
				    </td>
			    </tr>
			</c:if>
		</c:if>
	</c:if>	
	
	    <tr class="edit_tr">
	    
	     <th>
	     
    	<span class="text-font-style-tc"><jecs:label  key="bdReconsumMoneyReport.typeCH"/></span>
    </th>
    <td>
        <c:choose>
    		<c:when test="${editFlag ==1}">	
        		<span class="textbox"><jecs:list styleClass="textbox-text" listCode="annoclassno" name="annoClassNo" id="annoClassNo" value="${amAnnounce.annoClassNo}" defaultValue="1"/></span>
        	</c:when>
        	<c:otherwise>
        		<span class="textbox"><span class="textbox-text"><jecs:code listCode="annoclassno"  value="${amAnnounce.annoClassNo}"/></span></span>
          	</c:otherwise>
         </c:choose>
    </td>
    	<th>
    		
		   <span class="text-font-style-tc"><jecs:label  key="amAnnounce.subject"/></span>
		    
    	</th>
	    <td>
	        <!--form:errors path="subject" cssClass="fieldError"/-->
	        <c:choose>
	       		<c:when test="${editFlag ==1}">
	        		<span class="textbox"><form:input path="subject" id="subject"   size="45"  cssClass="textbox-text"/></span><!-- disabled="${readOnly}" -->
	        	</c:when>	
				<c:otherwise>
					 <span class="textbox"><input size="45" class="textbox-text" value="${amAnnounce.subject}" readonly="readonly"/></span>
				</c:otherwise>
			</c:choose>
	    </td>
    
   
    
    </tr>
<c:if test="${strAction !='browserAmAnnounce'}">	
	
	
	<tr class="edit_tr">
		<th>
		
			<span class="text-font-style-tc"><jecs:label  key="amAnnounce.startTime"/></span>
	    </th>
	    <td>
	    	<c:choose>
		    	<c:when test ="${editFlag==1}">
		    		<!--form:errors path="endTime" cssClass="fieldError"/-->
		    		<%-- 
		    		<span class="textbox">
			        <form:input path="startTime"   id="startTime"  cssClass="textbox-text"/></span>
			        	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			        	<script type="text/javascript">        	 
						Calendar.setup({
						inputField     :    "startTime", 
						ifFormat       :    "%Y-%m-%d",  
						button         :    "img_startTime", 
						singleClick    :    true
						}); 
					</script>
					--%>
					
					<span class="textbox">
			        <form:input path="startTime" id="startTime"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" cssClass="textbox-text"/></span>
				        
		    	</c:when>
		    	<c:otherwise>
		    		<%-- ${amAnnounce.startTime}	--%>
			        <span class="textbox"><input type="text" class="textbox-text" value="<fmt:formatDate value='${amAnnounce.startTime}' pattern='yyyy-MM-dd'/>" readonly="readonly"></span>
		    	</c:otherwise>
	    	</c:choose>
	    	
	        
	    </td>
	    
		<th>
			
			 <span class="text-font-style-tc"><jecs:label  key="amAnnounce.endTime"/></span>
	    </th>
	    <td>
	    	<c:choose>
		    	<c:when test ="${editFlag==1}">
		    		<!--form:errors path="endTime" cssClass="fieldError"/-->
		    		<%-- 
		    		<span class="textbox">
			        <form:input path="endTime"   id="endTime" cssClass="textbox-text"/></span>
			        	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			        	<script type="text/javascript">        	 
						Calendar.setup({
						inputField     :    "endTime", 
						ifFormat       :    "%Y-%m-%d",  
						button         :    "img_endTime", 
						singleClick    :    true
						}); 
					</script>
					--%>
					<span class="textbox">
			        <form:input path="endTime"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" id="endTime" cssClass="textbox-text"/></span>
		    	</c:when>
		    	<c:otherwise>
		    		
		    		<span class="textbox"><input type="text" class="textbox-text" value="<fmt:formatDate value='${amAnnounce.endTime}' pattern='yyyy-MM-dd'/>" readonly="readonly"></span>
		    	</c:otherwise>
	    	</c:choose>
	    	
	        
	    </td>
	</tr>
	
	<tr class="edit_tr">
	
			<th>
				<span class="edit_tr_span"><jecs:label  key="amAnnounce.towho"/></span>
			</th>
			
			
			<td align="left" colspan="3">

				<%-- 公司人员 --%>
			
				<c:forEach var="companyPermit" items="${companyPermits}">								
					<c:forEach var="amPermit" items="${amPermits}">
						<c:if test="${amPermit.permitNo == companyPermit.permitNo}">
							<c:set var="hascompany">true</c:set>
						</c:if>
					</c:forEach>								
				</c:forEach>
						
				<c:if test="${hascompany=='true'}">
					<c:if test="${editFlag==2}"> 	
								
								<c:forEach var="companyPermit" items="${companyPermits}">								
									<c:forEach var="amPermit" items="${amPermits}">
										<div class="edit_tr_div">
											<c:if test="${amPermit.permitNo == companyPermit.permitNo}">
													&nbsp;&nbsp;<jecs:locale  key="${companyPermit.permitName}"/>
											</c:if>
										</div>
									</c:forEach>								
								</c:forEach>
					</c:if>
				</c:if>
				
				<c:if test="${editFlag==1}"> 	
			
					<c:forEach var="companyPermit" items="${companyPermits}">
						<div class="edit_tr_div">
							<input type="checkbox"  name="permit" <c:if test="${editFlag!=1}"> disabled </c:if >
								<c:forEach var="amPermit" items="${amPermits}">
									<c:if test="${amPermit.permitNo == companyPermit.permitNo}">
											checked
									</c:if>
								</c:forEach>
								value='${companyPermit.permitNo}'/>
								<jecs:locale  key="${companyPermit.permitName}"/>
						</div>
					
					</c:forEach>		
				</c:if>
					
				
				<%-- 店铺 --%>
		
				<c:forEach var="agentPermit" items="${agentPermits}">							
						<c:forEach var="amPermit" items="${amPermits}">
								<c:if test="${amPermit.permitNo == agentPermit.permitNo}">
									<c:set var="hasagent">true</c:set>
								</c:if>
						</c:forEach>							
				</c:forEach>
				<c:if test="${hasagent=='true'}">
					<c:if test="${editFlag==2}"> 
		
						<c:forEach var="agentPermit" items="${agentPermits}">							
								<c:forEach var="amPermit" items="${amPermits}">
									<div class="edit_tr_div">
										<c:if test="${amPermit.permitNo == agentPermit.permitNo}">
												&nbsp;&nbsp;
												<c:if test="${agentPermit.permitName == 'miMember.isstore'}">
									 				<font color='#6666FF'><jecs:locale  key="${agentPermit.permitName}"/></font>
									 			</c:if>
												<c:if test="${agentPermit.permitName == 'busi.mi.substore'}">
									 				<font color='#6666FF'><jecs:locale  key="${agentPermit.permitName}"/></font>
									 			</c:if>
									 	
										</c:if>
									</div>
								</c:forEach>							
						</c:forEach>
	
					</c:if>
				</c:if>
				<c:if test="${editFlag==1}"> 
					
						<c:forEach var="agentPermit" items="${agentPermits}">
						
						
							<div class="edit_tr_div"><input type="checkbox" class="checkbox" name="permit" <c:if test="${editFlag!=1}"> disabled </c:if >
								<c:forEach var="amPermit" items="${amPermits}">
										<c:if test="${amPermit.permitNo == agentPermit.permitNo}">
												checked
										</c:if>
								</c:forEach>
							 value='${agentPermit.permitNo}'/>
							 			<c:if test="${agentPermit.permitName == 'miMember.isstore'}">
							 				<font color='#6666FF'><jecs:locale  key="${agentPermit.permitName}"/></font>
							 			</c:if>
							 			<c:if test="${agentPermit.permitName == 'busi.mi.substore'}">
							 				<font color='#6666FF'><jecs:locale  key="${agentPermit.permitName}"/></font>
							 			</c:if>
							</div>
						</c:forEach>
					</c:if>
				<%--会员 --%>
				
				<c:forEach var="memberPermit" items="${memberAllPermits}">
					<c:forEach var="amPermit" items="${amPermits}">
						<c:if test="${amPermit.permitNo == memberPermit.permitNo}">
							<c:set var="hasmem">true</c:set>
						</c:if>
					</c:forEach>
				</c:forEach>
				<c:if test="${hasmem=='true'}">		
					<c:if test="${editFlag==2}"> 
					<c:forEach var="memberPermit" items="${memberAllPermits}">
						<c:forEach var="amPermit" items="${amPermits}">
							<div class="edit_tr_div">
								<c:if test="${amPermit.permitNo == memberPermit.permitNo}">
										&nbsp;&nbsp;
								 	<c:if test="${memberPermit.permitName == 'miMember.joymain'}">
								 		<font color='#00908E'><jecs:locale  key="${memberPermit.permitName}"/></font>
								 	</c:if>
								 </c:if>
							</div>
						</c:forEach> 
					</c:forEach>
					<input type="hidden" id="permits" name="permits"/>
					</c:if>
				</c:if>
				<c:if test="${editFlag==1}">
						<c:forEach var="memberPermit" items="${memberAllPermits}">								
						
							<div class="edit_tr_div"><input type="checkbox" name="permit" <c:if test="${editFlag!=1}"> disabled </c:if >
								<c:forEach var="amPermit" items="${amPermits}">
											<c:if test="${amPermit.permitNo == memberPermit.permitNo}">
													checked
											</c:if>
									</c:forEach>
								 value='${memberPermit.permitNo}'/>
								 
									
							 			<c:if test="${memberPermit.permitName == 'miMember.joymain'}">
							 				<font color='#00908E'><jecs:locale  key="${memberPermit.permitName}"/></font>
							 			</c:if>
							</div>
						</c:forEach>
						
						<input type="hidden" id="permits" name="permits"/>
				</c:if>
				
				
				<!-- 2017-1-12 新级别  -->
				
				<c:forEach var="memberPermit" items="${memberLevelPermits}">
					<c:forEach var="amPermit" items="${amPermits}">
						<c:if test="${amPermit.permitNo == memberPermit.permitNo}">
							<c:set var="hasmem">true</c:set>
						</c:if>
					</c:forEach>
				</c:forEach>
				<c:if test="${hasmem=='true'}">		
					<c:if test="${editFlag==2}"> 
					<c:forEach var="memberPermit" items="${memberLevelPermits}">
						<c:forEach var="amPermit" items="${amPermits}">
							<div class="edit_tr_div">
								<c:if test="${amPermit.permitNo == memberPermit.permitNo}">
										&nbsp;&nbsp;
								 		${memberPermit.permitName}
								 </c:if>
							</div>
						</c:forEach> 
					</c:forEach>
					</c:if>
				</c:if>
				<c:if test="${editFlag==1}">
						<c:forEach var="memberPermit" items="${memberLevelPermits}">								
						
							<div class="edit_tr_div"><input type="checkbox" name="permit" <c:if test="${editFlag!=1}"> disabled </c:if >
								<c:forEach var="amPermit" items="${amPermits}">
											<c:if test="${amPermit.permitNo == memberPermit.permitNo}">
													checked
											</c:if>
									</c:forEach>
								 value='${memberPermit.permitNo}'/>
								 
									${memberPermit.permitName}
							</div>
						</c:forEach>
						
						
				</c:if>
				<!--  -->
			</td>
		</tr>
		
		<%--会员团队 --%>
		<tr class="edit_tr">
		
			<th>
				<span class="edit_tr_span"><jecs:label  key="member.team"/></span>
			</th>
				
			<td align="left" colspan="3">
				
				<c:forEach var="teamPermit" items="${teamPermits}">							
					<c:forEach var="amPermit" items="${amPermits}">
							<c:if test="${amPermit.permitNo == teamPermit.permitNo}">
									<c:set var="hasteam">true</c:set>
							</c:if>
						</c:forEach>							
				</c:forEach>
				<c:if test="${hasteam=='true'}">
					<c:if test="${editFlag==2}"> 
						<c:forEach var="teamPermit" items="${teamPermits}">							
								<c:forEach var="amPermit" items="${amPermits}">
									<div class="edit_tr_div">
										<c:if test="${amPermit.permitNo == teamPermit.permitNo}">
												&nbsp;&nbsp;
												${teamPermit.permitName}
										</c:if>
									</div>
								</c:forEach>							
						</c:forEach>
					</c:if>
					</c:if>
					<c:if test="${editFlag==1}"> 
					
						<c:forEach var="teamPermit" items="${teamPermits}">	
							<div class="edit_tr_div">
								<input type="checkbox" class="checkbox" name="permit" <c:if test="${editFlag!=1}"> disabled </c:if >
									<c:forEach var="amPermit" items="${amPermits}">
											<c:if test="${amPermit.permitNo == teamPermit.permitNo}">
													checked
											</c:if>
									</c:forEach>
								 value='${teamPermit.permitNo}'/>
								 	${teamPermit.permitName}</div>
								
						</c:forEach>
					</c:if>
			
			
			</td>
		</tr>
    
</c:if>


    <tr class="edit_tr">
    <th>
   		 <span class="edit_tr_span" style="border:0"><jecs:label  key="amAnnounce.content"/></span>
    </th>
    <td colspan="2">
        <!--form:errors path="content" cssClass="fieldError"/-->
       <c:choose>
       		<c:when test="${editFlag ==1}">
       		<%-- <FCK:editor instanceName="content" toolbarSet="selfBar" height="250px">
							<jsp:attribute name="value">${amAnnounce.content}</jsp:attribute>
							
						</FCK:editor> --%>
			    							<textarea id="content" name="content">${amAnnounce.content }</textarea>
				    							<script type="text/javascript">
													CKEDITOR.replace( 'content' );
												</script>
												
				 </c:when>	
				 <c:otherwise>
				 		<%-- 	${amAnnounce.content}	--%>
				 		<textarea id="content" name="content" readonly="readonly">${amAnnounce.content }</textarea>
				    							<script type="text/javascript">
													CKEDITOR.config.readOnly = true;//让CKEDITOR不可写，只读
													CKEDITOR.replace( 'content' );
												</script>
						
				 </c:otherwise>
			</c:choose>
    </td>  
</tr>



<!--  
	<tr>
		<th width="20%">
			<jecs:label  key="sysOperationLog.moduleName"/>
		</th>
		<td>
			<c:out value="${buttons}" escapeXml="false"/>
			
			<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>
		</td>
	</tr>
-->

	<tr>		
	    <td class="command" colspan="4" align="center">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    	<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>
	    </td>
    </tr>
    </tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AmAnnounce')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
window.onload=function(){
	window.scrollTo(0,0);
}
    Form.focusFirstElement($('amAnnounceForm'));
    function saveAmAnnounce(){
        	/*
        	var companyCode = document.getElementById("companyCode");
        	if(companyCode.value==""){
        		alert("please choose company");
        		document.getElementById("companyCode").focus();
        		return;
        	}
        	
        	var subject = document.getElementById("subject");
        	if(subject.value==""){
        		alert("please enter subject ");
        		document.getElementById("subject").focus();
        		return;
        	}
        	*/
			    		var permits = document.getElementsByName("permit");
			    		var nodes = $A(permits);   
				         var sltNodes = nodes.select(function(node)   
				         {   
				             return node.checked;   
				         });   
				         sltNodes.each(function(node)   
				         {   
				         	 $('permits').value += (node.value+"~");  
				           	 //alert(node.value);   
				         }); 
	        
	         <c:choose>
	         				<c:when test="${editFlag ==3}">
	         						this.location = '<c:url value="/amAnnounces.html"/>?strAction=browserAmAnnounce';
	         				</c:when>
			      			<c:when test="${editFlag ==2}">
						    		$('amAnnounceForm').submit();  
				        	</c:when>
	         				<c:otherwise>
	         						if(validateAmAnnounce($('amAnnounceForm')))
	         							$('amAnnounceForm').submit();
	         				</c:otherwise>
	         </c:choose>
	         
	         
    	}
    	
    	function toback(){
    		if("${param.strAction}" == "addAmAnnounce")
    			this.location='<c:url value="/amAnnounces.html"/>?strAction=editAmAnnounce';
    		else
    			this.location='<c:url value="/amAnnounces.html"/>?strAction=<c:out value="${param.strAction }"/>';
    	}
</script>
<script type="text/javascript" language="Javascript1.1"> 

     var bCancel = false; 

    function validateAmAnnounce(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateRequired(form); 
   } 

    function required () { 
     this.aa = new Array("companyCode", "分公司选项 为必填项。", new Function ("varName", " return this[varName];"));
     this.ab = new Array("subject", "主题 为必填项。", new Function ("varName", " return this[varName];"));
     //this.ac = new Array("content", "内容 为必填项。", new Function ("varName", " return this[varName];"));
    } 



</script>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
