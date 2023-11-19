package com.yelnar.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Getter
@Setter
public class Roles extends AbstractEntity implements GrantedAuthority {
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> userSet;

    @Override
    public String getAuthority() {
        return name;
    }
}
