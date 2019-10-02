<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<title><jecs:locale key="bdBounsDeductList.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductList.heading"/></content>
<meta name="menu" content="BdBounsDeductMenu"/>



<form action="" method="get" name="bdBounsDeductForm1" id="bdBounsDeducttForm1">
<input id="strAction" name="strAction" value="${param.strAction }" type="hidden">
<div class="searchBar">


	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	<div class="new_searchBar">
	<jecs:title key="bdBounsDeduct.status"/>
	<jecs:list name="status" listCode="bdbounsdeduct.status" value="${param.status }" defaultValue="" showBlankLine="true"/>
	</div>
	
	<div class="new_searchBar">
	<jecs:title key="bdReconsumMoneyReport.typeCH"/>
	<jecs:list name="type" listCode="bdbounsdeduct.item" value="${param.type }" defaultValue="" showBlankLine="true"/>
	</div>
	
	<div class="new_searchBar">	
<jecs:title key="miMember.memberNo"/>
	<input name="userCode" type="text" value="${param.userCode }" size="10"/>
	</div>
		
<%--<jecs:title key="bdCalculatingSubDetail.name"/>
	<input name="name" type="text" value="${param.name }" size="10"/>--%>
	
	<div class="new_searchBar">		
<jecs:title key="bdBounsDeduct.createName"/>	
	<input name="createName" type="text" value="${param.createName }" size="10"/>
	</div>	
	
	</c:if>
	
	
	<c:if test="${sessionScope.sessionLogin.userType=='C'||sessionScope.sessionLogin.userType=='M'}">

<div class="new_searchBar">
<jecs:title key="bdBounsDeduct.createTimeScope"/>

<input name="startCreateTime" id="startCreateTime" type="text" 
			value="${param.startCreateTime }" style="cursor: pointer;width:75px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endCreateTime" id="endCreateTime" type="text"
			value="${param.endCreateTime }" style="cursor: pointer;width:75px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
			
<div class="new_searchBar">	
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<button name="search" type="submit" class="btn btn_sele" >
		<jecs:locale key="operation.button.search"/>
	</button>
</div>	
	</c:if>
<%--<table width="100%" border="0">
	<tr>
		<th nowrap="nowrap">
		</th>
		
		<th>
		</th>
		
		<th>
		</th>
		<th>
		</th>
		</c:if>
		<th>
		</th>
		
		<th>
<jecs:locale key="operation.button.search"/>
		</th>	
		<th>&nbsp;</th>
	</tr>
	<tr>
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
		<td>	
		</td>
		<td>	
		</td>
		<td>	
		</td>
		<td>		
		</td>
		</c:if>
		<td>	
		</td>
		<td>
			
		</td>

		<td width="100%">&nbsp;</td>
	</tr>
</table>--%>
</div>
</form>




<ec:table 
	tableId="bdBounsDeductListTable"
	items="bdBounsDeductList"
	var="bdBounsDeduct"
	autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/bdBounsDeducts.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
			<ec:exportXls fileName="bdBounsDeduct.xls"/>
				<ec:row>
    			<ec:column property="create_time" title="prRefund.createTime" >
    				<fmt:parseDate value="${ bdBounsDeduct['create_time']}" pattern="yyyy-MM-dd" var="varDate"/>
					<fmt:formatDate value="${varDate }" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			<ec:column property="user_code" title="miMember.memberNo" />
    			<%--<ec:column property="user_name" title="bdCalculatingSubDetail.name" />--%>
    			
    			
    			<ec:column property="type" title="bdReconsumMoneyReport.typeCH" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="bdbounsdeduct.item" value="${bdBounsDeduct['type']}"/>
    			</ec:column>
    			<ec:column property="w_week" title="bdBounsDeduct.wweek" >
    				<jecs:weekFormat week="${bdBounsDeduct['w_week']}" weekType="w" />
    			</ec:column>
    			
    			
    			<ec:column property="money" title="bdBounsDeduct.money" styleClass="numberColumn">
				    <fmt:formatNumber value="${bdBounsDeduct['money']}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			<ec:column property="remain_money" title="bdBounsDeduct.remainMoney" styleClass="numberColumn">
				    <fmt:formatNumber value="${bdBounsDeduct['remain_money']}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			<ec:column property="deduct_money" title="bdBounsDeduct.deductMoney" styleClass="numberColumn">
				    <fmt:formatNumber value="${bdBounsDeduct['deduct_money']}" type="number" pattern="###,###,##0.00"/>
				</ec:column>
    			
    			<ec:column property="1" title="bdBounsDeduct.curWwekkRemainMoney" styleClass="numberColumn" sortable="false" cell="com.joymain.jecs.util.ectable.EcTableCell">
    			<fmt:formatNumber value="${bdBounsDeduct['remain_money']-bdBounsDeduct['deduct_money'] }" type="number" pattern="###,###,##0.00"/>
    				
    			</ec:column>
    			
    			<ec:column property="status" title="bdBounsDeduct.status" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="bdbounsdeduct.status" value="${bdBounsDeduct['status']}"/>
    			</ec:column>

				<c:if test="${sessionScope.sessionLogin.userType=='C' }" >
					
	    			<ec:column property="create_code" title="bdBounsDeduct.createName" cell="com.joymain.jecs.util.ectable.EcTableCell">
	    				${bdBounsDeduct['create_code']}/${bdBounsDeduct['create_name']}
	    			</ec:column>
    			
				</c:if>
    			<ec:column property="summary" title="bdBounsDeduct.summary" />

				<ec:column property="2" title="title.operation" sortable="false" styleClass="centerColumn" viewsDenied="xls">
						<img style="cursor:pointer" src="images/newIcons/search_16.gif" onclick="window.location.href='bdBounsDeducts.html?id=${bdBounsDeduct['id'] }&strAction=bdBounsDeductDetail';" alt="<jecs:locale key="function.menu.view"/>"/>
				</ec:column>
				</ec:row>

</ec:table>	

<form name="form1" action="<c:url value='editBdBounsDeduct.html'/>">
			<input type="hidden" name="strAction" id="strAction"/>
			<input type="hidden" name='id' id='id'/>
</form>

<script type="text/javascript">

   function editBdBounsDeduct(id){
   		<jecs:power powerCode="editBdBounsDeduct">
					window.location="editBdBounsDeduct.html?strAction=editBdBounsDeduct&id="+id;
			</jecs:power>
		}

</script>
