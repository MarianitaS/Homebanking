package com.ap.homebanking.repositories;

import com.ap.homebanking.dtos.ClientDto;
import com.ap.homebanking.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClientRepositiry extends JpaRepository <Client, Long>{
     ClientDto findByEmail (String email);


}
