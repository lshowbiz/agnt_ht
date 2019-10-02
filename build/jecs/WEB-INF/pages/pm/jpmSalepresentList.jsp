<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmSalePromoterList.title"/></title>
<content tag="heading"><jecs:locale key="jpmSalePromoterList.heading"/></content>
<meta name="menu" content="JpmSalePromoterMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="${ctx }/presentSale.html" name="presentSearchForm" method="post">
<div class="searchBar">
<input type="hidden" name="strAction" id="strAction" value="serchPresent">


		<div class="new_searchBar">	
		<jecs:locale key="jpmSalePromoter.time"/>:
		<input name="stime" id="stime" type="text" 
			value="${param.stime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endTime" id="endTime" type="text"
			value="${param.endTime }" style="cursor: pointer;width:70px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
		
		<div class="new_searchBar">	
		<jecs:locale key="jpmSalePromoter.presentno"/>:
		<input type="text" id="presentno" name="presentno" value="${presentno }"/>
		</div>
		
		<div class="new_searchBar">	
		<jecs:locale key="pdProductsDetail.productName"/>:
		<input type="text" id="proName" name="proName" value="${proName }"/>
		</div>
		
		<div class="new_searchBar">	
		<jecs:locale key="jpmSalePromoter.isactiva"/>:
		
			<select id="status" name="status">
				<option value=""><jecs:locale key="list.please.select"/></option>
				<option value="1" <c:if test="${status eq '1' }">selected="selected"</c:if>>
					<jecs:locale key="yesno.yes"/>
				</option>
				<option value="0" <c:if test="${status eq '0' }">selected="selected"</c:if>>
					<jecs:locale key="yesno.no"/>
				</option>
			</select>
		</div>
		
		<div class="new_searchBar">	
			<button type="submit" class="btn btn_sele">
				<jecs:locale key="operation.button.search"/>
			</button>
			
			<jecs:power powerCode="addSalePresent">
				    <button type="button" class="btn btn_ins" style="margin-right: 5px"
				        onclick="location.href='<c:url value="/editJpmSalePeresent.html"/>?strAction=addSalePresent'"
				        ><jecs:locale key="button.add"/></button>
			</jecs:power>
			
			<button type="button" class="btn btn_ins" style="margin-right: 5px"
				        onclick="deleteJpmSalePromoter()"
				        ><jecs:locale key="operation.button.delete"/></button>
			
		</div>
		
</div>
</form>

<input type="hidden" name="spnos" id="spnos">

<ec:table tableId="jpmSalepresentListTable" items="jpmSalepresentList"
	var="jpmSalePromoter" autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/presentSale.html"
	width="100%" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="false" imagePath="${ctx }/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="spnoChkBox" title="button.select">
					<input type="checkbox" id="spno" name="spno" value="${jpmSalePromoter.spno }">
				</ec:column>
<%-- 				<ec:column property="spno" title="jpmSalePromoter.promoterId" /> --%>
				<ec:column property="spname" title="jpmSalePromoter.promoterName" />
    			<ec:column property="startime" title="jpmSalePromoter.startime" >
    				<fmt:formatDate value="${jpmSalePromoter.startime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			<ec:column property="endtime" title="jpmSalePromoter.endtime" >
    				<fmt:formatDate value="${jpmSalePromoter.endtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			<ec:column property="presentno" title="jpmSalePromoter.presentno" />
    			<ec:column property="presentname" title="jpmSalePromoter.presentname" />
    			<ec:column property="presentlimit" title="jpmSalePromoter.presentlimit"/>
    			<%-- 
    			<ec:column property="teamno" title="jpmSalePromoter.teamno" />
    			<ec:column property="ordertype" title="jpmSalePromoter.ordertype" />
    			<ec:column property="companyno" title="jpmSalePromoter.companyno" />
    			--%>
    			<ec:column property="isactiva" title="jpmSalePromoter.isactiva" >
    				<c:if test="${jpmSalePromoter.isactiva==1 }">
    					<jecs:locale key="yesno.yes"/>
    				</c:if>
    				<c:if test="${jpmSalePromoter.isactiva==0 }">
    					<jecs:locale key="yesno.no"/>
    				</c:if>
    			</ec:column>
    			<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
					<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJpmSalePromoter('${jpmSalePromoter.spno}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					<img src="<c:url value='/images/icons/bindProduct.gif'/>" 
								onclick="javascript:productBind('${jpmSalePromoter.spno}')"
								style="cursor: pointer;" title="<jecs:locale key="jpmSalePromoter.bindpresent"/>"/> 
					<img src="<c:url value='/images/icons/view.gif'/>" 
								onclick="javascript:disountProductList('${jpmSalePromoter.spno}')"
								style="cursor: pointer;" title="<jecs:locale key="jpmSalePromoter.bindpresentList"/>"/>
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJpmSalePromoter(spno){
   		window.location="${ctx}/editJpmSalePeresent.html?strAction=editSalePresent&spno="+spno;
    }
   
	function deleteJpmSalePromoter(){
		var spnoArr = document.getElementsByName("spno");
		var spnos = document.getElementById("spnos");
		var ischked = false;
		for(var i=0; i<spnoArr.length; i++){
			if(spnoArr[i].checked){
				spnos.value+=","+spnoArr[i].value;
				ischked=true;
			}
		}
		if(ischked){
			if(confirm ('<jecs:locale key="jpmSalePromoter.isDel"/>?')){
				window.location="${ctx}/presentSale.html?strAction=deleteSalePresent&spnos="+spnos.value;
			}
		}else {
			alert('<jecs:locale key="jpmSalePromoter.ischeck"/>!');
			return;
		}
		
	}
	function productBind(spno){
		window.location="${ctx}/presentSale.html?strAction=showPresentPage&spno="+spno;
	}
	function disountProductList(spno){
		window.location="${ctx}/presentSale.html?strAction=showPresentList&spno="+spno;
	}
</script>
