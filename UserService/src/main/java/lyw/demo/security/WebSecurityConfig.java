 //package lyw.demo.security;
//
//import lyw.demo.pojo.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Configuration
////@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//////    配置策略
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
//////        阻止csrf跨站请求伪造
////        http.csrf().disable();
////        http.authorizeRequests().
////                antMatchers("/templates/**").permitAll().anyRequest().authenticated(). //任何用户的任意请求对指定资源都可访问
////                and().formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler()).
////                and().logout().permitAll().invalidateHttpSession(true).
////                deleteCookies("JESESSIONID").logoutSuccessHandler(logoutSuccessHandler()).
////                and().sessionManagement().maximumSessions(10).expiredUrl("/login");
////    }
////
//////    登入处理
////    @Bean
////    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler(){
////        return new SavedRequestAwareAuthenticationSuccessHandler(){
////            @Override
////            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
////                User user = (User) authentication.getPrincipal ();
////                System.out.println("132");
////                super.onAuthenticationSuccess(request, response, authentication);
////            }
////        };
////    }
////
////    @Bean
////    public LogoutSuccessHandler logoutSuccessHandler(){
////        return new LogoutSuccessHandler() {
////            @Override
////            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
////                SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
////                System.out.println("lyw");
////                httpServletResponse.sendRedirect("/login");
////            }
////        };
////    }
////
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
////        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
////        authenticationManagerBuilder.eraseCredentials(false);
////    }
////
////    @Bean
////    public UserDetailsService userDetailsService(){
////        return new UserDetailsService() {
////            @Override
////            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
////                if(!s.equals("lyw")) {
////                    try {
////                        throw new RuntimeException("not found");
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////                }
////                return new SecurityUser(new User());
////            }
////        };
////    }
//}
