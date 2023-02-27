package com.demo.security.security;

import com.demo.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }

    // 계정의 권한 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    // User의 비밀번호 리턴
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // User의 아이디 리턴
    @Override
    public String getUsername() {
        return user.getName();
    }

    // 계정이 만료되었는지 여부 리턴
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지 여부 리턴
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정의 비밀번호가 오래 사용했는지에 대한 여부 리턴
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부 리턴
    @Override
    public boolean isEnabled() {
        return true;
    }
}
