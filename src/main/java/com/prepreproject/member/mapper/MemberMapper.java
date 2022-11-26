package com.prepreproject.member.mapper;

import com.prepreproject.member.dto.MemberDto;
import com.prepreproject.member.entity.Member;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    // postDto -> member
    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
    // patchDto -> member
    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);
    // member -> responseDto
    MemberDto.Response memberToMemberResponseDto(Member member);
    // List<Member> -> List<responseDto>??????
    List<MemberDto.Response> membersToMemberResponseDtos(List<Member> members);
}
