import React, { useState, useEffect } from "react";
import Axios from "axios";

import TaskRegister from './components/TaskRegister/TaskRegister';
import TaskList from './components/TaskList/TaskList';
import './App.css';

function App() {
  const API = "http://localhost:3001/tasks";
  const [listTask, setListTask] = useState([]);
  
  /*
   * Função para pegar todas as tasks
   */
  useEffect(() => {
    Axios.get(API + "/getAll").then((response) => {
      setListTask(response.data);
    });
  }, []);

  /*
   * Função para adicionar task
   */
  const handleTaskAdd = (newTask) => {
    const newTasks = [
      ...listTask,
      {
        id: newTask.id,
        taskName: newTask.taskName,
        taskDateInitial: newTask.taskDateInitial,
        taskDateFinal: newTask.taskDateFinal
      }
    ];

    setListTask(newTasks);
  }

  /*
   * Função para alterar a task
   */
  const handleTaskUpdate = (updatedTask) => {
    listTask.forEach(task => {
      if (task.id === parseInt(updatedTask.id)) {
        task.taskName = updatedTask.taskName;
        task.taskDateInitial = updatedTask.taskDateInitial;
        task.taskDateFinal = updatedTask.taskDateFinal;
      }
    });

    const updatingTask = listTask.slice();
    
    setListTask(updatingTask);
  }

  /*
   * Função para alterar a task
   */
  const handleTaskDelete = (deletedTask) => {
    const updatingTask = listTask.filter(task => {
      return task.id !== deletedTask.deletedTask;
    });

    setListTask(updatingTask);
  }

  // Interface
  return (
    <div className="app__container">
      <TaskRegister handleTaskAdd={handleTaskAdd}></TaskRegister>
      <TaskList 
        listTask={listTask}
        handleTaskUpdate={handleTaskUpdate}
        handleTaskDelete={handleTaskDelete}
      ></TaskList>
    </div>
  );
}

export default App;