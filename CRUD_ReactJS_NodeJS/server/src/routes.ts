import { Router } from "express";
import TasksRouter from "./app/routers/tasks";

class Routes {
  static define(router: Router): Router {
    router.use("/tasks", TasksRouter);

    return router;
  }
}

export default Routes.define(Router());