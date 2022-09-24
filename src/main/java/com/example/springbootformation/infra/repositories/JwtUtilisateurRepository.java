package com.example.springbootformation.infra.repositories;

import com.example.springbootformation.domain.Utilisateur;
import com.example.springbootformation.domain.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class JwtUtilisateurRepository implements UtilisateurRepository {
  @Override
  public Utilisateur recupererUtilisateurConnecte() {
    return null;
  }
}
