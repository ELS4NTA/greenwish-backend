package edu.eci.ieti.greenwish.service.material;

import edu.eci.ieti.greenwish.controller.material.MaterialDto;
import edu.eci.ieti.greenwish.exception.MaterialNotFoundException;
import edu.eci.ieti.greenwish.repository.MaterialRepository;
import edu.eci.ieti.greenwish.repository.document.Material;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceMongoDB implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceMongoDB(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material save(MaterialDto materialDto) {
        return materialRepository.save(new Material(materialDto));
    }

    @Override
    public Material findById(String id) {
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        if (optionalMaterial.isEmpty()) throw new MaterialNotFoundException();
        return optionalMaterial.get();
    }

    @Override
    public List<Material> all() {
        return materialRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        if (!materialRepository.existsById(id)) throw new MaterialNotFoundException();
        materialRepository.deleteById(id);
    }

    @Override
    public void update(MaterialDto materialDto, String id) {
        Material materialToUpdate = findById(id);
        materialToUpdate.setName(materialDto.getName());
        materialToUpdate.setKiloValue(materialDto.getKiloValue());
        materialToUpdate.setDescription(materialDto.getDescription());
        materialRepository.save(materialToUpdate);
    }

}
