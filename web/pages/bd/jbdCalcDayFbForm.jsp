<%@ include file="/common/taglibs.jsp"%><%@ page contentType="text/html; charset=utf-8" language="java" %>


<title><jecs:locale key="jbdCalcDayFbDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdCalcDayFbDetail.heading"/></content>











<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="jbdCalcDayFbList"
		var="jbdCalcDayFb"
		width="100%"
		showPagination="false"
		showStatusBar="false"
		autoIncludeParameters="true"
		action="${pageContext.request.contextPath}/jbdDetail.html"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			  
			    				<ec:column property="reuser_code" title="miMember.memberNo" />
			    				<ec:column property="member_order_no" title="poMemberOrder.memberOrderNo" />
			    	
			    				<ec:column property="check_date" title="logType.C" />
			    				
								<ec:column property="fb_money" title="领取金额" >
			    					<fmt:formatNumber value="${jbdCalcDayFb.fb_money }" type="number" pattern="###,###,###.##"/>
								</ec:column>	
			    				 
		</ec:row>
	</ec:table>	