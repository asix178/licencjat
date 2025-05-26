package com.app.dao;

import com.app.dbmodel.CategoryEntity;
import com.app.dbmodel.PrizeEntity;
import com.app.model.Prize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrizeAdapter {
    private final PrizeRepository prizeRepository;
    private final CategoryRepository categoryRepository;

    public void addAll(List<Prize> prizeList) {
        try {
            Optional<CategoryEntity> categoryEntity =
                    categoryRepository.findByDomainId(prizeList.getFirst().getCategory().getId());
            List<PrizeEntity> prizeEntityList = new ArrayList<>();
            for (Prize prize : prizeList) {
                PrizeEntity prizeEntity = PrizeEntity.fromDomain(prize);
                prizeEntity.setCategory(categoryEntity.get());
                prizeEntityList.add(prizeEntity);
            }
            prizeRepository.saveAll(prizeEntityList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<Prize> getAll() {
        List<PrizeEntity> prizeEntityList = prizeRepository.findAll();
        return prizeEntityList.stream().map(PrizeEntity::toDomain).toList();
    }
}
