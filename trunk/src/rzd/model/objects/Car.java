/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rzd.model.objects;

import com.sun.org.apache.xpath.internal.operations.Equals;

/**
 *
 * @author ЧерныхЕА
 */
public class Car {

    private int number;

    public Car(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString(){
    return  new Integer(number).toString();
    }
    public boolean  equals(Object o){
    if(o==null|| !(o instanceof Car)) return false;
   if(number==((Car)o).getNumber()) return true;
   else return false;
    }



}
