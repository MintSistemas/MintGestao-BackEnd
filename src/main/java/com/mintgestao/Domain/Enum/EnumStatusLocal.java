package com.mintgestao.Domain.Enums;

public enum EnumStatusLocal {
    Inativo(0),
    Ativo(1);

    private Integer enumValue;

    EnumStatusLocal(Integer enumValue) {
        this.enumValue = enumValue;
    }

    public Integer getEnumValue() {
        return enumValue;
    }
}



