import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "model", "service"})
@EnableAutoConfiguration
public class ImportApplication {
  public static void main(String[] args) {
    SpringApplication.run(ImportApplication.class);
  }

}
