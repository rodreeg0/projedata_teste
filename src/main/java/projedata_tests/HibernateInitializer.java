package projedata_tests;


import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import java.math.BigDecimal;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import projedata_tests.entity.Funcionario;
import projedata_tests.entity.Pessoa;

public class HibernateInitializer {
    private SessionFactory sessionFactory;

    public HibernateInitializer() {
        
        // Initialize Hibernate and configure SessionFactory here
        Configuration config = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Pessoa.class)
            .addAnnotatedClass(Funcionario.class);
        this.sessionFactory = config.buildSessionFactory();

        if (!hasFuncionarioRecords()){
            populateDatabase();
        }
        
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
    public boolean hasFuncionarioRecords() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(f) FROM Funcionario f", Long.class);
            Long count = query.uniqueResult();
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> getAllFuncionarios() {
        try (Session session = sessionFactory.openSession()) {
            Query<Funcionario> query = session.createQuery("FROM Funcionario", Funcionario.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void populateDatabase() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            List<Funcionario> funcionarios = new ArrayList<>();
            
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "João", LocalDate.of(1985, 5, 15), new BigDecimal("3500.00"), "Analista"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
            funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

            funcionarios.forEach(funcionario -> {
                session.save(funcionario); 
            });
            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Banco populado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
