import { Component,Input, OnInit } from '@angular/core';
import { Produs } from '../produs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-produs',
  templateUrl: './produs.component.html',
  styleUrls: ['./produs.component.scss']
})

export class ProdusComponent implements OnInit {
  constructor(private route:ActivatedRoute){};
  prodList:Produs[]=[];
  ngOnInit(): void {
    const name = this.route.snapshot.paramMap.get('name');
    switch(name){
    case 'food':
      this.prodList = [{
        id: 0,
        name: 'Odysea de Somon',
        price: 70,
        photo:`assets/food1.jpg`
      },
      {
        id: 1,
        name: 'Amurg Toscan',
        price: 60,
        photo:`assets/food2.jpg`
      },
      {
        id: 2,
        name: 'Rissoto Rubi',
        price: 100,
        photo:`assets/food3.jpg`
      },
      {
        id: 3,
        name: 'Rață în zbor oriental',
        price: 150,
        photo:`assets/food4.jpg`
      },
      {
        id: 4,
        name: 'Burgerul Bistrof',
        price: 50,
        photo:`assets/food5.jpg`
      },
      {
        id: 5,
        name: 'Tartă Eclipsă',
        price: 30,
        photo:`assets/food6.jpg`
      },
      {
        id: 6,
        name: 'Panouree Delice',
        price: 35,
        photo:`assets/food7.jpg`
      },
      {
        id: 7,
        name: 'Ciocolata Vulcanica',
        price: 40,
        photo:`assets/food8.jpg`
      }
    ];
      break;
    case 'beer':
      this.prodList = [{
        id: 0,
        name: 'Unu unu doi',
        price: 15,
        photo:`assets/beer1.jpg`
      },
      {
        id: 1,
        name: 'The black pot',
        price: 16,
        photo:`assets/beer2.jpg`
      },
      {
        id: 2,
        name: 'Ce ar fi sa',
        price: 14,
        photo:`assets/beer3.jpg`
      },
      {
        id: 3,
        name: 'Crowd control',
        price: 11,
        photo:`assets/beer4.jpg`
      },
      {
        id: 4,
        name: 'Hobgoblin',
        price: 12,
        photo:`assets/beer5.jpg`
      },
      {
        id: 5,
        name: 'Shock therapy',
        price: 13,
        photo:`assets/beer6.jpg`
      },
      {
        id: 6,
        name: 'TNR',
        price: 13,
        photo:`assets/beer7.jpg`
      },
      {
        id: 7,
        name: 'Zaganu',
        price: 9,
        photo:`assets/beer8.jpg`
      }
    ];
      break;
    case 'wine':
      this.prodList = [{
        id: 0,
        name: 'Pinot noir & Gruyere',
        price: 200,
        photo:`assets/wine1.jpg`
      },
      {
        id: 1,
        name: 'Port & Stilton',
        price: 220,
        photo:`assets/wine2.jpg`
      },
      {
        id: 2,
        name: 'Bubbles & Brie',
        price: 180,
        photo:`assets/wine3.jpg`
      },
      {
        id: 3,
        name: 'Moscato & gorgonzola',
        price: 220,
        photo:`assets/wine4.jpg`
      },
      {
        id: 4,
        name: 'Tempranillo & Idiazabal',
        price: 195,
        photo:`assets/wine5.jpg`
      },
      {
        id: 5,
        name: 'Sauvignon blanc & goat cheese',
        price: 130,
        photo:`assets/wine6.jpg`
      },
      {
        id: 6,
        name: 'Caubernet savignon & aged cheddar',
        price: 160,
        photo:`assets/wine7.jpg`
      },
      {
        id: 7,
        name: 'Rose & havarti',
        price: 175,
        photo:`assets/wine8.jpg`
      }
    ];
      break;
    }
  }
}
