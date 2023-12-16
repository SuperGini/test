package com.gini.repository;

import com.gini.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface RouteRepository extends JpaRepository<Route, String> {
}
