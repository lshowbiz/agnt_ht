var PeerTip=Class.create();
PeerTip.prototype={
initialize:function(tid){
	this.tid=tid?tid:"Peer_JTip";
},
show:function(evt){
	clearTimeout(this.disposeTimer);
	var de = document.documentElement;
	var objlink=this.objlink=Event.element(evt);
	var w = self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth;
	var hasArea = w - this.getAbsoluteLeft(objlink);
	this.pretipWidth=objlink.getAttribute("JTipWidth");
	var tipTitle=objlink.getAttribute("JTipTitle");
	var ajaxRef=objlink.getAttribute("JTipAjax");
	var tipWidth=!this.pretipWidth?250:this.pretipWidth;
	if(!!ajaxRef){
		if($(this.tid)) $(this.tid).remove();
		new Insertion.Top($$('body')[0],'<div id="'+this.tid+'" class="Peer_JTip" style="display:none"></div>');
		$(this.tid).update('<div class="Peer_JT_loader"></div>');
		$(this.tid).setStyle({width:tipWidth});
		if(hasArea>(tipWidth*1)+75){//right side
			this.sidepos=0;
			this.setPosition();
		}
		else{//left side
			this.sidepos=1;
			this.setPosition();
		}
		$(this.tid).observe("mouseover",this.tipOver.bind(this));
		$(this.tid).observe("mouseout",this.tipOut.bind(this));
	var op={
		method:"get"
	}
	op.onSuccess=this.ajaxComplete.bind(this);
	new Ajax.Request(ajaxRef,op);
	}
},
setPosition:function(){//left is 1 and right is 0
	var	clickElementx;
	var clickElementy = this.getAbsoluteTop(this.objlink) - 3; //set y position
	if(this.sidepos==0){
		clickElementx= this.getAbsoluteLeft(this.objlink)+ this.objlink.getWidth()+11; //set x position
	}
	else{
		clickElementx = this.getAbsoluteLeft(this.objlink)-($(this.tid).getWidth()*1)-15;		
	}
	$(this.tid).setStyle({left: clickElementx+"px", top:clickElementy+"px"});
	$(this.tid).show();
},
ajaxComplete:function(r){
	$(this.tid).hide();
	$(this.tid).update(r.responseText);
	if(!this.pretipWidth){
		$(this.tid).setStyle({width:"auto"});
		this.setPosition();
	}
	else{
		$(this.tid).setStyle({overflow:"scroll"});
	}
	$(this.tid).show();
},
tipOver:function(){
	clearTimeout(this.disposeTimer);
},
tipOut:function(evt){
	if(!Position.within(this.tid,Event.pointerX(evt),Event.pointerY(evt))){
		this.close();
	}
},
close:function(){
	this.disposeTimer=setTimeout(this.dispose.bind(this),500);
},
dispose:function(){
	$(this.tid).remove();
},
getAbsoluteLeft:function(o) {
	var oLeft = o.offsetLeft;           // Get left position from the parent object
	var oParent;
	while(o.offsetParent!=null) {   // Parse the parent hierarchy up to the document element
		oParent = o.offsetParent;    // Get parent object reference
		oLeft += oParent.offsetLeft; // Add parent left position
		o = oParent;
	}
	return oLeft;
},
getAbsoluteTop:function(o) {
	var oTop = o.offsetTop;            // Get top position from the parent object
	var oParent;
	while(o.offsetParent!=null) { // Parse the parent hierarchy up to the document element
		oParent = o.offsetParent;  // Get parent object reference
		oTop += oParent.offsetTop; // Add parent top position
		o = oParent;
	}
	return oTop;
	}
}
