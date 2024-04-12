package edu.eci.ieti.greenwish.controller.material;

import edu.eci.ieti.greenwish.repository.document.Material;
import edu.eci.ieti.greenwish.service.material.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class MaterialControllerTest {

    @Mock
    private MaterialService materialService;

    @InjectMocks
    private MaterialController materialController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void all() {
        // Arrange
        Material material1 = new Material("1", "Material 1", "Description 1", 1);
        Material material2 = new Material("2", "Material 2", "Description 2", 2);
        when(materialService.all()).thenReturn(List.of(material1, material2));
        // Act
        ResponseEntity<List<Material>> response = materialController.all();
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void findById() {
        // Arrange
        String materialId = "1";
        Material material = new Material(materialId, "Material 1", "Description 1", 1);
        when(materialService.findById(materialId)).thenReturn(material);
        // Act
        ResponseEntity<Material> response = materialController.findById(materialId);
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Material 1", Objects.requireNonNull(response.getBody()).getName());
    }

    @Test
    void create() {
        // Arrange
        MaterialDto materialDto = new MaterialDto("Material 1", "Description 1", 1);
        Material material = new Material(materialDto);
        when(materialService.save(materialDto)).thenReturn(material);
        // Act
        ResponseEntity<Material> response = materialController.create(materialDto);
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Material 1", Objects.requireNonNull(response.getBody()).getName());
    }

    @Test
    void update() {
        // Arrange
        String materialId = "1";
        MaterialDto materialDto = new MaterialDto("Material 1", "Description 1", 1);
        Material material = new Material(materialId, "Material 1", "Description 1", 1);
        when(materialService.findById(materialId)).thenReturn(material);
        // Act
        ResponseEntity<HttpStatus> response = materialController.update(materialId, materialDto);
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteMaterial() {
        // Arrange
        String materialId = "1";
        Material material = new Material(materialId, "Material 1", "Description 1", 1);
        when(materialService.findById(materialId)).thenReturn(material);
        // Act
        ResponseEntity<HttpStatus> response = materialController.delete(materialId);
        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}