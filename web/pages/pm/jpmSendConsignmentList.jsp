<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>

<title><jecs:locale key="jpmSendConsignmentList.title"/></title>
<content tag="heading"><jecs:locale key="jpmSendConsignmentList.heading"/></content>
<meta name="menu" content="JpmSendConsignmentMenu"/>
<style>
	.abs { position:absolute;}
	.rel { position:relative;}
	.tr { text-align:right;}
	.personalInfoTab span { font-size:14px;}
	.personalInfoTab td { padding:5px 0;}
	.addrTr td { background:#CCC;}
	.pdl10 { padding-left:10px;}
	.newAddress { height:26px; line-height:26px; text-align:right; padding-right:10px; background-color:#CFDDF0;}
	.ota { text-align:right;padding-right:20px;font-size:14px;}
	.ota a { margin:0 10px;}
	.tabQueryLs { border: 1px solid #E3E3E3;}
	.tabQueryLs th,.tabQueryLs td {
		height: 26px;
		line-height: 26px;
		font-size: 12px;
		text-align:center;
		border-left: 1px solid #E3E3E3;
		border-bottom: 1px solid #E3E3E3;
	}
	.popupAddr {
		width:1000px;
		padding:10px 10px 30px;
		border:10px solid #CCC;
		border:10px solid rgba(0,0,0,0.2);
		-webkit-border-radius:10px;
		-moz-border-radius:10px;
		border-radius:10px;	
		-webkit-box-shadow: 10px 10px 5px #888888;
		-moz-border-box-shadow: 10px 10px 5px #888888;
		box-shadow: 10px 10px 5px #888888;
	}
	.popupT { height:30px; line-height:30px;}
	.popupT .bt { float:left; font-size:16px; font-weight:bold;}
	.popupT .sc { float:right; font-size:16px; font-weight:bold; color:red;}
	.popupT .sc:hover { text-decoration:none;}
</style>

<script src="<c:url value='/dwr/util.js'/>"></script>
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="${ctx}/scripts/jquery/jquery-142min.js"></script>
<script src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jmiAddrBookManager.js'/>" ></script>
	<input type="hidden" name="num" id="num" value="<c:out value="${ model['consignmenNum'] }" />" />

	<h2><jecs:locale key="jpmSendConsignment.config"/></h2>
	<h3><jecs:title key="jpmSendConsignment.addrChoose"/></h3>
	<table width="100%" class="personalInfoTab">
		<tbody>
			<c:forEach items="${jmiAddrBooks }" var="jmiAddrBookVar">

			<tr>
				<td colspan="3">
					<label for="${jmiAddrBookVar.fabId}">
						<c:if test="${jpmSendConsignment.fabId == jmiAddrBookVar.fabId}">
						<input type="radio" name="fabId" id="${jmiAddrBookVar.fabId}" value="${jmiAddrBookVar.fabId}" checked/>
						</c:if>
						<c:if test="${jpmSendConsignment.fabId != jmiAddrBookVar.fabId}">
						<input type="radio" name="fabId" id="${jmiAddrBookVar.fabId}" value="${jmiAddrBookVar.fabId}"/>
						</c:if>
						<span>
							<ng:region regionType="p" regionId="${jmiAddrBookVar.province }"></ng:region>
							<input type="hidden" value="${jmiAddrBookVar.province }">
						</span>
						<span>
							<ng:region regionType="c" regionId="${jmiAddrBookVar.city }"></ng:region>
							<input type="hidden" value="${jmiAddrBookVar.city }">
						</span> 
						<span>
							<ng:region regionType="d" regionId="${jmiAddrBookVar.district }"></ng:region>
							<input type="hidden" value="${jmiAddrBookVar.district }">
						</span>
						<span>${jmiAddrBookVar.address}</span>
						<span>(${jmiAddrBookVar.firstName}${jmiAddrBookVar.lastName}&nbsp;<jecs:locale key="jpmSendConsignment.shou"/>)</span>
						<span style="margin-left:10px;">${jmiAddrBookVar.phone}</span>
					</label>
				</td>
			</tr>
			</c:forEach>
			
			<input type="hidden" name="userCode" id="userCode" value="${model.userCode }"/>
			<tr>
				<td>
					<label class="pdl10"><jecs:title key="jpmSendConsignment.sendNum"/></label>
				</td>
				<td colspan="2">
					<input name="consignmenNum" id="consignmenNum" cssClass="formerror" value="${jpmSendConsignment.consignmenNum }" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'/>
					<input type="hidden" name="curConsignmentNum" id="curConsignmentNum" value="${jpmSendConsignment.consignmenNum }"/>
					<span style="color:red;">
					<jecs:locale key="jpmSendConsignment.consignmenNumBegin"/> 
					<c:out value="${ model['consignmenNum'] }" />
					<jecs:locale key="jpmSendConsignment.consignmenNumEnd"/>
					</span>
				</td>
			</tr>
			<tr>
				<td colspan="3" class="ota">
					<a href="javascript:submitValid('jpmSendConsignments.html?strAction=addConsignment','<c:out value="${ model['specNo'] }" />','${jpmSendConsignment.consignmentNo }','<c:out value="${ model['productNo'] }" />','<c:out value="${ model['molId'] }" />','${model.userCode }');" style="color:blue;"><jecs:locale key="operation.button.save"/></a>
					<a href="jpmWineMemberOrders.html?strAction=orderConfigList&userCode=${model.userCode }&molId=<c:out value="${ model['molId'] }" />&productNo=<c:out value="${ model['productNo'] }" />" style="color:blue;"><jecs:locale key="operation.button.return"/> </a>
				</td>
			</tr>
		</tbody>

	</table>
	
	<div class="newAddress">
		<a href="javascript:void(0);" style="color:#666;font-size:14px;" id="js_newAddr"><jecs:locale key="jpmSendConsignment.newAddr"/></a>
	</div>
	
	<h2><jecs:locale key="jpmSendConsignment.configManager"/></h2>
	<table width="100%" border="0" class="tabQueryLs" id="tabQueryLs" style="margin-top: 2px;">
		<thead>
			<tr>
				<th><jecs:locale key="jpmSendConsignment.addr"/></th>
				<th><jecs:locale key="jpmSendConsignment.sendNum"/></th>
				<th><jecs:locale key="jpmSendConsignment.configTime"/></th>
				<th><jecs:locale key="title.operation"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ jpmSendConsignmentList}" var="consignment">
				
			<tr>
				<td>
					<input type="hidden" name="consignmentNo" id="consignmentNo" value="${consignment.consignmentNo }">
					<input type="hidden" name="fabId" id="fabId" value="${consignment.fabId }">
					${consignment.address }
				</td>
				<td>
					<input type="hidden" name="consignmenNum" id="consignmenNum" value="${consignment.consignmenNum }">
					${consignment.consignmenNum }
				</td>
				<td>
					${consignment.sendDate }
				</td>
				<td>
					<a href="jpmSendConsignments.html?specNo=<c:out value="${ model['specNo'] }" />&userCode=${model.userCode }&consignmentNo=${consignment.consignmentNo}&productNo=<c:out value="${ model['productNo'] }" />&molId=<c:out value="${ model['molId'] }" />"><jecs:locale key="operation.button.edit"/></a>
					 | 
					<a href="#" style="color:red;" onclick="del('jpmSendConsignments.html?strAction=delConsignment','${consignment.consignmentNo}','<c:out value="${ model['specNo'] }" />','<c:out value="${ model['productNo'] }" />','<c:out value="${ model['molId'] }" />','&userCode=${model.userCode }')"><jecs:locale key="operation.button.delete"/></a>
				</td>
			</tr>

			</c:forEach>
		</tbody>
	</table>


<!--	<div class="mask" id="mask"></div> -->
	<div class="popupAddr abs" id="popupAddr" style="display:none;">
		<div class="popupT">
			<span class="bt"><jecs:locale key="jpmSendConsignment.newAddr"/></span>
			<a href="javascript:void(0);" class="sc" id="sc">[&nbsp;关闭&nbsp;]</a>
		</div>
		<form id="addrForm">
			<table width="100%" border="0">
				<colgroup style="width:100px;"></colgroup>
				<tbody id="address">
					<tr>
						<td class="tr"><jecs:title  key="shipping.province" required="true"/></td>
						<td>
							<div class="rel">
									 
								<select name="province" id="province" class="mySelect" onchange="getIdCity();" data-required="true">
									<option value="" selected="selected"><ng:locale key="list.please.select"/></option>
									<c:forEach items="${alStateProvinces }" var="alStateProvinceVar">
										 <option value="${alStateProvinceVar.stateProvinceId }">${alStateProvinceVar.stateProvinceName }</option>
									</c:forEach>
								</select>
							
							</div>
						</td>
						<td class="tr"><jecs:title  key="shipping.city" required="true"/></td>
						<td>
							<div class="rel">
								<select name="city" id="city" onchange="getIdDistrict();" class="mySelect" data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>
							</div>
						
						</td>
						<td class="tr"><jecs:title  key="shipping.district"/></td>
						<td>
							<div class="rel">
								<select name="district" id="district" class="mySelect"  data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								
								</select>
							</div>
						</td>
						<td class="tr"><jecs:title  key="shipping.postalcode" required="true"/></td>
						<td><div class="rel"><input type="text" id="postalcode" class="w60" data-required="true" data-type="number" data-maxlength="6" /></div></td>
					</tr>
					<tr>
						<td class="tr"><jecs:title  key="shipping.address" required="true"/></td>
						<td colspan="4"><div class="rel"><input type="text" id="addressV" data-required="true" style="width:85%;" /></div></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class="tr"><jecs:title  key="shipping.firstName" required="true"/></td>
						<td colspan="7"><div class="rel"><input type="text" id="firstName" data-required="true" /></div></td>
					</tr>
					<tr>
						<td class="tr"><jecs:title  key="shipping.lastName" required="true"/></td>
						<td colspan="7"><div class="rel"><input type="text" id="lastName" data-required="true" /></div></td>
					</tr>
					<tr>
						<td class="tr"><jecs:title key="shipping.mobiletele"/></td>
						<td>
							<div class="rel"><input type="text" id="mobiletele" data-required="true" data-type="number" data-maxlength="11" /></div>
						</td>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr>
						<td class="tr"><jecs:title key="shipping.phone"/></td>
						<td><input type="text" id="phone" /></td>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="8" style="text-align:center;">
							<a href="javascript:void(0);" style="color:blue;font-size:14px;" id="js_confirm" onclick="saveAddr();"><jecs:locale key="operation.button.save"/></a>
							&nbsp;&nbsp;|&nbsp;&nbsp;
							<a href="javascript:void(0);" style="color:blue;font-size:14px;" id="js_cancel"><jecs:locale key="operation.button.cancel"/></a>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	
	
<script>

   function editJpmSendConsignment(consignmentNo){
   		<jecs:power powerCode="editJpmSendConsignment">
					window.location="editJpmSendConsignment.html?strAction=editJpmSendConsignment&consignmentNo="+consignmentNo;
			</jecs:power>
		}

</script>
<script>
function del(url,consignmentNo,specNo,productNo,molId,userCode){
    if(confirm("<jecs:locale key="jpmMemberConfig.confirm.del"/>")){
        location.href=url+"&consignmentNo="+consignmentNo+"&specNo="+specNo+"&code="+userCode+"&userCode="+userCode+"&molId="+molId+"&productNo="+productNo;
    }
    else{
        return;
    }
}

function submitValid(url,specNo,consignmentNo,productNo,molId,userCode)
	{				
		var fabId = $("input:radio[name='fabId']:checked").val();
		var consignmenNum = $("#consignmenNum").val();
		var num = $("#num").val();
		var curConsignmentNum = $("#curConsignmentNum").val();
		
		if(null == fabId || '' == fabId || 'null' == fabId)
		{
			alert('<jecs:locale key="jpmSendConsignment.alert.addr"/>');
			return;
		}
		if(null == consignmenNum || '' == consignmenNum || 'null' == consignmenNum)
		{
			alert('<jecs:locale key="jpmSendConsignment.alert.sendNum"/>');
			return;
		}
		if(0 == consignmenNum || '0' == consignmenNum)
		{
			alert('<jecs:locale key="jpmSendConsignment.alert.sendNumNotZero"/>');
			return;
		}
		if(null == curConsignmentNum || '' == curConsignmentNum)
		{
			if(Number(consignmenNum) > Number(num))
			{
				alert('<jecs:locale key="jpmSendConsignment.alert.maxSendNum"/>');
				return;
			}
		}
		else
		{
			if(Number(consignmenNum) > (Number(curConsignmentNum) + Number(num)))
			{
				alert('<jecs:locale key="jpmSendConsignment.alert.maxSendNum"/>');
				return;
			}
		}
		location.href=url+"&fabId="+fabId+"&consignmenNum="+consignmenNum+"&specNo="+specNo+"&consignmentNo="+consignmentNo+"&productNo="+productNo+"&molId="+molId+"&userCode="+userCode;
	}
</script>
<script>

function popupNewAddr(){
	
//	var $mask = jQuery('#mask');
	var $popupAddr = jQuery('#popupAddr');
	
//	$mask.show().css({
//		height:jQuery("body").height()
//	});
	$popupAddr.css({
	
		left:( jQuery(window).width() - $popupAddr.outerWidth() ) / 2,
		top:( jQuery(window).height() - $popupAddr.outerHeight() ) / 2,
		zIndex:"9999",
		background:"#FFF"
	}).animate({
		opacity:"show"
	},600);
	
}
function closeDialog(obj){

//	var $mask = jQuery("#mask");
	
//	if( $mask ) $mask.animate({ opacity:"hide" },600);
	jQuery(obj).parents("[id^='popup']").animate({ opacity:"hide" },600);
	window.location.reload();
}


jQuery(function(){
	
		jQuery('#js_newAddr').click(function(){
			popupNewAddr();
		});


	
		jQuery('#js_cancel,#sc').click(function(){
			if( jQuery("#popupAddr:visible") ) closeDialog("#js_cancel");
			window.location.reload();
		}); 

/* 		$('#addrForm').coffee({   
			click: {  
				'#js_cancel': function(){  
					$('#addrForm').parsley('reset').find(":text").val('').end().find("select").find(":selected").val('').text('<jecs:locale key="list.please.select"/>');
				},  
				'#js_confirm': function(){  
					$('#addrForm').parsley('validate');
					
				}  
			}
		}); */
		
	//	var initId = $("#js_address").find(":checked").attr('id');
		
	//	$('#formOrder').find(":hidden[name='fabName']").val(initId);
});
 
function getIdCity(){
		   var province=$("#province").val();
		   alCityManager.getAlCityByProvinceId(province,callIdCity);
}
function callIdCity(valid){
			  DWRUtil.removeAllOptions("city");
			  DWRUtil.addOptions("city",{'':'select..'});  
			  DWRUtil.addOptions("city",valid,"cityId","cityName");
				if(''!='${jmiMember.city}'){
					$("#city").val('${jmiMember.city}');

				}
		   getIdDistrict();
	   }
function getIdDistrict(){
			var city=$("#city").val();
			alDistrictManager.getAlDistrictByCityId(city,callBackIdDistrict);
	   }
function callBackIdDistrict(valid){
			DWRUtil.removeAllOptions("district");
			DWRUtil.addOptions("district",{'':'select..'});  
			DWRUtil.addOptions("district",valid,"districtId","districtName");
			if(''!='${jmiMember.district}'){
				jq("#district").val('${jmiMember.district}');
			}

	   }
	   
function saveAddr(){
		
		var firstName=$('#firstName').val();
		var lastName=$('#lastName').val();
		var province=$('#province').val();
		var city=$('#city').val();
		var district=$('#district').val();
		var address=$('#addressV').val();
		var postalcode=$('#postalcode').val();
		var mobiletele=$('#mobiletele').val();
		var phone=$('#phone').val();
		var userCode=$('#userCode').val();
		var isDefault=$('#defaultAddr').attr('checked');
		if(isDefault=='checked'){
			isDefault='1';
		}else{
			isDefault='';
		}
		
		if(firstName==''){
			 var errorMsg = '<jecs:locale key="shippingLastName.isNotNull"/>';
			 alert(errorMsg);
			 return;
		}
		if(lastName==''){
			 var errorMsg = '<jecs:locale key="shippingFirstName.isNotNull"/>';
			 alert(errorMsg);
			 return;
		}
		if(province==''){
			 var errorMsg = '<jecs:locale key="shippingProvince.isNotNull"/>';
			 alert(errorMsg);
			 return;
		}
		if(city==''){
			 var errorMsg = '<jecs:locale key="shippingCity.isNotNull"/>';
			 alert(errorMsg);
			 return;
		}
		if(address==''){
			 var errorMsg = '<jecs:locale key="shippingAddress.isNotNull"/>';
			 alert(errorMsg);
			 return;
		}
		
		if(postalcode==''){
			 var errorMsg = '<jecs:locale key="shippingPostalcode.isNotNull"/>';
			 alert(errorMsg);
			 return;
		}
		if(mobiletele==''){
			 var errorMsg = '<jecs:locale key="shippingMobiletele.isNotNull"/>';
			 alert(errorMsg);
			 return;
		}
		
		if(isDefault=='checked'){
			isDefault='1';
		}else{
			isDefault='';
		}
		var jmiAddrBook;
		jmiAddrBook={"firstName":firstName,"lastName":lastName,"province":province,"city":city,"district":district,"address":address,"postalcode":postalcode,"mobiletele":mobiletele,"phone":phone,"isDefault":isDefault,"userCode":userCode};
		jmiAddrBookManager.saveJmiAddrBookPc(jmiAddrBook,function(){window.location.reload();});
				
	
	}
</script>