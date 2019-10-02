<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdSendInfoList.title"/></title>
<content tag="heading"><jecs:locale key="pdSendInfoList.heading"/></content>
<meta name="menu" content="PdSendInfoMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./js/jquery-1.4.2.min.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/pdShUrlManager.js'/>" ></script>
<script type="text/javascript" src="./scripts/My97DatePicker/WdatePicker.js"></script>

<c:set var="buttons">
	
		<c:if test="${requestParaMap.strAction=='editPdSendInfo'}">
			
			
				
				<!--<span onclick="addPdSendInfo()" style="cursor:pointer">
				<img alt="<jecs:locale  key='operation.button.add'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='operation.button.add'/></font>
				</span>-->
				

   
		</c:if>
		<div class="new_searchBar">
		    <button type="submit" class="btn btn_sele"  style="width:auto">
				   <jecs:locale  key='operation.button.search'/>
		    </button>
		<jecs:power powerCode="updateTrackingNo">	
			<c:if test="${(requestParaMap.strAction=='sendPdSendInfo') || (requestParaMap.strAction=='batchConfirmShipOrder')}">
			    <button type="button" class="btn btn_long"  style="width:auto"  onclick="importFile();">
				    <jecs:locale  key='operation.button.uploadtrackingno'/>
		        </button>
			</c:if>
		</jecs:power>
		<c:if test="${requestParaMap.strAction=='sendPdSendInfo'}">
			
					<textarea cols='20' rows='8' name="siNoTextarea" id="siNoTextarea" style="display: none;" cssClass="text medium" /><c:out value='${requestParaMap.siNoTextarea}'/></textarea>
				    <button type="button" class="btn btn_long" id="sinoButton"  style="width:auto" onclick="showBatchPrintTextArea();">
				        <jecs:locale  key='memu.pd.batchPrint.sinoshowmo'/>
				    </button>
			
				<jecs:power powerCode="updateTrackingNo">	
				    <button type="button" class="btn btn_long"  style="width:auto" onclick="batchPrint();">
					        <jecs:locale  key='memu.pd.batchPrint'/>
					</button>
				</jecs:power>
			
		</c:if>
		</div>
		
		<!-- 
			<input type="submit" class="button" value="<jecs:locale  key='operation.button.search'/>"/>
		<jecs:power powerCode="updateTrackingNo">	
			<c:if test="${(requestParaMap.strAction=='sendPdSendInfo') || (requestParaMap.strAction=='batchConfirmShipOrder')}">
			
  				<input type="button" class="button" onclick="importFile();" value="<jecs:locale  key='operation.button.uploadtrackingno'/>"/>
			</c:if>
		</jecs:power>
		 -->
		<!-- 
		<jecs:power powerCode="updateTrackingNo">	
  			<input type="button" class="button" onclick="importFile2(1);" value="<jecs:locale  key='operation.button.uploadrecipientinfo'/>"/>
		</jecs:power>
		 
		<jecs:power powerCode="updateTrackingNo">	
  			<input type="button" class="button" onclick="importFile2(2);" value="<jecs:locale  key='operation.button.uploaddepotgoodstate'/>"/>
		</jecs:power>
		-->
		
		<%-- <c:if test="${requestParaMap.strAction=='sendPdSendInfo'}">
			<br/>
			<div class="new_searchBar">
					<textarea cols='20' rows='8' name="siNoTextarea" id="siNoTextarea" style="display: none;" cssClass="text medium" /><c:out value='${requestParaMap.siNoTextarea}'/></textarea>
				    <button type="button" class="btn btn_long" id="sinoButton"  style="width:auto" onclick="showBatchPrintTextArea();">
				        <jecs:locale  key='memu.pd.batchPrint.sinoshow'/>
				    </button>
			</div>
			<div class="new_searchBar">
			        <button type="button" class="btn btn_long" id="clearButton"  style="width:auto" onclick="clearPrintTextArea();">
				        <jecs:locale  key='menu.pd.emptysinovalue'/>
				    </button>
			
			<jecs:power powerCode="updateTrackingNo">	
			    <button type="button" class="btn btn_long"  style="width:auto" onclick="batchPrint();">
				        <jecs:locale  key='memu.pd.batchPrint'/>
				</button>
			</jecs:power>
			</div>
		</c:if> --%>
		
		
		<!-- Add By WuCF 2013-05-09 -->
		<!-- 
		<c:if test="${requestParaMap.strAction=='sendPdSendInfo'}">
			<br/>
			<textarea cols='20' rows='8' name="siNoTextarea" id="siNoTextarea" style="display: none;" cssClass="text medium" /><c:out value='${requestParaMap.siNoTextarea}'/></textarea>
			<input type="button" class="button" id="sinoButton" value="<jecs:locale  key='memu.pd.batchPrint.sinoshow'/>" onclick="showBatchPrintTextArea();"/>
			<input type="button" class="button" id="clearButton" value="<jecs:locale  key='menu.pd.emptysinovalue'/>" onclick="clearPrintTextArea();"/>
			<jecs:power powerCode="updateTrackingNo">	
	  			<input type="button" class="button" onclick="batchPrint();" value="<jecs:locale  key='memu.pd.batchPrint'/>"/>
			</jecs:power>
		</c:if>
		 -->
</c:set>






	
<form name="pdSendInfoList" action="<c:url value='/pdSendInfos.html'/>" >
<!--  
	<c:if test="${sessionScope.sessionLogin.userCode == 'root'}" >
		<input type="text" id="siNos" name="siNos" value=""/>
		<input type="button" class="button" onclick="reUpload();" value="xxxxx"/>
	</c:if>
-->	
	
	<input type="hidden" id="strAction" name="strAction" value="${requestParaMap.strAction}"/> 
	
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title  key="busi.order.orderno"/>
			<input name="orderNo" id="orderNo" value="<c:out value='${requestParaMap.orderNo}'/>"  cssClass="text medium"/>
		</div>
					
			<c:if test="${sessionScope.sessionLogin.isManager || sessionScope.sessionLogin.isCompany}">
			
			<div class="new_searchBar">
			     <jecs:title  key="pdSendInfo.siNo"/>
			     <input name="siNo" id="siNo" value="<c:out value='${requestParaMap.siNo}'/>"  cssClass="text medium"/>
			</div>
			<div class="new_searchBar">	
			     <jecs:title key="busi.product.productno"/>
		         <input name="productNo"  value="<c:out value='${requestParaMap.productNo}'/>" cssClass="text medium"/>	
			</div>
			<div class="new_searchBar">	
			     <jecs:title  key="pdSendInfo.orderFlag"/>
				 <jecs:list listCode="pdsendinfo.orderflag" name="orderFlag" id="orderFlag" showBlankLine="true" value="${requestParaMap.orderFlag}" defaultValue=""/>
			</div>
			<div class="new_searchBar">			
				 <jecs:title  key="customerRecord.customerNo"/>
				 <input type="text" name="customCode" value='${requestParaMap.customCode}'  id="customCode" />		
<%-- 				 <img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchUser()" style="cursor:pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
 --%>				 <!--  <input type="text" name="customName"  id="customName" readonly="true" style="cursor:pointer;width:70px;"/>-->
			</div>	
			<div class="new_searchBar" style="white-space:nowrap;">				
				 <jecs:title key="pdOutWarehouse.warehouseNo"/>
				 <input name="warehouseNo" id="warehouseNo" value="<c:out value='${requestParaMap.warehouseNo}'/>"  cssClass="text medium"/>
			     <button name="select" class="btn btn_sele" onclick="selectWarehouse2('warehouseNo');" >
				     <jecs:locale key="button.select"/>
			     </button>
			</div>
							
			
				
				
				
				
				
			
					
				
				
				
				<div class="new_searchBar">	
				<jecs:title key="alStateProvince.stateProvinceName"/>
			
				<select name="recipientCaNo">
					<option value=""></option>
					<c:forEach items="${alStateProvinces}" var="alStateProvince">
						<option value="${alStateProvince.stateProvinceId}" <c:if test="${alStateProvince.stateProvinceId eq requestParaMap.recipientCaNo}">selected</c:if> >
								${alStateProvince.stateProvinceName}
						</option>
					</c:forEach>
				</select>
				</div>
				
				<div class="new_searchBar">	
				<jecs:title key="pd.createTime"/>
				 <input name="createTimeStart" id="createTimeStart" type="text" value="${param.createTimeStart }" style="cursor: pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            - 
	            <input name="createTimeEnd" id="createTimeEnd" type="text" value="${param.createTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</div>
				
				<div class="new_searchBar">	
				    <jecs:title key="pd.okTime"/>
				    <input name="okTimeStart" id="okTimeStart" type="text" value="${param.okTimeStart }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	                - 
	                <input name="okTimeEnd" id="okTimeEnd" type="text" value="${param.okTimeEnd }" style="cursor:pointer;width:76px;"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</div>
				
				<!-- 
				<jecs:title key="pd.okTime"/>
		<input name="okTimeStart" size='11' id="okTimeStart"   value="${requestParaMap.okTimeStart}">
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeStart" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeStart", 
					singleClick    :    true
					}); 
				</script>
				- <input name="okTimeEnd"  size='11' id="okTimeEnd"   value="${requestParaMap.okTimeEnd}">
      	<img src="./images/calendar/calendar7.gif" id="img_okTimeEnd" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "okTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_okTimeEnd", 
					singleClick    :    true
					}); 
				</script>
				 -->
				 <div class="new_searchBar">	
				<jecs:title key="pd.shno"/>
				<jecs:list listCode="pd.shno" name="shNo" showBlankLine="true" id="shNo" value="${requestParaMap.shNo}" defaultValue=""/>
			    </div>
			</c:if>	
			<div class="new_searchBar">	
			<jecs:title key="pdWarehouseStockTrace.orderType"/>
				<jecs:list listCode="pd.send.type" name="orderType" showBlankLine="true" id="orderType" value="${requestParaMap.orderType}" defaultValue=""/>
			</div>		
		    <div class="new_searchBar">		
					<jecs:title key="busi.common.trackingno"/>
						<input name="trackingNo" id="trackingNo" value="<c:out value='${requestParaMap.trackingNo}'/>"  cssClass="text medium"/>
			</div>
			
			<div class="new_searchBar">				
							<jecs:title key="po.order_type"/>
				<jecs:list listCode="po.all.order_type" name="subOrderType" showBlankLine="true" id="subOrderType" value="${requestParaMap.subOrderType}" defaultValue=""/>
			</div>
			<div class="new_searchBar">		
				<jecs:title key="jfiPayByCoin.Button"/>
				<jecs:list listCode="yesno" name="codFlag" showBlankLine="true" id="codFlag" value="${requestParaMap.codFlag}" defaultValue=""/>
            </div>
            <div class="new_searchBar">	
                <jecs:title  key="pd.hurry"/>
        	    <jecs:list listCode="yesno" name="hurryFlag" showBlankLine="true" id="hurryFlag" value="${requestParaMap.hurryFlag}" defaultValue=""/>
        	</div>
        	
        	<div class="new_searchBar">
        	 <jecs:title  key="po.shipment.type"/>
        	<jecs:list listCode="po.shipment.type" name="shipType" showBlankLine="true" id="shipType" value="${requestParaMap.shipType}" defaultValue=""/>	
 			</div>
 			<div class="new_searchBar">	 	
 				<jecs:title  key="pd.keyword"/>
 				<input name="keyword" id="keyword" value="<c:out value='${requestParaMap.keyword}'/>"  cssClass="text medium"/>
			</div>
			<div class="new_searchBar">	 	
				<jecs:title  key="pd.barcode"/>
 				<input name="barCode" id="barCode" value="<c:out value='${requestParaMap.barCode}'/>"  cssClass="text medium"/>
			</div>	
				<c:out value="${buttons}" escapeXml="false"/>
				
			
		
</div>
<ec:table 
	tableId="pdSendInfoListTable"
	items="pdSendInfoList"
	var="pdSendInfo"
	action="${pageContext.request.contextPath}/pdSendInfos.html"
	width="100%" 
	method="post"
	
	form="pdSendInfoList"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				
				
					<!--ec:exportCsv fileName="pdSendInfos.csv" /-->
					<!--ec:exportXls fileName="pdSendInfos.xls"/-->
				<ec:row onclick="showTR('tr${ROWCOUNT}');">
					<c:if test="${requestParaMap.strAction=='sendPdSendInfo'}">		
						<ec:column property="1" title="alCharacterKey.select1" sortable="false" styleClass="centerColumn" style="white-space:nowrap;">
							<input type="checkbox" name="selectedId" id="selectedId" value="${pdSendInfo.siNo}" class="checkbox"/>
						</ec:column>
					</c:if>
					
					<c:if test="${sessionScope.sessionLogin.isManager}">
						<ec:column property="companyCode" title="sys.company.code" style="white-space:nowrap;"/>
					</c:if>
					
					<ec:column property="siNo" title="pdSendInfo.siNo" style="white-space:nowrap;"/>
					<ec:column property="orderNo" title="busi.order.orderno" style="white-space:nowrap;"/>
    			<ec:column property="customer.userCode" title="customerRecord.customerNo" style="white-space:nowrap;">
    				${pdSendInfo.customer.userCode}/${pdSendInfo.customer.userName}
    			</ec:column>
    			
    			
    					
    			<ec:column property="createTime"   title="pd.createTime" style="white-space:nowrap;">
    				<fmt:formatDate value="${pdSendInfo.createTime}" pattern="yyyy-MM-dd"/>
    			</ec:column>
    			
    			
    		
    			
    			<ec:column property="warehouseNo" title="pdSendInfo.sendWarehouseNo" style="white-space:nowrap;"/>
    			
    			<ec:column property="shNo" title="pd.shno" style="white-space:nowrap;">
    				<jecs:code listCode="pd.shno" value="${pdSendInfo.shNo}"/>
    			</ec:column>
    			
    			
    		
    			<ec:column property="orderFlag" title="pdSendInfo.orderFlag" style="white-space:nowrap;">
    				<jecs:code listCode="pdsendinfo.orderflag" value="${pdSendInfo.orderFlag}"/>
    			</ec:column>
    			
    			<ec:column property="shipType" title="po.shipment.type" style="white-space:nowrap;">
    				<jecs:code listCode="po.shipment.type" value="${pdSendInfo.shipType}"/>
				</ec:column>
				
				
				<ec:column property="okTime" title="pd.okTime" style="white-space:nowrap;">
				        <fmt:formatDate value="${pdSendInfo.okTime}" pattern="yyyy-MM-dd"/>
				</ec:column>
				
				
				<ec:column property="trackingNo" title="busi.common.trackingno" styleClass="centerColumn" style="white-space:nowrap;">
    			      				<%-- <a href="#" onclick="javascript:ggetLogisticsDo('${pdSendInfo.logisticsDo}','${pdSendInfo.siNo}');"><font color="green">${pdSendInfo.logisticsDo}</green></a> --%>
    			      <jecs:substr key="${pdSendInfo.trackingNo }" length="10"/>    
    			</ec:column>
			
				
				<ec:column property="edit" title="title.operation" sortable="false" styleClass="centerColumn" viewsAllowed="html"	style="white-space:nowrap;">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editPdSendInfo('${pdSendInfo.siNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
						<c:if test="${requestParaMap.strAction=='sendPdSendInfo'}">		 	
								 	<img src="<c:url value='/images/icons/printer.gif'/>" 
								onclick="javascript:printPdSendInfo('${pdSendInfo.siNo}')"
								 style="cursor: pointer;" title="<jecs:locale key="button.print"/>"/> 
						</c:if>		 	
						
						<c:if test = "${pdSendInfo.warehouseNo=='LC'}">
						<input type="button" class="button" name="ret" onclick="showExtra('${pdSendInfo.siNo}');" value="<jecs:locale key="busi.common.remark"/>" />
					</c:if>
					</ec:column>
					
					
				<c:if test="${ROWCOUNT>0}">
						<c:if test="${ROWCOUNT%2!=0}"><tr class="odddetail" style="display:none" id="tr${ROWCOUNT}"></c:if>
						<c:if test="${ROWCOUNT%2==0}"><tr class="evendetail" style="display:none" id="tr${ROWCOUNT}"></c:if>
							<c:if test="${sessionScope.sessionLogin.isManager}">
								<td  colspan="2" align="right" valign="top">&nbsp;</td>
							</c:if>
							<c:if test="${!sessionScope.sessionLogin.isManager}">
								<td   align="right" valign="top">&nbsp;</td>
							</c:if>
							
								<td colspan="10">
							
							
								${alStateProvinceMap[pdSendInfo.recipientCaNo]}/${alCityMap[pdSendInfo.city]}/${pdSendInfo.recipientAddr}/${pdSendInfo.recipientName}/<c:choose><c:when test="${pdSendInfo.recipientPhone==pdSendInfo.recipientMobile}">${pdSendInfo.recipientMobile}</c:when><c:otherwise>${pdSendInfo.recipientPhone}/${pdSendInfo.recipientMobile}</c:otherwise></c:choose><br/>
								<c:forEach items="${pdSendInfo.pdSendInfoDetails}" var="pdSendInfoDetail" varStatus="status">
								  <c:if test="${pdSendInfoDetail.qty != 0}">
								  
									<c:if test="${!status.first}"><br/></c:if>
									<img src="<c:url value='/images/menu_tree/file12.gif'/>"/>	
									<font color=#888888>[<font color="green">${pdSendInfoDetail.qty}</font>]${pdSendInfoDetail.productNo}/
										<c:forEach items="${compamyProductMap}" var="productEn"><c:if test="${productEn.key eq pdSendInfoDetail.productNo}">${productEn.value}</c:if></c:forEach>
    								<c:if test="${not empty pdSendInfoDetail.combinationProductNo}">
	    								<font color="#FF3333">--<jecs:title key="pd.package"/></font>${pdSendInfoDetail.combinationProductNo}/<c:forEach items="${compamyProductMap}" var="combinationProductNo"><c:if test="${combinationProductNo.key eq pdSendInfoDetail.combinationProductNo}">${combinationProductNo.value}</c:if></c:forEach></font>
									</c:if>
									
									</c:if>

									
								</c:forEach>
							</td>
							
						</tr>
				</c:if>
				
					
			</ec:row >
				
</ec:table>	
</form>

<c:if test="${requestParaMap.strAction=='statisticPdSendInfo'}">
<ec:table 
	tableId="pdSendInfoTotalTable"
	items="pdSendInfoTotal"
	var="pd"
	action="${pageContext.request.contextPath}/pdSendInfos.html"
	width="100%"
	method="post"
	showPagination="false"
	 sortable="false" imagePath="./images/extremetable/*.gif">
				<ec:parameter name="strAction" value="${requestParaMap.strAction}"/>
				<ec:exportCsv fileName="pdSendInfosTotal.csv" encoding="UTF-8"/>
				<ec:exportXls fileName="pdSendInfosTotal.xls" encoding="UTF-8"/>
				<ec:row >
					
					<ec:column property="PRODUCT_NO" title="busi.product.productno" />
    			<ec:column property="PRODUCT_NAME" title="pmProduct.productName" />
    				
    			<ec:column property="QTY" calc="total" calcTitle="busi.logistics.total" styleClass="numberColumn" title="pd.qty" />
					<ec:column property="AMOUNT" calc="total" calcTitle="busi.logistics.total" cell="currency" format="###,###,##0.00" styleClass="numberColumn" title="busi.poMemberOrder.total.amount" />
				</ec:row>

</ec:table>	
</c:if>



<!--form name="form1" action="<c:url value='editPdSendInfo.html'/>">
			<input type="hidden" name="strAction" id="strAction" value='${requestParaMap.strAction}'/>
			<input type="hidden" name='siNo' id='siNo'/>
</form-->

<script type="text/javascript">
	$("#siNoTextarea").click(function(){ 
	 alert('1');
   });
	function reUpload(){
		 
			open("<c:url value='pdSendInfos.html'/>?strAction=reupdatePdWarehouseStock&siNos="+$('siNos').value);
		}
	function showExtra(siNo){
    			open('<c:url value="pdSendExtras.html?strAction=editPdSendExtra&siNo="/>'+siNo);
    		
    		}
    		
	 function addPdSendInfo(){
	 		window.location="<c:url value='pdSendInfos.html'/>?strAction=addPdSendInfo";
	 	}
	 	function printPdSendInfo(siNo){
	 		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printSendInfo&siNo="+siNo);
	 	}

   function editPdSendInfo(siNo){
   				
   			//	form1.siNo.value=siNo;
   			//	form1.submit();
   			<jecs:power powerCode="${requestParaMap.strAction}">
   			
				window.location="editPdSendInfo.html?siNo="+siNo+"&strAction=<c:out value='${requestParaMap.strAction}'/>";
				
			</jecs:power>
		}



function searchUser(){
    			var userCodeExample = $('customCode').value;
    			//open("<c:url value='/sysUserSelect.html'/>?selectControl=agentAndMember&userCode="+userCodeExample);
    			var ret = window.showModalDialog("<c:url value='/sysUserSelect.html'/>?strAction=companyUserSelect&selectControl=agentAndMember&userCode="+userCodeExample,null,"dialogHeight:400px;dialogWidth:1000px;");
    			
    			$('customCode').value=ret['userCode'];
    			$('customCodeName').value=ret['userName'];
    			
    		}
    		
    		
   function viewTracking(trackingNo,shNo){
   	       
   	       var b2='';
   				if(trackingNo.length >1){
   					b2=trackingNo.substring(0,1);
   				}
   	       
		    
   	        if(b2=='E'){
   	            viewEMS(trackingNo);
   	        }else if(shNo=='ZJS'){
    			viewZJS(trackingNo);
    		}else if(shNo=='DTW'){
    			viewDT(trackingNo);
    		}else if(shNo=='ZTO'){
    			viewZTO(trackingNo);
    		}else if(shNo=='ZHONGTIE'){
    			viewZHONGTIE(trackingNo);
    		}else if(shNo=='GUOTONG'){
    			viewGUOTONG(trackingNo);
    		}else if(shNo=='YUNDA'){
    			viewYUNDA(trackingNo);
    		}else if(shNo=='KEJIE'){
    			viewKEJIE(trackingNo);
    		}else if(shNo=='SHUNFENG'){
    			viewSHUNFENG(trackingNo);
    		}else if(shNo=='SHENTONG'){
    			viewSHENTONG(trackingNo);
    		}else if(shNo=='HUITONG'){
    			viewHUITONG(trackingNo);
    		}else if(shNo=='DEBANG'){
    			viewDEBANG(trackingNo);
    		}else if(shNo=='YUANTONG'){
    			viewYUANTONG(trackingNo);
    		}else if(shNo=='BAISHI'){
    			viewBAISHI(trackingNo);
    		}else if(shNo=='BSHT'){
    			viewBSHT(trackingNo);
    		}else if(shNo=='XINYITAI'){
    			viewXINYITAI(trackingNo);
    		}else if(shNo=='SUER'){
    			viewSUER(trackingNo);
    		}else if(shNo=='TLU'){
    			viewTLU(trackingNo);
    		}
   }
  function viewZTO(trackingNo){
   		var url="http://www.zto.cn/bill.aspx?txtbill=";

   		trackNos = trackingNo.split("/");
   		for(i=0;i<trackNos.length;i++){
   			if(!isNaN(trackNos[i])){
   				url = url+trackNos[i]+"%0D%0A";
   			}

   		}
   		window.open(url);
   }
    function viewEMS(trackingNo){
   		var url="http://www.ems.com.cn/";
			window.open(url);
   	}
   function viewZJS(trackingNo){
		var url="http://www.zjs.com.cn";
			window.open(url);
   	}
   	
   	function viewDT(trackingNo){
   		var url="http://www.dtw.com.cn/";
		window.open(url);
   	}
	function viewUPS(trackingNo){
		
			var url="http://wwwapps.ups.com/WebTracking/track?HTMLVersion=5.0&loc=en_US&Requester=UPSHome&track.x=Track&trackNums="+trackingNo;
			window.open(url);
		}
	
	/*****wuliugongsi url*****/
	function viewZHONGTIE(trackingNo){
   		var url="http://www.ztky.com";
		window.open(url);
   	}
   	function viewGUOTONG(trackingNo){
   		var url="http://www.gto365.com";
		window.open(url);
   	}
   	function viewYUNDA(trackingNo){
   		var url="http://www.yundaex.com";
		window.open(url);
   	}
   	function viewKEJIE(trackingNo){
   		var url="http://www.itl.cn";
		window.open(url);
   	}
   	
   	function viewSHUNFENG(trackingNo){
   		var url="http://www.sf-express.com/cn/sc";
			window.open(url);
   	}
   	
   	function viewSHENTONG(trackingNo){
   		var url="http://www.sto.cn";
			window.open(url);
   	}
   	
   	function viewHUITONG(trackingNo){
   		var url="http://www.fxnkd.com.cn";
			window.open(url);
   	}
   	
   	function viewDEBANG(trackingNo){
   		var url="http://www.deppon.com/zhuizong";
			window.open(url);
   	}
   	
   	function viewYUANTONG(trackingNo){
   		var url="http://www.yto.net.cn/cn/index/index.html";
			window.open(url); 
   	}
   	
   	function viewBAISHI(trackingNo){
   		var url="http://www.800best.com";
			window.open(url);
   	}
   	
   	function viewBSHT(trackingNo){
   		var url="http://www.htky365.com";
			window.open(url);
   	}

   	function viewXINYITAI(trackingNo){
   		var url="http://sou.chinawutong.com/";
			window.open(url);
   	}

   	function viewSUER(trackingNo){
   		var url="http://www.sure56.com";
			window.open(url);
   	}

   	function viewTLU(trackingNo){
   		var url="http://www.fyps.cn";
			window.open(url);
   	}
	/************************************/
	function newFuction2(){
			disableButton();
			window.open("<c:url value='/pdSendInfos.html?strAction=newFuction2'/>")
			
		}
    		function importFile(){
					window.open("<c:url value='/pdFileUpload.html?strAction=updateTrackingNo'/>");
			}
	<!-- Add By WuCF 2013-04-18  -->
	function importFile2(flagnum){
		window.open("<c:url value='/pdFileUpload2.html?strAction=updateTrackingNo&flagnum="+flagnum+"'/>");
	}
	<!---->
	function batchPrint(){
   	    var selectedId = document.getElementsByName("selectedId");
		var selectStr = '';
		for(var i=0;i<selectedId.length;i++){ 
			if(selectedId[i].checked){
				selectStr += selectedId[i].value+",";
			}
		}  
		if(selectStr==''){
			alert("<jecs:locale key='amMessage.discuss.select'/>");//?
			return;
		} 
		window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printSendInfo&siNo="+selectStr);		
   }
   
   function showBatchPrintTextArea(){
      window.open("<c:url value='/pdLogisticsBases.html'/>?strAction=pdsiNoTextarea","","height=150, width=120, top=20, left=20, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
   
   	  /* var siNoTextarea = document.getElementById("siNoTextarea");
   	  var sinoButton = document.getElementById("sinoButton");
   	  if(siNoTextarea.style.display==''){
   	  	 siNoTextarea.style.display = 'none';
   	  	 sinoButton.value='<jecs:locale  key='memu.pd.batchPrint.sinoshow'/>';
   	  }else{
   	  	 siNoTextarea.style.display = '';
   	  	 sinoButton.value='<jecs:locale  key='memu.pd.batchPrint.sinounshow'/>';
   	  } */
   }
   
   function clearPrintTextArea(){
   	  var siNoTextarea = document.getElementById("siNoTextarea");
   	  siNoTextarea.value='';
   }
   
   function selectWarehouse(str){
    			
    			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
    	}
   
    /**Add By WuCF 20130502 **/
    function selectWarehouse2(str){
   		window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse2&elementId="+str,"","height=300, width=1000, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
   	}
    
    /**Add By gw 20140710 **/
    function ggetUrl(shNo){
         pdShUrlManager.getShUrl(shNo,openUrl);
   }
   
   /**Add By gw 20140710 **/
   function openUrl(shUrl){        
        window.open(shUrl);
   }

   
   function ggetLogisticsDo(logisticsDo,siNo){
  		window.open("<c:url value='/mailStatuss.html'/>?strAction=logisticsDo&logisticsDo="+logisticsDo+"&siNo="+siNo,"","height=300, width=600, top=20, left=20, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
   }
    
</script>