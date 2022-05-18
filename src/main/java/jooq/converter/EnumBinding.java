package jooq.converter;

import jooq.enumeration.Status;
import org.jooq.BindingGetResultSetContext;
import org.jooq.BindingSetStatementContext;
import org.jooq.Converter;
import org.jooq.impl.AbstractBinding;

import java.sql.SQLException;
import java.util.Objects;

public class EnumBinding extends AbstractBinding<String, Status> {
    @Override
    public Converter<String, Status> converter() {
        return new EnumConverter();
    }

    @Override
    public void set(BindingSetStatementContext<Status> ctx) throws SQLException {
        ctx.statement().setString(ctx.index(), Objects.toString(ctx.convert(converter()).value(), null));
    }

    @Override
    public void get(BindingGetResultSetContext<Status> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.resultSet().getString(ctx.index()));
    }
}
