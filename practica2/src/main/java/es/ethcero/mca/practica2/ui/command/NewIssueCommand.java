/**
 * Copyright (C) 2020 Deveryware S.A. All Rights Reserved.
 */
package es.ethcero.mca.practica2.ui.command;

import es.ethcero.mca.practica2.model.Coverage;

/**
 * @author fran
 */
public class NewIssueCommand {

    private Double amount;
    private Coverage coverage;

    public Double getAmount() {
        return amount;
    }

    public Coverage getCoverage() {
        return coverage;
    }
}
