package com.training.petfood.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public List<UserDto> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserDto> getById(@PathVariable String id){
        int userId = Integer.parseInt(id);
        return userRepository.findById(userId);
    }

    @GetMapping("/search")
    public List<UserDto> getByName(@RequestParam String name){
        return userRepository.findByName(name);
    }

    @PostMapping()
    public UserDto create(@RequestBody Map<String, String> body){
        String name = body.get("name");
        Integer salary = Integer.parseInt(body.get("salary"));
        return userRepository.save(new UserDto(name, salary));
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable String id){
        int userId = Integer.parseInt(id);
        UserDto userToDelete = userRepository.findById(userId).orElse(null);
        userRepository.delete(userToDelete);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable String id, @RequestBody Map<String, String> body){
        int userId = Integer.parseInt(id);
        // getting blog
        UserDto userToUpdate = userRepository.findById(userId).orElse(null);
        userToUpdate.setName(body.get("name"));
        userToUpdate.setSalary(Integer.parseInt(body.get("salary")));
        return userRepository.save(userToUpdate);
    }
}
