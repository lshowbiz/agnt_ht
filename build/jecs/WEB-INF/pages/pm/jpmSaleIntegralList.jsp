<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmSalePromoterList.title"/></title>
<content tag="heading"><jecs:locale key="jpmSalePromoterList.heading"/></content>
<meta name="menu" content="JpmSalePromoterMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="${ctx }/integralSale.html" name="integralSearchForm" method="post">
<div class="searchBar">
<input type="hidden" name="strAction" id="strAction" value="serchIntegral">

<div class="new_searchBar">	
		<jecs:locale key="jpmSalePromoter.time"/>:
		<input name="stime" id="stime" type="text" 
			value="${param.stime }" style="cursor: pointer;width:75px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			-
			<input name="endTime" id="endTime" type="text"
			value="${param.endTime }" style="cursor: pointer;width:75px;"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</div>
		
<div class="new_searchBar">			
		<jecs:locale key="jpmSalePromoter.integral"/>:
		<input type="text" id="integral" name="integral" value="${integral }"/>
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
		
		<button type="button" class="btn btn_ins" style="margin-right: 5px"
			onclick="location.href='<c:url value="/editJpmSaleIntegral.html"/>?strAction=addIntegral'"
			><jecs:locale key="button.add"/></button>
		
		<button type="button" class="btn btn_ins" style="margin-right: 5px"
			onclick="deleteJpmSalePromoter()"
			><jecs:locale key="operation.button.delete"/></button>
			
	</div>

</div>
</form>

<input type="hidden" name="spnos" id="spnos">

<ec:table tableId="jpmSaleIntegralListTable" items="jpmSaleIntegralList"
	var="jpmSalePromoter" autoIncludeParameters="true"
	action="${pageContext.request.contextPath}/integralSale.html"
	width="100%"
	rowsDisplayed="20" sortable="false" imagePath="${ctx }/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="id" title="button.select">
					<input type="checkbox" id="spno" name="spno" value="${jpmSalePromoter.spno }">
				</ec:column>
<%-- 				<ec:column property="spno" title="jpmSalePromoter.promoterId" /> --%>
				<ec:column property="spname" title="jpmSalePromoter.promoterName" />
    			<ec:column property="startime" title="jpmSalePromoter.startime" >
    				<fmt:formatDate value="${jpmSalePromoter.startime }" pattern="yyyy-MM-dd HH:mm:ss"  />
    			</ec:column>
    			<ec:column property="endtime" title="jpmSalePromoter.endtime" >
    				<fmt:formatDate value="${jpmSalePromoter.endtime }" pattern="yyyy-MM-dd HH:mm:ss"  />
    			</ec:column>
    			<ec:column property="integral" title="jpmSalePromoter.integral" />
    			
    			<ec:column property="amount" title="jpmSalePromoter.amount" />
    			<ec:column property="pv" title="jpmSalePromoter.pv" />
    			<%--
    			<ec:column property="teamno" title="jpmSalePromoter.teamno" />
    			<ec:column property="ordertype" title="jpmSalePromoter.ordertype">
    				 <span onclick="showOrder(document.getElementById('orderDiv'));">...</span>
					<div style="position:relative;">
	    				<div id="orderDiv" style="display:none;">
							<table>
							<tr><td>${jpmSalePromoter.ordertype }</td></tr>
							</table>
						</div>
					<div>
    			</ec:column>
    			<ec:column property="companyno" title="jpmSalePromoter.companyno" />
    			--%>
    			<ec:column property="isactiva" title="jpmSalePromoter.isactiva">
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
				</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">
	function showOrder(obj){
		if(obj.style.display=='block'){
			obj.style.display='none';
		}else {
			obj.style.display = 'block';
			obj.style.zIndex = '9999';
			obj.background = 'red';
			obj.style.top = '-2px';
			obj.style.right = '0';
		}
			
	}
	
	function editJpmSalePromoter(spno){
   		window.location="${ctx}/editJpmSaleIntegral.html?strAction=editIntegral&spno="+spno;
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
				window.location="${ctx}/integralSale.html?strAction=deleteIntegral&spnos="+spnos.value;
			}
		}else {
			alert('<jecs:locale key="jpmSalePromoter.ischeck"/>!');
			return;
		}
	}
	function duibi(a, b) {
	    var arr = a.split("-");
	    var starttime = new Date(arr[0], arr[1], arr[2]);
	    var starttimes = starttime.getTime();

	    var arrs = b.split("-");
	    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
	    var lktimes = lktime.getTime();

	    if (starttimes >= lktimes) {
	        alert('<jecs:locale key="jpmSalePromoter.etimeBefStime"/>!');
	        return false;
	    }
	    else
	        return true;

	}
</script>
