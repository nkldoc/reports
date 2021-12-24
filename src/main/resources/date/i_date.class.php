<?php
class i_date{

	public $s_month_thai = array("01" => "ม.ค.",		"02" => "ก.พ.",		"03" => "มี.ค."
								,"04" => "เม.ย.",	"05" => "พ.ค.",		"06" => "มิ.ย."
								,"07" => "ก.ค.",		"08" => "ส.ค.",		"09" => "ก.ย."
								,"10" => "ต.ค.",		"11" => "พ.ย.",		"12" => "ธ.ค.");
	
	public $l_month_thai = array("01" => "มกราคม",	"02" => "กุมภาพันธ์",	"03" => "มีนาคม"
								,"04" => "เมษายน",	"05" => "พฤษภาคม",	"06" => "มิถุนายน"
								,"07" => "กรกฏาคม",	"08" => "สิงหาคม",		"09" => "กันยายน"
								,"10" => "ตุลาคม",	"11" => "พฤศจิกายน",	"12" => "ธันวาคม");
	
	public $s_month_eng	 = array("01" => "Jan.",	"02" => "Feb.",		"03" => "Mar."
								,"04" => "Apr.",	"05" => "May.",		"06" => "Jun."
								,"07" => "Jul.",	"08" => "Aug.",		"09" => "Sep."
								,"10" => "Oct.",	"11" => "Nov.",		"12" => "Dec.");
	
	public $l_month_eng	 = array("01" => "January",	"02" => "February",	"03" => "March"
								,"04" => "April",	"05" => "May",		"06" => "June"
								,"07" => "July",	"08" => "August",	"09" => "September"
								,"10" => "October",	"11" => "November",	"12" => "December");
	
	public $display_format = "d-m-Y";
	public $system_foamt = "Y-m-d";
	public $year_display = 543;

	function __CONSTRUCT()
	{ } 
	function __DESTRUCT()
	{ } 
	
	public function StdDate($date=Null)
	{ 
	if($date==Null){
		return Null;
	}else{
		$pda = strtotime($date);
 		if ($pda<0)
		{
			list($day, $month, $year) = split (' ', $date);
			$month = array_search($month,$this->s_month_thai); 
 		}
		else
		{
			$dd = date($this->display_format,$pda);
			list($day, $month, $year) = split ('[/.-]', $dd);
		}
		return sprintf ("%02d-%02d-%04d",$day,$month,$year + $this->year_display);
		}
	}

	public function extDateBuddha($date=NULL,$buddha=true,$split='-')
	{
          
                if($date==NULL)return NULL;
                $date = date('d-m-Y',@strtotime($date));
                list($day, $month, $year) = explode('-', $date);
                return sprintf("%02d-%02d-%04d", intval($day),intval($month),(intval($year)+543)); 
 
	} 
	
	public function getYearMonth(){ return date('y').(date('m')>10?'0'.date('m'):date('m')); }
	
	public function shot_date_from_db($date)
	{
		$dd = $this->extDateBuddha($date);
 		list($day, $month, $year) = explode('-', $dd);
 		
		return sprintf("%02d %s %04d",$day, $this->s_month_thai[$month],$year);
	}

	public function long_date_from_db($date)
	{
		$dd = $this->extDateBuddha($date);
		list($day, $month, $year) = explode('-', $dd);
		return sprintf ("%02d %s %04d",$day, $this->l_month_thai[$month],$year);
	}
	
	public function bc_to_ad($date=NULL)
	{
                if($date==NULL)return NULL;
                list($day, $month, $year) = explode('-', $date);
                $time = strtotime(sprintf ("%04d-%02d-%02d",($year-543), $month, $day)); 
		return date('Y-m-d',$time);
	}
}


?>