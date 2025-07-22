package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(
           @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) { // Pageable para retornar a lista em formato de paginaas e info do Springboot para o front
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new); // É preciso colocar o metodo dentro do findAll para que seja retornado em formato de paginação
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id()); // Busca no banco de dados a referencia pelo id
        medico.atualizarInformacores(dados); // Chama os metodos pra atualizar os dados baseado no DTO
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
