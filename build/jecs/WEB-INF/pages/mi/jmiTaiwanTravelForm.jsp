<%@ include file="/common/taglibs.jsp"%>

<%@ page contentType="text/html; charset=utf-8" language="java" %>
<title><jecs:locale key="jmiTaiwanTravelDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiTaiwanTravelDetail.heading"/></content>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key='operation.button.save'/>" /></c:if>
		</jecs:power>
		<%--<jecs:power powerCode="deleteJmiTaiwanTravel">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JmiTaiwanTravel')" value="<jecs:locale  key='operation.button.delete' />" />
		</jecs:power>--%>
		
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key='operation.button.return'/>" />
</c:set>

<spring:bind path="jmiTaiwanTravel.*">
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

<form:form commandName="jmiTaiwanTravel" method="post" action="editJmiTaiwanTravel.html" id="jmiTaiwanTravelForm">

<div id="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<c:if test="${ param.strAction!='addJmiTaiwanTravel'}">
	<form:hidden path="userCode" id="userCode"/>
</c:if>
	
<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiTaiwanTravel')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->

<table width="718"  border="0" cellpadding="0" cellspacing="0" class='detail'>
  <tr>
    <td colspan="7"  ><p align="right"><strong style="font-size:20px">台湾旅游报名申请表 </strong><strong style="font-size:15px">（注：以下资料必须完整填写） </strong></p></td>
  </tr>
  <tr>
    <td colspan="7"><p>报名申请日期：
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	<fmt:formatDate value="${ jmiTaiwanTravel.filingDate}" pattern="yyyy-MM-dd"/>
    	
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    <form:input path="filingDate" id="filingDate" readonly="true" size="8" cssClass="readonly"/>
				<img src="./images/calendar/calendar7.gif" id="img_filingDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "filingDate", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_filingDate", 
					singleClick    :    true
					}); 
				</script>
    </c:if>
	</td>
  </tr>
  <tr>
    <td width="43" rowspan="7"><p align="center" >申请人资料 </p></td>
    <td width="86" valign="middle" align="center">姓名</td>
    <td width="225">  
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.userName}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    <form:input path="userName" id="userName" cssClass="text medium"/>
    </c:if>
    </td>
    <td width="90" valign="middle" align="center">别名 <br />
      （常用名）</td><td colspan="3">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.petName}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
     <form:input path="petName" id="petName" cssClass="text medium"/>
    </c:if>
    
    </td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">性别 </td>
    <td width="225"> 
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
        <jecs:code listCode="sex" value="${jmiTaiwanTravel.sex}"/>
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
         <jecs:list name="sex" listCode="sex" value="${jmiTaiwanTravel.sex}" defaultValue=""/>	
    </c:if>
    </td>
    <td width="90" valign="middle" align="center">身份证号 </td>
    <td colspan="3"> 
	    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
	    	${ jmiTaiwanTravel.idNo}
	    </c:if>
	    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
	    <form:input path="idNo" id="idNo" cssClass="text medium"/>
	    </c:if>
    </td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">会员编号 </td>
    <td width="225">
    
    <c:if test="${param.strAction!='addJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.userCode }
    </c:if>
    <c:if test="${param.strAction=='addJmiTaiwanTravel' }">
    	<form:input path="userCode" id="userCode" cssClass="text medium"/>
    </c:if>
    
    </td>
    <td width="90" valign="middle" align="center">户口所在地</td>
    <td colspan="3"> 
    
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
			    <form:select path="idProvince"  cssClass="text medium" onchange="getIdCity();" disabled="true">
							<form:option label="" value=""/>
				            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
		       	<select name="idCity" id="idCity" disabled="true">
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
			    <form:select path="idProvince"  cssClass="text medium" onchange="getIdCity();">
							<form:option label="" value=""/>
				            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
    省
	
		       	<select name="idCity" id="idCity" >
					<option value=""><jecs:locale key="list.please.select"/></option>
				</select>市 
    </c:if>   
				   </td>
  </tr>
  <tr>
    <td width="86"  valign="middle" align="center">联系电话</td>
    <td width="225"> 
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.phone}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    	<form:input path="phone" id="phone" cssClass="text medium"/>
    </c:if></td>
    <td width="90" valign="middle" align="center">初次申请出入台湾地区 </td>
    <td colspan="3"> 
    
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
        <jecs:code listCode="tw.applytype" value="${jmiTaiwanTravel.applyType}"/>
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
         <jecs:list name="applyType" listCode="tw.applytype" value="${jmiTaiwanTravel.applyType}" defaultValue="" showBlankLine="true"/>
    </c:if>
    </td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">常住地</td>
    <td width="225">  
    
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
			    <form:select path="commonProvince"  cssClass="text medium" onchange="getCommonCity();" disabled="true">
							<form:option label="" value=""/>
				            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
  
    <select name="commonCity" id="commonCity" disabled="true">
			<option value=""><jecs:locale key="list.please.select"/></option>
	</select>
				
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
			    <form:select path="commonProvince"  cssClass="text medium" onchange="getCommonCity();">
							<form:option label="" value=""/>
				            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
    省
    <select name="commonCity" id="commonCity">
			<option value=""><jecs:locale key="list.please.select"/></option>
	</select>
				市 
    </c:if>      </td>
    <td width="90" valign="middle" align="center">普通地区选择出发城市 </td>
    <td colspan="3">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
        <jecs:code listCode="tw.commonfromcity" value="${jmiTaiwanTravel.commonFromCity}"/>
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
         <jecs:list name="commonFromCity" listCode="tw.commonfromcity" value="${jmiTaiwanTravel.commonFromCity}" defaultValue="" showBlankLine="true"/>	
    </c:if>
     </td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">特殊地区人员选择出发城市 </td>
    <td width="225">
    
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
        <jecs:code listCode="tw.specialfromcity" value="${jmiTaiwanTravel.specialFromCity}"/>
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
         <jecs:list name="specialFromCity" listCode="tw.specialfromcity" value="${jmiTaiwanTravel.specialFromCity}" defaultValue="" showBlankLine="true"/>	
    </c:if>
    </td>
    <td colspan="4">特殊地区包括：新疆、内蒙古、甘肃、宁夏、青海、西藏等6个省（自治区）</td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">通信地址 </td>
    <td > 
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.address}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    	<form:input path="address" id="address" cssClass="text medium"/>
    </c:if>
    </td>
    <td width="97" valign="middle" align="center">邮政编码 </td>
    <td width="146" colspan="3">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.postalcode}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    <form:input path="postalcode" id="postalcode" cssClass="text medium"/>
    </c:if></td>
  </tr>
  <tr>
    <td width="43" rowspan="2"><p align="center">汇款信息 </p></td>
    <td width="86"  valign="middle" align="center">汇款时间 </td>
    <td width="225">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	<fmt:formatDate value="${ jmiTaiwanTravel.remittanceTime}" pattern="yyyy-MM-dd"/>
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    <form:input path="remittanceTime" id="remittanceTime" readonly="true" size="8" cssClass="readonly"/>
<img src="./images/calendar/calendar7.gif" id="img_remittanceTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "remittanceTime", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_remittanceTime", 
					singleClick    :    true
					}); 
				</script>
    </c:if> 
	</td>
    <td width="90" valign="middle" align="center">汇款账户名</td>
    <td colspan="3">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.remittanceName}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    	<form:input path="remittanceName" id="remittanceName" cssClass="text medium"/>
    </c:if></td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">汇款账号</td>
    <td width="225">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.remittanceCard}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    	<form:input path="remittanceCard" id="remittanceCard" cssClass="text medium"/>
    </c:if></td>
    <td width="90" valign="middle" align="center">开户行 </td>
    <td colspan="3"> 
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.remittanceBank}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
	    <form:select path="remittanceBank"  cssClass="text medium">
							<form:option value=""></form:option>
				            <form:options items="${sysBankList}" itemValue="bankKey" itemLabel="bankValue"/>
		</form:select>
    </c:if>
    </td>
  </tr>
  <tr>
    <td colspan="7"><p align="center">如是夫妻卡，请将您配偶的申请资料填写到下面的：申请人配偶资料 <br />
      （注：做过夫妻卡备案的由结算部确认，未做备案的公司不予承认，填写无效） </p></td>
  </tr>
  <tr>
    <td width="43" rowspan="4"><p align="center" >申请人配偶资料 </p></td>
    <td width="86" valign="middle" align="center">姓名 </td>
    <td width="225">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.spouseName}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
     <form:input path="spouseName" id="spouseName" cssClass="text medium"/>
    </c:if></td>
    <td width="90" valign="middle" align="center">别名 <br />
      （常用名）</td>
    <td colspan="3">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.spousePetName}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    	<form:input path="spousePetName" id="spousePetName" cssClass="text medium"/>
    </c:if></td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">性别 </td>
    <td width="225">
    
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
        <jecs:code listCode="sex" value="${jmiTaiwanTravel.spouseSex}"/>
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
         <jecs:list name="spouseSex" listCode="sex" value="${jmiTaiwanTravel.spouseSex}" defaultValue="" showBlankLine="true"/>	
    </c:if>
    
    </td>
    <td width="90" valign="middle" align="center">身份证号 </td>
    <td colspan="3">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.spouseIdNo}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    	<form:input path="spouseIdNo" id="spouseIdNo" cssClass="text medium"/>
    </c:if></td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">会员编号 </td>
    <td width="225">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.spouseUserCode}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    <form:input path="spouseUserCode" id="spouseUserCode" cssClass="text medium"/>
    </c:if></td>
    <td width="90" valign="middle" align="center">户口所在地 </td>
    <td colspan="3">
    
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
			    <form:select path="spouseIdProvince"  cssClass="text medium" onchange="getSpouseIdCity();" disabled="true">
							<form:option label="" value=""/>
				            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
    <select name="spouseIdCity" id="spouseIdCity" disabled="true">
			<option value=""><jecs:locale key="list.please.select"/></option>
	</select> 
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
			    <form:select path="spouseIdProvince"  cssClass="text medium" onchange="getSpouseIdCity();">
							<form:option label="" value=""/>
				            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
				</form:select>
    省
    <select name="spouseIdCity" id="spouseIdCity">
			<option value=""><jecs:locale key="list.please.select"/></option>
	</select>
	市    
    </c:if>      </td>
  </tr>
  <tr>
    <td width="86" valign="middle" align="center">联系电话</td>
    <td width="225">
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
    	${ jmiTaiwanTravel.spousePhone}
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
    	<form:input path="spousePhone" id="spousePhone" cssClass="text medium"/>
    </c:if></td>
    <td width="90" valign="middle" align="center">初次申请出入台湾地区 </td>
    
    <td colspan="3"> 
    
    <c:if test="${param.strAction=='viewJmiTaiwanTravel' }">
        <jecs:code listCode="tw.applytype" value="${jmiTaiwanTravel.spouseApplyType}"/>
    </c:if>
    <c:if test="${param.strAction!='viewJmiTaiwanTravel' }">
         <jecs:list name="spouseApplyType" listCode="tw.applytype" value="${jmiTaiwanTravel.spouseApplyType}" defaultValue="" showBlankLine="true"/>
    </c:if>
    
    </td>
  </tr>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiTaiwanTravel')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    getIdCity();
	getCommonCity();
	function getIdCity(){
		   var province=document.getElementById('idProvince').value;
		   		alCityManager.getAlCityByProvinceId(province,callIdCity);
		   }
		   	function callIdCity(valid){
			   var cityElemment=$('idCity');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  

		       for (var i=0;i<valid.length;i++) {
			        var cityId= valid[i].cityId;	   
			        var cityName=valid[i].cityName;   
			        var o=new Option(cityName,cityId);
			        cityElemment.options.add(o);  
			        if(cityId=='${jmiTaiwanTravel.idCity}'){
			        	o.selected=true;
			        	
			        }
			   }
		   }
		   
	function getCommonCity(){
		   var province=document.getElementById('commonProvince').value;
		   		alCityManager.getAlCityByProvinceId(province,callCommonCity);
		   }
		   	function callCommonCity(valid){
			   var cityElemment=$('commonCity');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  

		       for (var i=0;i<valid.length;i++) {
			        var cityId= valid[i].cityId;	   
			        var cityName=valid[i].cityName;   
			        var o=new Option(cityName,cityId);
			        cityElemment.options.add(o);  
			        if(cityId=='${jmiTaiwanTravel.commonCity}'){
			        	o.selected=true;
			        }
			   }
		   }
	function getSpouseIdCity(){
		   var province=document.getElementById('spouseIdProvince').value;
		   		alCityManager.getAlCityByProvinceId(province,callSpouseIdCity);
		   }
		   	function callSpouseIdCity(valid){
			   var cityElemment=$('spouseIdCity');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  

		       for (var i=0;i<valid.length;i++) {
			        var cityId= valid[i].cityId;	   
			        var cityName=valid[i].cityName;   
			        var o=new Option(cityName,cityId);
			        cityElemment.options.add(o);  
			        if(cityId=='${jmiTaiwanTravel.spouseIdCity}'){
			        	o.selected=true;
			        }
			   }
		   }
</script>
