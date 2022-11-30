package com.prepreproject.coffee.service;

import com.prepreproject.coffee.entity.Coffee;
import com.prepreproject.coffee.repository.CoffeeRepository;
import com.prepreproject.exception.BusinessLogicException;
import com.prepreproject.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    // 커피 생성
    public Coffee createCoffee(Coffee coffee) {
        // 커피가 존재하는 지 검증 후 db에 저장
        // coffeeCode를 대문자로 변경 후 저장하여 혼선이 생기지 않도록 한다.
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();

        verifyExistCoffeeCode(coffeeCode);
        coffee.setCoffeeCode(coffeeCode);

        return coffeeRepository.save(coffee);
    }
    // 커피 수정
    public Coffee updateCoffee(Coffee coffee)  {
        // coffeeId를 가진 coffee 가져오기
        Coffee findCoffee = findVerifiedCoffee(coffee.getCoffeeId());
        // 변경사항이 있다면 새로 저장하기 -> korName, engName, price, coffeeStatus
        Optional.ofNullable(coffee.getKorName())
                .ifPresent(korName -> findCoffee.setKorName(korName));

        Optional.ofNullable(coffee.getEngName())
                .ifPresent(engName -> findCoffee.setEngName(engName));

        Optional.ofNullable(coffee.getPrice())
                .ifPresent(price -> findCoffee.setPrice(price));

        Optional.ofNullable(coffee.getCoffeeStatus())
                .ifPresent(status -> findCoffee.setCoffeeStatus(status));

        // 새로 값을 저장한 coffee를 db에 저장하기
        return coffeeRepository.save(findCoffee);
    }
    // 커피 조회Co
    public Coffee findCoffee(long coffeeId) {
        return findVerifiedCoffee(coffeeId);
    }
    // 커피 목록 조회
    public Page<Coffee> findCoffees(int page, int size) {
        // repository에 넘길 Pageable 생성
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("coffeeId").descending());
        return coffeeRepository.findAll(pageable);
    }
    // 커피 삭제
    public void deleteCoffee(long coffeeId) {
       Coffee coffee = findVerifiedCoffee(coffeeId);
       coffeeRepository.delete(coffee);
    }

    // 검증 메서드
    // 1. 커피코드로 커피 조회 -> 반환값 x
    private void verifyExistCoffeeCode(String coffeeCode) {
        Optional<Coffee> coffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if(coffee.isPresent()){
            throw new BusinessLogicException(ExceptionCode.COFFEE_EXISTS);
        }
    }
    // 2. 커피아이디로 커피 조회 -> 조회된 커피 반환
    public Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        // Optional<Entity> -> Entity
        Coffee findCoffee = optionalCoffee.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND)
        );
        return findCoffee;
    }
}
