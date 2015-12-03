package presentation.financeui.logui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.util.DatePickPanel;
import businesslogic.logbl.Log;
import businesslogicservice.LogblService;
import vo.LogVO;

public class LogDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5032227141174912005L;
	private JPanel parent;
	private DatePickPanel datePickPanel;

	public LogDialog(JPanel panel){
		this.parent=panel;
		
		int dialogx = 380;
		int dialogy = 240;
		this.setSize(dialogx, dialogy);
		this.setLocationRelativeTo(null);
		
		JLabel promptLabel = new JLabel("您可以输入日期进行主要操作查询:");
		promptLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		promptLabel.setSize(343,22);
		promptLabel.setLocation((dialogx-230)/2, dialogy/8);
		promptLabel.setVisible(true);
		
		int labelWidth = 80;
		int labelHeight = 25;
		int labelx = 70;
		int labely = 90;
		JLabel dateLabel = new JLabel("日期"); 
		dateLabel.setSize(labelWidth,labelHeight);
		dateLabel.setLocation(labelx, labely);

		int textFieldx = 123;
		int textFieldy = 90;

		datePickPanel = new DatePickPanel();
		datePickPanel.setLocation(textFieldx, textFieldy);

		JButton confirmButton = new JButton("确认");
		confirmButton.setBounds(280,160, 70, 30);
		confirmButton.addActionListener(new ConfirmButtonListener());
		JButton backButton = new JButton("返回");
		backButton.setBounds(190,160, 70, 30);
		backButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				LogDialog.this.dispose();
			}
		});
		
		this.setLayout(null);
		this.add(promptLabel);
		this.add(dateLabel);
		this.add(datePickPanel);
		this.add(confirmButton);
		this.add(backButton);
		
	    this.setVisible(true);
	}
	
	class ConfirmButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {		
			LogblService lbs = new Log();
			List<LogVO> vos = lbs.queryLogVO(datePickPanel.getDate());
			
			@SuppressWarnings("unused")
			LogPanel logPanel = new LogPanel(parent, vos);
			LogDialog.this.dispose();
		}
		
	}
}
