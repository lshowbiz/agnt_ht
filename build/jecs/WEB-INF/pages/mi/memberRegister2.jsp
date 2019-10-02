
<%@ include file="/common/taglibs.jsp"%>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<title><jecs:locale key="jmiAddrBookDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiAddrBookDetail.heading"/></content>


<style type="text/css">
<!--
	body {margin:0; font-family:Verdana, Geneva, sans-serif; font-size:13px;}
	img {border:0; vertical-align:middle;}
	button { cursor:pointer; vertical-align:middle; color:#090;}
	.regButton { background:url(images/joinUS/regButtonBg.jpg) center center; border:1px double #dddddd; padding:4px 12px 2px 12px;}
	.regButtonOver { background:url(images/joinUS/regButtonBgOver.jpg) center center;border:none; border:1px double #dddddd; padding:4px 12px 2px 12px;}
	.register {margin:0;padding:4px; border-collapse:collapse;}
	.register .required { font-weight:bold; color:#060; font-size:13px;}
	.register .title { background:#f0f0f0; border-bottom:2px solid #9c0;padding:6px 2px 2px 2px; font-size:15px; color:#060; font-weight:bold;}
	.register th {font-weight:normal; color:#999; border-bottom:1px solid #f0f0f0; padding-left:32px; text-align:right;}
	.register td { border-bottom:1px solid #f0f0f0;padding:2px; text-align:left; width:50%}
-->
</style>

<form:form commandName="jmiMember" action="memberRegister.html"  method="post">
        
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
    	<div style="float:left; background:url(images/joinUS/logo.jpg);width:354px;height:76px;" title="Joymain international" /></div>
    	<div style="float:right;padding:27px 27px 0 0;"> <jecs:label key="register.language.select" /><img src="images/joinUS/regTopIco.jpg" width="33" height="26" align="absmiddle" alt="" />
   
       <select name="characterCode" onchange="javascript:$('_target0').name='_target1';$('lang').value=this.value;this.form.submit();">
        <c:forEach items="${alCharacterCodings}" var="alCharacterCodingVar">
			<option value="${alCharacterCodingVar.characterCode }"<c:if test="${alCharacterCodingVar.characterCode==sessionScope.sessionLogin.defCharacterCoding }"> selected</c:if>>${alCharacterCodingVar.characterName }</option>
        </c:forEach>
        </select>
        <input id="lang" name="lang" value="" type="hidden">
        </div>
    </td>
  </tr>
  <tr>
    <td style="background:url(images/joinUS/regMenuBg.jpg); height:36px; padding:0 12px 0 12px" nowrap>
    	<img src="images/joinUS/regMenuIcon.jpg" border="0" align="absmiddle" style="float:left;" />
        <p style="color:#360;margin:10px 0 0 0;"><jecs:locale key="register.select.a"/> &gt;&gt; <span style="color:#fff"><jecs:locale key="register.select.b"/></span> &gt;&gt; <jecs:locale key="register.select.c"/> &gt;&gt; <jecs:locale key="register.select.d"/></p>
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td valign="top" style="padding:4px 4px 4px 9px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="background:url(images/joinUS/regCententTitleBg.jpg);">
          <tr>
            <td width="56" height="56" align="right"><img src="images/joinUS/regCententTitleIco2.jpg" width="56" height="56" alt="" /></td>
            <td style="font-size:16px; font-family:Verdana, Geneva, sans-serif; font-weight:bold; padding:0 0 0 12px; color:#333">Billing & Distributor Information</td>
            <td align="right" width="6"><img src="images/joinUS/regCententTitleRight.jpg" width="6" height="56" alt="" /></td>
          </tr>
        </table>
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
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
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
                        <%--<tr>
                            <th class="required"></th>
                            <td width="250">
                                
                                    <input name="appType" type="radio" class="checkbox" id="appType1" value="1"  checked onClick="location.href='?appType='+this.value"/>
                                <label for="appType1"></label>
                                
                                    <input name="appType" type="radio" class="checkbox" id="appType2" value="2"  onclick="location.href='?appType='+this.value"/>
                                <label for="appType2"></label>
                                
                            </td>
                        </tr>--%>
                        <tr>
                            <th class="required"><jecs:locale  key="miMember.linkNo" /></th>
                            <td nowrap="nowrap">
                                <form:input path="jmiLinkRef.linkJmiMember.userCode" cssStyle="width:132px;"/>
                                
                                <%--<input id="linkNo" name="miLinkRef.linkMiMember.memberNo" style="width:132px;" type="text" value=""/>--%>
                                <%--<button type="button" class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                                	onClick="miSelectLink()"><img src="images/joinUS/icon/Alert.gif" border="0" align="absmiddle" />Auto-placement</button>--%>
                                
                                
                            </td>
                        </tr>
              
                        
                        <tr>
                            <td colspan="2" class="title"><jecs:locale key="register.us.legend.member" /></td>
                        </tr>
                        
              
                        
                        <tr>
                            <th class="required"><jecs:locale key="miMember.firstName" /></th>
                            <td align="right">
        						<form:input path="firstName" id="firstName" cssClass="inputBox"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:locale key="miMember.lastName" /></th>
                            <td align="right">
       							 <form:input path="lastName" id="lastName" cssClass="inputBox"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="required"> <jecs:locale  key="miMember.sex"/></th>
                            <td>
                                
        					 <jecs:list name="sex" listCode="sex" value="${jmiMember.sex}" defaultValue=""/>
 
                                
                            </td>
                        </tr>
                        <tr >
                            <th class="required"><jecs:locale key="miMember.birthday" /></th>
                            <td align="right" nowrap="nowrap">
                               <form:input path="birthday" id="birthday" cssClass="readonly" readonly="true"/> 
					         <img src="./images/calendar/calendar7.gif" id="img_birthday" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
									<script type="text/javascript"> 
										Calendar.setup({
											inputField     :    "birthday", 
											ifFormat       :    "%Y-%m-%d",  
											button         :    "img_birthday", 
											singleClick    :    true
										}); 
									</script>
                            </td>
                        </tr>
                        <tr>
                            <th class="required"> <jecs:locale key="busi.address" /></th>
                            <td align="right">
       						 <form:input path="address" id="address" cssClass="inputBox"/>
                            
                            </td>
                        </tr>
                        <!-- <tr>
                            <th>Address Line 2 </th>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <th>Address Line 3</th>
                            <td>&nbsp;</td>
                        </tr>-->
                        <tr>
                            <th class="required"> <jecs:locale key="busi.city" />/ <jecs:locale key="miMember.province"  />/<jecs:locale key="miMember.postalcode"  /></th>
                            <td align="right" nowrap="nowrap">
                                <form:input path="city" id="city" cssStyle="width:98px"/>
                                <%--<select id="idArea" name="idArea" style="width:101px">
                                    <option value="" selected="selected"></option>
                                    <option value="002">Alabama</option><option value="003">Alaska</option><option value="004">Arizona</option><option value="005">Arkansas</option><option value="006">California</option><option value="007">Colorado</option><option value="008">Connecticut</option><option value="009">Delaware</option><option value="011">Florida</option><option value="012">Georgia</option><option value="013">Hawaii</option><option value="014">Idaho</option><option value="015">Illinois</option><option value="016">Indiana</option><option value="017">Iowa</option><option value="018">Kansas</option><option value="019">Kentucky</option><option value="020">Louisiana</option><option value="021">Maine</option><option value="022">Maryland</option><option value="023">Massachusetts</option><option value="024">Michigan</option><option value="025">Minnesota</option><option value="026">Mississippi</option><option value="027">Missouri</option><option value="028">Montana</option><option value="029">Nebraska</option><option value="030">Nevada</option><option value="031">New Hampshire</option><option value="032">New Jeresy</option><option value="033">New Mexico</option><option value="034">North Carolina</option><option value="035">North Dakota</option><option value="036">Ohio</option><option value="037">Oklahoma</option><option value="038">Oregon</option><option value="039">Pennsylvania</option><option value="010">Puerto Rico</option><option value="040">Rhode Island</option><option value="041">South Carolina</option><option value="042">South Dakota</option><option value="043">Tennessee</option><option value="044">Texas</option><option value="045">Utah</option><option value="046">Vermont</option><option value="047">Virginia</option><option value="048">Washington</option><option value="049">West Virginia</option><option value="050">Wisconsin</option><option value="051">Wyoming</option><option value="001">new york</option>
                                </select>--%>
                                
						        <form:select path="province"  cssStyle="width:101px">
											<form:option label="" value=""/>
								            <form:options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>
								</form:select>
						                                
                                <form:input path="postalcode" id="postalcode" cssStyle="width:50px"/>
                            </td>
                        </tr>
                        <!-- <tr>
                            <th>Country</th>
                            <td>&nbsp;</td>
                        </tr>-->
                        <tr>
                            <th class="required"> <jecs:locale key="miMember.phone" /></th>
                            <td align="right">
                            <form:input path="phone" id="phone" cssClass="inputBox"/></td>
                        </tr>
                        <tr>
                            <th> <jecs:locale  key="miMember.faxtele"/></th>
                            <td align="right">
                            <form:input path="faxtele" id="faxtele" cssClass="inputBox"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:locale key="miMember.mobiletele"  /></th>
                            <td align="right">
                           <form:input path="mobiletele" id="mobiletele" cssClass="inputBox"/>
                            </td>
                        </tr>
                        <tr>
                            <th><jecs:locale  key="miMember.officetele"/></th>
                            <td align="right">
                            <form:input path="officetele" id="officetele" cssClass="inputBox"/> 
                            </td>
                        </tr>
                        <tr>
                            <th ><jecs:locale   key="miMember.email"/></th>
                            <td align="right">
                           <form:input path="email" id="email" cssClass="inputBox"/>
                            </td>
                        </tr>
                        
           
                        
                        <tr>
                            <td colspan="2" class="title"><jecs:locale  key="register.bank" /></td>
                        </tr>
                        <tr>
                            <th class="required"> <jecs:locale  key="miMember.openBank" /></th>
                            <td align="right">
	                      <form:select path="bank"  cssClass="inputBox">
							<form:option value=""></form:option>
				            <form:options items="${sysBankList}" itemValue="bankKey" itemLabel="bankValue"/>
				        </form:select>
                            </td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:locale  key="miMember.bcity" /></th>
                            <td align="right">
                            <form:input path="bankaddress" id="bankaddress" cssClass="inputBox"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:locale  key="miMember.bname" /></th>
                            <td align="right"><jecs:locale key="miMember.openName.def" /></td>
                        </tr>
                        <tr>
                            <th class="required"><jecs:locale  key="miMember.bnum" /></th>
                            <td align="right"><form:input path="bankcard" id="bankcard" cssClass="inputBox"/></td>
                        </tr>
               
                        
                        <tr>
                            <td colspan="2" class="title">Backoffice Information</td>
                        </tr>
                        
                        <tr>
                            <th class="required"><jecs:locale key="miMember.pwd1" /></th>
                            <td align="right"><form:password path="sysUser.password" cssClass="inputBox" id="password1"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="required"><span style="color:#9C0"><jecs:locale key="label.affirmNewPassword" /></span></th>
                            <td align="right"><input id="password1Confirm" name="password1Confirm" type="password" ></td>
                        </tr>
                        
                        <tr>
                            <th class="required"><jecs:locale key="sysUser.defCharacterCoding" /></th>
                            <td align="right">
                            <%--<form:select path="sysUser.defCharacterCoding" cssClass="selectBox">
                            	<form:options items="" itemValue="" itemLabel=""/>
                            </form:select>--%>
        <form:select path="sysUser.defCharacterCoding"  cssClass="selectBox">
					<form:option label="" value=""/>
		            <form:options items="${alCharacterCodings}" itemValue="characterCode" itemLabel="characterName"/>
		</form:select>
                            
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"><hr size="1"/></td>
                        </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td align="right" nowrap><button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="submit" onclick="javascript:$('_target0').name='_cancel';"  value="" ><img src="images/joinUS/icon/Delete.gif" border="0" align="absmiddle" /><jecs:locale key="operation.button.cancel"/></button>
                    	
                    <button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="submit" onclick="javascript:$('_target0').name='_target0';" ><img src="images/joinUS/icon/arrowLeft.gif" border="0" align="absmiddle" /><jecs:locale key="register.perous"/></button>
						<input type="hidden" id="_target0">
                    <button class="regButton" onmouseover="this.className='regButtonOver'" onmouseout="this.className='regButton'" 
                    	type="submit" onclick="javascript:$('_target0').name='_target2';" ><img src="images/joinUS/icon/arrowRight.gif" border="0" align="absmiddle" /><b><jecs:locale key="button.next"/></b></button>
                  </td>
                 </tr>
                </table>
            	<!--centent end-->
            </td>
          </tr>
        </table>
		</form:form>
        </td>
        <td valign="top" width="30%" style="padding:10px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="50" height="42" align="left"><img src="images/joinUS/regRightTitleIco.jpg" width="50" height="42" alt="" /></td>
            <td width="100%" background="images/joinUS/regRightTitleBg.jpg" valign="middle" style="color:#990; font-size:16px; font-weight:bold;" nowrap="nowrap"><jecs:locale key="register.fillInfo"/></td>
            <td width="6" height="42" align="right"><img src="images/joinUS/regRightTitleRight.jpg" width="6" height="42" alt="" /></td>
          </tr>
          <tr>
            <td colspan="3" style="border-left:1px solid #99cc00;border-right:1px solid #99cc00;">
            <br />
            <ul style=" font-size:13px; color:#333; padding:6px; margin:24px 0 0 24px; list-style:decimal; line-height:24px;">
             <jecs:locale key="register.select.into.2"/>

            </ul>
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
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
        </table></td>
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
