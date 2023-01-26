package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.AccountAziendale;
import it.unicam.cs.ids.loyaltyplatform.service.AccountAziendaleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accountAziendale")
@AllArgsConstructor
public class AccountAziendaleController {
    private final AccountAziendaleService accountAziendaleService;

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
        if (accountAziendale == null)
        {throw new NullPointerException("accountAziendale è vuoto");}
        if(getAccountAziendaleByIdAndSeriale(accountAziendale.getQualeAzienda(),accountAziendale.getSeriale()).isPresent())
        {throw new IllegalArgumentException("id del record da aggiungere già presente");}

        return this.accountAziendaleService.addAccountAziendale(accountAziendale);
    }
    @DeleteMapping(value = "/deleteAccountAziendale")
    public void deleteAccountAziendale(@RequestBody AccountAziendale accountAziendale) {
        if (accountAziendale == null)
        {throw new NullPointerException("accountAziendale è vuoto");}

        this.accountAziendaleService.deleteAccountAziendale(accountAziendale);
    }
}
