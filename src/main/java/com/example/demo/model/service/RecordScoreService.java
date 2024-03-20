package com.example.demo.model.service;

import com.example.demo.model.dto.ItemDataQuizInOutDto;
import com.example.demo.model.dto.RecordQuizInOutDto;

public interface RecordScoreService {

    public void saveRecordedScore(ItemDataQuizInOutDto itemDataQuizInOutDto);
    public RecordQuizInOutDto getRecordQuizList(ItemDataQuizInOutDto itemDataQuizInOutDto);

}
