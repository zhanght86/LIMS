 package presentation.managerui.approvalui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import presentation.managerui.approvalui.arrivalui.ArrivalPendingPanel;
import presentation.managerui.approvalui.deliverui.DeliverPendingPanel;
import presentation.managerui.approvalui.loadui.LoadPendingPanel;
import presentation.managerui.approvalui.orderui.OrderPendingPanel;
import presentation.managerui.approvalui.paymentui.PaymentPendingPanel;
import presentation.managerui.approvalui.revenueui.RevenuePendingPanel;
import presentation.managerui.approvalui.storeinui.StoreinPendingPanel;
import presentation.managerui.approvalui.storeoutui.StoreoutPendingPanel;
import presentation.managerui.approvalui.transferui.TransferPendingPanel;
 
public class ApprovalPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5845404184989335354L;
	
	public ApprovalPanel(){
		JTabbedPane approvalPane = new JTabbedPane(JTabbedPane.TOP);
		approvalPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		approvalPane.addTab("订单", new OrderPendingPanel());
        approvalPane.addTab("派件单", new DeliverPendingPanel());
		approvalPane.addTab("收款单", new RevenuePendingPanel());
		approvalPane.addTab("中转单", new TransferPendingPanel());
		approvalPane.addTab("装车单",new LoadPendingPanel());
		approvalPane.addTab("出库单",new StoreoutPendingPanel());
		approvalPane.addTab("入库单",new StoreinPendingPanel());
		approvalPane.addTab("付款单", new PaymentPendingPanel());
		approvalPane.addTab("到达单", new ArrivalPendingPanel());		
		approvalPane.setBounds(0, 0,650, 500);
		
		this.add(approvalPane);
		this.setBounds(0, 0, 650, 500);
		this.setLayout(null);
		this.setVisible(true);

	}

}
