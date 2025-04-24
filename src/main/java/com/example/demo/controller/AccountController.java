package com.example.demo.controller;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

import org.hibernate.boot.jaxb.mapping.marshall.LockModeTypeMarshalling;
import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.boot.autoconfigure.batch.BatchTransactionManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AccountDto;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/api/accounts")  // 
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
    	System.out.println("Pavitheraaraaaaaaaaa");
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

   @GetMapping("/{id}")
   public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
        
    }
   @PutMapping("/{id}/deposit")
  public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
		                                     @RequestBody Map<String ,Double>request ){
	   
	  Double amount =request.get("amount");
	  AccountDto accountDto = accountService.deposit(id,amount);
	  return ResponseEntity.ok(accountDto);
	  
	  
	
}
// 
	  
	  @PutMapping("{id}/withdraw")
	  public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, 
	                                             @RequestBody Map<String, Double> request) {
	      double amount = request.get("amount");
	      AccountDto accountDto = accountService.withdraw(id, amount);
	      return ResponseEntity.ok(accountDto);
	      
	      
	  }
	  //Get All Accounts Rest API
	  @GetMapping
	  public ResponseEntity<List<AccountDto>> getAllAccounts(){
		  List<AccountDto> accounts=accountService.getAllAccounts();
		  return ResponseEntity.ok(accounts);
		  
		  
	  }
	  //Delete Account REst api 
	  @DeleteMapping("/{id}")
	  public ResponseEntity<String> deleteAccount(@PathVariable Long id){
	  accountService.deleteAccount(id);
	  return  ResponseEntity.ok("Account is deleted succcessfully!");
	  
		
	}
	  
 
	   
   }
		   


