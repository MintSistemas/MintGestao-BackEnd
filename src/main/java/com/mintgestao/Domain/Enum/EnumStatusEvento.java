package com.mintgestao.Domain.Enum;

public enum EnumStatusEvento {
    Cancelado(0),
    AguardandoPagto(1),
    Pago(2);

    private Integer enumValue;

    EnumStatusEvento(Integer enumValue) {
        this.enumValue = enumValue;
    }

    public Integer getEnumValue() {
        return enumValue;
    }
}
