package presentation.managerui.approvalui.deliverui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import presentation.util.ScreenMessage;

/**
 * 审批时派件单的{@code Jpanel}，提供派件单的查看、修改、审批功能
 * @author 林祖华
 * @version 1.4
 *
 */
public class DeliverPendingPanel extends JPanel {



	/**
     * 
     */
    private static final long serialVersionUID = 4846680007628719065L;

    private JScrollPane deliverPendingScrollPane;

	private JTable deliverPendingTable;
	private DeliverPendingTableModel tableModel;
	private TableRowSorter<TableModel> tableSorter;

	private JButton approveButton;
	private JButton modifyButton;
	private JButton queryButton;
    private JToggleButton toggleButton;
    ArrayList<Integer> indexes = new ArrayList<Integer>();
	

	public DeliverPendingPanel() {

		tableModel = new DeliverPendingTableModel();
		tableSorter = new TableRowSorter<TableModel>(tableModel);
		deliverPendingTable = new JTable(tableModel);
		deliverPendingTable.setSize(800, 500);
		deliverPendingTable.setRowSorter(tableSorter);

		deliverPendingScrollPane = new JScrollPane(deliverPendingTable);
		deliverPendingScrollPane.setBounds(0, 0, 650, 390);

	    toggleButton = new JToggleButton("批量审批");
		approveButton = new JButton("审批");
		modifyButton = new JButton("修改");
		queryButton = new JButton("详情");

        toggleButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                indexes.clear();
                deliverPendingTable.clearSelection();
            }
        });
        approveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = deliverPendingTable.getSelectedRow();
                if(row == -1){
                    ScreenMessage.putOnScreen(ScreenMessage.NO_CHOOSE_IN_TABLE);
                    return;
                }
                int indexesNum = deliverPendingTable.getSelectedRowCount();
                int[] selectedRows = deliverPendingTable.getSelectedRows();
                for(int i=0;i<indexesNum;i++){
                    for(int j=i+1;j<indexesNum;j++){
                        selectedRows[j] -= 1;
                    }
                    int firstSelectedRow = selectedRows[i];
                    int modelRow = deliverPendingTable.convertRowIndexToModel(firstSelectedRow);
                    tableModel.approve(modelRow);
                }
                ScreenMessage.putOnScreen(ScreenMessage.SAVE_SUCCESS);
                indexes.clear();
                deliverPendingTable.clearSelection();
            }
        });

		modifyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = deliverPendingTable.getSelectedRow();
                if(row == -1){
                    ScreenMessage.putOnScreen(ScreenMessage.NO_CHOOSE_IN_TABLE);
                    return;
                }
				int modelRow = deliverPendingTable.convertRowIndexToModel(row);
				new DeliverPendingDialog(tableModel, modelRow, true);
			}
		});
		queryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = deliverPendingTable.getSelectedRow();
                if(row == -1){
                    ScreenMessage.putOnScreen(ScreenMessage.NO_CHOOSE_IN_TABLE);
                    return;
                }
				int modelRow = deliverPendingTable.convertRowIndexToModel(row);
				new DeliverPendingDialog(tableModel, modelRow, false);
			}
		});
		
          deliverPendingTable.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    if(!toggleButton.isSelected())
                        return;
                    int clicked = deliverPendingTable.getSelectedRow();
                    if(indexes.contains(clicked)){
                        indexes.remove(new Integer(clicked));
                    }else{
                        indexes.add(clicked);
                    }
                    deliverPendingTable.clearSelection();
    
                    for(int i:indexes){
                        deliverPendingTable.addRowSelectionInterval(i, i);
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
    
                }
            });
	        
	        
          toggleButton.setBounds(320, 400, 70, 30);       
          approveButton.setBounds(405, 400, 70, 30);
          modifyButton.setBounds(490, 400, 70, 30);
          queryButton.setBounds(575, 400, 70, 30);
        


		// set panel
		this.setBounds(0, 0, 650, 470);
		this.setLayout(null);
		this.add(deliverPendingScrollPane);
	    this.add(toggleButton);
		this.add(approveButton);
		this.add(modifyButton);
		this.add(queryButton);
	}
}
