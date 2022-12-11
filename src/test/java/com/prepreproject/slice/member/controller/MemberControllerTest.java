package com.prepreproject.slice.member.controller;

import com.google.gson.Gson;
import com.prepreproject.member.dto.MemberDto;
import com.prepreproject.member.entity.Member;
import com.prepreproject.member.mapper.MemberMapper;
import com.prepreproject.member.service.MemberService;
import com.prepreproject.stamp.Stamp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    // Mock DI
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @Autowired
    private MemberMapper mapper;

    // Controller 테스트는 클라이언트의 요청에 대한 응답값을 검증!

    // postTest : request(MemberPostDto), response(HttpStatus:created, HttpHeader:uri)
    @Test
    void postMemberTest() throws Exception {
        //given - 테스트에 주어져야 하는 전제 조건 -> MemberPostDto
        MemberDto.Post post = new MemberDto.Post();
        post.setName("홍길동");
        post.setEmail("hgd@gmail.com");
        post.setPhone("010-1111-1111");

        Member member = mapper.memberPostDtoToMember(post);
        member.setStamp(new Stamp());

        // Dto -> 객체 직렬화
        String postContent = gson.toJson(post);

        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        //when - 테스트를 진행할 로직 -> post
        ResultActions actions =
                mockMvc.perform(post("/v1/members")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postContent));

        //then - 테스트에서 검증해야 할 것
        actions.andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/v1/members/"))));

    }
    // patchTest : request(MemberPatchDto), response(HttpStatus:OK, 변경된 회원 정보)
    // getTest : request(memberId), response(HttpStatus:Ok, 회원 정보)
    // get2Test : request(page, size), response(HttpStatus:OK, 회원 목록 페이지네이션)
    // deleteTest : request(memberId), response(HttpStatus: No Content)

}
