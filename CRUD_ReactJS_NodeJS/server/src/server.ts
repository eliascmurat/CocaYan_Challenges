import { SetupApplication } from "./app";

class ServerApplication {
  static start(): void {
    const application = new SetupApplication(3001);
    application.init();
    application.start();
  }
}

ServerApplication.start();