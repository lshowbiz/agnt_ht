<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
 
<%@ taglib uri="http://acegisecurity.org/authz" prefix="authz" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://struts-menu.sf.net/tag-el" prefix="menu" %>

<%@ taglib uri="/WEB-INF/core.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/fmt-rt.tld" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/sql.tld" prefix="sql" %>
<%@ taglib uri="/WEB-INF/t.tld"   prefix="t" %>
<%@ taglib uri="/WEB-INF/fn.tld"   prefix="fn" %>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec" %>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="/WEB-INF/tld/jecs.tld" prefix="jecs" %>
<%@ taglib prefix="elf" uri="/WEB-INF/elfunc.tld"%> 

<link rel="stylesheet" href="./scripts/jpmsalesanalysis/calendar-blue.css" />
<link rel="stylesheet" href="./scripts/jpmsalesanalysis/layout.css" />
<script src="./scripts/jpmsalesanalysis/jquery.min.js"></script> 
<script src="./scripts/jpmsalesanalysis/highcharts.src.js"></script> 
<script src="./scripts/jpmsalesanalysis/exporting.src.js"></script>
<script src="./scripts/jpmsalesanalysis/calendar.js"></script>
<script src="./scripts/jpmsalesanalysis/calendar-setup.js"></script> 
<script src="./scripts/calendar/lang.jsp"> </script>   
 
<script language="javascript">
 function loading(){
	/**waiting();**/
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<jecs:locale key="button.loading"/>';
	document.getElementById("container1").innerHTML=str;
} 
</script> 
<form name="frm" id="frm" action="<c:url value='/jpmSalesAnalysis.html'/>">
	<input type="hidden" name="strAction"  value="${strAction }" /> 
	<input type="hidden" name="search"  value="Y" /> 
	<div>
		<c:choose>
			<c:when
				test="${strAction=='userArea' }">
				<!-- 1.usreArea -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.userArea" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>										
										<jecs:title key='jpmSalesAnalysis.starttime' />
									</td>
									<td>
										<input id="startLogTime" name="startLogTime" type="text"
											size="10" maxlength="10" value="${startLogTime=='null'?'':startLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_startOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
				                        Calendar.setup({
				                            inputField     :    "startLogTime",
				                            ifFormat       :    "%Y-%m-%d",
				                            button         :    "img_startOperatorTime",
				                            singleClick    :    true
				                            });
				                        </script>
										-
									</td>
									<td>
										<input id="endLogTime" name="endLogTime" type="text" size="10"
											maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_endOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
				                        Calendar.setup({
					                        inputField     :    "endLogTime",
					                        ifFormat       :    "%Y-%m-%d",
					                        button         :    "img_endOperatorTime",
					                        singleClick    :    true
				                        });
				                        </script>
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>

				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>

								</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">
					<script language="javascript">
						 var chart;
						$(function() {
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container1',
									defaultSeriesType: 'bar',
									height:'${fn:length(list)*30+150}'
								},
								title: {
									text: '会员地域分布'
								},
								xAxis: {
									categories: [
	
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            '<a href="jpmSalesAnalysis.html?search=Y&strAction=userArea&userCode=${param.userCode}&province=${resultVar.PROVINCE}&startLogTime=${startLogTime}&endLogTime=${endLogTime}">${resultVar.NAME }</a>',
									</c:forEach>
	
									],
									title: {
										text: null
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: '人',
										align: 'high'
									}
								},
								tooltip: {
									formatter: function() {
										return ''+
											 this.y +' 人';
									}
								},
								plotOptions: {
									bar: {
										dataLabels: {
											enabled: true
										}
									}
								},
								legend: {
									layout: 'vertical',
									align: 'right',
									verticalAlign: 'top',
									x: -100,
									y: 100,
									floating: true,
									borderWidth: 1,
									shadow: true
								},
								credits: {
									enabled: false
								},
							        series: [{
									name: '会员地域分析',
									data: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            ${resultVar.SUM },
									</c:forEach>
									]
								}]
							});
							
							
						});
					</script>
				</c:if>
			</c:when>

			<c:when test="${strAction=='memberActivity'}">
				<!-- 2.memberActivity -->
				<c:choose>
					<c:when test="${saleFlag2=='logCount'}">
						<h2 class="title mgb20">
							<jecs:title key='jpmSalesAnalysis.login.num' />
						</h2>
						<div class="condition">
							
								<table class="personalInfoTab">
									<tbody>
										<tr>
											<td>
												<input type="hidden" name="saleFlag" value="${saleFlag }" />
												<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
												<jecs:title key="jpmSalesAnalysis.time" />:
											</td>
											<td>
												<input id="startLogTime" name="startLogTime" type="text"
													size="10" maxlength="10" value="${startLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_startOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                            inputField     :    "startLogTime",
		                            ifFormat       :    "%Y-%m-%d",
		                            button         :    "img_startOperatorTime",
		                            singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>
												<input id="endLogTime" name="endLogTime" type="text" size="10"
													maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_endOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                        inputField     :    "endLogTime",
		                        ifFormat       :    "%Y-%m-%d",
		                        button         :    "img_endOperatorTime",
		                        singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>										
												<jecs:title key='label.member.or.agent.code' />
											</td>
											<td>
												<input id="userCode" name="userCode" type="text" size="10"
													maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
											</td>
											<td>
												<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
													class="button" />
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
		
		
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td align="center" valign="top">
									<div class="table_div">
										<div class="table_LongTable">
											<div id="container1"></div>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<c:if test="${search=='Y'}">
							<script language="javascript">
							 var chart;
							$(function() {
								chart = new Highcharts.Chart({
									chart: {
										renderTo: 'container1',
										defaultSeriesType: 'column'
									},
									title: {
										text: '成员活跃度分析--登录次数'
									},
									xAxis: {
										categories: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            '${resultVar.NAME }',
										</c:forEach>
										],
										title: {
											text: null
										},
										max:7,
										labels:{
											style:{
												font:'normal 11px Verdana, sans-serif'
											}
										}
									},
									yAxis: {
										min: 0,
										title: {
											text: '次',
											align: 'high'
										}
									},
									tooltip: {
										formatter: function() {
											return ''+
												 this.y +' 次';
										}
									},
									plotOptions: {
										bar: {
											dataLabels: {
												enabled: true
											}
										}
									},
									legend: {
										layout: 'vertical',
										align: 'right',
										verticalAlign: 'top',
										x: -100,
										y: 100,
										floating: true,
										borderWidth: 1,
										shadow: true
									},
									credits: {
										enabled: false
									},
								        series: [{
										name: '<fmt:message key="成员活跃度分析--登录次数"/>',
										data: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            ${resultVar.SUM },
										</c:forEach>
										]
									}]
								});
								
								
							});
							</script>
						</c:if>
					</c:when>
					<c:when test="${saleFlag2=='orderType'}">
						<h2 class="title mgb20">
							<jecs:title key="jpmSalesAnalysis.order.type" />
						</h2>
						<div class="condition">
							
								<table class="personalInfoTab">
									<tbody>
										<tr>
											<td>
												<input type="hidden" name="saleFlag" value="${saleFlag }" />
												<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
												<jecs:title key="jpmSalesAnalysis.time" />:
											</td>
											<td>
												<input id="startLogTime" name="startLogTime" type="text"
													size="10" maxlength="10" value="${startLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_startOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                            inputField     :    "startLogTime",
		                            ifFormat       :    "%Y-%m-%d",
		                            button         :    "img_startOperatorTime",
		                            singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>
												<input id="endLogTime" name="endLogTime" type="text" size="10"
													maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_endOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                        inputField     :    "endLogTime",
		                        ifFormat       :    "%Y-%m-%d",
		                        button         :    "img_endOperatorTime",
		                        singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>										
												<jecs:title key='label.member.or.agent.code' />
											</td>
											<td>
												<input id="userCode" name="userCode" type="text" size="10"
													maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
											</td>
											<td>
												<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
													class="button" />
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
		
		
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td align="center" valign="top">
									<div class="table_div">
										<div class="table_LongTable">
											<div id="container1"></div>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<c:if test="${search=='Y'}">
							<script language="javascript">
							var chart;
							$(function() {
								chart = new Highcharts.Chart({
									chart: {
										renderTo: 'container1',
										defaultSeriesType: 'column'
									},
									title: {
										text: '成员活跃度分析--订单类型'
									},
									xAxis: {
										categories: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus"> 
								            '<jecs:code listCode="po.all.order_type" value="${resultVar.NAME}"/>',
										</c:forEach>
										],
										title: {
											text: null
										},
										max:7,
										labels:{
											style:{
												font:'normal 11px Verdana, sans-serif'
											}
										}
									},
									yAxis: {
										min: 0,
										title: {
											text: '个',
											align: 'high'
										}
									},
									tooltip: {
										formatter: function() {
											return ''+
												 this.y +' 个';
										}
									},
									plotOptions: {
										bar: {
											dataLabels: {
												enabled: true
											}
										}
									},
									legend: {
										layout: 'vertical',
										align: 'right',
										verticalAlign: 'top',
										x: -100,
										y: 100,
										floating: true,
										borderWidth: 1,
										shadow: true
									},
									credits: {
										enabled: false
									},
								        series: [{
										name: '<fmt:message key="成员活跃度分析--订单类型"/>',
										data: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            ${resultVar.SUM },
										</c:forEach>
										]
									}]
								});
								
								
							});
							</script>
						</c:if>
					</c:when>
					<c:when test="${saleFlag2=='orderCount'}">
						<h2 class="title mgb20">
							<jecs:title key="jpmSalesAnalysis.order.num" />
						</h2>
						<div class="condition">
							
								<table class="personalInfoTab">
									<tbody>
										<tr>
											<td>
												<input type="hidden" name="saleFlag" value="${saleFlag }" />
												<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
												<jecs:title key="jpmSalesAnalysis.time" />:
											</td>
											<td>
												<input id="startLogTime" name="startLogTime" type="text"
													size="10" maxlength="10" value="${startLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_startOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                            inputField     :    "startLogTime",
		                            ifFormat       :    "%Y-%m-%d",
		                            button         :    "img_startOperatorTime",
		                            singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>
												<input id="endLogTime" name="endLogTime" type="text" size="10"
													maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_endOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                        inputField     :    "endLogTime",
		                        ifFormat       :    "%Y-%m-%d",
		                        button         :    "img_endOperatorTime",
		                        singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>										
												<jecs:title key='label.member.or.agent.code' />
											</td>
											<td>
												<input id="userCode" name="userCode" type="text" size="10"
													maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
											</td>
											<td>
												<input type="submit" name="submit" onclick='loading();' value='<jecs:locale key="operation.button.search"/>'
													class="button" />
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
		
		
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td align="center" valign="top">
									<div class="table_div">
										<div class="table_LongTable">
											<div id="container1"></div>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<c:if test="${search=='Y'}">
							<script language="javascript">
							 var chart;
							$(function() {
								chart = new Highcharts.Chart({
									chart: {
										renderTo: 'container1',
										defaultSeriesType: 'column'
									},
									title: {
										text: '成员活跃度分析--订单数量'
									},
									xAxis: {
										categories: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            '${resultVar.NAME }',
										</c:forEach>
										],
										title: {
											text: null
										},
										max:7,
										labels:{
											style:{
												font:'normal 11px Verdana, sans-serif'
											}
										}
									},
									yAxis: {
										min: 0,
										title: {
											text: '个',
											align: 'high'
										}
									},
									tooltip: {
										formatter: function() {
											return ''+
												 this.y +' 个';
										}
									},
									plotOptions: {
										bar: {
											dataLabels: {
												enabled: true
											}
										}
									},
									legend: {
										layout: 'vertical',
										align: 'right',
										verticalAlign: 'top',
										x: -100,
										y: 100,
										floating: true,
										borderWidth: 1,
										shadow: true
									},
									credits: {
										enabled: false
									},
								        series: [{
										name: '<fmt:message key="成员活跃度分析--订单数量"/>',
										data: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            ${resultVar.SUM },
										</c:forEach>
										]
									}]
								});
								
								
							});
							</script>
						</c:if>
					</c:when>
					<c:when test="${saleFlag2=='orderMoney'}">
						<h2 class="title mgb20">
							<jecs:title key="jpmSalesAnalysis.order.money" />
						</h2>
						<div class="condition">
							
								<table class="personalInfoTab">
									<tbody>
										<tr>
											<td>
												<input type="hidden" name="saleFlag" value="${saleFlag }" />
												<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
												<jecs:title key="jpmSalesAnalysis.time" />:
											</td>
											<td>
												<input id="startLogTime" name="startLogTime" type="text"
													size="10" maxlength="10" value="${startLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_startOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                            inputField     :    "startLogTime",
		                            ifFormat       :    "%Y-%m-%d",
		                            button         :    "img_startOperatorTime",
		                            singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>
												<input id="endLogTime" name="endLogTime" type="text" size="10"
													maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_endOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                        inputField     :    "endLogTime",
		                        ifFormat       :    "%Y-%m-%d",
		                        button         :    "img_endOperatorTime",
		                        singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>										
												<jecs:title key='label.member.or.agent.code' />
											</td>
											<td>
												<input id="userCode" name="userCode" type="text" size="10"
													maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
											</td>
											<td>
												<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
													class="button" />
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
		
		
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td align="center" valign="top">
									<div class="table_div">
										<div class="table_LongTable">
											<div id="container1"></div>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<c:if test="${search=='Y'}">
							<script language="javascript">
							 var chart;
							$(function(){
								chart = new Highcharts.Chart({
									chart: {
										renderTo: 'container1',
										defaultSeriesType: 'column'
									},
									title: {
										text: '成员活跃度分析--订单金额'
									},
									xAxis: {
										categories: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            '${resultVar.NAME }',
										</c:forEach>
										],
										title: {
											text: null
										},
										max:7,
										labels:{
											style:{
												font:'normal 11px Verdana, sans-serif'
											}
										}
									},
									yAxis: {
										min: 0,
										title: {
											text: 'RMB',
											align: 'high'
										}
									},
									tooltip: {
										formatter: function() {
											return ''+
												 this.y +' RMB';
										}
									},
									plotOptions: {
										bar: {
											dataLabels: {
												enabled: true
											}
										}
									},
									legend: {
										layout: 'vertical',
										align: 'right',
										verticalAlign: 'top',
										x: -100,
										y: 100,
										floating: true,
										borderWidth: 1,
										shadow: true
									},
									credits: {
										enabled: false
									},
								        series: [{
										name: '<fmt:message key="成员活跃度分析--订单金额"/>',
										data: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            ${resultVar.SUM },
										</c:forEach>
										]
									}]
								});
								
								
							});
							</script>
						</c:if>
					</c:when>
					<c:when test="${saleFlag2=='orderPv'}">
						<h2 class="title mgb20">
							<jecs:title key="jpmSalesAnalysis.order.pv" />
						</h2>
						<div class="condition">
							
								<table class="personalInfoTab">
									<tbody>
										<tr>
											<td>
												<input type="hidden" name="saleFlag" value="${saleFlag }" />
												<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
												<jecs:title key="jpmSalesAnalysis.time" />:
											</td>
											<td>
												<input id="startLogTime" name="startLogTime" type="text"
													size="10" maxlength="10" value="${startLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_startOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                            inputField     :    "startLogTime",
		                            ifFormat       :    "%Y-%m-%d",
		                            button         :    "img_startOperatorTime",
		                            singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>
												<input id="endLogTime" name="endLogTime" type="text" size="10"
													maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_endOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
		                        Calendar.setup({
		                        inputField     :    "endLogTime",
		                        ifFormat       :    "%Y-%m-%d",
		                        button         :    "img_endOperatorTime",
		                        singleClick    :    true
		                        });
		                        </script>
											</td>
											<td>										
												<jecs:title key='label.member.or.agent.code' />
											</td>
											<td>
												<input id="userCode" name="userCode" type="text" size="10"
													maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
											</td>
											<td>
												<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
													class="button" />
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
		
		
						<table width="100%" border="0" cellspacing="5" cellpadding="0">
							<tr>
								<td align="center" valign="top">
									<div class="table_div">
										<div class="table_LongTable">
											<div id="container1"></div>
										</div>
									</div>
								</td>
							</tr>
						</table>
						<c:if test="${search=='Y'}">
							<script language="javascript">
							 var chart;
							$(function(){
								chart = new Highcharts.Chart({
									chart: {
										renderTo: 'container1',
										defaultSeriesType: 'column'
									},
									title: {
										text: '成员活跃度分析--订单PV'
									},
									xAxis: {
										categories: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            '${resultVar.NAME }',
										</c:forEach>
										],
										title: {
											text: null
										},
										max:7,
										labels:{
											style:{
												font:'normal 11px Verdana, sans-serif'
											}
										}
									},
									yAxis: {
										min: 0,
										title: {
											text: 'PV',
											align: 'high'
										}
									},
									tooltip: {
										formatter: function() {
											return ''+
												 this.y +' PV';
										}
									},
									plotOptions: {
										bar: {
											dataLabels: {
												enabled: true
											}
										}
									},
									legend: {
										layout: 'vertical',
										align: 'right',
										verticalAlign: 'top',
										x: -100,
										y: 100,
										floating: true,
										borderWidth: 1,
										shadow: true
									},
									credits: {
										enabled: false
									},
									series: [{
										name: '<fmt:message key="成员活跃度分析--订单PV"/>',
										data: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            ${resultVar.SUM },
										</c:forEach>
										]
									}]
								});
								
								
							});
							</script>
						</c:if>
					</c:when>
				</c:choose>				
			</c:when>

			<c:when test="${strAction=='teamAdd'}">
				<!-- 3.<jecs:title key="jpmSalesAnalysis.teamAdd" />d -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.teamAdd" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>
										<input type="hidden" name="saleFlag"
											value="${saleFlag }" />
										<jecs:title key='jpmSalesAnalysis.starttime' />:
									</td>
									<td>
										<select name="year" id="year" class="mySelect">
											<option value=""></option>
											<c:forEach items="${yearList }" var="yearVar"
												varStatus="yearListStatus">
												<c:if test='${param.year==yearVar }'>
													<option value="${yearVar }" selected>
														${yearVar }
													</option>
												</c:if>
												<c:if test='${param.year!=yearVar }'>
													<option value="${yearVar }">
														${yearVar }
													</option>
												</c:if>
											</c:forEach>
										</select>
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>										
										<jecs:title key='member.memberType' />
									</td>
									<td>
										<jecs:list listCode="isstore" name="userType" id="userType" showBlankLine="true" 
										value="${param.userType}" defaultValue=""/>
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>



				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">
					<script language="javascript">
					 var chart;
						$(function(){
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container1',
									defaultSeriesType: 'column'
								},
								title: {
									text: '团队新增会员'
								},
								xAxis: {
									categories: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										'${resultVar.NAME }',
									</c:forEach>
									],
									title: {
										text: null
									},
									labels:{
										style:{
											font:'normal 11px Verdana, sans-serif'
										}
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: '人',
										align: 'high'
									}
								},
								tooltip: {
									formatter: function() {
										return ''+
											 this.y +' 人';
									}
								},
								plotOptions: {
									bar: {
										dataLabels: {
											enabled: true
										}
									}
								},
								legend: {
									layout: 'vertical',
									align: 'right',
									verticalAlign: 'top',
									x: -100,
									y: 100,
									floating: true,
									borderWidth: 1,
									shadow: true
								},
								credits: {
									enabled: false
								},
									series: [{
									name: '团队新增会员',
									data: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										${resultVar.SUM },
									</c:forEach>
									]
								}]
							});
							
							
						});
					</script>
				</c:if>
			</c:when>

			<c:when test="${strAction=='mom'}">
				<!-- 4.mom -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.mom" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>
										<input type="hidden" name="saleFlag"
											value="${saleFlag }" />
										<input type="hidden" name="selectFlag" value="y" />
										<jecs:title key="jpmSalesAnalysis.order.time" />
									</td>
									<td>
										<input id="startLogTime" name="startLogTime" type="text"
											size="10" maxlength="10" value="${startLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_startOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
				                        Calendar.setup({
				                            inputField     :    "startLogTime",
				                            ifFormat       :    "%Y-%m-%d",
				                            button         :    "img_startOperatorTime",
				                            singleClick    :    true
				                            });
				                        </script>
										-
									</td>
									<td>
										<input id="endLogTime" name="endLogTime" type="text" size="10"
											maxlength="10" value="${endLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_endOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
				                        Calendar.setup({
				                        inputField     :    "endLogTime",
				                        ifFormat       :    "%Y-%m-%d",
				                        button         :    "img_endOperatorTime",
				                        singleClick    :    true
				                        });
				                        </script>
									</td>
									<td>										
										<jecs:title key='jpmsalesanlysis.type' />
									</td>
									<td>
										<jecs:list listCode="jpmsalesanalysis.amountorpv" name="type" id="type"
											showBlankLine="false" value="${param.type}"
											defaultValue="0" />
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>

				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>

								</div>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">				
					<script language="javascript">
					 var chart;
							$(function(){
								chart = new Highcharts.Chart({
									chart: {
										renderTo: 'container1',
										defaultSeriesType: 'column'
									},
									title: {
										text: '环比分析'
									},
									xAxis: {
										categories: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            '${resultVar.NAME }',
										</c:forEach>
										],
										title: {
											text: null
										},
										labels:{
											style:{
												font:'normal 11px Verdana, sans-serif'
											}
										}
									},
									yAxis: {
										min: 0,
										title: {
											text: '${unit}',
											align: 'high'
										}
									},
									tooltip: {
										formatter: function() {
											return ''+
												 this.y +' ${unit}';
										}
									},
									plotOptions: {
										bar: {
											dataLabels: {
												enabled: true
											}
										}
									},
									legend: {
										layout: 'vertical',
										align: 'right',
										verticalAlign: 'top',
										x: -100,
										y: 100,
										floating: true,
										borderWidth: 1,
										shadow: true
									},
									credits: {
										enabled: false
									},
								        series: [{
										name: '环比分析',
										data: [
										<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
								            ${resultVar.SUM },
										</c:forEach>
										]
									}]
								});
								
								
							});
					</script>
				</c:if>
			</c:when>

			<c:when test="${strAction=='production'}">
				<!-- 5.production -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.production" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>
										<input type="hidden" name="saleFlag"
											value="${saleFlag }" />
										<jecs:title key="jpmSalesAnalysis.order.checktime" />(ex:2011-01-01)
									</td>
									<td>
										<input id="startLogTime" name="startLogTime" type="text"
											size="10" maxlength="10" value="${startLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_startOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
                                    Calendar.setup({
                                    inputField     :    "startLogTime",
                                    ifFormat       :    "%Y-%m-%d",
                                    button         :    "img_startOperatorTime",
                                    singleClick    :    true
                                });
                                </script>
										-
									</td>
									<td>
										<input id="endLogTime" name="endLogTime" type="text" size="10"
											maxlength="10" value="${endLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_endOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
                                    Calendar.setup({
                                        inputField     :    "endLogTime",
                                        ifFormat       :    "%Y-%m-%d",
                                        button         :    "img_endOperatorTime",
                                        singleClick    :    true
                                    });
                                    </script>
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>




				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">
					<script language="javascript">
					 var chart;
						$(function() {
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container1',
									defaultSeriesType: 'bar',
									height: '500'
								},
								title: {
									text: '商品分析 '
								},
								xAxis: {
									categories: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            "<font style='font-weight: bold;font-size:12;' >${resultVar.NAME }</font>",
									</c:forEach>
									],
									title: {
										text: null
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: '件',
										align: 'high'
									}
								},
								tooltip: {
									formatter: function() {
										return ''+
											 this.y +' 件';
									}
								},
								plotOptions: {
									bar: {
										dataLabels: {
											enabled: true
										}
									}
								},
								legend: {
									layout: 'vertical',
									align: 'right',
									verticalAlign: 'top',
									x: -100,
									y: 100,
									floating: true,
									borderWidth: 1,
									shadow: true
								},
								credits: {
									enabled: false
								},
							        series: [{
									name: '商品分析',
									data: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            ${resultVar.SUM },
									</c:forEach>
									]
								}]
							});
							
							
						});
					</script>
				</c:if>
			</c:when>

			<c:when test="${strAction=='champion'}">
				<!-- 6.champion -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.champion" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>
										<input type="hidden" name="saleFlag"
											value="${saleFlag }" />
										<jecs:title key='jpmSalesAnalysis.starttime' />
									</td>
									<td>
										<input id="startLogTime" name="startLogTime" type="text"
											size="10" maxlength="10" value="${startLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_startOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
                                Calendar.setup({
                                inputField     :    "startLogTime",
                                ifFormat       :    "%Y-%m-%d",
                                button         :    "img_startOperatorTime",
                                singleClick    :    true
                                });
                                </script>
										-
									</td>
									<td>
										<input id="endLogTime" name="endLogTime" type="text" size="10"
											maxlength="10" value="${endLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_endOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
                                Calendar.setup({
                                inputField     :    "endLogTime",
                                ifFormat       :    "%Y-%m-%d",
                                button         :    "img_endOperatorTime",
                                singleClick    :    true
                                });
                                </script>
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>

				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<!--
							<div class="searchAreaBox">
								
									<input type="hidden" name="saleFlag" value="${saleFlag }" />
									<jecs:title key='jpmSalesAnalysis.starttime' />
									<input id="startLogTime" name="startLogTime" type="text" size="10"
										maxlength="10" value="${startLogTime }" />
									<img src="./images/calendar/calendar7.gif"
										id="img_startOperatorTime" style="cursor: pointer;"
										title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
									<script> 
												Calendar.setup({
												inputField     :    "startLogTime", 
												ifFormat       :    "%Y-%m-%d",  
												button         :    "img_startOperatorTime", 
												singleClick    :    true
												}); 
											</script>
									-
									<input id="endLogTime" name="endLogTime" type="text" size="10"
										maxlength="10" value="${endLogTime }" />
									<img src="./images/calendar/calendar7.gif"
										id="img_endOperatorTime" style="cursor: pointer;"
										title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
									<script> 
												Calendar.setup({
												inputField     :    "endLogTime", 
												ifFormat       :    "%Y-%m-%d",  
												button         :    "img_endOperatorTime", 
												singleClick    :    true
												}); 
											</script>
									<input name="search" class="bgBtn" type="submit" value="<jecs:title key='operation.button.search' />" />
								</form>
							</div>
                            -->
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">
					<script language="javascript">
					 var chart;
						$(function() {
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container1',
									defaultSeriesType: 'column'
								},
								title: {
									text: '推荐冠军'
								},
								xAxis: {
									categories: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            '${resultVar.NAME }',
									</c:forEach>
									],
									title: {
										text: null
									},
									max:9,
									labels:{
										rotation:-12,
										style:{
											font:'normal 11px Verdana, sans-serif'
										}
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: '人',
										align: 'high'
									}
								},
								tooltip: {
									formatter: function() {
										return ''+
											 this.y +' 人';
									}
								},
								plotOptions: {
									bar: {
										dataLabels: {
											enabled: true
										}
									}
								},
								legend: {
									layout: 'vertical',
									align: 'right',
									verticalAlign: 'top',
									x: -100,
									y: 100,
									floating: true,
									borderWidth: 1,
									shadow: true
								},
								credits: {
									enabled: false
								},
								series: [{
									name: '推荐冠军',
									data: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            ${resultVar.SUM },
									</c:forEach>
									]
								}]
							});
							
							
						});
					</script>
				</c:if>
			</c:when>
			<c:when
				test="${strAction=='awardTitle' }">
				<!-- 7.awardTitle -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.awardTitle" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>
										<jecs:title key='jpmSalesAnalysis.month' />
										<input id="month" name="month" type="text" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'
											size="10" maxlength="10" value="${month=='null'?'':month }" />
									</td>
									<td>										
										<jecs:title key='pass.star.type' />
									</td>
									<td>
										<jecs:list listCode="pass.star.type" name="starType" id="starType" showBlankLine="true" 
											value="${param.starType}" defaultValue=""/>
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>

				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>

								</div>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">
					<script language="javascript">
					 var chart;
						$(function(){
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container1',
									defaultSeriesType: 'bar',
									height:'${fn:length(list)*30+150}'
								},
								title: {
									text: '团队奖衔'
								},
								xAxis: {
									categories: [
	
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            '<jecs:code listCode="pass.star.zero" value="${resultVar.NAME }"/>',
									</c:forEach>
	
									],
									title: {
										text: null
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: '人',
										align: 'high'
									}
								},
								tooltip: {
									formatter: function() {
										return ''+
											 this.y +' 人';
									}
								},
								plotOptions: {
									bar: {
										dataLabels: {
											enabled: true
										}
									}
								},
								legend: {
									layout: 'vertical',
									align: 'right',
									verticalAlign: 'top',
									x: -100,
									y: 100,
									floating: true,
									borderWidth: 1,
									shadow: true
								},
								credits: {
									enabled: false
								},
							        series: [{
									name: '团队奖衔',
									data: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            ${resultVar.SUM },
									</c:forEach>
									]
								}]
							});
							
							
						});
					</script>
				</c:if>
			</c:when>
			<c:when
				test="${strAction=='addPromotion' }">
				<!-- 8.addPromotion -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.addPromotion" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>
										<jecs:title key='jpmSalesAnalysis.month' />
										<input id="month" name="month" type="text" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'
											size="10" maxlength="10" value="${month=='null'?'':month }" />
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>

				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>

								</div>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">
					<script language="javascript">
					 var chart;
						$(function(){
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container1',
									defaultSeriesType: 'bar',
									height:'${fn:length(list)*30+150}'
								},
								title: {
									text: '新增晋级'
								},
								xAxis: {
									categories: [
	
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            '<jecs:code listCode="pass.star.zero" value="${resultVar.NAME }"/>（数量：${resultVar.SUM }）',
									</c:forEach>
	
									],
									title: {
										text: null
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: '人',
										align: 'high'
									}
								},
								tooltip: {
									formatter: function() {
										return ''+
											 this.y +' 人';
									}
								},
								plotOptions: {
									bar: {
										dataLabels: {
											enabled: true
										}
									}
								},
								legend: {
									layout: 'vertical',
									align: 'right',
									verticalAlign: 'top',
									x: -100,
									y: 100,
									floating: true,
									borderWidth: 1,
									shadow: true
								},
								credits: {
									enabled: false
								},
							        series: [{
									name: '新增晋级',
									data: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            ${resultVar.SUM },
									</c:forEach>
									]
								}]
							});
							
							
						});
					</script>
				</c:if>
			</c:when>
			<c:when
				test="${strAction=='achievement' }">
				<!-- 9.achievement -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.achievement" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>										
										<jecs:title key='jpmSalesAnalysis.starttime' />
									</td>
									<td>
										<input id="startLogTime" name="startLogTime" type="text"
											size="10" maxlength="10" value="${startLogTime=='null'?'':startLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_startOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
				                        Calendar.setup({
				                            inputField     :    "startLogTime",
				                            ifFormat       :    "%Y-%m-%d",
				                            button         :    "img_startOperatorTime",
				                            singleClick    :    true
				                            });
				                        </script>
										-
									</td>
									<td>
										<input id="endLogTime" name="endLogTime" type="text" size="10"
											maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_endOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
				                        Calendar.setup({
					                        inputField     :    "endLogTime",
					                        ifFormat       :    "%Y-%m-%d",
					                        button         :    "img_endOperatorTime",
					                        singleClick    :    true
				                        });
				                        </script>
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>

				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>

								</div>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">
					<script language="javascript">
					 	var chart;
						$(function(){
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container1',
									defaultSeriesType: 'bar',
									height:'${fn:length(list)*30+150}'
								},
								title: {
									text: '团队业绩'
								},
								xAxis: {
									categories: [
									 <c:choose>
										<c:when test="${province!=null && province!='' && province!='null' }">
											 <c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            '<a href="jpmSalesAnalysis.html?search=Y&strAction=achievement&userCode=${param.userCode}&province=&startLogTime=${startLogTime}&endLogTime=${endLogTime}">${resultVar.NAME}</a>',
											</c:forEach>
										</c:when>
										<c:otherwise>
											 <c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            '<a href="jpmSalesAnalysis.html?search=Y&strAction=achievement&userCode=${param.userCode}&province=${resultVar.CODE}&startLogTime=${startLogTime}&endLogTime=${endLogTime}">${resultVar.NAME}</a>',
											</c:forEach>
										</c:otherwise>
									</c:choose>
											
										 
									],
									title: {
										text: null
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: 'PV',
										align: 'high'
									}
								},
								tooltip: {
									formatter: function() {
										return ''+
											 this.y +' PV';
									}
								},
								plotOptions: {
									bar: {
										dataLabels: {
											enabled: true
										}
									}
								},
								legend: {
									layout: 'vertical',
									align: 'right',
									verticalAlign: 'top',
									x: -100,
									y: 100,
									floating: true,
									borderWidth: 1,
									shadow: true
								},
								credits: {
									enabled: false
								},
							        series: [{
									name: '团队业绩',
									data: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            ${resultVar.SUM },
									</c:forEach>
									]
								}]
							});
							
							
						});
					</script>  
				</c:if>
			</c:when>
			<c:when
				test="${strAction=='highIncome' }">
				<!-- 10.highIncome -->
				<h2 class="title mgb20">
					<jecs:title key="jpmSalesAnalysis.highIncome" />
				</h2>
				<div class="condition">
					
						<table class="personalInfoTab">
							<tbody>
								<tr>
									<td>										
										<jecs:title key='jpmSalesAnalysis.starttime' />
									</td>
									<td>
										<input id="startLogTime" name="startLogTime" type="text"
											size="10" maxlength="10" value="${startLogTime=='null'?'':startLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_startOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
				                        Calendar.setup({
				                            inputField     :    "startLogTime",
				                            ifFormat       :    "%Y-%m-%d",
				                            button         :    "img_startOperatorTime",
				                            singleClick    :    true
				                            });
				                        </script>
										-
									</td>
									<td>
										<input id="endLogTime" name="endLogTime" type="text" size="10"
											maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_endOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script>
				                        Calendar.setup({
					                        inputField     :    "endLogTime",
					                        ifFormat       :    "%Y-%m-%d",
					                        button         :    "img_endOperatorTime",
					                        singleClick    :    true
				                        });
				                        </script>
									</td>
									<td>										
										<jecs:title key='label.member.or.agent.code' />
									</td>
									<td>
										<input id="userCode" name="userCode" type="text" size="10"
											maxlength="10" value="${param.userCode=='null' ? '':param.userCode }" />
									</td>
									<td>										
										<jecs:title key='jpmSalesAnalysis.maxNum' />
									</td>
									<td>
										<select name="maxNum" id="maxNum">
											<option value="10" ${param.maxNum==10 ? 'selected' : '' }>10</option>
											<option value="20" ${param.maxNum==20 ? 'selected' : '' }>20</option>
											<option value="50" ${param.maxNum==50 ? 'selected' : '' }>50</option>
										</select>
									</td>
									<td>										
										<jecs:title key='jpmSalesAnalysis.minMoney' />
									</td>
									<td>
										<input id="minMoney" name="minMoney" type="text" size="10"
											maxlength="10" value="${param.minMoney=='null' ? '':param.minMoney }" />
									</td>
									<td>
										<input type="submit" name="submit" onclick='loading();' value="<jecs:locale key="operation.button.search"/>"
											class="button" />
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>

				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center" valign="top">
							<div class="table_div">
								<div class="table_LongTable">
									<div id="container1"></div>

								</div>
							</div>
						</td>
					</tr>
				</table>
				<c:if test="${search=='Y'}">
					<script language="javascript">
					 var chart;
						$(function(){
							chart = new Highcharts.Chart({
								chart: {
									renderTo: 'container1',
									defaultSeriesType: 'bar',
									height:'${fn:length(list)*30+150}'
								},
								title: {
									text: '高收入人群'
								},
								xAxis: {
									categories: [
	
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            '${resultVar.NAME }',
									</c:forEach>
	
									],
									title: {
										text: null
									}
								},
								yAxis: {
									min: 0,
									title: {
										text: 'RMB',
										align: 'high'
									}
								},
								tooltip: {
									formatter: function() {
										return ''+
											 this.y +' RMB';
									}
								},
								plotOptions: {
									bar: {
										dataLabels: {
											enabled: true
										}
									}
								},
								legend: {
									layout: 'vertical',
									align: 'right',
									verticalAlign: 'top',
									x: -100,
									y: 100,
									floating: true,
									borderWidth: 1,
									shadow: true
								},
								credits: {
									enabled: false
								},
							        series: [{
									name: '高收入人群',
									data: [
									<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
							            ${resultVar.SUM },
									</c:forEach>
									]
								}]
							});
							
							
						});
					</script>
				</c:if>
			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>
	</body>
	</div>
</form>