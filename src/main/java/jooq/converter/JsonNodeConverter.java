package jooq.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jooq.JSONB;
import org.jooq.impl.AbstractConverter;

import java.io.IOException;
import java.util.Objects;

public class JsonNodeConverter extends AbstractConverter<JSONB, ObjectNode> {

    private ObjectMapper objectMapper;

    public JsonNodeConverter() {
        super(JSONB.class, ObjectNode.class);
        objectMapper = new ObjectMapper();
    }

    @Override
    public ObjectNode from(JSONB object) {

        if (Objects.isNull(object)) {
            return null;
        }

        try {
            return (ObjectNode) objectMapper.readTree(object.data());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public JSONB to(ObjectNode jsonNode) {

        if (Objects.isNull(jsonNode)) {
            return null;
        }

        try {
            return JSONB.jsonb(objectMapper.writeValueAsString(jsonNode));
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}