package com.beingapple.webservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Popular {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200, nullable = false)
    private String keyword;

    @Column
    private int count;

    @Builder
    public Popular(String keyword, int count){
        this.keyword = keyword;
        this.count = count;
    }

}
