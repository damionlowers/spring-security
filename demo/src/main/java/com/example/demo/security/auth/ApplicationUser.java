package com.example.demo.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import static com.example.demo.security.ApplicationUserRole.*;

public class ApplicationUser implements UserDetails {
   private Set<? extends GrantedAuthority> grantedAuthorities;
   private final String password;
   private final String username;
   private final boolean isAccountNonExpired;
   private final boolean isAccountNonLocked;
   private final boolean isCredentialsNonExpired;
   private final boolean isEnable;

    public ApplicationUser(Set<? extends GrantedAuthority> grantedAuthorities, String password, String username,
                           boolean isAccountNonExpired, boolean isAccountNonLocked,
                           boolean isCredentialsNonExpired, boolean isEnable) {
        this.grantedAuthorities = grantedAuthorities;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnable = isEnable;
    }

    public ApplicationUser(String userRole, String password, String username,
                           String isAccountNonExpired, String isAccountNonLocked,
                           String isCredentialsNonExpired, String isEnable) {
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired.toUpperCase().equals("Y")? true:false;
        this.isAccountNonLocked = isAccountNonLocked.toUpperCase().equals("Y")? true:false;;
        this.isCredentialsNonExpired = isCredentialsNonExpired.toUpperCase().equals("Y")? true:false;;
        this.isEnable = isEnable.toUpperCase().equals("Y")? true:false;;
        this.setGrantedAuthorities(userRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }


    private Set<? extends GrantedAuthority> setGrantedAuthorities(String userGroups){
        switch (userGroups.toUpperCase()){
            case "ADMIN":
                this.grantedAuthorities = ADMIN.getGrantedAuthorities();
                break;
            case "STUDENT":
                this.grantedAuthorities = STUDENT.getGrantedAuthorities();
                break;
            case "ADMIN_TRAINEE":
                this.grantedAuthorities = ADMIN_TRAINEE.getGrantedAuthorities();
                break;
            default:
                this.grantedAuthorities = STUDENT.getGrantedAuthorities();
        }
        return null;
    }

}
