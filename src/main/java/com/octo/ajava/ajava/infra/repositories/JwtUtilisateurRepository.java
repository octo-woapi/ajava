package com.octo.ajava.ajava.infra.repositories;

import com.octo.ajava.ajava.domain.Utilisateur;
import com.octo.ajava.ajava.domain.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class JwtUtilisateurRepository implements UtilisateurRepository {
    @Override
    public Utilisateur recupererUtilisateurConnecte() {
        return null;
    }
}
