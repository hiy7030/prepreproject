package com.prepreproject.coffee.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

public class CoffeeDto {
    @Getter
    @Setter
    public static class Post {

        @Pattern(regexp = "^\\S+(\\s?\\S+)*$")
        private String korName;

        @Pattern(regexp = "^\\S+(\\s?\\S+)*$")
        private String engName;

        @Range(min = 100, max = 50000)
        private int price;

        @Pattern(regexp = "^[A-Z]{3}$")
        private String coffeeCode;
    }

    public static class Patch {

        private long coffeeId;

        @Pattern(regexp = "^\\S+(\\s?\\S+)*$")
        private String korName;

        @Pattern(regexp = "^\\S+(\\s?\\S+)*$")
        private String engName;

        @Range(min = 100, max = 50000)
        private int price;

        @Pattern(regexp = "^[A-Z]{3}$")
        private String coffeeCode;
    }

    public static class Response {
        private long coffeeId;
        private String korName;
        private String engName;
        private int price;
        private String coffeeCode;
    }
}