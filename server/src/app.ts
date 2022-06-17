import express from "express";
import { Server } from "http";
import router from "./routes";
import bodyParser from "body-parser";

const cors = require("cors");

export class SetupApplication {
  private server?: Server;

  constructor(private port = 3001, public app = express()) { }

  public init(): void {
    this.setupCors();
    this.setupExpress();
    this.setupRoutes();
  }

  private setupExpress(): void {
    this.app.use(bodyParser.json());
    this.app.use(bodyParser.urlencoded({ extended: true }));
  }

  private setupRoutes(): void {
    this.app.use(router);
  }

  private setupCors(): void {
    this.app.use(cors());
  }

  public start(): void {
    this.server = this.app.listen(this.port, () => {
      console.log(`Servidor funcionando em: http://localhost:${this.port}/`);
    });
  }
}