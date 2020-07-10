package com.dailybet.tips.server.service;

import com.dailybet.tips.server.model.BettingTip;
import com.dailybet.tips.server.model.TippCategory;
import com.dailybet.tips.server.repository.BettingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BettingTipServiceImpl implements BettingTipService {

    @Autowired
    BettingTipRepository bettingTipRepository;


    @Override
    public BettingTip saveBettingTip(BettingTip bettingTip) {
        return bettingTipRepository.save(bettingTip);
    }

    @Override
    public BettingTip updateBettingTip(BettingTip bettingTip) {
        return bettingTipRepository.save(bettingTip);
    }

    @Override
    public void deleteBettingTip(Long bettingTipId) {
        bettingTipRepository.deleteById(bettingTipId);
    }

    @Override
    public BettingTip findByBettingTipByUid(String bettingTipUid) {
        return bettingTipRepository.findByTippUid(bettingTipUid).orElse(null);
    }

    @Override
    public List<BettingTip> findBettingtipsByTippster(String tippster, TippCategory category) {
        return bettingTipRepository.findBettingtipsByTippster(tippster,category);
    }

    @Override
    public List<BettingTip> findAllBettingTips() {
        return bettingTipRepository.findAll();
    }

    @Override
    public Long numberOfBettingTips() {
        return bettingTipRepository.count();
    }

    @Override
    public List<BettingTip> findBettingTipsByCategory(TippCategory category) {
        return bettingTipRepository.findBettingTipsByCategory(category);
    }

    @Override
    public List<BettingTip> findTippsByCategoryAndDate(TippCategory category, Date timestamp) {
        return bettingTipRepository.findTippsByCategoryAndDate(category,timestamp);
    }

    @Override
    public List<BettingTip> getAllBetweenDates(Date startDate, Date endDate) {
        return bettingTipRepository.getAllBetweenDates(startDate,endDate);
    }
    
    @Override
    public List<BettingTip> getAllBettingTipsByCategoryAndBetweenDates(TippCategory category, Date startDate, Date endDate) {
        return bettingTipRepository.getAllBettingTipsByCategoryAndBetweenDates(category,startDate,endDate);
    }
}
