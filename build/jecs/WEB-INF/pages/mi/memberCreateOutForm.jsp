<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><jecs:locale key="jmiMemberDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiMemberDetail.heading"/></content>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JOYMAIN INTERNATIONAL</title>
<style type="text/css">
<!--
	body {margin:0; font-family:Verdana, Geneva, sans-serif; font-size:13px;}
	img {border:0; vertical-align:middle;}
	button { cursor:pointer; vertical-align:middle; height:24px; color:#090;}
	button img { border-right:1px solid #eee; padding-right:4px; margin-right:4px;}
	.regButton { background:url(images/joinUS/regButtonBg.jpg) center center; border:1px double #dddddd; padding:4px 12px 2px 12px;}
	.regButtonOver { background:url(images/joinUS/regButtonBgOver.jpg) center center;border:none; border:1px double #dddddd; padding:5px 12px 2px 12px;}
-->
</style>

<style type="text/css">
<!--
	
	button { cursor:pointer; vertical-align:middle; color:#090;}
	.regButton { background:url(images/joinUS/regButtonBg.jpg) center center; border:1px double #dddddd; padding:4px 12px 2px 12px;}
	.regButtonOver { background:url(images/joinUS/regButtonBgOver.jpg) center center;border:none; border:1px double #dddddd; padding:4px 12px 2px 12px;}
	.register {margin:0;padding:4px; border-collapse:collapse;}
	.register .required { font-weight:bold; color:#060; font-size:13px;}
	.register .title { background:#f0f0f0; border-bottom:2px solid #9c0;padding:6px 2px 2px 2px; font-size:15px; color:#060; font-weight:bold;}
	.register th {font-weight:normal; color:#999; border-bottom:1px solid #f0f0f0; padding-left:32px; text-align:right;}
	.register td { border-bottom:1px solid #f0f0f0;padding:2px; text-align:left; width:50%}
	em {font-style:normal; color:#C53032; margin-left:10px}
-->
</style>
</head>





<body>

<spring:bind path="jmiMember.*">
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


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCountryManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>


<script type="text/javascript" src="<c:url value='/scripts/jquery/jquery-142min.js'/>"></script>

<script type="text/javascript">
var jq = jQuery.noConflict();

function blurInput(id){
          jq('#'+id).triggerHandler("blur");
	
}


function loadnext(divout,divin){
	
	if(divout=='step2'){
		jq("#" + divout)[0].style.display = '';
		jq("#" + divin)[0].style.display = 'none';
		jq("#steppic")[0].src='images/joinUS/regCententTitleIco2.jpg';
		jq("#stepTitle")[0].innerHTML='<jecs:locale key="register.select.b"/>';
		jq("#stepSort")[0].innerHTML='<jecs:locale key="register.select.a"/> &gt;&gt;<span style="color:#fff"> <jecs:locale key="register.select.b"/> </span>&gt;&gt; <jecs:locale key="register.select.d"/>';
	}else{
		jq("#" + divout)[0].style.display = '';
		jq("#" + divin)[0].style.display = 'none';
		jq("#steppic")[0].src='images/joinUS/regCententTitleIco.jpg';
		jq("#stepTitle")[0].innerHTML='<jecs:locale key="register.us.legend.member.register" />';
		jq("#stepSort")[0].innerHTML=' <span style="color:#fff"><jecs:locale key="register.select.a"/></span> &gt;&gt; <jecs:locale key="register.select.b"/> &gt;&gt;<jecs:locale key="register.select.d"/>';
	}
	
	
}

		  			   function callBackCheckRecommendNo(valid){
		  			   		if(valid==''){
					  			   var errors = jq('#errors_recommendNo')[0];
								   errors.innerHTML = "";
		  			   		}else{
					  			   var errors = jq('#errors_recommendNo')[0];
			                       errors.innerHTML = valid;
		  			   		}
		  				}
		  				
		  				function callBackCheckLinkNo(valid){
		  			   		if(valid==''){
					  			   var errors = jq('#errors_linkNo')[0];
								   errors.innerHTML = "";
		  			   		}else{
					  			   var errors = jq('#errors_linkNo')[0];
			                       errors.innerHTML = valid;
		  			   		}
		  				}
                   		function callBackCheckPapernumber(valid){
                   			if(valid==''){
					  			   var errors = jq('#errors_papernumber')[0];
                      				errors.innerHTML = '';
                   			}else{
					  			   var errors = jq('#errors_papernumber')[0];
                       				errors.innerHTML = valid;
                   			}
                   		}
jq(function(){
 DWREngine.setAsync(false);

       jq('form :input').blur(function(){
            var parent = jq(this).parent();
            parent.find(".formtips").remove();
            
            
            
            	if( jq(this).is('#companyCode') ){
	                   if( this.value==""){
						  	var errors = jq("#errors_" + jq(this)[0].id)[0];
	                       var errorMsg = '<jecs:locale key="errors.required" args="bdReconsumMoneyReport.company" argTransFlag="true"/>';
	                       	errors.innerHTML = errorMsg;
	                   }else{
						  	var errors = jq("#errors_" + jq(this)[0].id)[0];
	                      	errors.innerHTML = '';
	                   }
	            }
            
            	if( jq(this).is('#recommendNo') ){
	                   if( this.value==""){
						  	var errors = jq("#errors_" + jq(this)[0].id)[0];
	                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.recommendNo" argTransFlag="true"/>';
	                       	errors.innerHTML = errorMsg;
	                   }else{
                       		jmiMemberManager.getCheckRecommendNo(null,this.value,'',jq('#characterCode')[0].value,callBackCheckRecommendNo);
	                   }
	            }
				
				
				 if( jq(this).is('#linkNo') ){
            		var recommendNoVal=jq("#recommendNo").val();
            		if(this.value==''){
					   var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.linkNo" argTransFlag="true"/>';
                        errors.innerHTML = errorMsg;
            		}else{
                       jmiMemberManager.getCheckLinkNo(null,recommendNoVal,this.value,'',jq('#characterCode')[0].value,callBackCheckLinkNo);
            		}
           	 }
            
				          
            if( jq(this).is('#firstName') ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                        var errorMsg = '<jecs:locale key="errors.required" args="miMember.firstName" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            }  
	          
            if( jq(this).is('#lastName') ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                        var errorMsg = '<jecs:locale key="errors.required" args="miMember.lastName" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            } 
			if( jq(this).is('#petName') ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                        var errorMsg = '<jecs:locale key="errors.required" args="miMember.petName" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            }
			if( jq(this).is('#papernumber') ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.papernumber" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
                 		  var papertypeVal=jq("#papertype").val();
                   		if(jq('#companyCode')[0].value=="ID"){
                   			if(papertypeVal=='1'){
	                   			var errors = jq("#errors_" + jq(this)[0].id)[0];
		                   		var patrn=/^([0-9]{16})$/;
		                   		if(!patrn.exec(this.value)){
			                      	var errorMsg = '<jecs:locale key="errors.invalid" args="miMember.papernumber" argTransFlag="true"/>';
			                       	errors.innerHTML = errorMsg;
		                   		}else{
		                   			jmiMemberManager.getCheckIdNo(null,this.value,'',papertypeVal,jq('#companyCode')[0].value,jq('#characterCode')[0].value,callBackCheckPapernumber);
		                   		}
                   			}
                   		}else{
                   			jmiMemberManager.getCheckIdNo(null,this.value,'',papertypeVal,jq('#companyCode')[0].value,jq('#characterCode')[0].value,callBackCheckPapernumber);
                   		}
                      	
                   		
                   }
            }
			  
            if( jq(this).is('#birthday') && jq('#companyCode')[0].value=="ID" ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.birthday" argTransFlag="true"/> <jecs:locale key="bdPeriod.endTime.sample" />';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	if(checkDate(this.value)){
						  	var errors = jq("#errors_" + jq(this)[0].id)[0];
	                      	var errorMsg = '<jecs:locale key="errors.date" args="miMember.birthday" argTransFlag="true"/>';
	                       	errors.innerHTML = errorMsg;
	                   }else{
	                   		
                   			var  age=jsGetAge(this.value);
	                   			if(age<17){
								  	var errors = jq("#errors_" + jq(this)[0].id)[0];
			                      	var errorMsg = '<jecs:locale key="member.age.tw" args="17" argTransFlag="true"/>';
			                       	errors.innerHTML = errorMsg;
	                   			}else{
								  	var errors = jq("#errors_" + jq(this)[0].id)[0];
			                      	errors.innerHTML = '';
		                   			
	                   			}
	                   		
                   		}
                   }
            }
            
            
            if( jq(this).is('#province')){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.province" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            }
			
            if( jq(this).is('#city')){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.idAddr2" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            }
			
            if( jq(this).is('#address')){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.idAddr" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            }
			
            if( jq(this).is('#mobiletele')){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.mobiletele" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
                   		if(jq('#companyCode')[0].value=="ID"){
						  	var errors = jq("#errors_" + jq(this)[0].id)[0];
	                   		var patrn=/^[0]([0-9]{10,11})$/;
	                   		if(!patrn.exec(this.value)){
		                      	var errorMsg = '<jmos:locale key="errors.phone" args="miMember.mobiletele" argTransFlag="true"/>';
		                       	errors.innerHTML = errorMsg;
	                   		}else{
	                      		errors.innerHTML = '';
	                   		}
                   		}else{
						  	var errors = jq("#errors_" + jq(this)[0].id)[0];
	                      	errors.innerHTML = '';
                   		}
                   
                   
                   }
            }
			
			 if( jq(this).is('#password1') ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.pwd1" argTransFlag="true"/> <jecs:locale key="sys.pwd.tips" />';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            } 
            if(jq(this).is('#password1Confirm') ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="label.affirmNewPassword" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else if(this.value!=jq('#password1').val()){  
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                   	   var errorMsg = '<jecs:locale key="miMember.pwd1.isNotEqual"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            }
			
			if( jq(this).is('#password2') ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="miMember.pwd2" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            } 
            if(jq(this).is('#password2Confirm') ){
                   if( this.value==""){
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                       var errorMsg = '<jecs:locale key="errors.required" args="label.affirmAdvancedPassword" argTransFlag="true"/>';
                       	errors.innerHTML = errorMsg;
                   }else if(this.value!=jq('#password1').val()){  
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                   	   var errorMsg = '<jecs:locale key="miMember.pwd2.isNotEqual"/>';
                       	errors.innerHTML = errorMsg;
                   }else{
					  	var errors = jq("#errors_" + jq(this)[0].id)[0];
                      	errors.innerHTML = '';
                   }
            }
			
 			var errors = jq("#errors_" + jq(this)[0].id)[0];
			if(errors){
				if(""!=errors.innerHTML){
					
					if(jq(this)[0].id=='companyCode'||jq(this)[0].id=='recommendNo'){
					  jq(this)[0].className = "inputBox onev";
					  errors.className = "error1";
					}else{
					  jq(this)[0].className = "inputBox twov";
					  errors.className = "error1";
					}
					
				}else{
				  jq(this)[0].className = "inputBox";
				  errors.className="correct"
				}
			}
            
       }).focus(function(){
          jq(this).triggerHandler("blur");
		  var label = jq("#label_" + jq(this)[0].id)[0];
		  if(label) {
            label.style.display = "none";
          }
       });
       //end blur
       
       //提交，最终验证。
        jq('#next1').click(function(){
				jq(".onev").trigger('blur');
	            var numError = jq('.onev').length;
	            if(numError){
	               return false;
	            }
               loadnext('step2','step1');
        });
                jq('#next2').click(function(){
        		
				jq("form .twov").trigger('blur');
	            var numError = jq('form .twov').length;
	            if(numError){
	               return false;
	            }else{
	            	jq('#jmiMemberForm')[0].submit();
	            }
        });
});           
            
            
</script>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    	<div style="float:left; background:url(images/joinUS/logo.jpg);width:354px;height:76px;" title="Joymain international" /></div>
    	<div style="float:right;padding:27px 27px 0 0;"><jecs:label key="register.language.select"/><img src="images/joinUS/regTopIco.jpg" width="33" height="26" align="absmiddle" alt="" />
              <select name="characterCode" id="characterCode" onchange="location.href='?lang='+this.value">
        <c:forEach items="${alCharacterCodings}" var="alCharacterCodingVar">
			<option value="${alCharacterCodingVar.characterCode }"<c:if test="${alCharacterCodingVar.characterCode==sessionScope.sessionLogin.defCharacterCoding }"> selected</c:if>>${alCharacterCodingVar.characterName }</option>
        </c:forEach>
        </select>
        </div>
    </td>
  </tr>
  <tr>
    <td style="background:url(images/joinUS/regMenuBg.jpg); height:36px; padding:0 12px 0 12px" nowrap>
    	<img src="images/joinUS/regMenuIcon.jpg" border="0" align="absmiddle" style="float:left;" />
        <p style="color:#360;margin:10px 0 0 0;" id="stepSort">
        <span style="color:#fff"><jecs:locale key="register.select.a"/></span> &gt;&gt; <jecs:locale key="register.select.b"/> &gt;&gt; <jecs:locale key="register.select.c"/>
        </p>
    </td>
  </tr>
  <tr>
    <td>
		
		
<form:form commandName="jmiMember" method="post" action="memberRegister.html"  id="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}">


<input type="hidden" name="strAction" value="${param.strAction }"/>


<form:hidden path="userCode"/>

		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <td valign="top" style="padding:4px 4px 4px 9px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/joinUS/regCententTitleBg.jpg);">
          <tr>
            <td width="56" height="56" align="right"><img id="steppic" name="steppic" src="images/joinUS/regCententTitleIco.jpg" width="56" height="56" alt="" /></td>
            <td id="stepTitle" style="font-size:16px; font-family:Verdana, Geneva, sans-serif; font-weight:bold; padding:0 0 0 12px; color:#333"><jecs:locale key="register.us.legend.member.register"/></td>
            <td align="right" width="6"><img src="images/joinUS/regCententTitleRight.jpg" width="6" height="56" alt="" /></td>
          </tr>
        </table>
        
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" id="step1">
          <tr>
            <td valign="middle" align="center">
			
			
			
			
            	<!--centent start-->
				
				
				<!--setep 1 start-->
                <table border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td align="right" nowrap><jecs:label key="bdReconsumMoneyReport.company"/></td>
                  <td align="left" nowrap>
                      <jecs:list showBlankLine="true" styleClass="inputBox onev" name="companyCode" id="companyCode" listCode="register.company" defaultValue="ID" value="" onchange="getPapertype();getProvince();selectCompany(this);"/>	
						<script type="text/javascript">
							function selectCompany(selectEl){
								if('TW'==selectEl.value){
									window.open("http://ec.joylifeglobal.com/tw/memberCreateOut.html");
								}
								if('PH'==selectEl.value){
									window.open("http://ec.joylifeglobal.com/ph/memberCreateOut.html");
								}
								if('US'==selectEl.value){
									window.open("http://ec.joylifeglobal.com/us/memberCreateOut.html");
								}
								if('HK'!=selectEl.value && 'ID'!=selectEl.value ){
									selectEl.value="";
								}
								
								if('ID'==selectEl.value){
									viewStr(true,'inputBox twov','');
								}else{
									viewStr(false,'inputBox','none');
								}
								
								
								
							}
							
function viewStr(flag,className,dis){
		jq('#birthday')[0].className = className;
		
		jq('#birthdayTr')[0].style.display = dis;
		
		if(flag==false){
			jq('#birthday')[0].value ='';
		}
		
}
							
							
						</script>
					  <em id="errors_companyCode"></em>
                  </td>
                </tr>
                <tr>
                  <td align="right" nowrap><jecs:label key="miMember.recommendNo" /></td>
                  <td align="left" nowrap> <form:input path="jmiRecommendRef.recommendJmiMember.userCode" id="recommendNo" cssClass="inputBox onev"/><em id="errors_recommendNo"></em>
                  
<script type="text/javascript">
	document.getElementById('recommendNo').value='CN12905645';
</script>
                  </td>
                </tr>
                <tr>
                  <td align="right" nowrap><jecs:label key="sysOperationLog.moduleName" /></td>
                  <td align="left" nowrap>
                  	<button class="regButton" onMouseOver="this.className='regButtonOver'" onMouseOut="this.className='regButton'" 
                    	type="button" onclick="window.location.href='login.html'" value="" name=""><img src="images/joinUS/icon/Reply.gif" border="0" align="absmiddle" /><jecs:locale key="operation.button.return"/></button>
                  	<button class="regButton" onMouseOver="this.className='regButtonOver'" onMouseOut="this.className='regButton'" 
                    	type="button"  id="next1" name="next2" value="" ><img src="images/joinUS/icon/arrowRight.gif" border="0" align="absmiddle" /><b><jecs:locale key="button.next"/></b></button>
                  </td>
                </tr>
                </table>
					
            	<!--centent end-->
					
				
            </td>
          </tr>
        </table>
	





        <%--<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/joinUS/regCententTitleBg.jpg);">
          <tr>
            <td width="56" height="56" align="right"><img src="images/joinUS/regCententTitleIco2.jpg" width="56" height="56" alt="" /></td>
            <td style="font-size:16px; font-family:Verdana, Geneva, sans-serif; font-weight:bold; padding:0 0 0 12px; color:#333">Billing & Distributor Information</td>
            <td align="right" width="6"><img src="images/joinUS/regCententTitleRight.jpg" width="6" height="56" alt="" /></td>
          </tr>
        </table>--%>

<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" style="display: none"  id="step2">
          <tr>
            <td valign="middle" align="center">
            	<!--centent start-->
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                <tr>
                  <td nowrap>
                      <table width="100%" border="0" align="center" class="register">
                        <tr>
                            <td colspan="2" class="title"><jecs:locale key="register.submit.info" /></td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:locale  key="miMember.linkNo" /></th>
                            <td nowrap="nowrap">
                                
                                <form:input path="jmiLinkRef.linkJmiMember.userCode" id="linkNo"  cssClass="inputBox twov" cssStyle="vertical-align:middle"/>
                                <%--<button type="button" class="regButton" onMouseOver="this.className='regButtonOver'" onMouseOut="this.className='regButton'" 
                                	onClick="miSelectLink()"><img src="images/joinUS/icon/Alert.gif" border="0" align="absmiddle" />Auto-placement</button>--%>  
                             <em id="errors_linkNo"></em> </td>
                        </tr>
                        <!-- 个人 -->
                        
                        <tr>
                            <td colspan="2" class="title"><jecs:locale key="register.us.legend.member" /></td>
                        </tr>
                        
                        <!-- 公司 -->
                        
                        <!-- 个人 -->
                        
                        <tr>
                            <th class="required"><jecs:locale key="miMember.firstName" /></th>
                            <td align="right">
        						<form:input path="firstName" id="firstName" cssClass="inputBox twov"/><em id="errors_firstName"></em></td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:locale key="miMember.lastName" /></th>
                            <td align="right">
       							 <form:input path="lastName" id="lastName" cssClass="inputBox twov"/><em id="errors_lastName"></em></td>
                        </tr>
                        <tr>
                            <th ><jecs:locale  key="miMember.sex"/> </th>
                            <td>
								<jecs:list name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue=""/>							</td>
                        </tr>
						
                        <tr>
                            <th class="required"><jecs:locale  key="miMember.petName"/> </th>
                            <td>
								<form:input path="petName" id="petName" cssClass="inputBox twov"/><em id="errors_petName"></em></td>
                        </tr>
                        
                        <tr id="birthdayTr" >
                            <th class="required"><jecs:locale  key="miMember.birthday"/> </th>
                            <td>
								<form:input path="birthday" id="birthday" cssClass="inputBox twov"/>
					<img src="./images/calendar/calendar7.gif" id="img_birthday" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>" /> 
					<script type="text/javascript"> 
						Calendar.setup({
						inputField     :    "birthday", 
						ifFormat       :    "%Y-%m-%d",  
						button         :    "img_birthday", 
						singleClick    :    true,
						onUpdate       :	aa
						}); 
						
						function aa(){
								blurInput('birthday');
						}
						
					</script>
								<em id="errors_birthday"></em>
								
								</td>
                        </tr>
                        
                        <tr>
                            <th><jecs:label  key="miMember.papertype"/></th>
                            <td>
								<select name="papertype" id="papertype" >
									
								</select>
							</td>
                        </tr>
                        
                        
                        <tr>
                            <th class="required"> <jecs:locale key="miMember.papernumber" /></th>
                            <td>
								<form:input path="papernumber" id="papernumber" cssClass="inputBox twov" /><em id="errors_papernumber"></em></td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:locale key="miMember.province"  /> </th>
                            <td>
								<select name="province" id="province" onChange="getIdCity();" class="inputBox twov">
									<option value=""><jecs:locale key="list.please.select"/></option>
								</select><em id="errors_province"></em></td>
                        </tr>
                        <tr>
                            <th class="required"> <jecs:locale key="miMember.idAddr2"  /></th>
                            <td>
							 <select name="city" id="city" onChange="getIdDistrict();" class="inputBox twov">
								<option value=""><jecs:locale key="list.please.select"/></option>
							</select><em id="errors_city"></em></td>
                        </tr>
                        <tr>
                            <th><jecs:locale key="miMember.district" /> </th>
                            <td>
						<select name="district" id="district" >
							<option value=""><jecs:locale key="list.please.select" /></option>
						</select>							</td>
                        </tr>
                        <tr>
                            <th class="required">  <jecs:locale key="miMember.idAddr"  /></th>
                            <td>
								<form:input path="address" id="address"  cssClass="inputBox twov" /><em id="errors_address"></em></td>
                        </tr>
                        <tr>
                            <th>  <jecs:locale key="miMember.postalcode"  /></th>
                            <td>
								<form:input path="postalcode" id="postalcode"  cssClass="inputBox" />							</td>
                        </tr>
                        <tr>
                            <th>  <jecs:locale key="miMember.phone"  /></th>
                            <td>
								<form:input path="phone" id="phone"  cssClass="inputBox" />							</td>
                        </tr>
                        <tr>
                            <th class="required"> <jecs:locale key="miMember.mobiletele"  /> </th>
                            <td>
								<form:input path="mobiletele" id="mobiletele"  cssClass="inputBox twov" /><em id="errors_mobiletele"></em></td>
                        </tr>
                        <tr>
                            <th><jecs:locale key="miMember.faxtele"  /> </th>
                            <td>
								<form:input path="faxtele" id="faxtele"  cssClass="inputBox" />							</td>
                        </tr>
                        <tr>
                            <th><jecs:locale key="miMember.email"  /> </th>
                            <td>
								<form:input path="email" id="email"  cssClass="inputBox" />							</td>
                        </tr>
                        
                        <tr>
                            <td colspan="2" class="title"><jecs:locale key="member.security.info"  /> </td>
                        </tr>
                        
                        <tr>
                            <th class="required"><jecs:locale key="miMember.pwd1" /></th>
                            <td align="right"><form:password path="sysUser.password" cssClass="inputBox twov" id="password1"/><em id="errors_password1"></em></td>
                        </tr>
                        <tr>
                            <th class="required"><span style="color:#9C0"><jecs:locale key="label.affirmNewPassword" /></span></th>
                            <td align="right"><input id="password1Confirm" name="password1Confirm" type="password"  class="inputBox twov"><em id="errors_password1Confirm"></em></td>
                        </tr>
                        
                        <tr>
                            <th class="required"> <jecs:locale key="miMember.pwd2"/></th>
                            <td align="right"><form:password path="sysUser.password2" cssClass="inputBox twov" id="password2"/><em id="errors_password2"></em></td>
                        </tr>
                        <tr>
                            <th class="required"><span style="color:#9C0"><jecs:locale key="label.affirmAdvancedPassword"/></span></th>
                            <td align="right"><input id="password2Confirm" name="password2Confirm" type="password" class="inputBox twov"><em id="errors_password2Confirm"></em></td>
                        </tr>
						
                        <%--<tr>
                            <th class="required">默认语言</th>
                            <td align="right">
                            <select id="sysUser.defCharacterCoding" name="sysUser.defCharacterCoding" class="selectBox"><option value="ar_SA">Arabic</option><option value="bg_BG">Български</option><option value="de_DE">Deutsch</option><option value="en_AU">English (Australia)</option><option value="en_GB">English-GB</option><option value="en_US" selected="selected">English-US</option><option value="es_ES">Español</option><option value="es_PE">Español (Perú)</option><option value="es_US">Español (United States)</option><option value="fr_FR">Français (France)</option><option value="hi_IN">हिन्दी(India)</option><option value="ja_JP">日本語</option><option value="ko_KR">한국어(Korea)</option><option value="lt_LT">Lietuvių(Lithuania)</option><option value="ms_MY">Bahasa Melayu(Malay)</option><option value="nl_NL">Nederlands</option><option value="pl_PL">Polski(Poland)</option><option value="pt_BR">pt_BR</option><option value="pt_PT">Português(Portugal)</option><option value="ru_RU">Русский язык(Russia)</option><option value="th_TH">ภาษาไทย(Thailand)</option><option value="vi_VN">Việt(Vietnam)</option><option value="zh_CN">简体中文</option><option value="zh_TW">繁體中文</option></select>                            </td>
                        </tr>--%>
                        <tr>
                            <td colspan="2"><hr size="1"/></td>
                        </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td align="right" nowrap><button class="regButton" onMouseOver="this.className='regButtonOver'" onMouseOut="this.className='regButton'" 
                    	type="button" value="" onclick="window.location.href='login.html'" name=""><img src="images/joinUS/icon/Delete.gif" border="0" align="absmiddle" /><jecs:locale key="operation.button.cancel"/></button>
                    <button class="regButton" onMouseOver="this.className='regButtonOver'" onMouseOut="this.className='regButton'" 
                    	type="button" value="" name="" onClick="loadnext('step1','step2');"><img src="images/joinUS/icon/arrowLeft.gif" border="0" align="absmiddle" /><jecs:locale key="register.perous"/></button>
                    <button class="regButton" onMouseOver="this.className='regButtonOver'" onMouseOut="this.className='regButton'" 
                    	type="button" value="" id="next2" name="next2" ><img src="images/joinUS/icon/arrowRight.gif" border="0" align="absmiddle" /><b><jecs:locale key="button.next"/></b></button>
                  </td>
                 </tr>
                </table>
            	<!--centent end-->
            </td>
          </tr>
        </table>






</form:form>




        </td>
        <%--<td valign="top" width="30%" style="padding:10px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="50" height="42" align="left"><img src="images/joinUS/regRightTitleIco.jpg" width="50" height="42" alt="" /></td>
            <td width="100%" background="images/joinUS/regRightTitleBg.jpg" valign="middle" style="color:#990; font-size:16px; font-weight:bold;" nowrap="nowrap">填写说明</td>
            <td width="6" height="42" align="right"><img src="images/joinUS/regRightTitleRight.jpg" width="6" height="42" alt="" /></td>
          </tr>
          <tr>
            <td colspan="3" style="border-left:1px solid #99cc00;border-right:1px solid #99cc00;">
            <br />
            <ul style=" font-size:13px; color:#333; padding:6px; margin:24px 0 0 24px; list-style:decimal; line-height:24px;">
            <jecs:locale key="register.select.into.1"/>
            </ul>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
            </td>
            </tr>
          <tr>
            <td colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td style="background:url(images/joinUS/regRightBottomLeft.jpg); width:5px;height:5px;font-size:1px;">&nbsp;</td>
                <td style=" background:url(images/joinUS/regRightBottomBg.jpg);font-size:1px;">&nbsp;</td>
                <td style="background:url(images/joinUS/regRightBottomRight.jpg); width:5px;height:5px;font-size:1px;">&nbsp;</td>
              </tr>
            </table></td>
            </tr>
        </table>
        </td>--%>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/joinUS/regFooterBg.jpg);">
      <tr>
        <td align="left"><img src="images/joinUS/regFooterLeft.jpg" width="7" height="32" alt="" /></td>
        <td width="100%" align="center">Copyright &copy; 2009 Joymain International. All rights reserved.</td>
        <td align="right"><img src="images/joinUS/regFooterRight.jpg" width="8" height="32" alt="" /></td>
      </tr>
    </table>
    </td>
  </tr>
</table>




<script  type="text/javascript">
		
 DWREngine.setAsync(false);	
 
 function getPapertype(){
	  		var papertypeElemment=jq('#papertype')[0];
        		papertypeElemment.options.length=0;
 		if(jq('#companyCode')[0].value=='HK'){
	  		
          	var o=new Option("<jecs:locale key="miMember.idNo"/>","5");
			papertypeElemment.options.add(o);
 		}else{
 		
          	var o=new Option("<jecs:locale key="miMember.idNo"/>","1");
			papertypeElemment.options.add(o);
 		}
 }
 
function getProvince(){ 
	alCountryManager.getJalStateProvincesByCompanyCode(jq('#companyCode')[0].value,getProvincCallBack);
}

function getProvincCallBack(valid){
	  		var provinceElemment=jq('#province')[0];
	  
        		provinceElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		provinceElemment.options.add(o);  
           		
           		
			for(i=0;i<valid.length;i++){
		       for (var i=0;i<valid.length;i++) {
			        var provinceId= valid[i].stateProvinceId;	
			        var provinceName= valid[i].stateProvinceName;
			        var o=new Option(provinceName,provinceId);
			        
			        provinceElemment.options.add(o);
			        
			           
			        if(provinceId=='${jmiMember.province}'){
			        	o.selected=true;
			        }
			        
			      }
			}
}
 
 
 
 
 
			function getIdCity(){
			
		   var province=document.getElementById('province').value;
		   	alCityManager.getAlCityByProvinceId(province,callIdCity);
		   
		   }
		   function callIdCity(valid){
			   var cityElemment=document.getElementById('city');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  

           		
			   var districtElemment=document.getElementById('district');
        		districtElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		districtElemment.options.add(o);  

		       for (var i=0;i<valid.length;i++) {
			        var cityId= valid[i].cityId;	   
			        var cityName=valid[i].cityName;   
			        var o=new Option(cityName,cityId);
			        cityElemment.options.add(o);  
			        if(cityId=='${jmiMember.city}'){
			        	o.selected=true;
			        }
			   }
			   
			   		getIdDistrict();
			   
		   }

		   function getIdDistrict(){
		   	var city=document.getElementById('city').value;
		   		alDistrictManager.getAlDistrictByCityId(city,callBackIdDistrict);
		   }
		   function callBackIdDistrict(valid){
			   var districtElemment=document.getElementById('district');
    		
        		districtElemment.options.length=0;
     
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		districtElemment.options.add(o);  
           
		       for (var i=0;i<valid.length;i++) {
		        var districtId= valid[i].districtId;	   
		        var districtName=valid[i].districtName;   
		        var o=new Option(districtName,districtId);
		        districtElemment.options.add(o); 
		        
				   if('${jmiMember.district}'==districtId){
				   		o.selected=true;
				   } 
			   }

		   }
		   
		    
		   
		  
		   getProvince();
		   getIdCity();
		   getPapertype();
		   
		   
		   //JS日期系列：根据出生日期 得到周岁年龄               
//参数strBirthday已经是正确格式的2007.02.09这样的日期字符串
//后续再增加相关的如日期判断等JS关于日期处理的相关方法
function jsGetAge(strBirthday)
{       
    var returnAge;
    var strBirthdayArr=strBirthday.split("-");
    var birthYear = strBirthdayArr[0];
    var birthMonth = strBirthdayArr[1];
    var birthDay = strBirthdayArr[2];
    
    d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();
    
    if(nowYear == birthYear)
    {
        returnAge = 0;//同年 则为0岁
    }
    else
    {
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0)
        {
            if(nowMonth == birthMonth)
            {
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
            else
            {
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
        }
        else
        {
            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    
    return returnAge;//返回周岁年龄
    
}

		   
		   
		   
		   
		</script>








</body>
</html>





