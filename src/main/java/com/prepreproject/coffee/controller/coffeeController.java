package com.prepreproject.coffee.controller;

import com.prepreproject.coffee.entity.Coffee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/coffees")
public class coffeeController {
    //커피 등록
    @PostMapping
    public ResponseEntity postCoffee() {
        return null;
    }
    //커피 정보 수정
    @PatchMapping("/{coffee-id}")
    public ResponseEntity  patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        return null;
    }
    // 커피 조회
    @GetMapping("/{coffee-id}")
    public ResponseEntity  getCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        return null;
    }
    // 커피 목록 조회
    @GetMapping
    public List<Coffee> getCoffees() {
        return null;
    }
    // 커피 삭제
    @DeleteMapping("/{coffee-id}")
    public void deleteCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {

    }
}
