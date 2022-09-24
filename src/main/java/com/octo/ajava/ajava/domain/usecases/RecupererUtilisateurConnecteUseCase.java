package com.octo.ajava.ajava.domain.usecases;


import com.octo.ajava.ajava.domain.Utilisateur;
import com.octo.ajava.ajava.domain.repositories.UtilisateurRepository;
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
