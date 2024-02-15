package Project.CoffeVendingMachine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Value("${spring.security.user.role}")  private String userRole;
    @Value("${spring.security.user.urls}") private String[] userUrls;
    @Value("${spring.security.admin.role}") private String adminRole;
    @Value("${spring.security.admin.urls}") private String[] adminUrls;
    @Value("${user1.username}")  private String user_username;
    @Value("${user1.password}")  private String user_password;
    @Value("${user2.username}") private String admin_username;
    @Value("${user2.password}") private String admin_password;

    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username(user_username)
                .password(user_password)
                .roles(userRole)
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username(admin_username)
                .password(admin_password)
                .roles(adminRole)
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(userUrls).hasRole(userRole)
                        .requestMatchers(adminUrls).hasRole(adminRole)
                        .anyRequest().authenticated());

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
