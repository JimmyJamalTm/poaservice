import { Component, OnInit } from '@angular/core';
import { PoaService } from '../shared/powerofattorney/poa.service';
import {Poa} from "../model/poa.model";

@Component({
  selector: 'app-poa-list',
  templateUrl: './poa-list.component.html',
  styleUrls: ['./poa-list.component.css']
})
export class PoaListComponent implements OnInit {
  // TODO 1 lijst met alle records, extra veld : datatype
  powerOfAttorneys: Array<Poa>;
  powerOfAttorney: Poa;

  constructor(private recordService: PoaService) { }

  ngOnInit() {
    // service retrieves info from rest

  }

  public retrievePoas() : Array<Poa> {
    return null;

  }

  public retrievePoa(id: Number) : Poa {
    return null;


  }

}
