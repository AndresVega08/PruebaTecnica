package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.model.Producto;
import com.pruebatecnica.demo.repository.ProductoRepository;
import com.pruebatecnica.demo.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;  // Mock del servicio

    @MockBean
    private ProductoRepository productoRepository;  // Mock del repositorio

    @Test
    void obtenerTodos() throws Exception {
        Producto producto1 = new Producto(1L, "Producto A", "Descripción del producto A", 10.0);
        Producto producto2 = new Producto(2L, "Producto prueba", "Este es un producto de prueba", 20.0);

        // Configuración del comportamiento del mock para ProductoRepository
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));
        // Configuración del comportamiento del mock para ProductoService
        when(productoService.obtenerTodos()).thenReturn(Arrays.asList(producto1, producto2));

        mockMvc.perform(get("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Producto A"))
                .andExpect(jsonPath("$[0].descripcion").value("Descripción del producto A"))
                .andExpect(jsonPath("$[0].precio").value(10.0))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nombre").value("Producto prueba"))
                .andExpect(jsonPath("$[1].descripcion").value("Este es un producto de prueba"))
                .andExpect(jsonPath("$[1].precio").value(20.0));
    }
}
