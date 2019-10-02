<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdMemberLinkCalcHistDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdMemberLinkCalcHistDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
				<jecs:locale key="operation.button.save"/>
			</button>
		</jecs:power>
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
			<jecs:locale key="operation.button.return"/>
		</button>
		
</c:set>

<spring:bind path="jbdMemberLinkCalcHist.*">
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

<form:form commandName="jbdMemberLinkCalcHist" method="post" action="editJbdMemberLinkCalcHistGrade.html" id="jbdMemberLinkCalcHistForm">
<%-- 
	<div class="searchBar">	
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="id"/>

    <tr class="edit_tr">
    	<th>
	    	<span class="text-font-style-tc">
	        	<jecs:label  key="bdBounsDeduct.wweek"/>
	    	</span>
	    </th>
    	<td>
    		<span class="textbox">${jbdMemberLinkCalcHist.wweek }</span>
    	</td>
    
    	<th>
    		<span class="text-font-style-tc">
        		<jecs:label  key="bdNetWorkCostReport.memberCH"/>
    		</span>
    	</th>
    	<td>
    		<span class="textbox">${jbdMemberLinkCalcHist.userCode }</span>
    	</td>
    </tr>
    
    <tr class="edit_tr">
    	<th>
    		<span class="text-font-style-tc">
        		<jecs:label  key="bdCalculatingSubDetail.name"/>
    		</span>
    	</th>
    	<td>
    		<span class="textbox">${jbdMemberLinkCalcHist.name }</span>
    	</td>
    
    	<th>
    		<span class="text-font-style-tc">
        		<jecs:label  key="jbdMemberLinkCalcHist.honorStar"/>
    		</span>
    	</th>
    	<td>
    		<span class="textbox">
				<jecs:list styleClass="textbox-text" name="honorStar" listCode="honor.star.zero" 
				value="${jbdMemberLinkCalcHist.honorStar }" defaultValue="" />	
			</span>
			<span class="textbox">
				<input type="checkbox" value="changeTravelPoint2012" id="changeTravelPoint2012" 
				name="changeTravelPoint2012" class="textbox-text"/> 
				<jecs:locale  key="jbdMemberLinkCalcHist.changeTravelPoint2012"/>
    		</span>
    	</td>
    </tr>
    
    
	        <input type="hidden" id="oldHonorStar" name="oldHonorStar" value="${oldHonorStar }" />
	        <input type="hidden" id="oldPassStar" name="oldPassStar" value="${oldPassStar }" />
	        <input type="hidden" id="oldQualifyStar" name="oldQualifyStar" value="${oldQualifyStar }" />
	        <input type="hidden" id="oldQualifyRemark" name="oldQualifyRemark" value="${oldQualifyRemark }" />
    <tr class="edit_tr">
    	<th>
    		<span class="text-font-style-tc">
        		<jecs:label  key="jbdMemberLinkCalcHist.passStar"/>
    		</span>
    	</th>
    	<td>
    		<span class="textbox">
				<jecs:list styleClass="textbox-text" name="passStar" 
				listCode="pass.star.zero" value="${jbdMemberLinkCalcHist.passStar }" defaultValue="" />	
    		</span>
    	</td>
   
    	<th>
    		<span class="text-font-style-tc">
        		<jecs:label  key="jbdMemberLinkCalcHist.qualifyStar"/>
    		</span>
    	</th>
    	<td>
    		<span class="textbox">
				<jecs:list styleClass="textbox-text" name="qualifyStar" 
				listCode="qualify.star.zero" value="${jbdMemberLinkCalcHist.qualifyStar }" defaultValue="" />	
    		</span>
    	</td>
    </tr>
    
    <tr class="edit_tr">
    	<th>
    		<span class="text-font-style-tc-tare">
        		<jecs:label  key="jbdMemberLinkCalcHist.qualifyRemark"/>
    		</span>
    	</th>
	    <td colspan="3">
	    	<span class="text-font-style-tc-right">
				<textarea cssClass="textarea_border" name="qualifyRemark">
					${jbdMemberLinkCalcHist.qualifyRemark }
				</textarea>
	    	</span>
	    </td>
   	</tr>
    <tr class="edit_tr">
    	
	    <td colspan="4" class="command" align="center">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    </td>
   	</tr>
   	</tbody>
</table>
</form:form>
