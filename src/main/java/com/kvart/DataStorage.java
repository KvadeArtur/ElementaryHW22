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
            String hql = String.format("DELETE FROM %s", User.class.getSimpleName());
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
            String hql = String.format("DELETE FROM User WHERE id = :id ", User.class);
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
            String hql = String.format("DELETE FROM User WHERE name = :name ", User.class);
            Query query = session.createQuery(hql).setParameter("name", name);
            int count = query.executeUpdate();
            transaction.commit();
            return count;
        }
    }

    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    @Override
    public User getUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM User WHERE id = :id ", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }
}
