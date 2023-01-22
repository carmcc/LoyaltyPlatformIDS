package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.entity.AccountAziendale;
import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.repository.AccountAziendaleRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AccountAziendaleService {
    private final AccountAziendaleRepository accountAziendaleRepository;

    public AccountAziendaleService (AccountAziendaleRepository accountAziendaleRepository) {this.accountAziendaleRepository = accountAziendaleRepository;}
    public List<AccountAziendale> getAllAccountAziendaliById(List<Integer> id) {return this.accountAziendaleRepository.findAllById(id);}
    public AccountAziendale getAccountAziendaleBySeriale(List<AccountAziendale> listaAccountAziendali, Integer seriale) {return listaAccountAziendali.stream().filter(x -> x.getSeriale() == seriale.intValue()).toList().get(0);}
    public List<AccountAziendale> getAllAccountAziendali() {return this.accountAziendaleRepository.findAll();}
    public AccountAziendale addAccountAziendale(AccountAziendale accountAziendale) {return this.accountAziendaleRepository.save(accountAziendale);}
    public void deleteAccountAziendale(Integer id) {this.accountAziendaleRepository.deleteById(id);}
    public void updateAccountAziendale(AccountAziendale accountAziendale) {this.accountAziendaleRepository.saveAndFlush(accountAziendale);}
}
