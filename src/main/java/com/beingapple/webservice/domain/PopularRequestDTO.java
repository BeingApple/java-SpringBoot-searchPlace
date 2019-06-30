package com.beingapple.webservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PopularRequestDTO {
    private Long id;
    private String keyword;
    private Integer count;

    public Popular toEntity(){
        return Popular.builder()
                .id(id)
                .keyword(keyword)
                .count(count)
                .build();
    }
}
