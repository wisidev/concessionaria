package com.concessionaria.service;

import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obterPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}
