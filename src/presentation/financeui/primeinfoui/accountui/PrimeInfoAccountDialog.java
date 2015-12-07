package presentation.financeui.primeinfoui.accountui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import vo.AccountVO;

public class PrimeInfoAccountDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2959527949771784570L;
	private static final String[] LABEL_NAMES = {"�˻����","�˻�����","�˻����"};
    
    private PrimeInfoAccountTableModel tableModel;
    private JTextField[] textFields;
    
    public PrimeInfoAccountDialog(PrimeInfoAccountTableModel tm ){
        this.tableModel = tm;

        JLabel[] labels = new JLabel[3];
        for(int i=0;i<labels.length;i++){
            labels[i] = new JLabel();
            labels[i].setText(LABEL_NAMES[i]);
            labels[i].setBounds(70, 40+40*i, 100, 25);
            this.add(labels[i]);
        }
        
        textFields = new JTextField[3];
        for(int i=0;i<textFields.length;i++){
            textFields[i] = new JTextField();           
            if(i==2)
            	textFields[i].setBounds(140, 40+40*i, 90, 25);
            else
            	textFields[i].setBounds(140, 40+40*i, 180, 25);
            this.add(textFields[i]);
        }
        JButton confirmButton = new JButton("ȷ��");
        confirmButton.setBounds(250, 170, 70, 30);
        confirmButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	AccountVO vo = new AccountVO(textFields[0].getText(),textFields[1].getText(),Double.parseDouble(textFields[2].getText()));
                tableModel.create(vo);
                System.out.println("you've clicked confirm button..");
                PrimeInfoAccountDialog.this.dispose();
            }
        });
        JButton cancleButton = new JButton("ȡ��");
        cancleButton.setBounds(160, 170, 70, 30);
        cancleButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	PrimeInfoAccountDialog.this.dispose();
            }
        });
        this.add(confirmButton);
        this.add(cancleButton);
        
        this.setSize(380, 260);
        this.setTitle("�˻���Ϣ");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
    }
    
    public PrimeInfoAccountDialog(PrimeInfoAccountTableModel tm  , int modelRow ,boolean isEditable){
          	this.tableModel = tm;

	        JLabel[] labels = new JLabel[3];
	        for(int i=0;i<labels.length;i++){
	            labels[i] = new JLabel();
	            labels[i].setText(LABEL_NAMES[i]);
	            labels[i].setBounds(70, 40+40*i, 100, 25);
	            this.add(labels[i]);
	        }
	        
	        textFields = new JTextField[3];
	        for(int i=0;i<textFields.length;i++){
	            textFields[i] = new JTextField();           
	            if(i==2)
	            	textFields[i].setBounds(140, 40+40*i, 90, 25);
	            else
	            	textFields[i].setBounds(140, 40+40*i, 180, 25);
	            this.add(textFields[i]);
	        }

        	AccountVO vo = tableModel.getAccountVO(modelRow);
        	textFields[0].setText(vo.getId());
        	textFields[1].setText(vo.getName());
        	textFields[2].setText(String.format("%.2f",vo.getMoney()));
            JButton backButton = new JButton("����");
            backButton.setBounds(160, 170, 70, 30);
            backButton.addActionListener(new ActionListener() {
                
                @Override
                public void actionPerformed(ActionEvent e) {
                	PrimeInfoAccountDialog.this.dispose();
                }
            });
            JButton confirmButton = new JButton("ȷ��");
	        confirmButton.setBounds(250, 170, 70, 30);
	        confirmButton.addActionListener(new ActionListener() {
	            
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if(!isEditable){
	                	PrimeInfoAccountDialog.this.dispose();
	                }
	                AccountVO vo = new AccountVO(textFields[0].getText(),textFields[1].getText(),Double.parseDouble(textFields[2].getText()));
	                tableModel.modify(modelRow, vo);
	                PrimeInfoAccountDialog.this.dispose();
	                
	            }
	        });
	        if(isEditable){
	            JButton cancleButton = new JButton("ȡ��");
	            cancleButton.setBounds(160, 170, 70, 30);
	            cancleButton.addActionListener(new ActionListener() {
	                
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	PrimeInfoAccountDialog.this.dispose();
	                }
	            });
	            this.add(cancleButton);
	        }
	        this.add(confirmButton);
 
      
        this.setSize(380, 260);
        this.setTitle("�˻���Ϣ");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
    }


}