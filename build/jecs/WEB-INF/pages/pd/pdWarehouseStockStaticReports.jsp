<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfo.title" /></title>
<content tag="heading">
<jecs:locale key="pdSendInfo.heading" />
</content>
<meta name="menu" content="pdSendInfo" />


<!-- ???????JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/bdPeriodManager.js'/>" ></script>



<form method="post" action="pdWarehouseStockStaticReports.html" onsubmit="return validateOthers(this)" id="warehouseStockReport">
<table class="detail" width="70%">
	<tbody class="window" >
	
		<tr class="edit_tr">
		<c:if test="${sessionScope.sessionLogin.isManager}">
			<th><span class="text-font-style-tc"><jecs:label  key="sys.company.code"/></span></th>
			<td >
				<span class="textbox">
					
				<select name="companyCode" Class="textbox-text">
					  <option value=""></option>
						<c:forEach var="company" items="${companyKeyList}">
								<option value="${company.companyCode}" 
									<c:if test="${param.companyCode == company.companyCode}">
										selected
									</c:if> >${company.companyName}</option>
						</c:forEach>
				</select>
				</span>
			</td>
		</c:if>
			<th><span class="text-font-style-tc"><jecs:label  key="busi.warehouse.warehouseno"/></span></th>
			<td >
				<span class="textbox"><input type="text" name="warehouseNo"  readonly="true" onclick="selectWarehouse2('warehouseNo');"  id="warehouseNo" class="textbox-text"/></span>
				<input type="button" class="btn btn_sele" name="select" onclick="selectWarehouse2('warehouseNo');" value="<jecs:locale key="button.select"/>" />
			</td>
		
		</tr>
		
	<c:if test="${!sessionScope.sessionLogin.isManager}">
		<input type="hidden" name="companyCode" id="companyCode" value="${sessionScope.sessionLogin.companyCode}"/>
	</c:if>
	<input type="hidden" name="strAction" id="strAction" value="${param.strAction}"/>
	<input type="hidden" name="showFlag" id="showFlag" value="show"/>
	
	<tr class="edit_tr">
		
			<th><span class="text-font-style-tc"><jecs:label key="common.startTime"/></span></th>
			<td >
				<span class="textbox">
				<input name="startTime" type="text" id="startTime"  value="${param.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/> 
				</span>
			</td>
			
			<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime"/></span></th>
			<td >
				<span class="textbox">
				<input name="endTime" type="text" id="endTime" value="${param.endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text">
				</span>
			</td>
	</tr>
		<tr>		
			<td class="command" colspan="4" align="center">
				<button type="button" name="button" class="btn btn_sele" value="<jecs:locale key="button.report"/>"  onclick="submitMe('show')" ><jecs:locale key="button.report"/></button>
				<button type="button" name="button" class="btn btn_sele" value="<jecs:locale key="button.report"/>new"  onclick="submitMe('showDetail')" ><jecs:locale key="button.report"/>new</button>
				<button type="button" name="button" class="btn btn_sele" value="<jecs:locale key="button.report"/>fince"  onclick="submitMe('showDetailFince')" ><jecs:locale key="button.report"/>fince</button>
			</td>
		</tr>
	</tbody>
</table>

</form>



<script type="text/javascript">
	function selectWarehouse(str){
     			
     			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=600, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
function	submitMe(showFlag){
			waiting();
	 		$('showFlag').value=showFlag;
		  var startTime= $('startTime').value;
			var endTime =$('endTime').value;
			if(((startTime == null)||(startTime == ''))||((endTime == null)||(endTime ==''))){
					//alert('<jecs:locale key="bdCaculateLog.cretaeTime"/><jecs:locale key="isNotNull"/>');
					if((startTime == null)||(startTime == '')){
					        alert('<jecs:locale key="common.startTime"/><jecs:locale key="messageNotNull"/>');
						   waitingEnd();
						   return;
					}
					if((endTime == null)||(endTime =='')){
					        alert('<jecs:locale key="schedule.endTime"/><jecs:locale key="messageNotNull"/>');
							waitingEnd();
							return;
					}
					waitingEnd();
					return;
				}
				
	
		
				$('warehouseStockReport').submit();
				waitingEnd();
			
	}
	
function validateOthers(theForm){
		var warehouseNo = $('warehouseNo').value;
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
	
    /**Add By WuCF 20130502 **/
    function selectWarehouse2(str){
   		window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse2&elementId="+str,"","height=600, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
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