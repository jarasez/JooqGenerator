Use:
    
    ./gradlew composeUp 

to start the PostgreSQL DB and generate tables (host: localhost, port: 3333, db: postgres, user: postgres, pass: postgres)

Run [JooqGenerator](src/main/java/jooq/generate/JooqGenerate.java)

Generated sources will be created in: [build](build/generated/jooq/main/mypck)

Open [MyTableTable](build/generated/jooq/main/mypck/tables/MyTableTable.java), which will have compile issues for the fields: STATUS_FIELD and COLOR. 

Use: 

    ./gradlew composeDown

to stop PostgreSQL

