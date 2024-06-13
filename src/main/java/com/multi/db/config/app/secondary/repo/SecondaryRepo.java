package com.multi.db.config.app.secondary.repo;

import com.multi.db.config.app.secondary.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryRepo extends JpaRepository<Books,Long> {
}
