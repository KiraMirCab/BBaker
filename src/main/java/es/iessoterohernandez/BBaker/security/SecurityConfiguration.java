package es.iessoterohernandez.BBaker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    UserDetailsService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
              //  .antMatchers("/api/admin").hasRole("ADMIN")
              //  .antMatchers("/api/user").hasAnyRole("ADMIN", "CLIENT")
                .antMatchers("/api/registration").permitAll()
                .antMatchers("/api/products/*").permitAll()
                .antMatchers("/api/products").permitAll()
                .antMatchers("/api/BBorder/*").permitAll()
                .antMatchers("/api/BBorder/products/*").permitAll()
                .antMatchers("/api/delivery*").permitAll()
                .antMatchers("/api/delivery/*").permitAll()
                //.hasRole("ADMIN")
                .anyRequest()
                .authenticated().and()
                .formLogin();
    }

}
