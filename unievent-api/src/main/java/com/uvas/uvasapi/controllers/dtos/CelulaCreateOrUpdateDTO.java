package com.uvas.uvasapi.controllers.dtos;

import com.uvas.uvasapi.domain.*;
import com.uvas.uvasapi.domain.enums.DiaDaSemana;
import com.uvas.uvasapi.services.DiscipuladorService;
import com.uvas.uvasapi.services.LiderService;
import com.uvas.uvasapi.services.PessoaService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CelulaCreateOrUpdateDTO {

    @NotBlank(message = "Nome da celula não informado")
    @Length(max = 200, message = "Nome da celula não pode exceder 200 caracteres")
    private String nome;

    private DiaDaSemana diaDaSemana;

    private LocalTime horario;

    private LocalDate dataInauguracao;

    @NotNull(message = "Discipulador não informado")
    private Discipulador discipuladorId;

    @NotNull(message = "Lider não informado")
    private Lider liderId;

    private List<Pessoa> pessoas = new ArrayList<>();

    private Endereco enderecoId;

    public Celula getCelula(LiderService liderService, DiscipuladorService discipuladorService){
        Celula celula = new Celula();

        Lider lider = liderService.getLiderById(liderId.getId());
        Discipulador discipulador = discipuladorService.getDiscipuladorById(discipuladorId.getId());

        celula.setNome(nome);
        celula.setDiaDaSemana(diaDaSemana);
        celula.setHorario(horario);
        celula.setDataInauguracao(dataInauguracao);
        celula.setDiscipuladorId(discipulador);
        celula.setLiderId(lider);
        celula.setEnderecoId(enderecoId);

        return celula;
    }

}
