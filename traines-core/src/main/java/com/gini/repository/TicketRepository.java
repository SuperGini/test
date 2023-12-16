package com.gini.repository;

import com.gini.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface TicketRepository extends JpaRepository<Ticket, String> {
}
