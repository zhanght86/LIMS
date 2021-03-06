package presentation.util;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import systemenum.CheckResult;
import businesslogic.checkbl.CheckInfo;
import businesslogic.checkbl.CheckResultMessage;

/**
 * 界面层的检查类，提供了检查服务的界面展示以及调用检查业务逻辑的入口
 * @author 林祖华
 * @version 1.7
 * @see businesslogic.checkbl.CheckInfo
 * @see businesslogic.checkbl.CheckResultMessage
 * @see CheckInfoGetter
 *
 */
public class Checker extends JLabel{

    /**
     * 
     */
    private static final long serialVersionUID = -1601645180593740224L;
    
    private static final ImageIcon CORRECT_ICON = 
            new ImageIcon(Checker.class.getResource("image/correct.png"));
    private static final ImageIcon FALSE_ICON = 
            new ImageIcon(Checker.class.getResource("image/false.png"));
    private static final ImageIcon WARNING_ICON = 
            new ImageIcon(Checker.class.getResource("image/warning.png"));
    
    private CheckResult checkResult;
    private CheckInfoGetter checkInfoGetter;

    public Checker(){
        this.setSize(25, 25);
        checkResult = CheckResult.UNCHECKED;
    }
    
    public Checker(JComponent infoComponent, CheckInfoGetter checkInfoGetter){
        this();
        this.setLocation(infoComponent.getX()+infoComponent.getWidth()+10, infoComponent.getY());
        infoComponent.addComponentListener(new ComponentListener() {
            
            @Override
            public void componentShown(ComponentEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void componentResized(ComponentEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void componentMoved(ComponentEvent e) {
                Checker.this.setLocation(infoComponent.getX()+infoComponent.getWidth()+10, infoComponent.getY());
            }
            
            @Override
            public void componentHidden(ComponentEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        infoComponent.getParent().add(this);
        this.checkInfoGetter = checkInfoGetter;
    }
    
    public boolean check(){
        CheckInfo checkInfo = checkInfoGetter.getCheckInfo();
        CheckResultMessage checkResultMessage = checkInfo.check();
        showResult(checkResultMessage);
        return isCorrect();
    }
    
    private void showResult(CheckResultMessage checkResultMessage){
        this.checkResult = checkResultMessage.getCheckResult();
        this.setToolTipText(checkResultMessage.getCheckInfo());
        
        ImageIcon imageIcon = null;
        switch (checkResult) {
        case CORRECT:
            imageIcon = CORRECT_ICON;
            break;
        case WARNING:
            imageIcon = WARNING_ICON;
            break;
        case FALSE:
            imageIcon = FALSE_ICON;
            break;
        default:
            break;
        }
        this.setIcon(imageIcon);
    }

    public boolean isCorrect(){
        return (checkResult != CheckResult.FALSE)&&(checkResult != CheckResult.UNCHECKED);
    }
    
    public void discard(){
        this.getParent().remove(this);
    }
    
}
