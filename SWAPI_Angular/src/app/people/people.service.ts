import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import axios from 'axios';
import { People } from "./people";

const API = environment.swapi_url;

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  async getAllPeoples() {
    try {
      const result = await axios.get<People[]>(API + '/people');

      return result.data;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        console.log('error message: ', error.message);
        return error.message;
      } else {
        console.log('unexpected error: ', error);
        return 'An unexpected error occurred';
      }
    }
  }

}
