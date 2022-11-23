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
import java.util.List;

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

        Coffee coffee = coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDto));
        CoffeeDto.Response response = mapper.coffeeToCoffeeResponseDto(coffee);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }
    //커피 정보 수정
    @PatchMapping("/{coffee-id}")
    public ResponseEntity  patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                       @Valid @RequestBody CoffeeDto.Patch coffeePatchDto) {

        Coffee coffee = coffeeService.updateCoffee(mapper.coffeePatchDtoToCoffee(coffeePatchDto));
        coffee.setCoffeeId(coffeeId);

        CoffeeDto.Response response = mapper.coffeeToCoffeeResponseDto(coffee);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    // 커피 조회
    @GetMapping("/{coffee-id}")
    public ResponseEntity  getCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {


       Coffee coffee = coffeeService.findCoffee(coffeeId);
        CoffeeDto.Response response = mapper.coffeeToCoffeeResponseDto(coffee);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    // 커피 목록 조회
    @GetMapping
    public ResponseEntity getCoffees() {

        List<Coffee> coffees = coffeeService.findCoffees();
        List<CoffeeDto.Response> responses = mapper.coffeesToCoffeeResponseDtos(coffees);

        return new ResponseEntity<>(new MultiResponseDto<>(responses), HttpStatus.OK);
    }
    // 커피 삭제
    @DeleteMapping("/{coffee-id}")
    public void deleteCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {

    }
}
