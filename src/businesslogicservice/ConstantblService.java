package businesslogicservice;

import vo.ConstantVO;

public interface ConstantblService {
	public boolean priceModify(ConstantVO vo);
	
	public double getPrice();
	
	public boolean distanceModify(ConstantVO vo);
	
	public double getDistance(ConstantVO vo);
}
