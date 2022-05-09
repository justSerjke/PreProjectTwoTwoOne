package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        Car car = null;
        try {
            String HQL = "from Car where model=?0 and series=?1";
            Query query = sessionFactory.getCurrentSession()
                    .createQuery(HQL)
                    .setParameter(0, model)
                    .setParameter(1, series);
            car = (Car) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("User not found");
        }
        return car != null ? car.getUser() : null;
    }


//      try(Session session = HibernateUtil.getSessionFactory().openSession()) {
//         String HQL="FROM Address addr LEFT OUTER JOIN FETCH addr.employee WHERE addr.addressId=:addrId";
//         Address address = session.createQuery(HQL, Address.class).setParameter("addrId", 1).uniqueResult();
//         System.out.println(address);
//         System.out.println(address.getEmployee());
//      } catch (HibernateException e) {
//         e.printStackTrace();
//      }
}

