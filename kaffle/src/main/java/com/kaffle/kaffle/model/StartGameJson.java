package com.kaffle.kaffle.model;


public class StartGameJson {
    String board;
    int[] positionStatus;

    public StartGameJson(String board) {
        this.board = board;

        for(int i=0;i<5;i++){
            board.substring(i,i+1);
        }
    }

}
