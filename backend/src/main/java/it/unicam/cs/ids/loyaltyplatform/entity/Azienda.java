package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Aziende")
public class Azienda
{
    @Id
    private Integer id;

    //TODO chiave secondaria da settare
    private Integer qualeCoalizione ;
    private String nomeAzienda;
    private String email;
    private String password;
    private String pIVA;
    private String sede;
    private String referral;

    private String moltSistemaLivelli;
    private float divisoreCashback;

    private float moltiplicatoreVip;

    public Azienda() {
    }

    public Azienda(Integer id, String nomeAzienda, String email, String password, String pIVA, String sede, String referral, String moltSistemaLivelli, float divisoreCashback, float moltiplicatoreVip) {
        this.id = id;
        this.qualeCoalizione = -1;
        this.nomeAzienda = nomeAzienda;
        this.email = email;
        this.password = password;
        this.pIVA = pIVA;
        this.sede = sede;
        this.referral = referral;
        this.moltSistemaLivelli = moltSistemaLivelli;
        this.divisoreCashback = divisoreCashback;
        this.moltiplicatoreVip = moltiplicatoreVip;
    }


    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQualeCoalizione() {
        return qualeCoalizione;
    }

    public void setQualeCoalizione(Integer qualeCoalizione) {
        this.qualeCoalizione = qualeCoalizione;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getMoltSistemaLivelli() {
        return moltSistemaLivelli;
    }

    public void setMoltSistemaLivelli(String moltSistemaLivelli) {
        this.moltSistemaLivelli = moltSistemaLivelli;
    }

    public float getDivisoreCashback() {
        return divisoreCashback;
    }

    public void setDivisoreCashback(float divisoreCashback) {
        this.divisoreCashback = divisoreCashback;
    }

    public float getMoltiplicatoreVip() {
        return moltiplicatoreVip;
    }

    public void setMoltiplicatoreVip(float moltiplicatoreVip) {
        this.moltiplicatoreVip = moltiplicatoreVip;
    }
}
