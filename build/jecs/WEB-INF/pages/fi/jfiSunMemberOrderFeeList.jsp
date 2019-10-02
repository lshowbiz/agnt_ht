<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiSunMemberOrderFeeList.title"/></title>
<content tag="heading"><jecs:locale key="jfiSunMemberOrderFeeList.heading"/></content>
<meta name="menu" content="JfiSunMemberOrderFeeMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiSunMemberOrderFee">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiSunMemberOrderFee.html"/>?strAction=addJfiSunMemberOrderFee'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiSunMemberOrderFeeListTable"
	items="jfiSunMemberOrderFeeList"
	var="jfiSunMemberOrderFee"
	action="${pageContext.request.contextPath}/jfiSunMemberOrderFees.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiSunMemberOrderFee('${jfiSunMemberOrderFee.mofId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="moId" title="jfiSunMemberOrderFee.moId" />
    			<ec:column property="fee" title="jfiSunMemberOrderFee.fee" />
    			<ec:column property="feeType" title="jfiSunMemberOrderFee.feeType" />
    			<ec:column property="detailType" title="jfiSunMemberOrderFee.detailType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiSunMemberOrderFee(mofId){
   		<jecs:power powerCode="editJfiSunMemberOrderFee">
					window.location="editJfiSunMemberOrderFee.html?strAction=editJfiSunMemberOrderFee&mofId="+mofId;
			</jecs:power>
		}

</script>
