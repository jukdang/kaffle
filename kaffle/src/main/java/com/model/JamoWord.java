package com.model;

import lombok.ToString;

public class JamoWord {
    public String id;
    public String ja1;
    public String mo1;
    public String ja2;
    public String mo2;
    public String ja3;


    public JamoWord(String jamo) {
        this.id = "";
        this.ja1 = jamo.substring(0,1);
        this.mo1 = jamo.substring(1,2);
        this.ja2 = jamo.substring(2,3);
        this.mo2 = jamo.substring(3,4);
        this.ja3 = jamo.substring(4,5);
    }
    public JamoWord(String jamo, String id) {
        this.id = id;
        this.ja1 = jamo.substring(0,1);
        this.mo1 = jamo.substring(1,2);
        this.ja2 = jamo.substring(2,3);
        this.mo2 = jamo.substring(3,4);
        this.ja3 = jamo.substring(4,5);
    }
    public JamoWord(String ja1, String mo1, String ja2, String mo2, String ja3) {
        this.id = "";
        this.ja1 = ja1;
        this.mo1 = mo1;
        this.ja2 = ja2;
        this.mo2 = mo2;
        this.ja3 = ja3;
    }
    public JamoWord(String ja1, String mo1, String ja2, String mo2, String ja3, String id) {
        this.id = id;
        this.ja1 = ja1;
        this.mo1 = mo1;
        this.ja2 = ja2;
        this.mo2 = mo2;
        this.ja3 = ja3;
    }

    @Override
    public String toString() {
        return this.ja1 + this.mo1 + this.ja2 + this.mo2 + this.ja3;
    }
    
    
    
}
