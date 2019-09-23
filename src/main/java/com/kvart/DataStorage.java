package com.kvart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DataStorage implements Storage {

    private SessionFactory sessionFactory;

    public DataStorage() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public int removeAll() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", Users.class.getSimpleName());
            Query query = session.createQuery(hql);
            int count = query.executeUpdate();
            transaction.commit();
            return count;
        }
    }

    @Override
    public int removeUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM users WHERE id = :id ", Users.class);
            Query query = session.createQuery(hql).setParameter("id", id);
            int count = query.executeUpdate();
            transaction.commit();
            return count;
        }
    }

    @Override
    public int removeUserByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM users WHERE name = :name ", Users.class);
            Query query = session.createQuery(hql).setParameter("name", name);
            int count = query.executeUpdate();
            transaction.commit();
            return count;
        }
    }

    @Override
    public void addUser(Users user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void updateUser(Users user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public Users getUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM users WHERE id = :id ", Users.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    @Override
    public List<Users> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM users", Users.class).list();
        }
    }
}
