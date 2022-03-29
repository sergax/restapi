package com.sergax.restapi.repository.hibernateRepositoryImplementation;

import com.sergax.restapi.model.User;
import com.sergax.restapi.repository.UserRepository;
import com.sergax.restapi.util.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImplementation implements UserRepository {
    private Transaction transaction = null;
    private User user = new User();

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList();

        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("from User").list();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getById(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void create(User user) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

