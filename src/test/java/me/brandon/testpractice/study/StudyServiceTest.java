package me.brandon.testpractice.study;

import me.brandon.testpractice.domain.Member;
import me.brandon.testpractice.domain.Study;
import me.brandon.testpractice.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createNewStudy() {
        StudyService studyService = new StudyService(memberService, studyRepository);
        Assertions.assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("kkk@gmail.com");

        Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member));
        System.out.println(memberService.findById(1L).get().getEmail());

        Study study = new Study(10, "java");
        when(studyRepository.save(study)).thenReturn(study);

        //Mockito.when(memberService.findById(1L)).thenThrow(new IllegalArgumentException());
        //Assertions.assertThrows(IllegalArgumentException.class, () -> memberService.validate(1L));
        //doThrow(new RuntimeException()).when(memberService).validate(1L);
        //memberService.validate(1L);


        studyService.createNewStudy(1L, study);
        verify(memberService, times(2)).notify(study);
        verify(memberService, never()).validate(any());


        //Optional<Member> optionalMember = memberService.findById(1L);
        //Assertions.assertNotNull(optionalMember);


    }
}
