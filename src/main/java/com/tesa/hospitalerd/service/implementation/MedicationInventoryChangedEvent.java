package com.tesa.hospitalerd.service.implementation;

import org.springframework.context.ApplicationEvent;

public class MedicationInventoryChangedEvent extends ApplicationEvent {
    private final Long medicationId;

    public MedicationInventoryChangedEvent(Object source, Long medicationId) {
        super(source);
        this.medicationId = medicationId;
    }

    public Long getMedicationId() {
        return medicationId;
    }
}
