package com.example.demo.security.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

@Repository("Oracle-12g")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    @Autowired
    @Qualifier("oracle19CTemplate")
    private JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {

        String userAuthQuery = String.format(
                "select USERS.*,authorities.authority from USERS INNER JOIN AUTHORITIES ON USERS.username = AUTHORITIES.username where upper(USERS.username)=upper('%s')",
                username);
        List<ApplicationUser> applicationUserResult = null;

        try{
            applicationUserResult = jdbcTemplate.query(userAuthQuery,new Object[]{},
                    (resultSet, rowNUm) -> new ApplicationUser(
                            resultSet.getString("authority"),
                            resultSet.getString("password"),
                            resultSet.getString("username"),
                            resultSet.getString("isAccountNonExpired"),
                            resultSet.getString("isAccountNonLocked"),
                            resultSet.getString("isCredentialsNonExpired"),
                            resultSet.getString("isEnable")
                    ));
            System.out.println(String.format("Encoded Password: %s",passwordEncoder.encode("password")));
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println("result: "+applicationUserResult);

        Optional<ApplicationUser> first = applicationUserResult
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername())).findFirst();
        return first;
    }


    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        STUDENT.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "Jane",
                        true,
                        true,
                        true,
                        true

                ),new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "Mike",
                        true,
                        true,
                        true,
                        true

                ),new ApplicationUser(
                        ADMIN_TRAINEE.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "Paul",
                        true,
                        true,
                        true,
                        true

                )
        );

        return applicationUsers;
    }
}
