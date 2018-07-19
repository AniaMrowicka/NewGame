package pojo_entity;


import entities.WordsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateConfiguration {
    public static void main(String[] args) {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(registry)
                .buildMetadata();

        try(SessionFactory sessionFactory = metadata.buildSessionFactory();
            Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();



            WordsEntity wordsEntity = new WordsEntity("dom","easy");
            session.save(wordsEntity);
            transaction.commit();
        }
    }
}
