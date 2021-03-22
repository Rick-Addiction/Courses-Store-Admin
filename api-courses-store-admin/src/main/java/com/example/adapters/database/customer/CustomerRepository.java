package com.example.adapters.database.customer;

import java.util.Optional;

import com.example.adapters.database.customer.model.CustomerModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, String> {

    @Query(value = "SELECT * FROM TB_CUSTOMER WHERE id_customer = :idCustomer", nativeQuery = true)
    Optional<CustomerModel> findByIdCustomer(@Param(value = "idCustomer")String idCustomer);
}