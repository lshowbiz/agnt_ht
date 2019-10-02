<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />



<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>

<script type="text/javascript" src="./scripts/validate.js"> </script> 
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/print.css'/>" />
<style media="print">
	.noPrint { 
		display: none;
	}
</style>

<c:if test="${empty bdPeriod.wid}">
<form:form commandName="bdPeriod" method="post" action="jpoMemberOrderTeamReport.html" id="bdPeriodForm">
	<spring:bind path="bdPeriod.*">
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

	<div class="searchBar">
				
	</div>
	<table class="detail" width="70%">
	<tbody class="window" >
	<form:hidden path="wid"/>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="bdReconsumMoneyReport.companyCH"/></span></th>
			<td>
			
			<span class="textbox"><jecs:company name="companyCode" selected="${param.companyCode }"  withAA="true"  styleClass="textbox-text"/></span>
			</td>
		</tr>
		</c:if>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="miMember.memberNo"/></span></th>
			<td>
				<span class="textbox"><input type="text" name="userCode" id="userCode" value="${param.userCode }" size="10" class="textbox-text"/></span>
			</td>
			<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.wweek" /></span></th>
			<td>
				<span class="textbox"><input type="text" name="formatedWeek" id="formatedWeek" size="8" class="textbox-text"/></span>
				<jecs:locale key="label.example"/>200806
			</td>
		</tr>
		
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:label key="customerRecord.type"/></span></th>
			<td>
			<span class="textbox">
				<jecs:list name="type" id="type" listCode="relation.type" value="${param.relationType }" defaultValue="" styleClass="textbox-text"/></span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="date.type"/></span></th>
			<td>
			<span class="textbox">
				<jecs:list name="dateType" id="dateType" listCode="date.type" value="${param.dateType }" defaultValue="" styleClass="textbox-text"/></span>
			</td>
		</tr>
		
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="common.startTime"/></span></th>
			<td>
		
			<span class="textbox"><input name="startDate" id="startDate" type="text" value="${param.startDate }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/></span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime"/></span></th>
			<td >
			<span class="textbox"><input name="endDate" id="endDate" type="text" value="${param.endDate }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>	</span>
			</td>
		</tr>
		
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:locale key="bdPeriod.wyear" /></span></th>
			<td>
				<span class="textbox"><input type="text" name="formatedYear" id="formatedYear" size="8" value="${param.formatedYear }"  class="textbox-text"/></span>
			</td>
			<th><span class="text-font-style-tc"><jecs:locale key="bdPeriod.wmonth" /></span></th>
			<td>
				<span class="textbox"><input type="text" name="formatedMonth" id="formatedMonth" size="8" value="${param.formatedMonth }"  class="textbox-text"/></span>
			</td>
		</tr>
		
		<c:if test="${sessionScope.sessionLogin.companyCode!='AA'}">
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:locale key="shipping.province" /></span></th>
			<td>
			<span class="textbox">
				<select name="province" id="province" onchange="getCity()"  class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
					<c:forEach items="${alStateProvinces}" var="alStateProvince">
						<option value="${alStateProvince.stateProvinceId }">${alStateProvince.stateProvinceName }</option>
					</c:forEach>
				</select>
			</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:locale key="shipping.city" /></span></th>
			<td>
			<span class="textbox">
				<select name="city" id="city"  class="textbox-text">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
			</span>
			</td>
		</tr>
		
		</c:if>
		<tr>		
			<td class="command" colspan="4" align="center">
		<jecs:power powerCode="jpoMemberOrderTeamReport">	
				<input type="submit" name="submit" onclick="document.getElementById('statistic').value='';" value="<jecs:locale key="button.report"/>" class="btn btn_sele"/>
				<input type="submit" name="submit" onclick="document.getElementById('statistic').value='statistic';" value="<jecs:locale key="operation.button.create"/>" class="btn btn_sele"/>
				</jecs:power>
				<input type="hidden" name="strAction" value="jpoMemberOrderTeamReport"/>
				<input type="hidden" name="statistic" value="" id="statistic"/>
		</td>
		</tr>
	</tbody>
	</table>
	
</form:form>

</c:if>






<script type="text/javascript" >


		   function getCity(){
		   var province=document.getElementById('province').value;
		   alCityManager.getAlCityByProvinceId(province,callBackCity);
		   }
		   function callBackCity(valid){
			   var cityElemment=document.getElementById('city');
    
        		cityElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var cityId= valid[i].cityId;	   
		        var cityName=valid[i].cityName;   
		        var o=new Option(cityName,cityId);
		        cityElemment.options.add(o);
			        if(cityId=='${param.city}'){
			        	o.selected=true;
			        }  
			   }  
		   		
		   }
		   
		    </script> 