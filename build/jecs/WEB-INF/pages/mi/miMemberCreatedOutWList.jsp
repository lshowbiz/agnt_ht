<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>




<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
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
.w490px{ font-size:12px; width:490px; height:150px; padding:130px 0px; margin:0px auto;overflow:hidden; }
.sucleft{ width:105px; height:100px; float:left;}
.sucright{font:12px Arial, 宋体; width:385px; height:100px; float:right;}
.sucrtext{ font:12px Arial, 宋体; width:385px; line-height:23px;}
.sucrtext span{	font:normal 18px Arial;color:#ff1200;}
.sucrtext i,.sucrtext i a:link,.sucrtext i a:visited,.sucrtext i a:hover{ font:normal 12px Arial, 宋体; color:#1d47fc; text-decoration:underline;}
-->
</style>

</head><body class="AppBJ">
   <div class="AppTop">
      <div style="float: left;"><img src="images/wc/AppLogo.png" border="0"></div>
      <div style="float: right; padding-top: 15px;" class="White29"><a href="http://www.p.cn/Default.aspx">返回首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.p.cn/Reg.aspx" target="_parent">注册</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://sso.p.cn/cuo/login?service=http://www.p.cn/Default.aspx">登录</a></div>
   </div>
   <div class="AppContent">
      <div class="App_Con_top">
         <div style="float: left; width: 255px; height: 51px; background-image: url(images/wc/AppImg002.png); overflow: hidden;"></div>
         <div style="float: right; width: 12px; height: 51px; background-image: url(images/wc/AppImg003.png); overflow: hidden;"></div>
      </div>
      
      <div class="ContentBJ">
         <div class="ContentText">
     <div style="width: 846px; height: 3px; background-color: #5c5c5c; overflow: hidden; display: none;"></div>
            <div style="width: 826px; height: 70px; background-color: #5c5c5c; padding: 10px; overflow: hidden; display: none;">
              <div style="float: left; width: 75px;"><img src="images/wc/AppImg006.png"></div>
              <div style="float: left; width: 730px;" class="White12"><strong>Hi 我是P.CN</strong><br>
                &nbsp;&nbsp;&nbsp;&nbsp;现在推荐您的好友一起加入p.cn吧，赶快把惊喜与实用带给您的好友，推荐好友成功，您将有机会获得PV奖励</div>
            </div>
            <!--ContentForm start-->
           <div class="ContentForm" style="clear: both;">
           <div class="w490px">
           <div class="sucleft"><img src="images/wc/apimg04.gif" width="95" height="83" /></div>
           <div class="sucright">
           <div style="font:bold 14px 宋体; color:#F00; height:25px; line-height:25px;">恭喜您，已成功注册p.cn！</div>
           <div class="sucrtext">您的DTN号码为：<span>${resJmiMember.userCode }</span>，现在请定制您的个性专属上网主页吧！<i><a href="https://sso.p.cn/cuo/login?service=http://www.p.cn/Default.aspx">点击这里立即登录>></a></i></div>
           <div style=" width:100%;line-height:23px; color:#969696;">注意：p.cn全球统一认证中心登录需要DTN号码，请牢记您的DNT号码。</div>
           </div>
           </div>   
           </div>
 <!--ContentForm end--></div>
      </div>
      
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

           <img src="images/wc/stat.gif" width="0" border="0" height="0">
                </div>              
    </div>
</body></html>