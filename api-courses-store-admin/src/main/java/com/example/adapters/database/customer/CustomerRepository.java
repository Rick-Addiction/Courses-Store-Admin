package com.example.adapters.database.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, String> {

    @Query(value = "SELECT * FROM TB_CUSTOMER WHERE id_customer = :idCustomer", nativeQuery = true)
    Optional<CustomerModel> findByIdCustomer(@Param(value = "idCustomer")String idCustomer);
}