import React, {useState} from "react";
import Axios from "axios";
import "./TaskRegister.css";

function TaskRegister(props) {
  const API = "http://localhost:3001/tasks";
  const [values, setValues] = useState();

  /*
   * Função para pegar o valor do input enquanto digita.
   *
   * "...prevValue"        -> Pega os valores antigos e add o os novos
   * "[value.target.name]" -> Define nome da propriedade | nome do input
   * "value.target.value"  -> Pega o valor do input
   */
  const handleChangeValues = (value) => {
    setValues(prevValue => ({
      ...prevValue,
      [value.target.name]: value.target.value
    }));
  }

  /*
   * Função para enviar os valores
   */
  const handleClickButton = () => {
    if (validData()) {
      Axios.post(API + "/createTask", {
        taskName: values.taskName,
        taskDateInitial: values.taskDateInitial,
        taskDateFinal: values.taskDateFinal
      }).then((response) => {
        if (response.status === 201) {
          props.handleTaskAdd({
            id: response.data.id,
            taskName: response.data.taskName,
            taskDateInitial: response.data.taskDateInitial,
            taskDateFinal: response.data.taskDateFinal
          });
        } else {
          alert("❗ Ops... ocorreu um erro");
        }
      });
    }
  }

  /*
   * Função para validar os valores
   */
  const validData = () => {    
    if (!values) {
      alert("⚠️ Preencha todos os campos!");
      return false;
    }

    if (!values['taskName']) {
      alert("⚠️ Nome da tarefa não pode ser vazio!");
      return false;
    } else {
      if (values['taskName'].length > 100) {
        alert("⚠️ Nome da tarefa não pode ter mais de 100 (cem) caracteres!");
        return false;
      }
    }

    if (!values['taskDateInitial']) {
      alert("⚠️ Data inicial da tarefa não pode ser vazio!");
      return false;
    } 

    if (!values['taskDateFinal']) {
      alert("⚠️ Data final da tarefa não pode ser vazio!");
      return false;
    }

    if ((new Date(values['taskDateInitial']).getTime() > 
         new Date(values['taskDateFinal']).getTime())) {
      alert("⚠️ Data inicial não pode ser maior que a data final!");
      return false;
    }

    return true;
  }

  // Interface
  return (
    <div className="register__container">
      <h1 className="register__title">Task List</h1>

      <div className="register__control">
        <input 
          type="text"
          name="taskName"
          id="taskName"
          maxLength={100}
          placeholder="Digite aqui..."
          className="register__input"
          onChange={handleChangeValues}
        />
        <label htmlFor="taskName" className="register__label">Nome da tarefa:</label>
      </div>

      <div className="register__control">
        <input 
          type="datetime-local"
          name="taskDateInitial"
          id="taskDateInitial"
          className="register__input"
          onChange={handleChangeValues}
        />
        <label htmlFor="taskDateInitial" className="register__label">Data inicial:</label>
      </div>

      <div className="register__control">
        <input 
          type="datetime-local"
          name="taskDateFinal"
          id="taskDateFinal"
          className="register__input"
          onChange={handleChangeValues}
        />
        <label htmlFor="taskDateFinal" className="register__label">Data final:</label>
      </div>

      <button 
        className="register__button"
        onClick={handleClickButton}
      >
        Cadastrar
      </button>
    </div>
  );
}

export default TaskRegister;