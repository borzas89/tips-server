package com.dailybet.tips.server.service;

import com.dailybet.tips.server.model.BettingTip;
import com.dailybet.tips.server.model.TippCategory;

import java.util.Date;
import java.util.List;

public interface BettingTipService {

    BettingTip saveBettingTip(BettingTip bettingTip);

    BettingTip updateBettingTip(BettingTip bettingTip);

    void deleteBettingTip(Long bettingTipId);

    BettingTip findByBettingTipByUid(String bettingTipUid);

    List<BettingTip> findBettingtipsByTippster(String tippster, TippCategory category);

    List<BettingTip> findAllBettingTips();

    Long numberOfBettingTips();

    List<BettingTip> findBettingTipsByCategory(TippCategory category);

    List<BettingTip> findTippsByCategoryAndDate(TippCategory category, Date timestamp);

    List<BettingTip> getAllBetweenDates(Date startDate, Date endDate);
    
    List<BettingTip> getAllBettingTipsByCategoryAndBetweenDates(TippCategory category,Date startDate, Date endDate);
}
