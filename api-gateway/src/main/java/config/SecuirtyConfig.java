package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




//@Configuration
//@EnableWebSecurity
//public class SecuirtyConfig {
//    @Bean
//    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .requestMatchers(new AntPathRequestMatcher("/eureka/**"))
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2ResourceServer().jwt();
//        return http.build();
//    }
//}
@Configuration
@EnableWebFluxSecurity
public class SecuirtyConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {

        serverHttpSecurity.csrf()
                .disable()
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/eureka/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated())
                .oauth2ResourceServer().jwt();

        return serverHttpSecurity.build();
    }


}
