package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.AccountAziendale;
import it.unicam.cs.ids.loyaltyplatform.service.AccountAziendaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountAziendale")
public class AccountAziendaleController {
    private final AccountAziendaleService accountAziendaleService;
    public AccountAziendaleController(AccountAziendaleService accountAziendaleService) {this.accountAziendaleService = accountAziendaleService;}
    @GetMapping(value = "/getAllAccountAziendaliById/{id}")
    public List<AccountAziendale> getAllAccountAziendaliById(@PathVariable("id") Integer id) {
        return this.accountAziendaleService.getAllAccountAziendaliById(id);
    }
    @GetMapping(value = "/getAccountAziendaleByIdAndSeriale/{id}&{seriale}")
    public AccountAziendale getAccountAziendaleByIdAndSeriale(@PathVariable("id") Integer id,@PathVariable("seriale") Integer seriale) {
        return this.accountAziendaleService.getAccountAziendaleBySeriale(getAllAccountAziendaliById(id), seriale);
    }
    @GetMapping(value = "/getAllAccountAziendali")
    public List<AccountAziendale> getAllAccountAziendali() {return this.accountAziendaleService.getAllAccountAziendali();}
    @PutMapping(value = "/addAccountAziendale")
    public AccountAziendale addAccountAziendale(AccountAziendale accountAziendale) {return this.accountAziendaleService.addAccountAziendale(accountAziendale);}
    @DeleteMapping(value = "/deleteAccountAziendale")
    public void deleteAccountAziendale(Integer id) {this.accountAziendaleService.deleteAccountAziendale(id);}
    @PostMapping(value = "/updateAccountAziendale")
    public void updateAccountAziendale(AccountAziendale accountAziendale) {this.accountAziendaleService.updateAccountAziendale(accountAziendale);}
}
