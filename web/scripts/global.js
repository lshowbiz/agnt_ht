/* This function is used to change the style class of an element */
function swapClass(obj, newStyle) {
    obj.className = newStyle;
}

function isUndefined(value) {   
    var undef;   
    return value == undef; 
}
	function switchAll() {
			var selectedAll=document.getElementsByName("selectedAll");
 			var selectedId=document.getElementsByName("selectedId");
			if(selectedId!=undefined){
				for(var i=0;i<selectedId.length;i++){
					selectedId[i].checked=selectedAll[0].checked;
				}
			}
		}
function checkAll(theForm) { // check all the checkboxes in the list
  for (var i=0;i<theForm.elements.length;i++) {
    var e = theForm.elements[i];
		var eName = e.name;
    	if (eName != 'allbox' && 
            (e.type.indexOf("checkbox") == 0)) {
        	e.checked = theForm.allbox.checked;		
		}
	} 
}

/* Function to clear a form of all it's values */
function clearForm(frmObj) {
	for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
		if(element.type.indexOf("text") == 0 || 
				element.type.indexOf("password") == 0) {
					element.value="";
		} else if (element.type.indexOf("radio") == 0) {
			element.checked=false;
		} else if (element.type.indexOf("checkbox") == 0) {
			element.checked = false;
		} else if (element.type.indexOf("select") == 0) {
			for(var j = 0; j < element.length ; j++) {
				element.options[j].selected=false;
			}
            element.options[0].selected=true;
		}
	} 
}

/* Function to get a form's values in a string */
function getFormAsString(frmObj) {
    var query = "";
	for (var i = 0; i < frmObj.length; i++) {
        var element = frmObj.elements[i];
        if (element.type.indexOf("checkbox") == 0 || 
            element.type.indexOf("radio") == 0) { 
            if (element.checked) {
                query += element.name + '=' + escape(element.value) + "&";
            }
		} else if (element.type.indexOf("select") == 0) {
			for (var j = 0; j < element.length ; j++) {
				if (element.options[j].selected) {
                    query += element.name + '=' + escape(element.value) + "&";
                }
			}
        } else {
            query += element.name + '=' 
                  + escape(element.value) + "&"; 
        }
    } 
    return query;
}

/* Function to hide form elements that show through
   the search form when it is visible */
function toggleForm(frmObj, iState) // 1 visible, 0 hidden 
{
	for(var i = 0; i < frmObj.length; i++) {
		if (frmObj.elements[i].type.indexOf("select") == 0 || frmObj.elements[i].type.indexOf("checkbox") == 0) {
            frmObj.elements[i].style.visibility = iState ? "visible" : "hidden";
		}
	} 
}

/* Helper function for re-ordering options in a select */
function opt(txt,val,sel) {
    this.txt=txt;
    this.val=val;
    this.sel=sel;
}

/* Function for re-ordering <option>'s in a <select> */
function move(list,to) {     
    var total=list.options.length;
    index = list.selectedIndex;
    if (index == -1) return false;
    if (to == +1 && index == total-1) return false;
    if (to == -1 && index == 0) return false;
    to = index+to;
    var opts = new Array();
    for (i=0; i<total; i++) {
        opts[i]=new opt(list.options[i].text,list.options[i].value,list.options[i].selected);
    }
    tempOpt = opts[to];
    opts[to] = opts[index];
    opts[index] = tempOpt
    list.options.length=0; // clear
    
    for (i=0;i<opts.length;i++) {
        list.options[i] = new Option(opts[i].txt,opts[i].val);
        list.options[i].selected = opts[i].sel;
    }
    
    list.focus();
} 

/*  This function is to select all options in a multi-valued <select> */
function selectAll(elementId) {
    var element = document.getElementById(elementId);
	len = element.length;
	if (len != 0) {
		for (i = 0; i < len; i++) {
			element.options[i].selected = true;
		}
	}
}

/* This function is used to select a checkbox by passing
 * in the checkbox id
 */
function toggleChoice(elementId) {
    var element = document.getElementById(elementId);
    if (element.checked) {
        element.checked = false;
    } else {
        element.checked = true;
    }
}

/* This function is used to select a radio button by passing
 * in the radio button id and index you want to select
 */
function toggleRadio(elementId, index) {
    var element = document.getElementsByName(elementId)[index];
    element.checked = true;
}

/* This function is used to open a pop-up window */
function openWindow(url, winTitle, winParams) {
	winName = window.open(url, winTitle, winParams);
    winName.focus();
}


/* This function is to open search results in a pop-up window */
function openSearch(url, winTitle) {
    var screenWidth = parseInt(screen.availWidth);
    var screenHeight = parseInt(screen.availHeight);

    var winParams = "width=" + screenWidth + ",height=" + screenHeight;
        winParams += ",left=0,top=0,toolbar,scrollbars,resizable,status=yes";

    openWindow(url, winTitle, winParams);
}

/* This function is used to set cookies */
function setCookie(name,value,expires,path,domain,secure) {
  document.cookie = name + "=" + escape (value) +
    ((expires) ? "; expires=" + expires.toGMTString() : "") +
    ((path) ? "; path=" + path : "") +
    ((domain) ? "; domain=" + domain : "") + ((secure) ? "; secure" : "");
}

/* This function is used to get cookies */
function getCookie(name) {
	var prefix = name + "=" 
	var start = document.cookie.indexOf(prefix) 

	if (start==-1) {
		return null;
	}
	
	var end = document.cookie.indexOf(";", start+prefix.length) 
	if (end==-1) {
		end=document.cookie.length;
	}

	var value=document.cookie.substring(start+prefix.length, end) 
	return unescape(value);
}

/* This function is used to delete cookies */
function deleteCookie(name,path,domain) {
  if (getCookie(name)) {
    document.cookie = name + "=" +
      ((path) ? "; path=" + path : "") +
      ((domain) ? "; domain=" + domain : "") +
      "; expires=Thu, 01-Jan-70 00:00:01 GMT";
  }
}

// This function is for stripping leading and trailing spaces
function trim(str) { 
    if (str != null) {
        var i; 
        for (i=0; i<str.length; i++) {
            if (str.charAt(i)!=" ") {
                str=str.substring(i,str.length); 
                break;
            } 
        } 
    
        for (i=str.length-1; i>=0; i--) {
            if (str.charAt(i)!=" ") {
                str=str.substring(0,i+1); 
                break;
            } 
        } 
        
        if (str.charAt(0)==" ") {
            return ""; 
        } else {
            return str; 
        }
    }
} 

// This function is used by the login screen to validate user/pass
// are entered. 
function validateRequired(form) {                                    
    var bValid = true;
    var focusField = null;
    var i = 0;                                                                                          
    var fields = new Array();                                                                           
    oRequired = new required();                                                                         
                                                                                                        
    for (x in oRequired) {                                                                              
        if ((form[oRequired[x][0]].type == 'text' || form[oRequired[x][0]].type == 'textarea' || form[oRequired[x][0]].type == 'select-one' || form[oRequired[x][0]].type == 'radio' || form[oRequired[x][0]].type == 'password') && form[oRequired[x][0]].value == '') {
           if (i == 0)
              focusField = form[oRequired[x][0]]; 
              
           fields[i++] = oRequired[x][1];
            
           bValid = false;                                                                             
        }                                                                                               
    }                                                                                                   
                                                                                                       
    if (fields.length > 0) {
       focusField.focus();
       alert(fields.join('\n'));                                                                      
    }                                                                                                   
                                                                                                       
    return bValid;                                                                                      
}

// This function is a generic function to create form elements
function createFormElement(element, type, name, id, value, parent) {
    var e = document.createElement(element);
    e.setAttribute("name", name);
    e.setAttribute("type", type);
    e.setAttribute("id", id);
    e.setAttribute("value", value);
    parent.appendChild(e);
}

function confirmDelete(theForm, obj) {   
    //var msg = confirmDeleteMsg + obj + "?";
    var msg= confirmDeleteMsg+"?";
    ans = confirm(msg);
    if (ans) {
    	theForm.strAction.value="delete"+obj;
        return true;
    } else {
        return false;
    }
}

function highlightTableRows(tableId) {
    var previousClass = null;
    var table = document.getElementById(tableId); 
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows;
    if (tbody == null) {
        rows = table.getElementsByTagName("tr");
    } else {
        rows = tbody.getElementsByTagName("tr");
    }
    // add event handlers so rows light up and are clickable
    for (i=0; i < rows.length; i++) {
        rows[i].onmouseover = function() { previousClass=this.className;this.className+=' over' };
        rows[i].onmouseout = function() { this.className=previousClass };
        rows[i].onclick = function() {
            var cell = this.getElementsByTagName("td")[0];
            var link = cell.getElementsByTagName("a")[0];
            location.href = link.getAttribute("href");
            this.style.cursor="wait";
        }
    }
}

function highlightFormElements() {
    // add input box highlighting
    addFocusHandlers(document.getElementsByTagName("input"));
    addFocusHandlers(document.getElementsByTagName("textarea"));
}

function addFocusHandlers(elements) {
    for (i=0; i < elements.length; i++) {
        if (elements[i].type != "button" && elements[i].type != "submit" &&
            elements[i].type != "reset" && elements[i].type != "checkbox" && elements[i].type != "radio") {
            if (!elements[i].getAttribute('readonly') && !elements[i].getAttribute('disabled')) {
                elements[i].onfocus=function() {this.style.backgroundColor='#ffd';this.select()};
                elements[i].onmouseover=function() {this.style.backgroundColor='#ffd'};
                elements[i].onblur=function() {this.style.backgroundColor='';}
                elements[i].onmouseout=function() {this.style.backgroundColor='';}
            }
        }
    }
}

function radio(clicked){
    var form = clicked.form;
    var checkboxes = form.elements[clicked.name];
    if (!clicked.checked || !checkboxes.length) {
        clicked.parentNode.parentNode.className="";
        return false;
    }

    for (i=0; i<checkboxes.length; i++) {
        if (checkboxes[i] != clicked) {
            checkboxes[i].checked=false;
            checkboxes[i].parentNode.parentNode.className="";
        }
    }

    // highlight the row    
    clicked.parentNode.parentNode.className="over";
}

window.onload = function() {
    highlightFormElements();
    /*if ($('successMessages')) {
        new Effect.Highlight('successMessages');
        // causes webtest exception on OS X : http://lists.canoo.com/pipermail/webtest/2006q1/005214.html
        // window.setTimeout("Effect.DropOut('successMessages')", 3000);
    }
    if ($('errorMessages')) {
        new Effect.Highlight('errorMessages');
    }*/
    
    /* Initialize menus for IE */
    /*if ($("primary-nav")) {
        var navItems = $("primary-nav").getElementsByTagName("td");
    
        for (var i=0; i<navItems.length; i++) {
            if (navItems[i].className == "menubar") {
                navItems[i].onmouseover=function() { this.className += " over"; }
                navItems[i].onmouseout=function() { this.className = "menubar"; }
            }
        }
    }*/
}

// Show the document's title on the status bar
window.defaultStatus=document.title;

/*显示或隐藏搜索栏*/
function switchSearchBar(searchBarId){
	with(document.getElementById(searchBarId).style){
		if(display=="block"){
			display="none";
		}else{
			display="block";
		}
	}
}

/**
显示普通窗口
**/
function showDialog(siteroot,params,width,Height,scroll) {
	var features="height="+Height+",width="+width;
	if(scroll!=undefined && scroll!=""){
		features+=",scrollbars";
	}
	
	newWindow = window.open(params[1],"subWind","status,menubar,"+features);
	newWindow.focus( );
}

/**
显示模式窗口
**/
function showDialogWin(siteroot,params,width,height,scroll) {
	if(scroll==undefined || scroll==""){
		scroll="no";
	}
	params[3]=scroll;
	var feature="dialogHeight: '"+height+"px'; dialogWidth: '"+width+"px';  help: No; resizable: No; status: No;scrolling:"+scroll+";unadorned:Yes;taget:self";   
	if(!document.all){
		feature="dialogheight:"+height+"; dialogwidth:"+width+"; resizable:off;dialogtop:"+((screen.availHeight-height)/2)+";dialogleft:"+((screen.availWidth-width)/2);//menubar=0;toolbar=0;location=0;scroll:0;status=no;
	}
	var retval = window.showModalDialog(siteroot+"/dialog.html", params, feature);
	return retval;
}


function selectAll(){
		  ar = document.getElementsByName('chkSel');
			for (var i=0;i<ar.length;i++)
				{
					ar[i].checked=true;
				}
	}
function reverseSelect(){
		ar = document.getElementsByName('chkSel');
		for (var i=0;i<ar.length;i++)
			{
				var e = ar[i];
				if (e.checked == true){
				    e.checked = false;
				}else {
					e.checked = true;
				}
			}
}
function cancelCheckAll(){
		ar = document.getElementsByName('chkSel');
		for (var i=0;i<ar.length;i++)
			{
				ar[i].checked=false;
				
			}
}
function toBack(){
		javascript:history.back()
}

/**
* @param filePathName 文件名称,包括路径名
* @return String 小写的后缀名,如.jpg;如果没有,则返回空
**/
function getFileType(filePathName){
	var pos = filePathName.lastIndexOf(".");
	if(pos==-1){
		return "";
	}else{
		return filePathName.substring(pos,filePathName.length).toLowerCase();  
		//此处文件后缀名也可用数组方式获得str.split(".") 
	}
}

function loading(loading){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;'+loading;
	document.getElementById("loading").innerHTML=str;
} 

//用于重复提交
var isPosted=false;

function isFormPosted(){
	if(!isPosted){
		isPosted=true;
		return true;
	}else{
		alert(errorDataSubmited);
		return false;
	}
}

function checkDate(INDate)
{ if (INDate=="")
    {return true;}
 subYY=INDate.substr(0,4)
 if(isNaN(subYY) || subYY<=0){
  return true;
 }
 if(INDate.indexOf('-',0)!=-1){ separate="-"}
 else{
  if(INDate.indexOf('/',0)!=-1){return true;}
  else {return true;}
  }
  area=INDate.indexOf(separate,0)
  subMM=INDate.substr(area+1,INDate.indexOf(separate,area+1)-(area+1))
  if(isNaN(subMM) || subMM<=0){
  return true;
 }
  if(subMM.length<2){subMM="0"+subMM}
 area=INDate.lastIndexOf(separate)
 subDD=INDate.substr(area+1,INDate.length-area-1)
 if(isNaN(subDD) || subDD<=0){
  return true;
 }
 if(eval(subDD)<10){subDD="0"+eval(subDD)}
 NewDate=subYY+"-"+subMM+"-"+subDD
 if(NewDate.length!=10){return true;}
    if(NewDate.substr(4,1)!="-"){return true;}
    if(NewDate.substr(7,1)!="-"){return true;}
 var MM=NewDate.substr(5,2);
 var DD=NewDate.substr(8,2);
 if((subYY%4==0 && subYY%100!=0)||subYY%400==0){ 
  if(parseInt(MM)==2){
   if(DD>29){return true;}
  }
 }else{
  if(parseInt(MM)==2){
   if(DD>28){return true;}
  } 
 }
 var mm=new Array(1,3,5,7,8,10,12); 
 var flag=false;
 
 for(i=0;i< mm.length;i++){
  if (parseInt(MM) == mm[i]){
   flag=true;
  }
 }
 if(flag){
 	if(parseInt(DD)>31){return true;}
 }else{
 	if(parseInt(DD)>30){return true;}
 }
 
 
 if(parseInt(MM)>12){return true;}
   return false;}