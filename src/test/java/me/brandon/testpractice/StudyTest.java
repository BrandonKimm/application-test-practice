package me.brandon.testpractice;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(FindSlowTestExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    //리소스 내 라이프사이클 Per Class 값에 의해 클래스단위 인스턴스 생성
    //junit.jupiter.testinstance.lifecycle.default = per_class
    int value = 1;

    @Order(2)
    @Test
    @DisplayName("스터디만들기🐶🐶🐶🐶")
    void create_new_study() {
        Study study = new Study(10);
        System.out.println(value++);

        // 람다를 사용하면 실패했을때만 문자열수식을 계산한다.
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디의 초기상태값은 DRAFT 여야한다. "),
                () -> assertTrue(study.getLimit() > 0, () -> "스터디의 최대 참석인원은 1 이상이어야 한다.")

        );
        System.out.println("create");
    }

    @Order(1)
    @Test
    @DisplayName("스터디만들기")
    void create_new_study_again() {
        System.out.println(value++);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Study(-1));

        assertEquals("limit 은 0보다 커야한다.", exception.getMessage());
        System.out.println("create");
    }

    @Test
    @DisplayName("타임아웃 테스트 10초미만")
    void timeOut_test() {
        assertTimeout(Duration.ofSeconds(2), () -> {
            new Study(10);
            Thread.sleep(1000);
        });
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


    @RepeatedTest(value = 10)
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
    }

    @ParameterizedTest
    @ValueSource(strings = {"날씨가", "많이", "추워"})
    void parameterizedTest(String message) {
        System.out.println(message);

    }

    @BeforeAll
    //@Disabled //일단 테스트수행하지 않는
    static void beforeAll() {

        System.out.println("before All");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }


}