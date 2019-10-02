<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
Calendar.LANG("cn", "LANG", {

        fdow: 1,                // first day of week for this locale; 0 = Sunday, 1 = Monday, etc.

        goToday: "<jecs:locale key="Calendar.TT.PART_TODAY"/>",

        today: "<jecs:locale key="Calendar.TT.PART_TODAY"/>",         // appears in bottom bar

        wk: "<jecs:locale key="Calendar.TT.WK"/>",

        weekend: "0,6",         // 0 = Sunday, 1 = Monday, etc.

        AM: "AM",

        PM: "PM",

        mn : ["<jecs:locale key="Calendar.MN.January"/>",
			 "<jecs:locale key="Calendar.MN.February"/>",
			 "<jecs:locale key="Calendar.MN.March"/>",
			 "<jecs:locale key="Calendar.MN.April"/>",
			 "<jecs:locale key="Calendar.MN.May"/>",
			 "<jecs:locale key="Calendar.MN.June"/>",
			 "<jecs:locale key="Calendar.MN.July"/>",
			 "<jecs:locale key="Calendar.MN.August"/>",
			 "<jecs:locale key="Calendar.MN.September"/>",
			 "<jecs:locale key="Calendar.MN.October"/>",
			 "<jecs:locale key="Calendar.MN.November"/>",
			 "<jecs:locale key="Calendar.MN.December"/>"],

      smn : [ "<jecs:locale key="Calendar.MN.January"/>",
			 "<jecs:locale key="Calendar.MN.February"/>",
			 "<jecs:locale key="Calendar.MN.March"/>",
			 "<jecs:locale key="Calendar.MN.April"/>",
			 "<jecs:locale key="Calendar.MN.May"/>",
			 "<jecs:locale key="Calendar.MN.June"/>",
			 "<jecs:locale key="Calendar.MN.July"/>",
			 "<jecs:locale key="Calendar.MN.August"/>",
			 "<jecs:locale key="Calendar.MN.September"/>",
			 "<jecs:locale key="Calendar.MN.October"/>",
			 "<jecs:locale key="Calendar.MN.November"/>",
			 "<jecs:locale key="Calendar.MN.December"/>"],

        dn : [ "<jecs:locale key="Calendar.SDN.Sun"/>",
               "<jecs:locale key="Calendar.SDN.Mon"/>",
               "<jecs:locale key="Calendar.SDN.Tue"/>",
               "<jecs:locale key="Calendar.SDN.Wed"/>",
               "<jecs:locale key="Calendar.SDN.Thu"/>",
               "<jecs:locale key="Calendar.SDN.Fri"/>",
               "<jecs:locale key="Calendar.SDN.Sat"/>",
               "<jecs:locale key="Calendar.SDN.Sun"/>" ],

        sdn : [ "<jecs:locale key="Calendar.SDN.Sun"/>",
                "<jecs:locale key="Calendar.SDN.Mon"/>",
                "<jecs:locale key="Calendar.SDN.Tue"/>",
                "<jecs:locale key="Calendar.SDN.Wed"/>",
                "<jecs:locale key="Calendar.SDN.Thu"/>",
                "<jecs:locale key="Calendar.SDN.Fri"/>",
                "<jecs:locale key="Calendar.SDN.Sat"/>",
                "<jecs:locale key="Calendar.SDN.Sun"/>" ]

});
