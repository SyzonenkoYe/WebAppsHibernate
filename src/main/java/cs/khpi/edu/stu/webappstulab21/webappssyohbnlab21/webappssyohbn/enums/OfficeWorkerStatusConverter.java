package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums;

import jakarta.persistence.AttributeConverter;

public class OfficeWorkerStatusConverter implements AttributeConverter<OfficeWorkerStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(OfficeWorkerStatus pl) {
        return pl.ordinal();
    }


    @Override
    public OfficeWorkerStatus convertToEntityAttribute(Integer codPL) {
        return OfficeWorkerStatus.getOfficeWorkerStatusById(codPL);
    }
}
