<%@ include file="/common/taglibs.jsp"%>

<title><wecs:locale key="miLinkRefList.title"/></title>
<content tag="heading"><wecs:locale key="miLinkRefList.heading"/></content>


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jbdUserCardListManager.js'/>" ></script>
<script>
		var cardTypeMap={
			
		}
		   function searchMiMember(){
		   var loadinfo = "loading....." ; 
		   var userCode=document.getElementById('userCode').value;
		   jmiMemberManager.getJmiMember(userCode,callBack);
		       DWRUtil.useLoadingMessage(loadinfo);  
		   }
		   function callBack(valid){
			   if(valid==null){
			   	alert('<jecs:locale key="operation.notice.js.orderNo.miMember.memberNoError"/>');
			   }else if('${sessionScope.sessionLogin.companyCode}'!='AA'&&'${sessionScope.sessionLogin.companyCode}'!=valid.companyCode){
			   	alert('error');
			   }else{
				   document.getElementById('memberName').innerText=valid.petName;
				   getJbdUserCardList();
				   if(valid.cardType==0){
				   	   document.getElementById('memberCardType').innerText="  <jecs:locale key='miMember.cardType0'/>";
				   	   selectCardType(0);
				   }else if(valid.cardType==1){
				   	   document.getElementById('memberCardType').innerText="  <jecs:locale key='miMember.cardType1'/>";
				   	   selectCardType(1);
				   }else if(valid.cardType==2){
				   	   document.getElementById('memberCardType').innerText="  <jecs:locale key='miMember.cardType2'/>";
				   	   selectCardType(2);
				   }else if(valid.cardType==3){
				   	   document.getElementById('memberCardType').innerText="  <jecs:locale key='miMember.cardType3'/>";
				   	   selectCardType(3);
				   }else if(valid.cardType==4){
				   	   document.getElementById('memberCardType').innerText="  <jecs:locale key='miMember.cardType4'/>";
				   	   selectCardType(4);
				   }else if(valid.cardType==5){
				   	   document.getElementById('memberCardType').innerText="  <jecs:locale key='miMember.cardType5'/>";
				   	   selectCardType(5);
				   }else if(valid.cardType==6){
				   	   document.getElementById('memberCardType').innerText="VIP";
				   	   selectCardType(6);
				   }
			   }
		   }
		   function getJbdUserCardList(){
		   	 var userCode=document.getElementById('userCode').value;
		   	 jbdUserCardListManager.getJbdUserCardListByUserCodeFweek(userCode,callBackJbdUserCardLis);
		   }
		     function callBackJbdUserCardLis(valid){
	
				 DWRUtil.removeAllRows("tbodyId"); 
		     	 DWRUtil.addRows("tbodyId", valid, cellFunctions,{
        rowCreator:function(options) { 
            var row = document.createElement("tr"); 
			var index = options.rowIndex;
			if(index%2==0){
				row.className='even';
			}else{
				row.className='odd';
			}
            return row; 
        }, 
        cellCreator:function(options) { 
            var td = document.createElement("td"); 

            return td; 
        } 
      });

		     }
		   function selectCardType(cardTypeValue){
		   		cardType = document.getElementById('cardType');
		   		for(i=0;i<cardType.options.length;i++){
		   			if(cardType.options[i].value==cardTypeValue){
		   				cardType.options[i].selected=true;
		   			}else{
		   				cardType.options[i].selected=false;
		   			}
		   		}
		   }

			var cellFunctions = [ 
            function(item) { return item.wweek; }, 
            function(item) { return o[item.oldCard]; }, 
            function(item) { return o[item.newCard]; }, 
            function(item) { return updateType[item.updateType]; } 
      
        ];
	var o = new Object();
	o={0:'<jecs:locale key="miMember.cardType0"/>',1:'<jecs:locale key="miMember.cardType1"/>',2:'<jecs:locale key="miMember.cardType2"/>',3:'<jecs:locale key="miMember.cardType3"/>',4:'<jecs:locale key="miMember.cardType4"/>',5:'<jecs:locale key="miMember.cardType5"/>',6:'VIP'};
	var updateType=new Object();
	updateType={0:'',1:'<jecs:locale key="member.first.upgrade"/>',2:'<jecs:locale key="member.secend.upgrade"/>',3:'<jecs:locale key="member.3.upgrade"/>'};
	
</script>

<form action="" method="post" name="miMemberForm" id="miMemberForm" onsubmit="return validateOthers(this)">
<%-- 
<div class="searchBar">	
<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
	</div>--%>
<table class='detail' width="70%">
	<tbody class="window">
	<tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc"><jecs:label key="bd.week.update" required="true"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	    		<input type="text" name="wweek" id="wweek" value="${param.wweek }" class="textbox-text"/>
	    	</span>
	    </td>
	
    	<th>
        	<span class="text-font-style-tc"><jecs:label key="miMember.memberNo"/></span>
    	</th>
	    <td>
	    	<span class="textbox">
	    		<input type="text" name="userCode" id="userCode" value="${userCode }" class="textbox-text"/>
	    	</span>
	    	<img src="<c:url value="/images/l_1.gif"/>" id="person"  onclick="searchMiMember();" 
	    		style="cursor: pointer;" title="<jecs:locale key="operation.button.search"/>"/> 
	    	<span id="memberName"></span>	
	    </td>
    </tr>
    <tr class="edit_tr">
    	<th>
        	<span class="text-font-style-tc"><jecs:label key="member.change.cardtype"/></span>
    	</th>
    	<td>
	        <span class="textbox">
	        	<jecs:list name="cardType" id="cardType" listCode="bd.cardtype"
	        	 value="" defaultValue="" showBlankLine="true" styleClass="textbox-text"/>
	        </span>
    	</td>
  
	    <th>
	    	<span class="text-font-style-tc"><jecs:label key="cur.cardType"/></span>
	    </th>
	    <td>
	    	<span id="memberCardType" class="textbox"></span>	
	    </td>
	</tr>
    <tr class="edit_tr">
    	<th>
        	<span class="text-font-style-tc-tare"><jecs:label key="busi.common.remark"/></span>
    	</th>
	    <td colspan="3">
	        <span class="text-font-style-tc-right">
	        	<textarea id="miRemark" name="miRemark" class="textarea_border"></textarea>
	        </span>
	    </td>
	</tr>
	
	<tr class="edit_tr">
		<td class="command" colspan="4" align="center">
			<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
				<jecs:locale key="operation.button.save"/>
			</button>
		</td>
	</tr>
    </tbody>
</table>
</form>



			<div class="eXtremeTable">
				<table id="poMemberOrderSearchListTable_table" border="0"
					cellspacing="0" cellpadding="4" class="tableRegion" width="100%">
					<thead>
						<tr>
							<td class="tableHeader"><jecs:locale key="bdBounsDeduct.wweek"/></td>
							<td class="tableHeader"><jecs:locale key="member.oldCard"/>	</td>
							<td class="tableHeader"><jecs:locale key="member.newCard"/></td>
							<td class="tableHeader"><jecs:locale key="bdReconsumMoneyReport.typeCH"/></td>
						</tr>
					</thead> 
					<tbody  id="tbodyId" class="tableBody">


					</tbody>
				</table>
			</div>



<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.wweek.value=="" || theForm.wweek.value.length!=6 || !isUnsignedInteger(theForm.wweek.value)){
		alert("<jecs:locale key="week.isError"/>");
		theForm.wweek.focus();
		return false;
	}
	//setTimeout('showProgressBar()', 4000);
	return true;
}
</script>