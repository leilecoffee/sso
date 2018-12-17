export default new class DateUtil {
	
	constructor() {
	}
	
	SIGN_DATE_REG = /([YMDhmsiWw])(\1*)/g;
	/**
     * 默认的pattern
     * 'YYYY-MM-DD hh:mm:ss:iii'
     */
    DEFAULT_PATTERN = 'YYYY-MM-DD hh:mm:ss:iii';
	objectPrototypeToString = Object.prototype.toString;
	 /**
     * @description 判断一个值是否是日期类型
     * @param {Object} value
     */
	isDate(value) {
        return this.objectPrototypeToString.call(value) === '[object Date]';
    }
	
	/**
     * @description 将字符串多余的长度补齐0
     * @param {String} s
     * @param {Number} len
     */
    paddingFillWith0(s, len) {
        var len = len - (s + "").length;
        for (var i = 0; i < len; i++) {
            s = "0" + s;
        }
        return s;
    }
	
	formatDateToString(value, pattern) {
        if (!this.isDate(value)) {
            return '';
        }
        try {
            //默认为输出所有的
            pattern = pattern || this.DEFAULT_PATTERN;
			var This=this;
            return pattern.replace(this.SIGN_DATE_REG, function($0) {
                //如果传入一个yyyy-MM-dd 的表达式
                //实际上这个函数会分别回调多次 没符合一次匹配就回调一次
                //$0:yyyy  $0:MM $0:dd  依次类推
                //console.log('$0:'+$0);
                switch ($0.charAt(0)) {
                    case 'Y':
                        return This.paddingFillWith0(value.getFullYear(), $0.length);
                    case 'M':
                        return This.paddingFillWith0(value.getMonth() + 1, $0.length);
                    case 'D':
                        return This.paddingFillWith0(value.getDate(), $0.length);
                    case 'h':
                        return This.paddingFillWith0(value.getHours(), $0.length);
                    case 'm':
                        return This.paddingFillWith0(value.getMinutes(), $0.length);
                    case 's':
                        return This.paddingFillWith0(value.getSeconds(), $0.length);
                    case 'i':
                        return This.paddingFillWith0(value.getMilliseconds(), $0.length);
                    case 'w':
                        return value.getDay();
                    case 'W':
                        //自动将星期转为了大写
                        var week = ['日', '一', '二', '三', '四', '五', '六'];
                        return This.paddingFillWith0(week[value.getDay()], $0.length);
                    default:
                        return '';
                }
            });
        } catch (e) {
            console.log('error:' + e);
            return '';
        }
    };
} 