package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    // 1. Exibir lista de carros
    @GetMapping("/carro")
    public String listarCarros(Model model) {
        model.addAttribute("carros", carroRepository.findAll()); 
        return "carro"; // Nome da view
    }

    // 2. Exibir o formulário para adicionar um novo carro
    @GetMapping("/novo")
    public String exibirFormularioNovoCarro(Model model) {
        model.addAttribute("carro", new Carro()); 
        return "formulario"; // Nome da view
    }

    // 3. Criar novo carro
    @PostMapping("/salvar")
    public String salvarCarro( @ModelAttribute("carro") Carro carro, BindingResult result) {
        // Verificar se existem erros de validação no formulário
        if (result.hasErrors()) {
            return "formulario"; // Retorna para o formulário se houver erros de validação
        }
        carroRepository.save(carro); // Salvar no banco
        return "redirect:/carro"; // Redireciona para a lista de carros após salvar
    }

    // 4. Exibir formulário para editar um carro existente
    @GetMapping("/editar/{id}")
    public String exibirFormularioEditarCarro(@PathVariable("id") Long id, Model model) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));
        model.addAttribute("carro", carro);
        return "formulario"; // Retorna o formulário com os dados do carro para edição
    }

    // 5. Atualizar carro existente
    @PostMapping("/atualizar/{id}")
    public String atualizarCarro(@PathVariable("id") Long id, @ModelAttribute("carro") Carro carro, BindingResult result) {
        if (result.hasErrors()) {
            return "formulario"; // Retorna para o formulário se houver erros de validação
        }
        carro.setId(id); // Garantir que o id correto será mantido
        carroRepository.save(carro); // Atualizar no banco
        return "redirect:/carro"; // Redireciona para a lista de carros após atualizar
    }

    // 6. Excluir carro
    @GetMapping("/deletar/{id}")
    public String deletarCarro(@PathVariable("id") Long id) {
        Carro carro = carroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));
        carroRepository.delete(carro); // Excluir o carro
        return "redirect:/carro"; // Redireciona para a lista de carros após excluir
    }
}
