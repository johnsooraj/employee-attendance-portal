import { Component, OnInit } from '@angular/core';
import { CommonService } from 'src/app/services/common.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  currentTime: Date = new Date();
  pageNumber = 0;
  pageSize = 0;
  pageTotal = 0;

  items: any[];

  constructor(
    private commonService: CommonService
  ) {
    setInterval(() => {
      this.currentTime = new Date();
    }, 1);
  }

  ngOnInit() {
    this.commonService.getDummyData().subscribe((data) => {
      this.items = data
      this.pageTotal = this.items.length;
      this.pageSize = 10;
      this.pageNumber = 1;
      console.log(this.items)
    });
  }

}
