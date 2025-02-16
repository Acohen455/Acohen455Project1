@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
}