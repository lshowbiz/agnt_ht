<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmSalePromoterDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmSalePromoterDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript">
function teamsCheck(obj,str){
	
	var teams = document.getElementById(str);
	if(obj.checked){
		teams.value+=","+obj.value;
	}else{
		var temKey = obj.value;
		var tValue = teams.value;
		var tem ="";
		var temArr = tValue.split(",");
		for(var i=1;i<temArr.length;i++){
			if(temKey !=temArr[i]){
				tem += ","+temArr[i];
			}	
		}
		teams.value = tem;
	}
}

function showTR(str){
	var trObj = document.getElementById('trIntegral');
	if(str=="1"){
		trObj.style.display="inline-table";
	} else {
		trObj.style.display="none";
	}
}

function strDateTime(str)
{
	var reg = /^(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/
	var r = str.match(reg);
	if(r==null)return false; 
	var d = new Date(r[1],r[2]-1,r[3],r[4],r[5],r[6]);  
// alert(d.getFullYear()==r[1]); alert((d.getMonth()+1)==r[2]); alert(d.getDate()==r[3]); alert(d.getHours()==r[4]);alert(d.getMinutes()==r[5]); alert(d.getSeconds()==r[6]);
	return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[2]&&d.getDate()==r[3]&&d.getHours()==r[4]&&d.getMinutes()==r[5]&&d.getSeconds()==r[6]);
}

function validateJpmSalePromoter1(form){
	var ret = true;
	var fields = new Array(); 
	var i=0;
	var stime = form['startime'].value;
	var etime = form['endtime'].value;

	if(stime==null || stime==''){
		alert("开始时间不能为空！");
		 return false;
	}
	if(etime==null || etime==''){
		alert("结束时间不能为空！");
		 return false;
	}
	var sDate = new Date(stime.replace(/\-/g,'/')); 	
    var eDate = new Date(etime.replace(/\-/g,'/'));
    if(sDate > eDate)
    {
     alert("结束时间不能小于开始时间");
     return false;
    }
    
    var integral = form['integral'].value;
    var amount = form['amount'].value;
    var pv = form['pv'].value;
 	if(isNaN(integral)){
    	fields[i++] = "赠送比例填写错误！";
 		ret = false;
    }
 	if(isNaN(amount)){
 		fields[i++] = "总金额填写错误！";
 		ret = false;
 	}
 	if(isNaN(pv)){
 		fields[i++] = "pv值填写错误！";
 		ret = false;
 	}
    
    if (fields.length > 0) { 
	       alert(fields.join('\n'));                                                                      
	    } 
 
	return ret;
}

</script>


<spring:bind path="jpmSalePromoter.*">
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

<form:form commandName="jpmSalePromoter" method="post" action="editJpmSaleIntegral.html" onsubmit="return validateJpmSalePromoter1(this)" id="jpmSalePromoterForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%" >
<tbody class="window" >
<form:hidden path="spno"/>
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="jpmSalePromoter.promoterName"/></span></th>
	    <td>
	        <span class="textbox"><form:input path="spname" id="spname" cssClass="textbox-text"/></span>
	    </td>
		<th><span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="jpmSalePromoter.integral"/></span></th>
	    <td>
	        <span class="textbox"><form:input path="integral" id="integral" cssClass="textbox-text"/></span>
	    </td>
	</tr>
	
    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.startime"/></span></th>
		<td>
			<span class="textbox"><input type="text" name="startime" id="startime"  class="textbox-text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
			  value="<fmt:formatDate value="${jpmSalePro.startime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/></span>
			
		</td>
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.endtime"/></span></th>
		<td>
			<!--form:errors path="endtime" cssClass="fieldError"/-->
			<span class="textbox"><input type="text" name="endtime" id="endtime"   class="textbox-text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
			value="<fmt:formatDate value="${jpmSalePro.endtime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/></span>
			
		</td>
	</tr>
    <tr class="edit_tr">
	
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.amount"/></span></th>
		<td>
			<!--form:errors path="amount" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="amount" id="amount" cssClass="textbox-text"/></span>
		</td>
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.pv"/></span></th>
		<td>
			<!--form:errors path="pv" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="pv" id="pv" cssClass="textbox-text"/></span>
		</td>
	</tr>

  
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.ispresent"/></span></th>
	    <td>
			<span class="textbox">
	        <form:radiobutton path="ispresent" value="0" onclick="showTR(0);" /><jecs:locale key="yesno.no"/>
			<form:radiobutton path="ispresent" value="1" onclick="showTR(1);"/><jecs:locale key="yesno.yes"/> 
			</span>
	    </td>
		<th><span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="jpmSalePromoter.isactiva"/></span></th>
		<td>
			<!--form:errors path="isactiva" cssClass="fieldError"/-->
			<span class="textbox">
			<form:radiobutton path="isactiva" value="0"/><jecs:locale key="yesno.no"/>
			<form:radiobutton path="isactiva" value="1"/><jecs:locale key="yesno.yes"/>
			</span>
			
		</td>
	</tr>
	
	<tr class="edit_tr">
		
		
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.isorder"/></span></th>
	    <td>
	        <!--form:errors path="isactiva" cssClass="fieldError"/-->
			<span class="textbox">
	        <form:radiobutton path="isorder" value="0"/><jecs:locale key="yesno.no"/>
			<form:radiobutton path="isorder" value="1"/><jecs:locale key="yesno.yes"/> 
			</span>
	        
	    </td>
		<th><span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="jpmSalePromoter.companyno"/></span></th>
		<td>
			<!--form:errors path="companyno" cssClass="fieldError"/-->
			 <input type="hidden" id="companys" name="companys" value="<c:if test="${companys !=null }">${companys }</c:if>">
			<c:choose>
					<c:when test="${spno !=null }">
						<span class="textbox">
						<c:forEach var="company" items="${comSet }">
							<input type="checkbox" id="company${company}" name="company${company}" value="${company}" 
							onclick="teamsCheck(this,'companys')"
							<c:forEach var="com" items="${compArr }">
								<c:if test="${com==company }">checked="checked"</c:if> 
							</c:forEach>/>${company }
							
						</c:forEach>
						</span>
					</c:when>
					<c:otherwise>
						<span class="textbox">
						<c:forEach var="company" items="${comSet }">
							<input type="checkbox" id="company${company}" name="company${company}" value="${company}" 
							onclick="teamsCheck(this,'companys')"/>${company }
						</c:forEach>
						</span>
					</c:otherwise>
				</c:choose>
		</td>
	</tr>
	
	<tr class="edit_tr">
	   
		<th align="left" ><span class="edit_tr_span"><font color="red">*</font><jecs:label  key="miMember.cardType"/></span></th>
		<td colspan="3">
	        <input type="hidden" id="userLever" name="userLever" value="${jpmSalePro.userLevel }">
	        
	         <c:choose>
					<c:when test="${leverArr !=null }">
						<c:forEach var="userLevel" items="${userLevelMap }">
							<div class="edit_tr_div"><input type="checkbox" id="userLevel${userLevel.key }"   class="textbox-text"
								name="userLevel${userLevel.key }" value="${userLevel.key }" onclick="teamsCheck(this,'userLever')"
								<c:forEach var="ot" items="${leverArr }">
									<c:if test="${ot==userLevel.key}">checked="checked"</c:if>
								</c:forEach>/><jecs:locale key="${userLevel.value }"/></div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="userLevel" items="${userLevelMap }">
							<div class="edit_tr_div">
							<input type="checkbox" id="userLevel${userLevel.key }" name="userLevel${userLevel.key }" value="${userLevel.key }" 
											 class="textbox-text" onclick="teamsCheck(this,'userLever')"/>
							 <jecs:locale key="${userLevel.value }"/>
							</div>
						</c:forEach>
					</c:otherwise>
			</c:choose>
	        <%-- <c:choose>
		        	<c:when test="${leverArr !=null }">
		        		<div class="edit_tr_div"><input type="checkbox" id="userLever0" name="userLever0" value="0" 
					      	onclick="teamsCheck(this,'userLever')"
					      	<c:forEach var="lever" items="${leverArr }">
					      		<c:if test="${lever==0}">checked="checked"</c:if>
					      	</c:forEach>/><jecs:label key="bd.cardType0"/></div>
					    <div class="edit_tr_div"><input type="checkbox" id="userLever1" name="userLever1" value="1" 
					      	onclick="teamsCheck(this,'userLever')"
					      	<c:forEach var="lever" items="${leverArr }">
					      		<c:if test="${lever==1}">checked="checked"</c:if>
					      	</c:forEach>/><jecs:label key="bd.cardType1"/></div>
		        		<div class="edit_tr_div"><input type="checkbox" id="userLever2" name="userLever2" value="2" 
					      	onclick="teamsCheck(this,'userLever')"
					      	<c:forEach var="lever" items="${leverArr }">
					      		<c:if test="${lever==2}">checked="checked"</c:if>
					      	</c:forEach>/><jecs:label key="bd.cardType2"/></div>
					   <div class="edit_tr_div"> <input type="checkbox" id="userLever3" name="userLever3" value="3" 
					      	onclick="teamsCheck(this,'userLever')"
					      	<c:forEach var="lever" items="${leverArr }">
					      		<c:if test="${lever==3}">checked="checked"</c:if>
					      	</c:forEach>/><jecs:label key="bd.cardType3"/></div>
		        		<div class="edit_tr_div"><input type="checkbox" id="userLever4" name="userLever4" value="4" 
					      	onclick="teamsCheck(this,'userLever')"
					      	<c:forEach var="lever" items="${leverArr }">
					      		<c:if test="${lever==4}">checked="checked"</c:if>
					      	</c:forEach>/><jecs:label key="bd.cardType4"/></div>
		        		<div class="edit_tr_div"><input type="checkbox" id="userLever6" name="userLever6" value="6"
					      	onclick="teamsCheck(this,'userLever')"
					      	<c:forEach var="lever" items="${leverArr }">
					      		<c:if test="${lever==6}">checked="checked"</c:if>
					      	</c:forEach>/><jecs:label key="permit.vipClass"/></div>
					    
		        	</c:when>
		        	<c:otherwise>
		        		<div class="edit_tr_div"><input type="checkbox" id="userLever0" name="userLever0" value="0" 
					      	onclick="teamsCheck(this,'userLever')"/><jecs:label key="bd.cardType0"/></div>
					   <div class="edit_tr_div"> <input type="checkbox" id="userLever1" name="userLever1" value="1" 
					      	onclick="teamsCheck(this,'userLever')"/><jecs:label key="bd.cardType1"/></div>
		        		<div class="edit_tr_div"><input type="checkbox" id="userLever2" name="userLever2" value="2" 
					      	onclick="teamsCheck(this,'userLever')"/><jecs:label key="bd.cardType2"/></div>
					   <div class="edit_tr_div"> <input type="checkbox" id="userLever3" name="userLever3" value="3" 
					      	onclick="teamsCheck(this,'userLever')"/><jecs:label key="bd.cardType3"/></div>
		        		<div class="edit_tr_div"><input type="checkbox" id="userLever4" name="userLever4" value="4" 
					      	onclick="teamsCheck(this,'userLever')"/><jecs:label key="bd.cardType4"/></div>
		        		<div class="edit_tr_div"><input type="checkbox" id="userLever6" name="userLever6" value="6"
					      	onclick="teamsCheck(this,'userLever')"/><jecs:label key="permit.vipClass"/></div>
					   
		        	</c:otherwise>
		        </c:choose> --%>
	    </td>
		
    </tr>
	
	<tr>
		<td colspan="2" style="padding:0;">
			<table class='' width="100%" border="0" id="trIntegral" style="display:none;">
				<tr>
					<th><span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="jpmSalePromoter.reintegral"/></span></th>
					<td>
						<form:input path="reintegral" id="reintegral" cssClass="textbox-text"/>
					</td>
				</tr>
				<tr>
					<th><span class="text-font-style-tc"><jecs:locale key="bdBonusRankingReport.recommandPerson"/><jecs:label key="pd.orderType"/></span></th>
					<td>
						<input type="hidden" id="preOrderType" name="preOrderType" value="<c:if test="${preOrderType !=null }">${preOrderType }</c:if>">
					<c:choose>
					<c:when test="${spno !=null }">
						<c:forEach var="ordertype" items="${orderMap }">
							<div class="edit_tr_div"><input type="checkbox" id="pOrderType${ordertype.key }"   class="textbox-text"
								name="pOrderType${ordertype.key }" value="${ordertype.key }" onclick="teamsCheck(this,'preOrderType')"
								<c:forEach var="ot" items="${preTypeArr }">
									<c:if test="${ot==ordertype.key}">checked="checked"</c:if>
								</c:forEach>/><jecs:locale key="${ordertype.value }"/></div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="ordertype" items="${orderMap }">
							<div class="edit_tr_div">
								<input type="checkbox" id="pOrderType${ordertype.key }" 
									name="pOrderType${ordertype.key }" value="${ordertype.key }"  class="textbox-text" onclick="teamsCheck(this,'preOrderType')"/>
									<jecs:locale key="${ordertype.value }"/>
							</div>
						</c:forEach>
					</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</table>
		</td>
		
	</tr>
	
	<tr class="edit_tr">
		
		<th align="left" ><span class="edit_tr_span"><font color="red">*</font><jecs:label  key="jpmSalePromoter.ordertype"/><span></th>
		<td colspan="3">
			<!--form:errors path="ordertype" cssClass="fieldError"/-->
			<input type="hidden" id="orderTypes" name="orderTypes" value="<c:if test="${orderTypes !=null }">${orderTypes }</c:if>">
			<c:choose>
					<c:when test="${spno !=null }">
						<c:forEach var="ordertype" items="${orderMap }">
							<div class="edit_tr_div"><input type="checkbox" id="orderType${ordertype.key }" 
								name="orderType${ordertype.key }" value="${ordertype.key }" onclick="teamsCheck(this,'orderTypes')"
								<c:forEach var="ot" items="${orderTypeArr }">
									<c:if test="${ot==ordertype.key}">checked="checked"</c:if>
								</c:forEach>/><jecs:locale key="${ordertype.value }"/>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="ordertype" items="${orderMap }">
							<div class="edit_tr_div">
								<input type="checkbox" id="orderType${ordertype.key }" name="orderType${ordertype.key }" value="${ordertype.key }" 
						  		onclick="teamsCheck(this,'orderTypes')"/>
						  		<jecs:locale key="${ordertype.value }"/>
						  	</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		</td>
	</tr>
	
  	<tr class="edit_tr">
		<th align="left" ><span class="edit_tr_span"><jecs:label  key="jpmSale.notTeams"/><span></th>
	    <td colspan="3">
	       <input type="hidden" id="teams" name="teams" <c:if test="${teams!=null }">value="${teams }"</c:if> />
	        <c:choose>
	        	<c:when test="${spno !=null }">
	        		
	        				<input type="button" class="button" onclick="selectAll();" value="<jecs:locale  key='button.selectAll'/>"/>
								<input type="button" class="button" onclick="reSelectAll();" value="<jecs:locale  key='common.reSelectAll'/>"/>
							<br>
		        		<c:forEach var="teammap" items="${teamList }" varStatus="st">
		        				
				        			<div class="edit_tr_div"><input type="checkbox" id="team${teammap.code }" 
				        				name="team${teammap.code }" value="${teammap.code }" onclick="teamsCheck(this,'teams')"
				        				<c:forEach var="team" items="${teamArr }" >
				        				<c:if test="${team==teammap.code}">checked="checked"</c:if> 
				        				</c:forEach>/>${teammap.fullName }(${teammap.name })
									</div>
		        		</c:forEach>
		        	
	        	</c:when>
	        	<c:otherwise>
	        		
		        		<c:forEach var="teammap" items="${teamList }" varStatus="st">
		        			<c:if test="${st.count%6==1}"></c:if>
		        				<div class="edit_tr_div">
				        			<input type="checkbox" id="team${teammap.code }" 
				        			name="team${teammap.code }" value="${teammap.code }" 
				        			onclick="teamsCheck(this,'teams')"/>${teammap.fullName }(${teammap.name })
		        				</div>
			        		<c:if test="${st.count%6==0}"></c:if>
		        		</c:forEach>
		        	
	        	</c:otherwise>
	        </c:choose>
	    </td>
	</tr>
	<tr class="edit_tr">
		<th><span class="text-font-style-tc-tare"> <jecs:label  key="jpmSalePromoter.descr"/></span></th>
		<td  colspan="3">
        <!--form:errors path="descr" cssClass="fieldError"/-->
       
		<span class="text-font-style-tc-right"> <form:textarea path="descr" id="descr" rows="5" cols="60" cssClass="textarea_border"/></span>
			
		</td>
	</tr>
	
	<tr>
		<td class="command" colspan="4" align="center">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
			<button type="button" class="btn btn_sele" onclick="javascript:history.back();" ><jecs:locale key="operation.button.return"/></button>
		</td>
	</tr>
	
</table>
<!--/fieldset-->
<input type="hidden" id="teamCodeStr" />
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmSalePromoterForm'));
</script>

<v:javascript formName="jpmSalePromoter" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>

<!-- 添加全选反选按钮 Modify By WuCF 20150327  -->
<script type="text/javascript">
	var teamCodes = '';
	<c:forEach items="${teamList}" var="team">
		teamCodes = teamCodes+','+'${team.code}';
	</c:forEach>
	document.getElementById("teamCodeStr").value = teamCodes;

	function selectAll(){
		var teamCodeStr = document.getElementById("teamCodeStr").value;
		teamCodeStr = teamCodeStr.substring(1);
		var myarray = teamCodeStr.split(",");
	    for (i=0;i<myarray.length ;i++ ){
			var teamId = document.getElementById('team'+myarray[i]);
			teamId.checked=true;
	    }   
	}

	function reSelectAll(){
		var teamCodeStr = document.getElementById("teamCodeStr").value;
		teamCodeStr = teamCodeStr.substring(1);
		var myarray = teamCodeStr.split(",");
	    for (i=0;i<myarray.length ;i++ ){
			var teamId = document.getElementById('team'+myarray[i]);
			if(teamId.checked==false){
				teamId.checked=true;
			}else if(teamId.checked==true){
				teamId.checked=false;
			}
	    }   		
	}
</script>