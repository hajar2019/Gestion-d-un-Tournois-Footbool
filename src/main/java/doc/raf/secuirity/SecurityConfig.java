package doc.raf.secuirity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
    // pour initialiser la securité
    //Pour personnaliser la securite on a besoin de definir deux methodes
    
   // PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
 
    
    private MyUserDetailsService userDetailsService;

    public SecurityConfig(MyUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        
        // auth.inMemoryAuthentication().withUser("Abdouraouf").password(encoder.encode("2002")).roles("ADMIN", "USER");
        // auth.inMemoryAuthentication().withUser("doc").password(encoder.encode("2002")).roles("USER");

        // auth.jdbcAuthentication().dataSource(DataSource)
        //     .usersByUsernameQuery("select nom as principal,password as credentials,active from user where nom=?")
        //     .authoritiesByUsernameQuery("select nom,role from user where nom=?")
        //     .rolePrefix("ROLE_");
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
   
    http
    .csrf().disable()
    .authorizeRequests()
    .antMatchers("/userLogin","/userSave", "resources/**","/Css/**").permitAll()
    .antMatchers("/userRegister","/userSave", "resources/**","/Css/**").permitAll()
    .antMatchers("/add**/**","/register**/**","/edit**/**","/delete**/**").hasRole("ADMIN")
    .anyRequest().authenticated()
    .and()
    .formLogin()
    .loginPage("/userLogin")
    .defaultSuccessUrl("/home")
    .failureUrl("/login?error=true");
;
    http.exceptionHandling().accessDeniedPage("/noAutoried");

    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    } 
    
    
}
