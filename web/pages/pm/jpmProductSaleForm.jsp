<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pmProductSaleDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="pmProductSaleDetail.heading" />
</content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<c:set var="buttons">

	<c:choose>
		<c:when test="${param.strAction =='confirmJpmProductSale'}">
			<jecs:power powerCode="confirmJpmProductSale">
				<input type="button" class="button" name="confirm"
					onclick="editJpmProductSale('confirm')"
					value="<jecs:locale  key='pmProductSale.confirm'/>" />

			</jecs:power>
		</c:when>

		<c:otherwise>
			<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save" onclick="bCancel = false;" value="<jecs:locale key='operation.button.save'/>" />
			</jecs:power>
		</c:otherwise>
	</c:choose>

	<input type="button" class="button" name="back" onclick="javascript:history.back();" value="<jecs:locale  key="operation.button.return"/>" />


</c:set>

<spring:bind path="jpmProductSale.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="images/newIcons/warning_16.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>

<form:form commandName="jpmProductSale" method="post"
	action="editJpmProductSale.html"
	onsubmit="return validateJpmProductSale(this)" id="jpmProductSaleForm">

	<div id="titleBar">
		<c:if test="${param.strAction=='confirmJpmProductSale'}">

			<jecs:label key="piProduct.statusNo" />


			<jecs:list listCode="pmproduct.statusno" name="status" id="status"
				value="${jpmProductSale.status}" defaultValue="1" />

		</c:if>
		<c:out value="${buttons}" escapeXml="false" />
	</div>
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSale')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->


	<form:hidden path="uniNo" />
	<fieldset style="padding: 2">
		<legend>
			&nbsp;
		</legend>
		<table class='detail'>
			<c:if test="${sessionScope.sessionLogin.companyCode == 'AA'}">
				<tr>
					<th width='200'>
						<jecs:label key="sys.company.code" />
					</th>
					<td align="left">
						<!--form:errors path="companyCode" cssClass="fieldError"/-->

						<jecs:company name="companyCode"
							selected="${jpmProductSale.companyCode}" prompt="" withAA="true"
							label="${disabled}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${sessionScope.sessionLogin.companyCode != 'AA'}">
				<form:hidden id="companyCode" path="companyCode" />
			</c:if>

			<tr>
				<th width='200'>
					<jecs:label key="busi.product.productno" />
				</th>
				<td align="left">
					<!--form:errors path="companyN0" cssClass="fieldError"/-->
					<c:if test="${jpmProductSale.jpmProduct.productNo != null}">
        	${jpmProductSale.jpmProduct.productNo}
        </c:if>
					<c:if test="${jpmProductSale.jpmProduct.productNo == null}">
						<form:input path="jpmProduct.productNo" disabled="${disabled}"
							readonly="true" id="productNo" onclick="javascript:alert;"
							cssClass="text medium" />
						<input type="button" class="button" name="select"
							onclick=selectProduct();
							value="<jecs:locale key="button.select"/>" />
					</c:if>
				</td>
			</tr>


			<tr>
				<th width='200'>
					<jecs:label key="pmProduct.productName" />
				</th>
				<td align="left">
					<!--form:errors path="productName" cssClass="fieldError"/-->
					<form:input path="productName" id="productName"
						disabled="${disabled}" cssClass="text medium" />
				</td>
			</tr>


			<tr>
				<th width='200'>
					<jecs:label key="pmProduct.onsale.start" />
				</th>
				<td align="left">
					<!--form:errors path="productName" cssClass="fieldError"/-->
					<form:input path="startOnSale" id="startOnSale" readonly="true"
						cssClass="text medium" />
					<img src="./images/calendar/calendar7.gif" id="img_startTime"
						style="cursor: pointer;"
						title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
					<script type="text/javascript">
	Calendar.setup( {
		inputField : "startOnSale",
		ifFormat : "%Y-%m-%d",
		button : "img_startTime",
		singleClick : true
	});
</script>
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="pmProduct.onsale.end" />
				</th>
				<td align="left">
					<!--form:errors path="productName" cssClass="fieldError"/-->
					<form:input path="endOnSale" id="endOnSale" readonly="true"
						cssClass="text medium" />
					<img src="./images/calendar/calendar7.gif" id="img_endTime"
						style="cursor: pointer;"
						title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" />
					<script type="text/javascript">
	Calendar.setup( {
		inputField : "endOnSale",
		ifFormat : "%Y-%m-%d",
		button : "img_endTime",
		singleClick : true
	});
</script>
				</td>
			</tr>


			<tr>
				<th width='200'>
					<jecs:label key="pdShipStrategyDetail.ssId" />
				</th>
				<td align="left">
					<!--form:errors path="productName" cssClass="fieldError"/-->

					<jecs:list listCode="ship.strategy" name="shipStrategy"
						id="shipStrategy" showBlankLine="false"
						value="${jpmProductSale.shipStrategy}" defaultValue="1" />
				</td>
			</tr>

			<c:if test="${param.strAction=='editStorageCordon'}">
				<tr>
					<th width='200'>
						<jecs:label key="pmProduct.storageCordon" />
					</th>
					<td align="left">
						<!--form:errors path="productName" cssClass="fieldError"/-->
						<form:input path="storageCordon" id="storageCordon"
							cssClass="text medium" />
					</td>
				</tr>
			</c:if>


			<!-----------ÐÂÔö------------------------>
			<tr>
				<th width='200'>
					<jecs:label key="pm.hotFlag" />
				</th>
				<td align="left">

					<jecs:list listCode="yesno" name="hotFlag" id="hotFlag"
						showBlankLine="false" value="${jpmProductSale.hotFlag}"
						defaultValue="" />
				</td>
			</tr>
			<!-- Modify By WuCF 20130517 -->
			<tr>
				<th width='200'>
					<jecs:label key="pm.ishidden" />
				</th>
				<td align="left">

					<jecs:list listCode="yesno" name="isHidden" id="isHidden"
						showBlankLine="false" value="${jpmProductSale.isHidden}"
						defaultValue="" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="sysListValue.orderNo" />
				</th>
				<td align="left">

					<form:input path="sortFlag" id="sortFlag" cssClass="text medium" />

				</td>
			</tr>

		</table>
	</fieldset>


	<fieldset style="padding: 2">
		<legend>
			<jecs:label key="register.us.legend.member" />
		</legend>
		<table class='detail'>
			<tr>
				<th width='200'>
					<jecs:label key="busi.direct.price.first.purchase" />
				</th>
				<td align="left">
					<!--form:errors path="fpPrice" cssClass="fieldError"/-->
					<form:input path="fpPrice" disabled="${disabled}" id="fpPrice"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.product.pv" />
				</th>
				<td align="left">
					<!--form:errors path="fpPv" cssClass="fieldError"/-->
					<form:input path="fpPv" disabled="${disabled}" id="fpPv"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="piProduct.rePrice" />
				</th>
				<td align="left">
					<!--form:errors path="mpPrice" cssClass="fieldError"/-->
					<form:input path="mpPrice" disabled="${disabled}" id="mpPrice"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="piProduct.rePv" />
				</th>
				<td align="left">
					<!--form:errors path="mpPv" cssClass="fieldError"/-->
					<form:input path="mpPv" disabled="${disabled}" id="mpPv"
						cssClass="text medium" />
				</td>
			</tr>
		</table>
	</fieldset>

	<fieldset style="padding: 2">
		<legend>
			<jecs:label key="busi.common.store" />
		</legend>
		<table class='detail'>
			<tr>
				<th width='200'>
					<jecs:label key="pmProductSale.storeFpPrice" />
				</th>
				<td align="left">
					<!--form:errors path="storeFpPrice" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="storeFpPrice"
						id="storeFpPrice" cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="pmProductSale.storeFpPv" />
				</th>
				<td align="left">
					<!--form:errors path="storeFpPv" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="storeFpPv" id="storeFpPv"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="pmProductSale.storeMpPrice" />
				</th>
				<td align="left">
					<!--form:errors path="storeMpPrice" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="storeMpPrice"
						id="storeMpPrice" cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="pmProductSale.storeMpPv" />
				</th>
				<td align="left">
					<!--form:errors path="storeMpPv" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="storeMpPv" id="storeMpPv"
						cssClass="text medium" />
				</td>
			</tr>
		</table>
	</fieldset>

	<fieldset style="padding: 2">
		<legend>
			<jecs:locale key="busi.mi.substore" />
		</legend>
		<table class='detail'>
			<tr>
				<th width='200'>
					<jecs:label key="busi.direct.price.first.purchase" />
				</th>
				<td align="left">
					<!--form:errors path="storeFpPrice" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="substoreFpPrice"
						id="substoreFpPrice" cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.product.pv" />
				</th>
				<td align="left">
					<!--form:errors path="storeFpPv" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="substoreFpPv"
						id="substoreFpPv" cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="piProduct.rePrice" />
				</th>
				<td align="left">
					<!--form:errors path="storeMpPrice" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="substoreMpPrice"
						id="substoreMpPrice" cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="piProduct.rePv" />
				</th>
				<td align="left">
					<!--form:errors path="storeMpPv" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="substoreMpPv"
						id="substoreMpPv" cssClass="text medium" />
				</td>
			</tr>
		</table>
	</fieldset>

	<fieldset style="padding: 2">
		<legend>
			<jecs:label key="busi.pm.info" />
		</legend>
		<table class='detail'>
			<tr>
				<th width='200'>
					<jecs:label key="piProduct.whoPrice" />
				</th>
				<td align="left">
					<!--form:errors path="whoPrice" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="whoPrice" id="whoPrice"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="pmProduct.discountPrice" />
				</th>
				<td align="left">
					<!--form:errors path="discountPrice" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="discountPrice"
						id="discountPrice" cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.pd.weight" />
				</th>
				<td align="left">
					<!--form:errors path="weight" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="weight" id="weight"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.pd.volume" />
				</th>
				<td align="left">
					<!--form:errors path="volume" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="volume" id="volume"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.common.length" />
				</th>
				<td align="left">
					<!--form:errors path="length" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="length" id="length"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.common.width" />
				</th>
				<td align="left">
					<!--form:errors path="width" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="width" id="width"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.common.height" />
				</th>
				<td align="left">
					<!--form:errors path="height" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="height" id="height"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.pm.imageLink" />
				</th>
				<td align="left">
					<!--form:errors path="imageLink" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="imageLink" id="imageLink"
						cssClass="text large" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.pm.albumLink" />
				</th>
				<td align="left">
					<!--form:errors path="albumLink" cssClass="fieldError"/-->
					<form:input disabled="${disabled}" path="albumLink" id="albumLink"
						cssClass="text large" />
				</td>
			</tr>

		</table>
	</fieldset>

	<fieldset style="padding: 2">
		<legend>
			<jecs:label key="busi.product.sale.control" />
		</legend>
		<table class='detail'>
			<!-- Add By WuCF 团队选择 -->
			<!--<tr>
				<th>
					<jecs:label key="busi.product.sale.memberteam" />
				</th>
				<td align="left">
					<c:forEach items="${list }" var="mp" varStatus="status">
						<input type="checkbox" name="jmiMemberTeams" value="${fn:substring(mp.code,0,fn:length(mp.code)-1)}" ${fn:endsWith(mp.code,'#') ? '':'checked'}/>${mp.name }&nbsp;&nbsp;&nbsp;&nbsp;${status.count%8==0 ? '<br/>':'' }
					</c:forEach>
				</td>
			</tr>-->
			<tr>
				<th>
					<jecs:label key="busi.product.sale.control" />
				</th>
				<td align="left">
					<form:checkbox path="controlFirst" value="1" disabled="${disabled}"
						id="controlFirst" />
					<jecs:label key="busi.direct.first.purchase" />

					<form:checkbox path="controlUpdate" value="1"
						disabled="${disabled}" id="controlUpdate" />
					<jecs:label key="busi.bdPinTitleRecord.upgrade" />

					<form:checkbox path="controlRepurchase" value="1"
						disabled="${disabled}" id="controlRepurchase" />
					<jecs:label key="pmProduct.controlRepurchase" />

					<form:checkbox path="controlAutosale" value="1"
						disabled="${disabled}" id="controlAutosale" />
					<jecs:label key="busi.pm.controlAutosale" />

				</td>
			</tr>

			<tr>
				<th>
					<jecs:locale key="busi.common.store" />
					<jecs:label key="busi.product.sale.control" />
				</th>
				<td align="left">
					<form:checkbox path="storeControlFirst" value="1"
						disabled="${disabled}" id="storeControlFirst" />
					<jecs:label key="busi.direct.first.purchase" />

					<form:checkbox path="storeControlUpdate" value="1"
						disabled="${disabled}" id="storeControlUpdate" />
					<jecs:label key="busi.bdPinTitleRecord.upgrade" />

					<form:checkbox path="storeControlRepurchase" value="1"
						disabled="${disabled}" id="storeControlRepurchase" />
					<jecs:label key="pmProduct.controlRepurchase" />



				</td>
			</tr>

			<tr>
				<th>
					<jecs:locale key="busi.mi.substore" />
					<jecs:label key="busi.product.sale.control" />
				</th>
				<td align="left">
					<form:checkbox path="subStoreFirst" value="1"
						disabled="${disabled}" id="subStoreFirst" />
					<jecs:label key="busi.direct.first.purchase" />

					<form:checkbox path="subStoreUpdate" value="1"
						disabled="${disabled}" id="subStoreUpdate" />
					<jecs:label key="busi.bdPinTitleRecord.upgrade" />

					<form:checkbox path="subStoreRepurchase" value="1"
						disabled="${disabled}" id="subStoreRepurchase" />
					<jecs:label key="pmProduct.controlRepurchase" />



				</td>
			</tr>

			<tr>
				<th>
					<jecs:locale key="function.menu.agent.order.poIROrder" />
					<jecs:label key="busi.product.sale.control" />
				</th>
				<td align="left">
					<form:checkbox path="controlPointExchange" value="1"
						disabled="${disabled}" id="controlPointExchange" />
				</td>
			</tr>

			<tr>
				<th width='200'>
					<jecs:label key="busi.common.remark" />
				</th>
				<td align="left">
					<!--form:errors path="remark" cssClass="fieldError"/-->
					<form:textarea cols='70' rows='3' path="remark" id="remark"
						cssClass="text medium" />
				</td>
			</tr>

			<tr>
				<th class="command">
					<jecs:label key="sysOperationLog.moduleName" />
				</th>
				<td class="command">
					<c:out value="${buttons}" escapeXml="false" />
				</td>
			</tr>
		</table>
	</fieldset>

	<!--/fieldset-->

	<!--table class='detail' width="50%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSale')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
	Form.focusFirstElement($('jpmProductSaleForm'));

	function editJpmProductSale(flag) {

		$('jpmProductSaleForm').submit();
	}
	function toback(strAction) {
		if (strAction == 'addJpmProductSale') {
			strAction = 'editJpmProductSale';
		}
		this.location = '<c:url value="/jpmProductSales.html"/>?strAction=' + strAction;
	}
	function selectProduct() {
		open(
				"<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=1'/>",
				"",
				"height=400, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	}
</script>

<v:javascript formName="jpmProductSale" cdata="false"
	dynamicJavascript="true" staticJavascript="false" /> 
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
