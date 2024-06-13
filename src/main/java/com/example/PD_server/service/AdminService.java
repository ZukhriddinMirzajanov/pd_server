package com.example.PD_server.service;

import com.example.PD_server.dto.AdminDto;
import com.example.PD_server.exception.ResourceNotFoundException;
import com.example.PD_server.model.User;
import com.example.PD_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public User createAdmin(AdminDto adminDto) {
        User user = new User();
        user.setEmail(adminDto.getEmail());
        user.setEnabled(adminDto.isEnabled());
        return userRepository.save(user);
    }

    public List<User> getAllAdmins() {
        return userRepository.findAll();
    }

    public User getAdminById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
    }

    public User updateAdmin(Long id, AdminDto adminDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        user.setEmail(adminDto.getEmail());
        user.setEnabled(adminDto.isEnabled());
        return userRepository.save(user);
    }

    public void deleteAdmin(Long id) {
        userRepository.deleteById(id);
    }

    public User updateStatus(Long id, boolean enabled) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        user.setEnabled(enabled);
        return userRepository.save(user);
    }

    public AdminDto toDto(User user) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(user.getId());
        adminDto.setUsername(user.getUsername());
        adminDto.setEmail(user.getEmail());
        adminDto.setEnabled(user.isEnabled());
        return adminDto;
    }
}
