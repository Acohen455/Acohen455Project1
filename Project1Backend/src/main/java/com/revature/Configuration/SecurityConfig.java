//setting up built in passwordencoder implementation to work as a bean
//this comes native with spring security

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}