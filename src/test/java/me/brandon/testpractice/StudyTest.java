package me.brandon.testpractice;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    @DisplayName("스터디만들기🐶🐶🐶🐶")
    void create_new_study() {
        Study study = new Study(10);

        // 람다를 사용하면 실패했을때만 문자열수식을 계산한다.
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),() -> "스터디의 초기상태값은 DRAFT 여야한다. "),
                () -> assertTrue(study.getLimit() > 0, ()-> "스터디의 최대 참석인원은 1 이상이어야 한다.")

        );
        System.out.println("create");
    }

    @Test
    @DisplayName("스터디만들기")
    void create_new_study_again() {
        IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Study(-1));

        assertEquals("limit 은 0보다 커야한다.", exception.getMessage());
        System.out.println("create");
    }

    @Test
    @DisplayName("타임아웃 테스트 10초미만")
    void timeOut_test(){
        assertTimeout(Duration.ofSeconds(2), () -> {new Study(10); Thread.sleep(500);});
    }

    @Test
    @DisplayName("환경변수에 따른 조건부테스트")
    @EnabledOnOs(OS.MAC)
    @EnabledOnJre(JRE.JAVA_11)
    void assumeTrue_test() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        Assumptions.assumeTrue(test_env == null);


    }
    @BeforeAll
    //@Disabled //일단 테스트수행하지 않는
    static void beforeAll() {

        System.out.println("before All");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("After All");
    }
    @BeforeEach
    void beforeEach(){
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach(){
        System.out.println("After Each");
    }


}