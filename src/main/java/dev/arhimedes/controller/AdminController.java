package dev.arhimedes.controller;

import dev.arhimedes.Entity.Authority;
import dev.arhimedes.Entity.User;
import dev.arhimedes.dto.AdminFrontUserDTO;
import dev.arhimedes.repository.RoleRepository;
import dev.arhimedes.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    public AdminController(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/findAll")
    public List<AdminFrontUserDTO> getAllUser(){
        List<User> userList = repository.findAll();
        List<AdminFrontUserDTO> frontUserDTOS = new ArrayList<>();
        for(User user: userList){
            AdminFrontUserDTO adminFrontUserDTO = new AdminFrontUserDTO();
            adminFrontUserDTO.setName(user.getName());
            adminFrontUserDTO.setEmail(user.getEmail());
            adminFrontUserDTO.setRoleList(user.getRoles());
            adminFrontUserDTO.setCreatedOn(user.getCreatedOn());
            frontUserDTOS.add(adminFrontUserDTO);
        }
        return frontUserDTOS;
    }

    @GetMapping("/updateRole")
    public boolean updateRole(@Param("email") String email){
        System.out.println(email);
        User user = repository.findByEmail(email);
        user.addRole(Authority.ADMIN.toString());
        System.out.println(user);
        repository.save(user);
        return true;
    }

    @GetMapping("/getUser")
    public User getUser(Principal principal){
        return repository.findByEmail(principal.getName());
    }

    @GetMapping("/dashboardData")
    public Map<String, Integer> getData(){
        Map<String, Integer> dashboard = new HashMap<>();
        dashboard.put("Total User", repository.getCount());
        return dashboard;
    }
}
