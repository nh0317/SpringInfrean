package hello.hellospring.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//spring bean에 추가해야함
@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* hello.hellospring.service..*(..))") //적용할 범위 지정
    public  Object excut(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("Start: "+ joinPoint.toString());
        try{
            return joinPoint.proceed();// 함수 진행
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("End: "+ joinPoint.toString()+" "+ timeMs +"ms");

        }
    }
}
//AOP를 적용하면
//컨트롤러에서 가짜 memberService(프록시)를 생성, proceed이후에
//진짜 memberService를 생상한다.
