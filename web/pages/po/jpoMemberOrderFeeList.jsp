<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderFeeList.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderFeeList.heading"/></content>
<meta name="menu" content="JpoMemberOrderFeeMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJpoMemberOrderFee">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJpoMemberOrderFee.html"/>?strAction=addJpoMemberOrderFee'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jpoMemberOrderFeeListTable"
	items="jpoMemberOrderFeeList"
	var="jpoMemberOrderFee"
	action="${pageContext.request.contextPath}/jpoMemberOrderFees.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJpoMemberOrderFee('${jpoMemberOrderFee.mofId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="moId" title="jpoMemberOrderFee.moId" />
    			<ec:column property="fee" title="jpoMemberOrderFee.fee" />
    			<ec:column property="feeType" title="jpoMemberOrderFee.feeType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpoMemberOrderFee(mofId){
   		<jecs:power powerCode="editJpoMemberOrderFee">
					window.location="editJpoMemberOrderFee.html?strAction=editJpoMemberOrderFee&mofId="+mofId;
			</jecs:power>
		}

</script>
