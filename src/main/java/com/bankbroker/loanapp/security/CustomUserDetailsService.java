package com.bankbroker.loanapp.security;

import com.bankbroker.loanapp.entity.AdminUser;
import com.bankbroker.loanapp.entity.Customer;
import com.bankbroker.loanapp.repository.AdminUserRepository;
import com.bankbroker.loanapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminUserRepository adminRepo;
    private final CustomerRepository customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser admin = adminRepo.findByEmail(username).orElse(null);
        if (admin != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + admin.getRole().name());
            return new User(admin.getEmail(), admin.getPassword(), List.of(authority));
        }

        Customer customer = customerRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + customer.getRole().name());
        return new User(customer.getEmail(), customer.getPassword(), List.of(authority));
    }
}
