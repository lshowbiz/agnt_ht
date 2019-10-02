<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" %>	  
		 <div class="mlma">
              
              <div class="mnel" id="webDirectWyPay"><strong><a href="webDirectWyPay_one.jsp?pts=<%=(new java.util.Date()).getTime()%>" title="">商户向平台下订单(网银直连)</a></strong></div>             
              <div class="mnel" id="merRefund"><strong><a href="merRefund_one.jsp?pts=<%=(new java.util.Date()).getTime()%>" title="">商户退费服务</a></strong></div>
              <div class="mnel" id="checkOrderState"><strong><a href="checkOrderState_one.jsp?pts=<%=(new java.util.Date()).getTime()%>" title="">查询交易记录</a></strong></div>
              <div class="mnel" id="downloadTransBill"><strong><a href="downloadTransBill_one.jsp?pts=<%=(new java.util.Date()).getTime()%>" title="">获取交易数据对账文件</a></strong></div>
              <div class="mnel" id="downloadSettleBill"><strong><a href="downloadSettleBill_one.jsp?pts=<%=(new java.util.Date()).getTime()%>" title="">获取清算数据对账文件</a></strong></div>
			  <div class="mnel" id="paymentnotify"><strong><a href="paymentNotify_one.jsp?pts=<%=(new java.util.Date()).getTime()%>" title="">商户结果通知</a></strong></div>         

</div>