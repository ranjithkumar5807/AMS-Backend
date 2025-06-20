package ams.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ams.model.Farmer;

public class FarmerUserDetails implements UserDetails {

    private final Farmer farmer;

    public FarmerUserDetails(Farmer farmer) {
        this.farmer = farmer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = "ROLE_" + farmer.getRole().name(); // e.g., "ROLE_FARMER"
        return Collections.<GrantedAuthority>singleton(new SimpleGrantedAuthority(roleName));
    }


    @Override
    public String getPassword() {
        return farmer.getPassword(); // Ensure this is encoded
    }

    @Override
    public String getUsername() {
        return farmer.getEmail(); // Or farmer.getUsername() if applicable
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
