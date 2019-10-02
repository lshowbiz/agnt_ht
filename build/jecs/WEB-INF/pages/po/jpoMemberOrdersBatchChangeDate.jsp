<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderList.heading"/></content>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:if test="${not empty errorList || not empty successList }">
	<div class="error" id="errorMessages">
		<c:forEach var="error" items="${successList}">
			<img src="<c:url value="images/newIcons/tick_16.gif"/>"
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
		<c:forEach var="error" items="${errorList}">
			<img src="<c:url value="images/newIcons/warning_16.gif"/>"
				alt="<jecs:locale key="icon.warning"/>" class="icon" />
			<c:out value="${error}" escapeXml="false" />
			<br />
		</c:forEach>
	</div>
</c:if>

<form action="jpoMemberOrdersBatchChangeDate.html" method="post" enctype="multipart/form-data"  name="jpoMemberOrderForm" id="jpoMemberOrderForm">

<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr">
    <th width="25%">
        <label for="xlsFile" class="required">
        	<span class="req">*</span> 
        	<jecs:locale key="fiPayData.importFile"/>:
        </label>
    </th>
    <td><input type="file" name="xlsFile" id="xlsFile" size="50"/></td>
    </tr>
     <tr class="edit_tr">
     	<th width="25%">
     	<jecs:locale key="jpmProductWineTemplate.memo"/>:</th>
    	<td  width="100%">
    		<jecs:locale key="file.model"/>
    	</td>
    </tr>
    <tr class="edit_tr">
    <th width="25%">
        <label for="logCreateTime" class="required">
        	<span class="req">*</span> 
        	<jecs:locale key="button.editConfirmDate"/>
        </label>
    </th>
    <td>
    <input name="logCreateTime" id="logCreateTime" type="text" 
			value="${param.logCreateTime }" style="cursor: pointer;width:130px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
    
	</td>
    </tr>
    
    <tr class="edit_tr">
		<td  class="command"  colspan="2" align="center">
			<button  type="submit" class="btn btn_sele" name="import"  onclick="bCancel=false" >
				<jecs:locale key="button.import"/>
			</button>
		</td>
	</tr>
</tbody>
</table>
</form>
