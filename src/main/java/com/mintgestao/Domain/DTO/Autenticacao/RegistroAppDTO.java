package com.mintgestao.Domain.DTO.Autenticacao;

public record RegistroAppDTO(
        String nome,
        String email,
        String senha,
        String endereco,
        String bairro,
        String cidade,
        String telefone,
        String numero,
        String cpf,
        String dataNascimento,
        String cep,
        boolean usaapp
        ) {
}
