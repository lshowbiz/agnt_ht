     var bCancel = false; 

    function validateJpoMemberOrder(form) {                                                                   
        if (bCancel) 
      return true; 
        else 
       return validateRequired(form) && validateMaxLength(form) && validateEmail(form); 
   } 

    function required () { 
     this.aa = new Array("firstName", "收货人姓 为必填项。", new Function ("varName", "this.maxlength='100';  return this[varName];"));
     this.ab = new Array("lastName", "收货人名 为必填项。", new Function ("varName", "this.maxlength='100';  return this[varName];"));
     this.ac = new Array("province", "收货省 为必填项。", new Function ("varName", "this.maxlength='20';  return this[varName];"));
     this.ad = new Array("address", "收货地址 为必填项。", new Function ("varName", "this.maxlength='500';  return this[varName];"));
     this.ae = new Array("postalcode", "收货邮编 为必填项。", new Function ("varName", "this.maxlength='10';  return this[varName];"));
     this.af = new Array("phone", "联系电话 为必填项。", new Function ("varName", "this.maxlength='20';  return this[varName];"));
     this.ag = new Array("mobiletele", "jpoMemberOrder.mobiletele 为必填项。", new Function ("varName", " return this[varName];"));
    } 

    function maxlength () { 
     this.aa = new Array("firstName", "收货人姓 不能大于 100 个字符。", new Function ("varName", "this.maxlength='100';  return this[varName];"));
     this.ab = new Array("lastName", "收货人名 不能大于 100 个字符。", new Function ("varName", "this.maxlength='100';  return this[varName];"));
     this.ac = new Array("province", "收货省 不能大于 20 个字符。", new Function ("varName", "this.maxlength='20';  return this[varName];"));
     this.ad = new Array("city", "收货城市 不能大于 20 个字符。", new Function ("varName", "this.maxlength='20';  return this[varName];"));
     this.ae = new Array("address", "收货地址 不能大于 500 个字符。", new Function ("varName", "this.maxlength='500';  return this[varName];"));
     this.af = new Array("postalcode", "收货邮编 不能大于 10 个字符。", new Function ("varName", "this.maxlength='10';  return this[varName];"));
     this.ag = new Array("phone", "联系电话 不能大于 20 个字符。", new Function ("varName", "this.maxlength='20';  return this[varName];"));
     this.ah = new Array("email", "jpoMemberOrder.email 不能大于 100 个字符。", new Function ("varName", "this.maxlength='100';  return this[varName];"));
    } 

    function email () { 
     this.aa = new Array("email", "jpoMemberOrder.email 为无效邮件地址。", new Function ("varName", "this.maxlength='100';  return this[varName];"));
    }
