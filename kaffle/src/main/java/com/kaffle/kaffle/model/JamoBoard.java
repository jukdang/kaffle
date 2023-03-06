package com.kaffle.kaffle.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JamoBoard {
    public JamoWord row1;
    public JamoWord row2;
    public JamoWord row3;
    public JamoWord col1;
    public JamoWord col2;
    public JamoWord col3;

    public JamoBoard() {
        this.row1 = null;
        this.row2 = null;
        this.row3 = null;
        this.col1 = null;
        this.col2 = null;
        this.col3 = null;
    }

    public JamoBoard(String row1, String row2, String row3, String col1, String col2, String col3) {
        this.row1 = new JamoWord(row1);
        this.row2 = new JamoWord(row2);
        this.row3 = new JamoWord(row3);
        this.col1 = new JamoWord(col1);
        this.col2 = new JamoWord(col2);
        this.col3 = new JamoWord(col3);
    }

    public JamoBoard mixing(){

        List<String> allJamo= Arrays.asList(
            (row1.toString() + row2.toString() + row3.toString() + 
            col1.mo1 + col1.mo2 + 
            col2.mo1 + col2.mo2 + 
            col3.mo1 + col3.mo2).split(""));

        Collections.shuffle(allJamo);

        JamoBoard ret = new JamoBoard();

        ret.setRow1(new JamoWord(String.join("", 
            allJamo.subList(0, 5))));
        ret.setRow2(new JamoWord(String.join("", 
            allJamo.subList(5, 10))));
        ret.setRow3(new JamoWord(String.join("", 
            allJamo.subList(10, 15))));
        
        ret.setCol1(new JamoWord(ret.row1.ja1, allJamo.get(15), ret.row2.ja1, allJamo.get(16), ret.row3.ja1));
        ret.setCol2(new JamoWord(ret.row1.ja2, allJamo.get(17), ret.row2.ja2, allJamo.get(18), ret.row3.ja2));
        ret.setCol3(new JamoWord(ret.row1.ja3, allJamo.get(19), ret.row2.ja3, allJamo.get(20), ret.row3.ja3));

        return ret;
    }

    public String toString(){
        return row1.toString() + "\n" + row2.toString() + "\n" + row3.toString() + "\n" +
                    col1.toString() + "\n" + col2.toString() + "\n" + col3.toString();
    }

    public String toResponse(){
        
        return row1.toString() + 
            col1.mo1 + col2.mo1 + col3.mo1 + 
            row2.toString() +
            col1.mo2 + col2.mo2 + col3.mo2 + 
            row3.toString();
    }

    public int checkTile(Tile tile){
        String letter = tile.getTile(); // alphabet
        int row = tile.getRow(); // 0~4
        int col = tile.getCol(); // 0~4
        
        System.out.println("--row: "+ row + ", col: " + col);

        int ret = 0;

        if(row%2==0 && col%2==0){
            // check row and col
            int r = getLine("row", row).isInLine(letter, col);
            int c = getLine("col", col).isInLine(letter, row);
            ret = r+c;
            
        }
        else if(row%2==0){
            //check only row
            int r = getLine("row", row).isInLine(letter, col);
            ret = r;
        }
        else{
            //check only col
            int c = getLine("col", col).isInLine(letter, row);
            ret = c;
        }

        return ret;
    }

    private JamoWord getLine(String line, int position){
        if(line=="row"){
            if(position == 0){
                return this.row1;
            }
            else if(position == 2){
                return this.row2;
            }
            else if(position == 4){
                return this.row3;
            }
        }
        else if(line=="col"){
            if(position == 0){
                return this.col1;
            }
            else if(position == 2){
                return this.col2;
            }
            else if(position == 4){
                return this.col3;
            }
        }

        return null;
        
    }
    
}
