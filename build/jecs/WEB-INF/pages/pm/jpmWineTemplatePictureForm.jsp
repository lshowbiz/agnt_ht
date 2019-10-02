<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<script src="${ctx}/scripts/jquery/jquery-142min.js"> </script>
<title><jecs:locale key="jpmWineTemplatePictureDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmWineTemplatePictureDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="button" onclick="saveObject();" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
    <input type="button" class="button" name="back" onclick="javascript:history.back();" value="<jecs:locale  key="operation.button.return"/>" />
		<%-- <jecs:power powerCode="deleteJpmWineTemplatePicture">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmWineTemplatePicture')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power> --%>
</c:set>

<spring:bind path="jpmWineTemplatePicture.*">
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

<form:form commandName="jpmWineTemplatePicture" method="post" enctype="multipart/form-data" action="editJpmWineTemplatePicture.html?strAction=${param.strAction }"  onsubmit="return validateJpmWineTemplatePicture(this)" id="jpmWineTemplatePictureForm">

<div id="titleBar"><%-- --%> 
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<%-- <input type="hidden" name="strAction" value="${param.strAction }"/> --%>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmWineTemplatePicture')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="100%">

<form:hidden path="idf"/>


    <tr><th>
        <jecs:label  key="jpmWineTemplatePicture.pictureName"/><span style="color: red;">*</span>
    </th>
    <td align="left">
        <!--form:errors path="pictureName" cssClass="fieldError"/-->
        <form:input path="pictureName" id="pictureName" cssClass="text medium"/>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="jpmWineTemplatePicture.subNo"/><span style="color: red;">*</span>
    </th>
    <td align="left">
        <!--form:errors path="subNo" cssClass="fieldError"/-->
        <input type="text" onclick="selectSub();" readonly value="${jpmWineTemplatePicture.jpmProductWineTemplateSub.subNo}" class="text medium" name="subNo" id="subNo">
        <input type="button" class="button" name="select"
                onclick="selectSub();"
                value="<jecs:locale key="button.select"/>"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="jpmProductWineTemplatesub.subName"/>
    </th>
    <td align="left">
        <!--form:errors path="subNo" cssClass="fieldError"/-->
        <form:input readonly="true" path="subName" id="subName" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmWineTemplatePicture.picturePath"/><span style="color: red;">*</span>
    </th>
    <td align="left">
        <!--form:errors path="picturePath" cssClass="fieldError"/-->
        <input type="file" name="file" id="file"/>
        <c:if test="${not empty jpmWineTemplatePicture.pictureNameDist}">
          <img id="fileImg" style="width:150px;height:150px;cursor:pointer;" onclick="suonvtuShow('${requestScope.fileUrl}${jpmWineTemplatePicture.pictureNameDist}')" src="${requestScope.fileUrl}${jpmWineTemplatePicture.pictureNameDist}">
        </c:if>
    </td></tr>
    <tr><th>
        <jecs:label  key="jpmWineTemplatePicture.isInvalid"/>
    </th>
    <td align="left">
        <!--form:errors path="isInvalid" cssClass="fieldError"jpmWineTemplatePicture.isInvalid/-->
        <jecs:list listCode="jpmwinetemplatepicture.isinvalid" name="isInvalid" id="isInvalid"  value="" defaultValue="0"/>
        <%-- <form:input path="isInvalid" id="isInvalid" cssClass="text medium"/> --%>
    </td></tr>

</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmWineTemplatePicture')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>


<div style="position: absolute; top: 10px; display: none;" id="suonvtuId">
  <table style="border: 4px #C4C4C4 solid; width: 460px; height: 260px;"
    cellspacing="0" cellpadding="0">
    <tr>
      <td align="right"
        style="background-color: #8D8D8D; height: 24px;"
        >
        <img src="/jecs/images/close.gif" height="10px;" width="10px;" />
        <font style="color: #ffffff; font-size: 12px;cursor:pointer; " onclick="removeSuonvtu();">close</font>&nbsp;&nbsp;
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

<script type="text/javascript">
    Form.focusFirstElement($('jpmWineTemplatePictureForm'));
</script>

<v:javascript formName="jpmWineTemplatePicture" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>

<script type="text/javascript">
$(function(){
	$('#file').change(function(){
		$('#fileImg').hide();
	});
});

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

function selectSub(){
	open("${ctx}/jpmProductWineTemplateSubs.html?strAction=selectProduct&isInvalid=0","","height=400, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
}

function saveObject(){
	if($('#pictureName').val()==''){
		alert('请输入图片名称！');
		return ;
	}
	
	if($('#subNo').val()==''){
		alert('请选择子项！');
		return ;
	}
	if('${param.strAction}'=='addJpmWineTemplatePicture'){
    	if($('#file').val()==''){
    		alert('请选择图片！');
    		return ;
    	}
    	var pref = $('#file').val().substr($('#file').val().lastIndexOf('.')+1);
    	if('jpg'!=pref.toLowerCase()
    	   &&'png'!=pref.toLowerCase()
    	   &&'bmp'!=pref.toLowerCase()){
    		alert('图片只支持jpg、jpeg、png图片');
        	return ;
    	}
	}

	$('#jpmWineTemplatePictureForm').submit();
}
</script>
