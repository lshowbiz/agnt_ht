<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="./scripts/validate.js"> </script> 
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/print.css'/>" />
<style media="print">
	.noPrint { 
		display: none;
	}
</style>
<c:if test="${not empty errorMessages }">

	${ errorMessages }
	
		<c:remove var="errorMessages" scope="session" />
</c:if>
<form:form commandName="bdPeriod" method="post" action="jmiMemberDataReport.html" id="bdPeriodForm"  enctype="multipart/form-data">


	<spring:bind path="bdPeriod.*">
	    <c:if test="${not empty status.errorMessages}">
	    <div class="error">    
	        <c:forEach var="error" items="${status.errorMessages}">
	            <img src="<c:url value="/images/iconWarning.gif"/>"
	                alt="<jecs:locale key="icon.warning"/>" class="icon" />
	            <c:out value="${error}" escapeXml="false"/><br />
	        </c:forEach>
	    </div>
	    </c:if>
	</spring:bind>

	<table class="detail" width="70%">
	<tbody class="window">
	<form:hidden path="wid"/>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<tr class="edit_tr">
			<th><jecs:label key="bdReconsumMoneyReport.companyCH"/></th>
			<td width="25%">
			
			<jecs:company name="companyCode" selected="${param.companyCode }"  withAA="false"  />
			</td>
		</tr>
		</c:if>
		
		<jecs:power powerCode="memberDataFile">
			<tr class="edit_tr"><th>
		        <jecs:label  key="fiPayData.importFile"/>
		    </th>
		    <td>
		   		<input name="importExcelFile" type="file" id="importExcelFile" size="50" /></td></tr>
		    
		    <tr>
		    
		    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td>
        所导入的文件格式说明:<br/>
		1.第一行为抬头，不导入<br/>
		2.第1列为： 会员编号<br/>
    </td></tr>
		</jecs:power>
		
		<tr class="edit_tr">
			<th><jecs:label key="miMember.cardType.old"/></th>
			<td>
				<c:forEach items="${cardTypeMap }" var="varCardType">
					<input name="cardTypeGroup" type="checkbox" value="${varCardType.key }" checked="checked"/>
					<jecs:locale key="${varCardType.value }"/>
				</c:forEach>
			</td>
		</tr>
		<tr class="edit_tr">
			<th><jecs:label key="miMember.cardType"/></th>
			<td>
				<c:forEach items="${memberLevelMap }" var="varMemberLevel">
					<input name="memberLevelGroup" type="checkbox" value="${varMemberLevel.key }" checked="checked"/><jecs:locale key="${varMemberLevel.value }"/>
				</c:forEach>
			</td>
		</tr>
		<tr class="edit_tr">
			<th><jecs:label key="pd.createTime"/></th>
			<td>
				<input id="createBTime" name="createBTime" type="text" value="${param.createBTime }"
				style="cursor: pointer;width:70px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				 - 
				<input id="createETime" name="createETime" type="text" value="${param.createETime }"
				style="cursor: pointer;width:70px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				
			</td>
		</tr>
		<tr class="edit_tr">
			<th><jecs:label key="pd.checkTime"/></th>
			<td>
				<input id="checkBTime" name="checkBTime" type="text" value="${param.checkBTime }"
				style="cursor: pointer;width:70px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				- 
				<input id="checkETime" name="checkETime" type="text" value="${param.checkETime }"
				style="cursor: pointer;width:70px;" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				
			</td>
		</tr>
		
		
		<jecs:power powerCode="memberIsStore">
			<tr class="edit_tr">
				<th><jecs:label key="miMember.store.type"/></th>
				<td>
					<input name="isStoreGroup" type="checkbox" value="1" /><jecs:locale key="miMember.isstore"/>
					<input name="isStoreGroup" type="checkbox" value="2"/><jecs:locale key="store.type2"/>
				</td>
			</tr>
		</jecs:power>
		
		
		<jecs:power powerCode="linkNoNum">
			<tr class="edit_tr">
				<th><jecs:label key="miMember.linkNoNum"/></th>
				<td>
					<input name="linkNoNum" type="checkbox" value="1" />
				</td>
			</tr>
		</jecs:power>
		
		<tr class="edit_tr">
			<th><jecs:label key="busi.field.select"/></th>
			<td>
				<div class="eXtremeTable" >
<table id="miMemberListTable_table"  border="0"  cellspacing="0"  cellpadding="4"  class="tableRegion"  width="100%" >
	<thead>
	<tr>
		<td class="tableHeader"  ><jecs:locale key="buis.order.member"/></td>
		<td class="tableHeader"  ><jecs:locale key="busi.member.field"/></td>
	</tr>
	</thead>
	<tbody class="tableBody" >
			
			
			
			
			
				
	<tr class="odd"  onmouseover="this.className='highlight'"  onmouseout="this.className='odd'" >

			<c:forEach items="${textMap }" var="var">
				<jecs:power powerCode="mi_data_${var.key }">
					<tr>
					<td class="centerColumn" width="60">
						<input name="${var.key}" value="" size="4">
					</td>
			
					<td><jecs:locale key="${var.value }"/></td>
					</tr>
				</jecs:power>
				
			</c:forEach>
			
		
	</tr>
		

	</tbody>
</table>
</div>
			</td>
		</tr>
		<tr class="edit_tr">
			<td class="command" colspan="4" align="center">
			<button type="submit" class="btn btn_sele" name="import"	><jecs:locale key="button.report"/></button>
				<input type="hidden" name="strAction" value="jmiMemberDataReport"/>
			</td>
		</tr>
		</tbody>
	</table>
	
</form:form>

