package com.dailybet.tips.server.service;

import com.dailybet.tips.server.model.SingleTip;
import com.dailybet.tips.server.model.TippCategory;

import java.util.Date;
import java.util.List;

public interface SingleTipService {

    SingleTip saveSingleTip(SingleTip singleTip);

    SingleTip updateSingleTip(SingleTip singleTip);

    void deleteSingleTip(Long singleTipId);

    SingleTip findBySingleTipByUid(String tipUid);

    List<SingleTip> findAllSingleTips();

    List<SingleTip> findSingleTipsByTippster(String name, TippCategory category);

    List<SingleTip> getAllSingleTipsByCategoryAndBetweenDates(TippCategory category, Date startDate, Date endDate);

}
