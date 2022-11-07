package com.example.userservice.repository;

import com.example.userservice.entity.MsUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<MsUser, Long> {
}
