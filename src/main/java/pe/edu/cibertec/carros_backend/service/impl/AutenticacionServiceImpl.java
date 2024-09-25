package pe.edu.cibertec.carros_backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.carros_backend.DTO.ConsultaRequestDTO;
import pe.edu.cibertec.carros_backend.service.AutenticacionService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class AutenticacionServiceImpl implements AutenticacionService {

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public String[] validarConsulta(ConsultaRequestDTO consultaRequestDTO) throws IOException {

        String[] datosVehiculo = null;
        Resource resource = resourceLoader.getResource("classpath:vehiculos.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (consultaRequestDTO.placa().equals(datos[1])) {
                    datosVehiculo = new String[6];
                    datosVehiculo[0] = datos[2]; // Marca
                    datosVehiculo[1] = datos[3]; // Modelo
                    datosVehiculo[2] = datos[4]; // Nro asientos
                    datosVehiculo[3] = datos[5]; // Precio
                    datosVehiculo[4] = datos[6]; // Color
                    break;
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        return datosVehiculo;
    }
}

