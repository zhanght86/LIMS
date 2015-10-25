package businesslogicservice_driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vo.AccountVO;
import vo.RevenueVO;
import businesslogicservice.SettlementblService;


public class SettlementblService_Driver {

	public void driver(SettlementblService settlementblService){
		List<Long> list=new ArrayList<Long>();
		list.add(new Long("1008356612"));
		list.add(new Long("1008356614"));
		
		boolean result1=settlementblService.deposit(new Long("1000000000000000009"), new RevenueVO(new Long("025001150118000001"), new Date(),new Long(025001001) , 30.00,list));
		if(result1==true)
			System.out.println("Create succeed\n");
		
		boolean result2=settlementblService.execute(new RevenueVO(new Long("025001150118000001"), new Date(),new Long(025001001) , 30.00,list));
		if(result2==true)
			System.out.println("Execute succeed\n");
		
		List<RevenueVO> vos = settlementblService.queryRevenueVO(new Date(), "�Ͼ��й�¥��Ӫҵ��");
	    System.out.println("get "+vos.size()+" AccountVO!");
	}
}