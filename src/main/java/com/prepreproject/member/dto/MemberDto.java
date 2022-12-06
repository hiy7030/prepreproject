package com.prepreproject.member.dto;

import com.prepreproject.member.entity.Member;
import com.prepreproject.stamp.Stamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @Setter
    public static class Post {

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
        private String phone;
    }

    @Getter
    @Setter
    public static class Patch {

        private long memberId;

        @NotBlank
        @Email(message = "정확한 이메일 주소가 아닙니다.")
        private String email;

        @NotBlank
        private String name;

        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$")
        private String phone;

        private Member.MemberStatus memberStatus;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response { // 유효성 검증에서 제외! 당연함 서버에서 보내주는 정보임..

        private long memberId;
        private String email;
        private String name;
        private String phone;
        private Member.MemberStatus memberStatus;
        private int stampCount;
        private LocalDateTime createAt;

    }
}
