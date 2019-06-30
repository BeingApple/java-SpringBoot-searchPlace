package com.beingapple.webservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HistoryRequestDTO {
    private Long memberId;
    private String keyword;

    public History toEntity(){
        return History.builder()
                .memberId(memberId)
                .keyword(keyword)
                .build();
    }
}
