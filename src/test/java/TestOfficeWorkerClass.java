import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorkerList;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.Messages;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.OfficeWorkerStatus;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.OfficeWorkerStatusConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class TestOfficeWorkerClass {


    @Test
    void createWorker() {
        OfficeWorker worker = new OfficeWorker();
        System.out.println(worker);
        OfficeWorker workerOnlyName = new OfficeWorker("New Office Worker");
        System.out.println(workerOnlyName);
        OfficeWorker workerFullInfo = new OfficeWorker("New Office Worker");
        System.out.println(workerFullInfo);
    }

    @Test
    void testWorkerList(){
        OfficeWorkerList.getInstance().stream().forEach(System.out::println);
    }

    @Test
    void testFormatLine() {
        System.out.println(DateTimeFormatter.ISO_DATE.toFormat());
    }

    @Test
    void testEnum(){
        System.out.println(OfficeWorkerStatus.getOfficeWorkerStatusByName("Intern"));
        //Не существует, по умолчанию должно установиться java
        System.out.println(OfficeWorkerStatus.getOfficeWorkerStatusByName("Junior"));
        System.out.println(OfficeWorkerStatus.getOfficeWorkerStatusByName("Middle"));
        System.out.println(OfficeWorkerStatus.getOfficeWorkerStatusByName("Senior"));
        System.out.println(OfficeWorkerStatus.getOfficeWorkerStatusByName("Lead"));
        System.out.println(OfficeWorkerStatus.getOfficeWorkerStatusById(0));
        System.out.println(Arrays.toString(OfficeWorkerStatus.getOfficeWorkerStatus()));
        System.out.println((new OfficeWorkerStatusConverter()).convertToDatabaseColumn(OfficeWorkerStatus.intern));
    }

    @Test
    void testEnumMessages(){
        System.out.println(Messages.OK_DEL_MSG);
        System.out.println(Messages.OK_DEL_MSG.getText());
        Messages errMSG = Messages.valueOf("OK_DEL_MSG");
        System.out.println(errMSG);
        System.out.println(errMSG.getText());
    }


}
