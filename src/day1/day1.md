# Java OOP deep-dive + small Bank Account system

## -- BASIC -- 
### Encapsulation
- Keep fields private.
- Expose behaviours via public methods (deposit(), withdraw()).
- Validate inputs inside methods (no negative deposits).
- Use immutable value objects for some data (e.g. Transaction with final fields).

### Abstraction
- Represent a general Account with common API and hide specifics.
- Use abstract class Account with deposit(), withdraw() signatures and shared logic.

### Inheritance
- Concrete account types extend Account: SavingsAccount, CheckingAccount.
- Reuse code from Account and override behaviors (e.g., interest in Savings, overdraft in Checking).

### Polymorphism
- Methods accept Account type and work with any subclass; dynamic method dispatch chooses subclass implementation at runtime.
- Example: bank.processMonthly() calls applyMonthlyUpdates() on each Account.

## High-level design (classes & responsibilities)

### Account (abstract)
- id, ownerName, balance, synchronized deposit/withdraw
- abstract applyMonthlyUpdate() (for interest, fees)
- getBalance(), getId() (encapsulation)
### SavingsAccount extends Account
- interestRate
- implements applyMonthlyUpdate() to add interest
### CheckingAccount extends Account
- overdraftLimit or monthlyFee
- override withdraw for overdraft rules
### Transaction (value object)
- id, date, type (DEPOSIT/WITHDRAW/TRANSFER), amount, fromId/toId
### Bank
- map of accounts, createAccount, findAccount, transferFunds, processMonthly
- Custom exceptions: InsufficientFundsException, AccountNotFoundException, InvalidAmountException

