import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import axios from 'axios';
import { People } from "./people";

const API_URL = environment.swapi_url;

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  async getAllPeoples() {
    try {
      const result = await axios.get<People>(API_URL);

      console.log(result);

      return result;
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
