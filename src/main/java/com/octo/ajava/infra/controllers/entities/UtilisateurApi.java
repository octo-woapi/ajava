package com.octo.ajava.infra.controllers.entities;

import java.util.List;

public record UtilisateurApi(String username, List<String> roles) {}
