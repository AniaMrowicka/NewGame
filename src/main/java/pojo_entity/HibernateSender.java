package pojo_entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateSender {
    public String word;
    public void readFromHangman(String chosenLevel){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            List<String> list = session.createQuery("SELECT word FROM WordsEntity WHERE level='"+chosenLevel+"' ORDER BY RAND()").setMaxResults(1).list();
            word=list.get(0);

            transaction.commit();
        }
    }
    public String getWord() {
        return word;
    }


}

