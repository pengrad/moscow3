/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd;

import rzd.MainFrame;

/**
 *
 * @author ЧерныхЕА
 */
public class ConntrollerMain {

    private final static ConntrollerMain cm = new ConntrollerMain();
    private MainFrame mf;
    public static ConntrollerMain getInstans() {
        return cm;
    }

    private ConntrollerMain() {
    mf=new MainFrame();
    }

    public void updateStation(){
    
    }
}
