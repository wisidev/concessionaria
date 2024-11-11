package com.concessionaria.controller;

import com.concessionaria.model.Cliente;
import com.concessionaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // 1. Listar todos os clientes
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "listar-clientes"; // Nome da página de listagem
    }

    // 2. Exibir o formulário para adicionar um novo cliente
    @GetMapping("/cliente/novo")
    public String exibirFormularioNovoCliente(Model model) {
        model.addAttribute("cliente", new Cliente()); // Novo cliente para o formulário
        return "formulario-cliente"; // Nome da página do formulário
    }

    // 3. Salvar um novo cliente
    @PostMapping("/cliente/salvar")
    public String salvarCliente(@ModelAttribute("cliente") Cliente cliente) {
        clienteRepository.save(cliente); // Salva o cliente no banco de dados
        return "redirect:/clientes"; // Redireciona para a página de listagem
    }

    // 4. Exibir o formulário para editar um cliente existente
    @GetMapping("/cliente/editar/{id}")
    public String exibirFormularioEditarCliente(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        model.addAttribute("cliente", cliente); // Adiciona o cliente para edição no formulário
        return "formulario-cliente"; // Nome da página do formulário
    }

    // 5. Atualizar um cliente existente
    @PostMapping("/cliente/atualizar/{id}")
    public String atualizarCliente(@PathVariable("id") Long id, @ModelAttribute("cliente") Cliente cliente) {
        cliente.setId(id); // Garante que o ID do cliente seja mantido
        clienteRepository.save(cliente); // Atualiza o cliente no banco de dados
        return "redirect:/clientes"; // Redireciona para a página de listagem
    }

    // 6. Excluir um cliente
    @GetMapping("/cliente/deletar/{id}")
    public String deletarCliente(@PathVariable("id") Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        clienteRepository.delete(cliente); // Exclui o cliente do banco de dados
        return "redirect:/clientes"; // Redireciona para a página de listagem
    }
}
