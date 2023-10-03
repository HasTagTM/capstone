package com.luxstylehub.server.security.runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.luxstylehub.server.security.entity.ERole;
import com.luxstylehub.server.security.entity.Indirizzo;
import com.luxstylehub.server.security.entity.Prodotto;
import com.luxstylehub.server.security.entity.Role;
import com.luxstylehub.server.security.payload.RegisterDto;
import com.luxstylehub.server.security.repository.ProdottoRepository;
import com.luxstylehub.server.security.repository.RoleRepository;
import com.luxstylehub.server.security.repository.UserRepository;
import com.luxstylehub.server.security.service.AuthService;
import com.luxstylehub.server.security.service.IndirizzoService;
import com.luxstylehub.server.security.service.ProdottoService;
import com.opencsv.bean.CsvToBeanBuilder;


import jakarta.transaction.Transactional;


@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	@Autowired ProdottoRepository prodRep;
	@Autowired IndirizzoService as;
	
	private Set<Role> adminRole;
	private Set<Role> moderatorRole;
	private Set<Role> userRole;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		// Da lanciare solo la prima volta
		
		//setRoleDefault();
		stampaProdotti();
	}
	
	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		Role moderator = new Role();
		moderator.setRoleName(ERole.ROLE_MODERATOR);
		roleRepository.save(moderator);
		
		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(moderator);
		adminRole.add(user);
		
		moderatorRole = new HashSet<Role>();
		moderatorRole.add(moderator);
		moderatorRole.add(user);
		
		userRole = new HashSet<Role>();
		userRole.add(user);
	}
	
	private void saveAdressDb() throws IllegalStateException, IOException {
		List<Indirizzo> listaAdress = new CsvToBeanBuilder<Indirizzo>(
				new FileReader("../server_Airsound/src/main/resources/csv/Elenco-comuni-italiani.csv"))
				.withSeparator(';').withType(Indirizzo.class).build().parse();
		for (int i = 1; i < listaAdress.size(); i++) {
			as.add(listaAdress.get(i));
			//System.out.println(listaAdress.get(i));
		}
	}
	
	@Transactional
	private List<Prodotto> stampaProdotti(){
		List<Prodotto> prodotti = (List<Prodotto>) prodRep.findAll();
		System.out.println(prodotti);
		return prodotti;
	}

	public void saveAdmin() {
		RegisterDto admin=new RegisterDto();
		
		
		admin.setEmail("prova@gmail.com");
		admin.setName("Marco");
		admin.setPassword("admin");
		admin.setUsername("admin");
		Set<String> roles = new HashSet<String>();
		roles.add("ADMIN");
		admin.setRoles(roles);
		
		authService.register(admin);
	}
}
