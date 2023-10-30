package dev.arhimedes.controller;

import dev.arhimedes.Entity.Authority;
import dev.arhimedes.Entity.User;
import dev.arhimedes.dto.AdminFrontUserDTO;
import dev.arhimedes.dto.RoleUpdateDTO;
import dev.arhimedes.dto.UserUpdateDTO;
import dev.arhimedes.repository.RoleRepository;
import dev.arhimedes.repository.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
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
            adminFrontUserDTO.setActive(user.isAccountActive());
            frontUserDTOS.add(adminFrontUserDTO);
        }
        return frontUserDTOS;
    }

    @GetMapping("/updateRole")
    public ResponseEntity<?> updateRole(@RequestBody RoleUpdateDTO roleUpdateDTO){
        User user = repository.findByEmail(roleUpdateDTO.getEmail());
        try {
            Authority.valueOf(roleUpdateDTO.getRole());
            user.addRole(roleUpdateDTO.getRole());
            repository.save(user);
            return new ResponseEntity<>("Role added to the user", HttpStatus.ACCEPTED);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(user);
        return new ResponseEntity<>("No such role exist", HttpStatus.CONFLICT);
    }

    @PostMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestBody String email){
        if(repository.existsByEmail(email)){
            AdminFrontUserDTO adminFrontUserDTO = new AdminFrontUserDTO();
            User user = repository.findByEmail(email);
            adminFrontUserDTO.setId(user.getId());
            adminFrontUserDTO.setName(user.getName());
            adminFrontUserDTO.setEmail(user.getEmail());
            adminFrontUserDTO.setRoleList(user.getRoles());
            adminFrontUserDTO.setActive(user.isAccountActive());
            adminFrontUserDTO.setCreatedOn(user.getCreatedOn());
            return new ResponseEntity<>(adminFrontUserDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("No such user exist", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/dashboardData")
    public Map<String, Integer> getData(){
        Map<String, Integer> dashboard = new HashMap<>();
        dashboard.put("Total User", repository.getCount());
        dashboard.put("Active User", repository.getActiveAccountCount());
        dashboard.put("Managers", roleRepository.getManagersCount());
        return dashboard;
    }

    @GetMapping("/activeUsers")
    public List<AdminFrontUserDTO> getActiveUsers(){
        List<User> activeUsers = repository.getActiveUsers();
        List<AdminFrontUserDTO> frontUserDTOS = activeUsers.stream()
                .map(user -> new AdminFrontUserDTO(
                        user.getId(),
                         user.getName(),
                         user.getEmail(),
                         user.getRoles(),
                         user.getCreatedOn(),
                         user.isAccountActive()
                 )).toList();
        return frontUserDTOS;
    }

    @GetMapping("/currentUser")
    public ResponseEntity<?> getUserName(Principal principal){
        String name = repository.findByEmail(principal.getName()).getName();
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO, @Param("id") long id){
        if(repository.existsById(id)){
            User user = repository.findById(id).get();
            user.setName(userUpdateDTO.getName());
            user.setEmail(userUpdateDTO.getEmail());
            user.setAccountActive(userUpdateDTO.isAccountStatus());
            System.out.println(LocalDateTime.now());
            user.setUpdatedOn(LocalDateTime.now());
            String[] roles = userUpdateDTO.getRole().split(" ");
            for(String role: roles){
                System.out.println(role);
                user.addRole(role);
            }
            repository.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


