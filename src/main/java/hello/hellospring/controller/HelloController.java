package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //웹 애플리케이션의 첫번째 진입점
public class HelloController {

    @GetMapping("hello") //웹 어플리케이션에서 /hello 라고 들어오면 이 메서드를 호출
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello"; //resources의 templates 의 hello와 동일, 이 파일을 찾아서 랜더링 하게됨
        //model -> mvc(model view controller)의 모델에 데이터 입력
    }

    @GetMapping("hello-mvc") //requestparam을 통해 외부에서 parameter를 가져옴
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody //http 통신 프로토콜상 Body에 내용을 직접 넣겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //html 없이 받은 데이터를 그대로 내려주는 형식
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
