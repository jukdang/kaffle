package com.kaffle.kaffle.model;

public class JamoWord {
    public String ja1;
    public String mo1;
    public String ja2;
    public String mo2;
    public String ja3;
    
    public String jamo;

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
    
    public int isInLine(String s, int position){
        if(jamo.substring(position,position+1).equals(s)){
            return 1;
        }
        else if(jamo.contains(s)){
            return -1;
        }

        return 0;
    }
    
    
}
