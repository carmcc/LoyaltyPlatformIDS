package it.unicam.cs.ids.loyaltyplatform.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Aziende")
public class Azienda
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer idAzienda;
    private Integer qualeCoalizione;
    @Column(unique = true,nullable = false)
    private String nomeAzienda;
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String IVA;
    @Column(nullable = false)
    private String sede;
    @Column(nullable = false)
    private String referral;
    private String moltSistemaLivelli;
    private Float divisoreCashback;
    private Float moltiplicatoreVip;


    public Azienda() {
        idAzienda = 0;
    }

    public Azienda(Integer id, String nomeAzienda, String email, String password, String IVA, String sede, String referral, String moltSistemaLivelli, float divisoreCashback, float moltiplicatoreVip) {
        this.idAzienda = id;
        this.qualeCoalizione = -1;
        this.nomeAzienda = nomeAzienda;
        this.email = email;
        this.password = password;
        this.IVA = IVA;
        this.sede = sede;
        this.referral = referral;
        this.moltSistemaLivelli = moltSistemaLivelli;
        this.divisoreCashback = divisoreCashback;
        this.moltiplicatoreVip = moltiplicatoreVip;
    }


    //getters and setters
    public Integer getId() {
        return idAzienda;
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

    public void setPassword(String password) { this.password = password; }

    public String getpIVA() { return IVA; }

    public void setpIVA(String pIVA) { this.IVA = pIVA; }

    public String getSede() { return sede; }

    public void setSede(String sede) { this.sede = sede; }

    public String getReferral() { return referral; }

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
