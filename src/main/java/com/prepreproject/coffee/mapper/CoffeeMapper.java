package com.prepreproject.coffee.mapper;

import com.prepreproject.coffee.dto.CoffeeDto;
import com.prepreproject.coffee.entity.Coffee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeeDto.Post coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeeDto.Patch coffeePatchDto);
    CoffeeDto.Response coffeeToCoffeeResponseDto (Coffee coffee);
}
