package com.dailybet.tips.server.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name="singletip")
public class SingleTip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="event")
    private String event;

    @Column(name="tipp")
    private String tipp;

    @Column(name="odds")
    private Double odds;
	
    @Column(name="date_string")
    private String dateString;

    @Column(name="deadline")
    private String deadline;

    @Column(name="sport_category")
    private String sportcategory;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private TippCategory category;

    @Basic
    @Column(name="date_stamp")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datestamp;

    @Column(name="tippster")
    private String tippster;

    @Column(name="tipp_uid")
    private String tippUid;
	
	@Column(name="stake")
    private Double stake;

    @Column(name="result")
    private String result;
	
	@Column(name="isWin")
    private Boolean isWin;

}
