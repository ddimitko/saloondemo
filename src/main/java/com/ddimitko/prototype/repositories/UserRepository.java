package com.ddimitko.prototype.repositories;

import com.ddimitko.prototype.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*@Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);*/

    @Query("SELECT u FROM User u WHERE u.staffId = ?1")
    Optional<User> findByStaffId(String staffId);

    List<User> findAllByShopId(Long shopId);


}
