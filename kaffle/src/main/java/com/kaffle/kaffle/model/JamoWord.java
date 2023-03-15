package com.kaffle.kaffle.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JamoWord {
    private String ja1;
    private String mo1;
    private String ja2;
    private String mo2;
    private String ja3;
    
    private String jamo;

    public JamoWord(String jamo) {
        this.ja1 = jamo.substring(0,1);
        this.mo1 = jamo.substring(1,2);
        this.ja2 = jamo.substring(2,3);
        this.mo2 = jamo.substring(3,4);
        this.ja3 = jamo.substring(4,5);

        this.jamo = jamo;
    }

    public JamoWord(String ja1, String mo1, String ja2, String mo2, String ja3) {
        this.ja1 = ja1;
        this.mo1 = mo1;
        this.ja2 = ja2;
        this.mo2 = mo2;
        this.ja3 = ja3;

        this.jamo = ja1+mo1+ja2+mo2+ja3;
    }

    @Override
    public String toString() {
        return jamo;
    }

    public String getMos(){
        return mo1 + mo2;
    }
    
    
}
