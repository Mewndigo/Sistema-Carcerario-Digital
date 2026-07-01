package com.cefet.sistema_carcerario_digital.services;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.sistema_carcerario_digital.dto.CondenacaoRequestDTO;
import com.cefet.sistema_carcerario_digital.dto.CondenacaoResponseDTO;
import com.cefet.sistema_carcerario_digital.entities.Condenacao;
import com.cefet.sistema_carcerario_digital.entities.StatusDetento;
import com.cefet.sistema_carcerario_digital.exceptions.BusinessException;
import com.cefet.sistema_carcerario_digital.exceptions.ResourceNotFoundException;
import com.cefet.sistema_carcerario_digital.repositories.CondenacaoRepository;
import com.cefet.sistema_carcerario_digital.repositories.OcorrenciaRepository;
import com.cefet.sistema_carcerario_digital.repositories.PessoaRepository;
import com.cefet.sistema_carcerario_digital.repositories.RegistroAtividadeRepository;

@Service
public class CondenacaoService {

    private static final EnumSet<StatusDetento> SITUACOES_ATIVAS = EnumSet.of(
            StatusDetento.ATIVO,
            StatusDetento.ISOLAMENTO);

    private final CondenacaoRepository repo;
    private final PessoaRepository pessoaRepo;
    private final OcorrenciaRepository ocorrenciaRepo;
    private final RegistroAtividadeRepository registroAtividadeRepo;

    CondenacaoService(
            CondenacaoRepository condenacaoRepository,
            PessoaRepository pessoaRepository,
            OcorrenciaRepository ocorrenciaRepository,
            RegistroAtividadeRepository registroAtividadeRepository) {
        this.repo = condenacaoRepository;
        this.pessoaRepo = pessoaRepository;
        this.ocorrenciaRepo = ocorrenciaRepository;
        this.registroAtividadeRepo = registroAtividadeRepository;
    }

    @Transactional(readOnly = true)
    public List<CondenacaoResponseDTO> listar() {
        List<Condenacao> lista = repo.findAll();
        return lista.stream().map(CondenacaoResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CondenacaoResponseDTO buscarPorId(Long id) {
        Condenacao entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condenacao nao encontrada. Id: " + id));

        return new CondenacaoResponseDTO(entity);
    }

    @Transactional
    public CondenacaoResponseDTO inserir(CondenacaoRequestDTO dto) {
        validar(dto, null);

        Condenacao entity = new Condenacao();
        copiarDtoParaEntidade(dto, entity);

        entity = repo.save(entity);

        return new CondenacaoResponseDTO(entity);
    }

    @Transactional
    public CondenacaoResponseDTO alterar(Long id, CondenacaoRequestDTO dto) {
        Condenacao entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Condenacao nao encontrada. Id: " + id));

        validar(dto, id);
        copiarDtoParaEntidade(dto, entity);
        entity = repo.save(entity);

        return new CondenacaoResponseDTO(entity);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Condenacao nao encontrada. Id: " + id);
        }

        if (ocorrenciaRepo.existsByCondenacaoId(id) || registroAtividadeRepo.existsByCondenacaoId(id)) {
            throw new BusinessException("Não é possível excluir condenação com ocorrências ou registros de atividade vinculados.");
        }

        repo.deleteById(id);
    }

    private void validar(CondenacaoRequestDTO dto, Long id) {
        if (!pessoaRepo.existsById(dto.getPessoaId())) {
            throw new ResourceNotFoundException("Pessoa não encontrada. Id: " + dto.getPessoaId());
        }

        if (dto.getDataSaida().isBefore(dto.getDataEntrada())) {
            throw new BusinessException("A data de saída não pode ser anterior à data de entrada.");
        }

        validarCondenacaoAtivaUnica(dto, id);
    }

    private void validarCondenacaoAtivaUnica(CondenacaoRequestDTO dto, Long id) {
        if (!SITUACOES_ATIVAS.contains(dto.getSituacao())) {
            return;
        }

        boolean periodoSobreposto = id == null
                ? repo.existsByPessoaIdAndSituacaoInAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqual(
                        dto.getPessoaId(), SITUACOES_ATIVAS, dto.getDataSaida(), dto.getDataEntrada())
                : repo.existsByPessoaIdAndSituacaoInAndDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndIdNot(
                        dto.getPessoaId(), SITUACOES_ATIVAS, dto.getDataSaida(), dto.getDataEntrada(), id);

        if (periodoSobreposto) {
            throw new BusinessException("Já existe condenação ativa para esta pessoa no período informado.");
        }
    }

    private void copiarDtoParaEntidade(CondenacaoRequestDTO dto, Condenacao entity) {
        entity.setDataEntrada(dto.getDataEntrada());
        entity.setDataSaida(dto.getDataSaida());
        entity.setDescricao(dto.getDescricao());
        entity.setSituacao(dto.getSituacao());
        entity.setPessoaId(dto.getPessoaId());
    }
}
