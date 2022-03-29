package com.sergax.restapi.repository.hibernateRepositoryImplementation;

import com.sergax.restapi.model.Event;
import com.sergax.restapi.repository.EventRepository;
import com.sergax.restapi.util.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImplementation implements EventRepository {
    private Transaction transaction = null;
    private Event event = new Event();

    @Override
    public List<Event> getAll() {
        List<Event> eventList = new ArrayList<>();

        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            eventList = session.createQuery("from Event").list();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return eventList;
    }

    @Override
    public Event getById(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            event = session.get(Event.class, id);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return event;
    }

    @Override
    public void create(Event event) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Event event = session.get(Event.class, id);
            session.delete(event);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

