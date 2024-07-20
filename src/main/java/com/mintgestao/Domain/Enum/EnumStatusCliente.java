package com.mintgestao.Domain.Enums;

public enum EnumStatusCliente {
    Inativo(0),
    Ativo(1);

    private Integer enumValue;

    EnumStatusCliente(Integer enumValue) {
        this.enumValue = enumValue;
    }

    public Integer getEnumValue() {
        return enumValue;
    }
}



