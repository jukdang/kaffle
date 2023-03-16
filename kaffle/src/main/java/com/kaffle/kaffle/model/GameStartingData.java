package com.kaffle.kaffle.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStartingData {
    String boardString;
    String positionString;
    
    public GameStartingData(String boardString, String positionString) {
        this.boardString = boardString;
        this.positionString = positionString;
    }

    

}
