package com.beingapple.webservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

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
        @JsonProperty(value="is_end")
        private boolean is_end;
        private SameName same_name;
    }

    @Getter
    @Setter
    @ToString
    public static class SameName{
        private List<String> region;
        private String keyword;
        private String selected_region;
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
