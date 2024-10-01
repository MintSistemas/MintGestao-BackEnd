package com.mintgestao.Domain.Enum;

public enum EnumStatusContasAReceber {
    Aberto(0),
    Pago(1),
    Cancelado(2);

    private Integer enumValue;

    EnumStatusContasAReceber(Integer enumValue) {
        this.enumValue = enumValue;
    }

    public Integer getEnumValue() {
        return enumValue;
    }
}



