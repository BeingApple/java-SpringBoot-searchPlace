package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Popular;
import com.beingapple.webservice.domain.PopularRequestDTO;
import com.beingapple.webservice.repository.PopularRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PopularServiceImpl implements PopularService {
    private PopularRepository popularRepository;
    private Optional<Popular> optionalPopular;

    @Async
    @Override
    public void savePopularKeyword(String keyword) {
        Popular saveData = makeSavePopularData(keyword);
        popularRepository.save(saveData);
    }

    public Popular makeSavePopularData(String keyword){
        Popular popular = popularRepository.findFirstByKeyword(keyword);
        PopularRequestDTO dto = optionalPopular.ofNullable(popular)
                .filter(p -> p.getId() > 0)
                .map(p -> {
                    PopularRequestDTO dtoTemp = new PopularRequestDTO();
                    dtoTemp.setId(p.getId());
                    dtoTemp.setKeyword(p.getKeyword());
                    dtoTemp.setCount(p.getCount() + 1);

                    return dtoTemp;
                })
                .orElseGet(() -> {
                    PopularRequestDTO dtoTemp = new PopularRequestDTO();
                    dtoTemp.setKeyword(keyword);
                    dtoTemp.setCount(1);

                    return dtoTemp;
                });

        return dto.toEntity();
    }

    @Override
    public List<Popular> getPopularList() {
        return popularRepository.findFirst10ByOrderByCountDesc();
    }
}
