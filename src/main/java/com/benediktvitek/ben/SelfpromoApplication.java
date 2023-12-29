package com.benediktvitek.ben;

import com.benediktvitek.ben.models.Role;
import com.benediktvitek.ben.models.UserEntity;
import com.benediktvitek.ben.repositories.RoleRepository;
import com.benediktvitek.ben.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SelfpromoApplication implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserEntityRepository userEntityRepository;

	public static void main(String[] args) {
		SpringApplication.run(SelfpromoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.save(new Role("USER"));
		UserEntity anon = new UserEntity();
		anon.setUsername("anonymous");
		userEntityRepository.save(anon);

	}
}
