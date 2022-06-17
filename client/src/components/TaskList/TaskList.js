import React from "react";
import Axios from "axios";

import TaskCard from "../TaskCard/TaskCard";
import "./TaskList.css";

function TaskList(props) {
  const API = "http://localhost:3001/tasks";

  /*
   * Função para alterar a nova task
   */
  const handleTaskUpdate = (taskUpdate) => {
    if (taskUpdate.taskName && taskUpdate.taskDateInitial && taskUpdate.taskDateFinal) {
      let dataInicial = new Date(taskUpdate.taskDateInitial)
      dataInicial.setHours(dataInicial.getHours() - 3);
      dataInicial = dataInicial.toISOString();
      dataInicial = dataInicial.substring(0, dataInicial.length - 5);
      
      let dataFinal = new Date(taskUpdate.taskDateInitial)
      dataFinal.setHours(dataFinal.getHours() - 3);
      dataFinal = dataFinal.toISOString();
      dataFinal = dataFinal.substring(0, dataFinal.length - 5);
  
      Axios.put(API + "/updateTask/" + taskUpdate.id, {
        taskName: taskUpdate.taskName,
        taskDateInitial: dataInicial,
        taskDateFinal: dataFinal
      }).then((response) => {
        if (response.status === 200) {
          props.handleTaskUpdate({
            id: response.data.id,
            taskName: response.data.taskName,
            taskDateInitial: response.data.taskDateInitial,
            taskDateFinal: response.data.taskDateFinal
          });
        } else {
          alert("❗ Ops... ocorreu um erro");
          return;
        }
      });
    } else {
      alert("⚠️ Preencha os campos corretamente");
      return;
    }
  }

  /*
   * Função para alterar a nova task
   */
  const handleTaskDelete = (taskDeleteId) => {
    Axios.delete(API + "/deleteTask/" + taskDeleteId).then((response) => {
      if (response.status === 200) {
        props.handleTaskDelete({deletedTask: taskDeleteId});
      } else {
        alert("❗ Ops... ocorreu um erro");
      }
    });
  }

  // Interface
  return (
    <>
      { 
        props.listTask.map((task) => {
          return (
            <TaskCard 
              key={task.id} 
              id={task.id}
              taskName={task.taskName}
              taskDateInitial={task.taskDateInitial}
              taskDateFinal={task.taskDateFinal}
              handleTaskUpdate={handleTaskUpdate}
              handleTaskDelete={handleTaskDelete}
            ></TaskCard>
          );
        })
      }
    </>
  );
  
}

export default TaskList;