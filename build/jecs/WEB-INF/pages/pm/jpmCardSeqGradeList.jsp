<%@ include file="/common/taglibs.jsp"%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<title><jecs:locale key="jpmCardSeqList.title"/></title>
<content tag="heading"><jecs:locale key="jpmCardSeqList.heading"/></content>
<meta name="menu" content="JpmCardSeqMenu"/>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<form action="jpmCardSeqGrades.html" method="post" name="miMemberForm" id="miMemberForm">
	<div class="searchBar">

	<input type="hidden" id="strAction" name="strAction" value="getGrade"/>
	
	
	<label>等奖:</label>
      	   <jecs:list name="curGrade" listCode="seq.grade" value="${param.curGrade}" defaultValue="" showBlankLine="true"/>	
      	   
	<label >抽奖人数:</label>
		<input name="gradeNum" type="text" value="${param.gradeNum}" size="10"/>
		
		
	<label >抽奖时间:</label>
		<input name="gradeBTime" id="gradeBTime" type="text" value="${param.gradeBTime }" size="18" />
			    <img src="./images/calendar/calendar7.gif" id="img_gradeBTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "gradeBTime", 
					ifFormat       :    "%Y-%m-%d %H:%M:00",
					button         :    "img_gradeBTime", 
					showsTime	   :	true,
					singleClick    :    true
					}); 
				</script>
			     - 
			     <input name="gradeETime" id="gradeETime" type="text" value="${param.gradeETime }" size="18"/>
			     <img src="./images/calendar/calendar7.gif" id="img_gradeETime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "gradeETime", 
					ifFormat       :    "%Y-%m-%d %H:%M:59",  
					button         :    "img_gradeETime", 
					showsTime	   :	true,
					singleClick    :    true
					}); 
				</script>
		
		
		
		
		
		
		
<input name="search" class="button" type="submit" value="抽奖" />
<input name="search" class="button" type="button" onclick="window.location.href='jpmCardSeqGrades.html?strAction=rebuildGrade'" value="还原" />
		
</div>

</form>





	<ec:table 
		tableId="bdRecCalculatingSubDetailsTable"
		items="gradeList"
		var="jpmCardSeq"
		width="100%"
	action="${pageContext.request.contextPath}/jpmCardSeqGrades.html"
		showPagination="false"
		showStatusBar="false"
		sortable="false" imagePath="./images/extremetable/*.gif">
		<ec:row>
			<ec:exportXls fileName="grade.xls"/>
			<ec:parameter name="strAction" value="jpmCardSeqGrades"></ec:parameter>
    			<ec:column property="grade" title="等奖" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="grade" value="${jpmCardSeq.grade}"/>
    			</ec:column>
    			<ec:column property="card_no" title="抽奖号" />
    			<ec:column property="user_code" title="会员编号" />
    			<ec:column property="mi_user_name" title="姓名" />
    			<ec:column property="papernumber" title="身份证" escapeAutoFormat="true"/>
    			<ec:column property="member_order_no" title="订单号" />
    			<ec:column property="amount" title="订单金额" />
    			<ec:column property="state_province_name" title="省" />
    			<ec:column property="city_name" title="市" />
    			<ec:column property="district_name" title="区" />
    			<ec:column property="address" title="地址" />
    			<ec:column property="mobiletele" title="移动电话" />
		</ec:row>
	</ec:table>	




