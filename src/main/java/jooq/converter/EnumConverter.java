package jooq.converter;

import jooq.enumeration.Status;
import org.jooq.impl.AbstractConverter;

public class EnumConverter extends AbstractConverter<String, Status> {
    public EnumConverter() {
        super(String.class, Status.class);
    }

    @Override
    public Status from(String databaseObject) {
        return Status.valueOf(databaseObject);
    }

    @Override
    public String to(Status userObject) {
        return userObject.name();
    }
}
