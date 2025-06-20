package com.app.service;

import com.app.controller.request.PrizeRequest;
import com.app.dao.PrizeAdapter;
import com.app.model.Category;
import com.app.model.Prize;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PrizeService {
    private final PrizeAdapter prizeAdapter;

    public void addAllPrizes(PrizeRequest prizeRequest) {
        List<Prize> prizeList = new ArrayList<>();
        for (int i = 0; i < prizeRequest.getAmount(); i++) {
            prizeList.add(new Prize(prizeRequest.getName(), new Category(prizeRequest.getCategoryId(), null)));
        }
        prizeAdapter.addAll(prizeList);
    }

    public List<Prize> getAllPrizes() {
        return prizeAdapter.getAll();
    }

    public Map<String, Integer> countPrizesByName() {
        Map<String, Integer> prizeMap = new TreeMap<>();
        List<Prize> prizeList = new ArrayList<>(getAllPrizes());
        prizeList.sort(Comparator.comparing(Prize::getName).reversed());
        for (Prize prize : prizeList) {
            if (prizeMap.containsKey(prize.getName())) {
                prizeMap.put(prize.getName(), prizeMap.get(prize.getName()) + 1);
            } else {
                prizeMap.put(prize.getName(), 1);
            }
        }

        return prizeMap;
    }

}
