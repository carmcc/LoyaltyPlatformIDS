import {Component, OnInit} from '@angular/core';
import {Consumatore} from "../../_interfaces/consumatore";
import {ConsumatoreService} from "../../_services/consumatore.service";

@Component({
  selector: 'app-consumatore',
  templateUrl: './consumatore.component.html',
  styleUrls: ['./consumatore.component.css']
})
export class ConsumatoreComponent implements OnInit {
  consumatori: Consumatore[] =[];

  constructor(private consumatoreService: ConsumatoreService) {}

  ngOnInit(): void {
    this.consumatoreService.getAllConsumatori().subscribe(
      (data: Consumatore[]) => {
        console.log(data);
        this.consumatori = data;
      }
    )
  }
}
