<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<script src="${ctx}/scripts/jquery/jquery-142min.js"> </script>
<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jpmProductWineTemplateSubManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jpmProductWineTemplateManager.js'/>" ></script>
<title><jecs:locale key="jpmProductWineTemplateDetail.title" /></title>
<content tag="heading">
<jecs:locale key="jpmProductWineTemplateDetail.heading" /></content>

<c:set var="buttons">

  <jecs:power powerCode="${param.strAction}">
    <input type="button" class="button" name="save" onclick="saveTemplate();" value="<jecs:locale key="button.save"/>" />
  </jecs:power>
<%--   <jecs:power powerCode="deleteJpmProductWineTemplate">
    <input type="submit" class="button" name="delete"
      onclick="bCancel=true;return confirmDelete(this.form,'JpmProductWineTemplate')"
      value="<jecs:locale key="button.lock"/>" />
  </jecs:power> --%>

  <input type="button" class="button" name="back" onclick="javascript:history.back();" value="<jecs:locale  key="operation.button.return"/>" />
</c:set>

<spring:bind path="jpmProductWineTemplate.*">
  <c:if test="${not empty status.errorMessages}">
    <div class="error">
      <c:forEach var="error" items="${status.errorMessages}">
        <img src="<c:url value="/images/iconWarning.gif"/>" alt="<jecs:locale key="icon.warning"/>" class="icon" />
        <c:out value="${error}" escapeXml="false" />
        <br />
      </c:forEach>
    </div>
  </c:if>
</spring:bind>

<form:form commandName="jpmProductWineTemplate" method="post" action="editJpmProductWineTemplate.html"
  onsubmit="return validateJpmProductWineTemplate(this)" id="jpmProductWineTemplateForm">

  <div id="titleBar">
    <c:out value="${buttons}" escapeXml="false" />
  </div>
  <input type="hidden" name="strAction" value="${param.strAction }" />
  <input type="hidden" name="jpmProductWineTemplatesubSize" id="jpmProductWineTemplatesubSize"/>

  <!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductWineTemplate')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
  <table class='detail' width="100%">
<form:hidden path="productTemplateNo" />
<tr>
      <th><jecs:label key="jpmProductWineTemplate.productTemplateName" /><span style="color: red;">*</span></th>
      <td align="left">
    <form:input path="productTemplateName" id="productTemplateName" maxlength="60"
        cssClass="text medium" />
      </td>
      <th><jecs:label key="jpmProductWineTemplate.parentQty" /><span style="color: red;">*</span></th>
      <td colspan="3" align="left">
    <form:input path="parentQty" id="parentQty" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')" 
        cssClass="text medium" />
      </td>
     
    </tr>
    
    <tr>
      <th><jecs:label key="jpmProductWineTemplate.productNo" /><span style="color: red;">*</span></th>
      <td align="left">
        <!--form:errors path="productNo" cssClass="fieldError"/--> <form:input path="productNo" id="productNo" 
        readonly="true"  onclick="selectProduct(4,this,188);"  cssClass="text medium" />
          <input type="button" class="button" name="select"
                onclick="selectProduct(4,this,188);"
                value="<jecs:locale key="button.select"/>"/>
      </td>
      <!-- </tr>

    <tr> -->
      <th><jecs:label key="jpmProductWineTemplate.productName" /></th>
      <td align="left">
        <!--form:errors path="productName" cssClass="fieldError"/--> <form:input path="productName" id="productName" readonly="true"
          cssClass="text medium" />
      </td>
    </tr>

    <tr>
      <th><jecs:label key="jpmProductWineTemplate.isDefault" /></th>
      <td align="left">
        <!--form:errors path="isDefault" cssClass="fieldError"/--> 
          <jecs:list listCode="jpmproductwinetemplate.isdefault" name="isDefault" id="isDefault" value="" defaultValue="0"/>
      </td>
      <!-- </tr>

    <tr> -->
      <th><jecs:label key="jpmProductWineTemplate.isInvalid" /></th>
      <td align="left">
        <!--form:errors path="isInvalid" cssClass="fieldError"/--> 
          <jecs:list listCode="jpmproductwinetemplate.isinvalid" name="isInvalid" id="isInvalid" value="" defaultValue="0"/>
      </td>
    </tr>

    <tr>
      <th><jecs:label key="jpmProductWineTemplate.memo" /></th>
      <td align="left">
        <!--form:errors path="memo" cssClass="fieldError"/--> 
        <textarea name="memo" id="memo" cols="50" rows="5" maxlength="100"></textarea>
      </td>
    </tr>

    <%--  <tr><th>
        <jecs:label  key="jpmProductWineTemplate.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr> --%>

  </table>

  <table id="detail_table" class='detail' width="100%">
     <tr>
      <th><jecs:label key="title.operation"/><a style="font-size: 16px; font: bold;cursor: pointer;" href="javascript:void(0);" onclick="addTR();">+</a></th>
      <th><jecs:label key="jpmProductWineTemplatesub.subName" /><span style="color: red;">*</span></th><!-- 子项名称 -->
      <th><jecs:label key="jpmProductWineTemplatesub.productNo" /><span style="color: red;">*</span></th><!-- 材料编号 -->
      <th><jecs:label key="jpmProductWineTemplatesub.productName" /></th><!-- 材料名称 -->
      <th><jecs:label key="jpmProductWineTemplatesub.price" /></th><!-- 单价 -->
      <th><jecs:label key="jpmProductWineTemplatesub.specification" /></th><!-- 规格 -->
      <th><jecs:label key="jpmProductWineTemplatesub.num" /></th><!-- 数量 -->
      <th><jecs:label key="jpmProductWineTemplatesub.unit" /></th><!-- 计量单位 -->
      
      <th><jecs:label key="jpmProductWineTemplatesub.lossRatio" /></th><!-- 损耗系数 -->
      <th><jecs:label key="jpmProductWineTemplatesub.isMainMaterial" /></th><!-- 是否主料 -->
      <th><jecs:label key="jpmProductWineTemplatesub.isSendMaterial" /></th><!-- 是否发料 -->
      <th><jecs:label key="jpmProductWineTemplatesub.isDelegateOut" /></th><!-- 是否委外 -->
      <th><jecs:label key="jpmProductWineTemplatesub.isFeatureItem" /></th><!-- 是否特征项 -->
      
      <th><jecs:label key="jpmProductWineTemplatesub.isMustSelected" /></th><!-- 是否必选 -->
      <th><jecs:label key="jpmProductWineTemplatesub.isDefaultSelected" /></th><!-- 是否默认选择 -->
      <th><jecs:label key="jpmProductWineTemplatesub.isNumChange" /></th><!-- 是否数量可调 -->
      <th><jecs:label key="jpmProductWineTemplatesub.numMin" /></th><!-- 数据下限 -->
      <th><jecs:label key="jpmProductWineTemplatesub.numMax" /></th><!-- 数量上限 -->
    </tr>
   
 <c:forEach var="sub" items="${jpmProductWineTemplate.jpmProductWineTemplateSub}" varStatus="status">
  
    <tr id="trid${status.index}">
      <td align="left">
         <a style="font-size: 16px; font: bold;cursor: pointer;" href="javascript:void(0);" onclick="delTR(this);">-</a>
         <input type="hidden" size="15" value="${sub.subNo}" name="jpmProductWineTemplatesub[*].subNo" id="jpmProductWineTemplatesub[*].subNo">
      </td>
      <td align="left">
        <input type="text" size="15" value="${sub.subName}" name="jpmProductWineTemplatesub[*].subName" id="jpmProductWineTemplatesub[*].subName">
      </td>
      <td align="left">
        <input type="text" size="15" readonly="readonly" value="${sub.productNo}" onclick="selectProduct(5,this,189);" name="jpmProductWineTemplatesub[*].productNo" id="jpmProductWineTemplatesub[*].productNo">
      </td>
      <td align="left">
        <input type="text" size="8" readonly="readonly" value="${sub.productName}" name="jpmProductWineTemplatesub[*].productName" id="jpmProductWineTemplatesub[*].productName">
      </td>
      <td align="left">
        <input type="text" size="5" value="${sub.price}" name="jpmProductWineTemplatesub[*].price" id="jpmProductWineTemplatesub[*].price">
      </td>
      <td align="left">
        <input type="text" size="15" value="${sub.specification}" name="jpmProductWineTemplatesub[*].specification" id="jpmProductWineTemplatesub[*].specification">
      </td>
      <td align="left">
        <input type="text" size="5" value="${sub.num}"  onkeyup="this.value=this.value.replace(/\D/g,'')" name="jpmProductWineTemplatesub[*].num" id="jpmProductWineTemplatesub[*].num">
      </td>
      <td align="left">
      <jecs:list listCode="pmproduct.unitno"  name="jpmProductWineTemplatesub[*].unit" id="jpmProductWineTemplatesub[*].unit" showBlankLine="true" value="${sub.unit}" defaultValue=""/>
       <%-- <input type="text" size="5" value="${sub.unit}" name="jpmProductWineTemplatesub[*].unit" id="jpmProductWineTemplatesub[*].unit"> --%>
      </td>
      
      <td align="left">
       <input type="text" size="5" value="${sub.lossRatio}" name="jpmProductWineTemplatesub[*].lossRatio" id="jpmProductWineTemplatesub[*].lossRatio">
      </td>
      <td align="left">
       <input type="checkbox" value="1"  ${sub.isMainMaterial eq '1' ? 'checked':''} name="jpmProductWineTemplatesub[*].isMainMaterial" id="jpmProductWineTemplatesub[*].isMainMaterial">
      </td>
      <td align="left">
        <input type="checkbox" value="1" ${sub.isSendMaterial eq '1' ? 'checked':''} name="jpmProductWineTemplatesub[*].isSendMaterial" id="jpmProductWineTemplatesub[*].isSendMaterial">
      </td>
      <td align="left">
       <input type="checkbox" value="1" ${sub.isDelegateOut eq '1' ? 'checked':''}  name="jpmProductWineTemplatesub[*].isDelegateOut" id="jpmProductWineTemplatesub[*].isDelegateOut">
      </td>
      <td align="left">
        <input type="checkbox" value="1" ${sub.isFeatureItem eq '1' ? 'checked':''}  name="jpmProductWineTemplatesub[*].isFeatureItem" id="jpmProductWineTemplatesub[*].isFeatureItem">
      </td>
      
      <td align="left">
       <input type="checkbox" value="1"  ${sub.isMustSelected eq '1' ? 'checked':''}  name="jpmProductWineTemplatesub[*].isMustSelected" id="jpmProductWineTemplatesub[*].isMustSelected">
      </td>
      <td align="left">
        <input type="checkbox" value="1"  ${sub.isDefaultSelected eq '1' ? 'checked':''} name="jpmProductWineTemplatesub[*].isDefaultSelected" id="jpmProductWineTemplatesub[*].isDefaultSelected">
      </td>
      <td align="left">
       <input type="checkbox" value="1" ${sub.isNumChange eq '1' ? 'checked':''} name="jpmProductWineTemplatesub[*].isNumChange" id="jpmProductWineTemplatesub[*].isNumChange">
      </td>
      <td align="left">
       <input type="text" value="${sub.numMin}" size="5" name="jpmProductWineTemplatesub[*].numMin" id="jpmProductWineTemplatesub[*].numMin">
      </td>
      <td align="left">
       <input type="text" value="${sub.numMax}" size="5" name="jpmProductWineTemplatesub[*].numMax" id="jpmProductWineTemplatesub[*].numMax">
      </td>
    </tr>
</c:forEach>

  </table>
  <!--/fieldset-->

  <!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmProductWineTemplate')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<table style="display: none;" id="trContent">
  <tr>
      <td align="left">
        <a style="font-size: 16px; font: bold;cursor: pointer;" href="javascript:void(0);" onclick="delTR(this);">-</a> 
        <input type="hidden" value="" name="jpmProductWineTemplatesub[*].subNo" id="jpmProductWineTemplatesub[*].subNo">
      </td>
      <td align="left">
        <input type="text" size="15"  name="jpmProductWineTemplatesub[*].subName" id="jpmProductWineTemplatesub[*].subName">
      </td>
      <td align="left">
        <input type="text" size="15" readonly="readonly" onclick="selectProduct(5,this,189);" name="jpmProductWineTemplatesub[*].productNo" id="jpmProductWineTemplatesub[*].productNo">
      </td>
      <td align="left">
        <input type="text" size="8" readonly="readonly" name="jpmProductWineTemplatesub[*].productName" id="jpmProductWineTemplatesub[*].productName">
      </td>
      <td align="left">
        <input type="text" size="5" name="jpmProductWineTemplatesub[*].price" id="jpmProductWineTemplatesub[*].price">
      </td>
      <td align="left">
        <input type="text" size="15" name="jpmProductWineTemplatesub[*].specification" id="jpmProductWineTemplatesub[*].specification">
      </td>
      <td align="left">
        <input type="text" size="5" name="jpmProductWineTemplatesub[*].num"  onkeyup="this.value=this.value.replace(/\D/g,'')" id="jpmProductWineTemplatesub[*].num">
      </td>
      <td align="left">
         <jecs:list listCode="pmproduct.unitno"  name="jpmProductWineTemplatesub[*].unit" id="jpmProductWineTemplatesub[*].unit" showBlankLine="true" value="" defaultValue=""/>
      </td>
      
      <td align="left">
       <input type="text" size="5" name="jpmProductWineTemplatesub[*].lossRatio" id="jpmProductWineTemplatesub[*].lossRatio">
      </td>
      <td align="left">
       <input type="checkbox" value="1" name="jpmProductWineTemplatesub[*].isMainMaterial" id="jpmProductWineTemplatesub[*].isMainMaterial">
      </td>
      <td align="left">
        <input type="checkbox" value="1" name="jpmProductWineTemplatesub[*].isSendMaterial" id="jpmProductWineTemplatesub[*].isSendMaterial">
      </td>
      <td align="left">
       <input type="checkbox" value="1" name="jpmProductWineTemplatesub[*].isDelegateOut" id="jpmProductWineTemplatesub[*].isDelegateOut">
      </td>
      <td align="left">
        <input type="checkbox" value="1" name="jpmProductWineTemplatesub[*].isFeatureItem" id="jpmProductWineTemplatesub[*].isFeatureItem">
      </td>
      
      <td align="left">
       <input type="checkbox" value="1" name="jpmProductWineTemplatesub[*].isMustSelected" id="jpmProductWineTemplatesub[*].isMustSelected">
      </td>
      <td align="left">
        <input type="checkbox" value="1" name="jpmProductWineTemplatesub[*].isDefaultSelected" id="jpmProductWineTemplatesub[*].isDefaultSelected">
      </td>
      <td align="left">
       <input type="checkbox" value="1" name="jpmProductWineTemplatesub[*].isNumChange" id="jpmProductWineTemplatesub[*].isNumChange">
      </td>
      <td align="left">
       <input type="text" size="5" name="jpmProductWineTemplatesub[*].numMin" id="jpmProductWineTemplatesub[*].numMin">
      </td>
      <td align="left">
       <input type="text" size="5" name="jpmProductWineTemplatesub[*].numMax" id="jpmProductWineTemplatesub[*].numMax">
      </td>
    </tr>
</table>

<script type="text/javascript">
	Form.focusFirstElement($('jpmProductWineTemplateForm'));
</script>

<v:javascript formName="jpmProductWineTemplate" cdata="false" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

<script type="text/javascript">
function selectProduct(type,that,productCategory){
	var trId="";
	if(type==5){
		trId=$(that).parent().parent().attr('id');
	}
	open("${ctx}/jpmProducts.html?strAction=selectProduct&selectControl="+type+"&trId="+trId+"&productCategory="+productCategory,"","height=400, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
}
var trIndex = $('#detail_table tr').length-1;
function addTR(){
	$('#detail_table').append($('#trContent').html());
	$('#detail_table tr:last').attr("id","trid"+trIndex);
	trIndex++;
}
$(function(){
	if('${param.strAction}'=='addJpmProductWineTemplate'){
		addTR();
	}
});


function lock(){
	 if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
     	jpmProductWineTemplateManager.updateInvalidStatus(subNo,'1',function(){
     		
     	});
	    }
}

function delTR(that){
	var $tr = $(that).parent().parent();
	var subNo = $tr.find('input[name$="subNo"]').val();
	if(subNo!=''){
        if(confirm("删除不可恢复，确定要删除吗？")){
        	jpmProductWineTemplateSubManager.removeJpmProductWineTemplateSub(subNo,function(){
        		$(that).parent().parent().remove();
        	});
	    }
	}else{
		$(that).parent().parent().remove();
	}
}
function validVal(){
	if($('#productTemplateName').val()==''){
		alert('请填写模板名!');
		return false;
	}
	if($('#parentQty').val()==''){
		alert('请填写父数量!');
		return false;
	}
	if($('#productNo').val()==''){
		alert('请选择模板对应的商品!');
		return false;
	}
	
	
	var subNameList = $('#detail_table input[name$="subName"]');
	var productNoList = $('#detail_table input[name$="productNo"]');
	for(var i=0;i<subNameList.length;i++){
		if($(subNameList[i]).val()==''){
			alert('请填写子项名称!');
			return false;
		}
	}
	for(var i=0;i<productNoList.length;i++){
		if($(productNoList[i]).val()==''){
			alert('请选择子项对应的商品!');
			return false;
		}
	}
	return true;;
	//jpmProductWineTemplatesub[*].productNo
}
function saveTemplate(){
	if(false==validVal()){
		return;
	}
	var trList = $('#detail_table tr');
	var len = trList.length;
	for(var i=1;i<len;i++){
		var index = i-1;
		var inputList = $(trList[i]).find(':input');
		for( var j=0;j<inputList.length;j++){
				var name = $(inputList[j]).attr('name');
				name = name.replace("*",index);
				
				$(inputList[j]).attr('name',name);
				$(inputList[j]).attr('id',name);
		}
	}
	
	 $('#jpmProductWineTemplatesubSize').val($('#detail_table tr').length-1);
	 $('#jpmProductWineTemplateForm').submit();
}
</script>
