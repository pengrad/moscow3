package logic;

/**
 * User: Стас
 * Date: 16.11.2010
 * Time: 23:58:42
 */

public class Test {

    public static void main(String[] args) {
        try {
            BusinessManager bm = BusinessManager.get();
            while (true) {
                try {
                    System.in.read();
                    bm.getSession().createSQLQuery("create temporary table t1 (id int);").executeUpdate();
                    bm.getSession().createSQLQuery("load data infile 'c:/qwe.txt' INTO TABLE t1;").executeUpdate();
                    bm.getSession().createSQLQuery("drop table t1;").executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                    bm.getSession().createSQLQuery("drop table t1;").executeUpdate();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
