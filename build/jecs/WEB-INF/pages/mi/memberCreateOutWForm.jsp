<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>






<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 


<script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
<script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alCityManager.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/dwr/interface/alDistrictManager.js'/>" ></script>





















<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新用户注册--P.CN云计算门户，优秀的个性化平台</title>
<meta content="P.CN,快速上网个人桌面,个性化门户,个性网址导航,收藏夹,网址收藏夹,个性书签,网络书签,上网导航,快捷上网" name="keywords">
<meta content="P.CN,快速上网个人桌面,个性化门户,属于自己独有的个性快捷网址导航" name="description">
<meta content="P.CN,快速上网个人桌面,p.cn" name="author">
<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible">
<link type="image/x-icon" href="http://www.p.cn/images/wc/32.ico" rel="shortcut icon">
<link type="image/vnd.microsoft.icon" href="http://www.p.cn/images/wc/32.ico" rel="icon">
<link href="css/wc/Public.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.AppTop,.AppContent{width:890px; margin-left:auto; margin-right:auto; overflow:hidden;}
.AppBJ{ background-color:#000; width:100%; background-image:url(images/wc/AppBJ.png); background-position:top; background-repeat:repeat-x;}
.App_Con_top{ width:100%; height:50px; background-image:url(images/wc/AppImg001.png); overflow:hidden;}
.ContentBJ{ clear:both; width:100%; background-image:url(images/wc/AppImg004.png); overflow:hidden;}
.ContentText{ margin:0px 8px; padding:15px; background-image:url(images/wc/AppImg005.png); background-position:left top; background-repeat:repeat-x; background-color:#FFF; overflow:hidden;}
.ContentForm{ width:100%;margin-top:10px; overflow:hidden;}
.ContentForm_left{ width:420px; float:left; overflow:hidden;}
.ContentForm_left ul{ margin:0px; padding:0px;}
.ContentForm_left ul li{ width:420px;margin-top:10px;display:block; overflow:hidden;}
.Form01{ float:left; width:120px; text-align:right;font-size:12px; line-height:20px; color:#000; overflow:hidden;}
.Form02{ float:left; overflow:hidden;}
.Form03{ float:left; overflow:hidden;}
.Form04{ float:left; background-image:url(images/wc/AppImg008.png); height:29px; overflow:hidden;}
.Form05{ float:left; background-image:url(images/wc/AppImg011.png); height:29px; overflow:hidden;}
.App_border{ width:230px; height:21px; line-height:21px; margin:0px; padding:0px; display:block;}
.ContentForm_right{ width:200px; float:right; border:1px solid #dadada; padding:10px; margin-top:15px; overflow:hidden;}
.App_Con_foot{ width:100%; height:13px; background-image:url(images/wc/AppImg019.png); overflow:hidden;}
.button{cursor:pointer;}
.containment{float:left;border:1px red solid;}
.Success{ width:500px; padding:120px 0px; margin:0 auto; overflow:hidden;}
-->
</style>

</head><body class="AppBJ">
   <div class="AppTop">
      <div style="float: left;"><img src="images/wc/AppLogo.png" border="0" /></div>
      <div style="float: right; padding-top: 15px;" class="White29"><a href="http://www.p.cn/Default.aspx">返回首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.p.cn/Reg.aspx" target="_parent">注册</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://sso.p.cn/cuo/login?service=http://www.p.cn/Default.aspx">登录</a></div>
   </div>
   <div class="AppContent">
      <div class="App_Con_top">
         <div style="float: left; width: 255px; height: 51px; background-image: url(images/wc/AppImg002.png); overflow: hidden;"></div>
         <div style="float: right; width: 12px; height: 51px; background-image: url(images/wc/AppImg003.png); overflow: hidden;"></div>
      </div>
   
    
        <spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    	<c:set value="" var="errorMsg"></c:set>
    	<c:set value="\n" var="spa"></c:set>
        <c:forEach var="error" items="${status.errorMessages}">
        	
            <c:set var="errorMsg" value="${elf:append(errorMsg , elf:append(error,spa))}"/>
        </c:forEach>
        
        
		<script type="text/JavaScript">
            alert('${errorMsg}');
        </script>
    </c:if>
</spring:bind>
   
<form:form commandName="jmiMember" method="post" action="memberRegisterW.do"  id="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}">

   
      <div class="ContentBJ">
         <div class="ContentText">
            <div style="width: 846px; float: left; background-image: url(images/wc/Enterprise_005_On.gif); margin-top: 6px;">
         <div style="float: left;"><a href="http://www.p.cn/Reg.aspx"><img src="images/wc/Enterprise_006.gif" border="0" /></a><a href="http://www.p.cn/EnterpriseReg.aspx"><img src="images/wc/Enterprise_007_On.gif" border="0"></a><a href="http://www.p.cn/dtnreg.aspx"><img src="images/wc/Dtn_003_On.gif" border="0"></a><img src="images/wc/linebuy.png" border="0"></div>
         <div style="float: right;"><img src="images/wc/Enterprise_004_On.gif" /></div>
      </div>
            <div style="width: 846px; height: 3px; background-color: #5c5c5c; overflow: hidden; display: none;"></div>
            <div style="width: 826px; height: 70px; background-color: #5c5c5c; padding: 10px; overflow: hidden; display: none;">
              <div style="float: left; width: 75px;"><img src="images/wc/AppImg006.png" /></div>
              <div style="float: left; width: 730px;" class="White12"><strong>Hi 我是P.CN</strong><br>
                &nbsp;&nbsp;&nbsp;&nbsp;现在推荐您的好友一起加入p.cn吧，赶快把惊喜与实用带给您的好友，推荐好友成功，您将有机会获得PV奖励</div>
            </div>
            <!--ContentForm start-->
           <div class="ContentForm" style="clear: both;">
            <!--ContentForm_left　start-->
           
            
            
<form:hidden path="userCode"/>
            <div class="ContentForm_left">
                  <ul class="Bule12">
                  <li><img src="images/wc/apimg01.gif" /></li>
                  
                     <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>推荐人编号：</strong></div>
                        <div class="Form02">
                        <form:input path="jmiRecommendRef.recommendJmiMember.userCode" id="recommendNo" cssClass="App_border"/>
                        </div>
                     </li>
                     <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>姓：</strong></div>
                        <div class="Form02"><form:input path="firstName" id="firstName" cssClass="App_border"/></div>
                    
                   </li>
                     <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>昵称：</strong></div>
                        <div class="Form02"><form:input path="petName" id="petName" cssClass="App_border"/></div>
                     </li>
                     
                      <li>
                        <div class="Form01"><strong>证件类型：</strong></div>
                        <div class="Form02">
                         <select name="papertype" id="papertype"><option value="1" selected="selected">身份证</option><option value="3">回乡证</option><option value="2">护照</option></select>
                        
                        </div>
                     </li>
                     
                    <li>
                       <div class="Form01"><strong><font color="#FF0000">*</font>身份证省：</strong></div>
                       <div class="Form02">
                         <form:select path="province"  cssClass="App_border" onchange="getIdCity();">
					<form:option label="" value=""/>
		            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
		</form:select>
                       </div>
                     </li>
                     <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>身份证所在区县：</strong></div>
                        <div class="Form02">
                           <select name="district" id="district">
				<option value=""><jecs:locale key="list.please.select"/></option>
			</select>	
 
                        </div>
                     </li>
                     <li>
                        <div class="Form01"><strong>身份证邮编：</strong></div>
                        <div class="Form02"><form:input path="postalcode" id="postalcode" cssClass="App_border"/></div>
                     </li>
                     <li>
                        <div class="Form01"><strong>传真：</strong></div>
                        <div class="Form02"> <form:input path="faxtele" id="faxtele" cssClass="App_border"/></div>
                     </li>
                     <li>
                        <div class="Form01"><strong>电子邮箱：</strong></div>
                        <div class="Form02"> <form:input path="email" id="email" cssClass="App_border"/></div>
                     </li>
                  </ul>          
             </div>
 <!--ContentForm_left end--> 
 <!--ContentForm_left start-->   
 <div class="ContentForm_left">
                  <ul class="Bule12">
                  <li>&nbsp;　</li>
                  
                    <li>
                        <div class="Form01"><strong>&nbsp;&nbsp;</div>
                      <div class="Form02">&nbsp;&nbsp;</div>
                     </li>
                     <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>名：</strong></div>
                        <div class="Form02"><form:input path="lastName" id="lastName" cssClass="App_border"/></div>
                    
                   </li>
                     <li>
                        <div class="Form01"><strong>姓别：</strong></div>
                        <div class="Form02"> <jecs:list name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue=""/></div>
                     </li>
                     
                      <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>证件号：</strong></div>
                        <div class="Form02">
                         <form:input path="papernumber" id="papernumber" cssClass="App_border" />
                        </div>
                     </li>
                     
                    <li>
                       <div class="Form01"><strong><font color="#FF0000">*</font>身份证城市：</strong></div>
                       <div class="Form02">
                          <select name="city" id="city" onchange="getIdDistrict();">
			<option value=""><jecs:locale key="list.please.select"/></option>
		</select>
 
                       </div>
                    </li>
                     <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>身份证住址：</strong></div>
                        <div class="Form02"><form:input path="address" id="address" cssClass="App_border"/></div>
                     </li>
                     <li>
                        <div class="Form01"><strong>联系电话：</strong></div>
                        <div class="Form02">  <form:input path="phone" id="phone" cssClass="App_border"/></div>
                     </li>
                     <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>移动电话：</strong></div>
                        <div class="Form02"> <form:input path="mobiletele" id="mobiletele" cssClass="App_border"/></div>
                     </li>
                  </ul>              
             </div>
 <!--ContentForm_left end-->   
 
           </div>
           
           
           
 <!--ContentForm end-->
 <div class="ContentForm" style="clear: both;">
            <!--ContentForm_left　start-->
            <div class="ContentForm_left">
                  <ul class="Bule12">
                  <li><img src="images/wc/apimg02.gif" /></li>
                  <li>
                    <div class="Form01"><strong><font color="#FF0000">*</font>密码：</strong></div>
                    <div class="Form02">
                     <form:password path="sysUser.password" cssClass="App_border" id="password1"/>
                    </div>
                  </li>
                  <li>
                    <div class="Form01"><strong><font color="#FF0000">*</font>进阶密码：</strong></div>
                    <div class="Form02">
                       <form:password path="sysUser.password2" cssClass="App_border" id="password2"/>
                      </div>
                  </li>
                  <li>
                     <div class="Form01">&nbsp;</div>
                     <div class="Form02 button" id="imgSubmit"><img onclick="jmiMemberForm.submit();" src="images/wc/apimg03.gif" /></div>
                    </li>
                     <li>
                        <div class="Form01">&nbsp;</div>
                        <div class="Form02 button">
                           <div>点击 立即注册 表示您同意并遵守 <a href="http://www.p.cn/Agreement.html" target="_blank" style="text-decoration: underline;">网创用户注册协议</a></div>
                        </div>
                     </li>
                  </ul>          
        </div>
 <!--ContentForm_left end--> 
 <!--ContentForm_left start-->   
 <div class="ContentForm_left">
                  <ul class="Bule12">
                  <li>&nbsp;　</li>
                  
                    <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>确认密码：</strong></div>
                      <div class="Form02"><input id="password1Confirm" name="password1Confirm" type="password" class="App_border" /></div>
                     </li>
                     <li>
                        <div class="Form01"><strong><font color="#FF0000">*</font>确认进阶密码：</strong></div>
                        <div class="Form02"><input id="password2Confirm" name="password2Confirm" type="password" class="App_border" /></div>
                   </li>
                  </ul>               
        </div>
        
        
 <!--ContentForm_left end-->          
            </div>
           
         </div>
      </div>
     </form:form>
      <div class="App_Con_foot">
         <div style="float: left; width: 14px; height: 13px; background-image: url(images/wc/AppImg018.png); overflow: hidden;"></div>
         <div style="float: right; width: 14px; height: 13px; background-image: url(images/wc/AppImg020.png); overflow: hidden;"></div>
      </div>
   </div>
    <div class="foot Gray18">
        <a href="http://www.p.cn/about/about.html" target="_blank">关于我们</a><span class="Gray2_24">|</span><a href="http://www.p.cn/about/contact.html" target="_blank">联系我们</a><span class="Gray2_24">|</span><a href="http://www.p.cn/about/advertising.html" target="_blank">广告服务</a><span class="Gray2_24">|</span><a href="http://www.p.cn/about/web_map.html" target="_blank">网站地图</a>
        <span class="Gray2_24">|</span><a href="#" target="_blank">媒体报道</a><span class="Gray2_24">|</span><a href="#" target="_blank">网站联盟</a><span class="Gray2_24">|</span><a targe="_blank" href="http://www.miibeian.gov.cn/">粤ICP备10027335号</a><span class="Gray2_24">|</span><a href="#" target="_blank">增值电信业务经营许可证：粤B2-20100313</a><br>
        <div>
              Copyright © 2009-2010. All Rights Reserved. Powered by <a href="http://www.p.cn/" target="_blank">
                P.CN</a> 

           <img src="images/wc/stat.gif" width="0" border="0" height="0" />
                </div>              
    </div>
</body></html>



<script>

getIdCity();
			
			
			
			function getIdCity(){
			
		   var province=document.getElementById('province').value;
		   	alCityManager.getAlCityByProvinceId(province,callIdCity);
		   
		   }
		   function callIdCity(valid){
			   var cityElemment=$('city');
        		cityElemment.options.length=0;
          		var o=new Option("<jecs:locale key="list.please.select"/>","");
           		cityElemment.options.add(o);  

           		
			   var districtElemment=$('district');
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
</script>



































