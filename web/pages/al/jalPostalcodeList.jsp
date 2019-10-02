<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalPostalcodeList.title"/></title>
<content tag="heading"><jecs:locale key="jalPostalcodeList.heading"/></content>
<meta name="menu" content="JalPostalcodeMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJalPostalcode">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJalPostalcode.html"/>?strAction=addJalPostalcode'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jalPostalcodeListTable"
	items="jalPostalcodeList"
	var="jalPostalcode"
	action="${pageContext.request.contextPath}/jalPostalcodes.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJalPostalcode('${jalPostalcode.postalcodeId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="cityId" title="jalPostalcode.cityId" />
    			<ec:column property="postalcode" title="jalPostalcode.postalcode" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJalPostalcode(postalcodeId){
   		<jecs:power powerCode="editJalPostalcode">
					window.location="editJalPostalcode.html?strAction=editJalPostalcode&postalcodeId="+postalcodeId;
			</jecs:power>
		}

</script>
