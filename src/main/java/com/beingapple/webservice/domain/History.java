package com.beingapple.webservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class History extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long memberId;

    @Column(length = 200, nullable = false)
    private String keyword;

    @Temporal(TemporalType.TIMESTAMP)
    private Date searchDate;

    @Builder
    public History(Long memberId, String keyword, Date searchDate){
        this.memberId = memberId;
        this.keyword = keyword;
        this.searchDate = searchDate;
    }
}
