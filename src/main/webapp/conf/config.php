<?php session_start();
//set version chage dev
define("__VPRODUCT_","mcot_erp-2562-01-05-00-09");	// 
// บริษัท
define("COMPANY_NAME", ".:: PPP SOLUTION ::.");

define("DB_SERVER","PPP\SQL2017");//Panda PPP
define ( "DB_NAME" , "MCOT_ERP003" ) ;
define("DB_USER","sa");
define("DB_PASS","nkl");
define("DB_CHARSET","UTF-8"); 

	// สถานะการใช้งาน
	define("STATUS_ENABLE",		1); // ใช้งาน
	define("STATUS_DISABLE",	2); // ไม่ใช้งาน
	$arr_status = array(
		0				=>"เลือกทั้งหมด",
		STATUS_ENABLE	=>"ใช้งาน",
		STATUS_DISABLE	=>"ไม่ใช้งาน"
	);
	
	// สถานะการใช้งาน
	define("DC_AREA_LOCATION_BRANCH",		1); // สาขา
	define("DC_AREA_LOCATION_HEADQUARTER",	2); // สำนักงานใหญ๋
 
	$arr_dc_branch_type = array(
		0								=>"เลือกทั้งหมด",
		DC_AREA_LOCATION_BRANCH			=>"สาขา",
		DC_AREA_LOCATION_HEADQUARTER	=>"สำนักงานใหญ่"
	);
  
	// สถานะการแสดงข้อมูลที่ต้องการ( i_delete )
	define("DELETE_TRUE",		1); // ถูกลบระบบไม่สามารถมองเห็นแต่มีอยู่ในฐานข้อมูล
	define("DELETE_FALSE",		2); // ใช้งานได้ตามปกติ
  
 	define("I_LAST", 1); // เป็นข้อมูลระดับล่างสุด
 	
 	// ประเภทหน่วยงานตามที่ตั้ง 
 	define("DC_COST_REGION_CENTRAL",	1); // ส่วนกลาง
 	define("DC_COST_REGION_PROVINCIAL",	2); // ส่วนภูมิภาค
 	// dc_cost.i_locate  ประเภทหน่วยงานตามที่ตั้ง
 	$arr_dc_cost_region = array(
		DC_COST_REGION_CENTRAL		=>"ส่วนกลาง",
		DC_COST_REGION_PROVINCIAL	=>"ส่วนภูมิภาค"
 	);
 	
 	//ระดับหน่วยงาน
 	define("DC_COST_RANK_OTHER",		1); // อื่นๆ
 	define("DC_COST_RANK_INSTITUTE",	2); // สำนัก
 	define("DC_COST_RANK_PARTY",		3); // ฝ่าย
 	define("DC_COST_RANK_CENTRE",		4); // ส่วนภูมิภาค
 	define("DC_COST_RANK_STATION",		5); // คลื่น/สถานี
 	define("DC_COST_RANK_CT",			6); // CT
 	// dc_cost.i_rank  ระดับหน่วยงาน
 	$arr_dc_cost_rank = array(0=>"ไม่ระบุ"
 							,DC_COST_RANK_OTHER=>"อื่นๆ"
 							,DC_COST_RANK_CT=>"CT"
 							,DC_COST_RANK_INSTITUTE=>"สำนัก"
 							,DC_COST_RANK_PARTY=>"ฝ่าย"
 							,DC_COST_RANK_CENTRE=>"ศูนย์ภาค"
 							,DC_COST_RANK_STATION=>"คลื่น/สถานี");
 	
 	//ประเภทหน่วยงานเพื่อการจัดกลุ่มข้อมูล 
 	define("DC_COST_IS_TV_TV",		1); // โทรทัศน์
 	define("DC_COST_IS_TV_RADIO",	2); // วิทยุ
 	define("DC_COST_IS_TV_MARKET",	3); // การตลาด
 	define("DC_COST_IS_TV_FINANCE",	4); // การเงิน
 	define("DC_COST_IS_TV_UNKNOWN",	5); // ไม่ระบุ
 	define("DC_COST_IS_TV_SUPPORT",	6); // สนับสนุน
 	// dc_cost.i_is_tv  ประเภทหน่วยงานเพื่อการจัดกลุ่มข้อมูล 
 	$arr_dc_cost_rank = array(DC_COST_IS_TV_UNKNOWN=>"ไม่ระบุ"
				 			,DC_COST_IS_TV_SUPPORT=>"สนับสนุน"
				 			,DC_COST_IS_TV_TV=>"โทรทัศน์"
				 			,DC_COST_IS_TV_RADIO=>"วิทยุ"
				 			,DC_COST_IS_TV_MARKET=>"การตลาด"
				 			,DC_COST_IS_TV_FINANCE=>"การเงิน");
 	
 	// โฆษณา/เช่าเวลาวิทยุ
 	define("DC_COST_REGION_RADIO_CENTRAL",		1); // ส่วนกลาง
 	define("DC_COST_REGION_RADIO_PROVINCIAL",	2); // ภูมิภาค 
 	define("DC_COST_REGION_RADIO_UNKNOWN",		3); // ไม่ระบุ
 	// dc_cost.i_type_region_radio  โฆษณา/เช่าเวลาวิทยุ
 	$arr_dc_cost_region_radio = array(DC_COST_REGION_RADIO_UNKNOWN=>"ไม่ระบุ"
						 			,DC_COST_REGION_RADIO_CENTRAL=>"ส่วนกลาง"
						 			,DC_COST_REGION_RADIO_PROVINCIAL=>"ภูมิภาค");
 	
 	//Segment งบทำการ
 	define("DC_COST_ESTIMATE_TV",			1); // โทรทัศน์
 	define("DC_COST_ESTIMATE_RADIO",		2); // วิทยุ
 	define("DC_COST_ESTIMATE_NEWS",			3); // ข่าว
 	define("DC_COST_ESTIMATE_CONCESSION",	4); // สัมปทาน
 	define("DC_COST_ESTIMATE_CENTRAL",		5); // ส่วนกลาง
 	define("DC_COST_ESTIMATE_UNKNOWN",		6); // ไม่ระบุ
 	//dc_cost.i_exp_estimate Segment งบทำการ
 	$arr_dc_cost_estimate = array(DC_COST_ESTIMATE_UNKNOWN=>"ไม่ระบุ"
					 			,DC_COST_ESTIMATE_TV=>"โทรทัศน์"
					 			,DC_COST_ESTIMATE_RADIO=>"วิทยุ"
					 			,DC_COST_ESTIMATE_NEWS=>"ข่าว"
					 			,DC_COST_ESTIMATE_CONCESSION=>"สัมปทาน"
					 			,DC_COST_ESTIMATE_CENTRAL=>"ส่วนกลาง");

 	// หน่วยงานที่บันทึก/รับ Order
 	define("DC_COST_IS_ORDER_YES",	1); // เป็นหน่วยงานรับ Order
 	define("DC_COST_IS_ORDER_NO",	2); // ไม่เป็นหน่วยงานรับ Order
 	// dc_cost_id.i_is_order หน่วยงานที่บันทึก/รับ Order
 	$arr_dc_cost_is_order = array(DC_COST_IS_ORDER_YES	=>"เป็น",
 									DC_COST_IS_ORDER_NO	=>"ไม่เป็น");
 	
 	// ประเภทค่าใช้จ่ายเงินเดือน
 	define("DC_COST_COA_COST",		1); // ค่าใช้จ่ายบุคลากร-ต้นทุน
 	define("DC_COST_COA_MANAGE",	2); // ค่าใช้จ่ายในการบริหาร
 	// dc_cost_id.i_cost_or_admin  ประเภทค่าใช้จ่ายเงินเดือน
 	$arr_dc_cost_coa = array(DC_COST_COA_COST	=>"ค่าใช้จ่ายบุคลากร-ต้นทุน",
 							DC_COST_COA_MANAGE	=>"ค่าใช้จ่ายในการบริหาร");

 	
 	// ประเภทหน่วยนับ
 	define("DC_UNIT_TYPE_IS_TYPE_ASSET",	1); // สินทรัพย์/พัสดุ 
 	define("DC_UNIT_TYPE_IS_TYPE_ORDER",	2); // รายได้โฆษณา/เช่าเวลา 
 	define("DC_UNIT_TYPE_IS_TYPE_INCOME",	3); // รายได้อื่นๆ
 	// dc_unit_type.i_is_unit_type  ประเภทหน่วยนับ
 	$arr_dc_unit_type_is_type = array(
 			DC_UNIT_TYPE_IS_TYPE_ASSET	=>"สินทรัพย์/พัสดุ",
 			DC_UNIT_TYPE_IS_TYPE_ORDER	=>"รายได้โฆษณา/เช่าเวลา",
 			DC_UNIT_TYPE_IS_TYPE_INCOME	=>"รายได้อื่นๆ"
 	);
 	//=================================================
 	//ประเภทการคิดภาษี ตาราง dc_tax Field i_type_whtax
 	define("TAX_BY_RATE"	,1);
 	define("TAX_BY_PROGRESS",2);
 	define("TAX_BY_M48"		,3);
 	define("TAX_BY_PENSION"	,4);
 	define("TAX_BY_NONE"	,5);
 	
 	$arr_tax_itype = array(
 		TAX_BY_RATE=>"หักตามอัตราภาษี"
		,TAX_BY_PROGRESS=>"หักตามอัตราก้าวหน้า"
		,TAX_BY_M48=>"หักตามเกณฑ์มาตรา 48"
		,TAX_BY_PENSION=>"หัก ณ ที่จ่ายจากบำเหน็จ"
		,TAX_BY_NONE=>"ไม่หัก ณ ที่จ่าย"
 	);
 	
 	//การคิดภาษีหัก ณ ที่จ่ายของประเภทกิจการ (ระบบเจ้าหนี้/บริหารการเงิน ตรวจจ่าย) ตาราง dc_tax_customer Field i_type_tax
 	define ("TAX_NOT",0); // ยังไม่ระบุ
 	define ("TAX_JURISTIC_PERSON",1); // นิติบุคคล
 	define ("TAX_NORMAL_PERSON",2); // บุคคลธรรมดา
 	
 	define ("I_IS_INCOME",1); //
 	define ("I_IS_INCOME_NONE",2); //
 	
 	//  i_is_type มีรายการภาษีเงินได้สำหรับจัดซื้อจัดจ้าง  รายการภาษีเงินไดั
 	$arr_tax_is_type = array(0 => "ไม่มีภาษีเงินได้" ,I_IS_INCOME =>"มีภาษีเงินได้" ,I_IS_INCOME_NONE =>"ไม่มีภาษีเงินได้");
 	//i_type_tax การคิดภาษีหัก ณ ที่จ่าย (ระบบเจ้าหนี้/บริหารการเงิน ตรวจจ่าย)
 	$arr_tax_type_tax 	= array(TAX_NOT =>"ยังไม่กำหนด" ,TAX_JURISTIC_PERSON =>"นิติบุคคล" ,TAX_NORMAL_PERSON =>"บุคคลธรรมดา");

 	// กลุ่มภาษี
 	define("TAX_GROUP_TAX", 1); //ภาษีหัก ณ ที่จ่าย
 	define("TAX_GROUP_VAT", 2); //ภาษีมูลค่าเพิ่ม
 	//dc_tax.i_group_tax  --กลุ่มภาษี
 	$arr_tax_group = array(TAX_GROUP_TAX =>"ภาษีหัก ณ ที่จ่าย" ,TAX_GROUP_VAT =>"ภาษีมูลค่าเพิ่ม");
 	
 	// คิด/หัก
 	define("TAX_CAL_YES", 1); //คิด/หัก
 	define("TAX_CAL_NO", 2); //ไม่คิด/ไม่หัก
 	//dc_tax.i_cal  --คิด/หัก ภาษี
 	$arr_tax_cal = array(TAX_CAL_YES =>"คิด/หัก ภาษี" ,TAX_CAL_NO =>"ไม่คิด/ไม่หัก ภาษี");
 	
 	//กำหนดแสดงอัตราภาษีฯ
 	define("TAX_SHOW_BY_NONE", 1); // ไม่แสดงอัตราภาษีหัก ณ ที่จ่าย
 	define("TAX_SHOW_BY_TAX", 2); // แสดง ตามอัตราภาษีหัก ณ ที่จ่าย
 	define("TAX_SHOW_BY_PROGRESS", 3); // แสดง แบบสะสมยอด อัตราก้าวหน้า
 	//dc_tax.i_show_by  --กำหนดแสดงอัตราภาษีฯ
 	$arr_tax_show_by = array(TAX_SHOW_BY_NONE =>"ไม่แสดงอัตราภาษีหัก ณ ที่จ่าย" 
 							,TAX_SHOW_BY_TAX =>"แสดง ตามอัตราภาษีหัก ณ ที่จ่าย"
 							,TAX_SHOW_BY_PROGRESS =>"แสดง แบบสะสมยอด อัตราก้าวหน้า");
 	
 	//กำหนดแสดงชื่อภาษี สำหรับใบสำคัญจ่ายเงิน (Payment Voucher)
 	define("TAX_SHOW_NONE", 1); // ไม่แสดงชื่อภาษี
 	define("TAX_SHOW_NONE_ISPROGRESS", 2); // ไม่แสดงชื่อภาษี แต่สะสมยอดที่ภาษีหัก ณ ที่จ่ายอัตราก้าวหน้า
 	define("TAX_SHOW_YES", 3); // แสดงชื่อภาษี
 	//dc_tax.i_show  --กำหนดแสดงอัตราภาษีฯ
 	$arr_tax_show = array(TAX_SHOW_NONE =>"ไม่แสดงชื่อภาษี"
				 			,TAX_SHOW_NONE_ISPROGRESS =>"ไม่แสดงชื่อภาษี แต่สะสมยอดที่ภาษีหัก ณ ที่จ่ายอัตราก้าวหน้า"
				 			,TAX_SHOW_YES =>"แสดงชื่อภาษี");
 	
 
 	// ประเภทลูกหนี้/เจ้าหนี้ => dc_cnt.i_is_debtor
 	define("CNT_TYPE_DEBTOR"				,	1); // ลูกหนี้
 	define("CNT_TYPE_DEBTOR_AND_CREDITOR"	,	2); // ลูกหนี้/เจ้าหนี้
 	define("CNT_TYPE_CREDITOR"				,	3); // เจ้าหนี้
 	$arr_cnt_type = array(
 			0				=>"ทั้งหมด",
 			CNT_TYPE_DEBTOR	=>"ลูกหนี้",
 			CNT_TYPE_DEBTOR_AND_CREDITOR	=>"ลูกหนี้/เจ้าหนี้",
 			CNT_TYPE_CREDITOR =>"เจ้าหนี้"
 	);
 
	
?>
