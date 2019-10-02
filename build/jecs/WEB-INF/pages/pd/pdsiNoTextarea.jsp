<%@ include file="/common/taglibs.jsp"%>

<body>
    <div>
			<textarea cols='20' rows='8' name="siNoTextareav" id="siNoTextareav" cssClass="text medium" /><c:out value='${requestParaMap.siNoTextarea}'/></textarea>
		    </br>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <button type="button" class="btn btn_long" id="sinoButton"  style="width:auto" onclick="pdSiNoTextareav();">
		        <jecs:locale  key='memu.pd.batchPrint'/>
		    </button>
	</div>

<script>
  function pdSiNoTextareav(){
       var pdSiNoTextareav = document.getElementById("siNoTextareav").value;
        if(""==pdSiNoTextareav ||null ==pdSiNoTextareav){
              alert("<jecs:locale key='memu.pd.batchPrint.siNoTextareav'/>");
        }else{
             var a = pdSiNoTextareav.indexOf("LO");
             if(-1==a){
                  alert("<jecs:locale key='memu.pd.batchPrint.siNoTextareavno'/>");
                  document.getElementById("siNoTextareav").value="";
             }else{
		              var siNOs = pdSiNoTextareav.split("LO");
		              var selectStr = '';
		              for(var i=1;i<siNOs.length;i++){ 
							selectStr += "LO"+siNOs[i]+",";
				      }  
		               window.open("<c:url value='/pdOrderPrints.html'/>?strAction=printSendInfo&siNo="+selectStr);
		               this.close();
              }
        }
  }

</script>
</body>