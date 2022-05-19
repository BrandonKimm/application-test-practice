package me.brandon.testpractice.study;


import me.brandon.testpractice.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}