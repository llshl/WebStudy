package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //등록된 빈 찾기
        //basePackages = "hello.core.memeber",    //member패키지부터 컴포넌트 찾기
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)   //AppConfig는 제외하자
public class AutoAppConfig {
}
