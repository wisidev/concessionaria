package com.concessionaria.service;

import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    public Carro obterPorId(Long id) {
        return carroRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        carroRepository.deleteById(id);
    }
}
