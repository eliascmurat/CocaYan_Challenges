import { Injectable } from '@angular/core';
import axios from 'axios';
import { environment } from 'src/environments/environment';

const API = environment.swapi_url + '/people/1';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  getAllPeoples() {
    return axios.get(API);
  }

}
