<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
// ** I18N

// Calendar ZH language
// Author: muziq, <muziq@sina.com>
// Encoding: GB2312 or GBK
// Distributed under the same terms as the calendar itself.

// full day names
Calendar._DN = new Array
("<jecs:locale key="Calendar.DN.Sunday"/>",
 "<jecs:locale key="Calendar.DN.Monday"/>",
 "<jecs:locale key="Calendar.DN.Tuesday"/>",
 "<jecs:locale key="Calendar.DN.Wednesday"/>",
 "<jecs:locale key="Calendar.DN.Thursday"/>",
 "<jecs:locale key="Calendar.DN.Friday"/>",
 "<jecs:locale key="Calendar.DN.Saturday"/>",
 "<jecs:locale key="Calendar.DN.Sunday"/>");

// Please note that the following array of short day names (and the same goes
// for short month names, _SMN) isn't absolutely necessary.  We give it here
// for exemplification on how one can customize the short day names, but if
// they are simply the first N letters of the full name you can simply say:
//
//   Calendar._SDN_len = N; // short day name length
//   Calendar._SMN_len = N; // short month name length
//
// If N = 3 then this is not needed either since we assume a value of 3 if not
// present, to be compatible with translation files that were written before
// this feature.

// short day names
Calendar._SDN = new Array
("<jecs:locale key="Calendar.SDN.Sun"/>",
 "<jecs:locale key="Calendar.SDN.Mon"/>",
 "<jecs:locale key="Calendar.SDN.Tue"/>",
 "<jecs:locale key="Calendar.SDN.Wed"/>",
 "<jecs:locale key="Calendar.SDN.Thu"/>",
 "<jecs:locale key="Calendar.SDN.Fri"/>",
 "<jecs:locale key="Calendar.SDN.Sat"/>",
 "<jecs:locale key="Calendar.SDN.Sun"/>");

// First day of the week. "0" means display Sunday first, "1" means display
// Monday first, etc.
Calendar._FD = 0;

// full month names
Calendar._MN = new Array
("<jecs:locale key="Calendar.MN.January"/>",
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
 "<jecs:locale key="Calendar.MN.December"/>");

// short month names
Calendar._SMN = new Array
("<jecs:locale key="Calendar.SMN.Jan"/>",
 "<jecs:locale key="Calendar.SMN.Feb"/>",
 "<jecs:locale key="Calendar.SMN.Mar"/>",
 "<jecs:locale key="Calendar.SMN.Apr"/>",
 "<jecs:locale key="Calendar.SMN.May"/>",
 "<jecs:locale key="Calendar.SMN.Jun"/>",
 "<jecs:locale key="Calendar.SMN.Jul"/>",
 "<jecs:locale key="Calendar.SMN.Aug"/>",
 "<jecs:locale key="Calendar.SMN.Sep"/>",
 "<jecs:locale key="Calendar.SMN.Oct"/>",
 "<jecs:locale key="Calendar.SMN.Nov"/>",
 "<jecs:locale key="Calendar.SMN.Dec"/>");

// tooltips
Calendar._TT = {};
Calendar._TT["INFO"] = "<jecs:locale key="Calendar.TT.INFO"/>";

Calendar._TT["ABOUT"] = "<jecs:locale key="Calendar.TT.ABOUT"/>";
Calendar._TT["ABOUT_TIME"] = "<jecs:locale key="Calendar.TT.ABOUT_TIME"/>";

Calendar._TT["PREV_YEAR"] = "<jecs:locale key="Calendar.TT.PREV_YEAR"/>";
Calendar._TT["PREV_MONTH"] = "<jecs:locale key="Calendar.TT.PREV_MONTH"/>";
Calendar._TT["GO_TODAY"] = "<jecs:locale key="Calendar.TT.GO_TODAY"/>";
Calendar._TT["NEXT_MONTH"] = "<jecs:locale key="Calendar.TT.NEXT_MONTH"/>";
Calendar._TT["NEXT_YEAR"] = "<jecs:locale key="Calendar.TT.NEXT_YEAR"/>";
Calendar._TT["SEL_DATE"] = "<jecs:locale key="Calendar.TT.SEL_DATE"/>";
Calendar._TT["DRAG_TO_MOVE"] = "<jecs:locale key="Calendar.TT.DRAG_TO_MOVE"/>";
Calendar._TT["PART_TODAY"] = "<jecs:locale key="Calendar.TT.PART_TODAY"/>";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT["DAY_FIRST"] = "<jecs:locale key="Calendar.TT.DAY_FIRST"/>";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT["WEEKEND"] = "0,6";

Calendar._TT["CLOSE"] = "<jecs:locale key="Calendar.TT.CLOSE"/>";
Calendar._TT["TODAY"] = "<jecs:locale key="Calendar.TT.TODAY"/>";
Calendar._TT["TIME_PART"] = "<jecs:locale key="Calendar.TT.TIME_PART"/>";

// date formats
Calendar._TT["DEF_DATE_FORMAT"] = "%Y-%m-%d";
Calendar._TT["TT_DATE_FORMAT"] = "<jecs:locale key="Calendar.TT.TT_DATE_FORMAT"/>";

Calendar._TT["WK"] = "<jecs:locale key="Calendar.TT.WK"/>";
Calendar._TT["TIME"] = "<jecs:locale key="Calendar.TT.TIME"/>:";
