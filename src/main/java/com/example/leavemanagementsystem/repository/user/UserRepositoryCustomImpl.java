package com.example.leavemanagementsystem.repository.user;

import lombok.*;
import org.springframework.stereotype.*;

import javax.persistence.*;

@Service
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


}
