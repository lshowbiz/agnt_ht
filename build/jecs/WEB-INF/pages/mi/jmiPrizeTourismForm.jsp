<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiPrizeTourismDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiPrizeTourismDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
			<button type="button" class="btn btn_sele" name="save"  onclick="bCancel=false;addOrUpdate(this.form)">
				<jecs:locale key="button.save"/>
			</button>
		</jecs:power>
		<jecs:power powerCode="deleteJmiPrizeTourism">
			<button type="submit" class="btn btn_sele" name="delete" 
				onclick="bCancel=true;return confirmDelete(this.form,'JmiPrizeTourism')">
				<jecs:locale key="button.delete"/>
			</button>
		</jecs:power>
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
			<jecs:locale key="operation.button.return"/>
		</button>
</c:set>

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalTownManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jalPostalcodeManager.js'/>" ></script>
<script src="${ctx}/scripts/jquerymobile/jquery-1.7.1.min.js"></script>

<spring:bind path="jmiPrizeTourism.*">
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

<form:form commandName="jmiPrizeTourism" method="post" action="editJmiPrizeTourism.html" onsubmit="return validateJmiPrizeTourism(this)" id="jmiPrizeTourismForm">
<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<form:hidden path="prizeTouismId" />
<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiPrizeTourism')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window">
	<tr class="edit_tr">
		<th>
			<span class="text-font-style-tc">
        		<jecs:label  key="miMember.memberNo" required="true"/>
    		</span>
    	</th>
	    <td>
	    	<span class="textbox">
	        	<form:input path="userCode" id="userCode" readonly="true"  cssClass="textbox-text"/>
	    	</span>
	   	</td>
	    <th>
	    	<span class="text-font-style-tc">
	        	<jecs:label  key="jmiPrizeTourism.phone" required="true"/>
	    	</span>
	   	</th>
	    <td>
	    	<span class="textbox">
	    		<form:input path="phone" id="phone" cssClass="textbox-text"/>
	    	</span>
	    </td>
    </tr>
    
    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.cardname" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="cardname" cssClass="fieldError"/-->
        <form:input path="cardname" id="cardname" cssClass="textbox-text"/>
    </span></td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.cardid" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="cardid" cssClass="fieldError"/-->
        <form:input path="cardid" id="cardid" cssClass="textbox-text"/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.province" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="province" cssClass="fieldError"/-->
        <!--<form:input path="province" id="province" cssClass="textbox-text"/>-->
        <form:select path="province"  cssClass="textbox-text" onchange="getCity('province')">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
		</form:select>
    </span></td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.city" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="city" cssClass="fieldError"/-->
        <!--<form:input path="city" id="city" cssClass="textbox-text"/>-->
        <select name="city" id="city" onchange="getDistrict('city')" class="textbox-text">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.area" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="area" cssClass="fieldError"/-->
        <!--<form:input path="area" id="area" cssClass="textbox-text"/>-->
		<select name="area" id="area" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
    </span></td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.address" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="address" cssClass="fieldError"/-->
        <form:input path="address" id="address" cssClass="textbox-text"/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.height" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="height" cssClass="fieldError"/-->
        <form:input path="height" id="height" cssClass="textbox-text"/>
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.weight" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="weight" cssClass="fieldError"/-->
        <form:input path="weight" id="weight" cssClass="textbox-text"/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.clothessize" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="clothessize" cssClass="fieldError"/-->
        <jecs:list styleClass="textbox-text" listCode="jmiprizetourism.clothessize" name="clothessize" id="clothessize" value="${jmiPrizeTourism.clothessize}" defaultValue=""/>
    </span></td>
	<th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.avoidcertainfood" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="avoidcertainfood" cssClass="fieldError"/-->
        <form:hidden path="avoidcertainfood" />
         <c:forEach items="${avoidcertainfoodList}" var="cb" >
        <input type="checkbox" name="avoidcertainfoods" onchange="chk('avoidcertainfoods','avoidcertainfood')" onclick="chk('avoidcertainfoods','avoidcertainfood')"  <c:if test="${cb.checked=='1'}">checked</c:if> value="${cb.cId}"/>${cb.cValue}&nbsp;&nbsp;
        </c:forEach>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.acceptanceaddress" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="acceptanceaddress" cssClass="fieldError"/-->
        <form:input path="acceptanceaddress" id="acceptanceaddress" cssClass="textbox-text"/>
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.passStar"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="passStar" cssClass="fieldError"/-->
        <jecs:code listCode="pass.star" value="${jmiPrizeTourism.passStar}"/>
    </span></td></tr>
    
    <tr class="edit_tr">
	    <th>
		    <span class="text-font-style-tc">
		        <jecs:label  key="jmiPrizeTourism.isPeer"/>
		    </span>
		</th>
    	<td colspan="3">
	    	<span class="textbox">
		        <jecs:list styleClass="textbox-text" listCode="jmiprizetourism.ispeer" 
		        onchange="isShowPeer();" onclick="isShowPeer();" name="isPeer" id="isPeer" 
		        value="${jmiPrizeTourism.isPeer}" defaultValue=""/>
	    	</span>
    	</td>
	</tr>
    
    <tr class="edit_tr"><th colspan="4">
	    <fieldset style="border-top: 2px solid #cccccc;border-right: 0px ;border-bottom: 0px ;border-left: 0px ;font-family: Arial, Helvetica, sans-serif;font-weight:bold;">
		<legend ><jecs:locale key="jmiPrizeTourism.peer.info"/>:</legend>
    </span></th></tr>
    
    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.peertype" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peertype" cssClass="fieldError"/-->
        <jecs:list styleClass="textbox-text" listCode="jmiprizetourism.peertype" name="peertype" id="peertype"  value="${jmiPrizeTourism.peertype}" defaultValue=""/>
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="list.sex" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peersex" cssClass="fieldError"/-->
        <jecs:list styleClass="textbox-text" listCode="testuser.sex" name="peersex" id="peersex"  value="${jmiPrizeTourism.peersex}" defaultValue=""/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.cardname" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peercardname" cssClass="fieldError"/-->
        <form:input path="peercardname" id="peercardname" cssClass="textbox-text"/>
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.cardid" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peercardid" cssClass="fieldError"/-->
        <form:input path="peercardid" id="peercardid" cssClass="textbox-text"/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.phone" required="true"/>
    </span></th>
    <td colspan="3">
    	<span class="textbox">
        <!--form:errors path="peerphone" cssClass="fieldError"/-->
        <form:input path="peerphone" id="peerphone" cssClass="textbox-text"/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.province" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peerprovince" cssClass="fieldError"/-->
        <!--<form:input path="peerprovince" id="peerprovince" cssClass="textbox-text"/>-->
        <form:select path="peerprovince"  cssClass="textbox-text" onchange="getCity('peerprovince')">
					<form:option label="" value=""><jecs:locale key='list.please.select'/></form:option>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName" />
		</form:select>
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.city" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peercity" cssClass="fieldError"/-->
        <!--<form:input path="peercity" id="peercity" cssClass="textbox-text"/>-->
        <select name="peercity" id="peercity" onchange="getDistrict('peercity')" class="textbox-text">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.area" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peerarea" cssClass="fieldError"/-->
        <!--<form:input path="peerarea" id="peerarea" cssClass="textbox-text"/>-->
        <select name="peerarea" id="peerarea" class="textbox-text">
				<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.address" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peeraddress" cssClass="fieldError"/-->
        <form:input path="peeraddress" id="peeraddress" cssClass="textbox-text"/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.height" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peerheight" cssClass="fieldError"/-->
        <form:input path="peerheight" id="peerheight" cssClass="textbox-text"/>
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.weight" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peerweight" cssClass="fieldError"/-->
        <form:input path="peerweight" id="peerweight" cssClass="textbox-text"/>
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.clothessize" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peerclothessize" cssClass="fieldError"/-->
        <jecs:list styleClass="textbox-text" listCode="jmiprizetourism.clothessize" name="peerclothessize" id="peerclothessize" value="${jmiPrizeTourism.clothessize}" defaultValue=""/>
    </span></td>
    <th><span class="text-font-style-tc">
        <jecs:label  key="jmiPrizeTourism.avoidcertainfood" required="true"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="peeravoidcertainfood" cssClass="fieldError"/-->
        <form:hidden path="peeravoidcertainfood" />
        <c:forEach items="${peerAvoidcertainfoodList}" var="cb" >
        <input type="checkbox" name="peeravoidcertainfoods" onchange="chk('peeravoidcertainfoods','peeravoidcertainfood')" onclick="chk('peeravoidcertainfoods','peeravoidcertainfood')" <c:if test="${cb.checked=='1'}">checked</c:if> value="${cb.cId}"/>${cb.cValue}&nbsp;&nbsp;
        </c:forEach>
    </span></td></tr>
    <tr class="edit_tr">
    	<td class="command" colspan="4" align="center"><c:out value="${buttons}" escapeXml="false"/></td>
    </tr>
</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr class="edit_tr">
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiPrizeTourism')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </span></td></tr>
</table-->
<script>
			
	function chk(o1,o2) {
		var obj = document.getElementsByName(o1); 
		
		var s = '';
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].checked)
				s += obj[i].value + ','; 
		}
		$('#'+o2).val(s);
	}

	function getCity(p) {
		var province = document.getElementById(p).value;
		if ('peerprovince' == p) {
			alCityManager.getAlCityByProvinceId(province, callBackPeerCity);
		} else if ('province' == p) {
			alCityManager.getAlCityByProvinceId(province, callBackCity);
		}

	}
	function callBackCity(valid) {
		var cityElemment = document.getElementById('city');

		cityElemment.options.length = 0;

		var o = new Option("<jecs:locale key="list.please.select"/>", "");
		cityElemment.options.add(o);

		for ( var i = 0; i < valid.length; i++) {
			var cityId = valid[i].cityId;
			var cityName = valid[i].cityName;
			var o = new Option(cityName, cityId);
			cityElemment.options.add(o);
			if (cityId == '${jmiPrizeTourism.city}') {
				o.selected = true;
			}
		}
		getDistrict('city');
	}

	function callBackPeerCity(valid) {
		var cityElemment = document.getElementById('peercity');

		cityElemment.options.length = 0;

		var o = new Option("<jecs:locale key="list.please.select"/>", "");
		cityElemment.options.add(o);

		for ( var i = 0; i < valid.length; i++) {
			var cityId = valid[i].cityId;
			var cityName = valid[i].cityName;
			var o = new Option(cityName, cityId);
			cityElemment.options.add(o);
			if (cityId == '${jmiPrizeTourism.peercity}') {
				o.selected = true;
			}
		}
		getDistrict('peercity');
	}

	function getDistrict(cc) {
		var city = document.getElementById(cc).value;
		if ('peercity' == cc) {
			alDistrictManager.getAlDistrictByCityId(city, callBackPeerDistrict);
		} else if ('city' == cc) {
			alDistrictManager.getAlDistrictByCityId(city, callBackDistrict);
		}

	}
	function callBackDistrict(valid) {
		var districtElemment = document.getElementById('area');

		districtElemment.options.length = 0;

		var o = new Option("<jecs:locale key="list.please.select"/>", "");
		districtElemment.options.add(o);

		for ( var i = 0; i < valid.length; i++) {
			var districtId = valid[i].districtId;
			var districtName = valid[i].districtName;
			var o = new Option(districtName, districtId);
			districtElemment.options.add(o);
			if ('${jmiPrizeTourism.area}' == districtId) {
				o.selected = true;
			}
		}
	}
	function callBackPeerDistrict(valid) {
		var districtElemment = document.getElementById('peerarea');

		districtElemment.options.length = 0;

		var o = new Option("<jecs:locale key="list.please.select"/>", "");
		districtElemment.options.add(o);

		for ( var i = 0; i < valid.length; i++) {
			var districtId = valid[i].districtId;
			var districtName = valid[i].districtName;
			var o = new Option(districtName, districtId);
			districtElemment.options.add(o);
			if ('${jmiPrizeTourism.peerarea}' == districtId) {
				o.selected = true;
			}
		}
	}
	getCity('province');
	getCity('peerprovince');
	
	
	function chkWrite(o1,o2) {
		var val = document.getElementById(o2).value.split(",");
		var obj = document.getElementsByName(o1); 
		
		for ( var i = 0; i < obj.length; i++) {
			for ( var j = 0; j < val.length; j++) {
				if(obj[i].value == val[j]){
					obj[i].checked = true;
				}
			}
		}
	}
	
	chkWrite('avoidcertainfoods','avoidcertainfood');
	chkWrite('peeravoidcertainfoods','peeravoidcertainfood');
	
	function isShowPeer(){
		var isPeer = $('#isPeer').val();
		if('1'==isPeer){
			$(".peer").css('display','');
		}else{
			$(".peer").css('display','none');
		}
		
	}
	isShowPeer();
	
	//非空验证
	function validateEmpty(inputArr,messageArr){
		for(var i=0;i<inputArr.length;i++){
			if(''==$('#'+inputArr[i]).val()){
				alert(messageArr[i]+' 必填项！');
				$('#'+inputArr[i]).focus();
				return false;
			}	
		}
		return true;
	}
	//长度验证
	function validateLen(inputArr,messageArr,lenArr){
		for(var i=0;i<inputArr.length;i++){
			if(getBytesLength($('#'+inputArr[i]).val())>lenArr[i]){
				alert(messageArr[i]+' 超长！');
				$('#'+inputArr[i]).focus();
				return false;
			}	
		}
		return true;
	}
	function getBytesLength(str) {
	// 在GBK编码里，除了ASCII字符，其它都占两个字符宽
		return str.replace(/[^\x00-\xff]/g, 'xx').length;
	}
	//正则表达式验证数据合法性
	function validateExec(patrn,input,mes){
		if(!patrn.exec($('#'+input).val())){
			alert(mes);
			$('#'+input).focus();
			return false;
		}
		return true;
	}
	function validateForm(){
		var inputArr=['cardname','cardid','phone','province','city','area','address','height','weight','avoidcertainfood','acceptanceaddress'];//需验证的元素控件
		var messageArr=['证件姓名','身份证号码','联系电话','省','市','区','详细地址','身高','体重','餐饮忌口','证件材料收寄地址'];//提示语
		var lenArr=[50,18,20,10,10,10,200,20,20,20,200];//长度
		//为空
		if(!validateEmpty(inputArr,messageArr)) return false;
		//长度，字节数
		if(!validateLen(inputArr,messageArr,lenArr)) return false;
		
		var isPeer = $('#isPeer').val();
		if('1'==isPeer){
			inputArr=['peercardname','peercardid','peerphone','peerprovince','peercity','peerarea','peeraddress','peerheight','peerweight','peeravoidcertainfood'];
			messageArr=['同行人证件姓名','同行人身份证号码','同行人联系电话','同行人省','同行人市','同行人区','同行人详细地址','同行人身高','同行人体重','同行人餐饮忌口'];
			lenArr=[50,18,20,10,10,10,200,20,20,20];//长度
			//为空
			if(!validateEmpty(inputArr,messageArr)) return false;
			//长度，字节数
			if(!validateLen(inputArr,messageArr,lenArr)) return false;
		}
		
		var patrn=/^\d{15}|\d{18}$/;//身份证
		if(!validateExec(patrn,'cardid','身份证格式不正确！')) return false;
		if('1'==isPeer){
			if(!validateExec(patrn,'peercardid','同行人身份证格式不正确！')) return false;
		}
		patrn=/^([0-9]{0,20})$/;//手机
		if(!validateExec(patrn,'phone','联系电话为不正确手机号码！')) return false;
		if('1'==isPeer){
			if(!validateExec(patrn,'peerphone','同行人联系电话为不正确手机号码！')) return false;
		}
		patrn=/^[0-9]+(.[0-9]{0,2})?$/;//手机
		if(!validateExec(patrn,'height','身高为数字且最多两位小数！')) return false;
		if(!validateExec(patrn,'weight','体重为数字且最多两位小数！')) return false;
		if('1'==isPeer){
			if(!validateExec(patrn,'peerheight','同行人身高为数字且最多两位小数！')) return false;
			if(!validateExec(patrn,'peerweight','同行人体重为数字且最多两位小数！')) return false;
		}
		
		return true;
	}
	
	function addOrUpdate(theFrom){
	   if(!validateForm()) return false;
	   theFrom.submit();
	}
</script>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiPrizeTourismForm'));
</script>

<v:javascript formName="jmiPrizeTourism" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>

