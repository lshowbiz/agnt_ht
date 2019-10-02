<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmCombinationRelatedList.title"/></title>
<content tag="heading"><jecs:locale key="jpmCombinationRelatedList.heading"/></content>
<meta name="menu" content="JpmCombinationRelatedMenu"/>

<form name="frm" id="frm"
	action="<c:url value='/jpmCombinationRelateds.html?strAction=${strAction }'/>">
	<input type="hidden" name="strAction" value="${param.strAction }" />
	<c:set var="buttons">
			<button type="submit" class="btn btn_sele" style="width:auto"><jecs:locale  key='operation.button.search'/></button>
			<jecs:power powerCode="addJpmCombinationRelated">
				    <button type="button" class="btn btn_ins" style="width:auto"
				        onclick="location.href='<c:url value="/editJpmCombinationRelated.html"/>?strAction=addJpmCombinationRelated'"
				        ><jecs:locale key="button.add"/></button>
				    <button type="button" class="btn btn_long" style="width:auto" onclick="importFile('7');"><jecs:locale  key='piliang.shangchuan'/></button>
			</jecs:power>
	</c:set>
		<div id="titleBar">
			<div class="new_searchBar">
				<jecs:title key="jpmCombinationRelated.productNo" />
				<input name="productNo" id="productNo"
					value="<c:out value='${param.productNo }'/>" cssClass="text medium" maxlength="20"/>
			</div>
			<div class="new_searchBar">
				<jecs:title key="jpmCombinationRelated.productName"/>
				<input name="productName" id="productName"
					value="<c:out value='${param.productName }'/>" cssClass="text medium"  maxlength="20"/>
			</div>
			<div class="new_searchBar">		
				<jecs:title key="jpmCombinationRelated.isFree"/>
				<jecs:list listCode="yesno" name="isFree" id="isFree" showBlankLine="true" value="${param.isFree }" defaultValue=""/>
			</div>	
			<div class="new_searchBar">
				<jecs:title key="jpmCombinationRelated.subProductNo" />
				<input name="subProductNo" id="subProductNo"
					value="<c:out value='${param.subProductNo }'/>" cssClass="text medium"  maxlength="20"/>
			</div>
			<div class="new_searchBar">
				<jecs:title key="jpmCombinationRelated.subProductName" />
				<input name="subProductName" id="subProductName"
					value="<c:out value='${param.subProductName }'/>" cssClass="text medium"  maxlength="20"/>
			</div>	
			<div class="new_searchBar">
				<jecs:title key="jpmCombinationRelated.productDate" />
				<input name="productDate" id="productDate"
					value="<c:out value='${param.productDate }'/>" cssClass="text medium"  maxlength="20"/>
			</div>	
			<div class="new_searchBar">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<c:out value="${buttons}" escapeXml="false"/>
			</div>
	</div>
	
<!--  
	<div id="titleBar">
		<jecs:title key="jpmCombinationRelated.productNo" />
		<input name="productNo" id="productNo"
			value="<c:out value='${param.productNo }'/>" cssClass="text medium" maxlength="20"/>
		<jecs:title key="jpmCombinationRelated.productName"/>
		<input name="productName" id="productName"
			value="<c:out value='${param.productName }'/>" cssClass="text medium"  maxlength="20"/>
			
		<jecs:title key="jpmCombinationRelated.isFree"/>
		<jecs:list listCode="yesno" name="isFree" id="isFree" showBlankLine="true" value="${param.isFree }" defaultValue=""/>
		
		<br/>
		<jecs:title key="jpmCombinationRelated.subProductNo" />
		<input name="subProductNo" id="subProductNo"
			value="<c:out value='${param.subProductNo }'/>" cssClass="text medium"  maxlength="20"/>
		<jecs:title key="jpmCombinationRelated.subProductName" />
		<input name="subProductName" id="subProductName"
			value="<c:out value='${param.subProductName }'/>" cssClass="text medium"  maxlength="20"/>
		
		
		<jecs:title key="jpmCombinationRelated.productDate" />
		<input name="productDate" id="productDate"
			value="<c:out value='${param.productDate }'/>" cssClass="text medium"  maxlength="20"/>
		

		<c:out value="${buttons}" escapeXml="false"/>
	</div>
-->
</form>
<ec:table 
	tableId="jpmCombinationRelatedListTable"
	items="jpmCombinationRelatedList"
	var="jpmCombinationRelated"
	action="${pageContext.request.contextPath}/jpmCombinationRelateds.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:exportXls fileName="bdBounsDeduct.xls"/>
				<ec:row >
	    			<ec:column property="productNo" title="jpmCombinationRelated.productNo" style="white-space:nowrap;"/>
	    			<ec:column property="productName" title="jpmCombinationRelated.productName" style="white-space:nowrap;">
	    				<jecs:substr key="${jpmCombinationRelated.productName }" length="15"/>
	    			</ec:column>
	    			<ec:column property="productDate" title="jpmCombinationRelated.productDate"/>
	    			<ec:column property="subProductNo" title="jpmCombinationRelated.subProductNo" style="white-space:nowrap;"/>
	    			<ec:column property="subProductName" title="jpmCombinationRelated.subProductName" style="white-space:nowrap;">
	    				<jecs:substr key="${jpmCombinationRelated.subProductName }" length="15"/>
	    			</ec:column>
	    			<ec:column property="qty" title="jpmCombinationRelated.qty" />
	    			<ec:column property="price" title="jpmCombinationRelated.price" />
	    			<ec:column property="pv" title="jpmCombinationRelated.pv" />
	    			<ec:column property="totalPrice" title="jpmCombinationRelated.totalPrice" />
	    			<ec:column property="totalPv" title="jpmCombinationRelated.totalPv" />
	    			<ec:column property="isFree" title="jpmCombinationRelated.isFree" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
	    				<jecs:code listCode="yesno" value="${jpmCombinationRelated.isFree}"/>
	    			</ec:column>
	    			<ec:column property="createUserCode" title="pd.createUserNo" />
	    			<ec:column property="createTime" title="pd.createTime" style="white-space:nowrap;">
	    				<fmt:formatDate value="${jpmCombinationRelated.createTime}" pattern="yyyy-MM-dd"/>
	    			</ec:column>
	    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmCombinationRelated('${jpmCombinationRelated.uniNo}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmCombinationRelated(uniNo){
   		<jecs:power powerCode="editJpmCombinationRelated">
					window.location="editJpmCombinationRelated.html?strAction=editJpmCombinationRelated&uniNo="+uniNo;
			</jecs:power>
		}

   function importFile(flagnum){
		window.location="<c:url value='/pdFileUpload2.html?strAction=uploadjpmcombinationrelated&flagnum="+flagnum+"'/>";
   }
</script>
