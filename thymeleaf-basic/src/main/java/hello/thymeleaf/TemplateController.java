package hello.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
타임리프에 템플릿 추가하기
 */
@Controller
@RequestMapping("/template")
public class TemplateController {

    @GetMapping("/fragment")
    public String fragment() {
        return "template/fragment/fragmentMain";
    }

    /*
    코드 조각을 레이아웃에 넘겨서 사용하기

    <head>에 공통적으로 사용하는 정보를 한 곳에 모아둔 뒤,
    새 html을 만들 때마다 기존 정보를 불러온 뒤에,
    파일의 특색에 맞춰 변형시켜 보자.

    조각 표현식: ~{...} 명심하기. layoutMain.html에서 사용한다.
     */
    @GetMapping("/layout")
    public String layout() {
        return "template/layout/layoutMain";
    }

    /*
    HTML 전체에 템플릿 적용해보기
     */
    @GetMapping("/layoutExtend")
    public String layoutExtend() {
        return "template/layoutExtend/layoutExtendMain";
    }
}
