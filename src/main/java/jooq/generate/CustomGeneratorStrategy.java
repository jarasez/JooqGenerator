package jooq.generate;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

import java.util.List;

public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {
    public static final String POJO_PACKAGE_NAME = "jooq.domain.impl";
    public static final String INTERFACE_PACKAGE_NAME = "jooq.api.domain";
    public static final String ENUM_PACKAGE_NAME = "jooq.enumeration";

    @Override
    public List<String> getJavaClassImplements(Definition definition, Mode mode) {
        if (mode == Mode.POJO) {
            final String javaClassName = super.getJavaClassName(definition, mode);
            return List.of(INTERFACE_PACKAGE_NAME + "." + javaClassName);
        }

        return super.getJavaClassImplements(definition, mode);
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        final String javaClassName = super.getJavaClassName(definition, mode);
        if (mode == null) {
            return javaClassName;
        }
        switch (mode) {
            case POJO:
                return javaClassName + "Impl";
            case DEFAULT:
                return javaClassName + "Table";
            case INTERFACE:
                //by default, "I" is added at the beginning of the interface name, so we need to remove it
                return javaClassName.substring(1);
            case RECORD:
            case DAO:
            case ENUM:
            case DOMAIN:
            default:
                return javaClassName;
        }
    }

    @Override
    public String getJavaPackageName(Definition definition, Mode mode) {
        if (mode == Mode.POJO) {
            return POJO_PACKAGE_NAME;
        }
        if (mode == Mode.INTERFACE) {
            return INTERFACE_PACKAGE_NAME;
        }
        if (mode == Mode.ENUM) {
            return ENUM_PACKAGE_NAME;
        }
        return super.getJavaPackageName(definition, mode);
    }

}
