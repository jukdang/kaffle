package com.kaffle.kaffle.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaffle.kaffle.Service.JamoService;
import com.kaffle.kaffle.db.JamoEntity;
import com.kaffle.kaffle.model.JamoBoard;
import com.kaffle.kaffle.model.JamoWord;
import com.kaffle.kaffle.model.Tile;

@RestController
public class JamoController {

    @Autowired
    JamoService jamoService;

    @Autowired
    JamoEntity jamoEntity;

    


    /**
     * called from web when starting to get data of board
     * @return starting-data includes mixed-board and position-condition
     */
    @GetMapping("/todayMixedJamo")
    public String todaymixedJamo() {
        System.out.println("Request mixed JAMO");
        return jamoEntity.getMixed_jamoBoard().toResponse();
    }

    /**
     * For test, Not Used
     * @return answer JamoBoard
     */
    @GetMapping("/todayAnswerJamo")
    public JamoBoard todayJamo() {
        System.out.println("Request answer JAMO");
        return jamoEntity.getJamo_board();
    }

    /**
     * For test, Not Used
     * @return jamoEntity to test output
     */
    @GetMapping("/makeTodayJamo")
    public JamoEntity makeTodayJamo(){
        jamoService.dayChange();

        return jamoEntity;
    }


    /**
     * called from web when tiles exchanges to check is position condition
     * @param JSON-object(Tile)
     * @return Integer-value(1: correct, 0: in-line, -1: not-in-line)
     */
    @PostMapping("/positionCheck")
    public Integer positionCheck(@RequestBody Tile tile){
        System.out.println("Request position Check");
        return jamoEntity.getJamo_board().checkTile(tile);
    }
    
}