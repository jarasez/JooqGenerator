CREATE TYPE status AS ENUM ('OPEN', 'COMPLETED');
CREATE TYPE my_color AS ENUM ('RED', 'BLACK', 'WHITE');


-- auto-generated definition
create table my_table
(
    id           bigint primary key not null,
    data         jsonb,
    status_field status,
    color        my_color

);