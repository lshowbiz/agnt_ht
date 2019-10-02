<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmProductSaleImageDetail.title" /></title>
<content tag="heading">
<jecs:locale key="jpmProductSaleImageDetail.heading" />
</content>

<c:choose>
	<c:when test="${uniNoStr!=null && uniNoStr!='' && uniNoStr!='null'}">
		<script type="text/javascript">
			location.href='jpmProductSaleImages.html?strAction=confirmJpmProductSaleImage&uniNoStr=${uniNoStr }&status2=${status2 }';
		</script>
	</c:when>
	<c:otherwise>
<%-- 
<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<input type="submit" class="button" name="save"
			onclick="bCancel=false"
			value="<jecs:locale key="operation.button.save"/>" />
	</jecs:power>
	<!--<jecs:power powerCode="deleteJpmProductSaleImage">
		<input type="submit" class="button" name="delete"
			onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleImage')"
			value="<jecs:locale key="operation.button.delete"/>" />
	</jecs:power>-->
	<input type="button" class="button" name="back"
		onclick="javascript:history.back();"
		value="<jecs:locale  key="operation.button.return"/>" />
</c:set>
--%>
<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<button type="submit" class="btn btn_sele" name="save"
			onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
	</jecs:power>
	<!--<jecs:power powerCode="deleteJpmProductSaleImage">
		<input type="submit" class="button" name="delete"
			onclick="bCancel=true;return confirmDelete(this.form,'JpmProductSaleImage')"
			value="<jecs:locale key="operation.button.delete"/>" />
	</jecs:power>-->
	<button type="button" class="btn btn_sele" name="back"
		onclick="javascript:history.back();"><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="jpmProductSaleImage.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="/images/iconWarning.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>

<form:form commandName="jpmProductSaleImage" method="post"
	action="editJpmProductSaleImage.html"
	enctype="multipart/form-data"
	onsubmit="return validateJpmProductSaleImage(this) && waiting();"
	id="jpmProductSaleImageForm">
<%-- 
	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false" />
	</div>
--%>	
	<input type="hidden" name="strAction" value="${param.strAction }" />

	<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleImage')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
	<table class='detail' width="70%">
		<tbody class="window">
		<form:hidden path="imageId" />

			
			<c:choose>
				<c:when test="${param.strAction=='editJpmProductSaleImage'}">
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.imageLink" /></span>
						</th>
						<td colspan="20">
							<span class="textbox"><input type="file" class="textbox-text" name="file2" id="file2"/></span>
							<form:hidden path="imageLink" />
							<img src="${FILE_URL }${jpmProductSaleImage.imageLink }" onclick="suonvtuShow('${FILE_URL }${jpmProductSaleImage.imageLink }');" width="150" height="150" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>"/>
						
						</td>
					</tr>
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.productNo" /></span>
						</th>
						<td>
							<c:choose>
								<c:when test="${jpmProductSaleImage.jpmProductSaleNew.jpmProduct.productNo!=null}">
									<span class="textbox"><span class="textbox-text">${jpmProductSaleImage.jpmProductSaleNew.jpmProduct.productNo }</span></span>
								</c:when>
								<c:otherwise>
									<span class="textbox"><form:input path="jpmProductSaleNew.jpmProduct.productNo" id="jpmProductSaleNew.jpmProduct.productNo" cssClass="textbox-text" onclick="selectProduct();"/></span>
									<button type="button" class="btn btn_sele" name="select"
										onclick="selectProduct();" ><jecs:locale key="button.select"/></button>
									<!--<form:hidden id="jpmProductSaleNew.uniNo" path="jpmProductSaleNew.uniNo" />-->
									<form:hidden path="uniNo" id="uniNo"/>
								</c:otherwise>
							</c:choose>
						</td>
			
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.imageType" /></span>
						</th>
						<td>
							<!--form:errors path="imageType" cssClass="fieldError"/-->
							
							<span class="textbox"><jecs:list styleClass="textbox-text" listCode="productimage.imagetype" name="imageType" id="imageType"
								showBlankLine="false" value="${jpmProductSaleImage.imageType}"
								defaultValue="1" disabled="true"/></span> 
						
						</td>
					</tr>
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.sortFlag" /></span>
						</th>
						<td>
							<!--form:errors path="imageOrder" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="imageOrder" id="imageOrder" cssClass="textbox-text" />
							</span>
						</td>
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.status" /></span>
						</th>
						<td>
							<span class="textbox"><jecs:list styleClass="textbox-text" listCode="jmimemberteam.status" name="status" id="status"
								showBlankLine="false" value="${jpmProductSaleImage.status}"
								defaultValue="0"  disabled="true"/></span>
						</td>
					</tr>
				</c:when>
				<c:when test="${param.strAction=='confirmJpmProductSaleImage'}">
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.imageLink" /></span>
						</th>
						<td colspan="20">
							<span class="textbox"><input type="file" class="textbox-text" name="file2" id="file2"/></span>
							<form:hidden path="imageLink" />
							<img src="${FILE_URL }${jpmProductSaleImage.imageLink }" onclick="suonvtuShow('${FILE_URL }${jpmProductSaleImage.imageLink }');" width="150" height="150" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>"/>
						
						</td>
						
					</tr>
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.productNo" /></span>
						</th>
						<td>
							<c:choose>
								<c:when test="${jpmProductSaleImage.jpmProductSaleNew.jpmProduct.productNo!=null}">
									<span class="textbox"><span class="textbox-text">${jpmProductSaleImage.jpmProductSaleNew.jpmProduct.productNo }</span></span>
								</c:when>
								<c:otherwise>
									<span class="textbox"><form:input path="jpmProductSaleNew.jpmProduct.productNo" id="jpmProductSaleNew.jpmProduct.productNo" cssClass="textbox-text" onclick="selectProduct();"/></span>
									<button type="button" class="btn btn_sele" name="select"
										onclick="selectProduct();" ><jecs:locale key="button.select"/></button>
									<!--<form:hidden id="jpmProductSaleNew.uniNo" path="jpmProductSaleNew.uniNo" />-->
									<form:hidden path="uniNo" id="uniNo"/>
								</c:otherwise>
							</c:choose>
						</td>
					
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.imageType" /></span>
						</th>
						<td>
							<!--form:errors path="imageType" cssClass="fieldError"/-->
							<span class="textbox"><jecs:list styleClass="textbox-text" listCode="productimage.imagetype" name="imageType" id="imageType"
									showBlankLine="false" value="${jpmProductSaleImage.imageType}"
									defaultValue="1" /></span> 
							
						</td>
					</tr>
				<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.sortFlag" /></span>
						</th>
						<td>
							<!--form:errors path="imageOrder" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="imageOrder" id="imageOrder" cssClass="textbox-text" /></span>
							
						</td>
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.status" /></span>
						</th>
						<td>
							<span class="textbox"><jecs:list styleClass="textbox-text" listCode="jmimemberteam.status" name="status" id="status"
								showBlankLine="false" value="${jpmProductSaleImage.status}"
								defaultValue="0"/></span>
						</td>
					</tr>
					
				</c:when>
				<c:otherwise>
						<tr class="edit_tr">
							<th>
								<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.imageLink" /></span>
							</th>
							<td>
								<form:hidden path="imageLink" />
								<span class="textbox"><input type="file" name="file" id="file" class="textbox-text"/></span>
							</td>
							
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.sortFlag" /></span>
						</th>
						<td>
							<!--form:errors path="imageOrder" cssClass="fieldError"/-->
							<span class="textbox"><form:input path="imageOrder" id="imageOrder" cssClass="textbox-text" />
							</span>
						</td>
					</tr> 	
					
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc"><jecs:label key="jpmProductSaleNew.productNo" /></span>
						</th>
							<td>
								<c:choose>
									<c:when test="${jpmProductSaleImage.jpmProductSaleNew.jpmProduct.productNo!=null}">
										<span class="textbox"><span class="textbox-text">${jpmProductSaleImage.jpmProductSaleNew.jpmProduct.productNo }</span></span>
									</c:when>
									<c:otherwise>
										<span class="textbox"><form:input path="jpmProductSaleNew.jpmProduct.productNo" id="jpmProductSaleNew.jpmProduct.productNo" cssClass="textbox-text" onclick="selectProduct();"/></span>
										<button type="button" class="btn btn_sele" name="select"
											onclick="selectProduct();" ><jecs:locale key="button.select"/></button>
										<!--<form:hidden id="jpmProductSaleNew.uniNo" path="jpmProductSaleNew.uniNo" />-->
										<form:hidden path="uniNo" id="uniNo"/>
									</c:otherwise>
								</c:choose>
							</td>
			
							<th>
								<span class="text-font-style-tc"><jecs:label key="jpmProductSaleImage.imageType" /></span>
							</th>
							<td>
								<!--form:errors path="imageType" cssClass="fieldError"/-->
								<c:choose>
									<c:when test="${param.strAction=='editJpmProductSaleImage'}">
										<span class="textbox"><jecs:list styleClass="textbox-text" listCode="productimage.imagetype" name="imageType" id="imageType"
										showBlankLine="false" value="${jpmProductSaleImage.imageType}"
										defaultValue="1" disabled="true"/></span> 
									</c:when>
									<c:otherwise>
										<span class="textbox"><jecs:list styleClass="textbox-text" listCode="productimage.imagetype" name="imageType" id="imageType"
										showBlankLine="false" value="${jpmProductSaleImage.imageType}"
										defaultValue="1" /></span> 
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
				
					</c:otherwise>
				</c:choose>
			
			
			
			<tr>		
			<td class="command" colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
		</tr>
		</tbody>
	</table>
	<!--/fieldset-->

	<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductSaleImage')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>
 </c:otherwise>
</c:choose>
<script type="text/javascript">
    Form.focusFirstElement($('jpmProductSaleImageForm'));
    
     function selectProduct() {
		open(
				"<c:url value='/jpmProducts.html?strAction=selectProduct&selectControl=2'/>",
				"",
				"height=700, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
	}
	
	function suonvtuShow(suonvtu) {	
		document.getElementById("suonvtuFrame").innerHTML="<iframe src='"+suonvtu+"' width='600' align='middle' frameborder='0' height='400' scrolling='auto'></iframe>";
   		var width = (document.body.clientWidth - 728) / 2;
   		var suonvtuId = document.getElementById("suonvtuId");
   		suonvtuId.style.width=728;
		suonvtuId.style.left = width;    		
   		suonvtuId.style.display="";			    	
   	}
   	
   	function removeSuonvtu() {
		document.getElementById("suonvtuId").style.display = "none";
	}

	function validateJpmProductSaleImage(){
		waiting();
		return true;
	}

	function waiting(){
		disableButton();
		var o = document.createElement('iframe');
		o.id = 'fram_bk';
		document.body.appendChild(o);
		with ($('fram_bk').style){
			display='block';
			top = "60px";
			left = "30%";
		}
		var el=document.getElementById("sending_sub");
		if (el.hasChildNodes()){
		  el.removeChild(el.lastChild);
		}
		
		el.appendChild( document.createTextNode('<jecs:locale key="button.loading"/>'));
		document.getElementById("sending").style.visibility="visible";
		document.getElementById("sending").style.display="block";
	}
</script>

<v:javascript formName="jpmProductSaleImage" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>

<div style="position: absolute; top: 10px; display: none;" id="suonvtuId">
	<table style="border: 4px #C4C4C4 solid; width: 460px; height: 260px;"
		cellspacing="0" cellpadding="0">
		<tr>
			<td align="right"
				style="background-color: #8D8D8D; cursor: hand; height: 24px;"
				onclick="removeSuonvtu();">
				<img src="/jecs/images/close.gif" height="10px;" width="10px;" />
				<font style="color: #ffffff; font-size: 12px;">close</font>&nbsp;&nbsp;
				<br />
			</td>
		</tr>
		<tr>
			<td bgcolor="#F6F6F6" id="suonvtuFrame">
				<!-- <iframe src="selectrequriment.action?requireId=1" width="600" align="middle" frameborder="0" height="400" scrolling="auto"></iframe>
					 -->
			</td>
		</tr>
	</table>
</div>
