import {Component, OnInit } from '@angular/core';
import {PoaService } from '../shared/powerofattorney/poa.service';
import {Poa} from "../model/poa.model";
import {Account} from "../model/account.model";
import {Debitcard} from "../model/debitcard.model";
import {Creditcard} from "../model/creditcard.model";
import {Card} from "../model/card.model";
import {AuthenticationService} from "../shared/authentication/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-poa-list',
  templateUrl: './poa-list.component.html',
  styleUrls: ['./poa-list.component.css']
})
export class PoaListComponent implements OnInit {
  powerOfAttorneys: Array<Poa>;
  powerOfAttorney: Poa;
  account: Account;
  debitCard: Debitcard;
  creditCard: Creditcard;

  showPowerOfAttorneys: boolean = true;
  showPowerOfAttorneyDetails: boolean = false;
  showCCDetails: boolean = false;
  showDebitDetails: boolean = false;
  showAccountDetails: boolean = false;

  constructor(private poaService: PoaService, private loginservice: AuthenticationService, private router: Router,) { }

  ngOnInit() {
    // TODO: idealiter los je dit in de backend op.
    if(this.loginservice.getLoggedInUserName() === 'admin') {
      this.poaService.getAllPoas().subscribe(data => {
        this.powerOfAttorneys = data;
      });
    } else {
      this.showPoaDetails(this.loginservice.getLoggedInUserName());
    }
  }

  public retrievePoa(id: string){
    this.poaService.getPoa(id).subscribe(data => {
      this.powerOfAttorney = data;
    });
  }

  public retrieveAccount(id: string){
    this.poaService.getAccount(id).subscribe(data => {
      this.account = data;
    });
  }

  public retrieveDebitCard(id: string){
    this.poaService.getDebitcard(id).subscribe(data => {
      this.debitCard = data;
    });
  }

  public retrieveCreditCard(id: string){
    this.poaService.getCreditcard(id).subscribe(data => {
      this.creditCard = data;
    });
  }

  public showPoaDetails(id: string) {
    this.retrievePoa(id)
    this.showPowerOfAttorneys = false;
    this.showPowerOfAttorneyDetails = true;
  }

  public showCardDetails(card: Card) {
    if (card.type === 'DEBIT_CARD' ) {
      this.showDebitcardDetails(card.id);
    } else {
      this.showCreditcardDetails(card.id);
    }
  }

  public showCreditcardDetails(id: string) {
    this.retrieveCreditCard(id)
    this.showPowerOfAttorneyDetails = false;
    this.showCCDetails = true;
  }

  public showDebitcardDetails(id: string) {
    this.retrieveDebitCard(id)
    this.showPowerOfAttorneyDetails = false;
    this.showDebitDetails = true;
  }

  public showAccount(id: string) {
    this.retrieveAccount(id)
    this.showPowerOfAttorneyDetails = false;
    this.showAccountDetails = true;
  }

  public resetApp() {
    if (this.loginservice.getLoggedInUserName() === 'admin') {
      this.showPowerOfAttorneys = true;
      this.showPowerOfAttorneyDetails = false;
      this.showCCDetails = false;
      this.showDebitDetails = false;
      this.showAccountDetails = false;
    } else if (!this.showPowerOfAttorneyDetails) {
      this.showPowerOfAttorneyDetails = true;
      this.showCCDetails = false;
      this.showDebitDetails = false;
      this.showAccountDetails = false;
    } else {
      this.logOut();
    }
  }

  public logOut() {
    this.loginservice.logout();
    this.router.navigate(['']);
  }

}
