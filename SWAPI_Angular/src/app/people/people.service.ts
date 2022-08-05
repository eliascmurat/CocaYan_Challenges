import { Injectable } from '@angular/core';
import axios from 'axios';
import { environment } from 'src/environments/environment';

const API = environment.swapi_url + '/people';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  getPeopleById(peopleId: number) {
    return axios.get(API + '/' + peopleId);
  }

}
