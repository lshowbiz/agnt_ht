<!DOCTYPE html>

<%@ include file="/common/taglibs.jsp"%>
<html>
    <head>
        <%@ include file="/common/meta.jsp" %>
        <title>JOYLIFE INTERNATIONAL E-COMMERCE SYSTEMS</title>
		<meta charset="UTF-8" />
		<script>
		var errorDataSubmited="<jecs:locale key="error.data.submited"/>";
		</script>
        <link rel="stylesheet" media="all" href="<c:url value='/styles/default.css'/>" />
        <script src="<c:url value='/scripts/prototype.js'/>"></script>
        <script src="<c:url value='/scripts/scriptaculous.js'/>"></script>
        <script src="<c:url value='/scripts/effects.js'/>"></script>
        	
		<script src="<c:url value='/scripts/slider.js'/>"></script>  
		<script src="<c:url value='/scripts/controls.js'/>"></script> 
		<script src="<c:url value='/scripts/dragdrop.js'/>"></script> 
		<script src="<c:url value='/scripts/builder.js'/>"></script> 
        
        <script src="<c:url value='/scripts/global.js'/>"></script>
        <script src="<c:url value='/scripts/jquerymobile/jquery-1.7.1.min.js'/>"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/scripts/My97DatePicker/WdatePicker.js"></script>        
		<script>
			jQuery.noConflict();
		</script>
		<script>
		var confirmDeleteMsg="<jecs:locale key="confirm.delete"/>";		 
		function selectWarehouse(str){
			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectWarehouse&elementId="+str,"","height=400, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
		} 
		function selectAllWarehouse(str){
			window.open("<c:url value='/pdWarehouses.html'/>?strAction=selectAllWarehouse&elementId="+str,"","height=400, width=1000, top=50, left=50, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no");
		}
		



		</script>
				
	 <style>  
        #waitViewFade{    
            display: none;    
            position: absolute;    
            top: 0;    
            left: 0;    
            width: 100%;    
            height: 100%;    
            background-color: black;    
            z-index:9998;    
            -moz-opacity: 0.5;    
            opacity:.50;    
            filter: alpha(opacity=50);  
        }    
        #waitView{    
            color:#ff0000;  
            display: none;    
            position: absolute;    
            top: 25%;    
            left: 25%;    
            width: 50%;    
            height: 20%;   
            line-height:50%;  
            padding: 16px;    
            border: 12px solid orange;    
            background-color: white;    
            z-index:9999;    
            overflow: auto;   
            text-align:center;   
        }         
      </style>  
      			 
<!-- ͨ�õĽ������ʾ -->
<style type="text/css">
iframe#fram_bk{border:0;position:absolute;display:none;z-index:0;width:180px;height:50px;background:none;}
</style>


<div id="waitView">  
        <br><br><br><br>   
        <span style="color:#000000"><jecs:locale key="timeReamin.tips1"/>&nbsp;&nbsp;&nbsp;&nbsp;<b><span id="totalSecond"  style="color:red;">${sessionScope[ elf:append(sessionScope['operationCode'],'-time')]}</span></b>&nbsp;&nbsp;&nbsp;&nbsp;<jecs:locale key="timeReamin.tips2"/></span>  
      <br><br><br>    
    </div>  
    <div id="waitViewFade"></div>


<div id="sending" style="display:none;position:absolute;top:60px; left:30%; z-index:10; visibility:hidden;border:3px solid #f0f0f0;">
  <div class="searchBar" style="width:180px;text-align:center;padding:30px 10px 10px 10px;height:50px;color:red;" id="sending_sub">
    <img style="position:absolute;z-index:1;top:6;" src="<%=request.getContextPath()%>/images/ajax-loader.gif" />
  </div>
</div>
<script>
function waiting(){
	disableButton();
	var o = document.createElement('iframe');
	o.id = 'fram_bk';
	document.body.appendChild(o);
	with ($('fram_bk').style){
		display='block';
		top = "60px";
		left = "30%";
	}
	var el=document.getElementById("sending_sub");
	if (el.hasChildNodes()){
	  el.removeChild(el.lastChild);
	}
	
	el.appendChild( document.createTextNode('<jecs:locale key="button.loading"/>'));
	document.getElementById("sending").style.visibility="visible";
	document.getElementById("sending").style.display="block";
}
function waitingEnd(){
	ableButton();
	document.getElementById("sending").style.visibility="hidden";
	document.getElementById("sending").style.display="none";
	if($('fram_bk') != undefined){
		$('fram_bk').style.display="none";
	}
}


function disableButton(){
			var buttonList = document.getElementsByClassName('btn btn_sele');
			
			
			var nodes = $A(buttonList);
		
			nodes.each(function(node){
			 			node.disabled = true;
			 			
			 	});
			 	
			
	}

function ableButton(){
			var buttonList = document.getElementsByClassName('btn btn_sele');
			
			
			var nodes = $A(buttonList);
		
			nodes.each(function(node){
			 			node.disabled = false;
			 			
			 	});
			 	
			
	}

function showTR(tr){
	//Effect.toggle(tr, 'appear');
	if($(tr).style.display==''){
		$(tr).style.display='none';
	}else{
	
		$(tr).style.display='';
	}
}

	<c:if test="${ not empty sessionScope[ elf:append(sessionScope['operationCode'],'-time')] && sessionScope[ elf:append(sessionScope['operationCode'],'-time')]>0 }">
	
		
            var second=${sessionScope[ elf:append(sessionScope['operationCode'],'-time')]};  
            var timerId; 
            function checkByWebServices(){  
				//document.getElementById("waitView").style.display='block';
				//document.getElementById("waitViewFade").style.display='block'; 
				$('waitView').style.display='block';
				$('waitViewFade').style.display='block';
                //second =  document.getElementById("totalSecond").innerText; 
				second = $('totalSecond').innerHTML; 
                timerId = window.setInterval("redirect()", 1000);  
            }         
            function redirect(){ 
            
                if(second < 0){  
                    window.clearInterval(timerId); //
					$('waitView').style.display='none';
					$('waitViewFade').style.display='none';
                }  
                else{  
                	//document.getElementById("totalSecond").innerText=second--; 
                	 $('totalSecond').innerHTML=second--; 
                }  
            }  


		checkByWebServices();
	</c:if>

</script>

<%
	String strActionCode=(String)request.getSession().getAttribute("operationCode");
	request.getSession().removeAttribute(strActionCode+"-time");
 %>
<c:remove var="operationCode" scope="session"/>

        <decorator:head/>
    </head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>

	<jsp:include page="/common/header.jsp"/>
		<%@ include file="/common/messages.jsp" %>
		<decorator:body/>
</body>


</html>
