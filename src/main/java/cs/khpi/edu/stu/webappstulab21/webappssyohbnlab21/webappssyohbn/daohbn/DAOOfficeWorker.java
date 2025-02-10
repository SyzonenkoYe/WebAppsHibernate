package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.daohbn;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.hibernate.HibernateUtil;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.Messages;



public class DAOOfficeWorker {


    public static List<OfficeWorker> getAllList() {
        List<OfficeWorker> results = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(OfficeWorker.class);
            Root rootEntry = cq.from(OfficeWorker.class);
            CriteriaQuery all = cq.select(rootEntry);
            TypedQuery allQuery = session.createQuery(all);
            results = allQuery.getResultList();
        }
        return results;
    }


    // Метод для отримання OfficeWorker за ID
    public static OfficeWorker getOfficeWorkerById(Long idToFind) {
        OfficeWorker workerInDB = null;
        List<OfficeWorker> results;
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<OfficeWorker> cq = cb.createQuery(OfficeWorker.class);
        Root<OfficeWorker> rootEntry = cq.from(OfficeWorker.class);
        cq.select(rootEntry).where(cb.equal(rootEntry.get("id"), idToFind));
        Query<OfficeWorker> query = session.createQuery(cq);
        results = query.getResultList();
        if (!results.isEmpty()) {
            workerInDB = results.get(0);
        }
        return workerInDB;
    }

    // Метод для отримання OfficeWorker за унікальними полями (наприклад, name)
    public static OfficeWorker getOfficeWorkerByKeyset(OfficeWorker workerKey) {
        OfficeWorker findWorker = null;
        List<OfficeWorker> results;
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<OfficeWorker> cq = cb.createQuery(OfficeWorker.class);
        Root<OfficeWorker> rootEntry = cq.from(OfficeWorker.class);
        cq.select(rootEntry).where(cb.equal(rootEntry.get("name"), workerKey.getName()));
        Query<OfficeWorker> query = session.createQuery(cq);
        results = query.getResultList();
        if (!results.isEmpty()) {
            findWorker = results.get(0);
        }
        return findWorker;
    }

    // Метод для додавання нового OfficeWorker
    public static String insert(OfficeWorker workerForInsert) {
        String msgIns = Messages.OK_INS_MSG.getText();
        Session session = null;
        OfficeWorker workerInDB = DAOOfficeWorker.getOfficeWorkerByKeyset(workerForInsert);
        if (workerInDB == null) {
            Transaction transaction = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                session.persist(workerForInsert);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                msgIns = Messages.ERR_INS_MSG.getText();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        } else {
            msgIns = Messages.ERR_DOUBLE_MSG.getText();
        }
        return msgIns;
    }

    // Метод для оновлення існуючого OfficeWorker
    public static String update(OfficeWorker workerForUpdate, Long idWorkerToUpdate) {
        String msgUp = Messages.OK_UPD_MSG.getText();
        OfficeWorker workerForEdit = getOfficeWorkerById(idWorkerToUpdate);
        if (workerForEdit == null) {
            msgUp = Messages.ERR_NOT_IN_MSG.getText();
        } else {
            OfficeWorker workerInDB = DAOOfficeWorker.getOfficeWorkerByKeyset(workerForUpdate);
            if (workerInDB == null || (workerInDB != null && workerInDB.getId().equals(idWorkerToUpdate))) {
                Transaction transaction = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    workerForUpdate.setId(workerForEdit.getId());
                    transaction = session.beginTransaction();
                    session.merge(workerForUpdate);
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    msgUp = Messages.ERR_UPD_MSG.getText();
                }
            } else {
                msgUp = Messages.ERR_DOUBLE_MSG.getText();
            }
        }
        return msgUp;
    }

    // Метод для видалення OfficeWorker за ID
    public static String delete(Long idWorkerToDelete) {
        String msgDel = Messages.OK_DEL_MSG.getText();
        OfficeWorker workerForDel = getOfficeWorkerById(idWorkerToDelete);
        if (workerForDel == null) {
            msgDel = Messages.ERR_NOT_IN_MSG.getText();
        } else {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.remove(workerForDel);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                msgDel = Messages.ERR_DEL_MSG.getText();
            }
        }
        return msgDel;
    }

    public static boolean checkDuplicate(OfficeWorker workerKey) {
        List<OfficeWorker> results;
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<OfficeWorker> cq = cb.createQuery(OfficeWorker.class);
        Root<OfficeWorker> rootEntry = cq.from(OfficeWorker.class);
        cq.select(rootEntry).where(
                cb.equal(rootEntry.get("surname"), workerKey.getSurname()),
                cb.equal(rootEntry.get("name"), workerKey.getName()),
                cb.equal(rootEntry.get("pname"), workerKey.getPname())
        );

        Query<OfficeWorker> query = session.createQuery(cq);
        results = query.getResultList();

        session.close();
        return !results.isEmpty();
    }


}

