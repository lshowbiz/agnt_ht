//返回字符串长度（中英文）
function strLen(str) {
	str = replace(str,"%","b");
	var strE = escape(str);
	var count = getCountOfSub(strE,"%");// 中文字符数量
	var lenE = strE.length;
	var lenCh = lenE - count*6;// ascii字符数量
    return lenCh + count*2;
}

/**
 * 验证IP地址
 */
function javaValidIPAddress(addr)
{
	str = addr;
	str = addr.replace(/\./g,"");
	str2 = addr;
	dotCount = 0;
	for (i=0;i<str2.length ;i++ )
	{
		ch = str2.charAt(i);
		if (ch == '.')
		{
			dotCount++;
		}
	}
	if (!javaValidNumber(str) || addr.indexOf("-") != -1 || dotCount != 3 || addr.length>15)
	{
		alert("请输入正确的IP地址");
		return false;
	}
	str = addr;
	ip1 = str.substring(0,str.indexOf("."));
	str = str.substring(str.indexOf(".") + 1);
	ip2 = str.substring(0,str.indexOf("."));
	str = str.substring(str.indexOf(".") + 1);
	ip3 = str.substring(0,str.indexOf("."));
	str = str.substring(str.indexOf(".") + 1);
	ip4 = str;
	ip1 = ip1.length>0?ip1:"300";
	ip2 = ip2.length>0?ip2:"300";
	ip3 = ip3.length>0?ip3:"300";
	ip4 = ip4.length>0?ip4:"300";
	if (parseInt(ip1) > 255 || parseInt(ip2) > 255 || parseInt(ip3) > 255 || parseInt(ip4) > 255)
	{
		alert("请输入正确的IP数值");
		return false;
	}
	return true;  
}

// 验证日期, 不兼容Firefox
function javaValidDate(mydt){
	var mydate = '' ; mytime = ''
	var tmp,dttmp

	if (mydt.indexOf(" ") == -1) { 
		mydate = mydt
	}else{
		dttmp = mydt.split(" ")
		mydate = dttmp[0]
		if (dttmp[1]) { mytime = dttmp[1] }
	}
	
	
	// ************* check date
	mydate = mydate.replace("-","/")
	mydate = mydate.replace("-","/")

	var dttmp = mydate.split("/")
	
	if( dttmp.length != 3 ){ return false }
	mydate = new Date(mydate)
	if( isNaN(mydate) ){ return false }

	tmp = mydate.getYear()
	if(tmp < 100){ tmp = tmp + 1900}
	if(tmp > 2099){ return false}
	if(dttmp[0] != tmp){ return false }

	if(dttmp[1] != mydate.getMonth()+1){ return false }
	if(dttmp[2] != mydate.getDate()){ return false }
	
	// ********* datetime is valid
	if ('' == mytime) { return true }

	// ************* check time
	
	var dttmp = mytime.split(":")
	if (dttmp.length > 3) { return false }
	if (dttmp.length >= 1){
		tmp = dttmp[0]
		if (isNaN(tmp) || tmp == '') { return false }
		tmp = parseInt(tmp)
		if (tmp < 0 || tmp > 24){ return false }
	}
	if (dttmp.length >= 2){
		tmp = dttmp[1]
		if (isNaN(tmp) || tmp == '') { return false }
		tmp = parseInt(tmp)
		if (tmp < 0 || tmp > 60){ return false }
	}
	if (dttmp.length == 3){
		tmp = dttmp[2]
		if (isNaN(tmp) || tmp == '') { return false }
		tmp = parseInt(tmp)
		if (tmp < 0 || tmp > 60){ return false }
	}
	
	// ********* datetime is valid
	return true
}

	// 判断字符串是否是有效的日期格式
// 日期的正确格式：2004-09-15 2004/09/15 20040915
// separator:分隔符
function isValidDateFormat(objStr,separator){
	var dateExp;
	// var dateArray;
	switch(separator){
		case "-":
			dateExp=/^([0-9]{4})-([0-9]{2})-([0-9]{2})$/;
			break;
		case "/":
			dateExp=/^([0-9]{4})\/([0-9]{1,2})\/([0-9]{1,2})$/;
			break;
		default:
			dateExp=/^([0-9]{4})([0-9]{2})([0-9]{2})$/;
			break;	
	}
	if(dateExp.test(objStr)){
		var dateArray = objStr.match(dateExp);
		var year = dateArray[1];
		var month = dateArray[2];
		var day = dateArray[3];
		if(month<1 || month>12) return false;
		if(day<1 || day>31) return false;
		if((month==4 || month==6 || month==9 || month==11) && day>30) return false;// 平月最大日期为30
		if(month==2){
			if(isLeapYear(year) && day>29) return false;// 闰年2月最大29
			if(!isLeapYear(year) && day>28) return false;// 平年2月最大28
		}
		return true;			
	}
	return false;			
}

// 验证是否数字
function javaValidNumber(str) { 
   tmp = Math.floor(str)
   if (isNaN(tmp)){
       return false
   }
   return true
}

// 验证是否Email
function javaValidEmail(str){
	var cnt1, cnt2;
	var len1;

	if(javaValidString(str)==false) {
		return false;
	}

	// 检查E-Mail是否正确！
	cnt1=0;
	cnt2=0;
	len1 = str.length;
	for(var i=0; i<len1; i++) {
		if(str.charAt(i)=='@') 	{
			cnt1++;
		}
		if(str.charAt(i)=='.') 	{
			cnt2++;
		}
		if(str.charAt(i)==' '){
			return false;
		}
	}
	if( cnt1!=1 || cnt2<1){
		return false;
	}
	return true;
}

// 去除空格
function javaTrim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

// 检查是否为任意数（实数）
function   isNumeric(strNumber)   {  
	var   newPar=/^(-|\+)?\d+(\.\d+)?$/  
	return   newPar.test(strNumber);  
}  
// 检查是否为正数
function   isUnsignedNumeric(strNumber)   {  
	var   newPar=/^\d+(\.\d+)?$/  
	return   newPar.test(strNumber);  
}  
// 检查是否为整数
function   isInteger(strInteger)   {  
	var   newPar=/^(-|\+)?\d+$/  
	return   newPar.test(strInteger);  
}  
// 检查是否为正整数
function   isUnsignedInteger(strInteger)   {  
	var   newPar=/^\d+$/  
	return   newPar.test(strInteger);  
}
// 检查是否为正整数最多保留2为小数
function isUnsignedMoney(strInteger)   {  
	var regu = /^([1-9]\d*|0)(\.\d{1,2})?$/; 
	return regu.test(strInteger);
	
}
// 检查是否全为大写字母
function isAllUpperLetter(str){
	var newPar=/^[A-Z]+$/;
	return newPar.test(str);
}

// 检查是否为可用用户帐号:所有包含一个以上的字母、数字或下划线的字符串
function isValidateUserCode(str){
	var newPar=/^[a-zA-Z0-9]+$/;
	return newPar.test(str);
}