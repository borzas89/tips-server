package com.dailybet.tips.server.repository;

import com.dailybet.tips.server.model.BettingTip;
import com.dailybet.tips.server.model.TippCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BettingTipRepository extends JpaRepository<BettingTip, Long> {

    Optional<BettingTip> findByEvent(String event);
    Optional<BettingTip> findByTippUid(String tippUid);
    Optional<BettingTip> findByCategory(String category);

    @Query("Select a from BettingTip a where a.category LIKE :category")
    List<BettingTip> findBettingTipsByCategory(@Param("category") TippCategory category);

    @Query("Select a from BettingTip a where a.category LIKE :category AND a.datestamp <= :datestamp")
    List<BettingTip> findTippsByCategoryAndDate(@Param("category") TippCategory category,
                                                @Param("datestamp") Date datestamp);

    List<BettingTip> findAllByDatestamp(Date dateStamp);

    @Query(value = "select  a from BettingTip a where a.datestamp BETWEEN :startDate AND :endDate")
    List<BettingTip> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

    @Query(value = "select  a from BettingTip a where a.category LIKE :category AND a.datestamp BETWEEN :startDate AND :endDate")
    List<BettingTip> getAllBetweenDatesAndCategory(@Param("startDate")Date startDate,@Param("endDate")Date endDate);

    @Query(value = "select a from BettingTip a where a.category LIKE :category AND a.datestamp BETWEEN :startDate AND :endDate")
    List<BettingTip> getAllBettingTipsByCategoryAndBetweenDates(@Param("category")TippCategory category,
	@Param("startDate")Date startDate,@Param("endDate")Date endDate);

    @Query("Select a from BettingTip a where a.tippster LIKE :tippster AND a.category LIKE :category")
    List<BettingTip> findBettingtipsByTippster(@Param("tippster") String tippster,@Param("category") TippCategory category);




}
