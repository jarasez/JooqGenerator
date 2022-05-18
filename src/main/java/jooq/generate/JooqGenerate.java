package jooq.generate;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;


public class JooqGenerate {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("org.postgresql.Driver")
                        .withUrl("jdbc:postgresql://localhost:3333/postgres")
                        .withUser("postgres")
                        .withPassword("postgres"))
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.postgres.PostgresDatabase")
                                .withIncludes(".*")
                                //.withExcludes("status")
                                .withInputSchema("public")
                                .withForcedTypes(
                                        new ForcedType()
                                                .withUserType("com.fasterxml.jackson.databind.node.ObjectNode")
                                                .withBinding("jooq.converter.JsonBinding")
                                                .withTypes("jsonb"),
                                        new ForcedType()
                                                .withUserType("jooq.enumeration.Status")
                                                .withEnumConverter(false)
                                                .withBinding("jooq.converter.EnumBinding")
                                                //.withExcludeTypes("source_data_set_status")
                                                //.withIncludeTypes("source_data_set_status")
                                                //.withIncludeExpression("source_data_set.status")
                                                .withTypes("status")
                                               // .withIncludeExpression(".*\\.status")

                                ))
                        .withStrategy(new Strategy().withName("jooq.generate.CustomGeneratorStrategy"))
                        .withGenerate(new Generate().withUdts(false))
                        .withTarget(new Target()
                                .withPackageName("mypck")
                                .withDirectory("build/generated/jooq/main")));

        GenerationTool.generate(configuration);
    }
}
