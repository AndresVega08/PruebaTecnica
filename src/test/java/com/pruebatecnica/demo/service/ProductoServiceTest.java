package com.pruebatecnica.demo.service;


import com.pruebatecnica.demo.model.Producto;
import com.pruebatecnica.demo.repository.ProductoRepository;
import com.pruebatecnica.demo.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    private final ProductoRepository productoRepository = mock(ProductoRepository.class);
    private final ProductoService productoService = new ProductoService(productoRepository);

    @Test
    void obtenerTodos() {
        // Arrange
        List<Producto> productos = Arrays.asList(
                new Producto(1L, "Producto 1", "Descripción 1", 10.0),
                new Producto(2L, "Producto 2", "Descripción 2", 20.0)
        );
        when(productoRepository.findAll()).thenReturn(productos);

        // Act
        List<Producto> resultado = productoService.obtenerTodos();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Producto 1", resultado.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_Encontrado() {
        // Arrange
        Producto producto = new Producto(1L, "Producto 1", "Descripción 1", 10.0);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        // Act
        Optional<Producto> resultado = productoService.obtenerPorId(1L);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Producto 1", resultado.get().getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void obtenerPorId_NoEncontrado() {
        // Arrange
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Producto> resultado = productoService.obtenerPorId(1L);

        // Assert
        assertFalse(resultado.isPresent());
        verify(productoRepository, times(1)).findById(1L);
    }
}
