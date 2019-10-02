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
function strDateTime(str)
{
	var reg = /^(\d{4})-(\d{2})-(\d{2}) (\d{2}):(\d{2}):(\d{2})$/
	var r = str.match(reg);
	if(r==null)return false; 
	var d = new Date(r[1],r[2]-1,r[3],r[4],r[5],r[6]);  
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
    
    var discount = form['discount'].value;
    var amount = form['amount'].value;
    var pv = form['pv'].value;
 	if(isNaN(discount)){
    	fields[i++] = "折扣率填写错误！";
 		ret = false;
    }
 	if(isNaN(amount)){
 		fields[i++] = "金额填写错误！";
 		ret = false;
 	}
 	if(isNaN(pv)){
 		fields[i++] = "pv值填写错误！";
 		ret = false;
 	}
    
	var sDate = new Date(stime.replace(/\-/g,'/')); 	
    var eDate = new Date(etime.replace(/\-/g,'/'));
    if(sDate > eDate)
    {
     alert("结束时间不能小于开始时间");
     return false;
    }
    
    var objs=document.getElementsByName('company');
    var comp = false;//判断是否有选中项，默认为无
    for(var i=0;i<objs.length;i++)
    {
      if(objs[i].checked==true)
       {
    	comp = true;
        break;
       }
    }
    if(comp == false)
    {
	    fields[i++] = "请选择国别！";
	    ret = false;
    }
    
    var orderType=document.getElementsByName('orderType');
    var ot = false;//判断是否有选中项，默认为无
    for(var i=0;i<orderType.length;i++)
    {
      if(orderType[i].checked==true)
       {
    	ot = true;
        break;
       }
    }
    if(ot == false)
    {
	    fields[i++] = "请选择订单类型 ！";
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

<form:form commandName="jpmSalePromoter" method="post" action="editJpmSalePromoter.html" onsubmit="return validateJpmSalePromoter1(this)" id="jpmSalePromoterForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmSalePromoter')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="spno"/>
<tbody class="window" >
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="jpmSalePromoter.promoterName"/></span></th>
	    <td>
			<span class="textbox">
	        <form:input path="spname" id="spname" cssClass="textbox-text"/>
			</span>
	    </td>
		<th><span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="jpmSalePromoter.discount"/></span></th>
	    <td>
	        <!--form:errors path="discount" cssClass="fieldError"/-->
			<span class="textbox">
	        <form:input path="discount" id="discount" cssClass="textbox-text"/>
			</span>
	    </td>
	</tr>
    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.startime"/></span></th>
	    <td>
			<span class="textbox">
	    	<input type="text" name="startime" id="startime"  Class="textbox-text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
    	  		value="<fmt:formatDate value="${jpmSalePro.startime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</span>
	       
	    </td>
	    <th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.endtime"/></span></th>
	    <td>
	        <!--form:errors path="endtime" cssClass="fieldError"/-->
			<span class="textbox">
	        <input type="text" name="endtime" id="endtime" Class="textbox-text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" 
    		value="<fmt:formatDate value="${jpmSalePro.endtime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</span>
	       
	    </td>
    </tr>

<%-- 
    <tr><th>
        <jecs:label  key="jpmSalePromoter.spNum"/>
    </th>
    <td>
        <!--form:errors path="spNum" cssClass="fieldError"/-->
        <form:input path="spNum" id="spNum" cssClass="text medium"/>
    </td></tr>
--%>
   <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.amount"/></span></th>
		<td>
			<!--form:errors path="amount" cssClass="fieldError"/-->
			<span class="textbox">
			<form:input path="amount" id="amount"  cssClass="textbox-text"/>
			</span>
		</td>
		
		<th><span class="text-font-style-tc"><jecs:label  key="jpmSalePromoter.pv"/></span></th>
		<td>
			<!--form:errors path="pv" cssClass="fieldError"/-->
			<span class="textbox">
			<form:input path="pv" id="pv" cssClass="textbox-text"/>
			</span>
		</td>
	</tr>

    <tr></tr>
	
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><spring:message code="sale.DisModel"/></span></th>
	    <td>
			<span class="textbox">
	        <select name="saleType" id="saleType" class="textbox-text">
	        	<option value="1" <c:if test="${saleType==1 }">selected="selected"</c:if>><spring:message code="sale.productDis"/></option>
	        	<option value="5" <c:if test="${saleType==5 }">selected="selected"</c:if>><spring:message code="sale.orderPriceDis"/></option>
	        </select></span>
	    </td>
		<th><span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="jpmSalePromoter.companyno"/></span></th>
		<td>
			<!--form:errors path="companyno" cssClass="fieldError"/-->
			<input type="hidden" id="companys" name="companys" value="<c:if test="${companys !=null }">${companys }</c:if>">
			<c:choose>
					
					<c:when test="${spno !=null }">
						<span class="textbox">
						<c:forEach var="company" items="${comSet }">
							<input type="checkbox" id="company${company}" name="company" value="${company}" 
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
							<input type="checkbox" id="company${company}" name="company" value="${company}" 
							onclick="teamsCheck(this,'companys')"/>${company }
						</c:forEach>
						</span>
					</c:otherwise>
					
				</c:choose>
		</td>
    </tr>
	<tr class="edit_tr">
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
		<th align="left" ><span class="edit_tr_span"><font color="red">*</font><jecs:label  key="jpmSalePromoter.ordertype"/><span></th>
		<td align="left"  colspan="3">
			<!--form:errors path="ordertype" cssClass="fieldError"/-->
			<input type="hidden" id="orderTypes" name="orderTypes" value="<c:if test="${orderTypes !=null }">${orderTypes }</c:if>">
			<c:choose>
					<c:when test="${spno !=null }">
						<c:forEach var="ordertype" items="${orderMap }">
						<div class="edit_tr_div">
							<input type="checkbox" id="orderType${ordertype.key }" 
								name="orderType" value="${ordertype.key }" onclick="teamsCheck(this,'orderTypes')"
								<c:forEach var="ot" items="${orderTypeArr }">
									<c:if test="${ot==ordertype.key}">checked="checked"</c:if>
								</c:forEach>/>${ordertype.value }
						</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="ordertype" items="${orderMap }">
						<div class="edit_tr_div">
							<input type="checkbox" id="orderType${ordertype.key }" name="orderType" value="${ordertype.key }" 
						  onclick="teamsCheck(this,'orderTypes')"/>${ordertype.value }
						  </div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		</td>
	</tr>

    <tr class="edit_tr">
		<th align="left" ><span class="edit_tr_span"><jecs:label  key="jpmSale.notTeams"/></span></th>
		<td align="left"  colspan="3">
			<!--form:errors path="teamno" cssClass="fieldError"/-->
		   <input type="hidden" id="teams" name="teams" <c:if test="${teams!=null }">value="${teams }"</c:if> />
				<c:choose>
					<c:when test="${spno !=null }">
						<input type="button" class="button" onclick="selectAll();"
													value="<jecs:locale  key='button.selectAll'/>"/>
													<input type="button" class="button" onclick="reSelectAll();"
													value="<jecs:locale  key='common.reSelectAll'/>"/>
							<c:forEach var="teammap" items="${teamList }" varStatus="st">
								<div class="edit_tr_div">
										<input type="checkbox" id="team${teammap.code }" 
											name="team${teammap.code }" value="${teammap.code }"
											onclick="teamsCheck(this,'teams')"
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
			 <span class="text-font-style-tc-right"><form:textarea path="descr" id="descr" rows="5" cols="60" cssClass="textarea_border"/></span>
			
		</td>
	</tr>
	
	
	<tr>
		<td class="command" colspan="4" align="center">
			
			<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save" ><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<jecs:power powerCode="deleteJpmSalePromoter">
				<button type="button" class="btn btn_sele" onclick="javascript:history.back();" ><jecs:locale key="operation.button.return"/></button>
			</jecs:power>
		</td>
	</tr>
</tbody>

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