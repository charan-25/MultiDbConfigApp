package com.multi.db.config.app.primary.repo;

import com.multi.db.config.app.primary.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryRepo extends JpaRepository<Users,Long> {
}
