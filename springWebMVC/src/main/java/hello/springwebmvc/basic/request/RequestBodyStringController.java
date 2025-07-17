package hello.springwebmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    /*
    HTTP 메시지 바디를 통해 데이터가 직접 String 형태로 넘어오면,
    @RequestParam, @ModelAttribute 를 사용할 수 없다.
    (HTML form 형식으로 넘어오는 경우에는 Form 객체를 만들어서 받을 수 있다.)
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    /**
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    //request가 통으로 필요한게 아니니까 InputStream과 Writer 사용 가능
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
            throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream,
                StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * HttpEntity: HTTP header, body 정보를 편리하게 조회
     * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * - HTTP 헤더와 바디 정보를 편리하게 조회할 수 있는 객체
     *
     * 응답에서도 HttpEntity 사용 가능
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - 헤더 정보 포함 가능
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity){

        //String messageBody = StreamUtils.copyToString(inputStream,
        //                StandardCharsets.UTF_8);
        //위 코드를 HttpEntity가 대신 실행해서 문자로 바꿔준다.

        //문자열로 변환된 바디를 꺼내준다.
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    /*
    HttpEntity 를 상속받은 다음 객체들도 같은 기능을 제공한다.
    RequestEntity
        HttpMethod, url 정보가 추가, 요청에서 사용
    ResponseEntity
        HTTP 상태 코드 설정 가능, 응답에서 사용

        return new ResponseEntity<String>("Hello World", responseHeaders,
        HttpStatus.CREATED)
     */
    /**
     * @RequestBody
     * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     * - 헤더 정보가 필요하다면 HttpEntity나 @RequestHeader을 사용하면 된다.
     * (RequestHeaderController.java 참조)
     *
     * @ResponseBody
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - 응답 결과를 HTTP 메시지 바디에 직접 반환
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){
        log.info("messageBody={}", messageBody);

        return "ok";
    }
}
