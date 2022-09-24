package com.example.springbootformation.domain.usecases;

import com.example.springbootformation.domain.Utilisateur;
import com.example.springbootformation.domain.repositories.UtilisateurRepository;
import org.springframework.stereotype.Component;

@Component
public class RecupererUtilisateurConnecteUseCase {

  public final UtilisateurRepository utilisateurRepository;

  public RecupererUtilisateurConnecteUseCase(UtilisateurRepository utilisateurRepository) {
    this.utilisateurRepository = utilisateurRepository;
  }

  public Utilisateur executer() {
    return this.utilisateurRepository.recupererUtilisateurConnecte();
  }
}
