import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import axios from 'axios';
import { People } from "./people";

const API = environment.swapi_url + '/people';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  getAllPeoples() {
    return axios.get(API);
  }

}
