package com.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyJamoJSON {
    public JamoWord row1;
    public JamoWord row2;
    public JamoWord row3;
    public JamoWord col1;
    public JamoWord col2;
    public JamoWord col3;

    public DailyJamoJSON() {
        this.row1 = null;
        this.row2 = null;
        this.row3 = null;
        this.col1 = null;
        this.col2 = null;
        this.col3 = null;
    }

    public DailyJamoJSON(JamoWord row1, JamoWord row2, JamoWord row3, JamoWord col1, JamoWord col2, JamoWord col3) {
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
    }

    public DailyJamoJSON mixing(){
        List<String> allJamo= Arrays.asList((row1.toString() + row2.toString() + row3.toString() + 
        col1.mo1 + col1.mo2 + col2.mo1 + col2.mo2 + col3.mo1 + col3.mo2).split(""));

        Collections.shuffle(allJamo);
        
        DailyJamoJSON ret = new DailyJamoJSON();
        ret.row1 = new JamoWord(String.join("", allJamo.subList(0, 5)));
        ret.row2 = new JamoWord(String.join("", allJamo.subList(5, 10)));
        ret.row3 = new JamoWord(String.join("", allJamo.subList(10, 15)));

        ret.col1 = new JamoWord(ret.row1.ja1, allJamo.get(15), ret.row2.ja1, allJamo.get(16), ret.row3.ja1);
        ret.col2 = new JamoWord(ret.row1.ja2, allJamo.get(17), ret.row2.ja2, allJamo.get(18), ret.row3.ja2);
        ret.col3 = new JamoWord(ret.row1.ja3, allJamo.get(19), ret.row2.ja3, allJamo.get(20), ret.row3.ja3);

        return ret;
    }

    public String toString(){
        return row1.toString() + "\n" + row2.toString() + "\n" + row3.toString() + "\n" +
                    col1.toString() + "\n" + col2.toString() + "\n" + col3.toString();
    }
    
}
