<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShUrlDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdShUrlDetail.heading"/></content>

<body onload="javascript:history.go(0);history.go(0);">
<spring:bind path="pdWarehouseFrozenBatch.*">
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

<c:if test="${strAction == 'photoBatchUpdate'}">
	<form:form commandName="jpmProductSaleImage" method="post"
		action="editPdWarehouseFrozenBatch.html?strAction=${strAction}"
		enctype="multipart/form-data"
		onsubmit="return validateJpmProductSaleImage(this)"
		id="jpmProductSaleImageForm">
		<div class="searchBar">
		<input type="hidden" id="strAction" name="strAction" value="${strAction}"/> 
		<input type="hidden" id="flag" name="flag" value="Y"/> 
			<table>
				<tr>
					<td align="right" width="10%"><jecs:label key="busi.product.pic"/>1</td>
					<td align="left" width="45%">
						<img src="${FILE_URL }guaten/${images[0]}.jpg" onclick="suonvtuShow('${FILE_URL }guaten/${images[0]}.jpg');" width="281" height="142" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>1"/>
						<br/><input type="file" name="file1" id="file1"/><jecs:label key="jpmProductSaleImage.imageLink.Prompt" />
					</td>
					<td align="left" width="45%">
						<jecs:label key="jamDownload.fileLink"/>1:<input name="imageLink1" type="text" id="imageLink1" value="${links[0] }" style="width: 333px;"/><font color="red"><b>*</b></font>
					</td>
				</tr>
				<tr>
					<td align="right" width="10%"><jecs:label key="busi.product.pic"/>2</td>
					<td align="left" width="45%">
						<img src="${FILE_URL }guaten/${images[1]}.jpg" onclick="suonvtuShow('${FILE_URL }guaten/${images[1]}.jpg');" width="281" height="142" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>2"/>
						<br/><input type="file" name="file2" id="file2"/><jecs:label key="jpmProductSaleImage.imageLink.Prompt" />
					</td>
					<td align="left" width="45%">
						<jecs:label key="jamDownload.fileLink"/>2:<input name="imageLink2" type="text" id="imageLink2" value="${links[1] }" style="width: 333px;"/><font color="red"><b>*</b></font>
					</td>
				</tr>
				<tr>
					<td align="right" width="10%"><jecs:label key="busi.product.pic"/>3</td>
					<td align="left" width="45%">
						<img src="${FILE_URL }guaten/${images[2]}.jpg" onclick="suonvtuShow('${FILE_URL }guaten/${images[2]}.jpg');" width="281" height="142" style="border-color: green;border-width: 1px;cursor: pointer;" alt="<jecs:locale key='productimage.onclickshow'/>3"/>
						<br/><input type="file" name="file3" id="file3"/><jecs:label key="jpmProductSaleImage.imageLink.Prompt" />
					</td>
					<td align="left" width="45%">
						<jecs:label key="jamDownload.fileLink"/>3:<input name="imageLink3" type="text" id="imageLink3" value="${links[2] }" style="width: 333px;"/><font color="red"><b>*</b></font>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						<input type="button" class="button" name="save"
								onclick="submitMe();"
								value="<jecs:locale key="operation.button.save"/>" />
						<input type="button" class="button" name="back"
							onclick="javascript:history.go(0);"
							value="<jecs:locale  key="button.refresh"/>" />
					</td>
				</tr>
			</table>
			
		</div>
		</form:form>
		<script type="text/javascript">
			Form.focusFirstElement($('jpmProductSaleImageForm'));
			
			 function submitMe(){
	    		var imageLink1 = document.getElementById("imageLink1");
	    		var imageLink2 = document.getElementById("imageLink2");
	    		var imageLink3 = document.getElementById("imageLink3");
	    		if(imageLink1==null || imageLink1.value==''){
	    			alert("<jecs:locale key="guaten.imageurl.isnull"/>1");
	    			imageLink1.focus();
	    			return false;
	    		}
	    		if(imageLink2==null || imageLink2.value==''){
	    			alert("<jecs:locale key="guaten.imageurl.isnull"/>2");
	    			imageLink2.focus();
	    			return false;
	    		}
	    		if(imageLink3==null || imageLink3.value==''){
	    			alert("<jecs:locale key="guaten.imageurl.isnull"/>3");
	    			imageLink3.focus();
	    			return false;
	    		}
	    		$('jpmProductSaleImageForm').submit();
	    	}
			
		     function queryReport(){
		     		/*if(strActionValue=='zhongcenum' || strActionValue=='province'){
			     		var query_yearMonth = document.getElementById("query_yearMonth");
			     		if(query_yearMonth.value==null || query_yearMonth.value=='' || query_yearMonth.value.length!=6){
			     			alert("<jecs:locale key='operation.notice.yearmonth'/>");
			     			return false;
			     		}
		     		}*/
		     		var imageLink1 = document.getElementById("imageLink1");
		     		var imageLink2 = document.getElementById("imageLink2");
		     		var imageLink3 = document.getElementById("imageLink3");
		     		if(imageLink1==null || imageLink1.value==''){
		     			alert("<jecs:locale key='isNotNull'/>");
		     		}
		     		waiting();
		     }
		     
			function queryReport2(theForm,strActionValue){
					/**var netType = document.getElementById("netType");
					if(strActionValue=='netLead' || strActionValue=='areaLead' || strActionValue=='recommendLead'){
						if(netType.value==null || netType.value==''){
							alert("<jecs:locale key='amMessage.discuss.select'/><jecs:locale key='miMember.netType'/>");
						}
					}**/
		
		     		if(strActionValue=='netLead' || strActionValue=='areaLead' || strActionValue=='recommendLead' || strActionValue=='bigJpomemberorder' || strActionValue=='huicuiProduct' || strActionValue=='yunnanchongxiao' || strActionValue=='daoheWineJpomemberorder' || strActionValue=='daoheWineJprrefund' || strActionValue=='jponetfee'){
			     		var startDate = document.getElementById("startDate");
			     		var endDate = document.getElementById("endDate");
			     		var formatedWeek = document.getElementById("formatedWeek");
			     		var formatedYear = document.getElementById("formatedYear");
			     		var formatedMonth = document.getElementById("formatedMonth");
			     		if( (startDate.value==null || startDate.value=='' ||
			     		    endDate.value==null || endDate.value=='') &&
			     		    (formatedWeek.value==null || formatedWeek.value=='') &&
			     		    (formatedYear.value==null || formatedYear.value=='') &&
			     		    (formatedMonth.value==null || formatedMonth.value=='') ){
			     			alert("<jecs:locale key='please.input.search.condition'/>");
			     			return false;
			     		}
		     		}
		     		 waiting();
		             theForm.strAction.value=strActionValue;
		     		 theForm.submit();
		     }
		
		
		     function cancel(){
		     	waitingEnd();
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
		</script>
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
</c:if>

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
</body>