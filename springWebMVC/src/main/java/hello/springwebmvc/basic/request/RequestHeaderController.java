package hello.springwebmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
//알아서 log 변수를 선언해준다. 개발자는 그냥 log 변수를 호출하면 된다.
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request, HttpServletResponse response,
                          HttpMethod httpMethod, Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie) {
        log.info("request={}", request);
        log.info("response={}", response);
        //HttpMethod - HTTP 메서드 조회
        log.info("httpMethod={}", httpMethod);
        //Locale - locale 정보 조회
        log.info("locale={}", locale);
        //모든 HTTP 헤더를 MultiValueMap의 형식으로 조회
        //Map인데 하나의 키에 여러 값을 받을 수 있는 형식
        log.info("headerMap={}", headerMap);
        //특정 HTTP 헤더를 조회 (이 경우에는 host)
        log.info("header host={}", host);
        //특정 쿠키를 조회한다 required -> 필수값 여부
        log.info("myCookie={}", cookie);
        return "ok";
    }
}
