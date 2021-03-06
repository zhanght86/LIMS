package businesslogic.checkbl.orderinfo;

import systemenum.CheckResult;
import businesslogic.checkbl.CheckInfo;
import businesslogic.checkbl.CheckResultMessage;


/**
 * 该类用于检查快递员在创建订单时输入的货物的重量的是否正确
 * @author lc
 * @version 1.1
 *
 */
public class OrderWeight implements CheckInfo {

	private double weight;
		
	public OrderWeight(double weight) {
		this.weight = weight;
	}


	@Override
	public CheckResultMessage check() {
		CheckResultMessage checkResultMessage = new CheckResultMessage();
		if (weight<=0) {
			checkResultMessage.addInfo(CheckResult.FALSE, "货物的重量必须大于0");
			return checkResultMessage;
		}
		if (weight>=1000) {
			checkResultMessage.addInfo(CheckResult.WARNING, "货物过重，请确认！");
		}
		return checkResultMessage;
	}

}
