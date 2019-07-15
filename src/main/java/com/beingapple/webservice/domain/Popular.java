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
public class Popular extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200, nullable = false)
    private String keyword;

    @Column(nullable = false)
    private Integer count;

    @Builder
    public Popular(Long id, String keyword, Integer count){
        this.id = id;
        this.keyword = keyword;
        this.count = count;
    }
}
