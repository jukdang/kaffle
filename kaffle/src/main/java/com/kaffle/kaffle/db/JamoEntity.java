package com.kaffle.kaffle.db;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaffle.kaffle.model.GameStartingData;
import com.kaffle.kaffle.model.JamoBoard;
import com.kaffle.kaffle.model.Meanings;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dailyJamo")
@Getter
@Setter
@SequenceGenerator( 
    name = "dailyJamo_SEQ_GENERATOR", 
    sequenceName = "dailyJamo_SEQ", //매핑할 데이터베이스 시퀀스 이름
    initialValue = 1, allocationSize = 1) 
public class JamoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dailyJamo_SEQ_GENERATOR")
    private Long id;
    
    @Column
    @CreationTimestamp
    private Date date;

    @Column
    private String row1;
    @Column
    private String row2;
    @Column
    private String row3;
    @Column
    private String col1;
    @Column
    private String col2;
    @Column
    private String col3;

    @Column
    private String mix_row1;
    @Column
    private String mix_row2;
    @Column
    private String mix_row3;
    @Column
    private String mix_col1;
    @Column
    private String mix_col2;
    @Column
    private String mix_col3;

    @Transient
    private List<List<String[]>> meanings;

    @Transient
    private JamoBoard jamo_board;
    @Transient
    private JamoBoard mixed_jamoBoard;


    @Transient
    public void fill_datas(){
        entity_to_jamoBoard();
        mixing();
        mixed_jamoBoard_to_entity();
    }

    @Transient
    public void entity_to_jamoBoard(){
        jamo_board = new JamoBoard(row1, row2, row3, col1, col2, col3);
    }

    @Transient
    public void mixing(){
        mixed_jamoBoard = jamo_board.mixing();
    }

    @Transient
    public void mixed_jamoBoard_to_entity(){
        mix_row1 = mixed_jamoBoard.getRow1().toString();
        mix_row2 = mixed_jamoBoard.getRow2().toString();
        mix_row3 = mixed_jamoBoard.getRow3().toString();
        mix_col1 = mixed_jamoBoard.getCol1().toString();
        mix_col2 = mixed_jamoBoard.getCol2().toString();
        mix_col3 = mixed_jamoBoard.getCol3().toString();
    }

    public GameStartingData toResponse(){
        String boardString = mixed_jamoBoard.toString(); 
        String positionString = "";
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                try{
                    String letter = mixed_jamoBoard.getBoard()[i][j];
                    positionString += Integer.toString(jamo_board.checkTile(letter, i, j));
                }
                catch(Exception e){
                    continue;
                }
                
            }
        }

        GameStartingData ret = new GameStartingData(boardString, positionString);

        return ret;
    }

    @Transient
    public void loadFromDB(JamoEntity entity){
        this.id = entity.getId();
        this.date = entity.getDate();

        this.row1 = entity.getRow1();
        this.row2 = entity.getRow2();
        this.row3 = entity.getRow3();
        this.col1 = entity.getCol1();
        this.col2 = entity.getCol2();
        this.col3 = entity.getCol3();

        this.mix_row1 = entity.getMix_row1();
        this.mix_row2 = entity.getMix_row2();
        this.mix_row3 = entity.getMix_row3();
        this.mix_col1 = entity.getMix_col1();
        this.mix_col2 = entity.getMix_col2();
        this.mix_col3 = entity.getMix_col3();

        this.jamo_board = new JamoBoard(this.row1, this.row2, this.row3, this.col1, this.col2, this.col3);
        this.mixed_jamoBoard = new JamoBoard(this.mix_row1, this.mix_row2, this.mix_row3, this.mix_col1, this.mix_col2, this.mix_col3);
    }
}
