package com.beingapple.webservice.domain;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Place {
    private Meta meta;
    private List<Document> documents;

    @Getter
    @Setter
    @ToString
    public static class Meta{
        private Integer total_count;
        private Integer pageable_count;
        private boolean is_end;
    }

    @Getter
    @Setter
    @ToString
    public static class Document{
        private String id;
        private String place_name;
        private String category_name;
        private String category_group_code;
        private String category_group_name;
        private String phone;
        private String address_name;
        private String road_address_name;
        private String x;
        private String y;
        private String place_url;
        private String distance;
    }
}
