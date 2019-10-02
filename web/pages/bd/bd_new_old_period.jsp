<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title"/></title>
<content tag="heading"><jecs:locale key="bdPeriodList.heading"/></content>
<meta name="menu" content="BdPeriodMenu"/>

<ec:table 
	tableId="bdPeriodListTable"
	items="bdPeriodList"
	var="bdPeriod"
	action="${pageContext.request.contextPath}/bd_new_old_period.html"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	width="100%"
	rowsDisplayed="100" sortable="false" imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif">
	<ec:row>
	<c:if test="${empty bdPeriod.bonus_send || bdPeriod.bonus_send != 0 }">
		<c:set var="color" value="red"/>
	</c:if>
	<c:if test="${bdPeriod.bonus_send == 0 }">
		<c:set var="color" value=""/>
	</c:if>
		<ec:column property="f_year" title="bdPeriod.fyear">
			<span  style="color: ${color }">
			${bdPeriod.f_year }
			</span>
		</ec:column>
		<ec:column property="f_month" title="bdPeriod.fmonth">
			<span  style="color: ${color }">
			${bdPeriod.f_month }
			</span>
		</ec:column>
		<ec:column property="123" title="财政旬">
			<span  style="color: ${color }">
			
			<c:choose>
			
			
				
             	<c:when test="${(bdPeriod.f_year*100+bdPeriod.f_week)>=201544 && (bdPeriod.f_year*100+bdPeriod.f_week)<201905  }">
             
	             	--
	             	
             	</c:when>
			
             	<c:when test="${(bdPeriod.f_year*100+bdPeriod.f_week)>=201529  || (bdPeriod.f_year*100+bdPeriod.f_week)>=201905 }">
             
             			<c:choose>
		             	<c:when test="${fn:substring(bdPeriod.start_time, 8, 10)=='01' }">
		             		上旬
		             	</c:when>
		             	<c:when test="${fn:substring(bdPeriod.start_time, 8, 10)=='11'}">
		             		中旬
		             	</c:when>
		             	<c:otherwise>
		             		下旬
		             	</c:otherwise>
		             	
						</c:choose>
             	
             	
             	</c:when>
             	<c:otherwise>
             	--
             	</c:otherwise>
             	
				</c:choose>
			</span>
		</ec:column>
		<ec:column property="f_week" title="bdBounsDeduct.wweek">
			<span  style="color: ${color }">
			<c:set var="tmpWeek">${bdPeriod.f_week}</c:set>
			<c:if test="${fn:length(tmpWeek)==2}">${bdPeriod.f_year}${bdPeriod.f_week}</c:if>
			<c:if test="${fn:length(tmpWeek)==1}">${bdPeriod.f_year}0${bdPeriod.f_week}</c:if>
			</span>
		</ec:column>
		<ec:column property="start_Time" title="bdPeriod.startTime">
			<span  style="color: ${color }">
    		${bdPeriod.start_time }
			</span>
		</ec:column>
		<ec:column property="end_Time" title="sysDataLog.endOperatorTime">
			<span  style="color: ${color }">
			${bdPeriod.end_time }
			</span>
		</ec:column>
		<ec:column property="bonus_Send" title="bdPeriod.bonusSend" styleClass="centerColumn">
			<span  style="color: ${color }">
			<jecs:code listCode="bdperiod.bonussend" value="${bdPeriod.bonus_send}"/>
			</span>
		</ec:column>
		<ec:column property="month_Status" title="bdPeriod.monthStatus" styleClass="centerColumn">
			<span  style="color: ${color }">
			<jecs:code listCode="yesno" value="${bdPeriod.month_status}"/>
			</span>
		</ec:column>
		<ec:column property="w_year" title="老期别">
			<span  style="color: ${color }">
			<c:set var="tmpWeek">${bdPeriod.w_week}</c:set>
			<c:if test="${fn:length(tmpWeek)==2}">${bdPeriod.w_year}${bdPeriod.w_week}</c:if>
			<c:if test="${fn:length(tmpWeek)==1}">${bdPeriod.w_year}0${bdPeriod.w_week}</c:if></span>
		</ec:column>
		<ec:column property="w_month" title="bdPeriod.wmonth">
			<span  style="color: ${color }">
			${bdPeriod.w_month }
			</span>
		</ec:column>
	</ec:row>
</ec:table>	