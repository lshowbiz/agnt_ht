<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmConfigDetailedDetail.title"/></title>
<style>
.inputSubmit {border:none;background:none;color:blue;cursor:pointer;}
.inputSubmit:hover {text-decoration:underline;}
</style>
<script src="${ctx}/scripts/jquery/jquery-142min.js"></script>
<content tag="heading"><jecs:locale key="jpmConfigDetailedDetail.heading"/></content>
<form:form commandName="jpmConfigDetailed" method="post" action="editJpmConfigDetailed.html" onsubmit="return saveConfig();" id="jpmConfigDetailedForm">
	<input type="hidden" name="jpmConfigDetailedSize" id="jpmConfigDetailedSize"/>
	<input type="hidden" name="molId" id="molId" value="${ model.molId }"/>
	<input type="hidden" name="productNo" id="productNo" value="${ model.productNo }" />
	<input type="hidden" name="specNo" id="specNo" value="${ model.specNo }"/>
	<input type="hidden" name="saveType" id="saveType" value="${ model.saveType }"/>
	<input type="hidden" name="configNo" id="configNo" value="${ model.configNo }"/>
	<input type="hidden" name="moId" id="moId" value="${ model.moId }"/>
	<input type="hidden" name="productId" id="productId" value="${ model.productId }"/>
	<input type="hidden" name="productName" id="productName" value="${ model.productName }"/>
	<input type="hidden" name="currentMaxWeight" id="currentMaxWeight" value="${ model.curWeight }"/>
	<input type="hidden" name="maxWeight" id="maxWeight" value="${ model.chooseWeightCount }"/>
	<input type="hidden" name="maxNum" id="maxNum" value="${ model.chooseConfigNum }"/>
	
	<table width="100%" class="personalInfoTab" id="table_n">
		<colgroup style="width:70px;" />
		<colgroup />
		<colgroup style="width:70px;" />
		<colgroup />
		<colgroup style="width:70px;" />
		<colgroup />
		<colgroup style="width:94px;" />
		<colgroup />
		<tbody>
			<tr>
				<td><jecs:label key="jpmConfigDetailed.configModel"/> </td>
				<td>
					<select name="productTemplateNo" id="productTemplateNo"	class="mySelect" onchange="">
					<c:if test="${empty template.productTemplateNo}">
						<option value="">
							<ng:locale key="list.please.select" />
						</option>
					</c:if>
					<c:if test="${not empty template.productTemplateNo}">
						<option value="${template.productTemplateNo }" selected="selected">
							${template.productTemplateName }
						</option>
						<option value="">
							<ng:locale key="list.please.select" />
						</option>
					</c:if>
					<c:forEach items="${templateList}" var="template">
						<option value="${template.productTemplateNo }">
							${template.productTemplateName }
						</option>
					</c:forEach>
					</select>
				</td>
				<td><jecs:label key="jpmConfigDetailed.configNum"/></td>
				<td>
					<input type="text" id="chooseConfigNum" name="chooseConfigNum" <c:if test="${model.saveType ne '0'}"> class='inputOs' style='width:25px;' readonly='readonly' </c:if> value="${ model.chooseConfigNum }" onblur="changeConfigNum();" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'>
				</td>
				<td><jecs:label key="jpmConfigDetailed.configWeight"/></td>
				<td><input type="text" id="curWeight" name="curWeight" value="<c:out value="${ model.curWeight }" />" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'></td>
				<td><jecs:label key="jpmConfigDetailed.chooseWeightCount"/></td>
				<td>
					<input type="text" id="chooseWeightCount" name="chooseWeightCount" value="<c:out value="${ model.chooseWeightCount }" />" class='inputOs' style='width:30px;' readonly='readonly' onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'>
				</td>
			</tr>
		</tbody>
	</table>
	
	<table width="100%">
		<colgroup></colgroup>
		<colgroup style="width:130px;"></colgroup>
		<colgroup style="width:130px;"></colgroup>
		<tbody>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><a href="javascript:void(0);" style="color:blue;" onclick="changeModel('jpmWineMemberOrders.html?strAction=configDetailed');"><jecs:locale key="jpmConfigDetailed.showConfigList"/></a></td>
			</tr>
		</tbody>
	</table>
		
	<h3><jecs:locale key="jpmConfigDetailed.configList"/></h3>
	<table id="table_must" class='detail' width="100%">
		<thead>
			<tr>
				<th><jecs:locale key="jpmConfigDetailed.configName" /></th>
				<th><jecs:locale key="jpmConfigDetailed.num" /></th>
				<th><jecs:locale key="jpmConfigDetailed.pic" /></th>
				<th><jecs:locale key="jpmConfigDetailed.price" /></th>
				<th><jecs:locale key="jpmConfigDetailed.remark" /></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty jpmConfigDetailedList }">
			<c:forEach items="${jpmConfigDetailedList}" var="detailed" varStatus="index">
				<c:if test="${detailed.isMustSelected eq '1'}">
				<tr>
					<td>
						<input type="hidden" value="${detailed.isMustSelected }" name="jpmConfigDetailed[*].isMustSelected" id="jpmConfigDetailed[*].isMustSelected">
						<input type="hidden" value="${detailed.specNo}" name="jpmConfigDetailed[*].specNo" id="jpmConfigDetailed[*].specNo">
						<input type="hidden" value="${detailed.configdetailedNo}" name="jpmConfigDetailed[*].configdetailedNo" id="jpmConfigDetailed[*].configdetailedNo">
						<input type="hidden" value="${detailed.subNo}" name="jpmConfigDetailed[*].subNo" id="jpmConfigDetailed[*].subNo">
						<input type="hidden" value="${detailed.isMainMaterial}" name="jpmConfigDetailed[*].isMainMaterial" id="jpmConfigDetailed[*].isMainMaterial">
						<input type="text" value="${detailed.subName }" name="jpmConfigDetailed[*].subName" id="jpmConfigDetailed[*].subName" class="inputOs" readonly="readonly">
					</td>
					<td>
						<input type="text" value="${detailed.subAmount }" name="jpmConfigDetailed[*].subAmount" id="subAmount${index.count }" onmouseout="changePrice('${index.count }','${detailed.unitPrice }');" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'>
					</td>
					<td>
						<c:if test="${not empty detailed.jpmWineTemplatePictureList}">
						<select class="mySelect" onChange="changeMustImg(${index.count })" name="jpmConfigDetailed[*].idf" id="sel${index.count }">
							<c:if test="${empty detailed.idf}">
							<option value="" selected="selected">
								<ng:locale key="list.please.select" />
							</option>				
							</c:if>
							<c:if test="${not empty detailed.idf}">
							<option value="${detailed.idf }" selected="selected">${detailed.picName }</option>
							<option value=""><ng:locale key="list.please.select" /></option>
							</c:if>
							<c:forEach items="${detailed.jpmWineTemplatePictureList}" var="pic">
							<option value="${pic.picturePath},${pic.idf}" title="${pic.picturePath }">
								${pic.pictureName }
							</option>
							</c:forEach>
						</select>
						
						<c:if test="${not empty detailed.idf}">
						<img id="img${index.count }" src="file:///${detailed.picUrl}" width="56" height="56"/>
						</c:if>
						<c:if test="${ empty detailed.idf}">
						<img id="img${index.count }" width="56" height="56"/>
						</c:if>
						</c:if>
					</td>
					<td>
						<input type="text" value="${detailed.price }" name="jpmConfigDetailed[*].price" id="price${index.count }" class="inputOs" readonly="readonly">
					</td>
					<td>
						<input type="text" value="${detailed.remark }" name="jpmConfigDetailed[*].remark" id="jpmConfigDetailed[*].remark">
					</td>
				</tr>
				</c:if>
			</c:forEach>
			</c:if>
		</tbody>
	</table>
		
		
	<h3><jecs:locale key="jpmConfigDetailed.chooseConfigList"/></h3>
	<table id="table_no_must" class='detail' width="100%">
		<thead>
			<tr>
				<th><jecs:locale key="jpmConfigDetailed.configName" /></th>
				<th><jecs:locale key="jpmConfigDetailed.num" /></th>
				<th><jecs:locale key="jpmConfigDetailed.pic" /></th>
				<th><jecs:locale key="jpmConfigDetailed.price" /></th>
				<th><jecs:locale key="jpmConfigDetailed.remark" /></th>
				<th><jecs:locale key="jpmConfigDetailed.choose" /></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty jpmConfigDetailedList }">
			<c:forEach items="${jpmConfigDetailedList}" var="detailedOne" varStatus="index">
				<c:if test="${detailedOne.isMustSelected eq '0'}">
				<tr>
					<td>
						<input type="hidden" value="${detailedOne.isMustSelected }" name="jpmConfigDetailed[*].isMustSelected" id="jpmConfigDetailed[*].isMustSelected">
						<input type="hidden" value="${detailedOne.specNo}" name="jpmConfigDetailed[*].specNo" id="jpmConfigDetailed[*].specNo">
						<input type="hidden" value="${detailedOne.configdetailedNo}" name="jpmConfigDetailed[*].configdetailedNo" id="jpmConfigDetailed[*].configdetailedNo">
						<input type="hidden" value="${detailedOne.subNo}" name="jpmConfigDetailed[*].subNo" id="jpmConfigDetailed[*].subNo">
						<input type="hidden" value="${detailedOne.isMainMaterial}" name="jpmConfigDetailed[*].isMainMaterial" id="jpmConfigDetailed[*].isMainMaterial">
						<input type="text" value="${detailedOne.subName }" name="jpmConfigDetailed[*].subName" id="jpmConfigDetailed[*].subName" class="inputOs" readonly="readonly">
					</td>
					<td>
						<input type="text" value="${detailedOne.subAmount }" name="jpmConfigDetailed[*].subAmount" id="subAmountm${index.count }" onmouseout="changeMustPrice(${index.count },${detailedOne.unitPrice });" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'>
					</td>
					<td>
						<c:if test="${not empty detailedOne.jpmWineTemplatePictureList}">
						<select class="mySelect" onChange="changeImg(${index.count })" name="jpmConfigDetailed[*].idf" id="selm${index.count }">
							<c:if test="${empty detailedOne.idf}">
							<option value="" selected="selected">
								<ng:locale key="list.please.select" />
							</option>				
							</c:if>
							<c:if test="${not empty detailedOne.idf}">
							<option value="${detailedOne.idf}" selected="selected">${detailedOne.picName}</option>
							<option value=""><ng:locale key="list.please.select" /></option>		
							</c:if>
							<c:forEach items="${detailedOne.jpmWineTemplatePictureList}" var="pic">
							<option value="${pic.picturePath},${pic.idf}" title="${pic.picturePath }">
								${pic.pictureName }
							</option>
							</c:forEach>
						</select>
						
							<c:if test="${not empty detailedOne.idf}">
							<img id="imgm${index.count }" src="file:///${detailedOne.picUrl}" width="56" height="56"/>
							</c:if>
							<c:if test="${ empty detailedOne.idf}">
							<img id="imgm${index.count }" width="56" height="56"/>
							</c:if>
						</c:if>
					</td>
					<td>
						<input type="text" value="${detailedOne.price }" name="jpmConfigDetailed[*].price" id="pricem${index.count }" class="inputOs" readonly="readonly">
					</td>
					<td>
						<input type="text" value="${detailedOne.remark }" name="jpmConfigDetailed[*].remark" id="jpmConfigDetailed[*].remark">
					</td>
					<td>
						<input type="checkbox" value="1" ${detailedOne.isCheck eq '1' ? 'checked': ''} name="jpmConfigDetailed[*].isCheck" id="jpmConfigDetailed[*].isCheck" >
					</td>
				</tr>
				</c:if>
			</c:forEach>
			</c:if>
		</tbody>
	</table>

	<table width="100%">
		<colgroup></colgroup>
		<colgroup style="width:130px;"></colgroup>
		<colgroup style="width:130px;"></colgroup>
		<tbody>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" class="inputSubmit" vlaue="<jecs:locale key="operation.button.save"/>"></td>
				<td><a href="javascript:void(0);" style="color:blue;" href="javascript:history.go(-1);"><jecs:locale key="operation.button.cancel"/></a></td>
			</tr>
		</tbody>
	</table>

</form:form>
<script>
	function saveConfig(){
	var trList = $('#table_must tr');
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
	var trListm = $('#table_no_must tr');
	var lenm = trListm.length;
	for(var z=1;z<lenm;z++){
 		var indexm = z-1;
		var inputListm = $(trListm[z]).find(':input');
		for( var x=0;x<inputListm.length;x++){
				var namem = $(inputListm[x]).attr('name');
				namem = namem.replace("*",indexm+len-1);
				$(inputListm[x]).attr('name',namem);
				$(inputListm[x]).attr('id',namem);
		}
	}
	var size = $('#table_must tr').length-1+$('#table_no_must tr').length-1;
	$('#jpmConfigDetailedSize').val(size);
	if(size <= 0)
	{
		alert('<jecs:locale key="jpmConfigDetailed.alert.sizezero"/>');
		return false;
	}
	//$('#jpmConfigDetailedForm').submit();
}
function changeMustImg(index)
{
	var sel = document.getElementById('sel'+index).selectedIndex;
	var v=document.getElementById('sel'+index).options[sel].value;
	var str = v.split(',');
	var src  = str[0];
	document.getElementById('img'+index).src = src;
}

function changeImg(index)
{
	var sel = document.getElementById('selm'+index).selectedIndex;
	var v=document.getElementById('selm'+index).options[sel].value;
	var str = v.split(',');
	var src  = str[0];
	document.getElementById('imgm'+index).src = src;
}
function changePrice(index,unitPrice)
{
	var num = document.getElementById('subAmount'+index).value;
	document.getElementById('price'+index).value = Number(num) * Number(unitPrice);
}

function changeMustPrice(index,unitPrice)
{
	var num = document.getElementById('subAmountm'+index).value;
	document.getElementById('pricem'+index).value = Number(num) * Number(unitPrice);
}

function changeConfigNum()
{
	//获取当前最大配置坛数
	var maxNum = $("#maxNum").val();
	var chooseConfigNum = $("#chooseConfigNum").val();
	
	if(Number(chooseConfigNum) > Number(maxNum))
	{
		alert('<jecs:locale key="jpmConfigDetailed.alert.configMaxNum"/>');
		return;
	}
	//获取当前最大重量
	var maxWeight = $("#maxWeight").val();
	//计算每一坛的重量
	var weight = Number(maxWeight) / Number(maxNum);
	//每坛重量*当前选择坛数
	document.getElementById('chooseWeightCount').value = Number(weight) * Number(chooseConfigNum);
}

function changeModel(url)
{
	var molId = $("#molId").val();
	var productNo = $("#productNo").val();
	var specNo = $("#specNo").val();
	var configNum = $("#configNum").val();
	var weightCount = $("#weightCount").val();
	var saveType = $("#saveType").val();
	var productId = $("#productId").val();
	var moId = $("#moId").val();
	var configNo = $("#configNo").val();
	var productName = $("#productName").val();
	var curWeight = $("#curWeight").val();
	var currentMaxWeight = $("#currentMaxWeight").val();
	var chooseWeightCount = $("#chooseWeightCount").val();
	var maxWeight = Number(currentMaxWeight) + Number(chooseWeightCount);
	var index = document.getElementById("productTemplateNo").selectedIndex;
	var productTemplateNo = document.getElementById("productTemplateNo").options[index].value;
	var chooseConfigNum = $("#chooseConfigNum").val();
	var maxNum = $("#maxNum").val();
	//当前配置数量不可以大于剩余重量+当前数量
	if(curWeight > maxWeight)
	{
		alert('<jecs:locale key="jpmConfigDetailed.alert.configMaxWeight"/>');
		return;
	}
	if(0 == curWeight || '' == curWeight)
	{
		alert('<jecs:locale key="jpmConfigDetailed.alert.configWeight"/>');
		return;
	}
	if(null == productTemplateNo || '' == productTemplateNo || 'null' == productTemplateNo)
	{
		alert('<jecs:locale key="jpmConfigDetailed.alert.configModelNull"/>');
		return;
	}
	if(Number(chooseConfigNum) > Number(maxNum))
	{
		alert('<jecs:locale key="jpmConfigDetailed.alert.maxNum"/>');
		return;
	}
	
	var chooseWeightCount = $("#chooseWeightCount").val();
	
	window.location=url+"&molId="+molId+"&curWeight="+curWeight+"&productName="+productName+"&configNo="+configNo+"&moId="+moId+"&productId="+productId+"&productNo="+productNo+"&specNo="+specNo+"&productTemplateNo="+productTemplateNo+"&chooseConfigNum="+chooseConfigNum+"&chooseWeightCount="+chooseWeightCount+"&configNum="+configNum+"&weightCount="+weightCount+"&saveType="+saveType;
}
</script>
<script>
    Form.focusFirstElement($('jpmConfigDetailedForm'));
</script>

<v:javascript formName="jpmConfigDetailed" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script src="<c:url value="/scripts/validator.jsp"/>"></script>
