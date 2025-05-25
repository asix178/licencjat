package com.app.service;

import com.app.controller.request.PrizeRequest;
import com.app.dao.PrizeAdapter;
import com.app.model.Category;
import com.app.model.Prize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrizeService {
    private final PrizeAdapter prizeAdapter;

    public void addAllPrizes(PrizeRequest prizeRequest) {
        List<Prize> prizeList = new ArrayList<>();
        for(int i = 0; i< prizeRequest.getAmount(); i++){
            prizeList.add(new Prize(prizeRequest.getName(), new Category(prizeRequest.getCategoryId(),null)));
        }
        prizeAdapter.addAll(prizeList);
    }
}
