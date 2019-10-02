<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoPinListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoPinListDetail.heading"/></content>

<spring:bind path="jpoPinList.*">
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

<form:form commandName="jpoPinList" method="post" action="editJpoPinList.html" 
		enctype="multipart/form-data"
	onsubmit="return validateJpoPinList(this)" id="jpoPinListForm">
<table class='detail' width="100%">
	<tbody>
		 <tr class="edit_tr">
		    <th align="right">
		       <jecs:label key="uploadForm.file" styleClass="desc" />
		    </th>
		    <td colspan="3">
		        <input type="file" name="pinFile" id="pinFile" >
		    </td>
    	</tr>
	    <tr class="edit_tr">
		    <th align="right">
		       <jecs:label key="busi.common.remark" styleClass="desc" />
		    </th>
		    <td colspan="3">
		        <jecs:locale key="jpopinlist.common.remark.uploadexl" />
		    </td>
	    </tr>
	        	
		<tr class="edit_tr">			
			<td align="right">
				<jecs:label key="upload.module" styleClass="desc" />
			</td>
			<td  colspan="3">	
			     <a href="./images/uploadJpoPinList.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>  
			</td>
		</tr>
		
	   <tr class="edit_tr">
		    <td class="command"  colspan="4" align="center">
		        <input type="submit" value="导入" class="btn btn_sele">
		        <button type="button" onclick="window.location.href='jpoPinLists.html';" class="btn btn_long">返回</button>
		    </td>
	   </tr>			        		        	
    </tbody>
<input type="hidden" name="strAction" value="${param.strAction }"/>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpoPinListForm'));
    
    function validateJpoPinList(theFrom){
    	
    	var file=document.getElementById("pinFile").value;
    	
    	if(file==""){
            alert("请选择需要导入的模版！");
            return false;
        }else{
    		var nameT1=file.lastIndexOf("."); 
    		var nameT2=file.length;
    		var name=file.substring(nameT1,nameT2);//后缀名
    		if(name!='.xls'){
    			alert("导入模版格式不正确");
    			return false;
    		}else{
    			return true;
    		}
        }
    }    
</script>

<v:javascript formName="jpoPinList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
