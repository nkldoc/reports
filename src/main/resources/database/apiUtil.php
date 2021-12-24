<?php 
class apiUtil{
	
	public function __construct(){}
	
	public function viewAcc($i,$alias=''){
                $alias = ($alias=='')?'':$alias.'.';
		switch($i){
			case 1:		$ret = ' and '.$alias.'dc_user_create_id= '.$_SESSION["user_id"]; break;
			case 2:		$ret = ' and '.$alias.'dc_user_create_cost_id='.$_SESSION["dc_cost_id"]; break;
			default:	$ret = '';
		}
		return $ret;
	}
	public function get($a=null){ return $a??null; }
	
	public function json_clean_decode($json, $assoc = false, $depth = 512, $options = 0) {
	
		// search and remove comments like /* */ and //
		$json = preg_replace("#(/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/)|([\s\t](//).*)#", '', $json);
	
		if(version_compare(phpversion(), '5.4.0', '>=')) {
			$json = json_decode($json, $assoc, $depth, $options);
		}
		elseif(version_compare(phpversion(), '5.3.0', '>=')) {
			$json = json_decode($json, $assoc, $depth);
		}
		else {
			$json = json_decode($json, $assoc);
		}
	
		return $json;
	}//end func
	
	public function sendAPI($arr,$url='http://staging-api.sellsuki.com/dpx/warehousetxcheck',$meth='POST')
	{
		$data_json 	= $arr;
	
		$ch 		= curl_init();
	
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
		curl_setopt($ch, CURLOPT_VERBOSE, true);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, true);
		curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 2);
		curl_setopt($ch, CURLOPT_CAINFO,  getcwd() ."/dpx.crt");
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
		curl_setopt($ch, CURLOPT_POSTFIELDS,$data_json);
		if($meth=='POST'){
			curl_setopt($ch, CURLOPT_POST, 1);
		}else if($meth=='PUT'){
			curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
		}else if($meth=='DELETE'){
			curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
		}
	
		$response  = curl_exec($ch);
		curl_close($ch);
		return $response;
	}
	
	public function mnUser($data, $mode="")
	{
		 

		if ($mode == "")
		{ 
			$mode =$data["mode"]??NULL;
		}
		if (strtoupper($mode) == "ADD")
		{
			$data["dc_user_create_id"] = $_SESSION["user_id"];
			$data["dc_user_create_cost_id"] = $_SESSION["dc_cost_id"];
			$data["d_create"] = date("Y-m-d H:i:s");
		}
		
		$data["dc_user_update_id"] = $_SESSION["user_id"];
		$data["dc_user_update_cost_id"] = $_SESSION["dc_cost_id"];
		$data["d_update"] = date("Y-m-d H:i:s");
		
		return $data;
	}
	public function __Destruct(){}
}
?>