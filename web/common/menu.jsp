<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="WEB-INF/classes/cssHorizontalMenu.vm" permissions="rolesAdapter">
<ul id="primary-nav" class="menuList">
    <li class="pad">&nbsp;</li>
    <c:if test="${empty pageContext.request.remoteUser}"><li><a href="<c:url value="/login.jsp"/>" class="current"><jecs:locale key="login.title"/></a></li></c:if>
    <menu:displayMenu name="MainMenu"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="FileUpload"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    
    <!--JmiMember-START-->
    <menu:displayMenu name="JmiMemberMenu"/>
    <!--JmiMember-END-->
    <!--JmiMemberUpgradeNote-START-->
    <menu:displayMenu name="JmiMemberUpgradeNoteMenu"/>
    <!--JmiMemberUpgradeNote-END-->
    <!--JfiBankbookBalance-START-->
    <menu:displayMenu name="JfiBankbookBalanceMenu"/>
    <!--JfiBankbookBalance-END-->
    <!--JfiBankbookJournal-START-->
    <menu:displayMenu name="JfiBankbookJournalMenu"/>
    <!--JfiBankbookJournal-END-->
    <!--JfiBankbookTemp-START-->
    <menu:displayMenu name="JfiBankbookTempMenu"/>
    <!--JfiBankbookTemp-END-->
    <!--JfiPayAdvice-START-->
    <menu:displayMenu name="JfiPayAdviceMenu"/>
    <!--JfiPayAdvice-END-->
    <!--JfiPayBank-START-->
    <menu:displayMenu name="JfiPayBankMenu"/>
    <!--JfiPayBank-END-->
    <!--JfiPayData-START-->
    <menu:displayMenu name="JfiPayDataMenu"/>
    <!--JfiPayData-END-->
    <!--JmiAddrBook-START-->
    <menu:displayMenu name="JmiAddrBookMenu"/>
    <!--JmiAddrBook-END-->
    <!--JmiTreeIndex-START-->
    <menu:displayMenu name="JmiTreeIndexMenu"/>
    <!--JmiTreeIndex-END-->
    <!--JmiLinkRef-START-->
    <menu:displayMenu name="JmiLinkRefMenu"/>
    <!--JmiLinkRef-END-->
    <!--JmiRecommendRef-START-->
    <menu:displayMenu name="JmiRecommendRefMenu"/>
    <!--JmiRecommendRef-END-->
    <!--Jfi99billLog-START-->
    <menu:displayMenu name="Jfi99billLogMenu"/>
    <!--Jfi99billLog-END-->
    <!--Jfi99billTemp-START-->
    <menu:displayMenu name="Jfi99billTempMenu"/>
    <!--Jfi99billTemp-END-->
    <!--PdWarehouse-START-->
    <menu:displayMenu name="PdWarehouseMenu"/>
    <!--PdWarehouse-END-->
    <!--PdWarehouseStockTrace-START-->
    <menu:displayMenu name="PdWarehouseStockTraceMenu"/>
    <!--PdWarehouseStockTrace-END-->
    <!--PdWarehouseStock-START-->
    <menu:displayMenu name="PdWarehouseStockMenu"/>
    <!--PdWarehouseStock-END-->
    <!--PdSendInfo-START-->
    <menu:displayMenu name="PdSendInfoMenu"/>
    <!--PdSendInfo-END-->
    <!--PdSendInfoDetail-START-->
    <menu:displayMenu name="PdSendInfoDetailMenu"/>
    <!--PdSendInfoDetail-END-->
    <!--PdShipmentsDetail-START-->
    <menu:displayMenu name="PdShipmentsDetailMenu"/>
    <!--PdShipmentsDetail-END-->
    <!--PdWarehouseUser-START-->
    <menu:displayMenu name="PdWarehouseUserMenu"/>
    <!--PdWarehouseUser-END-->
    <!--PdWarehouseArea-START-->
    <menu:displayMenu name="PdWarehouseAreaMenu"/>
    <!--PdWarehouseArea-END-->
    <!--JpoMemberOrder-START-->
    <menu:displayMenu name="JpoMemberOrderMenu"/>
    <!--JpoMemberOrder-END-->
    <!--JpoMemberOrderFee-START-->
    <menu:displayMenu name="JpoMemberOrderFeeMenu"/>
    <!--JpoMemberOrderFee-END-->
    <!--JpoMemberOrderList-START-->
    <menu:displayMenu name="JpoMemberOrderListMenu"/>
    <!--JpoMemberOrderList-END-->
    <!--JprRefund-START-->
    <menu:displayMenu name="JprRefundMenu"/>
    <!--JprRefund-END-->
    <!--JprRefundList-START-->
    <menu:displayMenu name="JprRefundListMenu"/>
    <!--JprRefundList-END-->
    <!--BdOutwardBank-START-->
    <menu:displayMenu name="BdOutwardBankMenu"/>
    <!--BdOutwardBank-END-->
    <!--JbdSendRecord-START-->
    <menu:displayMenu name="JbdSendRecordMenu"/>
    <!--JbdSendRecord-END-->
    <!--JbdSendRecordHist-START-->
    <menu:displayMenu name="JbdSendRecordHistMenu"/>
    <!--JbdSendRecordHist-END-->
    <!--JbdBonusBalance-START-->
    <menu:displayMenu name="JbdBonusBalanceMenu"/>
    <!--JbdBonusBalance-END-->
    <!--PdEnterWarehouse-START-->
    <menu:displayMenu name="PdEnterWarehouseMenu"/>
    <!--PdEnterWarehouse-END-->
    <!--PdEnterWarehouseDetail-START-->
    <menu:displayMenu name="PdEnterWarehouseDetailMenu"/>
    <!--PdEnterWarehouseDetail-END-->
    <!--PdOutWarehouse-START-->
    <menu:displayMenu name="PdOutWarehouseMenu"/>
    <!--PdOutWarehouse-END-->
    <!--PdOutWarehouseDetail-START-->
    <menu:displayMenu name="PdOutWarehouseDetailMenu"/>
    <!--PdOutWarehouseDetail-END-->
    
    <!--PdReturnPurchase-START-->
    <menu:displayMenu name="PdReturnPurchaseMenu"/>
    <!--PdReturnPurchase-END-->
    <!--PdReturnPurchaseDetail-START-->
    <menu:displayMenu name="PdReturnPurchaseDetailMenu"/>
    <!--PdReturnPurchaseDetail-END-->
    <!--PdAdjustStock-START-->
    <menu:displayMenu name="PdAdjustStockMenu"/>
    <!--PdAdjustStock-END-->
    <!--PdAdjustStockDetail-START-->
    <menu:displayMenu name="PdAdjustStockDetailMenu"/>
    <!--PdAdjustStockDetail-END-->
    <!--PdFlitWarehouse-START-->
    <menu:displayMenu name="PdFlitWarehouseMenu"/>
    <!--PdFlitWarehouse-END-->
    <!--PdFlitWarehouseDetail-START-->
    <menu:displayMenu name="PdFlitWarehouseDetailMenu"/>
    <!--PdFlitWarehouseDetail-END-->
    <!--PdStatusExcStock-START-->
    <menu:displayMenu name="PdStatusExcStockMenu"/>
    <!--PdStatusExcStock-END-->
    <!--PdStatusExcStockDetail-START-->
    <menu:displayMenu name="PdStatusExcStockDetailMenu"/>
    <!--PdStatusExcStockDetail-END-->
    <!--BdPeriod-START-->
    <menu:displayMenu name="BdPeriodMenu"/>
    <!--BdPeriod-END-->
    <!--JbdSellCalcSubHist-START-->
    <menu:displayMenu name="JbdSellCalcSubHistMenu"/>
    <!--JbdSellCalcSubHist-END-->
    <!--JbdMemberLinkCalcHist-START-->
    <menu:displayMenu name="JbdMemberLinkCalcHistMenu"/>
    <!--JbdMemberLinkCalcHist-END-->
    <!--AlDistrict-START-->
    <menu:displayMenu name="AlDistrictMenu"/>
    <!--AlDistrict-END-->
    <!--AlCity-START-->
    <menu:displayMenu name="AlCityMenu"/>
    <!--AlCity-END-->
    <!--JbdUserCardList-START-->
    <menu:displayMenu name="JbdUserCardListMenu"/>
    <!--JbdUserCardList-END-->
    <!--JpoShippingFee-START-->
    <menu:displayMenu name="JpoShippingFeeMenu"/>
    <!--JpoShippingFee-END-->
    <!--JbdDayBounsCalc-START-->
    <menu:displayMenu name="JbdDayBounsCalcMenu"/>
    <!--JbdDayBounsCalc-END-->
    <!--JbdDayBounsCalcHist-START-->
    <menu:displayMenu name="JbdDayBounsCalcHistMenu"/>
    <!--JbdDayBounsCalcHist-END-->
    
    <!--JbdManuallyAdjustStar-START-->
    <menu:displayMenu name="JbdManuallyAdjustStarMenu"/>
    <!--JbdManuallyAdjustStar-END-->
    <!--JbdVentureLeaderSubHist-START-->
    <menu:displayMenu name="JbdVentureLeaderSubHistMenu"/>
    <!--JbdVentureLeaderSubHist-END-->
    <!--JbdSellCalcSubDetailHist-START-->
    <menu:displayMenu name="JbdSellCalcSubDetailHistMenu"/>
    <!--JbdSellCalcSubDetailHist-END-->
    <!--AmMessagePermit-START-->
    <menu:displayMenu name="AmMessagePermitMenu"/>
    <!--AmMessagePermit-END-->
    <!--BillAccount-START-->
    <menu:displayMenu name="BillAccountMenu"/>
    <!--BillAccount-END-->
    <!--JpoCounterOrder-START-->
    <menu:displayMenu name="JpoCounterOrderMenu"/>
    <!--JpoCounterOrder-END-->
    <!--JpoCounterOrderDetail-START-->
    <menu:displayMenu name="JpoCounterOrderDetailMenu"/>
    <!--JpoCounterOrderDetail-END-->
    <!--JamMsnType-START-->
    <menu:displayMenu name="JamMsnTypeMenu"/>
    <!--JamMsnType-END-->
    <!--JamMsnDetail-START-->
    <menu:displayMenu name="JamMsnDetailMenu"/>
    <!--JamMsnDetail-END-->
    <!--SysLoginLog-START-->
    <menu:displayMenu name="SysLoginLogMenu"/>
    <!--SysLoginLog-END-->
    <!--PdCombinationOrder-START-->
    <menu:displayMenu name="PdCombinationOrderMenu"/>
    <!--PdCombinationOrder-END-->
    <!--PdCombinationDetail-START-->
    <menu:displayMenu name="PdCombinationDetailMenu"/>
    <!--PdCombinationDetail-END-->
    <!--JbdCaculateLog-START-->
    <menu:displayMenu name="JbdCaculateLogMenu"/>
    <!--JbdCaculateLog-END-->

    <!--SmsSendLog-START-->
    <menu:displayMenu name="SmsSendLogMenu"/>
    <!--SmsSendLog-END-->

    <!--PdCheckstockOrder-START-->
    <menu:displayMenu name="PdCheckstockOrderMenu"/>
    <!--PdCheckstockOrder-END-->
    <!--PdCheckstockOrderDetail-START-->
    <menu:displayMenu name="PdCheckstockOrderDetailMenu"/>
    <!--PdCheckstockOrderDetail-END-->

    <!--JamMsnModule-START-->
    <menu:displayMenu name="JamMsnModuleMenu"/>
    <!--JamMsnModule-END-->
    <!--VtVote-START-->
    <menu:displayMenu name="VtVoteMenu"/>
    <!--VtVote-END-->
    <!--VtVoteDetail-START-->
    <menu:displayMenu name="VtVoteDetailMenu"/>
    <!--VtVoteDetail-END-->
    <!--VtVoteNote-START-->
    <menu:displayMenu name="VtVoteNoteMenu"/>
    <!--VtVoteNote-END-->
    <!--VtVotePow-START-->
    <menu:displayMenu name="VtVotePowMenu"/>
    <!--VtVotePow-END-->
    <!--JbdUserCompanyCode-START-->
    <menu:displayMenu name="JbdUserCompanyCodeMenu"/>
    <!--JbdUserCompanyCode-END-->
    
    
    
    <!--JmiMemberCompanyNote-START-->
    <menu:displayMenu name="JmiMemberCompanyNoteMenu"/>
    <!--JmiMemberCompanyNote-END-->
    
    <!--Jfi99billPosLog-START-->
    <menu:displayMenu name="Jfi99billPosLogMenu"/>
    <!--Jfi99billPosLog-END-->
    <!--Jfi99billPosSendLog-START-->
    <menu:displayMenu name="Jfi99billPosSendLogMenu"/>
    <!--Jfi99billPosSendLog-END-->
    
    <!--JpmCardSeq-START-->
    <menu:displayMenu name="JpmCardSeqMenu"/>
    <!--JpmCardSeq-END-->
    <!--JfiSunMemberOrderList-START-->
    <menu:displayMenu name="JfiSunMemberOrderListMenu"/>
    <!--JfiSunMemberOrderList-END-->
    <!--JfiSunMemberOrderFee-START-->
    <menu:displayMenu name="JfiSunMemberOrderFeeMenu"/>
    <!--JfiSunMemberOrderFee-END-->
    <!--JfiSunMemberOrder-START-->
    <menu:displayMenu name="JfiSunMemberOrderMenu"/>
    <!--JfiSunMemberOrder-END-->
    <!--JfiSunDistribution-START-->
    <menu:displayMenu name="JfiSunDistributionMenu"/>
    <!--JfiSunDistribution-END-->
    <!--JfiSunConfigKey-START-->
    <menu:displayMenu name="JfiSunConfigKeyMenu"/>
    <!--JfiSunConfigKey-END-->
    <!--JbdTaiwanTravelPoint-START-->
    <menu:displayMenu name="JbdTaiwanTravelPointMenu"/>
    <!--JbdTaiwanTravelPoint-END-->
    <!--JmiTaiwanTravel-START-->
    <menu:displayMenu name="JmiTaiwanTravelMenu"/>
    <!--JmiTaiwanTravel-END-->
    <!--PdExchangeOrder-START-->
    <menu:displayMenu name="PdExchangeOrderMenu"/>
    <!--PdExchangeOrder-END-->
    <!--PdExchangeOrderBack-START-->
    <menu:displayMenu name="PdExchangeOrderBackMenu"/>
    <!--PdExchangeOrderBack-END-->
    <!--PdExchangeOrderDetail-START-->
    <menu:displayMenu name="PdExchangeOrderDetailMenu"/>
    <!--PdExchangeOrderDetail-END-->
    <!--JmiSubStore-START-->
    <menu:displayMenu name="JmiSubStoreMenu"/>
    <!--JmiSubStore-END-->
    <!--JpoAutoShip-START-->
    <menu:displayMenu name="JpoAutoShipMenu"/>
    <!--JpoAutoShip-END-->
    <!--JbdUserValidList-START-->
    <menu:displayMenu name="JbdUserValidListMenu"/>
    <!--JbdUserValidList-END-->
    <!--JalTown-START-->
    <menu:displayMenu name="JalTownMenu"/>
    <!--JalTown-END-->
    <!--JmiStore-START-->
    <menu:displayMenu name="JmiStoreMenu"/>
    <!--JmiStore-END-->
    <!--FiCoinLog-START-->
    <menu:displayMenu name="FiCoinLogMenu"/>
    <!--FiCoinLog-END-->
    <!--JfiAlipayLog-START-->
    <menu:displayMenu name="JfiAlipayLogMenu"/>
    <!--JfiAlipayLog-END-->
    <!--JamDownload-START-->
    <menu:displayMenu name="JamDownloadMenu"/>
    <!--JamDownload-END-->
    <!--JmiBlacklist-START-->
    <menu:displayMenu name="JmiBlacklistMenu"/>
    <!--JmiBlacklist-END-->
    <!--JfiHiTrustLog-START-->
    <menu:displayMenu name="JfiHiTrustLogMenu"/>
    <!--JfiHiTrustLog-END-->
    
    <!--JbdManuallyAdjustAlgebra-START-->
    <menu:displayMenu name="JbdManuallyAdjustAlgebraMenu"/>
    <!--JbdManuallyAdjustAlgebra-END-->
    <!--JpoMemberOrderTask-START-->
    <menu:displayMenu name="JpoMemberOrderTaskMenu"/>
    <!--JpoMemberOrderTask-END-->
    <!--JpoMemberOrderListTask-START-->
    <menu:displayMenu name="JpoMemberOrderListTaskMenu"/>
    <!--JpoMemberOrderListTask-END-->
    <!--JamPromotion-START-->
    <menu:displayMenu name="JamPromotionMenu"/>
    <!--JamPromotion-END-->
    <!--JbdTravelPoint-START-->
    <menu:displayMenu name="JbdTravelPointMenu"/>
    <!--JbdTravelPoint-END-->
    <!--JbdTravelPointDetail-START-->
    <menu:displayMenu name="JbdTravelPointDetailMenu"/>
    <!--JbdTravelPointDetail-END-->
    <!--JbdTravelPointLog-START-->
    <menu:displayMenu name="JbdTravelPointLogMenu"/>
    <!--JbdTravelPointLog-END-->
    <!--JbdSummaryList-START-->
    <menu:displayMenu name="JbdSummaryListMenu"/>
    <!--JbdSummaryList-END-->
    <!--JbdVentureFundList-START-->
    <menu:displayMenu name="JbdVentureFundListMenu"/>
    <!--JbdVentureFundList-END-->
    <!--JfiUsCreditCardLog-START-->
    <menu:displayMenu name="JfiUsCreditCardLogMenu"/>
    <!--JfiUsCreditCardLog-END-->
    
    
    
    <!--JfiSunOrderList-START-->
    <menu:displayMenu name="JfiSunOrderListMenu"/>
    <!--JfiSunOrderList-END-->
    <!--JfiSunOrder-START-->
    <menu:displayMenu name="JfiSunOrderMenu"/>
    <!--JfiSunOrder-END-->
    <!--JfiSunJmiMember-START-->
    <menu:displayMenu name="JfiSunJmiMemberMenu"/>
    <!--JfiSunJmiMember-END-->
    <!--JalPostalcode-START-->
    <menu:displayMenu name="JalPostalcodeMenu"/>
    <!--JalPostalcode-END-->
    <!--SunDistShip-START-->
    <menu:displayMenu name="SunDistShipMenu"/>
    <!--SunDistShip-END-->
    <!--SunProductInfo-START-->
    <menu:displayMenu name="SunProductInfoMenu"/>
    <!--SunProductInfo-END-->
    <!--JbdSendNote-START-->
    <menu:displayMenu name="JbdSendNoteMenu"/>
    <!--JbdSendNote-END-->
    <!--JbdNetworkList-START-->
    <menu:displayMenu name="JbdNetworkListMenu"/>
    <!--JbdNetworkList-END-->
    <!--JmiCustomerLevelNote-START-->
    <menu:displayMenu name="JmiCustomerLevelNoteMenu"/>
    <!--JmiCustomerLevelNote-END-->
    <!--JfiCreditCardLog-START-->
    <menu:displayMenu name="JfiCreditCardLogMenu"/>
    <!--JfiCreditCardLog-END-->
    <!--FiBcoinJournal-START-->
    <menu:displayMenu name="FiBcoinJournalMenu"/>
    <!--FiBcoinJournal-END-->
    <!--FiBcoinBalance-START-->
    <menu:displayMenu name="FiBcoinBalanceMenu"/>
    <!--FiBcoinBalance-END-->
    <!--FiCcoinJournal-START-->
    <menu:displayMenu name="FiCcoinJournalMenu"/>
    <!--FiCcoinJournal-END-->
    <!--FiCcoinBalance-START-->
    <menu:displayMenu name="FiCcoinBalanceMenu"/>
    <!--FiCcoinBalance-END-->
    <!--PdShipStrategy-START-->
    <menu:displayMenu name="PdShipStrategyMenu"/>
    <!--PdShipStrategy-END-->
    <!--PdShipStrategyDetail-START-->
    <menu:displayMenu name="PdShipStrategyDetailMenu"/>
    <!--PdShipStrategyDetail-END-->
    <!--FiBankbookBalance-START-->
    <menu:displayMenu name="FiBankbookBalanceMenu"/>
    <!--FiBankbookBalance-END-->
    <!--FiBankbookJournal-START-->
    <menu:displayMenu name="FiBankbookJournalMenu"/>
    <!--FiBankbookJournal-END-->
    <!--FiBankbookTemp-START-->
    <menu:displayMenu name="FiBankbookTempMenu"/>
    <!--FiBankbookTemp-END-->
    <!--JbdTravelPoint2012-START-->
    <menu:displayMenu name="JbdTravelPoint2012Menu"/>
    <!--JbdTravelPoint2012-END-->
    <!--JbdTravelPointDetail2012-START-->
    <menu:displayMenu name="JbdTravelPointDetail2012Menu"/>
    <!--JbdTravelPointDetail2012-END-->
    <!--JbdTravelPointLog2012-START-->
    <menu:displayMenu name="JbdTravelPointLog2012Menu"/>
    <!--JbdTravelPointLog2012-END-->
    <!--JfiTenpayLog-START-->
    <menu:displayMenu name="JfiTenpayLogMenu"/>
    <!--JfiTenpayLog-END-->
    <!--PdShipFee-START-->
    <menu:displayMenu name="PdShipFeeMenu"/>
    <!--PdShipFee-END-->
    <!--Jfi99billmsLog-START-->
    <menu:displayMenu name="Jfi99billmsLogMenu"/>
    <!--Jfi99billmsLog-END-->
    <!--PdWarehouseFrozenBatch-START-->
    <menu:displayMenu name="PdWarehouseFrozenBatchMenu"/>
    <!--PdWarehouseFrozenBatch-END-->
    <!--PdWarehouseFrozenDetail-START-->
    <menu:displayMenu name="PdWarehouseFrozenDetailMenu"/>
    <!--PdWarehouseFrozenDetail-END-->
    <!--AmNew-START-->
    <menu:displayMenu name="AmNewMenu"/>
    <!--AmNew-END-->
    <!--JbdGradeNote-START-->
    <menu:displayMenu name="JbdGradeNoteMenu"/>
    <!--JbdGradeNote-END-->
    <!--JfiPosImport-START-->
    <menu:displayMenu name="JfiPosImportMenu"/>
    <!--JfiPosImport-END-->
    <!--JbdTravelPoint2013-START-->
    <menu:displayMenu name="JbdTravelPoint2013Menu"/>
    <!--JbdTravelPoint2013-END-->
    <!--JbdTravelPointLog2013-START-->
    <menu:displayMenu name="JbdTravelPointLog2013Menu"/>
    <!--JbdTravelPointLog2013-END-->
    <!--JbdTravelPointDetail2013-START-->
    <menu:displayMenu name="JbdTravelPointDetail2013Menu"/>
    <!--JbdTravelPointDetail2013-END-->
    <!--JbdSpecialStar-START-->
    <menu:displayMenu name="JbdSpecialStarMenu"/>
    <!--JbdSpecialStar-END-->
    <!--JsysResource-START-->
    <menu:displayMenu name="JsysResourceMenu"/>
    <!--JsysResource-END-->
    
    
    <!--JsysResRole-START-->
    <menu:displayMenu name="JsysResRoleMenu"/>
    <!--JsysResRole-END-->
    <!--FiTransferAccount-START-->
    <menu:displayMenu name="FiTransferAccountMenu"/>
    <!--FiTransferAccount-END-->

	
    
    <!--JmiMemberTeam-START-->
    <menu:displayMenu name="JmiMemberTeamMenu"/>
    <!--JmiMemberTeam-END-->
    <!--JpmProductSaleNew-START-->
    <menu:displayMenu name="JpmProductSaleNewMenu"/>
    <!--JpmProductSaleNew-END-->
    <!--JpmProductSaleImage-START-->
    <menu:displayMenu name="JpmProductSaleImageMenu"/>
    <!--JpmProductSaleImage-END-->
    <!--JpmProductSaleTeamType-START-->
    <menu:displayMenu name="JpmProductSaleTeamTypeMenu"/>
    <!--JpmProductSaleTeamType-END-->
    <!--JpmProductSaleRelated-START-->
    <menu:displayMenu name="JpmProductSaleRelatedMenu"/>
    <!--JpmProductSaleRelated-END-->
    <!--JpmSalePromoter-START-->
    <menu:displayMenu name="JpmSalePromoterMenu"/>
    <!--JpmSalePromoter-END-->
    <!--JpmSalepromoterPro-START-->
    <menu:displayMenu name="JpmSalepromoterProMenu"/>
    <!--JpmSalepromoterPro-END-->
    <!--JpmProductWineTemplate-START-->
    <menu:displayMenu name="JpmProductWineTemplateMenu"/>
    <!--JpmProductWineTemplate-END-->
    <!--JpmProductWineTemplateSub-START-->
    <menu:displayMenu name="JpmProductWineTemplateSubMenu"/>
    <!--JpmProductWineTemplateSub-END-->
    <!--JpmWineTemplatePicture-START-->
    <menu:displayMenu name="JpmWineTemplatePictureMenu"/>
    <!--JpmWineTemplatePicture-END-->
    
    
    
    <!--JmiZcwMember-START-->
    <menu:displayMenu name="JmiZcwMemberMenu"/>
    <!--JmiZcwMember-END-->
    <!--FiSecurityDeposit-START-->
    <menu:displayMenu name="FiSecurityDepositMenu"/>
    <!--FiSecurityDeposit-END-->
    <!--FiSecurityDepositJournal-START-->
    <menu:displayMenu name="FiSecurityDepositJournalMenu"/>
    <!--FiSecurityDepositJournal-END-->
    <!--JpmConfigDetailed-START-->
    <menu:displayMenu name="JpmConfigDetailedMenu"/>
    <!--JpmConfigDetailed-END-->
    <!--JpmMemberConfig-START-->
    <menu:displayMenu name="JpmMemberConfigMenu"/>
    <!--JpmMemberConfig-END-->
    <!--JpmConfigSpecDetailed-START-->
    <menu:displayMenu name="JpmConfigSpecDetailedMenu"/>
    <!--JpmConfigSpecDetailed-END-->
    <!--JpmSendConsignment-START-->
    <menu:displayMenu name="JpmSendConsignmentMenu"/>
    <!--JpmSendConsignment-END-->
    
    <!--FiCoinConfig-START-->
    <menu:displayMenu name="FiCoinConfigMenu"/>
    <!--FiCoinConfig-END-->
    
    <!--FiBillAccountRelation-START-->
    <menu:displayMenu name="FiBillAccountRelationMenu"/>
    <!--FiBillAccountRelation-END-->
    
    
    
    <!--FiGetbillaccountLog-START-->
    <menu:displayMenu name="FiGetbillaccountLogMenu"/>
    <!--FiGetbillaccountLog-END-->
    
    
    
    
    <!--FiCommonAddr-START-->
    <menu:displayMenu name="FiCommonAddrMenu"/>
    <!--FiCommonAddr-END-->
    <!--FiFundbookBalance-START-->
    <menu:displayMenu name="FiFundbookBalanceMenu"/>
    <!--FiFundbookBalance-END-->
    <!--FiFundbookJournal-START-->
    <menu:displayMenu name="FiFundbookJournalMenu"/>
    <!--FiFundbookJournal-END-->
    <!--FiFundbookTemp-START-->
    <menu:displayMenu name="FiFundbookTempMenu"/>
    <!--FiFundbookTemp-END-->
    <!--FiTransferFundbook-START-->
    <menu:displayMenu name="FiTransferFundbookMenu"/>
    <!--FiTransferFundbook-END-->
    <!--FiBillAccount-START-->
    <menu:displayMenu name="FiBillAccountMenu"/>
    <!--FiBillAccount-END-->
    <!--FiBillAccountWarning-START-->
    <menu:displayMenu name="FiBillAccountWarningMenu"/>
    <!--FiBillAccountWarning-END-->
    <!--FiMovieOrder-START-->
    <menu:displayMenu name="FiMovieOrderMenu"/>
    <!--FiMovieOrder-END-->
    
    <!--JpoProductNumLimit-START-->
    <menu:displayMenu name="JpoProductNumLimitMenu"/>
    <!--JpoProductNumLimit-END-->
    <!--JfiPayLog-START-->
    <menu:displayMenu name="JfiPayLogMenu"/>
    <!--JfiPayLog-END-->
    <!--FiAvailableInvoice-START-->
    <menu:displayMenu name="FiAvailableInvoiceMenu"/>
    <!--FiAvailableInvoice-END-->
    
    <!--FiInvoiceBalance-START-->
    <menu:displayMenu name="FiInvoiceBalanceMenu"/>
    <!--FiInvoiceBalance-END-->
    <!--FiInvoiceChange-START-->
    <menu:displayMenu name="FiInvoiceChangeMenu"/>
    <!--FiInvoiceChange-END-->
    <!--JmiMemberLog-START-->
    <menu:displayMenu name="JmiMemberLogMenu"/>
    <!--JmiMemberLog-END-->
    <!--JmiPrizeTourism-START-->
    <menu:displayMenu name="JmiPrizeTourismMenu"/>
    <!--JmiPrizeTourism-END-->
    <!--JbdTravelPointAll-START-->
    <menu:displayMenu name="JbdTravelPointAllMenu"/>
    <!--JbdTravelPointAll-END-->
    <!--JbdTravelPointLogAll-START-->
    <menu:displayMenu name="JbdTravelPointLogAllMenu"/>
    <!--JbdTravelPointLogAll-END-->
    <!--JfiBusinessNum-START-->
    <menu:displayMenu name="JfiBusinessNumMenu"/>
    <!--JfiBusinessNum-END-->
    <!--JfiQuota-START-->
    <menu:displayMenu name="JfiQuotaMenu"/>
    <!--JfiQuota-END-->
    <!--JfiAmountDetail-START-->
    <menu:displayMenu name="JfiAmountDetailMenu"/>
    <!--JfiAmountDetail-END-->
    
    <!--JpmCombinationRelated-START-->
    <menu:displayMenu name="JpmCombinationRelatedMenu"/>
    <!--JpmCombinationRelated-END-->
    
    <!--PdNotChangeProduct-START-->
    <menu:displayMenu name="PdNotChangeProductMenu"/>
    <!--PdNotChangeProduct-END-->
    <!--JmiAssure-START-->
    <menu:displayMenu name="JmiAssureMenu"/>
    <!--JmiAssure-END-->
    <!--JpmCouponInfo-START-->
    <menu:displayMenu name="JpmCouponInfoMenu"/>
    <!--JpmCouponInfo-END-->
    <!--JpmCouponRelationship-START-->
    <menu:displayMenu name="JpmCouponRelationshipMenu"/>
    <!--JpmCouponRelationship-END-->
    <!--JpoUserCoupon-START-->
    <menu:displayMenu name="JpoUserCouponMenu"/>
    <!--JpoUserCoupon-END-->
    <!--JpoCouponRelationship-START-->
    <menu:displayMenu name="JpoCouponRelationshipMenu"/>
    <!--JpoCouponRelationship-END-->
    <!--JpoMemberNyc-START-->
    <menu:displayMenu name="JpoMemberNycMenu"/>
    <!--JpoMemberNyc-END-->
    <!--JpoMemberNycLog-START-->
    <menu:displayMenu name="JpoMemberNycLogMenu"/>
    <!--JpoMemberNycLog-END-->
    <!--JpoMemberNycQualify-START-->
    <menu:displayMenu name="JpoMemberNycQualifyMenu"/>
    <!--JpoMemberNycQualify-END-->
</ul>

























































</menu:useMenuDisplayer>