package businesslogic.checkbl.driverinfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import systemenum.CheckResult;
import businesslogic.checkbl.CheckInfo;
import businesslogic.checkbl.CheckResultMessage;

public class DriverIdCard implements CheckInfo{
	private String idCard;
	
	
	public DriverIdCard(String idCard) { 
		this.idCard = idCard;
	}


	@Override
	public CheckResultMessage check() {
		// TODO Auto-generated method stub
		CheckResultMessage checkResultMessage = new CheckResultMessage();
		 if(idCard.length() == 0){
			 checkResultMessage.addInfo(CheckResult.FALSE, "����֤���벻��Ϊ��");
	         return checkResultMessage;
		 }
		if(idCard.length() != 18){
			 checkResultMessage.addInfo(CheckResult.FALSE, "����֤����ӦΪ18λ");
	         return checkResultMessage;
		}
		if(!isRight(idCard)){
			checkResultMessage.addInfo(CheckResult.FALSE, "����֤�����ʽ����ȷ");
	         return checkResultMessage;
		}
		
		return checkResultMessage;
	}
	
	public boolean isRight(String str){
		 Pattern pattern = Pattern.compile("\\d{17}[0-9a-zA-Z]"); 
		 Matcher isNum = pattern.matcher(str);		 
		 return isNum.matches(); 
		 
		
	}
	
	 
}