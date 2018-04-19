package syuu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "syuu")
public class SpringBootCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCommonApplication.class, args);
    }
}
