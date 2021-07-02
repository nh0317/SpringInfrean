package hello.hellospring; //이 하위 패키지의 conponent를 spring bean 에 등록함
//이 패키지 하위가 아니면 spring bean에 등록하지 않음

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
