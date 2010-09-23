/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rzd.utils;

public class KeyGenerator {
    private static int i = 0;

    public static int getKey() {
        return i++;
    }
}
