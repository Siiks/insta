package com.example.inst.Repository;

import com.example.inst.Model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    @Query(value = "select * from accounts a where a.id in (select af.follower_id from accounts_followers af where following_id = :following_id)", nativeQuery = true)
    List<Accounts> findFollowersByUserId(Long following_id);

    @Query(value = "select * from accounts a where a.id in (select af.following_id from accounts_followers af where follower_id = :id)", nativeQuery = true)
    List<Accounts> findFollowingsByUserId(@Param(value = "id") Long id);

    Optional<Accounts> findAccountsByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    Optional<Accounts> findById(Long id);
}
