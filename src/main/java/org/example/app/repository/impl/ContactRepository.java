package org.example.app.repository.impl;

import org.example.app.config.HibernateUtil;
import org.example.app.domain.contact.Contact;
import org.example.app.repository.AppRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactRepository implements AppRepository<Contact> {

    private static final Logger LOGGER =
            Logger.getLogger(ContactRepository.class.getName());

    @Override
    public void create(Contact contact) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "INSERT INTO Contact (setName, setPhone) " +
                    "VALUES (:setName, :setphone)";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("Name", contact.getName());
            query.setParameter("phone", contact.getPhone());
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public Optional<List<Contact>> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction;
            transaction = session.beginTransaction();
            List<Contact> list =
                    session.createQuery("FROM Contact", Contact.class).list();
            transaction.commit();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Contact> fetchById(Long id) {
        Transaction transaction;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Contact> query = session.createQuery("FROM Contact WHERE id = :id", Contact.class);
            query.setParameter("id", id);
            query.setMaxResults(1);
            Contact product = query.uniqueResult();
            transaction.commit();
            return Optional.ofNullable(product);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public void update(Long id, Contact product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "UPDATE Contact SET Name = :sName," +
                    " phone = :phone"  +
                    " WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("Name", product.getName());
            query.setParameter("phone", product.getPhone());
            query.setParameter("id", id);
            query.executeUpdate();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Contact WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    // ---- Utils ----------------------

    public boolean isIdExists(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Contact product = new Contact();
            product.setId(id);
            product = session.get(Contact.class, product.getId());

            if (product != null) {
                Query<Contact> query = session.createQuery("FROM Contact", Contact.class);
                query.setMaxResults(1);
                query.getResultList();
            }
            return product != null;
        }
    }

    public Optional<Contact> getLastEntity() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Contact> query = session.createQuery("FROM Contact ORDER BY id DESC", Contact.class);
            query.setMaxResults(1);
            Contact contact = query.uniqueResult();
            transaction.commit();
            return Optional.of(contact);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.log(Level.WARNING, e.getMessage(), e);
            return Optional.empty();
        }
    }
}
