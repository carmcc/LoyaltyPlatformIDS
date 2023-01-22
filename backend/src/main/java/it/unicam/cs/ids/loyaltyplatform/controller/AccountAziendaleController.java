package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.AccountAziendale;
import it.unicam.cs.ids.loyaltyplatform.service.AccountAziendaleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accountAziendale")
public class AccountAziendaleController {
    private final AccountAziendaleService accountAziendaleService;
    public AccountAziendaleController(AccountAziendaleService accountAziendaleService) {this.accountAziendaleService = accountAziendaleService;}
    @RequestMapping(value = "/GetAllAccountAziendaliById/{id}", method = RequestMethod.GET)
    public List<AccountAziendale> getAllAccountAziendaliById(@PathVariable("id") List<Integer> id) {return this.accountAziendaleService.getAllAccountAziendaliById(id);}
    @RequestMapping(value = "/getAccountAziendaleByIdAndSeriale/{id}&{seriale}", method = RequestMethod.GET)
    public AccountAziendale getAccountAziendaleByIdAndSeriale(@PathVariable("id") List<Integer> id,@PathVariable("seriale") Integer seriale) {return this.accountAziendaleService.getAccountAziendaleBySeriale(getAllAccountAziendaliById(id), seriale);}
    @RequestMapping(value = "/getAllAccountAliendali", method = RequestMethod.GET)
    public List<AccountAziendale> getAllAccountAliendali() {return this.accountAziendaleService.getAllAccountAziendali();}
    @RequestMapping(value = "/addAccountAziendale", method = RequestMethod.PUT)
    public AccountAziendale addAccountAziendale(AccountAziendale accountAziendale) {return this.accountAziendaleService.addAccountAziendale(accountAziendale);}
    @RequestMapping(value = "/deleteAccountAziendale", method = RequestMethod.DELETE)
    public void deleteAccountAziendale(Integer id) {this.accountAziendaleService.deleteAccountAziendale(id);}
    @RequestMapping(value = "/updateAccountAziendale", method = RequestMethod.POST)
    public void updateAccountAziendale(AccountAziendale accountAziendale) {this.accountAziendaleService.updateAccountAziendale(accountAziendale);}
}
