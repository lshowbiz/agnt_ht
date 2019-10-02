<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="prRefundDetail.title"/></title>
<content tag="heading"><jecs:locale key="prRefundDetail.heading"/></content>

<c:set var="buttons">
<c:if test="${strAction !='alreadyCheck'}">
	<jecs:power powerCode="${param.strAction }">   
	         <button type="submit" class="btn btn_sele" name="save" >
	         		<jecs:locale key="operation.button.save"/>
	         </button>
	</jecs:power>
</c:if>
	<c:if test='${not empty jprRefund.roNo }'>
	     <!-- 
	        <c:if test="${jprRefund.orderStatus=='1'}">
			        <input type="button" class="button" onclick="alreadySubmit(document.prRefundForm)" name="save" value="<jecs:locale key="button.submit"/>"/>
			</c:if>
		 -->	
			<c:if test="${jprRefund.orderStatus=='2' && param.strDifference=='strDifference'}">
			    	<input type="button" class="button" onclick="toNewSubmit(document.prRefundForm)" name="save" value="<jecs:locale key="button.cancelToNew"/>"/>
			        <input type="button" class="button" onclick="checkSubmit(document.prRefundForm)" name="save" value="<jecs:locale key="operation.button.check"/>"/>
			</c:if>
			<!--  
			<c:if test="${jprRefund.orderStatus=='3' && param.strDifference=='strDifference'}">
			    	<input type="button" class="button" onclick="toNewSubmit(document.prRefundForm)" name="save" value="<jecs:locale key="button.cancelToNew"/>"/>
			</c:if>
			-->
	<jecs:power powerCode="deletePrRefund">
	           <button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PrRefund')">
	           		<jecs:locale key="operation.button.delete"/>
	           </button>
	</jecs:power>
	</c:if>
	<button type="button"  class="btn btn_sele" name="back" onclick="history.back()">
		<jecs:locale key="operation.button.return"/>
	</button>
</c:set>

<spring:bind path="jprRefund">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>
<script>
  var aSub = Array();
  function set_amt(nQty,no)
  {	
   
    if (!isFinite(nQty.value) || nQty.value < 0 ){
	  	nQty.value=aSub[no]['qty'];
	}else {
	  nQty.value=Number(nQty.value);
	  aSub[no]['qty']=Number(nQty.value);
	}
	amt_count();
  }
  function set_amt1(nPrice,no)
  {	
    if (!isFinite(nPrice.value) || nPrice.value < 0 ){
	  	nPrice.value=aSub[no]['price'];
	}else {
	  nPrice.value=Number(nPrice.value);
	  aSub[no]['price']=Number(nPrice.value);
	}
	amt_count();
  }
  function set_amt2(nPv,no)
  {	
    if (!isFinite(nPv.value) || nPv.value < 0 ){
	  	nPv.value=aSub[no]['nPv'];
	}else {
	  nPv.value=Number(nPv.value);
	  aSub[no]['pv']=Number(nPv.value);
	}
	amt_count();
  }
  function amt_count(){
    var price_amt = document.getElementById('total_price');
    var pv_amt = document.getElementById('total_pv');
    var qty_amt = document.getElementById('total_qty');
 	var nPriceAmt=Number(0);
	var nPvAmt=Number(0);
	var nPvQty=Number(0);
	<c:forEach  var="arl" items="${jprRefund.jpoMemberOrder.jpoMemberOrderList}" varStatus="status">
	nPriceAmt+=Number(aSub[${arl.molId}]['qty'])*Number(aSub[${arl.molId}]['price']);
	nPvAmt+=Number(aSub[${arl.molId}]['qty'])*Number(aSub[${arl.molId}]['pv']);
	nPvQty+=Number(aSub[${arl.molId}]['qty']);
	</c:forEach>
    price_amt.innerHTML=nPriceAmt;
    pv_amt.innerHTML=nPvAmt;
    qty_amt.innerHTML=nPvQty;
  }
</script>
<c:if test="${productNoHasRetired !='productNoHasRetired'}">
<form:form commandName="jprRefund" method="post" action="editJprRefund.html" onsubmit="watingwww();if(validateJprRefund(this)){if(isFormPosted()){return true;}{return false;}}else{return false;}" id="prRefundForm" name="prRefundForm">

<input type="hidden" name="moId" value="${param.moId }"/>
<input type="hidden" name="roNo" value="${jprRefund.roNo }"/>
<input type="hidden" name="strAction" value="${param.strAction }" id="strAction"/>

<table class='detail' width="70%" >
  <tbody class="window" >
    <tr class="edit_tr">
    
	    <th><span class="text-font-style-tc"><jecs:label  key="poMemberOrder.memberOrderNo"/></span></th>
			<td><span class="textbox">${jprRefund.jpoMemberOrder.memberOrderNo}</span></td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span></th>
			<td><span class="textbox">${jprRefund.sysUser.userCode}</span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><span class="req">*</span><jecs:label  key="prRefund.resendSpNo"/></span></th>
			<td style="white-space:nowrap;">
			<span class="textbox">
				<input class="textbox-text" name="resendSpNo" value="${jprRefund.resendSpNo}" id="resendSpNo" class="readonly" readonly="readonly"/>
				<!-- <input type="button" class="selectBtn" name="more" onclick="selectWarehouse('resendSpNo');" value="..." /> -->
			</span>
			<button type="button" class="btn btn_sele" name="more" onclick="selectWarehouse('resendSpNo');" >
				     <jecs:locale key="button.select"/>
			     </button>
			</td>
		
			<th><span class="text-font-style-tc"><jecs:label  key="poMemberOrder.pvAmt"/></span></th>
			<td><span class="textbox">${jprRefund.pvAmt}</span></td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="busi.order.amount"/></span></th>
			<td><span class="textbox">${jprRefund.amount}</span></td>
		
			    <th><span class="text-font-style-tc"><jecs:label  key="jprrefund.type"/></span></th>
			    <td><span class="textbox">
			    <jecs:list styleClass="textbox-text" name="refundTye" listCode="jprrefund.refundtype" defaultValue="0" value="${jprRefund.refundTye}"  id="refundTye" ></jecs:list>
			    </span></td>
		   </tr>
			<c:if test='${not empty jprRefund.roNo }'>
			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><jecs:label  key="busi.order.createTime"/></span></th>
				<td><span class="textbox">
	        		<fmt:formatDate value="${jprRefund.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.createUNo }"/>
	        	</span></td>
			
				<th><span class="text-font-style-tc"><jecs:label  key="busi.order.editTime"/></span></th>
				<td><span class="textbox">
	        		<fmt:formatDate value="${jprRefund.editTime }" pattern="yyyy-MM-dd hh:mm:ss"/> - <c:out value="${jprRefund.editUNo }"/>
	        	</span></td>
			</tr>
			</c:if>
				   
		   <tr class="edit_tr">
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeTwo"/></span></th>
			    <td><span class="textbox">
			                <input class="textbox-text" name="renovationFee" value="${jprRefund.renovationFee}" id="renovationFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeThree"/></span></th>
			    <td><span class="textbox">
			               <input class="textbox-text" name="logisticsFee" value="${jprRefund.logisticsFee}" id="logisticsFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   </tr>
		   <tr class="edit_tr">
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeFour"/></span></th>
			    <td><span class="textbox">
			    	       <input class="textbox-text" name="settledBonus" value="${jprRefund.settledBonus}" id="settledBonus" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeFive"/></span></th>
			    <td><span class="textbox">
			    			<input class="textbox-text" name="fillFreight" value="${jprRefund.fillFreight}" id="fillFreight" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   </tr>
		   
		   <!-- 
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeSix"/></span></th>
			    <td><span class="textbox">
			               <input name="logisticsFeeSix" value="${jprRefund.logisticsFeeSix}" id="logisticsFeeSix" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   </tr>
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeSeven"/></span></th>
			    <td><span class="textbox">
			              <input name="logisticsFeeSeven" value="${jprRefund.logisticsFeeSeven}" id="logisticsFeeSeven" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   </tr>
		   <tr>
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeEight"/></span></th>
			    <td><span class="textbox">
			              <input name="logisticsFeeEight" value="${jprRefund.logisticsFeeEight}" id="logisticsFeeEight" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>
			    </span></td>
		   </tr>
		    -->
			<!-- modify gw 2014-11-03  end-->
		   
		   <tr>
			
			<!-- modify gw 2014-11-03  begin-->
			    <th><span class="text-font-style-tc"><jecs:label  key="jprRefund.logisticsFeeOne"/></span></th>
			    <td><span class="textbox">
			    <input name="repairFee" class="textbox-text" value="${jprRefund.repairFee}" id="repairFee" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value"/>  
			    </span></td>                
		   </tr>
		   <tr class="edit_tr">
				<th><span class="text-font-style-tc-tare"><jecs:label  key="prRefund.remark"/></span></th>
				<td colspan="3">
					<span class="text-font-style-tc-right">
					  <textarea class="textarea_border" name="remark"/>${jprRefund.remark}</textarea>
					</span>
				</td>
		   </tr>
		   
		   
	<tr>
		<td class="command" colspan="4" align="center">
					<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>
	</tbody>
</table>
	

<c:set var="footTotalVar">
<tr>
	<td class="footer" align="right">
		&nbsp;
	</td>
	<td align="right" class="footer"><jecs:locale key="poOrder.amtCount"/></td>
	<td class="footer" align="right">
		<b><span id='total_qty'>0</span></b>
	</td>
	<td class="footer" align="right">
		<b><span id='total_price'>0</span></b>
	</td>
	<td class="footer">
		&nbsp;
	</td>
	<td class="footer" align="right">
		<b><span id='total_pv'>0</span></b>
	</td>
</tr>
</c:set>

<ec:table
	tableId="sellStock"
	items="jprRefund.jpoMemberOrder.jpoMemberOrderList"
	var="pi"
	width="100%" form = "prRefundForm"
	showPagination="false" sortable="false" imagePath="./images/extremetable/*.gif" footer="${footTotalVar }">
				<ec:row>
				<ec:column property="1" title="<input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchChange();>" sortable="false" styleClass="centerColumn">
					<input type="checkbox" name="selectedId" id="selectedId" value="${pi.molId}" class="checkbox" onclick="switchTdChange(this);"/>
				</ec:column>
    			<ec:column property="jpmProductSaleTeamType.jpmProductSaleNew.productName" title="pmProduct.productName"/>
    			<ec:column property="qty" title="pd.qty" styleClass="numberColumn">
    			  
    			</ec:column>
    			 <input type="hidden" id="hid<c:out value="${pi.molId}"/>"  value="${pi.qty}"/>
    			<ec:column property="pr_qty" title="pr.Stock.returnqty" styleClass="centerColumn">
				    <input type="hidden" name="molId" id="mol_id[<c:out value="${pi.molId }" />]" value="${pi.molId }" />
				    <input type="hidden" name="g_no" id="g_no[<c:out value="${pi.molId }" />]" value="${pi.jpmProductSaleTeamType.pttId }" />
				    <input type='text' class='text medium' name='qty' value='0' onchange="set_amt(this,'${pi.molId }');" size='3' id=qty<c:out value="${pi.molId }" />>
    			</ec:column>
    			<ec:column property="product_price" title="pd.price" styleClass="centerColumn">
    			<c:if test="${sessionLogin.companyCode=='CN' }">
				    <input type="text" class='text medium' name='price' value='<c:out value="${pi.price }" />' onchange="set_amt1(this,'${pi.molId }');" size='3' id=price<c:out value="${pi.molId }" />>
    			</c:if>
    			<c:if test="${sessionLogin.companyCode!='CN' }">
    				<input type="hidden" class='text medium' name='price' value='<c:out value="${pi.price }" />' onchange="set_amt1(this,'${pi.molId }');" size='3' id=price<c:out value="${pi.molId }" />>
    				${pi.price }
    			</c:if>
    			</ec:column>
    			<ec:column property="product_pv" title="busi.direct.pv" styleClass="centerColumn">
    			
				    <!--  <input type='hidden' class='text medium' name='pv' value='<c:out value="${pi.pv }" />' onchange="set_amt2(this,'${pi.molId }');" size='3' id=pv<c:out value="${pi.molId }" />>
				    ${pi.pv }
				    -->
				    <input type='text' class='text medium' name='pv' value='<c:out value="${pi.pv }" />' onchange="set_amt2(this,'${pi.molId }');" size='3' id=pv<c:out value="${pi.molId }" />>
				    <script>
						aSub[<c:out value="${pi.molId }" />] = Array();
						aSub[<c:out value="${pi.molId }" />]['g_no']='<c:out value="${pi.molId }" />';
						aSub[<c:out value="${pi.molId }" />]['qty']='0';
						aSub[<c:out value="${pi.molId }" />]['price']='<c:out value="${pi.price }" />';
						aSub[<c:out value="${pi.molId }" />]['pv']='<c:out value="${pi.pv }" />';
					</script>
    			</ec:column>
				</ec:row>
</ec:table>
</form:form>
</c:if>
<c:if test="${productNoHasRetired == 'productNoHasRetired' }">
             <table class='detail' width="100%">
                 <tr><td align="center"><font color="red" size="5"><jecs:locale key="pdWarehouseStock.productNo" /><jecs:locale key="operation.notice.js.refunded" /></font></span></td></tr>
                         	<tr><td align="center"><input type="button"  class="button" name="back" value="<jecs:locale key="operation.button.return"/>" onclick="history.back()"/></span></td></tr>
             </table>
</c:if>
<script type="text/javascript">
    Form.focusFirstElement($('prRefundForm'));

	<c:forEach  var="arl" items="${jprRefund.jprReFundList}" varStatus="status">
		prRefundForm.qty<c:out value="${arl.molId }" />.value='<c:out value="${arl.qty }" />';
		prRefundForm.price<c:out value="${arl.molId }" />.value='<c:out value="${arl.price }" />';
		prRefundForm.pv<c:out value="${arl.molId }" />.value='<c:out value="${arl.pv }" />';
		aSub[<c:out value="${arl.molId }" />]['qty'] = '<c:out value="${arl.qty }" />'
		aSub[<c:out value="${arl.molId }" />]['price']='<c:out value="${arl.price }" />';
		aSub[<c:out value="${arl.molId }" />]['pv']='<c:out value="${arl.pv }" />';
	</c:forEach>
amt_count();
    function selectWarehouse(elementId){
		var winName="<jecs:locale key="menu.pd.listPdWarehouses"/>";
		var theURL="pdWarehouses.html?strAction=selectWarehouse&elementId="+elementId;
		window.open(theURL,winName,'scrollbars=yes,width=1000,height=400');
     }
</script>

<script type="text/javascript">
function switchChange(){
            var selectedAll=document.getElementsByName("selectedAll");
 			var selectedId=document.getElementsByName("selectedId");
			if(selectedId!=undefined){
				for(var i=0;i<selectedId.length;i++){
					selectedId[i].checked=selectedAll[0].checked;
					if(selectedAll[0].checked==false)
					{
					   	document.getElementById("qty"+selectedId[i].value).value=0;
					}else
					{
						document.getElementById("qty"+selectedId[i].value).value=document.getElementById("hid"+selectedId[i].value).value;
					}
					var qtyObject=document.getElementById("qty"+selectedId[i].value);
					set_amt(qtyObject,selectedId[i].value);
				}
			}
}

function switchTdChange(object)
{
     if(object.checked)
     {
         document.getElementById("qty"+object.value).value=document.getElementById("hid"+object.value).value;
      
     }else
     {
          	document.getElementById("qty"+object.value).value=0;
     }
     var tdObject=document.getElementById("qty"+object.value);
     set_amt(tdObject,object.value);
}

function alreadySubmit(theForm){    
       if(validateJprRefund(theForm)){
            document.getElementById("strAction").value="alreadySubmit";
            theForm.submit();
       }  
}

function toNewSubmit(theForm){    
       if(validateJprRefund(theForm)){
            document.getElementById("strAction").value="toNewSingle";
            theForm.submit();
       }  
}

function checkSubmit(theForm){    
       if(validateJprRefund(theForm)){
            document.getElementById("strAction").value="alreadyCheck";
            theForm.submit();
       }  
}

function watingwww(){
waiting();
}

</script>


<v:javascript formName="jprRefund" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>