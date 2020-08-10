package com.example.deleteminiapps;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;


@Component
public class DeleteTables implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        main(args);
    }

    public static void main(String[] args) {

        SessionFactory sessionFactory = new HibernateFactory().getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String[] queries = new String[7];
        queries[0] = "delete from web_component_to_runtime";
        queries[1] = "delete from web_component_execution_modes";
        queries[2] = "delete from web_resources";
        queries[3] = "delete from web_components";
        queries[4] = "delete from web_component_settings";
        queries[5] = "delete from web_component_distros";
        queries[6] = "delete from web_component_runtimes";
        for(String stringQuery : queries){
            Query query = session.createSQLQuery(stringQuery);
            System.out.println("STWORZONO ZAPYTANIE");
            try{
                query.setTimeout(10);
                query.executeUpdate();
            }catch (Exception e){
                System.out.println(e.toString());
            }
            System.out.println("QUERY EXECUTED");
        }
        session.close();
        System.out.println("SESION CLOSED");
        sessionFactory.close();
        System.out.println("SESION FACTORY CLOSED");

    }
}

class HibernateFactory {
    public SessionFactory getSessionFactory() {
        SessionFactory sessionFactory =
                new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory;

    }
}


