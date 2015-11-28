package presentation.businesshallui.loadui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import vo.LoadVO;
import businesslogic.loadbl.Load;
import businesslogicservice.LoadblService;

public class LoadDialog extends JDialog{

    /**
     * 
     */
    private static final long serialVersionUID = 6468749815012470915L;
    private static final String[] LABEL_NAMES = {"装车单编号","汽运编号","车辆代号","出发地","目的地","装车日期"};
    
    private LoadblService loadblService;
    private JTextField[] textFields;
    private OrderTableModel tableModel;
    private JTextField costTextField;
    private JComboBox<String> departComboBox;
    private JComboBox<String> destinationComboBox;
    
    public LoadDialog(){
        
        loadblService = new Load();
        
        JLabel[] labels = new JLabel[6];
        for(int i=0;i<labels.length;i++){
            labels[i] = new JLabel();
            labels[i].setText(LABEL_NAMES[i]);
            labels[i].setBounds(20, 10+35*i, 100, 25);
            this.add(labels[i]);
        }
        
        textFields = new JTextField[3];
        for(int i=0;i<textFields.length;i++){
            textFields[i] = new JTextField();
            textFields[i].setBounds(100, 10+35*i, 150, 25);
            this.add(textFields[i]);
        }

        departComboBox = new JComboBox<String>();
        departComboBox.addItem("南京市栖霞区中转中心");
        departComboBox.addItem("上海市浦东新区中转中心");
        departComboBox.setBounds(100, 10+35*3, 180, 25);
        this.add(departComboBox);
        
        destinationComboBox = new JComboBox<String>();
        destinationComboBox.addItem("南京市栖霞区中转中心");
        destinationComboBox.addItem("上海市浦东新区中转中心");
        destinationComboBox.setBounds(100, 10+35*4, 180, 25);
        this.add(destinationComboBox);
        
        JComboBox<Integer> yearComboBox = new JComboBox<Integer>();
        JComboBox<Integer> monthComboBox = new JComboBox<Integer>();
        JComboBox<Integer> dayComboBox = new JComboBox<Integer>();
        for(int i=1960;i<=2015;i++)  yearComboBox.addItem(i);
        for(int i=1;i<=12;i++)  monthComboBox.addItem(i);
        for(int i=1;i<=31;i++)  dayComboBox.addItem(i);
        yearComboBox.setBounds(100, 10+35*5, 70, 25);
        monthComboBox.setBounds(170, 10+35*5, 60, 25);
        dayComboBox.setBounds(230, 10+35*5, 60, 25);
        this.add(yearComboBox);
        this.add(monthComboBox);
        this.add(dayComboBox);
        
        JLabel[] personLabels = new JLabel[2];
        String[] personLabelNames = {"监装员","押运员"};
        JTextField[] personTextFields = new JTextField[2];
        for(int i=0;i<2;i++){
            personLabels[i] = new JLabel();
            personLabels[i].setText(personLabelNames[i]);
            personLabels[i].setBounds(20+160*i, 10+35*6, 100, 25);
            this.add(personLabels[i]);
            personTextFields[i] = new JTextField();
            personTextFields[i].setBounds(100+140*i, 10+35*6, 70, 25);
            this.add(personTextFields[i]);
        }
        
        JLabel orderLabel = new JLabel();
        orderLabel.setText("装车订单号");
        orderLabel.setBounds(20, 10+35*7, 100, 25);
        this.add(orderLabel);
        
        tableModel = new OrderTableModel(loadblService);  
        TableRowSorter<TableModel>  tableSorter = new TableRowSorter<TableModel>(tableModel);
        JTable orderTable = new JTable(tableModel);
        orderTable.getTableHeader().setPreferredSize(new Dimension(180, 25));
        orderTable.setSize(250, 100);
        orderTable.setRowSorter(tableSorter);   
        
        
        JScrollPane OrderScrollPane = new JScrollPane(orderTable);
        OrderScrollPane.setBounds(100, 10+35*7, 150, 75);          
        JButton addOrderButton = new JButton("添加订单");
        addOrderButton.setBounds(270, 10+35*7, 70, 20);
        JButton deleteOrderButton = new JButton("删除订单");
        deleteOrderButton.setBounds(270, 10+35*8, 70, 20);
        addOrderButton.addActionListener(new ActionListener() {
                
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // new AddOrderDialog(tableModel, dialog);
                     new AddOrderDialog();
            }
        });
            
        deleteOrderButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int row = orderTable.getSelectedRow();
                   if(row == -1)
                       return;
                   int modelRow = orderTable.convertRowIndexToModel(row);
                   tableModel.delete(modelRow);
            }
        });
        
        this.add(OrderScrollPane);
        this.add(addOrderButton);
        this.add(deleteOrderButton);
        
        JLabel costLabel = new JLabel();
        costLabel.setText("运费");
        costLabel.setBounds(20, 20+35*9, 100, 25);
        this.add(costLabel);
        
        costTextField = new JTextField();
        costTextField.setBounds(100, 20+35*9, 60, 25);
        this.add(costTextField);
        
        JButton confirmButton = new JButton("确认");
        confirmButton.setBounds(230, 380, 80, 30);
        confirmButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> orderIdList = new ArrayList<String>();
                for(int i = 0; i < orderTable.getRowCount(); i ++)
                    orderIdList.add((String)orderTable.getValueAt(i, 0));
                LoadVO vo = new LoadVO(textFields[0].getText(), new Date(), textFields[1].getText(), (String)departComboBox.getSelectedItem(), (String)destinationComboBox.getSelectedItem(), textFields[2].getText(), personTextFields[0].getText(), personTextFields[1].getText(), orderIdList, new Double(costTextField.getText()));
                loadblService.createLoadPO(vo);
                LoadDialog.this.dispose();
            }
        });
        JButton cancleButton = new JButton("取消");
        cancleButton.setBounds(140, 380, 80, 30);
        cancleButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadDialog.this.dispose();
            }
        });
        
        this.add(confirmButton);
        this.add(cancleButton);
        
        this.setTitle("装车单");
        this.setSize(340, 470);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
    }
    
    private void setExpensesField(){
        costTextField.setText("" + loadblService.getCost
                ((String)departComboBox.getSelectedItem(),(String) destinationComboBox.getSelectedItem() ));
    }
    
    class AddOrderDialog extends JDialog{

        
    /**
         * 
         */
        private static final long serialVersionUID = -5436641251910399740L;
     
    
    
    public AddOrderDialog( ){
        
        JLabel infoLanel = new JLabel("订单");
        infoLanel.setBounds(105, 10, 170, 35);
        JLabel orderLabel = new JLabel("订单号");
        orderLabel.setBounds(35, 85, 100, 24);
        JTextField orderField = new JTextField();
        orderField.setBounds(145, 85, 180, 20);
        JButton cancelButton = new JButton("取消");
        cancelButton.setBounds(190, 150, 70, 30);
        JButton confirmButton = new JButton("确定");
        confirmButton.setBounds(275, 150, 70, 30);
        
        cancelButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                AddOrderDialog.this.dispose();
            }
        });
        
        confirmButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                tableModel.add(orderField.getText());
                setExpensesField();
                                    
            }
        });
        
        this.add(infoLanel);
        this.add(orderLabel);
        this.add(orderField);
        this.add(cancelButton);
        this.add(confirmButton);
        this.setLayout(null);
        this.setBounds(100, 100, 380, 240);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setVisible(true);      
    }

}
}
