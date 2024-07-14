package com.epam.repository;

import com.epam.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    Optional<Library> findByUsernameAndBookId(String username, Long bookId);

    Optional<Library> findByUsername(String username);

    @Query("Select bookId from Library l where l.username=?1")
    List<Long> findAllBookIdWithUsername(String username);


}
