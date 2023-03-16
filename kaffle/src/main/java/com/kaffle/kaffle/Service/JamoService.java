package com.kaffle.kaffle.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kaffle.kaffle.db.DictionaryEntity;
import com.kaffle.kaffle.db.DictionaryRepository;
import com.kaffle.kaffle.db.JamoEntity;
import com.kaffle.kaffle.db.JamoRepository;



@Service
public class JamoService {

    @Autowired
    JamoRepository jamoRepository;
    @Autowired
    DictionaryRepository dictionaryRepository;

    @Autowired
    JamoEntity jamoEntity;

    /**
     * create new JAMO every 0:00:00 of the day
     */
    @Scheduled(cron = "0 0 0 * * *") 
    public void dayChange(){

        Long dbSize = dictionaryRepository.count();
        while(true){
            try{
                System.out.println("----------trying to generate JAMO...");
                Long id1 = (long)(dbSize * Math.random());
                Long id2 = (long)(dbSize * Math.random());
                Long id3 = (long)(dbSize * Math.random());
                
                DictionaryEntity row1 = dictionaryRepository.findById(id1).get();
                DictionaryEntity row2 = dictionaryRepository.findById(id2).get();
                DictionaryEntity row3 = dictionaryRepository.findById(id3).get();

                List<DictionaryEntity> colList = new ArrayList<>();
                Random rand = new Random();
                colList = dictionaryRepository.findAllByJa1AndJa2AndJa3(row1.getJa1(), row2.getJa1(), row3.getJa1());
                DictionaryEntity col1 = colList.get(rand.nextInt(colList.size()));
                colList = dictionaryRepository.findAllByJa1AndJa2AndJa3(row1.getJa2(), row2.getJa2(), row3.getJa2());
                DictionaryEntity col2 = colList.get(rand.nextInt(colList.size()));
                colList = dictionaryRepository.findAllByJa1AndJa2AndJa3(row1.getJa3(), row2.getJa3(), row3.getJa3());
                DictionaryEntity col3 = colList.get(rand.nextInt(colList.size()));

                try{
                    jamoEntity.setId(jamoEntity.getId()+1);
                }
                catch(Exception e){
                    jamoEntity.setId(null);
                }
                jamoEntity.setRow1(row1.getJamo());
                jamoEntity.setRow2(row2.getJamo());
                jamoEntity.setRow3(row3.getJamo());
                jamoEntity.setCol1(col1.getJamo());
                jamoEntity.setCol2(col2.getJamo());
                jamoEntity.setCol3(col3.getJamo());
                jamoEntity.fill_datas();
                loadMeanings();

                jamoRepository.save(jamoEntity);

                break;
            }
            catch(Exception e){
                continue;
            }
        }

        System.out.println("----------Generated JAMO");
    }

    public void loadMeanings(){

        List<List<String[]>> meanings = new ArrayList<>();
        String[] words = {jamoEntity.getRow1(), jamoEntity.getRow2(), jamoEntity.getRow3(), 
            jamoEntity.getCol1(), jamoEntity.getCol2(), jamoEntity.getCol3()};
        
        //System.out.println(words);
        for(int i=0;i<words.length;i++){
            List<DictionaryEntity> wordDatas = dictionaryRepository.findAllByJamo(words[i]);
            System.out.println(wordDatas);
            List<String[]> word_meaning = new ArrayList<>();
            for(int j=0;j<wordDatas.size();j++){
                String[] meaning = {wordDatas.get(j).getClasstype(), wordDatas.get(j).getMeaning()};
                //System.out.println(meaning);
                word_meaning.add(meaning);
            }
            meanings.add(word_meaning);
        }

        jamoEntity.setMeanings(meanings);
    }

    /**
     * When server start load JamoEntity from DB
     * <br>
     * If DB is empty, create new JAMO
     */
    @EventListener(ApplicationReadyEvent.class)
    public void serverStart(){
        Long jamoRepoSize = jamoRepository.count();
        if(jamoRepoSize > 0){
            JamoEntity tempEntity = jamoRepository.findById(jamoRepoSize).get();

            jamoEntity.loadFromDB(tempEntity);
            loadMeanings();
        }
        else{
            dayChange();
        }
    }

    
}
