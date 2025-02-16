//setting up our security configuration


//deleting this in favor of no security for now

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    //setting up built in passwordencoder implementation to work as a bean
    //this comes native with spring security
    //we can use the default BCryptPasswordEncoder, as we don't need any ridiculous security measures
    //If we were working on a DOD pap or something there's always argon2 or SCrypt, but not necessary here
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    //setting up the security filter chain
    //this filters incoming HTTP requests for security
    //spring security has a default filter, but we can create our own
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/**").hasAuthority("USER"))
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

}

