package com.kaffle.kaffle.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaffle.kaffle.db.DbConnect;
import com.model.JamoWord;

@RestController
public class HelloWorldController {

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }

    public String todaysQuestion() {
        String query = "select id, 어휘_re, 어휘_자모 from words";

        List<JamoWord> words = new ArrayList<JamoWord>();
        try{
            Connection conn = DbConnect.openConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while(rs.next()){
                String id = rs.getNString("id");
                String word = rs.getNString("어휘_re");
                String jamo = rs.getNString("어휘_자모");
                words.add(new JamoWord(id, word, jamo));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        


        return "";
    }
}