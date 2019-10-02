<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="aiAgent.title" /></title>
<content tag="heading">
<jecs:locale key="aiAgent.heading" />
</content>
<meta name="menu" content="AiAgentMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form name="formAiFeat" method="post" action="../bdOrderStatisticPrintB.html" >
	<table class='detail' width="70%">
<tbody class="window" >
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<tr class="edit_tr">
	
			<th><span class="text-font-style-tc"><jecs:locale key="bdReconsumMoneyReport.company" />:</span></th>

				
		<td>
			<span class="textbox"><jecs:company  name="companyCode" prompt="" withAA='false' styleClass="textbox-text"/></span>
		</td>
		</c:if>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.wweek" />:</span></th>
			<td>
				<span class="textbox"><input type="text" name="wWeek" value="" class="textbox-text"/></span> (<jecs:locale key="label.example"/>:200801)
				<button type="submit" name="submit" onclick="javascript:this.form.action='../bdOrderStatisticPrintB.html?strAction=bdOrderStatisticWeekB'" class="btn btn_sele"><jecs:locale key="operation.button.create"/></button>
			</td>
			<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.wmonth" />:</span></th>
			<td>
				<span class="textbox"><input type="text" name="wMonth" value="" class="textbox-text"/></span>(<jecs:locale key="label.example"/>:200801)
				<button type="submit" name="submit" onclick="javascript:this.form.action='../bdOrderStatisticPrintB.html?strAction=bdOrderStatisticMonthB'" class="btn btn_sele"><jecs:locale key="operation.button.create"/></button>
			</td>
			
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="common.startTime"/></span></th>
			<td>
				<span class="textbox">
				<input id="createBTime" name="createBTime" type="text" value="${param.createBTime }" class="textbox-text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</span>
				
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime"/></span></th>
			<td>
				<span class="textbox"><input id="createETime" name="createETime" type="text" size="10" maxlength="10" value="${param.createETime }" class="textbox-text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></span>
				<button type="submit" name="submit" onclick="javascript:this.form.action='../bdOrderStatisticPrintB.html?strAction=bdOrderStatisticDateB'" class="btn btn_sele"><jecs:locale key="operation.button.create"/></button>
			</td>
		</tr>
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:locale key="jpoMemberOrder.productType"/>:</span></th>
			<td>
				<span class="textbox">
					<select name="productType" id="productType" class="textbox-text">
						<jecs:power powerCode="jpoMemberOrder.productType.all.b">
						  <option value="ALL"><jecs:locale key="jpoMemberOrder.productType.all"/></option>
						</jecs:power>
						<jecs:power powerCode="jpoMemberOrder.productType.joymain.b">
						  <option value=""><jecs:locale key="jpoMemberOrder.productType.joymain"/></option>
						</jecs:power>
						<jecs:power powerCode="jpoMemberOrder.productType.LC.b">
						  <option value="LC"><jecs:locale key="jpoMemberOrder.productType.LC"/></option>
						</jecs:power>
					</select>
				</span>
			</td>
			
		</tr>
	</table>
</form>