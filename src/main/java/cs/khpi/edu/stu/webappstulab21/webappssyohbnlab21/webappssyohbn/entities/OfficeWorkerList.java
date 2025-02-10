package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities;

import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.OfficeWorkerStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class OfficeWorkerList extends ArrayList<OfficeWorker> {
    private static final long serialVersionUID = 1L;
    private static OfficeWorkerList instance;

    private OfficeWorkerList() {
    }

    public static OfficeWorkerList getInstance() {
        if (instance == null) {
            instance = new OfficeWorkerList();

            instance.add(new OfficeWorker(1L, "Shevchenko", "Oleksandr", "Ivanovych", LocalDate.now().minusMonths(3), null, "1234", OfficeWorkerStatus.intern));
            instance.add(new OfficeWorker(2L,"Petrenko", "Mariya", "Andriyivna", LocalDate.now().minusYears(2).minusMonths(3), null, "2345", OfficeWorkerStatus.junior));
            instance.add(new OfficeWorker(3L, "Koval", "Mykola", "Petrovych", LocalDate.now().minusYears(3).minusMonths(4).minusDays(23), null, "3456", OfficeWorkerStatus.middle));
            instance.add(new OfficeWorker(4L, "Boyko", "Olha", "Andriyivna", LocalDate.now().minusYears(1).minusMonths(7).minusDays(4), LocalDate.now().minusMonths(1), "4567", OfficeWorkerStatus.senior));
            instance.add(new OfficeWorker(5L, "Vasylenko", "Viktor", "Mykolayovych", LocalDate.now().minusYears(5).minusMonths(4).minusDays(23), LocalDate.now().minusYears(1), "5678", OfficeWorkerStatus.lead));
            instance.add(new OfficeWorker(6L, "Hrytsenko", "Iryna", "Serhiyivna", LocalDate.now().minusMonths(4).minusDays(23), null, "6789", OfficeWorkerStatus.middle));
            instance.add(new OfficeWorker(7L, "Tkachenko", "Dmytro", "Oleksandrovych", LocalDate.now().minusYears(2).minusMonths(0).minusDays(10), null, "7890", OfficeWorkerStatus.junior));
            instance.add(new OfficeWorker(8L, "Semenyuk", "Olena", "Vasylivna", LocalDate.now().minusYears(2).minusMonths(4).minusDays(23), null, "8901", OfficeWorkerStatus.lead));
            instance.add(new OfficeWorker(9L,"Zubenko", "Lyudmyla", "Olehivna", LocalDate.now().minusYears(3).minusMonths(0).minusDays(23), LocalDate.now().minusYears(2), "9012", OfficeWorkerStatus.intern));
            instance.add(new OfficeWorker(10L, "Fedorenko", "Ivan", "Semenovych", LocalDate.now().minusYears(11).minusMonths(8).minusDays(23), LocalDate.now().minusYears(9).minusMonths(4), "0123", OfficeWorkerStatus.junior));
        }
        return instance;
    }

}
