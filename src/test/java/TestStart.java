import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.Messages;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.OfficeWorkerStatus;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.hibernate.HibernateUtil;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.daohbn.DAOOfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestStart {
    private static SessionFactory sf;
    private static Session session;
    private Transaction transaction;
    static DAOOfficeWorker daoofficeworker = new DAOOfficeWorker();
    @BeforeAll
    static void beforeAll (){
        sf= HibernateUtil.getSessionFactory();
        session = sf.openSession();
        System.out.println("\n\nHIBERNATE підключився!");
    }
    @Test
    void testCreate() {
        System.out.println("Має бути створена БД!");
    }

    @Test
    void testCRUD() {
        // 1. Create an OfficeWorker instance and add it to the database
        OfficeWorker workerToCreate = new OfficeWorker();
        workerToCreate.setSurname("Smith");
        workerToCreate.setName("John");
        workerToCreate.setPname("Doe");
        workerToCreate.setStartWork(LocalDate.of(2023, 1, 1));
        workerToCreate.setEndWork(LocalDate.of(2023, 12, 31));
        workerToCreate.setWorkerCod("1034");
        workerToCreate.setOfficeWorkerStatus(OfficeWorkerStatus.middle);

        // Insert the new worker
        String insertMessage = DAOOfficeWorker.insert(workerToCreate);
        assertEquals(Messages.OK_INS_MSG.getText(), insertMessage);

        // 2. Retrieve the worker by unique key fields
        OfficeWorker retrievedWorker = DAOOfficeWorker.getOfficeWorkerByKeyset(workerToCreate);
        assertNotNull(retrievedWorker);
        assertEquals(workerToCreate.getName(), retrievedWorker.getName());
        assertEquals(workerToCreate.getSurname(), retrievedWorker.getSurname());

        // 3. Update the worker
        retrievedWorker.setName("Jane");
        String updateMessage = DAOOfficeWorker.update(retrievedWorker, retrievedWorker.getId());
        assertEquals(Messages.OK_UPD_MSG.getText(), updateMessage);

        // Verify the update
        OfficeWorker updatedWorker = DAOOfficeWorker.getOfficeWorkerById(retrievedWorker.getId());
        assertEquals("Jane", updatedWorker.getName());

        // 4. Delete the worker
        String deleteMessage = DAOOfficeWorker.delete(updatedWorker.getId());
        assertEquals(Messages.OK_DEL_MSG.getText(), deleteMessage);

        // Verify the deletion
        OfficeWorker deletedWorker = DAOOfficeWorker.getOfficeWorkerById(updatedWorker.getId());
        assertNull(deletedWorker);
    }



    @AfterAll
    static void afterAll(){
        System.out.println("\n\nHIBERNATE завершив роботу");
        HibernateUtil.shutdown();
    }
}
