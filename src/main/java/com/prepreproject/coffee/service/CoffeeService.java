package com.prepreproject.coffee.service;

import com.prepreproject.coffee.entity.Coffee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    // 커피 생성
    public Coffee createCoffee(Coffee coffee) {

        coffee.setCoffeeId(1L);
        return coffee;
    }
    // 커피 수정
    public Coffee updateCoffee(Coffee coffee)  {

        return coffee;
    }
    // 커피 조회
    public Coffee findCoffee(long coffeeId) {
        Coffee coffee = new Coffee("아메리카노", "Americano", 3000,
                "AMR", Coffee.CoffeeStatus.COFFEE_FOR_SALE);
        coffee.setCoffeeId(coffeeId);
        return coffee;
    }
    // 커피 목록 조회
    public List<Coffee> findCoffees() {

        Coffee coffee1 = new Coffee("아메리카노", "Americano", 3000,
                "AMR", Coffee.CoffeeStatus.COFFEE_FOR_SALE);
        coffee1.setCoffeeId(1L);

        Coffee coffee2 = new Coffee("카페라떼", "Caffe Latte", 3000,
                "CFL", Coffee.CoffeeStatus.COFFEE_FOR_SALE);
        coffee2.setCoffeeId(2L);
        return List.of(coffee1, coffee2);
    }
    // 커피 삭제
    public void deleteCoffee(long coffeeId) {

    }
}
