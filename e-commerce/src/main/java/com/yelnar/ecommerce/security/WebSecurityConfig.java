package com.yelnar.ecommerce.security;

import com.yelnar.ecommerce.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/showReg","/","/index.html","/registerUser","/login","/showLogin","/login/*")
//                .permitAll()
//                .antMatchers("/css/**","/lib/**","/images/**","/js/**").permitAll()
//                .antMatchers("/admin/showAddFlight","/admin/admin/addFlight","/admin/*").hasAnyAuthority("ADMIN").anyRequest().authenticated()
//                .and().csrf().disable();

//        http
//                .authorizeHttpRequests(auth->auth
//                        .requestMatchers("/showReg","/","/index.html","/registerUser","/login","/showLogin","/login/*").permitAll()
//                        .requestMatchers("/css/**","/lib/**","/images/**","/js/**").permitAll()
//                        .requestMatchers("/admin/showAddFlight","/admin/admin/addFlight","/admin/*").hasAnyAuthority("ADMIN").anyRequest().authenticated());
//
//
//        return http.build();
//    }



//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) ->
//                        authorize.requestMatchers("/showReg").permitAll()
//                                .requestMatchers("/users").hasRole("ADMIN")
//                ).formLogin(
//                        form -> form
//                                .loginPage("/showLogin")
//                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/findFlights",true)
//                                .permitAll()
//                ).logout(
//                        logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .permitAll()
//                );
//        return http.build();
//    }



    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/showLogin").permitAll() // Allow access to the login page
                        .requestMatchers("/login").permitAll() // Allow access to the login endpoint
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/showLogin") // Use the /showLogin URL as the login page
                        .loginProcessingUrl("/login") // URL where the login form will be submitted
                        .defaultSuccessUrl("/findFlights", true) // Redirect to /findFlights on successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL to trigger the logout process
                        .permitAll()
                );

        // Add any additional security configurations as needed

        return http.build();
    }


}
