package presentation.managerui.approvalui.loadui;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import businesslogicservice.LoadblService;
import vo.GoodsVO;
 
 
/**
 * 维护装车信息的订单列表
 * @author 林祖华
 * @version 1.0
 * @see businesslogicservice.LoadblService
 */

public class OrderTableModel extends DefaultTableModel{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3918481690066300511L;

    private List<GoodsVO> dataList;
    private static final String[] TABLE_HEADER = {"订单编号","重量","出发地"};
    private LoadblService loadblService;
 
    
    public OrderTableModel(LoadblService lbs) {      
       loadblService = lbs; 
       dataList = new ArrayList<GoodsVO>();
       setDataVector(convertToVectorData(dataList), getColumnNamesVector());
    }
    
    public OrderTableModel(LoadblService lbs, List<String> idList){
    	loadblService = lbs;
    	dataList = new ArrayList<GoodsVO>();
    	for(String id : idList)
    		dataList.add(loadblService.getGoodsVO(id));    	
    	setDataVector(convertToVectorData(dataList), getColumnNamesVector());
    }
    
    public void add(String id){
    	GoodsVO vo = loadblService.getGoodsVO(id);
    	if(vo != null){
    		addRow(convertToVector(vo));
    		dataList.add(vo); 
    		loadblService.addGoods(vo);
    	}
    }
    
    public void delete(int row){
        removeRow(row);     
        loadblService.deleteGoods(dataList.get(row));
        dataList.remove(row);

         
    }
    
    
    
    public GoodsVO getGoodsVO(int row){
        return getRowData(row);
    }
    
 //   @SuppressWarnings({ "unchecked", "rawtypes" })
//    public Class getColumnClass(int column) {  
//        Class returnValue;  
//        if ((column >= 0) && (column < getColumnCount())) {  
//            returnValue = getValueAt(0, column).getClass();  
//        } else {  
//            returnValue = Object.class;  
//        }  
//        return returnValue;  
//    }  
//    
    public boolean isCellEditable(int row, int column) { 
        return false;
    }
    
    private GoodsVO getRowData(int row){
        return dataList.get(row);
    }
    
    private static Vector<Vector<Object>> convertToVectorData(List<GoodsVO> list){
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        for(GoodsVO vo:list){
            data.add(convertToVector(vo));
        }
        return data;
    }
    
    private static Vector<Object> convertToVector(GoodsVO vo){
        Vector<Object> rowVector = new Vector<Object>();
        rowVector.add(vo.getId()); 
        rowVector.add(vo.getWeight());
        rowVector.add(vo.getDepart());
        return rowVector;
    }
//    
    private static Vector<String> getColumnNamesVector(){
        Vector<String> v = new Vector<String>();
        for(String s:TABLE_HEADER){
            v.add(s);
        }
        return v;
    }
    
 
}
