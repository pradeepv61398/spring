package com.insurnace.customer.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurnace.customer.EntityModel.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //User findByEmail(String email);
    boolean existsByEmail(String email);
    //boolean existsByPhone(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);


}
