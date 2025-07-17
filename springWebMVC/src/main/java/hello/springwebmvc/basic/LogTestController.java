package hello.springwebmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
/*
@RestController 는 반환 값으로 뷰를 찾는 것이 아니라,
HTTP 메시지 바디에 바로 입력한다.
따라서 실행 결과로 ok 메세지를 받을 수 있다
 */

/*
로깅 관련

LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
개발 서버는 DEBUG, 운영 서버는 INFO 출력

중괄호를 사용해야 레벨에 따른 로그가 나온다.

장점
1. 출력 모양 자유롭게 조절
2. 부가 정보 함께 보기 가능
3. 로그 레벨에 따라 무엇이 출력될지 조정 가능
4. 파일, 네트워크 등 로그를 별도의 위치에 남길 수 있다.
5. 성능도 일반 System.out보다 좋다.
 */