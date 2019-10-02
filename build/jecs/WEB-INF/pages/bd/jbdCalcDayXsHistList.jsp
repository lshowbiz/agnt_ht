<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdCalcDayXsHistList.title"/></title>
<content tag="heading"><jecs:locale key="jbdCalcDayXsHistList.heading"/></content>
<meta name="menu" content="JbdCalcDayXsHistMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdCalcDayXsHist">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdCalcDayXsHist.html"/>?strAction=addJbdCalcDayXsHist'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<form name="frm" action="<c:url value='/jbdCalcDayXsHists.html'/>" >
		<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/>
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="jpoUserCoupon.userCode"/>
			<input name="userCode"  value="<c:out value='${param.userCode}'/>" cssClass="text medium"/>	
		</div>
		<div class="new_searchBar">	
			<jecs:title key="jbd.clac.xs.hist.wweek"/>
			<input name="wweek"  value="<c:out value='${param.wweek}'/>" cssClass="text medium"/>
		</div>
		<div class="new_searchBar">	
			<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
		</div>
	</div>
	

<ec:table 
	tableId="jbdCalcDayXsHistListTable"
	items="jbdCalcDayXsHistList"
	var="jbdCalcDayXsHist"
	action="${pageContext.request.contextPath}/jbdCalcDayXsHists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
	    			<ec:column property="wweek" title="结算日期" />
	    			<ec:column property="userCode" title="会员编号" />
	    			<ec:column property="memberLevel" title="miMember.cardType" cell="com.joymain.jecs.util.ectable.EcTableCell">
    					<jecs:code listCode="member.level" value="${jbdCalcDayXsHist.memberLevel }"/>
    				</ec:column>
	    			<ec:column property="keepPv" title="保留业绩" />
	    			<ec:column property="keepUserCode" title="保留业绩线" />
	    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJbdCalcDayXsHist('${jbdCalcDayXsHist.id}')"
								style="cursor: pointer;" title="<jecs:locale key='button.edit'/>"/> 
					</ec:column>
				</ec:row>
    			
 <%--    			
    			<ec:column property="recommendNo" title="jbdCalcDayXsHist.recommendNo" />
    			<ec:column property="linkNo" title="jbdCalcDayXsHist.linkNo" />
    			<ec:column property="petName" title="jbdCalcDayXsHist.petName" />
    			<ec:column property="active" title="jbdCalcDayXsHist.active" />
    			<ec:column property="exitDate" title="jbdCalcDayXsHist.exitDate" />
    			<ec:column property="freezeStatus" title="jbdCalcDayXsHist.freezeStatus" />
    			<ec:column property="firstMonth" title="jbdCalcDayXsHist.firstMonth" />
    			<ec:column property="monthConsumerPv" title="jbdCalcDayXsHist.monthConsumerPv" />
    			<ec:column property="firstPv" title="jbdCalcDayXsHist.firstPv" />
    			<ec:column property="algebraStatus" title="jbdCalcDayXsHist.algebraStatus" />
    			<ec:column property="groupPv" title="jbdCalcDayXsHist.groupPv" />
    			<ec:column property="bounsPoint" title="jbdCalcDayXsHist.bounsPoint" />
    			<ec:column property="bounsPv" title="jbdCalcDayXsHist.bounsPv" />
    			<ec:column property="lastKeepUserCode" title="jbdCalcDayXsHist.lastKeepUserCode" />
    			<ec:column property="lastKeepPv" title="jbdCalcDayXsHist.lastKeepPv" />
    			<ec:column property="serialNumber" title="jbdCalcDayXsHist.serialNumber" />
    			<ec:column property="areaConsumption" title="jbdCalcDayXsHist.areaConsumption" />
    			<ec:column property="rebounsPoint" title="jbdCalcDayXsHist.rebounsPoint" />
    			<ec:column property="rebounsPv" title="jbdCalcDayXsHist.rebounsPv" />
    			<ec:column property="rekeepPv" title="jbdCalcDayXsHist.rekeepPv" />
    			<ec:column property="relastKeepPv" title="jbdCalcDayXsHist.relastKeepPv" />
    			<ec:column property="companyCode" title="jbdCalcDayXsHist.companyCode" />
   --%>

</ec:table>	
</form>
<script type="text/javascript">

   function editJbdCalcDayXsHist(id){
   		<jecs:power powerCode="editJbdCalcDayXsHist">
					window.location="editJbdCalcDayXsHist.html?strAction=editJbdCalcDayXsHist&id="+id;
			</jecs:power>
		}

   
</script>
