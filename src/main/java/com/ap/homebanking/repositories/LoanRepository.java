package com.ap.homebanking.repositories;

import com.ap.homebanking.models.Client;
import com.ap.homebanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository <Loan, Long> {
    Loan findByName(String name);
}
