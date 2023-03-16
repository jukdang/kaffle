package com.kaffle.kaffle.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaffle.kaffle.Service.JamoService;
import com.kaffle.kaffle.db.JamoEntity;
import com.kaffle.kaffle.model.GameStartingData;
import com.kaffle.kaffle.model.JamoBoard;
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
    public GameStartingData todaymixedJamo() {
        System.out.println("Request mixed JAMO");
        return jamoEntity.toResponse();
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
     * For test, Not Used
     * @return jamoEntity to test output
     */
    @GetMapping("/explanation")
    public List<List<String[]>> explanation(){

        return jamoEntity.getMeanings();
    }

   
    /**
     * called from web when tiles exchanges to check is position condition
     * @param JSON-object(Tile)
     * @return Integer-value(2: correct, -1,-2: in-line, 0: not-in-line)
     */
    @PostMapping("/positionCheck")
    public Integer positionCheck(@RequestBody Tile tile) throws Exception{
        System.out.println("Request position Check");

        String letter = tile.getTile(); // alphabet
        int row = tile.getRow(); // 0~4
        int col = tile.getCol(); // 0~4

        return jamoEntity.getJamo_board().checkTile(letter, row, col);
    }
    
}