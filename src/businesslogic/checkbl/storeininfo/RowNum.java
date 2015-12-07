package businesslogic.checkbl.storeininfo;

import systemenum.CheckResult;
import vo.StorageSetAreaVO;
import businesslogic.checkbl.CheckInfo;
import businesslogic.checkbl.CheckResultMessage;
import businesslogic.storagebl.Storage;
import businesslogic.userbl.LoginController;

public class RowNum implements CheckInfo{

	private int areaNum;
	private int rowNum;
	
	
	public RowNum(int areaNum, int rowNum) {
		this.areaNum = areaNum;
		this.rowNum = rowNum;
	}
	
	@Override
	public CheckResultMessage check() {
		Storage storage = new Storage();
		StorageSetAreaVO storageSetAreaVO = storage.getStorageData(LoginController.getOrganizationId());
		int maxRow = -2;
		CheckResultMessage checkResultMessage = new CheckResultMessage();
		switch (areaNum) {
		case 0:
			maxRow = storageSetAreaVO.getAirCapacity();
			break;
		case 1:
			maxRow = storageSetAreaVO.getTrainCapacity();
			break;
		case 2:
			maxRow = storageSetAreaVO.getCarCapacity();
			break;
		case 3:
			maxRow = storageSetAreaVO.getMotorCapacity();
		default:
			break;
		}
		
		if (maxRow == -2 && rowNum>=0 && rowNum <= 19) {
			checkResultMessage.addInfo(CheckResult.WARNING, "���Ų���Ϊ��");
			return checkResultMessage;
		}
		if (rowNum<0 || rowNum>19) {
			checkResultMessage.addInfo(CheckResult.FALSE, "�ź�ֻ����0-19֮��");
			return checkResultMessage;
		}
		if (maxRow == -2) {
			checkResultMessage.addInfo(CheckResult.WARNING, "��������Ŵ���");
			return checkResultMessage;
		}
		
		if (rowNum>maxRow) {
			checkResultMessage.addInfo(CheckResult.FALSE, "�źŲ��ܳ�������������ţ�"+maxRow);
			return checkResultMessage;
		}
		
		return checkResultMessage;
	}
	
	

}