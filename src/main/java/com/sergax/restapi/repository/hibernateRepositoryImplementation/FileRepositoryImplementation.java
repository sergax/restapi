package com.sergax.restapi.repository.hibernateRepositoryImplementation;

import com.sergax.restapi.model.File;
import com.sergax.restapi.repository.FileRepository;
import com.sergax.restapi.util.HibernateConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class FileRepositoryImplementation implements FileRepository {
    private Transaction transaction = null;
    private File file = new File();

    @Override
    public List<File> getAll() {
        List<File> fileList = new ArrayList<>();

        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            fileList = session.createQuery("FROM File").getResultList();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileList;
    }

    @Override
    public File getById(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            file = session.get(File.class, id);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return file;
    }

    @Override
    public void create(File file) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(file);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(File file) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(file);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            File fileDelete = session.get(File.class, id);
            session.delete(fileDelete);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

