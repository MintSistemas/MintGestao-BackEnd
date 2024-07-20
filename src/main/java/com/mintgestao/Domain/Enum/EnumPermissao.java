package com.mintgestao.Domain.Enum;

public enum EnumPermissao {
    Administrador(0),
    Usuario(1);

    private Integer permissao;

    EnumPermissao(Integer role){
        this.permissao = role;
    }

    public Integer getPermissao(){
        return permissao;
    }
}
