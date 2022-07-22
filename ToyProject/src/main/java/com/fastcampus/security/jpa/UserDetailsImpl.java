package com.fastcampus.security.jpa;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fastcampus.domain.User;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private User user;
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	// 계정이 가지고 있는 권한 목록 저장하여 리턴한다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> roleList = new ArrayList<>();
		
		// 권한 설정
		roleList.add(new GrantedAuthority() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return "ROLE_"+user.getRole();
			}
		});
		return roleList;
	}

	@Override
	public String getPassword() {
		// {noop}은 비밀번호를 암호화하지 않도록 하는 접두사다.
		return "{noop}" + user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료됬는지 여부를 리턴한다.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있는지 여부를 리턴한다.
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료됬는지 여부를 리턴한다.
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화 되어있는지 여부를 리턴한다.
	@Override
	public boolean isEnabled() {
		return true;
	}

}
