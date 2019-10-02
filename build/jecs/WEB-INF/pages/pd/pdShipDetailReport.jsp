<%@ include file="/common/taglibs.jsp"%>
<%@ page pageEncoding="UTF-8"%> 
<title><jecs:locale key="pdSendInfo.title" /></title>
<content tag="heading">
<jecs:locale key="pdSendInfo.heading" />
</content>
<meta name="menu" content="pdSendInfo" />


<!-- è£è½½æ¥åJS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type='text/javascript' src='<c:url value="/dwr/interface/pdSendInfoManager.js"/>'></script>

<c:set var="buttons">
	<input type="button" name="button" value="<jecs:locale key="pd.shipdetail"/>"  onclick="exportReport('showShipByWarehouse')" class="button"/>
		<input type="button" name="button" value="<jecs:locale key="pd.shipdetailbystate"/>"  onclick="exportReport('showShipByState')" class="button"/>
</c:set>


<form method="post" action="pdShipDetailReports.html" onsubmit="return validateOthers(this)" id="pdSendInfoReport">
	<div id="titleBar">
	
	
	
	<input type="hidden" name="strAction" id="strAction" value="${param.strAction}"/>
	<input type="hidden" name="reportFlag" id="reportFlag" value=""/>

	</div>
<div class="searchBar">
		<div class="new_searchBar">		
			<jecs:label  key="busi.order.orderno"/>
			<input name="orderNo" id="orderNo" value="<c:out value='${requestParaMap.orderNo}'/>"  cssClass="text medium"/>
		</div>	
		<div class="new_searchBar">	
			<jecs:label key="pd.logisticsDo"/>
			<input name="logisticsDo" id="logisticsDo" value="<c:out value='${requestParaMap.logisticsDo}'/>"  cssClass="text medium"/>
		</div>
		<div class="new_searchBar">	
			<jecs:label  key="pdSendInfo.siNo"/>
			<input name="siNo" id="siNo" value="<c:out value='${requestParaMap.siNo}'/>"  cssClass="text medium"/>
		</div>
		<div class="new_searchBar">	
			<jecs:label key="busi.product.productno" />
			<input name="productNo" id="productNo" value="<c:out value='${requestParaMap.productNo}'/>"  cssClass="text medium"/>	
		</div>
		<div class="new_searchBar">				
			<jecs:label key="busi.common.trackingno"/>
			<input name="trackingNo" id="trackingNo" value="<c:out value='${requestParaMap.trackingNo}'/>"  cssClass="text medium"/>
		</div>
		
		<div class="new_searchBar" >					
			<jecs:label  key="customerRecord.customerNo"/>
			<input type="text" name="customCode" value='${requestParaMap.customCode}'  id="customCode" style="width:60px;"/>
			<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser()" style="cursor:pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
			<input type="text" name="customName"   id="customName" readonly="true" style="width:60px;"/>
		</div>
		<div class="new_searchBar">			
			<jecs:label key="pdOutWarehouse.warehouseNo"/>
			<input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>" style="width:118px;" />
			<button name="select" class="btn btn_sele" onclick="selectWarehouse2('warehouseNo');" >
				<jecs:locale key="button.select"/>
			</button>
			
		</div>
		<div class="new_searchBar">	
			<jecs:label  key="pd.barcode"/>
			<input name="barCode" id="barCode" value="<c:out value='${requestParaMap.barCode}'/>"  cssClass="text medium"/>
		</div>
		<div class="new_searchBar">	
			<jecs:label  key="pd.keyword"/>
			<input name="keyword" id="keyword" value="<c:out value='${requestParaMap.keyword}'/>"  cssClass="text medium"/>
		</div>
		<div class="new_searchBar">			
				
			<jecs:label key="pd.createTime"/>
			 <input name="createTimeStart" id="createTimeStart" type="text" value="${requestParaMap.createTimeStart }" style="cursor: pointer;width:70px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
				- 
			<input name="createTimeEnd" id="createTimeEnd" type="text" value="${requestParaMap.createTimeEnd }" style="cursor: pointer;width:70px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
				
		</div>
		<div class="new_searchBar">	
		<jecs:label key="pd.okTime"/>
			<input name="okTimeStart" id="okTimeStart" type="text" value="${requestParaMap.okTimeStart }" style="cursor: pointer;width:70px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
				- 
			<input name="okTimeEnd" id="okTimeEnd" type="text" value="${requestParaMap.okTimeEnd }" style="cursor: pointer;width:70px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
      	</div>
		<div class="new_searchBar">	
			<jecs:label key="alStateProvince.stateProvinceName"/>
			<select name="recipientCaNo">
				<option value=""></option>
				<c:forEach items="${alStateProvinces}" var="alStateProvince">
					<option value="${alStateProvince.stateProvinceId}" <c:if test="${alStateProvince.stateProvinceId eq requestParaMap.recipientCaNo}">selected</c:if> >
							${alStateProvince.stateProvinceName}
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="new_searchBar">	
			<jecs:label key="pd.shno"/>
			<jecs:list listCode="pd.shno" name="shNo" showBlankLine="true" id="shNo" value="${requestParaMap.shNo}" defaultValue=""/>
		</div>
		<div class="new_searchBar">		
				
			<jecs:label key="pdWarehouseStockTrace.orderType"/>
			<jecs:list listCode="pd.send.type" name="orderType" showBlankLine="true" id="orderType" value="${requestParaMap.orderType}" defaultValue=""/>
		</div>
		<div class="new_searchBar">		
			<jecs:label  key="pdSendInfo.orderFlag"/>
				<jecs:list listCode="pdsendinfo.orderflag" name="orderFlag" id="orderFlag" showBlankLine="true" value="${requestParaMap.orderFlag}" defaultValue=""/>
		</div>

		<c:if test="${sessionScope.sessionLogin.isManager}">
			<div class="new_searchBar">	
				 <jecs:label  key="sys.company.code"/>
				<!--form:errors path="companyCode" cssClass="fieldError"/-->
				<jecs:company name="companyCode" selected="${param.companyCode}"  prompt="" withAA="false"  />
			</div>
		</c:if>
		<div class="new_searchBar">			
				<jecs:label key="po.order_type"/>
				<jecs:list listCode="po.all.order_type" name="subOrderType" showBlankLine="true" id="subOrderType" value="${requestParaMap.subOrderType}" defaultValue=""/>
		</div>
		<div class="new_searchBar">			
				<jecs:label key="jfiPayByCoin.Button"/>
				<jecs:list listCode="yesno" name="codFlag" showBlankLine="true" id="codFlag" value="${requestParaMap.codFlag}" defaultValue=""/>
		</div>
		<div class="new_searchBar">	
			<jecs:label  key="pd.hurry"/>
        	<jecs:list listCode="yesno" name="hurryFlag" showBlankLine="true" id="hurryFlag" value="${requestParaMap.hurryFlag}" defaultValue=""/>
		</div>
		<div class="new_searchBar">	
        	<jecs:label  key="pd.whetherStock"/>
        	<jecs:list listCode="pd.sendinfowhetherstock" name="whetherStock" showBlankLine="true" id="whetherStock" value="${requestParaMap.whetherStock}" defaultValue=""/>
        </div>
		<div class="new_searchBar">		
        	 <jecs:label  key="po.shipment.type"/>
        	<jecs:list listCode="po.shipment.type" name="shipType" showBlankLine="true" id="shipType" value="${requestParaMap.shipType}" defaultValue=""/>
		</div>
		
		<div class="new_searchBar" style="margin-left:45px;">	
		
			<button name="button" class="btn btn_long" onclick="exportReport('showShipByWarehouse')" >
				<jecs:locale key="pd.shipdetail"/>
			</button>	
			<button name="button" class="btn btn_long" onclick="exportReport('showShipByState')" >
				<jecs:locale key="pd.shipdetailbystate"/>
			</button>	
			<button name="button" class="btn btn_long" onclick="exportReport('showShipByWarehouseCsv')" >
				<jecs:locale key="pd.shipdetail"/>CSV
			</button>
		</div>
		<div class="new_searchBar" style="margin-left:45px;">	
			<c:if test="${returnUrl!=null && returnUrl!='' }">
				<c:choose>
					<c:when test="${fn:contains(returnUrl,'http')}">
						<a href="${returnUrl }"><font color="green" style="font-size: 16">点击下载csv文件</font></a>
					</c:when>
					<c:otherwise>
						<font color="green" style="font-size: 16">下载数量超过最大数量：</font><font color="red" style="font-size: 16">${returnUrl }</font><font color="green" style="font-size: 16">，请缩小查询范围！</font>
					</c:otherwise> 
				</c:choose>				
			</c:if>
		</div>
</div>

</form>



<script type="text/javascript">
	
function exportReport(flag){
		$('reportFlag').value=flag;
		//$('pdSendInfoReport').submit();
	}
function	submitMe1(){
			$('strAction').value="showPdSendInfoReport";
			waiting();
			//$('reportFlag').value="showSendListReport";
	 		//DWREngine.setAsync(false);
		  var startTime= $('checkTimeStart').value;
			var endTime =$('checkTimeEnd').value;
		//	if(((startTime == null)||(startTime == ''))&&((endTime == null)||(endTime ==''))){
		//			changePeriod();
		//		}
		//	
			
		//	DWREngine.setAsync(true);
			if(((startTime == null)||(startTime == ''))&&((endTime == null)||(endTime ==''))){
				alert('<jecs:locale key="saFiIncomeReport.dataSection"/><jecs:locale key="isNotNull"/>');
				waitingEnd();
				return;
			}
			$('pdSendInfoReport').submit();
			waitingEnd();
	}
	function submitMe4(){
			waiting();
			$('reportFlag').value="shippingReportByUserCode";
			$('userCode').value="US0010";
	 		DWREngine.setAsync(false);
	 		var startTime= $('startTime').value;
			var endTime =$('endTime').value;
			if(((startTime == null)||(startTime == ''))&&((endTime == null)||(endTime ==''))){
				alert('<jecs:locale key="saFiIncomeReport.dataSection"/><jecs:locale key="isNotNull"/>');
				waitingEnd();
				return;
			}
			

			$('pdSendInfoReport').submit();
			waitingEnd();
		}
	function	submitMe2(){
			waiting();
			$('strAction').value="showPdSendInfoDetailReport";
	 		var startTime= $('checkTimeStart').value;
			var endTime =$('checkTimeEnd').value;
			if(((startTime == null)||(startTime == ''))&&((endTime == null)||(endTime ==''))){
				alert('<jecs:locale key="saFiIncomeReport.dataSection"/><jecs:locale key="isNotNull"/>');
				waitingEnd();
				return;
			}
			
			$('pdSendInfoReport').submit();
			waitingEnd();
	}
	function submitMe3(){
			waiting();
			$('reportFlag').value="showWillcallReport";
	 		DWREngine.setAsync(false);
		  var startTime= $('checkTimeStart').value;
			var endTime =$('checkTimeEnd').value;
			if(((startTime == null)||(startTime == ''))&&((endTime == null)||(endTime ==''))){
					changePeriod();
				}
			
			
			DWREngine.setAsync(true);
			if(((startTime == null)||(startTime == ''))&&((endTime == null)||(endTime ==''))){
				alert('<jecs:locale key="saFiIncomeReport.dataSection"/><jecs:locale key="isNotNull"/>');
				waitingEnd();
				return;
			}
			$('pdSendInfoReport').submit();
			//waitingEnd();
	}
function validateOthers(theForm){
	
}

function changePeriod(){
		
		var year = $('wyear').value;
		var week = $('wweek').value;
		if((year!=null)&&(year !='')&&(week!='')&& (week != null)){
					bdPeriodManager.getBdPeriodByWeek(year,week,null,reWriteDate);
			}
		
	}
	
	function reWriteDate(ret){
			 
			var bp = ret;
			$('startTime').value=bp.startTime;
			$('endTime').value=bp.endTime;
		}
function createReport(){
		$('strAction').value="pdSendGoodsReport";
		this.submit();
	}

function toBack(){
		this.location="<c:url value='/pdSendInfoReport.html'/>";
	}	
	
	/**Add By WuCF 20130502 **/
    function selectWarehouse2(str){
   		window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse2&elementId="+str,"","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
   	}
	
    function exportReportCsv(siNo){
		var str = pdSendInfoManager.exportReportCsv(siNo,validatePdShowShipByWarehouse);
	}
    
    function validatePdShowShipByWarehouse(ret){
    	alert(ret);
		/*if(ret=="succ"){
			alert("<jecs:locale key="pd.update.hurry.success"/>");
			document.getElementById('hurryFlag').value='1';
		}*/
	}
</script>