package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.AccountAziendale;
import it.unicam.cs.ids.loyaltyplatform.entity.Ruolo;
import it.unicam.cs.ids.loyaltyplatform.service.AccountAziendaleService;
import it.unicam.cs.ids.loyaltyplatform.service.RuoloService;
import it.unicam.cs.ids.loyaltyplatform.utilities.EntityValidator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accountAziendale")
@AllArgsConstructor
public class AccountAziendaleController extends EntityValidator {
    private final AccountAziendaleService accountAziendaleService;
    private final RuoloService ruoloService;

    @GetMapping(value = "/getAccountAziendaliById/{id}")
    public List<AccountAziendale> getAccountAziendaliById(@PathVariable("id") Integer id) {
        return this.accountAziendaleService.getAccountAziendaliById(id);
    }
    @GetMapping(value = "/getAccountAziendaleByIdAndSeriale/{id}&{seriale}")
    public Optional<AccountAziendale> getAccountAziendaleByIdAndSeriale(@PathVariable("id") Integer id, @PathVariable("seriale") Integer seriale) {
        return this.accountAziendaleService.getAccountAziendaleByIdAndSeriale(id, seriale);
    }
    @GetMapping(value = "/getAllAccountAziendali")
    public List<AccountAziendale> getAllAccountAziendali() {
        return this.accountAziendaleService.getAllAccountAziendali();
    }
    @PostMapping(value = "/addAccountAziendale")
    public AccountAziendale addAccountAziendale(@RequestBody AccountAziendale accountAziendale) {
       validateEntity(accountAziendale);
        accountAziendale.setSeriale(null);
        AccountAziendale accountAziendaleAggiunto = this.accountAziendaleService.addAccountAziendale(accountAziendale);

        //dopo aver aggiunto l'account aziendale gli aggiungo il ruolo di default nominandolo "nuovoImpiegato"
        this.ruoloService.addRuolo(new Ruolo(accountAziendaleAggiunto.getQualeAzienda(), accountAziendaleAggiunto.getSeriale(), "select", "nuovoImpiegato"));

        return accountAziendaleAggiunto;
    }
    @DeleteMapping(value = "/deleteAccountAziendale")
    public void deleteAccountAziendale(@RequestBody AccountAziendale accountAziendale) {
        validateEntity(accountAziendale);
        if(getAccountAziendaleByIdAndSeriale(accountAziendale.getQualeAzienda(), accountAziendale.getSeriale()).isEmpty())
            throw new IllegalArgumentException("ID del record da rimuovere non esiste");

//        List<Ruolo> listaRuoliAccountAziendale = this.ruoloService.getRuoliByAziendaAndSeriale(accountAziendale.getQualeAzienda(),
//                                                                                                accountAziendale.getSeriale());
//        for (Ruolo r: listaRuoliAccountAziendale) { //elimino tutti i ruoli dell'account aziendale
//            this.ruoloService.deleteRuolo(r);
//        }

        this.accountAziendaleService.deleteAccountAziendale(accountAziendale);
    }
}
