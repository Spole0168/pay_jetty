/**
 * 表单的校验
 * Shangzhuzi
 * 2016年12月15日09:38:20
 */
/**
 * jsp 中的常量
 * Shangzhuzi
 * 
 */

var CORE_CERT_TYPE_01 = "01";
//操作界面ajax 返回结果
var CORE_AJAX_RESULT_Y = "Y";
var CORE_AJAX_RESULT_N = "N";


/**
 * arr ["custName","custCode"]
 * 校验指定的的ids 是否全部 非空
 */
function formHasNull(arr){
	if(!arr || arr.length<=0){
		return false;
	}
	var flag = true;
	for(var i=0;i<arr.length;i++){
		var id = arr[i];
		var value = $.trim($("#"+id).val());
		if(!value){
			flag = false;
			//设置红框
			$("#"+id).css("border-color","red");
			$("#"+id).focus();
		}
	}	
	return flag;
}
/**
 * 是否包含特殊字符
 * @param id
 */
function isHasSpecialChar(id){
	var value = $.trim($("#"+id).val());
	
}
/**
 * 非空校验 
 * 空 或者 null  	true;
 * 	非空        	false
 */
function isNullOrBlank(id){
	var value = $.trim($("#"+id).val());
	if(!!value){   //!!value (value不为空) true
		return false;
	}
	//设置红框
	$("#"+id).css("border-color","red");
	$("#"+id).focus();
	$("#"+id).bind('change', function(){
		removeRed(id);
	});
	return true; 
}
/**
 * 非空字段不为空时，去掉红框
 */
function removeRed(id){
	var value = $.trim($("#"+id).val());
	if(!!value){   //!!value (value不为空) true
		$("#"+id).css("border-color","#ccc");//#ccc
		try{
			clearWarningInfo();
		}catch(e){
			console.log("removeRed"+e);
		}
	}
}

/**
 * 电子邮箱简单校验
 * @param id
 * @returns {Boolean}
 */ 
function isEmail(id){
	var value = $.trim($("#"+id).val());
	var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/gi; 
    if(!value||!reg.test(value)){
		$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }
    return true;
    
} 
/**
 * 邮编(只能为六位)
 * @param id
 * @returns {Boolean}
 */
function isPostcode(id){
	var value = $.trim($("#"+id).val());
	var reg=/^\d{6}$/;
    if(!value||!reg.test(value)){
    	$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }
    return true;
}     
/**
 * 校验日期格式  YYYY-MM-dd
 * @param id
 * @returns {Boolean}
 */
function isDate(id){  
	var value = $.trim($("#"+id).val());
	var reg= /^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/; 
    if(!value||!reg.test(value)){
    	$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }
    return true;
} 
/**
 * 校验是否是 电话号码 或者 手机号
 */
function isContact(id){
	var value = $.trim($("#"+id).val());
	var regTel = /^1[34578]\d{9}$/; 
	var regPhone  = /^(\d{3,4}-)?\d{7,8}$/;
	if(!value||(!regTel.test(value)&&!regPhone.test(value))){//!value (value为空) true
    	$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }
    return true;
}
/**
 * 校验手机号码
 * @param id
 * @returns {Boolean}
 */
function isTel(id){ 
	var value = $.trim($("#"+id).val());
	var reg = /^1[34578]\d{9}$/;   
    if(!value||!reg.test(value)){
    	$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }
    return true;
}   
/**
 * 校验电话号码   XXXX(3,4)-XXXXXXXX (7,8)
 * @param id
 * @returns {Boolean}
 */
function isPhone(id){
	var value = $.trim($("#"+id).val());
	var regPhone  = /^(\d{3,4}-)?\d{7,8}$/;
    if(!value||!regPhone.test(value)){
    	$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }
    return true;
}   
/**
 * 校验合法金额
 * /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
 * @param id
 * @returns {Boolean}
 */
function isAmount(id){     
	var value = $.trim($("#"+id).val());
	var reg  = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if(!value||!reg.test(value)||value < 0){
    	$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }
    return true;
}  
/**
 * 校验数字 
 * @param id
 * @returns {Boolean}
 */
function isNumber(id){     
	var value = $.trim($("#"+id).val());
	if(!value||isNaN(value)||value!==String(Number(value))){    
		$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }
    return true;
} 
/**
 * 校验合法身份证
 * @param id
 * @returns {Boolean}
 */
function isIDCard(id){     
	var code = $.trim($("#"+id).val());
    var city={	11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",
    			31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
    			42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",
    			53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",
    			81:"香港",82:"澳门",91:"国外 "};
    
    if(	!code 
    		|| !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)
    		|| !city[code.substr(0,2)]
    ){
    	$("#"+id).css("border-color","red");
		$("#"+id).focus();
		$("#"+id).bind('change', function(){
			removeRed(id);
		});
        return false;
    }else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
            	$("#"+id).css("border-color","red");
        		$("#"+id).focus();
        		$("#"+id).bind('change', function(){
        			removeRed(id);
        		});
                return false;
            }
        }
    }
    return true;
}
/**
 * 计算两个指定格式日期相差天数
 * @param startDateStr   "yyyy-MM-dd"
 * @param endDateStr	"yyyy-MM-dd"
 * @returns {Number}
 */
function getDaysDiff(startDateStr,endDateStr){
	var day = 0;
	if(!startDateStr||!endDateStr
	){
		return;
	}
	var sDateArr = startDateStr.replace(/(^\s*)|(\s*$)/g, "").substring(0,10).split("-");
	var eDateArr = endDateStr.replace(/(^\s*)|(\s*$)/g, "").substring(0,10).split("-");
	if(!sDateArr||!eDateArr||
			sDateArr.length< 3||eDateArr.length< 3){
		return;
	}
	var sDate = new Date(sDateArr[0], sDateArr[1]-1, sDateArr[2]);
	var eDate = new Date(eDateArr[0], eDateArr[1]-1, eDateArr[2]);
	day = parseInt((eDate.getTime() - sDate.getTime()) / 1000 / 60 / 60 /24); //把相差的毫秒数转换为天数 
	return day;
}
/**
 * paramer :"yyyy-MM-dd HH:mm:ss.." 或者 "yyyy/MM/dd HH:mm:ss.."
 * 时间戳 转化为 日期
 * return “yyyy-MM-dd”
 */
function transDateTime2Date(dateTimeStr){
	if(!dateTimeStr||dateTimeStr.length<10){
		return;
	}
	return dateTimeStr.replace(/(^\s*)|(\s*$)/g, "").replace(/\//g, "-").substring(0,10);
}



/**
 * 校验合法数字(只能为0~9的整数)
 * 
 * @param id
 * @returns {Boolean}
 */
function isIntNumber(id) {
	var reg = new RegExp("^[0-9]*$");
	var value = $.trim($("#" + id).val());
	if (!value || !reg.test(value)) {
		$("#" + id).css("border-color", "red");
		$("#" + id).focus();
		$("#" + id).bind('change', function() {
			removeRed(id);
		});
		return false;
	}
	return true;

}








