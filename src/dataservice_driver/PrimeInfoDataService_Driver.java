package dataservice_driver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import po.AccountPO;
import po.EmployeePO;
import po.OrganizationPO;
import po.PayPO;
import po.PrimeInfoPO;
import po.StoragePO;
import po.TruckPO;
import systemenum.Position;
import dataservice.PrimeInfoDataService;

public class PrimeInfoDataService_Driver {
	
	public void drive(PrimeInfoDataService primeInfoDataService){
		List<OrganizationPO> organizationPOList= new ArrayList<OrganizationPO>();
		organizationPOList.add(new OrganizationPO(new Long("025001"), "�Ͼ��й�¥��Ӫҵ��", "�Ͼ���"));
		List<EmployeePO> employeePOList= new ArrayList<EmployeePO>();
		employeePOList.add(new EmployeePO(new Long("025001002"), "zhangSan", "�Ͼ��й�¥��Ӫҵ��", Position.SELLINGBUSINESSMAN, new PayPO(20.00, 2.00, 2, 2.00, 2.00)));
		List<TruckPO> truckPOList= new ArrayList<TruckPO>();
		truckPOList.add(new TruckPO(new Long("025001014"), "sdda", "��A88888", "daffd", new Date(), null));
		List<StoragePO> storagePOList= new ArrayList<StoragePO>();
		storagePOList.add(new StoragePO(5, 50,50, 50, 50, 50, 50.00));
		List<AccountPO> accountPOList= new ArrayList<AccountPO>();
		accountPOList.add(new AccountPO(new Long("2000000000002345678"), 100.00));
		
		PrimeInfoPO po=new PrimeInfoPO(organizationPOList, employeePOList, truckPOList,storagePOList, accountPOList);
		try {
			primeInfoDataService.init();
			primeInfoDataService.insert(po);

	        List<PrimeInfoPO> allPO = primeInfoDataService.getAll();
	        System.out.println("get "+allPO.size()+" PrimeInfoPO!");
	        
	        primeInfoDataService.finish();
	    } catch (RemoteException e) {
	        e.printStackTrace();
	    }
	}

}