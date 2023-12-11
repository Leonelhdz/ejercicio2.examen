package cesur.examen.domain.client;

import cesur.examen.common.HibernateUtil;
import cesur.examen.domain.car.Car;
import cesur.examen.domain.car.CarDAO;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Francisco Leonel Soriano Hernandez
 * Fecha: 11/12/2023
 */

public class ClientService {

    /**
     * Return a List of Client entities that have one or more cars of the given manufacturer.
     * If a client has more than one car of the manufacturer, it only appears once in
     * the list (similar to a Set). Tip: start querying to Car entities...
     *
     * @param manufacturer
     * @return the list of clients
     */
    public static List<Client> hasManufacturer(String manufacturer){
        var out= new ArrayList<Client>(0);

        List<Car> carsByManufacturer = CarDAO.getAllByManufacturer(manufacturer);

        Set<Client> customer = new HashSet<>();

        for (Car car : carsByManufacturer){
            customer.add(car.getClient());
        }
        out.addAll(customer);

        return  out;


        /* Implement method here */
        /*Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String Query = "select distinct car.client from Car car where car.manufacturer = :manufacturer";
            Query<Client> query = session.createQuery(Query, Client.class);
            query.setParameter("manufacturer", manufacturer);
            out = new ArrayList<>(query.getResultList());
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }


        return out;

         */
    }
}
