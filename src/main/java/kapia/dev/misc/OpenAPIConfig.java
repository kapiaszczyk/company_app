package kapia.dev.misc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${dev-url}")
    private String devUrl;

    @Value("${prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in production environment");

        Contact contact = new Contact();
        contact.setEmail("some-addres@gmail.com");
        contact.setName("Some Company");
        contact.setUrl("https://www.some-url.com");

        Info info = new Info()
                .title("Company Internal API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints for accessing internal data.").termsOfService("some-url.com/terms");

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}