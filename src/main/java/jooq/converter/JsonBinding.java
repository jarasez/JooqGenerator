package jooq.converter;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jooq.BindingGetResultSetContext;
import org.jooq.BindingSetStatementContext;
import org.jooq.JSONB;
import org.jooq.impl.AbstractBinding;

import java.sql.SQLException;
import java.util.Objects;

public class JsonBinding extends AbstractBinding<JSONB, ObjectNode> {
    @Override
    public JsonNodeConverter converter() {
        return new JsonNodeConverter();
    }


    // Converting the JsonNode to a String value and setting that on a JDBC PreparedStatement
    @Override
    public void set(BindingSetStatementContext<ObjectNode> ctx) throws SQLException {
        ctx.statement().setString(ctx.index(), Objects.toString(ctx.convert(converter()).value(), null));
    }

    // Getting a String value from a JDBC ResultSet and converting that to a JsonNode
    @Override
    public void get(BindingGetResultSetContext<ObjectNode> ctx) throws SQLException {
        ctx.convert(converter()).value(ctx.resultSet().getObject(ctx.index(), JSONB.class));
    }
}