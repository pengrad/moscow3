import logic.model.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new AnnotationConfiguration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        Session session = getSession();
//        TrainEntity train = (TrainEntity) session.get(TrainEntity.class, 4);
//        CarEntity car = (CarEntity) session.get(CarEntity.class, 111);
//        CarLocationEntity cl = (CarLocationEntity) session.get(CarLocationEntity.class, 1);
//        System.out.println(cl.getIdLocation());
        session.delete(null);
//        for(TrainCarsEntity tc : car.getTrainCarsesByNumber()) {
//            System.out.println(tc.getTrainByIdTrain().getRouteByIdRoute().getPointDeparture());
//        }
//        Timestamp time = new Timestamp(new Date().getTime());
//        CarLocationEntity loc = new CarLocationEntity(null, null, null);
//        CarEntity car = new CarEntity(111, time, time, loc);
//        RouteEntity route = new RouteEntity("fff", "Kursk", "Kiev");
//        TrainEntity train = new TrainEntity(time, time, route);
//        session.beginTransaction();
//        session.saveOrUpdate(loc);
//        session.saveOrUpdate(car);
//        session.saveOrUpdate(route);
//        session.saveOrUpdate(train);
//        TrainEntity train = (TrainEntity) session.get(TrainEntity.class, 6);
//        CarEntity car = (CarEntity) session.get(CarEntity.class, 111);
//        System.out.println(car.getDateUpdateLocation());
//        System.out.println(train.getRouteByIdRoute().getPointDeparture());
//        TrainCarsEntity cars = new TrainCarsEntity();
//        cars.setCarByIdCar(car);
//        cars.setTrainByIdTrain(train);
//        session.saveOrUpdate(cars);
//        session.getTransaction().commit();`
        session.close();
    }
}
