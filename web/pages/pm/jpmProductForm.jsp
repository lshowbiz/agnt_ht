<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/dwr/interface/jpmProductManager.js'/>"></script>

<title><jecs:locale key="pmProductDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="pmProductDetail.heading" />
</content>


<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<button type="button" class="btn btn_sele" name="save"
			onclick="submitMe();">
			<jecs:locale key="operation.button.save" />
		</button>
	</jecs:power>
	<button type="button" class="btn btn_sele" name="back"
			onclick="javascript:history.back();">
		<jecs:locale key="operation.button.return" />
	</button>
</c:set>

<spring:bind path="jpmProduct.*">
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

<form:form commandName="jpmProduct" method="post"
	action="editJpmProduct.html" onsubmit="return validateJpmProduct(this)"
	id="jpmProductForm">


	<input type="hidden" name="strAction" value="${param.strAction }" />

	<input type="hidden" name="companySelect" id="companySelect" value="" />
	<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProduct')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
	<table class='detail' width="70%">
		<tbody class="window">
			<tr class="edit_tr">

				<th>
					<span class="text-font-style-tc"><jecs:label
							key="busi.product.productno" />
					</span>
				</th>
				<td>
					<c:if test="${not empty jpmProduct.productNo}">
						<span class="textbox"> <form:input path="productNo"
								readonly="true" id="productNo" cssClass="textbox-text" />
						</span>
					</c:if>
					<c:if test="${empty jpmProduct.productNo}">
						<span class="textbox"><form:input path="productNo"
								id="productNo" cssClass="textbox-text" /> </span>
					</c:if>
					<button type="button" class="btn btn_sele" name="check"
						onclick=
	checkProductNo();;
>
						<jecs:locale key="operation.button.search" />
					</button>
				</td>

				<th>
					<span class="text-font-style-tc"><jecs:label
							key="piProductCategory.categoryNo" />
					</span>
				</th>
				<td>
					<span class="textbox"><jecs:list styleClass="textbox-text"
							listCode="pmproduct.productcategory" name="productCategory"
							id="productCategory" showBlankLine="true"
							value="${jpmProduct.productCategory}" defaultValue="" />
					</span>
				</td>
			</tr>
<%-- 
	屏蔽掉ERP编码和ERP价格相关的
	
	
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label
							key="jpmProduct.erpProductNo" />
					</span>
				</th>
				<td>
					<c:choose>
						<c:when test="${jpmProduct.productNo!=null}">
							<c:choose>
								<c:when test="${sessionLogin.userCode=='root'}">
									<span class="textbox"><form:input path="erpProductNo"
											id="erpProductNo" cssClass="textbox-text" maxlength="20" />
									</span>
								</c:when>
								<c:otherwise>
									<span class="textbox"><form:input path="erpProductNo"
											id="erpProductNo" cssClass="textbox-text" maxlength="20"
											disabled="true" />
									</span>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<span class="textbox"><form:input path="erpProductNo"
									id="erpProductNo" cssClass="textbox-text" maxlength="20" />
							</span>
						</c:otherwise>
					</c:choose>
				</td>

				<th>
					<span class="text-font-style-tc"><font color="red">*</font>&nbsp;<jecs:label
							key="jpmProduct.erpPrice" />
					</span>
				</th>
				<td align="left">
					<span class="textbox"><form:input path="erpPrice"
							id="erpPrice" cssClass="textbox-text"
							onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"
							onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"
							maxlength="12" />
					</span>
				</td>

			</tr>
--%>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label
							key="pmProduct.productName" />
					</span>
				</th>
				<td>
					<span class="textbox"><form:input path="productName"
							id="productName" cssClass="textbox-text" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc"><jecs:label
							key="piProduct.unitNo" />
					</span>
				</th>
				<td>
					<span class="textbox"><jecs:list styleClass="textbox-text"
							listCode="pmproduct.unitno" name="unitNo" id="unitNo"
							value="${jpmProduct.unitNo}" defaultValue="" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="pm.smNo" />
					</span>
				</th>
				<td>
					<span class="textbox"><jecs:list styleClass="textbox-text"
							listCode="pmproduct.smno" name="smNo" id="smNo"
							value="${jpmProduct.smNo}" defaultValue="D" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc"><jecs:label
							key="pm.combineFlag" />
					</span>
				</th>
				<td>
					<span class="textbox"><form:checkbox path="combineFlag"
							value="1" disabled="${disabled}" id="combineFlag" />
					</span>
				</td>
			</tr>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label
							key="piWholesale.locked" />
					</span>
				</th>
				<td colspan="3">
					<span class="textbox"><form:checkbox path="lockFlag"
							value="1" disabled="${disabled}" id="lockFlag" />
					</span>
				</td>

			</tr>

			<tr class="edit_tr">

				<th>
					<span class="text-font-style-tc-tare"><jecs:label
							key="busi.common.remark" />
					</span>
				</th>
				<td colspan="3">
					<span class="text-font-style-tc-right"><form:textarea
							cssClass="textarea_border" path="remark" id="remark" />
					</span>

				</td>
			</tr>

			<tr class="edit_tr">
				<th align="left">
					<span class="edit_tr_span"><span style="margin-left:60px;"><jecs:label key="sys.company.code" /></span></span>
				</th>
				<td align="left" colspan="3">

					<c:forEach items="${companyList}" var="company">
						<div class="edit_tr_div">
							<input type="checkbox" name="cks" value="${company.COMPANY_CODE}">
							${company.COMPANY_NAME}
						</div>
					</c:forEach>
				</td>
			</tr>



			<tr>
				<td class="command" colspan="4" align="center">
					<c:out value="${buttons}" escapeXml="false" />
				</td>
			</tr>
		</tbody>
	</table>
</form:form>

<script type="text/javascript">
	var sFlag=0;
function formatNum(id){                          

       document.getElementById(id).value=document.getElementById(id).value.replace(/,/gi,'');

}
    Form.focusFirstElement($('jpmProductForm'));
    
    
    function submitMe(){
    		<c:if test="${param.strAction=='addJpmProduct'}">
    		DWREngine.setAsync(false);
    			checkProductNo();
    		if(sFlag==1){
    			  return;
    			}	
    		</c:if>
    		var compayList = getSelectedInfoRows('jpmProductForm','cks');
    		$('companySelect').value=compayList;
    		if(validateJpmProduct($('jpmProductForm'))){
    			
    			/*
    			add by lihao ,屏蔽掉ERP编码和ERP价格相关的
    			var erpPrice = $("erpPrice").value;
    	    	if(erpPrice==""){
    	    		alert("erp"+"<jecs:locale key="pdWarehouseStock.productNo"/>"+"<jecs:locale key="pd.price"/>"+"<jecs:locale key="poAssistMemberOrder.notNUll"/>");
    	    		return;
    	    	}
    	    	
    	    	*/
    	    		waiting();
    				$('jpmProductForm').submit();
    			}
    		
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
function getSelectedInfoRows(formId,checkName)
{
 var temp = "";
 for ( var i=0; i<Form.getInputs(formId,'checkbox',checkName).length; i++ )
 {
  var e = Form.getInputs(formId,'checkbox',checkName)[i];
  if((!e.disabled)&&(e.checked)){
  	temp += temp==""? e.value: "," + e.value ;
  	}

 }

 return temp;
}

    function toback(){
    	var retView = $('returnView').value;
    	//alert(retView);
    	if(retView=='1'){
    				this.location='<c:url value="/jpmProductSales.html"/>?strAction=editPmProductSale';
    		}else {
    			this.location='<c:url value="/jpmProducts.html"/>?strAction=listPmProducts';
    					
    			}
    		
    }
    
    function checkProductNo(){
    	var productNo = $("productNo").value;
    	if(productNo=="")
    		alert("<jecs:locale key="please.input.search.condition"/>");
    	else
        	jpmProductManager.checkProductNo(productNo,callback);
        
         
    
    }
    
    function callback(checkProductNo){
            if(checkProductNo){
                alert("<jecs:locale key="pmproduct.productNo.used"/>");
                sFlag=1;
            }else{
                alert("<jecs:locale key="pmproduct.productNo.canuse"/>");
                sFlag=0;
            }
        }
</script>

<v:javascript formName="jpmProduct" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
