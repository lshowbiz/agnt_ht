<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfo.title" /></title>
<content tag="heading">
<jecs:locale key="pdSendInfo.heading" />
</content>
<meta name="menu" content="pdSendInfo" />


<!-- װ������JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/bdPeriodManager.js'/>" ></script>



<form method="post" action="pdOutWarehouseReports.html" onsubmit="return validateOthers(this)" id="warehouseStockReport">
<table class="detail" width="70%">
	<tbody class="window" >
		
		<tr class="edit_tr">
			<c:if test="${sessionScope.sessionLogin.isManager}">
			<th><span class="text-font-style-tc"><jecs:label  key="sys.company.code"/></span></th>
			<td >
				<span class="textbox">
					<select name="companyCode" class="textbox-text">
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
			<td>
				<span class="textbox">
					<input type="text" name="warehouseNo"  readonly="true" onclick="selectWarehouse('warehouseNo');"  id="warehouseNo" class="textbox-text"/>
					
				</span>
				<button name="select" class="btn btn_sele" onclick="selectWarehouse('warehouseNo');">
					<jecs:locale key="button.select"/>
				</button>
			</td>
		</tr>
		
		<c:if test="${!sessionScope.sessionLogin.isManager}">
			<input type="hidden" name="companyCode" id="companyCode" value="${sessionScope.sessionLogin.companyCode}"/>
		</c:if>
		<input type="hidden" name="strAction" value="${param.strAction}"/>
		<input type="hidden" name="showFlag" value="show"/>
		
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:label key="common.startTime"/></span></th>
			<td>
				<span class="textbox">
				
				<input name="startTime" id="startTime" type="text" value="${param.startTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>				
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime"/></span></th>
			<td>
				<span class="textbox">
					<input name="endTime" id="endTime" type="text" value="${param.endTime }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text"/>
				</span>
			</td>
			
		</tr>
		
		<tr>		
			<td class="command" colspan="4" align="center">
				<button name="button" class="btn btn_sele" onclick="submitMe()">
					<jecs:locale key="button.report"/>
				</button>
			</td>
		</tr>
	</tbody>
</table>




</form>



<script type="text/javascript">
	function selectWarehouse(str){
     			
     			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
     	}
function	submitMe(){
			waiting();
	 		
		  var startTime= $('startTime').value;
			var endTime =$('endTime').value;
			if(((startTime == null)||(startTime == ''))||((endTime == null)||(endTime ==''))){
					/* alert('<jecs:locale key="bdCaculateLog.cretaeTime"/><jecs:locale key="isNotNull"/>');
					waitingEnd();
					return; */
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