package pe.edu.cibertec.carros_backend.service;

import pe.edu.cibertec.carros_backend.DTO.ConsultaRequestDTO;

import java.io.IOException;

public interface AutenticacionService {

    String[] validarConsulta(ConsultaRequestDTO loginRequestDTO) throws IOException;
}
