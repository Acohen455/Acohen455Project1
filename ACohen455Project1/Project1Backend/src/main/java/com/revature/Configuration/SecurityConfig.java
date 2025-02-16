//setting up built in passwordencoder implementation to work as a bean
//this comes native with spring security

//we can use the default BCryptPasswordEncoder, as we don't need any ridiculous security measures
//If we were working on a DOD pap or something there's always argon2 or SCrypt, but not necessary here
@Bean
public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
}

