package es.iessoterohernandez.BBaker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.iessoterohernandez.BBaker.service.JwtFilter;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    UserDetailsService userService;

    @Autowired
    JwtFilter jwtFilter;

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

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
              //  .antMatchers("/api/admin").hasRole("ADMIN")
              //  .antMatchers("/api/user").hasAnyRole("ADMIN", "CLIENT")
                .antMatchers("/").permitAll()
                .antMatchers("/*").permitAll()
                .antMatchers("/img/*").permitAll()
                .antMatchers("/css/*").permitAll()
                .antMatchers("/js/*").permitAll()
                .antMatchers("/api/registration").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/login/getuser/").permitAll()
                .antMatchers("/api/login/*").permitAll()
                .antMatchers("/api/products/*").permitAll()
                .antMatchers("/api/products").permitAll()
                .antMatchers("/api/BBorder/*").permitAll()
                .antMatchers("/api/BBorder/products/*").permitAll()
                .antMatchers("/api/BBorder/myorders/*").permitAll()
                .antMatchers("/api/delivery*").permitAll()
                .antMatchers("/api/delivery/*").permitAll()
                .antMatchers("/api/delivery/order/*").permitAll()
                //.hasRole("ADMIN")
                .anyRequest()
                .authenticated().and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
    }

}
