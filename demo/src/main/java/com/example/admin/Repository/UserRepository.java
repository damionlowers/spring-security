package com.example.admin.Repository;

import com.example.admin.modules.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder,NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
        public void createUser(User user) throws DataAccessException {

            int createUserResult = 3;

        String userInsertionQuery = String.format("INSERT INTO users " +
                "(username,password,isEnable,isAccountNonExpired,isAccountNonLocked,isCredentialsNonExpired) " +
                "values ('%s','%s','%s','%s','%s','%s')",
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                user.getIsEnable(),
                user.getIsAccountNonExpired(),
                user.getIsAccountNonLocked(),
                user.getIsCredentialsNonExpired());
        try{
            createUserResult = jdbcTemplate.update(userInsertionQuery);

            if(createUserResult==1){
                //create authorities for user
                System.out.println(String.format("Creating authorities for user %s",user.getUsername()));
                String userAuthoritiesCreation = String.format("INSERT INTO AUTHORITIES (USERNAME,AUTHORITY) VALUES ('%s','%s')",
                        user.getUsername(),"ADMIN_TRAINEE");
                jdbcTemplate.update(userAuthoritiesCreation);
            }
//           jdbcTemplate.execute(userInsertionQuery);

        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();

        }
        System.out.println(String.format("Result from insert user %s into USERS table: %s",user.toString(),createUserResult));

    }
}
