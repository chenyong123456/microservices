package cn.knowimage.util;

/**
 * 功能:主要对数值类型的String类型的字符串进行相应的转换和换算
 * @author 啊勇
 */
public class StringConvertNumber {

	public static float stringToFloat(String timeType,String timeValue){
		float time = 0;
		//判断值了格式类型1/2,0.5这两种类型
		if(timeValue.contains("/")){
			String[] temporaryTime = timeValue.split("/");
			float molecule = Float.parseFloat(temporaryTime[0]);
			float denominator = Float.parseFloat(temporaryTime[1]);
			time = molecule/denominator;
			System.out.println(time);
		}else{
			time = Float.parseFloat(timeValue);
			//System.out.println(time);
		}
		//判断时间类型进行换算
		if("小时".equals(timeType)){
			
		}else if("日".equals(timeType)){
			time = time * 24;
		}else if("周".equals(timeType)){
			time = time * 168;
		}else if("月".equals(timeType)){
			time = time * 672;
		}
		return time;
	}
	
	public static void main(String[] args) {
		System.out.println(
				StringConvertNumber.stringToFloat("天", "1/2"));
	}
	
}
