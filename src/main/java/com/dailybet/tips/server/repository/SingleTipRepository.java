package com.dailybet.tips.server.repository;

import com.dailybet.tips.server.model.SingleTip;
import com.dailybet.tips.server.model.TippCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SingleTipRepository extends JpaRepository<SingleTip, Long> {

    Optional<SingleTip> findByTippUid(String tippUid);
    Optional<SingleTip> findByCategory(String category);
    Optional<SingleTip> findByTippster(String tippster);

    @Query(value = "select a from SingleTip a where a.category LIKE :category AND a.datestamp BETWEEN :startDate AND :endDate")
    List<SingleTip> getAllSingleTipsByCategoryAndBetweenDates(@Param("category") TippCategory category,
                                                                @Param("startDate")Date startDate,@Param("endDate")Date endDate);

    @Query("Select a from SingleTip a where a.tippster LIKE :tippster AND a.category LIKE :category")
    List<SingleTip> findSingleTipsByTippster(@Param("tippster") String tippster,@Param("category") TippCategory category);




}
