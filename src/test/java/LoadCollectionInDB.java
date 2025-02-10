import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.hibernate.HibernateUtil;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.daohbn.DAOOfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorkerList;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class LoadCollectionInDB {

    @Test
    void insertCollectionInDB() {
        // Before runnig - delete table 'employee' from DB
        //Creat session == connection to DB
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n\nHIBERNATE connect to DB!!!");

        // Insert objects from collection into table 'officeworkers'
        OfficeWorkerList list = OfficeWorkerList.getInstance();
        list.stream().forEach(
                (worker) -> {
                    worker.setId(null);
                    System.out.println(worker.getName()+": "+DAOOfficeWorker.insert(worker));
                }
        );

        // SELECT data from table 'officeworker' and output it to console
        System.out.println("\n\nData from table \'officeworkers\'!!!");
        for (OfficeWorker worker : DAOOfficeWorker.getAllList()) {
            System.out.println(worker);
        }

        // Disconnect from DB
        System.out.println("\n\nHIBERNATE halt!!!");
        HibernateUtil.shutdown();
    }


}
