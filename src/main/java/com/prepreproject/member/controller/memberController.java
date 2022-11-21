package com.prepreproject.member.controller;

import com.prepreproject.member.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("v1/members")
public class memberController {
    //회원 등록
    // request body : json -> object 변환 필요, response body: object -> json 변환 필요
    @PostMapping
    public ResponseEntity postMember(Member member) {

        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
    //회원 정보 수정
    // request body : patch 정보 json -> object 변환 필요, response body : object -> json 변환 필요
    // request path 정보 필요 -> path : memberId
    @PatchMapping("/{member-id}")
    public ResponseEntity  patchMember(@PathVariable("member-id") @Positive long memberId,
                                       Member member) {
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
    //회원 조회
    // request body : x, request path : memberId, response body: object -> json 변환 필요
    @GetMapping("/{member-id}")
    public ResponseEntity  getMember(@PathVariable("member-id") @Positive long memberId,
                                     Member member) {
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
    //회원 목록 조회
    // request body : x, request path :x , response body : pagenation
    // request parameter -> int page, int size
    @GetMapping
    public List<Member> getMembers(@RequestParam int page,
                                   @RequestParam int size) {
        return null;
    }
    //회원 삭제
    // request body : x, request path : memberId, response body : x
    @DeleteMapping("/{member-id}")
    public void deleteMember(@PathVariable("member-id") @Positive long memberId) {

    }
}
