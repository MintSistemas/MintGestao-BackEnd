package com.mintgestao.Domain.Enum;

public enum EnumStatusFilial {
    Inativo(0),
    Ativo(1);

    private Integer enumValue;

    EnumStatusFilial(Integer enumValue) {
        this.enumValue = enumValue;
    }

    public Integer getEnumValue() {
        return enumValue;
    }
}



