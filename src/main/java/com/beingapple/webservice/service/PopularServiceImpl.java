package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Popular;
import com.beingapple.webservice.domain.PopularRequestDTO;
import com.beingapple.webservice.repository.PopularRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PopularServiceImpl implements PopularService {
    private PopularRepository popularRepository;

    @Override
    public void savePopularKeyword(String keyword) {
        Popular popular = popularRepository.findFirstByKeyword(keyword);

        if(popular != null ){
            //이미 누적된 검색 기록이 존재할 때
            PopularRequestDTO dto = popular.getModifyDto();
            dto.setCount(dto.getCount() + 1);

            popularRepository.save(dto.toEntity());
        }else{
            //처음 검색했을 때
            PopularRequestDTO dto = new PopularRequestDTO();
            dto.setKeyword(keyword);
            dto.setCount(1);

            popularRepository.save(dto.toEntity());
        }
    }

    @Override
    public List<Popular> getPopular() {
        return popularRepository.findFirst10ByOrderByCountDesc();
    }
}
