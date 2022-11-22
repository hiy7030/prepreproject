package com.prepreproject.coffee.controller;

import com.prepreproject.coffee.dto.CoffeeDto;
import com.prepreproject.coffee.entity.Coffee;
import com.prepreproject.coffee.mapper.CoffeeMapper;
import com.prepreproject.coffee.service.CoffeeService;
import com.prepreproject.dto.MultiResponseDto;
import com.prepreproject.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("/v1/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;

    public CoffeeController(CoffeeService coffeeService,
                            CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }

    //커피 등록
    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeeDto.Post coffeePostDto) {

        Coffee coffee = mapper.coffeePostDtoToCoffee(coffeePostDto);

        return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.CREATED);
    }
    //커피 정보 수정
    @PatchMapping("/{coffee-id}")
    public ResponseEntity  patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                       @Valid @RequestBody CoffeeDto.Patch coffeePatchDto) {
        return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.OK);
    }
    // 커피 조회
    @GetMapping("/{coffee-id}")
    public ResponseEntity  getCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.OK);
    }
    // 커피 목록 조회
    @GetMapping
    public ResponseEntity getCoffees() {
        return new ResponseEntity<>(new MultiResponseDto<>(), HttpStatus.OK);
    }
    // 커피 삭제
    @DeleteMapping("/{coffee-id}")
    public void deleteCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {

    }
}
