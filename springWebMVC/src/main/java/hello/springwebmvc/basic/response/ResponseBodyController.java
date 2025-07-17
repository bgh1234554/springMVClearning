package hello.springwebmvc.basic.response;

import hello.springwebmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@Controller
//@RestController
// - @RestController 애노테이션을 사용하면,
// 해당 컨트롤러에 모두 @ResponseBody가 적용되는 효과가 있다.
// 따라서 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에 직접 데이터를 입력한다.
// 그래서 REST API를 만들 때 사용하는 컨트롤러라고 보면 된다.
public class ResponseBodyController {

    //HTTP 응답 - HTTP API, 메시지 바디에 직접 입력하는 방법

    /**
     * 서블릿을 직접 다룰 때처럼, HTTP 메시지 바디에 직접 응답 메시지를 싣는다.
     */
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * HttpEntity -> ResponseEntity 를 통해 반환하는 방법
     */
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }

    //JSON 형식으로 반환하는 방법
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("hello");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //ResponseStatus를 어노테이션으로 만들 수 있다.
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}
