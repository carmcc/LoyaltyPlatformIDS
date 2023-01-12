package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AccountAziendali")
public class AccountAziendale
{
    @Id
    private Integer seriale;
    private Integer qualeAzienda;//TODO chiave composta

    public AccountAziendale()
    {

    }

    public AccountAziendale(Integer seriale, Integer qualeAzienda) {
        this.seriale = seriale;
        this.qualeAzienda = qualeAzienda;
    }

    public Integer getSeriale() {
        return seriale;
    }

    public void setSeriale(Integer seriale) {
        this.seriale = seriale;
    }

    public Integer getQualeAzienda() {
        return qualeAzienda;
    }

    public void setQualeAzienda(Integer qualeAzienda) {
        this.qualeAzienda = qualeAzienda;
    }
}
