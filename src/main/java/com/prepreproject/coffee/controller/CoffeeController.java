package com.prepreproject.coffee.controller;

import com.prepreproject.coffee.dto.CoffeeDto;
import com.prepreproject.coffee.entity.Coffee;
import com.prepreproject.coffee.mapper.CoffeeMapper;
import com.prepreproject.coffee.service.CoffeeService;
import com.prepreproject.dto.MultiResponseDto;
import com.prepreproject.dto.SingleResponseDto;
import com.prepreproject.response.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/v1/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;
    private final CoffeeMapper mapper;
    private final static String COFFEE_DEFAULT_URI = "/v1/coffees";

    public CoffeeController(CoffeeService coffeeService,
                            CoffeeMapper mapper) {
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }

    //커피 등록
    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeeDto.Post coffeePostDto) {

        Coffee coffee = coffeeService.createCoffee(mapper.coffeePostDtoToCoffee(coffeePostDto));
        URI location =
                UriComponentsBuilder.newInstance()
                        .path(COFFEE_DEFAULT_URI + "/{coffee-id}")
                        .buildAndExpand(coffee.getCoffeeId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }
    //커피 정보 수정
    @PatchMapping("/{coffee-id}")
    public ResponseEntity  patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                       @Valid @RequestBody CoffeeDto.Patch coffeePatchDto) {

        Coffee coffee = mapper.coffeePatchDtoToCoffee(coffeePatchDto);
        coffee.setCoffeeId(coffeeId);

        Coffee updateCoffee = coffeeService.updateCoffee(coffee);

        CoffeeDto.Response response = mapper.coffeeToCoffeeResponseDto(updateCoffee);

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
    public ResponseEntity getCoffees(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {

        // 페이지 인포
        Page<Coffee> coffeePage = coffeeService.findCoffees(page, size);
        PageInfo pageInfo = new PageInfo(page, size, (int) coffeePage.getTotalElements(), coffeePage.getTotalPages());
        // 커피 정보
        List<Coffee> coffees = coffeePage.getContent();
        List<CoffeeDto.Response> responses = mapper.coffeesToCoffeeResponseDtos(coffees);

        return new ResponseEntity<>(new MultiResponseDto<>(responses, pageInfo), HttpStatus.OK);
    }
    // 커피 삭제
    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {

        coffeeService.deleteCoffee(coffeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
