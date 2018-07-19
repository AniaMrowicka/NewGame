package pojo_entity;

import entities.Level;
import entities.WordsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class WordsEntityDao {
    private static Logger logger = LoggerFactory.getLogger(WordsEntityDao.class);
    private final SessionFactory sessionFactory;

    public WordsEntityDao() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(WordsEntity.class)
                .buildMetadata();

        sessionFactory = metadata.buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void save(WordsEntity wordsEntity, Session session) {
        Transaction transaction = session.beginTransaction();
        logger.info("Saving: {}", wordsEntity);
        session.save(wordsEntity);
        logger.info("Saving to db: {}", wordsEntity);
        transaction.commit();
    }

    public WordsEntity update(WordsEntity wordsEntity) {
        try (SessionFactory sessionFactory = getSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            logger.info("Updating: {}", wordsEntity);
            session.update(wordsEntity);
            logger.info("Updating in db: {}", wordsEntity);
            transaction.commit();
        }
        return wordsEntity;
    }

    public void delete(WordsEntity wordsEntity) {
        try (SessionFactory sessionFactory = getSessionFactory();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            logger.info("Deleting: {}", wordsEntity);
            session.delete(wordsEntity);
            logger.info("Deleting from db: {}", wordsEntity);
            transaction.commit();
        }
    }

    public static void main(String[] args) {

        WordsEntityDao wed = new WordsEntityDao();

        try (Session session = wed.sessionFactory.openSession()) {
            List<WordsEntity> list = new ArrayList();

            list.add(new WordsEntity("PIES", "easy"));
            list.add(new WordsEntity("KOT", "easy"));
            list.add(new WordsEntity("KWIAT", "easy"));
            list.add(new WordsEntity("NOS", "easy"));
            list.add(new WordsEntity("KRZAK", "easy"));
            list.add(new WordsEntity("NIC", "easy"));
            list.add(new WordsEntity("DAWNO", "easy"));
            list.add(new WordsEntity("OKNO", "easy"));
            list.add(new WordsEntity("KOC", "easy"));
            list.add(new WordsEntity("MYSZ", "easy"));
            list.add(new WordsEntity("KSIĘGA", "medium"));
            list.add(new WordsEntity("KOSZYK", "medium"));
            list.add(new WordsEntity("BOCIAN", "medium"));
            list.add(new WordsEntity("ZABAWKA", "medium"));
            list.add(new WordsEntity("BAŁWAN", "medium"));
            list.add(new WordsEntity("ROWER", "medium"));
            list.add(new WordsEntity("CHMURA", "medium"));
            list.add(new WordsEntity("DYWAN", "medium"));
            list.add(new WordsEntity("OMLETY", "medium"));
            list.add(new WordsEntity("DZIWNY", "medium"));
            list.add(new WordsEntity("DZWONNIK", "medium"));
            list.add(new WordsEntity("SUSZARKA", "medium"));
            list.add(new WordsEntity("KARTOFEL", "hard"));
            list.add(new WordsEntity("WYCIERACZKA", "hard"));
            list.add(new WordsEntity("AGNIESZKA", "hard"));
            list.add(new WordsEntity("ONOMATOPEJA", "hard"));
            list.add(new WordsEntity("KALENDARIUM", "hard"));
            list.add(new WordsEntity("PLANETARIUM", "hard"));
            list.add(new WordsEntity("ASTRONAUTA", "hard"));
            list.add(new WordsEntity("WŚCIEKŁY", "hard"));
            list.add(new WordsEntity("KOSMONAUTA", "hard"));
            list.add(new WordsEntity("SZCZEBRZESZYNO", "hard"));
            list.add(new WordsEntity("MAŁŻEŃSWTO", "hard"));
            list.add(new WordsEntity("MALEŃSTWO", "hard"));
            list.add(new WordsEntity("PUCHATEK", "hard"));
            list.add(new WordsEntity("MIKSER", "hard"));
            list.add(new WordsEntity("TELEWIZOR", "hard"));
            list.add(new WordsEntity("SATELITA", "hard"));
            list.add(new WordsEntity("SESJA", "medium"));


            list.stream()
                    .forEach(words -> wed.save(words, session));
            wed.sessionFactory.close();

        }
    }


}
