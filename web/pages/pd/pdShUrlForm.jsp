<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShUrlDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdShUrlDetail.heading"/></content>

<spring:bind path="pdShUrl.*">
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

<c:if test="${strAction == 'pdShUrlQueryDetail'}">
	<form:form commandName="pdShUrl" method="post" action="editPdShUrl.html" id="pdShUrlFormOne" name="pdShUrlFormOne">
	
	<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>
	<table class='detail' width="70%">
	<tbody class="window" >
	<form:hidden path="id"/>
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="pdShUrl.valueCode"/></span>
		    </th>
		    <td>
		        <span class="textbox">${pdShUrl.valueCode }</span>
		    </td>
	  
	
	    
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="pdShUrl.valueTitle"/></span>
		    </th>
		    <td>
		         <span class="textbox">${pdShUrl.valueTitle }</span>
		    </td>
	    </tr>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="pdShUrl.shUrl"/></span>
		    </th>
		    <td>
		        <span class="textbox">${pdShUrl.shUrl }</span>
		    </td>  
	    </tr> 
	    </tbody> 
	</table>
	</form:form>
</c:if>

<c:if test="${strAction != 'pdShUrlQueryDetail'}">
	<form:form commandName="pdShUrl" method="post" action="editPdShUrl.html" id="pdShUrlFormTwo" name="pdShUrlFormTwo">
	
	<input type="hidden" name="strAction" value="${param.strAction }"/>
	<table class='detail' width="70%">
	<tbody class="window" >
	<form:hidden path="id"/>
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="pdShUrl.valueCode"/></span>
		    </th>
		    <td>
		        <span class="textbox"><form:input path="valueCode" id="valueCode" cssClass="textbox-text" /></span>
		    </td>
	  
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="pdShUrl.valueTitle"/></span>
		    </th>
		    <td>
		        <span class="textbox"><form:input path="valueTitle" id="valueTitle" cssClass="textbox-text"/></span>
		    </td>
	    </tr>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="pdShUrl.shUrl"/></span>
		    </th>
		    <td>
		         <span class="textbox"><form:input path="shUrl" id="shUrl" cssClass="textbox-text"/></span>
		    </td>  
	    </tr> 
	    </tbody> 
	</table>
	</form:form>
</c:if>

<table class='detail' width="70%">
<tbody class="window" >
<tr class="edit_tr">
		<td class="command"  colspan="4" align="center">
		      <c:if test="${strAction == 'pdShUrlQueryUpdate' || strAction == 'pdShUrlAdd'}">
<%--                  <input type="button" name="ssave"  class="button" onclick="pdShUrlSave(document.pdShUrlFormTwo)" value="<jecs:locale key="button.save"/>" />&nbsp;&nbsp;&nbsp;&nbsp; 
 --%>                 
                 <button type="button" class="btn btn_sele" name="ssave"  onclick="pdShUrlSave(document.pdShUrlFormTwo)" >
				         <jecs:locale key="button.save"/>
				</button>
             </c:if>
             <c:if test="${strAction == 'pdShUrlQueryUpdate'}">
<%--                 <input type="button" name="delet"  class="button" onclick="pdShUrlDelete(document.pdShUrlFormTwo)" value="<jecs:locale key="operation.button.delete"/>" />&nbsp;&nbsp;&nbsp;&nbsp; 
 --%>            
				 <button type="button" class="btn btn_sele" name="delet"  onclick="pdShUrlDelete(document.pdShUrlFormTwo)" >
						 <jecs:locale key="operation.button.delete"/>
				</button>
             </c:if>
<%--              <input name="cancel"  type="button" class="button"  size="1" onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />
 --%>		
             <button type="button" class="btn btn_sele" name="cancel"  onclick="returnOld()" >
					 <jecs:locale key="operation.button.return"/>
			</button>
		</td>
	</tr>
    <%--  <tr>
        <td align="right" width="25%">    
              <c:if test="${strAction == 'pdShUrlQueryUpdate' || strAction == 'pdShUrlAdd'}">
                 <input type="button" name="ssave"  class="button" onclick="pdShUrlSave(document.pdShUrlFormTwo)" value="<jecs:locale key="button.save"/>" />&nbsp;&nbsp;&nbsp;&nbsp; 
             </c:if>
             <c:if test="${strAction == 'pdShUrlQueryUpdate'}">
                <input type="button" name="delet"  class="button" onclick="pdShUrlDelete(document.pdShUrlFormTwo)" value="<jecs:locale key="operation.button.delete"/>" />&nbsp;&nbsp;&nbsp;&nbsp; 
             </c:if>
        </td>
        <td align="left" width="45%">       
               <input name="cancel"  type="button" class="button"  size="1" onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />
        </td> 
     
     </tr> --%>
</tbody>
</table>


<script type="text/javascript">
      function pdShUrlSave(theForm){
           theForm.submit();
      }
      
      function pdShUrlDelete(theForm){
          if(confirm("<jecs:locale key="amMessage.confirmdelete"/>")){
             document.getElementById("strAction").value= "pdShUrlDelete";
             theForm.submit();
         }
      }
      
      function returnOld(){
         window.history.back();
     }
      
</script>
