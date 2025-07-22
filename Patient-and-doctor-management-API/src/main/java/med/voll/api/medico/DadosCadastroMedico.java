package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.dadosEndereco;

public record DadosCadastroMedico(

        @NotBlank //Indica que o atributo não pode ser nulo nem vazio
        String nome,

        @NotBlank
        @Email // Valida se o atributo está no formato de email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // indica que o atributo deve estar conforme o padrao entre aspas
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull @Valid dadosEndereco dadosEndereco) {
}
