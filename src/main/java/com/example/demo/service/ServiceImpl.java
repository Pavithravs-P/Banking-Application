package com.example.demo.service;

import java.awt.AlphaComposite;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AccountDto;
import com.example.demo.entity.Account;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.respository.AccountRepository;
@Service
public class ServiceImpl implements AccountService {
	private AccountRepository accountRepository;



	public ServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account  account= AccountMapper.mapToAccount(accountDto);
		Account saveAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
		
		

	}

	@Override
	public AccountDto getAccountById(Long id) {
	    Account account = accountRepository
	            .findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exists"));

	    return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		 Account account = accountRepository
		            .findById(id)
		            .orElseThrow(() -> new RuntimeException("Account does not exists"));

		  double total = account.getBalance()+amount;
		  account.setBalance(total);
		  Account savedAccount= accountRepository.save(account);
		  return AccountMapper.mapToAccountDto(savedAccount);
		  
		    
	
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository
	            .findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exists"));
		if (account.getBalance()<amount) {
		throw new RuntimeException("Insufficient  amount");
		
			
		}
		double total =account.getBalance()- amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	

		
	
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List <Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
		
	}

	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository
	            .findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exists"));
		accountRepository.deleteById(id);

		
	}
	}


