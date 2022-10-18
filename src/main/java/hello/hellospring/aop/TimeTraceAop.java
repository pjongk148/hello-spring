package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //@Component로 직접 등록 or springconfig에 직접 @bean 등록
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //적용범위 설정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("start : " + joinPoint.toString());
        try {
            //다음 메서드로 진행
            return joinPoint.proceed();
        }

        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("finish : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
