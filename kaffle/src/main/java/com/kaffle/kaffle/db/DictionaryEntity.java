package com.kaffle.kaffle.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "word_dictionary")
@Getter
@Setter
@SequenceGenerator( 
    name = "DICT_SEQ_GENERATOR", 
    sequenceName = "DICT_SEQ", //매핑할 데이터베이스 시퀀스 이름
    initialValue = 1, allocationSize = 1 ) 
public class DictionaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICT_SEQ_GENERATOR")
    private Long id;
    
    @Column
    private String baseword;
    @Column
    private String unit;
    @Column
    private String isnative;
    @Column
    private String classtype; 
    @Column
    private String meaning;
    @Column
    private String category; 
    @Column
    private String word;
    @Column
    private String jamo; 
    @Column
    private String len;
    @Column
    private String ja1;
    @Column
    private String mo1; 
    @Column
    private String ja2; 
    @Column
    private String mo2; 
    @Column
    private String ja3;


    
}
