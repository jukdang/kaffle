package com.kaffle.kaffle.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JamoBoard {
    private JamoWord row1;
    private JamoWord row2;
    private JamoWord row3;
    private JamoWord col1;
    private JamoWord col2;
    private JamoWord col3; 

    private String[][] board;


    public JamoBoard(String row1, String row2, String row3, String col1, String col2, String col3) {
        this.row1 = new JamoWord(row1);
        this.row2 = new JamoWord(row2);
        this.row3 = new JamoWord(row3);
        this.col1 = new JamoWord(col1);
        this.col2 = new JamoWord(col2);
        this.col3 = new JamoWord(col3); 

        setBoard();
    }

    public JamoBoard(JamoWord row1, JamoWord row2, JamoWord row3, JamoWord col1, JamoWord col2, JamoWord col3) {
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;

        setBoard();
    }

    private void setBoard(){
        this.board = new String[5][5];

        board[0][0] = this.row1.getJa1();
        board[0][1] = this.row1.getMo1();
        board[0][2] = this.row1.getJa2();
        board[0][3] = this.row1.getMo2();
        board[0][4] = this.row1.getJa3();

        board[1][0] = this.col1.getMo1();
        board[1][1] = "-";
        board[1][2] = this.col2.getMo1();
        board[1][3] = "-";
        board[1][4] = this.col3.getMo1();

        board[2][0] = this.row2.getJa1();
        board[2][1] = this.row2.getMo1();
        board[2][2] = this.row2.getJa2();
        board[2][3] = this.row2.getMo2();
        board[2][4] = this.row2.getJa3();

        board[3][0] = this.col1.getMo2();
        board[3][1] = "-";
        board[3][2] = this.col2.getMo2();
        board[3][3] = "-";
        board[3][4] = this.col3.getMo2();

        board[4][0] = this.row3.getJa1();
        board[4][1] = this.row3.getMo1();
        board[4][2] = this.row3.getJa2();
        board[4][3] = this.row3.getMo2();
        board[4][4] = this.row3.getJa3();
    }

    private Integer checkRow(String letter, Integer rowNum, Integer colNum){
        for(int i=0;i<5;i++){
            if(board[rowNum][i].equals(letter)){
                if(i == colNum){
                    return 1;
                }
                return 0;
            }
        }

        return -1;
    }

    private Integer checkCol(String letter, Integer rowNum, Integer colNum){
        for(int i=0;i<5;i++){
            if(board[rowNum][i].equals(letter)){
                if(i == colNum){
                    return 1;
                }
                return 0;
            }
        }

        return -1;
    }
    
    public int checkTile(String letter, int row, int col) throws Exception{
        
        System.out.println("--row: "+ row + ", col: " + col);

        int ret = 0;

        if(row%2==0 && col%2==0){
            // check row and col
            ret = checkRow(letter, row, col) + checkCol(letter, row, col);
            
        }
        else if(row%2==0){
            //check only row
            ret = checkRow(letter, row, col);
        }
        else if(col%2==0){
            //check only col
            ret = checkCol(letter, col, row);
        }
        else{
            throw new Exception();
        }

        return ret;
    }

    public JamoBoard mixing(){

        List<String> allJamo= Arrays.asList(
            (row1.toString() + row2.toString() + row3.toString() + 
            col1.getMos() + col2.getMos() + col3.getMos()).split(""));

        Collections.shuffle(allJamo);

        JamoWord newRow1 = new JamoWord(String.join("", allJamo.subList(0, 5)));
        JamoWord newRow2 = new JamoWord(String.join("", allJamo.subList(5, 10)));
        JamoWord newRow3 = new JamoWord(String.join("", allJamo.subList(10, 15)));
        JamoWord newCol1 = new JamoWord(newRow1.getJa1(), allJamo.get(15), newRow2.getJa1(), allJamo.get(16), newRow3.getJa1());
        JamoWord newCol2 = new JamoWord(newRow1.getJa2(), allJamo.get(17), newRow2.getJa1(), allJamo.get(18), newRow3.getJa2());
        JamoWord newCol3 = new JamoWord(newRow1.getJa3(), allJamo.get(19), newRow2.getJa3(), allJamo.get(20), newRow3.getJa3());

        JamoBoard ret = new JamoBoard(newRow1, newRow2, newRow3, newCol1, newCol2, newCol3);

        return ret;
    }

    public String toString(){
        return row1.toString() + col1.getMo1() + col2.getMo1() + col3.getMo1() +
                row2.toString() + col1.getMo2() + col2.getMo2() + col3.getMo2() +
                row3.toString();
    }


    
}
