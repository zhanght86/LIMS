package presentation.financeui.primeinfoui.truckui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import businesslogic.truckbl.Truck;
import businesslogicservice.PrimeInfoblService;
import businesslogicservice.TruckblService;
import vo.TruckVO;

public class PrimeInfoTruckTableModel extends DefaultTableModel{

    /**
     * 
     */
    private static final long serialVersionUID = -7045438119707253107L;
    
    private PrimeInfoblService primeInfoblService;
    private List<TruckVO> dataList;
    private static final String[] TABLE_HEADER = {"�������","���ڻ���","��������","���ƺ�","���̺�","��������"};
    
    
    public PrimeInfoTruckTableModel(PrimeInfoblService primeInfoblService) {
        this.primeInfoblService = primeInfoblService;
        dataList = new ArrayList<TruckVO>();
        setDataVector(convertToVectorData(dataList), getColumnNamesVector());
    }
    
    public void create(TruckVO vo){
        addRow(convertToVector(vo));
        dataList.add(vo);
        primeInfoblService.addTruckVO(vo);
    }
    
    public void delete(int row){
        removeRow(row);
        TruckVO vo = dataList.get(row);
        dataList.remove(row);
        primeInfoblService.removeTruckVO(vo);
    }
    
    public TruckVO getTruckVO(int row){
        return getRowData(row);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Class getColumnClass(int column) {  
        Class returnValue;  
        if ((column >= 0) && (column < getColumnCount())) {  
            returnValue = getValueAt(0, column).getClass();  
        } else {  
            returnValue = Object.class;  
        }  
        return returnValue;  
    }  
    
    public boolean isCellEditable(int row, int column) { 
        return false;
    }
    
    private TruckVO getRowData(int row){
        return dataList.get(row);
    }
    
    private static Vector<Vector<Object>> convertToVectorData(List<TruckVO> list){
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for(TruckVO vo:list){
            data.add(convertToVector(vo));
        }
        return data;
    }
    
    private static Vector<Object> convertToVector(TruckVO vo){
        Vector<Object> rowVector = new Vector<Object>();
        rowVector.add(vo.getId());
        rowVector.add(vo.getOrganization());
        rowVector.add(vo.getEngineNumber());
        rowVector.add(vo.getTruckNumber());
        rowVector.add(vo.getChassisNumber());
        rowVector.add(new SimpleDateFormat().format(vo.getPurchaseDate()));
        return rowVector;
    }
    
    private static Vector<String> getColumnNamesVector(){
        Vector<String> v = new Vector<String>();
        for(String s:TABLE_HEADER){
            v.add(s);
        }
        return v;
    }
    
}