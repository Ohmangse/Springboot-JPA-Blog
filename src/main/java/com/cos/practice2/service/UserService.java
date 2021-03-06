package com.cos.practice2.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.practice2.model.RoleType;
import com.cos.practice2.model.UserTB;
import com.cos.practice2.repository.UserRepository;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void SignUpService(UserTB user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	
//	@Transactional(readOnly=true)	//Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
//	public UserTB LoginService(UserTB user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
