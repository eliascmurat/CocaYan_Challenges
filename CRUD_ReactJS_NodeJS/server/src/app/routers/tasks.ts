import { Router, request, response } from "express";

const router = Router();
const db = require("../../db");

/*
 * Função para pegar todas as task
 */
router.get("/getAll", (req, res) => {
  let sql = "SELECT * FROM tasks";

  db.query(sql, (err: any, result: any) => {
    if (err) console.log(err);
    else res.send(result);
  });  
});

/*
 * Função para pegar uma unica task pelo id
 */
router.get("/getById/:id", (req, res) => {
  let sql = "SELECT * FROM tasks WHERE id = ?";

  db.query(sql, req.params, (err: any, result: any) => {
    if (err) console.log(err);
    else res.send(result);
  });  
});

/*
 * Função para criar uma task
 */
router.post("/createTask", (req, res) => {
  const { taskName } = req.body;
  const { taskDateInitial } = req.body;
  const { taskDateFinal } = req.body;

  let sql = "INSERT INTO tasks (taskName, taskDateInitial, taskDateFinal) VALUES (?, ?, ?)";

  db.query(sql, [taskName, taskDateInitial, taskDateFinal], (err: any, result: any) => {
    if (err) {
      console.log(err);
    } else {
      res.status(201).json({
        id: result.insertId,
        taskName: taskName,
        taskDateInitial: taskDateInitial,
        taskDateFinal: taskDateFinal,
      });
    }
  }) 
});

/*
 * Função para editar uma task
 */
router.put("/updateTask/:id", (req, res) => {
  const { id } = req.params;

  const { taskName } = req.body;
  const { taskDateInitial } = req.body;
  const { taskDateFinal } = req.body;

  let sql = "UPDATE tasks SET taskName = ?, taskDateInitial = ?, taskDateFinal = ? WHERE id = ?";
  
  db.query(sql, [taskName, taskDateInitial, taskDateFinal, id], (err: any, result: any) => {
    if (err) {
      console.log(err);
    } else {
      res.status(200).json({
        id: id,
        taskName: taskName,
        taskDateInitial: taskDateInitial,
        taskDateFinal: taskDateFinal,
      });
    }
  });
});

/*
 * Função para deletar uma task
 */
router.delete("/deleteTask/:id", (req, res) => {
  const { id } = req.params;

  let sql = "DELETE FROM tasks WHERE id = ?";

  db.query(sql, id, (err: any, result: any) => {
    if (err) console.log(err);
    else res.send(result);
  });
});

export default router;