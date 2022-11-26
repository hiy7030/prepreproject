package com.prepreproject.coffee.service;

import com.prepreproject.coffee.entity.Coffee;
import com.prepreproject.coffee.repository.CoffeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

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
    public Page<Coffee> findCoffees(int page, int size) {
        // repository에 넘길 Pageable 생성
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("coffeeId").descending());
        return coffeeRepository.findAll(pageable);
    }
    // 커피 삭제
    public void deleteCoffee(long coffeeId) {

    }
}
