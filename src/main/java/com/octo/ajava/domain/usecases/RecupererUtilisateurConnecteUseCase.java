package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.Utilisateur;
import com.octo.ajava.domain.repositories.UtilisateurRepository;
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
