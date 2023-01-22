package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.AccountAziendale;
import it.unicam.cs.ids.loyaltyplatform.service.AccountAziendaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountAziendale")
public class AccountAziendaleController {
    private final AccountAziendaleService accountAziendaleService;
    public AccountAziendaleController(AccountAziendaleService accountAziendaleService) {
        this.accountAziendaleService = accountAziendaleService;
    }
    @GetMapping(value = "/getAllAccountAziendaliById/{id}")
    public List<AccountAziendale> getAllAccountAziendaliById(@PathVariable("id") Integer id) {
        return this.accountAziendaleService.getAccountAziendaliById(id);
    }
    @GetMapping(value = "/getAccountAziendaleByIdAndSeriale/{id}&{seriale}")
    public AccountAziendale getAccountAziendaleByIdAndSeriale(@PathVariable("id") Integer id,@PathVariable("seriale") Integer seriale) {
        return this.accountAziendaleService.getAccountAziendaleByIdAndSeriale(id, seriale);
    }
    @GetMapping(value = "/getAllAccountAziendali")
    public List<AccountAziendale> getAllAccountAziendali() {
        return this.accountAziendaleService.getAllAccountAziendali();
    }
    @PutMapping(value = "/addAccountAziendale")
    public AccountAziendale addAccountAziendale(@RequestBody AccountAziendale accountAziendale) {
        return this.accountAziendaleService.addAccountAziendale(accountAziendale);
    }
    @DeleteMapping(value = "/deleteAccountAziendale")
    public void deleteAccountAziendale(@RequestBody AccountAziendale accountAziendale) {
        this.accountAziendaleService.deleteAccountAziendale(accountAziendale);
    }
    @PostMapping(value = "/updateAccountAziendale")
    public void updateAccountAziendale(@RequestBody AccountAziendale accountAziendale) {
        this.accountAziendaleService.updateAccountAziendale(accountAziendale);
    }
}
