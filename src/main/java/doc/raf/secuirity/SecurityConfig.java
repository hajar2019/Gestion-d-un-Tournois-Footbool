package doc.raf.secuirity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
    // pour initialiser la securité
    //Pour personnaliser la securite on a besoin de definir deux methodes

    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Autowired
    DataSource DataSource;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("raf").password("{noop}2002").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("doc").password("{noop}2002").roles("USER");
       
        // auth.jdbcAuthentication().dataSource(DataSource)
        // .usersByUsernameQuery("select username,active from user where username=?") 
        // .authoritiesByUsernameQuery("select username, role from user where username=?")
        // .passwordEncoder(new BCryptPasswordEncoder());
    }

    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
   
    http.authorizeRequests()
    .antMatchers("/home").permitAll()
    .antMatchers("**").hasRole("ADMIN")
    .antMatchers("/joueur").hasRole("USER")
    .and()
    .formLogin();

    }

    

    
    
}
