package com.dailybet.tips.server.service;

import com.dailybet.tips.server.model.SingleTip;
import com.dailybet.tips.server.model.TippCategory;
import com.dailybet.tips.server.repository.SingleTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SingleTipServiceImpl implements SingleTipService {

    @Autowired
    SingleTipRepository singleTipRepository;

    @Override
    public SingleTip saveSingleTip(SingleTip singleTip) {
        return singleTipRepository.save(singleTip);
    }

    @Override
    public SingleTip updateSingleTip(SingleTip singleTip) {
        return singleTipRepository.save(singleTip);
    }

    @Override
    public void deleteSingleTip(Long singleTipId) {
        singleTipRepository.deleteById(singleTipId);
    }

    @Override
    public SingleTip findBySingleTipByUid(String tipUid) {
        return singleTipRepository.findByTippUid(tipUid).orElse(null);
    }

    @Override
    public List<SingleTip> findAllSingleTips() {
        return singleTipRepository.findAll();
     }

    @Override
    public List<SingleTip> findSingleTipsByTippster(String name, TippCategory category) {
        return singleTipRepository.findSingleTipsByTippster(name,category);
    }

    @Override
    public List<SingleTip> getAllSingleTipsByCategoryAndBetweenDates(TippCategory category, Date startDate, Date endDate) {
        return singleTipRepository.getAllSingleTipsByCategoryAndBetweenDates(category,startDate,endDate);
    }

}
