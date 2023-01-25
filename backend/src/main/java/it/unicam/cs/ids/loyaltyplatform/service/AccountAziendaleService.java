package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.AccountAziendale;
import it.unicam.cs.ids.loyaltyplatform.repository.AccountAziendaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountAziendaleService {
    private final AccountAziendaleRepository accountAziendaleRepository;

    public List<AccountAziendale> getAccountAziendaliById(Integer id) {
        return this.accountAziendaleRepository.findAll().stream().filter(x->x.getQualeAzienda()==id.intValue()).toList();
    }
    public Optional<AccountAziendale> getAccountAziendaleByIdAndSeriale(Integer idAzienda, Integer seriale) {
        return this.accountAziendaleRepository.findAll().stream()
                .filter(x -> x.getQualeAzienda()==idAzienda.intValue() && x.getSeriale() == seriale.intValue()).findAny();
    }
    public List<AccountAziendale> getAllAccountAziendali() {
        return this.accountAziendaleRepository.findAll();
    }
    public AccountAziendale addAccountAziendale(AccountAziendale accountAziendale) {
        return this.accountAziendaleRepository.save(accountAziendale);
    }
    public void deleteAccountAziendale(AccountAziendale accountAziendale) {
        this.accountAziendaleRepository.delete(accountAziendale);
    }
    public void updateAccountAziendale(AccountAziendale accountAziendale) {
        this.accountAziendaleRepository.saveAndFlush(accountAziendale);
    }
}
