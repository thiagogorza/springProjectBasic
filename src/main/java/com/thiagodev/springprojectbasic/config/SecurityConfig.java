package com.thiagodev.springprojectbasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Environment env;

    private static final String[] PUBLIC_MATCHERS = {
      "/h2-console/**"

    };
    private static final String[] PUBLIC_MATCHERS_GET = { //permissao apenas para de acesso apenas para leitura
            "/produtos/**",
            "/categorias/**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
        }
        http.cors().and().csrf().disable(); // desabilita a protecao contra ataques csrf (como usuario nao tera sessao n é necessario)
        http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll()
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //nao cria sessao de usuario
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}