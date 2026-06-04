package com.example.models;

import java.util.Set;

public record Detalle(String nombreDpto, Set<String> correos, Set<String> numTlfo) {

}
