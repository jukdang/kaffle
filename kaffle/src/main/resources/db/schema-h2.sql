DROP TABLE word_dictionary IF EXISTS;

CREATE TABLE word_dictionary(
    id bigint generated by default as identity, 
    word_dict varchar, 
    unit varchar, 
    is_native varchar, 
    class_type varchar, 
    meaning varchar, 
    category varchar, 
    word varchar, 
    jamo varchar, 
    jamo_len varchar, 
    ja1 varchar, 
    mo1 varchar, 
    ja2 varchar, 
    mo2 varchar, 
    ja3 varchar,
    PRIMARY KEY (id)
) ;

DROP TABLE daily_jamo IF EXISTS;

create table daily_jamo (
    id bigint not null, 
    date date, 
    row1 varchar(255), 
    row2 varchar(255), 
    row3 varchar(255), 
    col1 varchar(255), 
    col2 varchar(255), 
    col3 varchar(255), 
    mix_col1 varchar(255), 
    mix_col2 varchar(255), 
    mix_col3 varchar(255), 
    mix_row1 varchar(255), 
    mix_row2 varchar(255), 
    mix_row3 varchar(255), 
    PRIMARY KEY (id)
) ;