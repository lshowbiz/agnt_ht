<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfo.title" /></title>
<content tag="heading">
<jecs:locale key="pdSendInfo.heading" />
</content>
<meta name="menu" content="pdSendInfo" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 




<form method="post" action="pdSendInfoReports.html" onsubmit="return validateOthers(this)" id="pdSendInfoReport">
	<div id="titleBar">
	
	
	
	<input type="hidden" name="strAction" id="strAction" value="showPdSendInfoReport"/>
	<input type="hidden" name="orderFlag" id="orderFlag" value="1"/>

	</div>
<div class="searchBar">
	
		
		
		<c:if test="${sessionScope.sessionLogin.companyCode == 'AA'}">
    
        <jecs:label  key="sys.company.code"/>
    
    
        <!--form:errors path="companyCode" cssClass="fieldError"/-->
        
        <jecs:company name="companyCode" selected="${param.companyCode}"  prompt="" withAA="false"  />
    
	</c:if>
<!-- 
<c:if test="${sessionScope.sessionLogin.companyCode == 'CN'}">
<jecs:list listCode="pd.exportflag" name="exportFlag" id="exportFlag" showBlankLine="false" value="${requestParaMap.exportFlag}" defaultValue="0"/>
</c:if>
 -->	
       <c:if test="${sessionScope.sessionLogin.companyCode == 'CN'}">
		   <div class="new_searchBar">
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <jecs:list listCode="pd.exportflag" name="exportFlag" id="exportFlag" showBlankLine="false" value="${requestParaMap.exportFlag}" defaultValue="0"/>
           </div>
       </c:if>
       
      <div class="new_searchBar" style="white-space:nowrap;">
        <jecs:title  key="pdSendInfo.sendWarehouseNo"/>
    
   
        <!--form:errors path="sendWarehouseNo" cssClass="fieldError"/-->
        <input name="warehouseNo"  onclick="selectWarehouse2('warehouseNo');" readonly="readonly" id="warehouseNo"  cssClass="text medium"/>
    	<!-- <input type="button" class="button" name="select" onclick="selectWarehouse2('warehouseNo');" value="<jecs:locale key="button.select"/>" /> -->
        
        <button type="button" class="btn btn_sele"  style="width:auto" name="select" onclick="selectWarehouse2('warehouseNo');">
			 <jecs:locale key="button.select"/>
		</button>
      
      </div>
    <div class="new_searchBar">
			<jecs:title key="saFiIncomeReport.dataSection" />
			 <input name="checkTimeStart" id="checkTimeStart" type="text" value="${param.checkTimeStart }" style="cursor: pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            - 
	            <input name="checkTimeEnd" id="checkTimeEnd" type="text" value="${param.checkTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	</div>				
			<%-- <input name="checkTimeStart" type="text" id="checkTimeStart" size="16" value="${param.checkTimeStart}" readonly="readonly" class="readonly"/> 
			<img src="./images/calendar/calendar7.gif" id="img_startDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "checkTimeStart", 
				ifFormat       :    "%Y-%m-%d",  
				button         :    "img_startDate", 
				singleClick    :    true
				}); 
			</script>
			- 
			<input name="checkTimeEnd" type="text" id="checkTimeEnd" size="16" value="${param.checkTimeEnd}" readonly="readonly" class="readonly"/>
			<img src="./images/calendar/calendar7.gif" id="img_endDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
			<script type="text/javascript"> 
				Calendar.setup({
				inputField     :    "checkTimeEnd", 
				ifFormat       :    "%Y-%m-%d",  
				button         :    "img_endDate", 
				singleClick    :    true
				}); 
			</script> --%>
		
		<div class="new_searchBar">
		           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		          <button type="button" class="btn btn_long"   style="width:auto" onclick="submitMe1('showPdSendInfoReport')">
				        <jecs:locale key="button.report"/>
				  </button>
		         <button type="button" class="btn btn_long"   style="width:auto" onclick="submitMe2()">
				        List<jecs:locale key="button.report"/>
				 </button>
		</div>
	
	<%-- <input type="button" name="button" value="<jecs:locale key="button.report"/>"  onclick="submitMe1('showPdSendInfoReport')" class="button"/>
		<input type="button" name="button" value="List<jecs:locale key="button.report"/>"  onclick="submitMe2()" class="button"/> --%>
	<!-- <input type="button" name="button" value="<jecs:locale key="button.report.mobile"/>"  onclick="submitMe1('mNewsPaper')" class="button"/> 
	<input name="export2" class="button" type="button" onclick="settime3(this,3);exportOrderAndInfo(3);" value="<jecs:locale key="button.report"/>_new" />
	<input name="export3" class="button" type="button" onclick="settime4(this,4);exportOrderAndInfo(4);" value="List<jecs:locale key="button.report"/>_new" />
-->
	<c:if test="${sessionScope.sessionLogin.companyCode =='TW'}">
	<div class="new_searchBar">
	       <button type="button" class="btn btn_long"   style="width:auto" onclick="submitMe1('exportEDI')">
				  <jecs:locale key="button.exportedi"/>
		   </button>
	</div>
		<!--  <input type="button" name="button" value="<jecs:locale key="button.exportedi"/>"  onclick="submitMe1('exportEDI')" class="button"/>-->
	</c:if>
</div>

</form>



<script type="text/javascript">
	
function	submitMe1(strAction){
	var warehouseNo = document.getElementById('warehouseNo');
	if(warehouseNo.value==null || warehouseNo.value==''){
		alert('<jecs:locale key="pdSendInfo.sendWarehouseNo"/><jecs:locale key="operation.notice.js.bankKey.notNull"/>');
		return false;
	}
	
			$('strAction').value=strAction;
			waiting();
		  var startTime= $('checkTimeStart').value;
			var endTime =$('checkTimeEnd').value;
			if(((startTime == null)||(startTime == ''))&&((endTime == null)||(endTime ==''))){
				alert('<jecs:locale key="saFiIncomeReport.dataSection"/><jecs:locale key="isNotNull"/>');
				waitingEnd();
				return;
			}
			if(strAction == 'mNewsPaper'){
				$('orderFlag').value='';
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
			var warehouseNo = document.getElementById('warehouseNo');
			if(warehouseNo.value==null || warehouseNo.value==''){
				alert('<jecs:locale key="pdSendInfo.sendWarehouseNo"/><jecs:locale key="operation.notice.js.bankKey.notNull"/>');
				return false;
			}
		
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
		window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse2&elementId="+str,"","height=600, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	}

	/**order&info xls**/
	function exportOrderAndInfo(type){
		//waiting();
		var memberOrderNo = document.getElementsByName("memberOrderNo")[0].value;
		var userCode = document.getElementsByName("sysUser.userCode")[0].value;
		var logType = document.getElementsByName("logType")[0].value;
		var startLogTime = document.getElementsByName("startLogTime")[0].value;
		var endLogTime = document.getElementsByName("endLogTime")[0].value;
		var orderType = document.getElementsByName("orderType")[0].value;
		var status = document.getElementsByName("status")[0].value;
		var isRetreatOrder = document.getElementsByName("isRetreatOrder")[0].value;
		var province = document.getElementsByName("province")[0].value;
		var city = document.getElementsByName("city")[0].value;

		var district = document.getElementsByName("district")[0].value;
		var productType = document.getElementsByName("productType")[0].value;
		var payByCoin = document.getElementsByName("payByCoin")[0].value;
		var payByJJ = document.getElementsByName("payByJJ")[0].value;
		var memberType = document.getElementsByName("team")[0].value;
		var userCodeV = document.getElementsByName("userCodeV")[0].value;
		alert("<jecs:locale key='pdexportlog.info'/>");
		//waitingEnd();
		document.getElementById("downloadId").href='${pageContext.request.contextPath}/pdShipFees.html?strAction=pdExportLog&logType='+type;
		var str = pdWarehouseStockManager.exportOrderAndInfo(memberOrderNo,userCode,logType,startLogTime,endLogTime,
															orderType,status,isRetreatOrder,province,city,
															district,productType,payByCoin,payByJJ,memberType,userCodeV,type,
															validateExportOrderAndInfo);
	}
	
	function validateExportOrderAndInfo(ret){
		//alert(ret);
		/**if(ret!=null && ret!=''){
			var url = '<a href=\'${pageContext.request.contextPath}/pdShipFees.html?strAction=pdExportLog\' target=\'blank\'><jecs:locale key="button.download"/></a>';
			document.getElementById('exportOrderAndInfoId').innerHTML=url;
			//waitingEnd();
		}**/
	}
</script>
<script>
var numw = 5; 
var numw2 = 5; 
function settime3(val,type) { 
	if (numw == 0) { 
		//val.removeAttribute("disabled"); 
		val.value='<jecs:locale key="toolbar.order.xls"/>_new'; 
		numw = 0; 
	} else { 
		val.setAttribute("disabled", true); 
		val.value='<jecs:locale key="pdexportlog.tishi"/>(' + numw + ")"; 
		numw--; 
	} 
	setTimeout(function() { 
		if(numw>=0){
			settime3(val) 
		}
	},1000)
}

function settime4(val,type) { 
	if (numw2 == 0) { 
		//val.removeAttribute("disabled"); 
		val.value='<jecs:locale key="toolbar.text.xls"/>_new'; 
		numw2 = 0; 
	} else { 
		val.setAttribute("disabled", true); 
		val.value='<jecs:locale key="pdexportlog.tishi"/>(' + numw2 + ")"; 
		numw2--; 
	} 
	setTimeout(function() { 
		if(numw2>=0){
			settime4(val) 
		}
	},1000)
} 
</script>