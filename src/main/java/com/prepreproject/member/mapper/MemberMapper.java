package com.prepreproject.member.mapper;

import com.prepreproject.member.dto.MemberDto;
import com.prepreproject.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    // postDto -> member
    Member memberPostDtoToMember(MemberDto.Post memberPostDto);
    // patchDto -> member
    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);
    // member -> responseDto
    MemberDto.Response memberToMemberResponseDto(Member member);
}
