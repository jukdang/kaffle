package com.kaffle.kaffle.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffle.kaffle.db.DbConnect;
import com.model.JamoWord;
import com.model.DailyJamoJSON;

@RestController
public class HelloWorldController {
    public DailyJamoJSON answerJamo = new DailyJamoJSON();
    public DailyJamoJSON mixedJamo = new DailyJamoJSON();

    @GetMapping("/test")
    public String test() {
        return "Hello, world!";
    }

    @GetMapping("/testJamo")
    public DailyJamoJSON testJamo() {
        // const answerList = [
        //     ['ㄱ', 'ㅖ', 'ㅅ', 'ㅏ', 'ㄴ'],
        //     ['ㅣ', '-', 'ㅏ', '-', 'ㅐ'],
        //     ['ㅇ', 'ㅏ', 'ㅇ', 'ㅑ', 'ㅇ'],
        //     ['ㅓ', '-', 'ㅛ', '-', 'ㅛ'],
        //     ['ㄱ', 'ㅛ', 'ㅇ', 'ㅑ', 'ㅇ']
        // ]
        // const wordList = [
        //     'ㄱ', 'ㅖ', 'ㅅ', 'ㅏ', 'ㄴ',
        //     'ㅣ', 'ㅏ', 'ㅐ',
        //     'ㅇ', 'ㅏ', 'ㅇ', 'ㅑ', 'ㅇ',
        //     'ㅓ', 'ㅛ', 'ㅛ',
        //     'ㄱ', 'ㅇ', 'ㅛ', 'ㅇ', 'ㅑ'
        // ];
        
        answerJamo.row1 = new JamoWord("ㄱ","ㅖ","ㅅ","ㅏ","ㄴ");
        answerJamo.row2 = new JamoWord("ㅇ","ㅏ","ㅇ","ㅑ","ㅇ");
        answerJamo.row3 = new JamoWord("ㄱ","ㅛ","ㅇ","ㅑ","ㅇ");
        answerJamo.col1 = new JamoWord("ㄱ","ㅣ","ㅇ","ㅓ","ㄱ");
        answerJamo.col2 = new JamoWord("ㅅ","ㅏ","ㅇ","ㅛ","ㅇ");
        answerJamo.col3 = new JamoWord("ㄴ","ㅐ","ㅇ","ㅛ","ㅇ");
        //mixingJamo();
        mixedJamo.row1 = new JamoWord("ㄱ","ㅖ","ㅅ","ㅏ","ㄴ");
        mixedJamo.row2 = new JamoWord("ㅇ","ㅏ","ㅇ","ㅑ","ㅇ");
        mixedJamo.row3 = new JamoWord("ㄱ","ㅇ","ㅛ","ㅇ","ㅑ");
        mixedJamo.col1 = new JamoWord("ㄱ","ㅣ","ㅇ","ㅓ","ㄱ");
        mixedJamo.col2 = new JamoWord("ㅅ","ㅏ","ㅇ","ㅛ","ㅇ");
        mixedJamo.col3 = new JamoWord("ㄴ","ㅐ","ㅇ","ㅛ","ㅇ");
    
        System.out.println("Successfully Give test JAMO!");
        return mixedJamo;
    }

    @GetMapping("/todayJamo")
    public DailyJamoJSON todayJamo() {
        makeTodayJamo();
    
        System.out.println("Successfully Generate today's JAMO!");
        return answerJamo;
    }

    @GetMapping("/todaymixedJamo")
    public DailyJamoJSON todaymixedJamo() {
    
        System.out.println("Successfully Send mixed JAMO!");
        return mixedJamo;
    }

    @GetMapping("/makeTodayJamo")
    public DailyJamoJSON makeTodayJamo(){

        while(true){

            try{
                JamoWord row1 = getQueryJamo(null);
                System.out.println("1" + row1);
                JamoWord row2 = getQueryJamo(null);
                System.out.println("2" + row2);
                JamoWord row3 = getQueryJamo(null);
                System.out.println("3" + row3);

                JamoWord col1 = getQueryJamo(row1.ja1+row2.ja1+row3.ja1);
                System.out.println("4" + col1);
                JamoWord col2 = getQueryJamo(row1.ja2+row2.ja2+row3.ja2);
                System.out.println("5" + col2);
                JamoWord col3 = getQueryJamo(row1.ja3+row2.ja3+row3.ja3);
                System.out.println("6" + col3);

                answerJamo = new DailyJamoJSON(row1, row2, row3, col1, col2, col3);
                mixedJamo = answerJamo.mixing();

                break;
            }
            catch(Exception e){
                continue;
            }
            
        }
        // System.out.println(answerJamo);
        // System.out.println(mixedJamo);

        return answerJamo;
    }


    public JamoWord getQueryJamo(String jas){
        JamoWord ret = null;

        String wherePhrase = (!(jas == null || jas.isEmpty())
                                ? "WHERE " + 
                                    "ja1 = " + "\'" + jas.charAt(0) + "\'" + " AND " + 
                                    "ja2 = " + "\'" + jas.charAt(1) + "\'" + " AND " +
                                    "ja3 = " + "\'" + jas.charAt(2) + "\'"
                                : "");

        String query = "SELECT id, ja1, mo1, ja2, mo2, ja3 " + 
                        "FROM words " + wherePhrase;

        List<JamoWord> words = new ArrayList<JamoWord>();
        try{
            Connection conn = DbConnect.openConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                String id = rs.getString("id");
                String ja1 = rs.getString("ja1");
                String mo1 = rs.getString("mo1");
                String ja2 = rs.getString("ja2");
                String mo2 = rs.getString("mo2");
                String ja3 = rs.getString("ja3");
                words.add(new JamoWord(ja1, mo1, ja2, mo2, ja3, id));
            }
  
            Random randomizer = new Random();
            ret = words.get(randomizer.nextInt(words.size()));

            //System.out.println("Successfully Generated JAMO!");
        }
        catch(Exception e){
            //e.printStackTrace();
        }

        return ret;
    }
    
}