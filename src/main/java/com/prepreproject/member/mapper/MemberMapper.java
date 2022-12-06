package com.prepreproject.member.mapper;

import com.prepreproject.member.dto.MemberDto;
import com.prepreproject.member.entity.Member;
import com.prepreproject.stamp.Stamp;
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
    default MemberDto.Response memberToMemberResponseDto(Member member) {
           MemberDto.Response memberResponseDto = new MemberDto.Response();
           memberResponseDto.setMemberId(member.getMemberId());
           memberResponseDto.setEmail(member.getEmail());
           memberResponseDto.setName(member.getName());
           memberResponseDto.setPhone(member.getPhone());
           memberResponseDto.setMemberStatus(member.getMemberStatus());
           memberResponseDto.setCreateAt(member.getCreateAt());
           if(member.getStamp() != null) {
               memberResponseDto.setStampCount(member.getStamp().getStampCount());
           } else {
               memberResponseDto.setStampCount(0);
           }
           return memberResponseDto;
    }
    // List<Member> -> List<responseDto>??????
    List<MemberDto.Response> membersToMemberResponseDtos(List<Member> members);
}
