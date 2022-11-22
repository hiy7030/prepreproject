package com.prepreproject.member.controller;

import com.prepreproject.dto.MultiResponseDto;
import com.prepreproject.dto.SingleResponseDto;
import com.prepreproject.member.dto.MemberDto;
import com.prepreproject.member.mapper.MemberMapper;
import com.prepreproject.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@Validated
@RequestMapping("v1/members")
public class MemberController {

    // 서비스 계층과의 연동 필요! Dto -> 객체로 변환, 객체 -> Dto 변환해줄 매개체 필요
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService,
                            MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    //회원 등록
    // request body : json -> object 변환 필요, response body: object -> json 변환 필요
    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post postMemberDto) {

            return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.CREATED);
    }
    //회원 정보 수정
    // request body : patch 정보 json -> object 변환 필요, response body : object -> json 변환 필요
    // request path 정보 필요 -> path : memberId
    @PatchMapping("/{member-id}")
    public ResponseEntity  patchMember(@PathVariable("member-id") @Positive long memberId,
                                       @Valid @RequestBody MemberDto.Patch patchMemberDto) {
        return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.OK);
    }
    //회원 조회
    // request body : x, request path : memberId, response body: object -> json 변환 필요
    @GetMapping("/{member-id}")
    public ResponseEntity  getMember(@PathVariable("member-id") @Positive long memberId) {
        return new ResponseEntity<>(new SingleResponseDto<>(), HttpStatus.OK);
    }
    //회원 목록 조회
    // request body : x, request path :x , response body : pagenation
    // request parameter -> int page, int size
    @GetMapping
    public ResponseEntity getMembers(@RequestParam int page,
                                   @RequestParam int size) {

        return new ResponseEntity<>(new MultiResponseDto<>(), HttpStatus.OK);
    }
    //회원 삭제
    // request body : x, request path : memberId, response body : x
    @DeleteMapping("/{member-id}")
    public void deleteMember(@PathVariable("member-id") @Positive long memberId) {

    }
}
