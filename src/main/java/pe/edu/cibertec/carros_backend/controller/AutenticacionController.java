package pe.edu.cibertec.carros_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.carros_backend.DTO.ConsultaRequestDTO;
import pe.edu.cibertec.carros_backend.DTO.ConsultaResponseDTO;
import pe.edu.cibertec.carros_backend.service.AutenticacionService;

@RestController
@RequestMapping("/autenticacion")
public class AutenticacionController {
    @Autowired
    AutenticacionService autenticacionService;

    @PostMapping("/inicio")
    public ConsultaResponseDTO login(@RequestBody ConsultaRequestDTO consultaRequestDTO) {


        try {
            String[] datosVehiculo = autenticacionService.validarConsulta(consultaRequestDTO);
            if (datosVehiculo == null) {
                return new ConsultaResponseDTO(consultaRequestDTO.placa(), "ERROR: VEHÍCULO NO ENCONTRADO", "", "", 0, 0.0, "");
            }
            return new ConsultaResponseDTO(consultaRequestDTO.placa(), "Consulta exitosa", datosVehiculo[0], datosVehiculo[1], Integer.parseInt(datosVehiculo[2]), Double.parseDouble(datosVehiculo[3]), datosVehiculo[4]);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ConsultaResponseDTO("", "ERROR: Ocurrió un problema", "", "", 0, 0.0, "");
        }

    }
}

