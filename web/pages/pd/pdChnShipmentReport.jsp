<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfo.title" /></title>
<content tag="heading">
<jecs:locale key="pdSendInfo.heading" />
</content>
<meta name="menu" content="pdSendInfo" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script src="./scripts/calendar/calendar.js"> </script> 
<script src="./scripts/calendar/calendar-setup.js"> </script> 
<script src="./scripts/jquery/jquery-142min.js"> </script> 
<script src="./scripts/calendar/lang.jsp"> </script> 
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/bdPeriodManager.js'/>" ></script>
<script>
	var jq = jQuery.noConflict();
</script>



<form method="post" action="pdChnShipmentReport.html" id="warehouseStockReport">
<div class="searchBar">
		<input type="hidden" name="companyCode" id="companyCode" value="${sessionScope.sessionLogin.companyCode}"/>
	<c:if test="${!sessionScope.sessionLogin.isManager}">
	</c:if>
	<input type="hidden" name="strAction" id="strAction" value=""/>
	<input type="hidden" name="showFlag" id="showFlag" value="show"/>
			<!-- 
			<jecs:label key="saFiIncomeReport.dataSection" />
			 -->
		
   
	</div>
<table class="detail" width="70%">
	<tbody class="window" >
		
		<tr class="edit_tr">
			<td class="command" colspan="4" align="center">
				<button type="button" name="button" value="<jecs:locale key="button.report"/>"  onclick="submitMe('show')" class="btn btn_sele"><jecs:locale key="button.report"/></button>
			</td>
		</tr>
		</tbody>
	</table>	


</form>



<script type="text/javascript">
	function selectWarehouse(str){
     			
     			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
function submitMe(showFlag) {
	waiting();
	jq('#showFlag').val(showFlag);
	jq('#warehouseStockReport').submit();
	waitingEnd();
}
	
function validateOthers(theForm){
		var warehouseNo = $('#warehouseNo').value;
		if((warehouseNo=='')||(warehouseNo==null)){
			alert('<jecs:locale key="busi.warehouse.warehouseno"/><jecs:locale key="isNotNull"/>');
			return false;
		}
}

function changePeriod(){
		
		var year = $('wyear').value;
		var week = $('wweek').value;
		
		if((year!=null)&&(year !='')&&(week!='')&& (week != null)){
					bdPeriodManager.getBdPeriodByWeek(year,week,null,reWriteDate);
			}
		
	}
	function selectWarehouse(str){
     			
     			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
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
		this.location="<c:url value='/pdSendGoodsReports.html'/>";
	}	
</script>