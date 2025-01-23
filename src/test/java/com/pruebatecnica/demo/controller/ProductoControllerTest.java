package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.model.Producto;
import com.pruebatecnica.demo.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Carga explícita del controlador
@WebMvcTest
@ContextConfiguration(classes = ProductoController.class) // Importa el controlador manualmente
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        // Crear el mock manualmente
        productoService = mock(ProductoService.class);
    }

    @Test
    void obtenerTodos() throws Exception {
        // Arrange
        Producto producto1 = new Producto(1L, "Producto 1", "Descripción 1", 10.0);
        Producto producto2 = new Producto(2L, "Producto 2", "Descripción 2", 20.0);

        // Configuración del comportamiento del mock
        when(productoService.obtenerTodos()).thenReturn(Arrays.asList(producto1, producto2));

        // Act & Assert
        mockMvc.perform(get("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Producto 1"))
                .andExpect(jsonPath("$[0].descripcion").value("Descripción 1"))
                .andExpect(jsonPath("$[0].precio").value(10.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nombre").value("Producto 2"))
                .andExpect(jsonPath("$[1].descripcion").value("Descripción 2"))
                .andExpect(jsonPath("$[1].precio").value(20.0));
    }
}
