package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
