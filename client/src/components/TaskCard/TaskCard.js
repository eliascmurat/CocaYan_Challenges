import "./TaskCard.css";

import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen, faTrash } from "@fortawesome/free-solid-svg-icons";

function TaskCard(props) {

  /*
   * Formatando datas
   */
  let dataInicial = new Date(props.taskDateInitial).toLocaleString("pt-BR").toString();
  dataInicial = dataInicial.substring(0, dataInicial.length - 3);

  let dataFinal = new Date(props.taskDateFinal).toLocaleString("pt-BR").toString();
  dataFinal = dataFinal.substring(0, dataFinal.length - 3);

  /*
   * Função para editar uma tarefa 
   */
  const editTask = () => { 
    let newTaskName = window.prompt("Digite o novo nome da tarefa", props.taskName);
    let newTaskDateInitial = window.prompt("Digite o nova data inicial da tarefa (YYYY-MM-ddTHH:mm:ss)", props.taskDateInitial);
    let newTaskDateFinal = window.prompt("Digite o nova data final da tarefa (YYYY-MM-ddTHH:mm:ss)", props.taskDateFinal);

    props.handleTaskUpdate({
      id: props.id,
      taskName: newTaskName,
      taskDateInitial: new Date(newTaskDateInitial),
      taskDateFinal: new Date(newTaskDateFinal)
    });
  }

  /*
   * Função para deletar uma tarefa 
   */
  const deleteTask = () => {
    if (window.confirm("Deseja deletar essa tarefa?")) {
      props.handleTaskDelete(props.id);
    } else {
      return;
    }
  }

  // Interface
  return (
    <div className="card__container">
      <input type="checkbox" name="complete" id="complete" className="card__checkbox"></input>
      
      <div className="card__description">
        <h2 className="card__description-text">{props.taskName}</h2>
        <p className="card__description-time">
          <time>{dataInicial}</time> - <time>{dataFinal}</time>
        </p>
      </div>
      
      <div className="card__actions">
        <button className="card__actions-edit" onClick={editTask}>
          <FontAwesomeIcon icon={faPen} />
        </button>
        
        <button className="card__actions-delete" onClick={deleteTask}>
          <FontAwesomeIcon icon={faTrash} />
        </button>
      </div>
    </div>
  );
}

export default TaskCard;