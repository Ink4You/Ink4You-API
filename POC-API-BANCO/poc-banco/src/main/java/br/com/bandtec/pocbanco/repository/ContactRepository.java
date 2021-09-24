package br.com.bandtec.pocbanco.repository;

import br.com.bandtec.pocbanco.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {}
