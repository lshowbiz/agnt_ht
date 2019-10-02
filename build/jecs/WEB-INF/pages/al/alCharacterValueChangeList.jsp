<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function updateLan(){
	if(!confirm("<jecs:locale key="alCharacterKey.confirm.update"/>"))
	{
		return false;
	}
	theForm=window.document.getElementById("typeForm1");
	
	
	
	var e = document.createElement("input");
	e.id="strAction";
	e.name="strAction";
	e.type="hidden";
	e.value="updateLan";

	
	
	var strAction = theForm.appendChild(e);
	
	theForm.submit();
}
</script>
<title><jecs:locale key="alCharacterKeyList.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterKeyList.heading"/></content>

<form action="alCharacterValuesChange.html" method="get" name="searchForm" id="searchForm">
<input type="hidden"  name=menuLan  value="${menuLan}" />
<input type="hidden"  name="ec_i"  value="${param.ec_i }" />
<input type="hidden"  name="alCharacterValueChangeListTable_crd"  value="${param.alCharacterValueChangeListTable_crd }" />
<input type="hidden"  name="alCharacterValueChangeListTable_p"  value="${param.alCharacterValueChangeListTable_p }" />
<div id="titleBar" class="searchBar">
	<div class="new_searchBar">	
		<jecs:title key="al.ref.language"/>
		<select name="referral">
			   <option value="">
				<jecs:locale key="list.please.select"/>
				</option>
			<c:forEach items="${alLanguages }" var="alLanguage">
				<c:choose>
					<c:when test="${empty param.referral ? referral==alLanguage.characterCode: param.referral==alLanguage.characterCode}">
						<option value="${alLanguage.characterCode }" selected="selected"><jecs:locale key="${alLanguage.characterName }"/></option> 
					</c:when>
					<c:when test="${param.referral!=alLanguage.characterCode}">
						<option value="${alLanguage.characterCode }"><jecs:locale key="${alLanguage.characterName }"/></option>  
					</c:when>
				</c:choose>
			</c:forEach>
		</select>
	</div>
	<div class="new_searchBar">	
	<jecs:title key="al.ref.language"/><input name="characterValue" id="characterValue" type="text" value="${param.characterValue }"/>
	</div>
	<div class="new_searchBar">	
	<jecs:title key="alCharacterKey.link.editAlCharacterValue"/><input name="characterValueEdit" id="characterValueEdit" type="text" value="${param.characterValueEdit }"/>
	</div>
	<div class="new_searchBar" style="margin-left:45px;">	
		<button name="search" class="btn btn_sele" type="submit" >
			<jecs:locale key="operation.button.search"/>
		</button>
		<button type="button" class="btn btn_ins" onclick="javascript:updateLan()">
			<jecs:locale key="operation.button.save"/>
		</button>
	</div>

</div>






</form>

<form action="alCharacterValuesChange.html" method="post" name="typeForm1" id="typeForm1" >

<input type="hidden"  name="menuLan"  value="${menuLan}" />
<input type="hidden"  name="referral"  value="${empty param.referral ? referral: param.referral}" />

<ec:table tableId="alCharacterValueChangeListTable"
	items="alCharacterValueChangeList" 
	autoIncludeParameters="false"
	method="get"
	var="alVar"
	retrieveRowsCallback="limit"
	form="searchForm"
	action="${pageContext.request.contextPath}/al/alCharacterValuesChange.html"
	width="100%"
	rowsDisplayed="20"
	sortable="false"
	
	imagePath="${pageContext.request.contextPath}/images/extremetable/*.gif" 
	>
	
	<ec:row>
<ec:column property="rowcount" cell="rowCount" sortable="false" title="title.index" width="4%" styleClass="centerColumn"/> 
		
		
		<ec:column property="zhong" title="alCharacterKey.zh_CN" width="30%"/>
		
		<ec:column property="${fn:toLowerCase(referral) }" title="alCharacterKey.${referral }" width="30%"/>
		
		
		<ec:column property="${fn:toLowerCase(menuLan) }" title="alCharacterKey.${menuLan}">
		
			<textarea name="lanVal" id="lanVal" rows="3" cols="50" >${alVar[fn:toLowerCase(menuLan)] }</textarea>
			
			<input type="hidden" value="${alVar[elf:append(fn:toLowerCase(menuLan),'_value_id')]}" name="lanKey" id="lanKey"/>
			
			<input type="hidden" value="${alVar['key_id'] }" name="keyId" id="keyId"/>
		</ec:column>
		
	</ec:row>
</ec:table>
</form>

