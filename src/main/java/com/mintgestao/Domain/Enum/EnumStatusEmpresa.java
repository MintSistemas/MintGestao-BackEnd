package com.mintgestao.Domain.Enum;

public enum EnumStatusEmpresa {
    Inativo(0),
    Ativo(1);

    private Integer enumValue;

    EnumStatusEmpresa(Integer enumValue) {
        this.enumValue = enumValue;
    }

    public Integer getEnumValue() {
        return enumValue;
    }
}



